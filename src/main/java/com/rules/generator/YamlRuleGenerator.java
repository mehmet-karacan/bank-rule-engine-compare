package com.rules.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class YamlRuleGenerator {

    private static final String OUTPUT_DIR = "src/main/resources/rules/easyrules/";

    public static void main(String[] args) {
        generateRules1To25();
        generateRules26To50();
        generateRules51To75();
        generateRules76To100();
    }

    private static void generateRules1To25() {
        List<String> conditions = Arrays.asList("veri.yas >= 30", "veri.egitim == 'university'", "veri.medeniDurum == 'married'", "veri.hedef == 'yes'", "veri.iletisimTuru == 'telephone'", "veri.kampanyaSayisi > 0", "veri.yas < 25", "veri.egitim == 'high.school'", "veri.hedef == 'no'", "veri.yas > 50", "veri.yas >= 40 && veri.egitim == 'university'", "veri.yas < 30 && veri.medeniDurum == 'single'", "veri.hedef == 'yes' && veri.iletisimTuru == 'telephone'", "veri.egitim == 'high.school' && veri.kampanyaSayisi > 0", "veri.yas >= 60 && veri.hedef == 'no'", "veri.egitim == 'university' && veri.iletisimTuru == 'unknown'", "veri.yas < 35 && veri.kampanyaSayisi == 0", "veri.hedef == 'yes' && veri.iletisimTuru == 'telephone'", "veri.medeniDurum == 'married' && veri.kampanyaSayisi > 2", "veri.egitim == 'university' && veri.yas > 45", "(veri.yas >= 30 && veri.yas <= 40) && veri.egitim == 'university'", "(veri.hedef == 'no' && veri.iletisimTuru == 'unknown') || (veri.hedef == 'yes' && veri.iletisimTuru == 'telephone')", "(veri.yas < 30 && veri.medeniDurum == 'single') && veri.kampanyaSayisi > 1", "(veri.egitim == 'high.school' || veri.egitim == 'university') && (veri.hedef == 'yes' || veri.hedef == 'no')", "(veri.yas >= 50 && veri.medeniDurum == 'married' && veri.kampanyaSayisi >= 1) || (veri.yas < 30 && veri.egitim == 'high.school' && veri.hedef == 'yes')");

        new File(OUTPUT_DIR).mkdirs();

        for (int i = 0; i < conditions.size(); i++) {
            String ruleName = "rules_" + (i + 1);
            String condition = conditions.get(i);
            String content = generateYamlRule(ruleName, "", condition, "veri.setKuralAdi('" + ruleName + "');");
            File file = new File(OUTPUT_DIR + ruleName + ".yml");

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
                System.out.println("YAML rule written: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to write rule: " + ruleName);
                e.printStackTrace();
            }
        }
    }

    private static String generateYamlRule(String name, String description, String condition, String action) {
        return String.format("name: \"%s\"\n" + "condition: \"%s\"\n" + "actions:\n" + "  - \"%s\"\n", name, condition, action);
    }

    private static void generateRules26To50() {
        List<String> conditions = Arrays.asList("veri.yas >= 30 && veri.egitim == 'university'", "veri.medeniDurum == 'single' && veri.kampanyaSayisi > 1", "veri.hedef == 'yes' && veri.iletisimTuru == 'cellular'", "veri.yas < 25 && veri.egitim == 'high.school'", "veri.medeniDurum == 'divorced' && veri.hedef == 'no'", "veri.kampanyaSayisi == 0 && veri.iletisimTuru == 'unknown'", "veri.yas > 60 && veri.egitim == 'university'", "veri.medeniDurum == 'married' && veri.kampanyaSayisi > 3", "veri.egitim == 'primary' && veri.hedef == 'no'", "veri.yas >= 45 && veri.iletisimTuru == 'telephone'", "veri.medeniDurum == 'single' && veri.hedef == 'yes'", "veri.kampanyaSayisi >= 2 && veri.egitim == 'high.school'", "veri.yas < 40 && veri.medeniDurum == 'single'", "veri.hedef == 'yes' && veri.kampanyaSayisi > 1", "veri.yas >= 50 && veri.egitim == 'primary'", "veri.iletisimTuru == 'cellular' && veri.kampanyaSayisi >= 1", "veri.yas < 35 && veri.hedef == 'no'", "veri.medeniDurum == 'married' && veri.egitim == 'university'", "veri.yas >= 55 && veri.iletisimTuru == 'telephone'", "veri.hedef == 'no' && veri.kampanyaSayisi == 0", "veri.egitim == 'unknown' && veri.iletisimTuru == 'unknown'", "veri.yas > 30 && veri.medeniDurum == 'divorced'", "veri.kampanyaSayisi <= 1 && veri.hedef == 'yes'", "veri.egitim == 'high.school' && veri.medeniDurum == 'single'", "veri.yas >= 40 && veri.hedef == 'no'");

        new File(OUTPUT_DIR).mkdirs();

        for (int i = 0; i < conditions.size(); i++) {
            int ruleNumber = 26 + i;
            String ruleName = "rules_" + ruleNumber;
            String condition = conditions.get(i);
            String content = generateYamlRule(ruleName, "", condition, "veri.setKuralAdi('" + ruleName + "');");
            File file = new File(OUTPUT_DIR + ruleName + ".yml");

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
                System.out.println("YAML rule written: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to write rule: " + ruleName);
                e.printStackTrace();
            }
        }
    }

    private static void generateRules51To75() {
        List<String> conditions = Arrays.asList("(veri.yas > 25 && veri.egitim == 'university') || (veri.hedef == 'no' && veri.kampanyaSayisi > 2)", "!((veri.yas < 30 && veri.medeniDurum == 'single') || (veri.iletisimTuru == 'unknown'))", "(veri.egitim == 'high.school' && (veri.yas == 30 || veri.yas == 50)) && (veri.kampanyaSayisi >= 1 || veri.hedef == 'yes')", "(veri.yas > 40 && veri.medeniDurum != 'divorced') && !(veri.hedef == 'no')", "(veri.yas <= 35 || veri.kampanyaSayisi < 2) && (veri.egitim == 'university' || veri.iletisimTuru == 'telephone')", "((veri.yas > 50 && veri.hedef == 'yes') || veri.kampanyaSayisi == 0) && veri.medeniDurum != 'single'", "((veri.yas == 20 || veri.yas == 25 || veri.yas == 30 || veri.yas == 35) && veri.hedef != 'unknown') || (veri.kampanyaSayisi > 1 && veri.egitim == 'university')", "((veri.yas >= 60 && veri.medeniDurum == 'married') || veri.kampanyaSayisi > 3) && !(veri.egitim == 'unknown')", "((veri.yas <= 30 && veri.hedef == 'yes') && (veri.medeniDurum == 'single' || veri.iletisimTuru == 'cellular')) || veri.kampanyaSayisi == 1", "(veri.yas > 45 && veri.hedef == 'no' && !(veri.kampanyaSayisi == 0)) || (veri.egitim == 'university' && veri.iletisimTuru == 'telephone')", "((veri.yas > 35 && veri.egitim != 'basic') && (veri.hedef == 'yes' || veri.kampanyaSayisi > 2)) || (!(veri.medeniDurum == 'divorced') && veri.iletisimTuru == 'cellular')", "(veri.yas < 40 && !(veri.egitim == 'unknown')) && ((veri.kampanyaSayisi >= 2 && veri.hedef == 'no') || (veri.medeniDurum == 'single' && veri.iletisimTuru != 'telephone'))", "(veri.egitim == 'university' && (veri.yas == 28 || veri.yas == 55)) && (!((veri.hedef == 'yes' && veri.kampanyaSayisi < 2)) || veri.medeniDurum == 'married')", "((veri.yas > 50 && veri.hedef != 'unknown') || (veri.kampanyaSayisi > 0 && veri.iletisimTuru == 'cellular')) && !(veri.egitim == 'basic' && veri.medeniDurum == 'divorced')", "((veri.yas < 35 && veri.medeniDurum == 'single') || (veri.egitim == 'high.school' && veri.kampanyaSayisi > 3)) && !(veri.hedef == 'no' && veri.iletisimTuru == 'unknown')", "(veri.yas >= 60 && veri.hedef == 'yes' && !(veri.egitim == 'unknown')) || ((veri.medeniDurum != 'married' && veri.iletisimTuru == 'telephone') && veri.kampanyaSayisi < 2)", "(veri.egitim == 'basic' && (veri.yas == 25 || veri.yas == 45)) && ((veri.hedef == 'no' && veri.kampanyaSayisi == 0) || veri.medeniDurum == 'divorced')", "((veri.yas > 50 && veri.medeniDurum == 'married') && veri.egitim != 'unknown') || ((veri.hedef == 'no' && veri.kampanyaSayisi > 1) && veri.iletisimTuru == 'cellular')", "(veri.yas < 30 && veri.iletisimTuru == 'telephone' && veri.hedef == 'yes') || (veri.kampanyaSayisi > 2 && !(veri.egitim == 'high.school'))", "(veri.yas >= 20 && veri.yas <= 30 && veri.egitim == 'university' && veri.hedef == 'no') || (!(veri.medeniDurum == 'single') && veri.kampanyaSayisi < 1)", "((veri.kampanyaSayisi > 2 && veri.iletisimTuru == 'cellular') && veri.hedef != 'unknown') || ((veri.yas > 45 && veri.egitim == 'basic') && !(veri.medeniDurum == 'divorced'))", "((veri.egitim == 'basic' && veri.medeniDurum == 'married') && veri.kampanyaSayisi < 2) || ((veri.yas > 55 && veri.hedef == 'yes') && veri.iletisimTuru != 'unknown')", "((veri.yas >= 25 && veri.hedef == 'no') && veri.egitim != 'unknown') && (!(veri.kampanyaSayisi > 3) || veri.medeniDurum == 'single')", "((veri.yas < 30 && veri.iletisimTuru == 'cellular') && veri.kampanyaSayisi >= 2) || ((veri.egitim == 'university' && veri.hedef == 'yes') && !(veri.medeniDurum == 'married'))", "((veri.egitim == 'high.school' && veri.hedef == 'yes') && veri.medeniDurum == 'single') || ((veri.yas > 40 && veri.kampanyaSayisi < 2) && !(veri.iletisimTuru == 'unknown'))");

        new File(OUTPUT_DIR).mkdirs();

        for (int i = 0; i < conditions.size(); i++) {
            int ruleNumber = 51 + i;
            String ruleName = "rules_" + ruleNumber;
            String condition = conditions.get(i);
            String content = generateYamlRule(ruleName, "", condition, "veri.setKuralAdi('" + ruleName + "');");
            File file = new File(OUTPUT_DIR + ruleName + ".yml");

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
                System.out.println("YAML rule written: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to write rule: " + ruleName);
                e.printStackTrace();
            }
        }
    }

    private static void generateRules76To100() {
        List<String> conditions = Arrays.asList("(veri.yas >= 30 && veri.yas <= 50 && veri.hedef == 'yes') || (veri.yas > 50 && veri.medeniDurum == 'single' && veri.egitim == 'university')", "veri.iletisimTuru == 'cellular' && ((veri.kampanyaSayisi > 1 && veri.hedef == 'no') || (veri.kampanyaSayisi == 0 && veri.egitim == 'high.school'))", "(veri.yas < 40 && veri.egitim == 'university' && veri.medeniDurum == 'married') || (veri.yas > 60 && veri.hedef == 'yes' && veri.kampanyaSayisi >= 2)", "(veri.egitim == 'unknown' || veri.iletisimTuru == 'unknown') && (veri.hedef == 'no' && veri.kampanyaSayisi > 0)", "veri.yas > 45 && ((veri.medeniDurum == 'single' && veri.hedef == 'yes') || (veri.medeniDurum == 'married' && veri.iletisimTuru == 'telephone'))", "(veri.yas < 30 || veri.egitim == 'high.school') && (veri.hedef == 'no' || (veri.kampanyaSayisi >= 2 && veri.iletisimTuru == 'cellular'))", "((veri.yas >= 40 && veri.yas <= 60) && veri.medeniDurum == 'divorced') || (veri.egitim == 'university' && veri.hedef == 'yes' && veri.kampanyaSayisi == 1)", "((veri.egitim == 'university' || veri.egitim == 'high.school') && veri.yas > 55) && (veri.kampanyaSayisi > 1 || veri.iletisimTuru == 'telephone')", "veri.hedef == 'yes' && ((veri.yas < 35 && veri.medeniDurum == 'single') || (veri.yas > 60 && veri.egitim == 'unknown'))", "((veri.kampanyaSayisi > 3 && veri.iletisimTuru == 'telephone') || (veri.kampanyaSayisi <= 1 && veri.iletisimTuru == 'unknown')) && veri.hedef == 'no'", "veri.yas < 50 && ((veri.egitim == 'university' && veri.kampanyaSayisi > 1) || (veri.egitim == 'high.school' && veri.medeniDurum == 'married'))", "veri.iletisimTuru != 'unknown' && ((veri.yas < 35 && veri.hedef == 'no') || (veri.yas >= 60 && veri.kampanyaSayisi > 0))", "(veri.hedef == 'yes' && veri.egitim != 'unknown') && ((veri.kampanyaSayisi == 1 && veri.yas > 40) || (veri.kampanyaSayisi == 0 && veri.medeniDurum == 'single'))", "(veri.yas >= 50 && veri.medeniDurum == 'married') && (veri.egitim == 'university' || veri.iletisimTuru == 'cellular')", "(veri.hedef == 'no' && veri.iletisimTuru != 'unknown') && (veri.kampanyaSayisi > 1 && veri.yas < 45)", "(veri.iletisimTuru == 'telephone' && (veri.egitim == 'high.school' || veri.egitim == 'university')) && veri.hedef == 'yes'", "(veri.kampanyaSayisi == 0 && veri.medeniDurum == 'single') || (veri.yas > 60 && veri.hedef == 'no')", "(veri.egitim == 'unknown' && veri.yas < 40) && (veri.iletisimTuru == 'cellular' || veri.hedef == 'yes')", "(veri.medeniDurum == 'married' && veri.kampanyaSayisi >= 1 && veri.hedef == 'yes') || (veri.egitim == 'university' && veri.yas >= 30 && veri.iletisimTuru == 'telephone')", "(veri.yas > 35 && veri.kampanyaSayisi == 2) && (veri.medeniDurum == 'divorced' || veri.hedef == 'no')", "veri.iletisimTuru == 'telephone' && (veri.kampanyaSayisi > 0 && (veri.yas < 30 || veri.medeniDurum == 'single'))", "(veri.hedef == 'no' && veri.egitim == 'unknown') && (veri.iletisimTuru != 'cellular' && veri.kampanyaSayisi < 3)", "veri.yas >= 45 && veri.yas <= 55 && veri.egitim != 'unknown' && (veri.hedef == 'yes' || veri.kampanyaSayisi > 0)", "(veri.kampanyaSayisi == 1 && veri.medeniDurum == 'married') && (veri.egitim == 'high.school' && (veri.iletisimTuru == 'telephone' || veri.hedef == 'yes'))", "(veri.yas < 35 && (veri.kampanyaSayisi > 0 || veri.egitim == 'university')) && (veri.hedef == 'yes' && (veri.medeniDurum == 'single' || veri.iletisimTuru == 'cellular'))");

        new File(OUTPUT_DIR).mkdirs();

        for (int i = 0; i < conditions.size(); i++) {
            int ruleNumber = 76 + i;
            String ruleName = "rules_" + ruleNumber;
            String condition = conditions.get(i);
            String content = generateYamlRule(ruleName, "", condition, "veri.setKuralAdi('" + ruleName + "');");
            File file = new File(OUTPUT_DIR + ruleName + ".yml");

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(content);
                System.out.println("YAML rule written: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to write rule: " + ruleName);
                e.printStackTrace();
            }
        }
    }
}

