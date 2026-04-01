package com.samsung.android.gallery.app.controller.story;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.crop.SmartCropUtils;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class StorySaveHelper {
    public static Object[] createInfoForStoryCard(MediaItem mediaItem, List<ListViewHolder> list, int i2) {
        Bitmap bitmap;
        Rect rect;
        String str;
        int i7;
        int i8;
        String str2;
        float width;
        int width2;
        Bitmap bitmap2;
        MediaItem mediaItem2;
        Bitmap bitmap3;
        Bitmap bitmap4;
        View view;
        Bitmap bitmap5;
        Rect rect2;
        int saveTargetSize = StorySaveLayoutInfo.getSaveTargetSize(mediaItem);
        Rect rect3 = new Rect();
        Rect rect4 = new Rect();
        Point point = new Point();
        int i10 = 0;
        int i11 = 0;
        for (ListViewHolder rootView : list) {
            i11 += rootView.getRootView().getWidth();
        }
        Rect rect5 = rect3;
        Rect rect6 = rect4;
        int i12 = 0;
        Bitmap bitmap6 = null;
        String str3 = null;
        while (true) {
            if (i12 >= list.size()) {
                break;
            }
            ListViewHolder listViewHolder = list.get(i12);
            View childAt = ((ViewGroup) listViewHolder.getRootView()).getChildAt(i10);
            View decoView = listViewHolder.getDecoView(11);
            ListViewHolder listViewHolder2 = listViewHolder;
            MediaItem mediaItem3 = listViewHolder2.getMediaItem();
            if (mediaItem3 == null || decoView == null) {
                int i13 = i2;
                str2 = str3;
                i8 = i10;
                i7 = i12;
                rect6 = rect6;
            } else {
                if (decoView.getHeight() > decoView.getWidth()) {
                    width = (float) decoView.getHeight();
                    width2 = childAt.getHeight();
                } else {
                    width = (float) decoView.getWidth();
                    width2 = childAt.getWidth();
                }
                float f = width / ((float) width2);
                ListViewHolder listViewHolder3 = listViewHolder2;
                ImageView image = listViewHolder3.getImage();
                if (mediaItem3.isVideo()) {
                    bitmap2 = listViewHolder3.getBitmap();
                } else {
                    bitmap2 = null;
                }
                ListViewHolder listViewHolder4 = listViewHolder3;
                Bitmap newDrawingBitmap = StorySaveLayoutInfo.getNewDrawingBitmap(mediaItem3, image, (Rect) null, bitmap2, saveTargetSize, f);
                if (newDrawingBitmap != null) {
                    if (bitmap6 == null) {
                        bitmap6 = BitmapUtils.createBitmap(saveTargetSize, saveTargetSize, Bitmap.Config.ARGB_8888, i2);
                    } else {
                        int i14 = i2;
                    }
                    Point point2 = new Point();
                    if (MediaItemStory.getStoryTimeVisible(mediaItem3)) {
                        TextView textView = (TextView) ((ViewGroup) childAt).getChildAt(i10);
                        Bitmap scaledDrawingBitmap = StorySaveLayoutInfo.getScaledDrawingBitmap(textView, ((float) bitmap6.getWidth()) / ((float) childAt.getHeight()));
                        mediaItem2 = mediaItem3;
                        point2.x = (int) ((((float) textView.getLeft()) / ((float) childAt.getWidth())) * ((float) bitmap6.getWidth()));
                        point2.y = (bitmap6.getHeight() - scaledDrawingBitmap.getHeight()) / 2;
                        str2 = str3;
                        bitmap3 = scaledDrawingBitmap;
                        i7 = i12;
                        bitmap4 = bitmap6;
                        bitmap5 = newDrawingBitmap;
                        rect2 = rect6;
                        view = childAt;
                    } else {
                        mediaItem2 = mediaItem3;
                        str2 = str3;
                        bitmap3 = null;
                        i7 = i12;
                        bitmap4 = bitmap6;
                        view = childAt;
                        bitmap5 = newDrawingBitmap;
                        rect2 = rect6;
                    }
                    View view2 = decoView;
                    Rect drawStoryPicturesOnBitmap = StorySaveLayoutInfo.drawStoryPicturesOnBitmap(view, view2, bitmap4, point, i11, bitmap5, bitmap3, point2);
                    point.x += (int) ((((float) view.getWidth()) / ((float) i11)) * ((float) bitmap4.getWidth()));
                    if (PreviewFactory.isPreviewableFormat(mediaItem2)) {
                        str2 = StorySaveLayoutInfo.getVideoPath(mediaItem);
                        i8 = 0;
                        rect6 = StorySaveLayoutInfo.getVideoCenterCropRect(listViewHolder4.getBitmap(false), view2, 0);
                        rect5 = drawStoryPicturesOnBitmap;
                    } else {
                        i8 = 0;
                        rect6 = rect2;
                    }
                    bitmap5.recycle();
                    if (bitmap3 != null) {
                        bitmap3.recycle();
                    }
                    bitmap6 = bitmap4;
                } else if (bitmap6 != null) {
                    bitmap6.recycle();
                    rect = rect6;
                    str = str3;
                    bitmap = null;
                }
            }
            str3 = str2;
            i12 = i7 + 1;
            i10 = i8;
        }
        rect = rect6;
        str = str3;
        bitmap = bitmap6;
        return new Object[]{bitmap, str, rect5, rect};
    }

    public static Object[] createInfoForStoryCover(Context context, MediaItem mediaItem, List<ListViewHolder> list) {
        Rect rect;
        Rect rect2 = null;
        if (list == null || list.isEmpty()) {
            Log.d("StorySaveHelper", "createInfoForStoryCover - viewHolder is null! : " + list);
            return null;
        }
        ImageView image = list.get(0).getImage();
        if (list.get(0).getMediaItem() != mediaItem) {
            rect = getStoryImageCropRect(context, mediaItem, image);
        } else {
            rect = null;
        }
        Bitmap contentBitmap = StorySaveLayoutInfo.getContentBitmap(mediaItem, image, rect);
        String videoPath = StorySaveLayoutInfo.getVideoPath(mediaItem);
        if (!(contentBitmap == null || videoPath == null)) {
            rect2 = StorySaveLayoutInfo.getVideoDisplayRect(mediaItem, contentBitmap.getWidth());
        }
        return new Object[]{contentBitmap, videoPath, rect2};
    }

    private static Rect getCropRect(RectF rectF, Drawable drawable, ImageView imageView, int i2, MediaItem mediaItem) {
        if (SmartCropUtils.isValidRect(rectF)) {
            return SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectF).setDrawableSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).setViewSize((float) imageView.getWidth(), (float) imageView.getHeight()).setRotation(i2).build());
        }
        return getFaceCropRect(imageView, drawable, mediaItem, i2);
    }

    private static Rect getFaceCropRect(ImageView imageView, Drawable drawable, MediaItem mediaItem, int i2) {
        RectF rectF;
        ArrayList cropRectRatioList = mediaItem.getCropRectRatioList();
        if (cropRectRatioList == null || cropRectRatioList.size() != 6) {
            return null;
        }
        if (((float) imageView.getHeight()) / ((float) imageView.getWidth()) >= 0.75f) {
            rectF = (RectF) cropRectRatioList.get(3);
        } else {
            rectF = (RectF) cropRectRatioList.get(5);
        }
        if (!SmartCropUtils.isValidRect(rectF)) {
            return null;
        }
        return SmartCropUtils.calcSmartCropRectForStory(new SmartCropUtils.CropInfo.Builder(rectF).setDrawableSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).setViewSize((float) imageView.getWidth(), (float) imageView.getHeight()).setRotation(i2).build());
    }

    private static Rect getStoryImageCropRect(Context context, MediaItem mediaItem, ImageView imageView) {
        ImageView imageView2 = new ImageView(context);
        imageView2.setImageBitmap(BitmapUtils.getDecodedBitmap(mediaItem.getPath(), 0, StorySaveLayoutInfo.getSaveTargetSize(mediaItem), true));
        Drawable drawable = imageView2.getDrawable();
        if (drawable == null) {
            return null;
        }
        int orientation = mediaItem.getOrientation();
        return RectUtils.getRotatedRect(getCropRect(RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem)), drawable, imageView, orientation, mediaItem), drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), orientation);
    }
}
