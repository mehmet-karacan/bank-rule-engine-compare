package com.rules.compare.dao;

import com.rules.compare.model.BankaPazarlamaVerisi;
import com.rules.compare.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankaPazarlamaVerisiDAO {

    public List<BankaPazarlamaVerisi> tumKayitlariGetir() {
        List<BankaPazarlamaVerisi> liste = new ArrayList<>();

        String sql = "SELECT * FROM banka_pazarlama_verisi";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BankaPazarlamaVerisi v = new BankaPazarlamaVerisi();
                v.setId(rs.getInt("id"));
                v.setYas(rs.getInt("yas"));
                v.setMeslek(rs.getString("meslek"));
                v.setMedeniDurum(rs.getString("medeni_durum"));
                v.setEgitim(rs.getString("egitim"));
                v.setTemerrutDurumu(rs.getString("temerrut_durumu"));
                v.setKonutKredisi(rs.getString("konut_kredisi"));
                v.setTuketiciKredisi(rs.getString("tuketici_kredisi"));
                v.setIletisimTuru(rs.getString("iletisim_tipi"));
                v.setAy(rs.getString("ay"));
                v.setGun(rs.getString("gun"));
                v.setGorusmeSuresi(rs.getInt("gorusme_suresi"));
                v.setKampanyaSayisi(rs.getInt("kampanya_iletisim_sayisi"));
                v.setOncekiGoruseKadarGecenGun(rs.getInt("onceki_kampanyadan_sonra_gecen_gun"));
                v.setOncekiKampanyaSayisi(rs.getInt("onceki_kampanya_iletisim_sayisi"));
                v.setOncekiKampanyaSonucu(rs.getString("onceki_kampanya_sonucu"));
                v.setIsGucuDegisimOrani(rs.getFloat("is_gucu_degisim_orani"));
                v.setTuketiciFiyatEndeksi(rs.getFloat("tuketici_fiyat_endeksi"));
                v.setTuketiciGuvenEndeksi(rs.getFloat("tuketici_guven_endeksi"));
                v.setEuribor3ay(rs.getFloat("euribor3ay"));
                v.setCalisanSayisi(rs.getFloat("calisan_sayisi"));
                v.setHedef(rs.getString("hedef"));

                liste.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Veri alınırken hata: " + e.getMessage());
        }

        return liste;
    }
}
