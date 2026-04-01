package com.samsung.android.app.sdk.deepsky.objectcapture.video;

import android.graphics.Bitmap;
import android.graphics.PointF;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/objectcapture/video/VideoData;", "", "bitmap", "Landroid/graphics/Bitmap;", "point", "Landroid/graphics/PointF;", "<init>", "(Landroid/graphics/Bitmap;Landroid/graphics/PointF;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getPoint", "()Landroid/graphics/PointF;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "deepsky-sdk-objectcapture-8.5.9_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VideoData {
    private final Bitmap bitmap;
    private final PointF point;

    public VideoData(Bitmap bitmap2, PointF pointF) {
        j.e(bitmap2, "bitmap");
        j.e(pointF, "point");
        this.bitmap = bitmap2;
        this.point = pointF;
    }

    public static /* synthetic */ VideoData copy$default(VideoData videoData, Bitmap bitmap2, PointF pointF, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bitmap2 = videoData.bitmap;
        }
        if ((i2 & 2) != 0) {
            pointF = videoData.point;
        }
        return videoData.copy(bitmap2, pointF);
    }

    public final Bitmap component1() {
        return this.bitmap;
    }

    public final PointF component2() {
        return this.point;
    }

    public final VideoData copy(Bitmap bitmap2, PointF pointF) {
        j.e(bitmap2, "bitmap");
        j.e(pointF, "point");
        return new VideoData(bitmap2, pointF);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoData)) {
            return false;
        }
        VideoData videoData = (VideoData) obj;
        if (j.a(this.bitmap, videoData.bitmap) && j.a(this.point, videoData.point)) {
            return true;
        }
        return false;
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final PointF getPoint() {
        return this.point;
    }

    public int hashCode() {
        return this.point.hashCode() + (this.bitmap.hashCode() * 31);
    }

    public String toString() {
        Bitmap bitmap2 = this.bitmap;
        PointF pointF = this.point;
        return "VideoData(bitmap=" + bitmap2 + ", point=" + pointF + ")";
    }
}
