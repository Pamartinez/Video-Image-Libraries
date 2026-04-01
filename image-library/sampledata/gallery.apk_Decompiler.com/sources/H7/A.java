package H7;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.pictures.filter.ScreenShotFilterListViewHolder;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.AlbumSettingFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.setting.ScreenshotFilterCustomFragment;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.FuzzyKeywordSuggestionView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.HierarchicalView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SettingActionView;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggestedActionView;
import com.samsung.android.gallery.app.ui.list.sharings.family.FamilySuggestedPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingHeaderViewDelegateV2;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatTransitoryItemViewHolderV80;
import com.samsung.android.gallery.app.ui.list.stories.spotify.SpotifySlideshowFragment;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesPinchViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesViewHolder;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsViewHolder;
import com.samsung.android.gallery.app.ui.list.suggestions.cleanout.CleanOutPicturesFragment;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoController;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.VideoSeekController;
import com.samsung.android.gallery.app.ui.viewer2.details.DetailsHandler;
import com.samsung.android.gallery.app.ui.viewer2.details.items.ViewHolderCreaturesRemove;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.unlock.UnlockViewerHolder;
import com.samsung.android.gallery.widget.search.filter.OnlyThemView;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersAdapter;
import com.samsung.android.gallery.widget.search.pictures.SearchCountHeaderView;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FloatingAutoCompleteView;
import com.samsung.android.gallery.widget.smartalbum.SmartAlbumLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class A implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ A(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((VideoController) obj).onPlayPauseViewClicked(view);
                return;
            case 1:
                ((VideoSeekController) obj).onPlayPauseViewClicked(view);
                return;
            case 2:
                ((FuzzyKeywordSuggestionView) obj).lambda$bind$0(view);
                return;
            case 3:
                ((HierarchicalView) obj).lambda$inflate$0(view);
                return;
            case 4:
                ((SettingActionView) obj).lambda$bind$0(view);
                return;
            case 5:
                ((SuggestedActionView) obj).lambda$inflate$0(view);
                return;
            case 6:
                ((SpotifySlideshowFragment) obj).lambda$bindView$0(view);
                return;
            case 7:
                ((DetailsHandler) obj).lambda$setEditClickListener$5(view);
                return;
            case 8:
                ((StoriesPinchViewHolder) obj).onFavoriteViewClicked(view);
                return;
            case 9:
                ((StoriesViewHolder) obj).onViewClicked(view);
                return;
            case 10:
                ((ViewHolderCreaturesRemove) obj).lambda$bindView$0(view);
                return;
            case 11:
                ((ScreenShotFilterListViewHolder) obj).lambda$bindView$0(view);
                return;
            case 12:
                ((FamilySuggestedPicturesPresenter) obj).launchPeopleSelectView(view);
                return;
            case 13:
                ((SuggestionsViewHolder) obj).lambda$bindView$0(view);
                return;
            case 14:
                ((SharingHeaderViewDelegateV2) obj).startSlideShow(view);
                return;
            case 15:
                ((SharingPicturesFragment) obj).launchFamilyGroupDetail(view);
                return;
            case 16:
                ((CleanOutPicturesFragment) obj).onHeaderButtonClicked(view);
                return;
            case 17:
                ((AlbumSettingFragment) obj).lambda$onViewCreated$0(view);
                return;
            case 18:
                ((ScreenshotFilterCustomFragment) obj).lambda$onBindView$0(view);
                return;
            case 19:
                ((SharingPicturesSettingFragment) obj).lambda$onViewCreated$1(view);
                return;
            case 20:
                ((StoriesBaseViewHolder) obj).lambda$bindView$0(view);
                return;
            case 21:
                ((OnlyThemView) obj).lambda$inflate$0(view);
                return;
            case 22:
                ((SearchFiltersAdapter) obj).lambda$showSubFilterDialog$1(view);
                return;
            case 23:
                ((SearchCountHeaderView) obj).lambda$bindViewAll$0(view);
                return;
            case 24:
                ((StoriesCatBaseViewHolder) obj).onHeaderClicked(view);
                return;
            case 25:
                ((FloatingAutoCompleteView) obj).onFilterClicked(view);
                return;
            case 26:
                ((StoriesCatTransitoryItemViewHolderV80) obj).lambda$initSaveIcon$0(view);
                return;
            case 27:
                ((SlideshowViewerHolder) obj).toggleOsd(view);
                return;
            case 28:
                ((UnlockViewerHolder) obj).onLockIconClick(view);
                return;
            default:
                ((SmartAlbumLayout) obj).onItemClick(view);
                return;
        }
    }
}
