package com.samsung.android.sum.solution.filter;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.filter.ImgpFilter;
import com.samsung.android.sum.core.filter.factory.MediaFilterFactory;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.plugin.ImgpPlugin;
import com.samsung.android.sum.core.plugin.NativeUniImgpPlugin;
import com.samsung.android.sum.core.plugin.Plugin;
import com.samsung.android.sum.core.plugin.PluginStore;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.core.types.OptionBase;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UniImgp {
    public static final int INTER_AREA = 3;
    public static final int INTER_CUBIC = 2;
    public static final int INTER_LANCZOS4 = 4;
    public static final int INTER_LINEAR = 1;
    public static final int INTER_LINEAR_EXACT = 5;
    public static final int INTER_MAX = 7;
    public static final int INTER_NEAREST = 0;
    public static final int OPTION_IMGP_TYPE = 2010;
    public static final int OPTION_IMGP_TYPE_NAME = 2011;
    public static final int OPTION_LATEST_PLUGIN_ORDER = 2001;
    public static final int OPTION_PERSISTENT_INPUT_FORMAT = 2003;
    public static final int OPTION_PERSISTENT_OUTPUT_FORMAT = 2004;
    public static final int OPTION_PLUGIN_ORDER = 2002;
    public static final int OPTION_PREFERRED_COLOR_FORMAT = 2005;
    public static final int OPTION_USE_PERSISTENT_FORMAT = 2000;
    public static final int PSNR = 0;
    public static final int SSIM = 1;
    private static volatile PluginStore globalStore;
    private final MediaFilterFactory factory;
    private Option option;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface InterpolationType {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Option extends OptionBase {
        public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
            public Option createFromParcel(Parcel parcel) {
                return new Option(parcel);
            }

            public Option[] newArray(int i2) {
                return new Option[i2];
            }
        };
        private transient PluginStore pluginStore;

        public Option() {
        }

        public MediaFormat getPersistentInputFormat() {
            return (MediaFormat) get(2003);
        }

        public MediaFormat getPersistentOutputFormat() {
            return (MediaFormat) get(2004);
        }

        public Map<ImgpType, List<ImgpPlugin.Type>> getPluginOrderMap() {
            return (Map) get(2002);
        }

        public PluginStore getPluginStore() {
            return this.pluginStore;
        }

        public ColorFormat getPreferredColorFormat() {
            return (ColorFormat) get(UniImgp.OPTION_PREFERRED_COLOR_FORMAT);
        }

        public boolean isLatestPluginsOrder() {
            return contains(2001);
        }

        public boolean isUsePersistentFormat() {
            return ((Boolean) get(2000, Boolean.FALSE)).booleanValue();
        }

        public Option latestPluginsOrder() {
            set(2001);
            return this;
        }

        public OptionBase set(int i2) {
            return super.set(i2);
        }

        public Option setPersistentInputFormat(MediaFormat mediaFormat) {
            set(2003, mediaFormat);
            return this;
        }

        public Option setPersistentOutputFormat(MediaFormat mediaFormat) {
            set(2004, mediaFormat);
            return this;
        }

        public Option setPluginOrder(ImgpPlugin.Type... typeArr) {
            Map hashMap = contains(2002) ? (Map) get(2002) : new HashMap();
            hashMap.put(ImgpType.ANY, Arrays.asList(typeArr));
            return (Option) set(2002, hashMap);
        }

        public Option setPluginStore(PluginStore pluginStore2) {
            this.pluginStore = pluginStore2;
            return this;
        }

        public Option setPreferredColorFormat(ColorFormat colorFormat) {
            set(UniImgp.OPTION_PREFERRED_COLOR_FORMAT, colorFormat);
            return this;
        }

        public Option setUsePersistentFormat(boolean z) {
            set(2000, Boolean.valueOf(z));
            return this;
        }

        public Option(Parcel parcel) {
            super(parcel);
        }

        public OptionBase set(int i2, Object obj) {
            return super.set(i2, obj);
        }

        public Option setPluginOrder(ImgpType imgpType, ImgpPlugin.Type... typeArr) {
            Map hashMap = contains(2002) ? (Map) get(2002) : new HashMap();
            hashMap.put(imgpType, Arrays.asList(typeArr));
            return (Option) set(2002, hashMap);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface OptionType {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface QualityMetricType {
    }

    private UniImgp() {
        this.option = null;
        this.factory = new MediaFilterFactory.Builder().addPluginStore(getGlobalStore()).addDefaultCreators().build();
    }

    public static void clearGlobalStore() {
        if (globalStore != null) {
            synchronized (PluginStore.class) {
                globalStore.clear();
            }
        }
    }

    private void configDescriptorByOption(ImgpDescriptor imgpDescriptor) {
        Option option2 = this.option;
        if (option2 != null) {
            imgpDescriptor.setUsePersistentFormat(option2.isUsePersistentFormat());
            imgpDescriptor.setLatestPluginsOrder(this.option.isLatestPluginsOrder());
        }
    }

    public static PluginStore getGlobalStore() {
        if (globalStore == null) {
            synchronized (PluginStore.class) {
                try {
                    if (globalStore == null) {
                        globalStore = PluginStore.of();
                        globalStore.add((Plugin<?>[]) new Plugin[]{new NativeUniImgpPlugin()});
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return globalStore;
    }

    public static UniImgp of() {
        return new UniImgp();
    }

    public static Operator ofCrop() {
        return new UniImgp().newOperator(ImgpType.CROP);
    }

    public static Operator ofCvtColor() {
        return new UniImgp().newOperator(ImgpType.CVT_COLOR);
    }

    public static Operator ofCvtData() {
        return new UniImgp().newOperator(ImgpType.CVT_DATA);
    }

    public static Operator ofCvtGamut() {
        return new UniImgp().newOperator(ImgpType.CVT_GAMUT);
    }

    public static Operator ofDecode() {
        return new UniImgp().newOperator(ImgpType.DECODE);
    }

    public static Operator ofEncode() {
        return new UniImgp().newOperator(ImgpType.ENCODE);
    }

    public static Operator ofEncodeHDR() {
        return new UniImgp().newOperator(ImgpType.ENCODE_HDR);
    }

    public static Operator ofFlip() {
        return new UniImgp().newOperator(ImgpType.FLIP);
    }

    public static Operator ofMerge() {
        return new UniImgp().newOperator(ImgpType.MERGE);
    }

    public static Operator ofQuality() {
        return new UniImgp().newOperator(ImgpType.QUALITY_MEASURE);
    }

    public static Operator ofResize() {
        return new UniImgp().newOperator(ImgpType.RESIZE);
    }

    public static Operator ofRotate() {
        return new UniImgp().newOperator(ImgpType.ROTATE);
    }

    public static Operator ofSplit() {
        return new UniImgp().newOperator(ImgpType.SPLIT);
    }

    public static Operator ofUnified() {
        return new UniImgp().newOperator(ImgpType.ANY);
    }

    public Operator newOperator() {
        if (this.option.contains(2004)) {
            return new NativeImgpFilterAdapter(this.option.getPersistentInputFormat(), this.option.getPersistentOutputFormat(), this.option.getPreferredColorFormat());
        }
        throw new IllegalArgumentException("persistent output format should be given");
    }

    public static UniImgp of(Option option2) {
        return new UniImgp(option2);
    }

    public static Operator ofCrop(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.CROP);
    }

    public static Operator ofCvtColor(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.CVT_COLOR);
    }

    public static Operator ofCvtData(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.CVT_DATA);
    }

    public static Operator ofCvtGamut(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.CVT_GAMUT);
    }

    public static Operator ofDecode(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.DECODE);
    }

    public static Operator ofEncode(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.ENCODE);
    }

    public static Operator ofEncodeHDR(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.ENCODE_HDR);
    }

    public static Operator ofFlip(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.FLIP);
    }

    public static Operator ofMerge(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.MERGE);
    }

    public static Operator ofQuality(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.QUALITY_MEASURE);
    }

    public static Operator ofResize(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.RESIZE);
    }

    public static Operator ofRotate(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.ROTATE);
    }

    public static Operator ofSplit(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.SPLIT);
    }

    public static Operator ofUnified(Option option2) {
        return new UniImgp(option2).newOperator(ImgpType.ANY);
    }

    private UniImgp(Option option2) {
        this.option = null;
        MediaFilterFactory.Builder builder = new MediaFilterFactory.Builder();
        builder.addPluginStore((PluginStore) Optional.ofNullable(option2.getPluginStore()).orElse(getGlobalStore())).addDefaultCreators();
        this.option = option2;
        this.factory = builder.build();
    }

    public <T extends ImgpType> Operator newOperator(T t) {
        ImgpDescriptor imgpDescriptor = (ImgpDescriptor) MFDescriptor.newBuilder().setImgpType(t).build(ImgpDescriptor.class);
        configDescriptorByOption(imgpDescriptor);
        return new ImgpFilterAdapter((ImgpFilter) this.factory.newFilter(imgpDescriptor));
    }

    public static PluginStore getGlobalStore(Context context) {
        if (globalStore == null) {
            synchronized (PluginStore.class) {
                try {
                    if (globalStore == null) {
                        globalStore = PluginStore.of(context);
                        globalStore.add((Plugin<?>[]) new Plugin[]{new NativeUniImgpPlugin()});
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return globalStore;
    }

    public Operator newOperator(ImgpDescriptor imgpDescriptor) {
        configDescriptorByOption(imgpDescriptor);
        return new ImgpFilterAdapter((ImgpFilter) this.factory.newFilter(imgpDescriptor));
    }
}
