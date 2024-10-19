package hello.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "economic_indicators")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EconomicIndicators {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventType;

    @Column(length = 255)
    private String indicatorName; // 경제 지표 이름 (해당하는 경우)

    @Column(length = 100)
    private String fedSpeaker; // 연준 인사 이름 (해당하는 경우)

    @Column(length = 100)
    private String fedPosition; // 연준 인사 직책 (해당하는 경우)

    @Column(nullable = false)
    private java.time.LocalDate eventDate; // 이벤트 날짜 (YYYY-MM-DD 형식)

    @Column(nullable = false)
    private java.time.LocalDateTime eventTimeKst; // 한국 시간

    @Column(nullable = false)
    private java.time.LocalDateTime eventTimeEst; // 미국 동부 시간

    @Column(length = 50)
    private String indicatorValue; // 발표된 수치 (해당하는 경우)

    @Column(length = 50)
    private String forecastValue; // 예상 수치 (해당하는 경우)

    @Column(length = 50)
    private String previousValue; // 이전 수치 (해당하는 경우)

    @Column(columnDefinition = "TEXT")
    private String content; // 지표나 연준 발언에 대한 설명

    @Column
    private Integer importance; // 중요도 레벨 (★ 개수)
}
