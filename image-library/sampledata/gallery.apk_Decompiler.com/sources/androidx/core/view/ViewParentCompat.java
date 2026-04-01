package androidx.core.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ViewParentCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f5, boolean z) {
            return viewParent.onNestedFling(view, f, f5, z);
        }

        public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f5) {
            return viewParent.onNestedPreFling(view, f, f5);
        }

        public static void onNestedPreScroll(ViewParent viewParent, View view, int i2, int i7, int[] iArr) {
            viewParent.onNestedPreScroll(view, i2, i7, iArr);
        }

        public static void onNestedScroll(ViewParent viewParent, View view, int i2, int i7, int i8, int i10) {
            viewParent.onNestedScroll(view, i2, i7, i8, i10);
        }

        public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i2) {
            viewParent.onNestedScrollAccepted(view, view2, i2);
        }

        public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i2) {
            return viewParent.onStartNestedScroll(view, view2, i2);
        }

        public static void onStopNestedScroll(ViewParent viewParent, View view) {
            viewParent.onStopNestedScroll(view);
        }
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f5, boolean z) {
        try {
            return Api21Impl.onNestedFling(viewParent, view, f, f5, z);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e);
            return false;
        }
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f5) {
        try {
            return Api21Impl.onNestedPreFling(viewParent, view, f, f5);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e);
            return false;
        }
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i2, int i7, int[] iArr, int i8) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedPreScroll(view, i2, i7, iArr, i8);
        } else if (i8 == 0) {
            try {
                Api21Impl.onNestedPreScroll(viewParent, view, i2, i7, iArr);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e);
            }
        }
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i2, int i7, int i8, int i10, int i11, int[] iArr) {
        if (viewParent instanceof NestedScrollingParent3) {
            ((NestedScrollingParent3) viewParent).onNestedScroll(view, i2, i7, i8, i10, i11, iArr);
            return;
        }
        int i12 = i2;
        View view2 = view;
        int[] iArr2 = iArr;
        int i13 = i11;
        int i14 = i10;
        int i15 = i8;
        int i16 = i7;
        int i17 = i12;
        iArr2[0] = iArr2[0] + i15;
        iArr2[1] = iArr2[1] + i14;
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScroll(view2, i17, i16, i15, i14, i13);
        } else if (i13 == 0) {
            ViewParent viewParent2 = viewParent;
            try {
                Api21Impl.onNestedScroll(viewParent2, view2, i17, i16, i15, i14);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent2 + " does not implement interface method onNestedScroll", e);
            }
        }
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i2, int i7) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onNestedScrollAccepted(view, view2, i2, i7);
        } else if (i7 == 0) {
            try {
                Api21Impl.onNestedScrollAccepted(viewParent, view, view2, i2);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e);
            }
        }
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i2, int i7) {
        if (viewParent instanceof NestedScrollingParent2) {
            return ((NestedScrollingParent2) viewParent).onStartNestedScroll(view, view2, i2, i7);
        }
        if (i7 != 0) {
            return false;
        }
        try {
            return Api21Impl.onStartNestedScroll(viewParent, view, view2, i2);
        } catch (AbstractMethodError e) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e);
            return false;
        }
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view, int i2) {
        if (viewParent instanceof NestedScrollingParent2) {
            ((NestedScrollingParent2) viewParent).onStopNestedScroll(view, i2);
        } else if (i2 == 0) {
            try {
                Api21Impl.onStopNestedScroll(viewParent, view);
            } catch (AbstractMethodError e) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e);
            }
        }
    }
}
