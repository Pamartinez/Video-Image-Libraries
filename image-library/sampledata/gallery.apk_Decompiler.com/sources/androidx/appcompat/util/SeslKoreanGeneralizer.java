package androidx.appcompat.util;

import android.util.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslKoreanGeneralizer {
    private static final Pattern HAS_JOSA_PATTERN = Pattern.compile("(?s)(.*)\\((.+)\\)(.*)");
    private Map<String, Pair<String, String>> JOSA_KOREAN_MAP = new HashMap() {
        {
            put("은(는)", new Pair("은", "는"));
            put("(은)는", new Pair("은", "는"));
            put("이(가)", new Pair("이", "가"));
            put("(이)가", new Pair("이", "가"));
            put("을(를)", new Pair("을", "를"));
            put("(을)를", new Pair("을", "를"));
            put("와(과)", new Pair("과", "와"));
            put("(와)과", new Pair("과", "와"));
            put("아(야)", new Pair("아", "야"));
            put("(아)야", new Pair("아", "야"));
            put("(이)여", new Pair("이여", "여"));
            put("(으)로", new Pair("으로", "로"));
            put("(이)라", new Pair("이라", "라"));
            put("(이에)예", new Pair("이에", "예"));
            put("이에(예)", new Pair("이에", "예"));
            put("(이었)였", new Pair("이었", "였"));
            put("이었(였)", new Pair("이었", "였"));
            put("(이)네", new Pair("이네", "네"));
        }
    };
    private final Map<Character, Pair<Boolean, Boolean>> PRONOUNCEABLE_SYMBOLS = new HashMap() {
        {
            Boolean bool = Boolean.TRUE;
            Boolean bool2 = Boolean.FALSE;
            put('0', new Pair(bool, bool2));
            put('1', new Pair(bool, bool));
            put('2', new Pair(bool2, bool2));
            put('3', new Pair(bool, bool2));
            put('4', new Pair(bool2, bool2));
            put('5', new Pair(bool2, bool2));
            put('6', new Pair(bool, bool2));
            put('7', new Pair(bool, bool));
            put('8', new Pair(bool, bool));
            put('9', new Pair(bool2, bool2));
            put('%', new Pair(bool2, bool2));
            put(65285, new Pair(bool2, bool2));
            put('$', new Pair(bool2, bool2));
            put('#', new Pair(bool, bool2));
            put(8451, new Pair(bool2, bool2));
            put(8457, new Pair(bool2, bool2));
            put(13221, new Pair(bool2, bool2));
            put('+', new Pair(bool2, bool2));
            put(176, new Pair(bool2, bool2));
            put(186, new Pair(bool2, bool2));
            put(13252, new Pair(bool2, bool2));
            put(13206, new Pair(bool2, bool2));
            put(8467, new Pair(bool2, bool2));
            put(13256, new Pair(bool, bool));
            put(13189, new Pair(bool2, bool2));
            put(13190, new Pair(bool2, bool2));
            put(13191, new Pair(bool2, bool2));
            put(13268, new Pair(bool2, bool2));
        }
    };

    private Boolean checkIfEndsWithKoreanJongSung(int i2, Boolean bool) {
        if (44032 > i2 || i2 > 55203) {
            return null;
        }
        int i7 = (i2 - 44032) % 28;
        boolean booleanValue = bool.booleanValue();
        boolean z = false;
        if (booleanValue && (i7 == 0 || i7 == 8)) {
            i7 = 0;
        }
        if (i7 > 0) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    private Boolean checkIfEndsWithPronounceableSymbols(char c5, Boolean bool) {
        Pair pair = this.PRONOUNCEABLE_SYMBOLS.get(Character.valueOf(c5));
        if (pair == null) {
            return null;
        }
        boolean booleanValue = ((Boolean) pair.first).booleanValue();
        if (((Boolean) pair.second).booleanValue() && bool.booleanValue()) {
            booleanValue = !booleanValue;
        }
        return Boolean.valueOf(booleanValue);
    }

    private Boolean hasJosaInString(String str) {
        return Boolean.valueOf(HAS_JOSA_PATTERN.matcher(str).matches());
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c8 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String naturalize(java.lang.String r13) {
        /*
            r12 = this;
            boolean r0 = r13.isEmpty()
            java.lang.String r1 = ""
            if (r0 == 0) goto L_0x0009
            return r1
        L_0x0009:
            java.lang.Boolean r0 = r12.hasJosaInString(r13)
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0014
            return r13
        L_0x0014:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r2 = r13.length()
            r0.<init>(r2)
            r2 = 0
            r3 = r2
            r4 = r3
        L_0x0020:
            int r5 = r13.length()
            if (r3 >= r5) goto L_0x00cb
            java.lang.String r5 = r13.substring(r3)
            java.util.Map<java.lang.String, android.util.Pair<java.lang.String, java.lang.String>> r6 = r12.JOSA_KOREAN_MAP
            java.util.Set r6 = r6.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0034:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0047
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            boolean r8 = r5.startsWith(r7)
            if (r8 == 0) goto L_0x0034
            goto L_0x0048
        L_0x0047:
            r7 = 0
        L_0x0048:
            r5 = 1
            if (r7 == 0) goto L_0x0075
            boolean r6 = r7.isEmpty()
            if (r6 != 0) goto L_0x0075
            java.util.Map<java.lang.String, android.util.Pair<java.lang.String, java.lang.String>> r6 = r12.JOSA_KOREAN_MAP
            java.lang.Object r6 = r6.get(r7)
            android.util.Pair r6 = (android.util.Pair) r6
            java.lang.Object r6 = r6.first
            java.lang.String r6 = (java.lang.String) r6
            java.util.Map<java.lang.String, android.util.Pair<java.lang.String, java.lang.String>> r8 = r12.JOSA_KOREAN_MAP
            java.lang.Object r8 = r8.get(r7)
            android.util.Pair r8 = (android.util.Pair) r8
            java.lang.Object r8 = r8.second
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r9 = "(으)로"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L_0x0073
            r9 = r5
            goto L_0x0079
        L_0x0073:
            r9 = r2
            goto L_0x0079
        L_0x0075:
            r6 = r1
            r7 = r6
            r8 = r7
            goto L_0x0073
        L_0x0079:
            char r10 = r13.charAt(r3)
            boolean r11 = r6.isEmpty()
            if (r11 == 0) goto L_0x008d
            boolean r11 = r8.isEmpty()
            if (r11 == 0) goto L_0x008d
            r0.append(r10)
            goto L_0x00bf
        L_0x008d:
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r9)
            java.lang.Boolean r11 = r12.checkIfEndsWithKoreanJongSung(r4, r11)
            if (r11 != 0) goto L_0x009f
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            java.lang.Boolean r11 = r12.checkIfEndsWithPronounceableSymbols(r4, r9)
        L_0x009f:
            if (r11 == 0) goto L_0x00bc
            boolean r9 = r11.booleanValue()
            if (r9 == 0) goto L_0x00a8
            goto L_0x00a9
        L_0x00a8:
            r6 = r8
        L_0x00a9:
            r0.append(r6)
            int r7 = r7.length()
            int r7 = r7 - r5
            int r3 = r3 + r7
            int r7 = r6.length()
            int r7 = r7 - r5
            char r10 = r6.charAt(r7)
            goto L_0x00bf
        L_0x00bc:
            r0.append(r10)
        L_0x00bf:
            java.lang.String r6 = "()[]<>{};:|`'\"\\.=!?&。 ♡♥…«»‘’‚‛“”„‟‹›❛❜❝❞〝〞〟＂＇"
            int r6 = r6.indexOf(r10)
            if (r6 >= 0) goto L_0x00c8
            r4 = r10
        L_0x00c8:
            int r3 = r3 + r5
            goto L_0x0020
        L_0x00cb:
            java.lang.String r12 = r0.toString()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.util.SeslKoreanGeneralizer.naturalize(java.lang.String):java.lang.String");
    }

    public String naturalizeText(String str) {
        return naturalize(str);
    }
}
