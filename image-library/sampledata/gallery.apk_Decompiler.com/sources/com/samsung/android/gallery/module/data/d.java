package com.samsung.android.gallery.module.data;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ MdeData e;

    public /* synthetic */ d(MdeData mdeData, int i2) {
        this.d = i2;
        this.e = mdeData;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        MdeData mdeData = this.e;
        switch (i2) {
            case 0:
                mdeData.lambda$parse$5((String) obj);
                return;
            case 1:
                mdeData.lambda$parse$6((String) obj);
                return;
            case 2:
                mdeData.lambda$parseSpace$11((String) obj);
                return;
            case 3:
                mdeData.lambda$parse$7((String) obj);
                return;
            case 4:
                mdeData.lambda$parse$8((String) obj);
                return;
            case 5:
                mdeData.lambda$parse$9((String) obj);
                return;
            case 6:
                mdeData.lambda$parse$2((Integer) obj);
                return;
            case 7:
                mdeData.lambda$parse$3((Integer) obj);
                return;
            default:
                mdeData.lambda$parse$4((Boolean) obj);
                return;
        }
    }
}
