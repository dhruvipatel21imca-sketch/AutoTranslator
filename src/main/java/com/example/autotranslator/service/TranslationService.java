package com.example.autotranslator.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TranslationService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String translate(String text, String source, String target) {
        Map<String, String> body = new HashMap<>();
        body.put("q", text);
        body.put("source", source);
        body.put("target", target);
        body.put("format", "text");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        String LIBRE_URL = "https://libretranslate.com/translate";
        Map response = restTemplate.postForObject(LIBRE_URL, request, Map.class);

        assert response != null;
        return response.get("translatedText").toString();
    }
}