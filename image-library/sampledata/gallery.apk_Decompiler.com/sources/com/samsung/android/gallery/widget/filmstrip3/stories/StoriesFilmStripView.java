package com.samsung.android.gallery.widget.filmstrip3.stories;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesFilmStripView extends FilmStripView {
    private boolean mIsDraggable = true;

    public StoriesFilmStripView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsDraggable) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void resetViewHolderPosition() {
        this.mLayoutManager.setViewHolderPosition((FilmStripViewHolder<?>) null, 0.0f);
    }

    public void setDraggable(boolean z) {
        this.mIsDraggable = z;
    }
}
