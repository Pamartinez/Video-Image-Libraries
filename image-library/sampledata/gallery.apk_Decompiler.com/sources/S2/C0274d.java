package s2;

import android.graphics.Rect;
import android.view.View;
import androidx.core.oneui.common.internal.log.LogTagHelperKt;
import androidx.core.widget.SeslScrollable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import m2.a;
import q2.r;

/* renamed from: s2.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0274d implements C0275e, a {
    public final RecyclerView d;
    public final ArrayList e = new ArrayList();
    public final C0273c f;

    public C0274d(RecyclerView recyclerView) {
        j.e(recyclerView, "view");
        this.d = recyclerView;
        C0273c cVar = new C0273c(this);
        this.f = cVar;
        LogTagHelperKt.debug(this, "init " + this + ", view=" + recyclerView);
        recyclerView.addOnScrollListener(cVar);
    }

    public final void a() {
        LogTagHelperKt.debug(this, "dispose " + this);
        this.e.clear();
        this.d.removeOnScrollListener(this.f);
    }

    public final boolean b(int i2, int i7, int i8) {
        View view;
        boolean z;
        int i10;
        RecyclerView recyclerView = this.d;
        int childCount = recyclerView.getChildCount();
        if (childCount > 0) {
            if (recyclerView.getHeight() >= i2) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    view = layoutManager.findViewByPosition(childCount - 1);
                } else {
                    view = null;
                }
                if (view != null) {
                    RecyclerView.LayoutManager layoutManager2 = recyclerView.getLayoutManager();
                    if (layoutManager2 != null) {
                        z = layoutManager2.isViewPartiallyVisible(view, true, true);
                    } else {
                        z = false;
                    }
                    Rect seslGetAvailableBounds = recyclerView.seslGetAvailableBounds();
                    if (seslGetAvailableBounds != null) {
                        i10 = seslGetAvailableBounds.bottom;
                    } else {
                        i10 = 0;
                    }
                    if (!C0275e.d(view) ? z && view.getBottom() <= i10 + i8 : z && view.getTop() <= i10 + i8) {
                        return true;
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
        return "FloatingRecyclerviewAdapter";
    }
}
