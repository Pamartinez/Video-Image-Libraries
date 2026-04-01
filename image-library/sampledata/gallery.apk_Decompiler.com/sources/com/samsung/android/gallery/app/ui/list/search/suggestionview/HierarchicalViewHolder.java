package com.samsung.android.gallery.app.ui.list.search.suggestionview;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class HierarchicalViewHolder extends ListViewHolder {
    private TextView mTitleView;

    public HierarchicalViewHolder(View view) {
        super(view, 0);
    }

    public final void bindView(View view) {
        this.mTitleView = (TextView) view.findViewById(R.id.hierarchical_text);
    }

    public void setTitle(String str) {
        this.mTitleView.setText(str);
    }
}
