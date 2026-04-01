package com.samsung.android.livetranslation.text;

import com.samsung.android.livetranslation.text.LiveTranslation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ LiveTranslation.LiveTranslationTaskManagerListenerImpl e;
    public final /* synthetic */ KeyFrame f;
    public final /* synthetic */ int g;

    public /* synthetic */ a(int i2, KeyFrame keyFrame, LiveTranslation.LiveTranslationTaskManagerListenerImpl liveTranslationTaskManagerListenerImpl) {
        this.d = 0;
        this.e = liveTranslationTaskManagerListenerImpl;
        this.g = i2;
        this.f = keyFrame;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                this.e.lambda$onTRLFinish$3(this.g, this.f);
                return;
            case 1:
                this.e.lambda$onTRLFinish$4(this.f, this.g);
                return;
            case 2:
                this.e.lambda$onSTRFinish$0(this.f, this.g);
                return;
            default:
                this.e.lambda$onSTRFinish$2(this.f, this.g);
                return;
        }
    }

    public /* synthetic */ a(LiveTranslation.LiveTranslationTaskManagerListenerImpl liveTranslationTaskManagerListenerImpl, KeyFrame keyFrame, int i2, int i7) {
        this.d = i7;
        this.e = liveTranslationTaskManagerListenerImpl;
        this.f = keyFrame;
        this.g = i2;
    }
}
