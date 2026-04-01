package Bb;

import android.app.Activity;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePresenter;
import com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBaseViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationBlur;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.FloatingRecommendationDelegate;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item.FloatingItemDelegate;
import com.samsung.android.gallery.app.ui.list.stories.StoriesViewAdapter;
import com.samsung.android.gallery.module.mde.abstraction.ConnectListener;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.settings.ui.AIEditSuggestionsFragment;
import com.samsung.android.gallery.settings.ui.BasePreferenceFragment;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.widget.HelpPreference;
import com.samsung.android.gallery.support.utils.BooleanFeatures;
import com.samsung.android.gallery.support.utils.PerformanceLog;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripProgressBarDelegate;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.utils.LayoutRuleHolder;
import java.io.File;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ l(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((FilmStripProgressBarDelegate) obj).onViewRecycled();
                return;
            case 1:
                ((Activity) obj).recreate();
                return;
            case 2:
                ((LayoutRuleHolder) obj).removeRules();
                return;
            case 3:
                ((LayoutRuleHolder) obj).restoreRules();
                return;
            case 4:
                ((AlbumsBaseViewAdapter) obj).notifyMoveModeChanged();
                return;
            case 5:
                ((AlbumsBasePresenter) obj).forceReloadData();
                return;
            case 6:
                ((AlbumsBaseViewAdapter) obj).checkVisibleViewHoldersForXLarge();
                return;
            case 7:
                ((RecyclerView.LayoutManager) obj).setItemPrefetchEnabled(false);
                return;
            case 8:
                ((FloatingItemDelegate) obj).updateVisibility();
                return;
            case 9:
                ((FloatingItemDelegate) obj).handleConfigurationChanged();
                return;
            case 10:
                ((FloatingItemDelegate) obj).clearAnimation();
                return;
            case 11:
                ((FloatingRecommendationDelegate) obj).initFloatingToolbarLayout();
                return;
            case 12:
                ((FloatingRecommendationDelegate) obj).handleConfigurationChanged();
                return;
            case 13:
                ((FloatingRecommendationBlur) obj).updateBackgroundImageMatrix();
                return;
            case 14:
                ((FloatingRecommendationDelegate) obj).onDestroy();
                return;
            case 15:
                ((FloatingRecommendationBlur) obj).onDestroy();
                return;
            case 16:
                ((ConnectListener) obj).onSuccess();
                return;
            case 17:
                ((GalleryAppBarLayout) obj).disableAppbarScroll();
                return;
            case 18:
                ((ImageView) obj).setImageBitmap(ThumbnailLoader.getInstance().getDefaultReplacedThumbnail());
                return;
            case 19:
                ((RecyclerView.LayoutManager) obj).setItemPrefetchEnabled(false);
                return;
            case 20:
                ((StoriesViewAdapter) obj).resetItemHeight();
                return;
            case 21:
                ((GalleryListView) obj).resetScrollbarHeight();
                return;
            case 22:
                ((StoriesViewAdapter) obj).startSelect(0);
                return;
            case 23:
                AIEditSuggestionsFragment.lambda$initPreference$0((HelpPreference) obj);
                return;
            case 24:
                BasePreferenceFragment.lambda$onMultiWindowModeChanged$13((RecyclerView) obj);
                return;
            case 25:
                BasePreferenceFragment.lambda$removePreference$7((Preference) obj);
                return;
            case 26:
                ((HelpPreference) obj).setLinkClickListener((Runnable) null);
                return;
            case 27:
                ((BooleanFeatures) obj).recycle();
                return;
            case 28:
                LabsDevManageFragment.lambda$addCategoryStatus$48((File) obj);
                return;
            default:
                PerformanceLog.setEnabled(((Boolean) obj).booleanValue());
                return;
        }
    }
}
