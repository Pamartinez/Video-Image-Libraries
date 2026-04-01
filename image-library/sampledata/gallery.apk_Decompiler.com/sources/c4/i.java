package C4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import com.google.android.material.appbar.AppBarLayout;
import com.samsung.android.gallery.app.ui.list.abstraction.IReorderHandler;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumsPanePresenter;
import com.samsung.android.gallery.app.ui.list.reorder.ReorderDragManager;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchScreenShotHeaderAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchScreenShotHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.ForegroundViewController;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.GridCollageLayoutBinder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.GridCollagePage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.utils.AudioPermissionHelper;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.clip.objectcapture.ObjectCaptureView;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.util.OcrDataToSceneTextConverter;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ i(Object obj, int i2, Object obj2, int i7) {
        this.d = i7;
        this.f = obj;
        this.e = i2;
        this.g = obj2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((AlbumsBasePresenter) this.f).lambda$onListItemClickInternal$0(this.e, (MediaItem) this.g, (Integer) obj);
                return;
            case 1:
                ((ForegroundViewController) this.f).lambda$updateViews$2((Rect) this.g, this.e, (FloatingRecommendationDelegate) obj);
                return;
            case 2:
                ((StoriesPinchViewPresenter) this.f).lambda$updateStoryTheme$6((Object[]) this.g, this.e, (MediaItem) obj);
                return;
            case 3:
                ((AlbumPicturesFragment) this.f).lambda$updateAlbumsPaneBottomPadding$39((AppBarLayout) this.g, this.e, (GalleryListView) obj);
                return;
            case 4:
                ((AlbumsPanePresenter) this.f).lambda$onListItemClickInternal$7(this.e, (MediaItem) this.g, (Integer) obj);
                return;
            case 5:
                ((OcrDataToSceneTextConverter) this.f).lambda$convert$1(this.e, (Context) this.g, (LttOcrResult.BlockInfo) obj);
                return;
            case 6:
                ((ReorderDragManager) this.f).lambda$createFolder$6(this.e, (MediaItem[]) this.g, (IReorderHandler) obj);
                return;
            case 7:
                PageDataLoader.lambda$loadCollageItem$4((PageItem[]) this.f, this.e, (CountDownLatch) this.g, (CollageInfo) obj);
                return;
            case 8:
                ((ObjectCaptureView) this.f).lambda$drawDebugView$0(this.e, (Canvas) this.g, (Rect) obj);
                return;
            case 9:
                ((GridCollagePage) this.f).lambda$handleSaveInternal$0((GridCollageLayoutBinder) this.g, this.e, (View) obj);
                return;
            case 10:
                ((AudioPermissionHelper) this.f).lambda$requestPermission$1(this.e, (Runnable) this.g, (Boolean) obj);
                return;
            default:
                ((SearchScreenShotHeaderAdapter) this.f).lambda$onBindViewHolder$1((SearchScreenShotHeaderViewHolder) this.g, this.e, (MediaItem) obj);
                return;
        }
    }

    public /* synthetic */ i(Object obj, Object obj2, int i2, int i7) {
        this.d = i7;
        this.f = obj;
        this.g = obj2;
        this.e = i2;
    }
}
