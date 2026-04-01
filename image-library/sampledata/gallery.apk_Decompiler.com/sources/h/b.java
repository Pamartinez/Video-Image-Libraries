package H;

import android.content.Context;
import android.database.Cursor;
import androidx.media3.effect.DebugViewShaderProgram;
import com.samsung.android.gallery.app.receiver.SharedAlbumNotificationReceiver;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisherV2;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b(SharedTransition sharedTransition, long j2, Blackboard blackboard) {
        this.d = 7;
        this.f = sharedTransition;
        this.e = j2;
        this.g = blackboard;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((DebugViewShaderProgram) this.f).lambda$queueInputFrame$2((Exception) this.g, this.e);
                return;
            case 1:
                ((StoriesDataPublisher) this.f).lambda$publishHideSceneSelectionData$11((Cursor[]) this.g, this.e);
                return;
            case 2:
                ((StoriesDataPublisherV2) this.f).lambda$publishHideSceneSelectionData$5((Cursor[]) this.g, this.e);
                return;
            case 3:
                ((SharedAlbumNotificationReceiver) this.f).lambda$handleOnePersonAlbumDeleteNotice$6((Context) this.g, this.e);
                return;
            case 4:
                ((ImageViewHolder) this.f).lambda$updateSimilarItem$7((MediaItem) this.g, this.e);
                return;
            case 5:
                ((RecapImageAssetDelegateForRecorder) this.f).lambda$fetchBitmap$1((String) this.g, this.e);
                return;
            case 6:
                ((OnDemandSuggestionDataManager) this.f).lambda$update$0((List) this.g, this.e);
                return;
            default:
                SharedTransition.lambda$startPostponedEnterTransition$4((SharedTransition) this.f, this.e, (Blackboard) this.g);
                return;
        }
    }

    public /* synthetic */ b(Object obj, Object obj2, long j2, int i2) {
        this.d = i2;
        this.f = obj;
        this.g = obj2;
        this.e = j2;
    }
}
