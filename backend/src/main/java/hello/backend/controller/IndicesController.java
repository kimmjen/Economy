package hello.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/indices")
public class IndicesController {

//    // US Stock
//    private final Sp500Service sp500Service;
//    private final DowService dowService;
//    private final Nasdaq100Service nasdaq100Service;
//    private final Russell2000Service russell2000Service;
//
//    // US Bond
//    private final Treasury2yrService treasury2yrService;
//    private final Treasury10yrService treasury10yrService;
//
//    // US Dollar index
//    private final DollarService dollarService;
//
//    // US Gold
//    private final GoldService goldService;
//
//    // US Energy
//    private final WtiOilService wtiOilService;
//    private final NaturalGasService naturalGasService;
//
//    public IndicesController(
//            Sp500Service sp500Service,
//            DowService dowService,
//            Nasdaq100Service nasdaq100Service,
//            Russell2000Service russell2000Service,
//            Treasury2yrService treasury2yrService,
//            Treasury10yrService treasury10yrService,
//            DollarService dollarService,
//            GoldService goldService,
//            WtiOilService wtiOilService,
//            NaturalGasService naturalGasService) {
//        this.sp500Service = sp500Service;
//        this.dowService = dowService;
//        this.nasdaq100Service = nasdaq100Service;
//        this.russell2000Service = russell2000Service;
//        this.treasury2yrService = treasury2yrService;
//        this.treasury10yrService = treasury10yrService;
//        this.dollarService = dollarService;
//        this.goldService = goldService;
//        this.wtiOilService = wtiOilService;
//        this.naturalGasService = naturalGasService;
//    }
//
//    @GetMapping("/data/{date}")
//    public ResponseEntity<?> getIndicesDataByDate(@PathVariable String date) {
//        try {
//            LocalDate localDate = LocalDate.parse(date);
//
//            // 각 서비스에서 날짜별 데이터를 조회
//            Sp500 sp500 = sp500Service.getByDate(localDate);
//            Dow dow = dowService.getByDate(localDate);
//            Nasdaq100 nasdaq100 = nasdaq100Service.getByDate(localDate);
//            Russell2000 russell2000 = russell2000Service.getByDate(localDate);
//            Treasury2yr treasury2yr = treasury2yrService.getByDate(localDate);
//            Treasury10yr treasury10yr = treasury10yrService.getByDate(localDate);
//            Dollar dollar = dollarService.getByDate(localDate);
//            Gold gold = goldService.getByDate(localDate);
//            WtiOil wtiOil = wtiOilService.getByDate(localDate);
//            NaturalGas naturalGas = naturalGasService.getByDate(localDate);
//
//            // DTO에 데이터를 담아서 리턴
//            IndicesDataDTO indicesDataDTO = new IndicesDataDTO(sp500, dow, nasdaq100, russell2000);
//            return ResponseEntity.ok(indicesDataDTO);
//
//        } catch (DateTimeParseException e) {
//            // 날짜 형식이 잘못되었을 때의 처리
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format. Please use 'YYYY-MM-DD'.");
//        } catch (Exception e) {
//            // 기타 예외 처리
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request.");
//        }
//    }
}
