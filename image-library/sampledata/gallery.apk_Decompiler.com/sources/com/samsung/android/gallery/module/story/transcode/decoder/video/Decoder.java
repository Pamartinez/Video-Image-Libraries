package com.samsung.android.gallery.module.story.transcode.decoder.video;

import android.graphics.Bitmap;
import c0.C0086a;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.renderer.constants.BlendOption;
import com.samsung.android.sdk.scs.base.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Decoder {
    protected String TAG = getClass().getSimpleName();
    private StaticImageRender mBgImageRender;
    private StaticImageRender mFgImageRender;
    protected final int mId;
    private boolean mImmutable;
    private final boolean mIsImage;

    public Decoder(int i2) {
        String str = this.TAG + Log.TAG_SEPARATOR + i2;
        this.TAG = str;
        this.mId = i2;
        this.mIsImage = str.contains("ImageDecoder");
        com.samsung.android.gallery.support.utils.Log.d(this.TAG, "create");
    }

    public void draw(FrameProperty frameProperty) {
        render(frameProperty);
    }

    public void initBgImage(Bitmap bitmap, int i2, int i7) {
        this.mBgImageRender = new StaticImageRender(bitmap, BlendOption.NORMAL, i2, i7);
    }

    public void initFgImage(Bitmap bitmap, int i2, int i7) {
        this.mFgImageRender = new StaticImageRender(bitmap, BlendOption.ADDITIVE, i2, i7);
    }

    public void release() {
        StaticImageRender staticImageRender = this.mBgImageRender;
        if (staticImageRender != null) {
            staticImageRender.release();
        }
        StaticImageRender staticImageRender2 = this.mFgImageRender;
        if (staticImageRender2 != null) {
            staticImageRender2.release();
        }
        com.samsung.android.gallery.support.utils.Log.d(this.TAG, "release");
    }

    public void render(FrameProperty frameProperty) {
        StaticImageRender staticImageRender = this.mBgImageRender;
        if (staticImageRender != null) {
            staticImageRender.draw(frameProperty);
        }
        renderInternal(frameProperty);
        StaticImageRender staticImageRender2 = this.mFgImageRender;
        if (staticImageRender2 != null) {
            staticImageRender2.draw(frameProperty);
        }
    }

    public abstract void renderInternal(FrameProperty frameProperty);

    public void setImmutable(boolean z) {
        this.mImmutable = z;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getClass().getSimpleName());
        sb2.append(Log.TAG_SEPARATOR);
        sb2.append(hashCode());
        sb2.append("{id=");
        return C0086a.l(sb2, this.mId, "}");
    }

    public void interrupt() {
    }

    public void preRelease() {
    }
}
