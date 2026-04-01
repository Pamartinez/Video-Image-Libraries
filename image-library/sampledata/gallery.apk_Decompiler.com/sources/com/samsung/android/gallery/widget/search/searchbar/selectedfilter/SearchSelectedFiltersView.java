package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import B2.i;
import U9.b;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.root.AllScreenshotFilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.module.search.root.MultiModalFilterResultsEntity;
import com.samsung.android.gallery.module.search.root.PeopleFilterResultsEntity;
import com.samsung.android.gallery.module.search.root.PetFilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FilterAutoCompleteView;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSelectedFiltersView extends LinearLayout {
    private SearchSelectedFiltersAdapter mAdapter;
    private SearchSelectedFiltersViewEditable mEditable;
    private RecyclerView mRecyclerView;
    private View mView;

    public SearchSelectedFiltersView(Context context) {
        this(context, (AttributeSet) null, 0, 0);
    }

    private void addMainFilter(String str, String str2) {
        addMainFilter(str, str2, (Bitmap) null, false, (MediaItem) null);
    }

    public static SearchSelectedFiltersView create(Blackboard blackboard, Context context, Consumer<ViewGroup> consumer) {
        SearchSelectedFiltersView searchSelectedFiltersView = new SearchSelectedFiltersView(context);
        searchSelectedFiltersView.initView();
        searchSelectedFiltersView.initRecyclerView(blackboard, consumer);
        return searchSelectedFiltersView;
    }

    private SearchSelectedFiltersAdapter createAdapter(Blackboard blackboard) {
        if (this.mEditable == null) {
            return new SearchSelectedFiltersAdapter(blackboard);
        }
        SearchSelectedFiltersEditableAdapter searchSelectedFiltersEditableAdapter = new SearchSelectedFiltersEditableAdapter(blackboard);
        SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable = this.mEditable;
        Objects.requireNonNull(searchSelectedFiltersViewEditable);
        searchSelectedFiltersEditableAdapter.setAutoCompleteDataListener(new c(searchSelectedFiltersViewEditable));
        SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable2 = this.mEditable;
        Objects.requireNonNull(searchSelectedFiltersViewEditable2);
        searchSelectedFiltersEditableAdapter.setAutoCompleteViewListener(new d(searchSelectedFiltersViewEditable2));
        return searchSelectedFiltersEditableAdapter;
    }

    private void createEditable(Blackboard blackboard, FilterAutoCompleteView filterAutoCompleteView) {
        this.mEditable = new SearchSelectedFiltersViewEditable(blackboard, filterAutoCompleteView, this.mRecyclerView);
    }

    private FilterResultsEntity createMainKeywordEntity(String str, String str2) {
        String mainKeywordTerm = this.mAdapter.getMainKeywordTerm();
        if ("recommended_id".equals(mainKeywordTerm)) {
            return new PeopleFilterResultsEntity(str, mainKeywordTerm);
        }
        if ("pet_recommended_id".equals(mainKeywordTerm)) {
            return new PetFilterResultsEntity(str, mainKeywordTerm);
        }
        if ("multi_modal".equals(mainKeywordTerm)) {
            return new MultiModalFilterResultsEntity(str2);
        }
        if ("all_screenshot".equals(mainKeywordTerm)) {
            return new AllScreenshotFilterResultsEntity(str);
        }
        FilterResultsEntity filterResultsEntity = new FilterResultsEntity(str, IntelligentSearchIndexFieldIcon.create(mainKeywordTerm, str2));
        filterResultsEntity.addCategory(mainKeywordTerm);
        return filterResultsEntity;
    }

    public static SearchSelectedFiltersView createWithEditable(Blackboard blackboard, Activity activity, FilterAutoCompleteView filterAutoCompleteView, Consumer<ViewGroup> consumer) {
        SearchSelectedFiltersView searchSelectedFiltersView = new SearchSelectedFiltersView(activity);
        searchSelectedFiltersView.initView();
        searchSelectedFiltersView.createEditable(blackboard, filterAutoCompleteView);
        searchSelectedFiltersView.initRecyclerView(blackboard, consumer);
        return searchSelectedFiltersView;
    }

    private void initRecyclerView(Blackboard blackboard, Consumer<ViewGroup> consumer) {
        updateFadingEdgeEnable();
        this.mRecyclerView.setItemAnimator(new SearchToolbarFilterItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mRecyclerView.getContext(), 0, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        SearchSelectedFiltersAdapter createAdapter = createAdapter(blackboard);
        this.mAdapter = createAdapter;
        this.mRecyclerView.setAdapter(createAdapter);
        this.mRecyclerView.seslSetHoverScrollEnabled(false);
        this.mRecyclerView.setOnTouchListener(new i(14, this));
        this.mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                rect.right = (int) view.getResources().getDimension(R$dimen.search_selected_filter_item_gap);
            }
        });
        consumer.accept(this);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        layoutParams.weight = 1.0f;
        setLayoutParams(layoutParams);
        setVisibility(8);
    }

    private void initView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R$layout.search_toolbar_filter_layout, this, true);
        this.mView = inflate;
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R$id.recycler_list);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initRecyclerView$0(View view, MotionEvent motionEvent) {
        if (this.mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY()) != null) {
            return false;
        }
        onEmptyAreaTouched(motionEvent);
        return false;
    }

    private void onEmptyAreaTouched(MotionEvent motionEvent) {
        SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable = this.mEditable;
        if (searchSelectedFiltersViewEditable == null || this.mRecyclerView == null) {
            this.mView.onTouchEvent(motionEvent);
        } else {
            searchSelectedFiltersViewEditable.emptyAreaTouched();
        }
    }

    private String replaceFuzzyKeywordIfSupported(String str) {
        SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable = this.mEditable;
        if (searchSelectedFiltersViewEditable != null) {
            return searchSelectedFiltersViewEditable.replaceFuzzyKeyword(str);
        }
        return str;
    }

    public void autoCompleteDismiss() {
        SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable = this.mEditable;
        if (searchSelectedFiltersViewEditable != null) {
            searchSelectedFiltersViewEditable.dismissAutoCompleteView();
        }
    }

    public void clearDataExceptMainEntity() {
        this.mAdapter.clearDataExceptMainEntity(true);
    }

    public ArrayList<FilterResultsEntity> getFilterResult() {
        SearchSelectedFiltersAdapter searchSelectedFiltersAdapter = this.mAdapter;
        if (searchSelectedFiltersAdapter != null) {
            return searchSelectedFiltersAdapter.mData;
        }
        return null;
    }

    public View getView() {
        return this.mView;
    }

    public boolean hasFilter() {
        if (this.mAdapter.getFilterCount() > 0) {
            return true;
        }
        return false;
    }

    public void initMainKeywordEntity(String str) {
        String argValue = ArgumentsUtil.getArgValue(str, "title", "");
        if (!TextUtils.isEmpty(argValue)) {
            addMainFilter(argValue, ArgumentsUtil.getArgValue(str, "sub", ""));
        }
    }

    public void initSubEntities(ArrayList<FilterResultsEntity> arrayList, boolean z) {
        if (arrayList != null) {
            this.mAdapter.addAllData(arrayList, z);
        }
    }

    public boolean isEditable() {
        if (this.mEditable != null) {
            return true;
        }
        return false;
    }

    public void notifyDataPrepared() {
        this.mAdapter.notifyDataPrepared();
    }

    public void onDestroy() {
        this.mAdapter.clearData(true);
    }

    public void removeFilter(FilterResultsEntity filterResultsEntity) {
        this.mAdapter.removeFilter(filterResultsEntity);
    }

    public void replaceLastFilter(FilterResultsEntity filterResultsEntity, String str) {
        this.mAdapter.replaceLastData(filterResultsEntity);
        this.mRecyclerView.scrollToPosition(this.mAdapter.getItemCount() - 1);
    }

    public void replaceLastFilterToFuzzyKeyword(FilterResultsEntity filterResultsEntity) {
        this.mAdapter.replaceLastDataToFuzzyKeyword(filterResultsEntity);
        this.mRecyclerView.scrollToPosition(this.mAdapter.getItemCount() - 1);
    }

    public void replaceMainFilter(String str) {
        this.mAdapter.clearData(false);
        addMainFilter(str, (String) null, (Bitmap) null, true);
    }

    public void requestFilterViewVoiceRecognition(String str) {
        SearchSelectedFiltersAdapter searchSelectedFiltersAdapter = this.mAdapter;
        if (searchSelectedFiltersAdapter != null) {
            searchSelectedFiltersAdapter.searchKeyword(str);
        }
    }

    public void showIme() {
        SearchSelectedFiltersViewEditable searchSelectedFiltersViewEditable = this.mEditable;
        if (searchSelectedFiltersViewEditable != null) {
            searchSelectedFiltersViewEditable.showIme();
        }
    }

    public void swapFilter(ArrayList<FilterResultsEntity> arrayList) {
        if (arrayList != null) {
            this.mAdapter.swapData(arrayList);
            this.mRecyclerView.scrollToPosition(this.mAdapter.getItemCount() - 1);
        }
    }

    public void updateFadingEdgeEnable() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.setHorizontalFadingEdgeEnabled(!Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY));
            this.mRecyclerView.invalidate();
        }
    }

    public void updateFilter(FilterResultsEntity filterResultsEntity, String str) {
        if (!this.mAdapter.isMainKeywordAdded() && !TextUtils.isEmpty(str)) {
            addMainFilter(replaceFuzzyKeywordIfSupported(str), (String) null);
        }
        this.mAdapter.addData(filterResultsEntity);
        this.mRecyclerView.scrollToPosition(this.mAdapter.getItemCount() - 1);
    }

    public SearchSelectedFiltersView(Context context, AttributeSet attributeSet, int i2, int i7) {
        super(context, attributeSet, i2, i7);
    }

    public void addMainFilter(String str, String str2, Bitmap bitmap, boolean z) {
        addMainFilter(str, str2, bitmap, z, (MediaItem) null);
    }

    public void addMainFilter(String str, String str2, Bitmap bitmap, boolean z, MediaItem mediaItem) {
        FilterResultsEntity createMainKeywordEntity = createMainKeywordEntity(str, str2);
        Optional.ofNullable(mediaItem).ifPresent(new b(14, createMainKeywordEntity));
        if (bitmap != null) {
            createMainKeywordEntity.setThumb(bitmap);
        }
        if (this.mAdapter.isMainKeywordAdded()) {
            this.mAdapter.replaceMainKeywordData(createMainKeywordEntity);
        } else {
            this.mAdapter.addMainKeywordData(createMainKeywordEntity);
        }
        if (z) {
            this.mAdapter.notifyDataSetChanged();
        }
    }
}
