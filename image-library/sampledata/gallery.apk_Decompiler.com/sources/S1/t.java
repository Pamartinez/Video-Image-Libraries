package S1;

import android.view.View;
import java.util.Stack;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends Stack {
    public final /* bridge */ boolean contains(Object obj) {
        boolean z;
        if (obj == null) {
            z = true;
        } else {
            z = obj instanceof View;
        }
        if (!z) {
            return false;
        }
        return super.contains((View) obj);
    }

    public final /* bridge */ int indexOf(Object obj) {
        boolean z;
        if (obj == null) {
            z = true;
        } else {
            z = obj instanceof View;
        }
        if (!z) {
            return -1;
        }
        return super.indexOf((View) obj);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        boolean z;
        if (obj == null) {
            z = true;
        } else {
            z = obj instanceof View;
        }
        if (!z) {
            return -1;
        }
        return super.lastIndexOf((View) obj);
    }

    /* renamed from: p */
    public final View push(View view) {
        j.e(view, "item");
        if (!isEmpty()) {
            Object peek = peek();
            j.b(peek);
            ((View) peek).setVisibility(4);
        }
        Object push = super.push(view);
        j.d(push, "super.push(item)");
        return (View) push;
    }

    public final Object pop() {
        View view;
        synchronized (this) {
            try {
                view = (View) super.pop();
                if (!isEmpty()) {
                    ((View) peek()).setVisibility(0);
                }
                j.d(view, "result");
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return view;
    }

    public final /* bridge */ boolean remove(Object obj) {
        boolean z;
        if (obj == null) {
            z = true;
        } else {
            z = obj instanceof View;
        }
        if (!z) {
            return false;
        }
        return super.remove((View) obj);
    }
}
