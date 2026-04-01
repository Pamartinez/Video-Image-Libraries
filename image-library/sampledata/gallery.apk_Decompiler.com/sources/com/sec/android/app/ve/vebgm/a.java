package com.sec.android.app.ve.vebgm;

import android.os.Parcel;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements IntConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Parcel f4224a;
    public final /* synthetic */ Map b;

    public /* synthetic */ a(Parcel parcel, HashMap hashMap) {
        this.f4224a = parcel;
        this.b = hashMap;
    }

    public final void accept(int i2) {
        this.b.put(this.f4224a.readString(), this.f4224a.readString());
    }
}
