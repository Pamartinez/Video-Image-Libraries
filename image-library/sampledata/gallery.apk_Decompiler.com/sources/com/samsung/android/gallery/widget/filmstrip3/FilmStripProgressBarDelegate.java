package com.samsung.android.gallery.widget.filmstrip3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripProgressBarDelegate {
    private final String TAG;
    private final ViewGroup mParentView;
    private View mProgress;

    public FilmStripProgressBarDelegate(String str, ViewGroup viewGroup) {
        this.TAG = str;
        this.mParentView = viewGroup;
    }

    private void clearPostProcessing() {
        if (this.mProgress != null) {
            Log.p(this.TAG, "ppp circle removed");
            this.mParentView.removeView(this.mProgress);
            this.mProgress = null;
        }
    }

    public void addPostProcessing(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isPostProcessing() && this.mProgress == null) {
            LayoutInflater.from(this.mParentView.getContext()).inflate(R$layout.postprocessing_with_theme, this.mParentView, true);
            this.mProgress = this.mParentView.findViewById(R$id.post_processing);
            this.mProgress.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.mProgress.setVisibility(0);
        }
    }

    public void onFocusOut() {
        clearPostProcessing();
    }

    public void onFocused(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isPostProcessing() && !mediaItem.isPostProcessingDraftProcessed()) {
            addPostProcessing(mediaItem);
        }
    }

    public void onViewRecycled() {
        clearPostProcessing();
    }
}
