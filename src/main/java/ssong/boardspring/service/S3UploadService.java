package ssong.boardspring.service;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface S3UploadService {
    //파일 저장
    Map<String, Object> saveFile(MultipartFile multipartFile) throws IOException;

    //파일 다운로드
    ResponseEntity<UrlResource> downloadFile(String originalFileName);

    //파일 삭제
    void deleteImage(String originalFilename);

}
