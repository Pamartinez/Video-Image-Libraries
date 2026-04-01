package com.samsung.android.gallery.app.ui.viewer2.model;

import M4.j;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import androidx.fragment.app.Fragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseSystemUi;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.module.clip.objectcapture.ObjectCaptureHelper;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentModel implements BottomSheetState.StateListener, OverlayViewState.StateListener {
    private final Object LOCK_SUB_ITEMS = new Object();
    protected final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private int mBestItemIndex = -1;
    protected Bitmap mBitmap;
    protected final Blackboard mBlackboard;
    protected boolean mBmpLoaded = false;
    protected MediaItem mBmpMediaItem;
    private int mBottomOverlayHeight = 0;
    private ContentModel mChildModel = null;
    protected final ContainerModel mContainerModel;
    protected final Context mContext;
    private int mDetailsState = 4;
    private MarginParams mGroupPanelBaseMargin = new MarginParams();
    private boolean mInstantSlowMoOptionsTipRecognizeEnabled = false;
    private boolean mIsFirstView = false;
    private final AtomicBoolean mIsGroupItemLoading = new AtomicBoolean(false);
    private boolean mIsGroupPanelViewer = false;
    private boolean mIsHighlightFrcMode = false;
    private boolean mIsInstantSlowMoPlayEnabled = false;
    private boolean mIsPendingPhotoViewSet = false;
    private boolean mIsPlaying = false;
    private boolean mIsSelectMode = false;
    private boolean mIsUnsupportedVideo = false;
    protected MediaItem mMediaItem;
    private PhotoViewMotionControl mMotionControl;
    private MotionPlayViewer mMotionPlayViewer = MotionPlayViewer.PHOTO;
    private ObjectCaptureHelper.State mObjectCaptureState = ObjectCaptureHelper.State.NONE;
    private MediaItem mOriginMediaItem;
    private OverlayViewState mOverlayViewState = OverlayViewState.hide;
    private ContentModel mParentModel = null;
    protected int mPosition;
    protected Bitmap mPreViewBitmap;
    private MediaItem mRemasteredMediaItem;
    private int mSubItemCurrentIndex = -1;
    protected final ArrayList<MediaItem> mSubMediaItems = new ArrayList<>();
    private TextExtractionHelper.State mTextExtractionState = TextExtractionHelper.State.NONE;
    private int mVideoSeekPosition;
    private boolean mVideoSpeedChangeOnGoing = false;
    private boolean mViewConfirmed = false;
    protected String mViewerName;

    public ContentModel(Context context, Blackboard blackboard, ContainerModel containerModel) {
        this.mContext = context;
        this.mBlackboard = blackboard;
        Objects.requireNonNull(containerModel);
        this.mContainerModel = containerModel;
    }

    private MediaItem getSubMediaItem(int i2) {
        synchronized (this.LOCK_SUB_ITEMS) {
            if (i2 >= 0) {
                try {
                    if (i2 < this.mSubMediaItems.size()) {
                        MediaItem mediaItem = this.mSubMediaItems.get(i2);
                        return mediaItem;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return null;
        }
    }

    public void addSubMediaItem(MediaItem mediaItem) {
        synchronized (this.LOCK_SUB_ITEMS) {
            this.mSubMediaItems.add(mediaItem);
        }
    }

    public void clearSubMediaItem() {
        synchronized (this.LOCK_SUB_ITEMS) {
            this.mSubMediaItems.clear();
            this.mSubItemCurrentIndex = -1;
            this.mBestItemIndex = -1;
        }
    }

    public Activity getActivity() {
        return getContainerModel().getActivity();
    }

    public MediaItem getBestItem() {
        MediaItem mediaItem;
        if (this.mBestItemIndex < 0) {
            return null;
        }
        synchronized (this.LOCK_SUB_ITEMS) {
            mediaItem = this.mSubMediaItems.get(this.mBestItemIndex);
        }
        return mediaItem;
    }

    public int getBestItemIndex() {
        return this.mBestItemIndex;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public int getBottomOverlayHeight() {
        ContentModel contentModel = this.mParentModel;
        if (contentModel == null) {
            return this.mBottomOverlayHeight;
        }
        return contentModel.getBottomOverlayHeight();
    }

    public Bitmap getChildBitmap() {
        ContentModel contentModel = this.mChildModel;
        if (contentModel == null) {
            return null;
        }
        return contentModel.mBitmap;
    }

    public ContainerModel getContainerModel() {
        return this.mContainerModel;
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getDetailsState() {
        return this.mDetailsState;
    }

    public MarginParams getGroupPanelBaseMargin() {
        return this.mGroupPanelBaseMargin;
    }

    public MediaItem getMediaItem() {
        int i2 = this.mSubItemCurrentIndex;
        if (i2 < 0) {
            return this.mMediaItem;
        }
        return getSubMediaItem(i2);
    }

    public PhotoViewMotionControl getMotionControl() {
        return this.mMotionControl;
    }

    public MotionPlayViewer getMotionPlayViewer() {
        return this.mMotionPlayViewer;
    }

    public MediaItem getOriginMediaItem() {
        return this.mOriginMediaItem;
    }

    public OverlayViewState getOverlayViewState() {
        ContentModel contentModel = this.mChildModel;
        if (contentModel == null) {
            return this.mOverlayViewState;
        }
        return contentModel.getOverlayViewState();
    }

    public ContentModel getParentModel() {
        return this.mParentModel;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public Bitmap getPreViewBitmap() {
        Bitmap bitmap = this.mPreViewBitmap;
        if (bitmap != null) {
            return bitmap;
        }
        StringCompat stringCompat = this.TAG;
        Log.e(stringCompat, "mPreViewBitmap is null return bitmap : " + this.mBitmap);
        return this.mBitmap;
    }

    public MediaItem getRemasteredMediaItem() {
        return this.mRemasteredMediaItem;
    }

    public MediaItem getRepresentativeItem() {
        return this.mMediaItem;
    }

    public ModelStateHelper getStateHelper() {
        return this.mContainerModel.getStateHelper();
    }

    public int getSubItemCurrentIndex() {
        return this.mSubItemCurrentIndex;
    }

    public List<MediaItem> getSubItems() {
        ArrayList arrayList;
        synchronized (this.LOCK_SUB_ITEMS) {
            arrayList = new ArrayList(this.mSubMediaItems);
        }
        return arrayList;
    }

    public int getSubMediaItemCount() {
        int size;
        synchronized (this.LOCK_SUB_ITEMS) {
            size = this.mSubMediaItems.size();
        }
        return size;
    }

    public MvpBaseSystemUi getSystemUi() {
        return getContainerModel().getSystemUi();
    }

    public int getVideoSeekPosition() {
        return this.mVideoSeekPosition;
    }

    public String getViewerName() {
        return this.mViewerName;
    }

    public boolean hasChildModel() {
        if (this.mChildModel != null) {
            return true;
        }
        return false;
    }

    public boolean isBitmapLoaded() {
        return this.mBmpLoaded;
    }

    public boolean isFirstView() {
        return this.mIsFirstView;
    }

    public boolean isFragmentResumed() {
        Fragment containerFragment = this.mContainerModel.getContainerFragment();
        if (containerFragment == null || !containerFragment.isResumed()) {
            return false;
        }
        return true;
    }

    public boolean isGroupItemLoading() {
        return this.mIsGroupItemLoading.get();
    }

    public boolean isGroupPanelViewer() {
        return this.mIsGroupPanelViewer;
    }

    public boolean isHighlightFrcMode() {
        ContentModel contentModel = this.mChildModel;
        if (contentModel == null) {
            return this.mIsHighlightFrcMode;
        }
        return contentModel.isHighlightFrcMode();
    }

    public boolean isInMultiWindowMode() {
        if (getActivity() == null || !getActivity().isInMultiWindowMode()) {
            return false;
        }
        return true;
    }

    public boolean isInstantSlowMoOptionsTipRecognizeEnabled() {
        return this.mInstantSlowMoOptionsTipRecognizeEnabled;
    }

    public boolean isInstantSlowMoPlayEnabled() {
        return this.mIsInstantSlowMoPlayEnabled;
    }

    public boolean isObjectCaptureState() {
        ObjectCaptureHelper.State state = this.mObjectCaptureState;
        if (state == ObjectCaptureHelper.State.CAPTURING || state == ObjectCaptureHelper.State.CAPTURED) {
            return true;
        }
        return false;
    }

    public boolean isPendingPhotoViewSet() {
        return this.mIsPendingPhotoViewSet;
    }

    public boolean isPlaying() {
        return this.mIsPlaying;
    }

    public boolean isPppLoading() {
        if (getSubMediaItemCount() <= 0) {
            return this.mMediaItem.isPostProcessing();
        }
        return getSubItems().stream().anyMatch(new j(25));
    }

    public boolean isRecycled() {
        if (this.mMediaItem == null) {
            return true;
        }
        return false;
    }

    public boolean isSelectMode() {
        return this.mIsSelectMode;
    }

    public boolean isSingleTakenShot() {
        MediaItem mediaItem = getMediaItem();
        if (mediaItem == null || !mediaItem.isSingleTakenShot()) {
            return false;
        }
        return true;
    }

    public boolean isTextExtractionFullState() {
        ContentModel contentModel = this.mChildModel;
        if (contentModel != null) {
            return contentModel.isTextExtractionFullState();
        }
        TextExtractionHelper.State state = this.mTextExtractionState;
        if (state == TextExtractionHelper.State.FULL || state == TextExtractionHelper.State.FULL_INDIRECT) {
            return true;
        }
        return false;
    }

    public boolean isUnsupportedVideo() {
        return this.mIsUnsupportedVideo;
    }

    public boolean isVideoSpeedChangeOnGoing() {
        return this.mVideoSpeedChangeOnGoing;
    }

    public boolean isViewConfirmed() {
        return this.mViewConfirmed;
    }

    public void recycle() {
        this.mMediaItem = null;
        this.mPosition = -1;
        this.mBmpMediaItem = null;
        this.mBitmap = null;
        this.mPreViewBitmap = null;
        this.mBmpLoaded = false;
        this.mSubMediaItems.clear();
        this.mSubItemCurrentIndex = -1;
        this.mBestItemIndex = -1;
        this.mOverlayViewState = OverlayViewState.hide;
        this.mVideoSeekPosition = 0;
        this.mIsUnsupportedVideo = false;
        this.mIsSelectMode = false;
        this.mDetailsState = 4;
        this.mChildModel = null;
        this.mParentModel = null;
        this.mOriginMediaItem = null;
        this.mRemasteredMediaItem = null;
        this.mObjectCaptureState = ObjectCaptureHelper.State.NONE;
        this.mTextExtractionState = TextExtractionHelper.State.NONE;
        this.mIsFirstView = false;
        this.mIsPendingPhotoViewSet = false;
        this.mIsPlaying = false;
        this.mIsGroupPanelViewer = false;
        this.mIsHighlightFrcMode = false;
        this.mInstantSlowMoOptionsTipRecognizeEnabled = false;
        this.mIsInstantSlowMoPlayEnabled = false;
        this.mVideoSpeedChangeOnGoing = false;
        this.mViewConfirmed = false;
        this.mGroupPanelBaseMargin = new MarginParams();
        this.mMotionPlayViewer = MotionPlayViewer.PHOTO;
    }

    public Bitmap removeBitmap() {
        Bitmap bitmap = this.mBitmap;
        this.mBitmap = null;
        return bitmap;
    }

    public void set(MediaItem mediaItem, int i2) {
        this.mMediaItem = mediaItem;
        this.mPosition = i2;
        this.TAG.setTag(Integer.valueOf(i2));
    }

    public void setBestItemIndex(int i2) {
        if (i2 < this.mSubMediaItems.size()) {
            this.mBestItemIndex = i2;
            return;
        }
        throw new IndexOutOfBoundsException("out of index");
    }

    public boolean setBitmap(Bitmap bitmap, MediaItem mediaItem) {
        long j2;
        MediaItem mediaItem2 = getMediaItem();
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId()) {
            StringCompat stringCompat = this.TAG;
            long j3 = -1;
            if (mediaItem2 != null) {
                j2 = mediaItem2.getFileId();
            } else {
                j2 = -1;
            }
            Long valueOf = Long.valueOf(j2);
            if (mediaItem != null) {
                j3 = mediaItem.getFileId();
            }
            Log.w((CharSequence) stringCompat, "setBitmap failed", valueOf, Long.valueOf(j3), bitmap);
            return false;
        }
        this.mBitmap = bitmap;
        this.mBmpMediaItem = mediaItem;
        this.mBmpLoaded = true;
        return true;
    }

    public void setChildModel(ContentModel contentModel) {
        this.mChildModel = contentModel;
    }

    public void setDetailsState(int i2) {
        this.mDetailsState = i2;
    }

    public void setFirstView(boolean z) {
        this.mIsFirstView = z;
    }

    public void setGroupItemLoading(boolean z) {
        this.mIsGroupItemLoading.set(z);
    }

    public void setGroupMediaItems(List<MediaItem> list, int i2, int i7) {
        synchronized (this.LOCK_SUB_ITEMS) {
            this.mSubMediaItems.clear();
            this.mSubMediaItems.addAll(list);
            setBestItemIndex(i2);
            setSubItemCurrentIndex(i7);
        }
    }

    public void setGroupPanelBaseMargin(MarginParams marginParams) {
        this.mGroupPanelBaseMargin = marginParams;
    }

    public void setGroupPanelViewer(boolean z) {
        this.mIsGroupPanelViewer = z;
    }

    public void setHighlightFrcMode(boolean z) {
        this.mIsHighlightFrcMode = z;
    }

    public void setInstantSlowMoOptionsTipRecognizeEnabled(boolean z) {
        this.mInstantSlowMoOptionsTipRecognizeEnabled = z;
    }

    public void setInstantSlowMoPlayEnabled(boolean z) {
        this.mIsInstantSlowMoPlayEnabled = z;
    }

    public void setIsUnsupportedVideo(boolean z) {
        this.mIsUnsupportedVideo = z;
    }

    public void setMotionControl(PhotoViewMotionControl photoViewMotionControl) {
        this.mMotionControl = photoViewMotionControl;
    }

    public void setMotionPlayViewer(MotionPlayViewer motionPlayViewer) {
        this.mMotionPlayViewer = motionPlayViewer;
    }

    public void setObjectCaptureState(ObjectCaptureHelper.State state) {
        this.mObjectCaptureState = state;
    }

    public void setOriginMediaItem(MediaItem mediaItem) {
        this.mOriginMediaItem = mediaItem;
    }

    public void setOverlayViewState(OverlayViewState overlayViewState) {
        this.mOverlayViewState = overlayViewState;
    }

    public void setParentModel(ContentModel contentModel) {
        this.mParentModel = contentModel;
    }

    public void setPendingPhotoViewSet(boolean z) {
        ContentModel contentModel = this.mChildModel;
        if (contentModel != null) {
            contentModel.setPendingPhotoViewSet(z);
        } else {
            this.mIsPendingPhotoViewSet = z;
        }
    }

    public void setPlaying(boolean z) {
        this.mIsPlaying = z;
    }

    public void setPosition(int i2) {
        this.mPosition = i2;
    }

    public boolean setPreViewBmp(Bitmap bitmap, MediaItem mediaItem) {
        return setPreViewBmp(bitmap, mediaItem, false);
    }

    public void setRemasteredMediaItem(MediaItem mediaItem) {
        this.mRemasteredMediaItem = mediaItem;
    }

    public void setRepresentativeMediaItem(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void setSubItemCurrentIndex(int i2) {
        if (i2 < this.mSubMediaItems.size()) {
            this.mSubItemCurrentIndex = i2;
            return;
        }
        throw new IndexOutOfBoundsException("out of index");
    }

    public void setTextExtractionState(TextExtractionHelper.State state) {
        this.mTextExtractionState = state;
    }

    public void setVideoSeekPosition(int i2) {
        this.mVideoSeekPosition = i2;
    }

    public void setVideoSpeedChangeOnGoing(boolean z) {
        this.mVideoSpeedChangeOnGoing = z;
    }

    public void setViewConfirmed(boolean z) {
        this.mViewConfirmed = z;
    }

    public ContentModel setViewerName(String str) {
        this.mViewerName = str;
        return this;
    }

    public boolean supportViewerZoomOsd() {
        if (!PocFeatures.isEnabled(PocFeatures.ViewerZoomedOsd) || LocationKey.isSecondDepthGroupPanelView(this.mContainerModel.getLocationKey())) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "" + this.mPosition + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mMediaItem;
    }

    public boolean setPreViewBmp(Bitmap bitmap, MediaItem mediaItem, boolean z) {
        MediaItem mediaItem2 = getMediaItem();
        if (mediaItem == null || mediaItem2 == null || mediaItem.getFileId() != mediaItem2.getFileId() || (this.mBmpLoaded && MediaItemUtil.equals(mediaItem, this.mBmpMediaItem) && !z)) {
            StringCompat stringCompat = this.TAG;
            long j2 = -1;
            Long valueOf = Long.valueOf(mediaItem2 != null ? mediaItem2.getFileId() : -1);
            if (mediaItem != null) {
                j2 = mediaItem.getFileId();
            }
            Log.w((CharSequence) stringCompat, "setPreViewBmp failed", valueOf, Long.valueOf(j2));
            return false;
        }
        this.mBitmap = bitmap;
        this.mPreViewBitmap = bitmap;
        this.mBmpMediaItem = mediaItem;
        return true;
    }
}
