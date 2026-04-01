package com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper;

import android.content.Context;
import com.samsung.android.sdk.ocr.OCRType;
import com.samsung.android.sdk.ocr.Recognizer;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/ocrwrapper/OcrWrapper;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Lcom/samsung/android/sdk/ocr/OCRType;", "ocrType", "", "isSupported", "(Landroid/content/Context;Lcom/samsung/android/sdk/ocr/OCRType;)Z", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OcrWrapper {
    public static final OcrWrapper INSTANCE = new OcrWrapper();

    private OcrWrapper() {
    }

    public static /* synthetic */ boolean isSupported$default(OcrWrapper ocrWrapper, Context context, OCRType oCRType, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            oCRType = OCRType.OCR_ALL;
        }
        return ocrWrapper.isSupported(context, oCRType);
    }

    public final boolean isSupported(Context context, OCRType oCRType) {
        j.e(context, "context");
        j.e(oCRType, "ocrType");
        return Recognizer.isSupported(context, oCRType);
    }
}
