package com.samsung.android.gallery.app.ui.list.search.pictures.screenshot;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesFragment;
import com.samsung.android.gallery.app.ui.list.search.pictures.screenshot.ISearchScreenShotPicturesView;
import com.samsung.android.gallery.app.ui.list.search.pictures.screenshot.SearchScreenShotPicturesPresenter;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchScreenShotPicturesFragment<V extends ISearchScreenShotPicturesView, P extends SearchScreenShotPicturesPresenter> extends SearchPicturesFragment<V, P> implements ISearchScreenShotPicturesView {
    private boolean mIsScopedSearchVisible;

    private String createLocationKeyWithSubKey(MediaItem mediaItem, int i2) {
        if (ScreenShotFilterType.All.key().equals(mediaItem.getSubCategory())) {
            return ArgumentsUtil.removeArg(getLocationKey(), "sub_sub");
        }
        ArrayList arrayList = new ArrayList(new ArrayList(Arrays.asList(ArgumentsUtil.getArgValue(getLocationKey(), "sub_sub", "").split(GlobalPostProcInternalPPInterface.SPLIT_REGEX))).subList(0, i2));
        arrayList.add(mediaItem.getSubCategory());
        return ArgumentsUtil.appendArgs(getLocationKey(), "sub_sub", String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
    }

    private void moveToAll() {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setSubCategory(ScreenShotFilterType.All.key());
        onHeaderItemClicked((View) null, 0, mediaItem, (Bitmap) null);
    }

    public void bindHeaderData(SearchHeaderView searchHeaderView) {
        ArrayList arrayList;
        MediaData mediaData;
        MediaData childMediaData = this.mMediaData.getChildMediaData("location://search/fileList/Category/ScreenShot/" + ArgumentsUtil.getArgValue(getLocationKey(), "sub"));
        String argValue = ArgumentsUtil.getArgValue(getLocationKey(), "sub_sub");
        if (!TextUtils.isEmpty(argValue)) {
            arrayList = new ArrayList(Arrays.asList(argValue.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        } else {
            arrayList = new ArrayList();
        }
        if (!arrayList.isEmpty()) {
            mediaData = this.mMediaData.getChildMediaData("location://search/fileList/Category/ScreenShot/" + ((String) arrayList.get(0)));
        } else {
            mediaData = null;
        }
        searchHeaderView.bindData(childMediaData, mediaData);
    }

    public String getScreenId() {
        return ScreenShotFilterType.getSubCategoryFilesScreenId(ArgumentsUtil.getArgValue(getLocationKey(), "sub", ""));
    }

    public void handleNoResultWithoutSelectedFilter() {
        if (ArgumentsUtil.getArgValue(getLocationKey(), "sub_sub") != null) {
            moveToAll();
        } else {
            super.handleNoResultWithoutSelectedFilter();
        }
    }

    public void onHeaderItemClicked(View view, int i2, MediaItem mediaItem, Bitmap bitmap) {
        if (!isDestroyed()) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SCREEN_SHOT_SUB_SUB_CATEGORY_SELECTED);
            mediaItem.getSubCategory();
            setLocationKey(createLocationKeyWithSubKey(mediaItem, i2));
            ((SearchScreenShotPicturesPresenter) this.mPresenter).replaceByLocationKey();
        }
    }

    public void onScopedSearchVisibilityChanged(boolean z) {
        if (this.mIsScopedSearchVisible != z) {
            this.mIsScopedSearchVisible = z;
            if (z) {
                moveToAll();
            }
            super.setEnabledHeader(!z);
        }
    }

    public void setEnabledHeader(boolean z) {
        if (!this.mIsScopedSearchVisible) {
            super.setEnabledHeader(z);
        }
    }

    public SearchScreenShotPicturesPresenter createPresenter(ISearchScreenShotPicturesView iSearchScreenShotPicturesView) {
        return new SearchScreenShotPicturesPresenter(this.mBlackboard, iSearchScreenShotPicturesView);
    }
}
