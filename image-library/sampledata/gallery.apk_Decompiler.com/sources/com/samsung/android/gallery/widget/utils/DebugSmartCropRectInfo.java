package com.samsung.android.gallery.widget.utils;

import B8.e;
import Bb.j;
import H7.o;
import a6.C0419b;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import bc.d;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapPool;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import gc.a;
import gc.b;
import gc.c;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DebugSmartCropRectInfo {
    private static final DebugSmartCropRectInfo sInstance = new DebugSmartCropRectInfo();
    private Bitmap mCropStrokeBitmap;
    private AlertDialog mDebugInfoDialog;
    private Bitmap mDecodedBitmap;
    private MediaItem mMediaItem;
    private ShowType mShowType = ShowType.OT_RR;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ShowType {
        OT_OR,
        OT_RR,
        RT_OR,
        RT_RR
    }

    private ShowType checkShowType() {
        if (this.mMediaItem.isScenes()) {
            if (this.mMediaItem.getOrientation() == 0) {
                return ShowType.OT_OR;
            }
            return ShowType.RT_OR;
        } else if (!this.mMediaItem.isCreature() || !this.mMediaItem.isVideo() || this.mMediaItem.isCloudOnly()) {
            if (this.mMediaItem.getOrientation() == 0) {
                return ShowType.OT_RR;
            }
            return ShowType.RT_RR;
        } else if (this.mMediaItem.getOrientation() == 0) {
            return ShowType.OT_OR;
        } else {
            return ShowType.OT_RR;
        }
    }

    private void clearCache() {
        ThumbnailLoader.getInstance().removeCache(this.mMediaItem);
    }

    private void dismissed() {
        recycle(this.mDecodedBitmap);
        recycle(this.mCropStrokeBitmap);
        this.mDecodedBitmap = null;
        this.mCropStrokeBitmap = null;
        this.mMediaItem = null;
        this.mDebugInfoDialog = null;
    }

    private void drawLine(Context context, ImageView imageView, MediaItem mediaItem) {
        Rect rect;
        int width = this.mDecodedBitmap.getWidth();
        int height = this.mDecodedBitmap.getHeight();
        this.mCropStrokeBitmap = BitmapPool.getInstance().get(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.mCropStrokeBitmap);
        Paint paint = new Paint();
        paint.setColor(-256);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        ShowType showType = this.mShowType;
        if (showType == ShowType.OT_RR || showType == ShowType.RT_RR) {
            rect = RectUtils.getSmartCropRect(mediaItem.getCropRectRatio(), width, height);
        } else {
            rect = RectUtils.getSmartCropRect(mediaItem.getRawCropRectRatio(), width, height);
        }
        canvas.drawRect(rect, paint);
        imageView.setForeground(new BitmapDrawable(context.getResources(), this.mCropStrokeBitmap));
    }

    private void getDecodedBitmap() {
        if (this.mMediaItem.isImage() || FileUtils.isCloudVideoThumbCache(this.mMediaItem.getPath())) {
            this.mDecodedBitmap = BitmapUtils.getDecodedBitmap(this.mMediaItem.getPath(), BitmapOptionsFactory.of(this.mMediaItem).adjustInSampleSize(360));
        } else {
            Bitmap videoThumbnailFromMeta = BitmapUtils.getVideoThumbnailFromMeta(this.mMediaItem.getPath());
            if (videoThumbnailFromMeta != null) {
                this.mDecodedBitmap = BitmapUtils.resize(videoThumbnailFromMeta, 360.0f / ((float) Math.max(videoThumbnailFromMeta.getWidth(), videoThumbnailFromMeta.getHeight())));
                recycle(videoThumbnailFromMeta);
            }
        }
        Bitmap bitmap = this.mDecodedBitmap;
        if (bitmap != null) {
            ShowType showType = this.mShowType;
            if (showType == ShowType.RT_OR || showType == ShowType.RT_RR) {
                this.mDecodedBitmap = BitmapUtils.rotateBitmap(bitmap, this.mMediaItem.getOrientation());
            }
        }
    }

    private int getDefaultShotTypeRadiobuttonId() {
        ShowType showType = this.mShowType;
        if (showType == ShowType.OT_OR) {
            return R$id.type_OT_OR;
        }
        if (showType == ShowType.OT_RR) {
            return R$id.type_OT_RR;
        }
        if (showType == ShowType.RT_OR) {
            return R$id.type_RT_OR;
        }
        return R$id.type_RT_RR;
    }

    public static DebugSmartCropRectInfo getInstance() {
        return sInstance;
    }

    private void initBasicInfo(View view, MediaItem mediaItem) {
        String str;
        TextView textView = (TextView) view.findViewById(R$id.basicInfo);
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("> SUB_CATEGORY :  " + mediaItem.getSubCategory() + "(cnt:" + mediaItem.getCount() + ")");
        StringBuilder sb2 = new StringBuilder("> ORIENTATION :  ");
        sb2.append(mediaItem.getOrientation());
        stringJoiner.add(sb2.toString());
        stringJoiner.add("> RESOLUTION :  " + mediaItem.getWidth() + "x" + mediaItem.getHeight());
        StringBuilder sb3 = new StringBuilder("> PATH  :  ");
        if (mediaItem.isCloudOnly()) {
            str = mediaItem.getCloudServerPath();
        } else {
            str = mediaItem.getPath();
        }
        sb3.append(str);
        stringJoiner.add(sb3.toString());
        textView.setText(stringJoiner.toString());
    }

    private void initCreatureInfo(View view, MediaItem mediaItem) {
        if (mediaItem.isCreature()) {
            TextView textView = (TextView) view.findViewById(R$id.creature_info);
            StringJoiner stringJoiner = new StringJoiner("\n");
            stringJoiner.add(" [Creature Data]");
            stringJoiner.add("  " + CreatureData.of(mediaItem)).add(" ");
            textView.setText(stringJoiner.toString());
            ViewUtils.setVisibleOrGone(textView, true);
        }
    }

    private void initRectInfo(View view, MediaItem mediaItem) {
        String str;
        String str2;
        TextView textView = (TextView) view.findViewById(R$id.rect_info_details);
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("Smart crop rect (DB origin)");
        if (mediaItem.getCropRectRatioList() != null) {
            Iterator it = mediaItem.getCropRectRatioList().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                stringJoiner.add(" " + i2 + " : " + ((RectF) it.next()).toString());
                i2++;
            }
        } else {
            stringJoiner.add("   empty smart crop rect");
        }
        stringJoiner.add("\nRaw rect (DB origin)");
        StringBuilder sb2 = new StringBuilder("   ");
        if (mediaItem.getRawCropRectRatio() != null) {
            str = mediaItem.getRawCropRectRatio().toString();
        } else {
            str = " empty raw crop rect";
        }
        sb2.append(str);
        stringJoiner.add(sb2.toString());
        stringJoiner.add("\nCrop rect (considered orientation)");
        StringBuilder sb3 = new StringBuilder("   ");
        if (mediaItem.getCropRectRatio() != null) {
            str2 = mediaItem.getCropRectRatio().toString();
        } else {
            str2 = " empty crop rect";
        }
        sb3.append(str2);
        stringJoiner.add(sb3.toString());
        textView.setText(stringJoiner.toString());
    }

    private void initShowType(View view) {
        if (this.mDecodedBitmap != null) {
            ((ViewGroup) view.findViewById(R$id.show_type)).setVisibility(0);
            RadioGroup radioGroup = (RadioGroup) view.findViewById(R$id.show_type_radio_group);
            radioGroup.check(getDefaultShotTypeRadiobuttonId());
            radioGroup.setOnCheckedChangeListener(new a(0, this));
            initShowTypeHint(view);
        }
    }

    private void initShowTypeHint(View view) {
        if (this.mDecodedBitmap != null) {
            ((TextView) view.findViewById(R$id.type_hint)).setOnClickListener(new C0419b(13, view));
        }
    }

    private void initThumbnail(Context context, View view, MediaItem mediaItem, Bitmap bitmap) {
        if (this.mDecodedBitmap != null) {
            ImageView imageView = (ImageView) view.findViewById(R$id.thumbnail);
            imageView.setImageBitmap(this.mDecodedBitmap);
            drawLine(context, imageView, mediaItem);
        }
        if (bitmap != null) {
            ImageView imageView2 = (ImageView) view.findViewById(R$id.viewHolderBitmap);
            imageView2.setImageBitmap(bitmap);
            ((TextView) view.findViewById(R$id.clear_cache)).setOnClickListener(new b(this, imageView2, context, 0));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initShowType$7(RadioGroup radioGroup, int i2) {
        if (i2 == R$id.type_OT_OR) {
            this.mShowType = ShowType.OT_OR;
        } else if (i2 == R$id.type_OT_RR) {
            this.mShowType = ShowType.OT_RR;
        } else if (i2 == R$id.type_RT_OR) {
            this.mShowType = ShowType.RT_OR;
        } else {
            this.mShowType = ShowType.RT_RR;
        }
        if (this.mMediaItem.getOrientation() != 0) {
            updateThumbnail();
        } else {
            Utils.showToast(this.mDebugInfoDialog.getContext(), "MediaItem's orientation is 0, nothing changed!!");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initThumbnail$3(Context context, ImageView imageView) {
        Utils.showToast(context, "Reload ViewHolder bitmap");
        reloadViewHolderThumbnail(imageView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initThumbnail$4(ImageView imageView, Context context, View view) {
        clearCache();
        imageView.setForeground(new ColorDrawable(context.getColor(R$color.white)));
        Utils.showToast(context, "Cleared cache : " + this.mMediaItem.getThumbCacheKey());
        ThreadUtil.postOnUiThreadDelayed(new d((Object) this, (Object) context, (Object) imageView, 17), 1500);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$reloadViewHolderThumbnail$5(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
        imageView.setForeground((Drawable) null);
        imageView.invalidate();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showCropRectForThumbnail$0(Activity activity, MediaItem mediaItem, Bitmap bitmap) {
        if (!activity.isDestroyed()) {
            showDebugInfo(activity, mediaItem, bitmap);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showCropRectForThumbnail$1(Activity activity, MediaItem mediaItem, Bitmap bitmap) {
        getDecodedBitmap();
        ThreadUtil.postOnUiThread(new gc.d(this, activity, mediaItem, bitmap, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showDebugInfo$2(DialogInterface dialogInterface) {
        dismissed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateThumbnail$10() {
        getDecodedBitmap();
        ThreadUtil.postOnUiThread(new c(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateThumbnail$9() {
        if (this.mDecodedBitmap != null) {
            ImageView imageView = (ImageView) this.mDebugInfoDialog.findViewById(R$id.thumbnail);
            imageView.setImageBitmap(this.mDecodedBitmap);
            drawLine(this.mDebugInfoDialog.getContext(), imageView, this.mMediaItem);
            imageView.invalidate();
        }
    }

    private void recycle(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    private void reloadViewHolderThumbnail(ImageView imageView) {
        if (imageView != null) {
            ThumbnailLoader instance = ThumbnailLoader.getInstance();
            MediaItem mediaItem = this.mMediaItem;
            ThumbKind thumbKind = ThumbKind.MEDIUM_KIND;
            Objects.requireNonNull(mediaItem);
            instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new j(imageView));
        }
    }

    private void showDebugInfo(Context context, MediaItem mediaItem, Bitmap bitmap) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle((CharSequence) mediaItem.getDisplayName());
        builder.setCancelable(true);
        View inflate = LayoutInflater.from(context).inflate(R$layout.smartcrop_rect_info_dialog, (ViewGroup) null);
        initThumbnail(context, inflate, mediaItem, bitmap);
        initShowType(inflate);
        initBasicInfo(inflate, mediaItem);
        initCreatureInfo(inflate, mediaItem);
        initRectInfo(inflate, mediaItem);
        builder.setView(inflate);
        builder.setOnDismissListener(new o(2, this));
        AlertDialog create = builder.create();
        this.mDebugInfoDialog = create;
        create.show();
    }

    private void updateThumbnail() {
        SimpleThreadPool.getInstance().execute(new c(this, 0));
    }

    public void showCropRectForThumbnail(Activity activity, MediaItem mediaItem, Bitmap bitmap) {
        this.mMediaItem = mediaItem;
        this.mShowType = checkShowType();
        SimpleThreadPool.getInstance().execute(new gc.d(this, activity, mediaItem, bitmap, 0));
    }
}
