package com.samsung.android.gallery.app.ui.list.stories.highlight;

import C4.j;
import android.view.Menu;
import androidx.core.view.MenuCompat;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sum.core.descriptor.b;
import com.samsung.scsp.media.api.d;
import com.sec.android.gallery3d.R;
import g6.c;
import java.util.Optional;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuUpdater {
    private final EventHandler mEventHandler;
    private final IStoryHighlightView mView;

    public MenuUpdater(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mEventHandler = iStoryHighlightView.getEventHandler();
    }

    /* access modifiers changed from: private */
    public boolean enableDownloadAllMenu() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryBgmWithSEAContentProvider)) {
            return false;
        }
        EventHandler eventHandler = this.mEventHandler;
        DataRequest dataRequest = DataRequest.IS_ALL_BGM_DOWNLOADED;
        Boolean bool = Boolean.FALSE;
        if (((Boolean) eventHandler.requestData(dataRequest, bool)).booleanValue() || ((Boolean) this.mEventHandler.requestData(DataRequest.BGM_TIP_CARD_VISIBLE, bool)).booleanValue() || !PreferenceCache.StoryHighlightBgmDownloadAll.getBoolean()) {
            return false;
        }
        return true;
    }

    private void enableExportMenuGroup(Menu menu, boolean z) {
        if (z) {
            menu.setGroupVisible(R.id.export_mode, true);
        } else {
            menu.removeGroup(R.id.export_mode);
        }
    }

    private void enableGroupDivider(Menu menu) {
        MenuCompat.setGroupDividerEnabled(menu, true);
    }

    /* access modifiers changed from: private */
    public boolean hideFromStoryMenuVisible() {
        boolean z;
        int i2;
        MediaItem currentItem = getCurrentItem();
        MediaItem headerItem = this.mView.getHeaderItem();
        if (currentItem == null || headerItem == null || currentItem.getFileId() != headerItem.getFileId()) {
            z = false;
        } else {
            z = true;
        }
        if (this.mView.getMediaData() != null) {
            i2 = this.mView.getMediaData().getCount();
        } else {
            i2 = 0;
        }
        if (!isKeepPaused() || z || i2 <= 1) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateFavoriteMenu$0(boolean z) {
        return !z;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateFavoriteMenu$2(MenuDataBinding menuDataBinding, MediaItem mediaItem) {
        boolean z;
        if (MediaItemStory.getStoryFavorite(mediaItem) > 0) {
            z = true;
        } else {
            z = false;
        }
        menuDataBinding.setVisible((int) R.id.action_add_to_favorite, (BooleanSupplier) new j(z, 5));
        menuDataBinding.setVisible((int) R.id.action_remove_from_favorite, (BooleanSupplier) new j(z, 6));
    }

    private void prepareMenuGroup(Menu menu, boolean z) {
        if (z) {
            menu.removeGroup(R.id.normal_mode);
            menu.setGroupVisible(R.id.bgm_mode, true);
        } else {
            menu.removeGroup(R.id.bgm_mode);
            menu.setGroupVisible(R.id.normal_mode, true);
        }
        enableExportMenuGroup(menu, !z);
    }

    private void updateBgmDownloadAllMenu(MenuDataBinding menuDataBinding) {
        if (this.mEventHandler.isBgmPickerVisible() && menuDataBinding.hasItem(R.id.action_download_all)) {
            menuDataBinding.setVisible((int) R.id.action_download_all, (BooleanSupplier) new c(this, 0));
        }
    }

    private void updateFavoriteMenu(MenuDataBinding menuDataBinding) {
        Optional.ofNullable(this.mView.getHeaderItem()).ifPresent(new b(28, menuDataBinding));
    }

    private void updateHideFromStoryMenu(MenuDataBinding menuDataBinding) {
        int i2;
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            i2 = R.id.action_hide_from_story;
        } else {
            i2 = R.id.action_remove_from_story;
        }
        menuDataBinding.setVisible(i2, (BooleanSupplier) new c(this, 1));
    }

    private void updateViewOriginalPictureMenu(MenuDataBinding menuDataBinding) {
        boolean z;
        if (menuDataBinding.hasItem(R.id.action_view_original_picture)) {
            if (!isKeepPaused() || !((Boolean) Optional.ofNullable(this.mView.getBlackboard()).map(new d(16)).orElse(Boolean.FALSE)).booleanValue()) {
                z = false;
            } else {
                z = true;
            }
            menuDataBinding.setVisible((int) R.id.action_view_original_picture, z);
        }
    }

    public MediaItem getCurrentItem() {
        PreviewViewHolder currentViewHolder = this.mView.getEventHandler().getCurrentViewHolder();
        if (currentViewHolder != null) {
            return currentViewHolder.getMediaItem();
        }
        return null;
    }

    public boolean isKeepPaused() {
        return ((Boolean) this.mEventHandler.requestData(DataRequest.PLAYER_KEEP_PAUSED, Boolean.FALSE)).booleanValue();
    }

    public void prepareOptionsMenu(Menu menu, MenuDataBinding menuDataBinding) {
        if (menuDataBinding != null) {
            boolean isBgmPickerVisible = this.mEventHandler.isBgmPickerVisible();
            enableGroupDivider(menu);
            prepareMenuGroup(menu, isBgmPickerVisible);
            menuDataBinding.prepareOptionsMenu(menu);
            if (isBgmPickerVisible) {
                updateBgmDownloadAllMenu(menuDataBinding);
                return;
            }
            updateHideFromStoryMenu(menuDataBinding);
            updateFavoriteMenu(menuDataBinding);
            updateViewOriginalPictureMenu(menuDataBinding);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$updateFavoriteMenu$1(boolean z) {
        return z;
    }
}
