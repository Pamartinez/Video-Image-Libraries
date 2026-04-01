package com.samsung.android.vexfwk.docscan;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocDewarpModes;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocDewarpModes {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocDewarpModes$Companion;", "", "<init>", "()V", "fromIntToDewarpMode", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocDewarpModes$Companion$DewarpMode;", "value", "", "DewarpMode", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocDewarpModes$Companion$DewarpMode;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "DEWARP_MODE_AUTO", "DEWARP_MODE_CV", "DEWARP_MODE_AI", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum DewarpMode {
            DEWARP_MODE_AUTO(0),
            DEWARP_MODE_CV(1),
            DEWARP_MODE_AI(2);
            
            private final int value;

            static {
                DewarpMode[] $values;
                $ENTRIES = c.t($values);
            }

            private DewarpMode(int i2) {
                this.value = i2;
            }

            public static C1295a getEntries() {
                return $ENTRIES;
            }

            public final int getValue() {
                return this.value;
            }
        }

        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final DewarpMode fromIntToDewarpMode(int i2) {
            for (DewarpMode dewarpMode : DewarpMode.values()) {
                if (dewarpMode.getValue() == i2) {
                    return dewarpMode;
                }
            }
            return null;
        }

        private Companion() {
        }
    }
}
