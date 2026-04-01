package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import B7.d;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.adobe.internal.xmp.options.PropertyOptions;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.app.remote.DlnaManager;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.remote.DlnaIntent;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import h.C0199b;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import k6.b;
import k7.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DlnaDelegate extends AbsVuDelegate<IVuContainerView> {
    private DlnaManager mDlnaManager;
    private boolean mFocused;
    private final AtomicBoolean mOnceInit = new AtomicBoolean(false);

    public DlnaDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void connectOriginalContents(Object... objArr) {
        MediaItem mediaItem;
        if (this.mView != null) {
            mediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        } else {
            mediaItem = null;
        }
        if (mediaItem == null) {
            Log.rme(this.TAG, "connectOriginalContents skip. null item");
            return;
        }
        if (mediaItem.isVideo()) {
            launchVideoPlayer(mediaItem);
        } else {
            if (this.mDlnaManager == null) {
                createDlnaManager((String) null);
            }
            if (this.mDlnaManager.isBound()) {
                this.mDlnaManager.connectOriginalContents(((IVuContainerView) this.mView).hashCode(), mediaItem);
            } else {
                this.mDlnaManager.setOnDlnaServiceBound(new C0199b(6, this, mediaItem));
            }
        }
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString(), AnalyticsEventId.EVENT_DETAIL_VIEW_CAST_IN_FULL_QUALITY.toString(), mediaItem.getWidth() + "X" + mediaItem.getHeight() + " , " + mediaItem.getMimeType());
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("connectOriginalContents");
        sb2.append(Logger.v(mediaItem, this.mDlnaManager));
        Log.rm(str, sb2.toString());
    }

    private void createDlnaManager(String str) {
        String str2 = this.TAG;
        Log.rme(str2, "createDlnaManager" + Logger.v(str));
        if (this.mDlnaManager == null) {
            this.mDlnaManager = new DlnaManager();
        }
        this.mDlnaManager.create(str, ((ContainerModel) this.mModel).getCurrentMediaItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$connectOriginalContents$2(MediaItem mediaItem, DlnaManager dlnaManager) {
        dlnaManager.connectOriginalContents(((IVuContainerView) this.mView).hashCode(), mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$connectOriginalContents$3(MediaItem mediaItem) {
        Optional.ofNullable(this.mDlnaManager).ifPresent(new e(26, this, mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$0(Object obj, Bundle bundle) {
        Log.d(this.TAG, "DlnaConnectReqStateChanged", obj);
        String str = (String) obj;
        if (str == null) {
            releaseDlnaManager();
            return;
        }
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        createDlnaManager(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createGlobalSubscriberList$1(Object obj, Bundle bundle) {
        Log.d(this.TAG, "DlnaStateChanged", obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchVideoPlayer$4() {
        this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$launchVideoPlayer$5(Object obj, Bundle bundle) {
        ThreadUtil.runOnUiThread(new b(2, this));
    }

    private void launchVideoPlayer(MediaItem mediaItem) {
        EventContext eventContext;
        if (mediaItem == null) {
            Log.rme(this.TAG, "OpenInVideoPlayer Menu Select failed: null item");
            return;
        }
        V v = this.mView;
        if (v != null) {
            eventContext = ((IVuContainerView) v).getEventContext();
        } else {
            eventContext = null;
        }
        if (eventContext == null) {
            Log.rme(this.TAG, "eventContext is null so do not launch video player");
            return;
        }
        this.mActionInvoker.invoke(ViewerAction.PREPARE_VIDEO_APP_TRANSITION, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.SET_DECOR_VISIBILITY, Boolean.FALSE);
        if (SeApiCompat.isFreeFormMode(false) || SeApiCompat.isDexMode(((IVuContainerView) this.mView).getContext())) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_VIEW_PAUSE_FOR_MULTI_WINDOW, new Object[0]);
        }
        eventContext.subscribeInstant("lifecycle://on_activity_resume", new G7.e(7, this));
        new PlayVideoCmd().execute(eventContext, mediaItem);
    }

    /* access modifiers changed from: private */
    public void onDisconnectDMRContent(Object obj, Bundle bundle) {
        DlnaManager dlnaManager = this.mDlnaManager;
        if (dlnaManager != null) {
            dlnaManager.disconnectOriginalContents();
        }
    }

    private void releaseDlnaManager() {
        DlnaManager dlnaManager = this.mDlnaManager;
        if (dlnaManager != null) {
            dlnaManager.destroy();
            this.mDlnaManager = null;
        }
    }

    private void startDlnaActivity() {
        Log.rm(this.TAG, "startDlnaActivity");
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.DlnaActivity");
            intent.putExtra("blackboard_name", this.mBlackboard.getName());
            intent.addFlags(PropertyOptions.DELETE_EXISTING);
            this.mFocused = false;
            BlackboardUtils.readActivity(this.mBlackboard).startActivityForResult(intent, 801);
        } catch (Exception e) {
            String str = this.TAG;
            Log.rme(str, "startDlnaActivity failed. e=" + e.getMessage());
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://remote2/event/disconnect_dmr_content", new h(this, 0)));
        arrayList.add(new SubscriberInfo("event/dlnaConnectReqStateChanged", new h(this, 1)).setWorkingOnUI().setTriggerPreloadedAsync());
        arrayList.add(new SubscriberInfo("event/dlnaStateChanged", new h(this, 2)).setWorkingOnUI().setTriggerPreloadedAsync());
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (((ContainerModel) this.mModel).getActivity() == null || !((ContainerModel) this.mModel).getActivity().hasWindowFocus() || eventMessage.what != 9003) {
            return false;
        }
        startDlnaActivity();
        return true;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        String str;
        if (!this.mOnceInit.getAndSet(true)) {
            str = DlnaIntent.getDeviceAddress(((IVuContainerView) this.mView).getActivity().getIntent());
        } else {
            str = null;
        }
        if (str != null) {
            createDlnaManager(str);
        } else if (this.mDlnaManager != null) {
            this.mDlnaManager.updateContents(((ContainerModel) this.mModel).getCurrentMediaItem());
        }
    }

    public void onStart() {
        this.mFocused = true;
    }

    public void onStop() {
        if (this.mFocused) {
            releaseDlnaManager();
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        super.setActionInvokeListener(actionInvoker);
        actionInvoker.add(ViewerAction.CONNECT_ORIGINAL_CONTENTS, new d(17, this));
    }
}
