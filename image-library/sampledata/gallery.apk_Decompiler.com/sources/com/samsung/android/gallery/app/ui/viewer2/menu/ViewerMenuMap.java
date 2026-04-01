package com.samsung.android.gallery.app.ui.viewer2.menu;

import A5.a;
import C3.C0392b;
import android.util.SparseArray;
import android.view.View;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerMenuMap {
    private final ConcurrentHashMap<String, List<ViewerMenuItem>> mLocationMap = new ConcurrentHashMap<>();
    private final ArrayList<ViewerMenuItem> mViewerMenuList = new ArrayList<>();
    private final SparseArray<ViewerMenuItem> mViewerMenuMap = new SparseArray<>();

    /* access modifiers changed from: private */
    public /* synthetic */ List lambda$get$1(String str) {
        return (List) this.mViewerMenuList.stream().filter(new C0392b(str, 10)).collect(Collectors.toList());
    }

    public void addMenuItem(ViewerMenuItem... viewerMenuItemArr) {
        this.mViewerMenuList.addAll(Arrays.asList(viewerMenuItemArr));
        for (ViewerMenuItem viewerMenuItem : viewerMenuItemArr) {
            this.mViewerMenuMap.put(viewerMenuItem.getMenuId(), viewerMenuItem);
        }
    }

    public List<ViewerMenuItem> get(String str) {
        return this.mLocationMap.computeIfAbsent(str, new a(21, this));
    }

    public ViewerMenuItem getMenuItem(int i2) {
        return this.mViewerMenuMap.get(i2);
    }

    public boolean onMenuItemSelected(int i2, View view) {
        ViewerMenuItem viewerMenuItem = this.mViewerMenuMap.get(i2);
        if (viewerMenuItem == null) {
            return false;
        }
        Log.d(viewerMenuItem.TAG, "onMenuSelect");
        return viewerMenuItem.onMenuSelect(view);
    }
}
