package com.samsung.context.sdk.samsunganalytics.internal.connection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum b {
    DEVICE_CONTROLLER_DIR("/v3/sdk/quotas"),
    DATA_DELETE("/v3/sdk/indiv/delete"),
    DLS_DIR(""),
    DLS_DIR_BAT("");
    
    String directory;

    /* access modifiers changed from: public */
    b(String str) {
        this.directory = str;
    }

    public final void a(String str) {
        this.directory = str;
    }
}
