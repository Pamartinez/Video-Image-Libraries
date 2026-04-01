package com.samsung.android.app.sdk.deepsky.contract.search;

import B1.a;
import android.os.Bundle;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.TakingPicturesActivityBundleWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import me.i;
import ne.C1194l;
import ne.C1196n;
import ne.y;
import ne.z;
import o1.C0246a;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/ResponseData;", "", "bundle", "Landroid/os/Bundle;", "<init>", "(Landroid/os/Bundle;)V", "response", "", "getResponse", "()Ljava/lang/String;", "hasError", "", "getHasError", "()Z", "hasResponse", "getHasResponse", "promise", "Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise;", "getPromise", "()Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise;", "isFulfilled", "unzip", "content", "", "Companion", "Builder", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ResponseData {
    public static final Companion Companion = new Companion((e) null);
    private final Bundle bundle;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/ResponseData$Builder;", "", "<init>", "()V", "promise", "", "response", "responseBytes", "setPromise", "setResponse", "setResponseBytes", "build", "Lcom/samsung/android/app/sdk/deepsky/contract/search/RequestData;", "zip", "", "content", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String promise;
        private String response;
        private String responseBytes;

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002f, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x002b, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x002c, code lost:
            B1.a.g(r1, r3);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final byte[] zip(java.lang.String r4) {
            /*
                r3 = this;
                java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
                r3.<init>()
                java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter
                java.util.zip.GZIPOutputStream r1 = new java.util.zip.GZIPOutputStream
                r1.<init>(r3)
                java.nio.charset.Charset r2 = Tf.a.f3815a
                r0.<init>(r1, r2)
                java.io.BufferedWriter r1 = new java.io.BufferedWriter
                r2 = 8192(0x2000, float:1.14794E-41)
                r1.<init>(r0, r2)
                r1.write(r4)     // Catch:{ all -> 0x0029 }
                r1.close()
                byte[] r3 = r3.toByteArray()
                java.lang.String r4 = "toByteArray(...)"
                kotlin.jvm.internal.j.d(r3, r4)
                return r3
            L_0x0029:
                r3 = move-exception
                throw r3     // Catch:{ all -> 0x002b }
            L_0x002b:
                r4 = move-exception
                B1.a.g(r1, r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.search.ResponseData.Builder.zip(java.lang.String):byte[]");
        }

        public final RequestData build() {
            Bundle bundle = new Bundle();
            String str = this.promise;
            if (str != null) {
                bundle.putString(Contract.PROMISE, str);
            }
            String str2 = this.response;
            if (str2 != null) {
                bundle.putString(Contract.RESPONSE, str2);
            }
            String str3 = this.responseBytes;
            if (str3 != null) {
                bundle.putByteArray(Contract.RESPONSE_GZIP, zip(str3));
            }
            return new RequestData(bundle);
        }

        public final Builder setPromise(String str) {
            j.e(str, Contract.PROMISE);
            this.promise = str;
            return this;
        }

        public final Builder setResponse(String str) {
            j.e(str, Contract.RESPONSE);
            this.response = str;
            return this;
        }

        public final Builder setResponseBytes(String str) {
            j.e(str, Contract.RESPONSE);
            this.responseBytes = str;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002J\u000e\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u000f\u001a\u000e\u0012\b\u0012\u00060\u0011j\u0002`\u0012\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\tJ\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010*\u00020\u0016¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/ResponseData$Companion;", "", "<init>", "()V", "from", "Lcom/samsung/android/app/sdk/deepsky/contract/search/ResponseData;", "bundle", "Landroid/os/Bundle;", "createErrors", "", "msg", "classification", "createBundleErrors", "it", "", "convertErrorList", "", "Ljava/lang/Error;", "Lkotlin/Error;", "response", "asObjList", "Lorg/json/JSONObject;", "Lorg/json/JSONArray;", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public final String createErrors(String str, String str2) {
            String jSONObject = new JSONObject(z.b0(new i(Contract.ERRORS, new JSONArray(C0246a.e0(z.b0(new i("message", str), new i(TakingPicturesActivityBundleWrapper.BUNDLE_KEY_LOCATIONS, new JSONArray()), new i("extensions", z.a0(new i("classification", str2))))))), new i("data", (Object) null))).toString();
            j.d(jSONObject, "toString(...)");
            return jSONObject;
        }

        public final List<JSONObject> asObjList(JSONArray jSONArray) {
            j.e(jSONArray, "<this>");
            Ge.e Z = a.Z(0, jSONArray.length());
            ArrayList arrayList = new ArrayList(C1196n.w0(Z, 10));
            Iterator it = Z.iterator();
            while (it.hasNext()) {
                arrayList.add(jSONArray.optJSONObject(((y) it).nextInt()));
            }
            return C1194l.k1(arrayList);
        }

        public final List<Error> convertErrorList(String str) {
            Object obj;
            j.e(str, Contract.RESPONSE);
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray(Contract.ERRORS);
                j.d(jSONArray, "getJSONArray(...)");
                Iterable<JSONObject> asObjList = asObjList(jSONArray);
                ArrayList arrayList = new ArrayList(C1196n.w0(asObjList, 10));
                for (JSONObject string : asObjList) {
                    arrayList.add(string.getString("message"));
                }
                ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new Error((String) it.next()));
                }
                obj = C1194l.k1(arrayList2);
            } catch (Throwable th) {
                obj = L2.a.l(th);
            }
            if (obj instanceof me.j) {
                obj = null;
            }
            return (List) obj;
        }

        public final Bundle createBundleErrors(Throwable th) {
            j.e(th, "it");
            Bundle bundle = new Bundle();
            Companion companion = ResponseData.Companion;
            String message = th.getMessage();
            if (message == null) {
                message = "no-message";
            }
            bundle.putString(Contract.RESPONSE, companion.createErrors(message, String.valueOf(v.f4727a.b(th.getClass()).o())));
            return bundle;
        }

        public final ResponseData from(Bundle bundle) {
            j.e(bundle, "bundle");
            return new ResponseData(bundle);
        }

        private Companion() {
        }
    }

    public ResponseData(Bundle bundle2) {
        j.e(bundle2, "bundle");
        this.bundle = bundle2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        B1.a.g(r1, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0030, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0032, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        B1.a.g(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0036, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0038, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0039, code lost:
        B1.a.g(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x003c, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String unzip(byte[] r4) {
        /*
            r3 = this;
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream
            r3.<init>(r4)
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ all -> 0x0026 }
            r4.<init>(r3)     // Catch:{ all -> 0x0026 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ all -> 0x0028 }
            java.nio.charset.Charset r1 = Tf.a.f3815a     // Catch:{ all -> 0x0028 }
            r0.<init>(r4, r1)     // Catch:{ all -> 0x0028 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0028 }
            r2 = 8192(0x2000, float:1.14794E-41)
            r1.<init>(r0, r2)     // Catch:{ all -> 0x0028 }
            java.lang.String r0 = D1.f.K(r1)     // Catch:{ all -> 0x002a }
            r1.close()     // Catch:{ all -> 0x0028 }
            r4.close()     // Catch:{ all -> 0x0026 }
            r3.close()
            return r0
        L_0x0026:
            r4 = move-exception
            goto L_0x0037
        L_0x0028:
            r0 = move-exception
            goto L_0x0031
        L_0x002a:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x002c }
        L_0x002c:
            r2 = move-exception
            B1.a.g(r1, r0)     // Catch:{ all -> 0x0028 }
            throw r2     // Catch:{ all -> 0x0028 }
        L_0x0031:
            throw r0     // Catch:{ all -> 0x0032 }
        L_0x0032:
            r1 = move-exception
            B1.a.g(r4, r0)     // Catch:{ all -> 0x0026 }
            throw r1     // Catch:{ all -> 0x0026 }
        L_0x0037:
            throw r4     // Catch:{ all -> 0x0038 }
        L_0x0038:
            r0 = move-exception
            B1.a.g(r3, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.contract.search.ResponseData.unzip(byte[]):java.lang.String");
    }

    public final boolean getHasError() {
        String string = this.bundle.getString(Contract.RESPONSE);
        Boolean bool = null;
        if (string == null) {
            byte[] byteArray = this.bundle.getByteArray(Contract.RESPONSE_GZIP);
            if (byteArray != null) {
                string = unzip(byteArray);
            } else {
                string = null;
            }
        }
        if (string == null) {
            return false;
        }
        List<Error> convertErrorList = Companion.convertErrorList(string);
        if (convertErrorList != null) {
            bool = Boolean.valueOf(!convertErrorList.isEmpty());
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final boolean getHasResponse() {
        return this.bundle.containsKey(Contract.RESPONSE_GZIP) | this.bundle.containsKey(Contract.RESPONSE);
    }

    public final Promise getPromise() {
        String string = this.bundle.getString(Contract.PROMISE);
        if (string != null) {
            return Promise.Companion.parse(string);
        }
        return null;
    }

    public final String getResponse() {
        String str;
        String string = this.bundle.getString(Contract.RESPONSE);
        if (string != null) {
            return string;
        }
        byte[] byteArray = this.bundle.getByteArray(Contract.RESPONSE_GZIP);
        if (byteArray != null) {
            str = unzip(byteArray);
        } else {
            str = null;
        }
        if (str == null) {
            return Companion.createErrors("failed to fetch data", "EmptyResponse");
        }
        return str;
    }

    public final boolean isFulfilled() {
        String string = this.bundle.getString(Contract.RESPONSE);
        if (string != null) {
            return string.equalsIgnoreCase("fulfilled");
        }
        return false;
    }
}
