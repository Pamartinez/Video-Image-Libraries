package pc;

import com.samsung.android.util.SemLog;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {
    public static void a(String str, String str2) {
        j.e(str2, "msg");
        try {
            SemLog.d("SamSearch_".concat(str), str2);
        } catch (Exception unused) {
        }
    }

    public static void b(Exception exc, String str) {
        try {
            SemLog.e("SamSearch_".concat("SamsungSearchProvider"), str, exc);
        } catch (Exception unused) {
        }
    }
}
