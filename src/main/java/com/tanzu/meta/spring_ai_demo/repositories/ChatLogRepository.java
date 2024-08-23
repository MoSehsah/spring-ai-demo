package com.tanzu.meta.spring_ai_demo.repositories;

import com.tanzu.meta.spring_ai_demo.models.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
}