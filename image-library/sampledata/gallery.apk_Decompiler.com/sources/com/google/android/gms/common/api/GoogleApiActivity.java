package com.google.android.gms.common.api;

import E1.e;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.query.EngramQueryOptionBundleWrapper;
import t1.C0276a;
import v1.C0298c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    public static final /* synthetic */ int e = 0;
    public int d = 0;

    public final void onActivityResult(int i2, int i7, Intent intent) {
        super.onActivityResult(i2, i7, intent);
        if (i2 == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.d = 0;
            setResult(i7, intent);
            if (booleanExtra) {
                C0298c e7 = C0298c.e(this);
                if (i7 == -1) {
                    e eVar = e7.m;
                    eVar.sendMessage(eVar.obtainMessage(3));
                } else if (i7 == 0) {
                    e7.f(new C0276a(13, (PendingIntent) null), getIntent().getIntExtra("failing_client_id", -1));
                }
            }
        } else if (i2 == 2) {
            this.d = 0;
            setResult(i7, intent);
        }
        finish();
    }

    public final void onCancel(DialogInterface dialogInterface) {
        this.d = 0;
        setResult(0);
        finish();
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0090  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r12) {
        /*
            r11 = this;
            super.onCreate(r12)
            if (r12 == 0) goto L_0x000d
            java.lang.String r0 = "resolution"
            int r12 = r12.getInt(r0)
            r11.d = r12
        L_0x000d:
            int r12 = r11.d
            r1 = 1
            if (r12 == r1) goto L_0x00c4
            android.content.Intent r12 = r11.getIntent()
            android.os.Bundle r12 = r12.getExtras()
            java.lang.String r2 = "GoogleApiActivity"
            if (r12 != 0) goto L_0x0027
            java.lang.String r12 = "Activity started without extras"
            android.util.Log.e(r2, r12)
            r11.finish()
            return
        L_0x0027:
            java.lang.String r0 = "pending_intent"
            java.lang.Object r0 = r12.get(r0)
            r3 = r0
            android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            java.lang.String r0 = "error_code"
            java.lang.Object r0 = r12.get(r0)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r3 != 0) goto L_0x0046
            if (r0 == 0) goto L_0x003d
            goto L_0x0046
        L_0x003d:
            java.lang.String r12 = "Activity started without resolution"
            android.util.Log.e(r2, r12)
            r11.finish()
            return
        L_0x0046:
            if (r3 == 0) goto L_0x00b5
            android.content.IntentSender r5 = r3.getIntentSender()     // Catch:{ ActivityNotFoundException -> 0x006a, SendIntentException -> 0x005e }
            r9 = 0
            r10 = 0
            r6 = 1
            r7 = 0
            r8 = 0
            r4 = r11
            r4.startIntentSenderForResult(r5, r6, r7, r8, r9, r10)     // Catch:{ ActivityNotFoundException -> 0x005b, SendIntentException -> 0x0058 }
            r4.d = r1     // Catch:{ ActivityNotFoundException -> 0x005b, SendIntentException -> 0x0058 }
            return
        L_0x0058:
            r0 = move-exception
        L_0x0059:
            r11 = r0
            goto L_0x0061
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            r11 = r0
            goto L_0x006d
        L_0x005e:
            r0 = move-exception
            r4 = r11
            goto L_0x0059
        L_0x0061:
            java.lang.String r12 = "Failed to launch pendingIntent"
            android.util.Log.e(r2, r12, r11)
            r4.finish()
            goto L_0x00c4
        L_0x006a:
            r0 = move-exception
            r4 = r11
            goto L_0x005c
        L_0x006d:
            java.lang.String r0 = "notify_manager"
            boolean r12 = r12.getBoolean(r0, r1)
            if (r12 == 0) goto L_0x0090
            v1.c r11 = v1.C0298c.e(r4)
            t1.a r12 = new t1.a
            r0 = 22
            r2 = 0
            r12.<init>(r0, r2)
            android.content.Intent r0 = r4.getIntent()
            java.lang.String r2 = "failing_client_id"
            r3 = -1
            int r0 = r0.getIntExtra(r2, r3)
            r11.f(r12, r0)
            goto L_0x00af
        L_0x0090:
            java.lang.String r12 = r3.toString()
            java.lang.String r0 = "Activity not found while launching "
            java.lang.String r3 = "."
            java.lang.String r12 = i.C0212a.m(r0, r12, r3)
            java.lang.String r0 = android.os.Build.FINGERPRINT
            java.lang.String r3 = "generic"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x00ac
            java.lang.String r0 = " This may occur when resolving Google Play services connection issues on emulators with Google APIs but not Google Play Store."
            java.lang.String r12 = r12.concat(r0)
        L_0x00ac:
            android.util.Log.e(r2, r12, r11)
        L_0x00af:
            r4.d = r1
            r4.finish()
            goto L_0x00c4
        L_0x00b5:
            r4 = r11
            w1.r.b(r0)
            int r11 = r0.intValue()
            t1.d r12 = t1.C0279d.d
            r12.c(r4, r11, r4)
            r4.d = r1
        L_0x00c4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApiActivity.onCreate(android.os.Bundle):void");
    }

    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(EngramQueryOptionBundleWrapper.BUNDLE_KEY_RESOLUTION, this.d);
        super.onSaveInstanceState(bundle);
    }
}
