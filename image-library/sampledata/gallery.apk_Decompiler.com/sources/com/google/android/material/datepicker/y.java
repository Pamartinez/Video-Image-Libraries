package com.google.android.material.datepicker;

import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.sec.android.gallery3d.R;
import java.util.Calendar;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class y extends RecyclerView.Adapter {
    public final C0117b d;
    public final j e;
    public final int f;

    public y(ContextThemeWrapper contextThemeWrapper, C0117b bVar, j jVar) {
        int i2;
        u uVar = bVar.d;
        u uVar2 = bVar.e;
        u uVar3 = bVar.g;
        if (uVar.d.compareTo(uVar3.d) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        } else if (uVar3.d.compareTo(uVar2.d) <= 0) {
            int dimensionPixelSize = contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) * v.g;
            if (s.e(contextThemeWrapper, 16843277)) {
                i2 = contextThemeWrapper.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
            } else {
                i2 = 0;
            }
            this.f = dimensionPixelSize + i2;
            this.d = bVar;
            this.e = jVar;
            setHasStableIds(true);
        } else {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
    }

    public final int getItemCount() {
        return this.d.f1453j;
    }

    public final long getItemId(int i2) {
        Calendar a7 = D.a(this.d.d.d);
        a7.add(2, i2);
        a7.set(5, 1);
        Calendar a10 = D.a(a7);
        a10.get(2);
        a10.get(1);
        a10.getMaximum(7);
        a10.getActualMaximum(5);
        a10.getTimeInMillis();
        return a10.getTimeInMillis();
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        x xVar = (x) viewHolder;
        C0117b bVar = this.d;
        Calendar a7 = D.a(bVar.d.d);
        a7.add(2, i2);
        u uVar = new u(a7);
        xVar.d.setText(uVar.e());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) xVar.e.findViewById(R.id.month_grid);
        if (materialCalendarGridView.a() == null || !uVar.equals(materialCalendarGridView.a().d)) {
            new v(uVar, bVar);
            throw null;
        }
        materialCalendarGridView.invalidate();
        materialCalendarGridView.a().getClass();
        throw null;
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        LinearLayout linearLayout = (LinearLayout) C0086a.d(viewGroup, R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!s.e(viewGroup.getContext(), 16843277)) {
            return new x(false, linearLayout);
        }
        linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.f));
        return new x(true, linearLayout);
    }
}
