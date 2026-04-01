package B0;

import A0.l;
import D0.b;
import S1.u;
import S1.v;
import a.C0068a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Property;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import androidx.appcompat.R$interpolator;
import cf.C0922a;
import cf.e;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.context.sdk.samsunganalytics.internal.connection.c;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import jf.C1105e;
import jf.o;
import jf.p;
import kotlin.jvm.internal.j;
import me.f;
import ne.C1194l;
import org.json.JSONException;
import org.json.JSONObject;
import qf.C1235b;
import qf.C1240g;
import vf.C1321a;
import vf.C1326f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Xd.a, o {
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;

    /* renamed from: h  reason: collision with root package name */
    public Object f34h;

    public /* synthetic */ a(D0.a aVar, Bf.a aVar2, b bVar, b bVar2, Bf.a aVar3) {
        this.d = aVar;
        this.e = aVar2;
        this.f = bVar;
        this.g = bVar2;
        this.f34h = aVar3;
    }

    public void I(C1240g gVar, C1326f fVar) {
        ((C1105e) this.d).I(gVar, fVar);
    }

    public void a(JSONObject jSONObject) {
        try {
            SharedPreferences.Editor putInt = ((SharedPreferences) this.f).edit().putInt("oq-3g", jSONObject.getInt("oq-3g") * 1024).putInt("dq-3g", jSONObject.getInt("dq-3g") * 1024).putInt("oq-w", jSONObject.getInt("oq-w") * 1024).putInt("dq-w", jSONObject.getInt("dq-w") * 1024);
            putInt.putString("dom", "https://" + jSONObject.getString("dom")).putString(OCRServiceConstant.KEY_PARAM_URI, jSONObject.getString(OCRServiceConstant.KEY_PARAM_URI)).putString("bat-uri", jSONObject.getString("bat-uri")).putString("lgt", jSONObject.getString("lgt")).putInt("rint", jSONObject.getInt("rint")).putLong("policy_received_date", System.currentTimeMillis()).apply();
            c cVar = c.DLS;
            cVar.a("https://" + jSONObject.getString("dom"));
            com.samsung.context.sdk.samsunganalytics.internal.connection.b.DLS_DIR.a(jSONObject.getString(OCRServiceConstant.KEY_PARAM_URI));
            com.samsung.context.sdk.samsunganalytics.internal.connection.b.DLS_DIR_BAT.a(jSONObject.getString("bat-uri"));
            C0068a.e("dq-3g: " + (jSONObject.getInt("dq-3g") * 1024) + ", dq-w: " + (jSONObject.getInt("dq-w") * 1024) + ", oq-3g: " + (jSONObject.getInt("oq-3g") * 1024) + ", oq-w: " + (jSONObject.getInt("oq-w") * 1024));
        } catch (JSONException e7) {
            C0068a.J("Fail to save policy" + e7.getMessage());
            C0068a.e("[GetPolicyClient] " + e7.getMessage());
        }
    }

    public void n() {
        ((C1105e) this.e).n();
        C1321a aVar = new C1321a((Re.b) C1194l.b1((ArrayList) this.f34h));
        ((C1105e) this.f).e.put((C1240g) this.g, aVar);
    }

    public p o(C1240g gVar) {
        return ((C1105e) this.d).o(gVar);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00eb A[SYNTHETIC, Splitter:B:40:0x00eb] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00f4 A[Catch:{ IOException -> 0x00f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0118 A[SYNTHETIC, Splitter:B:54:0x0118] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0121 A[Catch:{ IOException -> 0x0124 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onFinish() {
        /*
            r14 = this;
            java.lang.String r0 = "policy_received_date"
            java.lang.Object r1 = r14.g
            Ed.a r1 = (Ed.a) r1
            java.lang.String r2 = ""
            java.lang.String r3 = "lgt"
            java.lang.Object r4 = r14.f
            android.content.SharedPreferences r4 = (android.content.SharedPreferences) r4
            java.lang.String r5 = "Fail to get Policy; Invalid Message. Result code : "
            java.lang.String r6 = "Fail to get Policy. Response code : "
            r7 = -61
            r8 = 0
            java.lang.Object r9 = r14.f34h     // Catch:{ Exception -> 0x00e4 }
            javax.net.ssl.HttpsURLConnection r9 = (javax.net.ssl.HttpsURLConnection) r9     // Catch:{ Exception -> 0x00e4 }
            int r9 = r9.getResponseCode()     // Catch:{ Exception -> 0x00e4 }
            r10 = 200(0xc8, float:2.8E-43)
            r11 = 0
            if (r9 == r10) goto L_0x003e
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00e4 }
            r9.<init>(r6)     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r6 = r14.f34h     // Catch:{ Exception -> 0x00e4 }
            javax.net.ssl.HttpsURLConnection r6 = (javax.net.ssl.HttpsURLConnection) r6     // Catch:{ Exception -> 0x00e4 }
            int r6 = r6.getResponseCode()     // Catch:{ Exception -> 0x00e4 }
            r9.append(r6)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r6 = r9.toString()     // Catch:{ Exception -> 0x00e4 }
            a.C0068a.d(r6)     // Catch:{ Exception -> 0x00e4 }
            r6 = r7
            goto L_0x003f
        L_0x003b:
            r0 = move-exception
            goto L_0x0116
        L_0x003e:
            r6 = r11
        L_0x003f:
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00e4 }
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00e4 }
            java.lang.Object r12 = r14.f34h     // Catch:{ Exception -> 0x00e4 }
            javax.net.ssl.HttpsURLConnection r12 = (javax.net.ssl.HttpsURLConnection) r12     // Catch:{ Exception -> 0x00e4 }
            java.io.InputStream r12 = r12.getInputStream()     // Catch:{ Exception -> 0x00e4 }
            r10.<init>(r12)     // Catch:{ Exception -> 0x00e4 }
            r9.<init>(r10)     // Catch:{ Exception -> 0x00e4 }
            java.lang.String r8 = r9.readLine()     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            a.C0068a.e(r8)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r10.<init>(r8)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r8 = "rc"
            int r8 = r10.getInt(r8)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r12 = 1000(0x3e8, float:1.401E-42)
            java.lang.String r13 = "GetPolicyClient"
            if (r8 != r12) goto L_0x0096
            java.lang.String r5 = "Get Policy Success"
            a.C0068a.c(r13, r5)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r5 = r4.getString(r3, r2)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            if (r5 == 0) goto L_0x0092
            if (r1 == 0) goto L_0x0092
            java.lang.String r3 = r10.getString(r3)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r5 = "rtb"
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            if (r3 == 0) goto L_0x0092
            java.lang.Boolean r3 = java.lang.Boolean.TRUE     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r1.onResult(r3)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            goto L_0x0092
        L_0x008c:
            r0 = move-exception
            r8 = r9
            goto L_0x0116
        L_0x0090:
            r8 = r9
            goto L_0x00e4
        L_0x0092:
            r14.a(r10)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            goto L_0x00d7
        L_0x0096:
            r1 = 2002(0x7d2, float:2.805E-42)
            if (r8 != r1) goto L_0x00c7
            java.lang.String r1 = "Result code : 2002, quota should be changed to zero"
            a.C0068a.c(r13, r1)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            android.content.SharedPreferences$Editor r1 = r4.edit()     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r3 = "oq-3g"
            android.content.SharedPreferences$Editor r1 = r1.putInt(r3, r11)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r3 = "dq-3g"
            android.content.SharedPreferences$Editor r1 = r1.putInt(r3, r11)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r3 = "oq-w"
            android.content.SharedPreferences$Editor r1 = r1.putInt(r3, r11)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r3 = "dq-w"
            android.content.SharedPreferences$Editor r1 = r1.putInt(r3, r11)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            android.content.SharedPreferences$Editor r1 = r1.putLong(r0, r10)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r1.apply()     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            goto L_0x00d7
        L_0x00c7:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r1.append(r8)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            a.C0068a.J(r1)     // Catch:{ Exception -> 0x0090, all -> 0x008c }
            r6 = r7
        L_0x00d7:
            r9.close()     // Catch:{ IOException -> 0x00f8 }
            java.lang.Object r14 = r14.f34h     // Catch:{ IOException -> 0x00f8 }
            javax.net.ssl.HttpsURLConnection r14 = (javax.net.ssl.HttpsURLConnection) r14     // Catch:{ IOException -> 0x00f8 }
            if (r14 == 0) goto L_0x00f8
            r14.disconnect()     // Catch:{ IOException -> 0x00f8 }
            goto L_0x00f8
        L_0x00e4:
            java.lang.String r1 = "Fail to get Policy"
            a.C0068a.d(r1)     // Catch:{ all -> 0x003b }
            if (r8 == 0) goto L_0x00ee
            r8.close()     // Catch:{ IOException -> 0x00f7 }
        L_0x00ee:
            java.lang.Object r14 = r14.f34h     // Catch:{ IOException -> 0x00f7 }
            javax.net.ssl.HttpsURLConnection r14 = (javax.net.ssl.HttpsURLConnection) r14     // Catch:{ IOException -> 0x00f7 }
            if (r14 == 0) goto L_0x00f7
            r14.disconnect()     // Catch:{ IOException -> 0x00f7 }
        L_0x00f7:
            r6 = r7
        L_0x00f8:
            java.lang.String r14 = "dom"
            java.lang.String r14 = r4.getString(r14, r2)
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r6 != r7) goto L_0x0115
            if (r14 != 0) goto L_0x0115
            android.content.SharedPreferences$Editor r14 = r4.edit()
            long r1 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r14 = r14.putLong(r0, r1)
            r14.apply()
        L_0x0115:
            return r6
        L_0x0116:
            if (r8 == 0) goto L_0x011b
            r8.close()     // Catch:{ IOException -> 0x0124 }
        L_0x011b:
            java.lang.Object r14 = r14.f34h     // Catch:{ IOException -> 0x0124 }
            javax.net.ssl.HttpsURLConnection r14 = (javax.net.ssl.HttpsURLConnection) r14     // Catch:{ IOException -> 0x0124 }
            if (r14 == 0) goto L_0x0124
            r14.disconnect()     // Catch:{ IOException -> 0x0124 }
        L_0x0124:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: B0.a.onFinish():int");
    }

    public o p(C1235b bVar, C1240g gVar) {
        return ((C1105e) this.d).p(bVar, gVar);
    }

    public void r(C1240g gVar, C1235b bVar, C1240g gVar2) {
        ((C1105e) this.d).r(gVar, bVar, gVar2);
    }

    public void run() {
        HashMap hashMap = (HashMap) this.e;
        com.samsung.context.sdk.samsunganalytics.internal.connection.a aVar = (com.samsung.context.sdk.samsunganalytics.internal.connection.a) this.d;
        try {
            Uri.Builder buildUpon = Uri.parse(aVar.b()).buildUpon();
            for (String str : hashMap.keySet()) {
                buildUpon.appendQueryParameter(str, (String) hashMap.get(str));
            }
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(buildUpon.build().toString()).openConnection();
            this.f34h = httpsURLConnection;
            httpsURLConnection.setSSLSocketFactory(((SSLContext) Hd.a.f3418a.e).getSocketFactory());
            ((HttpsURLConnection) this.f34h).setRequestMethod(aVar.a());
            ((HttpsURLConnection) this.f34h).setConnectTimeout(3000);
        } catch (Exception unused) {
            C0068a.d("Fail to get Policy");
        }
    }

    public void s(C1240g gVar, Object obj) {
        ((C1105e) this.d).s(gVar, obj);
    }

    public a(Map map, Map map2, Map map3, Map map4, Map map5) {
        j.e(map, "class2ContextualFactory");
        j.e(map2, "polyBase2Serializers");
        j.e(map3, "polyBase2DefaultSerializerProvider");
        j.e(map4, "polyBase2NamedSerializers");
        j.e(map5, "polyBase2DefaultDeserializerProvider");
        this.d = map;
        this.e = map2;
        this.f = map3;
        this.g = map4;
        this.f34h = map5;
    }

    public a(FrameLayout frameLayout) {
        this.d = frameLayout;
        this.e = new Stack();
        Interpolator loadInterpolator = AnimationUtils.loadInterpolator(frameLayout.getContext(), R$interpolator.sesl_interpolator_0_0_1_1);
        Property property = View.ALPHA;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object) null, property, new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(loadInterpolator);
        ofFloat.setDuration(200);
        ofFloat.setStartDelay(100);
        ofFloat.addListener(new v(0, ofFloat));
        this.f = ofFloat;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat((Object) null, property, new float[]{1.0f, 0.0f});
        ofFloat2.setInterpolator(loadInterpolator);
        ofFloat2.setDuration(100);
        ofFloat2.addListener(new u(ofFloat2, this));
        this.g = ofFloat2;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat2, ofFloat});
        this.f34h = animatorSet;
    }

    public a(Drawable.Callback callback) {
        this.d = new C0.j(0);
        this.e = new HashMap();
        this.f = new HashMap();
        this.f34h = ".ttf";
        if (!(callback instanceof View)) {
            J0.b.b("LottieDrawable must be inside of a view for images to work.");
            this.g = null;
            return;
        }
        this.g = ((View) callback).getContext().getAssets();
    }

    public a(com.samsung.context.sdk.samsunganalytics.internal.connection.a aVar, HashMap hashMap, SharedPreferences sharedPreferences, Ed.a aVar2) {
        this.f34h = null;
        this.d = aVar;
        this.e = hashMap;
        this.f = sharedPreferences;
        this.g = aVar2;
    }

    public a() {
        this.e = new HashMap();
        this.f = new HashMap();
        this.d = new HashMap();
        this.g = new HashMap();
        this.f34h = new HashMap();
    }

    public a(C0922a aVar, e eVar, f fVar) {
        j.e(eVar, "typeParameterResolver");
        this.d = aVar;
        this.e = eVar;
        this.f = fVar;
        this.g = fVar;
        this.f34h = new l(this, eVar);
    }

    public a(C1105e eVar, C1105e eVar2, C1240g gVar, ArrayList arrayList) {
        this.e = eVar;
        this.f = eVar2;
        this.g = gVar;
        this.f34h = arrayList;
        this.d = eVar;
    }
}
