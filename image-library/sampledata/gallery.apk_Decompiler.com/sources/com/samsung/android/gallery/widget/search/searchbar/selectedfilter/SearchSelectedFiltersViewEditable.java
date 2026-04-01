package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FilterAutoCompleteView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchSelectedFiltersViewEditable {
    private final Blackboard mBlackboard;
    private final FilterAutoCompleteView mFilterAutoCompleteView;
    private final RecyclerView mRecyclerView;

    public SearchSelectedFiltersViewEditable(Blackboard blackboard, FilterAutoCompleteView filterAutoCompleteView, RecyclerView recyclerView) {
        this.mBlackboard = blackboard;
        this.mFilterAutoCompleteView = filterAutoCompleteView;
        this.mRecyclerView = recyclerView;
    }

    private Object getLastViewHolder() {
        if (this.mRecyclerView.getAdapter() == null) {
            return null;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView.findViewHolderForAdapterPosition(recyclerView.getAdapter().getItemCount() - 1);
    }

    private boolean isLastItemVisible() {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            if (this.mRecyclerView.getAdapter() != null) {
                View childAt = linearLayoutManager.getChildAt(linearLayoutManager.getChildCount() - 1);
                int itemCount = this.mRecyclerView.getAdapter().getItemCount() - 1;
                if (childAt != null) {
                    if (this.mRecyclerView.getChildViewHolder(childAt).getAbsoluteAdapterPosition() == itemCount) {
                        return true;
                    }
                    return false;
                } else if (linearLayoutManager.findLastVisibleItemPosition() == itemCount) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onAutoCompleteItemsLoaded$0(ArrayList arrayList, String str) {
        this.mFilterAutoCompleteView.onDataChanged(arrayList, str);
    }

    public void dismissAutoCompleteView() {
        this.mFilterAutoCompleteView.dismiss();
        if (isLastItemVisible()) {
            Object lastViewHolder = getLastViewHolder();
            if (lastViewHolder instanceof SearchSelectedFiltersEditableViewHolder) {
                ((SearchSelectedFiltersEditableViewHolder) lastViewHolder).clearIME();
            }
        }
    }

    public void emptyAreaTouched() {
        if (isLastItemVisible()) {
            Object lastViewHolder = getLastViewHolder();
            if (lastViewHolder instanceof SearchSelectedFiltersEditableViewHolder) {
                ((SearchSelectedFiltersEditableViewHolder) lastViewHolder).requestFocusAndShowIME();
            }
        }
    }

    public void onAutoCompleteItemsLoaded(ArrayList<AutoCompleteItem> arrayList, String str) {
        ThreadUtil.postOnUiThread(new e(this, arrayList, str));
    }

    public String replaceFuzzyKeyword(String str) {
        String argValue = ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(this.mBlackboard), "fuzzyKeyword");
        if (TextUtils.isEmpty(argValue)) {
            return str;
        }
        return argValue;
    }

    public void showIme() {
        if (isLastItemVisible()) {
            Object lastViewHolder = getLastViewHolder();
            if (lastViewHolder instanceof SearchSelectedFiltersEditableViewHolder) {
                ((SearchSelectedFiltersEditableViewHolder) lastViewHolder).requestFocusAndShowIME();
            }
        }
    }
}
