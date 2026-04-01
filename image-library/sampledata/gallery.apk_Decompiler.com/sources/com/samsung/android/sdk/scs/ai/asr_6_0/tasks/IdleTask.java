package com.samsung.android.sdk.scs.ai.asr_6_0.tasks;

import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IdleTask extends RecognitionTask<Boolean> {
    private static final String TAG = "IdleTask";

    public void execute() {
        Log.d(TAG, "connected");
        complete(Boolean.TRUE);
    }
}
