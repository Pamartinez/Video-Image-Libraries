package com.samsung.android.app.sdk.deepsky.textextraction.translate.engine;

import android.content.Context;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.TextTranslator;
import com.samsung.android.app.sdk.deepsky.textextraction.util.Rune;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngineFactory;", "", "<init>", "()V", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;", "textTranslator", "Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine;", "create", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/TextTranslator;)Lcom/samsung/android/app/sdk/deepsky/textextraction/translate/engine/ImageTranslationEngine;", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageTranslationEngineFactory {
    public static final ImageTranslationEngineFactory INSTANCE = new ImageTranslationEngineFactory();

    private ImageTranslationEngineFactory() {
    }

    public final ImageTranslationEngine create(Context context, TextTranslator textTranslator) {
        j.e(context, "context");
        j.e(textTranslator, "textTranslator");
        Rune rune = Rune.INSTANCE;
        if (rune.isSupportVex()) {
            LibLogger.i("ImageTranslationEngineFactory", "create VexImageTranslationEngine");
            return new VexImageTranslationEngine(context, textTranslator);
        } else if (rune.isSupportImageInPainting()) {
            LibLogger.i("ImageTranslationEngineFactory", "create LttV5ImageTranslationEngine");
            return new LttV5ImageTranslationEngine(context, textTranslator);
        } else {
            LibLogger.i("ImageTranslationEngineFactory", "create LttV4ImageTranslationEngine");
            return new LttV4ImageTranslationEngine(context, textTranslator);
        }
    }
}
