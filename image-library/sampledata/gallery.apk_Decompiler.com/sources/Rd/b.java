package Rd;

import N2.t;
import Qd.a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final String f3687a = "[SCPMSDK][1.0.0803][Configuration]";
    public final Context b;

    public b(Context context) {
        this.b = context;
    }

    public final Bundle a(String str, String str2, Bundle bundle) {
        Context context = this.b;
        String str3 = this.f3687a;
        try {
            Log.i(str3, "call : Method = " + str + ", arg = " + str2);
            String string = context.getSharedPreferences("scpm.token.store", 0).getString("22n6hzkam0_token", (String) null);
            StringBuilder sb2 = new StringBuilder("call appId : 22n6hzkam0, token : ");
            sb2.append(string);
            Log.d(str3, sb2.toString());
            if (string != null) {
                bundle.putString("token", string);
            }
            bundle.putString("appId", "22n6hzkam0");
            return context.getContentResolver().call(Uri.parse("content://com.samsung.android.scpm.policy/"), str, str2, bundle);
        } catch (Throwable th) {
            Log.e(str3, "Unknown exception");
            th.printStackTrace();
            return new Bundle();
        }
    }

    public final a b() {
        Context context = this.b;
        int i2 = a.f3654a;
        String str = this.f3687a;
        Log.i(str, "initialize : 22n6hzkam0");
        try {
            Bundle bundle = new Bundle();
            bundle.putString("receiverPackageName", "com.sec.android.gallery3d");
            bundle.putString("version", context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            return a.j(a("initialize", context.getPackageName(), bundle), (ParcelFileDescriptor) null);
        } catch (Exception e) {
            a.a(str, "cannot register package : " + e.getMessage());
            return a.k(e);
        }
    }

    public final ParcelFileDescriptor c(String str) {
        Context context = this.b;
        String str2 = this.f3687a;
        Log.i(str2, "openFile : parameter = " + str);
        try {
            return context.getContentResolver().openFileDescriptor(Uri.parse("content://com.samsung.android.scpm.policy/" + context.getSharedPreferences("scpm.token.store", 0).getString("22n6hzkam0_token", (String) null) + "/" + str), "r");
        } catch (Throwable th) {
            C0212a.y(th, new StringBuilder("Unknown exception : "), str2);
            return null;
        }
    }

    public final t d() {
        Context context = this.b;
        String str = this.f3687a;
        Log.i(str, "register : 22n6hzkam0");
        try {
            Bundle bundle = new Bundle();
            bundle.putString(MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME, context.getPackageName());
            new Bundle();
            Bundle a7 = a("register", "", bundle);
            String string = a7.getString("token");
            Log.d(str, "register token : " + string);
            context.getSharedPreferences("scpm.token.store", 0).edit().putString("22n6hzkam0_token", string).apply();
            return t.b(a7);
        } catch (Exception e) {
            Log.e(str, "cannot register package : " + e.getMessage());
            return new t(2, 90000000, "There is an exception, please check  { " + e.getMessage() + "}");
        }
    }
}
