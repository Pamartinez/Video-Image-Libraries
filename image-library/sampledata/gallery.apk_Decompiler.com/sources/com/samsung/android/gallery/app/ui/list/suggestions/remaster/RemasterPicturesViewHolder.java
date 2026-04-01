package com.samsung.android.gallery.app.ui.list.suggestions.remaster;

import A4.B;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterPicturesViewHolder extends ListViewHolder {
    TextView mCount;
    RecyclerView mListView;
    private RemasterItemView mRemasterItemView;
    TextView mTitle;

    public RemasterPicturesViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindInfo(String str, String str2) {
        Optional.ofNullable(this.mTitle).ifPresent(new B(str, 12));
        Optional.ofNullable(this.mCount).ifPresent(new B(str2, 13));
    }

    public void bindList(RemasterItemView remasterItemView) {
        this.mRemasterItemView = remasterItemView;
        remasterItemView.bind(this.mListView);
    }

    public void bindView(View view) {
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mCount = (TextView) view.findViewById(R.id.count);
        this.mListView = (RecyclerView) view.findViewById(R.id.listview);
    }

    public RecyclerView getListView() {
        return this.mListView;
    }

    public void recycle() {
        super.recycle();
        RemasterItemView remasterItemView = this.mRemasterItemView;
        if (remasterItemView != null) {
            remasterItemView.destroy();
            this.mRemasterItemView = null;
        }
    }

    public void updateLayout(Context context) {
        RemasterItemView remasterItemView = this.mRemasterItemView;
        if (remasterItemView != null) {
            remasterItemView.updateLayout(context);
        }
    }
}
