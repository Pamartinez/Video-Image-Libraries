package Y2;

import D0.e;
import X2.r;
import X2.u;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends u {
    public static final Pattern f = Pattern.compile("^(Z:)\\p{Alnum}+(\\$I:)\\p{Alnum}+(\\%SN:)\\p{Alnum}{5}(A)[1-5]{1}\\p{Alnum}{7}$");
    public static final Pattern g = Pattern.compile("^(SN:)[0-9]{1}(0)[0-9]{1}(1)[0-9]{6}(\\%E:)\\p{Alnum}{12}");

    /* renamed from: h  reason: collision with root package name */
    public static final Pattern f955h = Pattern.compile(".*Z[:\\$]{1}.*(\\$I:)\\p{XDigit}{18}.*");

    /* renamed from: i  reason: collision with root package name */
    public static final Pattern f956i = Pattern.compile(".*Z(\\$I:)\\p{XDigit}{18}.*");

    /* renamed from: j  reason: collision with root package name */
    public static final Pattern f957j = Pattern.compile("^(zws2dsk:){1}(\\p{Digit}{5}-){7}\\p{Digit}{5}$");
    public static final Pattern k = Pattern.compile("^(90)\\p{Digit}{88,}");
    public static final Pattern l = Pattern.compile("[\r\n]");
    public static final String[] m = {"https://qr.samsungiots", "https://qrd.samsungiots"};

    public final r e(e eVar) {
        String str = (String) eVar.e;
        if (str == null || str.length() == 0) {
            return null;
        }
        String replaceAll = l.matcher(str).replaceAll("");
        if (f.matcher(replaceAll).matches()) {
            return new a(replaceAll, c.SAMJIN_SENSOR);
        }
        if (g.matcher(replaceAll).matches()) {
            return new a(replaceAll, c.SAMJIN_HUB_V3);
        }
        if (f957j.matcher(replaceAll).matches()) {
            return new a(replaceAll, c.ZWAVE_V2_SPEC_OLD);
        }
        if (k.matcher(replaceAll).matches()) {
            return new a(replaceAll, c.ZWAVE_V2_SPEC_NEW);
        }
        if (f955h.matcher(replaceAll).matches() || f956i.matcher(replaceAll).matches()) {
            return new a(replaceAll, c.ZIGBEE_V3);
        }
        if (!replaceAll.startsWith("(MN)") ? !(!replaceAll.startsWith("(SN)") || !replaceAll.contains("(MAC)") || !replaceAll.contains("(PIN)")) : replaceAll.contains("(SN)")) {
            return new a(replaceAll, c.ST_Camera);
        }
        String[] strArr = m;
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str2 = strArr[i2];
            if (!replaceAll.contains(str2 + ".cn")) {
                if (!replaceAll.contains(str2 + ".com")) {
                    i2++;
                }
            }
            return new a(replaceAll, c.ON_BOARDING_STANDARD_QR);
        }
        if (replaceAll.length() != 0 && replaceAll.contains("https://spotted.smartthings.com")) {
            return new a(replaceAll, c.SMART_TAG_QR);
        }
        if (replaceAll.startsWith("https://www.smartthings.com/applink")) {
            return new a(replaceAll, c.APP_LINK_QR);
        }
        if (replaceAll.startsWith("MT:")) {
            return new a(replaceAll, c.MATTER_QR);
        }
        return null;
    }
}
