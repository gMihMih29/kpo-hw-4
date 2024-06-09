package ru.hse.bpi223.hw4.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hse.bpi223.hw4.exceptions.UserNotFoundException;
import ru.hse.bpi223.hw4.models.Session;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.repositories.SessionRepository;

import java.sql.Timestamp;
import java.util.*;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;
    @Value("${token.validity.duration}")
    private int jwtValidityDuration;

    private final SessionRepository sessionRepository;

    public JwtServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public boolean validate(String token, User usr) {
        Claims claims = extractAllClaims(token);
        if (!Objects.equals(claims.getIssuer(), usr.getNickname())) {
            return false;
        }
        if (!Objects.equals(claims.get("email", String.class), usr.getEmail())) {
            return false;
        }
        return claims.getExpiration().before(new Date());
    }

    public Session findByUser(User usr) {
        removeInvalidSessions(usr);
        List<Session> sessions = sessionRepository.findByUser(usr);
        if (sessions.isEmpty()) {
            return null;
        }
        return sessions.get(0);
    }

    @Override
    public User findUserByToken(String token) throws UserNotFoundException {
        Session session = sessionRepository.findByToken(token);
        if (session == null) {
            throw new UserNotFoundException("No user exists with such token");
        }
        if (isExpired(session)) {
            sessionRepository.delete(session);
            throw new UserNotFoundException("No user exists with such token");
        }
        return session.getUser();
    }

    @Override
    public String generateToken(User usr) {
        removeInvalidSessions(usr);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usr.getId());
        claims.put("email", usr.getEmail());
        Timestamp issuedAt = new Timestamp(System.currentTimeMillis());
        Timestamp expiresAt = new Timestamp(System.currentTimeMillis() + jwtValidityDuration);
        String token = generateToken(claims, usr, issuedAt, expiresAt);
        Session session = new Session(usr, token, expiresAt);
        sessionRepository.save(session);
        return token;
    }

    private String generateToken(Map<String, Object> extraClaims, User usr, Timestamp issuedAt, Timestamp expiresAt) {
        return Jwts.builder().setClaims(extraClaims).setSubject(usr.getNickname())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey).compact();
    }

    private void removeInvalidSessions(User usr) {
        List<Session> sessions = sessionRepository.findByUser(usr);
        for (Session s: sessions) {
            if (validate(s.getToken(), usr)) {
                sessionRepository.delete(s);
            }
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSigningKey).build().parseClaimsJws(token)
                .getBody();
    }

    private boolean isExpired(Session session) {
        return session.getExpires().before(new Date());
    }
}