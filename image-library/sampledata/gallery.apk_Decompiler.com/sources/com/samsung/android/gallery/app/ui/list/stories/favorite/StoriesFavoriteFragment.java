package com.samsung.android.gallery.app.ui.list.stories.favorite;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.stories.favorite.IStoriesFavoriteView;
import com.samsung.android.gallery.app.ui.list.stories.favorite.StoriesFavoritePresenter;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewFragment;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesFavoriteFragment<V extends IStoriesFavoriteView, P extends StoriesFavoritePresenter> extends StoriesPinchViewFragment<V, P> implements IStoriesFavoriteView {
    public View createHeaderView() {
        return null;
    }

    public String getFragmentTag(String str) {
        return "StoriesFavoriteFragment";
    }

    public int getLayoutId() {
        return R.layout.fragment_stories_favorite_layout;
    }

    public String getScreenId() {
        if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_STORIES_FAVORITE_SELECTION.toString();
        }
        return AnalyticsScreenId.SCREEN_STORIES_FAVORITE.toString();
    }

    public void savePinchDepth(String str, int i2) {
        super.savePinchDepth(str, i2);
        P p6 = this.mPresenter;
        if (p6 != null) {
            Blackboard.publishGlobal("command://StoriesViewChanged", Integer.valueOf(((StoriesFavoritePresenter) p6).getCurrentViewDepth()));
        }
    }

    public void setScreenMode() {
        enterFullListScreen(false);
    }

    public void startShrinkAnimation() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
        startDecoAnimationForReturn();
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportPinView() {
        return false;
    }

    public boolean supportTabLayout() {
        return false;
    }

    public StoriesFavoritePresenter createPresenter(IStoriesFavoriteView iStoriesFavoriteView) {
        return new StoriesFavoritePresenter(this.mBlackboard, iStoriesFavoriteView);
    }
}
