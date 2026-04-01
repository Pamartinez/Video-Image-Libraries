package com.samsung.android.gallery.app.ui.list.search.autoComplete;

import B2.i;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.ISearchAutoCompleteView;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompletePresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.progressbar.SearchProgressCircle;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAutoCompleteFragment<V extends ISearchAutoCompleteView, P extends SearchAutoCompletePresenter> extends BaseListFragment<V, P> implements ISearchAutoCompleteView {
    ImageView mBackground;
    SearchProgressCircle mProgressCircle;

    private void resetView() {
        GalleryListView galleryListView = this.mRecyclerView;
        if (galleryListView != null) {
            ViewUtils.removeAllViews(galleryListView);
            this.mRecyclerView.setVisibility(8);
        }
        this.mBackground.setVisibility(8);
    }

    private void updateMarginOnSmallSizeScreenChanged() {
        BaseListViewAdapter baseListViewAdapter;
        if (ResourceCompat.isSmallScreenSizeChanged(getContext()) && (baseListViewAdapter = this.mListAdapter) != null) {
            baseListViewAdapter.notifyItemRangeChanged(0, baseListViewAdapter.getItemCount(), "updateSideMargin");
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mProgressCircle = (SearchProgressCircle) view.findViewById(R.id.progressCircle);
        this.mBackground = (ImageView) view.findViewById(R.id.background_layer);
        view.findViewById(R.id.my_recycler_view).setOnTouchListener(new i(25, this));
    }

    public String getKeyword() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((SearchAutoCompletePresenter) p6).getKeyword();
        }
        return "";
    }

    public int getLayoutId() {
        return R.layout.search_suggestion_keyword_fragment;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_KEYWORDS_LIST.toString();
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        updateMarginOnSmallSizeScreenChanged();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (!isConfigStateChanged(2)) {
            updateMarginOnSmallSizeScreenChanged();
        }
    }

    public void onAttach(Context context) {
        setLocationKey(getTag());
        super.onAttach(context);
    }

    public void onDataChangedOnUi() {
        int i2;
        super.onDataChangedOnUi();
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            if (baseListViewAdapter.getDataCount() > 0) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            this.mBackground.setVisibility(i2);
            this.mRecyclerView.setVisibility(i2);
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        setProgressBarVisibility(!z);
        if (z) {
            resetView();
            ((SearchAutoCompletePresenter) this.mPresenter).resetKeyword();
            return;
        }
        ((SearchAutoCompletePresenter) this.mPresenter).setKeywordFromLocationKey();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        this.mBlackboard.postEvent(EventMessage.obtain(8502));
        return false;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mRecyclerView.drawBottomColor();
    }

    public void setProgressBarVisibility(boolean z) {
        boolean z3;
        SearchProgressCircle searchProgressCircle = this.mProgressCircle;
        if (searchProgressCircle != null) {
            if (!z || isDataPrepared()) {
                z3 = false;
            } else {
                z3 = true;
            }
            searchProgressCircle.updateVisibility(z3);
        }
    }

    public boolean supportActivityToolbar() {
        return !PickerUtil.isNormalLaunchMode(this.mBlackboard);
    }

    public boolean supportSelection() {
        return false;
    }

    public String toString() {
        return getClass().getSimpleName() + Log.TAG_SEPARATOR + hashCode();
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getApplicationContext());
    }

    public SearchAutoCompleteAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new SearchAutoCompleteAdapter(this, ((SearchAutoCompletePresenter) this.mPresenter).getLocationKey());
    }

    public SearchAutoCompletePresenter createPresenter(ISearchAutoCompleteView iSearchAutoCompleteView) {
        return new SearchAutoCompletePresenter(this.mBlackboard, iSearchAutoCompleteView);
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
