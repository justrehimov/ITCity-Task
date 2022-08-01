package az.itcity.itcitytask.service.impl;

import az.itcity.itcitytask.dto.response.PhotoResponse;
import az.itcity.itcitytask.dto.response.ResponseModel;
import az.itcity.itcitytask.dto.response.ResponseStatus;
import az.itcity.itcitytask.entity.Photo;
import az.itcity.itcitytask.exception.CustomException;
import az.itcity.itcitytask.exception.ExceptionCode;
import az.itcity.itcitytask.exception.ExceptionMessage;
import az.itcity.itcitytask.repository.PhotoRepository;
import az.itcity.itcitytask.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    @Value("${domain}")
    private String domain;

    @Override
    public ResponseModel<PhotoResponse> upload(MultipartFile multipartFile) {
        log.info("Photo uploading to folder fileName:{}", multipartFile.getOriginalFilename());
        if(multipartFile.isEmpty() || !isValidContentType(multipartFile))
            throw new CustomException(ExceptionMessage.UNSUPPORTED_FILE, ExceptionCode.UNSUPPORTED_FILE);
        String fileName = copyFileToDirectory(multipartFile);
        String photoUrl = domain + "/" + fileName;
        Photo photo = new Photo();
        photo.setUrl(photoUrl);
        photo.setCreatedAt(new Date());
        Photo savedPhoto = photoRepository.save(photo);
        return ResponseModel.<PhotoResponse>builder()
                .response(new PhotoResponse(savedPhoto.getUrl()))
                .status(ResponseStatus.getSuccess())
                .build();
    }

    private boolean isValidContentType(MultipartFile multipartFile){
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        return extension.equals("jpg") || extension.equals("image/jpeg");
    }

    private String copyFileToDirectory(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        File file = new File("src/main/resources/upload/" + fileName);
        try {
            file.createNewFile();
            InputStream is = multipartFile.getInputStream();
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            is.close();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }
}
