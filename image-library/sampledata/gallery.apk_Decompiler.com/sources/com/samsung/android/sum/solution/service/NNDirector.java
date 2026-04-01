package com.samsung.android.sum.solution.service;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.controller.SumClient;
import com.samsung.android.sum.core.descriptor.CodecDescriptor;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptorBuilder;
import com.samsung.android.sum.core.descriptor.MediaMuxerDescriptor;
import com.samsung.android.sum.core.descriptor.MediaParserDescriptor;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.graph.MFDescriptorGraph;
import com.samsung.android.sum.core.service.ServiceProxySupplier;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.HwUnit;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.nn.Model;
import com.samsung.android.sum.core.types.nn.NNFW;
import com.samsung.android.sum.solution.Option;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNDirector {
    private static final String TAG = Def.tagOf((Class<?>) NNDirector.class);
    private final String AlphaChannelPluginName = "com.samsung.android.sum.ext.plugin.AlphaChannelPlugin";
    private final String OldPhotoPluginName = "com.samsung.android.sum.nn.plugin.OldPhotoPlugin";
    private final String defaultServiceClass = "com.samsung.android.sum.nn.service.RemoteNNService";
    private final String defaultServicePackage = "com.samsung.android.sum.nn.service";
    private MFDescriptorBuilder descriptorBuilder;
    private final MFDescriptorGraph.Builder graphBuilder;
    private final ServiceProxySupplier serviceProxySupplier;

    public NNDirector(ServiceProxySupplier serviceProxySupplier2) {
        if (serviceProxySupplier2 instanceof PlaceHolder) {
            this.serviceProxySupplier = (ServiceProxySupplier) ((PlaceHolder) serviceProxySupplier2).setParameters("com.samsung.android.sum.nn.service", "com.samsung.android.sum.nn.service.RemoteNNService").reset();
        } else {
            this.serviceProxySupplier = serviceProxySupplier2;
        }
        this.graphBuilder = new MFDescriptorGraph.Builder();
    }

    @Deprecated
    public SumClient newAiUpscaler() {
        return newImageUpscaler();
    }

    public SumClient newImageUpscaler() {
        return newImageUpscaler(new Option());
    }

    public SumClient newMotionPhotoUpscaler() {
        return newMotionPhotoUpscaler(new Option());
    }

    public SumClient newOldPhotoDetector() {
        return newOldPhotoDetector(new Option());
    }

    public SumClient newOldPhotoEnhancer() {
        return newOldPhotoEnhancer(new Option());
    }

    public SumClient newVideoUpscaler() {
        Option option = new Option();
        option.setAudioBitrate(256000);
        return newVideoUpscaler(option);
    }

    @Deprecated
    public SumClient newAiUpscaler(Option option) {
        return newImageUpscaler(option);
    }

    public SumClient newImageUpscaler(Option option) {
        MFDescriptorBuilder identifier = this.descriptorBuilder.setIdentifier(Model.MIRACLE_ESTIMATOR);
        NNFW nnfw = NNFW.TFLITE;
        MFDescriptorBuilder nNFramework = identifier.setNNFramework(nnfw);
        HwUnit hwUnit = HwUnit.GPU;
        Class<NNDescriptor> cls = NNDescriptor.class;
        NNDescriptor nNDescriptor = (NNDescriptor) nNFramework.setHardwareUnit(hwUnit).setNumOfUnits(1).setKeepFilterDatatype(true).setInputWithEvaluationValue(true).build(cls);
        NNDescriptor nNDescriptor2 = (NNDescriptor) this.descriptorBuilder.setIdentifier(Model.MIRACLE_FILTER).setNNFramework(nnfw).setHardwareUnit(hwUnit).setNumOfUnits(1).build(cls);
        NNDescriptor nNDescriptor3 = (NNDescriptor) this.descriptorBuilder.setIdentifier(Model.IMAGE_UPSCALER_X4).setNNFramework(NNFW.SNAP).setHardwareUnit(hwUnit).setNumOfUnits(1).setTargetFormat(MediaFormat.newImageBuilder().setDataType(DataType.U8C3).build()).build(cls);
        float floatValue = option.getFilterThreshold().floatValue();
        if (floatValue == 0.0f) {
            floatValue = 86.0f;
        }
        this.graphBuilder.addNode((MFDescriptor) nNDescriptor, (MFDescriptor) nNDescriptor3, Evaluator.ge(Float.valueOf(floatValue))).addNode((MFDescriptor) nNDescriptor, (MFDescriptor) nNDescriptor2, Evaluator.lt(Float.valueOf(floatValue))).addNode(nNDescriptor2, nNDescriptor3);
        if (option.isSupportAlphaChannel()) {
            Class<ImgpDescriptor> cls2 = ImgpDescriptor.class;
            this.graphBuilder.addNode((ImgpDescriptor) this.descriptorBuilder.setPluginClassName("com.samsung.android.sum.ext.plugin.AlphaChannelPlugin").setImgpTypeName("EXTRACT_ALPHA").build(cls2), nNDescriptor).addNode(nNDescriptor3, (ImgpDescriptor) this.descriptorBuilder.setPluginClassName("com.samsung.android.sum.ext.plugin.AlphaChannelPlugin").setImgpTypeName("MERGE_ALPHA").build(cls2));
        }
        return new SumClient(this.serviceProxySupplier.get(), this.graphBuilder.build(option));
    }

    public SumClient newMotionPhotoUpscaler(Option option) {
        this.graphBuilder.addNode((NNDescriptor) this.descriptorBuilder.setIdentifier(Model.IMAGE_UPSCALER_X4).setNNFramework(NNFW.SNAP).setHardwareUnit(HwUnit.GPU).setNumOfUnits(1).setTargetFormat(MediaFormat.newImageBuilder().setDataType(DataType.U8C3).build()).build(NNDescriptor.class));
        return new SumClient(this.serviceProxySupplier.get(), this.graphBuilder.build(option));
    }

    public SumClient newOldPhotoDetector(Option option) {
        return new SumClient(this.serviceProxySupplier.get(), this.graphBuilder.addNode((NNDescriptor) this.descriptorBuilder.setIdentifier(Model.OLD_PHOTO_ESTIMATOR).setNNFramework(NNFW.SNAP).setHardwareUnit(HwUnit.GPU).setNumOfUnits(1).setKeepFilterDatatype(true).build(NNDescriptor.class)).build(option));
    }

    public SumClient newOldPhotoEnhancer(Option option) {
        Class<ImgpDescriptor> cls = ImgpDescriptor.class;
        ImgpDescriptor imgpDescriptor = (ImgpDescriptor) this.descriptorBuilder.setPluginClassName("com.samsung.android.sum.nn.plugin.OldPhotoPlugin").setImgpTypeName("SEPARATE_BG_FACES").build(cls);
        ImgpDescriptor imgpDescriptor2 = (ImgpDescriptor) this.descriptorBuilder.setPluginClassName("com.samsung.android.sum.nn.plugin.OldPhotoPlugin").setImgpTypeName("COMPOSE_BG_FACES").setWaitToReceiveAll(true).build(cls);
        imgpDescriptor2.setWaitToReceiveAll(true);
        MFDescriptorBuilder identifier = this.descriptorBuilder.setIdentifier(Model.OLD_PHOTO_ENHANCER);
        NNFW nnfw = NNFW.SNAP;
        MFDescriptorBuilder nNFramework = identifier.setNNFramework(nnfw);
        HwUnit hwUnit = HwUnit.GPU;
        Class<NNDescriptor> cls2 = NNDescriptor.class;
        NNDescriptor nNDescriptor = (NNDescriptor) nNFramework.setHardwareUnit(hwUnit).setNumOfUnits(1).setFilterIgnorable(true).setTargetFormat(MediaFormat.newImageBuilder().setDataType(DataType.U8C3).build()).build(cls2);
        NNDescriptor nNDescriptor2 = (NNDescriptor) this.descriptorBuilder.setIdentifier(Model.OLD_PHOTO_FACE_ENHANCER).setNNFramework(nnfw).setHardwareUnit(hwUnit).setNumOfUnits(1).build(cls2);
        return new SumClient(this.serviceProxySupplier.get(), this.graphBuilder.addNode(imgpDescriptor, nNDescriptor).addNode(nNDescriptor, imgpDescriptor2).addNode(imgpDescriptor, nNDescriptor2).addNode(nNDescriptor2, imgpDescriptor2).build(option));
    }

    public SumClient newVideoUpscaler(Option option) {
        MFDescriptorBuilder mFDescriptorBuilder = this.descriptorBuilder;
        MediaType mediaType = MediaType.COMPRESSED_AUDIO;
        Class<CodecDescriptor> cls = CodecDescriptor.class;
        CodecDescriptor codecDescriptor = (CodecDescriptor) mFDescriptorBuilder.setMediaType(mediaType).setRunInstant(true).build(cls);
        MFDescriptorBuilder mFDescriptorBuilder2 = this.descriptorBuilder;
        MediaType mediaType2 = MediaType.RAW_AUDIO;
        CodecDescriptor codecDescriptor2 = (CodecDescriptor) mFDescriptorBuilder2.setMediaType(mediaType2).setRunInstant(true).setBitrate(option.getAudioBitrate()).build(cls);
        MFDescriptorBuilder mFDescriptorBuilder3 = this.descriptorBuilder;
        MediaType mediaType3 = MediaType.COMPRESSED_VIDEO;
        CodecDescriptor codecDescriptor3 = (CodecDescriptor) mFDescriptorBuilder3.setMediaType(mediaType3).setRunInstant(true).build(cls);
        MFDescriptorBuilder mFDescriptorBuilder4 = this.descriptorBuilder;
        MediaType mediaType4 = MediaType.RAW_VIDEO;
        CodecDescriptor codecDescriptor4 = (CodecDescriptor) mFDescriptorBuilder4.setMediaType(mediaType4).setRunInstant(true).setScale(4.0f).setBitrate(option.getVideoBitrate()).build(cls);
        MediaParserDescriptor mediaParserDescriptor = (MediaParserDescriptor) this.descriptorBuilder.build(MediaParserDescriptor.class);
        MediaMuxerDescriptor mediaMuxerDescriptor = (MediaMuxerDescriptor) this.descriptorBuilder.setFileFormat(0).setMediaTypesToNotifyEvent(mediaType3).build(MediaMuxerDescriptor.class);
        NNDescriptor nNDescriptor = (NNDescriptor) this.descriptorBuilder.setIdentifier(Model.VIDEO_UPSCALER_X4).setNNFramework(NNFW.TFLITE).setHardwareUnit(HwUnit.GPU).setNumOfUnits(1).setBatchIO(true).setKeepFilterDatatype(true).build(NNDescriptor.class);
        option.runOneByOne();
        option.packedIOBuffers();
        return new SumClient(this.serviceProxySupplier.get(), this.graphBuilder.addNode((MFDescriptor) mediaParserDescriptor, (MFDescriptor) codecDescriptor, Evaluator.eq(mediaType), 1).addNode(codecDescriptor, codecDescriptor2).addNode((MFDescriptor) codecDescriptor2, (MFDescriptor) mediaMuxerDescriptor, Evaluator.eq(mediaType2)).addNode((MFDescriptor) mediaParserDescriptor, (MFDescriptor) codecDescriptor3, Evaluator.eq(mediaType3), 1).addNode((MFDescriptor) codecDescriptor3, (MFDescriptor) nNDescriptor, 2).addNode((MFDescriptor) nNDescriptor, (MFDescriptor) codecDescriptor4, 3).addNode((MFDescriptor) codecDescriptor4, (MFDescriptor) mediaMuxerDescriptor, Evaluator.eq(mediaType4)).build(option));
    }
}
