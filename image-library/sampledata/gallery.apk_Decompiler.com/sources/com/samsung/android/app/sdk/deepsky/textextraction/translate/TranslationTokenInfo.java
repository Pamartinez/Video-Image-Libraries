package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u000bR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0013\u001a\u0004\b\u0015\u0010\u000bR\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0016\u0010\u000bR\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0013\u001a\u0004\b\u0017\u0010\u000bR\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0013\u001a\u0004\b\u0018\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TranslationTokenInfo;", "", "", "apiKey", "signingKey", "appId", "accessToken", "serverUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getApiKey", "getSigningKey", "getAppId", "getAccessToken", "getServerUrl", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TranslationTokenInfo {
    private final String accessToken;
    private final String apiKey;
    private final String appId;
    private final String serverUrl;
    private final String signingKey;

    public TranslationTokenInfo(String str, String str2, String str3, String str4, String str5) {
        j.e(str, "apiKey");
        j.e(str2, "signingKey");
        j.e(str3, "appId");
        j.e(str4, "accessToken");
        j.e(str5, "serverUrl");
        this.apiKey = str;
        this.signingKey = str2;
        this.appId = str3;
        this.accessToken = str4;
        this.serverUrl = str5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TranslationTokenInfo)) {
            return false;
        }
        TranslationTokenInfo translationTokenInfo = (TranslationTokenInfo) obj;
        if (j.a(this.apiKey, translationTokenInfo.apiKey) && j.a(this.signingKey, translationTokenInfo.signingKey) && j.a(this.appId, translationTokenInfo.appId) && j.a(this.accessToken, translationTokenInfo.accessToken) && j.a(this.serverUrl, translationTokenInfo.serverUrl)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.serverUrl.hashCode() + C0212a.d(C0212a.d(C0212a.d(this.apiKey.hashCode() * 31, 31, this.signingKey), 31, this.appId), 31, this.accessToken);
    }

    public String toString() {
        String str = this.apiKey;
        String str2 = this.signingKey;
        String str3 = this.appId;
        String str4 = this.accessToken;
        String str5 = this.serverUrl;
        StringBuilder q = C0086a.q("TranslationTokenInfo(apiKey=", str, ", signingKey=", str2, ", appId=");
        C0086a.z(q, str3, ", accessToken=", str4, ", serverUrl=");
        return C0212a.p(q, str5, ")");
    }
}
