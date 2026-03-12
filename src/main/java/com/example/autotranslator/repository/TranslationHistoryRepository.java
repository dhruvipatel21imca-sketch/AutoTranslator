package com.example.autotranslator.repository;

import com.example.autotranslator.model.TranslationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationHistoryRepository extends JpaRepository<TranslationHistory,Long>{
}