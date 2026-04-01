package androidx.fragment.app;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.os.CancellationSignal;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FragmentTransitionImpl {
    public static void bfsAddViewChildren(List<View> list, View view) {
        int size = list.size();
        if (!containedBeforeIndex(list, view, size)) {
            if (ViewCompat.getTransitionName(view) != null) {
                list.add(view);
            }
            for (int i2 = size; i2 < list.size(); i2++) {
                View view2 = list.get(i2);
                if (view2 instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view2;
                    int childCount = viewGroup.getChildCount();
                    for (int i7 = 0; i7 < childCount; i7++) {
                        View childAt = viewGroup.getChildAt(i7);
                        if (!containedBeforeIndex(list, childAt, size) && ViewCompat.getTransitionName(childAt) != null) {
                            list.add(childAt);
                        }
                    }
                }
            }
        }
    }

    private static boolean containedBeforeIndex(List<View> list, View view, int i2) {
        for (int i7 = 0; i7 < i2; i7++) {
            if (list.get(i7) == view) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNullOrEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public abstract void addTarget(Object obj, View view);

    public abstract void addTargets(Object obj, ArrayList<View> arrayList);

    public abstract void beginDelayedTransition(ViewGroup viewGroup, Object obj);

    public abstract boolean canHandle(Object obj);

    public abstract Object cloneTransition(Object obj);

    public Object controlDelayedTransition(ViewGroup viewGroup, Object obj) {
        return null;
    }

    public void getBoundsOnScreen(View view, Rect rect) {
        if (view.isAttachedToWindow()) {
            RectF rectF = new RectF();
            rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
            view.getMatrix().mapRect(rectF);
            rectF.offset((float) view.getLeft(), (float) view.getTop());
            ViewParent parent = view.getParent();
            while (parent instanceof View) {
                View view2 = (View) parent;
                rectF.offset((float) (-view2.getScrollX()), (float) (-view2.getScrollY()));
                view2.getMatrix().mapRect(rectF);
                rectF.offset((float) view2.getLeft(), (float) view2.getTop());
                parent = view2.getParent();
            }
            int[] iArr = new int[2];
            view.getRootView().getLocationOnScreen(iArr);
            rectF.offset((float) iArr[0], (float) iArr[1]);
            rect.set(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
        }
    }

    public abstract boolean isSeekingSupported();

    public abstract boolean isSeekingSupported(Object obj);

    public abstract Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3);

    public abstract Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3);

    public ArrayList<String> prepareSetNameOverridesReordered(ArrayList<View> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = arrayList.get(i2);
            arrayList2.add(ViewCompat.getTransitionName(view));
            ViewCompat.setTransitionName(view, (String) null);
        }
        return arrayList2;
    }

    public abstract void scheduleHideFragmentView(Object obj, View view, ArrayList<View> arrayList);

    public abstract void scheduleRemoveTargets(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3);

    public abstract void setEpicenter(Object obj, Rect rect);

    public abstract void setEpicenter(Object obj, View view);

    public abstract void setListenerForTransitionEnd(Fragment fragment, Object obj, CancellationSignal cancellationSignal, Runnable runnable);

    public void setListenerForTransitionEnd(Fragment fragment, Object obj, CancellationSignal cancellationSignal, Runnable runnable, Runnable runnable2) {
        runnable2.run();
    }

    public void setNameOverridesReordered(View view, ArrayList<View> arrayList, ArrayList<View> arrayList2, ArrayList<String> arrayList3, Map<String, String> map) {
        final int size = arrayList2.size();
        final ArrayList arrayList4 = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = arrayList.get(i2);
            String transitionName = ViewCompat.getTransitionName(view2);
            arrayList4.add(transitionName);
            if (transitionName != null) {
                ViewCompat.setTransitionName(view2, (String) null);
                String str = map.get(transitionName);
                int i7 = 0;
                while (true) {
                    if (i7 >= size) {
                        break;
                    } else if (str.equals(arrayList3.get(i7))) {
                        ViewCompat.setTransitionName(arrayList2.get(i7), transitionName);
                        break;
                    } else {
                        i7++;
                    }
                }
            }
        }
        final ArrayList<View> arrayList5 = arrayList;
        final ArrayList<View> arrayList6 = arrayList2;
        final ArrayList<String> arrayList7 = arrayList3;
        OneShotPreDrawListener.add(view, new Runnable() {
            public void run() {
                for (int i2 = 0; i2 < size; i2++) {
                    ViewCompat.setTransitionName((View) arrayList6.get(i2), (String) arrayList7.get(i2));
                    ViewCompat.setTransitionName((View) arrayList5.get(i2), (String) arrayList4.get(i2));
                }
            }
        });
    }

    public abstract void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList);

    public abstract void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2);

    public abstract Object wrapTransitionInSet(Object obj);

    public void animateToEnd(Object obj) {
    }

    public void animateToStart(Object obj, Runnable runnable) {
    }

    public void setCurrentPlayTime(Object obj, float f) {
    }
}
