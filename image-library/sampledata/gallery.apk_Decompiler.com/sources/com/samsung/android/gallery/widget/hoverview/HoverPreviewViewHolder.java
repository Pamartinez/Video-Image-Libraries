package com.samsung.android.gallery.widget.hoverview;

import B2.i;
import D7.g;
import Da.f;
import Eb.b;
import Eb.c;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.media.GifAnimationFactory;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HoverPreviewViewHolder extends RecyclerView.ViewHolder implements MediaPlayerListener {
    private OnDeleteClickListener mDeleteClickListener;
    private ImageButton mHoverPreviewDeleteBtn;
    private ImageView mHoverPreviewIcon;
    private ImageView mHoverPreviewImage;
    private LinearLayout mHoverPreviewMenu;
    private ImageButton mHoverPreviewShareBtn;
    private OnItemTouchListener mItemTouchListener;
    private IMediaPlayerView mMediaView;
    private GifAnimation mMovie;
    private OnShareClickListener mShareClickListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnDeleteClickListener {
        void onDeleteClick(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemTouchListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnShareClickListener {
        void onShareClick(int i2);
    }

    public HoverPreviewViewHolder(View view) {
        super(view);
        bindView(view);
    }

    private void bindView(View view) {
        IMediaPlayerView iMediaPlayerView = (IMediaPlayerView) view.findViewById(R$id.media_view);
        this.mMediaView = iMediaPlayerView;
        iMediaPlayerView.addMediaPlayerListener(this);
        this.mHoverPreviewImage = (ImageView) view.findViewById(R$id.hover_preview_image);
        this.mHoverPreviewIcon = (ImageView) view.findViewById(R$id.hover_preview_icon);
        this.mHoverPreviewMenu = (LinearLayout) view.findViewById(R$id.hover_preview_menu);
        this.mHoverPreviewShareBtn = (ImageButton) view.findViewById(R$id.hover_preview_menu_share);
        this.mHoverPreviewDeleteBtn = (ImageButton) view.findViewById(R$id.hover_preview_menu_delete);
        this.itemView.setOnTouchListener(new i(7, this));
    }

    private GifAnimation decodeMovie(MediaItem mediaItem) {
        return GifAnimationFactory.getDecodedGifAnimation(this.itemView.getContext(), mediaItem).setAnimationFrameUpdateListener(new c(this)).setAnimationFrameStartListener(new c(this));
    }

    private void drawCanvasRect(int i2, Canvas canvas, Paint paint, float f, float f5) {
        if ((i2 & 1) == 0) {
            canvas.drawRect(0.0f, 0.0f, f / 2.0f, f5 / 2.0f, paint);
        }
        if ((i2 & 4) == 0) {
            canvas.drawRect(0.0f, f5 / 2.0f, f / 2.0f, f5, paint);
        }
        if ((i2 & 2) == 0) {
            canvas.drawRect(f / 2.0f, 0.0f, f, f5 / 2.0f, paint);
        }
        if ((i2 & 8) == 0) {
            canvas.drawRect(f / 2.0f, f5 / 2.0f, f, f5, paint);
        }
    }

    private Bitmap getRoundedCornerImage(Bitmap bitmap, int i2, float f) {
        if (i2 == 0 || f == 0.0f) {
            return bitmap;
        }
        Bitmap createBitmap = BitmapUtils.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        drawCanvasRect(i2, canvas, paint, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        BitmapUtils.putBitmap(bitmap);
        return createBitmap;
    }

    private boolean isCloudThumbnail(String str) {
        if (str == null || !str.endsWith(".jpg")) {
            return false;
        }
        return true;
    }

    private boolean isMovieAvailable() {
        GifAnimation gifAnimation = this.mMovie;
        if (gifAnimation == null || !gifAnimation.isAnimation()) {
            return false;
        }
        return true;
    }

    private boolean isVideo(MediaItem mediaItem) {
        if (!mediaItem.isVideo() || mediaItem.isCloudOnly() || mediaItem.isSharing() || isCloudThumbnail(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoStop$0() {
        this.mHoverPreviewImage.setVisibility(0);
        this.mHoverPreviewIcon.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnableDelete$3(View view) {
        OnDeleteClickListener onDeleteClickListener = this.mDeleteClickListener;
        if (onDeleteClickListener != null) {
            onDeleteClickListener.onDeleteClick(getBindingAdapterPosition());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setEnableShare$2(View view) {
        OnShareClickListener onShareClickListener = this.mShareClickListener;
        if (onShareClickListener != null) {
            onShareClickListener.onShareClick(getBindingAdapterPosition());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCurrentBitmap$1(Bitmap bitmap) {
        this.mHoverPreviewImage.setImageBitmap(bitmap);
    }

    /* access modifiers changed from: private */
    public void onAnimationFrameUpdated(Bitmap bitmap) {
        if (bitmap == null) {
            Log.d("HoverPreviewViewHolder", "onAnimationFrameUpdated bitmap is null!");
        } else {
            updateCurrentBitmap(bitmap);
        }
    }

    private void onVideoStop() {
        ViewUtils.post(this.mHoverPreviewImage, new g(5, this));
    }

    private void playMovie(MediaItem mediaItem) {
        if (this.mMovie == null) {
            this.mMovie = decodeMovie(mediaItem);
        }
        startMovie();
    }

    private void releaseMovie() {
        GifAnimation gifAnimation = this.mMovie;
        if (gifAnimation != null) {
            gifAnimation.release();
            this.mMovie = null;
        }
    }

    private void setHoverPreviewIconLayoutParams(Rect rect) {
        this.mHoverPreviewIcon.getLayoutParams().width = rect.width();
        this.mHoverPreviewIcon.getLayoutParams().height = rect.height();
        ImageView imageView = this.mHoverPreviewIcon;
        imageView.setLayoutParams(imageView.getLayoutParams());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void startMovie() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isMovieAvailable()     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x0013
            java.lang.String r0 = "HoverPreviewViewHolder"
            java.lang.String r1 = "startMovie skipped"
            com.samsung.android.gallery.support.utils.Log.e(r0, r1)     // Catch:{ all -> 0x0011 }
            monitor-exit(r2)
            return
        L_0x0011:
            r0 = move-exception
            goto L_0x001c
        L_0x0013:
            com.samsung.android.gallery.module.media.GifAnimation r0 = r2.mMovie     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x001a
            r0.start()     // Catch:{ all -> 0x0011 }
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            monitor-exit(r2)     // Catch:{ all -> 0x0011 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder.startMovie():void");
    }

    private synchronized void stopMovie() {
        GifAnimation gifAnimation = this.mMovie;
        if (gifAnimation != null) {
            gifAnimation.stop();
        }
    }

    private void updateBoarder(View view, float f) {
        ViewUtils.setViewShape(view, 1, f);
    }

    private void updateCurrentBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = BitmapUtils.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-16777216);
        canvas.drawBitmap(bitmap, (Rect) null, new RectF(0.0f, 0.0f, (float) width, (float) height), (Paint) null);
        this.mHoverPreviewImage.post(new f(7, this, createBitmap));
    }

    public void onSurfaceDestroyed() {
        onVideoStop();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        OnItemTouchListener onItemTouchListener = this.mItemTouchListener;
        b bVar = (b) onItemTouchListener;
        boolean a7 = bVar.f3204a.lambda$onBindViewHolder$2(bVar.b, view, motionEvent, getBindingAdapterPosition());
        if (motionEvent.getAction() == 1 && a7) {
            this.mMediaView.close();
        }
        return true;
    }

    public void onVideoCompleted() {
        onVideoStop();
    }

    public void onVideoError(int i2, int i7) {
        onVideoStop();
    }

    public void onVideoPlay(int i2) {
        SimpleAnimator.setVisibility(new View[]{this.mHoverPreviewImage, this.mHoverPreviewIcon}, 8, 60);
    }

    public void recycle() {
        stopMovie();
        releaseMovie();
        this.mMediaView.close();
        this.mMediaView.setVisibility(8);
        this.mHoverPreviewImage.setOutlineProvider((ViewOutlineProvider) null);
        ((View) this.mMediaView).setOutlineProvider((ViewOutlineProvider) null);
        this.mHoverPreviewShareBtn.setOnClickListener((View.OnClickListener) null);
        this.mHoverPreviewDeleteBtn.setOnClickListener((View.OnClickListener) null);
    }

    public void setEnableDelete(boolean z) {
        int i2;
        ImageButton imageButton = this.mHoverPreviewDeleteBtn;
        int i7 = 0;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageButton.setVisibility(i2);
        this.mHoverPreviewDeleteBtn.setOnClickListener(new b(this, 0));
        if (this.mHoverPreviewMenu.getVisibility() == 8) {
            LinearLayout linearLayout = this.mHoverPreviewMenu;
            if (!z) {
                i7 = 8;
            }
            linearLayout.setVisibility(i7);
        }
    }

    public void setEnableShare(boolean z) {
        int i2;
        ImageButton imageButton = this.mHoverPreviewShareBtn;
        int i7 = 8;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageButton.setVisibility(i2);
        this.mHoverPreviewShareBtn.setOnClickListener(new b(this, 1));
        LinearLayout linearLayout = this.mHoverPreviewMenu;
        if (z) {
            i7 = 0;
        }
        linearLayout.setVisibility(i7);
    }

    public void setIcon(int i2, Rect rect) {
        if (i2 != 0 && rect != null) {
            setHoverPreviewIconLayoutParams(rect);
            this.mHoverPreviewIcon.setImageResource(i2);
            this.mHoverPreviewIcon.setAlpha(0.9f);
            this.mHoverPreviewIcon.setVisibility(0);
        } else if (this.mHoverPreviewIcon.getVisibility() == 0) {
            this.mHoverPreviewIcon.setImageResource(0);
            this.mHoverPreviewIcon.setVisibility(8);
        }
    }

    public void setImage(MediaItem mediaItem, Bitmap bitmap, boolean z, int i2, float f) {
        if (i2 == 15) {
            this.mHoverPreviewImage.setImageBitmap(bitmap);
            ViewUtils.resize(this.itemView, bitmap.getWidth(), bitmap.getHeight());
            updateBoarder(this.mHoverPreviewImage, f);
        } else {
            Bitmap roundedCornerImage = getRoundedCornerImage(bitmap, i2, f);
            this.mHoverPreviewImage.setImageBitmap(roundedCornerImage);
            ViewUtils.resize(this.itemView, roundedCornerImage.getWidth(), roundedCornerImage.getHeight());
            this.mHoverPreviewImage.setClipToOutline(true);
        }
        if (z) {
            IMediaPlayerView iMediaPlayerView = this.mMediaView;
            if (!isVideo(mediaItem) || iMediaPlayerView == null) {
                if (mediaItem.isGif()) {
                    playMovie(mediaItem);
                }
            } else if (iMediaPlayerView.open(mediaItem)) {
                iMediaPlayerView.setVisibility(0);
                iMediaPlayerView.setLooping(true);
                iMediaPlayerView.disableWfdTcp();
                iMediaPlayerView.play();
                updateBoarder((View) iMediaPlayerView, f);
            }
        }
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.mDeleteClickListener = onDeleteClickListener;
    }

    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
        this.mItemTouchListener = onItemTouchListener;
    }

    public void setOnShareClickListener(OnShareClickListener onShareClickListener) {
        this.mShareClickListener = onShareClickListener;
    }

    /* access modifiers changed from: private */
    public void onAnimationFrameStarted() {
    }
}
