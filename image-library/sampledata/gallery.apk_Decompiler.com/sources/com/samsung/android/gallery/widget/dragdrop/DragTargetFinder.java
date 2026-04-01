package com.samsung.android.gallery.widget.dragdrop;

import android.graphics.Rect;
import android.view.DragEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DragTargetFinder {
    protected View mTargetDropView;

    public boolean checkPositionInView(View view, int i2, int i7) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        if (i2 < rect.left || i2 > rect.right || i7 < rect.top || i7 > rect.bottom) {
            return false;
        }
        return true;
    }

    public abstract MediaItem findItemAtDropPosition(View view, MediaItem mediaItem, int i2, int i7, boolean z);

    public abstract RecyclerView getAlbumListView(View view);

    public abstract void onViewDragFocusOut(View view);

    public void resetDragState(DragEvent dragEvent) {
        onViewDragFocusOut(this.mTargetDropView);
        this.mTargetDropView = null;
        View view = (View) dragEvent.getLocalState();
        if (view != null) {
            view.setAlpha(1.0f);
        }
    }
}
