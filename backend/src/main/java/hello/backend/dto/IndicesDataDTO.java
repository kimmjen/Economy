package hello.backend.dto;

import hello.backend.entity.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IndicesDataDTO {

    // US Stock
    private Sp500 sp500;
    private Dow dow;
    private Nasdaq100 nasdaq100;
    private Russell2000 russell2000;

    // US Bond
    private Treasury2yr treasury2yr;
    private Treasury10yr treasury10y;

    // US Dollar index
    private Dollar dollar;

    // US Gold
    private Gold gold;

    // US Energy
    private WtiOil wtiOil;
    private NaturalGas naturalGas;

    public IndicesDataDTO(Sp500 sp500, Dow dow, Nasdaq100 nasdaq100, Russell2000 russell2000) {
        this.sp500 = sp500;
        this.dow = dow;
        this.nasdaq100 = nasdaq100;
        this.russell2000 = russell2000;
    }
}
