package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import A4.C0369d;
import A6.a;
import Ab.e;
import B2.i;
import R6.c;
import V3.b;
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
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.ondemand.OnDemandSuggestionDataManager;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.NewSearchToolbar;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbarOptions;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCatTransitoryItemOnDemandViewHolder extends StoriesCatTransitoryItemViewHolder {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() == 1) {
                StoriesCatTransitoryItemOnDemandViewHolder.this.onDemandToolbarClicked();
            }
            return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    };
    private int mLimitDataCount = Integer.MAX_VALUE;
    private TextView mNoOnDemandDataView;
    private RecyclerView mOnDemandList;
    private View mOnDemandParent;
    private OnDemandSuggestionItemAdapter mOnDemandSuggestionsAdapter;
    private NewSearchToolbar mSearchToolbar;
    private final Runnable mSuggestionDataChanged = new b(13, this);

    public StoriesCatTransitoryItemOnDemandViewHolder(View view, int i2) {
        super(view, i2, "location://search/fileList/Category/Stories/Transitory");
    }

    private void bindSearchToolbarAsync(View view) {
        SimpleThreadPool.getInstance().execute(new c(this, (ViewGroup) view.findViewById(R.id.search_toolbar_layer), view, 23));
    }

    /* access modifiers changed from: private */
    public int getDimensionPixelOffset(int i2) {
        return this.itemView.getResources().getDimensionPixelOffset(i2);
    }

    private int getGravity(int i2, int i7) {
        int i8 = 17;
        if (i2 == 0) {
            return 17;
        }
        if (i7 != 0) {
            i8 = 80;
        }
        return i8 | 8388611;
    }

    private int getOrientation(int i2) {
        int dimensionPixelOffset = (getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_item_vertical_padding) * 2) + getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_item_top_margin) + getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_item_text_size);
        int i7 = dimensionPixelOffset * i2;
        if (ViewUtils.getMeasuredHeight(this.itemView) >= ((int) ((((float) (i7 + ((getDimensionPixelOffset(R.dimen.stories_category_on_demand_search_toolbar_vertical_padding) * 2) + (ViewUtils.getMeasuredHeight(this.itemView.findViewById(R.id.content_title)) + getDimensionPixelOffset(R.dimen.bottom_search_toolbar_container_min_height))))) * 1.1f) - ((float) getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_item_top_margin))))) {
            return 1;
        }
        return 0;
    }

    private void initOnDemandRecommendData() {
        OnDemandSuggestionDataManager.getInstance().init();
        registerSuggestionDataChange();
    }

    private View initRootView(View view) {
        view.setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(view.getContext().getColor(R.color.daynight_default_background)), new GradientDrawable(GradientDrawable.Orientation.TL_BR, ResourceUtil.BG_GRADIENT)}));
        ViewUtils.setVisibleOrGone(view, true);
        return view;
    }

    private void initSearchToolbar(View view) {
        NewSearchToolbar newSearchToolbar = (NewSearchToolbar) view.findViewById(R.id.search);
        newSearchToolbar.getSearchTextView().setOnTouchListener(new i(13, this));
        newSearchToolbar.getSearchTextView().setFocusable(false);
        newSearchToolbar.setAccessibilityDelegate(this.mAccessibilityDelegate);
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            newSearchToolbar.getSearchTextView().setAutoHandwritingEnabled(false);
        }
        newSearchToolbar.getSearchTextView().setCursorVisible(false);
        newSearchToolbar.initEffect();
        newSearchToolbar.onAttached(SearchToolbarOptions.buildEmpty());
        this.mSearchToolbar = newSearchToolbar;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindSearchToolbarAsync$0(View view, ViewGroup viewGroup) {
        ViewUtils.setAlpha(view, 0.0f);
        viewGroup.findViewById(R.id.search_toolbar_visual_effect_view).setBackgroundResource(R.drawable.bottom_search_bar_background);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSearchToolbarAsync$1(ViewGroup viewGroup, View view, View view2) {
        viewGroup.addView(view);
        initSearchToolbar(view2);
        ViewUtils.setAlpha(view, 0.0f);
        view.animate().setDuration(50).alpha(1.0f).withStartAction(new e(view, viewGroup)).start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindSearchToolbarAsync$2(ViewGroup viewGroup, View view) {
        ThreadUtil.postOnUiThread(new a((Object) this, (Object) viewGroup, (Object) LayoutInflater.from(getContext()).inflate(R.layout.recycler_item_on_demand_story_search_toolbar_layout, viewGroup, false), (Object) view, 26));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initSearchToolbar$3(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        onDemandToolbarClicked();
        return true;
    }

    /* access modifiers changed from: private */
    public void onDemandToolbarClicked() {
        this.itemView.setTag(new Object[]{1143, null});
        onItemClickInternal(1003);
    }

    /* access modifiers changed from: private */
    public void onRecommendItemClicked(String str) {
        this.itemView.setTag(new Object[]{1145, str});
        onItemClickInternal(1003);
    }

    /* access modifiers changed from: private */
    public void onSuggestionChanged() {
        List<String> data = OnDemandSuggestionDataManager.getInstance().getData();
        boolean isEmpty = data.isEmpty();
        ViewUtils.setVisibleOrGone(this.mOnDemandList, !isEmpty);
        ViewUtils.setVisibleOrGone(this.mNoOnDemandDataView, isEmpty);
        RecyclerView recyclerView = this.mOnDemandList;
        if (recyclerView != null) {
            recyclerView.setFocusable(!isEmpty);
        }
        if (data.size() > this.mLimitDataCount) {
            data = new ArrayList<>(data.subList(0, this.mLimitDataCount));
        }
        this.mOnDemandSuggestionsAdapter.setData(data);
        updateView(getMediaItem());
        this.itemView.invalidate();
        Log.i(this.TAG, "onSuggestionChanged", Integer.valueOf(data.size()), Boolean.valueOf(ViewUtils.isVisible(this.mOnDemandList)));
    }

    private void registerSuggestionDataChange() {
        OnDemandSuggestionDataManager.getInstance().registerDataChangedListener(this.mSuggestionDataChanged);
    }

    private void unregisterSuggestionDataChange() {
        OnDemandSuggestionDataManager.getInstance().unregisterDataChangedListener(this.mSuggestionDataChanged);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        initOnDemandRecommendData();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mOnDemandParent = initRootView(view.findViewById(R.id.content_parent));
        bindSearchToolbarAsync(view);
        this.mNoOnDemandDataView = (TextView) view.findViewById(R.id.no_item_description);
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.content_title), true);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        this.mOnDemandList = recyclerView;
        this.mOnDemandSuggestionsAdapter = new OnDemandSuggestionItemAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 1, false) {
            public void addView(View view, int i2) {
                int i7;
                int i8;
                int i10;
                if (getOrientation() == 1) {
                    i7 = StoriesCatTransitoryItemOnDemandViewHolder.this.getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_item_top_margin);
                } else {
                    i7 = 0;
                }
                ViewMarginUtils.setTopMargin(view, i7);
                if (getOrientation() == 1) {
                    i8 = 0;
                } else {
                    i8 = StoriesCatTransitoryItemOnDemandViewHolder.this.getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_item_top_margin);
                }
                ViewMarginUtils.setEndMargin(view, i8);
                TextView textView = (TextView) view.findViewById(R.id.title);
                StoriesCatTransitoryItemOnDemandViewHolder storiesCatTransitoryItemOnDemandViewHolder = StoriesCatTransitoryItemOnDemandViewHolder.this;
                if (getOrientation() == 1) {
                    i10 = R.dimen.stories_category_on_demand_list_item_text_size;
                } else {
                    i10 = R.dimen.stories_category_on_demand_list_item_small_text_size;
                }
                textView.setTextSize(0, (float) storiesCatTransitoryItemOnDemandViewHolder.getDimensionPixelOffset(i10));
                super.addView(view, i2);
            }
        });
        recyclerView.setAdapter(this.mOnDemandSuggestionsAdapter);
        this.mOnDemandSuggestionsAdapter.setOnItemClickListener(new U9.b(13, this));
        updateView(getMediaItem());
    }

    public View getScalableView() {
        return getImage();
    }

    public int getSeekTime() {
        return 0;
    }

    public int getViewItemId(View view) {
        return 102;
    }

    public boolean hasImageView() {
        return false;
    }

    public void onClear() {
        unregisterSuggestionDataChange();
        NewSearchToolbar newSearchToolbar = this.mSearchToolbar;
        if (newSearchToolbar != null) {
            newSearchToolbar.destroy();
        }
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibleOrGone(getImage(), false);
        unregisterSuggestionDataChange();
    }

    public void updateView(MediaItem mediaItem) {
        int i2;
        if (this.mOnDemandList != null) {
            int size = OnDemandSuggestionDataManager.getInstance().getData().size();
            int i7 = size;
            while (true) {
                if (i7 < 2) {
                    i7 = 1;
                    break;
                } else if (getOrientation(i7) == 1) {
                    break;
                } else {
                    i7--;
                }
            }
            if (i7 != 1) {
                size = i7;
            }
            this.mLimitDataCount = size;
            int i8 = 0;
            if (i7 == 1) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i2 == 0) {
                this.mOnDemandList.scrollToPosition(0);
            }
            Optional.ofNullable((LinearLayoutManager) this.mOnDemandList.getLayoutManager()).ifPresent(new C0369d(i2, 14));
            ViewUtils.setViewShape(this.mOnDemandParent, 1, (float) getDimensionPixelOffset(R.dimen.stories_category_on_demand_round_radius));
            ViewMarginUtils.setVerticalPadding(this.mNoOnDemandDataView, 0, 0);
            View view = (View) this.mOnDemandList.getParent();
            if (i2 == 0) {
                i8 = getDimensionPixelOffset(R.dimen.stories_category_on_demand_list_top_padding);
            }
            ViewMarginUtils.setTopPadding(view, i8);
            ((LinearLayout) this.mOnDemandList.getParent()).setGravity(getGravity(this.mLimitDataCount, i2));
        }
    }

    public void executeHourglass() {
    }

    public void setViewMatrix() {
    }

    public void onClick(View view) {
    }
}
