package com.samsung.android.gallery.app.ui.list.albums.virtual;

import A.a;
import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.externals.LaunchAlbumViewCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.toolbar.FloatingRecommendationLauncher;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuSupportHelper;
import com.samsung.android.gallery.app.ui.menu.popmenu.PopupMenuVisibility;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarDataKey;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VirtualAlbumPicturesPresenter<V extends IPicturesView> extends PicturesPresenter<V> {
    MediaItem mAlbumItem;
    String mAlbumTitle;
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class VirtualAlbumPicturesMenuUpdater extends ListMenuUpdater {
        public VirtualAlbumPicturesMenuUpdater(V v) {
            super(v, VirtualAlbumPicturesPresenter.this);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean lambda$updateOptionsMenuAction$0() {
            return VirtualAlbumPicturesPresenter.this.supportAlbumSearch();
        }

        private boolean supportedRemoveFavoriteInList() {
            if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !LocationKey.isFavoritePictures(getLocationKey()) || VirtualAlbumPicturesPresenter.this.getSelectedItemCount() <= 0 || !VirtualAlbumPicturesPresenter.this.isSelectionMode() || VirtualAlbumPicturesPresenter.this.isOnAdvancedMouseFocused()) {
                return false;
            }
            return true;
        }

        private void updateSelectionMenu(Menu menu) {
            boolean z;
            boolean z3;
            boolean z7 = false;
            if (!VirtualAlbumPicturesPresenter.this.isSelectMenuEnabled() || VirtualAlbumPicturesPresenter.this.isSelectionMode()) {
                z = false;
            } else {
                z = true;
            }
            setMenuVisibility(menu, (int) R.id.action_select, z);
            if (!supportCreateMenu() || !VirtualAlbumPicturesPresenter.this.isSelectMenuEnabled() || VirtualAlbumPicturesPresenter.this.isSelectionMode()) {
                z3 = false;
            } else {
                z3 = true;
            }
            setMenuVisibility(menu, (int) R.id.action_create, z3);
            if (Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT)) {
                if (VirtualAlbumPicturesPresenter.this.isSelectMenuEnabled() && !VirtualAlbumPicturesPresenter.this.isSelectionMode()) {
                    z7 = true;
                }
                setMenuVisibility(menu, (int) R.id.action_create_movie, z7);
            }
        }

        public boolean supportAddFavoriteInList() {
            if (!super.supportAddFavoriteInList() || LocationKey.isFavoritePictures(getLocationKey())) {
                return false;
            }
            return true;
        }

        public boolean supportCreateMenu() {
            if (LocationKey.isVideoPictures(getLocationKey())) {
                return MenuSupportHelper.supportCreateVideo();
            }
            return super.supportCreateMenu();
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            boolean z;
            super.updateOptionsMenuAction(menu, selectionMode);
            setMenuVisibility(menu, (int) R.id.action_remove_favorite_in_list, supportedRemoveFavoriteInList());
            updateSelectionMenu(menu);
            boolean z3 = false;
            if (PocFeatures.SLIDESHOW_SELECTED_ITEMS) {
                if (selectionMode == MenuDataBinding.SelectionMode.SELECT) {
                    z = true;
                } else {
                    z = false;
                }
                setMenuVisibility(menu, (int) R.id.action_slideshow_with_selection, z);
            }
            if (PocFeatures.TBD.COMPARE_IMAGES) {
                if (selectionMode == MenuDataBinding.SelectionMode.SELECT) {
                    z3 = true;
                }
                setMenuVisibility(menu, (int) R.id.action_compare_images, z3);
            }
            if (selectionMode == MenuDataBinding.SelectionMode.NORMAL) {
                setMenuVisibility(menu, (int) R.id.action_album_search, (BooleanSupplier) new g(this));
            }
        }

        public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
            setMenuVisibility(menu, (int) R.id.action_add_shortcut, !PopupMenuVisibility.isShortcutAlbum(getLocationKey()));
            updateSelectionMenu(menu);
        }
    }

    public VirtualAlbumPicturesPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        if (LocationKey.isAlbumViewPictures(v.getLocationKey())) {
            String argValue = ArgumentsUtil.getArgValue(v.getLocationKey(), "title");
            this.mAlbumTitle = argValue;
            if (argValue == null) {
                Context context = v.getContext();
                Objects.requireNonNull(context);
                this.mAlbumTitle = context.getString(R.string.unknown);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateSubTitle$1(GalleryAppBarLayout galleryAppBarLayout) {
        galleryAppBarLayout.setTitle((CharSequence) null);
        galleryAppBarLayout.setSubtitle((CharSequence) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSubTitle$2(Toolbar toolbar, String str) {
        try {
            toolbar.setSubtitle((CharSequence) str);
            Optional.ofNullable(((IPicturesView) this.mView).getAppbarLayout()).ifPresent(new a(2));
        } catch (Exception e) {
            a.r(e, new StringBuilder("updateSubtitle failed e="), this.TAG);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateSubTitle$3(Toolbar toolbar, CharSequence charSequence, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar, charSequence);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateToolbar$0(Toolbar toolbar, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar, " ");
    }

    /* access modifiers changed from: private */
    public boolean supportAlbumSearch() {
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !Features.isEnabled(Features.SUPPORT_SCS_ALBUM_PICTURES_SEARCH) || !LocationKey.isVideoPictures(getLocationKey()) || getMediaData().getCount() == 0) {
            return false;
        }
        return true;
    }

    private Object updateSubTitle(Toolbar toolbar, CharSequence charSequence) {
        int[] albumCount = getAlbumCount();
        if (albumCount != null) {
            String makeSubtitle = makeSubtitle(albumCount[0], albumCount[1]);
            if (!TextUtils.equals(charSequence, makeSubtitle)) {
                ThreadUtil.postOnUiThread(new e(this, toolbar, makeSubtitle));
            }
        }
        return null;
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(getLocationKey(), this.mBlackboard);
    }

    public int[] getAlbumCount() {
        try {
            String locationKey = getLocationKey();
            if (LocationKey.isFavoritePictures(locationKey)) {
                return new MpHelper().getAlbumVirtualFavoriteCount();
            }
            if (LocationKey.isVideoPictures(locationKey)) {
                return new MpHelper().getAlbumVirtualVideoCount();
            }
            if (LocationKey.isRecentlyPictures(locationKey)) {
                return new MpHelper(new QueryParams(GroupType.BURST, GroupType.SINGLE_TAKEN)).getAlbumVirtualRecentCount();
            }
            return new int[]{0, 0};
        } catch (Exception e) {
            a.r(e, new StringBuilder("getAlbumCount failed e="), this.TAG);
        }
    }

    public MediaItem getCurrentItem() {
        MediaItem mediaItem;
        String locationKey = getLocationKey();
        if (this.mAlbumItem == null) {
            MediaItem read = ((IPicturesView) this.mView).getMediaData(locationKey).read(0);
            if (read != null) {
                mediaItem = read.clone();
            } else {
                mediaItem = null;
            }
            this.mAlbumItem = mediaItem;
        }
        MediaItem mediaItem2 = this.mAlbumItem;
        if (mediaItem2 != null) {
            mediaItem2.setTitle(AppResources.getString(getTitleResource()));
            if (LocationKey.isVideoPictures(locationKey)) {
                this.mAlbumItem.setAlbumID(BucketUtils.VirtualBucketHolder.video);
            } else if (LocationKey.isRecentlyPictures(locationKey)) {
                this.mAlbumItem.setAlbumID(BucketUtils.VirtualBucketHolder.recent);
            } else if (LocationKey.isFavoritePictures(locationKey)) {
                this.mAlbumItem.setAlbumID(BucketUtils.VirtualBucketHolder.favorite);
            }
            this.mAlbumItem.setVirtualAlbum(true);
        }
        return this.mAlbumItem;
    }

    public Object getEventContextData(String str) {
        if ("searchToolbarFocus".equals(str)) {
            return Boolean.valueOf(this.mSearchToolbarDelegate.hasFocus());
        }
        return super.getEventContextData(str);
    }

    public String getLabelForAccessibility(Context context) {
        String str = this.mAlbumTitle;
        if (str != null) {
            return str;
        }
        return context.getString(getTitleResource());
    }

    public int getTitleResource() {
        String locationKey = getLocationKey();
        if (locationKey == null) {
            return R.string.unknown;
        }
        if (LocationKey.isFavoritePictures(locationKey)) {
            return R.string.smart_album_favorites;
        }
        if (LocationKey.isVideoPictures(locationKey)) {
            return R.string.smart_album_videos;
        }
        if (LocationKey.isRecentlyPictures(locationKey)) {
            return R.string.recently_added;
        }
        return R.string.unknown;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        if (eventMessage.what == 8520 && PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            new FloatingRecommendationLauncher((IBaseListView) this.mView).captureScreenAndExecuteFloatingRecommendation(eventMessage.obj);
            return true;
        } else if (this.mSearchToolbarDelegate.handleEvent(eventMessage) || super.handleEvent(eventMessage)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isConsumeBackPress() {
        Boolean bool = (Boolean) this.mSearchToolbarDelegate.getData(SearchToolbarDataKey.IS_BACK_PRESS_CONSUMED);
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    public boolean isShortcutAlbum() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "shortcut_album", false);
    }

    public void notifyDataChanged(MediaData mediaData) {
        updateSubTitle();
        this.mAlbumItem = null;
    }

    public void onDestroy() {
        super.onDestroy();
        this.mSearchToolbarDelegate.onDestroy();
    }

    public void onDrawerSizeChanged(int i2) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(19, Integer.valueOf(i2)));
    }

    public void onNavigationPressed(View view) {
        if (isShortcutAlbum()) {
            new LaunchAlbumViewCmd().execute(this, new Object[0]);
            Log.d(this.TAG, "onNavigationPressed : launch album view.");
            return;
        }
        super.onNavigationPressed(view);
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildForAlbumPictures(this);
        }
    }

    public void onViewResume() {
        super.onViewResume();
        this.mSearchToolbarDelegate.onResume();
    }

    public void updateToolbar(Toolbar toolbar) {
        String str = this.mAlbumTitle;
        if (str != null) {
            toolbar.setTitle((CharSequence) str);
            toolbar.setSubtitle((CharSequence) null);
        } else {
            toolbar.setTitle(getTitleResource());
            toolbar.setSubtitle((CharSequence) " ");
            ThreadPool.getInstance().submit(new d(this, toolbar));
        }
        if (!isSelectionMode()) {
            setNavigationUpButton(toolbar);
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new VirtualAlbumPicturesMenuUpdater(v);
    }

    public void updateSubTitle() {
        GalleryToolbar toolbar = ((IPicturesView) this.mView).getToolbar();
        ThreadPool.getInstance().submit(new f(this, toolbar, toolbar.getSubtitle()));
    }
}
