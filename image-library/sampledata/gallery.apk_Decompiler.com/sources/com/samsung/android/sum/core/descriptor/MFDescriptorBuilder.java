package com.samsung.android.sum.core.descriptor;

import android.media.MediaFormat;
import android.util.Pair;
import android.view.Surface;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.descriptor.MFDescriptorBase;
import com.samsung.android.sum.core.filter.collection.ParallelFilter;
import com.samsung.android.sum.core.filter.collection.SequentialFilter;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.types.HwUnit;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.core.types.LoadType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.Version;
import com.samsung.android.sum.core.types.nn.NNFW;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MFDescriptorBuilder {
    int align = 0;
    MediaFormat androidMediaFormat;
    int bitrate = 0;
    float cropRectRatio = 0.0f;
    List<MFDescriptor> descriptors;
    Pair<Integer, Integer> dimension;
    Map<String, Object> extra;
    int fileFormat;
    String filterId;
    MFDescriptorBase.Option filterOption = new MFDescriptorBase.Option();
    Class<?> filterType;
    HwUnit hardwareUnit;
    Enum<?> identifier;
    ImgpPlugin.Type imgpPluginType;
    ImgpType imgpType;
    String imgpTypeName;
    /* access modifiers changed from: package-private */
    public LoadType loadType;
    MediaType mediaType;
    List<MediaType> mediaTypes;
    Set<MediaType> mediaTypesToNotifyEvent = new HashSet();
    int[] messageToReceive;
    String mimeType;
    NNFW nnFramework;
    int numOfUnits;
    String operatorName;
    ParallelFilter.Type parallelType = ParallelFilter.Type.DNC;
    Class<?> pluginClass;
    String pluginClassName;
    float scale = 0.0f;
    SequentialFilter.Mode sequentialMode;
    SequentialFilter.Type sequentialType;
    MFDescriptor successorDescriptor;
    Surface surface;
    com.samsung.android.sum.core.format.MediaFormat targetFormat;
    Version version;

    public <V extends MFDescriptor> V build(Class<V> cls) {
        V v;
        if (cls == StapleDescriptor.class) {
            v = new StapleDescriptor(this);
        } else if (cls == NNFWDescriptor.class) {
            v = new NNFWDescriptor(this);
        } else if (cls == CodecDescriptor.class) {
            v = new CodecDescriptor(this);
        } else if (cls == DecorateDescriptor.class) {
            v = new DecorateDescriptor(this);
        } else if (cls == ImgpDescriptor.class) {
            v = new ImgpDescriptor(this);
        } else if (cls == MediaMuxerDescriptor.class) {
            v = new MediaMuxerDescriptor(this);
        } else if (cls == MediaParserDescriptor.class) {
            v = new MediaParserDescriptor(this);
        } else if (cls == ParallelDescriptor.class) {
            v = new ParallelDescriptor(this);
        } else if (cls == SequentialDescriptor.class) {
            v = new SequentialDescriptor(this);
        } else if (cls == NNDescriptor.class) {
            v = new NNDescriptor(this);
        } else if (cls == StaplePluginDescriptor.class) {
            v = new StaplePluginDescriptor(this);
        } else if (cls == DecoratePluginDescriptor.class) {
            v = new DecoratePluginDescriptor(this);
        } else if (cls == StreamPluginDescriptor.class) {
            v = new StreamPluginDescriptor(this);
        } else if (cls == DecorateStreamPluginDescriptor.class) {
            v = new DecorateStreamPluginDescriptor(this);
        } else {
            v = null;
        }
        if (v != null) {
            return v;
        }
        throw new UnsupportedOperationException();
    }

    public MFDescriptorBuilder setAlign(int i2) {
        this.align = i2;
        return this;
    }

    public MFDescriptorBuilder setAndroidMediaFormat(MediaFormat mediaFormat) {
        this.androidMediaFormat = mediaFormat;
        return this;
    }

    public MFDescriptorBuilder setBatchIO(boolean z) {
        this.filterOption.setBatchIO(z);
        return this;
    }

    public MFDescriptorBuilder setBitrate(int i2) {
        this.bitrate = i2;
        return this;
    }

    public MFDescriptorBuilder setCropRectRatio(float f) {
        this.cropRectRatio = f;
        return this;
    }

    public MFDescriptorBuilder setDescriptors(List<MFDescriptor> list) {
        this.descriptors = list;
        return this;
    }

    public MFDescriptorBuilder setDimension(Pair<Integer, Integer> pair) {
        this.dimension = pair;
        return this;
    }

    public MFDescriptorBuilder setExtra(Map<String, Object> map) {
        this.extra = map;
        return this;
    }

    public MFDescriptorBuilder setFileFormat(int i2) {
        this.fileFormat = i2;
        return this;
    }

    public MFDescriptorBuilder setFilterId(String str) {
        this.filterId = str;
        return this;
    }

    public MFDescriptorBuilder setFilterIgnorable(boolean z) {
        this.filterOption.setFilterIgnorable(z);
        return this;
    }

    public MFDescriptorBuilder setFilterType(Class<?> cls) {
        this.filterType = cls;
        return this;
    }

    public MFDescriptorBuilder setHardwareUnit(HwUnit hwUnit) {
        this.hardwareUnit = hwUnit;
        return this;
    }

    public MFDescriptorBuilder setIdentifier(Enum<?> enumR) {
        this.identifier = enumR;
        return this;
    }

    public MFDescriptorBuilder setImgpPluginType(ImgpPlugin.Type type) {
        this.imgpPluginType = type;
        return this;
    }

    public MFDescriptorBuilder setImgpType(ImgpType imgpType2) {
        this.imgpType = imgpType2;
        return this;
    }

    public MFDescriptorBuilder setImgpTypeName(String str) {
        this.imgpTypeName = str;
        return this;
    }

    public MFDescriptorBuilder setInputWithEvaluationValue(boolean z) {
        this.filterOption.setInputWithEvaluationValue(z);
        return this;
    }

    public MFDescriptorBuilder setKeepFilterDatatype(boolean z) {
        this.filterOption.setKeepFilterDatatype(z);
        return this;
    }

    public MFDescriptorBuilder setLoadType(LoadType loadType2) {
        this.loadType = loadType2;
        return this;
    }

    public MFDescriptorBuilder setMediaType(MediaType mediaType2) {
        this.mediaType = mediaType2;
        return this;
    }

    public MFDescriptorBuilder setMediaTypes(List<MediaType> list) {
        this.mediaTypes = list;
        return this;
    }

    public MFDescriptorBuilder setMediaTypesToNotifyEvent(MediaType... mediaTypeArr) {
        this.mediaTypesToNotifyEvent.addAll((Collection) Arrays.stream(mediaTypeArr).map(new a(10)).collect(Collectors.toList()));
        return this;
    }

    public MFDescriptorBuilder setMessageToReceive(int... iArr) {
        this.messageToReceive = iArr;
        return this;
    }

    public MFDescriptorBuilder setMimeType(String str) {
        this.mimeType = str;
        return this;
    }

    public MFDescriptorBuilder setNNFramework(NNFW nnfw) {
        this.nnFramework = nnfw;
        return this;
    }

    public MFDescriptorBuilder setNumOfUnits(int i2) {
        this.numOfUnits = i2;
        return this;
    }

    public MFDescriptorBuilder setOperatorName(String str) {
        this.operatorName = str;
        return this;
    }

    public MFDescriptorBuilder setParallelType(ParallelFilter.Type type) {
        this.parallelType = type;
        return this;
    }

    public MFDescriptorBuilder setPluginClass(Class<?> cls) {
        this.pluginClass = cls;
        return this;
    }

    public MFDescriptorBuilder setPluginClassName(String str) {
        Def.require(str.startsWith("com.samsung.android."), "pluginClassName should be follow sec package naming rule: com.samsung.android.{}", new Object[0]);
        this.pluginClassName = str;
        return this;
    }

    public MFDescriptorBuilder setPriority(MediaType... mediaTypeArr) {
        this.filterOption.setPriority(Arrays.asList(mediaTypeArr));
        return this;
    }

    public MFDescriptorBuilder setRunInstant(boolean z) {
        this.filterOption.setRunInstant(z);
        return this;
    }

    public MFDescriptorBuilder setScale(float f) {
        this.scale = f;
        return this;
    }

    public MFDescriptorBuilder setSequentialMode(SequentialFilter.Mode mode) {
        this.sequentialMode = mode;
        return this;
    }

    public MFDescriptorBuilder setSequentialType(SequentialFilter.Type type) {
        this.sequentialType = type;
        return this;
    }

    public MFDescriptorBuilder setSuccessorDescriptor(MFDescriptor mFDescriptor) {
        this.successorDescriptor = mFDescriptor;
        return this;
    }

    public MFDescriptorBuilder setSurface(Surface surface2) {
        this.surface = surface2;
        return this;
    }

    public MFDescriptorBuilder setTag(String str) {
        this.filterOption.set(13, (Object) str);
        return this;
    }

    public MFDescriptorBuilder setTargetFormat(com.samsung.android.sum.core.format.MediaFormat mediaFormat) {
        this.targetFormat = mediaFormat;
        return this;
    }

    public MFDescriptorBuilder setVersion(Version version2) {
        this.version = version2;
        return this;
    }

    public MFDescriptorBuilder setWaitToReceiveAll(boolean z) {
        this.filterOption.setWaitToReceiveAll(z);
        return this;
    }

    public MFDescriptorBuilder setDescriptors(MFDescriptor... mFDescriptorArr) {
        return setDescriptors((List<MFDescriptor>) Arrays.asList(mFDescriptorArr));
    }

    public MFDescriptorBuilder setDimension(int i2, int i7) {
        return setDimension(new Pair(Integer.valueOf(i2), Integer.valueOf(i7)));
    }

    public <T> MFDescriptorBuilder setExtra(String str, T t) {
        if (this.extra == null) {
            this.extra = new HashMap();
        }
        this.extra.put(str, t);
        return this;
    }

    public MFDescriptorBuilder setMediaTypes(MediaType... mediaTypeArr) {
        return setMediaTypes((List<MediaType>) Arrays.asList(mediaTypeArr));
    }

    public MFDescriptorBuilder setPriority(List<MediaType> list) {
        this.filterOption.setPriority(list);
        return this;
    }

    public MFDescriptorBuilder setVersion(String str) {
        return setVersion(new Version(str));
    }
}
