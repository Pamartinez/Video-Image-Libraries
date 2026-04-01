package com.samsung.android.gallery.widget.livemotion;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.story.DurationHelper;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DurationMeasure implements IDuration {
    private int[] mBasisDuration;
    private int[] mContentDuration;
    private int mCount;
    private int mCurrentDuration;
    private int mTotalDuration;

    private float getRatio(float f, float[] fArr) {
        for (int length = fArr.length - 1; length >= 0; length--) {
            if (f > fArr[length]) {
                return ((float) (length + 1)) / ((float) (fArr.length + 1));
            }
        }
        return 0.0f;
    }

    public int getCurrentDuration() {
        return this.mCurrentDuration;
    }

    public int getDuration(ThumbnailInterface thumbnailInterface) {
        return DurationHelper.getItemDuration(thumbnailInterface);
    }

    public int getOffsetTime(int i2, float f, float[] fArr) {
        float f5;
        if (f > 0.99999f) {
            f5 = (float) i2;
        } else {
            f5 = getRatio(f, fArr) * ((float) i2);
        }
        return (((int) f5) / 1000) * 1000;
    }

    public int getTotalDuration() {
        return this.mTotalDuration;
    }

    public void measure(MediaData mediaData) {
        this.mTotalDuration = 0;
        int count = mediaData.getCount();
        this.mCount = count;
        this.mContentDuration = new int[count];
        this.mBasisDuration = new int[count];
        for (int i2 = 0; i2 < this.mCount; i2++) {
            MediaItem read = mediaData.read(i2);
            if (read != null) {
                this.mBasisDuration[i2] = this.mTotalDuration;
                this.mContentDuration[i2] = getDuration(read);
                this.mTotalDuration += this.mContentDuration[i2];
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r3 = r3 % r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(int r3, float r4, float[] r5) {
        /*
            r2 = this;
            int r0 = r2.mCount
            if (r0 != 0) goto L_0x0005
            goto L_0x0019
        L_0x0005:
            int r3 = r3 % r0
            int[] r0 = r2.mBasisDuration
            r0 = r0[r3]
            int[] r1 = r2.mContentDuration
            r3 = r1[r3]
            int r3 = r2.getOffsetTime(r3, r4, r5)
            int r3 = r3 + r0
            int r4 = r2.mCurrentDuration
            if (r4 == r3) goto L_0x0019
            r2.mCurrentDuration = r3
        L_0x0019:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.livemotion.DurationMeasure.update(int, float, float[]):void");
    }
}
