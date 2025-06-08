package com.rules.compare.util;

import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.lang.management.ManagementFactory;

public class SistemKaynakOlcumUtil {

    public static long getDosyaBoyutuBayt(File dosyaYolu) {
        File dosya = dosyaYolu;
        if (dosya.exists() && dosya.isFile()) {
            long boyutBayt = dosya.length();
            return boyutBayt;
        }
        return 0;
    }

    public static long getSistemSaatMs() {
        return System.currentTimeMillis();
    }

    public static int getRamKullanimiMb() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        return (int) (usedMemory / (1024 * 1024));
    }

    public static double getCpuKullanimiYuzde() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        double processCpuLoad = osBean.getProcessCpuLoad();

        if (processCpuLoad < 0) {
            return -1;
        }

        return Math.round(processCpuLoad * 10000.0) / 100.0;
    }
}