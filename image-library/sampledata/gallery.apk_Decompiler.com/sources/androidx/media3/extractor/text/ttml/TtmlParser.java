package androidx.media3.extractor.text.ttml;

import A.a;
import android.text.Layout;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.common.util.XmlPullParserUtil;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.LegacySubtitleUtil;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleParser;
import c0.C0086a;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import og.k;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TtmlParser implements SubtitleParser {
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^([-+]?\\d+\\.?\\d*?)% ([-+]?\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^([-+]?\\d+\\.?\\d*?)px ([-+]?\\d+\\.?\\d*?)px$");
    static final Pattern SIGNED_PERCENTAGE = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
    private final XmlPullParserFactory xmlParserFactory;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        public FrameAndTickRate(float f, int i2, int i7) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i2;
            this.tickRate = i7;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class TtsExtent {
        final int height;
        final int width;

        public TtsExtent(int i2, int i7) {
            this.width = i2;
            this.height = i7;
        }
    }

    public TtmlParser() {
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = newInstance;
            newInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    private static TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        if (ttmlStyle == null) {
            return new TtmlStyle();
        }
        return ttmlStyle;
    }

    private static boolean isSupportedTag(String str) {
        if (str.equals("tt") || str.equals("head") || str.equals("body") || str.equals("div") || str.equals("p") || str.equals("span") || str.equals("br") || str.equals("style") || str.equals("styling") || str.equals("layout") || str.equals(BuddyContract.Address.REGION) || str.equals(GroupContract.Group.META_DATA) || str.equals("image") || str.equals("data") || str.equals("information")) {
            return true;
        }
        return false;
    }

    private static Layout.Alignment parseAlignment(String str) {
        String S = k.S(str);
        S.getClass();
        char c5 = 65535;
        switch (S.hashCode()) {
            case -1364013995:
                if (S.equals("center")) {
                    c5 = 0;
                    break;
                }
                break;
            case 100571:
                if (S.equals("end")) {
                    c5 = 1;
                    break;
                }
                break;
            case 3317767:
                if (S.equals("left")) {
                    c5 = 2;
                    break;
                }
                break;
            case 108511772:
                if (S.equals("right")) {
                    c5 = 3;
                    break;
                }
                break;
            case 109757538:
                if (S.equals("start")) {
                    c5 = 4;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return Layout.Alignment.ALIGN_CENTER;
            case 1:
            case 3:
                return Layout.Alignment.ALIGN_OPPOSITE;
            case 2:
            case 4:
                return Layout.Alignment.ALIGN_NORMAL;
            default:
                return null;
        }
    }

    private static int parseCellRows(XmlPullParser xmlPullParser, int i2) {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "cellResolution");
        if (attributeValue == null) {
            return i2;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w("TtmlParser", "Ignoring malformed cell resolution: ".concat(attributeValue));
            return i2;
        }
        boolean z = true;
        try {
            int parseInt = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)));
            int parseInt2 = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2)));
            if (parseInt == 0 || parseInt2 == 0) {
                z = false;
            }
            Assertions.checkArgument(z, "Invalid cell resolution " + parseInt + " " + parseInt2);
            return parseInt2;
        } catch (NumberFormatException unused) {
            Log.w("TtmlParser", "Ignoring malformed cell resolution: ".concat(attributeValue));
            return i2;
        }
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) {
        Matcher matcher;
        String[] split = Util.split(str, "\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.w("TtmlParser", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException(C0086a.l(new StringBuilder("Invalid number of entries for fontSize: "), split.length, "."));
        }
        if (matcher.matches()) {
            String str2 = (String) Assertions.checkNotNull(matcher.group(3));
            str2.getClass();
            char c5 = 65535;
            switch (str2.hashCode()) {
                case 37:
                    if (str2.equals("%")) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 3240:
                    if (str2.equals("em")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 3592:
                    if (str2.equals("px")) {
                        c5 = 2;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    ttmlStyle.setFontSizeUnit(3);
                    break;
                case 1:
                    ttmlStyle.setFontSizeUnit(2);
                    break;
                case 2:
                    ttmlStyle.setFontSizeUnit(1);
                    break;
                default:
                    throw new SubtitleDecoderException(C0212a.m("Invalid unit for fontSize: '", str2, "'."));
            }
            ttmlStyle.setFontSize(Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1))));
            return;
        }
        throw new SubtitleDecoderException(C0212a.m("Invalid expression for fontSize: '", str, "'."));
    }

    private static FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) {
        int i2;
        float f;
        boolean z;
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        if (attributeValue != null) {
            i2 = Integer.parseInt(attributeValue);
        } else {
            i2 = 30;
        }
        String attributeValue2 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = Util.split(attributeValue2, " ");
            if (split.length == 2) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkArgument(z, "frameRateMultiplier doesn't have 2 parts");
            f = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
        } else {
            f = 1.0f;
        }
        FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
        int i7 = frameAndTickRate.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if (attributeValue3 != null) {
            i7 = Integer.parseInt(attributeValue3);
        }
        int i8 = frameAndTickRate.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        if (attributeValue4 != null) {
            i8 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(((float) i2) * f, i7, i8);
    }

    private static Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, int i2, TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain(map.get(str));
                    }
                }
                String id = parseStyleAttributes.getId();
                if (id != null) {
                    map.put(id, parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, BuddyContract.Address.REGION)) {
                TtmlRegion parseRegionAttributes = parseRegionAttributes(xmlPullParser, i2, ttsExtent, map);
                if (parseRegionAttributes != null) {
                    map2.put(parseRegionAttributes.id, parseRegionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, GroupContract.Group.META_DATA)) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "head"));
        return map;
    }

    private static void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) {
        String attributeValue;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "image") && (attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id")) != null) {
                map.put(attributeValue, xmlPullParser.nextText());
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, GroupContract.Group.META_DATA));
    }

    private static TtmlNode parseNode(XmlPullParser xmlPullParser, TtmlNode ttmlNode, Map<String, TtmlRegion> map, FrameAndTickRate frameAndTickRate) {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        TtmlNode ttmlNode2 = ttmlNode;
        FrameAndTickRate frameAndTickRate2 = frameAndTickRate;
        int attributeCount = xmlPullParser2.getAttributeCount();
        String str = null;
        TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser2, (TtmlStyle) null);
        long j2 = -9223372036854775807L;
        long j3 = -9223372036854775807L;
        long j8 = -9223372036854775807L;
        String[] strArr = null;
        String str2 = "";
        int i2 = 0;
        while (i2 < attributeCount) {
            String attributeName = xmlPullParser2.getAttributeName(i2);
            int i7 = attributeCount;
            String attributeValue = xmlPullParser2.getAttributeValue(i2);
            attributeName.getClass();
            char c5 = 65535;
            switch (attributeName.hashCode()) {
                case -934795532:
                    if (attributeName.equals(BuddyContract.Address.REGION)) {
                        c5 = 0;
                        break;
                    }
                    break;
                case 99841:
                    if (attributeName.equals("dur")) {
                        c5 = 1;
                        break;
                    }
                    break;
                case 100571:
                    if (attributeName.equals("end")) {
                        c5 = 2;
                        break;
                    }
                    break;
                case 93616297:
                    if (attributeName.equals("begin")) {
                        c5 = 3;
                        break;
                    }
                    break;
                case 109780401:
                    if (attributeName.equals("style")) {
                        c5 = 4;
                        break;
                    }
                    break;
                case 1292595405:
                    if (attributeName.equals("backgroundImage")) {
                        c5 = 5;
                        break;
                    }
                    break;
            }
            switch (c5) {
                case 0:
                    if (!map.containsKey(attributeValue)) {
                        break;
                    } else {
                        str2 = attributeValue;
                        continue;
                    }
                case 1:
                    j8 = parseTimeExpression(attributeValue, frameAndTickRate2);
                    break;
                case 2:
                    j3 = parseTimeExpression(attributeValue, frameAndTickRate2);
                    break;
                case 3:
                    j2 = parseTimeExpression(attributeValue, frameAndTickRate2);
                    break;
                case 4:
                    String[] parseStyleIds = parseStyleIds(attributeValue);
                    if (parseStyleIds.length > 0) {
                        strArr = parseStyleIds;
                        break;
                    }
                    break;
                case 5:
                    if (attributeValue.startsWith("#")) {
                        str = attributeValue.substring(1);
                        break;
                    }
                    break;
            }
            Map<String, TtmlRegion> map2 = map;
            i2++;
            xmlPullParser2 = xmlPullParser;
            attributeCount = i7;
        }
        if (ttmlNode2 != null) {
            long j10 = ttmlNode2.startTimeUs;
            if (j10 != -9223372036854775807L) {
                if (j2 != -9223372036854775807L) {
                    j2 += j10;
                }
                if (j3 != -9223372036854775807L) {
                    j3 += j10;
                }
            }
        }
        long j11 = j2;
        if (j3 == -9223372036854775807L) {
            if (j8 != -9223372036854775807L) {
                j3 = j11 + j8;
            } else if (ttmlNode2 != null) {
                long j12 = ttmlNode2.endTimeUs;
                if (j12 != -9223372036854775807L) {
                    j3 = j12;
                }
            }
        }
        return TtmlNode.buildNode(xmlPullParser.getName(), j11, j3, parseStyleAttributes, strArr, str2, str, ttmlNode2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01ce, code lost:
        if (r0.equals("tb") == false) goto L_0x01ad;
     */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x019e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.text.ttml.TtmlRegion parseRegionAttributes(org.xmlpull.v1.XmlPullParser r17, int r18, androidx.media3.extractor.text.ttml.TtmlParser.TtsExtent r19, java.util.Map<java.lang.String, androidx.media3.extractor.text.ttml.TtmlStyle> r20) {
        /*
            r0 = r17
            r1 = r19
            r2 = r20
            java.lang.String r3 = "id"
            java.lang.String r5 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r3)
            r3 = 0
            if (r5 != 0) goto L_0x0010
            return r3
        L_0x0010:
            java.lang.String r4 = "origin"
            java.lang.String r4 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r4)
            java.lang.String r6 = "style"
            if (r4 != 0) goto L_0x002d
            java.lang.String r7 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r6)
            if (r7 == 0) goto L_0x002d
            java.lang.Object r7 = r2.get(r7)
            androidx.media3.extractor.text.ttml.TtmlStyle r7 = (androidx.media3.extractor.text.ttml.TtmlStyle) r7
            if (r7 == 0) goto L_0x002d
            java.lang.String r4 = r7.getOrigin()
        L_0x002d:
            java.lang.String r7 = "Ignoring region with missing tts:extent: "
            r8 = 1120403456(0x42c80000, float:100.0)
            r9 = 2
            r10 = 1
            java.lang.String r11 = "TtmlParser"
            if (r4 == 0) goto L_0x00c0
            java.util.regex.Pattern r12 = PERCENTAGE_COORDINATES
            java.util.regex.Matcher r12 = r12.matcher(r4)
            java.util.regex.Pattern r13 = PIXEL_COORDINATES
            java.util.regex.Matcher r13 = r13.matcher(r4)
            boolean r14 = r12.matches()
            java.lang.String r15 = "Ignoring region with malformed origin: "
            if (r14 == 0) goto L_0x0072
            java.lang.String r13 = r12.group(r10)     // Catch:{ NumberFormatException -> 0x006a }
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)     // Catch:{ NumberFormatException -> 0x006a }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ NumberFormatException -> 0x006a }
            float r13 = java.lang.Float.parseFloat(r13)     // Catch:{ NumberFormatException -> 0x006a }
            float r13 = r13 / r8
            java.lang.String r12 = r12.group(r9)     // Catch:{ NumberFormatException -> 0x006a }
            java.lang.Object r12 = androidx.media3.common.util.Assertions.checkNotNull(r12)     // Catch:{ NumberFormatException -> 0x006a }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ NumberFormatException -> 0x006a }
            float r12 = java.lang.Float.parseFloat(r12)     // Catch:{ NumberFormatException -> 0x006a }
            float r12 = r12 / r8
            goto L_0x00c2
        L_0x006a:
            java.lang.String r0 = r15.concat(r4)
            androidx.media3.common.util.Log.w(r11, r0)
            return r3
        L_0x0072:
            boolean r12 = r13.matches()
            if (r12 == 0) goto L_0x00b6
            if (r1 != 0) goto L_0x0082
            java.lang.String r0 = r7.concat(r4)
            androidx.media3.common.util.Log.w(r11, r0)
            return r3
        L_0x0082:
            java.lang.String r12 = r13.group(r10)     // Catch:{ NumberFormatException -> 0x00ae }
            java.lang.Object r12 = androidx.media3.common.util.Assertions.checkNotNull(r12)     // Catch:{ NumberFormatException -> 0x00ae }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ NumberFormatException -> 0x00ae }
            int r12 = java.lang.Integer.parseInt(r12)     // Catch:{ NumberFormatException -> 0x00ae }
            java.lang.String r13 = r13.group(r9)     // Catch:{ NumberFormatException -> 0x00ae }
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)     // Catch:{ NumberFormatException -> 0x00ae }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ NumberFormatException -> 0x00ae }
            int r13 = java.lang.Integer.parseInt(r13)     // Catch:{ NumberFormatException -> 0x00ae }
            float r12 = (float) r12     // Catch:{ NumberFormatException -> 0x00ae }
            int r14 = r1.width     // Catch:{ NumberFormatException -> 0x00ae }
            float r14 = (float) r14     // Catch:{ NumberFormatException -> 0x00ae }
            float r12 = r12 / r14
            float r13 = (float) r13     // Catch:{ NumberFormatException -> 0x00ae }
            int r14 = r1.height     // Catch:{ NumberFormatException -> 0x00ae }
            float r14 = (float) r14
            float r13 = r13 / r14
            r16 = r13
            r13 = r12
            r12 = r16
            goto L_0x00c2
        L_0x00ae:
            java.lang.String r0 = r15.concat(r4)
            androidx.media3.common.util.Log.w(r11, r0)
            return r3
        L_0x00b6:
            java.lang.String r0 = "Ignoring region with unsupported origin: "
            java.lang.String r0 = r0.concat(r4)
            androidx.media3.common.util.Log.w(r11, r0)
            return r3
        L_0x00c0:
            r12 = 0
            r13 = r12
        L_0x00c2:
            java.lang.String r14 = "extent"
            java.lang.String r14 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r14)
            if (r14 != 0) goto L_0x00dc
            java.lang.String r6 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r6)
            if (r6 == 0) goto L_0x00dc
            java.lang.Object r2 = r2.get(r6)
            androidx.media3.extractor.text.ttml.TtmlStyle r2 = (androidx.media3.extractor.text.ttml.TtmlStyle) r2
            if (r2 == 0) goto L_0x00dc
            java.lang.String r14 = r2.getExtent()
        L_0x00dc:
            if (r14 == 0) goto L_0x0157
            java.util.regex.Pattern r6 = PERCENTAGE_COORDINATES
            java.util.regex.Matcher r6 = r6.matcher(r14)
            java.util.regex.Pattern r15 = PIXEL_COORDINATES
            java.util.regex.Matcher r14 = r15.matcher(r14)
            boolean r15 = r6.matches()
            r20 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r2 = "Ignoring region with malformed extent: "
            if (r15 == 0) goto L_0x0117
            java.lang.String r1 = r6.group(r10)     // Catch:{ NumberFormatException -> 0x0113 }
            java.lang.Object r1 = androidx.media3.common.util.Assertions.checkNotNull(r1)     // Catch:{ NumberFormatException -> 0x0113 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ NumberFormatException -> 0x0113 }
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x0113 }
            float r1 = r1 / r8
            java.lang.String r6 = r6.group(r9)     // Catch:{ NumberFormatException -> 0x0113 }
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)     // Catch:{ NumberFormatException -> 0x0113 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x0113 }
            float r2 = java.lang.Float.parseFloat(r6)     // Catch:{ NumberFormatException -> 0x0113 }
            float r2 = r2 / r8
            goto L_0x014b
        L_0x0113:
            A.a.C(r2, r4, r11)
            return r3
        L_0x0117:
            boolean r6 = r14.matches()
            if (r6 == 0) goto L_0x0151
            if (r1 != 0) goto L_0x0123
            A.a.C(r7, r4, r11)
            return r3
        L_0x0123:
            java.lang.String r6 = r14.group(r10)     // Catch:{ NumberFormatException -> 0x014d }
            java.lang.Object r6 = androidx.media3.common.util.Assertions.checkNotNull(r6)     // Catch:{ NumberFormatException -> 0x014d }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ NumberFormatException -> 0x014d }
            int r6 = java.lang.Integer.parseInt(r6)     // Catch:{ NumberFormatException -> 0x014d }
            java.lang.String r7 = r14.group(r9)     // Catch:{ NumberFormatException -> 0x014d }
            java.lang.Object r7 = androidx.media3.common.util.Assertions.checkNotNull(r7)     // Catch:{ NumberFormatException -> 0x014d }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x014d }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x014d }
            float r6 = (float) r6     // Catch:{ NumberFormatException -> 0x014d }
            int r8 = r1.width     // Catch:{ NumberFormatException -> 0x014d }
            float r8 = (float) r8     // Catch:{ NumberFormatException -> 0x014d }
            float r6 = r6 / r8
            float r7 = (float) r7     // Catch:{ NumberFormatException -> 0x014d }
            int r1 = r1.height     // Catch:{ NumberFormatException -> 0x014d }
            float r1 = (float) r1
            float r2 = r7 / r1
            r1 = r6
        L_0x014b:
            r11 = r2
            goto L_0x015c
        L_0x014d:
            A.a.C(r2, r4, r11)
            return r3
        L_0x0151:
            java.lang.String r0 = "Ignoring region with unsupported extent: "
            A.a.C(r0, r4, r11)
            return r3
        L_0x0157:
            r20 = 1065353216(0x3f800000, float:1.0)
            r1 = r20
            r11 = r1
        L_0x015c:
            java.lang.String r2 = "displayAlign"
            java.lang.String r2 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r2)
            r3 = 0
            if (r2 == 0) goto L_0x018d
            java.lang.String r2 = og.k.S(r2)
            r2.getClass()
            java.lang.String r4 = "center"
            boolean r4 = r2.equals(r4)
            if (r4 != 0) goto L_0x0183
            java.lang.String r4 = "after"
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x017d
            goto L_0x018d
        L_0x017d:
            float r12 = r12 + r11
            r2 = r18
            r4 = r9
        L_0x0181:
            r7 = r12
            goto L_0x0192
        L_0x0183:
            r2 = 1073741824(0x40000000, float:2.0)
            float r2 = r11 / r2
            float r12 = r12 + r2
            r2 = r18
            r4 = r9
            r9 = r10
            goto L_0x0181
        L_0x018d:
            r2 = r18
            r4 = r9
            r7 = r12
            r9 = r3
        L_0x0192:
            float r2 = (float) r2
            float r2 = r20 / r2
            java.lang.String r6 = "writingMode"
            java.lang.String r0 = androidx.media3.common.util.XmlPullParserUtil.getAttributeValue(r0, r6)
            if (r0 == 0) goto L_0x01d9
            java.lang.String r0 = og.k.S(r0)
            r0.getClass()
            int r6 = r0.hashCode()
            r8 = -1
            switch(r6) {
                case 3694: goto L_0x01c7;
                case 3553396: goto L_0x01bb;
                case 3553576: goto L_0x01af;
                default: goto L_0x01ad;
            }
        L_0x01ad:
            r3 = r8
            goto L_0x01d1
        L_0x01af:
            java.lang.String r3 = "tbrl"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x01b9
            goto L_0x01ad
        L_0x01b9:
            r3 = r4
            goto L_0x01d1
        L_0x01bb:
            java.lang.String r3 = "tblr"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x01c5
            goto L_0x01ad
        L_0x01c5:
            r3 = r10
            goto L_0x01d1
        L_0x01c7:
            java.lang.String r6 = "tb"
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x01d1
            goto L_0x01ad
        L_0x01d1:
            switch(r3) {
                case 0: goto L_0x01d7;
                case 1: goto L_0x01d7;
                case 2: goto L_0x01d5;
                default: goto L_0x01d4;
            }
        L_0x01d4:
            goto L_0x01d9
        L_0x01d5:
            r14 = r10
            goto L_0x01dc
        L_0x01d7:
            r14 = r4
            goto L_0x01dc
        L_0x01d9:
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r14 = r0
        L_0x01dc:
            androidx.media3.extractor.text.ttml.TtmlRegion r4 = new androidx.media3.extractor.text.ttml.TtmlRegion
            r8 = 0
            r12 = 1
            r10 = r1
            r6 = r13
            r13 = r2
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseRegionAttributes(org.xmlpull.v1.XmlPullParser, int, androidx.media3.extractor.text.ttml.TtmlParser$TtsExtent, java.util.Map):androidx.media3.extractor.text.ttml.TtmlRegion");
    }

    private static float parseShear(String str) {
        Matcher matcher = SIGNED_PERCENTAGE.matcher(str);
        if (!matcher.matches()) {
            a.C("Invalid value for shear: ", str, "TtmlParser");
            return Float.MAX_VALUE;
        }
        try {
            return Math.min(100.0f, Math.max(-100.0f, Float.parseFloat((String) Assertions.checkNotNull(matcher.group(1)))));
        } catch (NumberFormatException e) {
            Log.w("TtmlParser", "Failed to parse shear: " + str, e);
            return Float.MAX_VALUE;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01d5, code lost:
        if (r3.equals("text") == false) goto L_0x01cc;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static androidx.media3.extractor.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r12, androidx.media3.extractor.text.ttml.TtmlStyle r13) {
        /*
            int r0 = r12.getAttributeCount()
            r1 = 0
            r2 = r1
        L_0x0006:
            if (r2 >= r0) goto L_0x0300
            java.lang.String r3 = r12.getAttributeValue(r2)
            java.lang.String r4 = r12.getAttributeName(r2)
            r4.getClass()
            int r5 = r4.hashCode()
            r6 = 5
            r7 = 4
            r8 = -1
            r9 = 3
            r10 = 2
            r11 = 1
            switch(r5) {
                case -1550943582: goto L_0x00f0;
                case -1289044182: goto L_0x00e4;
                case -1224696685: goto L_0x00d8;
                case -1065511464: goto L_0x00cb;
                case -1008619738: goto L_0x00bf;
                case -879295043: goto L_0x00b2;
                case -734428249: goto L_0x00a6;
                case 3355: goto L_0x009a;
                case 3511770: goto L_0x008d;
                case 94842723: goto L_0x0080;
                case 109403361: goto L_0x0073;
                case 110138194: goto L_0x0065;
                case 365601008: goto L_0x0058;
                case 921125321: goto L_0x004a;
                case 1115953443: goto L_0x003d;
                case 1287124693: goto L_0x0030;
                case 1754920356: goto L_0x0023;
                default: goto L_0x0020;
            }
        L_0x0020:
            r4 = r8
            goto L_0x00fb
        L_0x0023:
            java.lang.String r5 = "multiRowAlign"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x002c
            goto L_0x0020
        L_0x002c:
            r4 = 16
            goto L_0x00fb
        L_0x0030:
            java.lang.String r5 = "backgroundColor"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0039
            goto L_0x0020
        L_0x0039:
            r4 = 15
            goto L_0x00fb
        L_0x003d:
            java.lang.String r5 = "rubyPosition"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0046
            goto L_0x0020
        L_0x0046:
            r4 = 14
            goto L_0x00fb
        L_0x004a:
            java.lang.String r5 = "textEmphasis"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0054
            goto L_0x0020
        L_0x0054:
            r4 = 13
            goto L_0x00fb
        L_0x0058:
            java.lang.String r5 = "fontSize"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0061
            goto L_0x0020
        L_0x0061:
            r4 = 12
            goto L_0x00fb
        L_0x0065:
            java.lang.String r5 = "textCombine"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x006f
            goto L_0x0020
        L_0x006f:
            r4 = 11
            goto L_0x00fb
        L_0x0073:
            java.lang.String r5 = "shear"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x007c
            goto L_0x0020
        L_0x007c:
            r4 = 10
            goto L_0x00fb
        L_0x0080:
            java.lang.String r5 = "color"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0089
            goto L_0x0020
        L_0x0089:
            r4 = 9
            goto L_0x00fb
        L_0x008d:
            java.lang.String r5 = "ruby"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0096
            goto L_0x0020
        L_0x0096:
            r4 = 8
            goto L_0x00fb
        L_0x009a:
            java.lang.String r5 = "id"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00a4
            goto L_0x0020
        L_0x00a4:
            r4 = 7
            goto L_0x00fb
        L_0x00a6:
            java.lang.String r5 = "fontWeight"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00b0
            goto L_0x0020
        L_0x00b0:
            r4 = 6
            goto L_0x00fb
        L_0x00b2:
            java.lang.String r5 = "textDecoration"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00bd
            goto L_0x0020
        L_0x00bd:
            r4 = r6
            goto L_0x00fb
        L_0x00bf:
            java.lang.String r5 = "origin"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00c9
            goto L_0x0020
        L_0x00c9:
            r4 = r7
            goto L_0x00fb
        L_0x00cb:
            java.lang.String r5 = "textAlign"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00d6
            goto L_0x0020
        L_0x00d6:
            r4 = r9
            goto L_0x00fb
        L_0x00d8:
            java.lang.String r5 = "fontFamily"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00e2
            goto L_0x0020
        L_0x00e2:
            r4 = r10
            goto L_0x00fb
        L_0x00e4:
            java.lang.String r5 = "extent"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00ee
            goto L_0x0020
        L_0x00ee:
            r4 = r11
            goto L_0x00fb
        L_0x00f0:
            java.lang.String r5 = "fontStyle"
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x00fa
            goto L_0x0020
        L_0x00fa:
            r4 = r1
        L_0x00fb:
            java.lang.String r5 = "TtmlParser"
            switch(r4) {
                case 0: goto L_0x02ee;
                case 1: goto L_0x02e5;
                case 2: goto L_0x02dc;
                case 3: goto L_0x02cf;
                case 4: goto L_0x02c6;
                case 5: goto L_0x0263;
                case 6: goto L_0x0253;
                case 7: goto L_0x023c;
                case 8: goto L_0x01be;
                case 9: goto L_0x01aa;
                case 10: goto L_0x019c;
                case 11: goto L_0x016f;
                case 12: goto L_0x015f;
                case 13: goto L_0x0151;
                case 14: goto L_0x0124;
                case 15: goto L_0x0110;
                case 16: goto L_0x0102;
                default: goto L_0x0100;
            }
        L_0x0100:
            goto L_0x02fc
        L_0x0102:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            android.text.Layout$Alignment r3 = parseAlignment(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setMultiRowAlign(r3)
            goto L_0x02fc
        L_0x0110:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            int r4 = androidx.media3.common.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x011d }
            r13.setBackgroundColor(r4)     // Catch:{ IllegalArgumentException -> 0x011d }
            goto L_0x02fc
        L_0x011d:
            java.lang.String r4 = "Failed parsing background value: "
            A.a.C(r4, r3, r5)
            goto L_0x02fc
        L_0x0124:
            java.lang.String r3 = og.k.S(r3)
            r3.getClass()
            java.lang.String r4 = "before"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0147
            java.lang.String r4 = "after"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x013d
            goto L_0x02fc
        L_0x013d:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyPosition(r10)
            goto L_0x02fc
        L_0x0147:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyPosition(r11)
            goto L_0x02fc
        L_0x0151:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TextEmphasis r3 = androidx.media3.extractor.text.ttml.TextEmphasis.parse(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextEmphasis(r3)
            goto L_0x02fc
        L_0x015f:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)     // Catch:{ SubtitleDecoderException -> 0x0168 }
            parseFontSize(r3, r13)     // Catch:{ SubtitleDecoderException -> 0x0168 }
            goto L_0x02fc
        L_0x0168:
            java.lang.String r4 = "Failed parsing fontSize value: "
            A.a.C(r4, r3, r5)
            goto L_0x02fc
        L_0x016f:
            java.lang.String r3 = og.k.S(r3)
            r3.getClass()
            java.lang.String r4 = "all"
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x0192
            java.lang.String r4 = "none"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0188
            goto L_0x02fc
        L_0x0188:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextCombine(r1)
            goto L_0x02fc
        L_0x0192:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextCombine(r11)
            goto L_0x02fc
        L_0x019c:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            float r3 = parseShear(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setShearPercentage(r3)
            goto L_0x02fc
        L_0x01aa:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            int r4 = androidx.media3.common.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x01b7 }
            r13.setFontColor(r4)     // Catch:{ IllegalArgumentException -> 0x01b7 }
            goto L_0x02fc
        L_0x01b7:
            java.lang.String r4 = "Failed parsing color value: "
            A.a.C(r4, r3, r5)
            goto L_0x02fc
        L_0x01be:
            java.lang.String r3 = og.k.S(r3)
            r3.getClass()
            int r4 = r3.hashCode()
            switch(r4) {
                case -618561360: goto L_0x0205;
                case -410956671: goto L_0x01fa;
                case -250518009: goto L_0x01ef;
                case -136074796: goto L_0x01e3;
                case 3016401: goto L_0x01d8;
                case 3556653: goto L_0x01ce;
                default: goto L_0x01cc;
            }
        L_0x01cc:
            r6 = r8
            goto L_0x020f
        L_0x01ce:
            java.lang.String r4 = "text"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x020f
            goto L_0x01cc
        L_0x01d8:
            java.lang.String r4 = "base"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01e1
            goto L_0x01cc
        L_0x01e1:
            r6 = r7
            goto L_0x020f
        L_0x01e3:
            java.lang.String r4 = "textContainer"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01ed
            goto L_0x01cc
        L_0x01ed:
            r6 = r9
            goto L_0x020f
        L_0x01ef:
            java.lang.String r4 = "delimiter"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01f8
            goto L_0x01cc
        L_0x01f8:
            r6 = r10
            goto L_0x020f
        L_0x01fa:
            java.lang.String r4 = "container"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0203
            goto L_0x01cc
        L_0x0203:
            r6 = r11
            goto L_0x020f
        L_0x0205:
            java.lang.String r4 = "baseContainer"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x020e
            goto L_0x01cc
        L_0x020e:
            r6 = r1
        L_0x020f:
            switch(r6) {
                case 0: goto L_0x0232;
                case 1: goto L_0x0228;
                case 2: goto L_0x021e;
                case 3: goto L_0x0214;
                case 4: goto L_0x0232;
                case 5: goto L_0x0214;
                default: goto L_0x0212;
            }
        L_0x0212:
            goto L_0x02fc
        L_0x0214:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r9)
            goto L_0x02fc
        L_0x021e:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r7)
            goto L_0x02fc
        L_0x0228:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r11)
            goto L_0x02fc
        L_0x0232:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setRubyType(r10)
            goto L_0x02fc
        L_0x023c:
            java.lang.String r4 = "style"
            java.lang.String r5 = r12.getName()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x02fc
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setId(r3)
            goto L_0x02fc
        L_0x0253:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            java.lang.String r4 = "bold"
            boolean r3 = r4.equalsIgnoreCase(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setBold(r3)
            goto L_0x02fc
        L_0x0263:
            java.lang.String r3 = og.k.S(r3)
            r3.getClass()
            int r4 = r3.hashCode()
            switch(r4) {
                case -1461280213: goto L_0x0294;
                case -1026963764: goto L_0x0288;
                case 913457136: goto L_0x027d;
                case 1679736913: goto L_0x0272;
                default: goto L_0x0271;
            }
        L_0x0271:
            goto L_0x029e
        L_0x0272:
            java.lang.String r4 = "linethrough"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x027b
            goto L_0x029e
        L_0x027b:
            r8 = r9
            goto L_0x029e
        L_0x027d:
            java.lang.String r4 = "nolinethrough"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0286
            goto L_0x029e
        L_0x0286:
            r8 = r10
            goto L_0x029e
        L_0x0288:
            java.lang.String r4 = "underline"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0292
            goto L_0x029e
        L_0x0292:
            r8 = r11
            goto L_0x029e
        L_0x0294:
            java.lang.String r4 = "nounderline"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x029d
            goto L_0x029e
        L_0x029d:
            r8 = r1
        L_0x029e:
            switch(r8) {
                case 0: goto L_0x02bd;
                case 1: goto L_0x02b4;
                case 2: goto L_0x02ab;
                case 3: goto L_0x02a2;
                default: goto L_0x02a1;
            }
        L_0x02a1:
            goto L_0x02fc
        L_0x02a2:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setLinethrough(r11)
            goto L_0x02fc
        L_0x02ab:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setLinethrough(r1)
            goto L_0x02fc
        L_0x02b4:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setUnderline(r11)
            goto L_0x02fc
        L_0x02bd:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setUnderline(r1)
            goto L_0x02fc
        L_0x02c6:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setOrigin(r3)
            goto L_0x02fc
        L_0x02cf:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            android.text.Layout$Alignment r3 = parseAlignment(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x02fc
        L_0x02dc:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setFontFamily(r3)
            goto L_0x02fc
        L_0x02e5:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setExtent(r3)
            goto L_0x02fc
        L_0x02ee:
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = createIfNull(r13)
            java.lang.String r4 = "italic"
            boolean r3 = r4.equalsIgnoreCase(r3)
            androidx.media3.extractor.text.ttml.TtmlStyle r13 = r13.setItalic(r3)
        L_0x02fc:
            int r2 = r2 + 1
            goto L_0x0006
        L_0x0300:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, androidx.media3.extractor.text.ttml.TtmlStyle):androidx.media3.extractor.text.ttml.TtmlStyle");
    }

    private static String[] parseStyleIds(String str) {
        String trim = str.trim();
        if (trim.isEmpty()) {
            return new String[0];
        }
        return Util.split(trim, "\\s+");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00bc, code lost:
        if (r13.equals("ms") == false) goto L_0x00b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00f4, code lost:
        r8 = r8 / r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00fc, code lost:
        r8 = r8 * r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long parseTimeExpression(java.lang.String r13, androidx.media3.extractor.text.ttml.TtmlParser.FrameAndTickRate r14) {
        /*
            java.util.regex.Pattern r0 = CLOCK_TIME
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            r2 = 4
            r3 = 3
            r4 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r6 = 2
            r7 = 1
            if (r1 == 0) goto L_0x0085
            java.lang.String r13 = r0.group(r7)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r7 = java.lang.Long.parseLong(r13)
            r9 = 3600(0xe10, double:1.7786E-320)
            long r7 = r7 * r9
            double r7 = (double) r7
            java.lang.String r13 = r0.group(r6)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r9 = java.lang.Long.parseLong(r13)
            r11 = 60
            long r9 = r9 * r11
            double r9 = (double) r9
            double r7 = r7 + r9
            java.lang.String r13 = r0.group(r3)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            long r9 = java.lang.Long.parseLong(r13)
            double r9 = (double) r9
            double r7 = r7 + r9
            java.lang.String r13 = r0.group(r2)
            r1 = 0
            if (r13 == 0) goto L_0x0057
            double r9 = java.lang.Double.parseDouble(r13)
            goto L_0x0058
        L_0x0057:
            r9 = r1
        L_0x0058:
            double r7 = r7 + r9
            r13 = 5
            java.lang.String r13 = r0.group(r13)
            if (r13 == 0) goto L_0x006a
            long r9 = java.lang.Long.parseLong(r13)
            float r13 = (float) r9
            float r3 = r14.effectiveFrameRate
            float r13 = r13 / r3
            double r9 = (double) r13
            goto L_0x006b
        L_0x006a:
            r9 = r1
        L_0x006b:
            double r7 = r7 + r9
            r13 = 6
            java.lang.String r13 = r0.group(r13)
            if (r13 == 0) goto L_0x0081
            long r0 = java.lang.Long.parseLong(r13)
            double r0 = (double) r0
            int r13 = r14.subFrameRate
            double r2 = (double) r13
            double r0 = r0 / r2
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            double r1 = r0 / r13
        L_0x0081:
            double r7 = r7 + r1
            double r7 = r7 * r4
            long r13 = (long) r7
            return r13
        L_0x0085:
            java.util.regex.Pattern r0 = OFFSET_TIME
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            if (r1 == 0) goto L_0x010b
            java.lang.String r13 = r0.group(r7)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            double r8 = java.lang.Double.parseDouble(r13)
            java.lang.String r13 = r0.group(r6)
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            java.lang.String r13 = (java.lang.String) r13
            r13.getClass()
            int r0 = r13.hashCode()
            r1 = -1
            switch(r0) {
                case 102: goto L_0x00e1;
                case 104: goto L_0x00d6;
                case 109: goto L_0x00cb;
                case 116: goto L_0x00bf;
                case 3494: goto L_0x00b6;
                default: goto L_0x00b4;
            }
        L_0x00b4:
            r2 = r1
            goto L_0x00eb
        L_0x00b6:
            java.lang.String r0 = "ms"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00eb
            goto L_0x00b4
        L_0x00bf:
            java.lang.String r0 = "t"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00c9
            goto L_0x00b4
        L_0x00c9:
            r2 = r3
            goto L_0x00eb
        L_0x00cb:
            java.lang.String r0 = "m"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00d4
            goto L_0x00b4
        L_0x00d4:
            r2 = r6
            goto L_0x00eb
        L_0x00d6:
            java.lang.String r0 = "h"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00df
            goto L_0x00b4
        L_0x00df:
            r2 = r7
            goto L_0x00eb
        L_0x00e1:
            java.lang.String r0 = "f"
            boolean r13 = r13.equals(r0)
            if (r13 != 0) goto L_0x00ea
            goto L_0x00b4
        L_0x00ea:
            r2 = 0
        L_0x00eb:
            switch(r2) {
                case 0: goto L_0x0104;
                case 1: goto L_0x00fe;
                case 2: goto L_0x00fa;
                case 3: goto L_0x00f6;
                case 4: goto L_0x00ef;
                default: goto L_0x00ee;
            }
        L_0x00ee:
            goto L_0x0108
        L_0x00ef:
            r13 = 4652007308841189376(0x408f400000000000, double:1000.0)
        L_0x00f4:
            double r8 = r8 / r13
            goto L_0x0108
        L_0x00f6:
            int r13 = r14.tickRate
            double r13 = (double) r13
            goto L_0x00f4
        L_0x00fa:
            r13 = 4633641066610819072(0x404e000000000000, double:60.0)
        L_0x00fc:
            double r8 = r8 * r13
            goto L_0x0108
        L_0x00fe:
            r13 = 4660134898793709568(0x40ac200000000000, double:3600.0)
            goto L_0x00fc
        L_0x0104:
            float r13 = r14.effectiveFrameRate
            double r13 = (double) r13
            goto L_0x00f4
        L_0x0108:
            double r8 = r8 * r4
            long r13 = (long) r8
            return r13
        L_0x010b:
            androidx.media3.extractor.text.SubtitleDecoderException r14 = new androidx.media3.extractor.text.SubtitleDecoderException
            java.lang.String r0 = "Malformed time expression: "
            java.lang.String r13 = i.C0212a.l(r0, r13)
            r14.<init>(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ttml.TtmlParser.parseTimeExpression(java.lang.String, androidx.media3.extractor.text.ttml.TtmlParser$FrameAndTickRate):long");
    }

    private static TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "extent");
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w("TtmlParser", "Ignoring non-pixel tts extent: ".concat(attributeValue));
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1))), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2))));
        } catch (NumberFormatException unused) {
            Log.w("TtmlParser", "Ignoring malformed tts extent: ".concat(attributeValue));
            return null;
        }
    }

    public int getCueReplacementBehavior() {
        return 1;
    }

    public void parse(byte[] bArr, int i2, int i7, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        LegacySubtitleUtil.toCuesWithTiming(parseToLegacySubtitle(bArr, i2, i7), outputOptions, consumer);
    }

    public Subtitle parseToLegacySubtitle(byte[] bArr, int i2, int i7) {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            hashMap2.put("", new TtmlRegion(""));
            TtmlSubtitle ttmlSubtitle = null;
            newPullParser.setInput(new ByteArrayInputStream(bArr, i2, i7), (String) null);
            ArrayDeque arrayDeque = new ArrayDeque();
            int i8 = 0;
            int i10 = 15;
            FrameAndTickRate frameAndTickRate = DEFAULT_FRAME_AND_TICK_RATE;
            TtsExtent ttsExtent = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i8 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if ("tt".equals(name)) {
                            frameAndTickRate = parseFrameAndTickRates(newPullParser);
                            i10 = parseCellRows(newPullParser, 15);
                            ttsExtent = parseTtsExtent(newPullParser);
                        }
                        FrameAndTickRate frameAndTickRate2 = frameAndTickRate;
                        TtsExtent ttsExtent2 = ttsExtent;
                        int i11 = i10;
                        FrameAndTickRate frameAndTickRate3 = frameAndTickRate2;
                        if (!isSupportedTag(name)) {
                            Log.i("TtmlParser", "Ignoring unsupported tag: " + newPullParser.getName());
                        } else {
                            if ("head".equals(name)) {
                                parseHeader(newPullParser, hashMap, i11, ttsExtent2, hashMap2, hashMap3);
                            } else {
                                try {
                                    TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate3);
                                    arrayDeque.push(parseNode);
                                    if (ttmlNode != null) {
                                        ttmlNode.addChild(parseNode);
                                    }
                                } catch (SubtitleDecoderException e) {
                                    Log.w("TtmlParser", "Suppressing parser error", e);
                                }
                            }
                            FrameAndTickRate frameAndTickRate4 = frameAndTickRate3;
                            i10 = i11;
                            ttsExtent = ttsExtent2;
                            frameAndTickRate = frameAndTickRate4;
                        }
                        i8++;
                        FrameAndTickRate frameAndTickRate42 = frameAndTickRate3;
                        i10 = i11;
                        ttsExtent = ttsExtent2;
                        frameAndTickRate = frameAndTickRate42;
                    } else if (eventType == 4) {
                        ((TtmlNode) Assertions.checkNotNull(ttmlNode)).addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                    } else if (eventType == 3) {
                        if (newPullParser.getName().equals("tt")) {
                            ttmlSubtitle = new TtmlSubtitle((TtmlNode) Assertions.checkNotNull((TtmlNode) arrayDeque.peek()), hashMap, hashMap2, hashMap3);
                        }
                        arrayDeque.pop();
                    }
                } else if (eventType == 2) {
                    i8++;
                } else if (eventType == 3) {
                    i8--;
                }
                newPullParser.next();
            }
            return (Subtitle) Assertions.checkNotNull(ttmlSubtitle);
        } catch (XmlPullParserException e7) {
            throw new IllegalStateException("Unable to decode source", e7);
        } catch (IOException e8) {
            throw new IllegalStateException("Unexpected error when reading input.", e8);
        }
    }
}
