package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.filter.PluginFilter;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.Version;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PluginDescriptor extends MFDescriptorBase {
    private static final String TAG = Def.tagOf((Class<?>) PluginDescriptor.class);
    private MutableMediaFormat inputFormat;
    protected String operatorName;
    private MutableMediaFormat outputFormat;
    private Class<?> pluginClass;
    private String pluginClassName;
    private Enum<?> pluginId;
    private Version pluginVersion;
    private MutableMediaFormat targetFormat;

    public PluginDescriptor() {
        this.pluginVersion = new Version(0);
        setFilterType(PluginFilter.class);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getFilterId$0(String str) {
        return !str.isEmpty();
    }

    public void copyTo(MFDescriptorBase mFDescriptorBase) {
        super.copyTo(mFDescriptorBase);
        if (mFDescriptorBase instanceof PluginDescriptor) {
            PluginDescriptor pluginDescriptor = (PluginDescriptor) mFDescriptorBase;
            pluginDescriptor.pluginClass = this.pluginClass;
            pluginDescriptor.pluginClassName = this.pluginClassName;
            pluginDescriptor.pluginId = this.pluginId;
            pluginDescriptor.pluginVersion = this.pluginVersion;
            pluginDescriptor.extra.putAll(this.extra);
        }
    }

    public String getFilterId() {
        return (String) Stream.of(new String[]{(String) Optional.ofNullable(getPluginId()).map(new a(13)).orElse(""), (String) Optional.ofNullable(getPluginClassName()).orElse("")}).filter(new com.samsung.android.gallery.module.dynamicview.a(8)).collect(Collectors.joining("#"));
    }

    public Class<?> getFilterType() {
        return PluginFilter.class;
    }

    public MutableMediaFormat getInputFormat() {
        return this.inputFormat;
    }

    public String getOperatorName() {
        return this.operatorName;
    }

    public MutableMediaFormat getOutputFormat() {
        return this.outputFormat;
    }

    public Class<?> getPluginClass() {
        Class<?> cls = this.pluginClass;
        if (cls != null) {
            return cls;
        }
        String str = this.pluginClassName;
        if (str != null) {
            try {
                Class<?> cls2 = Class.forName(str);
                this.pluginClass = cls2;
                return cls2;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public String getPluginClassName() {
        String str = this.pluginClassName;
        if (str != null) {
            return str;
        }
        Class<?> cls = this.pluginClass;
        if (cls == null) {
            return null;
        }
        String name = cls.getName();
        this.pluginClassName = name;
        return name;
    }

    public Enum<?> getPluginId() {
        return this.pluginId;
    }

    public MutableMediaFormat getTargetFormat() {
        return this.targetFormat;
    }

    public Version getVersion() {
        return this.pluginVersion;
    }

    public void setOutputFormat(MutableMediaFormat mutableMediaFormat) {
        this.outputFormat = mutableMediaFormat;
    }

    public void setPluginClass(Class<?> cls) {
        this.pluginClass = cls;
    }

    public void setPluginClassName(String str) {
        this.pluginClassName = str;
    }

    public void setPluginId(Enum<?> enumR) {
        this.pluginId = enumR;
    }

    public void setTargetFormat(MutableMediaFormat mutableMediaFormat) {
        this.targetFormat = mutableMediaFormat;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeSerializable(getPluginClass());
        parcel.writeString(getPluginClassName());
        parcel.writeSerializable(this.pluginId);
        parcel.writeSerializable(this.pluginVersion);
        parcel.writeString(this.operatorName);
        parcel.writeMap(this.extra);
    }

    public PluginDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        this.operatorName = mFDescriptorBuilder.operatorName;
    }

    public PluginDescriptor(Parcel parcel) {
        super(parcel);
        this.pluginClass = (Class) parcel.readSerializable();
        this.pluginClassName = parcel.readString();
        this.pluginId = (Enum) parcel.readSerializable();
        this.pluginVersion = (Version) parcel.readSerializable(Version.class.getClassLoader(), Version.class);
        this.operatorName = parcel.readString();
        HashMap hashMap = new HashMap();
        this.extra = hashMap;
        parcel.readMap(hashMap, HashMap.class.getClassLoader(), String.class, Object.class);
    }
}
