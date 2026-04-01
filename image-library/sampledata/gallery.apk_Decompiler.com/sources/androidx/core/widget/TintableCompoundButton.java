package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface TintableCompoundButton {
    ColorStateList getSupportButtonTintList();

    void setSupportButtonTintList(ColorStateList colorStateList);

    void setSupportButtonTintMode(PorterDuff.Mode mode);
}
