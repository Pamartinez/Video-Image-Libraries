package U0;

import Q0.k;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class c {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f839a = Pattern.compile("[-_./;:]");

    public static k a(String str, String str2) {
        int i2;
        String str3;
        String[] split = f839a.split("2.12.7");
        int i7 = 0;
        int b = b(split[0]);
        if (split.length > 1) {
            i2 = b(split[1]);
        } else {
            i2 = 0;
        }
        if (split.length > 2) {
            i7 = b(split[2]);
        }
        if (split.length > 3) {
            str3 = split[3];
        } else {
            str3 = null;
        }
        return new k(b, i2, i7, str3, str, str2);
    }

    public static int b(String str) {
        int length = str.length();
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            char charAt = str.charAt(i7);
            if (charAt > '9' || charAt < '0') {
                break;
            }
            i2 = (i2 * 10) + (charAt - '0');
        }
        return i2;
    }
}
