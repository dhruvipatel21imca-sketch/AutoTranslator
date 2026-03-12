package com.example.autotranslator.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TranslationController {

    private static final String LIBRE_TRANSLATE_URL = "https://libretranslate.com/translate";

    @GetMapping("/api/translate")
    public Map<String, String> translate(
            @RequestParam String text,
            @RequestParam String source,
            @RequestParam String target) {

        RestTemplate restTemplate = new RestTemplate();

        // Prepare request body
        Map<String, Object> body = new HashMap<>();
        body.put("q", text);
        body.put("source", source);
        body.put("target", target);
        body.put("format", "text");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        // Send POST request
        ResponseEntity<Map> response = restTemplate.postForEntity(LIBRE_TRANSLATE_URL, request, Map.class);

        // Extract translated text
        String translatedText = response.getBody() != null ? (String) response.getBody().get("translatedText") : "";

        Map<String, String> result = new HashMap<>();
        result.put("sourceText", text);
        result.put("translatedText", translatedText);
        result.put("sourceLanguage", source);
        result.put("targetLanguage", target);

        return result;
    }
}