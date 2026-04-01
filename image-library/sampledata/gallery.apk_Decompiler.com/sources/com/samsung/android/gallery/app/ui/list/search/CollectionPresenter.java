package com.samsung.android.gallery.app.ui.list.search;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.StartPrivateAlbumCmd;
import com.samsung.android.gallery.app.controller.story.LaunchOnDemandStoryCmd;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListPresenter;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.ICollectionView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinder;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.module.search.root.SearchLocationKeyBuilder;
import com.samsung.android.gallery.module.search.root.VisualSearchLoggerHelper;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollectionPresenter<V extends ICollectionView> extends BaseListPresenter<V> {
    private boolean mLocationAuthEnabled = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class CollectionMenuUpdater extends ListMenuUpdater {
        public CollectionMenuUpdater(V v) {
            super(v, CollectionPresenter.this);
        }

        public MenuDataBinding.ItemMode getItemMode() {
            if (getSelectedItemCountForMenuUpdate() > 0) {
                return MenuDataBinding.ItemMode.SELECTED_ITEM;
            }
            return MenuDataBinding.ItemMode.ANY_ITEM;
        }
    }

    public CollectionPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        OnDemandSuggestionDataManager.getInstance().init();
    }

    private boolean enableSearchToolbar(MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 && "Documents".equals(mediaItem.getCategory())) {
            return false;
        }
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || !"Activity".equals(mediaItem.getCategory())) {
            return true;
        }
        return false;
    }

    private boolean isAskScreenshot(MediaItem mediaItem) {
        if (!PocFeatures.ASK_SCREENSHOT || !TextUtils.equals(mediaItem.getCategory(), "ScreenShot") || !TextUtils.equals(mediaItem.getSubCategory(), "cap_ask")) {
            return false;
        }
        return true;
    }

    private boolean isPrivateAlbum(MediaItem mediaItem, String str) {
        if ((!PocFeatures.SUPPORT_PRIVATE_ALBUM || !"PrivateAlbum".equals(mediaItem.getSubCategory())) && !LocationKey.isPrivateAlbum(str)) {
            return false;
        }
        return true;
    }

    private boolean isTrash(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 || !"Activity".equals(mediaItem.getCategory()) || !"Trash".equals(mediaItem.getSubCategory())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onDateTimeChanged(Object obj, Bundle bundle) {
        if (!isDestroyed()) {
            getBlackboard().publish("command:///event_command", new Object[]{"ON_DATE_TIME_CHANGED"});
        }
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

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://event/dateTimeChanged", new l(2, this)).setWorkingOnUI());
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_collection);
        MenuDataBinder.bindActionSearch(menuDataBinding);
        MenuDataBinder.bindActionSearchSetting(menuDataBinding);
        return menuDataBinding;
    }

    public String getLabelForAccessibility(Context context) {
        return context.getString(R.string.collection);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 != 103) {
            if (i2 == 1026) {
                checkLocationAuth();
                return true;
            } else if (i2 != 1077) {
                if (i2 != 1143) {
                    if (i2 != 3001) {
                        if (i2 != 8503) {
                            return super.handleEvent(eventMessage);
                        }
                    }
                }
                LaunchOnDemandStoryCmd launchOnDemandStoryCmd = new LaunchOnDemandStoryCmd();
                EventContext eventContext = ((ICollectionView) this.mView).getEventContext();
                Object obj = eventMessage.obj;
                if (obj == null) {
                    obj = "";
                }
                launchOnDemandStoryCmd.execute(eventContext, obj, Integer.valueOf(eventMessage.what));
                return true;
            } else {
                this.mBlackboard.publish("command:///event_command", new Object[]{"UPDATE_BADGE"});
                return true;
            }
        }
        forceReloadData();
        return true;
    }

    public void onCategoryExpansionClick(String str) {
        VisualSearchLoggerHelper.postAnalyticsOnClickVieMoreArrow(getScreenId(), ArgumentsUtil.removeArgs(str));
        this.mBlackboard.erase(str);
        this.mBlackboard.post("command://MoveURL", str);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        VisualSearchLoggerHelper.postAnalyticsOnClickCategoryItem(getScreenId(), mediaItem.getCategory(), mediaItem.getSubCategory(), CreatureData.hasName(mediaItem));
        if (isTrash(mediaItem)) {
            new LaunchTrashBinCmd().execute(this, Boolean.TRUE);
        } else if (isAskScreenshot(mediaItem)) {
            this.mBlackboard.postEvent(EventMessage.obtain(8020, new Object[]{Boolean.TRUE, null}));
        } else {
            String build = new SearchLocationKeyBuilder(mediaItem, this.mBlackboard).showCluster(showCluster(mediaItem.getCategory())).searchToolbarEnabled(enableSearchToolbar(mediaItem)).build();
            if (isPrivateAlbum(mediaItem, build)) {
                new StartPrivateAlbumCmd().execute(this, new Object[0]);
            } else {
                this.mBlackboard.post("command://MoveURL", build);
            }
        }
    }

    public void onViewCreate() {
        super.onViewCreate();
        this.mLocationAuthEnabled = SettingPreference.LocationAuth.isEnabled();
    }

    public void onViewResume() {
        super.onViewResume();
        FragmentVolatileStatus.resetVolatile();
    }

    public void updateToolbar(Toolbar toolbar) {
        String str;
        if (PickerUtil.isSinglePickLaunchMode(this.mBlackboard)) {
            str = AppResources.getString(R.string.select_item);
        } else {
            str = null;
        }
        toolbar.setTitle((CharSequence) str);
        toolbar.setNavigationIcon((Drawable) null);
        if (((ICollectionView) this.mView).getAppbarLayout() != null) {
            ((ICollectionView) this.mView).getAppbarLayout().setTitle((int) R.string.collection);
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new CollectionMenuUpdater(v);
    }
}
