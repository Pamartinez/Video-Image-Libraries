package androidx.core.util;

import android.util.Log;
import java.io.Writer;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LogWriter extends Writer {
    private StringBuilder mBuilder = new StringBuilder(128);
    private final String mTag;

    public LogWriter(String str) {
        this.mTag = str;
    }

    private void flushBuilder() {
        if (this.mBuilder.length() > 0) {
            Log.d(this.mTag, this.mBuilder.toString());
            StringBuilder sb2 = this.mBuilder;
            sb2.delete(0, sb2.length());
        }
    }

    public void close() {
        flushBuilder();
    }

    public void flush() {
        flushBuilder();
    }

    public void write(char[] cArr, int i2, int i7) {
        for (int i8 = 0; i8 < i7; i8++) {
            char c5 = cArr[i2 + i8];
            if (c5 == 10) {
                flushBuilder();
            } else {
                this.mBuilder.append(c5);
            }
        }
    }
}
