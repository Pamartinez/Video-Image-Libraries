package androidx.media3.datasource;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseDataSource implements DataSource {
    private DataSpec dataSpec;
    private final boolean isNetwork;
    private int listenerCount;
    private final ArrayList<TransferListener> listeners = new ArrayList<>(1);

    public BaseDataSource(boolean z) {
        this.isNetwork = z;
    }

    public final void addTransferListener(TransferListener transferListener) {
        Assertions.checkNotNull(transferListener);
        if (!this.listeners.contains(transferListener)) {
            this.listeners.add(transferListener);
            this.listenerCount++;
        }
    }

    public final void bytesTransferred(int i2) {
        DataSpec dataSpec2 = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i7 = 0; i7 < this.listenerCount; i7++) {
            this.listeners.get(i7).onBytesTransferred(this, dataSpec2, this.isNetwork, i2);
        }
    }

    public final void transferEnded() {
        DataSpec dataSpec2 = (DataSpec) Util.castNonNull(this.dataSpec);
        for (int i2 = 0; i2 < this.listenerCount; i2++) {
            this.listeners.get(i2).onTransferEnd(this, dataSpec2, this.isNetwork);
        }
        this.dataSpec = null;
    }

    public final void transferInitializing(DataSpec dataSpec2) {
        for (int i2 = 0; i2 < this.listenerCount; i2++) {
            this.listeners.get(i2).onTransferInitializing(this, dataSpec2, this.isNetwork);
        }
    }

    public final void transferStarted(DataSpec dataSpec2) {
        this.dataSpec = dataSpec2;
        for (int i2 = 0; i2 < this.listenerCount; i2++) {
            this.listeners.get(i2).onTransferStart(this, dataSpec2, this.isNetwork);
        }
    }
}
