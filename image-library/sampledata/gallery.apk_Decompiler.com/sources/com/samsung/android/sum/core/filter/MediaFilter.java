package com.samsung.android.sum.core.filter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.Tag;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.functional.Operator;
import com.samsung.android.sum.core.message.MessageConsumer;
import com.samsung.android.sum.core.message.MessageProducer;
import com.samsung.android.sum.core.types.MediaType;
import com.samsung.android.sum.core.types.OptionBase;
import com.samsung.android.sum.core.types.PadType;
import com.samsung.android.sum.core.types.SplitType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaFilter extends MessageConsumer, Operator {
    public static final int OPTION_ALLOW_PARTIAL_CONNECTION = 0;
    public static final int OPTION_AS_INPUT = 8;
    public static final int OPTION_AS_OUTPUT = 9;
    public static final int OPTION_BATCH_IO = 5;
    public static final int OPTION_EXTERNAL_BUFFER_COMPOSER = 3;
    public static final int OPTION_IGNORABLE_FILTER = 10;
    public static final int OPTION_INPUT_WITH_EVALUATION_VALUE = 7;
    public static final int OPTION_KEEP_FILTER_DATA_TYPE = 4;
    public static final int OPTION_PAD = 1;
    public static final int OPTION_PRIORITY = 12;
    public static final int OPTION_RUN_INSTANT = 11;
    public static final int OPTION_SPLIT_TYPE = 2;
    public static final int OPTION_TAG = 13;
    public static final int OPTION_WAIT_RECEIVE_ALL = 6;

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
        private static final String TAG = Def.tagOf((Class<?>) Option.class);

        public Option() {
        }

        public Option asInputOption() {
            remove(9);
            return set(8);
        }

        public Option asOutputOption() {
            remove(8);
            return set(9);
        }

        public Pair<PadType, Integer> getPad() {
            return (Pair) get(1);
        }

        public List<MediaType> getPriority() {
            return (List) get(12, new ArrayList());
        }

        public SplitType getSplitType() {
            return (SplitType) get(2);
        }

        public boolean isAllowPartialConnection() {
            return ((Boolean) get(0, Boolean.FALSE)).booleanValue();
        }

        public boolean isBatchIO() {
            return ((Boolean) get(5, Boolean.FALSE)).booleanValue();
        }

        public boolean isIgnorableFilter() {
            return ((Boolean) get(10, Boolean.FALSE)).booleanValue();
        }

        public boolean isInputOption() {
            return ((Boolean) get(8, Boolean.FALSE)).booleanValue();
        }

        public boolean isInputWithEvaluationValue() {
            return ((Boolean) get(7, Boolean.FALSE)).booleanValue();
        }

        public boolean isKeepFilterDatatype() {
            return ((Boolean) get(4, Boolean.FALSE)).booleanValue();
        }

        public boolean isOutputOption() {
            return ((Boolean) get(9, Boolean.FALSE)).booleanValue();
        }

        public boolean isRunInstant() {
            return ((Boolean) get(11, Boolean.FALSE)).booleanValue();
        }

        public boolean isUseExternalBufferComposer() {
            return ((Boolean) get(3, Boolean.FALSE)).booleanValue();
        }

        public boolean isWaitToReceiveAll() {
            return ((Boolean) get(6, Boolean.FALSE)).booleanValue();
        }

        public void setAllowPartialConnection(boolean z) {
            set(0, (Object) Boolean.valueOf(z));
        }

        public void setBatchIO(boolean z) {
            set(5, (Object) Boolean.valueOf(z));
        }

        public void setFilterIgnorable(boolean z) {
            set(10, (Object) Boolean.valueOf(z));
        }

        public void setInputWithEvaluationValue(boolean z) {
            set(7, (Object) Boolean.valueOf(z));
        }

        public void setKeepFilterDatatype(boolean z) {
            set(4, (Object) Boolean.valueOf(z));
        }

        public void setPad(Pair<PadType, Integer> pair) {
            set(1, (Object) pair);
        }

        public void setPriority(List<MediaType> list) {
            set(12, (Object) list);
        }

        public void setRunInstant(boolean z) {
            set(11, (Object) Boolean.valueOf(z));
        }

        public void setSplitType(SplitType splitType) {
            set(2, (Object) splitType);
        }

        public void setUseExternalBufferComposer(boolean z) {
            set(3, (Object) Boolean.valueOf(z));
        }

        public void setWaitToReceiveAll(boolean z) {
            set(6, (Object) Boolean.valueOf(z));
        }

        public Option(Parcel parcel) {
            super(parcel);
        }

        public Option set(int i2) {
            super.set(i2);
            return this;
        }

        public Option set(int i2, Object obj) {
            super.set(i2, obj);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface OptionType {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        NN,
        IMGP,
        CODEC,
        PLUGIN
    }

    void accept(MediaFilterRetriever mediaFilterRetriever, MediaFilter mediaFilter) {
        mediaFilterRetriever.retrieve(this, mediaFilter);
    }

    void addTag(Enum<?> enumR, String str);

    void addTag(String str);

    MFDescriptor getDescriptor();

    String getId() {
        MFDescriptor descriptor = getDescriptor();
        if (descriptor instanceof NNDescriptor) {
            return ((NNDescriptor) descriptor).getModelId();
        }
        return descriptor.getFilterId();
    }

    Tag getTag();

    Tag getTag(Enum<?> enumR);

    Stream<MediaFilter> stream();

    void pause() {
    }

    void prepare() {
    }

    void release() {
    }

    void resume() {
    }

    void setMessageProducer(MessageProducer messageProducer) {
    }
}
