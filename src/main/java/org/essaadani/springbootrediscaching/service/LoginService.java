package org.essaadani.springbootrediscaching.service;

public interface LoginService {
    String loginAndGetToken(String username, String password);
    String getCachedToken(String username);
    void cacheToken(String username, String token);
    String generateRandomToken();
}
