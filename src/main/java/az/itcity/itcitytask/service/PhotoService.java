package az.itcity.itcitytask.service;

import az.itcity.itcitytask.dto.response.PhotoResponse;
import az.itcity.itcitytask.dto.response.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    ResponseModel<PhotoResponse> upload(MultipartFile multipartFile);
}
