package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.filter.MediaFilter;
import java.util.List;

@FunctionalInterface
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface BufferComposer {
    MediaBuffer compose(List<MediaBuffer> list, MediaFilter.Option option);
}
