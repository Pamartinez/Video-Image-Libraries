package Qd;

import android.os.Build;
import android.util.Log;
import i.C0212a;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f3654a = 0;

    static {
        "eng".equals(Build.TYPE);
    }

    public static void a(String str, String str2) {
        Locale locale = Locale.US;
        String B = C0212a.B(str2, " ", "");
        Log.e("[E]" + str, B);
    }
}
