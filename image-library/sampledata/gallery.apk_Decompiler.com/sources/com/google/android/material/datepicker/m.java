package com.google.android.material.datepicker;

import X1.c;
import android.view.View;
import androidx.appcompat.view.menu.MenuItemImpl;
import k2.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ m(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                p pVar = (p) this.e;
                o oVar = pVar.f1458h;
                o oVar2 = o.YEAR;
                if (oVar == oVar2) {
                    pVar.d(o.DAY);
                    return;
                } else if (oVar == o.DAY) {
                    pVar.d(oVar2);
                    return;
                } else {
                    return;
                }
            default:
                MenuItemImpl itemData = ((d) view).getItemData();
                c cVar = (c) this.e;
                if (cVar.f1814F.performItemAction(itemData, cVar.E, 0)) {
                    return;
                }
                if (!cVar.U) {
                    itemData.setChecked(!itemData.isChecked());
                    return;
                } else {
                    itemData.setChecked(true);
                    return;
                }
        }
    }
}
