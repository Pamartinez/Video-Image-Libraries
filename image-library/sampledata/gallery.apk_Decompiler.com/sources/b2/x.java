package B2;

import Ab.a;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends q {
    public final int e = R.drawable.design_password_eye;
    public EditText f;
    public final a g = new a(3, this);

    public x(p pVar, int i2) {
        super(pVar);
        if (i2 != 0) {
            this.e = i2;
        }
    }

    public final void b() {
        p();
    }

    public final int c() {
        return R.string.password_toggle_content_description;
    }

    public final int d() {
        return this.e;
    }

    public final View.OnClickListener f() {
        return this.g;
    }

    public final boolean j() {
        return true;
    }

    public final boolean k() {
        boolean z;
        EditText editText = this.f;
        if (editText == null || !(editText.getTransformationMethod() instanceof PasswordTransformationMethod)) {
            z = false;
        } else {
            z = true;
        }
        return !z;
    }

    public final void l(EditText editText) {
        this.f = editText;
        p();
    }

    public final void q() {
        EditText editText = this.f;
        if (editText == null) {
            return;
        }
        if (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224) {
            this.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public final void r() {
        EditText editText = this.f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
