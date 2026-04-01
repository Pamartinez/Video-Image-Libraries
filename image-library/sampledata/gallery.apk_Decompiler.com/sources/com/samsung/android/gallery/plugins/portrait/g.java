package com.samsung.android.gallery.plugins.portrait;

import android.content.Context;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LiveEffectDelegate f3101a;
    public final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ BiConsumer f3102c;

    public /* synthetic */ g(LiveEffectDelegate liveEffectDelegate, Context context, BiConsumer biConsumer) {
        this.f3101a = liveEffectDelegate;
        this.b = context;
        this.f3102c = biConsumer;
    }

    public final void accept(Object obj, Object obj2) {
        this.f3101a.lambda$convertAndSave$13(this.b, this.f3102c, (O3DPListener.ErrorType) obj, (String) obj2);
    }
}
