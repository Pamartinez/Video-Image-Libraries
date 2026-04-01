package com.samsung.android.sdk.scs.ai.asr.tasks;

import android.os.ParcelFileDescriptor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ SttRecognitionTask d;
    public final /* synthetic */ ParcelFileDescriptor.AutoCloseOutputStream e;

    public /* synthetic */ c(SttRecognitionTask sttRecognitionTask, ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream) {
        this.d = sttRecognitionTask;
        this.e = autoCloseOutputStream;
    }

    public final void run() {
        this.d.lambda$writeToPipe$0(this.e);
    }
}
