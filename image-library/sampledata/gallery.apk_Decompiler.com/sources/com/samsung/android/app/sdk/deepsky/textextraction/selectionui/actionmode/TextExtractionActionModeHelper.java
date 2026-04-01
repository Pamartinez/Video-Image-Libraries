package com.samsung.android.app.sdk.deepsky.textextraction.selectionui.actionmode;

import Ad.f;
import android.content.Context;
import android.graphics.Rect;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewParent;
import android.view.textclassifier.TextClassification;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import com.samsung.android.app.sdk.deepsky.textextraction.textclassification.TextClassificationHelper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import me.x;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\r\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0004\b\r\u0010\u000eJK\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u001a¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010 R\u0018\u0010!\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006'"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextExtractionActionModeHelper;", "", "Landroid/content/Context;", "context", "Landroid/view/View;", "teView", "Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;", "textClassificationHelper", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/ActionModeListener;", "listener", "<init>", "(Landroid/content/Context;Landroid/view/View;Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/ActionModeListener;)V", "Landroid/view/ActionMode;", "startActionModeInternal", "()Landroid/view/ActionMode;", "", "selectedText", "selectedTextForTextClassification", "leftText", "rightText", "", "Landroid/graphics/Rect;", "visibleWordsRect", "", "isVertical", "isAllTextSelected", "Lme/x;", "startActionMode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZZ)V", "stopActionMode", "()V", "Landroid/view/View;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/textclassification/TextClassificationHelper;", "actionMode", "Landroid/view/ActionMode;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextActionModeCallback;", "textActionModeCallback", "Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextActionModeCallback;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextExtractionActionModeHelper {
    public static final Companion Companion = new Companion((e) null);
    private ActionMode actionMode;
    private final View teView;
    private final TextActionModeCallback textActionModeCallback;
    private final TextClassificationHelper textClassificationHelper;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/selectionui/actionmode/TextExtractionActionModeHelper$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public TextExtractionActionModeHelper(Context context, View view, TextClassificationHelper textClassificationHelper2, ActionModeListener actionModeListener) {
        j.e(context, "context");
        j.e(view, "teView");
        j.e(textClassificationHelper2, "textClassificationHelper");
        j.e(actionModeListener, "listener");
        this.teView = view;
        this.textClassificationHelper = textClassificationHelper2;
        this.textActionModeCallback = new TextActionModeCallback(context, view, actionModeListener);
    }

    /* access modifiers changed from: private */
    public static final x startActionMode$lambda$0(TextExtractionActionModeHelper textExtractionActionModeHelper, TextClassification textClassification) {
        textExtractionActionModeHelper.textActionModeCallback.setTextClassification(textClassification);
        textExtractionActionModeHelper.actionMode = textExtractionActionModeHelper.startActionModeInternal();
        return x.f4917a;
    }

    private final ActionMode startActionModeInternal() {
        ViewParent parent = this.teView.getParent();
        if (parent == null || !this.teView.isAttachedToWindow()) {
            return null;
        }
        try {
            return parent.startActionModeForChild(this.teView, this.textActionModeCallback, 99);
        } catch (AbstractMethodError unused) {
            return parent.startActionModeForChild(this.teView, this.textActionModeCallback);
        } catch (Exception unused2) {
            LibLogger.e("TextExtractionActionModeHelper", "startActionMode exception");
            return null;
        }
    }

    public final void startActionMode(String str, String str2, String str3, String str4, List<Rect> list, boolean z, boolean z3) {
        j.e(str, "selectedText");
        j.e(str2, "selectedTextForTextClassification");
        j.e(str3, "leftText");
        j.e(str4, "rightText");
        j.e(list, "visibleWordsRect");
        this.textActionModeCallback.setSelectedTextInformation(str, list, z, z3);
        TextClassificationHelper.classifyTextAsync$default(this.textClassificationHelper, str2, str3, str4, false, new f(18, (Object) this), 8, (Object) null);
    }

    public final void stopActionMode() {
        this.textClassificationHelper.cancelAsyncTask();
        ActionMode actionMode2 = this.actionMode;
        if (actionMode2 != null) {
            actionMode2.hide(-1);
        }
        ActionMode actionMode3 = this.actionMode;
        if (actionMode3 != null) {
            actionMode3.finish();
        }
        this.actionMode = null;
    }
}
