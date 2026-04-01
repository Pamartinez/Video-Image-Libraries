package androidx.media3.extractor.text.ssa;

import A.a;
import android.graphics.PointF;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.ssa.SsaStyle;
import com.samsung.android.sum.core.types.NumericEnum;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SsaParser implements SubtitleParser {
    private static final Pattern SSA_TIMECODE_PATTERN = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)[:.](\\d+)");
    private final SsaDialogueFormat dialogueFormatFromInitializationData;
    private final boolean haveInitializationData;
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();
    private float screenHeight = -3.4028235E38f;
    private float screenWidth = -3.4028235E38f;
    private Map<String, SsaStyle> styles;

    public SsaParser(List<byte[]> list) {
        if (list == null || list.isEmpty()) {
            this.haveInitializationData = false;
            this.dialogueFormatFromInitializationData = null;
            return;
        }
        this.haveInitializationData = true;
        String fromUtf8Bytes = Util.fromUtf8Bytes(list.get(0));
        Assertions.checkArgument(fromUtf8Bytes.startsWith("Format:"));
        this.dialogueFormatFromInitializationData = (SsaDialogueFormat) Assertions.checkNotNull(SsaDialogueFormat.fromFormatLine(fromUtf8Bytes));
        parseHeader(new ParsableByteArray(list.get(1)), StandardCharsets.UTF_8);
    }

    private static int addCuePlacerholderByTime(long j2, List<Long> list, List<List<Cue>> list2) {
        int i2;
        ArrayList arrayList;
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                i2 = 0;
                break;
            } else if (list.get(size).longValue() == j2) {
                return size;
            } else {
                if (list.get(size).longValue() < j2) {
                    i2 = size + 1;
                    break;
                }
                size--;
            }
        }
        list.add(i2, Long.valueOf(j2));
        if (i2 != 0) {
            arrayList = new ArrayList(list2.get(i2 - 1));
        }
        list2.add(i2, arrayList);
        return i2;
    }

    private static float computeDefaultLineOrPosition(int i2) {
        if (i2 == 0) {
            return 0.05f;
        }
        if (i2 == 1) {
            return 0.5f;
        }
        if (i2 != 2) {
            return -3.4028235E38f;
        }
        return 0.95f;
    }

    private static Cue createCue(String str, int i2, SsaStyle ssaStyle, SsaStyle.Overrides overrides, float f, float f5) {
        SpannableString spannableString = new SpannableString(str);
        Cue.Builder zIndex = new Cue.Builder().setText(spannableString).setZIndex(i2);
        if (ssaStyle != null) {
            if (ssaStyle.primaryColor != null) {
                spannableString.setSpan(new ForegroundColorSpan(ssaStyle.primaryColor.intValue()), 0, spannableString.length(), 33);
            }
            if (ssaStyle.borderStyle == 3 && ssaStyle.outlineColor != null) {
                spannableString.setSpan(new BackgroundColorSpan(ssaStyle.outlineColor.intValue()), 0, spannableString.length(), 33);
            }
            float f8 = ssaStyle.fontSize;
            if (!(f8 == -3.4028235E38f || f5 == -3.4028235E38f)) {
                zIndex.setTextSize(f8 / f5, 1);
            }
            boolean z = ssaStyle.bold;
            if (z && ssaStyle.italic) {
                spannableString.setSpan(new StyleSpan(3), 0, spannableString.length(), 33);
            } else if (z) {
                spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 33);
            } else if (ssaStyle.italic) {
                spannableString.setSpan(new StyleSpan(2), 0, spannableString.length(), 33);
            }
            if (ssaStyle.underline) {
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 33);
            }
            if (ssaStyle.strikeout) {
                spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), 33);
            }
        }
        int i7 = overrides.alignment;
        if (i7 == -1) {
            if (ssaStyle != null) {
                i7 = ssaStyle.alignment;
            } else {
                i7 = -1;
            }
        }
        zIndex.setTextAlignment(toTextAlignment(i7)).setPositionAnchor(toPositionAnchor(i7)).setLineAnchor(toLineAnchor(i7));
        PointF pointF = overrides.position;
        if (pointF == null || f5 == -3.4028235E38f || f == -3.4028235E38f) {
            zIndex.setPosition(computeDefaultLineOrPosition(zIndex.getPositionAnchor()));
            zIndex.setLine(computeDefaultLineOrPosition(zIndex.getLineAnchor()), 0);
        } else {
            zIndex.setPosition(pointF.x / f);
            zIndex.setLine(overrides.position.y / f5, 0);
        }
        return zIndex.build();
    }

    private Charset detectUtfCharset(ParsableByteArray parsableByteArray2) {
        Charset readUtfCharsetFromBom = parsableByteArray2.readUtfCharsetFromBom();
        if (readUtfCharsetFromBom != null) {
            return readUtfCharsetFromBom;
        }
        return StandardCharsets.UTF_8;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void parseDialogueLine(java.lang.String r17, androidx.media3.extractor.text.ssa.SsaDialogueFormat r18, java.util.List<java.util.List<androidx.media3.common.text.Cue>> r19, java.util.List<java.lang.Long> r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            java.lang.String r5 = "Dialogue:"
            boolean r5 = r1.startsWith(r5)
            androidx.media3.common.util.Assertions.checkArgument(r5)
            r5 = 9
            java.lang.String r5 = r1.substring(r5)
            java.lang.String r6 = ","
            int r7 = r2.length
            java.lang.String[] r5 = r5.split(r6, r7)
            int r6 = r5.length
            int r7 = r2.length
            java.lang.String r8 = "SsaParser"
            if (r6 == r7) goto L_0x0032
            java.lang.String r0 = "Skipping dialogue line with fewer columns than format: "
            java.lang.String r0 = r0.concat(r1)
            androidx.media3.common.util.Log.w(r8, r0)
            return
        L_0x0032:
            int r6 = r2.layerIndex
            r7 = -1
            if (r6 == r7) goto L_0x0058
            r6 = r5[r6]     // Catch:{ RuntimeException -> 0x0043 }
            java.lang.String r6 = r6.trim()     // Catch:{ RuntimeException -> 0x0043 }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ RuntimeException -> 0x0043 }
        L_0x0041:
            r10 = r6
            goto L_0x005a
        L_0x0043:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r9 = "Fail to parse layer: "
            r6.<init>(r9)
            int r9 = r2.layerIndex
            r9 = r5[r9]
            r6.append(r9)
            java.lang.String r6 = r6.toString()
            androidx.media3.common.util.Log.w(r8, r6)
        L_0x0058:
            r6 = 0
            goto L_0x0041
        L_0x005a:
            int r6 = r2.startTimeIndex
            r6 = r5[r6]
            long r11 = parseTimecodeUs(r6)
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            java.lang.String r9 = "Skipping invalid timing: "
            if (r6 != 0) goto L_0x0075
            java.lang.String r0 = r9.concat(r1)
            androidx.media3.common.util.Log.w(r8, r0)
            return
        L_0x0075:
            int r6 = r2.endTimeIndex
            r6 = r5[r6]
            r15 = r8
            long r7 = parseTimecodeUs(r6)
            int r6 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r6 == 0) goto L_0x00e2
            int r6 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r6 > 0) goto L_0x0087
            goto L_0x00e2
        L_0x0087:
            java.util.Map<java.lang.String, androidx.media3.extractor.text.ssa.SsaStyle> r1 = r0.styles
            if (r1 == 0) goto L_0x009d
            int r6 = r2.styleIndex
            r9 = -1
            if (r6 == r9) goto L_0x009d
            r6 = r5[r6]
            java.lang.String r6 = r6.trim()
            java.lang.Object r1 = r1.get(r6)
            androidx.media3.extractor.text.ssa.SsaStyle r1 = (androidx.media3.extractor.text.ssa.SsaStyle) r1
            goto L_0x009e
        L_0x009d:
            r1 = 0
        L_0x009e:
            int r2 = r2.textIndex
            r2 = r5[r2]
            r5 = r11
            androidx.media3.extractor.text.ssa.SsaStyle$Overrides r12 = androidx.media3.extractor.text.ssa.SsaStyle.Overrides.parseFromDialogue(r2)
            java.lang.String r2 = androidx.media3.extractor.text.ssa.SsaStyle.Overrides.stripStyleOverrides(r2)
            java.lang.String r9 = "\\N"
            java.lang.String r11 = "\n"
            java.lang.String r2 = r2.replace(r9, r11)
            java.lang.String r9 = "\\n"
            java.lang.String r2 = r2.replace(r9, r11)
            java.lang.String r9 = "\\h"
            java.lang.String r11 = " "
            java.lang.String r9 = r2.replace(r9, r11)
            float r13 = r0.screenWidth
            float r14 = r0.screenHeight
            r11 = r1
            androidx.media3.common.text.Cue r0 = createCue(r9, r10, r11, r12, r13, r14)
            int r1 = addCuePlacerholderByTime(r5, r4, r3)
            int r2 = addCuePlacerholderByTime(r7, r4, r3)
        L_0x00d3:
            if (r1 >= r2) goto L_0x00e1
            java.lang.Object r4 = r3.get(r1)
            java.util.List r4 = (java.util.List) r4
            r4.add(r0)
            int r1 = r1 + 1
            goto L_0x00d3
        L_0x00e1:
            return
        L_0x00e2:
            java.lang.String r0 = r9.concat(r1)
            androidx.media3.common.util.Log.w(r15, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ssa.SsaParser.parseDialogueLine(java.lang.String, androidx.media3.extractor.text.ssa.SsaDialogueFormat, java.util.List, java.util.List):void");
    }

    private void parseEventBody(ParsableByteArray parsableByteArray2, List<List<Cue>> list, List<Long> list2, Charset charset) {
        SsaDialogueFormat ssaDialogueFormat;
        if (this.haveInitializationData) {
            ssaDialogueFormat = this.dialogueFormatFromInitializationData;
        } else {
            ssaDialogueFormat = null;
        }
        while (true) {
            String readLine = parsableByteArray2.readLine(charset);
            if (readLine == null) {
                return;
            }
            if (readLine.startsWith("Format:")) {
                ssaDialogueFormat = SsaDialogueFormat.fromFormatLine(readLine);
            } else if (readLine.startsWith("Dialogue:")) {
                if (ssaDialogueFormat == null) {
                    Log.w("SsaParser", "Skipping dialogue line before complete format: ".concat(readLine));
                } else {
                    parseDialogueLine(readLine, ssaDialogueFormat, list, list2);
                }
            }
        }
    }

    private void parseHeader(ParsableByteArray parsableByteArray2, Charset charset) {
        while (true) {
            String readLine = parsableByteArray2.readLine(charset);
            if (readLine == null) {
                return;
            }
            if ("[Script Info]".equalsIgnoreCase(readLine)) {
                parseScriptInfo(parsableByteArray2, charset);
            } else if ("[V4+ Styles]".equalsIgnoreCase(readLine)) {
                this.styles = parseStyles(parsableByteArray2, charset);
            } else if ("[V4 Styles]".equalsIgnoreCase(readLine)) {
                Log.i("SsaParser", "[V4 Styles] are not supported");
            } else if ("[Events]".equalsIgnoreCase(readLine)) {
                return;
            }
        }
    }

    private void parseScriptInfo(ParsableByteArray parsableByteArray2, Charset charset) {
        while (true) {
            String readLine = parsableByteArray2.readLine(charset);
            if (readLine == null) {
                return;
            }
            if (parsableByteArray2.bytesLeft() == 0 || parsableByteArray2.peekCodePoint(charset) != 91) {
                String[] split = readLine.split(NumericEnum.SEP);
                if (split.length == 2) {
                    String S = k.S(split[0].trim());
                    S.getClass();
                    if (S.equals("playresx")) {
                        this.screenWidth = Float.parseFloat(split[1].trim());
                    } else if (S.equals("playresy")) {
                        try {
                            this.screenHeight = Float.parseFloat(split[1].trim());
                        } catch (NumberFormatException unused) {
                        }
                    }
                }
            } else {
                return;
            }
        }
    }

    private static Map<String, SsaStyle> parseStyles(ParsableByteArray parsableByteArray2, Charset charset) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        SsaStyle.Format format = null;
        while (true) {
            String readLine = parsableByteArray2.readLine(charset);
            if (readLine == null || (parsableByteArray2.bytesLeft() != 0 && parsableByteArray2.peekCodePoint(charset) == 91)) {
                return linkedHashMap;
            }
            if (readLine.startsWith("Format:")) {
                format = SsaStyle.Format.fromFormatLine(readLine);
            } else if (readLine.startsWith("Style:")) {
                if (format == null) {
                    Log.w("SsaParser", "Skipping 'Style:' line before 'Format:' line: ".concat(readLine));
                } else {
                    SsaStyle fromStyleLine = SsaStyle.fromStyleLine(readLine, format);
                    if (fromStyleLine != null) {
                        linkedHashMap.put(fromStyleLine.name, fromStyleLine);
                    }
                }
            }
        }
        return linkedHashMap;
    }

    private static long parseTimecodeUs(String str) {
        Matcher matcher = SSA_TIMECODE_PATTERN.matcher(str.trim());
        if (!matcher.matches()) {
            return -9223372036854775807L;
        }
        return (Long.parseLong((String) Util.castNonNull(matcher.group(4))) * 10000) + (Long.parseLong((String) Util.castNonNull(matcher.group(3))) * 1000000) + (Long.parseLong((String) Util.castNonNull(matcher.group(2))) * 60000000) + (Long.parseLong((String) Util.castNonNull(matcher.group(1))) * 3600000000L);
    }

    private static int toLineAnchor(int i2) {
        switch (i2) {
            case -1:
                return Integer.MIN_VALUE;
            case 1:
            case 2:
            case 3:
                return 2;
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
            case 9:
                return 0;
            default:
                a.D(i2, "Unknown alignment: ", "SsaParser");
                return Integer.MIN_VALUE;
        }
    }

    private static int toPositionAnchor(int i2) {
        switch (i2) {
            case -1:
                return Integer.MIN_VALUE;
            case 1:
            case 4:
            case 7:
                return 0;
            case 2:
            case 5:
            case 8:
                return 1;
            case 3:
            case 6:
            case 9:
                return 2;
            default:
                a.D(i2, "Unknown alignment: ", "SsaParser");
                return Integer.MIN_VALUE;
        }
    }

    private static Layout.Alignment toTextAlignment(int i2) {
        switch (i2) {
            case -1:
                return null;
            case 1:
            case 4:
            case 7:
                return Layout.Alignment.ALIGN_NORMAL;
            case 2:
            case 5:
            case 8:
                return Layout.Alignment.ALIGN_CENTER;
            case 3:
            case 6:
            case 9:
                return Layout.Alignment.ALIGN_OPPOSITE;
            default:
                a.D(i2, "Unknown alignment: ", "SsaParser");
                return null;
        }
    }

    public int getCueReplacementBehavior() {
        return 1;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        ArrayList<CuesWithTiming> arrayList;
        int i8 = i2;
        SubtitleParser.OutputOptions outputOptions2 = outputOptions;
        Consumer<CuesWithTiming> consumer2 = consumer;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        this.parsableByteArray.reset(bArr, i8 + i7);
        this.parsableByteArray.setPosition(i8);
        Charset detectUtfCharset = detectUtfCharset(this.parsableByteArray);
        if (!this.haveInitializationData) {
            parseHeader(this.parsableByteArray, detectUtfCharset);
        }
        parseEventBody(this.parsableByteArray, arrayList2, arrayList3, detectUtfCharset);
        if (outputOptions2.startTimeUs == -9223372036854775807L || !outputOptions2.outputAllCues) {
            arrayList = null;
        } else {
            arrayList = new ArrayList<>();
        }
        for (int i10 = 0; i10 < arrayList2.size(); i10++) {
            List list = (List) arrayList2.get(i10);
            if (!list.isEmpty() || i10 == 0) {
                if (i10 != arrayList2.size() - 1) {
                    long longValue = ((Long) arrayList3.get(i10)).longValue();
                    long longValue2 = ((Long) arrayList3.get(i10 + 1)).longValue();
                    CuesWithTiming cuesWithTiming = new CuesWithTiming(list, longValue, longValue2 - longValue);
                    long j2 = outputOptions2.startTimeUs;
                    if (j2 == -9223372036854775807L || longValue2 >= j2) {
                        consumer2.accept(cuesWithTiming);
                    } else if (arrayList != null) {
                        arrayList.add(cuesWithTiming);
                    }
                } else {
                    throw new IllegalStateException();
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
