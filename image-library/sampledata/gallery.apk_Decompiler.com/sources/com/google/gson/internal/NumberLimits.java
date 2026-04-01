package com.google.gson.internal;

import i.C0212a;
import java.math.BigDecimal;
import java.math.BigInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NumberLimits {
    private static final int MAX_NUMBER_STRING_LENGTH = 10000;

    private NumberLimits() {
    }

    private static void checkNumberStringLength(String str) {
        if (str.length() > 10000) {
            throw new NumberFormatException("Number string too large: " + str.substring(0, 30) + "...");
        }
    }

    public static BigDecimal parseBigDecimal(String str) {
        checkNumberStringLength(str);
        BigDecimal bigDecimal = new BigDecimal(str);
        if (Math.abs((long) bigDecimal.scale()) < 10000) {
            return bigDecimal;
        }
        throw new NumberFormatException(C0212a.l("Number has unsupported scale: ", str));
    }

    public static BigInteger parseBigInteger(String str) {
        checkNumberStringLength(str);
        return new BigInteger(str);
    }
}
