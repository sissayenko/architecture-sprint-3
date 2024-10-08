package kz.yandex_practicum.sprint3.profile_management_ms.dto;

import lombok.Data;

@Data
public class HouseResponse {

    private String id;
    private String householderId;
    private String address;
    private Boolean isEnabled;

}
