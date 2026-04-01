package com.samsung.android.gallery.app.ui.viewholders;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageTitleDurationViewHolder extends ImageTitleViewHolder {
    protected TextView mDurationText;

    public ImageTitleDurationViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        TextView textView = this.mDurationText;
        if (textView != null) {
            textView.setText(getDurationText(mediaItem));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mDurationText = (TextView) view.findViewById(R.id.duration);
    }

    public View getDecoView(int i2) {
        if (i2 == 31) {
            return this.mDurationText;
        }
        return super.getDecoView(i2);
    }

    public String getDurationText(MediaItem mediaItem) {
        return MediaItemStory.getStoryTimeDuration(mediaItem);
    }

    public void recycle() {
        TextView textView = this.mDurationText;
        if (textView != null) {
            textView.setText((CharSequence) null);
        }
        super.recycle();
    }
}
