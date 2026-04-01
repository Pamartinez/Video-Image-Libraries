package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.emoji2.text.flatbuffer.MetadataItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TypefaceEmojiRasterizer {
    private static final ThreadLocal<MetadataItem> sMetadataItem = new ThreadLocal<>();
    private volatile int mCache = 0;
    private final int mIndex;
    private final MetadataRepo mMetadataRepo;

    public TypefaceEmojiRasterizer(MetadataRepo metadataRepo, int i2) {
        this.mMetadataRepo = metadataRepo;
        this.mIndex = i2;
    }

    private MetadataItem getMetadataItem() {
        ThreadLocal<MetadataItem> threadLocal = sMetadataItem;
        MetadataItem metadataItem = threadLocal.get();
        if (metadataItem == null) {
            metadataItem = new MetadataItem();
            threadLocal.set(metadataItem);
        }
        this.mMetadataRepo.getMetadataList().list(metadataItem, this.mIndex);
        return metadataItem;
    }

    public void draw(Canvas canvas, float f, float f5, Paint paint) {
        Typeface typeface = this.mMetadataRepo.getTypeface();
        Typeface typeface2 = paint.getTypeface();
        paint.setTypeface(typeface);
        Canvas canvas2 = canvas;
        Paint paint2 = paint;
        canvas2.drawText(this.mMetadataRepo.getEmojiCharArray(), this.mIndex * 2, 2, f, f5, paint2);
        paint2.setTypeface(typeface2);
    }

    public int getCodepointAt(int i2) {
        return getMetadataItem().codepoints(i2);
    }

    public int getCodepointsLength() {
        return getMetadataItem().codepointsLength();
    }

    public int getHasGlyph() {
        return this.mCache & 3;
    }

    public int getHeight() {
        return getMetadataItem().height();
    }

    public int getId() {
        return getMetadataItem().id();
    }

    public short getSdkAdded() {
        return getMetadataItem().sdkAdded();
    }

    public int getWidth() {
        return getMetadataItem().width();
    }

    public boolean isDefaultEmoji() {
        return getMetadataItem().emojiStyle();
    }

    public boolean isPreferredSystemRender() {
        if ((this.mCache & 4) > 0) {
            return true;
        }
        return false;
    }

    public void setExclusion(boolean z) {
        int hasGlyph = getHasGlyph();
        if (z) {
            this.mCache = hasGlyph | 4;
        } else {
            this.mCache = hasGlyph;
        }
    }

    public void setHasGlyph(boolean z) {
        int i2;
        int i7 = this.mCache & 4;
        if (z) {
            i2 = i7 | 2;
        } else {
            i2 = i7 | 1;
        }
        this.mCache = i2;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append(", id:");
        sb2.append(Integer.toHexString(getId()));
        sb2.append(", codepoints:");
        int codepointsLength = getCodepointsLength();
        for (int i2 = 0; i2 < codepointsLength; i2++) {
            sb2.append(Integer.toHexString(getCodepointAt(i2)));
            sb2.append(" ");
        }
        return sb2.toString();
    }
}
