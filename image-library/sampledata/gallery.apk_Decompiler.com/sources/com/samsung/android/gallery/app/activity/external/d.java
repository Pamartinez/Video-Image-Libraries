package com.samsung.android.gallery.app.activity.external;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ LinkViewNavController d;
    public final /* synthetic */ Consumer e;
    public final /* synthetic */ double f;
    public final /* synthetic */ double g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ double f2494h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f2495i;

    public /* synthetic */ d(LinkViewNavController linkViewNavController, Consumer consumer, double d2, double d3, double d5, String str) {
        this.d = linkViewNavController;
        this.e = consumer;
        this.f = d2;
        this.g = d3;
        this.f2494h = d5;
        this.f2495i = str;
    }

    public final void run() {
        this.d.lambda$loadLocation$4(this.e, this.f, this.g, this.f2494h, this.f2495i);
    }
}
