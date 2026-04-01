package com.samsung.android.motionphoto.utils.v2.video;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import te.C1295a;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/samsung/android/motionphoto/utils/v2/video/ExportEvent;", "", "<init>", "(Ljava/lang/String;I)V", "ANALYZE", "ANALYZE_DONE", "PREPARE", "PREPARE_DONE", "EXECUTE", "EXECUTE_COMPLETE", "EXECUTE_FAIL", "EXECUTE_CANCEL", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ExportEvent {
    ANALYZE,
    ANALYZE_DONE,
    PREPARE,
    PREPARE_DONE,
    EXECUTE,
    EXECUTE_COMPLETE,
    EXECUTE_FAIL,
    EXECUTE_CANCEL;

    static {
        ExportEvent[] $values;
        $ENTRIES = c.t($values);
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }
}
