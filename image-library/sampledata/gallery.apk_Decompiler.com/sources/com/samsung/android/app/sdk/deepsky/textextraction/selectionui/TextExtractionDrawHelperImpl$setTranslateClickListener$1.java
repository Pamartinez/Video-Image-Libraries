package com.samsung.android.app.sdk.deepsky.textextraction.selectionui;

import O8.b;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.TranslateCapsuleListener;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0004¨\u0006\u0006"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelperImpl$setTranslateClickListener$1", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/TranslateCapsuleListener;", "Lme/x;", "onTranslateOn", "()V", "onTranslateOff", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionDrawHelperImpl$setTranslateClickListener$1 implements TranslateCapsuleListener {
    final /* synthetic */ TextExtractionDrawHelper.TranslateCapsuleClickListener $listener;

    public TextExtractionDrawHelperImpl$setTranslateClickListener$1(TextExtractionDrawHelper.TranslateCapsuleClickListener translateCapsuleClickListener) {
        this.$listener = translateCapsuleClickListener;
    }

    public void onTranslateOff() {
        ((b) this.$listener).a(false);
    }

    public void onTranslateOn() {
        ((b) this.$listener).a(true);
    }
}
