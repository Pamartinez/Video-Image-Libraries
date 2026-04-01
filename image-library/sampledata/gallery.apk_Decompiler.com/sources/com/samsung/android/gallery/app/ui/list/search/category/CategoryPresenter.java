package com.samsung.android.gallery.app.ui.list.search.category;

import A.a;
import A4.C0381p;
import I5.e;
import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import bc.d;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.category.ICategoryView;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegate;
import com.samsung.android.gallery.app.ui.list.search.toolbar.SearchToolbarDelegateFactory;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarEvent;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryPresenter<V extends ICategoryView> extends BaseListPresenter<V> implements SearchToolbar.OptionMenuListener {
    private boolean mLocationAuthEnabled;
    protected SearchToolbarDelegate mSearchToolbarDelegate = SearchToolbarDelegateFactory.buildEmpty();

    public CategoryPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void destroySearchView() {
        this.mSearchToolbarDelegate.onDestroy();
    }

    private String getTargetLocation(String str) {
        String locationKey = getLocationKey();
        if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER) || !LocationKey.isSearchCategoryPeopleAndPets(locationKey)) {
            return locationKey;
        }
        if ("Pet".equals(str)) {
            return "location://search/fileList/Category/Pet";
        }
        return "location://search/fileList/Category/People";
    }

    private boolean isFromTabMoreMenu() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "searchFromTabMenu", false);
    }

    private boolean isShowCluster(String str) {
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$onDataPrepared$3(ThreadPool.JobContext jobContext) {
        updateSubTitle(((ICategoryView) this.mView).getToolbar());
        TranslationManager.getInstance().loadTranslationMap(AppResources.getAppContext());
        return null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postAnalyticsLogCreatureCount$4() {
        int i2;
        if (!isDestroyed()) {
            int dataCount = getDataCount();
            if (((ICategoryView) this.mView).getAdapter().supportHeader()) {
                i2 = dataCount + 1;
            } else {
                i2 = dataCount;
            }
            int i7 = 0;
            int i8 = 0;
            for (int supportHeader = ((ICategoryView) this.mView).getAdapter().supportHeader(); supportHeader < i2; supportHeader++) {
                MediaItem mediaItemFromCache = ((ICategoryView) this.mView).getAdapter().getMediaItemFromCache(supportHeader);
                if (mediaItemFromCache == null) {
                    mediaItemFromCache = ((ICategoryView) this.mView).getAdapter().getMediaItemSync(supportHeader);
                }
                if (CreatureData.hasName(mediaItemFromCache)) {
                    if (mediaItemFromCache.isPeople()) {
                        i7++;
                    } else if (mediaItemFromCache.isPet()) {
                        i8++;
                    }
                }
            }
            StringCompat stringCompat = this.TAG;
            StringBuilder h5 = a.h(i7, i8, "creature count : (", ArcCommonLog.TAG_COMMA, ") / ");
            h5.append(dataCount);
            Log.d(stringCompat, h5.toString());
            VisualSearchLoggerHelper.postAnalyticsPeopleCount(getScreenId(), dataCount, i7);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSubTitle$2(Toolbar toolbar, String str) {
        try {
            if (!TextUtils.equals(toolbar.getSubtitle(), str)) {
                toolbar.setSubtitle((CharSequence) str);
            }
        } catch (Exception e) {
            StringCompat stringCompat = this.TAG;
            Log.se(stringCompat, "updateSubtitle failed e=" + e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$updateToolbar$1(Toolbar toolbar, ThreadPool.JobContext jobContext) {
        return updateSubTitle(toolbar);
    }

    private Object updateSubTitle(Toolbar toolbar) {
        String str;
        if (!((ICategoryView) getView()).isDataPrepared()) {
            return null;
        }
        int dataCount = getDataCount();
        if (dataCount != 0) {
            str = ((ICategoryView) this.mView).getPropertyHelper().getCategorySubTitle(getContext(), dataCount);
        } else {
            str = null;
        }
        ThreadUtil.postOnUiThread(new d((Object) this, (Object) toolbar, (Object) str, 24));
        return null;
    }

    public void checkLocationAuth() {
        boolean isEnabled = SettingPreference.LocationAuth.isEnabled();
        if (isEnabled != this.mLocationAuthEnabled) {
            this.mLocationAuthEnabled = isEnabled;
            forceReloadData();
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        return MenuFactory.create(getLocationKey());
    }

    public String getLabelForAccessibility(Context context) {
        return (String) Optional.ofNullable(((ICategoryView) this.mView).getPropertyHelper()).map(new G9.a(context, 3)).orElse((Object) null);
    }

    public int getSearchToolbarHeight() {
        if (this.mSearchToolbarDelegate.isVisible()) {
            return this.mSearchToolbarDelegate.getDimAreaHeight();
        }
        return 0;
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
                if (i2 == 8008) {
                    onListItemClickInternal(eventMessage.arg1, (MediaItem) eventMessage.obj);
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
        if (!skipInitMenu()) {
            super.initMenu();
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(4));
        }
    }

    public boolean isEnableSearchToolbar() {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            return false;
        }
        return UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(getLocationKey(), "searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE));
    }

    public boolean isSearchToolbarEnabled() {
        return !this.mSearchToolbarDelegate.isEmpty();
    }

    public boolean isVolatile() {
        return FragmentVolatileStatus.isVolatile();
    }

    public void launchHidePeopleAndPetsView() {
        String str;
        Blackboard blackboard = this.mBlackboard;
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                str = "location://search/fileList/Category/HiddenPeopleAndPets";
            } else {
                str = "location://search/fileList/Category/HiddenPeople";
            }
        } else if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            str = "location://search/fileList/Category/PeopleAndPetsHide";
        } else {
            str = "location://search/fileList/Category/PeopleHide";
        }
        blackboard.post("command://MoveURL", str);
    }

    public void onDataPrepared() {
        super.onDataPrepared();
        if (!isSearchToolbarEnabled()) {
            ThreadPool.getInstance().submit(new C0381p(8, this));
        }
    }

    public void onEmptyViewVisibilityChanged(View view) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(17, view));
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        boolean z;
        String category = mediaItem.getCategory();
        VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(getScreenId(), mediaItem.getCategory(), mediaItem.getSubCategory(), CreatureData.hasName(mediaItem));
        Blackboard blackboard = this.mBlackboard;
        SearchLocationKeyBuilder showCluster = new SearchLocationKeyBuilder(mediaItem, blackboard).locationKey(ArgumentsUtil.removeArgs(getTargetLocation(category))).showCluster(isShowCluster(category));
        if (isSearchToolbarEnabled() || LocationKey.supportScopedSearch(getLocationKey())) {
            z = true;
        } else {
            z = false;
        }
        blackboard.post("command://MoveURL", showCluster.searchToolbarEnabled(z).fromMoreMenu(isFromTabMoreMenu()).build());
    }

    public void onViewCreated(View view) {
        super.onViewCreated(view);
        this.mLocationAuthEnabled = SettingPreference.LocationAuth.isEnabled();
        if (isEnableSearchToolbar()) {
            this.mSearchToolbarDelegate = SearchToolbarDelegateFactory.build(this);
        }
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        destroySearchView();
    }

    public void onViewResume() {
        super.onViewResume();
        this.mSearchToolbarDelegate.onResume();
    }

    public void postAnalyticsLogCreatureCount() {
        SimpleThreadPool.getInstance().execute(new C0451a(27, this));
    }

    public boolean skipInitMenu() {
        if ((LocationKey.isSearchCategoryDocuments(getLocationKey()) || LocationKey.isSearchCategoryScreenShot(getLocationKey()) || LocationKey.isSearchCategoryPeople(getLocationKey()) || LocationKey.isSearchCategoryPeopleAndPets(getLocationKey()) || LocationKey.isSearchCategoryCreatureSelect(getLocationKey()) || LocationKey.isSearchCategoryMyQuery(getLocationKey()) || LocationKey.isSearchCategoryHiddenPeopleMatch(getLocationKey()) || LocationKey.isSearchCategoryHiddenPeopleAndPetsMatch(getLocationKey()) || LocationKey.isSelectMe(getLocationKey()) || LocationKey.isSearchCategoryShotMode(getLocationKey())) && !PickerUtil.isPickerMode(this.mBlackboard)) {
            return false;
        }
        return true;
    }

    public void updateSearchToolbarHorizontalPadding(int i2) {
        this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(22, Integer.valueOf(i2)));
    }

    public void updateToolbar(Toolbar toolbar) {
        String str;
        String categoryTitle = ((ICategoryView) this.mView).getPropertyHelper().getCategoryTitle(getContext());
        if (isSearchToolbarEnabled()) {
            this.mSearchToolbarDelegate.handleInternalEvent(SearchToolbarEvent.obtain(2, categoryTitle));
        } else {
            toolbar.setTitle((CharSequence) categoryTitle);
            if (((ICategoryView) this.mView).getPropertyHelper().supportSubTitle()) {
                str = " ";
            } else {
                str = "";
            }
            toolbar.setSubtitle((CharSequence) str);
            setNavigationUpButton(toolbar);
            ThreadPool.getInstance().submit(new e(7, this, toolbar));
        }
        if (((ICategoryView) this.mView).getAppbarLayout() != null) {
            ((ICategoryView) this.mView).getAppbarLayout().setTitle((CharSequence) categoryTitle);
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CategoryMenuUpdater(v, this) {
            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                super.updateOptionsMenuAction(menu, selectionMode);
                if (!Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                    setMenuTitle(menu.findItem(R.id.action_hide_people_and_pets), R.string.hide);
                }
            }
        };
    }
}
