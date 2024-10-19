package hello.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class ErrorMessagesLoader {

    private Map<String, Map<String, String>> errorMessages;

    @PostConstruct
    public void init() throws IOException {
        // resources 폴더에서 JSON 파일 읽기
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/error/errorMessages.json");
        errorMessages = mapper.readValue(is, Map.class);
    }

    // 에러 메시지를 가져오는 메서드
    public Map<String, String> getErrorMessage(String exceptionName) {
        return errorMessages.get(exceptionName);
    }
}
