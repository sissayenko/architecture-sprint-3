package kz.yandex_practicum.sprint3.profile_management_ms.dto;

import lombok.Data;

@Data
public class HouseholderResponse {

    private String id;
    private String name;
    private String email;
    private Boolean isEnabled;

}
