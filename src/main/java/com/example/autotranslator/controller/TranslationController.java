package com.example.autotranslator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            // Just return input for testing
            result.put("translatedText", "OK: " + text);
        } catch (Exception e) {
            result.put("translatedText", "ERROR");
        }

        return result;
    }
}