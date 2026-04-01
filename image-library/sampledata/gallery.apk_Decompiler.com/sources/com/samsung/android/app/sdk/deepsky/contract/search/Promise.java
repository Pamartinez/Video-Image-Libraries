package com.samsung.android.app.sdk.deepsky.contract.search;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.i;
import ne.z;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise;", "", "raw", "", "id", "notifyUri", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRaw", "()Ljava/lang/String;", "getId", "getNotifyUri", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "Builder", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Promise {
    public static final Companion Companion = new Companion((e) null);
    private final String id;
    private final String notifyUri;
    private final String raw;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise$Builder;", "", "<init>", "()V", "promiseId", "", "notifyUri", "setPromiseId", "", "setNotifyUri", "toJson", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String notifyUri;
        private String promiseId;

        public final Builder setNotifyUri(String str) {
            j.e(str, Contract.NOTIFY_URL);
            this.notifyUri = str;
            return this;
        }

        public final Builder setPromiseId(int i2) {
            this.promiseId = String.valueOf(i2);
            return this;
        }

        public final String toJson() {
            String jSONObject = new JSONObject(z.b0(new i(Contract.PROMISE_ID, this.promiseId), new i(Contract.NOTIFY_URL, this.notifyUri))).toString();
            j.d(jSONObject, "toString(...)");
            return jSONObject;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise$Companion;", "", "<init>", "()V", "parse", "Lcom/samsung/android/app/sdk/deepsky/contract/search/Promise;", "promise", "", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final Promise parse(String str) {
            j.e(str, Contract.PROMISE);
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(Contract.PROMISE_ID);
            String string2 = jSONObject.getString(Contract.NOTIFY_URL);
            if (string == null) {
                throw new IllegalArgumentException("promiseId is null");
            } else if (string2 != null) {
                return new Promise(str, string, string2);
            } else {
                throw new IllegalArgumentException("promiseUri is null");
            }
        }

        private Companion() {
        }
    }

    public Promise(String str, String str2, String str3) {
        j.e(str, "raw");
        j.e(str2, "id");
        j.e(str3, Contract.NOTIFY_URL);
        this.raw = str;
        this.id = str2;
        this.notifyUri = str3;
    }

    public static /* synthetic */ Promise copy$default(Promise promise, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = promise.raw;
        }
        if ((i2 & 2) != 0) {
            str2 = promise.id;
        }
        if ((i2 & 4) != 0) {
            str3 = promise.notifyUri;
        }
        return promise.copy(str, str2, str3);
    }

    public final String component1() {
        return this.raw;
    }

    public final String component2() {
        return this.id;
    }

    public final String component3() {
        return this.notifyUri;
    }

    public final Promise copy(String str, String str2, String str3) {
        j.e(str, "raw");
        j.e(str2, "id");
        j.e(str3, Contract.NOTIFY_URL);
        return new Promise(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Promise)) {
            return false;
        }
        Promise promise = (Promise) obj;
        if (j.a(this.raw, promise.raw) && j.a(this.id, promise.id) && j.a(this.notifyUri, promise.notifyUri)) {
            return true;
        }
        return false;
    }

    public final String getId() {
        return this.id;
    }

    public final String getNotifyUri() {
        return this.notifyUri;
    }

    public final String getRaw() {
        return this.raw;
    }

    public int hashCode() {
        return this.notifyUri.hashCode() + C0212a.d(this.raw.hashCode() * 31, 31, this.id);
    }

    public String toString() {
        String str = this.raw;
        String str2 = this.id;
        return C0212a.p(C0086a.q("Promise(raw=", str, ", id=", str2, ", notifyUri="), this.notifyUri, ")");
    }
}
