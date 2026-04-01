package com.samsung.android.app.sdk.deepsky.barcode.action.text;

import X2.r;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.app.sdk.deepsky.barcode.R$string;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.barcode.action.common.ActionUtil;
import com.samsung.android.app.sdk.deepsky.barcode.action.di.Injector;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\f\u001a\u00020\u000b*\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u001a¨\u0006\u001c"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/text/ViewTextAction;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/abstraction/Action;", "Landroid/content/Context;", "context", "LX2/r;", "parsedResult", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "injector", "<init>", "(Landroid/content/Context;LX2/r;Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;)V", "Landroid/content/Intent;", "Lme/x;", "setTarget", "(Landroid/content/Intent;)V", "", "getTitleId", "()I", "", "getActionId", "()Ljava/lang/String;", "getIntent", "()Landroid/content/Intent;", "", "isActionAddible", "()Z", "LX2/r;", "Lcom/samsung/android/app/sdk/deepsky/barcode/action/di/Injector;", "Companion", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ViewTextAction extends Action {
    public static final Companion Companion = new Companion((e) null);
    private final Injector injector;
    private final r parsedResult;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/barcode/action/text/ViewTextAction$Companion;", "", "<init>", "()V", "MEMO_PACKAGE_NAME", "", "MEMO_CLASS_NAME", "SAMSUNG_NOTE_PACKAGE_NAME", "SAMSUNG_NOTE_CLASS_NAME", "deepsky-sdk-barcode-1.0.12_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ViewTextAction(Context context, r rVar, Injector injector2, int i2, e eVar) {
        this(context, rVar, (i2 & 4) != 0 ? new Injector() {
            public Intent getIntent() {
                return new Intent();
            }
        } : injector2);
    }

    private final void setTarget(Intent intent) {
        ActionUtil actionUtil = ActionUtil.INSTANCE;
        if (actionUtil.isPackageInstalled(getAppContext(), "com.samsung.android.app.memo")) {
            intent.setComponent(new ComponentName("com.samsung.android.app.memo", "com.samsung.android.app.memo.Main"));
        } else if (actionUtil.isPackageInstalled(getAppContext(), "com.samsung.android.app.notes")) {
            intent.setType("text/plain");
            intent.setComponent(new ComponentName("com.samsung.android.app.notes", "com.samsung.android.app.notes.composer.ComposerBaseActivity"));
        }
    }

    public String getActionId() {
        return "ViewText";
    }

    public Intent getIntent() {
        Intent intent = this.injector.getIntent();
        intent.setAction("android.intent.action.SEND");
        setTarget(intent);
        intent.putExtra("android.intent.extra.TEXT", this.parsedResult.a());
        intent.addFlags(268435456);
        return intent;
    }

    public int getTitleId() {
        return R$string.barcode_dialog_action_view;
    }

    public final boolean isActionAddible() {
        ActionUtil actionUtil = ActionUtil.INSTANCE;
        if (actionUtil.isPackageInstalled(getAppContext(), "com.samsung.android.app.memo") || actionUtil.isPackageInstalled(getAppContext(), "com.samsung.android.app.notes")) {
            return true;
        }
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewTextAction(Context context, r rVar, Injector injector2) {
        super(context);
        j.e(context, "context");
        j.e(rVar, "parsedResult");
        j.e(injector2, "injector");
        this.parsedResult = rVar;
        this.injector = injector2;
    }
}
