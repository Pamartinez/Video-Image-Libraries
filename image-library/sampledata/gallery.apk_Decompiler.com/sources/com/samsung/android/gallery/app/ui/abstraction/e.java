package com.samsung.android.gallery.app.ui.abstraction;

import android.animation.Animator;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ MvpBaseDelegate d;
    public final /* synthetic */ MvpBaseFragment e;

    public /* synthetic */ e(MvpBaseDelegate mvpBaseDelegate, MvpBaseFragment mvpBaseFragment) {
        this.d = mvpBaseDelegate;
        this.e = mvpBaseFragment;
    }

    public final void accept(Object obj) {
        this.d.lambda$postOpenEnterAnimStartEvent$2(this.e, (Animator) obj);
    }
}
