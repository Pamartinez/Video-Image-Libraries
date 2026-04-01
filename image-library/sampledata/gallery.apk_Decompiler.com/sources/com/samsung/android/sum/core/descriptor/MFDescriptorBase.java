package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.PadType;
import com.samsung.android.sum.core.types.SplitType;
import com.samsung.android.sum.core.types.Version;
import i.C0212a;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MFDescriptorBase implements MFDescriptor {
    protected static int PLUGIN_INPUT_FORMAT = 1004;
    private static final String TAG = Def.tagOf((Class<?>) MFDescriptorBase.class);
    protected Map<String, Object> extra = new HashMap();
    protected String filterId;
    protected Option filterOption = new Option();
    protected Class<?> filterType;
    protected Version filterVersion;
    protected int[] messageToReceive;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Option extends MediaFilter.Option {
        public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
            public Option createFromParcel(Parcel parcel) {
                return new Option(parcel);
            }

            public Option[] newArray(int i2) {
                return new Option[i2];
            }
        };

        public Option() {
        }

        public Map<Integer, Object> getAll() {
            return super.getAll();
        }

        public Option(Parcel parcel) {
            super(parcel);
        }
    }

    public MFDescriptorBase() {
    }

    public void copyTo(MFDescriptorBase mFDescriptorBase) {
        this.filterOption.copyTo(mFDescriptorBase.filterOption);
        mFDescriptorBase.filterId = this.filterId;
        mFDescriptorBase.filterType = this.filterType;
    }

    public int describeContents() {
        return 0;
    }

    public <T> T getExtra(String str) {
        return this.extra.get(str);
    }

    public String getFilterId() {
        Class<?> cls;
        String str = TAG;
        SLog.d(str, "getFilterId: id= " + this.filterId + ". type=" + this.filterType);
        if (this.filterId == null && (cls = this.filterType) != null) {
            this.filterId = cls.getName();
        }
        return this.filterId;
    }

    public MediaFilter.Option getFilterOption() {
        return this.filterOption;
    }

    public Class<?> getFilterType() {
        Class<?> filterTypeFromId;
        Class<?> cls;
        String str = TAG;
        SLog.d(str, "getFilterType[" + this + "]: type=" + this.filterType + ", id=" + this.filterId);
        if (this.filterType == null) {
            Class<MediaFilter> cls2 = MediaFilter.class;
            if (this.filterId != null) {
                Class<?> filterTypeFromId2 = getFilterTypeFromId();
                if (filterTypeFromId2 == null) {
                    this.filterType = cls2;
                } else {
                    this.filterType = filterTypeFromId2;
                }
            } else {
                this.filterType = cls2;
            }
        } else if (!(this.filterId == null || (filterTypeFromId = getFilterTypeFromId()) == null || filterTypeFromId == (cls = this.filterType) || !cls.isAssignableFrom(filterTypeFromId))) {
            SLog.d(str, "type-id is derivative class of type, so update type by id");
            this.filterType = filterTypeFromId;
        }
        return this.filterType;
    }

    public Class<?> getFilterTypeFromId() {
        String str = TAG;
        SLog.d(str, "getFilterTypeFromId: " + this.filterId);
        try {
            String str2 = (String) Optional.of(this.filterId).flatMap(new a(9)).orElse((Object) null);
            if (str2 != null) {
                return Class.forName(str2);
            }
            return null;
        } catch (Exception unused) {
            String str3 = TAG;
            SLog.d(str3, "fail to get filter-type of " + this.filterId);
            return null;
        }
    }

    public int[] getMessageToReceive() {
        return (int[]) this.extra.get("message-to-receive");
    }

    public List<MediaType> getPriority() {
        return this.filterOption.getPriority();
    }

    public SplitType getSplitType() {
        return this.filterOption.getSplitType();
    }

    public boolean isBatchIO() {
        return this.filterOption.isBatchIO();
    }

    public boolean isIgnorableFilter() {
        return this.filterOption.isIgnorableFilter();
    }

    public boolean isKeepFilterDatatype() {
        return this.filterOption.isKeepFilterDatatype();
    }

    public boolean isLatestPluginsOrder() {
        return ((Boolean) this.filterOption.get(2001, Boolean.FALSE)).booleanValue();
    }

    public boolean isUsePersistentFormat() {
        return this.filterOption.isUseExternalBufferComposer();
    }

    public void setAllowPartialConnection(boolean z) {
        this.filterOption.setAllowPartialConnection(z);
    }

    public void setBatchIO(boolean z) {
        this.filterOption.setBatchIO(z);
    }

    public <T> void setExtra(String str, T t) {
        this.extra.put(str, t);
    }

    public void setFilterId(String str) {
        this.filterId = str;
    }

    public void setFilterIgnorable(boolean z) {
        this.filterOption.setFilterIgnorable(z);
    }

    public void setFilterOption(MediaFilter.Option option) {
        option.copyTo(this.filterOption);
    }

    public void setFilterType(Class<?> cls) {
        this.filterType = cls;
    }

    public void setInputWithEvaluationValue(boolean z) {
        this.filterOption.setInputWithEvaluationValue(z);
    }

    public void setKeepFilterDatatype(boolean z) {
        this.filterOption.setKeepFilterDatatype(z);
    }

    public void setLatestPluginsOrder(boolean z) {
        this.filterOption.getAll().put(2001, Boolean.valueOf(z));
    }

    public void setPad(Pair<PadType, Integer> pair) {
        this.filterOption.setPad(pair);
    }

    public void setPriority(MediaType... mediaTypeArr) {
        this.filterOption.setPriority(Arrays.asList(mediaTypeArr));
    }

    public void setSplitType(SplitType splitType) {
        this.filterOption.setSplitType(splitType);
    }

    public void setUseExternalBufferComposer(boolean z) {
        this.filterOption.setUseExternalBufferComposer(z);
    }

    public void setUsePersistentFormat(boolean z) {
        this.filterOption.getAll().put(2000, Boolean.valueOf(z));
    }

    public void setWaitToReceiveAll(boolean z) {
        this.filterOption.setWaitToReceiveAll(z);
    }

    public String toString() {
        return C0212a.p(new StringBuilder("{"), Def.contentToString("filterId=" + this.filterId, "filterType=" + this.filterType, "filterOption=" + this.filterOption), "}");
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(getFilterId() + "@version=" + new Version(Def.getCoreVersion()));
        parcel.writeSerializable(getFilterType());
        parcel.writeParcelable(getFilterOption(), i2);
        parcel.writeMap(this.extra);
    }

    public <T> T getExtra(String str, T t) {
        return this.extra.getOrDefault(str, t);
    }

    public void setPriority(List<MediaType> list) {
        this.filterOption.setPriority(list);
    }

    public MFDescriptorBase(MFDescriptorBuilder mFDescriptorBuilder) {
        Class<?> cls = mFDescriptorBuilder.filterType;
        if (cls != null) {
            setFilterType(cls);
        }
        String str = mFDescriptorBuilder.filterId;
        if (str != null) {
            setFilterId(str);
        }
        setFilterOption(mFDescriptorBuilder.filterOption);
        Map<String, Object> map = (Map) Optional.ofNullable(mFDescriptorBuilder.extra).orElseGet(new u(0));
        this.extra = map;
        int[] iArr = mFDescriptorBuilder.messageToReceive;
        if (iArr != null) {
            map.put("message-to-receive", iArr);
        }
    }

    public MFDescriptorBase(Parcel parcel) {
        String[] split = parcel.readString().split("@version=");
        this.filterId = split[0];
        if (split.length > 1) {
            this.filterVersion = new Version(split[1]);
            String str = TAG;
            SLog.v(str, "filterVersion=" + this.filterVersion);
        }
        this.filterType = (Class) parcel.readSerializable();
        this.filterOption = (Option) parcel.readParcelable(Option.class.getClassLoader(), Option.class);
        Version version = this.filterVersion;
        if (version == null || !version.isGreaterOrEqual(Version.of("3.4.3"))) {
            this.extra = new HashMap();
            return;
        }
        HashMap hashMap = new HashMap();
        this.extra = hashMap;
        parcel.readMap(hashMap, HashMap.class.getClassLoader(), String.class, Object.class);
    }
}
