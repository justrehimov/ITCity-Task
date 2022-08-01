package az.itcity.itcitytask.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRequest {
    @NotNull
    @NotBlank
    @Size(min = 3, message = "Full name cannot be less than 3 characters")
    private String fullName;
    @NotNull
    @NotBlank
    @Size(min = 5, message = "Email cannot be less than 3 characters")
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 5,message = "Photo url cannot be less than 5")
    private String photoUrl;
}
