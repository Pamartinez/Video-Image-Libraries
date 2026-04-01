package com.samsung.android.sum.core.descriptor;

import A8.C0544a;
import Bd.C0726b;
import C9.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.descriptor.PluginDescriptor;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.Version;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PluginDescriptorPair<T extends PluginDescriptor> extends PluginDescriptor {
    public static final Parcelable.Creator<PluginDescriptorPair> CREATOR = new Parcelable.Creator<PluginDescriptorPair>() {
        public PluginDescriptorPair createFromParcel(Parcel parcel) {
            return new PluginDescriptorPair(parcel);
        }

        public PluginDescriptorPair[] newArray(int i2) {
            return new PluginDescriptorPair[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) PluginDescriptorPair.class);
    private final T primaryDescriptor;
    private T subDescriptor;

    public PluginDescriptorPair(PluginDescriptor pluginDescriptor) {
        this.primaryDescriptor = pluginDescriptor;
        if (pluginDescriptor instanceof DecoratePluginDescriptor) {
            this.subDescriptor = (PluginDescriptor) ((DecoratePluginDescriptor) pluginDescriptor).getSuccessorDescriptor();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getExtra$4(String str) {
        return Optional.ofNullable(this.subDescriptor).map(new a(str, 4)).orElse((Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$getExtra$6(String str, Object obj) {
        return Optional.ofNullable(this.subDescriptor).map(new Qa.a(7, (Object) str, obj)).orElse(obj);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MutableMediaFormat lambda$getInputFormat$0() {
        return (MutableMediaFormat) Optional.ofNullable(this.subDescriptor).map(new com.samsung.android.sdk.scs.ai.visual.c2pa.a(15)).orElse((Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MutableMediaFormat lambda$getOutputFormat$1() {
        return (MutableMediaFormat) Optional.ofNullable(this.subDescriptor).map(new com.samsung.android.sdk.scs.ai.visual.c2pa.a(16)).orElse((Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MutableMediaFormat lambda$getTargetFormat$2() {
        return (MutableMediaFormat) Optional.ofNullable(this.subDescriptor).map(new com.samsung.android.sdk.scs.ai.visual.c2pa.a(14)).orElse((Object) null);
    }

    public <V> V getExtra(String str) {
        return Optional.ofNullable(this.primaryDescriptor.getExtra(str)).orElseGet(new C0726b(7, this, str));
    }

    public String getFilterId() {
        return this.primaryDescriptor.filterId;
    }

    public MediaFilter.Option getFilterOption() {
        return this.primaryDescriptor.filterOption;
    }

    public Class<?> getFilterType() {
        return this.primaryDescriptor.filterType;
    }

    public MutableMediaFormat getInputFormat() {
        return (MutableMediaFormat) Optional.ofNullable(this.primaryDescriptor.getInputFormat()).orElseGet(new c(this, 0));
    }

    public MutableMediaFormat getOutputFormat() {
        return (MutableMediaFormat) Optional.ofNullable(this.primaryDescriptor.getOutputFormat()).orElseGet(new c(this, 2));
    }

    public Class<?> getPluginClass() {
        return this.primaryDescriptor.getPluginClass();
    }

    public String getPluginClassName() {
        return this.primaryDescriptor.getPluginClassName();
    }

    public Enum<?> getPluginId() {
        return this.primaryDescriptor.getPluginId();
    }

    public T getPrimaryDescriptor() {
        return this.primaryDescriptor;
    }

    public T getSubDescriptor() {
        return this.subDescriptor;
    }

    public MutableMediaFormat getTargetFormat() {
        return (MutableMediaFormat) Optional.ofNullable(this.primaryDescriptor.getTargetFormat()).orElseGet(new c(this, 1));
    }

    public Version getVersion() {
        return this.primaryDescriptor.getVersion();
    }

    public <V> void setExtra(String str, V v) {
        this.primaryDescriptor.setExtra(str, v);
    }

    public void setOutputFormat(MutableMediaFormat mutableMediaFormat) {
        this.primaryDescriptor.setOutputFormat(mutableMediaFormat);
    }

    public void setPluginClass(Class<?> cls) {
        this.primaryDescriptor.setPluginClass(cls);
    }

    public void setPluginClassName(String str) {
        this.primaryDescriptor.setPluginClassName(str);
    }

    public void setPluginId(Enum<?> enumR) {
        this.primaryDescriptor.setPluginId(enumR);
    }

    public void setSubDescriptor(PluginDescriptor pluginDescriptor) {
        this.subDescriptor = pluginDescriptor;
    }

    public void setTargetFormat(MutableMediaFormat mutableMediaFormat) {
        this.primaryDescriptor.setTargetFormat(mutableMediaFormat);
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.primaryDescriptor, i2);
        parcel.writeParcelable(this.subDescriptor, i2);
        super.writeToParcel(parcel, i2);
    }

    public <V> V getExtra(String str, V v) {
        return Optional.ofNullable(this.primaryDescriptor.getExtra(str)).orElseGet(new C0544a(this, str, v));
    }

    public PluginDescriptorPair(PluginDescriptor pluginDescriptor, PluginDescriptor pluginDescriptor2) {
        this.primaryDescriptor = pluginDescriptor;
        this.subDescriptor = pluginDescriptor2;
    }

    public PluginDescriptorPair(Parcel parcel) {
        super(parcel);
        Class<PluginDescriptor> cls = PluginDescriptor.class;
        this.primaryDescriptor = (PluginDescriptor) parcel.readParcelable(cls.getClassLoader(), PluginDescriptor.class);
        this.subDescriptor = (PluginDescriptor) parcel.readParcelable(cls.getClassLoader(), PluginDescriptor.class);
    }
}
