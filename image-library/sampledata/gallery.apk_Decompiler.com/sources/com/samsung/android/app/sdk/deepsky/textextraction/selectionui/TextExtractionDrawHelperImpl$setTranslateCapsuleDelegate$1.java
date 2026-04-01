package com.samsung.android.app.sdk.deepsky.textextraction.selectionui;

import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelperImpl;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0004¨\u0006\u0006"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelperImpl$setTranslateCapsuleDelegate$1", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleHelperImpl$TranslateCapsuleDelegate;", "Lme/x;", "onTranslateOn", "()V", "onTranslateOff", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionDrawHelperImpl$setTranslateCapsuleDelegate$1 implements CapsuleHelperImpl.TranslateCapsuleDelegate {
    final /* synthetic */ TextExtractionDrawHelperImpl this$0;

    public TextExtractionDrawHelperImpl$setTranslateCapsuleDelegate$1(TextExtractionDrawHelperImpl textExtractionDrawHelperImpl) {
        this.this$0 = textExtractionDrawHelperImpl;
    }

    public void onTranslateOff() {
        TextExtractionDrawHelper.ProgressBarCallback access$getProgressBarCallback$p = this.this$0.progressBarCallback;
        if (access$getProgressBarCallback$p != null) {
            access$getProgressBarCallback$p.finishProgressBar();
        }
        TranslateLanguageHelper access$getTranslateLangHelper$p = this.this$0.translateLangHelper;
        if (access$getTranslateLangHelper$p != null) {
            access$getTranslateLangHelper$p.invalidateLayout(LayoutState.HIDDEN);
        }
        this.this$0.textExtractionState = TextExtractionState.START_BY_BUTTON;
        View access$getTeView$p = this.this$0.teView;
        if (access$getTeView$p != null) {
            access$getTeView$p.invalidate();
            TextExtractionDrawHelperImpl textExtractionDrawHelperImpl = this.this$0;
            textExtractionDrawHelperImpl.setContentDescription(textExtractionDrawHelperImpl.textSelectionHelper.getContentDescription());
            return;
        }
        j.k("teView");
        throw null;
    }

    public void onTranslateOn() {
        this.this$0.clearAllSelection();
        TextExtractionDrawHelper.ProgressBarCallback access$getProgressBarCallback$p = this.this$0.progressBarCallback;
        if (access$getProgressBarCallback$p != null) {
            access$getProgressBarCallback$p.startProgressBar();
        }
    }
}
