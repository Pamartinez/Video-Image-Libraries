package com.samsung.android.gallery.plugins.portrait;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements MediaScannerConnection.OnScanCompletedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LiveEffectDelegate f3103a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f3104c;
    public final /* synthetic */ BiConsumer d;

    public /* synthetic */ h(LiveEffectDelegate liveEffectDelegate, Context context, int i2, BiConsumer biConsumer) {
        this.f3103a = liveEffectDelegate;
        this.b = context;
        this.f3104c = i2;
        this.d = biConsumer;
    }

    public final void onScanCompleted(String str, Uri uri) {
        this.f3103a.lambda$scanFile$14(this.b, this.f3104c, this.d, str, uri);
    }
}
