package com.samsung.android.gallery.plugins.portrait;

import android.net.Uri;
import com.samsung.android.gallery.plugins.portrait.LiveEffectDelegate;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Runnable {
    public final /* synthetic */ int d = 1;
    public final /* synthetic */ String e;
    public final /* synthetic */ BiConsumer f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3107h;

    public /* synthetic */ n(LiveEffectDelegate.AnonymousClass2 r22, Uri uri, String str, BiConsumer biConsumer) {
        this.g = r22;
        this.f3107h = uri;
        this.e = str;
        this.f = biConsumer;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                BiConsumer biConsumer = this.f;
                ((LiveEffectDelegate) this.g).lambda$convertImage$18(this.e, (CompletableFuture) this.f3107h, biConsumer);
                return;
            default:
                ((LiveEffectDelegate.AnonymousClass2) this.g).lambda$onVideoEncoded$1((Uri) this.f3107h, this.e, this.f);
                return;
        }
    }

    public /* synthetic */ n(LiveEffectDelegate liveEffectDelegate, String str, CompletableFuture completableFuture, BiConsumer biConsumer) {
        this.g = liveEffectDelegate;
        this.e = str;
        this.f3107h = completableFuture;
        this.f = biConsumer;
    }
}
