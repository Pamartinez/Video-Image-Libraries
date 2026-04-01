package com.samsung.android.gallery.widget.search.searchbar.autocomplete;

import Ba.g;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.utils.TextViewUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterAutoCompleteAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Blackboard mBlackboard;
    private ArrayList<AutoCompleteItem> mData = new ArrayList<>();
    private String mKeyword;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public View mDivider;
        /* access modifiers changed from: private */
        public ImageView mIconView;
        /* access modifiers changed from: private */
        public TextView mTitleView;

        public ViewHolder(View view) {
            super(view);
            this.mIconView = (ImageView) view.findViewById(R$id.auto_complete_icon);
            this.mTitleView = (TextView) view.findViewById(R$id.auto_complete_title);
            this.mDivider = view.findViewById(R$id.auto_complete_item_divider);
            if (PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM) {
                ViewUtils.setVisibleOrGone(this.mIconView, false);
            }
        }
    }

    public FilterAutoCompleteAdapter(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(AutoCompleteItem autoCompleteItem, View view) {
        String translatedKeyword = autoCompleteItem.getTranslatedKeyword();
        String field = autoCompleteItem.getField();
        FilterResultsEntity filterResultsEntity = new FilterResultsEntity(translatedKeyword, IntelligentSearchIndexFieldIcon.create(field, translatedKeyword), field);
        if (!PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM) {
            translatedKeyword = autoCompleteItem.getKeyword();
        }
        filterResultsEntity.addRawLabel(translatedKeyword);
        filterResultsEntity.setSuggestedKeywordFeature(autoCompleteItem.getSuggestedKeywordFeature());
        this.mBlackboard.postEvent(EventMessage.obtain(8017, filterResultsEntity));
    }

    private boolean showDivider(int i2) {
        if (getItemCount() - 1 == i2 || getItemCount() == 1) {
            return false;
        }
        return true;
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public int getItemLayoutId() {
        return R$layout.recycler_item_suggestion_keyword_layout_v2;
    }

    public void setData(ArrayList<AutoCompleteItem> arrayList, String str) {
        this.mData = arrayList;
        this.mKeyword = str;
        notifyDataSetChanged();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i2) {
        AutoCompleteItem autoCompleteItem = this.mData.get(i2);
        if (autoCompleteItem != null) {
            viewHolder.mTitleView.setText(autoCompleteItem.getTranslatedKeyword());
            viewHolder.itemView.setOnClickListener(new g(16, this, autoCompleteItem));
            if (!PreferenceFeatures.OneUi7x.SUPPORT_AC_UNIFIED_ITEM) {
                viewHolder.mIconView.setImageDrawable(autoCompleteItem.getDrawable(viewHolder.itemView.getContext()));
            }
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                TextViewUtils.highlightKeywordIgnoreTag(Blackboard.getContext(), viewHolder.mTitleView, this.mKeyword);
            } else {
                TextViewUtils.highlightKeyword(Blackboard.getContext(), viewHolder.mTitleView, this.mKeyword);
            }
            viewHolder.mDivider.setVisibility(showDivider(i2) ? 0 : 8);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayoutId(), viewGroup, false));
    }
}
