package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import A4.C0383s;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.listview.ListAutoDragHandler;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TopResultsClusterResult extends ClusterResult {
    private int[] columnCount;

    public TopResultsClusterResult(View view, EventContext eventContext) {
        super(view, eventContext);
    }

    /* access modifiers changed from: private */
    public int getColumnCount() {
        if (this.columnCount == null) {
            this.columnCount = AppResources.getAppContext().getResources().getIntArray(R.array.search_top_results_column_count);
        }
        return this.columnCount[0];
    }

    private String getIdList() {
        return this.mPresenter.getCarouselClusterMap().get(ClusterResultType.TOP_RESULT).replace("'", "");
    }

    private String getViewerLocationKey() {
        return ArgumentsUtil.appendArgs(getLocationKey(), "ocr_keyword", (String) this.mHandler.getEventContextData("ocr_keyword"));
    }

    /* access modifiers changed from: private */
    public void onAutoDragReady(int i2) {
        ClusterResultViewAdapter clusterResultViewAdapter = this.mClusterViewAdapter;
        if (clusterResultViewAdapter == null || !clusterResultViewAdapter.isAutoDragPossibleFromMainFragment()) {
            Log.w(this.TAG, "onAutoDragReady skip");
            return;
        }
        this.mClusterViewAdapter.getDragSelectTouchListener().reset();
        ListViewHolder listViewHolder = (ListViewHolder) getListView().findViewHolderForAdapterPosition(i2);
        if (listViewHolder != null) {
            this.mHandler.getBlackboard().postEvent(EventMessage.obtain(1043, new Object[]{listViewHolder, listViewHolder.getMediaItem()}));
        } else {
            Log.w(this.TAG, "onAutoDragReady : viewHolder is null");
        }
    }

    public ClusterResultViewAdapter createAdapter() {
        TopResultClusterViewAdapter topResultClusterViewAdapter = new TopResultClusterViewAdapter(this, this.mHandler);
        topResultClusterViewAdapter.setOnAutoDragHandler(new ListAutoDragHandler(new C0383s(5, this)));
        return topResultClusterViewAdapter;
    }

    public RecyclerView.LayoutManager createLayoutManager() {
        return new GalleryGridLayoutManager(this.mHandler.getContext(), getColumnCount()) {
            private int getHintWidthSpace(int i2) {
                return getWidth() - getHintHorizontalPadding(i2);
            }

            private void updateViewSize(View view, int i2) {
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                int hintWidthSpace = getHintWidthSpace(i2) / getRealGridSize(i2);
                if (hintWidthSpace != layoutParams.width || hintWidthSpace != layoutParams.height) {
                    layoutParams.height = hintWidthSpace;
                    layoutParams.width = hintWidthSpace;
                    view.setLayoutParams(layoutParams);
                }
            }

            public void addView(View view, int i2) {
                updateViewSize(view, getSpanCount());
                super.addView(view, i2);
            }

            public int getSpanCount() {
                return TopResultsClusterResult.this.getColumnCount();
            }
        };
    }

    public ClusterResultPresenter createPresenter(EventContext eventContext, IClusterResult iClusterResult) {
        return new TopResultsClusterPresenter(eventContext, iClusterResult);
    }

    public String getCategoryLocationKey() {
        return "";
    }

    public String getClusterItemLocationKey(ListViewHolder listViewHolder, MediaItem mediaItem) {
        return "";
    }

    public String getLocationKey() {
        return new UriBuilder("location://search/fileList/TopResultsPictures").appendArg("title", ArgumentsUtil.getArgValue(this.mHandler.getLocationKey(), "title", "")).appendArg("ids", getIdList()).build();
    }

    public ClusterResultType getType() {
        return ClusterResultType.TOP_RESULT;
    }

    public int getViewStubId() {
        return R.id.top_results_cluster_view_stub;
    }

    public void handleOrientationChange(int i2) {
        this.columnCount = this.mHandler.getContext().getResources().getIntArray(R.array.search_top_results_column_count);
        getListView().setLayoutManager(createLayoutManager());
        getListView().setColumnCount(this.columnCount);
    }

    public boolean isVirtualCtrlKeyPressedAllowablePoint(MotionEvent motionEvent) {
        return !ViewUtils.isTouchedOnView(this.mRecyclerView, motionEvent);
    }

    public void onClusterItemClicked(ListViewHolder listViewHolder, MediaItem mediaItem, int i2) {
        ClusterResultPresenter clusterResultPresenter = this.mPresenter;
        boolean z = true;
        if (clusterResultPresenter == null || !this.mIsEnabled) {
            String str = this.TAG;
            if (clusterResultPresenter != null) {
                z = false;
            }
            Log.w((CharSequence) str, "onClusterItemClicked failed : ", Boolean.valueOf(z), Boolean.valueOf(this.mIsEnabled));
            return;
        }
        if (i2 == 2) {
            AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), AnalyticsEventId.EVENT_TOUCH_EXPAND_BUTTON.toString());
            ClusterResultViewAdapter clusterResultViewAdapter = this.mClusterViewAdapter;
            if (!(clusterResultViewAdapter == null || clusterResultViewAdapter.getSelectableChecker() == null)) {
                this.mHandler.getBlackboard().publish("data://user/pick/ItemChecker", this.mClusterViewAdapter.getSelectableChecker());
            }
            SharedTransition.addSharedElement(this.mHandler.getBlackboard(), listViewHolder.getImage(), SharedTransition.getTransitionName(mediaItem), new TransitionInfo(mediaItem, listViewHolder.getBitmap()));
            this.mHandler.getBlackboard().post("command://HideBottomBar", Boolean.FALSE);
            new VuLauncher(this.mHandler.getBlackboard()).addTrueArgument("from_expand").addTrueArgument("from_top_result").requestBitmapUrgent().launch(getViewerLocationKey(), listViewHolder.getViewPosition(), mediaItem);
        } else {
            AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), AnalyticsEventId.EVENT_SEARCH_TOP_RESULTS.toString());
            SharedTransition.addSharedElement(this.mHandler.getBlackboard(), listViewHolder.getImage(), SharedTransition.getTransitionName(mediaItem), new TransitionInfo(mediaItem, listViewHolder.getBitmap()));
            new VuLauncher(this.mHandler.getBlackboard()).requestBitmapUrgent().launch(getViewerLocationKey(), listViewHolder.getViewPosition(), mediaItem);
        }
        this.mItemClicked = true;
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            this.mHandler.getBlackboard().post("command://event/FeedbackSearchedItem", new Object[]{-1, Long.valueOf(mediaItem.getFileId())});
        }
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            this.mHandler.getBlackboard().postEvent(EventMessage.obtain(8030));
        }
    }

    public void setEnabled(boolean z) {
        if (!supportItemSelection()) {
            super.setEnabled(z);
        }
    }

    public void setVisibility(boolean z) {
        super.setVisibility(z);
        ViewUtils.setVisibleOrGone(this.mTextView, false);
        ViewMarginUtils.setTopMargin(getListView(), getListView().getContext().getResources().getDimensionPixelOffset(R.dimen.search_cluster_results_recycler_view_margin_top));
        ViewMarginUtils.setTopMargin(this.mMainLayout.findViewById(R.id.search_cluster_title_header), 0);
    }

    public boolean supportItemSelection() {
        return true;
    }
}
