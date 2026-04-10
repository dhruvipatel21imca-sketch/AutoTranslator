package com.example.autotranslator.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/translator")
public class TranslationController {

    @GetMapping("/translate")
    public Map<String, String> translate(@RequestParam String text,
                                         @RequestParam String targetLanguage) {

        Map<String, String> response = new HashMap<>();

        try {
            // Encode text for URL
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);

            // MyMemory API URL
            String url = "https://api.mymemory.translated.net/get?q="
                    + encodedText + "&langpair=en|" + targetLanguage;

            RestTemplate restTemplate = new RestTemplate();

            Map apiResponse = restTemplate.getForObject(url, Map.class);

            // Extract translated text
            Map responseData = (Map) apiResponse.get("responseData");
            String translatedText = responseData.get("translatedText").toString();

            response.put("translatedText", translatedText);

        } catch (Exception e) {
            response.put("translatedText", "Error: " + e.getMessage());
        }

        return response;
    }
}