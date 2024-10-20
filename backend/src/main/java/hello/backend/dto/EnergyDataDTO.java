package hello.backend.dto;

import hello.backend.entity.NaturalGas;
import hello.backend.entity.WtiOil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EnergyDataDTO {

    private List<WtiOil> wtiOilData;
    private List<NaturalGas> naturalGasData;

    public EnergyDataDTO(List<WtiOil> wtiOilData, List<NaturalGas> naturalGasData) {
        this.wtiOilData = wtiOilData;
        this.naturalGasData = naturalGasData;
    }


}
