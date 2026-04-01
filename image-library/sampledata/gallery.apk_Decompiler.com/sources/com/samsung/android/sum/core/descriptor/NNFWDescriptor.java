package com.samsung.android.sum.core.descriptor;

import J5.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.filter.MediaFilter;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.types.HwUnit;
import com.samsung.android.sum.core.types.LoadType;
import com.samsung.android.sum.core.types.nn.Model;
import com.samsung.android.sum.core.types.nn.NNFW;
import com.samsung.android.sum.core.types.nn.NNFileDescriptor;
import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNFWDescriptor extends MFDescriptorBase implements Cloneable {
    public static final Parcelable.Creator<NNFWDescriptor> CREATOR = new Parcelable.Creator<NNFWDescriptor>() {
        public NNFWDescriptor createFromParcel(Parcel parcel) {
            return new NNFWDescriptor(parcel);
        }

        public NNFWDescriptor[] newArray(int i2) {
            return new NNFWDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) NNFWDescriptor.class);
    private final NNFW fw;
    private final HwUnit hw;
    private MutableMediaFormat inputFormat;
    private transient Supplier<LoadType> loadTypeSupplier;
    private Model model;
    private transient WeakReference<NNDescriptor> nnDescriptor;
    private NNFileDescriptor nnFileDescriptor;
    private MutableMediaFormat outputFormat;
    private transient MutableMediaFormat targetFormat;

    public NNFWDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        this.fw = mFDescriptorBuilder.nnFramework;
        this.hw = mFDescriptorBuilder.hardwareUnit;
        MFDescriptor mFDescriptor = mFDescriptorBuilder.successorDescriptor;
        if (mFDescriptor != null) {
            setNNDescriptor((NNDescriptor) mFDescriptor);
        }
        if (mFDescriptorBuilder.loadType != null) {
            this.loadTypeSupplier = new c(17, mFDescriptorBuilder);
        }
    }

    public String getFilterId() {
        return this.fw.name();
    }

    public MediaFilter.Option getFilterOption() {
        return (MediaFilter.Option) Optional.ofNullable(this.nnDescriptor.get()).map(new a(12)).orElse((Object) null);
    }

    public Class<?> getFilterType() {
        return NNFW.class;
    }

    public NNFW getFw() {
        return this.fw;
    }

    public HwUnit getHw() {
        return this.hw;
    }

    public MutableMediaFormat getInputFormat() {
        return this.inputFormat;
    }

    public LoadType getLoadType() {
        return this.loadTypeSupplier.get();
    }

    public NNDescriptor getNNDescriptor() {
        return this.nnDescriptor.get();
    }

    public NNFileDescriptor getNNFileDescriptor() {
        return this.nnFileDescriptor;
    }

    public NNFW getNNFramework() {
        return this.fw;
    }

    public MutableMediaFormat getOutputFormat() {
        return this.outputFormat;
    }

    public MutableMediaFormat getTargetFormat() {
        return this.targetFormat;
    }

    public boolean isInstant() {
        if (getLoadType() == LoadType.INSTANT) {
            return true;
        }
        return false;
    }

    public boolean isLazy() {
        if (getLoadType() == LoadType.LAZY) {
            return true;
        }
        return false;
    }

    public void setNNDescriptor(NNDescriptor nNDescriptor) {
        this.nnDescriptor = new WeakReference<>(nNDescriptor);
        this.model = (Model) nNDescriptor.getPluginId();
        this.inputFormat = nNDescriptor.getInputFormat();
        this.outputFormat = nNDescriptor.getOutputFormat();
        this.targetFormat = nNDescriptor.getTargetFormat();
        this.loadTypeSupplier = new c(18, nNDescriptor);
    }

    public void setNNFileDescriptor(NNFileDescriptor nNFileDescriptor) {
        this.nnFileDescriptor = nNFileDescriptor;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Def.tagOf((Object) this));
        sb2.append(Def.contentToString("model=" + ((String) Optional.ofNullable(this.nnFileDescriptor).map(new a(11)).orElse("n/a")), "[" + this.model.name() + "]", "fw=" + this.fw.name(), "hw=" + this.hw.name(), "input=" + this.inputFormat, "output=" + this.outputFormat));
        return sb2.toString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeSerializable(this.fw);
        parcel.writeSerializable(this.hw);
        parcel.writeParcelable(this.nnFileDescriptor, i2);
        parcel.writeSerializable(this.model);
        parcel.writeSerializable(this.inputFormat);
        parcel.writeSerializable(this.outputFormat);
    }

    public NNFWDescriptor clone() {
        try {
            return (NNFWDescriptor) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError("fail to clone NNFWDescriptor");
        }
    }

    public NNFWDescriptor(Parcel parcel) {
        super(parcel);
        this.fw = (NNFW) parcel.readSerializable(NNFW.class.getClassLoader(), NNFW.class);
        this.hw = (HwUnit) parcel.readSerializable(HwUnit.class.getClassLoader(), HwUnit.class);
        this.nnFileDescriptor = (NNFileDescriptor) parcel.readParcelable(NNFileDescriptor.class.getClassLoader(), NNFileDescriptor.class);
        this.model = (Model) parcel.readSerializable(Model.class.getClassLoader(), Model.class);
        Class<MutableMediaFormat> cls = MutableMediaFormat.class;
        this.inputFormat = (MutableMediaFormat) parcel.readSerializable(cls.getClassLoader(), MutableMediaFormat.class);
        this.outputFormat = (MutableMediaFormat) parcel.readSerializable(cls.getClassLoader(), MutableMediaFormat.class);
    }
}
