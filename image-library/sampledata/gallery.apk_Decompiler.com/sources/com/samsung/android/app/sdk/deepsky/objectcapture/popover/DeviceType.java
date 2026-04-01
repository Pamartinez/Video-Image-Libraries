package com.samsung.android.app.sdk.deepsky.objectcapture.popover;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.Metadata;
import te.C1295a;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/popover/DeviceType;", "", "<init>", "(Ljava/lang/String;I)V", "NORMAL_TYPE", "FOLD_BOOK_TYPE", "LARGE_SCREEN", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum DeviceType {
    NORMAL_TYPE,
    FOLD_BOOK_TYPE,
    LARGE_SCREEN;

    static {
        DeviceType[] $values;
        $ENTRIES = c.t($values);
    }

    public static C1295a getEntries() {
        return $ENTRIES;
    }
}
