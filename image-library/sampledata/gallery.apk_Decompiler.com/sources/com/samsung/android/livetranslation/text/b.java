package com.samsung.android.livetranslation.text;

import com.samsung.android.livetranslation.text.LiveTranslation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LiveTranslation.LiveTranslationTaskManagerListenerImpl e;
    public final /* synthetic */ int f;

    public /* synthetic */ b(LiveTranslation.LiveTranslationTaskManagerListenerImpl liveTranslationTaskManagerListenerImpl, int i2, int i7) {
        this.d = i7;
        this.e = liveTranslationTaskManagerListenerImpl;
        this.f = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onTRLFinish$6(this.f);
                return;
            default:
                this.e.lambda$onTRLFinish$5(this.f);
                return;
        }
    }
}
