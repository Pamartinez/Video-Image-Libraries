package com.samsung.android.gallery.plugins.portrait;

import android.content.Context;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LiveEffectDelegate f3105a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ BiConsumer f3106c;

    public /* synthetic */ k(LiveEffectDelegate liveEffectDelegate, Context context, BiConsumer biConsumer) {
        this.f3105a = liveEffectDelegate;
        this.b = context;
        this.f3106c = biConsumer;
    }

    public final Object apply(Object obj) {
        return this.f3105a.lambda$convert$15(this.b, this.f3106c, (FileItemInterface) obj);
    }
}
