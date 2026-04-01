package X2;

import D0.e;
import com.samsung.android.gallery.support.utils.MapUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o extends u {
    public static final Pattern f = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

    public final r e(e eVar) {
        Matcher matcher = f.matcher(u.a(eVar));
        if (!matcher.matches()) {
            return null;
        }
        String group = matcher.group(4);
        try {
            double parseDouble = Double.parseDouble(matcher.group(1));
            if (parseDouble > 90.0d) {
                return null;
            }
            if (parseDouble < -90.0d) {
                return null;
            }
            double parseDouble2 = Double.parseDouble(matcher.group(2));
            if (parseDouble2 > 180.0d) {
                return null;
            }
            if (parseDouble2 < -180.0d) {
                return null;
            }
            String group2 = matcher.group(3);
            double d = MapUtil.INVALID_LOCATION;
            if (group2 != null) {
                double parseDouble3 = Double.parseDouble(matcher.group(3));
                if (parseDouble3 < MapUtil.INVALID_LOCATION) {
                    return null;
                }
                d = parseDouble3;
            }
            return new n(parseDouble, parseDouble2, d, group);
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}
