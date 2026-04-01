package com.samsung.android.gallery.app.ui.list.search.pictures.headerview;

import A5.b;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchAnalysisTipHeaderView extends SearchHeaderView {
    SearchAnalysisTipView mAnalysisTipHeader;

    public SearchAnalysisTipHeaderView(IBaseListView iBaseListView, ViewGroup viewGroup) {
        super(iBaseListView.getContext(), viewGroup, false);
        SearchAnalysisTipView searchAnalysisTipView = new SearchAnalysisTipView(iBaseListView, this.mView, new b(viewGroup, 2));
        this.mAnalysisTipHeader = searchAnalysisTipView;
        searchAnalysisTipView.init();
    }

    public void dataChanged() {
        this.mAnalysisTipHeader.dataChanged();
    }

    public int getLayoutId() {
        return R.layout.search_analysis_tip_header;
    }

    public boolean setHeaderItem(MediaItem mediaItem) {
        return false;
    }

    public void initHeaderItem() {
    }

    public void recycle() {
    }

    public void setEnabled(boolean z) {
    }
}
