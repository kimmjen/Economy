package hello.backend.services.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataFastapiHealthCheckService {

    private final RestTemplate restTemplate;

    // application.yml의 datafastapi.url을 여기에 주입
    @Value("${datafastapi.url}")
    private String dataFastapiUrl;
    public DataFastapiHealthCheckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String checkDataFastapiHealth() {
        String url = dataFastapiUrl + "/health";  // /health 엔드포인트로 요청
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "Data FastAPI health check failed: " + e.getMessage();
        }

    }
}
