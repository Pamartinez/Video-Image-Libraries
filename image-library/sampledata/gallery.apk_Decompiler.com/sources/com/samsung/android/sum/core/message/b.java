package com.samsung.android.sum.core.message;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.List;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4124a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b(int i2, Object obj) {
        this.f4124a = i2;
        this.b = obj;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f4124a;
        Object obj3 = this.b;
        switch (i2) {
            case 0:
                ((Message) obj3).lambda$put$6((String) obj, obj2);
                return;
            case 1:
                ((Bundle) obj3).putParcelable((String) obj, (Parcelable) obj2);
                return;
            case 2:
                ((List) obj2).remove((MessageSubscriber) obj3);
                return;
            default:
                ((List) obj2).remove((MessageConsumer) obj3);
                return;
        }
    }
}
