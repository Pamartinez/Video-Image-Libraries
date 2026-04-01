package com.samsung.android.gallery.app.ui.list.stories.recap.delegate;

import H6.b;
import H6.c;
import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripAdapter;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.SeekerView;
import com.samsung.android.gallery.widget.recyclerview.FilmStripViewPool;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripDelegate extends Delegate {
    /* access modifiers changed from: private */
    public boolean isInitialized = false;
    /* access modifiers changed from: private */
    public int mCurrentTime = 0;
    private FilmStripAdapter mFilmStripAdapter;
    private final RecyclerView.OnScrollListener mFilmStripScrollListener = new RecyclerView.OnScrollListener() {
        private void onDraggingState() {
            FilmStripViewHolder p6 = FilmStripDelegate.this.getCurrentViewHolder();
            if (p6 != null && p6.isExpanded()) {
                FilmStripDelegate.this.mEventHandler.postEvent(Event.REQUEST_VIDEO_SEEK_BEGIN, new Object[0]);
            }
            FilmStripDelegate.this.mIsFilmDragging = true;
            FilmStripDelegate.this.mView.postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_FILM_STRIP);
        }

        private void onIdleState() {
            FilmStripViewHolder p6 = FilmStripDelegate.this.getCurrentViewHolder();
            if (p6 != null && p6.isExpanded() && FilmStripDelegate.this.mIsFilmDragging) {
                FilmStripDelegate.this.mEventHandler.postEvent(Event.REQUEST_VIDEO_SEEK_FINISH, new Object[0]);
            }
            FilmStripDelegate.this.mIsFilmDragging = false;
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                onIdleState();
            } else if (i2 == 1) {
                onDraggingState();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
            int i8;
            FilmStripViewHolder p6 = FilmStripDelegate.this.getCurrentViewHolder();
            if (!FilmStripDelegate.this.isInitialized) {
                FilmStripDelegate.this.isInitialized = true;
                FilmStripDelegate.this.initializeViewHolder(p6);
            } else if (FilmStripDelegate.this.mIsFilmDragging && p6 != null && p6.isExpanded()) {
                View view = p6.itemView;
                int width = (((FilmStripDelegate.this.mFilmStripView.getWidth() / 2) - ((int) view.getX())) - view.getPaddingLeft()) - (FilmStripDelegate.this.mVideoIndexWidth / 2);
                if (Features.isEnabled(Features.IS_RTL)) {
                    width = (((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) - (FilmStripDelegate.this.mVideoIndexWidth / 2)) - width;
                }
                float width2 = ((float) width) / ((float) (view.getWidth() - FilmStripDelegate.this.mVideoIndexWidth));
                if (width2 < 0.0f) {
                    width2 = 0.0f;
                }
                if (width2 > 1.0f) {
                    width2 = 1.0f;
                }
                MediaItem mediaItem = p6.getMediaItem();
                if (mediaItem == null || FilmStripDelegate.this.mTotalTime != 1) {
                    i8 = FilmStripDelegate.this.mTotalTime;
                } else {
                    i8 = VideoPropData.getVideoDuration(mediaItem);
                }
                int i10 = (int) (((float) i8) * width2);
                if (i10 != FilmStripDelegate.this.mCurrentTime) {
                    if (i10 + 100 >= i8) {
                        Log.d(FilmStripDelegate.this.TAG, "adjust position", Integer.valueOf(i10), Integer.valueOf(i8));
                        i10 = Math.max(i8 + LttEngineErrors.ERROR_INPAINTING_FAILED, 0);
                    }
                    FilmStripDelegate.this.mCurrentTime = i10;
                    FilmStripDelegate.this.mEventHandler.postEvent(Event.REQUEST_VIDEO_SEEK, Integer.valueOf(i10), mediaItem);
                    FilmStripDelegate.this.mSeekerView.updatePosition(i8, FilmStripDelegate.this.mCurrentTime);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public FilmStripView mFilmStripView;
    private final FilmStripViewPool mFilmStripViewPool = ((FilmStripViewPool) new FilmStripViewPool().setMaxSize(20, 30));
    /* access modifiers changed from: private */
    public boolean mIsFilmDragging = false;
    /* access modifiers changed from: private */
    public SeekerView mSeekerView;
    /* access modifiers changed from: private */
    public int mTotalTime = 1;
    /* access modifiers changed from: private */
    public int mVideoIndexWidth = 0;

    public FilmStripDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public FilmStripViewHolder<?> getCurrentViewHolder() {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            return filmStripView.getCenterViewHolder();
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void initializeViewHolder(FilmStripViewHolder<?> filmStripViewHolder) {
        Log.d(this.TAG, "initializeExpanded");
        if (filmStripViewHolder != null && this.mFilmStripView != null && this.mSeekerView != null) {
            filmStripViewHolder.expandSeekerMode(0.0f);
            this.mFilmStripView.setSeekerMode(true);
            filmStripViewHolder.setSelected(true);
            filmStripViewHolder.onFocused(this.mFilmStripView);
            View view = filmStripViewHolder.itemView;
            view.setOnClickListener((View.OnClickListener) null);
            view.setOnLongClickListener((View.OnLongClickListener) null);
            this.mSeekerView.setClingView(filmStripViewHolder);
            this.mSeekerView.enableExpandedSeeker(true);
        }
    }

    /* access modifiers changed from: private */
    public void onPlayPauseButtonClicked(Object... objArr) {
        if (objArr[0].booleanValue()) {
            Log.d(this.TAG, "stop filmScroll by playButton");
            FilmStripView filmStripView = this.mFilmStripView;
            if (filmStripView != null) {
                filmStripView.stopScroll();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onReplayVideo(Object... objArr) {
        FilmStripViewHolder<?> currentViewHolder = getCurrentViewHolder();
        if (currentViewHolder != null && !this.mIsFilmDragging) {
            this.mCurrentTime = 0;
            ThreadUtil.postOnUiThread(new c(this, currentViewHolder, 1));
        }
    }

    /* access modifiers changed from: private */
    public void onVideoPlayTimeUpdated(Object... objArr) {
        FilmStripViewHolder<?> currentViewHolder = getCurrentViewHolder();
        if (currentViewHolder != null && !this.mIsFilmDragging) {
            this.mCurrentTime = objArr[0].intValue();
            int intValue = objArr[1].intValue();
            this.mTotalTime = intValue;
            if (intValue <= 0) {
                this.mTotalTime = 1;
            }
            ThreadUtil.postOnUiThread(new c(this, currentViewHolder, 0));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateSeekerOnUiThread */
    public void lambda$onVideoPlayTimeUpdated$1(FilmStripViewHolder<?> filmStripViewHolder) {
        if (this.mFilmStripView == null || this.mSeekerView == null) {
            Log.e(this.TAG, "updateSeekerOnUi failed");
        } else if (!filmStripViewHolder.isExpanded()) {
            Log.e(this.TAG, "seek failed. not expanded");
        } else if (!filmStripViewHolder.isAnimating()) {
            this.mFilmStripView.setProgress(this.mTotalTime, this.mCurrentTime);
            this.mSeekerView.updatePosition(this.mTotalTime, this.mCurrentTime);
        }
    }

    public void addListenEvent() {
        addEvent(Event.VIDEO_PLAY_TIME_UPDATED, new b(this, 0));
        addEvent(Event.PLAYER_KEEP_PAUSE, new b(this, 1));
        addEvent(Event.REPLAY_VIDEO, new b(this, 2));
    }

    public void initView(View view) {
        this.mVideoIndexWidth = view.getResources().getDimensionPixelSize(R.dimen.film_strip3_video_index_width);
        this.mSeekerView = (SeekerView) view.findViewById(R.id.seeker_view);
        FilmStripView filmStripView = (FilmStripView) view.findViewById(R.id.film_strip_view);
        this.mFilmStripView = filmStripView;
        this.mFilmStripViewPool.setRecycledViewPool(filmStripView);
        this.mFilmStripView.setEnableSnapHelper(false);
        this.mFilmStripView.addOnScrollListener(this.mFilmStripScrollListener);
        this.mFilmStripView.setAdapter(this.mFilmStripAdapter);
        this.mFilmStripView.scrollToPosition(0);
    }

    public void onAttach() {
        Context context = this.mView.getContext();
        MediaData mediaData = this.mView.getMediaData();
        if (this.mFilmStripAdapter == null && context != null && mediaData != null) {
            this.mFilmStripAdapter = new FilmStripAdapter(context, mediaData);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mFilmStripViewPool.onDestroy(this.mFilmStripView);
        this.mFilmStripAdapter.onDestroy();
        this.mFilmStripView = null;
        this.mFilmStripAdapter = null;
        this.mSeekerView = null;
    }
}
