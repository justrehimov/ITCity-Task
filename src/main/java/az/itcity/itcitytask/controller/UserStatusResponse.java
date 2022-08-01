package az.itcity.itcitytask.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStatusResponse {
    private Long id;
    private String oldStatus;
    private String currentStatus;
}
