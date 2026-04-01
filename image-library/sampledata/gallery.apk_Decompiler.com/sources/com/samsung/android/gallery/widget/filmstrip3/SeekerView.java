package com.samsung.android.gallery.widget.filmstrip3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.databinding.Filmstrip3SeekerViewLayoutBinding;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeekerView extends LinearLayout {
    private final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private FilmStripViewHolder mClingViewHolder;
    private int mClingViewHolderBackHash;
    private int mDuration = -1;
    private int mDurationBack;
    private int mPosition = 0;
    private int mPositionBack;
    private boolean mSeekerEnabled = false;
    private boolean mSeekerExpanded = false;
    private Filmstrip3SeekerViewLayoutBinding mViewBinding;

    public SeekerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void backupPosition(FilmStripViewHolder filmStripViewHolder) {
        if (filmStripViewHolder == null) {
            if (this.mDuration > 0) {
                Log.d("SeekerView", "backupPosition", Integer.valueOf(this.mPosition), Integer.valueOf(this.mDuration), Integer.valueOf(this.mClingViewHolderBackHash));
            }
            this.mDurationBack = this.mDuration;
            this.mPositionBack = this.mPosition;
        }
    }

    private void enableSeeker(boolean z) {
        this.mSeekerEnabled = z;
        ViewUtils.setVisibility(this.mViewBinding.centerIndexer, 8);
        if (z) {
            seek(this.mDuration, this.mPosition);
            return;
        }
        ViewUtils.setVisibility(this.mViewBinding.progressView, 8);
        enableExpandedSeeker(false);
        this.mDuration = -1;
        this.mPosition = 0;
    }

    private int getHashCode(MediaItem mediaItem) {
        if (mediaItem == null) {
            return -1;
        }
        return (" " + mediaItem.getFileId() + mediaItem.getFileSize() + mediaItem.getDateModified() + mediaItem.getOrientation() + mediaItem.getStorageType()).hashCode();
    }

    private void initView(Context context) {
        this.mViewBinding = Filmstrip3SeekerViewLayoutBinding.inflate(LayoutInflater.from(context), this, true);
    }

    private void restorePosition(FilmStripViewHolder filmStripViewHolder) {
        MediaItem mediaItem;
        if ((filmStripViewHolder instanceof FilmStripVideoViewHolder) && (mediaItem = filmStripViewHolder.mMediaItem) != null && this.mClingViewHolderBackHash == getHashCode(mediaItem)) {
            this.mDuration = this.mDurationBack;
            int i2 = this.mPositionBack;
            this.mPosition = i2;
            Log.d("SeekerView", "restorePosition", Integer.valueOf(i2), Integer.valueOf(this.mDuration));
        } else if (filmStripViewHolder != null) {
            if (this.mDurationBack > 0) {
                Log.d("SeekerView", "restorePosition clear");
            }
            this.mDurationBack = -1;
            this.mPosition = 0;
            this.mClingViewHolderBackHash = -1;
        }
    }

    private void setSeekPos() {
        if (this.mDuration > 0) {
            int width = (this.mViewBinding.borderView.getWidth() - this.mViewBinding.indexer.getWidth()) - (this.mViewBinding.borderView.getPaddingEnd() + this.mViewBinding.borderView.getPaddingStart());
            if (this.IS_RTL) {
                Filmstrip3SeekerViewLayoutBinding filmstrip3SeekerViewLayoutBinding = this.mViewBinding;
                View view = filmstrip3SeekerViewLayoutBinding.indexer;
                int i2 = this.mDuration;
                view.setX((((float) ((i2 - this.mPosition) * width)) / ((float) i2)) + ((float) filmstrip3SeekerViewLayoutBinding.borderView.getPaddingStart()));
                return;
            }
            Filmstrip3SeekerViewLayoutBinding filmstrip3SeekerViewLayoutBinding2 = this.mViewBinding;
            filmstrip3SeekerViewLayoutBinding2.indexer.setX((((float) (this.mPosition * width)) / ((float) this.mDuration)) + ((float) filmstrip3SeekerViewLayoutBinding2.borderView.getPaddingStart()));
        }
    }

    public void enableExpandedSeeker(boolean z) {
        this.mSeekerExpanded = z;
        if (z) {
            SimpleAnimator.setVisibility(this.mViewBinding.centerIndexer, 0, StatusCodes.INPUT_MISSING, new AccelerateInterpolator(2.0f), (Runnable) null);
            ViewUtils.setVisibility(this.mViewBinding.progressView, 8);
            return;
        }
        ViewUtils.setVisibility(this.mViewBinding.centerIndexer, 8);
        FilmStripViewHolder filmStripViewHolder = this.mClingViewHolder;
        if (filmStripViewHolder != null && filmStripViewHolder.supportViewHolderSeek() && this.mDuration > 0) {
            SimpleAnimator.setVisibility(this.mViewBinding.progressView, 0, StatusCodes.INPUT_MISSING, new AccelerateInterpolator(2.0f), (Runnable) null);
            setSeekPos();
        }
    }

    public void hideProgressView() {
        ViewUtils.setVisibility(this.mViewBinding.progressView, 8);
    }

    public void seek(int i2, int i7) {
        this.mDuration = i2;
        this.mPosition = i7;
        if (this.mSeekerEnabled && !this.mSeekerExpanded) {
            if (i2 == -1) {
                ViewUtils.setVisibility(this.mViewBinding.progressView, 8);
                return;
            }
            FilmStripViewHolder filmStripViewHolder = this.mClingViewHolder;
            if (filmStripViewHolder == null || !filmStripViewHolder.supportViewHolderSeek()) {
                ViewUtils.setVisibility(this.mViewBinding.progressView, 8);
                return;
            }
            SimpleAnimator.setVisibility(this.mViewBinding.progressView, 0, 200, new AccelerateInterpolator(2.0f), (Runnable) null);
            setSeekPos();
        }
    }

    public void setClingView(FilmStripViewHolder filmStripViewHolder) {
        if (filmStripViewHolder == null || filmStripViewHolder != this.mClingViewHolder) {
            backupPosition(filmStripViewHolder);
            this.mDuration = -1;
            this.mPosition = 0;
            restorePosition(filmStripViewHolder);
            if (filmStripViewHolder != null) {
                this.mClingViewHolder = filmStripViewHolder;
                this.mClingViewHolderBackHash = getHashCode(filmStripViewHolder.mMediaItem);
                enableSeeker(filmStripViewHolder.supportSeeker());
                if (filmStripViewHolder.supportSeeker()) {
                    enableExpandedSeeker(filmStripViewHolder.isExpanded());
                } else {
                    this.mClingViewHolder = null;
                }
            } else {
                enableSeeker(false);
                this.mClingViewHolder = null;
            }
        }
    }

    public void updatePosition(int i2, int i7) {
        this.mDuration = i2;
        this.mPosition = i7;
    }
}
