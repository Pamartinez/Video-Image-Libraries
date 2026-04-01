package com.samsung.android.app.sdk.deepsky.textextraction;

import L1.d;
import android.content.Context;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.ocrwrapper.OcrWrapper;
import com.samsung.android.app.sdk.deepsky.textextraction.util.Rune;
import com.samsung.android.sdk.ocr.OCRType;
import f3.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.f;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u001d\u0010\f\u001a\u0004\u0018\u00010\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtractionProvider;", "", "Landroid/content/Context;", "appContext", "<init>", "(Landroid/content/Context;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtraction;", "getTextExtraction", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtraction;", "textExtractionByLazy$delegate", "Lme/f;", "getTextExtractionByLazy", "textExtractionByLazy", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionProvider {
    public static final Companion Companion = new Companion((e) null);
    /* access modifiers changed from: private */
    public static volatile TextExtractionProvider instance;
    /* access modifiers changed from: private */
    public static Boolean isTextExtractionSupported;
    private final f textExtractionByLazy$delegate;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtractionProvider$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lcom/samsung/android/app/sdk/deepsky/textextraction/TextExtractionProvider;", "isTextExtractionSupported", "", "Ljava/lang/Boolean;", "with", "context", "Landroid/content/Context;", "isOcrSupported", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private final boolean isOcrSupported(Context context) {
            if (!Rune.INSTANCE.isSupportOcr() || !OcrWrapper.isSupported$default(OcrWrapper.INSTANCE, context, (OCRType) null, 2, (Object) null)) {
                return false;
            }
            return true;
        }

        public final boolean isTextExtractionSupported(Context context) {
            boolean z;
            j.e(context, "context");
            Boolean access$isTextExtractionSupported$cp = TextExtractionProvider.isTextExtractionSupported;
            if (access$isTextExtractionSupported$cp != null) {
                z = access$isTextExtractionSupported$cp.booleanValue();
            } else {
                z = isOcrSupported(context);
                TextExtractionProvider.isTextExtractionSupported = Boolean.valueOf(z);
            }
            LibLogger.i("TextExtractionProvider", "isTextExtractionSupported " + z);
            return z;
        }

        public final TextExtractionProvider with(Context context) {
            TextExtractionProvider access$getInstance$cp;
            j.e(context, "context");
            TextExtractionProvider access$getInstance$cp2 = TextExtractionProvider.instance;
            if (access$getInstance$cp2 != null) {
                return access$getInstance$cp2;
            }
            synchronized (this) {
                access$getInstance$cp = TextExtractionProvider.instance;
                if (access$getInstance$cp == null) {
                    Context applicationContext = context.getApplicationContext();
                    j.d(applicationContext, "getApplicationContext(...)");
                    access$getInstance$cp = new TextExtractionProvider(applicationContext, (e) null);
                    TextExtractionProvider.instance = access$getInstance$cp;
                    LibLogger.i("TextExtractionProvider", "TextExtraction version = 8.5.30");
                }
            }
            return access$getInstance$cp;
        }

        private Companion() {
        }
    }

    public /* synthetic */ TextExtractionProvider(Context context, e eVar) {
        this(context);
    }

    private final TextExtraction getTextExtractionByLazy() {
        return (TextExtraction) this.textExtractionByLazy$delegate.getValue();
    }

    public static final boolean isTextExtractionSupported(Context context) {
        return Companion.isTextExtractionSupported(context);
    }

    /* access modifiers changed from: private */
    public static final TextExtractionImpl textExtractionByLazy_delegate$lambda$2(Context context) {
        TextExtractionImpl textExtractionImpl = new TextExtractionImpl(context);
        if (!textExtractionImpl.isSupported(OCRType.OCR_ALL)) {
            textExtractionImpl = null;
        }
        if (textExtractionImpl == null) {
            LibLogger.w("TextExtractionProvider", "TextExtraction is not supported");
        }
        return textExtractionImpl;
    }

    public static final TextExtractionProvider with(Context context) {
        return Companion.with(context);
    }

    public final TextExtraction getTextExtraction() {
        return getTextExtractionByLazy();
    }

    private TextExtractionProvider(Context context) {
        this.textExtractionByLazy$delegate = d.q(new a(context, 6));
    }
}
