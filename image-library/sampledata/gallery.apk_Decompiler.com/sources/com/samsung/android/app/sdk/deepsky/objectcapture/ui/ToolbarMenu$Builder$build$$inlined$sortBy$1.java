package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import a.C0068a;
import java.util.Comparator;
import kotlin.Metadata;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ToolbarMenu$Builder$build$$inlined$sortBy$1<T> implements Comparator {
    public final int compare(T t, T t3) {
        return C0068a.l(Integer.valueOf(((ToolbarMenuItem) t).getOrder()), Integer.valueOf(((ToolbarMenuItem) t3).getOrder()));
    }
}
