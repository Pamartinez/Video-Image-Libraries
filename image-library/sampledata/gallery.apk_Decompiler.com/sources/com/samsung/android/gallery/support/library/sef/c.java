package com.samsung.android.gallery.support.library.sef;

import java.util.ArrayList;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3143a;
    public final /* synthetic */ ArrayList b;

    public /* synthetic */ c(ArrayList arrayList, int i2) {
        this.f3143a = i2;
        this.b = arrayList;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f3143a;
        String str = (String) obj;
        SefData sefData = (SefData) obj2;
        ArrayList arrayList = this.b;
        switch (i2) {
            case 0:
                arrayList.add(sefData.pack());
                return;
            default:
                arrayList.add(sefData.pack());
                return;
        }
    }
}
