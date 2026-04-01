package com.samsung.android.gallery.plugins.portrait;

import android.net.Uri;
import android.view.View;
import com.samsung.android.gallery.plugins.portrait.LiveEffectDelegate;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ j(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((LiveEffectDelegate) this.e).lambda$applyFlexModeTransition$22((View) this.f, (Runnable) this.g);
                return;
            case 1:
                ((LiveEffectDelegate.AnonymousClass2) this.e).lambda$onFailed$2((O3DPListener.ErrorType) this.f, (BiConsumer) this.g);
                return;
            default:
                ((LiveEffectDelegate.AnonymousClass2) this.e).lambda$onVideoEncoded$0((Uri) this.f, (BiConsumer) this.g);
                return;
        }
    }
}
