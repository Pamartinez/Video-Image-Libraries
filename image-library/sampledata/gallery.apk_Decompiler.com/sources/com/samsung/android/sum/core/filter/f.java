package com.samsung.android.sum.core.filter;

import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.ReorderHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.reorder.ReorderListener;
import com.samsung.android.gallery.widget.videoview.mediaplayer.RoundedCornerDelegate;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;

    public /* synthetic */ f(int i2, int i7, int i8) {
        this.d = i8;
        this.e = i2;
        this.f = i7;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ImgpFilter.lambda$run$2(this.e, this.f, (MediaBuffer) obj);
                return;
            case 1:
                ((IReorderHandler) obj).moveFolderToPosition(this.e, this.f);
                return;
            case 2:
                ((RoundedCornerDelegate) obj).setCoverViewSize(this.e, this.f);
                return;
            case 3:
                ListContainerFragment.lambda$setBottomMoveBarMargin$5(this.e, this.f, (FragmentActivity) obj);
                return;
            default:
                ReorderHandler.lambda$onDrop$1(this.e, this.f, (ReorderListener) obj);
                return;
        }
    }
}
