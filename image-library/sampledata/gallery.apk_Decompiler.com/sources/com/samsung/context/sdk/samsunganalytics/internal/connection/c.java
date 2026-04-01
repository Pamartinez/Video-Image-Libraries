package com.samsung.context.sdk.samsunganalytics.internal.connection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum c {
    REGISTRATION("https://regi.di.atlas.samsung.com"),
    POLICY("https://dc.di.atlas.samsung.com"),
    DLS("");
    
    String domain;

    /* access modifiers changed from: public */
    c(String str) {
        this.domain = str;
    }

    public final void a(String str) {
        this.domain = str;
    }
}
