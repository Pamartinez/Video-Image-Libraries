package com.samsung.android.sdk.mobileservice.auth;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TokenInfo {
    public static final String TOKEN_TYPE_ACCESS = "accessToken";
    public static final String TOKEN_TYPE_AUTH_CODE = "authCode";
    public static final String TOKEN_TYPE_REFRESH = "refreshToken";
    private String mApiServerUrl;
    private String mAuthServerUrl;
    private String mToken;
    private long mTokenIssuedTime;
    private String mTokenType;
    private long mTokenValidityPeriod;

    public String getApiServerUrl() {
        return this.mApiServerUrl;
    }

    public String getAuthServerUrl() {
        return this.mAuthServerUrl;
    }

    public String getToken() {
        return this.mToken;
    }

    public long getTokenIssuedTime() {
        return this.mTokenIssuedTime;
    }

    public String getTokenType() {
        return this.mTokenType;
    }

    public long getTokenValidityPeriod() {
        return this.mTokenValidityPeriod;
    }

    public void setApiServerUrl(String str) {
        this.mApiServerUrl = str;
    }

    public void setAuthServerUrl(String str) {
        this.mAuthServerUrl = str;
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    public void setTokenIssuedTime(long j2) {
        this.mTokenIssuedTime = j2;
    }

    public void setTokenType(String str) {
        this.mTokenType = str;
    }

    public void setTokenValidityPeriod(long j2) {
        this.mTokenValidityPeriod = j2;
    }
}
