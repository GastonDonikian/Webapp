CREATE OR REPLACE FUNCTION unaccent(text)
    RETURNS text
    IMMUTABLE
    STRICT
    LANGUAGE SQL
AS
$$
SELECT translate(
               $1,
               'âãäåāăąÁÂÃÄÅĀĂĄèééêëēĕėęěĒĔĖĘĚìíîïìĩīĭÌÍÎÏÌĨĪĬóôõöōŏőÒÓÔÕÖŌŎŐùúûüũūŭůÙÚÛÜŨŪŬŮ',
               'aaaaaaaaaaaaaaaeeeeeeeeeeeeeeeiiiiiiiiiiiiiiiiooooooooooooooouuuuuuuuuuuuuuuu'
           )
$$;
