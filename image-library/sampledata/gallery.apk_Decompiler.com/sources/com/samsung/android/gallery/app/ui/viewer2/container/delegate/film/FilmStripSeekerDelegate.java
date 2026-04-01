package com.samsung.android.gallery.app.ui.viewer2.container.delegate.film;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.OnFilmStripItemClickListener;
import com.samsung.android.gallery.widget.filmstrip3.SeekerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import g6.g;
import h.C0199b;
import java.util.Optional;
import l7.C0484b;
import l7.C0485c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripSeekerDelegate extends AbsVuDelegate<IVuContainerView> implements OnFilmStripItemClickListener {
    /* access modifiers changed from: private */
    public int mDuration = 1;
    private FilmStripDelegate mFilmStripDelegate;
    private final RecyclerView.OnScrollListener mFilmStripScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                FilmStripSeekerDelegate.this.onIdleState();
            } else if (i2 == 1) {
                FilmStripSeekerDelegate.this.onDraggingState();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
            int i8;
            FilmStripViewHolder centerViewHolder = FilmStripSeekerDelegate.this.mFilmStripView.getCenterViewHolder();
            boolean z = true;
            if (FilmStripSeekerDelegate.this.mIsFilmDragging && centerViewHolder != null && centerViewHolder.isExpanded()) {
                View view = centerViewHolder.itemView;
                int width = (((FilmStripSeekerDelegate.this.mFilmStripView.getWidth() / 2) - ((int) view.getX())) - view.getPaddingLeft()) - (FilmStripSeekerDelegate.this.mVideIndexWidth / 2);
                if (Features.isEnabled(Features.IS_RTL)) {
                    width = (((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) - (FilmStripSeekerDelegate.this.mVideIndexWidth / 2)) - width;
                }
                float width2 = ((float) width) / ((float) (((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) - FilmStripSeekerDelegate.this.mVideIndexWidth));
                if (width2 < 0.0f) {
                    width2 = 0.0f;
                }
                if (width2 > 1.0f) {
                    width2 = 1.0f;
                }
                MediaItem mediaItem = centerViewHolder.getMediaItem();
                if (mediaItem == null || FilmStripSeekerDelegate.this.mDuration != 1) {
                    i8 = FilmStripSeekerDelegate.this.mDuration;
                } else {
                    i8 = VideoPropData.getVideoDuration(mediaItem);
                }
                int i10 = (int) (((float) i8) * width2);
                if (i10 != FilmStripSeekerDelegate.this.mPosition) {
                    FilmStripSeekerDelegate.this.mPosition = i10;
                    if (mediaItem == null || centerViewHolder.getItemViewType() != 3) {
                        FilmStripSeekerDelegate.this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_SEEK, Float.valueOf(width2), mediaItem);
                    } else {
                        FilmStripSeekerDelegate.this.mActionInvoker.invoke(ViewerAction.GROUP_CURRENT_ITEM_CHANGE_REQUEST, Integer.valueOf(i10));
                    }
                    FilmStripSeekerDelegate filmStripSeekerDelegate = FilmStripSeekerDelegate.this;
                    filmStripSeekerDelegate.mSeekerView.updatePosition(i8, filmStripSeekerDelegate.mPosition);
                }
            }
            FilmStripView filmStripView = FilmStripSeekerDelegate.this.mFilmStripView;
            if (centerViewHolder != null && centerViewHolder.isExpanded()) {
                z = false;
            }
            filmStripView.setEnableSnapHelper(z);
        }
    };
    protected FilmStripView mFilmStripView;
    /* access modifiers changed from: private */
    public boolean mIsFilmDragging = false;
    /* access modifiers changed from: private */
    public boolean mIsPageDragging = false;
    private MediaItem mMediaItem;
    /* access modifiers changed from: private */
    public int mPageDragState;
    /* access modifiers changed from: private */
    public int mPosition = 0;
    private boolean mSeekerEnabled = true;
    private boolean mSeekerUpdateEnabled = true;
    protected SeekerView mSeekerView;
    /* access modifiers changed from: private */
    public int mVideIndexWidth;
    private ViewPager2 mViewPager2;
    final ViewPager2.OnPageChangeCallback mViewPager2PageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        public void onPageScrollStateChanged(int i2) {
            boolean z = true;
            if (i2 == 1 || (FilmStripSeekerDelegate.this.mPageDragState == 0 && i2 == 2)) {
                FilmStripSeekerDelegate.this.mFilmStripView.stopScroll();
                FilmStripSeekerDelegate.this.mFilmStripView.restoreExpandedView();
            }
            FilmStripSeekerDelegate.this.mPageDragState = i2;
            FilmStripSeekerDelegate filmStripSeekerDelegate = FilmStripSeekerDelegate.this;
            if (i2 != 1) {
                z = false;
            }
            filmStripSeekerDelegate.mIsPageDragging = z;
        }
    };

    public FilmStripSeekerDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void bindFilmStripView(View view) {
        FilmStripView filmStripView = (FilmStripView) view.findViewById(R.id.film_strip_view);
        this.mFilmStripView = filmStripView;
        filmStripView.addOnScrollListener(this.mFilmStripScrollListener);
        this.mSeekerView = (SeekerView) view.findViewById(R.id.seeker_view);
    }

    private boolean checkEquals(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem != null && mediaItem2 != null && mediaItem.getFileId() == mediaItem2.getFileId() && mediaItem.getFileSize() == mediaItem2.getFileSize() && mediaItem.getDateModified() == mediaItem2.getDateModified() && mediaItem.getOrientation() == mediaItem2.getOrientation() && mediaItem.getStorageType() == mediaItem2.getStorageType()) {
            return true;
        }
        return false;
    }

    private float getSeekOffset() {
        float f;
        int i2;
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer == null || !currentViewer.getModel().isPlaying()) {
            f = (float) this.mPosition;
            i2 = this.mDuration;
        } else {
            f = (float) Math.min(this.mPosition + 200, this.mDuration);
            i2 = this.mDuration;
        }
        return f / ((float) i2);
    }

    private boolean isMotionPhotoPreviewing(ViewerObjectComposite viewerObjectComposite, MediaItem mediaItem) {
        if (viewerObjectComposite == null || mediaItem == null || !mediaItem.isMotionPhoto() || viewerObjectComposite.getModel().getMotionPlayViewer() != MotionPlayViewer.PHOTO_PREVIEW) {
            return false;
        }
        return true;
    }

    private boolean isMotionPhotoViewDisabled(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        try {
            if (mediaItem.isMotionPhoto()) {
                return ViewUtils.isVisible(((IVuContainerView) this.mView).getCurrentViewer().getViewHolder().getTransitionView());
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isPlayableMotionPhotoViewModeOnExpanded(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE || mediaItem == null || !mediaItem.isMotionPhoto() || MediaItemUtil.getMotionPhotoViewMode(mediaItem) == MotionPhotoViewMode.ON) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onItemRestored$8() {
        FilmStripViewHolder centerViewHolder;
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null && (centerViewHolder = filmStripView.getCenterViewHolder()) != null && centerViewHolder.getViewHolderPosition() != ((ContainerModel) this.mModel).getPosition()) {
            this.mFilmStripView.scrollToPosition(((ContainerModel) this.mModel).getPosition());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        bindFilmStripView(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$1(Object[] objArr) {
        this.mFilmStripView.restoreExpandedView(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$2(Object[] objArr) {
        if (objArr[0] == MotionPlayViewer.VIDEO_FILM) {
            FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
            ContentModel contentModel = ((ContainerModel) this.mModel).getContentModel();
            if (centerViewHolder != null && contentModel != null && centerViewHolder.getAbsoluteAdapterPosition() == contentModel.getPosition()) {
                restore();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$3(Object[] objArr) {
        restore();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$4() {
        this.mFilmStripView.stopScroll();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$5(Object[] objArr) {
        Log.d(this.TAG, "stop filmScroll by playButton");
        ThreadUtil.runOnUiThread(new C0485c(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$6(Object[] objArr) {
        this.mFilmStripView.restoreExpandedView(false);
    }

    private void loadDimenValues(Resources resources) {
        int i2;
        if (resources != null) {
            i2 = resources.getDimensionPixelSize(R.dimen.film_strip3_video_index_width);
        } else {
            i2 = 0;
        }
        this.mVideIndexWidth = i2;
    }

    /* access modifiers changed from: private */
    public void onVideoPlayTimeUpdated(Object... objArr) {
        FilmStripViewHolder centerViewHolder;
        if (!this.mIsPageDragging && (centerViewHolder = this.mFilmStripView.getCenterViewHolder()) != null && MediaItemUtil.equals(objArr[2], centerViewHolder.getMediaItem()) && !this.mIsFilmDragging) {
            int intValue = objArr[0].intValue();
            this.mDuration = intValue;
            if (intValue <= 0) {
                this.mDuration = 1;
            }
            this.mPosition = objArr[1].intValue();
            MediaItem mediaItem = objArr[2];
            this.mMediaItem = mediaItem;
            if (MediaItemUtil.equals(mediaItem, centerViewHolder.getMediaItem())) {
                updateSeeker(centerViewHolder);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateSeekerOnUiThread */
    public void lambda$updateSeeker$7(FilmStripViewHolder filmStripViewHolder) {
        if (this.mFilmStripView == null || this.mSeekerView == null) {
            Log.e(this.TAG, "updateSeekerOnUiThread failed. maybe viewer is destroyed");
        } else if (filmStripViewHolder.isExpanded()) {
            if (!filmStripViewHolder.isAnimating()) {
                if (this.mSeekerUpdateEnabled) {
                    this.mFilmStripView.setProgress(this.mDuration, this.mPosition);
                }
                this.mSeekerView.updatePosition(this.mDuration, this.mPosition);
            }
        } else if (filmStripViewHolder.supportSeekerOnRestored()) {
            this.mSeekerView.seek(this.mDuration, this.mPosition);
        } else {
            this.mSeekerView.hideProgressView();
        }
    }

    public void onBindView(View view) {
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        this.mViewPager2 = viewPager;
        viewPager.registerOnPageChangeCallback(this.mViewPager2PageChangeCallback);
        bindFilmStripView(view);
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            Optional.ofNullable(filmStripView.getCenterViewHolder()).ifPresent(new g(19, this));
        }
        if (z3 || z7) {
            loadDimenValues(((IVuContainerView) this.mView).getResources());
        }
    }

    public void onCreate(Bundle bundle) {
        this.mFilmStripDelegate = (FilmStripDelegate) getDelegate(FilmStripDelegate.class);
        loadDimenValues(((IVuContainerView) this.mView).getResources());
        this.mPageDragState = 0;
    }

    public void onDestroy() {
        this.mFilmStripView.removeOnScrollListener(this.mFilmStripScrollListener);
        ViewPager2 viewPager2 = this.mViewPager2;
        if (viewPager2 != null) {
            viewPager2.unregisterOnPageChangeCallback(this.mViewPager2PageChangeCallback);
        }
        FilmStripDelegate filmStripDelegate = this.mFilmStripDelegate;
        if (filmStripDelegate != null) {
            filmStripDelegate.removeFilmStripItemListener(this);
        }
        this.mFilmStripView = null;
        this.mSeekerView = null;
    }

    public void onDraggingState() {
        FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
        if (centerViewHolder != null && centerViewHolder.isExpanded()) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new Object[0]);
        }
        this.mIsFilmDragging = true;
    }

    public void onIdleState() {
        FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
        if (centerViewHolder != null && centerViewHolder.isExpanded() && this.mIsFilmDragging) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_SEEK_FINISH, new Object[0]);
        }
        this.mIsFilmDragging = false;
    }

    public void onItemRestored(int i2, FilmStripViewHolder filmStripViewHolder) {
        this.mIsFilmDragging = false;
        this.mFilmStripView.setSeekerMode(false);
        this.mFilmStripView.setEnableSnapHelper(true);
        ViewPager2 viewPager2 = this.mViewPager2;
        if (viewPager2 != null && viewPager2.getCurrentItem() == i2) {
            this.mActionInvoker.invoke(ViewerAction.FILM_STRIP_STATE_CHANGED, Boolean.FALSE, Integer.valueOf(i2));
        }
        MediaItem mediaItem = filmStripViewHolder.getMediaItem();
        if (mediaItem != null && mediaItem.isMotionPhoto()) {
            this.mPosition = 0;
        }
        ThreadUtil.postOnUiThreadDelayed(new C0485c(this, 1), 200);
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        if (!this.mIsPageDragging && !this.mIsFilmDragging) {
            ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
            FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
            if (centerViewHolder != null && centerViewHolder.isExpanded() && currentViewer != null && !currentViewer.getModel().isPlaying() && MediaItemUtil.equals(this.mMediaItem, centerViewHolder.getMediaItem())) {
                Log.d(this.TAG, "onPageInvalidate : fix expandedSeeker position");
                lambda$updateSeeker$7(centerViewHolder);
            }
        }
    }

    public void onPause() {
        restore();
    }

    public void onRequestExpandSeeker(int i2, FilmStripViewHolder filmStripViewHolder) {
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (!this.mFilmStripDelegate.isFilmStripScrollStateIdle() || !this.mFilmStripDelegate.isViewPagerScrollStateIdle()) {
            String str = this.TAG;
            Log.d(str, "mFilmStripDelegate.isFilmStripScrollStateIdle " + this.mFilmStripDelegate.isFilmStripScrollStateIdle());
            String str2 = this.TAG;
            Log.d(str2, "mFilmStripDelegate.isViewPagerScrollStateIdle " + this.mFilmStripDelegate.isViewPagerScrollStateIdle());
        } else if (currentViewer != null && currentViewer.getModel().isUnsupportedVideo()) {
            Utils.showToast(((IVuContainerView) this.mView).getContext(), (int) R.string.cant_play_video_file_type_not_supported);
        } else if (this.mSeekerEnabled) {
            MediaItem mediaItem = filmStripViewHolder.getMediaItem();
            if (!isPlayableMotionPhotoViewModeOnExpanded(mediaItem) || isMotionPhotoPreviewing(currentViewer, mediaItem)) {
                String str3 = this.TAG;
                Log.w(str3, "Not playable motion photo : view mode = " + MediaItemUtil.getMotionPhotoViewMode(mediaItem));
                return;
            }
            if (filmStripViewHolder.getItemViewType() == 3) {
                this.mDuration = mediaItem.getCount();
                this.mPosition = ((ContainerModel) this.mModel).getContentModel().getBestItemIndex();
                this.mMediaItem = mediaItem;
            }
            float f = 0.0f;
            if (checkEquals(this.mMediaItem, mediaItem)) {
                if (!isMotionPhotoViewDisabled(mediaItem)) {
                    f = getSeekOffset();
                }
                filmStripViewHolder.expandSeekerMode(f);
            } else {
                filmStripViewHolder.expandSeekerMode(0.0f);
            }
            this.mActionInvoker.invoke(ViewerAction.FILM_STRIP_STATE_CHANGED, Boolean.TRUE, Integer.valueOf(i2));
            this.mFilmStripView.setSeekerMode(true);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        FilmStripDelegate filmStripDelegate = this.mFilmStripDelegate;
        if (filmStripDelegate != null) {
            filmStripDelegate.addFilmStripItemListener(this);
        }
    }

    public void restore() {
        Log.d(this.TAG, "restore");
        FilmStripView filmStripView = this.mFilmStripView;
        if (filmStripView != null) {
            filmStripView.stopScroll();
            FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
            if (centerViewHolder != null && centerViewHolder.isExpanded() && !centerViewHolder.isAnimating()) {
                centerViewHolder.restoreFrameView(this.mFilmStripView);
            }
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.PLAY_TIME_UPDATED, new C0484b(this, 0));
        actionInvoker.add(ViewerAction.FILM_STRIP_LAYOUT_REPLACED, new C0484b(this, 1));
        actionInvoker.add(ViewerAction.FILM_STRIP_RESTORE, new C0484b(this, 2));
        actionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new C0484b(this, 3));
        actionInvoker.add(ViewerAction.PREPARE_FORCE_SWIPE, new C0484b(this, 4));
        actionInvoker.add(ViewerAction.PLAY_PAUSE_CLICKED, new C0484b(this, 5));
        actionInvoker.add(ViewerAction.SCROLL_BY_DIRECTION, new C0484b(this, 6));
    }

    public void setSeekerEnabled(boolean z) {
        this.mSeekerEnabled = z;
    }

    public void setSeekerUpdateEnabled(boolean z) {
        this.mSeekerUpdateEnabled = z;
        FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
        if (this.mSeekerUpdateEnabled && centerViewHolder != null) {
            lambda$updateSeeker$7(centerViewHolder);
        }
    }

    public void updateSeeker(FilmStripViewHolder filmStripViewHolder) {
        ThreadUtil.postOnUiThread(new C0199b(11, this, filmStripViewHolder));
    }
}
