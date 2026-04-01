package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPinViewHolderFactory {
    private float mRoundRadius;

    public StoryPinViewHolderFactory(Context context) {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            this.mRoundRadius = context.getResources().getDimension(R.dimen.stories_view_pin_item_round_radius);
        }
    }

    private int getDataLayoutResId() {
        if (PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return R.layout.recycler_item_stories_pin_layout_for_transitory;
        }
        return R.layout.recycler_item_stories_pin_layout;
    }

    private PinViewHolder getDataViewHolder(ViewGroup viewGroup, int i2) {
        return new PinDataViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getDataLayoutResId(), viewGroup, false), 0, this.mRoundRadius);
    }

    private PinViewHolder getDividerViewHolder(ViewGroup viewGroup, int i2) {
        return new PinDividerViewHolder(C0086a.d(viewGroup, R.layout.recycler_item_stories_pin_divider, viewGroup, false), -100);
    }

    public PinViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            return getDataViewHolder(viewGroup, i2);
        }
        return getDividerViewHolder(viewGroup, i2);
    }
}
