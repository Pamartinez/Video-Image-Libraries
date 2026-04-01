package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryTitleCountViewHolder extends ImageTitleViewHolder {
    protected TextView mCount;

    public CategoryTitleCountViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (TextUtils.isEmpty(getTitleText(mediaItem))) {
            this.mTitleText.setVisibility(8);
        }
        if (mediaItem.getCount() > 0) {
            ViewUtils.setText(this.mCount, StringCompat.toReadableCount(mediaItem.getCount()));
            ViewUtils.setVisibleOrGone(this.mCount, !invisibleCount());
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCount = (TextView) view.findViewById(R.id.count);
    }

    public boolean invisibleCount() {
        return false;
    }

    public void setContentDescription() {
        super.setContentDescription();
        if (ViewUtils.isVisible(this.mCount) && this.mMediaItem != null) {
            StringBuilder s = C0212a.s(getContentDescription());
            s.append(String.format(getString(R.string.speak_folder_name_n_items), new Object[]{Integer.valueOf(this.mMediaItem.getCount())}));
            this.itemView.setContentDescription(s.toString());
        }
    }
}
