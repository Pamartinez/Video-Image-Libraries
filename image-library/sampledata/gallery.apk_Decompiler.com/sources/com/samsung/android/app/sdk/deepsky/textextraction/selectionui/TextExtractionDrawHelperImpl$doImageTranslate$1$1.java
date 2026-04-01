package com.samsung.android.app.sdk.deepsky.textextraction.selectionui;

import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleHelperImpl;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.util.TextExtractionState;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.ImageTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.LayoutState;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TranslateLanguageHelper;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationResult;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"com/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelperImpl$doImageTranslate$1$1", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;", "Lme/x;", "onImageTranslateSuccess", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "error", "onImageTranslateFail", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionDrawHelperImpl$doImageTranslate$1$1 implements ImageTranslateListener {
    final /* synthetic */ LayoutState $languageLayoutState;
    final /* synthetic */ ImageTranslator $translator;
    final /* synthetic */ TextExtractionDrawHelperImpl this$0;

    public TextExtractionDrawHelperImpl$doImageTranslate$1$1(TextExtractionDrawHelperImpl textExtractionDrawHelperImpl, LayoutState layoutState, ImageTranslator imageTranslator) {
        this.this$0 = textExtractionDrawHelperImpl;
        this.$languageLayoutState = layoutState;
        this.$translator = imageTranslator;
    }

    public void onImageTranslateFail(ImageTranslationResult.Error error) {
        j.e(error, "error");
        LibLogger.i("TextExtractionDrawHelperImpl", "doImageTranslate failed: " + error);
        TextExtractionDrawHelper.ProgressBarCallback access$getProgressBarCallback$p = this.this$0.progressBarCallback;
        if (access$getProgressBarCallback$p != null) {
            access$getProgressBarCallback$p.finishProgressBar();
        }
        CapsuleHelperImpl access$getCapsuleHelper$p = this.this$0.capsuleHelper;
        if (access$getCapsuleHelper$p != null) {
            access$getCapsuleHelper$p.turnOffTranslate();
        }
        this.this$0.textExtractionState = TextExtractionState.START_BY_BUTTON;
    }

    public void onImageTranslateSuccess() {
        LibLogger.i("TextExtractionDrawHelperImpl", "doImageTranslate success");
        this.this$0.clearAllSelection();
        this.this$0.textExtractionState = TextExtractionState.IMAGE_TRANSLATION;
        TextExtractionDrawHelper.ProgressBarCallback access$getProgressBarCallback$p = this.this$0.progressBarCallback;
        if (access$getProgressBarCallback$p != null) {
            access$getProgressBarCallback$p.finishProgressBar();
        }
        TranslateLanguageHelper access$getTranslateLangHelper$p = this.this$0.translateLangHelper;
        if (access$getTranslateLangHelper$p != null) {
            access$getTranslateLangHelper$p.invalidateLayout(this.$languageLayoutState);
        }
        this.this$0.setContentDescription(this.$translator.getLastTranslatedText());
        View access$getTeView$p = this.this$0.teView;
        if (access$getTeView$p != null) {
            access$getTeView$p.invalidate();
        } else {
            j.k("teView");
            throw null;
        }
    }
}
