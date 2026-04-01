package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CompoundButtonCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static ColorStateList getButtonTintList(CompoundButton compoundButton) {
            return compoundButton.getButtonTintList();
        }

        public static PorterDuff.Mode getButtonTintMode(CompoundButton compoundButton) {
            return compoundButton.getButtonTintMode();
        }

        public static void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList) {
            compoundButton.setButtonTintList(colorStateList);
        }

        public static void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode mode) {
            compoundButton.setButtonTintMode(mode);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static Drawable getButtonDrawable(CompoundButton compoundButton) {
            return compoundButton.getButtonDrawable();
        }
    }

    public static Drawable getButtonDrawable(CompoundButton compoundButton) {
        return Api23Impl.getButtonDrawable(compoundButton);
    }

    public static ColorStateList getButtonTintList(CompoundButton compoundButton) {
        return Api21Impl.getButtonTintList(compoundButton);
    }

    public static PorterDuff.Mode getButtonTintMode(CompoundButton compoundButton) {
        return Api21Impl.getButtonTintMode(compoundButton);
    }

    public static void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList) {
        Api21Impl.setButtonTintList(compoundButton, colorStateList);
    }

    public static void setButtonTintMode(CompoundButton compoundButton, PorterDuff.Mode mode) {
        Api21Impl.setButtonTintMode(compoundButton, mode);
    }
}
