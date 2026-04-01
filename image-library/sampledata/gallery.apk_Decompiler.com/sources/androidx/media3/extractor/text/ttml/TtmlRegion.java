package androidx.media3.extractor.text.ttml;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TtmlRegion {
    public final float height;
    public final String id;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final float width;

    public TtmlRegion(String str) {
        this(str, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE);
    }

    public TtmlRegion(String str, float f, float f5, int i2, int i7, float f8, float f10, int i8, float f11, int i10) {
        this.id = str;
        this.position = f;
        this.line = f5;
        this.lineType = i2;
        this.lineAnchor = i7;
        this.width = f8;
        this.height = f10;
        this.textSizeType = i8;
        this.textSize = f11;
        this.verticalType = i10;
    }
}
