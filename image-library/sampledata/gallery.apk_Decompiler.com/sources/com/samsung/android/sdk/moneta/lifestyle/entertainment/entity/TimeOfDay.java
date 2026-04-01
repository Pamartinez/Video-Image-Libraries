package com.samsung.android.sdk.moneta.lifestyle.entertainment.entity;

import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import te.C1295a;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;", "", "value", "", "<init>", "(Ljava/lang/String;II)V", "getValue", "()I", "UNKNOWN", "MORNING", "AFTERNOON", "EVENING", "LATE_NIGHT", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum TimeOfDay {
    UNKNOWN(0),
    MORNING(1),
    AFTERNOON(2),
    EVENING(3),
    LATE_NIGHT(4);
    
    public static final Companion Companion = null;
    public static final String TAG = "TimeOfDay";
    private final int value;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay$Companion;", "", "<init>", "()V", "TAG", "", "fromValue", "Lcom/samsung/android/sdk/moneta/lifestyle/entertainment/entity/TimeOfDay;", "value", "", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final TimeOfDay fromValue(int i2) {
            for (TimeOfDay timeOfDay : TimeOfDay.getEntries()) {
                if (timeOfDay.getValue() == i2) {
                    return timeOfDay;
                }
            }
            Logger logger = Logger.INSTANCE;
            logger.e$pde_sdk_1_0_40_release(TimeOfDay.TAG, "No matching TimeOfDay found for value " + i2 + ". > Return UNKNOWN instead.");
            return TimeOfDay.UNKNOWN;
        }

        private Companion() {
        }
    }

    static {
        TimeOfDay[] $values;
        $ENTRIES = c.t($values);
        Companion = new Companion((e) null);
    }

    private TimeOfDay(int i2) {
        this.value = i2;
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }

    public final int getValue() {
        return this.value;
    }
}
