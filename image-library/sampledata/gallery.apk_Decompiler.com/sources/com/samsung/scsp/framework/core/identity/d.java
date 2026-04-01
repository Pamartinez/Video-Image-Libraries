package com.samsung.scsp.framework.core.identity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ IdentityImpl d;
    public final /* synthetic */ String e;

    public /* synthetic */ d(IdentityImpl identityImpl, String str) {
        this.d = identityImpl;
        this.e = str;
    }

    public final void run() {
        this.d.lambda$signOut$1(this.e);
    }
}
