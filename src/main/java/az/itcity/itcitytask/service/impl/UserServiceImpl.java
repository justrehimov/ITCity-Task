package az.itcity.itcitytask.service.impl;

import az.itcity.itcitytask.controller.UserStatusResponse;
import az.itcity.itcitytask.dto.request.FilterRequest;
import az.itcity.itcitytask.dto.request.UserRequest;
import az.itcity.itcitytask.dto.response.ResponseModel;
import az.itcity.itcitytask.dto.response.ResponseStatus;
import az.itcity.itcitytask.dto.response.SimpleUserResponse;
import az.itcity.itcitytask.dto.response.UserResponse;
import az.itcity.itcitytask.entity.User;
import az.itcity.itcitytask.entity.UserStatus;
import az.itcity.itcitytask.enums.EnumStatus;
import az.itcity.itcitytask.exception.CustomException;
import az.itcity.itcitytask.exception.ExceptionCode;
import az.itcity.itcitytask.exception.ExceptionMessage;
import az.itcity.itcitytask.repository.StatusRepository;
import az.itcity.itcitytask.repository.UserRepository;
import az.itcity.itcitytask.repository.UserStatusRepository;
import az.itcity.itcitytask.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final StatusRepository statusRepository;
    private final UserStatusRepository userStatusRepository;
    @Override
    public ResponseModel<SimpleUserResponse> save(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        user.setCreatedAt(new Date());
        User savedUser = userRepository.save(user);
        UserStatus userStatus = new UserStatus();
        userStatus.setStatus(statusRepository.getStatusByStatus(EnumStatus.ONLINE.getStatus()));
        userStatus.setUser(user);
        userStatus.setStatusChangedDate(new Timestamp(new Date().getTime()));
        UserStatus savedUserStatus = userStatusRepository.save(userStatus);
        user.setUserStatus(savedUserStatus);
        userRepository.save(user);
        log.info("User saved id:{}",user.getId());
        return ResponseModel.<SimpleUserResponse>builder()
                .response(new SimpleUserResponse(savedUser.getId()))
                .status(ResponseStatus.getSuccess())
                .build();
    }

    @Override
    public ResponseModel<UserResponse> getUser(Long id) {
        log.info("User pulling from database id:{}", id);
        User user = getUserById(id);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setStatus(user.getUserStatus().getStatus().getStatus());
        return ResponseModel.<UserResponse>builder()
                .response(userResponse)
                .status(ResponseStatus.getSuccess())
                .build();
    }

    @Override
    @Transactional
    public ResponseModel<UserStatusResponse> changeStatus(Long id) {
        log.info("User status is changing id:{}",id);
        User user = getUserById(id);
        String oldStatus = user.getUserStatus().getStatus().getStatus();
        if (EnumStatus.ONLINE.getStatus().equals(oldStatus)) {
            user.getUserStatus().setStatus(statusRepository.getStatusByStatus(EnumStatus.OFFLINE.getStatus()));
            user.getUserStatus().setStatusChangedDate(new Timestamp(new Date().getTime()));
        } else {
            user.getUserStatus().setStatus(statusRepository.getStatusByStatus(EnumStatus.ONLINE.getStatus()));
            user.getUserStatus().setStatusChangedDate(new Timestamp(new Date().getTime()));
        }
        UserStatusResponse userStatusResponse = new UserStatusResponse();
        userStatusResponse.setId(user.getId());
        userStatusResponse.setOldStatus(oldStatus);
        userStatusResponse.setCurrentStatus(user.getUserStatus().getStatus().getStatus());
        return ResponseModel.<UserStatusResponse>builder()
                .status(ResponseStatus.getSuccess())
                .response(userStatusResponse)
                .build();
    }

    @Override
    @Transactional
    public ResponseModel<List<UserResponse>> filterUsers(FilterRequest filterRequest) {
        log.info("Users pulling from database and filtering");
        List<User> users;
        if(filterRequest.getTimeStamp()!=null && filterRequest.getStatusId() != null){
            users = userRepository.findAll().stream()
                    .filter(u->(u.getUserStatus().getStatus().getId().equals(filterRequest.getStatusId()) &&
                            u.getUserStatus().getStatusChangedDate().after(parseToDate(filterRequest.getTimeStamp()))))
                    .collect(Collectors.toList());
        }else if(filterRequest.getStatusId()!=null){
            users = userRepository.findAll().stream()
                    .filter(u->u.getUserStatus().getStatus().getId().equals(filterRequest.getStatusId()))
                    .collect(Collectors.toList());
        }else if(filterRequest.getTimeStamp()!=null){
            users = userRepository.findAll().stream()
                    .filter(u-> u.getUserStatus().getStatusChangedDate().after(parseToDate(filterRequest.getTimeStamp())))
                    .collect(Collectors.toList());
        }else{
            users = userRepository.findAll();
        }
        List<UserResponse> userResponses = users.stream()
                .map(u->{
                    UserResponse userResponse = modelMapper.map(u, UserResponse.class);
                    userResponse.setStatus(u.getUserStatus().getStatus().getStatus());
                    return userResponse;
                }).collect(Collectors.toList());
        return ResponseModel.<List<UserResponse>>builder()
                .response(userResponses)
                .status(ResponseStatus.getSuccess())
                .build();
    }

    private User getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new CustomException(ExceptionMessage.USER_NOT_FOUND, ExceptionCode.USER_NOT_FOUND));
        return user;
    }

    private Timestamp parseToDate(String timeStamp){
            return Timestamp.valueOf(timeStamp);
    }

}
