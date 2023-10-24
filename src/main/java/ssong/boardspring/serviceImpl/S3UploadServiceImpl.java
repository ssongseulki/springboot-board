package ssong.boardspring.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ssong.boardspring.service.S3UploadService;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class S3UploadServiceImpl implements S3UploadService {
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public S3UploadServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    //     파일 저장
    public Map<String, Object> saveFile(MultipartFile multipartFile) throws IOException {
        Map<String, Object> param = new HashMap<>();
        String folderName = "board";        //  S3 버킷 폴더 이름
        String buildFileName = buildFileName(multipartFile.getOriginalFilename());      //  파일명 중복 방지를 위해 현재 시간 추가
        String filePath = folderName + "/" + buildFileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());     //  파일 크기 설정
        metadata.setContentType(multipartFile.getContentType());    //  파일 컨텐츠  타입

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, filePath, inputStream, metadata)    //  S3 버킷에 파일 업로드
                    .withCannedAcl(CannedAccessControlList.PublicRead));    //  공개 읽기 액세스 권한 부여
        } catch (IOException e) {
            throw new FileUploadException();
        }

        param.put("filePath", amazonS3.getUrl(bucket, filePath).toString());
        param.put("fileName", multipartFile.getOriginalFilename());
        param.put("s3fileName", buildFileName);
        return param;
    }


    //     파일 다운로드
    public ResponseEntity<UrlResource> downloadFile(String originalFileName) {
        String filePath = "board/" + originalFileName;
        //  URLResource 생성, S3 버킷 내 파일에 대한 UrlResource 생성 > 파일을 읽어 옴
        UrlResource urlResource = new UrlResource(amazonS3.getUrl(bucket, filePath));

        try {
            //  UTF-8로 파일 이름을 인코딩
            originalFileName = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8.toString());
            //  Content-Disposition 헤더 설정, 브라우저에서 파일 다운로드 시 파일 이름
            String contentDisposition = "attachment; filename*=UTF-8''" + originalFileName;

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(urlResource);     //  파일 다운로드 URL 반환
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    //     파일 삭제
    public void deleteImage(String originalFilename) {
        originalFilename = "board/" + originalFilename;
        amazonS3.deleteObject(bucket, originalFilename);
    }

    //     S3에 저장 될 파일 이름 빌드
    String buildFileName(String originalFileName) {
        //  파일 이름과 확장자 추출 후 현재 시각 추가하여 저장 할 파일 이름 변경
        final String FILE_EXTENSION_SEPARATOR = ".";
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());

        return fileName + timestamp + fileExtension;
    }

}
