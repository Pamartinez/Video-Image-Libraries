package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.ImgpDescriptor;
import com.samsung.android.sum.core.types.ImgpType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OperatorChain extends OpPriorityComputable implements Operator {
    private static final String TAG = Def.tagOf((Class<?>) OperatorChain.class);
    List<Operator> operators;
    private boolean usePersistentFormat;

    public OperatorChain(Operator... operatorArr) {
        this((List<Operator>) Arrays.asList(operatorArr));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Stream lambda$new$0(Operator operator) {
        if (operator instanceof OperatorChain) {
            return ((OperatorChain) operator).stream();
        }
        return Stream.of(operator);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Stream lambda$new$1(Operator operator) {
        if (operator instanceof OperatorChain) {
            return ((OperatorChain) operator).stream();
        }
        return Stream.of(operator);
    }

    public OperatorChain addOperator(Operator operator) {
        this.operators.add(operator);
        return this;
    }

    public void config(ImgpDescriptor imgpDescriptor) {
        this.usePersistentFormat = imgpDescriptor.isUsePersistentFormat();
    }

    public OperatorChain removeOperator(Operator operator) {
        this.operators.remove(operator);
        return this;
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        for (Operator next : this.operators) {
            try {
                return next.run(mediaBuffer, mutableMediaBuffer);
            } catch (UnsupportedOperationException unused) {
                if (this.usePersistentFormat) {
                    this.operators.remove(next);
                }
                SLog.w(TAG, "not support, try to next image processor ");
            }
        }
        throw new UnsupportedOperationException("none of image processors to handle this");
    }

    public Stream<Operator> stream() {
        return this.operators.stream();
    }

    public OperatorChain(List<Operator> list) {
        super(ImgpType.ANY);
        this.usePersistentFormat = false;
        this.operators = (List) list.stream().flatMap(new f(1)).collect(Collectors.toList());
    }

    public OperatorChain(Enum<?> enumR, List<Operator> list) {
        super(enumR);
        this.usePersistentFormat = false;
        this.operators = (List) list.stream().flatMap(new f(0)).collect(Collectors.toList());
    }
}
