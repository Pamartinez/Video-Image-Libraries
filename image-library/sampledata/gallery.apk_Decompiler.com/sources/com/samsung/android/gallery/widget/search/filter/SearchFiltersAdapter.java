package com.samsung.android.gallery.widget.search.filter;

import Ba.g;
import H7.A;
import Qb.e;
import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.search.filter.SingleFilterKey;
import com.samsung.android.gallery.module.search.root.FilterPriority;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.InputBlock;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import java.util.List;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFiltersAdapter extends AbsFiltersAdapter {
    private AlertDialog mDialog;
    private boolean mIsEnabled;
    private boolean mIsRtl;
    private Supplier<Boolean> mIsSearchProcessingSupplier;
    private final int mItemLayoutId;
    private final int mLimitCount;
    private int mSelectedIndex;
    private FilterResultsEntry mSubsetKeywordEntry;

    public SearchFiltersAdapter(Blackboard blackboard, int i2) {
        super(blackboard);
        this.mSelectedIndex = PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD ? -1 : 0;
        this.mIsEnabled = true;
        this.mLimitCount = 5;
        this.mIsRtl = Features.isEnabled(Features.IS_RTL);
        this.mItemLayoutId = getItemLayoutId(i2);
        this.mIsSearchProcessingSupplier = (Supplier) this.mBlackboard.read("data://user/SearchProcessing");
    }

    private boolean checkNeedNotifyDataChange(FilterResultsEntry filterResultsEntry, FilterResultsEntry filterResultsEntry2) {
        int count;
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || filterResultsEntry == null || filterResultsEntry2 == null || (count = filterResultsEntry.getCount()) != filterResultsEntry2.getCount()) {
            return true;
        }
        for (int i2 = 0; i2 < count; i2++) {
            FilterResultsEntity item = filterResultsEntry.getItem(i2);
            FilterResultsEntity item2 = filterResultsEntry2.getItem(i2);
            if (item.isGroupType() != item2.isGroupType() || item.getChild().size() != item2.getChild().size() || !TextUtils.equals(item.getCategory(), item2.getCategory()) || (item.getRawLabelsSize() != 0 && item2.getRawLabelsSize() != 0 && !TextUtils.equals(item.getRawLabels(), item2.getRawLabels()))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void dismissDialog() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private int getItemLayoutId(int i2) {
        if (i2 != 0) {
            return i2;
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.NewSearchBar)) {
            return R$layout.recycler_item_search_filters_no_theme;
        }
        return R$layout.recycler_item_search_filters;
    }

    private int getLimitCount() {
        if (this.mSubsetKeywordEntry.getCount() > 5) {
            return 5;
        }
        return this.mSubsetKeywordEntry.getCount();
    }

    private boolean isSelectedCheck() {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            return true;
        }
        FilterResultsEntry filterResultsEntry = this.mSubsetKeywordEntry;
        if (filterResultsEntry == null || filterResultsEntry.isEnableSearchToolbar()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addClickListener$0(SearchFiltersViewHolder searchFiltersViewHolder, View view) {
        FilterResultsEntry filterResultsEntry;
        if (InputBlock.getInstance().set(this.TAG)) {
            Supplier<Boolean> supplier = this.mIsSearchProcessingSupplier;
            if (supplier == null || !supplier.get().booleanValue()) {
                int bindingAdapterPosition = searchFiltersViewHolder.getBindingAdapterPosition();
                if (bindingAdapterPosition != -1 && (filterResultsEntry = this.mSubsetKeywordEntry) != null) {
                    FilterResultsEntity item = filterResultsEntry.getItem(bindingAdapterPosition);
                    if (PreferenceFeatures.OneUi8x.SUPPORT_GROUPING_SEARCH_FILTER) {
                        onClickedItem(item, bindingAdapterPosition);
                        return;
                    }
                    int i2 = this.mSelectedIndex;
                    if (bindingAdapterPosition != i2) {
                        notifyItemChanged(i2);
                        this.mSelectedIndex = bindingAdapterPosition;
                        notifyItemChanged(bindingAdapterPosition);
                        onClickedItem(item, bindingAdapterPosition);
                        return;
                    }
                    return;
                }
                return;
            }
            Log.w(this.TAG, "onClicked filter ignore : but query is processing now");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSubFilterDialog$1(View view) {
        dismissDialog();
    }

    private void onClickedItem(FilterResultsEntity filterResultsEntity, int i2) {
        boolean z;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            this.mBlackboard.postEvent(EventMessage.obtain(8030));
        }
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
            this.mBlackboard.postEvent(EventMessage.obtain(1066, SingleFilterKey.build(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), filterResultsEntity, i2)));
        } else if (filterResultsEntity.isGroupType()) {
            showSubFilterDialog(filterResultsEntity);
        } else {
            FilterResultsEntry filterResultsEntry = this.mSubsetKeywordEntry;
            if (filterResultsEntry != null && filterResultsEntry.isEnableSearchToolbar()) {
                this.mSelectedIndex = -1;
                this.mBlackboard.postEvent(EventMessage.obtain(8016, filterResultsEntity));
            }
        }
        if (i2 > 0) {
            z = true;
        } else {
            z = false;
        }
        postAnalyticsLog(z);
    }

    private void postAnalyticsLog(boolean z) {
        AnalyticsEventId analyticsEventId;
        AnalyticsLogger instance = AnalyticsLogger.getInstance();
        String analyticsScreenId = AnalyticsScreenId.SCREEN_VISUAL_SEARCH_VIEW_RESULT.toString();
        if (z) {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_VIEW_FILTER_RESULT_KEYWORD;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_SEARCH_VIEW_FILTER_RESULT_ALL;
        }
        instance.postLog(analyticsScreenId, analyticsEventId.toString());
    }

    private void showSubFilterDialog(FilterResultsEntity filterResultsEntity) {
        float f;
        Activity readActivity = BlackboardUtils.readActivity(this.mBlackboard);
        if (readActivity != null) {
            View inflate = LayoutInflater.from(readActivity).inflate(R$layout.search_all_filters_popup_view, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R$id.all_filters_title);
            ImageView imageView = (ImageView) inflate.findViewById(R$id.item_arrow);
            if (imageView != null) {
                if (this.mIsRtl) {
                    f = 270.0f;
                } else {
                    f = 90.0f;
                }
                imageView.setRotation(f);
                imageView.setOnClickListener(new A(22, this));
            }
            textView.setText(filterResultsEntity.getName());
            FilterSubListView filterSubListView = new FilterSubListView(this.mBlackboard, inflate, FilterPriority.getListPriority(filterResultsEntity), new e(20, this));
            filterResultsEntity.getChild().forEach(new d(filterSubListView));
            filterSubListView.updateListView();
            this.mDialog = new AlertDialog.Builder(readActivity).setView(inflate).create();
            if (SdkConfig.atLeast(SdkConfig.SEM.B) && !Features.isEnabled(Features.IS_THEME_INSTALLED) && !Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY)) {
                this.mDialog.seslSetBackgroundBlurEnabled(true);
            }
            this.mDialog.show();
        }
    }

    public void addClickListener(SearchFiltersViewHolder searchFiltersViewHolder) {
        searchFiltersViewHolder.setOnClickListener(new g(13, this, searchFiltersViewHolder));
    }

    public int getItemCount() {
        FilterResultsEntry filterResultsEntry = this.mSubsetKeywordEntry;
        if (filterResultsEntry == null) {
            return 0;
        }
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return getLimitCount();
        }
        return filterResultsEntry.getCount();
    }

    public int getItemLayoutResId() {
        return this.mItemLayoutId;
    }

    public int getItemViewType(int i2) {
        return 0;
    }

    public int getSelectedIndex() {
        return this.mSelectedIndex;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void removeEntity(FilterResultsEntity filterResultsEntity) {
        FilterResultsEntry filterResultsEntry = this.mSubsetKeywordEntry;
        if (filterResultsEntry != null) {
            filterResultsEntry.removeEntity(filterResultsEntity);
            notifyDataSetChanged();
        }
    }

    public void setData(FilterResultsEntry filterResultsEntry) {
        boolean checkNeedNotifyDataChange = checkNeedNotifyDataChange(this.mSubsetKeywordEntry, filterResultsEntry);
        if (checkNeedNotifyDataChange) {
            this.mSubsetKeywordEntry = filterResultsEntry;
            notifyDataSetChanged();
        }
        String str = this.TAG;
        Log.s(str, "setData :" + getItemCount() + ", notify=" + checkNeedNotifyDataChange);
    }

    public void setEnabled(boolean z) {
        this.mIsEnabled = z;
        notifyItemRangeChanged(0, getItemCount(), "enable");
    }

    public void onBindViewHolder(SearchFiltersViewHolder searchFiltersViewHolder, int i2, List<Object> list) {
        if (list.contains("enable")) {
            searchFiltersViewHolder.setEnable(this.mIsEnabled);
        } else {
            super.onBindViewHolder(searchFiltersViewHolder, i2, list);
        }
    }

    public void onBindViewHolder(SearchFiltersViewHolder searchFiltersViewHolder, int i2) {
        FilterResultsEntry filterResultsEntry = this.mSubsetKeywordEntry;
        if (filterResultsEntry != null) {
            onBindViewHolder(searchFiltersViewHolder, filterResultsEntry.getItem(i2), isSelectedCheck() && this.mSelectedIndex == i2);
        }
    }

    public SearchFiltersAdapter(Blackboard blackboard, SearchFiltersViewContainer searchFiltersViewContainer) {
        super(blackboard);
        this.mSelectedIndex = PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD ? -1 : 0;
        this.mIsEnabled = true;
        this.mLimitCount = 5;
        this.mIsRtl = Features.isEnabled(Features.IS_RTL);
        this.mSelectedIndex = searchFiltersViewContainer.getSelectedIndex();
        this.mIsEnabled = searchFiltersViewContainer.isEnabled();
        this.mItemLayoutId = getItemLayoutId(searchFiltersViewContainer.getCustomItemLayoutId());
    }
}
