package androidx.appcompat.util;

import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.reflect.SeslBaseReflector;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeslShapeDrawable extends GradientDrawable {
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        Method declaredMethod = SeslBaseReflector.getDeclaredMethod(GradientDrawable.class, "setSmoothCorner", (Class<?>[]) new Class[]{Boolean.TYPE});
        if (declaredMethod == null) {
            Log.w("SeslShapeDrawable", "This API is not supported by the platform.");
        } else {
            SeslBaseReflector.invoke(this, declaredMethod, Boolean.TRUE);
        }
    }
}
