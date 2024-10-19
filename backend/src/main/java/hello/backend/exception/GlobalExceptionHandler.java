package hello.backend.exception;

import hello.backend.config.ErrorMessagesLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorMessagesLoader errorMessagesLoader;

    public GlobalExceptionHandler(ErrorMessagesLoader errorMessagesLoader) {
        this.errorMessagesLoader = errorMessagesLoader;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // JSON에서 에러 메시지 불러오기
        Map<String, String> errorInfo = errorMessagesLoader.getErrorMessage("ResourceNotFoundException");

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errorCode", errorInfo.get("errorCode"));
        body.put("errorMessage", errorInfo.get("errorMessage"));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        // JSON에서 일반 에러 메시지 불러오기
        Map<String, String> errorInfo = errorMessagesLoader.getErrorMessage("GeneralException");

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("errorCode", errorInfo.get("errorCode"));
        body.put("errorMessage", errorInfo.get("errorMessage"));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
