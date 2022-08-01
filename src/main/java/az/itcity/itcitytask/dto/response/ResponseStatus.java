package az.itcity.itcitytask.dto.response;

import az.itcity.itcitytask.exception.ExceptionCode;
import az.itcity.itcitytask.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseStatus {
    private String message;
    private int code;

    public static ResponseStatus getSuccess(){
        return new ResponseStatus(ExceptionMessage.SUCCESS, ExceptionCode.SUCCESS);
    }
}
