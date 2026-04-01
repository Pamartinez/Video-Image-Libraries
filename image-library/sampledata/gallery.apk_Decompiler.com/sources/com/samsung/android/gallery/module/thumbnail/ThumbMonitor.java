package com.samsung.android.gallery.module.thumbnail;

import N2.j;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.thumbnail.type.ReqInfo;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.utils.FileLogger;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.JpegParser;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PerformanceLog;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sum.core.Def;
import java.util.Arrays;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ThumbMonitor extends Handler {
    boolean mActive = true;
    private long mLastStartTime;
    private final String[] mLog = new String[4];
    private final String[] mPath = new String[4];
    private final long[] mStartTime = new long[4];

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Data {
        String log;
        String path;
        long time = System.currentTimeMillis();

        public Data(String str, String str2) {
            this.log = str;
            this.path = str2;
        }
    }

    public ThumbMonitor(Looper looper) {
        super(looper);
    }

    private void checkProgressive(int i2) {
        if (!TextUtils.isEmpty(this.mPath[i2])) {
            try {
                String[] strArr = this.mPath;
                String str = strArr[i2];
                strArr[i2] = "";
                if (FileUtils.isProgressiveCandidate(str) && JpegParser.isProgressive(str)) {
                    StringBuilder sb2 = new StringBuilder();
                    String[] strArr2 = this.mLog;
                    sb2.append(strArr2[i2]);
                    sb2.append(" isProgressiveJpeg");
                    strArr2[i2] = sb2.toString();
                }
            } catch (Error | Exception unused) {
            }
        }
    }

    private void dumpThread(boolean z) {
        for (Map.Entry next : Thread.getAllStackTraces().entrySet()) {
            StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) next.getValue();
            String name = ((Thread) next.getKey()).getName();
            if (name.startsWith("thumbThread") || name.startsWith("Cache")) {
                StringBuilder k = j.k("==== Thread : ", name, ", length = ");
                k.append(stackTraceElementArr.length);
                k.append("\n");
                k.append(ThreadUtil.getLogFromStack(0, 20, stackTraceElementArr));
                String sb2 = k.toString();
                Log.d("ThumbMonitor", sb2);
                if (z) {
                    FileLogger.add(sb2);
                }
            }
        }
    }

    private void loop() {
        long j2;
        if (!DeviceConfig.UNIT_TEST) {
            if (this.mActive) {
                j2 = 1000;
            } else {
                j2 = Def.SURFACE_CHANNEL_TIMEOUT_MILLIS;
            }
            sendEmptyMessageDelayed(1000, j2);
        }
    }

    public void handleMessage(Message message) {
        int i2 = message.arg1;
        int i7 = message.what;
        int i8 = 1000;
        if (i7 != 0) {
            boolean z = true;
            if (i7 == 1) {
                long[] jArr = this.mStartTime;
                if (jArr[i2] == this.mLastStartTime) {
                    this.mLastStartTime = 0;
                }
                jArr[i2] = 0;
                this.mLog[i2] = "";
                this.mPath[i2] = "";
                if (Arrays.stream(jArr).sum() == 0) {
                    removeMessages(1000);
                }
            } else if (i7 == 1000) {
                if (this.mActive) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long asLong = currentTimeMillis - Arrays.stream(this.mStartTime).max().getAsLong();
                    boolean isEnabled = PerformanceLog.isEnabled();
                    if (isEnabled) {
                        i8 = 500;
                    }
                    int i10 = (asLong > ((long) i8) ? 1 : (asLong == ((long) i8) ? 0 : -1));
                    if (i10 <= 0) {
                        z = false;
                    }
                    int i11 = 0;
                    while (true) {
                        if (i11 >= 3) {
                            break;
                        } else if (this.mStartTime[i11] == 0) {
                            z = false;
                            break;
                        } else {
                            i11++;
                        }
                    }
                    if (z) {
                        for (int i12 = 0; i12 < this.mLog.length; i12++) {
                            checkProgressive(i12);
                            String str = i12 + " thread. elapsedTime = " + (currentTimeMillis - this.mStartTime[i12]) + ArcCommonLog.TAG_COMMA + this.mLog[i12] + ArcCommonLog.TAG_COMMA + Logger.getEncodedString(this.mPath[i12]);
                            Log.e("ThumbMonitor", str);
                            if (isEnabled) {
                                FileLogger.add(str);
                            }
                        }
                        dumpThread(isEnabled);
                    }
                }
                loop();
            }
        } else {
            Data data = (Data) message.obj;
            this.mLog[i2] = data.log;
            this.mPath[i2] = data.path;
            long[] jArr2 = this.mStartTime;
            long j2 = data.time;
            jArr2[i2] = j2;
            this.mLastStartTime = j2;
            if (!hasMessages(1000)) {
                loop();
            }
        }
    }

    public void setActive(boolean z) {
        this.mActive = z;
    }

    public void start(int i2, ReqInfo reqInfo) {
        Message obtainMessage = obtainMessage(0);
        obtainMessage.arg1 = i2;
        obtainMessage.obj = new Data(reqInfo.toString(), reqInfo.path);
        sendMessage(obtainMessage);
    }

    public void stop(int i2) {
        Message obtainMessage = obtainMessage(1);
        obtainMessage.arg1 = i2;
        sendMessage(obtainMessage);
    }
}
