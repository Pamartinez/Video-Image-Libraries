package com.samsung.android.sdk.scs.ai.language;

import B8.k;
import android.content.Context;
import android.os.RemoteException;
import c4.C0431a;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.NotesOrganizationServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.C;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NotesOrganizer {
    private static final String TAG = "NotesOrganizer";
    private final NotesOrganizationServiceExecutor mServiceExecutor;

    public NotesOrganizer(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new NotesOrganizationServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notesOrganize$0(AppInfo appInfo, String str, NotesOrganizationFormatType notesOrganizationFormatType, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((C) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, notesOrganizationFormatType.name(), llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Task<Result> notesOrganize(AppInfo appInfo, String str, NotesOrganizationFormatType notesOrganizationFormatType) {
        return notesOrganize(appInfo, str, notesOrganizationFormatType, new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public Task<Result> notesOrganize(AppInfo appInfo, String str, NotesOrganizationFormatType notesOrganizationFormatType, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_GEN_NOTES_ORGANIZATION, appInfo.isStreamingMode(), new k(this, appInfo, str, notesOrganizationFormatType, map, 11), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
