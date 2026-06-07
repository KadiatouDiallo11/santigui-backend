package com.backend.code.services;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

    private final Map<String, Long> blacklistedTokens = new ConcurrentHashMap<>();

    public void blacklist(String token, Date expiration) {
        cleanupExpiredTokens();
        blacklistedTokens.put(token, expiration.getTime());
    }

    public boolean isBlacklisted(String token) {
        cleanupExpiredTokens();
        Long expiration = blacklistedTokens.get(token);

        if (expiration == null) {
            return false;
        }

        if (expiration <= System.currentTimeMillis()) {
            blacklistedTokens.remove(token);
            return false;
        }

        return true;
    }

    private void cleanupExpiredTokens() {
        long now = System.currentTimeMillis();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue() <= now);
    }
}