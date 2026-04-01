package com.samsung.android.sdk.scs.ai.text;

import android.content.ContentResolver;
import com.samsung.android.sdk.scs.base.connection.ProviderExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextServiceExecutor extends ProviderExecutor {
    private static final String TAG = "ScsApi@TextServiceExecutor";
    private ContentResolver mTextContentResolver;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TextServiceExecutor(android.content.Context r9) {
        /*
            r8 = this;
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.SECONDS
            java.util.concurrent.LinkedBlockingDeque r7 = new java.util.concurrent.LinkedBlockingDeque
            r7.<init>()
            r2 = 1
            r3 = 1
            r4 = 60
            r0 = r8
            r1 = r9
            r0.<init>(r1, r2, r3, r4, r6, r7)
            android.content.ContentResolver r8 = r1.getContentResolver()
            r0.mTextContentResolver = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.text.TextServiceExecutor.<init>(android.content.Context):void");
    }

    public ContentResolver getTextContentResolver() {
        return this.mTextContentResolver;
    }
}
