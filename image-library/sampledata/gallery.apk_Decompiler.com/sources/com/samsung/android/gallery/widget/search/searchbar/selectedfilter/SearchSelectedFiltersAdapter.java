package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.search.filter.FilterRemoveHandler;
import com.samsung.android.gallery.module.search.filter.FilterRemoveParam;
import com.samsung.android.gallery.module.search.filter.MultipleFilterKey;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$string;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSelectedFiltersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final boolean SUPPORT_IN_APP_GUI = PreferenceFeatures.isEnabled(PreferenceFeatures.InAppAssistLook);
    protected final Blackboard mBlackboard;
    final ArrayList<FilterResultsEntity> mData = new ArrayList<>();
    protected final FilterRemoveHandler mFilterRemoveHandler = new FilterRemoveHandler();
    private final boolean mFixSubCategory;
    private boolean mIsMainKeywordAdded = false;
    private RecyclerView mRecyclerView;

    public SearchSelectedFiltersAdapter(Blackboard blackboard) {
        this.mBlackboard = blackboard;
        this.mFixSubCategory = needFixSubCategory();
    }

    private boolean isItemAnimationRunning() {
        RecyclerView.ItemAnimator itemAnimator;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            itemAnimator = recyclerView.getItemAnimator();
        } else {
            itemAnimator = null;
        }
        if (itemAnimator == null || !itemAnimator.isRunning()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDeleteButton$0(SearchSelectedFiltersViewHolder searchSelectedFiltersViewHolder, View view) {
        onDeleteClicked(searchSelectedFiltersViewHolder.getLayoutPosition());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDeleteButton$1(SearchSelectedFiltersViewHolder searchSelectedFiltersViewHolder, View view) {
        onDeleteClicked(searchSelectedFiltersViewHolder.getLayoutPosition());
    }

    private boolean needFixSubCategory() {
        if (!PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61 || !LocationKey.isSearchCategoryLocation(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard))) {
            return false;
        }
        return true;
    }

    private void onDeleteClicked(int i2) {
        if (i2 == -1 || this.mData.isEmpty() || i2 >= this.mData.size() || isItemAnimationRunning()) {
            Log.s("FilterBaseAdapter", "onDeleteClicked failed. " + Logger.v(Integer.valueOf(i2), Integer.valueOf(this.mData.size()), Boolean.valueOf(isItemAnimationRunning())));
            return;
        }
        FilterResultsEntity remove = this.mData.remove(i2);
        Log.s("FilterBaseAdapter", "onDeleteClicked. removed entity[" + remove.toString() + "]");
        if (this.mData.isEmpty()) {
            notifyDataSetChanged();
            this.mBlackboard.postEvent(EventMessage.obtain(8016, (Object) null));
            return;
        }
        notifyItemRemoved(i2);
        removeFilter(remove, i2);
        notifyItemChanged(0, "deleteIcon");
    }

    private void setIcon(SearchSelectedFiltersViewHolder searchSelectedFiltersViewHolder, FilterResultsEntity filterResultsEntity, boolean z) {
        if (filterResultsEntity.isThumbnailType()) {
            if (filterResultsEntity.isMultiModal()) {
                searchSelectedFiltersViewHolder.setRoundThumb(filterResultsEntity.getThumb(), filterResultsEntity.getMediaItem(), false);
            } else {
                searchSelectedFiltersViewHolder.setCircleThumb(filterResultsEntity.getThumb(), z);
            }
        } else if (filterResultsEntity.getFieldIconDrawableResId() == null || PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            searchSelectedFiltersViewHolder.setWithoutIcon();
        } else {
            searchSelectedFiltersViewHolder.setIcon(filterResultsEntity.getFieldIconDrawableResId().intValue());
        }
    }

    private void updateDeleteButton(int i2, SearchSelectedFiltersViewHolder searchSelectedFiltersViewHolder, String str) {
        int dimension = (int) searchSelectedFiltersViewHolder.mTitleView.getResources().getDimension(R$dimen.search_toolbar_chip_title_start_margin);
        if (i2 != 0 || !isSubCategoryFixed()) {
            ViewUtils.setVisibleOrGone(searchSelectedFiltersViewHolder.mDeleteIcon, true);
            ViewMarginUtils.setMarginRelative(searchSelectedFiltersViewHolder.mTitleView, Integer.valueOf(dimension), 0, 0, 0);
            searchSelectedFiltersViewHolder.mContainer.setOnClickListener(new a(this, searchSelectedFiltersViewHolder, 0));
            searchSelectedFiltersViewHolder.mDeleteIcon.setOnClickListener(new a(this, searchSelectedFiltersViewHolder, 1));
            searchSelectedFiltersViewHolder.mDeleteIcon.setContentDescription(searchSelectedFiltersViewHolder.itemView.getContext().getString(R$string.delete_tts, new Object[]{str}));
            return;
        }
        ViewUtils.setVisibleOrGone(searchSelectedFiltersViewHolder.mDeleteIcon, false);
        ViewMarginUtils.setMarginRelative(searchSelectedFiltersViewHolder.mTitleView, Integer.valueOf(dimension), 0, Integer.valueOf(dimension), 0);
    }

    public void addAllData(ArrayList<FilterResultsEntity> arrayList, boolean z) {
        Log.s("FilterBaseAdapter", "addData : " + arrayList.size());
        this.mData.addAll(arrayList);
        if (z) {
            notifyItemInserted(getItemCount() - 1);
            notifyItemChanged(0, "deleteIcon");
        }
    }

    public void addData(FilterResultsEntity filterResultsEntity) {
        Log.s("FilterBaseAdapter", "addData : " + filterResultsEntity.toString());
        this.mData.add(filterResultsEntity);
        notifyItemInserted(getItemCount() + -1);
        notifyItemChanged(0, "deleteIcon");
        reopenData(clearExpansionInfo(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard)), filterResultsEntity);
    }

    public void addMainKeywordData(FilterResultsEntity filterResultsEntity) {
        Log.s("FilterBaseAdapter", "addMainKeywordData : " + filterResultsEntity.toString());
        this.mData.add(0, filterResultsEntity);
        this.mIsMainKeywordAdded = true;
    }

    public void clearData(boolean z) {
        this.mData.clear();
        if (z) {
            notifyDataSetChanged();
        }
        this.mIsMainKeywordAdded = false;
    }

    public void clearDataExceptMainEntity(boolean z) {
        if (this.mData.isEmpty()) {
            Log.w("FilterBaseAdapter", "clearDataExceptMainEntity : empty");
            return;
        }
        this.mData.clear();
        this.mData.add(this.mData.get(0));
        if (z) {
            notifyDataSetChanged();
        }
        postReopenEvent(this.mBlackboard, -1, ArgumentsUtil.removeArg(ArgumentsUtil.removeArg(ArgumentsUtil.removeArg(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "SelectedFilter"), "people_only_them"), "fuzzyKeyword"));
    }

    public String clearExpansionInfo(String str) {
        if (!PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            return str;
        }
        if (LocationKey.isSearchKeyword(str) && PreferenceFeatures.OneUi7x.DISABLE_TIMELINE_ON_KEYWORD) {
            str = ArgumentsUtil.appendArgs(str, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
        String removeArg = ArgumentsUtil.removeArg(ArgumentsUtil.removeArg(str, "ExpandedDates"), "AddedCount");
        this.mBlackboard.post("command://event/ClearSearchExpansion", (Object) null);
        return removeArg;
    }

    public FilterResultsEntity createKeywordEntity(String str) {
        FilterResultsEntity filterResultsEntity = new FilterResultsEntity(str, "key_word");
        filterResultsEntity.addRawLabel(str);
        return filterResultsEntity;
    }

    public String createReopenKey(String str, FilterResultsEntity filterResultsEntity) {
        return MultipleFilterKey.build(str, filterResultsEntity);
    }

    public int getFilterCount() {
        return this.mData.size();
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public String getMainKeywordTerm() {
        return ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "term", "key_word");
    }

    public boolean isMainKeywordAdded() {
        return this.mIsMainKeywordAdded;
    }

    public boolean isSubCategoryFixed() {
        return this.mFixSubCategory;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        SearchSelectedFiltersViewHolder searchSelectedFiltersViewHolder = (SearchSelectedFiltersViewHolder) viewHolder;
        if (i2 >= 0 && i2 < getItemCount()) {
            FilterResultsEntity filterResultsEntity = this.mData.get(i2);
            String name = filterResultsEntity.getName();
            boolean z = true;
            if ("usertag".equals(filterResultsEntity.getCategory()) && name.startsWith("#")) {
                name = name.substring(1);
            }
            updateDeleteButton(i2, searchSelectedFiltersViewHolder, name);
            searchSelectedFiltersViewHolder.itemView.setTag(name);
            searchSelectedFiltersViewHolder.setTitle(name);
            if (i2 <= 0 && isSubCategoryFixed()) {
                z = false;
            }
            setIcon(searchSelectedFiltersViewHolder, filterResultsEntity, z);
            if (SUPPORT_IN_APP_GUI) {
                searchSelectedFiltersViewHolder.mContainer.setBackgroundTintList(searchSelectedFiltersViewHolder.itemView.getResources().getColorStateList(R$color.search_filter_chip_color_on_the_in_app_assist_toolbar, (Resources.Theme) null));
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return SearchSelectedFiltersViewHolderFactory.create(viewGroup.getContext(), i2);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getItemViewType() == 0) {
            SearchSelectedFiltersViewHolder searchSelectedFiltersViewHolder = (SearchSelectedFiltersViewHolder) viewHolder;
            searchSelectedFiltersViewHolder.mContainer.setOnClickListener((View.OnClickListener) null);
            searchSelectedFiltersViewHolder.mDeleteIcon.setOnClickListener((View.OnClickListener) null);
            searchSelectedFiltersViewHolder.mDeleteIcon.setContentDescription((CharSequence) null);
            ViewUtils.setVisibleOrGone(searchSelectedFiltersViewHolder.mDeleteIcon, true);
        }
    }

    public void postReopenEvent(Blackboard blackboard, int i2, String str) {
        if (!ArgumentsUtil.getArgValue(str, "people_only_them", false)) {
            blackboard.postEvent(EventMessage.obtain(8013));
        }
        blackboard.postEvent(EventMessage.obtain(1066, clearExpansionInfo(str)));
    }

    public void removeFilter(FilterResultsEntity filterResultsEntity) {
        onDeleteClicked(this.mData.indexOf(filterResultsEntity));
    }

    public void reopenData(String str, FilterResultsEntity filterResultsEntity) {
        this.mBlackboard.postEvent(EventMessage.obtain(1066, createReopenKey(str, filterResultsEntity)));
    }

    public void replaceMainKeywordData(FilterResultsEntity filterResultsEntity) {
        Log.s("FilterBaseAdapter", "replaceMainKeywordData : " + filterResultsEntity.toString());
        if (!this.mData.isEmpty()) {
            this.mData.remove(0);
        }
        this.mData.add(0, filterResultsEntity);
        this.mIsMainKeywordAdded = true;
    }

    public void searchKeyword(String str) {
        addData(createKeywordEntity(str));
    }

    public void swapData(ArrayList<FilterResultsEntity> arrayList) {
        Log.s("FilterBaseAdapter", "swapData : " + arrayList.size());
        this.mData.clear();
        if (arrayList.size() > 0) {
            this.mData.addAll(arrayList.subList(0, 1));
            this.mIsMainKeywordAdded = true;
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public void removeFilter(FilterResultsEntity filterResultsEntity, int i2) {
        postReopenEvent(this.mBlackboard, i2, this.mFilterRemoveHandler.handleTarget(this.mBlackboard, new FilterRemoveParam.FilterRemoveParamBuilder().setNewEntity(this.mData.get(0)).setRemovedEntity(filterResultsEntity).setPosition(i2).build()));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2, List<Object> list) {
        if (list.contains("deleteIcon")) {
            updateDeleteButton(i2, (SearchSelectedFiltersViewHolder) viewHolder, (String) viewHolder.itemView.getTag());
        } else {
            super.onBindViewHolder(viewHolder, i2, list);
        }
    }

    public void notifyDataPrepared() {
    }

    public void replaceLastData(FilterResultsEntity filterResultsEntity) {
    }

    public void replaceLastDataToFuzzyKeyword(FilterResultsEntity filterResultsEntity) {
    }
}
