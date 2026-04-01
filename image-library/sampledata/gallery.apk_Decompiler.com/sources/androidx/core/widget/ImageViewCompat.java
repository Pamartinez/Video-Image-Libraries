package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.ImageView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ImageViewCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static ColorStateList getImageTintList(ImageView imageView) {
            return imageView.getImageTintList();
        }

        public static PorterDuff.Mode getImageTintMode(ImageView imageView) {
            return imageView.getImageTintMode();
        }

        public static void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
            imageView.setImageTintList(colorStateList);
        }

        public static void setImageTintMode(ImageView imageView, PorterDuff.Mode mode) {
            imageView.setImageTintMode(mode);
        }
    }

    public static ColorStateList getImageTintList(ImageView imageView) {
        return Api21Impl.getImageTintList(imageView);
    }

    public static PorterDuff.Mode getImageTintMode(ImageView imageView) {
        return Api21Impl.getImageTintMode(imageView);
    }

    public static void setImageTintList(ImageView imageView, ColorStateList colorStateList) {
        Api21Impl.setImageTintList(imageView, colorStateList);
    }

    public static void setImageTintMode(ImageView imageView, PorterDuff.Mode mode) {
        Api21Impl.setImageTintMode(imageView, mode);
    }
}
