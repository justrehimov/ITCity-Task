package az.itcity.itcitytask.dto.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FilterRequest {
    private Long statusId;
    private String timeStamp;
}
