package com.samsung.android.sdk.scs.ai.language;

import com.samsung.android.sdk.scs.base.utils.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppInfo {
    public static final String ACCOUNT_TYPE_B2B = "B2B";
    public static final String ACCOUNT_TYPE_B2C = "B2C";
    private static final String TAG = "AppInfo";
    private final String accessToken;
    private final String accountType;
    private final String apiKey;
    private final String appId;
    private final boolean enableStreaming;
    private final RequestType requestType;
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
        public String AppId = "";
        /* access modifiers changed from: private */
        public String accessToken = "";
        /* access modifiers changed from: private */
        public String accountType = "B2C";
        /* access modifiers changed from: private */
        public String apiKey = "";
        /* access modifiers changed from: private */
        public boolean enableStreaming = false;
        /* access modifiers changed from: private */
        public RequestType requestType = RequestType.CLOUD;
        /* access modifiers changed from: private */
        public String serverUrl = "";
        /* access modifiers changed from: private */
        public String signingKey = "";
        /* access modifiers changed from: private */
        public String userId = "";

        public AppInfo build() {
            return new AppInfo(this, 0);
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
            this.AppId = str;
            return this;
        }

        public Builder setRequestType(RequestType requestType2) {
            this.requestType = requestType2;
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

        public Builder setStreamingMode(boolean z) {
            this.enableStreaming = z;
            return this;
        }

        public Builder setUserId(String str) {
            this.userId = str;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RequestType {
        CLOUD,
        ONDEVICE,
        ONDEVICE_EXTERNAL
    }

    public /* synthetic */ AppInfo(Builder builder, int i2) {
        this(builder);
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

    public RequestType getRequestType() {
        return this.requestType;
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

    public boolean isStreamingMode() {
        return this.enableStreaming;
    }

    private AppInfo(Builder builder) {
        this.apiKey = builder.apiKey;
        this.serverUrl = builder.serverUrl;
        this.appId = builder.AppId;
        this.signingKey = builder.signingKey;
        this.accessToken = builder.accessToken;
        this.userId = builder.userId;
        this.accountType = builder.accountType;
        this.requestType = builder.requestType;
        this.enableStreaming = builder.enableStreaming;
    }
}
