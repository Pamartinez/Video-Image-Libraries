package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import a6.C0419b;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryMyQueryItemViewHolder extends ListViewHolder {
    private TextView mQueryName;
    private ImageView mRemoveIcon;

    public CategoryMyQueryItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void setRemoveIconEnable() {
        this.mRemoveIcon.setVisibility(0);
        View view = this.itemView;
        ViewMarginUtils.setEndPadding(view, view.getResources().getDimensionPixelOffset(R.dimen.search_category_my_query_item_padding_end_with_remove_icon));
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mQueryName.setText(mediaItem.getDisplayName());
    }

    public void bindView(View view) {
        this.mQueryName = (TextView) view.findViewById(R.id.my_query_name);
        this.mRemoveIcon = (ImageView) view.findViewById(R.id.remove_icon);
        this.itemView.setOnClickListener(new C0419b(29, this));
        this.mRemoveIcon.setOnClickListener(new C0419b(29, this));
    }

    public void onClick(View view) {
        onItemClickInternal(0);
    }

    public void recycle() {
        super.recycle();
        this.mQueryName.setForeground((Drawable) null);
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        setRemoveIconEnable();
        return true;
    }
}
