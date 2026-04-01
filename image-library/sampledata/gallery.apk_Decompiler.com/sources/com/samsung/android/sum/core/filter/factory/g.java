package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaFilterCreatorChain f4085a;
    public final /* synthetic */ MediaFilterFactory b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ MediaFilter f4086c;

    public /* synthetic */ g(MediaFilterCreatorChain mediaFilterCreatorChain, MediaFilterFactory mediaFilterFactory, MediaFilter mediaFilter) {
        this.f4085a = mediaFilterCreatorChain;
        this.b = mediaFilterFactory;
        this.f4086c = mediaFilter;
    }

    public final Object apply(Object obj) {
        return this.f4085a.lambda$newFilter$7(this.b, this.f4086c, (MFDescriptor) obj);
    }
}
