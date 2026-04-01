package com.samsung.android.gallery.plugins.motionphoto;

import Ab.b;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoViewHolder extends MotionPhotoViewPlayer {
    int mLastFramePosition = -1;
    private boolean mLifeCycleResumed;
    private View mPreview;

    public MotionPhotoViewHolder(Context context) {
        super(context);
    }

    private void correctVideoSize(MotionPhotoViewCompat motionPhotoViewCompat, View view) {
        String str;
        int i2;
        if (view instanceof ImageView) {
            MediaHelper.VideoInfo videoInfo = this.mVideoInfo;
            if (videoInfo == null || !videoInfo.corrected) {
                try {
                    Drawable drawable = ((ImageView) view).getDrawable();
                    if (drawable != null) {
                        int intrinsicWidth = drawable.getIntrinsicWidth();
                        int intrinsicHeight = drawable.getIntrinsicHeight();
                        if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                            int[] videoSize = motionPhotoViewCompat.getVideoSize();
                            if (!(videoSize == null || (i2 = videoSize[1]) == 0)) {
                                if (MathUtils.equals(((float) videoSize[0]) / ((float) i2), ((float) intrinsicWidth) / ((float) intrinsicHeight))) {
                                    return;
                                }
                            }
                            StringCompat stringCompat = this.TAG;
                            StringBuilder sb2 = new StringBuilder("correctVideoRatio {");
                            sb2.append(intrinsicWidth);
                            sb2.append("x");
                            sb2.append(intrinsicHeight);
                            sb2.append(" vs ");
                            if (videoSize != null) {
                                str = videoSize[0] + "x" + videoSize[1];
                            } else {
                                str = "";
                            }
                            sb2.append(str);
                            sb2.append("}");
                            Log.e(stringCompat, sb2.toString());
                            motionPhotoViewCompat.setVideoSize(intrinsicWidth, intrinsicHeight);
                        }
                    }
                } catch (Exception e) {
                    Log.e((CharSequence) this.TAG, "correctVideoSize failed", (Throwable) e);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindView$1(MotionPhotoViewCompat motionPhotoViewCompat) {
        motionPhotoViewCompat.setVisibility(0);
        motionPhotoViewCompat.requestLayout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$2(MotionPhotoViewCompat motionPhotoViewCompat) {
        surfaceCreated(motionPhotoViewCompat.getHolder());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoRendered$3(MediaPlayerCompat mediaPlayerCompat, int i2) {
        if (this.mPlayState.get()) {
            ViewUtils.setVisibility(this.mVideoView, 0);
            ViewUtils.setAlpha(this.mVideoView, 1.0f);
            if (!notifyCallback(mediaPlayerCompat, i2, getCurrentPosition())) {
                ViewUtils.setVisibility(this.mPreview, 4);
            }
            updateBackgroundColor(true);
            return;
        }
        Log.w(this.TAG, "onVideoRendered skip");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void bindView(com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat r6, android.view.View r7, boolean r8) {
        /*
            r5 = this;
            java.lang.String r0 = "bindView +"
            java.lang.String r1 = "bindView -"
            monitor-enter(r5)
            com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat r2 = r5.mVideoView     // Catch:{ all -> 0x001c }
            if (r2 == 0) goto L_0x001f
            if (r2 != r6) goto L_0x001f
            android.view.View r7 = r5.mPreview     // Catch:{ all -> 0x001c }
            r5.correctVideoSize(r6, r7)     // Catch:{ all -> 0x001c }
            r6.setSurfaceHolderCallback(r5)     // Catch:{ all -> 0x001c }
            com.samsung.android.gallery.support.utils.StringCompat r6 = r5.TAG     // Catch:{ all -> 0x001c }
            java.lang.String r7 = "bindView skip"
            com.samsung.android.gallery.support.utils.Log.d(r6, r7)     // Catch:{ all -> 0x001c }
            monitor-exit(r5)
            return
        L_0x001c:
            r6 = move-exception
            goto L_0x009d
        L_0x001f:
            com.samsung.android.gallery.support.utils.StringCompat r2 = r5.TAG     // Catch:{ all -> 0x001c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x001c }
            r3.<init>(r1)     // Catch:{ all -> 0x001c }
            r3.append(r5)     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x001c }
            com.samsung.android.gallery.support.utils.Log.d(r2, r1)     // Catch:{ all -> 0x001c }
            boolean r1 = r5.isInPlayState()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x003e
            r5.pausePlayback()     // Catch:{ all -> 0x001c }
            r1 = 0
            r5.stopPlayback(r1)     // Catch:{ all -> 0x001c }
        L_0x003e:
            com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat r1 = r5.mVideoView     // Catch:{ all -> 0x001c }
            r2 = 0
            if (r1 == 0) goto L_0x0054
            int[] r3 = r1.getVideoSize()     // Catch:{ all -> 0x001c }
            r1.setSurfaceHolderCallback(r2)     // Catch:{ all -> 0x001c }
            Ba.j r2 = new Ba.j     // Catch:{ all -> 0x001c }
            r4 = 1
            r2.<init>(r1, r4)     // Catch:{ all -> 0x001c }
            r1.post(r2)     // Catch:{ all -> 0x001c }
            r2 = r3
        L_0x0054:
            r5.mVideoView = r6     // Catch:{ all -> 0x001c }
            r5.mPreview = r7     // Catch:{ all -> 0x001c }
            com.samsung.android.gallery.support.utils.StringCompat r1 = r5.TAG     // Catch:{ all -> 0x001c }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x001c }
            r3.<init>(r0)     // Catch:{ all -> 0x001c }
            r3.append(r5)     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x001c }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ all -> 0x001c }
            if (r6 == 0) goto L_0x009b
            r0 = 1
            if (r2 == 0) goto L_0x0080
            r7 = 0
            r7 = r2[r7]     // Catch:{ all -> 0x001c }
            r1 = r2[r0]     // Catch:{ all -> 0x001c }
            r6.setVideoSize(r7, r1)     // Catch:{ all -> 0x001c }
            Ba.j r7 = new Ba.j     // Catch:{ all -> 0x001c }
            r1 = 2
            r7.<init>(r6, r1)     // Catch:{ all -> 0x001c }
            r6.post(r7)     // Catch:{ all -> 0x001c }
            goto L_0x0083
        L_0x0080:
            r5.correctVideoSize(r6, r7)     // Catch:{ all -> 0x001c }
        L_0x0083:
            r6.setSurfaceHolderCallback(r5)     // Catch:{ all -> 0x001c }
            if (r8 == 0) goto L_0x008b
            r5.activatePlayback(r0)     // Catch:{ all -> 0x001c }
        L_0x008b:
            boolean r7 = r6.isSurfaceReady()     // Catch:{ all -> 0x001c }
            if (r7 == 0) goto L_0x009b
            A2.d r7 = new A2.d     // Catch:{ all -> 0x001c }
            r8 = 18
            r7.<init>(r8, r5, r6)     // Catch:{ all -> 0x001c }
            r6.post(r7)     // Catch:{ all -> 0x001c }
        L_0x009b:
            monitor-exit(r5)
            return
        L_0x009d:
            monitor-exit(r5)     // Catch:{ all -> 0x001c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewHolder.bindView(com.samsung.android.gallery.plugins.motionphoto.MotionPhotoViewCompat, android.view.View, boolean):void");
    }

    public void onVideoRendered(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        MotionPhotoViewCompat motionPhotoViewCompat = this.mVideoView;
        if (motionPhotoViewCompat != null) {
            motionPhotoViewCompat.postDelayed(new b((Object) this, (Object) mediaPlayerCompat, i2, 1), 52);
        } else {
            super.onVideoRendered(mediaPlayerCompat, i2, i7);
        }
    }

    public void setLifeCycle(boolean z) {
        this.mLifeCycleResumed = z;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (this.mLifeCycleResumed) {
            super.surfaceCreated(surfaceHolder);
        } else {
            Log.w(this.TAG, "surfaceCreated skip by paused");
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.mLifeCycleResumed && isPlaying()) {
            pausePlayback();
            setStartOnPrepared(true);
        }
        super.surfaceDestroyed(surfaceHolder);
    }
}
