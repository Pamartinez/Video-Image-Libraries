package com.samsung.android.app.sdk.deepsky.textextraction.textclassification;

import Ae.b;
import Vf.C0867e0;
import Vf.D;
import android.content.Context;
import android.os.Bundle;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.util.SingleThreadCoroutineSwitcher;
import dd.h;
import i.C0212a;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import qe.C1233i;
import v3.a;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\f\u0010\rJE\u0010\u0015\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000b2\u0014\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u00130\u0011H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u0017\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000bH\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ5\u0010\u001c\u001a\u0004\u0018\u00010\u00122\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u001c\u0010\u0018JI\u0010\u001d\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\u0014\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0004\b\u001d\u0010\u0016J\r\u0010\u001e\u001a\u00020\u0013¢\u0006\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010 \u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0018\u0010&\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;", "", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/view/textclassifier/TextClassifier;", "initSession", "()Landroid/view/textclassifier/TextClassifier;", "", "targetText", "", "isTextClassificationAvailable", "(Ljava/lang/String;)Z", "leftText", "rightText", "isDuplicatedEntityEnabled", "Lkotlin/Function1;", "Landroid/view/textclassifier/TextClassification;", "Lme/x;", "onClassificationComplete", "classifyTextWithTimeout", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLAe/b;)V", "classifyTextInternal", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Landroid/view/textclassifier/TextClassification;", "Landroid/os/Bundle;", "makeTextClassificationExtraBundle", "(Z)Landroid/os/Bundle;", "classifyText", "classifyTextAsync", "cancelAsyncTask", "()V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "textClassifier", "Landroid/view/textclassifier/TextClassifier;", "LVf/e0;", "classifyJob", "LVf/e0;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextClassificationHelper {
    public static final Companion Companion = new Companion((e) null);
    private C0867e0 classifyJob;
    private final Context context;
    private final TextClassifier textClassifier = initSession();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper$Companion;", "", "<init>", "()V", "TAG", "", "CLASSIFICATION_TIMEOUT", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextClassificationHelper(Context context2) {
        j.e(context2, "context");
        this.context = context2;
    }

    public static /* synthetic */ TextClassification classifyText$default(TextClassificationHelper textClassificationHelper, String str, String str2, String str3, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = "";
        }
        if ((i2 & 4) != 0) {
            str3 = "";
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        return textClassificationHelper.classifyText(str, str2, str3, z);
    }

    public static /* synthetic */ void classifyTextAsync$default(TextClassificationHelper textClassificationHelper, String str, String str2, String str3, boolean z, b bVar, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str2 = "";
        }
        if ((i2 & 4) != 0) {
            str3 = "";
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        textClassificationHelper.classifyTextAsync(str, str2, str3, z, bVar);
    }

    /* access modifiers changed from: private */
    public final TextClassification classifyTextInternal(String str, String str2, String str3, boolean z) {
        TextClassification.Request build = new TextClassification.Request.Builder(C0212a.B(str2, str, str3), str2.length(), str.length() + str2.length()).setExtras(makeTextClassificationExtraBundle(z)).build();
        j.d(build, "build(...)");
        TextClassification classifyText = this.textClassifier.classifyText(build);
        j.d(classifyText, "classifyText(...)");
        return classifyText;
    }

    private final void classifyTextWithTimeout(String str, String str2, String str3, boolean z, b bVar) {
        this.classifyJob = SingleThreadCoroutineSwitcher.INSTANCE.newChain().onBackground(new a(this, str, str2, str3, z)).start(new h(3, bVar), new com.samsung.android.vexfwk.sdk.docscan.b(26));
    }

    /* access modifiers changed from: private */
    public static final TextClassification classifyTextWithTimeout$lambda$0(TextClassificationHelper textClassificationHelper, String str, String str2, String str3, boolean z, x xVar) {
        j.e(xVar, "it");
        return (TextClassification) D.r(C1233i.d, new TextClassificationHelper$classifyTextWithTimeout$1$1(textClassificationHelper, str, str2, str3, z, (C1227c) null));
    }

    /* access modifiers changed from: private */
    public static final x classifyTextWithTimeout$lambda$1(b bVar, TextClassification textClassification) {
        bVar.invoke(textClassification);
        return x.f4917a;
    }

    /* access modifiers changed from: private */
    public static final x classifyTextWithTimeout$lambda$2(Throwable th) {
        j.e(th, "it");
        LibLogger.e("TextClassificationHelper", "text classification failed", th);
        return x.f4917a;
    }

    private final TextClassifier initSession() {
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.context.getSystemService(TextClassificationManager.class);
        if (textClassificationManager != null) {
            TextClassifier createTextClassificationSession = textClassificationManager.createTextClassificationSession(new TextClassificationContext.Builder(this.context.getPackageName(), "textview").build());
            j.b(createTextClassificationSession);
            return createTextClassificationSession;
        }
        LibLogger.w("TextClassificationHelper", "getTextClassificationSession - NO_OP");
        TextClassifier textClassifier2 = TextClassifier.NO_OP;
        j.b(textClassifier2);
        return textClassifier2;
    }

    private final boolean isTextClassificationAvailable(String str) {
        if (j.a(this.textClassifier, TextClassifier.NO_OP)) {
            LibLogger.e("TextClassificationHelper", "TextClassifier is NO_OP");
            return false;
        } else if (str.length() != 0) {
            return true;
        } else {
            LibLogger.d("TextClassificationHelper", "target text is empty");
            return false;
        }
    }

    private final Bundle makeTextClassificationExtraBundle(boolean z) {
        Bundle bundle = new Bundle();
        if (z) {
            bundle.putBoolean("is-duplicated-entity-enabled", true);
        }
        bundle.putBoolean("is-locale-specified-enabled", true);
        bundle.putBoolean("is-across-multiple-lines-entity-enabled", true);
        bundle.putString("text_source_type_id", "image");
        return bundle;
    }

    public final void cancelAsyncTask() {
        C0867e0 e0Var = this.classifyJob;
        if (e0Var != null) {
            if (!e0Var.isActive()) {
                e0Var = null;
            }
            if (e0Var != null) {
                e0Var.a((CancellationException) null);
            }
        }
        this.classifyJob = null;
    }

    public final TextClassification classifyText(String str, String str2, String str3, boolean z) {
        j.e(str, "targetText");
        j.e(str2, "leftText");
        j.e(str3, "rightText");
        if (isTextClassificationAvailable(str)) {
            return classifyTextInternal(str, str2, str3, z);
        }
        return null;
    }

    public final void classifyTextAsync(String str, String str2, String str3, boolean z, b bVar) {
        j.e(str, "targetText");
        j.e(str2, "leftText");
        j.e(str3, "rightText");
        j.e(bVar, "onClassificationComplete");
        if (isTextClassificationAvailable(str)) {
            cancelAsyncTask();
            classifyTextWithTimeout(str, str2, str3, z, bVar);
        }
    }
}
