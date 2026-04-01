package com.samsung.android.gallery.widget.animations;

import android.os.Bundle;
import android.widget.ImageView;
import com.samsung.android.gallery.support.blackboard.InstantSubscriberListener;
import com.samsung.android.gallery.widget.animations.SimpleDragShrinkHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements InstantSubscriberListener {
    public final /* synthetic */ SimpleDragShrinkHandler.AnonymousClass2 d;
    public final /* synthetic */ ImageView e;

    public /* synthetic */ l(SimpleDragShrinkHandler.AnonymousClass2 r1, ImageView imageView) {
        this.d = r1;
        this.e = imageView;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.lambda$onAnimationEnd$0(this.e, obj, bundle);
    }
}
