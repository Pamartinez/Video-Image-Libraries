package com.samsung.android.sum.core.graph;

import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.factory.MediaFilterCreator;
import com.samsung.android.sum.core.filter.factory.MediaFilterFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class w implements MediaFilterCreator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ MFGraphUnitFactory f4122a;

    public /* synthetic */ w(MFGraphUnitFactory mFGraphUnitFactory) {
        this.f4122a = mFGraphUnitFactory;
    }

    public final MediaFilter newFilter(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        return this.f4122a.parallelizeFilter(mediaFilterFactory, mFDescriptor, mediaFilter);
    }
}
