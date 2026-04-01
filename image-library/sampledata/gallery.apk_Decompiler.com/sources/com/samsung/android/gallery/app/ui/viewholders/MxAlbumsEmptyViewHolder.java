package com.samsung.android.gallery.app.ui.viewholders;

import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MxAlbumsEmptyViewHolder extends ListViewHolder implements View.OnClickListener {
    private View mEmptyButton;

    public MxAlbumsEmptyViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        View findViewById = view.findViewById(R.id.empty_button);
        this.mEmptyButton = findViewById;
        findViewById.setOnClickListener(this);
    }

    public View getDividerView() {
        return this.itemView;
    }

    public void onClick(View view) {
        ListViewHolder.OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this, getViewPosition(), (MediaItem) null, 0);
        }
    }

    public void setEnable(boolean z) {
        float f;
        super.setEnable(z);
        View view = this.mEmptyButton;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        view.setAlpha(f);
        View view2 = this.mEmptyButton;
        if (!z) {
            this = null;
        }
        view2.setOnClickListener(this);
    }
}
