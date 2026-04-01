package com.samsung.android.gallery.app.ui.list.search;

import android.content.Context;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.album.StartPrivateAlbumCmd;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchPresenter<V extends ISearchView> extends BaseListPresenter<V> implements SearchToolbar.OptionMenuListener {
    private boolean mLocationAuthEnabled = false;
    private SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SearchMenuUpdater extends ListMenuUpdater {
        public SearchMenuUpdater(V v) {
            super(v, SearchPresenter.this);
        }

        public MenuDataBinding.ItemMode getItemMode() {
            if (getSelectedItemCountForMenuUpdate() > 0) {
                return MenuDataBinding.ItemMode.SELECTED_ITEM;
            }
            return MenuDataBinding.ItemMode.ANY_ITEM;
        }

        public boolean supportEditMyQuery() {
            MediaData childMediaData = SearchPresenter.this.getMediaData().getChildMediaData("location://search/fileList/Category/MyQuery");
            if (childMediaData == null || childMediaData.getCount() <= 0) {
                return false;
            }
            return true;
        }

        public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
            setMenuVisibility(menu, (int) R.id.action_edit_quick_search, supportEditMyQuery());
        }
    }

    public SearchPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void destroySearchView() {
        this.mSearchToolbarDelegate.onDestroy();
    }

    private boolean enableSearchToolbar(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || !"Activity".equals(mediaItem.getCategory())) {
            return true;
        }
        return false;
    }

    private boolean isTrash(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || !"Activity".equals(mediaItem.getCategory()) || !"Trash".equals(mediaItem.getSubCategory())) {
            return false;
        }
        return true;
    }

    private boolean showCluster(String str) {
        return false;
    }

    public void checkLocationAuth() {
        boolean isEnabled = SettingPreference.LocationAuth.isEnabled();
        if (isEnabled != this.mLocationAuthEnabled) {
            this.mLocationAuthEnabled = isEnabled;
            forceReloadData();
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_search);
        MenuDataBinder.bindActionSearchSetting(menuDataBinding);
        MenuDataBinder.bindActionTrash(menuDataBinding);
        MenuDataBinder.bindActionSettings(menuDataBinding);
        MenuDataBinder.bindActionEditMyQuery(menuDataBinding);
        return menuDataBinding;
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.search);
    }

    public void handleDensityChange() {
        this.mSearchToolbarDelegate.handleDensityChange();
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 103) {
            if (i2 == 1026) {
                checkLocationAuth();
            } else if (i2 != 3001) {
                if (i2 == 8023) {
                    Optional.ofNullable(((ISearchView) this.mView).getSearchHeaderView()).ifPresent(new n(1));
                } else if (this.mSearchToolbarDelegate.handleEvent(eventMessage) || super.handleEvent(eventMessage)) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }
        forceReloadData();
        return true;
    }

    public void initMenu() {
        super.initMenu();
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(4));
    }

    public void onCategoryExpansionClick(String str) {
        VisualSearchLoggerHelper.postAnalyticsOnClickVieMoreArrow(getScreenId(), getLocationKey());
        this.mBlackboard.erase(str);
        this.mBlackboard.post("command://MoveURL", str);
    }

    public void onEmptyViewVisibilityChanged(View view) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(17, view));
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(9, Boolean.valueOf(z)));
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(getScreenId(), mediaItem.getCategory(), mediaItem.getSubCategory(), CreatureData.hasName(mediaItem));
        if (isTrash(mediaItem)) {
            new LaunchTrashBinCmd().execute(this, Boolean.TRUE);
            return;
        }
        String build = new SearchLocationKeyBuilder(mediaItem, this.mBlackboard).showCluster(showCluster(mediaItem.getCategory())).searchToolbarEnabled(enableSearchToolbar(mediaItem)).build();
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || (!"PrivateAlbum".equals(mediaItem.getSubCategory()) && !LocationKey.isPrivateAlbum(build))) {
            this.mBlackboard.post("command://MoveURL", build);
        } else {
            new StartPrivateAlbumCmd().execute(this, new Object[0]);
        }
    }

    public void onViewCreate() {
        super.onViewCreate();
        this.mLocationAuthEnabled = SettingPreference.LocationAuth.isEnabled();
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        setSearchView();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        destroySearchView();
    }

    public void onViewResume() {
        super.onViewResume();
        this.mSearchToolbarDelegate.onResume();
        FragmentVolatileStatus.resetVolatile();
    }

    public void setSearchView() {
        this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.build(this);
    }

    public void updateSearchToolbarHorizontalPadding(int i2) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(22, Integer.valueOf(i2)));
    }

    public void updateToolbar(Toolbar toolbar) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(2));
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new SearchMenuUpdater(v);
    }
}
