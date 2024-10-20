CREATE TABLE economic_indicators
(
    id              SERIAL PRIMARY KEY,
    event_type      VARCHAR(50),  -- 이벤트 유형 (경제 지표 또는 연준 발언)
    indicator_name  VARCHAR(255), -- 경제 지표 이름 (해당하는 경우)
    fed_speaker     VARCHAR(100), -- 연준 인사 이름 (해당하는 경우)
    fed_position    VARCHAR(100), -- 연준 인사 직책 (해당하는 경우)
    event_date      DATE,         -- 이벤트 날짜 (YYYY-MM-DD 형식)
    event_time_kst  TIMESTAMP,    -- 한국 시간
    event_time_est  TIMESTAMP,    -- 미국 동부 시간
    indicator_value VARCHAR(50),  -- 발표된 수치 (해당하는 경우)
    forecast_value  VARCHAR(50),  -- 예상 수치 (해당하는 경우)
    previous_value  VARCHAR(50),  -- 이전 수치 (해당하는 경우)
    content         TEXT,         -- 지표나 연준 발언에 대한 설명
    importance      INTEGER       -- 중요도 레벨 (★ 개수)
);

CREATE FUNCTION economic_indicators$insert(
    _event_type VARCHAR(50),
    _indicator_name VARCHAR(255),
    _fed_speaker VARCHAR(100),
    _fed_position VARCHAR(100),
    _event_date DATE,
    _event_time_kst TIMESTAMP,
    _event_time_est TIMESTAMP,
    _indicator_value VARCHAR(50),
    _forecast_value VARCHAR(50),
    _previous_value VARCHAR(50),
    _content TEXT,
    _importance INTEGER  -- 중요도 필드를 숫자로 변경
) RETURNS VOID
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO economic_indicators (
        event_type,
        indicator_name,
        fed_speaker,
        fed_position,
        event_date,
        event_time_kst,
        event_time_est,
        indicator_value,
        forecast_value,
        previous_value,
        content,
        importance
    )
    VALUES (
        _event_type,
        _indicator_name,
        _fed_speaker,
        _fed_position,
        _event_date,
        _event_time_kst,
        _event_time_est,
        _indicator_value,
        _forecast_value,
        _previous_value,
        _content,
        _importance  -- 숫자로 처리
    );
END;
$$;



BEGIN;
SELECT economic_indicators$insert(
    '경제 지표',  -- event_type
    '미국 9월 건축허가건수',  -- indicator_name
    NULL,  -- fed_speaker (경제 지표에는 연준 인사 정보가 없으므로 NULL)
    NULL,  -- fed_position
    '2024-10-19',  -- event_date
    '2024-10-19 21:30:00',  -- event_time_kst
    '2024-10-19 08:30:00',  -- event_time_est
    '1428K ▼',  -- indicator_value
    '1460K',  -- forecast_value
    '1470K',  -- previous_value
    '건축허가건수는 향후 주택 건설 활동을 가늠할 수 있는 중요한 선행 지표입니다.',  -- content
    2  -- importance
);
END;

-- 1. 미국 9월 건축허가건수 (경제 지표)
INSERT INTO economic_indicators
(event_type, indicator_name, event_date, event_time_kst, event_time_est, indicator_value, forecast_value,
 previous_value, content, importance)
VALUES ('경제 지표', '미국 9월 건축허가건수', '2024-10-19', '2024-10-19 21:30:00', '2024-10-19 08:30:00', '1428K', '1460K', '1470K',
        '건축허가건수는 향후 주택 건설 활동을 가늠할 수 있는 중요한 선행 지표입니다.', '★★');

-- 2. 연준 보스틱 총재 발언 (연준 발언)
INSERT INTO economic_indicators
(event_type, fed_speaker, fed_position, event_date, event_time_kst, event_time_est, indicator_value, content,
 importance)
VALUES ('연준 발언', '보스틱', '연준 총재', '2024-10-19', '2024-10-19 22:30:00', '2024-10-19 09:30:00', '중립/투표권 O',
        '보스틱 총재는 현재 투표권을 가지고 있으며 그의 발언은 통화정책의 방향성에 영향을 미칠 수 있습니다.', NULL);

-- 3. 미국 베이커휴즈 총시추기수 (경제 지표)
INSERT INTO economic_indicators
(event_type, indicator_name, event_date, event_time_kst, event_time_est, indicator_value, previous_value, content,
 importance)
VALUES ('경제 지표', '베이커휴즈 총시추기수', '2024-10-20', '2024-10-20 02:00:00', '2024-10-19 13:00:00', '585', '586',
        '베이커휴즈 시추기수는 원유와 천연가스 시추 활동의 변화를 나타내며, 에너지 시장의 활력을 평가하는 데 사용됩니다.', '★★');
