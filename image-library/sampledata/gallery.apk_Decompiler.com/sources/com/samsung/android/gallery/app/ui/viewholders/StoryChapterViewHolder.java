package com.samsung.android.gallery.app.ui.viewholders;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryChapterViewHolder extends ListViewHolder {
    TextView mDuration;
    TextView mTitle;

    public StoryChapterViewHolder(View view, int i2) {
        super(view, i2);
    }

    private void setTitleAndDuration(MediaItem mediaItem) {
        if (mediaItem != null) {
            String title = mediaItem.getTitle();
            String date = mediaItem.getDate();
            if (!TextUtils.isEmpty(title)) {
                this.mTitle.setText(title);
                this.mDuration.setText(date);
                ViewUtils.setVisibility(this.mDuration, 0);
                return;
            }
            this.mTitle.setText(date);
            ViewUtils.setVisibility(this.mDuration, 8);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setTitleAndDuration(mediaItem);
    }

    public void bindView(View view) {
        this.mDuration = (TextView) view.findViewById(R.id.duration);
        this.mTitle = (TextView) view.findViewById(R.id.title);
    }
}
