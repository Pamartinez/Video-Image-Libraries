package com.samsung.android.gallery.app.controller.story;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.abstraction.StoryCoverData;
import com.samsung.android.gallery.module.crop.SmartCropUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class StorySaveLayoutInfo {
    public static Rect drawStoryPicturesOnBitmap(View view, View view2, Bitmap bitmap, Point point, int i2, Bitmap bitmap2, Bitmap bitmap3, Point point2) {
        Rect rect = new Rect();
        Canvas canvas = new Canvas(bitmap);
        if (bitmap3 != null) {
            canvas.drawBitmap(bitmap3, (float) (point.x + point2.x), (float) point2.y, new Paint());
        }
        Rect rect2 = new Rect();
        view2.getDrawingRect(rect2);
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).offsetDescendantRectToMyCoords(view2, rect2);
        }
        int height = (int) ((((float) rect2.top) / ((float) view.getHeight())) * ((float) bitmap.getHeight()));
        int width = (int) ((((float) rect2.left) / ((float) i2)) * ((float) bitmap.getWidth()));
        canvas.drawBitmap(bitmap2, (float) (point.x + width), (float) height, new Paint());
        int i7 = point.x + width;
        rect.left = i7;
        rect.top = height;
        rect.right = bitmap2.getWidth() + i7;
        rect.bottom = bitmap2.getHeight() + rect.top;
        return rect;
    }

    public static Bitmap getBackgroundBitmap(MediaItem mediaItem) {
        Bitmap createBitmap = Bitmap.createBitmap(getSaveTargetSize(mediaItem), getSaveTargetSize(mediaItem), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, new Paint());
        return createBitmap;
    }

    private static String getCollageVideoPath(MediaItem mediaItem) {
        return ((StoryCoverData) MediaItemStory.getStoryOriginCoverData(mediaItem)).path;
    }

    public static Bitmap getContentBitmap(MediaItem mediaItem, ImageView imageView, Rect rect) {
        if (mediaItem == null) {
            return null;
        }
        if (isCollageVideo(mediaItem)) {
            return getCropBitmap(mediaItem, getHighQualityBitmap(mediaItem, imageView, rect), true);
        }
        if (isPreviewableFormat(mediaItem)) {
            return getBackgroundBitmap(mediaItem);
        }
        if (mediaItem.isBroken() || !mediaItem.isLocal() || !mediaItem.isVideo()) {
            return getHighQualityBitmap(mediaItem, imageView, rect);
        }
        return getNewDrawingBitmap(mediaItem, imageView, rect, getVideoThumbnail(mediaItem.getPath(), imageView), getSaveTargetSize(mediaItem), 1.0f);
    }

    public static Bitmap getCropBitmap(MediaItem mediaItem, Bitmap bitmap, boolean z) {
        if (bitmap == null) {
            return null;
        }
        if (MediaItemStory.getStoryCollagePath(mediaItem) != null) {
            return bitmap;
        }
        Rect cropRect = getCropRect(mediaItem, bitmap);
        int saveTargetSize = getSaveTargetSize(mediaItem);
        Bitmap createBitmap = Bitmap.createBitmap(saveTargetSize, saveTargetSize, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, cropRect, new Rect(0, 0, saveTargetSize, saveTargetSize), new Paint(6));
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    private static Rect getCropRect(MediaItem mediaItem, Bitmap bitmap) {
        Rect rect;
        RectF stringToRectF = RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
        if (SmartCropUtils.isValidRect(stringToRectF)) {
            rect = SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(stringToRectF).setDrawableSize(bitmap.getWidth(), bitmap.getHeight()).setViewSize((float) getSaveTargetSize(mediaItem), (float) getSaveTargetSize(mediaItem)).setRotation(mediaItem.getOrientation()).build());
        } else {
            rect = getFaceCropRect(mediaItem, bitmap);
        }
        return RectUtils.getRotatedRect(rect, getSaveTargetSize(mediaItem), getSaveTargetSize(mediaItem), mediaItem.getOrientation());
    }

    private static Rect getFaceCropRect(MediaItem mediaItem, Bitmap bitmap) {
        ArrayList cropRectRatioList = mediaItem.getCropRectRatioList();
        if (cropRectRatioList == null || cropRectRatioList.size() != 6) {
            return null;
        }
        RectF rectF = (RectF) cropRectRatioList.get(3);
        if (!SmartCropUtils.isValidRect(rectF)) {
            return null;
        }
        return SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectF).setDrawableSize(bitmap.getWidth(), bitmap.getHeight()).setViewSize((float) getSaveTargetSize(mediaItem), (float) getSaveTargetSize(mediaItem)).setRotation(mediaItem.getOrientation()).build());
    }

    public static Bitmap getHighQualityBitmap(MediaItem mediaItem, ImageView imageView, Rect rect) {
        Bitmap createScaledBitmap;
        if (MediaItemStory.getStoryCollagePath(mediaItem) != null) {
            int saveTargetSize = getSaveTargetSize(mediaItem);
            Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(MediaItemStory.getStoryCollagePath(mediaItem), 0, saveTargetSize, true);
            if (decodedBitmap == null) {
                Log.d("StorySaveLayoutInfo", "fail to create highQualityBitmap");
                return null;
            } else if (saveTargetSize == decodedBitmap.getWidth() || (createScaledBitmap = BitmapUtils.createScaledBitmap(decodedBitmap, saveTargetSize, saveTargetSize, true)) == null) {
                return decodedBitmap;
            } else {
                decodedBitmap.recycle();
                return createScaledBitmap;
            }
        } else {
            return getNewDrawingBitmap(mediaItem, imageView, rect, BitmapUtils.getDecodedBitmap(mediaItem.getPath(), 0, getSaveTargetSize(mediaItem), true), getSaveTargetSize(mediaItem), 1.0f);
        }
    }

    public static Bitmap getNewDrawingBitmap(MediaItem mediaItem, ImageView imageView, Rect rect, Bitmap bitmap, int i2, float f) {
        int i7 = 0;
        if (bitmap == null && (bitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), 0, i2, true)) == null) {
            return null;
        }
        if (!mediaItem.isVideo()) {
            i7 = mediaItem.getOrientation();
        }
        Bitmap rotateBitmap = BitmapUtils.rotateBitmap(bitmap, i7);
        if (rect == null) {
            rect = getNewDrawingRectFromMatrix(imageView, i7, bitmap.getWidth(), bitmap.getHeight());
        }
        return BitmapUtils.resize(BitmapUtils.crop(rotateBitmap, rect), (int) (((float) i2) * f));
    }

    private static Rect getNewDrawingRectFromMatrix(ImageView imageView, int i2, int i7, int i8) {
        float f;
        int intrinsicHeight;
        float f5;
        float f8;
        float f10;
        float intrinsicWidth;
        float f11;
        float[] fArr = new float[9];
        imageView.getImageMatrix().getValues(fArr);
        float max = Math.max(Math.abs(fArr[0]), Math.abs(fArr[1]));
        Drawable drawable = imageView.getDrawable();
        if (drawable.getIntrinsicWidth() > drawable.getIntrinsicHeight()) {
            f = (float) i7;
            intrinsicHeight = drawable.getIntrinsicWidth();
        } else {
            f = (float) i8;
            intrinsicHeight = drawable.getIntrinsicHeight();
        }
        float f12 = f / (((float) intrinsicHeight) * max);
        if (i2 == 0) {
            f8 = fArr[2];
            f5 = fArr[5];
        } else if (i2 == 90) {
            if (fArr[2] != 0.0f) {
                f8 = (((float) drawable.getIntrinsicHeight()) * max) - fArr[2];
            } else {
                f8 = 0.0f;
            }
            f5 = fArr[5];
        } else {
            if (i2 == 180) {
                if (fArr[2] != 0.0f) {
                    f10 = (((float) drawable.getIntrinsicWidth()) * max) - fArr[2];
                } else {
                    f10 = 0.0f;
                }
                if (fArr[5] != 0.0f) {
                    intrinsicWidth = ((float) drawable.getIntrinsicHeight()) * max;
                    f11 = fArr[5];
                }
                f5 = 0.0f;
            } else if (i2 == 270) {
                f10 = fArr[2];
                if (fArr[5] != 0.0f) {
                    intrinsicWidth = ((float) drawable.getIntrinsicWidth()) * max;
                    f11 = fArr[5];
                }
                f5 = 0.0f;
            } else {
                f8 = 0.0f;
                f5 = 0.0f;
            }
            f5 = intrinsicWidth - f11;
        }
        Rect rect = new Rect();
        rect.left = Math.round(Math.abs(f8 * f12));
        rect.top = Math.round(Math.abs(f5 * f12));
        rect.right = Math.round((((float) imageView.getWidth()) * f12) + ((float) rect.left));
        rect.bottom = Math.round((((float) imageView.getHeight()) * f12) + ((float) rect.top));
        return rect;
    }

    public static int getSaveTargetSize(MediaItem mediaItem) {
        if (isCollageVideo(mediaItem) || isPreviewableFormat(mediaItem)) {
            return 1440;
        }
        return 2896;
    }

    public static Bitmap getScaledDrawingBitmap(View view, float f) {
        Bitmap createBitmap = BitmapUtils.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return BitmapUtils.resize(createBitmap, f);
    }

    public static Rect getVideoCenterCropRect(Bitmap bitmap, View view, int i2) {
        if (bitmap == null || view == null) {
            return null;
        }
        return BitmapUtils.calcCenterCropRect((float) bitmap.getWidth(), (float) bitmap.getHeight(), (float) view.getWidth(), (float) view.getHeight(), i2);
    }

    public static Rect getVideoDisplayRect(MediaItem mediaItem, int i2) {
        int videoSize = getVideoSize(mediaItem, i2);
        Point videoPosition = getVideoPosition(mediaItem, i2);
        int i7 = videoPosition.x;
        int i8 = videoPosition.y;
        return new Rect(i7, i8, i7 + videoSize, videoSize + i8);
    }

    public static String getVideoPath(MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        if (isCollageVideo(mediaItem)) {
            return getCollageVideoPath(mediaItem);
        }
        if (isPreviewableFormat(mediaItem)) {
            return mediaItem.getPath();
        }
        return null;
    }

    public static Point getVideoPosition(MediaItem mediaItem, int i2) {
        int storyCollageType = MediaItemStory.getStoryCollageType(mediaItem);
        int collageColumns = StoryHelper.getCollageColumns(storyCollageType);
        if (storyCollageType != 2 && storyCollageType != 3) {
            return new Point();
        }
        int round = (int) Math.round((((double) i2) * 1.58839779d) / 100.0d);
        int i7 = (i2 - ((collageColumns + 1) * round)) / collageColumns;
        int storyCollageVideoIndex = MediaItemStory.getStoryCollageVideoIndex(mediaItem) % collageColumns;
        int storyCollageVideoIndex2 = MediaItemStory.getStoryCollageVideoIndex(mediaItem) / collageColumns;
        return new Point(((storyCollageVideoIndex + 1) * round) + (storyCollageVideoIndex * i7), ((storyCollageVideoIndex2 + 1) * round) + (i7 * storyCollageVideoIndex2));
    }

    public static int getVideoSize(MediaItem mediaItem, int i2) {
        int storyCollageType = MediaItemStory.getStoryCollageType(mediaItem);
        int round = (int) Math.round((((double) i2) * 1.58839779d) / 100.0d);
        if (storyCollageType == 0 || storyCollageType == 1) {
            return i2;
        }
        if (storyCollageType == 2) {
            return (i2 - (round * 3)) / 2;
        }
        if (storyCollageType == 3) {
            return (i2 - (round * 4)) / 3;
        }
        return 0;
    }

    private static Bitmap getVideoThumbnail(String str, ImageView imageView) {
        Bitmap videoThumbnailFromMeta = BitmapUtils.getVideoThumbnailFromMeta(str);
        if (videoThumbnailFromMeta != null || imageView == null || !(imageView.getDrawable() instanceof BitmapDrawable)) {
            return videoThumbnailFromMeta;
        }
        return ((BitmapDrawable) imageView.getDrawable()).getBitmap();
    }

    public static boolean isCollageVideo(MediaItem mediaItem) {
        StoryCoverData storyCoverData = (StoryCoverData) MediaItemStory.getStoryOriginCoverData(mediaItem);
        if (storyCoverData == null || !MediaType.Video.equals(storyCoverData.mediaType) || MediaItemStory.getStoryCollageType(mediaItem) == 1) {
            return false;
        }
        return true;
    }

    public static boolean isPreviewableFormat(MediaItem mediaItem) {
        return PreviewFactory.isPreviewableFormat(mediaItem);
    }
}
