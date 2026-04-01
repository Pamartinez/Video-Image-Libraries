package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.core.types.SplitType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OperatorWrapper extends OpPriorityComputable implements Operator {
    private static final String TAG = Def.tagOf((Class<?>) OperatorWrapper.class);
    private static final Map<ImgpType, MediaFormatUpdater> formatUpdaterMap = new HashMap<ImgpType, MediaFormatUpdater>() {
        {
            put(ImgpType.RESIZE, new k(0));
            put(ImgpType.CROP, new k(1));
            put(ImgpType.ROTATE, new k(2));
            put(ImgpType.CVT_COLOR, new k(3));
            put(ImgpType.CVT_DATA, new k(4));
            put(ImgpType.SPLIT, new k(5));
            put(ImgpType.MERGE, new k(6));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$2(MediaFormat mediaFormat, MutableMediaFormat mutableMediaFormat) {
            Integer num = (Integer) mediaFormat.get(Message.KEY_ROTATION);
            if (num == null) {
                return;
            }
            if (num.intValue() == 90 || num.intValue() == 270) {
                int cols = mutableMediaFormat.getCols();
                mutableMediaFormat.setCols(mutableMediaFormat.getRows());
                mutableMediaFormat.setRows(cols);
            }
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$3(MediaFormat mediaFormat, MutableMediaFormat mutableMediaFormat) {
            if (mediaFormat.getColorFormat().getChannels() != mutableMediaFormat.getColorFormat().getChannels()) {
                mutableMediaFormat.setDataType(DataType.of(mutableMediaFormat.getDataType().depth(), mediaFormat.getColorFormat().getChannels()));
            }
            mutableMediaFormat.setColorFormat(mediaFormat.getColorFormat());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$new$5(MediaFormat mediaFormat, MutableMediaFormat mutableMediaFormat) {
            if (((Integer) Optional.ofNullable(mediaFormat.getShape()).map(new f(7)).orElse(0)).intValue() != 0) {
                mutableMediaFormat.setRows(mediaFormat.getRows()).setCols(mediaFormat.getCols());
            }
            if (((SplitType) mediaFormat.get("split-type")) == SplitType.ALPHA) {
                mutableMediaFormat.setColorFormat(ColorFormat.GRAY);
            }
        }
    };
    protected MediaFormatUpdater formatUpdater;
    protected Operator operator;

    public OperatorWrapper(Enum<?> enumR, Operator operator2) {
        super(enumR);
        this.operator = operator2;
        this.formatUpdater = (MediaFormatUpdater) Optional.ofNullable(formatUpdaterMap.get(enumR)).orElseGet(new d(1));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ MediaFormatUpdater lambda$new$1() {
        return new k(7);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ OperatorWrapper lambda$of$2(Enum enumR, Operator operator2) {
        return new OperatorWrapper(enumR, operator2);
    }

    public static Operator of(Enum<?> enumR, Operator operator2) {
        if (operator2 instanceof OperatorChain) {
            return new OperatorChain(enumR, (List) ((OperatorChain) operator2).stream().map(new j(enumR, 1)).collect(Collectors.toList()));
        }
        return new OperatorWrapper(enumR, operator2);
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        return this.operator.run(mediaBuffer, mutableMediaBuffer);
    }

    public static Operator of(Map<Enum<?>, Operator> map) {
        return new OperatorMap((Map) map.entrySet().stream().collect(Collectors.toMap(new f(5), new f(6))));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(MediaFormat mediaFormat, MutableMediaFormat mutableMediaFormat) {
    }
}
