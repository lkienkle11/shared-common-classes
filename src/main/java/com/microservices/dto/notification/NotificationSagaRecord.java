package com.microservices.dto.notification;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationSagaRecord {
    String sagaId;                     // UUID
    Long   userId;
    StreakNotificationData payload;    // giữ lại để retry / đền bù
    int    attempt;                    // số lần gửi
    long   expireAt;                   // epoch millis timeout
}
