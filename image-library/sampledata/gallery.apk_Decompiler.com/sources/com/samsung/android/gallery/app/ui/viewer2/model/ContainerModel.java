package com.samsung.android.gallery.app.ui.viewer2.model;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowInsets;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseSystemUi;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.viewer.LastPreviewData;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import i.C0212a;
import java.io.Closeable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContainerModel implements Closeable, BottomSheetState.StateListener {
    private boolean mAudioEraserOff = true;
    private boolean mAudioEraserVisible = false;
    private boolean mAudioMute = true;
    private final Handler mColorModeHandler = new Handler(ThreadUtil.getBackgroundThreadLooper());
    private final WeakReference<Fragment> mContainerFragment;
    private ContentModel mContentModel;
    private int mDetailsState = 4;
    private boolean mEnablePreviewPlay = true;
    private ConcurrentHashMap<Class<?>, ViewerMenuItem> mEnabledMenuMap;
    private EventContext mEventContext;
    private FastOptionView mFastOptionView;
    private View mFilmStripLayout;
    private ViewStub mFilmStripLayoutViewStub;
    private int mInitialPos = -1;
    private boolean mIsFlipCoverGallery;
    Boolean mIsForViewing;
    private boolean mIsSelectMode;
    private boolean mIsTableMode;
    private LastPreviewData mLastPreviewData;
    private int mLatestVideoKey;
    private int mLatestVideoPos;
    private String mLocationKey;
    private MediaData mMediaData;
    private boolean mOsdVisible = true;
    private final Rect mOverlaySize = new Rect();
    int mPppRetryCounter = 5;
    private boolean mRemoteConnected;
    long mReservedPositionMediaId = -1;
    private final ArrayList<MediaItem> mSelectedItems = new ArrayList<>();
    private final ModelStateHelper mStateHelper = new ModelStateHelper(this);
    boolean mSupportPinchShrink = false;
    private final MvpBaseSystemUi mSystemUi;
    private AtomicBoolean mTransitionState;
    private ViewPager2 mViewPager;
    private int mWidgetVisibility;
    private WindowInsets mWindowInsets;

    public ContainerModel(MvpBaseSystemUi mvpBaseSystemUi, Fragment fragment) {
        this.mSystemUi = mvpBaseSystemUi;
        this.mContainerFragment = new WeakReference<>(fragment);
    }

    private WindowInsets getWindowInsets() {
        View view = getView();
        WindowInsets windowInsets = this.mWindowInsets;
        if (windowInsets != null || view == null) {
            return windowInsets;
        }
        return view.getRootWindowInsets();
    }

    private boolean isDismissInsets() {
        return SystemUi.isInMultiWindowMode(getActivity());
    }

    public void addSelectedItem(MediaItem mediaItem) {
        this.mSelectedItems.add(mediaItem);
    }

    public void bindView(EventContext eventContext, ViewPager2 viewPager2, ViewStub viewStub) {
        this.mEventContext = eventContext;
        this.mViewPager = viewPager2;
        this.mFilmStripLayoutViewStub = viewStub;
    }

    public void clearLastPreviewData() {
        this.mLastPreviewData = null;
    }

    public void clearSelectedItems() {
        this.mSelectedItems.clear();
    }

    public void close() {
        this.mContentModel = null;
        this.mEventContext = null;
        this.mViewPager = null;
        this.mFilmStripLayoutViewStub = null;
        this.mFilmStripLayout = null;
        this.mMediaData = null;
        this.mLastPreviewData = null;
        this.mWindowInsets = null;
        this.mEnablePreviewPlay = true;
    }

    public void decreasePppRetryCounter() {
        this.mPppRetryCounter--;
    }

    public void enablePinchShrink(boolean z) {
        this.mSupportPinchShrink = z;
    }

    public void enablePreviewPlay(boolean z) {
        this.mEnablePreviewPlay = z;
    }

    public Integer findMenuId(Class<?> cls) {
        ViewerMenuItem viewerMenuItem;
        ConcurrentHashMap<Class<?>, ViewerMenuItem> concurrentHashMap = this.mEnabledMenuMap;
        if (concurrentHashMap == null || (viewerMenuItem = concurrentHashMap.get(cls)) == null) {
            return null;
        }
        return Integer.valueOf(viewerMenuItem.getMenuId());
    }

    public Activity getActivity() {
        EventContext eventContext = this.mEventContext;
        if (eventContext != null) {
            return eventContext.getActivity();
        }
        return null;
    }

    public Blackboard getBlackboard() {
        EventContext eventContext = this.mEventContext;
        if (eventContext != null) {
            return eventContext.getBlackboard();
        }
        return null;
    }

    public Handler getColorModeHandler() {
        return this.mColorModeHandler;
    }

    public Fragment getContainerFragment() {
        return this.mContainerFragment.get();
    }

    public int getContainerWidgetVisibility() {
        return this.mWidgetVisibility;
    }

    public ContentModel getContentModel() {
        return this.mContentModel;
    }

    public MediaItem getCurrentMediaItem() {
        MediaData mediaData;
        MediaItem mediaItem;
        ContentModel contentModel = this.mContentModel;
        if (contentModel != null && (mediaItem = contentModel.getMediaItem()) != null) {
            return mediaItem;
        }
        int position = getPosition();
        if (position < 0 || (mediaData = this.mMediaData) == null) {
            return null;
        }
        return mediaData.read(position);
    }

    public Rect getCutouts() {
        if (isDismissInsets()) {
            return new Rect();
        }
        Rect displayCutoutRect = WindowUtils.getDisplayCutoutRect(getWindowInsets());
        return new Rect(displayCutoutRect.left, displayCutoutRect.top, displayCutoutRect.right, displayCutoutRect.bottom);
    }

    public int getDetailsState() {
        return this.mDetailsState;
    }

    public ConcurrentHashMap<Class<?>, ViewerMenuItem> getEnabledMenuMap() {
        return this.mEnabledMenuMap;
    }

    public EventContext getEventContext() {
        return this.mEventContext;
    }

    public FastOptionView getFastOptionView() {
        return this.mFastOptionView;
    }

    public View getFilmStripLayout() {
        return this.mFilmStripLayout;
    }

    public ViewStub getFilmStripViewStub() {
        return this.mFilmStripLayoutViewStub;
    }

    public int getInitialPos() {
        return this.mInitialPos;
    }

    public LastPreviewData getLastPreviewData() {
        return this.mLastPreviewData;
    }

    public int getLatestVideoPos(int i2) {
        int i7;
        if (this.mLatestVideoKey == i2) {
            i7 = this.mLatestVideoPos;
        } else {
            i7 = 0;
        }
        this.mLatestVideoKey = 0;
        this.mLatestVideoPos = 0;
        return i7;
    }

    public String getLocationKey() {
        return this.mLocationKey;
    }

    public MediaData getMediaData() {
        return this.mMediaData;
    }

    public Rect getOverlaySize() {
        if (isDismissInsets()) {
            return this.mOverlaySize;
        }
        Rect systemInsets = WindowUtils.getSystemInsets(getWindowInsets());
        Rect rect = this.mOverlaySize;
        return new Rect(rect.left + systemInsets.left, rect.top + systemInsets.top, rect.right + systemInsets.right, rect.bottom + systemInsets.bottom);
    }

    public Rect getOverlaySizeIgnoringVisibility() {
        if (isDismissInsets()) {
            return this.mOverlaySize;
        }
        Rect systemInsetsIgnoringVisibility = WindowUtils.getSystemInsetsIgnoringVisibility(getWindowInsets());
        Rect rect = this.mOverlaySize;
        return new Rect(rect.left + systemInsetsIgnoringVisibility.left, rect.top + systemInsetsIgnoringVisibility.top, rect.right + systemInsetsIgnoringVisibility.right, rect.bottom + systemInsetsIgnoringVisibility.bottom);
    }

    public int getPosition() {
        int i2;
        MediaData mediaData = this.mMediaData;
        if (mediaData != null && mediaData.getCount() > (i2 = this.mInitialPos) && i2 >= 0) {
            return i2;
        }
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            return viewPager2.getCurrentItem();
        }
        return 0;
    }

    public int getPppRetryCounter() {
        return this.mPppRetryCounter;
    }

    public long getReservedPositionMediaIdAndReset() {
        long j2 = this.mReservedPositionMediaId;
        this.mReservedPositionMediaId = -1;
        return j2;
    }

    public MediaItem[] getSelectedItems() {
        return (MediaItem[]) this.mSelectedItems.toArray(new MediaItem[0]);
    }

    public ModelStateHelper getStateHelper() {
        return this.mStateHelper;
    }

    public Rect getStatusBarInsets() {
        if (isDismissInsets()) {
            return new Rect();
        }
        Rect statusBarInsets = WindowUtils.getStatusBarInsets(getWindowInsets());
        return new Rect(statusBarInsets.left, statusBarInsets.top, statusBarInsets.right, statusBarInsets.bottom);
    }

    public Rect getSystemInsets() {
        if (isDismissInsets()) {
            return new Rect();
        }
        Rect systemInsets = WindowUtils.getSystemInsets(getWindowInsets());
        return new Rect(systemInsets.left, systemInsets.top, systemInsets.right, systemInsets.bottom);
    }

    public Rect getSystemInsetsIgnoringVisibility() {
        if (isDismissInsets()) {
            return new Rect();
        }
        Rect systemInsetsIgnoringVisibility = WindowUtils.getSystemInsetsIgnoringVisibility(getWindowInsets());
        return new Rect(systemInsetsIgnoringVisibility.left, systemInsetsIgnoringVisibility.top, systemInsetsIgnoringVisibility.right, systemInsetsIgnoringVisibility.bottom);
    }

    public Rect getSystemInsetsWithIme() {
        if (isDismissInsets()) {
            return new Rect();
        }
        Rect systemInsetsWithIme = WindowUtils.getSystemInsetsWithIme(getWindowInsets());
        return new Rect(systemInsetsWithIme.left, systemInsetsWithIme.top, systemInsetsWithIme.right, systemInsetsWithIme.bottom);
    }

    public MvpBaseSystemUi getSystemUi() {
        return this.mSystemUi;
    }

    public View getView() {
        Fragment fragment = this.mContainerFragment.get();
        if (fragment != null) {
            return fragment.getView();
        }
        return null;
    }

    public ViewPager2 getViewPager() {
        return this.mViewPager;
    }

    public boolean hasReservedPositionMediaIdExist() {
        if (this.mReservedPositionMediaId != -1) {
            return true;
        }
        return false;
    }

    public boolean isAppTransitionRunning() {
        AtomicBoolean atomicBoolean = this.mTransitionState;
        if (atomicBoolean == null || !atomicBoolean.get()) {
            return false;
        }
        return true;
    }

    public boolean isAudioEraserOff() {
        return this.mAudioEraserOff;
    }

    public boolean isAudioEraserVisible() {
        return this.mAudioEraserVisible;
    }

    public boolean isAudioMute() {
        return this.mAudioMute;
    }

    public boolean isEnablePreviewPlay() {
        return this.mEnablePreviewPlay;
    }

    public boolean isFlipCoverGallery() {
        return this.mIsFlipCoverGallery;
    }

    public boolean isOsdVisible() {
        return this.mOsdVisible;
    }

    public boolean isRemoteConnected() {
        return this.mRemoteConnected;
    }

    public boolean isSelectMode() {
        return this.mIsSelectMode;
    }

    public boolean isTableMode() {
        return this.mIsTableMode;
    }

    public MediaItem readCandidateMediaItemByViewPager() {
        return this.mMediaData.read(getPosition());
    }

    public void removeSelectedItem(MediaItem mediaItem) {
        this.mSelectedItems.remove(mediaItem);
    }

    public void resetLatestVideoPos(int i2) {
        if (this.mLatestVideoKey != i2) {
            this.mLatestVideoKey = 0;
            this.mLatestVideoPos = 0;
        }
    }

    public void setAudioEraserOff(boolean z) {
        this.mAudioEraserOff = z;
    }

    public void setAudioEraserVisible(boolean z) {
        this.mAudioEraserVisible = z;
    }

    public void setAudioMute(boolean z) {
        this.mAudioMute = z;
    }

    public void setContainerWidgetVisibility(int i2) {
        this.mWidgetVisibility = i2;
    }

    public void setContentModel(ContentModel contentModel) {
        this.mContentModel = contentModel;
    }

    public void setDetailsState(int i2) {
        String str;
        if (this.mDetailsState != i2) {
            if (i2 == 3) {
                str = "Expand";
            } else if (i2 == 4) {
                str = "Collapsed";
            } else if (i2 != 6) {
                str = null;
            } else {
                str = "Half";
            }
            if (str != null) {
                Log.majorEvent("ContainerModel", "setDetails : ".concat(str));
            }
        }
        this.mDetailsState = i2;
    }

    public void setEnabledMenuMap(ConcurrentHashMap<Class<?>, ViewerMenuItem> concurrentHashMap) {
        this.mEnabledMenuMap = concurrentHashMap;
    }

    public void setFastOptionView(FastOptionView fastOptionView) {
        this.mFastOptionView = fastOptionView;
    }

    public void setFilmStripLayout(View view) {
        this.mFilmStripLayout = view;
    }

    public void setFlipCoverGallery(boolean z) {
        this.mIsFlipCoverGallery = z;
    }

    public void setInitialPos(int i2) {
        this.mInitialPos = i2;
    }

    public void setLastPreviewData(LastPreviewData lastPreviewData, String str) {
        lastPreviewData.setTag(str);
        this.mLastPreviewData = lastPreviewData;
        if (lastPreviewData.isPostCandidate()) {
            Log.i("ContainerModel", C0212a.l("ppp set last preview data : ", str), Logger.v(lastPreviewData));
        }
    }

    public void setLatestVideoPos(int i2, int i7) {
        this.mLatestVideoKey = i2;
        this.mLatestVideoPos = i7;
    }

    public void setLocationKey(String str) {
        this.mLocationKey = str;
    }

    public void setMediaData(MediaData mediaData) {
        this.mMediaData = mediaData;
    }

    public void setOsdVisible(boolean z) {
        this.mOsdVisible = z;
    }

    public void setOverlaySize(int i2, int i7, int i8, int i10) {
        this.mOverlaySize.set(i2, i7, i8, i10);
    }

    public void setReservedPosition(long j2) {
        this.mReservedPositionMediaId = j2;
    }

    public void setSelectMode(boolean z) {
        this.mIsSelectMode = z;
    }

    public void setTableMode(boolean z) {
        this.mIsTableMode = z;
    }

    public void setTransitionState(AtomicBoolean atomicBoolean) {
        this.mTransitionState = atomicBoolean;
    }

    public void setWindowInsets(WindowInsets windowInsets) {
        this.mWindowInsets = windowInsets;
    }
}
