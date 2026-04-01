package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import android.widget.LinearLayout;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAnalysisTipOnNoItem extends SuggesterView {
    private SearchAnalysisTipView mAnalysisTipHeader;
    private IBaseListView mBaseListView;

    public SearchAnalysisTipOnNoItem(ISearchSuggestionView iSearchSuggestionView, EventContext eventContext) {
        super(iSearchSuggestionView, eventContext);
    }

    public void bind() {
        if (this.mEventContext.getContext() != null) {
            if (this.mSuggesterLayout == null) {
                LinearLayout linearLayout = (LinearLayout) this.mSuggestionView.getEmptyView().findViewById(R.id.search_analysis_tip);
                this.mSuggesterLayout = linearLayout;
                if (this.mAnalysisTipHeader == null) {
                    this.mAnalysisTipHeader = new SearchAnalysisTipView(this.mBaseListView, linearLayout);
                }
            }
            SearchAnalysisTipView searchAnalysisTipView = this.mAnalysisTipHeader;
            if (searchAnalysisTipView != null && this.mSuggesterLayout != null) {
                searchAnalysisTipView.init();
                this.mSuggesterLayout.setVisibility(0);
                Consumer<Boolean> consumer = this.mOnBindCompleted;
                if (consumer != null) {
                    consumer.accept(Boolean.TRUE);
                }
            }
        }
    }

    public boolean needUpdatePadding() {
        return false;
    }

    public void refresh() {
        SearchAnalysisTipView searchAnalysisTipView = this.mAnalysisTipHeader;
        if (searchAnalysisTipView != null) {
            searchAnalysisTipView.dataChanged();
        }
    }

    public void setData(Object obj) {
        this.mBaseListView = (IBaseListView) obj;
    }
}
