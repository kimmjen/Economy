package hello.backend.controller;

import hello.backend.services.data.DataFastapiHealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendHealthCheckController {

    @Autowired
    private DataFastapiHealthCheckService healthCheckService;

    @GetMapping("/check-fastapi-health")
    public String checkFastapiHealth() {
        return healthCheckService.checkDataFastapiHealth();
    }
}