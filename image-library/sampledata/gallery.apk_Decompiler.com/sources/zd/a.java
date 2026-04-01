package Zd;

import D1.f;
import Yd.b;
import ae.C0902a;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Xd.a {
    public final long d = TimeUnit.HOURS.toMillis(6);
    public final Context e;
    public final b f;
    public final Bundle g;

    public a(b bVar, Bundle bundle) {
        this.e = (Context) bVar.b;
        this.f = bVar;
        this.g = bundle;
    }

    public final int onFinish() {
        return 0;
    }

    public final void run() {
        boolean z;
        boolean z3;
        Context context = this.e;
        int a7 = C0902a.a(context);
        if (a7 != 0) {
            boolean z7 = false;
            b bVar = this.f;
            if (a7 == 1) {
                Yd.a aVar = (Yd.a) bVar.f;
                Context context2 = (Context) bVar.b;
                if (TextUtils.isEmpty((String) bVar.f3915c)) {
                    f.L("Service ID has to be set");
                } else {
                    if (C0902a.a(context2) == 1) {
                        z3 = aVar.b;
                    } else {
                        z3 = bVar.f3914a;
                    }
                    if (!z3) {
                        f.L("You have to agree to terms and conditions");
                    } else {
                        z7 = true;
                    }
                }
                if (!z7) {
                    f.L("Invalid DiagMonConfiguration");
                    f.L("SetConfiguration is aborted");
                    return;
                }
                try {
                    String concat = "com.sec.android.log.".concat((String) bVar.f3915c);
                    Bundle bundle = new Bundle();
                    bundle.putString("deviceId", "");
                    if (C0902a.a(context2) == 1) {
                        z = aVar.b;
                    } else {
                        z = bVar.f3914a;
                    }
                    bundle.putBoolean("serviceAgreeType", z);
                    bundle.putString(GroupContract.Group.SERVICE_ID, concat);
                    context.getContentResolver().call(Uri.parse("content://" + concat), "service_registration", (String) null, bundle);
                } catch (Exception e7) {
                    f.L("fail to send SR obj: " + e7.getMessage());
                }
                f.A("Valid DiagMonConfiguration");
            } else if (a7 != 2) {
                f.L("Exceptional case");
                f.L("SetConfiguration is aborted");
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                long j2 = context.getSharedPreferences("diagmon_pref", 0).getLong("diagmon_timestamp", 0);
                if ((("com.samsung.diagmonagenttest".equals(context.getPackageName()) || "com.samsung.context.sdk.sampleapp".equals(context.getPackageName())) && Build.TYPE.equals("eng")) || currentTimeMillis > j2 + this.d) {
                    String str = (String) bVar.f3915c;
                    if (a7 == 2) {
                        try {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString(GroupContract.Group.SERVICE_ID, str);
                            context.getContentResolver().call(C0902a.b, "request_deviceid", "request_deviceid", bundle2);
                        } catch (Exception unused) {
                            f.L("Authority check got failed");
                            return;
                        }
                    }
                    SharedPreferences.Editor edit = context.getSharedPreferences("diagmon_pref", 0).edit();
                    edit.putLong("diagmon_timestamp", currentTimeMillis);
                    edit.apply();
                    Bundle bundle3 = this.g;
                    if (L2.a.t(bundle3)) {
                        try {
                            f.A("Request Service Registration");
                            C0902a.c(context.getContentResolver().call(C0902a.b, "register_service", "registration", bundle3));
                        } catch (Exception unused2) {
                            f.L("fail to send SR obj");
                        }
                    } else {
                        Log.w(C0902a.f3987a, "Invalid SR object");
                    }
                }
            }
        } else {
            f.L("Not installed DMA");
            f.L("SetConfiguration is aborted");
        }
    }
}
