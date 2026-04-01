package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.factory.MediaFilterCreatorChain;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements MediaFilterCreatorChain.DescriptorFinder {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MediaFilterCreator f4084a;

    public /* synthetic */ f(MediaFilterCreator mediaFilterCreator) {
        this.f4084a = mediaFilterCreator;
    }

    public final MFDescriptor find(MFDescriptor mFDescriptor) {
        return MediaFilterCreatorChain.lambda$prepare$2(this.f4084a, mFDescriptor);
    }
}
