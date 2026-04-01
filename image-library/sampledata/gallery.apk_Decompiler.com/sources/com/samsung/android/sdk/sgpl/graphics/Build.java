package com.samsung.android.sdk.sgpl.graphics;

import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Build {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SEM {
        static final int B = 170000;

        /* renamed from: P  reason: collision with root package name */
        static final int f1674P = 100000;
        static final int Q = 110000;
        static final int R = 120000;
        static final int S = 130000;
        static final int SDK_INT = getSdkInt();
        static final int T = 140000;
        static final int U = 150000;
        static final int V = 160000;

        public static int getSdkInt() {
            try {
                return Build.VERSION.SEM_PLATFORM_INT;
            } catch (Error | Exception unused) {
                return 0;
            }
        }
    }
}
