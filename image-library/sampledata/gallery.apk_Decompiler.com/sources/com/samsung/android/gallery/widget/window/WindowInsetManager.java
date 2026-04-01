package com.samsung.android.gallery.widget.window;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import i4.C0468a;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WindowInsetManager {
    private final HashMap<Integer, Object> mMap = new HashMap<>();

    public static WindowInsetManager getInstance(Blackboard blackboard) {
        return (WindowInsetManager) blackboard.computeIfAbsent("WindowInsetManager", new C0468a(23));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ WindowInsetManager lambda$getInstance$0(String str) {
        return new WindowInsetManager();
    }

    public void clear() {
        this.mMap.clear();
    }

    public String toString() {
        return "WindowInsetManager{" + this.mMap.size() + '}';
    }
}
