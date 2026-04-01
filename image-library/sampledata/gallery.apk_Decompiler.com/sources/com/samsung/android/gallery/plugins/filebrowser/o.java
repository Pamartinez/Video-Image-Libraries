package com.samsung.android.gallery.plugins.filebrowser;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements View.OnLongClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ RecyclerView.ViewHolder e;

    public /* synthetic */ o(RecyclerView.ViewHolder viewHolder, int i2) {
        this.d = i2;
        this.e = viewHolder;
    }

    public final boolean onLongClick(View view) {
        int i2 = this.d;
        RecyclerView.ViewHolder viewHolder = this.e;
        switch (i2) {
            case 0:
                return ((FileViewHolder) viewHolder).lambda$new$1(view);
            default:
                return ((LogViewFragment.LineViewHolder) viewHolder).lambda$new$0(view);
        }
    }
}
