package com.sec.sshutter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlowShutter {
    public static final int ERROR_INPUT_INVALID = -1;
    public static final int ERROR_INPUT_NOT_ENOUGH_FRAMES = -4;
    public static final int ERROR_INPUT_UNSUPPORTED_FORMAT = -3;
    public static final int ERROR_INPUT_UNSUPPORTED_RESOLUTION = -2;
    public static final int ERROR_INTERNAL = -20;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_OUTPUT_INVALID = -10;
    public static final int ERROR_UNKNOWN = -100;

    static {
        System.loadLibrary("SlowShutter_jni.media.samsung");
    }

    public static native int recommendSSFromJNI(String str, long[] jArr);

    public static native int releaseSSFromJNI();

    public static native int startSSFromJNI(String str, String str2, SlowShutterParameters slowShutterParameters, ISlowShutterCallback iSlowShutterCallback);

    public static native int startSSFromRequestedTime(String str, String str2, long j2, ISlowShutterCallback iSlowShutterCallback);

    public static native int stopSSFromJNI();

    public synchronized int cancel() {
        return stopSSFromJNI();
    }

    public synchronized SlowShutterRecommendInfo recommend(String str) {
        SlowShutterRecommendInfo slowShutterRecommendInfo;
        slowShutterRecommendInfo = new SlowShutterRecommendInfo();
        long[] jArr = new long[2];
        slowShutterRecommendInfo.mode = recommendSSFromJNI(str, jArr);
        slowShutterRecommendInfo.startTimeUs = jArr[0];
        slowShutterRecommendInfo.duration = jArr[1];
        return slowShutterRecommendInfo;
    }

    public synchronized int release() {
        return releaseSSFromJNI();
    }

    public synchronized int start(String str, String str2, SlowShutterParameters slowShutterParameters, ISlowShutterCallback iSlowShutterCallback) {
        return startSSFromJNI(str, str2, slowShutterParameters, iSlowShutterCallback);
    }

    public synchronized int start(String str, String str2, ISlowShutterCallback iSlowShutterCallback) {
        return startSSFromJNI(str, str2, (SlowShutterParameters) null, iSlowShutterCallback);
    }

    public synchronized int start(String str, String str2, long j2, ISlowShutterCallback iSlowShutterCallback) {
        return startSSFromRequestedTime(str, str2, j2, iSlowShutterCallback);
    }
}
