package com.google.android.material.datepicker;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends B {
    public final /* synthetic */ int d;
    public final /* synthetic */ p e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public i(p pVar, Context context, int i2, int i7) {
        super(context, i2, false);
        this.e = pVar;
        this.d = i7;
    }

    public final void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
        int i2 = this.d;
        p pVar = this.e;
        if (i2 == 0) {
            iArr[0] = pVar.k.getWidth();
            iArr[1] = pVar.k.getWidth();
            return;
        }
        iArr[0] = pVar.k.getHeight();
        iArr[1] = pVar.k.getHeight();
    }
}
