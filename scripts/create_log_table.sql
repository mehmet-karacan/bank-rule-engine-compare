CREATE TABLE kural_motoru_karsilastirma_logu (
    id SERIAL PRIMARY KEY,
    kural_motoru VARCHAR(50),
    kural_dosyasi_adi VARCHAR(255),
    kural_sayisi INT,
    islenen_kayit_sayisi INT,
    eslesen_kayit_sayisi INT,
    kural_eslesme_sayisi INT,
    calisma_suresi_ms INT,
    ram_kullanimi_mb INT,
    cpu_kullanimi_yuzde DECIMAL(5,2),
    kural_dosyasi_boyutu_kb INT,
    kayit_tarihi TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);