package az.itcity.itcitytask.controller;

import az.itcity.itcitytask.dto.response.PhotoResponse;
import az.itcity.itcitytask.dto.response.ResponseModel;
import az.itcity.itcitytask.service.PhotoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;

    @PostMapping(value = "/upload",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseModel<PhotoResponse> upload(@RequestPart("photo") MultipartFile multipartFile){
        return photoService.upload(multipartFile);
    }
}
