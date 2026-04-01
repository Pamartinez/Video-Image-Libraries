package z2;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements View.OnTouchListener {
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        View view2;
        if (!(view instanceof ViewGroup)) {
            return true;
        }
        float x9 = motionEvent.getX();
        float y = motionEvent.getY();
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount() - 1;
        while (true) {
            if (childCount < 0) {
                view2 = null;
                break;
            }
            view2 = viewGroup.getChildAt(childCount);
            float x10 = view2.getX();
            float y8 = view2.getY();
            float width = ((float) view2.getWidth()) + x10;
            float height = ((float) view2.getHeight()) + y8;
            if (x9 >= x10 && y >= y8 && x9 < width && y < height) {
                break;
            }
            childCount--;
        }
        if (view2 != null) {
            return true;
        }
        return false;
    }
}
