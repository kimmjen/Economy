package hello.backend.dto;

import hello.backend.entity.Treasury10yr;
import hello.backend.entity.Treasury2yr;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BondsDataDTO {

    private List<Treasury2yr> treasury2yrData;
    private List<Treasury10yr> treasury10yrData;

    public BondsDataDTO(List<Treasury2yr> treasury2yrData, List<Treasury10yr> treasury10yrData) {
        this.treasury2yrData = treasury2yrData;
        this.treasury10yrData = treasury10yrData;
    }

}
