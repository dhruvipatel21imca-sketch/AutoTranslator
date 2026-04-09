package com.example.autotranslator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

            String url = String.format(
                    "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s",
                    source,
                    target,
                    java.net.URLEncoder.encode(text, java.nio.charset.StandardCharsets.UTF_8)
            );

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            // ✅ SAFE check (prevents crash)
            if (response != null && response.contains("\"")) {

                String translated = response.split("\"")[1];
                result.put("translatedText", translated);

            } else {

                result.put("translatedText", "Translation failed");

            }

        } catch (Exception e) {

            result.put("translatedText", "Error translating");

        }

        return result;
    }
}