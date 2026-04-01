package androidx.media3.extractor.text.subrip;

import F2.U;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SubripParser implements SubtitleParser {
    private static final Pattern SUBRIP_TAG_PATTERN = Pattern.compile("\\{\\\\.*?\\}");
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d{3}))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d{3}))?)\\s*");
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();
    private final ArrayList<String> tags = new ArrayList<>();
    private final StringBuilder textBuilder = new StringBuilder();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0046, code lost:
        if (r14.equals("{\\an7}") != false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004d, code lost:
        if (r14.equals("{\\an6}") != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0059, code lost:
        if (r14.equals("{\\an4}") != false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0060, code lost:
        if (r14.equals("{\\an3}") != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0062, code lost:
        r12.setPositionAnchor(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006f, code lost:
        if (r14.equals("{\\an1}") != false) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0071, code lost:
        r12.setPositionAnchor(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0075, code lost:
        r12.setPositionAnchor(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        switch(r14.hashCode()) {
            case -685620710: goto L_0x00b5;
            case -685620679: goto L_0x00ae;
            case -685620648: goto L_0x00a7;
            case -685620617: goto L_0x00a2;
            case -685620586: goto L_0x009d;
            case -685620555: goto L_0x0098;
            case -685620524: goto L_0x008e;
            case -685620493: goto L_0x0087;
            case -685620462: goto L_0x0080;
            default: goto L_0x007f;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0084, code lost:
        if (r14.equals("{\\an9}") == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        if (r14.equals("{\\an8}") == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0092, code lost:
        if (r14.equals("{\\an7}") == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0094, code lost:
        r12.setLineAnchor(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0098, code lost:
        r13 = r14.equals("{\\an6}");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009d, code lost:
        r13 = r14.equals("{\\an5}");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a2, code lost:
        r13 = r14.equals("{\\an4}");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ab, code lost:
        if (r14.equals("{\\an3}") == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b2, code lost:
        if (r14.equals("{\\an2}") == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b9, code lost:
        if (r14.equals("{\\an1}") == false) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00bb, code lost:
        r12.setLineAnchor(2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bf, code lost:
        r12.setLineAnchor(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00de, code lost:
        return r12.setPosition(getFractionalPositionForAnchorType(r12.getPositionAnchor())).setLine(getFractionalPositionForAnchorType(r12.getLineAnchor()), 0).build();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003a, code lost:
        if (r14.equals("{\\an9}") != false) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media3.common.text.Cue buildCue(android.text.Spanned r13, java.lang.String r14) {
        /*
            r12 = this;
            androidx.media3.common.text.Cue$Builder r12 = new androidx.media3.common.text.Cue$Builder
            r12.<init>()
            androidx.media3.common.text.Cue$Builder r12 = r12.setText(r13)
            if (r14 != 0) goto L_0x0010
            androidx.media3.common.text.Cue r12 = r12.build()
            return r12
        L_0x0010:
            int r13 = r14.hashCode()
            r0 = 2
            r1 = 1
            java.lang.String r2 = "{\\an1}"
            java.lang.String r3 = "{\\an2}"
            java.lang.String r4 = "{\\an3}"
            java.lang.String r5 = "{\\an4}"
            java.lang.String r6 = "{\\an5}"
            java.lang.String r7 = "{\\an6}"
            java.lang.String r8 = "{\\an7}"
            java.lang.String r9 = "{\\an8}"
            java.lang.String r10 = "{\\an9}"
            r11 = 0
            switch(r13) {
                case -685620710: goto L_0x006b;
                case -685620679: goto L_0x0066;
                case -685620648: goto L_0x005c;
                case -685620617: goto L_0x0055;
                case -685620586: goto L_0x0050;
                case -685620555: goto L_0x0049;
                case -685620524: goto L_0x0042;
                case -685620493: goto L_0x003d;
                case -685620462: goto L_0x0036;
                default: goto L_0x0035;
            }
        L_0x0035:
            goto L_0x0075
        L_0x0036:
            boolean r13 = r14.equals(r10)
            if (r13 == 0) goto L_0x0075
            goto L_0x0062
        L_0x003d:
            boolean r13 = r14.equals(r9)
            goto L_0x0075
        L_0x0042:
            boolean r13 = r14.equals(r8)
            if (r13 == 0) goto L_0x0075
            goto L_0x0071
        L_0x0049:
            boolean r13 = r14.equals(r7)
            if (r13 == 0) goto L_0x0075
            goto L_0x0062
        L_0x0050:
            boolean r13 = r14.equals(r6)
            goto L_0x0075
        L_0x0055:
            boolean r13 = r14.equals(r5)
            if (r13 == 0) goto L_0x0075
            goto L_0x0071
        L_0x005c:
            boolean r13 = r14.equals(r4)
            if (r13 == 0) goto L_0x0075
        L_0x0062:
            r12.setPositionAnchor(r0)
            goto L_0x0078
        L_0x0066:
            boolean r13 = r14.equals(r3)
            goto L_0x0075
        L_0x006b:
            boolean r13 = r14.equals(r2)
            if (r13 == 0) goto L_0x0075
        L_0x0071:
            r12.setPositionAnchor(r11)
            goto L_0x0078
        L_0x0075:
            r12.setPositionAnchor(r1)
        L_0x0078:
            int r13 = r14.hashCode()
            switch(r13) {
                case -685620710: goto L_0x00b5;
                case -685620679: goto L_0x00ae;
                case -685620648: goto L_0x00a7;
                case -685620617: goto L_0x00a2;
                case -685620586: goto L_0x009d;
                case -685620555: goto L_0x0098;
                case -685620524: goto L_0x008e;
                case -685620493: goto L_0x0087;
                case -685620462: goto L_0x0080;
                default: goto L_0x007f;
            }
        L_0x007f:
            goto L_0x00bf
        L_0x0080:
            boolean r13 = r14.equals(r10)
            if (r13 == 0) goto L_0x00bf
            goto L_0x0094
        L_0x0087:
            boolean r13 = r14.equals(r9)
            if (r13 == 0) goto L_0x00bf
            goto L_0x0094
        L_0x008e:
            boolean r13 = r14.equals(r8)
            if (r13 == 0) goto L_0x00bf
        L_0x0094:
            r12.setLineAnchor(r11)
            goto L_0x00c2
        L_0x0098:
            boolean r13 = r14.equals(r7)
            goto L_0x00bf
        L_0x009d:
            boolean r13 = r14.equals(r6)
            goto L_0x00bf
        L_0x00a2:
            boolean r13 = r14.equals(r5)
            goto L_0x00bf
        L_0x00a7:
            boolean r13 = r14.equals(r4)
            if (r13 == 0) goto L_0x00bf
            goto L_0x00bb
        L_0x00ae:
            boolean r13 = r14.equals(r3)
            if (r13 == 0) goto L_0x00bf
            goto L_0x00bb
        L_0x00b5:
            boolean r13 = r14.equals(r2)
            if (r13 == 0) goto L_0x00bf
        L_0x00bb:
            r12.setLineAnchor(r0)
            goto L_0x00c2
        L_0x00bf:
            r12.setLineAnchor(r1)
        L_0x00c2:
            int r13 = r12.getPositionAnchor()
            float r13 = getFractionalPositionForAnchorType(r13)
            androidx.media3.common.text.Cue$Builder r13 = r12.setPosition(r13)
            int r12 = r12.getLineAnchor()
            float r12 = getFractionalPositionForAnchorType(r12)
            androidx.media3.common.text.Cue$Builder r12 = r13.setLine(r12, r11)
            androidx.media3.common.text.Cue r12 = r12.build()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.subrip.SubripParser.buildCue(android.text.Spanned, java.lang.String):androidx.media3.common.text.Cue");
    }

    private Charset detectUtfCharset(ParsableByteArray parsableByteArray2) {
        Charset readUtfCharsetFromBom = parsableByteArray2.readUtfCharsetFromBom();
        if (readUtfCharsetFromBom != null) {
            return readUtfCharsetFromBom;
        }
        return StandardCharsets.UTF_8;
    }

    public static float getFractionalPositionForAnchorType(int i2) {
        if (i2 == 0) {
            return 0.08f;
        }
        if (i2 == 1) {
            return 0.5f;
        }
        if (i2 == 2) {
            return 0.92f;
        }
        throw new IllegalArgumentException();
    }

    private static long parseTimecode(Matcher matcher, int i2) {
        long j2;
        String group = matcher.group(i2 + 1);
        if (group != null) {
            j2 = Long.parseLong(group) * 3600000;
        } else {
            j2 = 0;
        }
        long parseLong = (Long.parseLong((String) Assertions.checkNotNull(matcher.group(i2 + 3))) * 1000) + (Long.parseLong((String) Assertions.checkNotNull(matcher.group(i2 + 2))) * 60000) + j2;
        String group2 = matcher.group(i2 + 4);
        if (group2 != null) {
            parseLong += Long.parseLong(group2);
        }
        return parseLong * 1000;
    }

    private String processLine(String str, ArrayList<String> arrayList) {
        String trim = str.trim();
        StringBuilder sb2 = new StringBuilder(trim);
        Matcher matcher = SUBRIP_TAG_PATTERN.matcher(trim);
        int i2 = 0;
        while (matcher.find()) {
            String group = matcher.group();
            arrayList.add(group);
            int start = matcher.start() - i2;
            int length = group.length();
            sb2.replace(start, start + length, "");
            i2 += length;
        }
        return sb2.toString();
    }

    public int getCueReplacementBehavior() {
        return 1;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        ArrayList<CuesWithTiming> arrayList;
        String str;
        int i8 = i2;
        SubtitleParser.OutputOptions outputOptions2 = outputOptions;
        Consumer<CuesWithTiming> consumer2 = consumer;
        this.parsableByteArray.reset(bArr, i8 + i7);
        this.parsableByteArray.setPosition(i8);
        Charset detectUtfCharset = detectUtfCharset(this.parsableByteArray);
        long j2 = -9223372036854775807L;
        if (outputOptions2.startTimeUs == -9223372036854775807L || !outputOptions2.outputAllCues) {
            arrayList = null;
        } else {
            arrayList = new ArrayList<>();
        }
        while (true) {
            String readLine = this.parsableByteArray.readLine(detectUtfCharset);
            if (readLine == null) {
                break;
            } else if (!readLine.isEmpty()) {
                try {
                    Integer.parseInt(readLine);
                    String readLine2 = this.parsableByteArray.readLine(detectUtfCharset);
                    if (readLine2 == null) {
                        Log.w("SubripParser", "Unexpected end");
                        break;
                    }
                    Matcher matcher = SUBRIP_TIMING_LINE.matcher(readLine2);
                    if (matcher.matches()) {
                        long parseTimecode = parseTimecode(matcher, 1);
                        long parseTimecode2 = parseTimecode(matcher, 6);
                        int i10 = 0;
                        this.textBuilder.setLength(0);
                        this.tags.clear();
                        for (String readLine3 = this.parsableByteArray.readLine(detectUtfCharset); !TextUtils.isEmpty(readLine3); readLine3 = this.parsableByteArray.readLine(detectUtfCharset)) {
                            if (this.textBuilder.length() > 0) {
                                this.textBuilder.append("<br>");
                            }
                            this.textBuilder.append(processLine(readLine3, this.tags));
                        }
                        Spanned fromHtml = Html.fromHtml(this.textBuilder.toString());
                        while (true) {
                            if (i10 >= this.tags.size()) {
                                str = null;
                                break;
                            }
                            str = this.tags.get(i10);
                            if (str.matches("\\{\\\\an[1-9]\\}")) {
                                break;
                            }
                            i10++;
                        }
                        long j3 = j2;
                        long j8 = outputOptions2.startTimeUs;
                        if (j8 == j3 || parseTimecode2 >= j8) {
                            consumer2.accept(new CuesWithTiming(U.B(buildCue(fromHtml, str)), parseTimecode, parseTimecode2 - parseTimecode));
                        } else if (arrayList != null) {
                            arrayList.add(new CuesWithTiming(U.B(buildCue(fromHtml, str)), parseTimecode, parseTimecode2 - parseTimecode));
                        }
                        j2 = j3;
                    } else {
                        long j10 = j2;
                        Log.w("SubripParser", "Skipping invalid timing: ".concat(readLine2));
                    }
                } catch (NumberFormatException unused) {
                    long j11 = j2;
                    Log.w("SubripParser", "Skipping invalid index: ".concat(readLine));
                }
            }
        }
        if (arrayList != null) {
            for (CuesWithTiming accept : arrayList) {
                consumer2.accept(accept);
            }
        }
    }
}
