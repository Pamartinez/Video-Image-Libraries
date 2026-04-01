package androidx.media3.common.text;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Cue {
    @Deprecated
    public static final Cue EMPTY = new Builder().setText("").build();
    private static final String FIELD_BITMAP_BYTES = Util.intToStringMaxRadix(18);
    private static final String FIELD_BITMAP_HEIGHT = Util.intToStringMaxRadix(12);
    private static final String FIELD_BITMAP_PARCELABLE = Util.intToStringMaxRadix(3);
    private static final String FIELD_CUSTOM_SPANS = Util.intToStringMaxRadix(17);
    private static final String FIELD_LINE = Util.intToStringMaxRadix(4);
    private static final String FIELD_LINE_ANCHOR = Util.intToStringMaxRadix(6);
    private static final String FIELD_LINE_TYPE = Util.intToStringMaxRadix(5);
    private static final String FIELD_MULTI_ROW_ALIGNMENT = Util.intToStringMaxRadix(2);
    private static final String FIELD_POSITION = Util.intToStringMaxRadix(7);
    private static final String FIELD_POSITION_ANCHOR = Util.intToStringMaxRadix(8);
    private static final String FIELD_SHEAR_DEGREES = Util.intToStringMaxRadix(16);
    private static final String FIELD_SIZE = Util.intToStringMaxRadix(11);
    private static final String FIELD_TEXT = Util.intToStringMaxRadix(0);
    private static final String FIELD_TEXT_ALIGNMENT = Util.intToStringMaxRadix(1);
    private static final String FIELD_TEXT_SIZE = Util.intToStringMaxRadix(10);
    private static final String FIELD_TEXT_SIZE_TYPE = Util.intToStringMaxRadix(9);
    private static final String FIELD_VERTICAL_TYPE = Util.intToStringMaxRadix(15);
    private static final String FIELD_WINDOW_COLOR = Util.intToStringMaxRadix(13);
    private static final String FIELD_WINDOW_COLOR_SET = Util.intToStringMaxRadix(14);
    private static final String FIELD_Z_INDEX = Util.intToStringMaxRadix(19);
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final Layout.Alignment multiRowAlignment;
    public final float position;
    public final int positionAnchor;
    public final float shearDegrees;
    public final float size;
    public final CharSequence text;
    public final Layout.Alignment textAlignment;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final int windowColor;
    public final boolean windowColorSet;
    public final int zIndex;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private Bitmap bitmap;
        private float bitmapHeight;
        private float line;
        private int lineAnchor;
        private int lineType;
        private Layout.Alignment multiRowAlignment;
        private float position;
        private int positionAnchor;
        private float shearDegrees;
        private float size;
        private CharSequence text;
        private Layout.Alignment textAlignment;
        private float textSize;
        private int textSizeType;
        private int verticalType;
        private int windowColor;
        private boolean windowColorSet;
        private int zIndex;

        public Cue build() {
            CharSequence charSequence = this.text;
            Layout.Alignment alignment = this.textAlignment;
            Layout.Alignment alignment2 = this.multiRowAlignment;
            Bitmap bitmap2 = this.bitmap;
            float f = this.line;
            int i2 = this.lineType;
            int i7 = this.lineAnchor;
            float f5 = this.position;
            int i8 = this.positionAnchor;
            int i10 = this.textSizeType;
            float f8 = this.textSize;
            float f10 = this.size;
            float f11 = this.bitmapHeight;
            boolean z = this.windowColorSet;
            int i11 = this.windowColor;
            CharSequence charSequence2 = charSequence;
            int i12 = this.verticalType;
            CharSequence charSequence3 = charSequence2;
            return new Cue(charSequence3, alignment, alignment2, bitmap2, f, i2, i7, f5, i8, i10, f8, f10, f11, z, i11, i12, this.shearDegrees, this.zIndex);
        }

        public int getLineAnchor() {
            return this.lineAnchor;
        }

        public int getPositionAnchor() {
            return this.positionAnchor;
        }

        public CharSequence getText() {
            return this.text;
        }

        public Builder setBitmap(Bitmap bitmap2) {
            this.bitmap = bitmap2;
            this.text = null;
            return this;
        }

        public Builder setBitmapHeight(float f) {
            this.bitmapHeight = f;
            return this;
        }

        public Builder setLine(float f, int i2) {
            this.line = f;
            this.lineType = i2;
            return this;
        }

        public Builder setLineAnchor(int i2) {
            this.lineAnchor = i2;
            return this;
        }

        public Builder setMultiRowAlignment(Layout.Alignment alignment) {
            this.multiRowAlignment = alignment;
            return this;
        }

        public Builder setPosition(float f) {
            this.position = f;
            return this;
        }

        public Builder setPositionAnchor(int i2) {
            this.positionAnchor = i2;
            return this;
        }

        public Builder setShearDegrees(float f) {
            this.shearDegrees = f;
            return this;
        }

        public Builder setSize(float f) {
            this.size = f;
            return this;
        }

        public Builder setText(CharSequence charSequence) {
            this.text = charSequence;
            this.bitmap = null;
            return this;
        }

        public Builder setTextAlignment(Layout.Alignment alignment) {
            this.textAlignment = alignment;
            return this;
        }

        public Builder setTextSize(float f, int i2) {
            this.textSize = f;
            this.textSizeType = i2;
            return this;
        }

        public Builder setVerticalType(int i2) {
            this.verticalType = i2;
            return this;
        }

        public Builder setZIndex(int i2) {
            this.zIndex = i2;
            return this;
        }

        public Builder() {
            this.text = null;
            this.bitmap = null;
            this.textAlignment = null;
            this.multiRowAlignment = null;
            this.line = -3.4028235E38f;
            this.lineType = Integer.MIN_VALUE;
            this.lineAnchor = Integer.MIN_VALUE;
            this.position = -3.4028235E38f;
            this.positionAnchor = Integer.MIN_VALUE;
            this.textSizeType = Integer.MIN_VALUE;
            this.textSize = -3.4028235E38f;
            this.size = -3.4028235E38f;
            this.bitmapHeight = -3.4028235E38f;
            this.windowColorSet = false;
            this.windowColor = -16777216;
            this.verticalType = Integer.MIN_VALUE;
        }

        private Builder(Cue cue) {
            this.text = cue.text;
            this.bitmap = cue.bitmap;
            this.textAlignment = cue.textAlignment;
            this.multiRowAlignment = cue.multiRowAlignment;
            this.line = cue.line;
            this.lineType = cue.lineType;
            this.lineAnchor = cue.lineAnchor;
            this.position = cue.position;
            this.positionAnchor = cue.positionAnchor;
            this.textSizeType = cue.textSizeType;
            this.textSize = cue.textSize;
            this.size = cue.size;
            this.bitmapHeight = cue.bitmapHeight;
            this.windowColorSet = cue.windowColorSet;
            this.windowColor = cue.windowColor;
            this.verticalType = cue.verticalType;
            this.shearDegrees = cue.shearDegrees;
            this.zIndex = cue.zIndex;
        }
    }

    private Bundle toBundleWithoutBitmap() {
        Bundle bundle = new Bundle();
        CharSequence charSequence = this.text;
        if (charSequence != null) {
            bundle.putCharSequence(FIELD_TEXT, charSequence);
            CharSequence charSequence2 = this.text;
            if (charSequence2 instanceof Spanned) {
                ArrayList<Bundle> bundleCustomSpans = CustomSpanBundler.bundleCustomSpans((Spanned) charSequence2);
                if (!bundleCustomSpans.isEmpty()) {
                    bundle.putParcelableArrayList(FIELD_CUSTOM_SPANS, bundleCustomSpans);
                }
            }
        }
        bundle.putSerializable(FIELD_TEXT_ALIGNMENT, this.textAlignment);
        bundle.putSerializable(FIELD_MULTI_ROW_ALIGNMENT, this.multiRowAlignment);
        bundle.putFloat(FIELD_LINE, this.line);
        bundle.putInt(FIELD_LINE_TYPE, this.lineType);
        bundle.putInt(FIELD_LINE_ANCHOR, this.lineAnchor);
        bundle.putFloat(FIELD_POSITION, this.position);
        bundle.putInt(FIELD_POSITION_ANCHOR, this.positionAnchor);
        bundle.putInt(FIELD_TEXT_SIZE_TYPE, this.textSizeType);
        bundle.putFloat(FIELD_TEXT_SIZE, this.textSize);
        bundle.putFloat(FIELD_SIZE, this.size);
        bundle.putFloat(FIELD_BITMAP_HEIGHT, this.bitmapHeight);
        bundle.putBoolean(FIELD_WINDOW_COLOR_SET, this.windowColorSet);
        bundle.putInt(FIELD_WINDOW_COLOR, this.windowColor);
        bundle.putInt(FIELD_VERTICAL_TYPE, this.verticalType);
        bundle.putFloat(FIELD_SHEAR_DEGREES, this.shearDegrees);
        bundle.putInt(FIELD_Z_INDEX, this.zIndex);
        return bundle;
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        Bitmap bitmap2;
        Bitmap bitmap3;
        if (this == obj) {
            return true;
        }
        if (obj != null && Cue.class == obj.getClass()) {
            Cue cue = (Cue) obj;
            if (TextUtils.equals(this.text, cue.text) && this.textAlignment == cue.textAlignment && this.multiRowAlignment == cue.multiRowAlignment && ((bitmap2 = this.bitmap) != null ? !((bitmap3 = cue.bitmap) == null || !bitmap2.sameAs(bitmap3)) : cue.bitmap == null) && this.line == cue.line && this.lineType == cue.lineType && this.lineAnchor == cue.lineAnchor && this.position == cue.position && this.positionAnchor == cue.positionAnchor && this.size == cue.size && this.bitmapHeight == cue.bitmapHeight && this.windowColorSet == cue.windowColorSet && this.windowColor == cue.windowColor && this.textSizeType == cue.textSizeType && this.textSize == cue.textSize && this.verticalType == cue.verticalType && this.shearDegrees == cue.shearDegrees && this.zIndex == cue.zIndex) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        CharSequence charSequence = this.text;
        Layout.Alignment alignment = this.textAlignment;
        Layout.Alignment alignment2 = this.multiRowAlignment;
        Bitmap bitmap2 = this.bitmap;
        Float valueOf = Float.valueOf(this.line);
        Integer valueOf2 = Integer.valueOf(this.lineType);
        Integer valueOf3 = Integer.valueOf(this.lineAnchor);
        Float valueOf4 = Float.valueOf(this.position);
        Integer valueOf5 = Integer.valueOf(this.positionAnchor);
        Float valueOf6 = Float.valueOf(this.size);
        Float valueOf7 = Float.valueOf(this.bitmapHeight);
        Boolean valueOf8 = Boolean.valueOf(this.windowColorSet);
        Integer valueOf9 = Integer.valueOf(this.windowColor);
        Integer valueOf10 = Integer.valueOf(this.textSizeType);
        Float valueOf11 = Float.valueOf(this.textSize);
        CharSequence charSequence2 = charSequence;
        Integer valueOf12 = Integer.valueOf(this.verticalType);
        Integer num = valueOf12;
        return Objects.hash(new Object[]{charSequence2, alignment, alignment2, bitmap2, valueOf, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, valueOf9, valueOf10, valueOf11, num, Float.valueOf(this.shearDegrees), Integer.valueOf(this.zIndex)});
    }

    public Bundle toSerializableBundle() {
        Bundle bundleWithoutBitmap = toBundleWithoutBitmap();
        if (this.bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Assertions.checkState(this.bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream));
            bundleWithoutBitmap.putByteArray(FIELD_BITMAP_BYTES, byteArrayOutputStream.toByteArray());
        }
        return bundleWithoutBitmap;
    }

    private Cue(CharSequence charSequence, Layout.Alignment alignment, Layout.Alignment alignment2, Bitmap bitmap2, float f, int i2, int i7, float f5, int i8, int i10, float f8, float f10, float f11, boolean z, int i11, int i12, float f12, int i13) {
        if (charSequence == null) {
            Assertions.checkNotNull(bitmap2);
        } else {
            Assertions.checkArgument(bitmap2 == null);
        }
        if (charSequence instanceof Spanned) {
            this.text = SpannedString.valueOf(charSequence);
        } else if (charSequence != null) {
            this.text = charSequence.toString();
        } else {
            this.text = null;
        }
        this.textAlignment = alignment;
        this.multiRowAlignment = alignment2;
        this.bitmap = bitmap2;
        this.line = f;
        this.lineType = i2;
        this.lineAnchor = i7;
        this.position = f5;
        this.positionAnchor = i8;
        this.size = f10;
        this.bitmapHeight = f11;
        this.windowColorSet = z;
        this.windowColor = i11;
        this.textSizeType = i10;
        this.textSize = f8;
        this.verticalType = i12;
        this.shearDegrees = f12;
        this.zIndex = i13;
    }
}
