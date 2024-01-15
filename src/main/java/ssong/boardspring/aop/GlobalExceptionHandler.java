package ssong.boardspring.aop;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleValidationException(MethodArgumentNotValidException ex, HandlerMethod method) {
        Method controllerMethod = method.getMethod();
        if(controllerMethod.getName().equals("createMember")) {
            ModelAndView mv = new ModelAndView("member/createMemberForm");
            mv.addObject("error", "형식에 맞는 정보를 입력하시기 바랍니다.");
            return mv;
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효성 검사에 실패하였습니다.");
        }
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 처리 중 오류가 발생하였습니다.");
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUploadException(FileUploadException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류가 발생하였습니다.");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 리소스를 찾을 수 없습니다.");
    }

    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<String> handleMalformedURLException(MalformedURLException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 URL 형식의 오류가 발생하였습니다.");
    }

    @ExceptionHandler(UnsupportedEncodingException.class)
    public ResponseEntity<String> handleUnsupportedEncodingException(UnsupportedEncodingException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("지원되지 않는 인코딩 오류가 발생했습니다.");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("요청한 리소스를 찾을 수 없습니다.");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("error", "데이터 처리 중 오류가 발생했습니다.");
        return "errorPage";
    }

    @ExceptionHandler(DataAccessException.class)
    public String handleDataAccessException(DataAccessException ex, Model model) {
        model.addAttribute("error", "데이터 처리 중 오류가 발생했습니다.");
        return "errorPage";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", "잘못된 요청입니다.");
        return "errorPage";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        model.addAttribute("error", "시스템 오류가 발생했습니다.");
        return "errorPage";
    }
}
