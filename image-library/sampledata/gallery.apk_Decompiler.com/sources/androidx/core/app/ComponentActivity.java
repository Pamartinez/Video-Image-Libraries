package androidx.core.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u00020\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0014¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\fH\u0015¢\u0006\u0004\b\u0012\u0010\u0010J\u0017\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0017¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0017\u0010\u0016J\u0017\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0018\u0010\u0016J\u001f\u0010\u0019\u001a\u00020\t2\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0004¢\u0006\u0004\b\u0019\u0010\u000bR.\u0010\u001d\u001a\u0016\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u001c0\u001b\u0012\u0004\u0012\u00020\u001c0\u001a8\u0002X\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u0012\u0004\b\u001f\u0010\u0005R\u001a\u0010!\u001a\u00020 8\u0002X\u0004¢\u0006\f\n\u0004\b!\u0010\"\u0012\u0004\b#\u0010\u0005¨\u0006$"}, d2 = {"Landroidx/core/app/ComponentActivity;", "Landroid/app/Activity;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/core/view/KeyEventDispatcher$Component;", "<init>", "()V", "", "", "args", "", "shouldSkipDump", "([Ljava/lang/String;)Z", "Landroid/os/Bundle;", "savedInstanceState", "Lme/x;", "onCreate", "(Landroid/os/Bundle;)V", "outState", "onSaveInstanceState", "Landroid/view/KeyEvent;", "event", "superDispatchKeyEvent", "(Landroid/view/KeyEvent;)Z", "dispatchKeyShortcutEvent", "dispatchKeyEvent", "shouldDumpInternalState", "Landroidx/collection/SimpleArrayMap;", "Ljava/lang/Class;", "", "extraDataMap", "Landroidx/collection/SimpleArrayMap;", "getExtraDataMap$annotations", "Landroidx/lifecycle/LifecycleRegistry;", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycleRegistry$annotations", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ComponentActivity extends Activity implements LifecycleOwner, KeyEventDispatcher.Component {
    private final SimpleArrayMap<Class<Object>, Object> extraDataMap = new SimpleArrayMap<>(0, 1, (e) null);
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r3.equals("--list-dumpables") == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r3.equals("--dump-dumpable") == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        if (android.os.Build.VERSION.SDK_INT < 33) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003f, code lost:
        return false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0050 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean shouldSkipDump(java.lang.String[] r3) {
        /*
            r2 = this;
            r2 = 0
            if (r3 == 0) goto L_0x0050
            int r0 = r3.length
            if (r0 != 0) goto L_0x0007
            goto L_0x0050
        L_0x0007:
            r3 = r3[r2]
            int r0 = r3.hashCode()
            r1 = 1
            switch(r0) {
                case -645125871: goto L_0x0040;
                case 100470631: goto L_0x002f;
                case 472614934: goto L_0x0026;
                case 1159329357: goto L_0x001c;
                case 1455016274: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0050
        L_0x0012:
            java.lang.String r0 = "--autofill"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x001b
            goto L_0x0050
        L_0x001b:
            return r1
        L_0x001c:
            java.lang.String r0 = "--contentcapture"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0025
            goto L_0x0050
        L_0x0025:
            return r1
        L_0x0026:
            java.lang.String r0 = "--list-dumpables"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0038
            goto L_0x0050
        L_0x002f:
            java.lang.String r0 = "--dump-dumpable"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0038
            goto L_0x0050
        L_0x0038:
            int r3 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r3 < r0) goto L_0x003f
            return r1
        L_0x003f:
            return r2
        L_0x0040:
            java.lang.String r0 = "--translation"
            boolean r3 = r3.equals(r0)
            if (r3 != 0) goto L_0x0049
            goto L_0x0050
        L_0x0049:
            int r3 = android.os.Build.VERSION.SDK_INT
            r0 = 31
            if (r3 < r0) goto L_0x0050
            return r1
        L_0x0050:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.ComponentActivity.shouldSkipDump(java.lang.String[]):boolean");
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        j.e(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        j.d(decorView, "window.decorView");
        if (KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return true;
        }
        return KeyEventDispatcher.dispatchKeyEvent(this, decorView, this, keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        j.e(keyEvent, "event");
        View decorView = getWindow().getDecorView();
        j.d(decorView, "window.decorView");
        if (KeyEventDispatcher.dispatchBeforeHierarchy(decorView, keyEvent)) {
            return true;
        }
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReportFragment.Companion.injectIfNeededIn(this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        j.e(bundle, "outState");
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }

    public final boolean shouldDumpInternalState(String[] strArr) {
        return !shouldSkipDump(strArr);
    }

    public boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        j.e(keyEvent, "event");
        return super.dispatchKeyEvent(keyEvent);
    }
}
