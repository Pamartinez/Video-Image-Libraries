package Le;

import Ae.b;
import java.util.Arrays;
import java.util.Map;
import kotlin.jvm.internal.j;

public final class e implements b {
    public static final e d = new Object();

    public final Object invoke(Object obj) {
        String str;
        Map.Entry entry = (Map.Entry) obj;
        j.e(entry, "entry");
        String str2 = (String) entry.getKey();
        Object value = entry.getValue();
        if (value instanceof boolean[]) {
            str = Arrays.toString((boolean[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof char[]) {
            str = Arrays.toString((char[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof byte[]) {
            str = Arrays.toString((byte[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof short[]) {
            str = Arrays.toString((short[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof int[]) {
            str = Arrays.toString((int[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof float[]) {
            str = Arrays.toString((float[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof long[]) {
            str = Arrays.toString((long[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof double[]) {
            str = Arrays.toString((double[]) value);
            j.d(str, "toString(...)");
        } else if (value instanceof Object[]) {
            str = Arrays.toString((Object[]) value);
            j.d(str, "toString(...)");
        } else {
            str = value.toString();
        }
        return str2 + '=' + str;
    }
}
