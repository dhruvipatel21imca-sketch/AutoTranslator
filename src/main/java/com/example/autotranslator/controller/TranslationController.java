package com.example.autotranslator.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/translator")
public class TranslationController {

    @GetMapping("/translate")
    public Map<String, String> translate(@RequestParam String text,
                                         @RequestParam String targetLanguage) {

        Map<String, String> response = new HashMap<>();

        // Temporary translation (for testing)
        String translated = "Translated to " + targetLanguage + ": " + text;

        response.put("translatedText", translated);

        return response;
    }
}