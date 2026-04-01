package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryTagItemViewHolder extends ListViewHolder {
    private final boolean mIsExpansionMode;
    TextView mTitleText;

    public CategoryTagItemViewHolder(View view, int i2, boolean z) {
        super(view, i2);
        this.mIsExpansionMode = z;
    }

    private boolean hasEnoughSpace(Context context, int i2) {
        int windowHeight = ResourceCompat.getWindowHeight(context);
        if (!ResourceCompat.isLandscape(context) || windowHeight != DeviceInfo.getDisplayHeight(context) || ((float) windowHeight) * 1.4f >= ((float) i2)) {
            return false;
        }
        return true;
    }

    private void updateTitleMaxWidth() {
        int i2;
        if (!this.mIsExpansionMode) {
            int windowWidth = ResourceCompat.getWindowWidth(this.itemView.getContext());
            if (hasEnoughSpace(this.itemView.getContext(), windowWidth)) {
                i2 = 4;
            } else {
                i2 = 2;
            }
            float f = (float) i2;
            TextView textView = this.mTitleText;
            textView.setMaxWidth((int) ((((((float) windowWidth) - (this.itemView.getResources().getDimension(R.dimen.search_card_padding_horizontal_for_tag) * 2.0f)) - (((this.itemView.getResources().getDimension(R.dimen.search_category_tag_item_stroke) * 2.0f) + this.itemView.getResources().getDimension(R.dimen.search_category_tag_item_margin_end)) * f)) - this.itemView.getResources().getDimension(R.dimen.search_card_arrow_size)) / f));
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mTitleText.setText(StringCompat.excludePrefix(mediaItem.getTitle(), "#"));
        updateTitleMaxWidth();
    }

    public final void bindView(View view) {
        this.mTitleText = (TextView) view.findViewById(R.id.tag_title);
        view.setOnClickListener(new C0496a(4, this));
    }

    public void onClick(View view) {
        onItemClickInternal(0);
    }

    public void recycle() {
        super.recycle();
        this.mTitleText.setText((CharSequence) null);
    }
}
