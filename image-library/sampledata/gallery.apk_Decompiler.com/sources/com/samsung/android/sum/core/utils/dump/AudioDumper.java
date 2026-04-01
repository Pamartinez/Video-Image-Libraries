package com.samsung.android.sum.core.utils.dump;

import N2.j;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioDumper extends BaseDumper {
    private static final String TAG = Def.tagOf((Class<?>) AudioDumper.class);
    private long currentStoredBytes = 0;
    private long expectedSaveByteSize = -1;

    public AudioDumper(DumpConfig dumpConfig) {
        super(dumpConfig);
        if (dumpConfig.getDurationMs() != -1) {
            long durationMs = dumpConfig.getDurationMs();
            this.expectedSaveByteSize = ((long) dumpConfig.getChannelCount()) * ((durationMs * ((long) dumpConfig.getSampleRate())) / 1000) * ((long) (dumpConfig.getBitDepth() / 8));
        }
    }

    private void dumpInternal(String str, long j2) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(str);
            Iterator<ByteBuffer> it = this.buffers.iterator();
            long j3 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ByteBuffer next = it.next();
                if (j3 >= j2) {
                    break;
                }
                next.rewind();
                int min = (int) Math.min((long) next.remaining(), j2 - j3);
                next.limit(min);
                fileOutputStream.getChannel().write(next);
                j3 += (long) min;
            }
            if (j3 < j2) {
                long j8 = j2 - j3;
                byte[] bArr = new byte[((int) j8)];
                if (this.config.getPaddingValue() != 0) {
                    Arrays.fill(bArr, this.config.getPaddingValue());
                }
                fileOutputStream.write(bArr);
                String str2 = TAG;
                SLog.d(str2, "Added padding: " + j8 + " bytes");
            }
            String str3 = TAG;
            SLog.d(str3, "PCM saved: " + str + " (" + j2 + " bytes)");
            fileOutputStream.close();
            return;
        } catch (Exception e) {
            String str4 = TAG;
            StringBuilder k = j.k("Failed to save PCM: ", str, " - ");
            k.append(e.getMessage());
            SLog.e(str4, k.toString());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void dump(String str) {
        long j2 = this.currentStoredBytes;
        String str2 = TAG;
        SLog.i(str2, "stored: " + this.currentStoredBytes + ", expected: " + this.expectedSaveByteSize);
        long j3 = this.expectedSaveByteSize;
        if (j3 >= 0) {
            j2 = j3;
        }
        dumpInternal(str, j2);
    }

    public void store(ByteBuffer byteBuffer) {
        super.store(byteBuffer);
        byteBuffer.rewind();
        this.currentStoredBytes += (long) byteBuffer.remaining();
    }
}
