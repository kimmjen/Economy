package hello.backend.dto;

import hello.backend.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class IndicesDataDTO {

    private List<Dow> dowData;
    private List<Sp500> sp500Data;
    private List<Nasdaq100> nasdaq100Data;
    private List<Russell2000> russell2000Data;

    public IndicesDataDTO(List<Dow> dowData, List<Sp500> sp500Data,
                          List<Nasdaq100> nasdaq100Data, List<Russell2000> russell2000Data) {
        this.dowData = dowData;
        this.sp500Data = sp500Data;
        this.nasdaq100Data = nasdaq100Data;
        this.russell2000Data = russell2000Data;
    }

    // Getter와 Setter
    public List<Dow> getDowData() {
        return dowData;
    }

    public void setDowData(List<Dow> dowData) {
        this.dowData = dowData;
    }

    public List<Sp500> getSp500Data() {
        return sp500Data;
    }

    public void setSp500Data(List<Sp500> sp500Data) {
        this.sp500Data = sp500Data;
    }

    public List<Nasdaq100> getNasdaq100Data() {
        return nasdaq100Data;
    }

    public void setNasdaq100Data(List<Nasdaq100> nasdaq100Data) {
        this.nasdaq100Data = nasdaq100Data;
    }

    public List<Russell2000> getRussell2000Data() {
        return russell2000Data;
    }

    public void setRussell2000Data(List<Russell2000> russell2000Data) {
        this.russell2000Data = russell2000Data;
    }
}
