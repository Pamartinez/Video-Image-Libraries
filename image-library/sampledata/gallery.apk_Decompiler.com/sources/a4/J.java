package A4;

import android.graphics.Bitmap;
import android.view.View;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.samsung.android.gallery.app.ui.container.tablet.ListContainerFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.list.search.CollectionFragment;
import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryFragment;
import com.samsung.android.gallery.app.ui.list.stories.category.StoriesCategory2Fragment;
import com.samsung.android.gallery.app.ui.list.trash.TrashFragment;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.objectcapture.ObjectCaptureHandler;
import com.samsung.android.gallery.app.ui.viewer2.grouppanel.list.GroupPanelVideoPreview;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;
import com.samsung.android.gallery.widget.livemotion.LiveMotionViewPager;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerCallback;
import com.samsung.android.gallery.widget.story.transitory.StoriesViewPager;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerListener;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.videoview.mediaplayer.CaptureDelegate;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.livetranslation.util.OcrDataToSceneTextConverter;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class J implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ J(int i2, Object obj, int i7) {
        this.d = i7;
        this.e = i2;
        this.f = obj;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((MenuDataBinding) this.f).setShowAsAction(((Integer) obj).intValue(), this.e);
                return;
            case 1:
                ((BasePreferenceFragment) this.f).lambda$updateToolbarTitle$5(this.e, (CollapsingToolbarLayout) obj);
                return;
            case 2:
                ((LiveMotionViewPager) this.f).lambda$onPageSelectedInternal$0(this.e, (ViewPagerCallback) obj);
                return;
            case 3:
                GroupPanelVideoPreview.lambda$makePreviewCandidates$0(this.e, (LinkedList) this.f, (PreviewViewHolder) obj);
                return;
            case 4:
                ((TrashFragment) this.f).lambda$setEmptyViewLabel$2(this.e, obj);
                return;
            case 5:
                ((StoriesCategory2Fragment) this.f).lambda$startSimpleAutoScroller$1(this.e, (Boolean) obj);
                return;
            case 6:
                ((StoriesViewPager) this.f).lambda$onPageSelected$3(this.e, (ViewPagerListener) obj);
                return;
            case 7:
                ((CollectionFragment) this.f).lambda$startSimpleAutoScroller$7(this.e, (Boolean) obj);
                return;
            case 8:
                ((OcrDataToSceneTextConverter) this.f).lambda$convert$1(this.e, (LttOcrResult.BlockInfo) obj);
                return;
            case 9:
                ((ReorderDragManager) this.f).lambda$reorderTo$7(this.e, (IReorderHandler) obj);
                return;
            case 10:
                ((DatabaseOperation) this.f).lambda$applyReady$2(this.e, (Map) obj);
                return;
            case 11:
                ((CaptureDelegate) obj).setVideoCaptured(this.e, (Bitmap) this.f);
                return;
            case 12:
                ((SearchMyQueryFragment) this.f).lambda$removeItem$0(this.e, (SearchMyQueryAdapter) obj);
                return;
            case 13:
                ListContainerFragment.lambda$updateToolbarNavigation$7((View.OnClickListener) this.f, this.e, (GalleryToolbar) obj);
                return;
            default:
                ((ObjectCaptureHandler) this.f).lambda$publishObjectCaptureResetConsumer$8(this.e, (Integer) obj);
                return;
        }
    }

    public /* synthetic */ J(Object obj, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
    }
}
