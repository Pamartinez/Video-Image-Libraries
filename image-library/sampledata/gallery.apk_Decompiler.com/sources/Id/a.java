package Id;

import a.C0068a;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.context.sdk.samsunganalytics.internal.sender.b;
import com.samsung.context.sdk.samsunganalytics.internal.sender.d;
import com.samsung.scsp.common.Header;
import i.C0212a;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Xd.a {
    public static final com.samsung.context.sdk.samsunganalytics.internal.connection.a k = com.samsung.context.sdk.samsunganalytics.internal.connection.a.SEND_LOG;
    public static final com.samsung.context.sdk.samsunganalytics.internal.connection.a l = com.samsung.context.sdk.samsunganalytics.internal.connection.a.SEND_BUFFERED_LOG;
    public final String d;
    public final b e;
    public final b f;
    public final LinkedBlockingQueue g;

    /* renamed from: h  reason: collision with root package name */
    public final d f3454h;

    /* renamed from: i  reason: collision with root package name */
    public HttpsURLConnection f3455i;

    /* renamed from: j  reason: collision with root package name */
    public final Boolean f3456j;

    public a(d dVar, String str, b bVar) {
        this.f3455i = null;
        this.f3456j = Boolean.FALSE;
        this.f3454h = dVar;
        this.d = str;
        this.f = bVar;
        this.e = dVar.d;
    }

    public final void a(int i2, String str) {
        if (i2 != 200 || !str.equalsIgnoreCase("1000")) {
            boolean booleanValue = this.f3456j.booleanValue();
            b bVar = this.f;
            if (booleanValue) {
                while (true) {
                    LinkedBlockingQueue linkedBlockingQueue = this.g;
                    if (!linkedBlockingQueue.isEmpty()) {
                        d dVar = (d) linkedBlockingQueue.poll();
                        bVar.Q(C0212a.o(new StringBuilder(), dVar.b, ""), dVar.f4199c, dVar.d.a());
                    } else {
                        return;
                    }
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                d dVar2 = this.f3454h;
                bVar.Q(C0212a.o(sb2, dVar2.b, ""), dVar2.f4199c, dVar2.d.a());
            }
        }
    }

    public final void b(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e7) {
                C0068a.e("[DLS Client] " + e7.getMessage());
                return;
            }
        }
        HttpsURLConnection httpsURLConnection = this.f3455i;
        if (httpsURLConnection != null) {
            httpsURLConnection.disconnect();
        }
    }

    public final String c() {
        if (!this.f3456j.booleanValue()) {
            return this.f3454h.f4199c;
        }
        Iterator it = this.g.iterator();
        StringBuilder sb2 = new StringBuilder(((d) it.next()).f4199c);
        while (it.hasNext()) {
            sb2.append("\u000e");
            sb2.append(((d) it.next()).f4199c);
        }
        return sb2.toString();
    }

    public final void d(URL url, String str, String str2) {
        String str3;
        BufferedOutputStream bufferedOutputStream;
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        this.f3455i = httpsURLConnection;
        httpsURLConnection.setSSLSocketFactory(((SSLContext) Hd.a.f3418a.e).getSocketFactory());
        this.f3455i.setRequestMethod(str2);
        HttpsURLConnection httpsURLConnection2 = this.f3455i;
        Boolean bool = this.f3456j;
        if (bool.booleanValue()) {
            str3 = Header.GZIP;
        } else {
            str3 = "text";
        }
        httpsURLConnection2.addRequestProperty(Header.CONTENT_ENCODING, str3);
        this.f3455i.setConnectTimeout(3000);
        this.f3455i.setDoOutput(true);
        if (bool.booleanValue()) {
            bufferedOutputStream = new BufferedOutputStream(new GZIPOutputStream(this.f3455i.getOutputStream()));
        } else {
            bufferedOutputStream = new BufferedOutputStream(this.f3455i.getOutputStream());
        }
        bufferedOutputStream.write(str.getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public final int onFinish() {
        int i2;
        BufferedReader bufferedReader = null;
        try {
            int responseCode = this.f3455i.getResponseCode();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(this.f3455i.getInputStream()));
            try {
                String string = new JSONObject(bufferedReader2.readLine()).getString("rc");
                if (responseCode == 200) {
                    if (string.equalsIgnoreCase("1000")) {
                        C0068a.b("[DLS Sender] send result success : " + responseCode + " " + string);
                        i2 = 1;
                        a(responseCode, string);
                        b(bufferedReader2);
                        return i2;
                    }
                }
                C0068a.b("[DLS Sender] send result fail : " + responseCode + " " + string);
                i2 = -7;
                a(responseCode, string);
                b(bufferedReader2);
                return i2;
            } catch (Exception e7) {
                e = e7;
                bufferedReader = bufferedReader2;
                try {
                    C0068a.d("[DLS Client] Send fail.");
                    C0068a.e("[DLS Client] " + e.getMessage());
                    a(0, "");
                    b(bufferedReader);
                    return -41;
                } catch (Throwable th) {
                    th = th;
                    b(bufferedReader);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = bufferedReader2;
                b(bufferedReader);
                throw th;
            }
        } catch (Exception e8) {
            e = e8;
            C0068a.d("[DLS Client] Send fail.");
            C0068a.e("[DLS Client] " + e.getMessage());
            a(0, "");
            b(bufferedReader);
            return -41;
        }
    }

    public final void run() {
        com.samsung.context.sdk.samsunganalytics.internal.connection.a aVar;
        String str = this.d;
        try {
            if (this.f3456j.booleanValue()) {
                aVar = l;
            } else {
                aVar = k;
            }
            Uri.Builder buildUpon = Uri.parse(aVar.b()).buildUpon();
            String valueOf = String.valueOf(System.currentTimeMillis());
            Uri.Builder appendQueryParameter = buildUpon.appendQueryParameter("ts", valueOf).appendQueryParameter("type", this.e.a()).appendQueryParameter("tid", str);
            appendQueryParameter.appendQueryParameter("hc", L2.a.y(str + valueOf + Od.a.f3608a));
            URL url = new URL(buildUpon.build().toString());
            String c5 = c();
            if (TextUtils.isEmpty(c5)) {
                Log.w("SamsungAnalytics605082", "[DLS Client] body is empty");
                return;
            }
            d(url, c5, aVar.a());
            C0068a.e("[DLS Client] Send to DLS : ".concat(c5));
        } catch (Exception e7) {
            C0068a.d("[DLS Client] Send fail.");
            C0068a.e("[DLS Client] " + e7.getMessage());
        }
    }

    public a(b bVar, LinkedBlockingQueue linkedBlockingQueue, String str, b bVar2) {
        this.f3455i = null;
        this.g = linkedBlockingQueue;
        this.d = str;
        this.f = bVar2;
        this.f3456j = Boolean.TRUE;
        this.e = bVar;
    }
}
