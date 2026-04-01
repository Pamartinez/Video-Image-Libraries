package com.samsung.android.gallery.widget.behavior;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface SheetBehaviorCompat {
    static boolean isClosed(int i2) {
        if (i2 == 4 || i2 == 5) {
            return true;
        }
        return false;
    }

    static boolean isCollapsed(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    static boolean isDragging(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    static boolean isExpanded(int i2) {
        if (i2 == 3) {
            return true;
        }
        return false;
    }

    static boolean isHidden(int i2) {
        if (i2 == 5) {
            return true;
        }
        return false;
    }

    static boolean isInExpanded(int i2) {
        if (i2 == 3 || i2 == 6) {
            return true;
        }
        return false;
    }

    static boolean isInTransition(int i2) {
        if (i2 == 2 || i2 == 1) {
            return true;
        }
        return false;
    }

    static boolean isSettling(int i2) {
        if (i2 == 2) {
            return true;
        }
        return false;
    }
}
