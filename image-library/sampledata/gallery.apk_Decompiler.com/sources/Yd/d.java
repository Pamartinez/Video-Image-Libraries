package Yd;

import D1.f;
import Dd.C0731b;
import a.C0068a;
import ae.C0902a;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class d {

    /* renamed from: a  reason: collision with root package name */
    public static b f3916a;
    public static Bundle b;

    /* renamed from: c  reason: collision with root package name */
    public static c f3917c = c.NONE;

    public static Bundle a(b bVar) {
        String str;
        Bundle bundle = new Bundle();
        bundle.putString(GroupContract.Group.SERVICE_ID, (String) bVar.f3915c);
        Context context = (Context) bVar.b;
        bundle.putString("serviceVersion", C0068a.G(context));
        if (C0902a.a(context) == 1) {
            str = ((a) bVar.f).f3913a;
        } else {
            str = (String) bVar.e;
        }
        bundle.putString("serviceAgreeType", str);
        String str2 = "";
        bundle.putString("deviceId", str2);
        bundle.putString("trackingId", str2);
        try {
            str2 = String.valueOf(C0731b.f3330a);
        } catch (Exception unused) {
        }
        bundle.putString("sdkVersion", str2);
        bundle.putString("sdkType", "S");
        bundle.putString("pkgName", context.getPackageName());
        bundle.putBoolean("wifiOnly", true);
        f.A("generated SR object");
        return bundle;
    }
}
