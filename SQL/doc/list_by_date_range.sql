create function sp500$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'dow_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM sp500
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;
create function dollar$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'dollar_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM dollar_index
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;
create function dow$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'dow_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM dow
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;
create function gold$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'gold_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM gold
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;

create function nasdaq100$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'nasdaq100_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM nasdaq100
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;

create function natural_gas$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'natural_gas_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM natural_gas
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;

create function russell2000$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'russell2000_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM russell2000
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;

create function treasury_2yr$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'treasury_2yr_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM treasury_2yr
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;

create function treasury_10yr$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'treasury_10yr_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM treasury_10yr
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;

create function wti_oil$list_by_date_range(_start_date date, _end_date date) returns refcursor
    language plpgsql
as
$$
DECLARE
    -- 커서 선언
    rtn_cursor REFCURSOR := 'wti_oil_cursor';  -- 'my_cursor'라는 이름의 커서 선언
BEGIN
    -- 커서 열기
    OPEN rtn_cursor FOR
    SELECT *,
           COUNT(*) OVER () AS total_count -- 총 행 수
    FROM wti_oil
    WHERE date BETWEEN _start_date AND _end_date -- 날짜 범위 필터링
    ORDER BY date DESC;  -- 최신 순으로 정렬

    -- 커서 반환
    RETURN rtn_cursor;
END;
$$;