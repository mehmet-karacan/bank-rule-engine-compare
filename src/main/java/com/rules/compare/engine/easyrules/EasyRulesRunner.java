package com.rules.compare.engine.easyrules;

import com.rules.compare.dao.BankaPazarlamaVerisiDAO;
import com.rules.compare.dao.KarsilastirmaLogDAO;
import com.rules.compare.model.BankaPazarlamaVerisi;
import com.rules.compare.util.DbUtil;
import com.rules.compare.util.SistemKaynakOlcumUtil;
import com.rules.compare.util.EasyRulesYamlUtil;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class EasyRulesRunner {

    private static final String KURAL_DOSYASI_ADI = "rules_1.yml"; // DRL dosyası yok, isimle takip
    private static final String KURAL_MOTORU = "Easy Rules";
    private static final int KURAL_BASLANGIC = 1;
    private static final int KURAL_BITIS = 25;

    public static void calistir(int kuralBaslangic, int kuralBitis) {
        try (Connection baglanti = DbUtil.getConnection()) {
            BankaPazarlamaVerisiDAO veriDAO = new BankaPazarlamaVerisiDAO();
            List<BankaPazarlamaVerisi> veriler = veriDAO.tumKayitlariGetir();

            int kuralSayisi = 25;
            int kayitSayisi = veriler.size();

            String kuralKlasorYolu = "src/main/resources/rules/easyrules/";
            File kuralDosyaYolu = new File(kuralKlasorYolu + KURAL_DOSYASI_ADI);

            long toplamDosyaBoyutuKb = 0;

            java.util.List<org.jeasy.rules.api.Rule> rulesList = new java.util.ArrayList<>();
            for (int i = kuralBaslangic; i <= kuralBitis; i++) {
                String dosyaAdi = "rules_" + i + ".yml";
                File kuralDosya = new File("src/main/resources/rules/easyrules/rules_" + i + ".yml");
                if (kuralDosya.exists()) {
                    toplamDosyaBoyutuKb += SistemKaynakOlcumUtil.getDosyaBoyutuBayt(kuralDosya);
                }
                try {
                    org.jeasy.rules.api.Rules kurallar = EasyRulesYamlUtil.kurallariYukle(kuralBaslangic, kuralBitis, dosyaAdi);
                    for (org.jeasy.rules.api.Rule rule : kurallar) {
                        rulesList.add(rule);
                    }
                } catch (Exception e) {
                    System.err.println("Kural dosyası yüklenemedi: " + dosyaAdi + " -> " + e.getMessage());
                }
            }

            long baslangicZamani = SistemKaynakOlcumUtil.getSistemSaatMs();

            Rules rules = new Rules();
            for (org.jeasy.rules.api.Rule rule : rulesList) {
                rules.register(rule);
            }

            int logId = KarsilastirmaLogDAO.logEkle(KURAL_MOTORU, "rules_" + kuralBaslangic + "_" + kuralBitis + ".yml", kuralSayisi, toplamDosyaBoyutuKb);

            RulesEngine engine = new DefaultRulesEngine();

            for (BankaPazarlamaVerisi veri : veriler) {
                Facts facts = new Facts();
                facts.put("veri", veri);
                engine.fire(rules, facts);
            }

            int calismaSuresiMs = (int) (SistemKaynakOlcumUtil.getSistemSaatMs() - baslangicZamani);
            int ramMb = SistemKaynakOlcumUtil.getRamKullanimiMb();
            double cpuYuzde = SistemKaynakOlcumUtil.getCpuKullanimiYuzde();

            KarsilastirmaLogDAO.logGuncelle(logId, kayitSayisi, calismaSuresiMs, ramMb, cpuYuzde);
        } catch (Exception e) {
            System.err.println("Easy Rules Hata Verdi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int calistirmaSayisi = 100;
        if (args.length > 0) {
            try {
                calistirmaSayisi = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Geçersiz Argüman, Varsayılan Olarak 1 Kez Çalıştırılıyor.");
                calistirmaSayisi = 1;
            }
        }
        System.out.println("EasyRules " + calistirmaSayisi + " Kez Çalıştırılacak.");
        for (int i = 0; i < calistirmaSayisi; i++) {
            calistir(KURAL_BASLANGIC, KURAL_BITIS);
        }
    }
}