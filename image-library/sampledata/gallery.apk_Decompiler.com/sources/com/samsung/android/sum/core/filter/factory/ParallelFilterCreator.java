package com.samsung.android.sum.core.filter.factory;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.ParallelDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.MediaFilterGroupBase;
import com.samsung.android.sum.core.filter.collection.ParallelDNCFilter;
import com.samsung.android.sum.core.filter.collection.ParallelSharedFilter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParallelFilterCreator implements MediaFilterCreator {

    /* renamed from: com.samsung.android.sum.core.filter.factory.ParallelFilterCreator$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sum$core$filter$collection$ParallelFilter$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.sum.core.filter.collection.ParallelFilter$Type[] r0 = com.samsung.android.sum.core.filter.collection.ParallelFilter.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sum$core$filter$collection$ParallelFilter$Type = r0
                com.samsung.android.sum.core.filter.collection.ParallelFilter$Type r1 = com.samsung.android.sum.core.filter.collection.ParallelFilter.Type.SHARED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sum$core$filter$collection$ParallelFilter$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sum.core.filter.collection.ParallelFilter$Type r1 = com.samsung.android.sum.core.filter.collection.ParallelFilter.Type.DNC     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sum.core.filter.factory.ParallelFilterCreator.AnonymousClass1.<clinit>():void");
        }
    }

    public MediaFilter newFilter(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        MediaFilterGroupBase mediaFilterGroupBase;
        ParallelDescriptor parallelDescriptor = (ParallelDescriptor) mFDescriptor;
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sum$core$filter$collection$ParallelFilter$Type[parallelDescriptor.getParallelType().ordinal()];
        boolean z = true;
        if (i2 == 1) {
            mediaFilterGroupBase = new ParallelSharedFilter(parallelDescriptor, mediaFilterFactory.getBufferChannelSupplier());
        } else if (i2 != 2) {
            mediaFilterGroupBase = null;
        } else {
            mediaFilterGroupBase = new ParallelDNCFilter(parallelDescriptor, mediaFilterFactory.getBufferChannelSupplier());
        }
        if (mediaFilterGroupBase == null) {
            z = false;
        }
        Def.require(z);
        Stream<MFDescriptor> stream = parallelDescriptor.getDescriptors().stream();
        Objects.requireNonNull(mediaFilterFactory);
        List list = (List) stream.map(new k(mediaFilterFactory, 0)).map(new k(mediaFilterFactory, 1)).collect(Collectors.toList());
        IntStream.range(0, list.size()).forEach(new l(list));
        mediaFilterGroupBase.addFilter(list);
        return mediaFilterGroupBase;
    }
}
