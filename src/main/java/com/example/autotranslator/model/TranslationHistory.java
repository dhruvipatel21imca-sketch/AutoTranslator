package com.example.autotranslator.model;

import jakarta.persistence.*;

@Entity
public class TranslationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceText;

    private String translatedText;

    private String targetLanguage;

    public TranslationHistory(String language) {
    }

    public TranslationHistory(String sourceText, String translatedText, String targetLanguage) {
        this.sourceText = sourceText;
        this.translatedText = translatedText;
        this.targetLanguage = targetLanguage;
    }

    public TranslationHistory() {

    }

    public Long getId() {
        return id;
    }

    public String getSourceText() {
        return sourceText;
    }

    public void setSourceText(String sourceText) {
        this.sourceText = sourceText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }
}