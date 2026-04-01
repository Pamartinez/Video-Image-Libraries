package com.samsung.android.vexfwk.depthmap;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/depthmap/DepthDataType;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "DEPTH_DATA_TYPE_BYTE", "DEPTH_DATA_TYPE_FLOAT", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DepthDataType {
    DEPTH_DATA_TYPE_BYTE(0),
    DEPTH_DATA_TYPE_FLOAT(1);
    
    public static final Companion Companion = null;
    private final int value;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/vexfwk/depthmap/DepthDataType$Companion;", "", "<init>", "()V", "fromInt", "Lcom/samsung/android/vexfwk/depthmap/DepthDataType;", "value", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final DepthDataType fromInt(int i2) {
            for (DepthDataType depthDataType : DepthDataType.getEntries()) {
                if (depthDataType.getValue() == i2) {
                    return depthDataType;
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }

        private Companion() {
        }
    }

    static {
        DepthDataType[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private DepthDataType(int i2) {
        this.value = i2;
    }

    public static final DepthDataType fromInt(int i2) {
        return Companion.fromInt(i2);
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
