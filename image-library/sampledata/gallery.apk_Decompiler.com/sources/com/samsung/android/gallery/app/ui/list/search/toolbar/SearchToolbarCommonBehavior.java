package com.samsung.android.gallery.app.ui.list.search.toolbar;

import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.search.abstraction.FragmentVolatileStatus;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchToolbarCommonBehavior {
    private final boolean mIsPickMode;
    private final IBaseListView mView;

    public SearchToolbarCommonBehavior(IBaseListView iBaseListView, boolean z) {
        this.mView = iBaseListView;
        this.mIsPickMode = z;
    }

    private boolean needToCollapseClipboardArea() {
        if (!this.mIsPickMode) {
            return false;
        }
        if (this.mView.isLandscape() || this.mView.isInMultiWindowMode()) {
            return true;
        }
        return false;
    }

    private void showAutoCompleteView(String str) {
        this.mView.getBlackboard().post("command://MoveURL", ArgumentsUtil.appendArgs("location://search/AutoComplete", "keyword", str));
    }

    private void showRecommendationView() {
        this.mView.getBlackboard().post("command://MoveURL", "location://search/fileList/Recommendation");
    }

    public void onClickCloseButton(SearchToolbar searchToolbar) {
        FragmentVolatileStatus.setVolatile();
        showRecommendationView();
    }

    public void onFocusChanged(SearchToolbar searchToolbar, boolean z) {
        if (z) {
            searchToolbar.clearFocus();
            if (TextUtils.isEmpty(searchToolbar.getQuery())) {
                showRecommendationView();
            } else {
                showAutoCompleteView(searchToolbar.getQuery().toString());
            }
            if (needToCollapseClipboardArea()) {
                this.mView.getBlackboard().post("command://OperateClipboardArea", Boolean.FALSE);
            }
            FragmentVolatileStatus.setVolatile();
        }
    }
}
