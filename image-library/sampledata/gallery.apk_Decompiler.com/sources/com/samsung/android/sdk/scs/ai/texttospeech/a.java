package com.samsung.android.sdk.scs.ai.texttospeech;

import com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeech;
import com.samsung.android.sdk.scs.base.tasks.OnCompleteListener;
import com.samsung.android.sdk.scs.base.tasks.Task;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements OnCompleteListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ TextToSpeech.OnConntectListener e;

    public /* synthetic */ a(TextToSpeech.OnConntectListener onConntectListener, int i2) {
        this.d = i2;
        this.e = onConntectListener;
    }

    public final void onComplete(Task task) {
        int i2 = this.d;
        TextToSpeech.OnConntectListener onConntectListener = this.e;
        switch (i2) {
            case 0:
                CustomTextToSpeech.lambda$bindSynthesizer$0(onConntectListener, task);
                return;
            default:
                TextToSpeech.lambda$bindSynthesizer$0(onConntectListener, task);
                return;
        }
    }
}
