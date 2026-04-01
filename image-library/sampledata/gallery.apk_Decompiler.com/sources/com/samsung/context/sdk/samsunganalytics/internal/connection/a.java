package com.samsung.context.sdk.samsunganalytics.internal.connection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum a {
    DATA_DELETE(r3, r4, r10),
    GET_POLICY(c.POLICY, b.DEVICE_CONTROLLER_DIR, d.GET),
    SEND_LOG(r8, b.DLS_DIR, r10),
    SEND_BUFFERED_LOG(r8, b.DLS_DIR_BAT, r10);
    
    b directory;
    c domain;
    d method;

    /* access modifiers changed from: public */
    a(c cVar, b bVar, d dVar) {
        this.domain = cVar;
        this.directory = bVar;
        this.method = dVar;
    }

    public final String a() {
        return this.method.method;
    }

    public final String b() {
        return this.domain.domain + this.directory.directory;
    }
}
