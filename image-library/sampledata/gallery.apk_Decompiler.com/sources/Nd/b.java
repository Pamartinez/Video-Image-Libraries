package Nd;

import Xd.a;
import a.C0068a;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.scsp.common.ContentType;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements a {
    public final com.samsung.context.sdk.samsunganalytics.internal.connection.a d = com.samsung.context.sdk.samsunganalytics.internal.connection.a.DATA_DELETE;
    public final String e;
    public final String f;
    public final long g;

    /* renamed from: h  reason: collision with root package name */
    public final a f3541h;

    /* renamed from: i  reason: collision with root package name */
    public HttpsURLConnection f3542i = null;

    public b(String str, String str2, long j2, a aVar) {
        this.e = str;
        this.f = str2;
        this.g = j2;
        this.f3541h = aVar;
    }

    public final void a(BufferedReader bufferedReader, InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e7) {
                C0068a.e("[Register Client] " + e7.getMessage());
                return;
            }
        }
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        HttpsURLConnection httpsURLConnection = this.f3542i;
        if (httpsURLConnection != null) {
            httpsURLConnection.disconnect();
        }
    }

    public final String b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tid", this.e);
            jSONObject.put("lid", this.f);
            jSONObject.put("ts", String.valueOf(this.g));
        } catch (JSONException e7) {
            C0068a.K("failed to make body" + e7.getMessage());
        }
        return jSONObject.toString();
    }

    public final void c(URL url, String str) {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        this.f3542i = httpsURLConnection;
        httpsURLConnection.setSSLSocketFactory(((SSLContext) Hd.a.f3418a.e).getSocketFactory());
        this.f3542i.setRequestMethod(this.d.a());
        this.f3542i.setConnectTimeout(3000);
        this.f3542i.setRequestProperty(ContentType.KEY, "application/json");
        this.f3542i.setDoOutput(true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.f3542i.getOutputStream());
        bufferedOutputStream.write(str.getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081 A[Catch:{ Exception -> 0x0068, all -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082 A[Catch:{ Exception -> 0x0068, all -> 0x0065 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b8 A[SYNTHETIC, Splitter:B:37:0x00b8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int onFinish() {
        /*
            r13 = this;
            Nd.a r0 = r13.f3541h
            java.lang.String r1 = ""
            java.lang.String r2 = "Fail : "
            java.lang.String r3 = "Success : "
            r4 = 0
            r5 = 0
            javax.net.ssl.HttpsURLConnection r6 = r13.f3542i     // Catch:{ Exception -> 0x001f, all -> 0x001b }
            int r6 = r6.getResponseCode()     // Catch:{ Exception -> 0x001f, all -> 0x001b }
            r7 = 400(0x190, float:5.6E-43)
            if (r6 < r7) goto L_0x0022
            javax.net.ssl.HttpsURLConnection r7 = r13.f3542i     // Catch:{ Exception -> 0x001f, all -> 0x001b }
            java.io.InputStream r7 = r7.getErrorStream()     // Catch:{ Exception -> 0x001f, all -> 0x001b }
            goto L_0x0028
        L_0x001b:
            r0 = move-exception
            r7 = r4
            goto L_0x00bf
        L_0x001f:
            r7 = r4
            goto L_0x00b5
        L_0x0022:
            javax.net.ssl.HttpsURLConnection r7 = r13.f3542i     // Catch:{ Exception -> 0x001f, all -> 0x001b }
            java.io.InputStream r7 = r7.getInputStream()     // Catch:{ Exception -> 0x001f, all -> 0x001b }
        L_0x0028:
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00b5 }
            java.io.InputStreamReader r9 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00b5 }
            r9.<init>(r7)     // Catch:{ Exception -> 0x00b5 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x00b5 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r9 = r8.readLine()     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r4.<init>(r9)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r9 = "rc"
            java.lang.String r4 = r4.getString(r9)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r9 = "1000"
            r10 = 200(0xc8, float:2.8E-43)
            java.lang.String r11 = " "
            if (r6 != r10) goto L_0x006a
            boolean r12 = r4.equalsIgnoreCase(r9)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            if (r12 == 0) goto L_0x006a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r2.append(r6)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r2.append(r11)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r2.append(r4)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            a.C0068a.e(r2)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            goto L_0x007f
        L_0x0065:
            r0 = move-exception
            r4 = r8
            goto L_0x00bf
        L_0x0068:
            r4 = r8
            goto L_0x00b5
        L_0x006a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r3.append(r6)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r3.append(r11)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r3.append(r4)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            a.C0068a.e(r2)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
        L_0x007f:
            if (r0 != 0) goto L_0x0082
            goto L_0x00af
        L_0x0082:
            if (r6 != r10) goto L_0x00ac
            boolean r2 = r4.equalsIgnoreCase(r9)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            if (r2 == 0) goto L_0x00ac
            D0.f r2 = r0.f3540i     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.Object r2 = r2.e     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            android.app.Application r2 = (android.app.Application) r2     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r3 = "SATerms"
            java.lang.String r3 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.D(r3)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            android.content.SharedPreferences r2 = r2.getSharedPreferences(r3, r5)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            android.content.SharedPreferences$Editor r2 = r2.edit()     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            java.lang.String r3 = r0.g     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            android.content.SharedPreferences$Editor r2 = r2.remove(r3)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r2.apply()     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r2 = 1
            r0.R(r2)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            goto L_0x00af
        L_0x00ac:
            r0.Q(r4, r1, r1)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
        L_0x00af:
            r13.a(r8, r7)
            return r5
        L_0x00b3:
            r0 = move-exception
            goto L_0x00bf
        L_0x00b5:
            if (r0 != 0) goto L_0x00b8
            goto L_0x00bb
        L_0x00b8:
            r0.Q(r1, r1, r1)     // Catch:{ all -> 0x00b3 }
        L_0x00bb:
            r13.a(r4, r7)
            return r5
        L_0x00bf:
            r13.a(r4, r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Nd.b.onFinish():int");
    }

    public final void run() {
        String str = this.e;
        try {
            Uri.Builder buildUpon = Uri.parse(this.d.b()).buildUpon();
            String valueOf = String.valueOf(System.currentTimeMillis());
            Uri.Builder appendQueryParameter = buildUpon.appendQueryParameter("tid", str).appendQueryParameter("ts", valueOf);
            appendQueryParameter.appendQueryParameter("hc", L2.a.y(str + valueOf + Od.a.f3608a));
            URL url = new URL(buildUpon.build().toString());
            String b = b();
            if (TextUtils.isEmpty(b)) {
                Log.w("SamsungAnalytics605082", "[Register Client] body is empty");
            } else {
                c(url, b);
            }
        } catch (Exception e7) {
            C0068a.e("[Register Client] " + e7.getMessage());
        }
    }
}
