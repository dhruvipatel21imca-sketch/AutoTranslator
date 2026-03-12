package com.example.autotranslator.service;

import com.example.autotranslator.model.TranslationHistory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranslationService {

    private final List<TranslationHistory> historyList = new ArrayList<>();


    public String translate(String text, String language) {

        String translatedText = text + " (Translated to " + language + ")";

        saveHistory(language);

        return translatedText;
    }


    public void saveHistory(String language) {

        TranslationHistory history =
                new TranslationHistory(language);

        historyList.add(history);
    }


    public List<TranslationHistory> getAllHistory() {
        return historyList;
    }
}
