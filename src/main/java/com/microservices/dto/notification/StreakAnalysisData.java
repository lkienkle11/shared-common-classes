package com.microservices.dto.notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StreakAnalysisData {
    Map<String, Object> userInfo;
    String userTz;
    String clientDate;
    Long currentStreak;
}