package X1;

import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.SeslTouchTargetDelegate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements Runnable {
    public final /* synthetic */ ViewGroup d;

    public d(ViewGroup viewGroup) {
        this.d = viewGroup;
    }

    public final void run() {
        View view;
        int i2;
        int i7;
        ViewGroup viewGroup = this.d;
        SeslTouchTargetDelegate seslTouchTargetDelegate = new SeslTouchTargetDelegate(viewGroup);
        int childCount = viewGroup.getChildCount();
        boolean z = false;
        int i8 = 0;
        while (true) {
            if (i8 >= childCount) {
                view = null;
                break;
            }
            view = viewGroup.getChildAt(i8);
            if (view instanceof c) {
                break;
            }
            i8++;
        }
        if (view != null && view.getVisibility() == 0) {
            ViewGroup viewGroup2 = (ViewGroup) view;
            int childCount2 = viewGroup2.getChildCount();
            boolean z3 = false;
            for (int i10 = 0; i10 < childCount2; i10++) {
                View childAt = viewGroup2.getChildAt(i10);
                if (childAt.getVisibility() == 0) {
                    int measuredHeight = childAt.getMeasuredHeight() / 2;
                    if (i10 == 0) {
                        i2 = measuredHeight;
                    } else {
                        i2 = 0;
                    }
                    if (i10 == childCount2 - 1) {
                        i7 = measuredHeight;
                    } else {
                        i7 = 0;
                    }
                    seslTouchTargetDelegate.addTouchDelegate(childAt, SeslTouchTargetDelegate.ExtraInsets.of(i2, measuredHeight, i7, measuredHeight));
                    z3 = true;
                }
            }
            z = z3;
        }
        if (z) {
            viewGroup.setTouchDelegate(seslTouchTargetDelegate);
        }
    }
}
