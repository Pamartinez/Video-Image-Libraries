package com.google.android.material.datepicker;

import android.graphics.Canvas;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ p f1457a;

    public k(p pVar) {
        this.f1457a = pVar;
        D.c((Calendar) null);
        D.c((Calendar) null);
    }

    public final void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if ((recyclerView.getAdapter() instanceof F) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
            F f = (F) recyclerView.getAdapter();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            this.f1457a.getClass();
            throw null;
        }
    }
}
