package com.example.autotranslator.repository;

import com.example.autotranslator.model.TranslationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationHistoryRepository extends JpaRepository<TranslationHistory, Long> {
    // No extra code needed, JpaRepository gives basic CRUD
}