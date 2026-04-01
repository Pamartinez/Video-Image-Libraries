package com.samsung.android.app.sdk.deepsky.barcode.parser.common;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/parser/common/ParserUtil;", "", "<init>", "()V", "getTtsOneDigitNumber", "", "number", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ParserUtil {
    public static final ParserUtil INSTANCE = new ParserUtil();

    private ParserUtil() {
    }

    public final String getTtsOneDigitNumber(String str) {
        j.e(str, "number");
        StringBuilder sb2 = new StringBuilder();
        char[] charArray = str.toCharArray();
        j.d(charArray, "toCharArray(...)");
        int length = charArray.length;
        int i2 = 0;
        int i7 = 0;
        while (i2 < length) {
            char c5 = charArray[i2];
            int i8 = i7 + 1;
            if ((j.f(c5, 48) >= 0 && j.f(c5, 57) <= 0) || c5 == '+' || c5 == '*' || c5 == '#') {
                sb2.append(c5);
                if (i7 < str.length() - 1) {
                    sb2.append(' ');
                }
            }
            i2++;
            i7 = i8;
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }
}
