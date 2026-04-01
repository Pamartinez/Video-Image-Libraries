package com.samsung.android.gallery.module.screenshotfilter;

import O3.o;
import android.database.Cursor;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScreenShotFilterManager {
    private String mFilteredScene = ScreenShotFilterType.All.key();

    private ScreenShotFilterManager() {
    }

    public static ScreenShotFilterManager getInstance(Blackboard blackboard) {
        return (ScreenShotFilterManager) blackboard.computeIfAbsent("data://ScreenShotFilterManager", new o(27));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ScreenShotFilterManager lambda$getInstance$0(String str) {
        return new ScreenShotFilterManager();
    }

    public String getFilteredScene() {
        return this.mFilteredScene;
    }

    public boolean isFiltered(String str) {
        return this.mFilteredScene.equals(str);
    }

    public boolean needResetFilteredList(Cursor[] cursorArr) {
        if (ScreenShotFilterType.All.key().equals(this.mFilteredScene)) {
            return false;
        }
        for (Cursor cursor : cursorArr) {
            if (cursor != null && cursor.getCount() != 0) {
                return false;
            }
        }
        return true;
    }

    public void release() {
        this.mFilteredScene = ScreenShotFilterType.All.key();
    }

    public void resetFilter() {
        this.mFilteredScene = ScreenShotFilterType.All.key();
    }

    public void setFilteredScene(String str) {
        this.mFilteredScene = str;
    }
}
