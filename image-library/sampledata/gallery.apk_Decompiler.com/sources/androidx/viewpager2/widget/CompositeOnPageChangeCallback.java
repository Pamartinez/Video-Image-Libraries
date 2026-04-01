package androidx.viewpager2.widget;

import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class CompositeOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
    private final List<ViewPager2.OnPageChangeCallback> mCallbacks;

    public CompositeOnPageChangeCallback(int i2) {
        this.mCallbacks = new ArrayList(i2);
    }

    private void throwCallbackListModifiedWhileInUse(ConcurrentModificationException concurrentModificationException) {
        throw new IllegalStateException("Adding and removing callbacks during dispatch to callbacks is not supported", concurrentModificationException);
    }

    public void addOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallbacks.add(onPageChangeCallback);
    }

    public void onPageScrollStateChanged(int i2) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageScrollStateChanged : this.mCallbacks) {
                onPageScrollStateChanged.onPageScrollStateChanged(i2);
            }
        } catch (ConcurrentModificationException e) {
            throwCallbackListModifiedWhileInUse(e);
        }
    }

    public void onPageScrolled(int i2, float f, int i7) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageScrolled : this.mCallbacks) {
                onPageScrolled.onPageScrolled(i2, f, i7);
            }
        } catch (ConcurrentModificationException e) {
            throwCallbackListModifiedWhileInUse(e);
        }
    }

    public void onPageSelected(int i2) {
        try {
            for (ViewPager2.OnPageChangeCallback onPageSelected : this.mCallbacks) {
                onPageSelected.onPageSelected(i2);
            }
        } catch (ConcurrentModificationException e) {
            throwCallbackListModifiedWhileInUse(e);
        }
    }

    public void removeOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallbacks.remove(onPageChangeCallback);
    }
}
