package a6;

import android.media.MediaCodec;
import android.os.Parcel;
import android.view.View;
import androidx.profileinstaller.DeviceProfileWriter;
import androidx.profileinstaller.ProfileInstaller;
import com.samsung.android.gallery.app.ui.list.pictures.ClusterPositionFinder;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.BottomStoryBehavior;
import com.samsung.android.gallery.app.ui.list.stories.highlight.ExportHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.StoryHighlightBehavior;
import com.samsung.android.gallery.app.ui.list.stories.pictures.StoryPicturesPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.ExportType;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.behavior.DrawerTabBehavior;
import com.samsung.android.gallery.widget.behavior.SearchPicturesLocationBehavior;
import com.samsung.android.gallery.widget.details.DetailsBehavior;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewImp;
import com.samsung.android.sum.core.filter.EncoderFilter;
import fe.m;
import fe.o;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: a6.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0418a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0418a(Object obj, int i2, Object obj2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
        this.g = obj2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((BottomStoryBehavior) this.f).lambda$setDecorViewsHeight$0(this.e, (SearchToolbar) this.g);
                return;
            case 1:
                ((EncoderFilter) this.f).lambda$run$7(this.e, (MediaCodec.BufferInfo) this.g);
                return;
            case 2:
                ((DeviceProfileWriter) this.f).lambda$result$0(this.e, this.g);
                return;
            case 3:
                ((ProfileInstaller.DiagnosticsCallback) this.f).onResultReceived(this.e, this.g);
                return;
            case 4:
                ((ClusterPositionFinder) this.f).lambda$moveToTargetPosition$2((PicturesViewAdapter) this.g, this.e);
                return;
            case 5:
                m mVar = (m) this.f;
                int i2 = this.e;
                Parcel parcel = (Parcel) this.g;
                Logger logger = o.b;
                try {
                    boolean transact = mVar.f4361a.transact(i2, parcel, (Parcel) null, 1);
                    parcel.recycle();
                    if (!transact) {
                        logger.log(Level.FINEST, "A oneway transaction was not understood - ignoring");
                        return;
                    }
                    return;
                } catch (Exception e7) {
                    logger.log(Level.FINEST, "A oneway transaction threw - ignoring", e7);
                    return;
                } catch (Throwable th) {
                    parcel.recycle();
                    throw th;
                }
            case 6:
                ((ExportHandler) this.f).lambda$handleExport$2(this.e, (ExportType) this.g);
                return;
            case 7:
                ((ExportHandler) this.f).lambda$showSavedInToast$13((MediaItem) this.g, this.e);
                return;
            case 8:
                ((MediaPlayerViewImp) this.f).lambda$setVideoFilter$28((String) this.g, this.e);
                return;
            case 9:
                SimpleAnimator.lambda$setVisibility$1((View) this.f, this.e, (Runnable) this.g);
                return;
            case 10:
                SimpleAnimator.lambda$setVisibility$3((View[]) this.f, this.e, (Runnable) this.g);
                return;
            case 11:
                ((StoryHighlightBehavior) this.f).lambda$settleToStatePendingLayout$1((View) this.g, this.e);
                return;
            case 12:
                ((DrawerTabBehavior) this.f).lambda$settleToStatePendingLayout$0((View) this.g, this.e);
                return;
            case 13:
                View view = (View) this.g;
                ((SearchPicturesLocationBehavior) this.f).lambda$setState$1(view, this.e);
                return;
            case 14:
                ((DetailsBehavior) this.f).lambda$setState$1((View) this.g, this.e);
                return;
            default:
                ((StoryPicturesPresenter) this.f).lambda$moveToSearchTagView$1((MediaItem) this.g, this.e);
                return;
        }
    }

    public /* synthetic */ C0418a(Object obj, Object obj2, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.e = i2;
    }
}
