package com.samsung.android.gallery.app.ui.list.search.pictures;

import android.text.TextUtils;
import android.view.Menu;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuSupportHelper;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.type.CategoryType;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchPicturesMenuUpdater extends ListMenuUpdater {
    private final SearchPicturesPresenter<?> mPresenter;

    public SearchPicturesMenuUpdater(IBaseListView iBaseListView, SearchPicturesPresenter<?> searchPicturesPresenter) {
        super(iBaseListView, searchPicturesPresenter);
        this.mPresenter = searchPicturesPresenter;
    }

    private boolean hasCloudOnlyItems() {
        for (MediaItem isCloudOnly : this.mInterface.getSelectedItems()) {
            if (isCloudOnly.isCloudOnly()) {
                return true;
            }
        }
        return false;
    }

    private boolean isMotionPhotoSuggestedKeywordResult() {
        String locationKey = getLocationKey();
        if (!TextUtils.isEmpty(locationKey) && "motion_photo".equals(ArgumentsUtil.getArgValue(locationKey, "sub")) && ((LocationKey.isSearchKeyword(locationKey) && "sef_file_type".equals(ArgumentsUtil.getArgValue(locationKey, "term"))) || this.mPresenter.isKeywordSearchedOnSupportMultipleKeyword())) {
            return true;
        }
        return false;
    }

    private boolean isScopedSearchMenuVisible(MenuDataBinding.SelectionMode selectionMode, String str) {
        if (!LocationKey.supportScopedSearch(str) || MenuDataBinding.SelectionMode.NORMAL != selectionMode || this.mPresenter.isSearchToolbarVisible() || !supportSearchToolbar(str)) {
            return false;
        }
        return true;
    }

    private boolean isShowCluster(String str) {
        return false;
    }

    private boolean isSupportMotionPhotoDeleteClip() {
        if (!PreferenceFeatures.OneUi40.MOTION_PHOTO_PLAYER) {
            return false;
        }
        if ((this.mPresenter.isInMotionPhotoCategory(getLocationKey()) || isMotionPhotoSuggestedKeywordResult()) && !hasCloudOnlyItems()) {
            return true;
        }
        return false;
    }

    private boolean isVideoShotModeCategory() {
        String locationKey = getLocationKey();
        if (TextUtils.isEmpty(locationKey) || !locationKey.startsWith("location://search/fileList/Category/ShotMode")) {
            return false;
        }
        return CategoryType.VIDEO_SHOT_MODE_NAME.containsKey(ArgumentsUtil.getArgValue(locationKey, "sub", (String) null));
    }

    private boolean supportSearchToolbar(String str) {
        return UnsafeCast.toBoolean(ArgumentsUtil.getArgValue(str, "searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE));
    }

    public boolean supportCreateMenu() {
        if (isVideoShotModeCategory()) {
            return MenuSupportHelper.supportCreateVideo();
        }
        return super.supportCreateMenu();
    }

    public boolean supportMotionPhotoDelete(int i2) {
        if (!this.mInterface.isSelectionMode()) {
            return this.mPresenter.isInMotionPhotoCategory(getLocationKey());
        }
        if (!super.lambda$updateMotionPhotoMenu$13(i2) || !isSupportMotionPhotoDeleteClip()) {
            return false;
        }
        return true;
    }

    public boolean supportMotionPhotoExport(int i2) {
        if (!super.lambda$updateMotionPhotoMenu$12(i2) || !isSupportMotionPhotoDeleteClip()) {
            return false;
        }
        return true;
    }

    public boolean supportRemoveFromResult() {
        boolean z;
        String locationKey = getLocationKey();
        if (locationKey == null || ((!locationKey.startsWith("location://search/fileList/Category") && !locationKey.startsWith("location://search/fileList/PeopleAllPictures")) || locationKey.startsWith("location://search/fileList/Category/Location") || locationKey.startsWith("location://search/fileList/Category/ShotMode") || isShowCluster(locationKey) || locationKey.startsWith("location://search/fileList/Category/Edited") || locationKey.startsWith("location://search/fileList/Category/AiEdited"))) {
            z = false;
        } else {
            z = true;
        }
        if (PreferenceFeatures.OneUi8x.SEARCH_CATEGORY_SCREENSHOT && LocationKey.isSearchCategoryScreenShot(locationKey)) {
            z &= !ScreenShotFilterType.All.key().equals(ArgumentsUtil.getArgValue(locationKey, "sub"));
        }
        if (!z || this.mPresenter.isKeywordSearchedOnSupportMultipleKeyword()) {
            return false;
        }
        return true;
    }

    public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
        String locationKey = getLocationKey();
        if (!TextUtils.isEmpty(locationKey)) {
            this.mPresenter.mCreaturePicturesDelegate.updateOptionsMenuAction(locationKey, menu);
            MenuDataBinding menuDataBinding = this.mInterface.getMenuDataBinding();
            if (menuDataBinding != null) {
                if (menuDataBinding.hasBinding(R.id.action_remove_from_result)) {
                    menuDataBinding.setVisible((int) R.id.action_remove_from_result, supportRemoveFromResult());
                }
                if (menuDataBinding.hasBinding(R.id.action_view_on_map)) {
                    menuDataBinding.setVisible((int) R.id.action_view_on_map, this.mPresenter.supportViewOnMapView());
                }
                if (menuDataBinding.hasBinding(R.id.action_view_by_category)) {
                    menuDataBinding.setVisible((int) R.id.action_view_by_category, this.mPresenter.supportViewByCategory());
                }
            }
            setMenuVisibility(menu, (int) R.id.action_scoped_search, isScopedSearchMenuVisible(selectionMode, locationKey));
            super.updateOptionsMenuAction(menu, selectionMode);
        }
    }

    public void updatePopupMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode, int i2) {
        super.updatePopupMenuAction(menu, selectionMode, i2);
        setMenuVisibility(menu, (int) R.id.action_remove_from_result, supportRemoveFromResult());
    }
}
