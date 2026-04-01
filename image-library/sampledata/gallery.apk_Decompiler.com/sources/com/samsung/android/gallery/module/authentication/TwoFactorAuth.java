package com.samsung.android.gallery.module.authentication;

import A.a;
import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TwoFactorAuth {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final TwoFactorAuth instance = new TwoFactorAuth();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Listener {
        void onActivityFinished();

        void onPageFinished();

        void onPageStarted();
    }

    public static TwoFactorAuth getInstance() {
        return LazyHolder.instance;
    }

    private void startSaClientPage(Fragment fragment) {
        try {
            fragment.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("samsungaccount://twostepverification.setup")), 2319);
        } catch (ActivityNotFoundException | IllegalStateException e) {
            j.u(e, new StringBuilder("startSaClientPage failed e="), "TwoStepVerification");
        }
    }

    private void startSaServerPage(Fragment fragment) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.settings.activity.TwoStepVerificationActivity");
            fragment.startActivity(intent);
        } catch (ActivityNotFoundException | IllegalStateException e) {
            j.u(e, new StringBuilder("startSaServerPage failed e="), "TwoStepVerification");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkVerification(boolean r8) {
        /*
            r7 = this;
            java.lang.String r7 = "TwoStepVerification"
            r0 = 301(0x12d, float:4.22E-43)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            com.samsung.android.gallery.module.authentication.TwoFactorAuthStatusReqInfo r1 = com.samsung.android.gallery.module.authentication.TwoFactorAuthStatusReqInfo.getInstance()
            boolean r8 = r1.refreshAccessToken(r8)
            java.lang.String r1 = "global://setting/twoStepVerification"
            if (r8 != 0) goto L_0x001c
            com.samsung.android.gallery.support.blackboard.Blackboard r7 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()
            r7.post(r1, r0)
            return
        L_0x001c:
            com.samsung.android.gallery.module.authentication.TwoFactorAuthStatusReqInfo r8 = com.samsung.android.gallery.module.authentication.TwoFactorAuthStatusReqInfo.getInstance()
            java.lang.String r8 = r8.getUrl()
            com.samsung.android.gallery.module.authentication.TwoFactorAuthStatusReqInfo r2 = com.samsung.android.gallery.module.authentication.TwoFactorAuthStatusReqInfo.getInstance()
            java.util.Map r2 = r2.getHeaderMap()
            long r3 = java.lang.System.currentTimeMillis()
            r5 = 0
            java.net.URL r6 = new java.net.URL     // Catch:{ IOException -> 0x0088 }
            r6.<init>(r8)     // Catch:{ IOException -> 0x0088 }
            java.net.URLConnection r8 = r6.openConnection()     // Catch:{ IOException -> 0x0088 }
            java.net.HttpURLConnection r8 = (java.net.HttpURLConnection) r8     // Catch:{ IOException -> 0x0088 }
            java.lang.String r5 = "GET"
            r8.setRequestMethod(r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r5 = 30000(0x7530, float:4.2039E-41)
            r8.setConnectTimeout(r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r8.setReadTimeout(r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.util.Set r2 = r2.entrySet()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
        L_0x0051:
            boolean r5 = r2.hasNext()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            if (r5 == 0) goto L_0x0073
            java.lang.Object r5 = r2.next()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.lang.Object r6 = r5.getKey()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.lang.Object r5 = r5.getValue()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r8.setRequestProperty(r6, r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            goto L_0x0051
        L_0x006d:
            r7 = move-exception
            r5 = r8
            goto L_0x00b8
        L_0x0070:
            r2 = move-exception
            r5 = r8
            goto L_0x0089
        L_0x0073:
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            int r5 = r8.getResponseCode()     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r2.post(r1, r5)     // Catch:{ IOException -> 0x0070, all -> 0x006d }
            r8.disconnect()
            goto L_0x00ad
        L_0x0086:
            r7 = move-exception
            goto L_0x00b8
        L_0x0088:
            r2 = move-exception
        L_0x0089:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r8.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r6 = "checkVerification failed. e="
            r8.append(r6)     // Catch:{ all -> 0x0086 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0086 }
            r8.append(r2)     // Catch:{ all -> 0x0086 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0086 }
            com.samsung.android.gallery.support.utils.Log.e(r7, r8)     // Catch:{ all -> 0x0086 }
            com.samsung.android.gallery.support.blackboard.Blackboard r8 = com.samsung.android.gallery.support.blackboard.Blackboard.getApplicationInstance()     // Catch:{ all -> 0x0086 }
            r8.post(r1, r0)     // Catch:{ all -> 0x0086 }
            if (r5 == 0) goto L_0x00ad
            r5.disconnect()
        L_0x00ad:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = " checkVerification httpResponse +"
            r8.<init>(r0)
            A.a.x(r8, r3, r7)
            return
        L_0x00b8:
            if (r5 == 0) goto L_0x00bd
            r5.disconnect()
        L_0x00bd:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.authentication.TwoFactorAuth.checkVerification(boolean):void");
    }

    public String queryStatus() {
        Cursor query;
        Throwable th;
        String str;
        try {
            query = AppResources.getAppContext().getContentResolver().query(Uri.parse("content://com.msc.openprovider.openContentProvider/openData"), new String[]{"Value"}, "Key=?", new String[]{"two_step_verification_is_on"}, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    if (Boolean.TRUE.toString().equalsIgnoreCase(string)) {
                        str = "on";
                    } else if (Boolean.FALSE.toString().equalsIgnoreCase(string)) {
                        str = "off";
                    }
                    query.close();
                    return str;
                }
            }
            if (query == null) {
                return "not_supported";
            }
            query.close();
            return "not_supported";
        } catch (Exception e) {
            a.s(e, new StringBuilder("getStatus failed e="), "TwoStepVerification");
            return "not_supported";
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void startVerification(Fragment fragment, String str) {
        if ("off".equalsIgnoreCase(str)) {
            startSaClientPage(fragment);
        } else if ("not_supported".equalsIgnoreCase(str)) {
            startSaServerPage(fragment);
        } else {
            Log.w("TwoStepVerification", "startVerification failed status=" + str);
        }
    }
}
