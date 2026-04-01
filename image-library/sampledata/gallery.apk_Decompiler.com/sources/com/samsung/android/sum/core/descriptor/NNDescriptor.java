package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.filter.NNFilter;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.format.MutableShape;
import com.samsung.android.sum.core.functional.ModelSelector;
import com.samsung.android.sum.core.types.HwUnit;
import com.samsung.android.sum.core.types.LoadType;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.nn.NNFW;
import com.samsung.android.sum.core.types.nn.NNFileDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNDescriptor extends PluginDescriptor {
    public static final Parcelable.Creator<NNDescriptor> CREATOR = new Parcelable.Creator<NNDescriptor>() {
        public NNDescriptor createFromParcel(Parcel parcel) {
            return new NNDescriptor(parcel);
        }

        public NNDescriptor[] newArray(int i2) {
            return new NNDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) NNDescriptor.class);
    private LoadType loadingType;
    private MediaType mediaType;
    private transient ModelSelector modelSelector;
    private transient List<NNFileDescriptor> nnFileDescriptor;
    private final List<NNFWProfile> nnfwProfiles;

    public NNDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        NNFW nnfw;
        int i2;
        ArrayList arrayList = new ArrayList();
        this.nnfwProfiles = arrayList;
        HwUnit hwUnit = mFDescriptorBuilder.hardwareUnit;
        if (!(hwUnit == null || (nnfw = mFDescriptorBuilder.nnFramework) == null || (i2 = mFDescriptorBuilder.numOfUnits) == 0)) {
            arrayList.add(new NNFWProfile(nnfw, hwUnit, i2));
        }
        Enum<?> enumR = mFDescriptorBuilder.identifier;
        Objects.requireNonNull(enumR);
        setPluginId(enumR);
    }

    public Class<?> getFilterType() {
        return NNFilter.class;
    }

    public LoadType getLoadingType() {
        return this.loadingType;
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public String getModelId() {
        return getFilterId();
    }

    public ModelSelector getModelSelector() {
        return this.modelSelector;
    }

    public List<NNFWProfile> getNNFWProfiles() {
        return this.nnfwProfiles;
    }

    public List<NNFileDescriptor> getNnFileDescriptors() {
        return this.nnFileDescriptor;
    }

    public void setModelSelector(ModelSelector modelSelector2) {
        this.modelSelector = modelSelector2;
    }

    public void setNNFileDescriptors(List<NNFileDescriptor> list) {
        this.nnFileDescriptor = list;
    }

    @Deprecated
    public void setOutputFormat(MutableMediaFormat mutableMediaFormat) {
        super.setOutputFormat(mutableMediaFormat);
        if (mutableMediaFormat.checkTypeOf("scale", Float.class)) {
            mutableMediaFormat.setShape(getInputFormat().getShape().toMutableShape().scale((Float) mutableMediaFormat.get("scale")));
        } else if (mutableMediaFormat.checkTypeOf("scale", Pair.class)) {
            Pair pair = (Pair) mutableMediaFormat.get("scale");
            MutableShape mutableShape = getInputFormat().getShape().toMutableShape();
            Objects.requireNonNull(pair);
            mutableMediaFormat.setShape(mutableShape.scale((Pair<Float, Float>) pair));
        }
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeSerializable(this.mediaType);
        parcel.writeSerializable(this.loadingType);
        parcel.writeList(this.nnfwProfiles);
        parcel.writeParcelableList(this.nnFileDescriptor, i2);
    }

    public NNDescriptor(Parcel parcel) {
        super(parcel);
        ArrayList arrayList = new ArrayList();
        this.nnfwProfiles = arrayList;
        this.mediaType = (MediaType) parcel.readSerializable(MediaType.class.getClassLoader(), MediaType.class);
        this.loadingType = (LoadType) parcel.readSerializable(LoadType.class.getClassLoader(), LoadType.class);
        List list = Collections.EMPTY_LIST;
        parcel.readList(arrayList, list.getClass().getClassLoader());
        parcel.readParcelableList(this.nnFileDescriptor, list.getClass().getClassLoader());
    }
}
