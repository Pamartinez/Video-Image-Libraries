package com.samsung.android.gallery.module.thumbnail;

import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {
    public final boolean test(Object obj) {
        return ((ReqInfo) obj).mInterruptChecker.isInterrupted();
    }
}
