package A4;

import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryHeaderContainer;
import com.samsung.android.gallery.app.ui.list.search.category.header.AbsCategoryHeaderView;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegateV2;
import com.samsung.android.gallery.app.ui.list.stories.pictures.header.StoryHeaderBasic;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.service.notification.StoryNotificationHelper;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.appbar.CustomCover;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.videoview.controller.VideoSpeedControllerCompat;
import java.util.function.Consumer;

/* renamed from: A4.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0369d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ int e;

    public /* synthetic */ C0369d(int i2, int i7) {
        this.d = i7;
        this.e = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((GalleryListView) obj).smoothScrollBy(0, this.e, PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_90));
                return;
            case 1:
                ((StoryHeaderBasic) obj).handleOrientationChange(this.e);
                return;
            case 2:
                ((AlbumsBaseViewAdapter) obj).scrollToDataPosition(this.e, 120);
                return;
            case 3:
                ((GalleryListView) obj).scrollToPosition(this.e);
                return;
            case 4:
                FloatingRecommendationDelegateV2.lambda$adjustAppbarHeightOffset$1(this.e, (GalleryAppBarLayout) obj);
                return;
            case 5:
                ((GalleryListAdapter) obj).startSelect(this.e);
                return;
            case 6:
                ((CustomCover) obj).handleOrientationChange(this.e);
                return;
            case 7:
                ((CustomCover) obj).handleOrientationChange(this.e);
                return;
            case 8:
                ((GalleryListView) obj).scrollToPositionWithOffset(Math.max(this.e, 0), 20);
                return;
            case 9:
                ((ScreenShotFilterView) obj).scrollToPosition(this.e);
                return;
            case 10:
                ((RecyclerView) obj).scrollToPosition(this.e);
                return;
            case 11:
                ((GalleryToolbar) obj).setBackgroundColor(this.e);
                return;
            case 12:
                ((QueryParams) obj).addAlbumId(this.e);
                return;
            case 13:
                ((MvpBaseFragment) obj).setCurrentTransitioningAnim(this.e);
                return;
            case 14:
                ((LinearLayoutManager) obj).setOrientation(this.e);
                return;
            case 15:
                ((QueryParams) obj).addAlbumId(this.e);
                return;
            case 16:
                ((StoryNotificationHelper) obj).dismiss(this.e);
                return;
            case 17:
                ((QueryParams) obj).setLimit(this.e);
                return;
            case 18:
                ((MenuDataBinding.MenuData) obj).setShowAsAction(this.e);
                return;
            case 19:
                ((GalleryListView) obj).scrollToPositionWithOffset(this.e, 0);
                return;
            case 20:
                ((Consumer) obj).accept(Integer.valueOf(this.e));
                return;
            case 21:
                VideoSpeedControllerCompat.lambda$updateSelected$0(this.e, (ViewGroup) obj);
                return;
            case 22:
                VideoSpeedControllerCompat.lambda$updateSelected$1(this.e, (ViewGroup) obj);
                return;
            case 23:
                VideoSpeedControllerCompat.lambda$updateSelected$2(this.e, (ViewGroup) obj);
                return;
            case 24:
                ((ViewGroup.LayoutParams) obj).height = this.e;
                return;
            case 25:
                if (obj == null) {
                    CategoryHeaderContainer.lambda$handleOrientationChange$1(this.e, (AbsCategoryHeaderView) null);
                    return;
                }
                throw new ClassCastException();
            case 26:
                if (obj == null) {
                    CategoryHeaderContainer.lambda$handleResolutionChange$2(this.e, (AbsCategoryHeaderView) null);
                    return;
                }
                throw new ClassCastException();
            case 27:
                ((Consumer) obj).accept(Integer.valueOf(this.e));
                return;
            case 28:
                ((MvpBaseFragment) obj).setCurrentTransitioningAnim(this.e);
                return;
            default:
                ((MvpBaseFragment) obj).setCurrentTransitioningAnim(this.e);
                return;
        }
    }
}
