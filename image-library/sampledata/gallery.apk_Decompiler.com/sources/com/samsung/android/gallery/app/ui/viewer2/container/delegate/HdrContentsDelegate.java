package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import A.a;
import B7.d;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Window;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaCacheLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import g6.g;
import h.C0199b;
import i4.C0468a;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import k6.b;
import k7.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HdrContentsDelegate extends AbsVuDelegate<IVuContainerView> {
    private static final boolean SUPPORT_SYSTEM_CONFIG = SuperHdrConfig.supportSystemConfig();
    /* access modifiers changed from: private */
    public Activity mActivity;
    private final AtomicInteger mColorMode;
    private final int mColorModeBase;
    private final Runnable mColorModeChanger = new b(3, this);
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        /* access modifiers changed from: private */
        public /* synthetic */ Activity lambda$handleMessage$0() {
            return BlackboardUtils.readActivity(HdrContentsDelegate.this.mBlackboard);
        }

        public void handleMessage(Message message) {
            Activity activity = (Activity) Optional.ofNullable(HdrContentsDelegate.this.mActivity).orElseGet(new a(this));
            if (activity != null) {
                HdrContentsDelegate.this.changeColorMode(activity, message.what);
            } else {
                Log.e((CharSequence) HdrContentsDelegate.this.TAG, "changeColorMode failed. null activity", Logger.toColorMode(message.what));
            }
        }
    };
    private boolean mSuperHdrEnabled = true;
    private volatile Boolean mSystemConfigEnabled;

    public HdrContentsDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        this.mColorMode = atomicInteger;
        FragmentActivity activity = iVuContainerView.getActivity();
        this.mActivity = activity;
        int colorMode = activity.getWindow().getColorMode();
        this.mColorModeBase = colorMode;
        atomicInteger.set(colorMode);
        LaunchIntent launchIntent = LaunchIntent.get(iVuContainerView.getBlackboard());
        if (launchIntent != null && launchIntent.isFromCamera()) {
            this.mSuperHdrEnabled = launchIntent.isSuperHdrEnabled();
        }
        Log.d(this.TAG, "HdrContentsDelegate", Boolean.valueOf(this.mSuperHdrEnabled), Logger.toColorMode(colorMode));
    }

    private boolean isHdr(MediaItem mediaItem) {
        if (!mediaItem.isImage() || !mediaItem.isPhotoHdrSupported()) {
            return false;
        }
        if (mediaItem.isUriItem()) {
            return true;
        }
        if (mediaItem.isPostProcessing()) {
            String str = DetailsData.of(mediaItem).pppOriginal;
            if (str == null || !str.endsWith(".dng")) {
                return true;
            }
            Log.w((CharSequence) this.TAG, "skip hdr for dng ppp", Long.valueOf(mediaItem.getFileId()), Logger.getEncodedString(str));
            return false;
        } else if (mediaItem.getMimeTypeEnum() == MimeType.AVIF || mediaItem.hasPhotoHdrInfo() || mediaItem.isPhotoHdrCandidate() || MediaCacheLoader.getInstance().computeGainMap(mediaItem)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSystemSuperHdrConfigEnabled() {
        if (!SUPPORT_SYSTEM_CONFIG) {
            return true;
        }
        if (this.mSystemConfigEnabled == null) {
            this.mSystemConfigEnabled = (Boolean) Blackboard.getApplicationInstance().computeIfAbsent("global://setting/system/super_hdr", new C0468a(21));
        }
        return this.mSystemConfigEnabled.booleanValue();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$0(Object obj, Bundle bundle) {
        ((IVuContainerView) this.mView).finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$1(Object obj, Bundle bundle) {
        boolean z;
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        this.mSystemConfigEnabled = bool;
        if (((IVuContainerView) this.mView).isDestroyed() || !((IVuContainerView) this.mView).isViewActive()) {
            z = false;
        } else {
            z = true;
        }
        Log.i(this.TAG, "onSystemSuperHdrConfigChanged", obj, Boolean.valueOf(z));
        if (z) {
            postChangeColorMode();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$6() {
        MediaItem mediaItem;
        ContentModel contentModel = ((ContainerModel) this.mModel).getContentModel();
        if (contentModel != null) {
            mediaItem = contentModel.getMediaItem();
        } else {
            mediaItem = null;
        }
        if (contentModel == null || mediaItem == null) {
            Log.e(this.TAG, "changeColorMode#test-abort");
        } else if (!isSystemSuperHdrConfigEnabled()) {
            Log.d(this.TAG, "changeColorMode#test-skip(off)");
            changeColorMode(false, true);
        } else if (!contentModel.isViewConfirmed()) {
            Log.w((CharSequence) this.TAG, "changeColorMode#test-skip. view not ready", Long.valueOf(mediaItem.getFileId()));
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            boolean isHdr = isHdr(mediaItem);
            String str = this.TAG;
            a.A(new Object[]{Long.valueOf(mediaItem.getFileId()), Boolean.valueOf(isHdr), Long.valueOf(currentTimeMillis)}, new StringBuilder("changeColorMode#test"), str);
            changeColorMode(isHdr, true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandleEvent$4(ContentModel contentModel) {
        MediaItem mediaItem = contentModel.getMediaItem();
        if (!isSystemSuperHdrConfigEnabled()) {
            Log.d(this.TAG, "changeColorMode#InputRelease skip(off)");
        } else if (mediaItem != null && isHdr(mediaItem)) {
            Log.d(this.TAG, "changeColorMode#InputRelease", Long.valueOf(mediaItem.getFileId()));
            changeColorMode(true, false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandleEvent$5(ContentModel contentModel, Handler handler) {
        handler.post(new C0199b(7, this, contentModel));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$postChangeColorMode$3(Handler handler) {
        handler.removeCallbacksAndMessages((Object) null);
        handler.post(this.mColorModeChanger);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$2(Object[] objArr) {
        changeColorMode(false, false);
    }

    private void postChangeColorMode() {
        if (supportSuperHdr()) {
            Optional.of(((ContainerModel) this.mModel).getColorModeHandler()).ifPresent(new g(14, this));
        }
    }

    private boolean supportSuperHdr() {
        if (!SuperHdrConfig.SUPPORT || !SuperHdrConfig.isEnabled() || !this.mSuperHdrEnabled) {
            return false;
        }
        return true;
    }

    public void changeColorMode(boolean z, boolean z3) {
        int i2 = z ? 2 : this.mColorModeBase;
        this.mHandler.removeCallbacksAndMessages((Object) null);
        if (this.mActivity != null && i2 != this.mColorMode.get()) {
            Window window = this.mActivity.getWindow();
            int colorMode = window != null ? window.getColorMode() : 2;
            if (colorMode != i2) {
                Log.d(this.TAG, "changeColorMode#post", Logger.toColorMode(colorMode), Logger.toColorMode(i2));
                this.mHandler.sendEmptyMessageDelayed(i2, (i2 == 2 || !z3) ? 0 : 2500);
                return;
            }
            this.mColorMode.set(i2);
            Log.d(this.TAG, "changeColorMode#skip-post", Logger.toColorMode(i2));
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createGlobalSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("global://setting/showHdrImages", new i(this, 0)));
        if (SUPPORT_SYSTEM_CONFIG) {
            arrayList.add(new SubscriberInfo("global://setting/system/super_hdr", new i(this, 1)));
        }
    }

    public void onDestroy() {
        ((ContainerModel) this.mModel).getColorModeHandler().removeCallbacksAndMessages((Object) null);
        this.mHandler.removeCallbacksAndMessages((Object) null);
        changeColorMode(this.mActivity, this.mColorModeBase);
        this.mActivity = null;
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        ContentModel contentModel = ((ContainerModel) this.mModel).getContentModel();
        if (eventMessage.what != 4004 || contentModel == null || !contentModel.isViewConfirmed() || !supportSuperHdr()) {
            return super.onHandleEvent(eventMessage);
        }
        Optional.of(((ContainerModel) this.mModel).getColorModeHandler()).ifPresent(new e(27, this, contentModel));
        return true;
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        postChangeColorMode();
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        postChangeColorMode();
    }

    public void onPause() {
        this.mColorMode.set(-1);
    }

    public void onResume() {
        if (this.mColorMode.get() < 0) {
            postChangeColorMode();
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        if (SuperHdrConfig.SUPPORT && this.mSuperHdrEnabled) {
            actionInvoker.add(ViewerAction.TURN_OFF_HDR_MODE_NO_DELAY, new d(18, this));
        }
    }

    /* access modifiers changed from: private */
    public void changeColorMode(Activity activity, int i2) {
        Window window = activity.getWindow();
        if (window == null || !supportSuperHdr()) {
            Log.d(this.TAG, "changeColorMode skip", Boolean.valueOf(this.mSuperHdrEnabled), Logger.toColorMode(i2));
            return;
        }
        int colorMode = window.getColorMode();
        if (colorMode == i2) {
            return;
        }
        if (i2 != 2 || this.mBlackboard.isEmpty("userInput")) {
            Log.i(this.TAG, "changeColorMode", Logger.toColorMode(colorMode), Logger.toColorMode(i2));
            this.mColorMode.set(i2);
            window.setColorMode(i2);
            window.getDecorView().getRootView().postInvalidate();
            return;
        }
        Log.e(this.TAG, "changeColorMode#skip-input");
    }
}
