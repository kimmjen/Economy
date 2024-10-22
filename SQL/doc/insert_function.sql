CREATE FUNCTION dow$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO dow (date,
                     open_price,
                     close_price,
                     high_price,
                     low_price,
                     volume,
                     change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;

CREATE FUNCTION sp500$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO sp500 (date,
                       open_price,
                       close_price,
                       high_price,
                       low_price,
                       volume,
                       change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION nasdaq100$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO nasdaq100 (date,
                           open_price,
                           close_price,
                           high_price,
                           low_price,
                           volume,
                           change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION russell2000$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO russell2000 (date,
                             open_price,
                             close_price,
                             high_price,
                             low_price,
                             volume,
                             change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION treasury_2yr$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO treasury_2yr (date,
                              open_price,
                              close_price,
                              high_price,
                              low_price,
                              volume,
                              change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION treasury_10yr$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO treasury_10yr (date,
                               open_price,
                               close_price,
                               high_price,
                               low_price,
                               volume,
                               change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION dollar_index$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO dollar_index (date,
                              open_price,
                              close_price,
                              high_price,
                              low_price,
                              volume,
                              change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION gold$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO gold (date,
                      open_price,
                      close_price,
                      high_price,
                      low_price,
                      volume,
                      change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION wti_oil$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO wti_oil (date,
                         open_price,
                         close_price,
                         high_price,
                         low_price,
                         volume,
                         change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;
CREATE FUNCTION natural_gas$insert(
    _date date,
    _open_price double precision,
    _close_price double precision,
    _high_price double precision,
    _low_price double precision,
    _volume bigint,
    _change_percentage double precision
) RETURNS void
    LANGUAGE plpgsql
AS
$$
BEGIN
    INSERT INTO natural_gas (date,
                             open_price,
                             close_price,
                             high_price,
                             low_price,
                             volume,
                             change_percentage)
    VALUES (_date,
            _open_price,
            _close_price,
            _high_price,
            _low_price,
            _volume,
            _change_percentage);
END;
$$;

create function economic_indicators$insert(_event_type character varying, _indicator_name character varying, _fed_speaker character varying, _fed_position character varying, _event_date date, _event_time_kst timestamp without time zone, _event_time_est timestamp without time zone, _indicator_value character varying, _forecast_value character varying, _previous_value character varying, _content text, _importance integer) returns void
    language plpgsql
as
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

alter function economic_indicators$insert(varchar, varchar, varchar, varchar, date, timestamp, timestamp, varchar, varchar, varchar, text, integer) owner to postgres;

