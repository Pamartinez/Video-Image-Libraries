package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import Za.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Display;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.app.remote.v2.GalleryBasePresentation;
import com.samsung.android.gallery.app.remote.v2.GalleryHighResPresentation;
import com.samsung.android.gallery.app.remote.v2.GalleryPresentation;
import com.samsung.android.gallery.app.remote.v2.IPresentationView;
import com.samsung.android.gallery.app.remote.v2.IVuDispatcher;
import com.samsung.android.gallery.app.remote.v2.PresentationOccupiedException;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPager;
import com.samsung.android.gallery.app.remote.v2.PresentationViewPagerAdapter;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSeekerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.module.remote.v2.ObserverRegister;
import com.samsung.android.gallery.module.remote.v2.RemoteDisplayState;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import h.C0199b;
import i.C0212a;
import java.util.ArrayList;
import java.util.function.BiPredicate;
import k7.m;
import k7.n;
import k7.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MirroringDelegate extends AbsVuDelegate<IVuContainerView> implements ObserverRegister {
    public final boolean SUPPORT_EXTERNAL_TOUCH_GESTURE = false;
    private boolean mIsPresentationOccupied;
    private GalleryPresentation mPresentation;
    private IPresentationView mPresentationView;
    BiPredicate<MediaItem, String> mPreviewControlPredicate = new a(2);

    public MirroringDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private PresentationViewPagerAdapter createAdapter(MediaData mediaData) {
        return new PresentationViewPagerAdapter(getContext(), mediaData);
    }

    private GalleryPresentation createNewPresentation(Context context, Display display) {
        int hashCode = ((ContainerModel) this.mModel).hashCode();
        if (RemoteDisplayState.getInstance().supportHighRes()) {
            return new GalleryHighResPresentation(context, display, hashCode, (IVuDispatcher) null);
        }
        return new GalleryBasePresentation(context, display, hashCode, (IVuDispatcher) null);
    }

    private void destroyPresentation() {
        if (this.mPresentation != null) {
            Log.rm(this.TAG, "destroyPresentation");
            this.mPresentation.dismiss();
            this.mPresentation = null;
        }
        IPresentationView iPresentationView = this.mPresentationView;
        if (iPresentationView != null) {
            iPresentationView.clearViews();
            this.mPresentationView = null;
        }
        this.mActionInvoker.invoke(ViewerAction.REMOTE_DISPLAY_ELIMINATE_FAKED_DRAG_VIEW_CALLBACK, new Object[0]);
    }

    private void disableFilmStrip(Object obj) {
        FilmStripSeekerDelegate filmStripSeekerDelegate = (FilmStripSeekerDelegate) getDelegate(FilmStripSeekerDelegate.class);
        if (filmStripSeekerDelegate != null) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            boolean z = !booleanValue;
            if (booleanValue) {
                filmStripSeekerDelegate.restore();
            }
            filmStripSeekerDelegate.setSeekerEnabled(z);
        }
    }

    private boolean isPresentationAvailable() {
        if (!RemoteDisplayState.getInstance().isConnected() || !isSupportedScreen() || !((IVuContainerView) this.mView).isViewResumed() || ((IVuContainerView) this.mView).isInMultiWindowMode() || ((ContainerModel) this.mModel).getActivity() == null || !((ContainerModel) this.mModel).getActivity().hasWindowFocus() || this.mIsPresentationOccupied) {
            return false;
        }
        return true;
    }

    private boolean isSupportedScreen() {
        String locationKey = ((IVuContainerView) this.mView).getLocationKey();
        if (locationKey == null || !LocationKey.isRemoteDisplaySupportedView(locationKey) || ((IVuContainerView) this.mView).isFromLockScreen() || ((ContainerModel) this.mModel).isFlipCoverGallery()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(MediaItem mediaItem, String str) {
        if (PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION || !RemoteDisplayState.getInstance().isConnected() || RemoteDisplayState.getInstance().isBackgroundPlaying() || !LocationKey.isRemoteDisplaySupportedView(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPresentationPlaying$2(MediaItem mediaItem) {
        boolean z;
        if (!PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION || !mediaItem.isVideo()) {
            z = false;
        } else {
            z = true;
        }
        updateEnablePreviewPlay(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPresentation$1() {
        if (this.mPresentation == null) {
            try {
                createPresentation();
            } catch (PresentationOccupiedException unused) {
                Log.d(this.TAG, "createPresentation: failed");
                return;
            }
        }
        onPresentationPlaying();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEnablePreviewPlay$3() {
        disableFilmStrip(Boolean.valueOf(!((ContainerModel) this.mModel).isEnablePreviewPlay()));
    }

    /* access modifiers changed from: private */
    public void onBitmapLoaded(Object... objArr) {
        showPresentation();
    }

    /* access modifiers changed from: private */
    public void onDisplayOccupied(Object obj, Bundle bundle) {
        this.mIsPresentationOccupied = true;
    }

    /* access modifiers changed from: private */
    public void onDisplayReleased(Object obj, Bundle bundle) {
        this.mIsPresentationOccupied = false;
        showPresentation();
    }

    /* access modifiers changed from: private */
    public void onExecuteCurrentShotMode(Object obj, Bundle bundle) {
        if (((IVuContainerView) this.mView).hashCode() == ((Integer) obj).intValue()) {
            this.mActionInvoker.invoke(ViewerAction.EXECUTE_CURRENT_SHOT_MODE, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onHide(Object obj, Bundle bundle) {
        updateEnablePreviewPlay(true);
        destroyPresentation();
        this.mActionInvoker.invoke(ViewerAction.UPDATE_DLNA_BUTTON, Boolean.FALSE);
    }

    private void onHideMotionVideoView() {
        GalleryPresentation galleryPresentation = this.mPresentation;
        if (galleryPresentation != null) {
            galleryPresentation.hideMediaView();
        }
    }

    /* access modifiers changed from: private */
    public void onMotionPlayViewerChanged(Object... objArr) {
        if (this.mPresentation != null) {
            MotionPlayViewer motionPlayViewer = objArr[0];
            MotionPlayViewer motionPlayViewer2 = objArr[1];
            if (motionPlayViewer2.isPhoto) {
                onHideMotionVideoView();
            } else if (MotionPlayViewer.isViewModeChanged(motionPlayViewer, motionPlayViewer2)) {
                showPresentation();
            }
            updateEnablePreviewPlay(motionPlayViewer2.isVideo);
            this.mActionInvoker.invoke(ViewerAction.UPDATE_VIDEO_MIRRORING_UI, Boolean.valueOf(motionPlayViewer2.isVideo));
        }
    }

    private void onPresentationPlaying() {
        GalleryPresentation galleryPresentation;
        Bitmap bitmap;
        boolean z;
        boolean z3;
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && (galleryPresentation = this.mPresentation) != null) {
            galleryPresentation.clearController();
            if (currentViewer.getModel().getChildBitmap() != null) {
                bitmap = currentViewer.getModel().getChildBitmap();
            } else {
                bitmap = currentViewer.getModel().getBitmap();
            }
            Bitmap bitmap2 = bitmap;
            MediaItem mediaItem = currentViewer.getModel().getMediaItem();
            if (bitmap2 != null && mediaItem != null) {
                this.mPresentation.post(new C0199b(8, this, mediaItem));
                this.mPresentation.updateData(bitmap2, currentViewer.getModel().getMotionControl(), mediaItem, ((ContainerModel) this.mModel).getPosition(), useInternalPlay(mediaItem));
                boolean z7 = false;
                if (!mediaItem.isImage() || !RemoteDisplayState.getInstance().isDMRConnected() || mediaItem.isPrivateItem()) {
                    z = false;
                } else {
                    z = true;
                }
                if (!mediaItem.isVideo() || !supportPreviewPlayControllable(mediaItem)) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z || z3) {
                    z7 = true;
                }
                updateDMRButton(Boolean.valueOf(z7), (Bundle) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onShow(Object obj, Bundle bundle) {
        showPresentation();
    }

    /* access modifiers changed from: private */
    public void onVideoStarted(Object... objArr) {
        boolean z;
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        if (currentViewer != null && this.mPresentation != null && isPresentationAvailable()) {
            MediaItem mediaItem = currentViewer.getModel().getMediaItem();
            if (mediaItem == null || ((!mediaItem.isVideo() || !PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION) && (!mediaItem.isMotionPhoto() || MediaItemUtil.getMotionPhotoViewMode(mediaItem) == MotionPhotoViewMode.OFF))) {
                z = false;
            } else {
                z = true;
            }
            GalleryPresentation galleryPresentation = this.mPresentation;
            if (galleryPresentation != null && z) {
                galleryPresentation.showMediaView();
                this.mActionInvoker.invoke(ViewerAction.UPDATE_VIDEO_MIRRORING_UI, Boolean.TRUE);
            }
        }
    }

    /* access modifiers changed from: private */
    public void onWindowFocusChanged(Object... objArr) {
        if (objArr[0].booleanValue()) {
            showPresentation();
            return;
        }
        GalleryPresentation galleryPresentation = this.mPresentation;
        if (galleryPresentation == null) {
            return;
        }
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            destroyPresentation();
        } else {
            galleryPresentation.hide();
        }
    }

    private void showPresentation() {
        if (isPresentationAvailable()) {
            ThreadUtil.postOnUiThread(new n(this, 1));
        }
    }

    private boolean supportPreviewPlayControllable(MediaItem mediaItem) {
        if (mediaItem == null) {
            return true;
        }
        if (mediaItem.isVideo() && PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION) {
            return true;
        }
        if (!mediaItem.isVideo() || isPresentationAvailable()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateDMRButton(Object obj, Bundle bundle) {
        this.mActionInvoker.invoke(ViewerAction.UPDATE_DLNA_BUTTON, obj);
    }

    private void updateEnablePreviewPlay(boolean z) {
        BiPredicate<MediaItem, String> biPredicate;
        if (((ContainerModel) this.mModel).isEnablePreviewPlay() != z) {
            ((ContainerModel) this.mModel).enablePreviewPlay(z);
            this.mActionInvoker.invoke(ViewerAction.ENABLE_PREVIEW_PLAY, Boolean.valueOf(((ContainerModel) this.mModel).isEnablePreviewPlay()));
            ThreadUtil.runOnUiThread(new n(this, 0));
        }
        boolean isConnected = RemoteDisplayState.getInstance().isConnected();
        if (this.mPresentation == null || !isConnected || !((ContainerModel) this.mModel).isEnablePreviewPlay()) {
            this.mActionInvoker.invoke(ViewerAction.RESTORE_MEDIA_VIEW, new Object[0]);
            this.mActionInvoker.invoke(ViewerAction.UPDATE_VIDEO_MIRRORING_UI, Boolean.FALSE);
        } else {
            this.mActionInvoker.invoke(ViewerAction.CHANGE_MEDIA_VIEW, this.mPresentation.getMediaView(((ContainerModel) ((IVuContainerView) this.mView).getModel()).getPosition()));
            this.mActionInvoker.invoke(ViewerAction.UPDATE_VIDEO_MIRRORING_UI, Boolean.TRUE);
        }
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.CHANGE_PREVIEW_CONTROLLABLE;
        if (!((ContainerModel) ((IVuContainerView) this.mView).getModel()).isEnablePreviewPlay()) {
            biPredicate = this.mPreviewControlPredicate;
        } else {
            biPredicate = null;
        }
        actionInvoker.invoke(viewerAction, biPredicate);
    }

    /* access modifiers changed from: private */
    public void updateSmartViewButton(Object obj, Bundle bundle) {
        this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
    }

    private boolean useInternalPlay(MediaItem mediaItem) {
        return mediaItem.isGif();
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("command:///ExecuteCurrentShotMode", new o(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://remote2/event/show_presentation", new o(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://remote2/event/hide_presentation", new o(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://remote2/event/dmr_status_updated", new o(this, 3)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://remote2/event/smart_view_status_updated", new o(this, 4)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("event/remote/requestRemoteDisplayData", new o(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("event/remote/remoteDisplayOccupied", new o(this, 5)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("event/remote/remoteDisplayReleased", new o(this, 6)).setWorkingOnUI());
    }

    public void createPresentation() {
        GalleryPresentation createNewPresentation;
        Display connectedDisplay = RemoteDisplayState.getInstance().getConnectedDisplay();
        Context context = getContext();
        if (connectedDisplay == null || context == null) {
            String str = this.TAG;
            Log.rm(str, "createPresentation failed. {" + connectedDisplay + "}");
            return;
        }
        Log.rm(this.TAG, "createPresentation");
        try {
            createNewPresentation = createNewPresentation(context, connectedDisplay);
            this.mPresentation = createNewPresentation;
            PresentationViewPager presentationViewPager = new PresentationViewPager(context, createAdapter(((ContainerModel) this.mModel).getMediaData()), true);
            this.mPresentationView = presentationViewPager;
            this.mActionInvoker.invoke(ViewerAction.REMOTE_DISPLAY_FAKED_DRAG_VIEW_CALLBACK, presentationViewPager.getViewPagerCallback());
            this.mPresentation.setPresentationView(this.mPresentationView);
            if (createNewPresentation != null) {
                C0212a.v(createNewPresentation);
                return;
            }
            return;
        } catch (PresentationOccupiedException e) {
            throw e;
        } catch (Exception e7) {
            throw new PresentationOccupiedException(e7.getMessage());
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public int getRegisterKey() {
        return hashCode();
    }

    public void onCreate(Bundle bundle) {
        RemoteDisplayState.getInstance().registerObserver(this);
    }

    public void onDestroy() {
        RemoteDisplayState.getInstance().unregisterObserver(this);
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        int i2;
        switch (eventMessage.what) {
            case 9004:
                boolean booleanValue = ((Boolean) eventMessage.obj).booleanValue();
                int position = ((ContainerModel) this.mModel).getPosition();
                if (booleanValue) {
                    i2 = 1;
                } else {
                    i2 = -1;
                }
                this.mActionInvoker.invoke(ViewerAction.SCROLL_TO, Integer.valueOf(position + i2), Boolean.TRUE);
                break;
            case 9005:
                this.mActionInvoker.invoke(ViewerAction.BACK_KEY_PRESSED, new Object[0]);
                break;
            case 9006:
                new PlayVideoCmd().execute(((ContainerModel) this.mModel).getEventContext(), ((ContainerModel) this.mModel).getCurrentMediaItem());
                break;
            default:
                return false;
        }
        return true;
    }

    public void onMediaDataChanged(int i2, int i7) {
        GalleryPresentation galleryPresentation = this.mPresentation;
        if (galleryPresentation != null) {
            galleryPresentation.onDataChanged();
        }
        showPresentation();
    }

    public void onMultiWindowModeChanged(boolean z) {
        if (z) {
            destroyPresentation();
        } else {
            showPresentation();
        }
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        showPresentation();
    }

    public void onPause() {
        destroyPresentation();
    }

    public void onResume() {
        RemoteDisplayState.getInstance().onResume();
        showPresentation();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.BITMAP_LOADED, new m(this, 0));
        actionInvoker.add(ViewerAction.WINDOW_FOCUS_CHANGED, new m(this, 1));
        actionInvoker.add(ViewerAction.VIDEO_STARTED, new m(this, 2));
        actionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new m(this, 3));
    }
}
