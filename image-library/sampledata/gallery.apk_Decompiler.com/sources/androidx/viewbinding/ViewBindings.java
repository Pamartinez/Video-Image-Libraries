package androidx.viewbinding;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewBindings {
    public static <T extends View> T findChildViewById(View view, int i2) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            T findViewById = viewGroup.getChildAt(i7).findViewById(i2);
            if (findViewById != null) {
                return findViewById;
            }
        }
        return null;
    }
}
