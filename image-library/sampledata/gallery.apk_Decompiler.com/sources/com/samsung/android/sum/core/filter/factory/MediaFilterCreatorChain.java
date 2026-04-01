package com.samsung.android.sum.core.filter.factory;

import Ad.C0720a;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaFilterCreatorChain implements MediaFilterCreator {
    private List<MediaFilterCreator> creators;
    private DescriptorFinder descriptorFinder;
    private final List<Enum<?>> types;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface DescriptorFinder {
        MFDescriptor find(MFDescriptor mFDescriptor);
    }

    public MediaFilterCreatorChain(Enum<?>... enumArr) {
        this.types = Arrays.asList(enumArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaFilter lambda$newFilter$7(MediaFilterFactory mediaFilterFactory, MediaFilter mediaFilter, MFDescriptor mFDescriptor) {
        MediaFilter mediaFilter2 = null;
        for (MediaFilterCreator newFilter : this.creators) {
            mediaFilter2 = newFilter.newFilter(mediaFilterFactory, mFDescriptor, mediaFilter);
        }
        return mediaFilter2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFilterCreator lambda$prepare$0(Map map, Enum enumR) {
        return (MediaFilterCreator) ((List) Optional.ofNullable((List) map.get(enumR)).orElseGet(new C0720a(1))).stream().findFirst().orElseThrow(new u(4));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$prepare$1(MediaFilterCreator mediaFilterCreator) {
        return mediaFilterCreator instanceof PluginFilterCreator;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MFDescriptor lambda$prepare$2(MediaFilterCreator mediaFilterCreator, MFDescriptor mFDescriptor) {
        return (MFDescriptor) Optional.ofNullable(((PluginFilterCreator) mediaFilterCreator).getPluginStore().get(mFDescriptor)).map(new c(1)).orElse((Object) null);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ DescriptorFinder lambda$prepare$3(MediaFilterCreator mediaFilterCreator) {
        return new f(mediaFilterCreator);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MFDescriptor lambda$prepare$4(MFDescriptor mFDescriptor) {
        return null;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.samsung.android.sum.core.filter.factory.MediaFilterCreatorChain$DescriptorFinder, java.lang.Object] */
    /* access modifiers changed from: private */
    public static /* synthetic */ DescriptorFinder lambda$prepare$5() {
        return new Object();
    }

    public MediaFilter newFilter(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        return (MediaFilter) Optional.ofNullable(this.descriptorFinder).map(new a(1, mFDescriptor)).map(new g(this, mediaFilterFactory, mediaFilter)).orElse((Object) null);
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Object, java.util.function.Supplier] */
    public void prepare(Map<Class<?>, List<MediaFilterCreator>> map) {
        List<MediaFilterCreator> list = (List) this.types.stream().map(new a(0, map)).collect(Collectors.toList());
        this.creators = list;
        this.descriptorFinder = list.stream().filter(new b(0)).findFirst().map(new c(0)).orElseGet(new Object());
    }
}
