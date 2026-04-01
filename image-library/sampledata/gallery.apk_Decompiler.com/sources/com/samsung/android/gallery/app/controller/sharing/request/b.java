package com.samsung.android.gallery.app.controller.sharing.request;

import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ RequestLeaveGroup f2510a;
    public final /* synthetic */ String b;

    public /* synthetic */ b(RequestLeaveGroup requestLeaveGroup, String str) {
        this.f2510a = requestLeaveGroup;
        this.b = str;
    }

    public final void accept(int i2) {
        this.f2510a.lambda$request$0(this.b, i2);
    }
}
