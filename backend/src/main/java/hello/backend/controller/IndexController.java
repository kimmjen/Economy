package hello.backend.controller;

import hello.backend.dto.BondsDataDTO;
import hello.backend.dto.EnergyDataDTO;
import hello.backend.dto.IndicesDataDTO;
import hello.backend.entity.*;
import hello.backend.services.*;
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
@RequestMapping("/api/v1")
public class IndexController {

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "OK");
        status.put("timestamp", LocalDateTime.now());
        status.put("message", "The server is running smoothly");
        return status;
    }

    // 지수 관련 서비스
    private final DowService dowService;
    private final Sp500Service sp500Service;
    private final Nasdaq100Service nasdaq100Service;
    private final Russell2000Service russell2000Service;

    // 채권 관련 서비스
    private final Treasury2yrService treasury2yrService;
    private final Treasury10yrService treasury10yrService;

    // 달러 인덱스 관련 서비스
    private final DollarService dollarService;

    // 금 관련 서비스
    private final GoldService goldService;

    // 에너지 관련 서비스
    private final WtiOilService wtiOilService;
    private final NaturalGasService naturalGasService;

    public IndexController(
            DowService dowService,
            Sp500Service sp500Service,
            Nasdaq100Service nasdaq100Service,
            Russell2000Service russell2000Service,
            Treasury2yrService treasury2yrService,
            Treasury10yrService treasury10yrService,
            DollarService dollarService,
            GoldService goldService,
            WtiOilService wtiOilService,
            NaturalGasService naturalGasService) {

        this.dowService = dowService;
        this.sp500Service = sp500Service;
        this.nasdaq100Service = nasdaq100Service;
        this.russell2000Service = russell2000Service;
        this.treasury2yrService = treasury2yrService;
        this.treasury10yrService = treasury10yrService;
        this.dollarService = dollarService;
        this.goldService = goldService;
        this.wtiOilService = wtiOilService;
        this.naturalGasService = naturalGasService;
    }

    @GetMapping("/indices")
    public IndicesDataDTO getIndicesByDate(@RequestParam LocalDate date) {
        List<Dow> dowData = dowService.getByDate(date);
        List<Sp500> sp500Data = sp500Service.getByDate(date);
        List<Nasdaq100> nasdaq100Data = nasdaq100Service.getByDate(date);
        List<Russell2000> russell2000Data = russell2000Service.getByDate(date);

        return new IndicesDataDTO(dowData, sp500Data, nasdaq100Data, russell2000Data);
    }

    @GetMapping("/bonds")
    public BondsDataDTO getBondsByDate(@RequestParam LocalDate date) {
        List<Treasury2yr> treasury2yrData = treasury2yrService.getByDate(date);
        List<Treasury10yr> treasury10yrData = treasury10yrService.getByDate(date);

        return new BondsDataDTO(treasury2yrData, treasury10yrData);
    }

    @GetMapping("/dollars")
    public List<Dollar> getDollarByDate(@RequestParam LocalDate date) {
        return dollarService.getByDate(date);
    }

    @GetMapping("/golds")
    public List<Gold> getGoldByDate(@RequestParam LocalDate date) {
        return goldService.getByDate(date);
    }

    @GetMapping("/energys")
    public EnergyDataDTO getEnergyByDate(@RequestParam LocalDate date) {
        List<WtiOil> wtiOilData = wtiOilService.getByDate(date);
        List<NaturalGas> naturalGasData = naturalGasService.getByDate(date);

        return new EnergyDataDTO(wtiOilData, naturalGasData);
    }
}
