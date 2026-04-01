package B2;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class q {

    /* renamed from: a  reason: collision with root package name */
    public final TextInputLayout f62a;
    public final p b;

    /* renamed from: c  reason: collision with root package name */
    public final Context f63c;
    public final CheckableImageButton d;

    public q(p pVar) {
        this.f62a = pVar.d;
        this.b = pVar;
        this.f63c = pVar.getContext();
        this.d = pVar.f58j;
    }

    public int c() {
        return 0;
    }

    public int d() {
        return 0;
    }

    public View.OnFocusChangeListener e() {
        return null;
    }

    public View.OnClickListener f() {
        return null;
    }

    public View.OnFocusChangeListener g() {
        return null;
    }

    public AccessibilityManagerCompat.TouchExplorationStateChangeListener h() {
        return null;
    }

    public boolean i(int i2) {
        return true;
    }

    public boolean j() {
        return this instanceof l;
    }

    public boolean k() {
        return false;
    }

    public final void p() {
        this.b.f(false);
    }

    public void a() {
    }

    public void b() {
    }

    public void q() {
    }

    public void r() {
    }

    public void l(EditText editText) {
    }

    public void m(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public void n(AccessibilityEvent accessibilityEvent) {
    }

    public void o(boolean z) {
    }
}
