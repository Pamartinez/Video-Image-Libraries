package com.samsung.android.gallery.module.story.transcode.decoder.video;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.util.ThumbProvider;
import g6.g;
import ic.l;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageDecoder extends Decoder {
    private ImageDecoder mEffectDecoder;
    private final ImageRender mImageRender;
    private final ThumbProvider mThumbProvider;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder implements Cloneable {
        Bitmap bitmap;
        String filePath;
        int id = -1;
        boolean immutable;
        int orientation;
        int outputHeight;
        int outputWidth;
        ThumbProvider thumbProvider;
        Uri uri;
        boolean useBlurBackground;
        boolean withAiEffect;

        public Decoder build() {
            return new ImageDecoder(this, 0);
        }

        public Builder setBitmap(Bitmap bitmap2) {
            this.bitmap = bitmap2;
            return this;
        }

        public Builder setData(String str, Uri uri2) {
            this.filePath = str;
            this.uri = uri2;
            return this;
        }

        public Builder setId(int i2) {
            this.id = i2;
            return this;
        }

        public Builder setImmutable(boolean z) {
            this.immutable = z;
            return this;
        }

        public Builder setOrientation(int i2) {
            this.orientation = i2;
            return this;
        }

        public Builder setSize(int i2, int i7) {
            this.outputWidth = i2;
            this.outputHeight = i7;
            return this;
        }

        public Builder setThumbnailProvider(ThumbProvider thumbProvider2) {
            this.thumbProvider = thumbProvider2;
            return this;
        }

        public Builder useBlurBackground(boolean z) {
            this.useBlurBackground = z;
            return this;
        }

        public Builder withAiEffect(boolean z) {
            this.withAiEffect = z;
            return this;
        }

        public Builder clone() {
            try {
                return (Builder) super.clone();
            } catch (CloneNotSupportedException unused) {
                return new Builder();
            }
        }
    }

    public /* synthetic */ ImageDecoder(Builder builder, int i2) {
        this(builder);
    }

    private Bitmap getBitmap(int i2) {
        Bitmap image = this.mThumbProvider.getImage(i2);
        if (image != null) {
            return image;
        }
        throw new FileNotFoundException(C0086a.i(i2, "fail to decode bitmap id="));
    }

    private void initEffect(Builder builder) {
        if (builder.withAiEffect) {
            this.mEffectDecoder = new ImageDecoder(builder.clone().setBitmap(this.mThumbProvider.getEffectImage(builder.id)).useBlurBackground(false).withAiEffect(false));
        }
    }

    private Bitmap setBitmap(Builder builder) {
        Bitmap bitmap = builder.bitmap;
        if (bitmap != null) {
            this.mImageRender.setBitmapWithRecycler(bitmap, new l(16));
            return bitmap;
        }
        Bitmap bitmap2 = getBitmap(builder.id);
        ImageRender imageRender = this.mImageRender;
        ThumbProvider thumbProvider = this.mThumbProvider;
        Objects.requireNonNull(thumbProvider);
        imageRender.setBitmapWithRecycler(bitmap2, new g(17, thumbProvider));
        return bitmap2;
    }

    private void setRect(Builder builder, Bitmap bitmap) {
        Rect[] displayRectWithSmartCrop = this.mThumbProvider.getDisplayRectWithSmartCrop(builder.id, new Rect(0, 0, builder.outputWidth, builder.outputHeight), bitmap.getWidth(), bitmap.getHeight());
        this.mImageRender.setMvpRatio(displayRectWithSmartCrop[0]);
        this.mImageRender.setCropRect(displayRectWithSmartCrop[1]);
    }

    public void release() {
        super.release();
        this.mImageRender.release();
        Optional.ofNullable(this.mEffectDecoder).ifPresent(new l(17));
    }

    public void renderInternal(FrameProperty frameProperty) {
        this.mImageRender.draw(frameProperty);
        Optional.ofNullable(this.mEffectDecoder).ifPresent(new g(16, frameProperty));
    }

    private ImageDecoder(Builder builder) {
        super(builder.id);
        ThumbProvider thumbProvider = builder.thumbProvider;
        this.mThumbProvider = thumbProvider;
        if (builder.useBlurBackground) {
            initBgImage(thumbProvider.getBlurImage(builder.id), builder.outputWidth, builder.outputHeight);
        }
        ImageRender imageRender = new ImageRender(builder.outputWidth, builder.outputHeight, builder.orientation);
        this.mImageRender = imageRender;
        setRect(builder, setBitmap(builder));
        setImmutable(builder.immutable);
        imageRender.initRender();
        initEffect(builder);
    }
}
