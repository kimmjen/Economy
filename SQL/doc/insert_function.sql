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
