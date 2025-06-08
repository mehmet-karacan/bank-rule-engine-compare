package com.rules.compare.engine.drools;

import com.rules.compare.dao.BankaPazarlamaVerisiDAO;
import com.rules.compare.dao.KarsilastirmaLogDAO;
import com.rules.compare.model.BankaPazarlamaVerisi;
import com.rules.compare.util.DbUtil;
import com.rules.compare.util.SistemKaynakOlcumUtil;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class DroolsRunner {

    private static final String KURAL_DOSYASI_ADI = "rules_100.drl";
    private static final String KURAL_MOTORU = "Drools";

    public static void calistir(String kuralDosyasiAd) {
        try (Connection baglanti = DbUtil.getConnection()) {
            BankaPazarlamaVerisiDAO veriDAO = new BankaPazarlamaVerisiDAO();
            List<BankaPazarlamaVerisi> veriler = veriDAO.tumKayitlariGetir();

            KieServices kieServis = KieServices.Factory.get();
            KieContainer konteyner = kieServis.getKieClasspathContainer();
            int kuralSayisi = 0;
            for (org.kie.api.definition.KiePackage paket : konteyner.getKieBase().getKiePackages()) {
                kuralSayisi += paket.getRules().size();
            }
            int kayitSayisi = veriler.size();

            File kuralDosyaYolu = new File("src/main/resources/rules/drools/" + kuralDosyasiAd);
            long dosyaBoyutuByte = SistemKaynakOlcumUtil.getDosyaBoyutuBayt(kuralDosyaYolu);

            long baslangicZamani = SistemKaynakOlcumUtil.getSistemSaatMs();

            int logId = KarsilastirmaLogDAO.logEkle(KURAL_MOTORU, kuralDosyasiAd, kuralSayisi, dosyaBoyutuByte);

            int eslesenKayitSayisi = 0;
            int toplamKuralEslesmeSayisi = 0;

            for (BankaPazarlamaVerisi veri : veriler) {
                KieSession kieOturum = konteyner.newKieSession("ksession-rules");
                kieOturum.insert(veri);
                int calisanKuralSayisi = kieOturum.fireAllRules();
                if (calisanKuralSayisi > 0) {
                    eslesenKayitSayisi++;
                    toplamKuralEslesmeSayisi += calisanKuralSayisi;
                }
                kieOturum.dispose();
            }

            int calismaSuresiMs = (int) (SistemKaynakOlcumUtil.getSistemSaatMs() - baslangicZamani);
            int ramMb = SistemKaynakOlcumUtil.getRamKullanimiMb();
            double cpuYuzde = SistemKaynakOlcumUtil.getCpuKullanimiYuzde();

            KarsilastirmaLogDAO.logGuncelle(logId, kayitSayisi, calismaSuresiMs, ramMb, cpuYuzde);
        } catch (Exception e) {
            System.err.println("DroolsRunner Hata Verdi: " + e.getMessage());
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
        System.out.println("Kural Motoru " + calistirmaSayisi + " Kez Çalıştırılacak.");
        for (int i = 0; i < calistirmaSayisi; i++) {
            calistir(KURAL_DOSYASI_ADI);
        }
    }
}
