package com.samsung.android.gallery.app.ui.list.stories.category.category;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.InternalEvent;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.OnDemandSuggestionItemAdapter;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.NewSearchToolbar;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCatOnDemandViewHolder extends StoriesCatViewHolder {
    private static final boolean IS_TABLET = Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES);
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 1) {
                StoriesCatOnDemandViewHolder.this.onDemandToolbarClicked();
            }
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    };
    private final TextView mNoItemView;
    private final RecyclerView mOnDemandList;
    private final View mOnDemandParent;
    private final OnDemandSuggestionItemAdapter mOnDemandSuggestionsAdapter;
    private final Runnable mSuggestionDataChanged = new a(this);
    /* access modifiers changed from: private */
    public final IStoriesCategoryView mView;
    private int mViewSideMarginDrawerClose;
    private int mViewSideMarginDrawerOpen;

    public StoriesCatOnDemandViewHolder(IStoriesCategoryView iStoriesCategoryView, View view, int i2) {
        super(iStoriesCategoryView, view, i2);
        this.mView = iStoriesCategoryView;
        View inflateViewStub = ViewUtils.inflateViewStub(view.findViewById(R.id.on_demand_view_stub));
        View initRootView = initRootView(iStoriesCategoryView, view, inflateViewStub);
        this.mOnDemandParent = initRootView;
        ViewMarginUtils.setTopPadding(initRootView, view.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_layout_top_padding));
        bindSearchToolbarAsync(view);
        this.mNoItemView = (TextView) inflateViewStub.findViewById(R.id.no_item_description);
        final RecyclerView recyclerView = (RecyclerView) inflateViewStub.findViewById(R.id.my_recycler_view);
        this.mOnDemandList = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(inflateViewStub.getContext(), getLayoutOrientation(), false) {
            public void addView(View view, int i2) {
                int i7;
                super.addView(view, i2);
                int absoluteAdapterPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getAbsoluteAdapterPosition();
                int i8 = 0;
                if (StoriesCatOnDemandViewHolder.this.mView.isDrawerMode()) {
                    i7 = recyclerView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_item_side_margin_drawer);
                } else {
                    i7 = 0;
                }
                ViewMarginUtils.setHorizontalMargin(view, i7);
                if (!StoriesCatOnDemandViewHolder.this.mView.isDrawerMode() && absoluteAdapterPosition != 0) {
                    i8 = recyclerView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_item_top_margin);
                }
                ViewMarginUtils.setTopMargin(view, i8);
            }
        });
        OnDemandSuggestionItemAdapter onDemandSuggestionItemAdapter = new OnDemandSuggestionItemAdapter();
        this.mOnDemandSuggestionsAdapter = onDemandSuggestionItemAdapter;
        recyclerView.setAdapter(onDemandSuggestionItemAdapter);
        initOnDemandRecommendData();
        onDemandSuggestionItemAdapter.setOnItemClickListener(new b(this, 0));
        updateLayout();
    }

    private void bindSearchToolbarAsync(View view) {
        SimpleThreadPool.getInstance().execute(new e(this, view, (ViewGroup) view.findViewById(R.id.search_toolbar_layer)));
    }

    private int getLayoutOrientation() {
        if (IS_TABLET) {
            return this.mView.isLandscape() ^ true ? 1 : 0;
        }
        return 1;
    }

    private void initOnDemandRecommendData() {
        OnDemandSuggestionDataManager.getInstance().init();
        registerSuggestionDataChange();
    }

    private View initRootView(IStoriesCategoryView iStoriesCategoryView, View view, View view2) {
        view2.setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(view.getContext().getColor(R.color.daynight_default_background)), new GradientDrawable(GradientDrawable.Orientation.TL_BR, ResourceUtil.BG_GRADIENT)}));
        ViewUtils.setViewShape(view2, 1, (float) iStoriesCategoryView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_round_radius));
        ViewUtils.setVisibleOrGone(view2, true);
        return view2;
    }

    private void initSearchToolbar(View view) {
        NewSearchToolbar newSearchToolbar = (NewSearchToolbar) view.findViewById(R.id.search);
        newSearchToolbar.setBackground(view.getContext().getDrawable(R.drawable.stories_category_on_demand_query_bar_background));
        newSearchToolbar.getSearchTextView().setOnTouchListener(new g(this));
        newSearchToolbar.getSearchTextView().setFocusable(false);
        newSearchToolbar.setAccessibilityDelegate(this.mAccessibilityDelegate);
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            newSearchToolbar.getSearchTextView().setAutoHandwritingEnabled(false);
        }
        newSearchToolbar.getSearchTextView().setCursorVisible(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSearchToolbarAsync$0(ViewGroup viewGroup, View view, View view2) {
        viewGroup.addView(view);
        initSearchToolbar(view2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSearchToolbarAsync$1(View view, ViewGroup viewGroup) {
        ThreadUtil.postOnUiThread(new f(this, viewGroup, LayoutInflater.from(view.getContext()).inflate(R.layout.recycler_item_on_demand_story_search_toolbar_layout, viewGroup, false), view));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initSearchToolbar$2(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        onDemandToolbarClicked();
        return true;
    }

    /* access modifiers changed from: private */
    public void onDemandToolbarClicked() {
        if (!this.mView.isSelectionMode() && this.mView.getBlackboard() != null) {
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1143));
            this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_ONDEMAND_STORY_TOOLBAR_CLICKED);
        }
    }

    /* access modifiers changed from: private */
    public void onRecommendItemClicked(String str) {
        if (!this.mView.isSelectionMode() && this.mView.getBlackboard() != null) {
            this.mView.getBlackboard().postEvent(EventMessage.obtain(1145, str));
            this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_ONDEMAND_STORY_RECOMMENDED_CLICKED);
        }
    }

    /* access modifiers changed from: private */
    public void onSuggestionChanged() {
        boolean isEmpty = OnDemandSuggestionDataManager.getInstance().getData().isEmpty();
        ViewUtils.setVisibleOrGone(this.mOnDemandList, !isEmpty);
        ViewUtils.setVisibleOrGone(this.mNoItemView, isEmpty);
        RecyclerView recyclerView = this.mOnDemandList;
        if (recyclerView != null) {
            recyclerView.setFocusable(!isEmpty);
        }
        this.mOnDemandSuggestionsAdapter.setData(OnDemandSuggestionDataManager.getInstance().getData());
        updateLayout();
    }

    private void registerSuggestionDataChange() {
        OnDemandSuggestionDataManager.getInstance().registerDataChangedListener(this.mSuggestionDataChanged);
    }

    private void unregisterSuggestionDataChange() {
        OnDemandSuggestionDataManager.getInstance().unregisterDataChangedListener(this.mSuggestionDataChanged);
    }

    private void updateLayout() {
        int i2;
        if (this.mOnDemandList != null) {
            int layoutOrientation = getLayoutOrientation();
            Optional.ofNullable((LinearLayoutManager) this.mOnDemandList.getLayoutManager()).ifPresent(new c(layoutOrientation, 0));
            int i7 = 8388691;
            if (this.mView.isDrawerMode()) {
                this.mViewSideMarginDrawerOpen = this.mView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_side_margin_open_drawer);
                this.mViewSideMarginDrawerClose = this.mView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_side_margin_close_drawer);
                View view = this.mOnDemandParent;
                if (this.mView.isDrawerOpen()) {
                    i2 = this.mViewSideMarginDrawerOpen;
                } else {
                    i2 = this.mViewSideMarginDrawerClose;
                }
                ViewMarginUtils.setHorizontalMargin(view, i2);
                if (layoutOrientation == 1) {
                    ViewMarginUtils.setVerticalPadding(this.mOnDemandList, this.mView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_top_padding), 0);
                } else {
                    int dimensionPixelOffset = this.mView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_vertical_padding_drawer);
                    ViewMarginUtils.setVerticalPadding(this.mOnDemandList, dimensionPixelOffset, dimensionPixelOffset);
                }
                if (layoutOrientation != 1) {
                    i7 = 81;
                }
            } else {
                Optional.ofNullable((LinearLayoutManager) this.mOnDemandList.getLayoutManager()).ifPresent(new d(0));
                ViewMarginUtils.setHorizontalMargin(this.mOnDemandParent, this.mView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_side_margin));
            }
            LinearLayout linearLayout = (LinearLayout) this.mOnDemandList.getParent();
            if (OnDemandSuggestionDataManager.getInstance().getData().isEmpty()) {
                i7 = 17;
            }
            linearLayout.setGravity(i7);
        }
    }

    public void bindInfo() {
        boolean z;
        super.bindInfo();
        MediaData mediaData = this.mMediaData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            z = false;
        } else {
            z = true;
        }
        ViewUtils.setVisibleOrInvisible(this.mCount, z);
        ViewUtils.setVisibleOrInvisible(this.mArrow, z);
    }

    public void clear() {
        super.clear();
        unregisterSuggestionDataChange();
    }

    public void invalidateLayout() {
        super.invalidateLayout();
        updateLayout();
    }

    public boolean onHandleInternalEvent(InternalEvent internalEvent, Object... objArr) {
        if (internalEvent == InternalEvent.SELECT_MODE_CHANGE) {
            ViewUtils.setViewEnabled(this.mOnDemandParent, true ^ this.mView.isSelectionMode());
        } else if (internalEvent == InternalEvent.UPDATE_EXTRA_PADDING) {
            float floatValue = objArr[0].floatValue();
            int i2 = this.mViewSideMarginDrawerOpen;
            int i7 = this.mViewSideMarginDrawerClose;
            ViewMarginUtils.setHorizontalMargin(this.mOnDemandParent, (int) ((((float) (i2 - i7)) * floatValue) + ((float) i7)));
            return true;
        }
        return super.onHandleInternalEvent(internalEvent, objArr);
    }

    public void onHeaderClicked(View view) {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null && mediaData.getCount() > 0) {
            super.onHeaderClicked(view);
        }
    }

    public void recycle() {
        super.recycle();
        clear();
    }
}
