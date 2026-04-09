package com.example.autotranslator.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TranslationController {

    @GetMapping("/")
    public String home() {
        return "AutoTranslator is running 🚀";
    }

    @GetMapping("/api/translate")
    public Map<String, String> translate(
            @RequestParam String text,
            @RequestParam String source,
            @RequestParam String target) {

        Map<String, String> result = new HashMap<>();

        try {

            RestTemplate restTemplate = new RestTemplate();

            String url = "https://api.mymemory.translated.net/get?q="
                    + java.net.URLEncoder.encode(text, java.nio.charset.StandardCharsets.UTF_8)
                    + "&langpair=" + source + "|" + target;

            ResponseEntity<Map> response =
                    restTemplate.getForEntity(url, Map.class);

            String translatedText = "Translation failed";

            // ✅ SAFE null checks
            if (response.getBody() != null) {

                Map body = response.getBody();

                // First try main response
                if (body.containsKey("responseData")) {
                    Map responseData = (Map) body.get("responseData");

                    if (responseData != null && responseData.get("translatedText") != null) {
                        translatedText = responseData.get("translatedText").toString();
                    }
                }

                // Backup: use matches
                if (translatedText.equals("Translation failed") && body.containsKey("matches")) {

                    java.util.List matches = (java.util.List) body.get("matches");

                    if (matches != null && !matches.isEmpty()) {
                        Map firstMatch = (Map) matches.get(0);

                        if (firstMatch.get("translation") != null) {
                            translatedText = firstMatch.get("translation").toString();
                        }
                    }
                }
            }

            result.put("translatedText", translatedText);

        } catch (Exception e) {

            result.put("translatedText", "Error translating");

        }

        return result;
    }}