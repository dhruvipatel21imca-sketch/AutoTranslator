package com.example.autotranslator.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TranslationController {

    private static final String LIBRE_TRANSLATE_URL = "https://libretranslate.de/translate";

    // Translation API
    @GetMapping("/api/translate")
    public Map<String, String> translate(
            @RequestParam String text,
            @RequestParam String source,
            @RequestParam String target) {

        Map<String, String> result = new HashMap<>();

        try {

            RestTemplate restTemplate = new RestTemplate();

            Map<String, Object> body = new HashMap<>();
            body.put("q", text);
            body.put("source", source);
            body.put("target", target);
            body.put("format", "text");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response =
                    restTemplate.postForEntity(LIBRE_TRANSLATE_URL, request, Map.class);

            String translatedText = "";

            if (response.getBody() != null) {
                translatedText = (String) response.getBody().get("translatedText");
            }

            result.put("translatedText", translatedText);

        } catch (Exception e) {

            e.printStackTrace();   // helps debug on Render
            result.put("translatedText", "Translation service unavailable");

        }

        return result;
    }
}