package com.samsung.android.sum.core.descriptor;

import A4.L;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.sum.core.filter.collection.ParallelFilter;
import com.samsung.android.sum.core.types.PadType;
import com.samsung.android.sum.core.types.SplitType;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParallelDescriptor extends MFDescriptorBase {
    public static final Parcelable.Creator<ParallelDescriptor> CREATOR = new Parcelable.Creator<ParallelDescriptor>() {
        public ParallelDescriptor createFromParcel(Parcel parcel) {
            return new ParallelDescriptor(parcel);
        }

        public ParallelDescriptor[] newArray(int i2) {
            return new ParallelDescriptor[i2];
        }
    };
    private List<MFDescriptor> descriptors;
    private final ParallelFilter.Type parallelType;

    public ParallelDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        ParallelFilter.Type type = mFDescriptorBuilder.parallelType;
        this.parallelType = type;
        this.descriptors = mFDescriptorBuilder.descriptors;
        setFilterId(type.name());
    }

    public List<MFDescriptor> getDescriptors() {
        return this.descriptors;
    }

    public Class<?> getFilterType() {
        return ParallelFilter.class;
    }

    public ParallelFilter.Type getParallelType() {
        return this.parallelType;
    }

    @Deprecated
    public void setAllowPartialConnection(boolean z) {
        super.setAllowPartialConnection(z);
        this.descriptors.forEach(new L(z, 23));
    }

    @Deprecated
    public void setPad(Pair<PadType, Integer> pair) {
        super.setPad(pair);
        this.descriptors.forEach(new b(0, pair));
    }

    @Deprecated
    public void setSplitType(SplitType splitType) {
        super.setSplitType(splitType);
        this.descriptors.forEach(new b(1, splitType));
    }

    @Deprecated
    public void setUseExternalBufferComposer(boolean z) {
        super.setUseExternalBufferComposer(z);
        this.descriptors.forEach(new L(z, 24));
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeSerializable(this.parallelType);
        parcel.writeParcelableList(this.descriptors, i2);
    }

    public ParallelDescriptor(Parcel parcel) {
        super(parcel);
        this.parallelType = (ParallelFilter.Type) parcel.readSerializable(ParallelFilter.Type.class.getClassLoader(), ParallelFilter.Type.class);
        parcel.readParcelableList(this.descriptors, List.class.getClassLoader());
    }
}
