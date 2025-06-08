package com.rules.compare.util;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.YamlRuleDefinitionReader;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class EasyRulesYamlUtil {

    private static final String KURAL_KLASORU = "src/main/resources/rules/easyrules/";
    
    public static Reader getYamlReader(String yamlDosyaYolu) throws Exception {
        return new FileReader(new File(yamlDosyaYolu));
    }

    public static Rules kurallariYukle(int kuralBaslangic, int kuralBitis, String dosyaAdi) {
        Rules tumKurallar = new Rules();
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        for (int i = kuralBaslangic; i <= kuralBitis; i++) {
            String dosyaYolu = KURAL_KLASORU + "rules_" + i + ".yml";
            try (Reader reader = getYamlReader(dosyaYolu)) {
                Rules kurallar = ruleFactory.createRules(reader);
                for (Rule rule : kurallar) {
                    tumKurallar.register(rule);
                }
            } catch (Exception e) {
                System.err.println("Kural dosyası yüklenemedi: rules_" + i + ".yml -> " + e.getMessage());
            }
        }
        return tumKurallar;
    }
}
