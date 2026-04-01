package com.adobe.internal.xmp.impl;

import com.adobe.internal.xmp.XMPDateTime;
import com.samsung.scsp.framework.core.network.Network;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ISO8601Converter {
    private ISO8601Converter() {
    }

    public static XMPDateTime parse(String str) {
        return parse(str, new XMPDateTimeImpl());
    }

    public static String render(XMPDateTime xMPDateTime) {
        StringBuffer stringBuffer = new StringBuffer();
        if (xMPDateTime.hasDate()) {
            DecimalFormat decimalFormat = new DecimalFormat("0000", new DecimalFormatSymbols(Locale.ENGLISH));
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.getYear()));
            if (xMPDateTime.getMonth() == 0) {
                return stringBuffer.toString();
            }
            decimalFormat.applyPattern("'-'00");
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.getMonth()));
            if (xMPDateTime.getDay() == 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(decimalFormat.format((long) xMPDateTime.getDay()));
            if (xMPDateTime.hasTime()) {
                stringBuffer.append('T');
                decimalFormat.applyPattern("00");
                stringBuffer.append(decimalFormat.format((long) xMPDateTime.getHour()));
                stringBuffer.append(':');
                stringBuffer.append(decimalFormat.format((long) xMPDateTime.getMinute()));
                if (!(xMPDateTime.getSecond() == 0 && xMPDateTime.getNanoSecond() == 0)) {
                    double nanoSecond = ((double) xMPDateTime.getNanoSecond()) / 1.0E9d;
                    decimalFormat.applyPattern(":00.#########");
                    stringBuffer.append(decimalFormat.format(nanoSecond + ((double) xMPDateTime.getSecond())));
                }
                if (xMPDateTime.hasTimeZone()) {
                    int offset = xMPDateTime.getTimeZone().getOffset(xMPDateTime.getCalendar().getTimeInMillis());
                    if (offset == 0) {
                        stringBuffer.append('Z');
                    } else {
                        int i2 = offset / 3600000;
                        int abs = Math.abs((offset % 3600000) / Network.DEFAULT_TIMEOUT);
                        decimalFormat.applyPattern("+00;-00");
                        stringBuffer.append(decimalFormat.format((long) i2));
                        decimalFormat.applyPattern(":00");
                        stringBuffer.append(decimalFormat.format((long) abs));
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:123:0x0224  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.adobe.internal.xmp.XMPDateTime parse(java.lang.String r12, com.adobe.internal.xmp.XMPDateTime r13) {
        /*
            if (r12 == 0) goto L_0x022c
            int r0 = r12.length()
            if (r0 != 0) goto L_0x0009
            return r13
        L_0x0009:
            com.adobe.internal.xmp.impl.ParseState r0 = new com.adobe.internal.xmp.impl.ParseState
            r0.<init>(r12)
            r12 = 0
            char r1 = r0.ch(r12)
            r2 = 45
            if (r1 != r2) goto L_0x001a
            r0.skip()
        L_0x001a:
            java.lang.String r1 = "Invalid year in date string"
            r3 = 9999(0x270f, float:1.4012E-41)
            int r1 = r0.gatherInt(r1, r3)
            boolean r3 = r0.hasNext()
            r4 = 5
            if (r3 == 0) goto L_0x0038
            char r3 = r0.ch()
            if (r3 != r2) goto L_0x0030
            goto L_0x0038
        L_0x0030:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after year"
            r12.<init>(r13, r4)
            throw r12
        L_0x0038:
            char r3 = r0.ch(r12)
            if (r3 != r2) goto L_0x003f
            int r1 = -r1
        L_0x003f:
            r13.setYear(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x004a
            goto L_0x0223
        L_0x004a:
            r0.skip()
            java.lang.String r1 = "Invalid month in date string"
            r3 = 12
            int r1 = r0.gatherInt(r1, r3)
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x006a
            char r3 = r0.ch()
            if (r3 != r2) goto L_0x0062
            goto L_0x006a
        L_0x0062:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after month"
            r12.<init>(r13, r4)
            throw r12
        L_0x006a:
            r13.setMonth(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x0075
            goto L_0x0223
        L_0x0075:
            r0.skip()
            java.lang.String r1 = "Invalid day in date string"
            r3 = 31
            int r1 = r0.gatherInt(r1, r3)
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0097
            char r3 = r0.ch()
            r5 = 84
            if (r3 != r5) goto L_0x008f
            goto L_0x0097
        L_0x008f:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after day"
            r12.<init>(r13, r4)
            throw r12
        L_0x0097:
            r13.setDay(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x00a2
            goto L_0x0223
        L_0x00a2:
            r0.skip()
            java.lang.String r1 = "Invalid hour in date string"
            r3 = 23
            int r1 = r0.gatherInt(r1, r3)
            r13.setHour(r1)
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x00b8
            goto L_0x0223
        L_0x00b8:
            char r1 = r0.ch()
            r5 = 59
            r6 = 58
            r7 = 43
            r8 = 90
            if (r1 != r6) goto L_0x00f9
            r0.skip()
            java.lang.String r1 = "Invalid minute in date string"
            int r1 = r0.gatherInt(r1, r5)
            boolean r9 = r0.hasNext()
            if (r9 == 0) goto L_0x00f6
            char r9 = r0.ch()
            if (r9 == r6) goto L_0x00f6
            char r9 = r0.ch()
            if (r9 == r8) goto L_0x00f6
            char r9 = r0.ch()
            if (r9 == r7) goto L_0x00f6
            char r9 = r0.ch()
            if (r9 != r2) goto L_0x00ee
            goto L_0x00f6
        L_0x00ee:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after minute"
            r12.<init>(r13, r4)
            throw r12
        L_0x00f6:
            r13.setMinute(r1)
        L_0x00f9:
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x0101
            goto L_0x0223
        L_0x0101:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0192
            char r1 = r0.ch()
            if (r1 != r6) goto L_0x0192
            r0.skip()
            java.lang.String r1 = "Invalid whole seconds in date string"
            int r1 = r0.gatherInt(r1, r5)
            boolean r9 = r0.hasNext()
            r10 = 46
            if (r9 == 0) goto L_0x013f
            char r9 = r0.ch()
            if (r9 == r10) goto L_0x013f
            char r9 = r0.ch()
            if (r9 == r8) goto L_0x013f
            char r9 = r0.ch()
            if (r9 == r7) goto L_0x013f
            char r9 = r0.ch()
            if (r9 != r2) goto L_0x0137
            goto L_0x013f
        L_0x0137:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after whole seconds"
            r12.<init>(r13, r4)
            throw r12
        L_0x013f:
            r13.setSecond(r1)
            char r1 = r0.ch()
            if (r1 != r10) goto L_0x01ad
            r0.skip()
            int r1 = r0.pos()
            java.lang.String r9 = "Invalid fractional seconds in date string"
            r10 = 999999999(0x3b9ac9ff, float:0.004723787)
            int r9 = r0.gatherInt(r9, r10)
            boolean r10 = r0.hasNext()
            if (r10 == 0) goto L_0x0179
            char r10 = r0.ch()
            if (r10 == r8) goto L_0x0179
            char r10 = r0.ch()
            if (r10 == r7) goto L_0x0179
            char r10 = r0.ch()
            if (r10 != r2) goto L_0x0171
            goto L_0x0179
        L_0x0171:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after fractional second"
            r12.<init>(r13, r4)
            throw r12
        L_0x0179:
            int r10 = r0.pos()
            int r10 = r10 - r1
        L_0x017e:
            r1 = 9
            if (r10 <= r1) goto L_0x0187
            int r9 = r9 / 10
            int r10 = r10 + -1
            goto L_0x017e
        L_0x0187:
            if (r10 >= r1) goto L_0x018e
            int r9 = r9 * 10
            int r10 = r10 + 1
            goto L_0x0187
        L_0x018e:
            r13.setNanoSecond(r9)
            goto L_0x01ad
        L_0x0192:
            char r1 = r0.ch()
            if (r1 == r8) goto L_0x01ad
            char r1 = r0.ch()
            if (r1 == r7) goto L_0x01ad
            char r1 = r0.ch()
            if (r1 != r2) goto L_0x01a5
            goto L_0x01ad
        L_0x01a5:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after time"
            r12.<init>(r13, r4)
            throw r12
        L_0x01ad:
            boolean r1 = r0.hasNext()
            if (r1 != 0) goto L_0x01b5
            goto L_0x0223
        L_0x01b5:
            char r1 = r0.ch()
            if (r1 != r8) goto L_0x01bf
            r0.skip()
            goto L_0x0207
        L_0x01bf:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0207
            char r1 = r0.ch()
            if (r1 != r7) goto L_0x01cd
            r1 = 1
            goto L_0x01d4
        L_0x01cd:
            char r1 = r0.ch()
            if (r1 != r2) goto L_0x01ff
            r1 = -1
        L_0x01d4:
            r0.skip()
            java.lang.String r2 = "Invalid time zone hour in date string"
            int r2 = r0.gatherInt(r2, r3)
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x01f2
            char r12 = r0.ch()
            if (r12 != r6) goto L_0x01f7
            r0.skip()
            java.lang.String r12 = "Invalid time zone minute in date string"
            int r12 = r0.gatherInt(r12, r5)
        L_0x01f2:
            r11 = r1
            r1 = r12
            r12 = r2
            r2 = r11
            goto L_0x0209
        L_0x01f7:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, after time zone hour"
            r12.<init>(r13, r4)
            throw r12
        L_0x01ff:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Time zone must begin with 'Z', '+', or '-'"
            r12.<init>(r13, r4)
            throw r12
        L_0x0207:
            r1 = r12
            r2 = r1
        L_0x0209:
            r3 = 3600000(0x36ee80, float:5.044674E-39)
            int r12 = r12 * r3
            r3 = 60000(0xea60, float:8.4078E-41)
            int r1 = r1 * r3
            int r1 = r1 + r12
            int r1 = r1 * r2
            java.util.SimpleTimeZone r12 = new java.util.SimpleTimeZone
            java.lang.String r2 = ""
            r12.<init>(r1, r2)
            r13.setTimeZone(r12)
            boolean r12 = r0.hasNext()
            if (r12 != 0) goto L_0x0224
        L_0x0223:
            return r13
        L_0x0224:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Invalid date string, extra chars at end"
            r12.<init>(r13, r4)
            throw r12
        L_0x022c:
            com.adobe.internal.xmp.XMPException r12 = new com.adobe.internal.xmp.XMPException
            java.lang.String r13 = "Parameter must not be null"
            r0 = 4
            r12.<init>(r13, r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.internal.xmp.impl.ISO8601Converter.parse(java.lang.String, com.adobe.internal.xmp.XMPDateTime):com.adobe.internal.xmp.XMPDateTime");
    }
}
