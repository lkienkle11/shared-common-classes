package com.microservices.dto.notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationSagaRecord {
    String                 sagaId;   // UUID
    String                 userId;
    StreakNotificationData payload;  // giữ lại để retry / đền bù
    Integer                attempt;  // số lần gửi
    Long                   expireAt; // epoch millis timeout
}
