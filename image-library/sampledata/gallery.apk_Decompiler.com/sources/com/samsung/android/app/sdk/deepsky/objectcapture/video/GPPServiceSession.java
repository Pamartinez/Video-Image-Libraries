package com.samsung.android.app.sdk.deepsky.objectcapture.video;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.objectcapture.common.Rune;
import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.globalpostprocmgr.GPPProcessing;
import com.samsung.android.sdk.globalpostprocmgr.GPPService;
import com.samsung.android.sdk.globalpostprocmgr.GPPServiceSessionFactory;
import com.samsung.android.sdk.globalpostprocmgr.IGPPProcessingListener;
import com.samsung.android.sdk.globalpostprocmgr.IGPPServiceSessionListener;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 52\u00020\u00012\u00020\u0002:\u00015B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ%\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0019\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001a\u0010\u0018J\u000f\u0010\u001b\u001a\u00020\fH\u0016¢\u0006\u0004\b\u001b\u0010\u0018J\u0017\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010\"\u001a\u00020\f2\b\u0010!\u001a\u0004\u0018\u00010 H\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\fH\u0016¢\u0006\u0004\b$\u0010\u0018J\u000f\u0010%\u001a\u00020\fH\u0016¢\u0006\u0004\b%\u0010\u0018J\u000f\u0010&\u001a\u00020\fH\u0016¢\u0006\u0004\b&\u0010\u0018J\u001f\u0010*\u001a\u00020\f2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'H\u0016¢\u0006\u0004\b*\u0010+R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00101\u001a\u0004\u0018\u0001008\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b3\u00104¨\u00066"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPServiceSession;", "Lcom/samsung/android/sdk/globalpostprocmgr/IGPPServiceSessionListener;", "Lcom/samsung/android/sdk/globalpostprocmgr/IGPPProcessingListener;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "", "supportGPPM", "()Z", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMListener;", "listener", "Lme/x;", "connect", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMListener;)V", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMData;", "data", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoData;", "videoData", "", "stickerID", "runPP", "(Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMData;Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoData;Ljava/lang/String;)V", "disconnect", "()V", "onServiceBound", "onServiceUnbound", "onServiceError", "Landroid/os/Bundle;", "bundle", "onTaskSubmitted", "(Landroid/os/Bundle;)V", "Landroid/os/Message;", "message", "onTaskCompleted", "(Landroid/os/Message;)V", "onTaskRejected", "onTaskStopped", "onTaskError", "", "p0", "p1", "onTaskProcessing", "(II)V", "Landroid/content/Context;", "Lcom/samsung/android/sdk/globalpostprocmgr/GPPServiceSession;", "session", "Lcom/samsung/android/sdk/globalpostprocmgr/GPPServiceSession;", "Lcom/samsung/android/sdk/globalpostprocmgr/GPPProcessing;", "internalProcessing", "Lcom/samsung/android/sdk/globalpostprocmgr/GPPProcessing;", "sessionListener", "Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPMListener;", "Companion", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GPPServiceSession implements IGPPServiceSessionListener, IGPPProcessingListener {
    public static final Companion Companion = new Companion((e) null);
    public static final String TAG = "GPPServiceSession";
    public static final String TASK_VIDEO_CLIPPER = "VideoClipper";
    private final Context context;
    private GPPProcessing internalProcessing;
    private com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession session;
    private GPPMListener sessionListener;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/GPPServiceSession$Companion;", "", "<init>", "()V", "TAG", "", "TASK_VIDEO_CLIPPER", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public GPPServiceSession(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    private final boolean supportGPPM() {
        GPPService gPPService = new GPPService();
        try {
            gPPService.initialize(this.context);
            return gPPService.isFeatureEnabled(3);
        } catch (SsdkUnsupportedException unused) {
            Log.w(TAG, "[supportGPPM()] GPPService is not supported.");
            return false;
        }
    }

    public final void connect(GPPMListener gPPMListener) {
        j.e(gPPMListener, "listener");
        if (!Rune.INSTANCE.getSUPPORT_VIDEO_CLIPPER()) {
            Log.w(TAG, "[connect()] Not support native AI feature");
        } else if (!supportGPPM()) {
            Log.w(TAG, "[connect()] FEATURE_TASK_VIDEOCLIPPER is not supported.");
        } else {
            com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession build = new GPPServiceSessionFactory().setContext(this.context).build();
            this.session = build;
            this.sessionListener = gPPMListener;
            this.internalProcessing = new GPPProcessing(build);
            com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession gPPServiceSession = this.session;
            if (gPPServiceSession == null) {
                return;
            }
            if (gPPServiceSession.isServiceBound()) {
                Log.i(TAG, "[connect()] session already is connected.");
                return;
            }
            gPPServiceSession.setSessionListener(this);
            gPPServiceSession.connect();
        }
    }

    public final void disconnect() {
        if (!Rune.INSTANCE.getSUPPORT_VIDEO_CLIPPER()) {
            Log.w(TAG, "[disconnect()] Not support native AI feature");
        } else if (!supportGPPM()) {
            Log.w(TAG, "[disconnect()] FEATURE_TASK_VIDEOCLIPPER is not supported.");
        } else {
            com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession gPPServiceSession = this.session;
            if (gPPServiceSession != null) {
                if (gPPServiceSession.isServiceBound()) {
                    gPPServiceSession.disconnect();
                    this.session = null;
                    this.sessionListener = null;
                }
                gPPServiceSession.destroy();
            }
        }
    }

    public void onServiceBound() {
        Log.i(TAG, "onServiceBound");
        GPPMListener gPPMListener = this.sessionListener;
        if (gPPMListener != null) {
            gPPMListener.onServiceBound();
        }
    }

    public void onServiceError() {
        Log.i(TAG, "onServiceError");
    }

    public void onServiceUnbound() {
        Log.i(TAG, "onServiceUnbound");
    }

    public void onTaskCompleted(Message message) {
        Log.i(TAG, "onTaskCompleted");
        GPPMListener gPPMListener = this.sessionListener;
        if (gPPMListener != null && message != null) {
            Log.i(TAG, "send message : " + message);
            gPPMListener.onTaskCompleted(message);
        }
    }

    public void onTaskError() {
        Log.i(TAG, "onTaskError");
    }

    public void onTaskProcessing(int i2, int i7) {
        Log.i(TAG, "onTaskProcessing");
    }

    public void onTaskRejected() {
        Log.i(TAG, "onTaskRejected");
    }

    public void onTaskStopped() {
        Log.i(TAG, "onTaskStopped");
    }

    public void onTaskSubmitted(Bundle bundle) {
        j.e(bundle, "bundle");
        Log.i(TAG, "onTaskSubmitted");
    }

    public final void runPP(GPPMData gPPMData, VideoData videoData, String str) {
        GPPProcessing gPPProcessing;
        j.e(gPPMData, "data");
        j.e(videoData, "videoData");
        j.e(str, "stickerID");
        if (!Rune.INSTANCE.getSUPPORT_VIDEO_CLIPPER()) {
            Log.w(TAG, "[runPP()] Not support native AI feature");
        } else if (!supportGPPM()) {
            Log.w(TAG, "[runPP()] FEATURE_TASK_VIDEOCLIPPER is not supported.");
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(IParameterKey.QUICKPANEL_VIEW, gPPMData.getPanelString().getViewString());
            bundle.putString(IParameterKey.QUICKPANEL_IN_PROGRESS, gPPMData.getPanelString().getInProgressString());
            bundle.putString(IParameterKey.QUICKPANEL_COMPLETED, gPPMData.getPanelString().getCompletedString());
            bundle.putString(IParameterKey.QUICKPANEL_CLOSE, gPPMData.getPanelString().getCloseString());
            bundle.putString(IParameterKey.QUICKPANEL_CANCEL, gPPMData.getPanelString().getCancelString());
            bundle.putInt(IParameterKey.COORDINATE_X, (int) videoData.getPoint().x);
            bundle.putInt(IParameterKey.COORDINATE_Y, (int) videoData.getPoint().y);
            bundle.putInt(IParameterKey.SCREEN_WIDTH, videoData.getBitmap().getWidth());
            bundle.putInt(IParameterKey.SCREEN_HEIGHT, videoData.getBitmap().getHeight());
            bundle.putInt(IParameterKey.PLAYTIME, gPPMData.getVideoFrameIndex());
            bundle.putString("mode", "VideoClipper");
            bundle.putString(IParameterKey.DST_PATH, gPPMData.getDstPath());
            bundle.putString(IParameterKey.SRC_PATH, gPPMData.getImageUri().toString());
            bundle.putString(IParameterKey.STICKER_ID, str);
            GPPProcessing gPPProcessing2 = this.internalProcessing;
            if (gPPProcessing2 != null) {
                gPPProcessing2.setProcessingListener(this);
            }
            com.samsung.android.sdk.globalpostprocmgr.GPPServiceSession gPPServiceSession = this.session;
            if (gPPServiceSession != null && gPPServiceSession.isServiceBound() && (gPPProcessing = this.internalProcessing) != null) {
                gPPProcessing.run(bundle);
            }
        }
    }
}
