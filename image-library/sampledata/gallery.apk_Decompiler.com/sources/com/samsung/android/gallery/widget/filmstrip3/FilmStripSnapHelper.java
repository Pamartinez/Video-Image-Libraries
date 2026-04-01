package com.samsung.android.gallery.widget.filmstrip3;

import android.view.View;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripSnapHelper extends LinearSnapHelper {
    protected final FilmStripView mFilmStripView;
    protected OnFindTargetSnapPosition mOnFindTargetSnapPosition;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnFindTargetSnapPosition {
        void destroy();

        void onFindTargetSnapPosition(int i2);
    }

    public FilmStripSnapHelper(FilmStripView filmStripView) {
        super(0.42f, 120.0f, 1);
        this.mFilmStripView = filmStripView;
    }

    private int distanceToCenter(View view) {
        FilmStripViewHolder filmStripViewHolder = (FilmStripViewHolder) this.mFilmStripView.getChildViewHolder(view);
        if (filmStripViewHolder == null) {
            return 0;
        }
        int maxWidth = filmStripViewHolder.getMaxWidth() - view.getWidth();
        return ((maxWidth / 2) + ((view.getWidth() / 2) + ((int) view.getX()))) - (this.mFilmStripView.getWidth() / 2);
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int i2;
        if (layoutManager.canScrollHorizontally()) {
            i2 = distanceToCenter(view);
        } else {
            i2 = 0;
        }
        return new int[]{i2, 0};
    }

    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i2, int i7) {
        int findTargetSnapPosition = super.findTargetSnapPosition(layoutManager, i2, i7);
        OnFindTargetSnapPosition onFindTargetSnapPosition = this.mOnFindTargetSnapPosition;
        if (onFindTargetSnapPosition != null) {
            onFindTargetSnapPosition.onFindTargetSnapPosition(findTargetSnapPosition);
            Log.d("FilmStripSnapHelper", "findTargetSnapPosition {" + findTargetSnapPosition + '}');
        }
        return findTargetSnapPosition;
    }

    public void setOnFindTargetSnapPosition(OnFindTargetSnapPosition onFindTargetSnapPosition) {
        OnFindTargetSnapPosition onFindTargetSnapPosition2;
        if (onFindTargetSnapPosition == null && (onFindTargetSnapPosition2 = this.mOnFindTargetSnapPosition) != null) {
            onFindTargetSnapPosition2.destroy();
        }
        this.mOnFindTargetSnapPosition = onFindTargetSnapPosition;
    }
}
