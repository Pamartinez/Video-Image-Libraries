package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryShortcutButtonItemViewHolder extends ListViewHolder {
    private View mIcon;
    private View mIconContainer;
    private TextView mTitle;
    private int mType;

    public CategoryShortcutButtonItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onItemClickInternal(this.mType);
    }

    public void bindView(View view) {
        this.mIconContainer = view.findViewById(R.id.icon_container);
        this.mIcon = view.findViewById(R.id.icon);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.itemView.setOnClickListener(new C0496a(0, this));
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mIconContainer, i2, f);
        return this;
    }
}
