package com.example.autotranslator.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TranslationController {

    @GetMapping("/api/translate")
    public Map<String, String> translate(
            @RequestParam String text,
            @RequestParam String source,
            @RequestParam String target) {

        Map<String, String> result = new HashMap<>();

        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = "https://api.mymemory.translated.net/get?q="
                    + text + "&langpair=" + source + "|" + target;

            ResponseEntity<Map> response =
                    restTemplate.getForEntity(url, Map.class);

            Map body = response.getBody();

            String translatedText = "";

            // First try main response
            if (body != null && body.get("responseData") != null) {
                Map responseData = (Map) body.get("responseData");
                translatedText = (String) responseData.get("translatedText");
            }

            // If empty → use matches (backup)
            if (translatedText == null || translatedText.isEmpty()) {

                if (body != null && body.get("matches") != null) {
                    java.util.List matches = (java.util.List) body.get("matches");

                    if (!matches.isEmpty()) {
                        Map firstMatch = (Map) matches.get(0);
                        translatedText = (String) firstMatch.get("translation");
                    }
                }
            }

            result.put("translatedText", translatedText);

        } catch (Exception e) {
            result.put("translatedText", "Translation failed");
        }

        return result;
    }}