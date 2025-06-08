package com.rules.compare.engine;

import com.rules.compare.engine.drools.DroolsRunner;
import com.rules.compare.engine.easyrules.EasyRulesRunner;

public class KarsilastirmaRunner {

    public static void main(String[] args) {
        int tekrarSayisi = 1;

        String droolsKuralDosyasiAdi = "rules_25.drl";
        int easyBaslangic = 1;
        int easyBitis = 25;

        System.out.println("=== DROOLS ÇALIŞTIRILIYOR ===");
        for (int i = 0; i < tekrarSayisi; i++) {
            DroolsRunner.calistir(droolsKuralDosyasiAdi);
        }

        System.out.println("=== EASY RULES ÇALIŞTIRILIYOR ===");
        for (int i = 0; i < tekrarSayisi; i++) {
            EasyRulesRunner.calistir(easyBaslangic, easyBitis);
        }

    }
}