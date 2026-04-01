package com.samsung.android.sum.core.filter;

import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaFilterGroup extends MediaFilter {
    MediaFilterGroup addFilter(List<MediaFilter> list);

    MediaFilterGroup addFilter(MediaFilter... mediaFilterArr) {
        addFilter((List<MediaFilter>) Arrays.asList(mediaFilterArr));
        return this;
    }

    boolean removeFilter(MediaFilter... mediaFilterArr);

    boolean replaceFilter(MediaFilter mediaFilter, MediaFilter mediaFilter2);
}
