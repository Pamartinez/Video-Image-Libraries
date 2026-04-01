package A4;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class K implements EventContext.OnSelectionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ BaseListPresenter e;

    public /* synthetic */ K(int i2, BaseListPresenter baseListPresenter) {
        this.d = i2;
        this.e = baseListPresenter;
    }

    public final boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        int i2 = this.d;
        BaseListPresenter baseListPresenter = this.e;
        switch (i2) {
            case 0:
                return baseListPresenter.lambda$onDeleteMotionPhotoVideoClipSelectionRequested$8(eventContext, mediaItemArr);
            default:
                return baseListPresenter.lambda$onShareViaSelectionRequested$7(eventContext, mediaItemArr);
        }
    }
}
