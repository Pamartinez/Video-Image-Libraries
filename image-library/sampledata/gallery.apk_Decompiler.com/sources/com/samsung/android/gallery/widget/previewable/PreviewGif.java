package com.samsung.android.gallery.widget.previewable;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.previewable.Previewable;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PreviewGif implements Previewable {
    protected final Animatable2.AnimationCallback mAnimCb = new Animatable2.AnimationCallback() {
        public void onAnimationEnd(Drawable drawable) {
            PreviewGif previewGif = PreviewGif.this;
            if (!previewGif.mForceStop) {
                previewGif.mPreviewDone = true;
                PreviewGif.this.stopPreview();
                PreviewGif.this.mListener.onPreviewEnd();
            }
        }
    };
    protected ImageView mAnimView;
    private final String mDataPath;
    protected Drawable mDrawable;
    protected boolean mForceStop;
    /* access modifiers changed from: private */
    public final Previewable.PreviewListener mListener;
    private boolean mLooping;
    /* access modifiers changed from: private */
    public boolean mPreviewDone;

    public PreviewGif(String str, Previewable.PreviewListener previewListener) {
        this.mDataPath = str;
        this.mListener = previewListener;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPreview$0(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        this.mAnimView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this.mAnimView, false)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startPreview$1(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
        Log.w("PreviewGif", "onHeaderDecoded info:" + imageInfo.getSize().toString());
        this.mListener.onPreviewReady();
    }

    public View createPreviewView(Context context, int i2) {
        ImageView imageView = new ImageView(context, (AttributeSet) null, 0, i2);
        this.mAnimView = imageView;
        return imageView;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        ImageView imageView = this.mAnimView;
        if (imageView != null) {
            imageView.setColorFilter(colorFilter);
        }
    }

    public void setLooping(boolean z) {
        this.mLooping = z;
    }

    public void startPreview() {
        ImageView imageView = this.mAnimView;
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new b(this));
            this.mAnimView.setScaleType(ImageView.ScaleType.MATRIX);
            try {
                startPreviewInternal(ImageDecoder.decodeDrawable(ImageDecoder.createSource(new File(this.mDataPath)), new c(this)));
            } catch (IOException e) {
                Log.w("PreviewGif", e.toString());
            }
        }
    }

    public void startPreviewInternal(Drawable drawable) {
        if (drawable instanceof AnimatedImageDrawable) {
            this.mDrawable = drawable;
            this.mAnimView.setImageDrawable(drawable);
            this.mListener.onPreviewStart();
            int i2 = 0;
            this.mAnimView.setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(this.mAnimView, false)));
            AnimatedImageDrawable animatedImageDrawable = (AnimatedImageDrawable) drawable;
            if (this.mLooping) {
                i2 = -1;
            }
            animatedImageDrawable.setRepeatCount(i2);
            animatedImageDrawable.registerAnimationCallback(this.mAnimCb);
            animatedImageDrawable.start();
        }
    }

    public void stopPreview() {
        Drawable drawable = this.mDrawable;
        if (drawable instanceof AnimatedImageDrawable) {
            if (!this.mPreviewDone) {
                this.mForceStop = true;
            }
            ((AnimatedImageDrawable) drawable).stop();
            this.mDrawable = null;
        }
    }

    public void seekTo(int i2) {
    }
}
