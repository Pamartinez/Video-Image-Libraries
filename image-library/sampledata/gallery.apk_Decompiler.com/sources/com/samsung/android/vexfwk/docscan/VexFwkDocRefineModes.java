package com.samsung.android.vexfwk.docscan;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes;", "", "<init>", "()V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkDocRefineModes {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001:\u0003\f\r\u000eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion;", "", "<init>", "()V", "fromIntToEnhancementMode", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$EnhancementMode;", "value", "", "fromIntToColorFilterType", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$ColorFilterType;", "fromIntToTaskType", "Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;", "EnhancementMode", "ColorFilterType", "TaskType", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$ColorFilterType;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "COLOR_FILTER_NONE", "COLOR_FILTER_LIGHT_AUTO", "COLOR_FILTER_GRAY", "COLOR_FILTER_BLACK_AND_WHITE", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum ColorFilterType {
            COLOR_FILTER_NONE(0),
            COLOR_FILTER_LIGHT_AUTO(1),
            COLOR_FILTER_GRAY(2),
            COLOR_FILTER_BLACK_AND_WHITE(3);
            
            private final int value;

            static {
                ColorFilterType[] $values;
                $ENTRIES = c.t($values);
            }

            private ColorFilterType(int i2) {
                this.value = i2;
            }

            public static C1295a getEntries() {
                return $ENTRIES;
            }

            public final int getValue() {
                return this.value;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$EnhancementMode;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "OFF", "ON", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum EnhancementMode {
            OFF(0),
            ON(1);
            
            private final int value;

            static {
                EnhancementMode[] $values;
                $ENTRIES = c.t($values);
            }

            private EnhancementMode(int i2) {
                this.value = i2;
            }

            public static C1295a getEntries() {
                return $ENTRIES;
            }

            public final int getValue() {
                return this.value;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/vexfwk/docscan/VexFwkDocRefineModes$Companion$TaskType;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "TASK_DEWARP_AI", "TASK_SHADOW_REMOVAL", "TASK_DEBLUR", "TASK_DEWARP_CV", "TASK_FILTER_GRAY", "TASK_FILTER_BW", "TASK_FILTER_DOCUMENT", "TASK_TEXT_REFLECTION", "TASK_FILTER_SCANNER_FILTER", "TASK_REMOVER_WRINKLE", "TASK_REMOVER_MOIRE", "TASK_REMOVER_FINGER_AND_DOGEAR", "TASK_REMOVER_FINGER", "TASK_REMOVER_DOGEAR", "TASK_DETECTOR_MOIRE", "TASK_DETECTOR_FINGER_DOGEAR", "TASK_DETECTOR_FINGER", "TASK_DETECTOR_DOGEAR", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum TaskType {
            TASK_DEWARP_AI(1),
            TASK_SHADOW_REMOVAL(2),
            TASK_DEBLUR(3),
            TASK_DEWARP_CV(4),
            TASK_FILTER_GRAY(5),
            TASK_FILTER_BW(6),
            TASK_FILTER_DOCUMENT(7),
            TASK_TEXT_REFLECTION(8),
            TASK_FILTER_SCANNER_FILTER(9),
            TASK_REMOVER_WRINKLE(10),
            TASK_REMOVER_MOIRE(11),
            TASK_REMOVER_FINGER_AND_DOGEAR(12),
            TASK_REMOVER_FINGER(13),
            TASK_REMOVER_DOGEAR(14),
            TASK_DETECTOR_MOIRE(15),
            TASK_DETECTOR_FINGER_DOGEAR(16),
            TASK_DETECTOR_FINGER(17),
            TASK_DETECTOR_DOGEAR(18);
            
            private final int value;

            static {
                TaskType[] $values;
                $ENTRIES = c.t($values);
            }

            private TaskType(int i2) {
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

        public final ColorFilterType fromIntToColorFilterType(int i2) {
            for (ColorFilterType colorFilterType : ColorFilterType.values()) {
                if (colorFilterType.getValue() == i2) {
                    return colorFilterType;
                }
            }
            return null;
        }

        public final EnhancementMode fromIntToEnhancementMode(int i2) {
            for (EnhancementMode enhancementMode : EnhancementMode.values()) {
                if (enhancementMode.getValue() == i2) {
                    return enhancementMode;
                }
            }
            return null;
        }

        public final TaskType fromIntToTaskType(int i2) {
            for (TaskType taskType : TaskType.values()) {
                if (taskType.getValue() == i2) {
                    return taskType;
                }
            }
            return null;
        }

        private Companion() {
        }
    }
}
