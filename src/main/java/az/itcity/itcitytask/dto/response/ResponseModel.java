package az.itcity.itcitytask.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseModel <T>{
    private T response;
    private ResponseStatus status;
}
