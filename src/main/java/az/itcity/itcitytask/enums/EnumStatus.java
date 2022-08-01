package az.itcity.itcitytask.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumStatus {
    ONLINE("online"),OFFLINE("offline");
    private String status;
}
