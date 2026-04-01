package com.samsung.android.gallery.widget.search.filter;

import Ba.g;
import O3.l;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.filter.SingleFilterKey;
import com.samsung.android.gallery.module.search.root.FilterPriority;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FilterSubListViewAdapter extends AbsFiltersAdapter {
    private final boolean mClickable;
    private final Runnable mDismissDialog;
    private Runnable mOnlyThemClickedListener;
    private ArrayList<FilterResultsEntity> mSubsetKeywordEntry;

    public FilterSubListViewAdapter(Blackboard blackboard, boolean z) {
        super(blackboard);
        this.mClickable = z;
        this.mDismissDialog = null;
    }

    private int getSAValue(FilterResultsEntity filterResultsEntity) {
        int listPriority = FilterPriority.getListPriority(filterResultsEntity);
        if (listPriority == 0) {
            return 1;
        }
        if (listPriority != 1) {
            if (listPriority == 2) {
                return 3;
            }
            if (listPriority != 3) {
                return 0;
            }
            return 5;
        } else if (TextUtils.equals("only_them", filterResultsEntity.getCategory())) {
            return 4;
        } else {
            return 2;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addClickListener$0(SearchFiltersViewHolder searchFiltersViewHolder, View view) {
        ArrayList<FilterResultsEntity> arrayList;
        int bindingAdapterPosition = searchFiltersViewHolder.getBindingAdapterPosition();
        if (bindingAdapterPosition != -1 && (arrayList = this.mSubsetKeywordEntry) != null) {
            FilterResultsEntity filterResultsEntity = arrayList.get(bindingAdapterPosition);
            if (TextUtils.equals("only_them", filterResultsEntity.getCategory())) {
                Optional.ofNullable(this.mOnlyThemClickedListener).ifPresent(new l(0));
            } else if (filterResultsEntity.isSelected()) {
                onUnSelectItem(filterResultsEntity);
            } else {
                onSelectItem(filterResultsEntity, bindingAdapterPosition);
            }
            Optional.ofNullable(this.mDismissDialog).ifPresent(new l(0));
        }
    }

    private void onSelectItem(FilterResultsEntity filterResultsEntity, int i2) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            this.mBlackboard.postEvent(EventMessage.obtain(8016, filterResultsEntity));
        } else {
            this.mBlackboard.postEvent(EventMessage.obtain(1066, SingleFilterKey.build(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), filterResultsEntity, i2)));
        }
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString(), AnalyticsEventId.EVENT_SEARCH_FILTER_IN_ALL_FILTERS.toString(), (long) getSAValue(filterResultsEntity));
    }

    private void onUnSelectItem(FilterResultsEntity filterResultsEntity) {
        this.mBlackboard.postEvent(EventMessage.obtain(8511, filterResultsEntity));
    }

    public void addClickListener(SearchFiltersViewHolder searchFiltersViewHolder) {
        if (this.mClickable) {
            searchFiltersViewHolder.setOnClickListener(new g(12, this, searchFiltersViewHolder));
        }
    }

    public void addEntity(FilterResultsEntity filterResultsEntity) {
        if (this.mSubsetKeywordEntry == null) {
            this.mSubsetKeywordEntry = new ArrayList<>();
        }
        this.mSubsetKeywordEntry.add(filterResultsEntity);
    }

    public int getItemCount() {
        ArrayList<FilterResultsEntity> arrayList = this.mSubsetKeywordEntry;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public int getItemLayoutResId() {
        return R$layout.recycler_item_search_popup_filters;
    }

    public int getItemViewType(int i2) {
        return 1;
    }

    public void initViewHolderMargin(SearchFiltersViewHolder searchFiltersViewHolder) {
        View view = searchFiltersViewHolder.itemView;
        ViewMarginUtils.setBottomMargin(view, (int) view.getResources().getDimension(R$dimen.search_category_tag_item_margin_bottom));
    }

    public void removeEntity(FilterResultsEntity filterResultsEntity) {
        ArrayList<FilterResultsEntity> arrayList = this.mSubsetKeywordEntry;
        if (arrayList != null) {
            arrayList.remove(filterResultsEntity);
            notifyDataSetChanged();
        }
    }

    public void setData(ArrayList<FilterResultsEntity> arrayList) {
        this.mSubsetKeywordEntry = arrayList;
    }

    public void setOnlyThemClickedListener(Runnable runnable) {
        this.mOnlyThemClickedListener = runnable;
    }

    public boolean supportTheme() {
        return true;
    }

    public void onBindViewHolder(SearchFiltersViewHolder searchFiltersViewHolder, int i2) {
        ArrayList<FilterResultsEntity> arrayList = this.mSubsetKeywordEntry;
        if (arrayList != null) {
            FilterResultsEntity filterResultsEntity = arrayList.get(i2);
            onBindViewHolder(searchFiltersViewHolder, filterResultsEntity, this.mClickable && filterResultsEntity.isSelected());
            searchFiltersViewHolder.getRootView().setClickable(this.mClickable);
            initViewHolderMargin(searchFiltersViewHolder);
        }
    }

    public FilterSubListViewAdapter(Blackboard blackboard, Runnable runnable) {
        super(blackboard);
        this.mClickable = true;
        this.mDismissDialog = runnable;
    }
}
