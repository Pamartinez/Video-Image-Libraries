package com.samsung.android.gallery.plugins.portrait;

import android.content.Context;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3099a;
    public final /* synthetic */ LiveEffectDelegate b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Context f3100c;

    public /* synthetic */ f(LiveEffectDelegate liveEffectDelegate, Context context, int i2) {
        this.f3099a = i2;
        this.b = liveEffectDelegate;
        this.f3100c = context;
    }

    public final void accept(Object obj, Object obj2) {
        O3DPListener.ErrorType errorType = (O3DPListener.ErrorType) obj;
        String str = (String) obj2;
        switch (this.f3099a) {
            case 0:
                this.b.lambda$convertAndShare$8(this.f3100c, errorType, str);
                return;
            default:
                this.b.lambda$convertAndSetAs$9(this.f3100c, errorType, str);
                return;
        }
    }
}
