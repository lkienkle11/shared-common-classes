package com.microservices.dto.notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StreakNotificationData {
    String message;

    String userId;

    Long currentStreak;

    LocalDate lastDateLearned;

    Map<String, Object> payload;
}