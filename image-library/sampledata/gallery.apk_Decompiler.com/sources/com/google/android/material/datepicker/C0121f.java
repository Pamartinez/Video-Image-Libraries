package com.google.android.material.datepicker;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.Calendar;

/* renamed from: com.google.android.material.datepicker.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0121f implements View.OnClickListener {
    public final /* synthetic */ y d;
    public final /* synthetic */ p e;

    public C0121f(p pVar, y yVar) {
        this.e = pVar;
        this.d = yVar;
    }

    public final void onClick(View view) {
        p pVar = this.e;
        int findLastVisibleItemPosition = ((LinearLayoutManager) pVar.k.getLayoutManager()).findLastVisibleItemPosition() - 1;
        if (findLastVisibleItemPosition >= 0) {
            Calendar a7 = D.a(this.d.d.d.d);
            a7.add(2, findLastVisibleItemPosition);
            pVar.c(new u(a7));
        }
    }
}
