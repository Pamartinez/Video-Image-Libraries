package androidx.media3.extractor.text.ssa;

import A.a;
import android.graphics.Color;
import android.graphics.PointF;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SsaStyle {
    public final int alignment;
    public final boolean bold;
    public final int borderStyle;
    public final float fontSize;
    public final boolean italic;
    public final String name;
    public final Integer outlineColor;
    public final Integer primaryColor;
    public final boolean strikeout;
    public final boolean underline;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Format {
        public final int alignmentIndex;
        public final int boldIndex;
        public final int borderStyleIndex;
        public final int fontSizeIndex;
        public final int italicIndex;
        public final int length;
        public final int nameIndex;
        public final int outlineColorIndex;
        public final int primaryColorIndex;
        public final int strikeoutIndex;
        public final int underlineIndex;

        private Format(int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17) {
            this.nameIndex = i2;
            this.alignmentIndex = i7;
            this.primaryColorIndex = i8;
            this.outlineColorIndex = i10;
            this.fontSizeIndex = i11;
            this.boldIndex = i12;
            this.italicIndex = i13;
            this.underlineIndex = i14;
            this.strikeoutIndex = i15;
            this.borderStyleIndex = i16;
            this.length = i17;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static androidx.media3.extractor.text.ssa.SsaStyle.Format fromFormatLine(java.lang.String r17) {
            /*
                r0 = 7
                r1 = r17
                java.lang.String r1 = r1.substring(r0)
                java.lang.String r2 = ","
                java.lang.String[] r1 = android.text.TextUtils.split(r1, r2)
                r2 = -1
                r3 = 0
                r6 = r2
                r7 = r6
                r8 = r7
                r9 = r8
                r10 = r9
                r11 = r10
                r12 = r11
                r13 = r12
                r14 = r13
                r15 = r14
                r4 = r3
            L_0x001a:
                int r5 = r1.length
                if (r4 >= r5) goto L_0x00c3
                r5 = r1[r4]
                java.lang.String r5 = r5.trim()
                java.lang.String r5 = og.k.S(r5)
                r5.getClass()
                int r16 = r5.hashCode()
                switch(r16) {
                    case -1178781136: goto L_0x009d;
                    case -1026963764: goto L_0x0091;
                    case -192095652: goto L_0x0085;
                    case -70925746: goto L_0x007a;
                    case 3029637: goto L_0x006f;
                    case 3373707: goto L_0x0064;
                    case 366554320: goto L_0x0059;
                    case 767321349: goto L_0x004e;
                    case 1767875043: goto L_0x0041;
                    case 1988365454: goto L_0x0034;
                    default: goto L_0x0031;
                }
            L_0x0031:
                r0 = r2
                goto L_0x00a7
            L_0x0034:
                java.lang.String r0 = "outlinecolour"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x003d
                goto L_0x0031
            L_0x003d:
                r0 = 9
                goto L_0x00a7
            L_0x0041:
                java.lang.String r0 = "alignment"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x004a
                goto L_0x0031
            L_0x004a:
                r0 = 8
                goto L_0x00a7
            L_0x004e:
                java.lang.String r0 = "borderstyle"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x0057
                goto L_0x0031
            L_0x0057:
                r0 = 7
                goto L_0x00a7
            L_0x0059:
                java.lang.String r0 = "fontsize"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x0062
                goto L_0x0031
            L_0x0062:
                r0 = 6
                goto L_0x00a7
            L_0x0064:
                java.lang.String r0 = "name"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x006d
                goto L_0x0031
            L_0x006d:
                r0 = 5
                goto L_0x00a7
            L_0x006f:
                java.lang.String r0 = "bold"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x0078
                goto L_0x0031
            L_0x0078:
                r0 = 4
                goto L_0x00a7
            L_0x007a:
                java.lang.String r0 = "primarycolour"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x0083
                goto L_0x0031
            L_0x0083:
                r0 = 3
                goto L_0x00a7
            L_0x0085:
                java.lang.String r0 = "strikeout"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x008f
                goto L_0x0031
            L_0x008f:
                r0 = 2
                goto L_0x00a7
            L_0x0091:
                java.lang.String r0 = "underline"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x009b
                goto L_0x0031
            L_0x009b:
                r0 = 1
                goto L_0x00a7
            L_0x009d:
                java.lang.String r0 = "italic"
                boolean r0 = r5.equals(r0)
                if (r0 != 0) goto L_0x00a6
                goto L_0x0031
            L_0x00a6:
                r0 = r3
            L_0x00a7:
                switch(r0) {
                    case 0: goto L_0x00bd;
                    case 1: goto L_0x00bb;
                    case 2: goto L_0x00b9;
                    case 3: goto L_0x00b7;
                    case 4: goto L_0x00b5;
                    case 5: goto L_0x00b3;
                    case 6: goto L_0x00b1;
                    case 7: goto L_0x00af;
                    case 8: goto L_0x00ad;
                    case 9: goto L_0x00ab;
                    default: goto L_0x00aa;
                }
            L_0x00aa:
                goto L_0x00be
            L_0x00ab:
                r9 = r4
                goto L_0x00be
            L_0x00ad:
                r7 = r4
                goto L_0x00be
            L_0x00af:
                r15 = r4
                goto L_0x00be
            L_0x00b1:
                r10 = r4
                goto L_0x00be
            L_0x00b3:
                r6 = r4
                goto L_0x00be
            L_0x00b5:
                r11 = r4
                goto L_0x00be
            L_0x00b7:
                r8 = r4
                goto L_0x00be
            L_0x00b9:
                r14 = r4
                goto L_0x00be
            L_0x00bb:
                r13 = r4
                goto L_0x00be
            L_0x00bd:
                r12 = r4
            L_0x00be:
                int r4 = r4 + 1
                r0 = 7
                goto L_0x001a
            L_0x00c3:
                if (r6 == r2) goto L_0x00ce
                androidx.media3.extractor.text.ssa.SsaStyle$Format r5 = new androidx.media3.extractor.text.ssa.SsaStyle$Format
                int r0 = r1.length
                r16 = r0
                r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
                return r5
            L_0x00ce:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ssa.SsaStyle.Format.fromFormatLine(java.lang.String):androidx.media3.extractor.text.ssa.SsaStyle$Format");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Overrides {
        private static final Pattern ALIGNMENT_OVERRIDE_PATTERN = Pattern.compile("\\\\an(\\d+)");
        private static final Pattern BRACES_PATTERN = Pattern.compile("\\{([^}]*)\\}");
        private static final Pattern MOVE_PATTERN = Pattern.compile(Util.formatInvariant("\\\\move\\(%1$s,%1$s,(%1$s),(%1$s)(?:,%1$s,%1$s)?\\)", "\\s*\\d+(?:\\.\\d+)?\\s*"));
        private static final Pattern POSITION_PATTERN = Pattern.compile(Util.formatInvariant("\\\\pos\\((%1$s),(%1$s)\\)", "\\s*\\d+(?:\\.\\d+)?\\s*"));
        public final int alignment;
        public final PointF position;

        private Overrides(int i2, PointF pointF) {
            this.alignment = i2;
            this.position = pointF;
        }

        private static int parseAlignmentOverride(String str) {
            Matcher matcher = ALIGNMENT_OVERRIDE_PATTERN.matcher(str);
            if (matcher.find()) {
                return SsaStyle.parseAlignment((String) Assertions.checkNotNull(matcher.group(1)));
            }
            return -1;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(9:4|5|6|(1:8)|9|10|(2:12|18)(1:17)|15|1) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0021 */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x0009 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static androidx.media3.extractor.text.ssa.SsaStyle.Overrides parseFromDialogue(java.lang.String r5) {
            /*
                java.util.regex.Pattern r0 = BRACES_PATTERN
                java.util.regex.Matcher r5 = r0.matcher(r5)
                r0 = -1
                r1 = 0
                r2 = r0
            L_0x0009:
                boolean r3 = r5.find()
                if (r3 == 0) goto L_0x0029
                r3 = 1
                java.lang.String r3 = r5.group(r3)
                java.lang.Object r3 = androidx.media3.common.util.Assertions.checkNotNull(r3)
                java.lang.String r3 = (java.lang.String) r3
                android.graphics.PointF r4 = parsePosition(r3)     // Catch:{ RuntimeException -> 0x0021 }
                if (r4 == 0) goto L_0x0021
                r1 = r4
            L_0x0021:
                int r3 = parseAlignmentOverride(r3)     // Catch:{ RuntimeException -> 0x0009 }
                if (r3 == r0) goto L_0x0009
                r2 = r3
                goto L_0x0009
            L_0x0029:
                androidx.media3.extractor.text.ssa.SsaStyle$Overrides r5 = new androidx.media3.extractor.text.ssa.SsaStyle$Overrides
                r5.<init>(r2, r1)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ssa.SsaStyle.Overrides.parseFromDialogue(java.lang.String):androidx.media3.extractor.text.ssa.SsaStyle$Overrides");
        }

        private static PointF parsePosition(String str) {
            String str2;
            String str3;
            Matcher matcher = POSITION_PATTERN.matcher(str);
            Matcher matcher2 = MOVE_PATTERN.matcher(str);
            boolean find = matcher.find();
            boolean find2 = matcher2.find();
            if (find) {
                if (find2) {
                    Log.i("SsaStyle.Overrides", "Override has both \\pos(x,y) and \\move(x1,y1,x2,y2); using \\pos values. override='" + str + "'");
                }
                str2 = matcher.group(1);
                str3 = matcher.group(2);
            } else if (!find2) {
                return null;
            } else {
                str2 = matcher2.group(1);
                str3 = matcher2.group(2);
            }
            return new PointF(Float.parseFloat(((String) Assertions.checkNotNull(str2)).trim()), Float.parseFloat(((String) Assertions.checkNotNull(str3)).trim()));
        }

        public static String stripStyleOverrides(String str) {
            return BRACES_PATTERN.matcher(str).replaceAll("");
        }
    }

    private SsaStyle(String str, int i2, Integer num, Integer num2, float f, boolean z, boolean z3, boolean z7, boolean z9, int i7) {
        this.name = str;
        this.alignment = i2;
        this.primaryColor = num;
        this.outlineColor = num2;
        this.fontSize = f;
        this.bold = z;
        this.italic = z3;
        this.underline = z7;
        this.strikeout = z9;
        this.borderStyle = i7;
    }

    public static SsaStyle fromStyleLine(String str, Format format) {
        int i2;
        Integer num;
        Integer num2;
        float f;
        boolean z;
        boolean z3;
        boolean z7;
        String str2 = str;
        Format format2 = format;
        Assertions.checkArgument(str2.startsWith("Style:"));
        String[] split = TextUtils.split(str2.substring(6), GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int length = split.length;
        int i7 = format2.length;
        if (length != i7) {
            Log.w("SsaStyle", Util.formatInvariant("Skipping malformed 'Style:' line (expected %s values, found %s): '%s'", Integer.valueOf(i7), Integer.valueOf(split.length), str2));
            return null;
        }
        try {
            String trim = split[format2.nameIndex].trim();
            int i8 = format2.alignmentIndex;
            int i10 = -1;
            if (i8 != -1) {
                i2 = parseAlignment(split[i8].trim());
            } else {
                i2 = -1;
            }
            int i11 = format2.primaryColorIndex;
            if (i11 != -1) {
                num = parseColor(split[i11].trim());
            } else {
                num = null;
            }
            int i12 = format2.outlineColorIndex;
            if (i12 != -1) {
                num2 = parseColor(split[i12].trim());
            } else {
                num2 = null;
            }
            int i13 = format2.fontSizeIndex;
            if (i13 != -1) {
                f = parseFontSize(split[i13].trim());
            } else {
                f = -3.4028235E38f;
            }
            float f5 = f;
            int i14 = format2.boldIndex;
            boolean z9 = false;
            boolean z10 = true;
            if (i14 == -1 || !parseBooleanValue(split[i14].trim())) {
                z = false;
            } else {
                z = false;
                z9 = true;
            }
            int i15 = format2.italicIndex;
            if (i15 == -1 || !parseBooleanValue(split[i15].trim())) {
                z3 = true;
                z10 = z;
            } else {
                z3 = true;
            }
            int i16 = format2.underlineIndex;
            if (i16 == -1 || !parseBooleanValue(split[i16].trim())) {
                z3 = false;
            }
            int i17 = format2.strikeoutIndex;
            if (i17 == -1 || !parseBooleanValue(split[i17].trim())) {
                z7 = false;
            } else {
                z7 = true;
            }
            int i18 = format2.borderStyleIndex;
            if (i18 != -1) {
                i10 = parseBorderStyle(split[i18].trim());
            }
            return new SsaStyle(trim, i2, num, num2, f5, z9, z10, z3, z7, i10);
        } catch (RuntimeException e) {
            Log.w("SsaStyle", "Skipping malformed 'Style:' line: '" + str2 + "'", e);
            return null;
        }
    }

    private static boolean isValidAlignment(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    private static boolean isValidBorderStyle(int i2) {
        if (i2 == 1 || i2 == 3) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static int parseAlignment(String str) {
        try {
            int parseInt = Integer.parseInt(str.trim());
            if (isValidAlignment(parseInt)) {
                return parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        a.C("Ignoring unknown alignment: ", str, "SsaStyle");
        return -1;
    }

    private static boolean parseBooleanValue(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            if (parseInt == 1 || parseInt == -1) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            Log.w("SsaStyle", "Failed to parse boolean value: '" + str + "'", e);
            return false;
        }
    }

    private static int parseBorderStyle(String str) {
        try {
            int parseInt = Integer.parseInt(str.trim());
            if (isValidBorderStyle(parseInt)) {
                return parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        a.C("Ignoring unknown BorderStyle: ", str, "SsaStyle");
        return -1;
    }

    public static Integer parseColor(String str) {
        long j2;
        boolean z;
        try {
            if (str.startsWith("&H")) {
                j2 = Long.parseLong(str.substring(2), 16);
            } else {
                j2 = Long.parseLong(str);
            }
            if (j2 <= 4294967295L) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z);
            int N6 = C0246a.N(((j2 >> 24) & 255) ^ 255);
            int N10 = C0246a.N((j2 >> 16) & 255);
            return Integer.valueOf(Color.argb(N6, C0246a.N(j2 & 255), C0246a.N((j2 >> 8) & 255), N10));
        } catch (IllegalArgumentException e) {
            Log.w("SsaStyle", "Failed to parse color expression: '" + str + "'", e);
            return null;
        }
    }

    private static float parseFontSize(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            Log.w("SsaStyle", "Failed to parse font size: '" + str + "'", e);
            return -3.4028235E38f;
        }
    }
}
