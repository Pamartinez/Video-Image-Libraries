package A4;

import com.samsung.android.gallery.app.controller.internals.RevertOriginalImageCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListDelegate;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.support.utils.Log;

/* renamed from: A4.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0370e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ MediaItem e;

    public /* synthetic */ C0370e(MediaItem mediaItem, int i2) {
        this.d = i2;
        this.e = mediaItem;
    }

    public final void run() {
        int i2 = this.d;
        MediaItem mediaItem = this.e;
        switch (i2) {
            case 0:
                BaseListDelegate.lambda$bindImage$1(mediaItem);
                return;
            case 1:
                Log.pke("PickerSelectableChecker", "drm: " + mediaItem.isDrm() + ", isBroken: " + mediaItem.isBroken() + ", ppp: " + mediaItem.isPostProcessing());
                return;
            case 2:
                Log.d("StoryContentsPickChecker", "unsupported", MediaItemUtil.getMediaLog(mediaItem));
                return;
            case 3:
                RevertOriginalImageCmd.ProcessManager.remove(mediaItem.getFileId());
                return;
            default:
                mediaItem.setCount(SimilarPhotoHelper.getSimilarCount(mediaItem.getBucketID(), mediaItem.getGroupMediaId()));
                return;
        }
    }
}
