package androidx.media3.extractor.text.webvtt;

import A.a;
import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.HorizontalTextInVerticalContextSpan;
import androidx.media3.common.text.RubySpan;
import androidx.media3.common.text.SpanUtil;
import androidx.media3.common.text.VoiceSpan;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WebvttCueParser {
    public static final Pattern CUE_HEADER_PATTERN = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)((?:.|\\f)*)?$");
    private static final Pattern CUE_SETTING_PATTERN = Pattern.compile("(\\S+?):(\\S+)");
    private static final Map<String, Integer> DEFAULT_BACKGROUND_COLORS;
    private static final Map<String, Integer> DEFAULT_TEXT_COLORS;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Element {
        /* access modifiers changed from: private */
        public static final Comparator<Element> BY_START_POSITION_ASC = new a(0);
        /* access modifiers changed from: private */
        public final int endPosition;
        /* access modifiers changed from: private */
        public final StartTag startTag;

        private Element(StartTag startTag2, int i2) {
            this.startTag = startTag2;
            this.endPosition = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class StartTag {
        public final Set<String> classes;
        public final String name;
        public final int position;
        public final String voice;

        private StartTag(String str, int i2, String str2, Set<String> set) {
            this.position = i2;
            this.name = str;
            this.voice = str2;
            this.classes = set;
        }

        public static StartTag buildStartTag(String str, int i2) {
            String str2;
            String trim = str.trim();
            Assertions.checkArgument(!trim.isEmpty());
            int indexOf = trim.indexOf(" ");
            if (indexOf == -1) {
                str2 = "";
            } else {
                String trim2 = trim.substring(indexOf).trim();
                trim = trim.substring(0, indexOf);
                str2 = trim2;
            }
            String[] split = Util.split(trim, "\\.");
            String str3 = split[0];
            HashSet hashSet = new HashSet();
            for (int i7 = 1; i7 < split.length; i7++) {
                hashSet.add(split[i7]);
            }
            return new StartTag(str3, i2, str2, hashSet);
        }

        public static StartTag buildWholeCueVirtualTag() {
            return new StartTag("", 0, "", Collections.EMPTY_SET);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class StyleMatch implements Comparable<StyleMatch> {
        public final int score;
        public final WebvttCssStyle style;

        public StyleMatch(int i2, WebvttCssStyle webvttCssStyle) {
            this.score = i2;
            this.style = webvttCssStyle;
        }

        public int compareTo(StyleMatch styleMatch) {
            return Integer.compare(this.score, styleMatch.score);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WebvttCueInfoBuilder {
        public long endTimeUs = 0;
        public float line = -3.4028235E38f;
        public int lineAnchor = 0;
        public int lineType = 1;
        public float position = -3.4028235E38f;
        public int positionAnchor = Integer.MIN_VALUE;
        public float size = 1.0f;
        public long startTimeUs = 0;
        public CharSequence text;
        public int textAlignment = 2;
        public int verticalType = Integer.MIN_VALUE;

        private static float computeLine(float f, int i2) {
            int i7 = (f > -3.4028235E38f ? 1 : (f == -3.4028235E38f ? 0 : -1));
            if (i7 != 0 && i2 == 0 && (f < 0.0f || f > 1.0f)) {
                return 1.0f;
            }
            if (i7 != 0) {
                return f;
            }
            if (i2 == 0) {
                return 1.0f;
            }
            return -3.4028235E38f;
        }

        private static Layout.Alignment convertTextAlignment(int i2) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return Layout.Alignment.ALIGN_CENTER;
                }
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            a.D(i2, "Unknown textAlignment: ", "WebvttCueParser");
                            return null;
                        }
                    }
                }
                return Layout.Alignment.ALIGN_OPPOSITE;
            }
            return Layout.Alignment.ALIGN_NORMAL;
        }

        private static float deriveMaxSize(int i2, float f) {
            if (i2 == 0) {
                return 1.0f - f;
            }
            if (i2 != 1) {
                if (i2 == 2) {
                    return f;
                }
                throw new IllegalStateException(String.valueOf(i2));
            } else if (f <= 0.5f) {
                return f * 2.0f;
            } else {
                return (1.0f - f) * 2.0f;
            }
        }

        private static float derivePosition(int i2) {
            if (i2 == 4) {
                return 0.0f;
            }
            if (i2 != 5) {
                return 0.5f;
            }
            return 1.0f;
        }

        private static int derivePositionAnchor(int i2) {
            if (i2 == 1) {
                return 0;
            }
            if (i2 == 3) {
                return 2;
            }
            if (i2 == 4) {
                return 0;
            }
            if (i2 != 5) {
                return 1;
            }
            return 2;
        }

        public WebvttCueInfo build() {
            return new WebvttCueInfo(toCueBuilder().build(), this.startTimeUs, this.endTimeUs);
        }

        public Cue.Builder toCueBuilder() {
            float f = this.position;
            if (f == -3.4028235E38f) {
                f = derivePosition(this.textAlignment);
            }
            int i2 = this.positionAnchor;
            if (i2 == Integer.MIN_VALUE) {
                i2 = derivePositionAnchor(this.textAlignment);
            }
            Cue.Builder verticalType2 = new Cue.Builder().setTextAlignment(convertTextAlignment(this.textAlignment)).setLine(computeLine(this.line, this.lineType), this.lineType).setLineAnchor(this.lineAnchor).setPosition(f).setPositionAnchor(i2).setSize(Math.min(this.size, deriveMaxSize(i2, f))).setVerticalType(this.verticalType);
            CharSequence charSequence = this.text;
            if (charSequence != null) {
                verticalType2.setText(charSequence);
            }
            return verticalType2;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("white", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap.put("lime", Integer.valueOf(Color.rgb(0, ScoverState.TYPE_NFC_SMART_COVER, 0)));
        hashMap.put("cyan", Integer.valueOf(Color.rgb(0, ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap.put("red", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, 0, 0)));
        hashMap.put("yellow", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER, 0)));
        hashMap.put("magenta", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, 0, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap.put("blue", Integer.valueOf(Color.rgb(0, 0, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap.put("black", Integer.valueOf(Color.rgb(0, 0, 0)));
        DEFAULT_TEXT_COLORS = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("bg_white", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap2.put("bg_lime", Integer.valueOf(Color.rgb(0, ScoverState.TYPE_NFC_SMART_COVER, 0)));
        hashMap2.put("bg_cyan", Integer.valueOf(Color.rgb(0, ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap2.put("bg_red", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, 0, 0)));
        hashMap2.put("bg_yellow", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, ScoverState.TYPE_NFC_SMART_COVER, 0)));
        hashMap2.put("bg_magenta", Integer.valueOf(Color.rgb(ScoverState.TYPE_NFC_SMART_COVER, 0, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap2.put("bg_blue", Integer.valueOf(Color.rgb(0, 0, ScoverState.TYPE_NFC_SMART_COVER)));
        hashMap2.put("bg_black", Integer.valueOf(Color.rgb(0, 0, 0)));
        DEFAULT_BACKGROUND_COLORS = Collections.unmodifiableMap(hashMap2);
    }

    private static void applyDefaultColors(SpannableStringBuilder spannableStringBuilder, Set<String> set, int i2, int i7) {
        for (String next : set) {
            Map<String, Integer> map = DEFAULT_TEXT_COLORS;
            if (map.containsKey(next)) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(map.get(next).intValue()), i2, i7, 33);
            } else {
                Map<String, Integer> map2 = DEFAULT_BACKGROUND_COLORS;
                if (map2.containsKey(next)) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(map2.get(next).intValue()), i2, i7, 33);
                }
            }
        }
    }

    private static void applyEntity(String str, SpannableStringBuilder spannableStringBuilder) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 3309:
                if (str.equals("gt")) {
                    c5 = 0;
                    break;
                }
                break;
            case 3464:
                if (str.equals("lt")) {
                    c5 = 1;
                    break;
                }
                break;
            case 96708:
                if (str.equals("amp")) {
                    c5 = 2;
                    break;
                }
                break;
            case 3374865:
                if (str.equals("nbsp")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                spannableStringBuilder.append('>');
                return;
            case 1:
                spannableStringBuilder.append('<');
                return;
            case 2:
                spannableStringBuilder.append('&');
                return;
            case 3:
                spannableStringBuilder.append(' ');
                return;
            default:
                Log.w("WebvttCueParser", "ignoring unsupported entity: '&" + str + ";'");
                return;
        }
    }

    private static void applyRubySpans(SpannableStringBuilder spannableStringBuilder, String str, StartTag startTag, List<Element> list, List<WebvttCssStyle> list2) {
        int rubyPosition = getRubyPosition(list2, str, startTag);
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        Collections.sort(arrayList, Element.BY_START_POSITION_ASC);
        int i2 = startTag.position;
        int i7 = 0;
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            if ("rt".equals(((Element) arrayList.get(i8)).startTag.name)) {
                Element element = (Element) arrayList.get(i8);
                int firstKnownRubyPosition = firstKnownRubyPosition(getRubyPosition(list2, str, element.startTag), rubyPosition, 1);
                int i10 = element.startTag.position - i7;
                int access$300 = element.endPosition - i7;
                CharSequence subSequence = spannableStringBuilder.subSequence(i10, access$300);
                spannableStringBuilder.delete(i10, access$300);
                spannableStringBuilder.setSpan(new RubySpan(subSequence.toString(), firstKnownRubyPosition), i2, i10, 33);
                i7 = subSequence.length() + i7;
                i2 = i10;
            }
        }
    }

    private static void applySpansForTag(String str, StartTag startTag, List<Element> list, SpannableStringBuilder spannableStringBuilder, List<WebvttCssStyle> list2) {
        int i2 = startTag.position;
        int length = spannableStringBuilder.length();
        String str2 = startTag.name;
        str2.getClass();
        char c5 = 65535;
        switch (str2.hashCode()) {
            case 0:
                if (str2.equals("")) {
                    c5 = 0;
                    break;
                }
                break;
            case 98:
                if (str2.equals("b")) {
                    c5 = 1;
                    break;
                }
                break;
            case 99:
                if (str2.equals("c")) {
                    c5 = 2;
                    break;
                }
                break;
            case 105:
                if (str2.equals("i")) {
                    c5 = 3;
                    break;
                }
                break;
            case 117:
                if (str2.equals("u")) {
                    c5 = 4;
                    break;
                }
                break;
            case 118:
                if (str2.equals("v")) {
                    c5 = 5;
                    break;
                }
                break;
            case 3314158:
                if (str2.equals("lang")) {
                    c5 = 6;
                    break;
                }
                break;
            case 3511770:
                if (str2.equals("ruby")) {
                    c5 = 7;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 6:
                break;
            case 1:
                spannableStringBuilder.setSpan(new StyleSpan(1), i2, length, 33);
                break;
            case 2:
                applyDefaultColors(spannableStringBuilder, startTag.classes, i2, length);
                break;
            case 3:
                spannableStringBuilder.setSpan(new StyleSpan(2), i2, length, 33);
                break;
            case 4:
                spannableStringBuilder.setSpan(new UnderlineSpan(), i2, length, 33);
                break;
            case 5:
                applyVoiceSpan(spannableStringBuilder, startTag.voice, i2, length);
                break;
            case 7:
                applyRubySpans(spannableStringBuilder, str, startTag, list, list2);
                break;
            default:
                return;
        }
        List<StyleMatch> applicableStyles = getApplicableStyles(list2, str, startTag);
        for (int i7 = 0; i7 < applicableStyles.size(); i7++) {
            applyStyleToText(spannableStringBuilder, applicableStyles.get(i7).style, i2, length);
        }
    }

    private static void applyStyleToText(SpannableStringBuilder spannableStringBuilder, WebvttCssStyle webvttCssStyle, int i2, int i7) {
        if (webvttCssStyle != null) {
            if (webvttCssStyle.getStyle() != -1) {
                SpanUtil.addOrReplaceSpan(spannableStringBuilder, new StyleSpan(webvttCssStyle.getStyle()), i2, i7, 33);
            }
            if (webvttCssStyle.isLinethrough()) {
                spannableStringBuilder.setSpan(new StrikethroughSpan(), i2, i7, 33);
            }
            if (webvttCssStyle.isUnderline()) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i2, i7, 33);
            }
            if (webvttCssStyle.hasFontColor()) {
                SpanUtil.addOrReplaceSpan(spannableStringBuilder, new ForegroundColorSpan(webvttCssStyle.getFontColor()), i2, i7, 33);
            }
            if (webvttCssStyle.hasBackgroundColor()) {
                SpanUtil.addOrReplaceSpan(spannableStringBuilder, new BackgroundColorSpan(webvttCssStyle.getBackgroundColor()), i2, i7, 33);
            }
            if (webvttCssStyle.getFontFamily() != null) {
                SpanUtil.addOrReplaceSpan(spannableStringBuilder, new TypefaceSpan(webvttCssStyle.getFontFamily()), i2, i7, 33);
            }
            int fontSizeUnit = webvttCssStyle.getFontSizeUnit();
            if (fontSizeUnit == 1) {
                SpanUtil.addOrReplaceSpan(spannableStringBuilder, new AbsoluteSizeSpan((int) webvttCssStyle.getFontSize(), true), i2, i7, 33);
            } else if (fontSizeUnit == 2) {
                SpanUtil.addOrReplaceSpan(spannableStringBuilder, new RelativeSizeSpan(webvttCssStyle.getFontSize()), i2, i7, 33);
            } else if (fontSizeUnit == 3) {
                SpanUtil.addOrReplaceSpan(spannableStringBuilder, new RelativeSizeSpan(webvttCssStyle.getFontSize() / 100.0f), i2, i7, 33);
            }
            if (webvttCssStyle.getCombineUpright()) {
                spannableStringBuilder.setSpan(new HorizontalTextInVerticalContextSpan(), i2, i7, 33);
            }
        }
    }

    private static void applyVoiceSpan(SpannableStringBuilder spannableStringBuilder, String str, int i2, int i7) {
        spannableStringBuilder.setSpan(new VoiceSpan(str), i2, i7, 33);
    }

    private static int findEndOfTag(String str, int i2) {
        int indexOf = str.indexOf(62, i2);
        if (indexOf == -1) {
            return str.length();
        }
        return indexOf + 1;
    }

    private static int firstKnownRubyPosition(int i2, int i7, int i8) {
        if (i2 != -1) {
            return i2;
        }
        if (i7 != -1) {
            return i7;
        }
        if (i8 != -1) {
            return i8;
        }
        throw new IllegalArgumentException();
    }

    private static List<StyleMatch> getApplicableStyles(List<WebvttCssStyle> list, String str, StartTag startTag) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            WebvttCssStyle webvttCssStyle = list.get(i2);
            int specificityScore = webvttCssStyle.getSpecificityScore(str, startTag.name, startTag.classes, startTag.voice);
            if (specificityScore > 0) {
                arrayList.add(new StyleMatch(specificityScore, webvttCssStyle));
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static int getRubyPosition(List<WebvttCssStyle> list, String str, StartTag startTag) {
        List<StyleMatch> applicableStyles = getApplicableStyles(list, str, startTag);
        for (int i2 = 0; i2 < applicableStyles.size(); i2++) {
            WebvttCssStyle webvttCssStyle = applicableStyles.get(i2).style;
            if (webvttCssStyle.getRubyPosition() != -1) {
                return webvttCssStyle.getRubyPosition();
            }
        }
        return -1;
    }

    private static String getTagName(String str) {
        String trim = str.trim();
        Assertions.checkArgument(!trim.isEmpty());
        return Util.splitAtFirst(trim, "[ \\.]")[0];
    }

    private static boolean isSupportedTag(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 98:
                if (str.equals("b")) {
                    c5 = 0;
                    break;
                }
                break;
            case 99:
                if (str.equals("c")) {
                    c5 = 1;
                    break;
                }
                break;
            case 105:
                if (str.equals("i")) {
                    c5 = 2;
                    break;
                }
                break;
            case 117:
                if (str.equals("u")) {
                    c5 = 3;
                    break;
                }
                break;
            case 118:
                if (str.equals("v")) {
                    c5 = 4;
                    break;
                }
                break;
            case 3650:
                if (str.equals("rt")) {
                    c5 = 5;
                    break;
                }
                break;
            case 3314158:
                if (str.equals("lang")) {
                    c5 = 6;
                    break;
                }
                break;
            case 3511770:
                if (str.equals("ruby")) {
                    c5 = 7;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                return false;
        }
    }

    public static Cue newCueForText(CharSequence charSequence) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        webvttCueInfoBuilder.text = charSequence;
        return webvttCueInfoBuilder.toCueBuilder().build();
    }

    public static WebvttCueInfo parseCue(ParsableByteArray parsableByteArray, List<WebvttCssStyle> list) {
        String readLine = parsableByteArray.readLine();
        if (readLine == null) {
            return null;
        }
        Pattern pattern = CUE_HEADER_PATTERN;
        Matcher matcher = pattern.matcher(readLine);
        if (matcher.matches()) {
            return parseCue((String) null, matcher, parsableByteArray, list);
        }
        String readLine2 = parsableByteArray.readLine();
        if (readLine2 == null) {
            return null;
        }
        Matcher matcher2 = pattern.matcher(readLine2);
        if (matcher2.matches()) {
            return parseCue(readLine.trim(), matcher2, parsableByteArray, list);
        }
        return null;
    }

    public static Cue.Builder parseCueSettingsList(String str) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        parseCueSettingsList(str, webvttCueInfoBuilder);
        return webvttCueInfoBuilder.toCueBuilder();
    }

    public static SpannedString parseCueText(String str, String str2, List<WebvttCssStyle> list) {
        boolean z;
        boolean z3;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayDeque arrayDeque = new ArrayDeque();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < str2.length()) {
            char charAt = str2.charAt(i2);
            if (charAt == '&') {
                i2++;
                int indexOf = str2.indexOf(59, i2);
                int indexOf2 = str2.indexOf(32, i2);
                if (indexOf == -1) {
                    indexOf = indexOf2;
                } else if (indexOf2 != -1) {
                    indexOf = Math.min(indexOf, indexOf2);
                }
                if (indexOf != -1) {
                    applyEntity(str2.substring(i2, indexOf), spannableStringBuilder);
                    if (indexOf == indexOf2) {
                        spannableStringBuilder.append(" ");
                    }
                    i2 = indexOf + 1;
                } else {
                    spannableStringBuilder.append(charAt);
                }
            } else if (charAt != '<') {
                spannableStringBuilder.append(charAt);
                i2++;
            } else {
                int i7 = i2 + 1;
                if (i7 < str2.length()) {
                    int i8 = 1;
                    if (str2.charAt(i7) == '/') {
                        z = true;
                    } else {
                        z = false;
                    }
                    i7 = findEndOfTag(str2, i7);
                    int i10 = i7 - 2;
                    if (str2.charAt(i10) == '/') {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z) {
                        i8 = 2;
                    }
                    int i11 = i2 + i8;
                    if (!z3) {
                        i10 = i7 - 1;
                    }
                    String substring = str2.substring(i11, i10);
                    if (!substring.trim().isEmpty()) {
                        String tagName = getTagName(substring);
                        if (isSupportedTag(tagName)) {
                            if (z) {
                                while (!arrayDeque.isEmpty()) {
                                    StartTag startTag = (StartTag) arrayDeque.pop();
                                    applySpansForTag(str, startTag, arrayList, spannableStringBuilder, list);
                                    if (!arrayDeque.isEmpty()) {
                                        arrayList.add(new Element(startTag, spannableStringBuilder.length()));
                                    } else {
                                        arrayList.clear();
                                    }
                                    if (startTag.name.equals(tagName)) {
                                        break;
                                    }
                                }
                            } else if (!z3) {
                                arrayDeque.push(StartTag.buildStartTag(substring, spannableStringBuilder.length()));
                            }
                        }
                    }
                }
                i2 = i7;
            }
        }
        while (!arrayDeque.isEmpty()) {
            applySpansForTag(str, (StartTag) arrayDeque.pop(), arrayList, spannableStringBuilder, list);
        }
        applySpansForTag(str, StartTag.buildWholeCueVirtualTag(), Collections.EMPTY_LIST, spannableStringBuilder, list);
        return SpannedString.valueOf(spannableStringBuilder);
    }

    private static int parseLineAnchor(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals("center")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1074341483:
                if (str.equals("middle")) {
                    c5 = 1;
                    break;
                }
                break;
            case 100571:
                if (str.equals("end")) {
                    c5 = 2;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 0;
            default:
                Log.w("WebvttCueParser", "Invalid anchor value: ".concat(str));
                return Integer.MIN_VALUE;
        }
    }

    private static void parseLineAttribute(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            webvttCueInfoBuilder.lineAnchor = parseLineAnchor(str.substring(indexOf + 1));
            str = str.substring(0, indexOf);
        }
        if (str.endsWith("%")) {
            webvttCueInfoBuilder.line = WebvttParserUtil.parsePercentage(str);
            webvttCueInfoBuilder.lineType = 0;
            return;
        }
        webvttCueInfoBuilder.line = (float) Integer.parseInt(str);
        webvttCueInfoBuilder.lineType = 1;
    }

    private static int parsePositionAnchor(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1842484672:
                if (str.equals("line-left")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals("center")) {
                    c5 = 1;
                    break;
                }
                break;
            case -1276788989:
                if (str.equals("line-right")) {
                    c5 = 2;
                    break;
                }
                break;
            case -1074341483:
                if (str.equals("middle")) {
                    c5 = 3;
                    break;
                }
                break;
            case 100571:
                if (str.equals("end")) {
                    c5 = 4;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    c5 = 5;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 5:
                return 0;
            case 1:
            case 3:
                return 1;
            case 2:
            case 4:
                return 2;
            default:
                Log.w("WebvttCueParser", "Invalid anchor value: ".concat(str));
                return Integer.MIN_VALUE;
        }
    }

    private static void parsePositionAttribute(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        int indexOf = str.indexOf(44);
        if (indexOf != -1) {
            webvttCueInfoBuilder.positionAnchor = parsePositionAnchor(str.substring(indexOf + 1));
            str = str.substring(0, indexOf);
        }
        webvttCueInfoBuilder.position = WebvttParserUtil.parsePercentage(str);
    }

    private static int parseTextAlignment(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals("center")) {
                    c5 = 0;
                    break;
                }
                break;
            case -1074341483:
                if (str.equals("middle")) {
                    c5 = 1;
                    break;
                }
                break;
            case 100571:
                if (str.equals("end")) {
                    c5 = 2;
                    break;
                }
                break;
            case 3317767:
                if (str.equals("left")) {
                    c5 = 3;
                    break;
                }
                break;
            case 108511772:
                if (str.equals("right")) {
                    c5 = 4;
                    break;
                }
                break;
            case 109757538:
                if (str.equals("start")) {
                    c5 = 5;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 1;
            default:
                Log.w("WebvttCueParser", "Invalid alignment value: ".concat(str));
                return 2;
        }
    }

    private static int parseVerticalAttribute(String str) {
        str.getClass();
        if (str.equals("lr")) {
            return 2;
        }
        if (str.equals("rl")) {
            return 1;
        }
        Log.w("WebvttCueParser", "Invalid 'vertical' value: ".concat(str));
        return Integer.MIN_VALUE;
    }

    private static void parseCueSettingsList(String str, WebvttCueInfoBuilder webvttCueInfoBuilder) {
        Matcher matcher = CUE_SETTING_PATTERN.matcher(str);
        while (matcher.find()) {
            String str2 = (String) Assertions.checkNotNull(matcher.group(1));
            String str3 = (String) Assertions.checkNotNull(matcher.group(2));
            try {
                if ("line".equals(str2)) {
                    parseLineAttribute(str3, webvttCueInfoBuilder);
                } else if ("align".equals(str2)) {
                    webvttCueInfoBuilder.textAlignment = parseTextAlignment(str3);
                } else if (Message.KEY_POSITION.equals(str2)) {
                    parsePositionAttribute(str3, webvttCueInfoBuilder);
                } else if ("size".equals(str2)) {
                    webvttCueInfoBuilder.size = WebvttParserUtil.parsePercentage(str3);
                } else if ("vertical".equals(str2)) {
                    webvttCueInfoBuilder.verticalType = parseVerticalAttribute(str3);
                } else {
                    Log.w("WebvttCueParser", "Unknown cue setting " + str2 + NumericEnum.SEP + str3);
                }
            } catch (NumberFormatException unused) {
                Log.w("WebvttCueParser", "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    private static WebvttCueInfo parseCue(String str, Matcher matcher, ParsableByteArray parsableByteArray, List<WebvttCssStyle> list) {
        WebvttCueInfoBuilder webvttCueInfoBuilder = new WebvttCueInfoBuilder();
        try {
            webvttCueInfoBuilder.startTimeUs = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(matcher.group(1)));
            webvttCueInfoBuilder.endTimeUs = WebvttParserUtil.parseTimestampUs((String) Assertions.checkNotNull(matcher.group(2)));
            parseCueSettingsList((String) Assertions.checkNotNull(matcher.group(3)), webvttCueInfoBuilder);
            StringBuilder sb2 = new StringBuilder();
            String readLine = parsableByteArray.readLine();
            while (!TextUtils.isEmpty(readLine)) {
                if (sb2.length() > 0) {
                    sb2.append("\n");
                }
                sb2.append(readLine.trim());
                readLine = parsableByteArray.readLine();
            }
            webvttCueInfoBuilder.text = parseCueText(str, sb2.toString(), list);
            return webvttCueInfoBuilder.build();
        } catch (IllegalArgumentException unused) {
            Log.w("WebvttCueParser", "Skipping cue with bad header: " + matcher.group());
            return null;
        }
    }
}
