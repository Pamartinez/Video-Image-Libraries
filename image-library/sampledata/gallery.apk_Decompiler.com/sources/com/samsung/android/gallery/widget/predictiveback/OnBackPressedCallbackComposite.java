package com.samsung.android.gallery.widget.predictiveback;

import androidx.activity.BackEventCompat;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnBackPressedCallbackComposite implements FragmentManager.SeslOnBackPressedCallback {
    private final ArrayList<FragmentManager.SeslOnBackPressedCallback> list = new ArrayList<>();

    public void addIfAbsent(FragmentManager.SeslOnBackPressedCallback seslOnBackPressedCallback) {
        if (seslOnBackPressedCallback != null && !this.list.contains(seslOnBackPressedCallback)) {
            this.list.add(seslOnBackPressedCallback);
        }
    }

    public void handleOnBackCancelled() {
        Iterator<FragmentManager.SeslOnBackPressedCallback> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().handleOnBackCancelled();
        }
    }

    public void handleOnBackPressed() {
        Iterator<FragmentManager.SeslOnBackPressedCallback> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().handleOnBackPressed();
        }
    }

    public void handleOnBackProgressed(BackEventCompat backEventCompat) {
        Iterator<FragmentManager.SeslOnBackPressedCallback> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().handleOnBackProgressed(backEventCompat);
        }
    }

    public void handleOnBackStarted(BackEventCompat backEventCompat) {
        Iterator<FragmentManager.SeslOnBackPressedCallback> it = this.list.iterator();
        while (it.hasNext()) {
            it.next().handleOnBackStarted(backEventCompat);
        }
    }

    public void remove(FragmentManager.SeslOnBackPressedCallback seslOnBackPressedCallback) {
        this.list.remove(seslOnBackPressedCallback);
    }
}
