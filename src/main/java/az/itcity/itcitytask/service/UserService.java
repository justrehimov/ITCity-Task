package az.itcity.itcitytask.service;

import az.itcity.itcitytask.controller.UserStatusResponse;
import az.itcity.itcitytask.dto.request.FilterRequest;
import az.itcity.itcitytask.dto.request.UserRequest;
import az.itcity.itcitytask.dto.response.ResponseModel;
import az.itcity.itcitytask.dto.response.SimpleUserResponse;
import az.itcity.itcitytask.dto.response.UserResponse;

import java.util.List;


public interface UserService {
    ResponseModel<SimpleUserResponse> save(UserRequest userRequest);

    ResponseModel<UserResponse> getUser(Long id);

    ResponseModel<UserStatusResponse> changeStatus(Long id);

    ResponseModel<List<UserResponse>> filterUsers(FilterRequest filterRequest);
}
