package org.essaadani.springbootrediscaching.service;

import org.essaadani.springbootrediscaching.entities.UserEntity;
import org.essaadani.springbootrediscaching.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String loginAndGetToken(String username, String password) {
        // check if token is already cached
        String cachedToken = getCachedToken(username);
        String generatedToken = "";

        if (cachedToken != null)
            return cachedToken;

        // authentication logic
        UserEntity user = userRepository.findByUsername(username);


        if (user != null && password != null && password.equals(user.getPassword())) {
            generatedToken = generateRandomToken();

            cacheToken(username, generatedToken);
        }

        return generatedToken;
    }

    @Override
    public String getCachedToken(String username) {
        return redisTemplate.opsForValue().get("token:" + username);
    }

    @Override
    public void cacheToken(String username, String token) {
        // cache token to redis with time to live of 60 minutes
        redisTemplate.opsForValue().set("token:" + username, token, Duration.ofMinutes(60));
    }

    @Override
    public String generateRandomToken() {
        return UUID.randomUUID().toString();
    }
}
