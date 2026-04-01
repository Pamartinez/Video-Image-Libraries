package com.samsung.android.sum.core.utils.dump;

import com.samsung.android.sum.core.SLog;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseDumper implements AutoCloseable {
    private static final String TAG = "BaseDumper";
    protected final List<ByteBuffer> buffers = new ArrayList();
    protected DumpConfig config;
    private AtomicBoolean isClosed = new AtomicBoolean(false);

    public BaseDumper(DumpConfig dumpConfig) {
        this.config = dumpConfig;
    }

    public void close() {
        flush();
        this.isClosed.set(true);
    }

    public abstract void dump(String str);

    public void finalize() {
        try {
            if (!this.isClosed.get()) {
                close();
            }
        } catch (Exception e) {
            SLog.w(TAG, "finalize: failed to close dumper " + e.getMessage());
        } finally {
            super.finalize();
        }
    }

    public void flush() {
        this.buffers.clear();
    }

    public void store(ByteBuffer byteBuffer) {
        if (this.config.containFlags(2)) {
            flush();
        }
        if (this.config.containFlags(4)) {
            this.buffers.add(byteBuffer);
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        allocate.put(byteBuffer);
        allocate.rewind();
        this.buffers.add(allocate);
    }
}
