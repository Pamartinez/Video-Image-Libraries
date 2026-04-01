package com.samsung.android.sdk.scs.ai.text.bnlp;

import java.util.concurrent.Callable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BasicNlpAnalyzer f1662a;
    public final /* synthetic */ String b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f1663c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ boolean f;

    public /* synthetic */ a(BasicNlpAnalyzer basicNlpAnalyzer, String str, String str2, boolean z, boolean z3, boolean z7) {
        this.f1662a = basicNlpAnalyzer;
        this.b = str;
        this.f1663c = str2;
        this.d = z;
        this.e = z3;
        this.f = z7;
    }

    public final Object call() {
        return this.f1662a.lambda$requestAnalyze$1(this.b, this.f1663c, this.d, this.e, this.f);
    }
}
