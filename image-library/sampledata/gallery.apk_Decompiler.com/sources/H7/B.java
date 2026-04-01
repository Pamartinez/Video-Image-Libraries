package H7;

import android.net.Uri;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MediaDataDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.widget.animations.photostacking.ThrowingViHandler;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class B implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ B(Object obj, int i2, int i7, int i8) {
        this.d = i8;
        this.g = obj;
        this.e = i2;
        this.f = i7;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((VideoController) this.g).lambda$updateTextView$4(this.e, this.f);
                return;
            case 1:
                ((ThrowingViHandler) this.g).lambda$updateLayoutPosition$4(this.e, this.f);
                return;
            case 2:
                ((ReorderDragManager) this.g).lambda$getScrollEndRunnable$11(this.e, this.f);
                return;
            case 3:
                SimpleAnimator.setVisibility((View[]) this.g, this.e, this.f, (Runnable) null);
                return;
            case 4:
                SimpleAnimator.setVisibility((View) this.g, this.e, this.f, (Runnable) null);
                return;
            case 5:
                ((MediaDataDelegate) this.g).lambda$onDataChanged$0(this.e, this.f);
                return;
            default:
                new FilesApi().updateMedia((Uri) this.g, this.e, this.f, false);
                return;
        }
    }
}
