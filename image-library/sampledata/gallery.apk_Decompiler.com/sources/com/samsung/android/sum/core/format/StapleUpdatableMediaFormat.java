package com.samsung.android.sum.core.format;

import com.samsung.android.sum.core.buffer.u;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StapleUpdatableMediaFormat extends StapleMediaFormat implements UpdatableMediaFormat {
    protected int flags = 0;
    MutableMediaFormat source;
    BiConsumer<MediaFormat, MutableMediaFormat> updater;

    public StapleUpdatableMediaFormat(MediaFormat mediaFormat) {
        super(((StapleMediaFormat) mediaFormat).impl);
    }

    private int coerceAtMostCols(int i2, int i7) {
        return Math.min(i7 + i2, this.source.getCols()) - i2;
    }

    private int coerceAtMostRows(int i2, int i7) {
        return Math.min(i7 + i2, this.source.getRows()) - i2;
    }

    private int[] getCroppedRect() {
        return (int[]) Optional.ofNullable((int[]) get("crop")).map(new g(this, 0)).orElseGet(new h(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int[] lambda$getCroppedRect$0(int[] iArr) {
        int i2 = iArr[0];
        return new int[]{i2, iArr[1], coerceAtMostCols(i2, iArr[2]), coerceAtMostRows(iArr[1], iArr[3])};
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int[] lambda$getCroppedRect$1(int[] iArr) {
        return new int[]{Math.max(0, (this.source.getCols() - iArr[0]) >> 1), Math.max(0, (this.source.getRows() - iArr[1]) >> 1), Math.min(iArr[0], this.source.getCols()), Math.min(iArr[1], this.source.getRows())};
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int[] lambda$getCroppedRect$2() {
        return (int[]) Optional.ofNullable((int[]) get("center-crop")).map(new g(this, 1)).orElseThrow(new u(4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Shape lambda$getCroppedShape$3(int[] iArr) {
        return Shape.of(this.source.getBatch(), coerceAtMostRows(iArr[1], iArr[3]), coerceAtMostCols(iArr[0], iArr[2]), this.source.getChannels());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Shape lambda$getCroppedShape$4(int[] iArr) {
        return Shape.of(this.source.getBatch(), Math.min(iArr[1], this.source.getRows()), Math.min(iArr[0], this.source.getCols()), this.source.getChannels());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Shape lambda$getCroppedShape$5() {
        return (Shape) Optional.ofNullable((int[]) get("center-crop")).map(new g(this, 2)).orElseThrow(new u(4));
    }

    public <T> T get(String str) {
        if (str.equals("crop-rect") && !contains(str) && containsAnyOf("crop", "center-crop")) {
            this.impl.set("crop-rect", getCroppedRect());
        }
        return this.impl.get(str);
    }

    public Shape getCroppedShape() {
        return (Shape) Optional.ofNullable((int[]) get("crop")).map(new g(this, 3)).orElseGet(new h(this, 1));
    }

    public UpdatableMediaFormat set(String str) {
        ((StapleMutableMediaFormat) this.impl).attributes.put(str, (Object) null);
        return this;
    }

    public UpdatableMediaFormat setUpdater(BiConsumer<MediaFormat, MutableMediaFormat> biConsumer) {
        this.updater = biConsumer;
        return this;
    }

    public String toString() {
        return contentToString(this);
    }

    public MediaFormat update() {
        BiConsumer<MediaFormat, MutableMediaFormat> biConsumer = this.updater;
        if (biConsumer != null) {
            biConsumer.accept(this, this.source);
        }
        return this.source.toMediaFormat();
    }

    public UpdatableMediaFormat with(MediaFormat mediaFormat) {
        if (mediaFormat instanceof MutableMediaFormat) {
            this.source = (MutableMediaFormat) mediaFormat;
            return this;
        }
        this.source = mediaFormat.toMutableFormat();
        return this;
    }
}
