package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import B7.d;
import H7.B;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ForceSwipeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.actioninvoker.ActionInvokeListener;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataDelegate extends AbsVuDelegate<IVuContainerView> {
    private final MediaData.OnDataChangeListener mDataChangeListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            if (MediaDataDelegate.this.isForceSwiping()) {
                ((IVuContainerView) MediaDataDelegate.this.mView).printLog(MediaDataDelegate.this.TAG, "onDataChanged skip");
                MediaDataDelegate.this.registerForceSwipeListener();
                return;
            }
            MediaDataDelegate.this.onDataChanged(-1, -1);
        }

        public void onDataRangeChanged(int i2, int i7) {
            MediaDataDelegate.this.onDataChanged(i2, i7);
        }
    };
    private String mDataKey;
    private boolean mIsFinishing = false;
    private MediaData mMediaData;
    private final ActionInvokeListener mOnForceSwipeFinished = new d(20, this);
    private boolean mRegForceSwipeFinishedListener = false;

    public MediaDataDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this.mDataChangeListener);
            this.mMediaData.close();
            this.mMediaData = null;
        }
        this.mIsFinishing = false;
    }

    private void initValues(String str) {
        String viewerDataKey = DataKey.getViewerDataKey(str);
        this.mDataKey = viewerDataKey;
        openMediaData(viewerDataKey);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(Object[] objArr) {
        Log.d(this.TAG, "onForceSwipeFinished");
        onDataChanged(-1, -1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChanged$0(int i2, int i7) {
        ((IVuContainerView) this.mView).onMediaDataChanged(i2, i7);
    }

    /* access modifiers changed from: private */
    public void onDataChanged(int i2, int i7) {
        String str;
        boolean z;
        unRegisterForceSwipeListener();
        MediaData mediaData = this.mMediaData;
        if (mediaData == null) {
            Log.e(this.TAG, "onDataChanged but no media data");
            return;
        }
        int count = mediaData.getCount();
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("onDataChanged");
        Integer valueOf = Integer.valueOf(i2);
        Integer valueOf2 = Integer.valueOf(i7);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(count);
        if (count != 0) {
            str = "";
        } else if (this.mIsFinishing) {
            str = ",finishing";
        } else {
            str = ",idle";
        }
        sb3.append(str);
        sb2.append(Logger.v(valueOf, valueOf2, sb3.toString()));
        Log.p(str2, sb2.toString());
        if (!LocationKey.isSecondDepthGroupPanelView(((IVuContainerView) this.mView).getLocationKey()) || count != 0) {
            z = false;
        } else {
            z = true;
        }
        if ((count != 0 && !z) || !((ContainerModel) this.mModel).getStateHelper().isContainerResumed()) {
            ThreadUtil.postOnUiThread(new B(this, i2, i7, 5));
        } else if (!this.mIsFinishing) {
            ((IVuContainerView) this.mView).finish();
            this.mIsFinishing = true;
        }
    }

    private void openMediaData(String str) {
        int argValue = ArgumentsUtil.getArgValue(str, "child_media_data_index", -1);
        if (argValue >= 0) {
            MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(str);
            try {
                this.mMediaData = open.getChildMediaData(argValue).open(str);
                open.close();
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            this.mMediaData = MediaDataFactory.getInstance(this.mBlackboard).open(str);
        }
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.register(this.mDataChangeListener);
            return;
        }
        return;
        throw th;
    }

    /* access modifiers changed from: private */
    public void registerForceSwipeListener() {
        if (!this.mRegForceSwipeFinishedListener) {
            this.mActionInvoker.add(ViewerAction.FORCE_SWIPE_FINISHED, this.mOnForceSwipeFinished);
            this.mRegForceSwipeFinishedListener = true;
        }
    }

    private void unRegisterForceSwipeListener() {
        if (this.mRegForceSwipeFinishedListener) {
            this.mActionInvoker.remove(ViewerAction.FORCE_SWIPE_FINISHED, this.mOnForceSwipeFinished);
            this.mRegForceSwipeFinishedListener = false;
        }
    }

    public boolean isForceSwiping() {
        ForceSwipeDelegate forceSwipeDelegate = (ForceSwipeDelegate) ((IVuContainerView) this.mView).getDelegate(ForceSwipeDelegate.class);
        if (forceSwipeDelegate == null || !forceSwipeDelegate.isWorking()) {
            return false;
        }
        Log.d(this.TAG, "forceSwipeDelegate.isWorking");
        return true;
    }

    public void onCreate(Bundle bundle) {
        initValues(((IVuContainerView) this.mView).getLocationKey());
        ((ContainerModel) this.mModel).setMediaData(this.mMediaData);
    }

    public void onDestroy() {
        closeMediaData();
        unRegisterForceSwipeListener();
    }

    public void reopenMediaData(String str) {
        this.mDataKey = str;
        closeMediaData();
        this.mActionInvoker.invoke(ViewerAction.RESET_DATA_VERSION, new Object[0]);
        openMediaData(str);
        ((ContainerModel) this.mModel).setMediaData(this.mMediaData);
        ((IVuContainerView) this.mView).onMediaDataUpdated(this.mMediaData);
    }
}
