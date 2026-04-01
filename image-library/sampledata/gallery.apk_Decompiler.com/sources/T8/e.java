package t8;

import J0.g;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ISearchPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.StoryThemeView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.EntryBlurHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerAiEditHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.AbsDecorBgHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.CloudDownloader;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DexZoomButtonUi;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DualShotOptionsViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.GroupPanelTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.OverlayViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.PostProcessingDefender;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.RemasterTransitionHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureMotionPhotoHandler;
import com.samsung.android.gallery.database.dal.mp.impl.StoryHelperBaseImpl;
import com.samsung.android.gallery.image360.activity.options.PlayBackOptionsFragment;
import com.samsung.android.gallery.image360.activity.viewer.Image360Fragment;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.widget.dialog.ProgressAvdCompat;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import java.io.ByteArrayInputStream;
import java.util.concurrent.Semaphore;
import x0.C0322D;
import x0.w;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ e(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void run() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((StoryHelperBaseImpl.CmhNotifier) obj).flushJobs();
                return;
            case 1:
                ((AnalyticsLogger) obj).sendLog();
                return;
            case 2:
                ((AlertDialog) obj).getButton(-1).performClick();
                return;
            case 3:
                ((ProgressAvdCompat) obj).lambda$hideProgressBar$1();
                return;
            case 4:
                ((AbsViewerHolder) obj).lambda$setDebugText$2();
                return;
            case 5:
                ((PhotoViewCompat) obj).clearBitmap();
                return;
            case 6:
                ((PhotoViewMotionControl) obj).zoomToMinScale();
                return;
            case 7:
                ((SearchPicturesFragment) obj).updateEmptyViews();
                return;
            case 8:
                ((ISearchPicturesView) obj).invalidateOptionsMenu();
                return;
            case 9:
                ((StoryThemeView) obj).lambda$setVisible$0();
                return;
            case 10:
                ((AbsDecorBgHandler) obj).lambda$onResolutionChanged$4();
                return;
            case 11:
                ((CloudDownloader) obj).lambda$afterConfirm$1();
                return;
            case 12:
                ((DexZoomButtonUi) obj).lambda$updateVisibility$7();
                return;
            case 13:
                ((DualShotOptionsViewHandler) obj).lambda$onDualPhotoChanged$2();
                return;
            case 14:
                ((GroupPanelTransitionHandler) obj).lambda$onBackKeyPressed$1();
                return;
            case 15:
                ((OverlayViewHandler) obj).lambda$updateLayout$4();
                return;
            case 16:
                ((PostProcessingDefender) obj).reload();
                return;
            case 17:
                ((RemasterTransitionHandler) obj).hideContainerPreview();
                return;
            case 18:
                ((PlayBackOptionsFragment) obj).lambda$setBitmaps$0();
                return;
            case 19:
                ((SearchClusterResultContainer) obj).lambda$startSimpleAutoScrollerInternal$8();
                return;
            case 20:
                ((Image360Fragment) obj).lambda$new$0();
                return;
            case 21:
                g.b((ByteArrayInputStream) obj);
                return;
            case 22:
                w wVar = (w) obj;
                Semaphore semaphore = wVar.Q;
                F0.e eVar = wVar.u;
                if (eVar != null) {
                    try {
                        semaphore.acquire();
                        eVar.q(wVar.e.a());
                    } catch (InterruptedException unused) {
                    } catch (Throwable th) {
                        semaphore.release();
                        throw th;
                    }
                    semaphore.release();
                    return;
                }
                return;
            case 23:
                ((C0322D) obj).c();
                return;
            case 24:
                ((ClusterPicturesFragment) obj).finish();
                return;
            case 25:
                ((ClusterResultPresenter) obj).onDataChangedOnUi();
                return;
            case 26:
                ((EntryBlurHolder) obj).lambda$disable$0();
                return;
            case 27:
                ((StoryHighlightViewPagerAdapter) obj).lambda$updateLastBoundBitmap$3();
                return;
            case 28:
                ((ViewPagerAiEditHolder) obj).fadeIn();
                return;
            default:
                ((ObjectCaptureMotionPhotoHandler) obj).lambda$onVideoStarted$3();
                return;
        }
    }
}
