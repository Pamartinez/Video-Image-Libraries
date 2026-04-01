package androidx.media3.extractor.text.ttml;

import F2.C0010c0;
import F2.E0;
import F2.L0;
import android.text.TextUtils;
import java.util.regex.Pattern;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TextEmphasis {
    private static final C0010c0 MARK_FILL_VALUES = C0010c0.x(2, "filled", "open");
    private static final C0010c0 MARK_SHAPE_VALUES = C0010c0.x(3, "dot", "sesame", "circle");
    private static final C0010c0 POSITION_VALUES = C0010c0.x(3, "after", "before", "outside");
    private static final C0010c0 SINGLE_STYLE_VALUES = C0010c0.x(2, "auto", "none");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s+");
    public final int markFill;
    public final int markShape;
    public final int position;

    private TextEmphasis(int i2, int i7, int i8) {
        this.markShape = i2;
        this.markFill = i7;
        this.position = i8;
    }

    public static TextEmphasis parse(String str) {
        C0010c0 c0Var;
        if (str == null) {
            return null;
        }
        String S = k.S(str.trim());
        if (S.isEmpty()) {
            return null;
        }
        String[] split = TextUtils.split(S, WHITESPACE_PATTERN);
        int length = split.length;
        if (length == 0) {
            c0Var = E0.m;
        } else if (length != 1) {
            c0Var = C0010c0.x(split.length, (Object[]) split.clone());
        } else {
            c0Var = new L0(split[0]);
        }
        return parseWords(c0Var);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ef  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.text.ttml.TextEmphasis parseWords(F2.C0010c0 r7) {
        /*
            F2.c0 r0 = POSITION_VALUES
            F2.H0 r0 = F2.C0040v.k(r0, r7)
            java.lang.String r1 = "outside"
            java.lang.Object r0 = F2.C0040v.h(r0, r1)
            java.lang.String r0 = (java.lang.String) r0
            int r2 = r0.hashCode()
            r3 = -1392885889(0xffffffffacfa3f7f, float:-7.112477E-12)
            r4 = 1
            r5 = 2
            if (r2 == r3) goto L_0x0036
            r3 = -1106037339(0xffffffffbe1335a5, float:-0.14375933)
            if (r2 == r3) goto L_0x002e
            r1 = 92734940(0x58705dc, float:1.2697491E-35)
            if (r2 == r1) goto L_0x0024
            goto L_0x003c
        L_0x0024:
            java.lang.String r1 = "after"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = r5
            goto L_0x003d
        L_0x002e:
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x003c
            r0 = -2
            goto L_0x003d
        L_0x0036:
            java.lang.String r1 = "before"
            boolean r0 = r0.equals(r1)
        L_0x003c:
            r0 = r4
        L_0x003d:
            F2.c0 r1 = SINGLE_STYLE_VALUES
            F2.H0 r1 = F2.C0040v.k(r1, r7)
            boolean r2 = r1.isEmpty()
            r3 = -1
            r6 = 0
            if (r2 != 0) goto L_0x007b
            F2.i0 r7 = new F2.i0
            r7.<init>(r1)
            java.lang.Object r7 = r7.next()
            java.lang.String r7 = (java.lang.String) r7
            int r1 = r7.hashCode()
            r2 = 3005871(0x2dddaf, float:4.212122E-39)
            if (r1 == r2) goto L_0x006f
            r2 = 3387192(0x33af38, float:4.746467E-39)
            if (r1 == r2) goto L_0x0065
            goto L_0x0075
        L_0x0065:
            java.lang.String r1 = "none"
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0075
            r3 = r6
            goto L_0x0075
        L_0x006f:
            java.lang.String r1 = "auto"
            boolean r7 = r7.equals(r1)
        L_0x0075:
            androidx.media3.extractor.text.ttml.TextEmphasis r7 = new androidx.media3.extractor.text.ttml.TextEmphasis
            r7.<init>(r3, r6, r0)
            return r7
        L_0x007b:
            F2.c0 r1 = MARK_FILL_VALUES
            F2.H0 r1 = F2.C0040v.k(r1, r7)
            F2.c0 r2 = MARK_SHAPE_VALUES
            F2.H0 r7 = F2.C0040v.k(r2, r7)
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L_0x0099
            boolean r2 = r7.isEmpty()
            if (r2 == 0) goto L_0x0099
            androidx.media3.extractor.text.ttml.TextEmphasis r7 = new androidx.media3.extractor.text.ttml.TextEmphasis
            r7.<init>(r3, r6, r0)
            return r7
        L_0x0099:
            java.lang.String r2 = "filled"
            java.lang.Object r1 = F2.C0040v.h(r1, r2)
            java.lang.String r1 = (java.lang.String) r1
            int r3 = r1.hashCode()
            r6 = -1274499742(0xffffffffb408ad62, float:-1.2729063E-7)
            if (r3 == r6) goto L_0x00ba
            r2 = 3417674(0x34264a, float:4.789181E-39)
            if (r3 == r2) goto L_0x00b0
            goto L_0x00be
        L_0x00b0:
            java.lang.String r2 = "open"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00be
            r1 = r5
            goto L_0x00bf
        L_0x00ba:
            boolean r1 = r1.equals(r2)
        L_0x00be:
            r1 = r4
        L_0x00bf:
            java.lang.String r2 = "circle"
            java.lang.Object r7 = F2.C0040v.h(r7, r2)
            java.lang.String r7 = (java.lang.String) r7
            int r3 = r7.hashCode()
            r6 = -1360216880(0xffffffffaeecbcd0, float:-1.0765577E-10)
            if (r3 == r6) goto L_0x00ef
            r2 = -905816648(0xffffffffca0255b8, float:-2135406.0)
            if (r3 == r2) goto L_0x00e5
            r2 = 99657(0x18549, float:1.39649E-40)
            if (r3 == r2) goto L_0x00db
            goto L_0x00f3
        L_0x00db:
            java.lang.String r2 = "dot"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x00f3
            r4 = r5
            goto L_0x00f3
        L_0x00e5:
            java.lang.String r2 = "sesame"
            boolean r7 = r7.equals(r2)
            if (r7 == 0) goto L_0x00f3
            r4 = 3
            goto L_0x00f3
        L_0x00ef:
            boolean r7 = r7.equals(r2)
        L_0x00f3:
            androidx.media3.extractor.text.ttml.TextEmphasis r7 = new androidx.media3.extractor.text.ttml.TextEmphasis
            r7.<init>(r4, r1, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TextEmphasis.parseWords(F2.c0):androidx.media3.extractor.text.ttml.TextEmphasis");
    }
}
