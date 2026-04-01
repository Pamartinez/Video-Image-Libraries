package c;

import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.view.KeyEventDispatcher;

/* renamed from: c.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0085a implements KeyEventDispatcher.Component {
    public final /* synthetic */ AppCompatDialog d;

    public /* synthetic */ C0085a(AppCompatDialog appCompatDialog) {
        this.d = appCompatDialog;
    }

    public final boolean superDispatchKeyEvent(KeyEvent keyEvent) {
        return this.d.superDispatchKeyEvent(keyEvent);
    }
}
