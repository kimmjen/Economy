package hello.backend.controller;

import hello.backend.dto.IndicesDataDTO;
import hello.backend.entity.Dow;
import hello.backend.entity.Nasdaq100;
import hello.backend.entity.Russell2000;
import hello.backend.entity.Sp500;
import hello.backend.services.DowService;
import hello.backend.services.Nasdaq100Service;
import hello.backend.services.Russell2000Service;
import hello.backend.services.Sp500Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "OK");
        status.put("timestamp", LocalDateTime.now());
        status.put("message", "The server is running smoothly");
        System.out.println(status);
        return status;
    }

    private final DowService dowService;
    private final Sp500Service sp500Service;
    private final Nasdaq100Service nasdaq100Service;
    private final Russell2000Service russell2000Service;

    public IndexController(DowService dowService, Sp500Service sp500Service,
                           Nasdaq100Service nasdaq100Service, Russell2000Service russell2000Service) {
        this.dowService = dowService;
        this.sp500Service = sp500Service;
        this.nasdaq100Service = nasdaq100Service;
        this.russell2000Service = russell2000Service;
    }

    @GetMapping("/date")
    public IndicesDataDTO getIndicesByDate(@RequestParam LocalDate date) {
        // 각 서비스에서 데이터를 가져옵니다.
        List<Dow> dowData = dowService.getByDate(date);
        List<Sp500> sp500Data = sp500Service.getByDate(date);
        List<Nasdaq100> nasdaq100Data = nasdaq100Service.getByDate(date);
        List<Russell2000> russell2000Data = russell2000Service.getByDate(date);

        // DTO로 묶어서 반환합니다.
        return new IndicesDataDTO(dowData, sp500Data, nasdaq100Data, russell2000Data);
    }
}
