package com.samsung.android.gallery.widget.dragdrop;

import android.view.ViewGroup;
import com.samsung.android.gallery.widget.dragdrop.SplitDnDAnimation;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ d(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ViewUtils.removeView((ViewGroup) obj2, ((DragViewSet) obj).mAnimView);
                return;
            case 1:
                SplitDnDAnimation.lambda$getReturnAnimators$5((ArrayList) obj2, (DragViewSet) obj);
                return;
            default:
                ((SplitDnDAnimation.AnonymousClass1) obj2).lambda$onAnimationEnd$0((DragViewSet) obj);
                return;
        }
    }
}
