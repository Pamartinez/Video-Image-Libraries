package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import h4.C0464a;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TabItemEnableCondition {
    private final Blackboard mBlackboard;
    private final Runnable mChangeNotifier;
    private Predicate<String> mEnableCondition = new m(4);
    private Predicate<String> mExpendableCondition = new m(5);

    public TabItemEnableCondition(Blackboard blackboard, Runnable runnable) {
        this.mBlackboard = blackboard;
        this.mChangeNotifier = runnable;
    }

    private String getCurrentKey() {
        return (String) this.mBlackboard.read("location://variable/currentv1");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$0(String str) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$new$1(String str) {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onDragEnded$11(boolean z, String str) {
        if (!z) {
            return true;
        }
        if (!LocationKey.isAlbumPictures(str) || !LocationKey.isAlbumPictures(getCurrentKey()) || LocationKey.isMxVirtualAlbum(str) || LocationKey.isMxVirtualAlbum(getCurrentKey())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onDragEnded$12(boolean z, String str) {
        if (!z) {
            return true;
        }
        if (!LocationKey.isAlbumPictures(str) || LocationKey.isMxVirtualAlbum(str) || LocationKey.isMxVirtualAlbum(getCurrentKey())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onDragStarted$10(String str) {
        if ((LocationKey.isAlbumPictures(str) || LocationKey.isTimeline(str)) && !LocationKey.isMxVirtualAlbum(getCurrentKey())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onDragStarted$9(String str) {
        if (!LocationKey.isAlbumPictures(str) || LocationKey.isMxVirtualAlbum(str)) {
            return false;
        }
        if ((LocationKey.isAlbumPictures(getCurrentKey()) || LocationKey.isTimeline(getCurrentKey())) && !LocationKey.isMxVirtualAlbum(getCurrentKey())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onEnterMoveMode$5(String str) {
        if (LocationKey.isFolder(str) || LocationKey.isAlbumsMatch(str) || LocationKey.isAllAlbumsMatch(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onEnterMoveMode$6(String str) {
        if (LocationKey.isFolder(str) || LocationKey.isAlbumsMatch(str) || LocationKey.isAllAlbumsMatch(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$onEnterSelectionMode$2(String str) {
        if (!LocationKey.isAlbumPictures(str) || !LocationKey.isAlbumPictures(getCurrentKey()) || LocationKey.isMxVirtualAlbum(getCurrentKey()) || LocationKey.isMxVirtualAlbum(str)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onExitMoveMode$7(String str) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onExitMoveMode$8(String str) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onExitSelectionMode$3(String str) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$onExitSelectionMode$4(String str) {
        return true;
    }

    public boolean isEnabled(String str) {
        return this.mEnableCondition.test(str);
    }

    public boolean isExpandable() {
        return this.mExpendableCondition.test(getCurrentKey());
    }

    public void onDragEnded(boolean z) {
        this.mEnableCondition = new l(this, z, 0);
        this.mExpendableCondition = new l(this, z, 1);
        this.mChangeNotifier.run();
    }

    public void onDragStarted() {
        this.mEnableCondition = new k(this, 1);
        this.mExpendableCondition = new k(this, 2);
        this.mChangeNotifier.run();
    }

    public void onEnterMoveMode() {
        this.mEnableCondition = new m(0);
        this.mExpendableCondition = new m(1);
        this.mChangeNotifier.run();
    }

    public void onEnterSelectionMode() {
        this.mEnableCondition = new k(this, 0);
        this.mExpendableCondition = new C0464a(20);
        this.mChangeNotifier.run();
    }

    public void onExitMoveMode() {
        this.mEnableCondition = new m(6);
        this.mExpendableCondition = new m(7);
        this.mChangeNotifier.run();
    }

    public void onExitSelectionMode() {
        this.mEnableCondition = new m(2);
        this.mExpendableCondition = new m(3);
        this.mChangeNotifier.run();
    }
}
