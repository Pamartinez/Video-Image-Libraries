package com.samsung.android.sum.core.descriptor;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.channel.BufferChannel;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.filter.collection.SequentialFilter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SequentialDescriptor extends MFDescriptorBase {
    public static final Parcelable.Creator<SequentialDescriptor> CREATOR = new Parcelable.Creator<SequentialDescriptor>() {
        public SequentialDescriptor createFromParcel(Parcel parcel) {
            return new SequentialDescriptor(parcel);
        }

        public SequentialDescriptor[] newArray(int i2) {
            return new SequentialDescriptor[i2];
        }
    };
    private static final String TAG = Def.tagOf((Class<?>) SequentialDescriptor.class);
    private List<MFDescriptor> descriptors = new ArrayList();
    private List<Evaluator> evaluators;
    private BufferChannel inputChannel;
    private BufferChannel outputChannel;
    private final SequentialFilter.Mode sequentialMode;
    private final SequentialFilter.Type sequentialType;

    public SequentialDescriptor(MFDescriptorBuilder mFDescriptorBuilder) {
        super(mFDescriptorBuilder);
        SequentialFilter.Type type = mFDescriptorBuilder.sequentialType;
        this.sequentialType = type;
        this.sequentialMode = mFDescriptorBuilder.sequentialMode;
        List<MFDescriptor> list = mFDescriptorBuilder.descriptors;
        if (list != null) {
            this.descriptors = list;
        }
        setFilterId(type.name());
    }

    public List<MFDescriptor> getDescriptors() {
        return this.descriptors;
    }

    public List<Evaluator> getEvaluators() {
        return this.evaluators;
    }

    public Class<?> getFilterType() {
        return SequentialFilter.class;
    }

    public BufferChannel getInputChannel() {
        return this.inputChannel;
    }

    public BufferChannel getOutputChannel() {
        return this.outputChannel;
    }

    public SequentialFilter.Mode getSequentialMode() {
        return this.sequentialMode;
    }

    public SequentialFilter.Type getSequentialType() {
        return this.sequentialType;
    }

    public void setInputChannel(BufferChannel bufferChannel) {
        this.inputChannel = bufferChannel;
    }

    public void setOutputChannel(BufferChannel bufferChannel) {
        this.outputChannel = bufferChannel;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeSerializable(this.sequentialType);
        parcel.writeSerializable(this.sequentialMode);
        parcel.writeInt(this.descriptors.size());
        for (MFDescriptor next : this.descriptors) {
            parcel.writeSerializable(next.getClass());
            parcel.writeParcelable(next, i2);
        }
    }

    public SequentialDescriptor(Parcel parcel) {
        super(parcel);
        this.sequentialType = (SequentialFilter.Type) parcel.readSerializable(SequentialFilter.Type.class.getClassLoader(), SequentialFilter.Type.class);
        this.sequentialMode = (SequentialFilter.Mode) parcel.readSerializable(SequentialFilter.Mode.class.getClassLoader(), SequentialFilter.Mode.class);
        int readInt = parcel.readInt();
        for (int i2 = 0; i2 < readInt; i2++) {
            this.descriptors.add((MFDescriptor) parcel.readParcelable(((Class) parcel.readSerializable()).getClassLoader()));
        }
    }
}
