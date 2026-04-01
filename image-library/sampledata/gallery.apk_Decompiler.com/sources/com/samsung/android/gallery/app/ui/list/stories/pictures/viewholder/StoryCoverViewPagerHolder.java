package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryCoverViewPagerHolder extends StoryCoverViewHolder {
    View mCoverContainer;
    View mCoverViewPagerContainer;
    View mTouchLayout;

    public StoryCoverViewPagerHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mTouchLayout.setContentDescription(getContentDescription());
    }

    public void bindExtraView() {
        bindPlayIconView();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCoverContainer = view.findViewById(R.id.cover_container);
        this.mCoverViewPagerContainer = view.findViewById(R.id.cover_viewpager_container);
        View findViewById = view.findViewById(R.id.touch_layout);
        this.mTouchLayout = findViewById;
        findViewById.setOnClickListener(this);
        ViewUtils.setVisibility(this.mCoverContainer, 8);
        ViewUtils.setVisibility(this.mCoverViewPagerContainer, 0);
    }

    public void onClick(View view) {
        onItemClick(view);
    }
}
