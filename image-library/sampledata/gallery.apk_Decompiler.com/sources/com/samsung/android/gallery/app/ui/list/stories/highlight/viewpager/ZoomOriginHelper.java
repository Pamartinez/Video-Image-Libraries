package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.smartrect.StoryHighlightRect;
import com.samsung.android.gallery.support.utils.ImageMatrix;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.livemotion.zoom.ZoomToOriginHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ZoomOriginHelper {
    public static void applyOriginScale(ViewPagerHolder viewPagerHolder, boolean z) {
        if (viewPagerHolder != null) {
            ZoomToOriginHandler zoomToOriginHandler = new ZoomToOriginHandler(viewPagerHolder);
            zoomToOriginHandler.setFromMatrix(new Matrix(viewPagerHolder.getImage().getImageMatrix()));
            zoomToOriginHandler.zoom(viewPagerHolder, z);
        }
    }

    public static void applySmartCropScale(ViewPagerHolder viewPagerHolder, boolean z) {
        if (viewPagerHolder != null) {
            ZoomToOriginHandler zoomToOriginHandler = new ZoomToOriginHandler(viewPagerHolder);
            Matrix matrix = new Matrix(viewPagerHolder.getImage().getImageMatrix());
            if (setSmartCropMatrix(viewPagerHolder)) {
                zoomToOriginHandler.setFromMatrix(matrix);
                zoomToOriginHandler.restore(z);
                return;
            }
            viewPagerHolder.getImage().invalidate();
        }
    }

    private static int getOrientation(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isVideo() || mediaItem.isBroken()) {
            return 0;
        }
        return mediaItem.getOrientation();
    }

    private static boolean setSmartCropMatrix(ViewPagerHolder viewPagerHolder) {
        View view = viewPagerHolder.itemView;
        MediaItem mediaItem = viewPagerHolder.getMediaItem();
        Drawable drawable = viewPagerHolder.getImage().getDrawable();
        if (!(mediaItem == null || drawable == null)) {
            Rect rect = new StoryHighlightRect.RectBuilder(mediaItem).setScreenRect(new Rect(0, 0, view.getWidth(), view.getHeight())).setImageSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()).build().imageCropRect;
            if (RectUtils.isValidRect(rect)) {
                viewPagerHolder.getImage().setImageMatrix(ImageMatrix.create(ImageMatrix.MatrixParam.create(viewPagerHolder.getImage(), false).withCropRect(rect).withOrientation(getOrientation(mediaItem)).withOrientationTag(mediaItem.getOrientationTag())));
                return true;
            }
        }
        return false;
    }
}
