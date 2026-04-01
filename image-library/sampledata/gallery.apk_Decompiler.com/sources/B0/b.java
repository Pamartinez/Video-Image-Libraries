package B0;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import java.util.Map;
import x0.C0325c;
import x0.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {
    public static final Object e = new Object();

    /* renamed from: a  reason: collision with root package name */
    public final Context f35a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public C0325c f36c;
    public final Map d;

    public b(Drawable.Callback callback, String str, C0325c cVar, Map map) {
        if (TextUtils.isEmpty(str) || str.charAt(str.length() - 1) == '/') {
            this.b = str;
        } else {
            this.b = str.concat("/");
        }
        this.d = map;
        this.f36c = cVar;
        if (!(callback instanceof View)) {
            this.f35a = null;
        } else {
            this.f35a = ((View) callback).getContext().getApplicationContext();
        }
    }

    public final void a(String str, Bitmap bitmap) {
        synchronized (e) {
            ((y) this.d.get(str)).f = bitmap;
        }
    }
}
