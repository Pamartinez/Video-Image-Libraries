package v7;

import android.view.animation.Animation;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.dialog.hidescene.HideSceneSelectionDialog;
import com.samsung.android.gallery.app.ui.list.search.pictures.abstraction.ICreatureContactDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultContainer;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.stories.pictures.cover.StoryCover;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeader;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureTask;
import com.samsung.android.gallery.module.clip.textextraction.DocumentScanner;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionTask;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.widget.clip.textextraction.TextExtractionView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerCallback;
import java.lang.ref.WeakReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ w(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Animation) obj).cancel();
                return;
            case 1:
                HideSceneSelectionDialog.lambda$onConfigurationChanged$3((RecyclerView) obj);
                return;
            case 2:
                ((SearchClusterResultContainer) obj).onDestroy();
                return;
            case 3:
                ((SearchClusterResultContainer) obj).resetClusterItemClicked();
                return;
            case 4:
                ((SearchClusterResultPresenter) obj).updateSubTitle();
                return;
            case 5:
                ((CreaturePicturesDelegate) obj).setFilterChangedOnCluster();
                return;
            case 6:
                ((GalleryListView) obj).selectAll();
                return;
            case 7:
                ((GalleryListView) obj).unSelectAll();
                return;
            case 8:
                ((GalleryListView) obj).exitSelectionMode(false);
                return;
            case 9:
                ((ViewPagerCallback) obj).onPreSlideShowDone();
                return;
            case 10:
                ((ObjectCaptureHelper) obj).setToolbarVisibility(false);
                return;
            case 11:
                DeepSkyHelper.removeCallbacks((ObjectCaptureTask) obj);
                return;
            case 12:
                ((WeakReference) obj).clear();
                return;
            case 13:
                ((ImageView) obj).clearAnimation();
                return;
            case 14:
                ((ICreatureContactDelegate) obj).launchContactDetail();
                return;
            case 15:
                ((StoryCover) obj).pause();
                return;
            case 16:
                ((StoryCover) obj).onDataChangedOnUi();
                return;
            case 17:
                ((StoryHeader) obj).resume();
                return;
            case 18:
                ((StoryCover) obj).resume();
                return;
            case 19:
                ((StoryCover) obj).destroyView();
                return;
            case 20:
                ((TextExtractionView) obj).invalidate();
                return;
            case 21:
                ((TextExtractionHelper) obj).finishTextSelection();
                return;
            case 22:
                ((TextExtractionHelper) obj).clearInitPoint();
                return;
            case 23:
                ((TextExtractionHelper) obj).resetObjectCaptureHelper();
                return;
            case 24:
                DeepSkyHelper.removeCallbacks((TextExtractionTask) obj);
                return;
            case 25:
                ((TextExtractionHelper) obj).clear();
                return;
            case 26:
                DeepSkyHelper.removeCallbacks((Runnable) obj);
                return;
            case 27:
                DeepSkyHelper.postDelayed((Runnable) obj, 100);
                return;
            case 28:
                DocumentScanner.getInstance().resetCache(((MediaItem) obj).getFileId());
                return;
            default:
                ((FloatingToolbarLayout) obj).h();
                return;
        }
    }
}
