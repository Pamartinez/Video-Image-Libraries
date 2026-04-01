package com.samsung.android.gallery.widget.search.filter;

import Qb.e;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.FilterOnlyThem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.listview.SearchFiltersView;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFiltersViewContainer extends SearchHeaderView implements SearchFiltersView.OnTouchDelegator {
    private SearchFiltersAdapter mAdapter;
    private AllFiltersView mAllFiltersView;
    private final Blackboard mBlackboard;
    private ViewGroup mFilterArea;
    private SearchFiltersView mFiltersView;
    private ViewGroup mFuzzyResultContainer;
    private TextView mFuzzyResultView;
    private String mFuzzyStr;
    private OnlyThemView mOnlyThemView;

    public SearchFiltersViewContainer(Context context, ViewGroup viewGroup, Blackboard blackboard, int i2) {
        super(context, viewGroup);
        this.mBlackboard = blackboard;
        if (blackboard != null) {
            createAdapter(blackboard);
        }
        this.mOnlyThemView.init(blackboard);
        this.mOnlyThemView.setCustomItemLayoutId(i2);
        AllFiltersView allFiltersView = this.mAllFiltersView;
        if (allFiltersView != null) {
            allFiltersView.init(blackboard);
        }
    }

    public static SearchFiltersViewContainer cloneInstance(Context context, SearchFiltersViewContainer searchFiltersViewContainer) {
        return new SearchFiltersViewContainer(context, searchFiltersViewContainer);
    }

    private void createAdapter(Blackboard blackboard) {
        this.mAdapter = new SearchFiltersAdapter(blackboard, getCustomItemLayoutId());
        initFilterListView();
    }

    private Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    private int getOnlyThemTitleSize() {
        return this.mOnlyThemView.getTitleSize();
    }

    private Spannable getSpannableFuzzyString(String str) {
        Resources resources = this.mFuzzyResultView.getResources();
        int i2 = R$string.fuzzy_showing_result_for;
        int indexOf = resources.getString(i2, new Object[]{Log.TAG_SEPARATOR}).indexOf(Log.TAG_SEPARATOR);
        String h5 = C0086a.h('\"', "\"", str);
        String string = this.mFuzzyResultView.getResources().getString(i2, new Object[]{h5});
        int length = h5.length() + indexOf;
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R$color.search_matched_text_color)), indexOf, length, 33);
        spannableString.setSpan(new TypefaceSpan(FontTypefaceUtils.TextFont.ROBOTO_SEMI_BOLD.getTypeface()), indexOf, length, 33);
        return spannableString;
    }

    private boolean hasFilterData(FilterResultsEntry filterResultsEntry) {
        if (filterResultsEntry == null || filterResultsEntry.isEmpty()) {
            return false;
        }
        return true;
    }

    private void initFilterListView() {
        SearchFiltersView searchFiltersView = this.mFiltersView;
        searchFiltersView.setLayoutManager(new LinearLayoutManager(searchFiltersView.getContext(), 0, false) {
            public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
                try {
                    super.onLayoutChildren(recycler, state);
                } catch (IndexOutOfBoundsException e) {
                    String access$000 = SearchFiltersViewContainer.this.TAG;
                    com.samsung.android.gallery.support.utils.Log.e(access$000, "onLayoutChildren failed. e=" + e.getMessage());
                }
            }
        });
        this.mFiltersView.setAdapter(this.mAdapter);
        this.mFiltersView.seslSetHoverScrollEnabled(false);
        this.mFiltersView.setOnTouchDelegator(this);
    }

    private boolean isAllFilterHasSubFilter() {
        AllFiltersView allFiltersView = this.mAllFiltersView;
        if (allFiltersView == null || !allFiltersView.hasSubFilter()) {
            return false;
        }
        return true;
    }

    private boolean isAllViewShownOnly(FilterResultsEntry filterResultsEntry, FilterOnlyThem filterOnlyThem) {
        if (filterOnlyThem != null || hasFilterData(filterResultsEntry) || !isAllFilterHasSubFilter()) {
            return false;
        }
        return true;
    }

    private boolean isFuzzyResultShow(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0() {
        this.mOnlyThemView.isOnlyThemClicked();
    }

    public static SearchFiltersViewContainer newInstance(Context context, ViewGroup viewGroup, Blackboard blackboard, int i2) {
        return new SearchFiltersViewContainer(context, viewGroup, blackboard, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mFiltersView = (SearchFiltersView) view.findViewById(R$id.recycler_list);
        this.mFuzzyResultContainer = (ViewGroup) view.findViewById(R$id.filter_results_fuzzy_container);
        this.mFuzzyResultView = (TextView) view.findViewById(R$id.filter_results_fuzzy);
        this.mFilterArea = (ViewGroup) view.findViewById(R$id.filter_area);
        OnlyThemView onlyThemView = new OnlyThemView();
        this.mOnlyThemView = onlyThemView;
        onlyThemView.bindView(view);
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchAllFilters)) {
            AllFiltersView allFiltersView = new AllFiltersView();
            this.mAllFiltersView = allFiltersView;
            allFiltersView.bindView(view);
            this.mAllFiltersView.setOnlyThemClickedListener(new e(21, this));
            ((LinearLayout.LayoutParams) this.mFiltersView.getLayoutParams()).weight = 1.0f;
        }
    }

    public int getCustomItemLayoutId() {
        return this.mOnlyThemView.getCustomItemLayoutId();
    }

    public int getLayoutId() {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar)) {
            return R$layout.search_filters_view_container2;
        }
        return R$layout.search_filters_view_container;
    }

    public int getSelectedIndex() {
        return this.mAdapter.getSelectedIndex();
    }

    public boolean isAllFilterSelected() {
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || getSelectedIndex() != 0) {
            return false;
        }
        return true;
    }

    public boolean isEnabled() {
        return this.mAdapter.isEnabled();
    }

    public boolean isOnlyThemCollapsed() {
        return this.mOnlyThemView.isCollapsed();
    }

    public boolean isOnlyThemVisible() {
        return this.mOnlyThemView.isVisible();
    }

    public boolean onDelegateTouch(MotionEvent motionEvent, float f) {
        return this.mOnlyThemView.onDelegateTouch(motionEvent, f);
    }

    public void resetOnlyThemView() {
        this.mOnlyThemView.reset();
    }

    public void setEnabled(boolean z) {
        SearchFiltersAdapter searchFiltersAdapter = this.mAdapter;
        if (searchFiltersAdapter != null) {
            searchFiltersAdapter.setEnabled(z);
        }
        this.mOnlyThemView.setEnabled(z);
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        return false;
    }

    public void setRelatedKeywords(FilterResultsEntry filterResultsEntry, String str, FilterOnlyThem filterOnlyThem, String str2) {
        int i2;
        boolean z;
        AllFiltersView allFiltersView = this.mAllFiltersView;
        if (allFiltersView != null) {
            allFiltersView.setData(filterResultsEntry, filterOnlyThem);
        }
        SearchFiltersAdapter searchFiltersAdapter = this.mAdapter;
        if (searchFiltersAdapter != null) {
            searchFiltersAdapter.setData(filterResultsEntry);
            this.mOnlyThemView.inflate(filterOnlyThem);
            this.mOnlyThemView.setEnabled(this.mAdapter.isEnabled());
            ViewGroup viewGroup = this.mFilterArea;
            boolean z3 = true;
            if (hasFilterData(filterResultsEntry) || filterOnlyThem != null || isAllFilterHasSubFilter()) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.setVisibleOrGone(viewGroup, z);
            OnlyThemView onlyThemView = this.mOnlyThemView;
            if (filterOnlyThem == null) {
                z3 = false;
            }
            onlyThemView.setButtonVisible(z3);
            updateFuzzyResult(str);
        }
        ViewGroup viewGroup2 = this.mFilterArea;
        if (isAllViewShownOnly(filterResultsEntry, filterOnlyThem)) {
            i2 = -2;
        } else {
            i2 = -1;
        }
        ViewUtils.setWidth(viewGroup2, i2);
    }

    public void showFuzzyTextOnly() {
        ViewUtils.setVisibleOrGone(getRootView(), true);
        ViewUtils.setVisibleOrGone(this.mFilterArea, false);
    }

    public boolean supportDelegateTouchEvent() {
        return this.mOnlyThemView.supportDelegateTouchEvent();
    }

    public void updateFuzzyResult(String str) {
        if (isFuzzyResultShow(str)) {
            this.mFuzzyStr = str;
            this.mFuzzyResultView.setText(getSpannableFuzzyString(str));
            ViewUtils.setVisibility(this.mFuzzyResultContainer, 0);
            return;
        }
        this.mFuzzyStr = null;
        ViewUtils.setVisibility(this.mFuzzyResultContainer, 8);
    }

    private void createAdapter(Blackboard blackboard, SearchFiltersViewContainer searchFiltersViewContainer) {
        this.mAdapter = new SearchFiltersAdapter(blackboard, searchFiltersViewContainer);
        initFilterListView();
    }

    private SearchFiltersViewContainer(Context context, SearchFiltersViewContainer searchFiltersViewContainer) {
        super(context, searchFiltersViewContainer.getRootView());
        Blackboard blackboard = searchFiltersViewContainer.getBlackboard();
        if (blackboard != null) {
            createAdapter(blackboard, searchFiltersViewContainer);
        }
        this.mBlackboard = blackboard;
        this.mOnlyThemView.init(blackboard);
        this.mOnlyThemView.setTitleSize(searchFiltersViewContainer.getOnlyThemTitleSize());
        AllFiltersView allFiltersView = this.mAllFiltersView;
        if (allFiltersView != null) {
            allFiltersView.init(blackboard);
        }
        if (ViewUtils.isVisible(searchFiltersViewContainer.mFuzzyResultContainer) && !TextUtils.isEmpty(searchFiltersViewContainer.mFuzzyStr)) {
            updateFuzzyResult(searchFiltersViewContainer.mFuzzyStr);
            showFuzzyTextOnly();
        }
    }

    public void initHeaderItem() {
    }

    public void recycle() {
    }
}
