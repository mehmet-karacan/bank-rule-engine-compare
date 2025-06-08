DO $$
DECLARE
    i INT;
    meslekler TEXT[] := ARRAY['admin.', 'blue-collar', 'entrepreneur', 'housemaid', 'management', 'retired', 'self-employed', 'services', 'student', 'technician', 'unemployed', 'unknown'];
    medeni_durumlar TEXT[] := ARRAY['married', 'single', 'divorced'];
    egitimler TEXT[] := ARRAY['basic.4y', 'basic.6y', 'basic.9y', 'high.school', 'illiterate', 'professional.course', 'university.degree', 'unknown'];
    temerrut_durumlar TEXT[] := ARRAY['yes', 'no', 'unknown'];
    krediler TEXT[] := ARRAY['yes', 'no', 'unknown'];
    iletisim_tipleri TEXT[] := ARRAY['cellular', 'telephone'];
    aylar TEXT[] := ARRAY['jan', 'feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'oct', 'nov', 'dec'];
    gunler TEXT[] := ARRAY['mon', 'tue', 'wed', 'thu', 'fri'];
    kampanya_sonuclari TEXT[] := ARRAY['success', 'failure', 'nonexistent'];
    hedefler TEXT[] := ARRAY['yes', 'no'];
BEGIN
    FOR i IN 1..400000
    LOOP
        INSERT INTO banka_pazarlama_verisi
        (
            yas,
            meslek,
            medeni_durum,
            egitim,
            temerrut_durumu,
            konut_kredisi,
            tuketici_kredisi,
            iletisim_tipi,
            ay,
            gun,
            gorusme_suresi,
            kampanya_iletisim_sayisi,
            onceki_kampanyadan_sonra_gecen_gun,
            onceki_kampanya_iletisim_sayisi,
            onceki_kampanya_sonucu,
            is_gucu_degisim_orani,
            tuketici_fiyat_endeksi,
            tuketici_guven_endeksi,
            euribor3ay,
            calisan_sayisi,
            hedef
        )
        VALUES
        (
            FLOOR(RANDOM() * 60 + 18)::INT,
            meslekler[FLOOR(RANDOM() * array_length(meslekler, 1) + 1)::INT],
            medeni_durumlar[FLOOR(RANDOM() * array_length(medeni_durumlar, 1) + 1)::INT],
            egitimler[FLOOR(RANDOM() * array_length(egitimler, 1) + 1)::INT],
            temerrut_durumlar[FLOOR(RANDOM() * array_length(temerrut_durumlar, 1) + 1)::INT],
            krediler[FLOOR(RANDOM() * array_length(krediler, 1) + 1)::INT],
            krediler[FLOOR(RANDOM() * array_length(krediler, 1) + 1)::INT],
            iletisim_tipleri[FLOOR(RANDOM() * array_length(iletisim_tipleri, 1) + 1)::INT],
            aylar[FLOOR(RANDOM() * array_length(aylar, 1) + 1)::INT],
            gunler[FLOOR(RANDOM() * array_length(gunler, 1) + 1)::INT],
            FLOOR(RANDOM() * 500)::INT,
            FLOOR(RANDOM() * 10)::INT,
            FLOOR(RANDOM() * 999)::INT,
            FLOOR(RANDOM() * 5)::INT,
            kampanya_sonuclari[FLOOR(RANDOM() * array_length(kampanya_sonuclari, 1) + 1)::INT],
            ROUND((RANDOM() * 4 - 2)::NUMERIC, 2),
            ROUND((RANDOM() * 10 + 90)::NUMERIC, 2),
            ROUND((RANDOM() * 20 - 10)::NUMERIC, 2),
            ROUND((RANDOM() * 5)::NUMERIC, 2),
            ROUND((RANDOM() * 500 + 500)::NUMERIC, 2),
            hedefler[FLOOR(RANDOM() * array_length(hedefler, 1) + 1)::INT]
        );
    END LOOP;
END;
$$;