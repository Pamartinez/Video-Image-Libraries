package com.samsung.android.gallery.support.library.sef;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.function.BiFunction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements BiFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3144a;
    public final /* synthetic */ ArrayList b;

    public /* synthetic */ d(ArrayList arrayList, int i2) {
        this.f3144a = i2;
        this.b = arrayList;
    }

    public final Object apply(Object obj, Object obj2) {
        int i2 = this.f3144a;
        RandomAccessFile randomAccessFile = (RandomAccessFile) obj;
        SefData sefData = (SefData) obj2;
        ArrayList arrayList = this.b;
        switch (i2) {
            case 0:
                return arrayList.add(sefData.name);
            default:
                return arrayList.add(Integer.valueOf(sefData.drdh.dataType));
        }
    }
}
