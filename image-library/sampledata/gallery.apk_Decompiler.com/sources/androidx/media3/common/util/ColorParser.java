package androidx.media3.common.util;

import A.a;
import android.graphics.Color;
import android.text.TextUtils;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import og.k;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ColorParser {
    private static final Map<String, Integer> COLOR_MAP;
    private static final Pattern RGBA_PATTERN_FLOAT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d*\\.?\\d*?)\\)$");
    private static final Pattern RGBA_PATTERN_INT_ALPHA = Pattern.compile("^rgba\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");
    private static final Pattern RGB_PATTERN = Pattern.compile("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$");

    static {
        HashMap hashMap = new HashMap();
        COLOR_MAP = hashMap;
        a.l(-984833, hashMap, "aliceblue", -332841, "antiquewhite");
        hashMap.put("aqua", -16711681);
        hashMap.put("aquamarine", -8388652);
        a.l(-983041, hashMap, "azure", -657956, "beige");
        a.l(-6972, hashMap, "bisque", -16777216, "black");
        a.l(-5171, hashMap, "blanchedalmond", -16776961, "blue");
        a.l(-7722014, hashMap, "blueviolet", -5952982, "brown");
        a.l(-2180985, hashMap, "burlywood", -10510688, "cadetblue");
        a.l(-8388864, hashMap, "chartreuse", -2987746, "chocolate");
        a.l(-32944, hashMap, "coral", -10185235, "cornflowerblue");
        a.l(-1828, hashMap, "cornsilk", -2354116, "crimson");
        hashMap.put("cyan", -16711681);
        hashMap.put("darkblue", -16777077);
        a.l(-16741493, hashMap, "darkcyan", -4684277, "darkgoldenrod");
        hashMap.put("darkgray", -5658199);
        hashMap.put("darkgreen", -16751616);
        hashMap.put("darkgrey", -5658199);
        hashMap.put("darkkhaki", -4343957);
        a.l(-7667573, hashMap, "darkmagenta", -11179217, "darkolivegreen");
        a.l(-29696, hashMap, "darkorange", -6737204, "darkorchid");
        a.l(-7667712, hashMap, "darkred", -1468806, "darksalmon");
        a.l(-7357297, hashMap, "darkseagreen", -12042869, "darkslateblue");
        hashMap.put("darkslategray", -13676721);
        hashMap.put("darkslategrey", -13676721);
        hashMap.put("darkturquoise", -16724271);
        hashMap.put("darkviolet", -7077677);
        a.l(-60269, hashMap, "deeppink", -16728065, "deepskyblue");
        hashMap.put("dimgray", -9868951);
        hashMap.put("dimgrey", -9868951);
        hashMap.put("dodgerblue", -14774017);
        hashMap.put("firebrick", -5103070);
        a.l(-1296, hashMap, "floralwhite", -14513374, "forestgreen");
        hashMap.put("fuchsia", -65281);
        hashMap.put("gainsboro", -2302756);
        a.l(-460545, hashMap, "ghostwhite", -10496, "gold");
        hashMap.put("goldenrod", -2448096);
        hashMap.put("gray", -8355712);
        a.l(-16744448, hashMap, "green", -5374161, "greenyellow");
        hashMap.put("grey", -8355712);
        hashMap.put("honeydew", -983056);
        a.l(-38476, hashMap, "hotpink", -3318692, "indianred");
        a.l(-11861886, hashMap, "indigo", -16, "ivory");
        a.l(-989556, hashMap, "khaki", -1644806, "lavender");
        a.l(-3851, hashMap, "lavenderblush", -8586240, "lawngreen");
        a.l(-1331, hashMap, "lemonchiffon", -5383962, "lightblue");
        a.l(-1015680, hashMap, "lightcoral", -2031617, "lightcyan");
        hashMap.put("lightgoldenrodyellow", -329006);
        hashMap.put("lightgray", -2894893);
        hashMap.put("lightgreen", -7278960);
        hashMap.put("lightgrey", -2894893);
        a.l(-18751, hashMap, "lightpink", -24454, "lightsalmon");
        a.l(-14634326, hashMap, "lightseagreen", -7876870, "lightskyblue");
        hashMap.put("lightslategray", -8943463);
        hashMap.put("lightslategrey", -8943463);
        hashMap.put("lightsteelblue", -5192482);
        hashMap.put("lightyellow", -32);
        a.l(-16711936, hashMap, "lime", -13447886, "limegreen");
        hashMap.put("linen", -331546);
        hashMap.put("magenta", -65281);
        a.l(-8388608, hashMap, "maroon", -10039894, "mediumaquamarine");
        a.l(-16777011, hashMap, "mediumblue", -4565549, "mediumorchid");
        a.l(-7114533, hashMap, "mediumpurple", -12799119, "mediumseagreen");
        a.l(-8689426, hashMap, "mediumslateblue", -16713062, "mediumspringgreen");
        a.l(-12004916, hashMap, "mediumturquoise", -3730043, "mediumvioletred");
        a.l(-15132304, hashMap, "midnightblue", -655366, "mintcream");
        a.l(-6943, hashMap, "mistyrose", -6987, "moccasin");
        a.l(-8531, hashMap, "navajowhite", -16777088, "navy");
        a.l(-133658, hashMap, "oldlace", -8355840, "olive");
        a.l(-9728477, hashMap, "olivedrab", -23296, "orange");
        a.l(-47872, hashMap, "orangered", -2461482, "orchid");
        a.l(-1120086, hashMap, "palegoldenrod", -6751336, "palegreen");
        a.l(-5247250, hashMap, "paleturquoise", -2396013, "palevioletred");
        a.l(-4139, hashMap, "papayawhip", -9543, "peachpuff");
        a.l(-3308225, hashMap, "peru", -16181, "pink");
        a.l(-2252579, hashMap, "plum", -5185306, "powderblue");
        a.l(-8388480, hashMap, "purple", -10079335, "rebeccapurple");
        a.l(-65536, hashMap, "red", -4419697, "rosybrown");
        a.l(-12490271, hashMap, "royalblue", -7650029, "saddlebrown");
        a.l(-360334, hashMap, "salmon", -744352, "sandybrown");
        a.l(-13726889, hashMap, "seagreen", -2578, "seashell");
        a.l(-6270419, hashMap, "sienna", -4144960, "silver");
        a.l(-7876885, hashMap, "skyblue", -9807155, "slateblue");
        hashMap.put("slategray", -9404272);
        hashMap.put("slategrey", -9404272);
        hashMap.put("snow", -1286);
        hashMap.put("springgreen", -16711809);
        a.l(-12156236, hashMap, "steelblue", -2968436, "tan");
        a.l(-16744320, hashMap, "teal", -2572328, "thistle");
        a.l(-40121, hashMap, "tomato", 0, "transparent");
        a.l(-12525360, hashMap, "turquoise", -1146130, "violet");
        a.l(-663885, hashMap, "wheat", -1, "white");
        a.l(-657931, hashMap, "whitesmoke", -256, "yellow");
        hashMap.put("yellowgreen", -6632142);
    }

    private static int parseColorInternal(String str, boolean z) {
        Pattern pattern;
        int i2;
        Assertions.checkArgument(!TextUtils.isEmpty(str));
        String replace = str.replace(" ", "");
        if (replace.charAt(0) == '#') {
            int parseLong = (int) Long.parseLong(replace.substring(1), 16);
            if (replace.length() == 7) {
                return -16777216 | parseLong;
            }
            if (replace.length() == 9) {
                return ((parseLong & ScoverState.TYPE_NFC_SMART_COVER) << 24) | (parseLong >>> 8);
            }
            throw new IllegalArgumentException();
        }
        if (replace.startsWith("rgba")) {
            if (z) {
                pattern = RGBA_PATTERN_FLOAT_ALPHA;
            } else {
                pattern = RGBA_PATTERN_INT_ALPHA;
            }
            Matcher matcher = pattern.matcher(replace);
            if (matcher.matches()) {
                if (z) {
                    i2 = (int) (Float.parseFloat((String) Assertions.checkNotNull(matcher.group(4))) * 255.0f);
                } else {
                    i2 = Integer.parseInt((String) Assertions.checkNotNull(matcher.group(4)), 10);
                }
                return Color.argb(i2, Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(2)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher.group(3)), 10));
            }
        } else if (replace.startsWith("rgb")) {
            Matcher matcher2 = RGB_PATTERN.matcher(replace);
            if (matcher2.matches()) {
                return Color.rgb(Integer.parseInt((String) Assertions.checkNotNull(matcher2.group(1)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher2.group(2)), 10), Integer.parseInt((String) Assertions.checkNotNull(matcher2.group(3)), 10));
            }
        } else {
            Integer num = COLOR_MAP.get(k.S(replace));
            if (num != null) {
                return num.intValue();
            }
        }
        throw new IllegalArgumentException();
    }

    public static int parseCssColor(String str) {
        return parseColorInternal(str, true);
    }

    public static int parseTtmlColor(String str) {
        return parseColorInternal(str, false);
    }
}
