package com.samsung.android.app.sdk.deepsky.textextraction;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.TextExtractionDrawHelper;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtraction;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper;", "getTextExtractionDrawHelper", "(Landroid/content/Context;)Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/TextExtractionDrawHelper;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/Recognizer;", "getRecognizer", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/Recognizer;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TextExtraction {
    Recognizer getRecognizer();

    TextExtractionDrawHelper getTextExtractionDrawHelper(Context context);
}
