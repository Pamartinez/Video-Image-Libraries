package t1;

import B1.a;
import B1.b;
import B1.c;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1923a = 12451000;
    public static final f b = new Object();

    /* JADX WARNING: type inference failed for: r0v2, types: [t1.f, java.lang.Object] */
    static {
        AtomicBoolean atomicBoolean = h.f1924a;
    }

    public Intent a(Context context, int i2, String str) {
        if (i2 == 1 || i2 == 2) {
            if (context != null && a.H(context)) {
                return C0280e.a("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION", "com.google.android.wearable.app");
            }
            StringBuilder sb2 = new StringBuilder("gcore_");
            sb2.append(f1923a);
            sb2.append("-");
            if (!TextUtils.isEmpty(str)) {
                sb2.append(str);
            }
            sb2.append("-");
            if (context != null) {
                sb2.append(context.getPackageName());
            }
            sb2.append("-");
            if (context != null) {
                try {
                    b a7 = c.a(context);
                    sb2.append(((Context) a7.e).getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            String sb3 = sb2.toString();
            Intent intent = new Intent("android.intent.action.VIEW");
            Uri.Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.gms");
            if (!TextUtils.isEmpty(sb3)) {
                appendQueryParameter.appendQueryParameter("pcampaignid", sb3);
            }
            intent.setData(appendQueryParameter.build());
            intent.setPackage("com.android.vending");
            intent.addFlags(524288);
            return intent;
        } else if (i2 != 3) {
            return null;
        } else {
            Uri fromParts = Uri.fromParts("package", "com.google.android.gms", (String) null);
            Intent intent2 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent2.setData(fromParts);
            return intent2;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int b(android.content.Context r4, int r5) {
        /*
            r3 = this;
            int r3 = t1.h.a(r4, r5)
            r5 = 1
            r0 = 18
            if (r3 != r0) goto L_0x000a
            goto L_0x0047
        L_0x000a:
            if (r3 != r5) goto L_0x0046
            android.content.pm.PackageManager r5 = r4.getPackageManager()     // Catch:{ Exception -> 0x0044 }
            android.content.pm.PackageInstaller r5 = r5.getPackageInstaller()     // Catch:{ Exception -> 0x0044 }
            java.util.List r5 = r5.getAllSessions()     // Catch:{ Exception -> 0x0044 }
            java.util.Iterator r5 = r5.iterator()
        L_0x001c:
            boolean r1 = r5.hasNext()
            java.lang.String r2 = "com.google.android.gms"
            if (r1 == 0) goto L_0x0037
            java.lang.Object r1 = r5.next()
            android.content.pm.PackageInstaller$SessionInfo r1 = (android.content.pm.PackageInstaller.SessionInfo) r1
            java.lang.String r1 = r1.getAppPackageName()
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x001c
            r4 = 1
        L_0x0035:
            r5 = r4
            goto L_0x0047
        L_0x0037:
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r5 = 8192(0x2000, float:1.14794E-41)
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo(r2, r5)     // Catch:{  }
            boolean r4 = r4.enabled     // Catch:{  }
            goto L_0x0035
        L_0x0044:
            r4 = 0
            goto L_0x0035
        L_0x0046:
            r5 = 0
        L_0x0047:
            if (r5 == 0) goto L_0x004a
            return r0
        L_0x004a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: t1.f.b(android.content.Context, int):int");
    }
}
