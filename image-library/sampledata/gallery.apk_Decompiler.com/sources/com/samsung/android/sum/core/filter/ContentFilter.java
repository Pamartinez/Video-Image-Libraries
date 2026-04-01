package com.samsung.android.sum.core.filter;

import com.samsung.android.sdk.scs.ai.visual.c2pa.a;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.evaluate.Evaluator;
import com.samsung.android.sum.core.exception.ContentFilterOutException;
import com.samsung.android.sum.core.format.MediaFormat;
import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.functional.PlaceHolder;
import com.samsung.android.sum.core.types.DataType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentFilter extends DecorateFilter {
    private static final String TAG = Def.tagOf((Class<?>) ContentFilter.class);
    /* access modifiers changed from: private */
    public final Map<Integer, Object> filterMap = new HashMap();
    private final PlaceHolder<String> message = new PlaceHolder<String>() {
        private String buf;

        public boolean isEmpty() {
            if (this.buf == null) {
                return true;
            }
            return false;
        }

        public boolean isNotEmpty() {
            if (this.buf != null) {
                return true;
            }
            return false;
        }

        public void put(String str) {
            this.buf = str;
        }

        public String reset() {
            String str = this.buf;
            this.buf = null;
            return str;
        }
    };

    public ContentFilter(ContentFilterRegister contentFilterRegister, MediaFilter mediaFilter) {
        super(mediaFilter);
        contentFilterRegister.registerFilter(new ContentFilterRegistry() {
            public void addFilter(int i2, Object obj) {
                ContentFilter.this.filterMap.put(Integer.valueOf(i2), obj);
            }

            public <R> R getFilter(int i2) {
                return ContentFilter.this.filterMap.get(Integer.valueOf(i2));
            }
        });
    }

    private boolean evaluateDataType(Object obj, DataType dataType, PlaceHolder<String> placeHolder) {
        boolean z;
        if (obj instanceof DataType) {
            if (obj == dataType) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                placeHolder.put(getSuccessorTag() + dataType + " is not supported by filter: " + obj);
            }
            return z;
        } else if (obj instanceof DataType[]) {
            DataType[] dataTypeArr = (DataType[]) obj;
            boolean anyMatch = Arrays.stream(dataTypeArr).anyMatch(new a(0, dataType));
            if (anyMatch) {
                placeHolder.put(getSuccessorTag() + dataType + " is not supported by filter: " + ((String) Arrays.stream(dataTypeArr).map(new a(17)).collect(Collectors.joining())));
            }
            return anyMatch;
        } else if (obj instanceof List) {
            List list = (List) obj;
            boolean anyMatch2 = list.stream().anyMatch(new a(1, dataType));
            if (anyMatch2) {
                placeHolder.put(getSuccessorTag() + dataType + " is not supported by filter: " + ((String) list.stream().map(new a(17)).collect(Collectors.joining())));
            }
            return anyMatch2;
        } else {
            throw new IllegalArgumentException("invalid filter value: " + obj);
        }
    }

    private boolean evaluateDimension(Object obj, Shape shape, PlaceHolder<String> placeHolder) {
        Def.require(obj instanceof Evaluator);
        boolean evaluate = ((Evaluator) obj).evaluate(Integer.valueOf(shape.getDimension()));
        if (evaluate) {
            placeHolder.put(getSuccessorTag() + shape + " is not supported by filter: " + obj);
        }
        return evaluate;
    }

    private boolean evaluateMediaType(Object obj, String str, PlaceHolder<String> placeHolder) {
        return false;
    }

    private void filterOut(MediaFormat mediaFormat) {
        if (this.filterMap.entrySet().stream().anyMatch(new T3.a(5, this, mediaFormat))) {
            throw new ContentFilterOutException(this.message.reset());
        }
    }

    private String getSuccessorTag() {
        return "[" + getSuccessorFilter().getId() + "]";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$evaluateDataType$1(DataType dataType, DataType dataType2) {
        if (dataType2 == dataType) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$evaluateDataType$2(DataType dataType, DataType dataType2) {
        if (dataType2 == dataType) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$filterOut$0(MediaFormat mediaFormat, Map.Entry entry) {
        int intValue = ((Integer) entry.getKey()).intValue();
        if (intValue == 1) {
            return evaluateDimension(entry.getValue(), mediaFormat.getShape(), this.message);
        }
        if (intValue == 2) {
            return evaluateDataType(entry.getValue(), mediaFormat.getDataType(), this.message);
        }
        if (intValue == 3) {
            return evaluateMediaType(entry.getValue(), (String) mediaFormat.get("mime-type"), this.message);
        }
        throw new IllegalArgumentException("");
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        filterOut(mediaBuffer.getFormat());
        return super.run(mediaBuffer, mutableMediaBuffer);
    }
}
