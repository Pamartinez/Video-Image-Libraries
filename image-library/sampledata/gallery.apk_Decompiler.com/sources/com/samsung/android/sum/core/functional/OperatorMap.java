package com.samsung.android.sum.core.functional;

import F9.d;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import com.samsung.android.sum.core.functional.OpPriorityComputable;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;
import com.samsung.android.sum.core.types.ImgpType;
import com.samsung.android.sum.core.types.SplitType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OperatorMap implements Operator {
    private static final String TAG = Def.tagOf((Class<?>) OperatorMap.class);
    private static final Map<ImgpType, OpPriorityComputable.ComputeBridge> priorityCheckMap = new HashMap<ImgpType, OpPriorityComputable.ComputeBridge>() {
        {
            put(ImgpType.RESIZE, new e(1));
            put(ImgpType.DECODE, new e(2));
            put(ImgpType.ENCODE, new e(3));
            put(ImgpType.CVT_COLOR, new e(4));
            put(ImgpType.CVT_DATA, new e(5));
            put(ImgpType.ROTATE, new e(6));
            put(ImgpType.CROP, new e(7));
            put(ImgpType.SPLIT, new e(8));
            put(ImgpType.MERGE, new e(9));
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ SplitType lambda$new$0(MediaFormat mediaFormat, String str) {
            return (SplitType) mediaFormat.get(str, SplitType.NONE);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$new$1(SplitType splitType) {
            if (splitType != SplitType.TILE) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$10(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if (mediaFormat.contains("merge-type")) {
                return 0.0f;
            }
            return -1.0f;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$2(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            boolean z;
            boolean allMatch = Arrays.stream(new String[]{"split-type", "merge-type"}).map(new j(mediaFormat, 0)).allMatch(new i(0));
            boolean z3 = false;
            if (mutableMediaFormat.size() != 0) {
                z = true;
            } else {
                z = false;
            }
            if (mediaFormat.size() != 0 && !mediaFormat.contains("keep-shape")) {
                z3 = true;
            }
            if (!allMatch || !z || !z3) {
                return -1.0f;
            }
            if (mutableMediaFormat.getCols() == mediaFormat.getCols() && mutableMediaFormat.getRows() == mediaFormat.getRows()) {
                return -1.0f;
            }
            return opPriorityCompute.compute(mutableMediaFormat.getShape(), mediaFormat.getShape());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$3(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if ((mutableMediaFormat.contains(Message.KEY_FILE_DESCRIPTOR) || mutableMediaFormat.contains(Message.KEY_IN_FILE)) && mediaFormat.getColorFormat() != ColorFormat.NONE) {
                return 0.0f;
            }
            return -1.0f;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$4(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if ((mediaFormat.contains(Message.KEY_FILE_DESCRIPTOR) || mediaFormat.contains(Message.KEY_OUT_FILE)) && mutableMediaFormat.getColorFormat() != ColorFormat.NONE) {
                return Float.MAX_VALUE;
            }
            return -1.0f;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$5(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if (mediaFormat.getColorFormat() == ColorFormat.NONE || mutableMediaFormat.getColorFormat() == mediaFormat.getColorFormat()) {
                return -1.0f;
            }
            return opPriorityCompute.compute(mutableMediaFormat.getColorFormat(), mediaFormat.getColorFormat());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$6(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if (mediaFormat.getDataType() == DataType.NONE || mutableMediaFormat.getDataType() == mediaFormat.getDataType()) {
                return -1.0f;
            }
            return opPriorityCompute.compute(mutableMediaFormat.getDataType(), mediaFormat.getDataType());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$7(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if (mutableMediaFormat.size() == 0 || !mediaFormat.contains(Message.KEY_ROTATION)) {
                return -1.0f;
            }
            return opPriorityCompute.compute(mutableMediaFormat.getShape(), mediaFormat.getShape());
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$8(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if (mediaFormat.containsAnyOf("crop", "center-crop")) {
                return Float.MIN_VALUE;
            }
            return -1.0f;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ float lambda$new$9(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat, OpPriorityCompute opPriorityCompute) {
            if (mediaFormat.contains("split-type")) {
                return Float.MAX_VALUE;
            }
            return -1.0f;
        }
    };
    private List<Operator> opList;
    private final Map<Enum<?>, Operator> operatorMap;
    private OpPriorityCompute priorityCompute;
    private boolean usePersistentFormat = false;

    public OperatorMap(Map<Enum<?>, Operator> map) {
        this.operatorMap = map;
    }

    public static List<ImgpType> inferOperations(MutableMediaFormat mutableMediaFormat, MediaFormat mediaFormat) {
        return (List) ((Map) priorityCheckMap.entrySet().stream().collect(Collectors.toMap(new f(4), new d(mutableMediaFormat, mediaFormat, new OpPriorityByDataSize(), 4)))).entrySet().stream().filter(new i(1)).sorted(Map.Entry.comparingByValue()).map(new f(4)).collect(Collectors.toList());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ OpPriorityComputable lambda$config$1(Operator operator) {
        return (OpPriorityComputable) operator;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$config$2(OpPriorityComputable opPriorityComputable) {
        opPriorityComputable.setComputeBridge(priorityCheckMap.get(opPriorityComputable.getType()), this.priorityCompute);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$inferOperations$4(Map.Entry entry) {
        if (((Float) entry.getValue()).floatValue() >= 0.0f) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$run$0(MutableMediaFormat mutableMediaFormat, MutableMediaBuffer mutableMediaBuffer, Map map, Operator operator) {
        float compute = ((OpPriorityComputable) operator).compute(mutableMediaFormat, mutableMediaBuffer.getFormat());
        if (compute != -1.0f) {
            map.put(Float.valueOf(compute), operator);
        }
    }

    public void config(ImgpDescriptor imgpDescriptor) {
        this.usePersistentFormat = imgpDescriptor.isUsePersistentFormat();
        this.priorityCompute = new OpPriorityByDataSize();
        this.operatorMap.values().stream().map(new f(3)).forEach(new h(this));
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        MutableMediaFormat mutableFormat = mediaBuffer.getFormat().toMutableFormat();
        if (!this.usePersistentFormat || this.opList == null) {
            HashMap hashMap = new HashMap();
            this.operatorMap.values().forEach(new g(mutableFormat, mutableMediaBuffer, hashMap, 0));
            this.opList = (List) hashMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).map(new f(2)).collect(Collectors.toList());
        }
        MutableMediaBuffer mutable = mediaBuffer.toMutable();
        for (Operator run : this.opList) {
            try {
                run.run((MediaBuffer) mutable, mutableMediaBuffer);
                MediaBuffer moveTo = mutableMediaBuffer.moveTo(mutable);
                if (!(moveTo == mediaBuffer || moveTo == mutableMediaBuffer.get())) {
                    moveTo.release();
                }
            } catch (UnsupportedOperationException unused) {
                String str = TAG;
                SLog.d(str, "restore format:\nformat=" + mutableMediaBuffer + "\nibuf=" + mediaBuffer);
            }
        }
        if (mutableMediaBuffer.isEmpty()) {
            mutableMediaBuffer.put((MediaBuffer) mutable);
        }
        return mutableMediaBuffer;
    }
}
