package kz.yandex_practicum.sprint3.telemetry_ms.dto;
        
public enum QueueItemStatus {
    PENDING("pending"),
    COMPLETED("completed");
    
    private String value;

    QueueItemStatus(String value) {
      this.value = value;
    }
}