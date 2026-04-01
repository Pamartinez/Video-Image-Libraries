package com.samsung.o3dp.jpeg3dcontainer.exception;

import com.samsung.o3dp.jpeg3dcontainer.util.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpfException extends RuntimeException {
    private static final String TAG = "Mpf";

    public MpfException(String str, Exception exc) {
        super(str, exc);
        LogUtil.e(TAG, str, exc);
    }

    public MpfException(String str) {
        super(str);
        LogUtil.e(TAG, str);
    }
}
