package com.example.autotranslator.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/translator")
public class TranslationController {

    @GetMapping("/translate")
    public String translate(@RequestParam String text, @RequestParam String targetLanguage) {
        return "Translated (" + targetLanguage + "): " + text;
    }

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