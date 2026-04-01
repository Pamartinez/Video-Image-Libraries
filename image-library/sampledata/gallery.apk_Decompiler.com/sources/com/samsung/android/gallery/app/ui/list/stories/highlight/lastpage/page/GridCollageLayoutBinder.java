package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageInfo;
import com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.IGridCollagePage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GridCollageLayoutBinder<V extends IGridCollagePage> extends SaveLayoutBinder<V> {
    private CollageView mCollageView;

    public GridCollageLayoutBinder(V v) {
        super(v);
    }

    private void initCollageList(View view, PageSpec.Config config) {
        this.mCollageView.initListView(view, config.baseSize);
        CollageInfo collageInfo = ((IGridCollagePage) this.mCollagePage).getCollageInfo();
        this.mCollageView.initData(collageInfo.getType(), collageInfo.getItems());
    }

    public RecyclerView getListView() {
        return this.mCollageView.getListView();
    }

    public void initViewInternal(View view, PageSpec.Config config, int i2) {
        CollageView collageView = new CollageView(view);
        this.mCollageView = collageView;
        ((IGridCollagePage) this.mCollagePage).updateViewSize(collageView.getListView(), config, i2);
        initCollageList(view, config);
    }
}
