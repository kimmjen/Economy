package hello.backend.controller;

import hello.backend.services.data.DataFastApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BackendHealthCheckController {

    @Autowired
    private DataFastApiService dataFastApiService;

    @GetMapping("/check-fastapi-health")
    public String checkFastapiHealth() {
        return dataFastApiService.checkDataFastapiHealth();
    }

    @GetMapping("/update")
    public String updateFinanceData() {
        return dataFastApiService.updateFinanceData();
    }
}