package com.samsung.android.app.sdk.deepsky.textextraction.translate;

import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ImageTranslationResult;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/ImageTranslateListener;", "", "Lme/x;", "onImageTranslateSuccess", "()V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;", "error", "onImageTranslateFail", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/data/ImageTranslationResult$Error;)V", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ImageTranslateListener {
    void onImageTranslateFail(ImageTranslationResult.Error error);

    void onImageTranslateSuccess();
}
