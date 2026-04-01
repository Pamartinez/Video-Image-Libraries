package androidx.media3.exoplayer.source;

import android.util.SparseArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SpannedData<V> {
    private int memoizedReadIndex;
    private final Consumer<V> removeCallback;
    private final SparseArray<V> spans = new SparseArray<>();

    public SpannedData(Consumer<V> consumer) {
        this.removeCallback = consumer;
        this.memoizedReadIndex = -1;
    }

    public void appendSpan(int i2, V v) {
        boolean z;
        boolean z3 = false;
        if (this.memoizedReadIndex == -1) {
            if (this.spans.size() == 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            this.memoizedReadIndex = 0;
        }
        if (this.spans.size() > 0) {
            SparseArray<V> sparseArray = this.spans;
            int keyAt = sparseArray.keyAt(sparseArray.size() - 1);
            if (i2 >= keyAt) {
                z3 = true;
            }
            Assertions.checkArgument(z3);
            if (keyAt == i2) {
                Consumer<V> consumer = this.removeCallback;
                SparseArray<V> sparseArray2 = this.spans;
                consumer.accept(sparseArray2.valueAt(sparseArray2.size() - 1));
            }
        }
        this.spans.append(i2, v);
    }

    public void clear() {
        for (int i2 = 0; i2 < this.spans.size(); i2++) {
            this.removeCallback.accept(this.spans.valueAt(i2));
        }
        this.memoizedReadIndex = -1;
        this.spans.clear();
    }

    public void discardFrom(int i2) {
        int i7;
        int size = this.spans.size() - 1;
        while (size >= 0 && i2 < this.spans.keyAt(size)) {
            this.removeCallback.accept(this.spans.valueAt(size));
            this.spans.removeAt(size);
            size--;
        }
        if (this.spans.size() > 0) {
            i7 = Math.min(this.memoizedReadIndex, this.spans.size() - 1);
        } else {
            i7 = -1;
        }
        this.memoizedReadIndex = i7;
    }

    public void discardTo(int i2) {
        int i7 = 0;
        while (i7 < this.spans.size() - 1) {
            int i8 = i7 + 1;
            if (i2 >= this.spans.keyAt(i8)) {
                this.removeCallback.accept(this.spans.valueAt(i7));
                this.spans.removeAt(i7);
                int i10 = this.memoizedReadIndex;
                if (i10 > 0) {
                    this.memoizedReadIndex = i10 - 1;
                }
                i7 = i8;
            } else {
                return;
            }
        }
    }

    public V get(int i2) {
        if (this.memoizedReadIndex == -1) {
            this.memoizedReadIndex = 0;
        }
        while (true) {
            int i7 = this.memoizedReadIndex;
            if (i7 > 0 && i2 < this.spans.keyAt(i7)) {
                this.memoizedReadIndex--;
            }
        }
        while (this.memoizedReadIndex < this.spans.size() - 1 && i2 >= this.spans.keyAt(this.memoizedReadIndex + 1)) {
            this.memoizedReadIndex++;
        }
        return this.spans.valueAt(this.memoizedReadIndex);
    }

    public V getEndValue() {
        SparseArray<V> sparseArray = this.spans;
        return sparseArray.valueAt(sparseArray.size() - 1);
    }

    public boolean isEmpty() {
        if (this.spans.size() == 0) {
            return true;
        }
        return false;
    }
}
