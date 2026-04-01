package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.factory.MediaFilterFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements MediaFilterCreator {
    public final MediaFilter newFilter(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        return MediaFilterFactory.Builder.lambda$addDefaultCreators$0(mediaFilterFactory, mFDescriptor, mediaFilter);
    }
}
