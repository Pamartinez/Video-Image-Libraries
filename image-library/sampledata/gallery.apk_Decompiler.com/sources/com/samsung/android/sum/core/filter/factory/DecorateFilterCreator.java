package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.descriptor.DecorateDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DecorateFilterCreator implements MediaFilterCreator {
    private static final String TAG = Def.tagOf((Class<?>) DecorateFilterCreator.class);

    public MediaFilter newFilter(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        String str = TAG;
        SLog.d(str, "newFilter: " + mFDescriptor + ", successor");
        return mediaFilterFactory.newFilter(mFDescriptor.getFilterTypeFromId(), mFDescriptor, mediaFilterFactory.newFilter(((DecorateDescriptor) mFDescriptor).getSuccessor(), mediaFilter));
    }
}
