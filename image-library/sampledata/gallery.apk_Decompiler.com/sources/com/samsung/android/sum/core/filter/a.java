package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.types.DataType;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4076a;
    public final /* synthetic */ DataType b;

    public /* synthetic */ a(int i2, DataType dataType) {
        this.f4076a = i2;
        this.b = dataType;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4076a;
        DataType dataType = this.b;
        DataType dataType2 = (DataType) obj;
        switch (i2) {
            case 0:
                return ContentFilter.lambda$evaluateDataType$1(dataType, dataType2);
            default:
                return ContentFilter.lambda$evaluateDataType$2(dataType, dataType2);
        }
    }
}
