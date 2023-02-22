package org.example.util;

import java.util.Random;

public enum Month {
    OCAK,
    SUBAT,
    MART,
    NISAN,
    MAYIS,
    HAZIRAN,
    TEMMUZ,
    AGUSTOS,
    EYLUL,
    EKIM,
    KASIM,
    ARALIK;

    public static Month randomMonthSelector(){
        Random randomVal = new Random();
        Month[] months = Month.values();
        return months[randomVal.nextInt(months.length)];
    }
}
