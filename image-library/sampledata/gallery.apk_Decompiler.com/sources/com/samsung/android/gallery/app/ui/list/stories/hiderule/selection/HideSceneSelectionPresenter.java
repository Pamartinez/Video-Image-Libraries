package com.samsung.android.gallery.app.ui.list.stories.hiderule.selection;

import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.story.SaveHideRuleCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.selection.IHideSceneSelectionView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.module.data.HideRuleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class HideSceneSelectionPresenter<V extends IHideSceneSelectionView> extends PicturesPresenter<V> {
    MediaData mHideRuleData;
    /* access modifiers changed from: private */
    public final HashMap<String, MediaItem> mHideSceneCache = new HashMap<>();

    public HideSceneSelectionPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void cacheHideScene() {
        MediaData mediaData = this.mHideRuleData;
        for (int i2 = 0; i2 < mediaData.getCount(); i2++) {
            MediaItem read = mediaData.read(i2);
            if (HideRuleData.isHideScene(read)) {
                for (MediaItem mediaItem : read.getItemsInFolder()) {
                    this.mHideSceneCache.put(getCacheKey(mediaItem.getSubCategory()), mediaItem);
                }
            }
        }
    }

    private String getParentLocationKey() {
        return "location://stories/hideRule";
    }

    public MenuDataBinding createMenuDataBinding() {
        MenuDataBinding menuDataBinding = new MenuDataBinding(R.menu.menu_hide_scene_selection);
        menuDataBinding.addBinding(new MenuDataBinding.MenuData(R.id.action_save) {
            public boolean getDefaultVisibility() {
                return true;
            }
        });
        return menuDataBinding;
    }

    public MenuHandler createMenuHandler() {
        return new HideSceneSelectionMenuHandler();
    }

    public MediaItem[] getAllItems() {
        return ((IHideSceneSelectionView) this.mView).getAllItems();
    }

    public int getHideRuleId(String str) {
        return HideRuleData.of(this.mHideSceneCache.get(getCacheKey(str))).hideRuleId;
    }

    public int getHideSceneCacheCount() {
        return this.mHideSceneCache.size();
    }

    public MediaItem[] getSelectedItems() {
        return ((IHideSceneSelectionView) this.mView).getSelectedItems();
    }

    public int getTitle() {
        return R.string.select_who_to_hide;
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 101) {
            ((IHideSceneSelectionView) this.mView).finishSelf();
            return true;
        } else if (i2 != 1085) {
            return super.handleEvent(eventMessage);
        } else {
            new SaveHideRuleCmd().execute(this, ((IHideSceneSelectionView) this.mView).getHideItems(), ((IHideSceneSelectionView) this.mView).getUnHideItems(), this.mHideSceneCache);
            postAnalyticsLog(AnalyticsEventId.EVENT_STORIES_HIDE_SCENE_DONE);
            return true;
        }
    }

    public void onViewAttach() {
        super.onViewAttach();
        this.mHideRuleData = MediaDataFactory.getInstance(this.mBlackboard).open(getParentLocationKey());
        cacheHideScene();
    }

    public void onViewDestroy() {
        super.onViewDestroy();
        MediaData mediaData = this.mHideRuleData;
        if (mediaData != null) {
            mediaData.close();
            this.mHideRuleData = null;
        }
    }

    public void updateToolbar(Toolbar toolbar) {
        super.updateToolbar(toolbar);
        if (!((IHideSceneSelectionView) this.mView).isSelectionMode()) {
            setNavigationUpButton(toolbar);
            toolbar.setTitle(getTitle());
            if (((IHideSceneSelectionView) this.mView).getAppbarLayout() != null) {
                ((IHideSceneSelectionView) this.mView).getAppbarLayout().setTitle(getTitle());
            }
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this) {
            private boolean isVisibilitySave() {
                if (!PreferenceFeatures.OneUi6x.SUPPORT_NEW_HIDE_SCENE_SELECTION || HideSceneSelectionPresenter.this.getSelectedItemCount() > 0 || HideSceneSelectionPresenter.this.mHideSceneCache.size() > 0) {
                    return true;
                }
                return false;
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                if (MenuDataBinding.SelectionMode.SELECT.equals(selectionMode)) {
                    setMenuVisibility(menu, (int) R.id.action_save, isVisibilitySave());
                }
            }
        };
    }

    public String getCacheKey(String str) {
        return str;
    }

    public void prepareBottomMenu(Menu menu) {
    }
}
