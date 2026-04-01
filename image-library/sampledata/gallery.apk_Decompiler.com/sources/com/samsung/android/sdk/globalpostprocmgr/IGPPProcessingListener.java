package com.samsung.android.sdk.globalpostprocmgr;

import android.os.Bundle;
import android.os.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IGPPProcessingListener {
    void onTaskCompleted(Message message);

    void onTaskError();

    void onTaskProcessing(int i2, int i7);

    void onTaskRejected();

    void onTaskStopped();

    void onTaskSubmitted(Bundle bundle);
}
