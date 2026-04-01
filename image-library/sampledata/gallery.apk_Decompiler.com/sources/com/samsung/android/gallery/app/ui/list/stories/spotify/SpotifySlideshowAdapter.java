package com.samsung.android.gallery.app.ui.list.stories.spotify;

import N2.j;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerAdapter;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.SlideshowAdapter;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SpotifySlideshowAdapter extends SlideshowAdapter {
    private SlideshowAnimation mAnimation;
    private int mPositionOffset;

    public SpotifySlideshowAdapter(Blackboard blackboard, MediaData mediaData, ContainerModel containerModel, VuContainerAdapter.ViewChangeListener viewChangeListener) {
        super(blackboard, mediaData, containerModel, viewChangeListener);
        setHasStableIds(true);
    }

    private SlideshowAnimation getAnimation() {
        if (this.mAnimation == null) {
            this.mAnimation = new SlideshowAnimation(this.mContainerModel.getViewPager());
        }
        return this.mAnimation;
    }

    private void moveTo(int i2, boolean z) {
        try {
            this.mContainerModel.getViewPager().setCurrentItem(i2, z);
        } catch (IllegalStateException | NullPointerException e) {
            j.u(e, C0086a.o(i2, "moveTo#", " failed. e="), this.TAG);
        }
    }

    public void destroy() {
        SlideshowAnimation slideshowAnimation = this.mAnimation;
        if (slideshowAnimation != null) {
            slideshowAnimation.cancel();
            this.mAnimation = null;
        }
        super.destroy();
    }

    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public boolean isMovable(int i2, int i7) {
        if (i2 == 0 && i7 == getItemCount() - 1) {
            return false;
        }
        if (i2 == 1 && i7 == this.mPositionOffset) {
            return false;
        }
        return true;
    }

    public void move(int i2) {
        int i7;
        ViewPager2 viewPager = this.mContainerModel.getViewPager();
        int currentItem = viewPager.getCurrentItem();
        if (isMovable(i2, currentItem)) {
            Log.d(this.TAG, "move", Integer.valueOf(i2), Integer.valueOf(currentItem));
            if (i2 == 0) {
                i7 = currentItem + 1;
            } else {
                i7 = currentItem - 1;
            }
            moveTo(i7, false);
        } else if (i2 == 1) {
            int i8 = currentItem - 1;
            if (i8 < this.mPositionOffset) {
                i8 += this.mMediaData.getCount();
            }
            Log.d(this.TAG, "move#last", Integer.valueOf(i2), Integer.valueOf(i8), Integer.valueOf(viewPager.getCurrentItem()), Integer.valueOf(this.mMediaData.getCount()));
            moveTo(i8, false);
        } else {
            Log.e((CharSequence) this.TAG, "move skip", Integer.valueOf(i2), Integer.valueOf(currentItem));
        }
    }

    public void moveSmooth(int i2) {
        int currentItem = this.mContainerModel.getViewPager().getCurrentItem();
        if (isMovable(i2, currentItem)) {
            Log.d(this.TAG, "moveSmooth", Integer.valueOf(i2), Integer.valueOf(currentItem));
            if (i2 == 0) {
                getAnimation().moveNext(true);
            } else {
                getAnimation().movePrev(true);
            }
        } else {
            Log.e((CharSequence) this.TAG, "moveSmooth skip", Integer.valueOf(i2), Integer.valueOf(currentItem));
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder onCreateViewHolder = super.onCreateViewHolder(viewGroup, i2);
        onCreateViewHolder.itemView.setEnabled(true);
        onCreateViewHolder.itemView.setOnTouchListener(new b(this));
        return onCreateViewHolder;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            int i2 = 1;
            if (!Features.isEnabled(Features.IS_RTL) ? ((int) motionEvent.getX()) > view.getWidth() / 3 : ((int) motionEvent.getX()) <= (view.getWidth() * 2) / 3) {
                i2 = 0;
            }
            Log.d(this.TAG, "onTouch", Integer.valueOf(motionEvent.getAction()), Float.valueOf(motionEvent.getX()), Integer.valueOf(i2));
            move(i2);
        }
        return false;
    }

    public SpotifySlideshowAdapter setOffset(int i2) {
        this.mPositionOffset = i2;
        return this;
    }
}
