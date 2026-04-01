package androidx.media3.common;

import android.util.SparseBooleanArray;
import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class FlagSet {
    private final SparseBooleanArray flags;

    public boolean contains(int i2) {
        return this.flags.get(i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlagSet)) {
            return false;
        }
        return this.flags.equals(((FlagSet) obj).flags);
    }

    public int get(int i2) {
        Assertions.checkIndex(i2, 0, size());
        return this.flags.keyAt(i2);
    }

    public int hashCode() {
        return this.flags.hashCode();
    }

    public int size() {
        return this.flags.size();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private boolean buildCalled;
        private final SparseBooleanArray flags = new SparseBooleanArray();

        public Builder add(int i2) {
            Assertions.checkState(!this.buildCalled);
            this.flags.append(i2, true);
            return this;
        }

        public Builder addAll(int... iArr) {
            for (int add : iArr) {
                add(add);
            }
            return this;
        }

        public Builder addIf(int i2, boolean z) {
            if (z) {
                return add(i2);
            }
            return this;
        }

        public FlagSet build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new FlagSet(this.flags);
        }

        public Builder addAll(FlagSet flagSet) {
            for (int i2 = 0; i2 < flagSet.size(); i2++) {
                add(flagSet.get(i2));
            }
            return this;
        }
    }

    private FlagSet(SparseBooleanArray sparseBooleanArray) {
        this.flags = sparseBooleanArray;
    }
}
