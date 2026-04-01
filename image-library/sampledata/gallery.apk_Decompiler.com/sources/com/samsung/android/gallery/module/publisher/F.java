package com.samsung.android.gallery.module.publisher;

import android.os.CancellationSignal;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F implements CancellationSignal.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SearchDataBasePublisher f3043a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f3044c;

    public /* synthetic */ F(SearchDataBasePublisher searchDataBasePublisher, String str, String str2) {
        this.f3043a = searchDataBasePublisher;
        this.b = str;
        this.f3044c = str2;
    }

    public final void onCancel() {
        this.f3043a.lambda$setCancellationSignal$21(this.b, this.f3044c);
    }
}
