package com.samsung.android.gallery.support.utils;

import android.util.Pair;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class G implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3162a;
    public final /* synthetic */ Serializable b;

    public /* synthetic */ G(Serializable serializable, int i2) {
        this.f3162a = i2;
        this.b = serializable;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f3162a;
        Serializable serializable = this.b;
        switch (i2) {
            case 0:
                PackageMonitorCompat.lambda$toDebugString$3((StringBuilder) serializable, (String) obj, (ConcurrentHashMap) obj2);
                return;
            case 1:
                PackageMonitorCompat.lambda$toDebugString$2((StringBuilder) serializable, (String) obj, obj2);
                return;
            default:
                ObjectDictionary.lambda$trimEmptyTags$0((ArrayList) serializable, (Integer) obj, (Pair) obj2);
                return;
        }
    }
}
