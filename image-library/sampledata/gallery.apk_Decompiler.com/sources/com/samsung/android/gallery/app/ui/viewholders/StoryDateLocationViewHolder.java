package com.samsung.android.gallery.app.ui.viewholders;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryDateLocationViewHolder extends DateLocationViewHolder {
    TextView mTripDate;

    public StoryDateLocationViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (MediaItemStory.getStoryTripDay(mediaItem) > 0) {
            TextView textView = this.mTripDate;
            textView.setText(textView.getResources().getString(R.string.continuous_day_title, new Object[]{Integer.valueOf(MediaItemStory.getStoryTripDay(mediaItem))}));
            this.mTripDate.setVisibility(0);
            return;
        }
        this.mTripDate.setVisibility(8);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTripDate = (TextView) view.findViewById(R.id.trip_date);
    }
}
