package com.samsung.android.gallery.app.ui.list.stories.header;

import B2.i;
import U9.b;
import android.content.Context;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b6.C0426a;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IPinView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BasePinView<V extends IStoriesView> implements IPinView {
    protected static final boolean ONE_UI_50 = PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;
    protected final String TAG;
    protected StoriesPinAdapter mAdapter;
    protected StoriesPinModel mModel;
    protected RecyclerView mRecyclerView;
    protected final View mRootView;
    protected final V mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PinLayoutManager extends LinearLayoutManager {
        private int mItemPaddingEnd;
        private final RecyclerView mRecyclerView;

        public PinLayoutManager(Context context, RecyclerView recyclerView) {
            super(context, 0, false);
            this.mRecyclerView = recyclerView;
            this.mItemPaddingEnd = context.getResources().getDimensionPixelOffset(R.dimen.stories_pin_recycler_item_margin_end);
        }

        private ListViewHolder getListViewHolder(View view) {
            return (ListViewHolder) this.mRecyclerView.findContainingViewHolder(view);
        }

        private void updateLayout(View view) {
            int i2;
            ListViewHolder listViewHolder = getListViewHolder(view);
            if (listViewHolder != null && listViewHolder.getItemViewType() == 0) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                if (layoutParams.getViewAdapterPosition() == getItemCount() - 1) {
                    i2 = 0;
                } else {
                    i2 = this.mItemPaddingEnd;
                }
                layoutParams.setMarginEnd(i2);
            }
        }

        public void addView(View view, int i2) {
            super.addView(view, i2);
            updateLayout(view);
        }
    }

    public BasePinView(V v) {
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        this.mView = v;
        View inflate = LayoutInflater.from(v.getContext()).inflate(getLayoutId(), (ViewGroup) null, false);
        this.mRootView = inflate;
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.pin_list);
        initialize();
        Log.d(simpleName, "create");
    }

    private int getDimens(int i2) {
        return this.mRootView.getResources().getDimensionPixelOffset(i2);
    }

    private AnalyticsEventId getEventId(MediaItem mediaItem) {
        if (StoryType.isTransitoryType(MediaItemStory.getStoryType(mediaItem))) {
            return AnalyticsEventId.EVENT_STORIES_TRANSITORY_SELECT;
        }
        if (MediaItemStory.getStoryFavorite(mediaItem) > 0) {
            return AnalyticsEventId.EVENT_STORIES_PIN_FAVORITE_SELECT;
        }
        return AnalyticsEventId.EVENT_STORIES_PIN_REMINDER_SELECT;
    }

    private int getLayoutId() {
        if (ONE_UI_50) {
            return R.layout.stories_pin_layout_for_transitory;
        }
        return R.layout.stories_pin_layout;
    }

    private StoriesPinModel getModel() {
        if (this.mModel == null) {
            this.mModel = new StoriesPinModel(this.mView.getContext());
        }
        return this.mModel;
    }

    private String getScreenId() {
        return AnalyticsScreenId.SCREEN_EVENT_VIEW_NORMAL.toString();
    }

    private String getTransitoryDetail(MediaItem mediaItem) {
        int storyType = MediaItemStory.getStoryType(mediaItem);
        if (storyType == StoryType.N_YEARS_AGO.getValue()) {
            return AnalyticsDetail.EVENT_DETAIL_TRANSITORY_N_YEAR_AGO.toString();
        }
        if (storyType == StoryType.BEST_MOMENT_OF_THE_YEAR.getValue()) {
            return AnalyticsDetail.EVENT_DETAIL_TRANSITORY_BEST_MOMENT_YEAR.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_TRANSITORY_RECENT_HIGHLIGHTS.toString();
    }

    /* access modifiers changed from: private */
    public boolean onEmptySpaceSecondaryClick(View view, MotionEvent motionEvent) {
        if (motionEvent.getButtonState() != 2 || motionEvent.getAction() != 0) {
            return false;
        }
        V v = this.mView;
        if (!v.setInputBlock(this.TAG + "_onEmptySpaceSecondaryClick", 500)) {
            return true;
        }
        V v6 = this.mView;
        v6.createPinPopupMenu((ViewGroup) v6.getListView().getRootView(), new PointF(motionEvent.getRawX(), motionEvent.getRawY()), (MediaItem) null);
        return true;
    }

    private void sendLoggingEvent(MediaItem mediaItem) {
        if (StoryType.isTransitoryType(MediaItemStory.getStoryType(mediaItem))) {
            this.mView.postAnalyticsLog(getScreenId(), getEventId(mediaItem), getTransitoryDetail(mediaItem));
        } else if (MediaItemStory.getStoryFavorite(mediaItem) > 0) {
            this.mView.postAnalyticsLog(getScreenId(), getEventId(mediaItem));
        } else {
            this.mView.postAnalyticsLog(getScreenId(), getEventId(mediaItem), getDetailId(mediaItem));
        }
    }

    public void destroy() {
        StoriesPinAdapter storiesPinAdapter = this.mAdapter;
        if (storiesPinAdapter != null) {
            storiesPinAdapter.destroy();
        }
        StoriesPinModel storiesPinModel = this.mModel;
        if (storiesPinModel != null) {
            storiesPinModel.onDestroy();
        }
        Log.d(this.TAG, "destroy");
    }

    public final StoriesPinAdapter getAdapter() {
        if (this.mAdapter == null) {
            StoriesPinAdapter storiesPinAdapter = new StoriesPinAdapter(this.mView.getContext(), new b(28, this));
            this.mAdapter = storiesPinAdapter;
            storiesPinAdapter.setOnItemClickListener(new C0426a(this));
            this.mAdapter.setOnItemSecondaryClickListener(new C0426a(this));
            if (supportSelection()) {
                this.mAdapter.setOnItemLongClickListener(new C0426a(this));
            }
        }
        return this.mAdapter;
    }

    public int getDataCount() {
        return getAdapter().getItemCount();
    }

    public String getDetailId(MediaItem mediaItem) {
        if (MediaItemStory.getStoryFavorite(mediaItem) <= 0) {
            return null;
        }
        if (StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem))) {
            return AnalyticsDetail.EVENT_DETAIL_STORY_PICTURE_NEW.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_STORY_PICTURE_VIEWED.toString();
    }

    public RecyclerView getListView() {
        return this.mRecyclerView;
    }

    public int getPaddingBottomResId() {
        if (ONE_UI_50) {
            return R.dimen.stories_pin_container_padding_bottom_for_transitory;
        }
        return R.dimen.stories_pin_container_padding_bottom;
    }

    public int getPaddingTopResId() {
        if (ONE_UI_50) {
            return R.dimen.stories_pin_container_padding_top_for_transitory;
        }
        return R.dimen.stories_pin_container_padding_top;
    }

    public List<PreviewViewHolder> getPreviewViewHolders() {
        ArrayList arrayList = new ArrayList();
        PinLayoutManager pinLayoutManager = (PinLayoutManager) this.mRecyclerView.getLayoutManager();
        if (pinLayoutManager != null) {
            int findLastVisibleItemPosition = pinLayoutManager.findLastVisibleItemPosition();
            for (int findFirstVisibleItemPosition = pinLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                arrayList.add((PreviewViewHolder) this.mRecyclerView.findViewHolderForLayoutPosition(findFirstVisibleItemPosition));
            }
        }
        return arrayList;
    }

    public int getRecyclerViewSizeResId() {
        if (ONE_UI_50) {
            return R.dimen.stories_pin_recycler_view_size_for_transitory;
        }
        return R.dimen.stories_pin_recycler_view_size;
    }

    public int getSelectCount() {
        return 0;
    }

    public ArrayList<MediaItem> getSelectedItems() {
        return new ArrayList<>();
    }

    public View getView() {
        return this.mRootView;
    }

    public void handleResolutionChange(int i2) {
        int dimens = getDimens(getPaddingTopResId());
        int dimens2 = getDimens(getPaddingBottomResId());
        View view = this.mRootView;
        view.setPaddingRelative(view.getPaddingStart(), dimens, this.mRootView.getPaddingEnd(), dimens2);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mRootView.getLayoutParams();
        if (marginLayoutParams != null) {
            marginLayoutParams.height = -2;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mRecyclerView.getLayoutParams();
        if (marginLayoutParams2 != null) {
            marginLayoutParams2.height = getDimens(getRecyclerViewSizeResId());
        }
        PinLayoutManager pinLayoutManager = (PinLayoutManager) this.mRecyclerView.getLayoutManager();
        int findFirstVisibleItemPosition = pinLayoutManager.findFirstVisibleItemPosition();
        pinLayoutManager.setItemPrefetchEnabled(false);
        this.mRecyclerView.setItemViewCacheSize(0);
        this.mRecyclerView.getRecycledViewPool().clear();
        ViewUtils.removeAllViews(this.mRecyclerView);
        pinLayoutManager.scrollToPosition(findFirstVisibleItemPosition);
    }

    public void initialize() {
        this.mRecyclerView.setLayoutManager(new PinLayoutManager(this.mRootView.getContext(), this.mRecyclerView));
        this.mRecyclerView.setAdapter(getAdapter());
        this.mRecyclerView.setOnTouchListener(new i(16, this));
        getAdapter().setModel(getModel());
    }

    public void onDataChangedOnUi(boolean z) {
        boolean z3;
        int itemCount = getAdapter().getItemCount();
        View view = this.mRootView;
        if (itemCount > 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewUtils.setVisibleOrGone(view, z3);
        Log.d(this.TAG, "onDataChangedOnUi", Integer.valueOf(itemCount));
    }

    public void onItemClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        V v = this.mView;
        if (v.setInputBlock(this.TAG + "_onItemClicked", 500) && !SharedTransition.isInReturnTransition(this.mView.getBlackboard())) {
            Blackboard blackboard = this.mView.getBlackboard();
            StorySharedTransitionHelper.addStoryAlbumTransition(blackboard, listViewHolder, mediaItem);
            if (blackboard != null) {
                String build = new StoryUriBuilder(blackboard, (ThumbnailInterface) mediaItem).withPosition(i2).build();
                String str = this.TAG;
                Log.d(str, "moveToMemoryPictures id=" + MediaItemStory.getStoryId(mediaItem));
                blackboard.post("command://MoveURL", build);
                sendLoggingEvent(mediaItem);
            }
        }
    }

    public boolean onItemLongClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return false;
    }

    public void onItemSecondaryClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
        V v = this.mView;
        if (v.setInputBlock(this.TAG + "_onItemSecondaryClicked", 500)) {
            this.mView.clearAdvancedMouseFocus();
            V v6 = this.mView;
            v6.createPinPopupMenu((ViewGroup) v6.getListView().getRootView(), pointF, mediaItem);
        }
    }

    public void setPaddingForAlignBaseList(int i2, int i7) {
        this.mRecyclerView.setPadding(i2, 0, i7, 0);
    }

    public boolean supportSelection() {
        return false;
    }

    public void hide() {
    }

    public void onSelectAll() {
    }

    public void onUnselectAll() {
    }

    public void show() {
    }

    public void onSelectModeChanged(boolean z) {
    }

    public void setEventListener(Consumer<Integer> consumer) {
    }
}
