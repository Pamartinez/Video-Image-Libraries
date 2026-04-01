package com.google.android.material.datepicker;

import android.view.View;
import android.widget.AdapterView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w implements AdapterView.OnItemClickListener {
    public final /* synthetic */ MaterialCalendarGridView d;
    public final /* synthetic */ y e;

    public w(y yVar, MaterialCalendarGridView materialCalendarGridView) {
        this.e = yVar;
        this.d = materialCalendarGridView;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i2, long j2) {
        MaterialCalendarGridView materialCalendarGridView = this.d;
        v a7 = materialCalendarGridView.a();
        if (i2 >= a7.a() && i2 <= a7.c()) {
            if (materialCalendarGridView.a().getItem(i2).longValue() >= this.e.e.f1456a.f.f.d) {
                throw null;
            }
        }
    }
}
