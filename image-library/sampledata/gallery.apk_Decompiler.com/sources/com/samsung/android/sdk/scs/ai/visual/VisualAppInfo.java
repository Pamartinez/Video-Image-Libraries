package com.samsung.android.sdk.scs.ai.visual;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VisualAppInfo {
    public static final String ACCOUNT_TYPE_B2B = "B2B";
    public static final String ACCOUNT_TYPE_B2C = "B2C";
    private static final String TAG = "VisualAppInfo";
    private final String accessToken;
    private final String accountType;
    private final String apiKey;
    private final String appId;
    private final String packageName;
    private final String serverUrl;
    private final String signingKey;
    private final String userId;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface AccountType {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String accessToken = "";
        /* access modifiers changed from: private */
        public String accountType = "B2C";
        /* access modifiers changed from: private */
        public String apiKey = "";
        /* access modifiers changed from: private */
        public String appId = "";
        /* access modifiers changed from: private */
        public String packageName = "";
        /* access modifiers changed from: private */
        public String serverUrl = "";
        /* access modifiers changed from: private */
        public String signingKey = "";
        /* access modifiers changed from: private */
        public String userId = "";

        public VisualAppInfo build() {
            return new VisualAppInfo(this, 0);
        }

        public Builder setAccessToken(String str) {
            this.accessToken = str;
            return this;
        }

        public Builder setAccountType(String str) {
            this.accountType = str;
            return this;
        }

        public Builder setApiKey(String str) {
            this.apiKey = str;
            return this;
        }

        public Builder setAppId(String str) {
            this.appId = str;
            return this;
        }

        public Builder setPackageName(String str) {
            this.packageName = str;
            return this;
        }

        public Builder setServerUrl(String str) {
            this.serverUrl = str;
            return this;
        }

        public Builder setSigningKey(String str) {
            this.signingKey = str;
            return this;
        }

        public Builder setUserId(String str) {
            this.userId = str;
            return this;
        }
    }

    public /* synthetic */ VisualAppInfo(Builder builder, int i2) {
        this(builder);
    }

    public static void appInfoToBundle(Bundle bundle, VisualAppInfo visualAppInfo) {
        bundle.putString("api-key", visualAppInfo.getApiKey());
        bundle.putString("package-signing-key", visualAppInfo.getSigningKey());
        bundle.putString("ssp-app-id", visualAppInfo.getAppId());
        if (Build.VERSION.SDK_INT < 35) {
            bundle.putString("package-name", visualAppInfo.getPackageName());
        }
        bundle.putString("ssp-access-token", visualAppInfo.getAccessToken());
        bundle.putString("ssp-user-id", visualAppInfo.getUserId());
        bundle.putString("ssp-server-url", visualAppInfo.getServerUrl());
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getServerUrl() {
        if ("B2B".equals(this.accountType)) {
            Log.i(TAG, "B2B account is set");
            return "";
        }
        Log.i(TAG, "B2B account is not set");
        return this.serverUrl;
    }

    public String getSigningKey() {
        return this.signingKey;
    }

    public String getUserId() {
        return this.userId;
    }

    private VisualAppInfo(Builder builder) {
        this.apiKey = builder.apiKey;
        this.serverUrl = builder.serverUrl;
        this.appId = builder.appId;
        this.packageName = builder.packageName;
        this.signingKey = builder.signingKey;
        this.accessToken = builder.accessToken;
        this.userId = builder.userId;
        this.accountType = builder.accountType;
    }
}
