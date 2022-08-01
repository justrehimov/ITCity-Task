package az.itcity.itcitytask.controller;

import az.itcity.itcitytask.dto.request.FilterRequest;
import az.itcity.itcitytask.dto.request.UserRequest;
import az.itcity.itcitytask.dto.response.ResponseModel;
import az.itcity.itcitytask.dto.response.SimpleUserResponse;
import az.itcity.itcitytask.dto.response.UserResponse;
import az.itcity.itcitytask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseModel<SimpleUserResponse> save(@Valid @RequestBody UserRequest userRequest){
        return userService.save(userRequest);
    }

    @PostMapping("/{id}")
    public ResponseModel<UserStatusResponse> changeStatus(@PathVariable("id") Long id){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return userService.changeStatus(id);
    }

    @GetMapping("/{id}")
    public ResponseModel<UserResponse> getUser(@PathVariable("id") Long id){
        return userService.getUser(id);
    }


    @GetMapping("/filter")
    private ResponseModel<List<UserResponse>> filterUsers(@RequestBody FilterRequest filterRequest){
        return userService.filterUsers(filterRequest);
    }
}
