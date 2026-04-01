package com.samsung.android.sum.core.filter.factory;

import L5.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptorBuilder;
import com.samsung.android.sum.core.descriptor.MFDescriptorHolder;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.descriptor.NNFWDescriptor;
import com.samsung.android.sum.core.descriptor.NNFWProfile;
import com.samsung.android.sum.core.descriptor.ParallelDescriptor;
import com.samsung.android.sum.core.filter.ImgpFilter;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.filter.PluginDecorateFilter;
import com.samsung.android.sum.core.filter.SyncFilter;
import com.samsung.android.sum.core.filter.collection.ParallelFilter;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.ImgpType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNFilterCreator implements MediaFilterCreator {
    /* access modifiers changed from: private */
    public static /* synthetic */ MFDescriptorHolder lambda$newFilter$0(NNDescriptor nNDescriptor, NNFWProfile nNFWProfile) {
        return new MFDescriptorHolder(MFDescriptor.newBuilder().setNNFramework(nNFWProfile.getFw()).setHardwareUnit(nNFWProfile.getHw()).setSuccessorDescriptor(nNDescriptor).build(NNFWDescriptor.class), new Object[0]);
    }

    public MediaFilter newFilter(MediaFilterFactory mediaFilterFactory, MFDescriptor mFDescriptor, MediaFilter mediaFilter) {
        MediaFilter mediaFilter2;
        NNDescriptor nNDescriptor = (NNDescriptor) mFDescriptor;
        List list = (List) nNDescriptor.getNNFWProfiles().stream().map(new c(2)).flatMap(new b(25)).map(new a(2, nNDescriptor)).collect(Collectors.toList());
        Def.require(!list.isEmpty());
        if (list.size() == 1) {
            mediaFilter2 = new SyncFilter(mediaFilterFactory.newFilter((MFDescriptor) list.get(0)));
        } else {
            mediaFilter2 = mediaFilterFactory.newFilter(MFDescriptor.newBuilder().setParallelType(ParallelFilter.Type.DNC).setDescriptors((List<MFDescriptor>) list).build(ParallelDescriptor.class));
        }
        MFDescriptorBuilder newBuilder = MFDescriptor.newBuilder();
        ImgpType imgpType = ImgpType.ANY;
        Class<ImgpDescriptor> cls = ImgpDescriptor.class;
        ImgpDescriptor imgpDescriptor = (ImgpDescriptor) newBuilder.setImgpType(imgpType).build(cls);
        ImgpDescriptor imgpDescriptor2 = (ImgpDescriptor) newBuilder.setImgpType(imgpType).build(cls);
        imgpDescriptor.setLatestPluginsOrder(true);
        imgpDescriptor2.setLatestPluginsOrder(true);
        MediaFilter newFilter = mediaFilterFactory.newFilter(PluginDecorateFilter.class, nNDescriptor, ImgpFilter.of(mediaFilter2, mediaFilterFactory.newFilter(imgpDescriptor), mediaFilterFactory.newFilter(imgpDescriptor2)));
        MutableMediaFormat inputFormat = nNDescriptor.getInputFormat();
        Objects.requireNonNull(inputFormat);
        inputFormat.set(nNDescriptor.getFilterOption().asInputOption());
        MutableMediaFormat outputFormat = nNDescriptor.getOutputFormat();
        Objects.requireNonNull(outputFormat);
        outputFormat.set(nNDescriptor.getFilterOption().asOutputOption());
        imgpDescriptor.setFilterOption(nNDescriptor.getFilterOption());
        imgpDescriptor2.setFilterOption(nNDescriptor.getFilterOption());
        imgpDescriptor.setTargetFormat(nNDescriptor.getInputFormat());
        if (nNDescriptor.getTargetFormat() != null) {
            imgpDescriptor2.setTargetFormat((MutableMediaFormat) nNDescriptor.getTargetFormat().dupAll());
            imgpDescriptor2.setKeepFilterDatatype(true);
            return newFilter;
        }
        imgpDescriptor2.setTargetFormat(nNDescriptor.getOutputFormat());
        return newFilter;
    }
}
