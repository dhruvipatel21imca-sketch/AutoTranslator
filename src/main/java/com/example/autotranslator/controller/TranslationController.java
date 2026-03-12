package com.example.autotranslator.controller;

import com.example.autotranslator.model.TranslationHistory;
import com.example.autotranslator.repository.TranslationHistoryRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/translator")
public class TranslationController {

    private final TranslationHistoryRepository repository;

    public TranslationController(TranslationHistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/translate")
    public String translate(@RequestParam String text,
                            @RequestParam String targetLanguage) {

        try {

            String url = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=auto&tl="
                    + targetLanguage + "&dt=t&q=" + text;

            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            if (response == null) {
                return "Translation failed";
            }

            String translated = response.split("\"")[1];

            TranslationHistory history =
                    new TranslationHistory(text, translated, targetLanguage);

            repository.save(history);

            return translated;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error translating text";
        }
    }
}