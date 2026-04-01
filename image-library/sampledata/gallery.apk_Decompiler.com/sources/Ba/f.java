package Ba;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.controller.abstraction.ViewerDownloadTask;
import com.samsung.android.gallery.app.controller.internals.SaveAsRemasterCmd;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.editor.AbsEditorTransitionHandler;
import com.samsung.android.gallery.module.camera.PostProcessingHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.idleworker.jobs.CacheTrimJob;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoActivity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.MediaHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ f(long j2, ArrayList arrayList, ArrayList arrayList2) {
        this.d = 6;
        this.e = j2;
        this.g = arrayList;
        this.f = arrayList2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((MotionPhotoActivity) this.g).lambda$startVideo$6((MediaItem) this.f, this.e, (MediaHelper.VideoInfo) obj);
                return;
            case 1:
                ((ViewerDownloadTask) this.g).lambda$prepareDownloadedInternal$3(this.e, (MediaItem) this.f, (Bitmap) obj);
                return;
            case 2:
                ((PostProcessingHelper) this.g).lambda$updateCompletedPpp$6((ArrayList) this.f, this.e, (MediaItem) obj);
                return;
            case 3:
                ((PostProcessingHelper) this.g).lambda$updateCompletedPpp$5(this.e, (MediaItem) this.f, (Blackboard) obj);
                return;
            case 4:
                ((SaveAsRemasterCmd) this.g).lambda$saveAsFile$1((String) this.f, this.e, (Boolean) obj);
                return;
            case 5:
                ((AbsEditorTransitionHandler) this.g).lambda$waitNewMediaItemLoaded$3(this.e, (MediaItem) this.f, (MediaItem) obj);
                return;
            default:
                CacheTrimJob.lambda$execute2$2(this.e, (ArrayList) this.g, (ArrayList) this.f, (List) obj);
                return;
        }
    }

    public /* synthetic */ f(Object obj, long j2, MediaItem mediaItem, int i2) {
        this.d = i2;
        this.g = obj;
        this.e = j2;
        this.f = mediaItem;
    }

    public /* synthetic */ f(Object obj, Object obj2, long j2, int i2) {
        this.d = i2;
        this.g = obj;
        this.f = obj2;
        this.e = j2;
    }
}
