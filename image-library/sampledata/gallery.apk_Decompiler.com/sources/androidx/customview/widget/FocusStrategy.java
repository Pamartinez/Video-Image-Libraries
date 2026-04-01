package androidx.customview.widget;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FocusStrategy {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BoundsAdapter<T> {
        void obtainBounds(T t, Rect rect);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CollectionAdapter<T, V> {
        V get(T t, int i2);

        int size(T t);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SequentialComparator<T> implements Comparator<T> {
        private final BoundsAdapter<T> mAdapter;
        private final boolean mIsLayoutRtl;
        private final Rect mTemp1 = new Rect();
        private final Rect mTemp2 = new Rect();

        public SequentialComparator(boolean z, BoundsAdapter<T> boundsAdapter) {
            this.mIsLayoutRtl = z;
            this.mAdapter = boundsAdapter;
        }

        public int compare(T t, T t3) {
            Rect rect = this.mTemp1;
            Rect rect2 = this.mTemp2;
            this.mAdapter.obtainBounds(t, rect);
            this.mAdapter.obtainBounds(t3, rect2);
            int i2 = rect.top;
            int i7 = rect2.top;
            if (i2 < i7) {
                return -1;
            }
            if (i2 > i7) {
                return 1;
            }
            int i8 = rect.left;
            int i10 = rect2.left;
            if (i8 < i10) {
                if (this.mIsLayoutRtl) {
                    return 1;
                }
                return -1;
            } else if (i8 <= i10) {
                int i11 = rect.bottom;
                int i12 = rect2.bottom;
                if (i11 < i12) {
                    return -1;
                }
                if (i11 > i12) {
                    return 1;
                }
                int i13 = rect.right;
                int i14 = rect2.right;
                if (i13 < i14) {
                    if (this.mIsLayoutRtl) {
                        return 1;
                    }
                    return -1;
                } else if (i13 <= i14) {
                    return 0;
                } else {
                    if (this.mIsLayoutRtl) {
                        return -1;
                    }
                    return 1;
                }
            } else if (this.mIsLayoutRtl) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static boolean beamBeats(int i2, Rect rect, Rect rect2, Rect rect3) {
        boolean beamsOverlap = beamsOverlap(i2, rect, rect2);
        if (beamsOverlap(i2, rect, rect3) || !beamsOverlap) {
            return false;
        }
        if (isToDirectionOf(i2, rect, rect3) && i2 != 17 && i2 != 66 && majorAxisDistance(i2, rect, rect2) >= majorAxisDistanceToFarEdge(i2, rect, rect3)) {
            return false;
        }
        return true;
    }

    private static boolean beamsOverlap(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            if (rect2.right < rect.left || rect2.left > rect.right) {
                return false;
            }
            return true;
        }
        if (rect2.bottom < rect.top || rect2.top > rect.bottom) {
            return false;
        }
        return true;
    }

    public static <L, T> T findNextFocusInAbsoluteDirection(L l, CollectionAdapter<L, T> collectionAdapter, BoundsAdapter<T> boundsAdapter, T t, Rect rect, int i2) {
        Rect rect2 = new Rect(rect);
        if (i2 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (i2 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (i2 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else if (i2 == 130) {
            rect2.offset(0, -(rect.height() + 1));
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int size = collectionAdapter.size(l);
        Rect rect3 = new Rect();
        T t3 = null;
        for (int i7 = 0; i7 < size; i7++) {
            T t5 = collectionAdapter.get(l, i7);
            if (t5 != t) {
                boundsAdapter.obtainBounds(t5, rect3);
                if (isBetterCandidate(i2, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    t3 = t5;
                }
            }
        }
        return t3;
    }

    public static <L, T> T findNextFocusInRelativeDirection(L l, CollectionAdapter<L, T> collectionAdapter, BoundsAdapter<T> boundsAdapter, T t, int i2, boolean z, boolean z3) {
        int size = collectionAdapter.size(l);
        ArrayList arrayList = new ArrayList(size);
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.add(collectionAdapter.get(l, i7));
        }
        Collections.sort(arrayList, new SequentialComparator(z, boundsAdapter));
        if (i2 == 1) {
            return getPreviousFocusable(t, arrayList, z3);
        }
        if (i2 == 2) {
            return getNextFocusable(t, arrayList, z3);
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    private static <T> T getNextFocusable(T t, ArrayList<T> arrayList, boolean z) {
        int i2;
        int size = arrayList.size();
        if (t == null) {
            i2 = -1;
        } else {
            i2 = arrayList.lastIndexOf(t);
        }
        int i7 = i2 + 1;
        if (i7 < size) {
            return arrayList.get(i7);
        }
        if (!z || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    private static <T> T getPreviousFocusable(T t, ArrayList<T> arrayList, boolean z) {
        int i2;
        int size = arrayList.size();
        if (t == null) {
            i2 = size;
        } else {
            i2 = arrayList.indexOf(t);
        }
        int i7 = i2 - 1;
        if (i7 >= 0) {
            return arrayList.get(i7);
        }
        if (!z || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    private static int getWeightedDistanceFor(int i2, int i7) {
        return (i7 * i7) + (i2 * 13 * i2);
    }

    private static boolean isBetterCandidate(int i2, Rect rect, Rect rect2, Rect rect3) {
        if (!isCandidate(rect, rect2, i2)) {
            return false;
        }
        if (!isCandidate(rect, rect3, i2) || beamBeats(i2, rect, rect2, rect3)) {
            return true;
        }
        if (!beamBeats(i2, rect, rect3, rect2) && getWeightedDistanceFor(majorAxisDistance(i2, rect, rect2), minorAxisDistance(i2, rect, rect2)) < getWeightedDistanceFor(majorAxisDistance(i2, rect, rect3), minorAxisDistance(i2, rect, rect3))) {
            return true;
        }
        return false;
    }

    private static boolean isCandidate(Rect rect, Rect rect2, int i2) {
        if (i2 == 17) {
            int i7 = rect.right;
            int i8 = rect2.right;
            if ((i7 > i8 || rect.left >= i8) && rect.left > rect2.left) {
                return true;
            }
            return false;
        } else if (i2 == 33) {
            int i10 = rect.bottom;
            int i11 = rect2.bottom;
            if ((i10 > i11 || rect.top >= i11) && rect.top > rect2.top) {
                return true;
            }
            return false;
        } else if (i2 == 66) {
            int i12 = rect.left;
            int i13 = rect2.left;
            if ((i12 < i13 || rect.right <= i13) && rect.right < rect2.right) {
                return true;
            }
            return false;
        } else if (i2 == 130) {
            int i14 = rect.top;
            int i15 = rect2.top;
            if ((i14 < i15 || rect.bottom <= i15) && rect.bottom < rect2.bottom) {
                return true;
            }
            return false;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
    }

    private static boolean isToDirectionOf(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    } else if (rect.bottom <= rect2.top) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (rect.right <= rect2.left) {
                    return true;
                } else {
                    return false;
                }
            } else if (rect.top >= rect2.bottom) {
                return true;
            } else {
                return false;
            }
        } else if (rect.left >= rect2.right) {
            return true;
        } else {
            return false;
        }
    }

    private static int majorAxisDistance(int i2, Rect rect, Rect rect2) {
        return Math.max(0, majorAxisDistanceRaw(i2, rect, rect2));
    }

    private static int majorAxisDistanceRaw(int i2, Rect rect, Rect rect2) {
        int i7;
        int i8;
        if (i2 == 17) {
            i7 = rect.left;
            i8 = rect2.right;
        } else if (i2 == 33) {
            i7 = rect.top;
            i8 = rect2.bottom;
        } else if (i2 == 66) {
            i7 = rect2.left;
            i8 = rect.right;
        } else if (i2 == 130) {
            i7 = rect2.top;
            i8 = rect.bottom;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return i7 - i8;
    }

    private static int majorAxisDistanceToFarEdge(int i2, Rect rect, Rect rect2) {
        return Math.max(1, majorAxisDistanceToFarEdgeRaw(i2, rect, rect2));
    }

    private static int majorAxisDistanceToFarEdgeRaw(int i2, Rect rect, Rect rect2) {
        int i7;
        int i8;
        if (i2 == 17) {
            i7 = rect.left;
            i8 = rect2.left;
        } else if (i2 == 33) {
            i7 = rect.top;
            i8 = rect2.top;
        } else if (i2 == 66) {
            i7 = rect2.right;
            i8 = rect.right;
        } else if (i2 == 130) {
            i7 = rect2.bottom;
            i8 = rect.bottom;
        } else {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        return i7 - i8;
    }

    private static int minorAxisDistance(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs(((rect.width() / 2) + rect.left) - ((rect2.width() / 2) + rect2.left));
        }
        return Math.abs(((rect.height() / 2) + rect.top) - ((rect2.height() / 2) + rect2.top));
    }
}
