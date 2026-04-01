package s2;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import androidx.core.widget.NestedScrollView;
import androidx.core.widget.SeslScrollable;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import m2.a;
import q2.r;

/* renamed from: s2.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0272b implements C0275e, a {
    public final NestedScrollView d;
    public final ArrayList e = new ArrayList();
    public final C0271a f;

    public C0272b(NestedScrollView nestedScrollView) {
        j.e(nestedScrollView, "view");
        this.d = nestedScrollView;
        C0271a aVar = new C0271a(this);
        this.f = aVar;
        LogTagHelperKt.debug(this, "init " + this + ", view=" + nestedScrollView);
        nestedScrollView.addOnScrollChangeListener(aVar);
    }

    public final void a() {
        LogTagHelperKt.debug(this, "dispose " + this);
        this.e.clear();
        this.d.removeOnScrollChangeListener(this.f);
    }

    public final boolean b(int i2, int i7, int i8) {
        int i10;
        NestedScrollView nestedScrollView = this.d;
        if (nestedScrollView.getChildCount() != 0) {
            View childAt = nestedScrollView.getChildAt(0);
            if (childAt instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) childAt;
                if (viewGroup.getChildCount() >= 2) {
                    int childCount = viewGroup.getChildCount() - 1;
                    View view = null;
                    View view2 = null;
                    while (true) {
                        if (-1 >= childCount) {
                            childCount = 0;
                            break;
                        }
                        view2 = viewGroup.getChildAt(childCount);
                        if (view2 != null && view2.getVisibility() == 0) {
                            break;
                        }
                        childCount--;
                    }
                    if (view2 != null) {
                        if (C0275e.d(view2)) {
                            for (int i11 = childCount - 1; -1 < i11; i11--) {
                                view = viewGroup.getChildAt(i11);
                                if (view != null && view.getVisibility() == 0) {
                                    break;
                                }
                            }
                            view2 = view;
                        }
                        if (view2 != null) {
                            if (view2.getBottom() <= viewGroup.getTop() + viewGroup.getHeight()) {
                                Rect seslGetAvailableBounds = nestedScrollView.seslGetAvailableBounds();
                                if (seslGetAvailableBounds != null) {
                                    i10 = seslGetAvailableBounds.bottom;
                                } else {
                                    i10 = 0;
                                }
                                if (viewGroup.getTop() + view2.getBottom() <= i10 + i8) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final void e(r rVar) {
        j.e(rVar, "listener");
        this.e.add(rVar);
    }

    public final void f(r rVar) {
        j.e(rVar, "listener");
        this.e.remove(rVar);
    }

    public final SeslScrollable g() {
        return this.d;
    }

    public final String getLogTag() {
        return "FloatingNestedScrollViewAdapter";
    }
}
