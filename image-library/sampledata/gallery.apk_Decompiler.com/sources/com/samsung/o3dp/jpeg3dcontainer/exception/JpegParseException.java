package com.samsung.o3dp.jpeg3dcontainer.exception;

import com.samsung.o3dp.jpeg3dcontainer.util.LogUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class JpegParseException extends Exception {
    private static final String TAG = "JpegParseException";

    public JpegParseException(String str) {
        super(str);
        LogUtil.e(TAG, str);
    }

    public JpegParseException(String str, Throwable th) {
        super(str, th);
    }
}
