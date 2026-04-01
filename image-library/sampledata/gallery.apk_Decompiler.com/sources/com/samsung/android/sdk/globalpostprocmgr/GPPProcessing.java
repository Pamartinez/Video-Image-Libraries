package com.samsung.android.sdk.globalpostprocmgr;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GPPProcessing {
    private final String TAG = "GPPProcessing";
    private IGPPProcessingListener mProcessingListener;
    GPPServiceSession mSession;

    public GPPProcessing(GPPServiceSession gPPServiceSession) {
        this.mSession = gPPServiceSession;
    }

    private Message createMessage(Bundle bundle) {
        Message obtain = Message.obtain((Handler) null, 10);
        obtain.replyTo = this.mSession.getCallBackMessenger();
        obtain.arg1 = 1;
        obtain.setData(bundle);
        return obtain;
    }

    public void cancel() {
        String str;
        Log.i(this.TAG, "cancel: E");
        HashMap<String, Pair<String, IGPPProcessingListener>> requestIdToListenerMap = this.mSession.getRequestIdToListenerMap();
        Iterator<Map.Entry<String, Pair<String, IGPPProcessingListener>>> it = requestIdToListenerMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            Map.Entry next = it.next();
            if (((IGPPProcessingListener) ((Pair) next.getValue()).second).equals(this.mProcessingListener)) {
                str = (String) next.getKey();
                break;
            }
        }
        Long valueOf = Long.valueOf((String) requestIdToListenerMap.get(str).first);
        Log.i(this.TAG, "request id - " + str + "task id - " + valueOf);
        Bundle bundle = new Bundle();
        bundle.putString("request_id", str);
        bundle.putLong(IParameterKey.TASK_ID, valueOf.longValue());
        Message obtain = Message.obtain((Handler) null, 6);
        obtain.replyTo = this.mSession.getCallBackMessenger();
        obtain.arg1 = 1;
        obtain.setData(bundle);
        this.mSession.sendMessage(obtain);
        Log.i(this.TAG, "cancel: X");
    }

    public boolean checkTaskRun(long j2) {
        Bundle bundle = new Bundle();
        bundle.putLong("sec_media_id", j2);
        return this.mSession.sendMessageSync(createMessage(bundle), this.mProcessingListener);
    }

    public void run(Bundle bundle) {
        Log.i(this.TAG, "run: E");
        if (bundle != null) {
            if (bundle.containsKey("media_id") && !bundle.getString("mode").equals("StarTrailV2")) {
                long j2 = bundle.getLong("media_id");
                this.mSession.getContext().grantUriPermission("com.samsung.android.globalpostprocmgr", Uri.parse("content://media/external/images/media/" + j2), 3);
            }
            if (bundle.containsKey(IParameterKey.SEF_ARRAY)) {
                Bundle bundle2 = new Bundle();
                bundle2.putByteArray(IParameterKey.SEF_ARRAY, bundle.getByteArray(IParameterKey.SEF_ARRAY));
                bundle.putBundle(IParameterKey.BUNDLE_KEY_SEF_INFO, bundle2);
            }
            String generateRequestId = this.mSession.generateRequestId();
            bundle.putString("request_id", generateRequestId);
            this.mSession.mapRequestIdToListener(this.mProcessingListener, generateRequestId);
        }
        Message obtain = Message.obtain((Handler) null, 3);
        obtain.replyTo = this.mSession.getCallBackMessenger();
        obtain.arg1 = 1;
        obtain.setData(bundle);
        this.mSession.sendMessage(obtain);
        Log.i(this.TAG, "run: X");
    }

    public void setProcessingListener(IGPPProcessingListener iGPPProcessingListener) {
        this.mProcessingListener = iGPPProcessingListener;
    }

    public boolean checkTaskRun(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("request_id", str);
        return this.mSession.sendMessageSync(createMessage(bundle), this.mProcessingListener);
    }
}
