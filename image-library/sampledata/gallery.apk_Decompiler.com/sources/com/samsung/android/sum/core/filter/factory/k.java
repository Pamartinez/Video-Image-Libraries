package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.StapleDescriptor;
import com.samsung.android.sum.core.filter.AsyncFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class k implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4088a;
    public final /* synthetic */ MediaFilterFactory b;

    public /* synthetic */ k(MediaFilterFactory mediaFilterFactory, int i2) {
        this.f4088a = i2;
        this.b = mediaFilterFactory;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4088a;
        MediaFilterFactory mediaFilterFactory = this.b;
        switch (i2) {
            case 0:
                return mediaFilterFactory.newFilter((MFDescriptor) obj);
            case 1:
                return mediaFilterFactory.newFilter(AsyncFilter.class, ((MediaFilter) obj).getDescriptor(), (MediaFilter) obj);
            default:
                return mediaFilterFactory.newFilter(MFDescriptor.newBuilder().setFilterType(AsyncFilter.class).build(StapleDescriptor.class), (MediaFilter) obj);
        }
    }
}
