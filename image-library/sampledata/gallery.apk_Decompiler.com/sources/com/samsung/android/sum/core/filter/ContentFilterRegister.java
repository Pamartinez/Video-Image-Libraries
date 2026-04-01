package com.samsung.android.sum.core.filter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@FunctionalInterface
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ContentFilterRegister {
    public static final int FILTER_DATA_TYPE = 2;
    public static final int FILTER_DIMENSION = 1;
    public static final int FILTER_MEDIA_TYPE = 3;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface FilterType {
    }

    void registerFilter(ContentFilterRegistry contentFilterRegistry);
}
