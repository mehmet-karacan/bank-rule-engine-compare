SELECT
    kural_motoru,
    case
        when kural_dosyasi_adi = 'rules_25.drl' or kural_dosyasi_adi = 'rules_1_25.yml' then 'SEVIYE_1'
        when kural_dosyasi_adi = 'rules_50.drl' or kural_dosyasi_adi = 'rules_26_50.yml' then 'SEVIYE_2'
        when kural_dosyasi_adi = 'rules_75.drl' or kural_dosyasi_adi = 'rules_51_75.yml' then 'SEVIYE_3'
        when kural_dosyasi_adi = 'rules_100.drl' or kural_dosyasi_adi = 'rules_76_100.yml' then 'SEVIYE_4'
    end as KURAL_SEVIYESI,
    case
        when kural_dosyasi_adi = 'rules_25.drl' or kural_dosyasi_adi = 'rules_1_25.yml' then 25
        when kural_dosyasi_adi = 'rules_50.drl' or kural_dosyasi_adi = 'rules_26_50.yml' then 50
        when kural_dosyasi_adi = 'rules_75.drl' or kural_dosyasi_adi = 'rules_51_75.yml' then 75
        when kural_dosyasi_adi = 'rules_100.drl' or kural_dosyasi_adi = 'rules_76_100.yml' then 100
    end KURAL_SAYISI,
    islenen_kayit_sayisi,
    AVG(calisma_suresi_ms) as calisma_suresi_ms,
    AVG(ram_kullanimi_mb) as ram_kullanimi_mb,
    AVG(cpu_kullanimi_yuzde) as cpu_kullanimi_yuzde,
    AVG(kural_dosyasi_boyutu_kb) as kural_dosyasi_boyutu_kb
from
    kural_motoru_karsilastirma_logu
group BY
    kural_motoru,
    case
        when kural_dosyasi_adi = 'rules_25.drl' or kural_dosyasi_adi = 'rules_1_25.yml' then 'SEVIYE_1'
        when kural_dosyasi_adi = 'rules_50.drl' or kural_dosyasi_adi = 'rules_26_50.yml' then 'SEVIYE_2'
        when kural_dosyasi_adi = 'rules_75.drl' or kural_dosyasi_adi = 'rules_51_75.yml' then 'SEVIYE_3'
        when kural_dosyasi_adi = 'rules_100.drl' or kural_dosyasi_adi = 'rules_76_100.yml' then 'SEVIYE_4'
    end,
    case
        when kural_dosyasi_adi = 'rules_25.drl' or kural_dosyasi_adi = 'rules_1_25.yml' then 25
        when kural_dosyasi_adi = 'rules_50.drl' or kural_dosyasi_adi = 'rules_26_50.yml' then 50
        when kural_dosyasi_adi = 'rules_75.drl' or kural_dosyasi_adi = 'rules_51_75.yml' then 75
        when kural_dosyasi_adi = 'rules_100.drl' or kural_dosyasi_adi = 'rules_76_100.yml' then 100
    end,
    islenen_kayit_sayisi
order by 1, 2, 3;