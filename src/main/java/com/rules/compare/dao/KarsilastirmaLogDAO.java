package com.rules.compare.dao;

import com.rules.compare.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * DAO sınıfı, karşılaştırma loglarının veritabanı işlemlerini yönetir.
 */
public class KarsilastirmaLogDAO {

    /**
     * Yeni bir karşılaştırma logu ekler ve oluşan kayıt ID'sini döner.
     *
     * @param kuralMotoru       Kullanılan kural motoru adı (örn. "Drools", "EasyRules")
     * @param kuralDosyasiAdi   Kural dosyasının adı
     * @param kuralSayisi       Kural sayısı
     * @return                  Eklenen log kaydının ID'si veya -1 (başarısızsa)
     */
    public static int logEkle(String kuralMotoru,
                              String kuralDosyasiAdi,
                              int kuralSayisi,
                              long kuralDosyasiBoyutuByte) {

        String sql = "INSERT INTO kural_motoru_karsilastirma_logu " +
                     "(kural_motoru, kural_dosyasi_adi, kural_sayisi, kural_dosyasi_boyutu_kb) " +
                     "VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kuralMotoru);
            ps.setString(2, kuralDosyasiAdi);
            ps.setInt(3, kuralSayisi);
            ps.setLong(4, kuralDosyasiBoyutuByte);

            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.err.println("Karşılaştırma Logu Eklenemedi: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public static void logGuncelle(int id,
                                   int islenenKayitSayisi,
                                   int calismaSuresiMs,
                                   int ramKullanimiMb,
                                   double cpuKullanimiYuzde) {

        String sql = "UPDATE kural_motoru_karsilastirma_logu SET " +
                "islenen_kayit_sayisi = ?, " +
                "calisma_suresi_ms = ?, ram_kullanimi_mb = ?, cpu_kullanimi_yuzde = ? " +
                "WHERE id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, islenenKayitSayisi);
            ps.setInt(4, calismaSuresiMs);
            ps.setInt(5, ramKullanimiMb);
            ps.setDouble(6, cpuKullanimiYuzde);
            ps.setInt(7, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Karşılaştırma Logu Güncellenemedi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
