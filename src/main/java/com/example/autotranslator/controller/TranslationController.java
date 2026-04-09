package com.example.autotranslator.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TranslationController {

    private static final String API_URL =
            "https://api.mymemory.translated.net/get";

    @GetMapping("/api/translate")
    public Map<String, String> translate(
            @RequestParam String text,
            @RequestParam String source,
            @RequestParam String target) {

        Map<String, String> result = new HashMap<>();

        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = API_URL +
                    "?q=" + text +
                    "&langpair=" + source + "|" + target;

            Map response = restTemplate.getForObject(url, Map.class);

            String translatedText = "";

            if (response != null && response.get("responseData") != null) {
                Map data = (Map) response.get("responseData");
                translatedText = (String) data.get("translatedText");
            }

            result.put("translatedText", translatedText);

        } catch (Exception e) {
            result.put("translatedText", "Error occurred");
        }

        return result;
    }
}