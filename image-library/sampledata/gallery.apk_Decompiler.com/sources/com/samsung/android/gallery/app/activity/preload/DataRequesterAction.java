package com.samsung.android.gallery.app.activity.preload;

import com.samsung.android.gallery.app.activity.preload.abstraction.IRequesterAction;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
enum DataRequesterAction implements IRequesterAction {
    ;
    
    private static final String TAG = "DataRequesterAction";
    private final String mLocationKey;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.activity.preload.DataRequesterAction$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends DataRequesterAction {
        public /* synthetic */ AnonymousClass1() {
            this("TIMELINE", 0, "location://timeline");
        }

        public void run(Blackboard blackboard, String str) {
            publishDataTimelineFake(blackboard, str);
        }

        private AnonymousClass1(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.activity.preload.DataRequesterAction$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends DataRequesterAction {
        public /* synthetic */ AnonymousClass2() {
            this("ALBUMS", 1, "location://albums");
        }

        public void run(Blackboard blackboard, String str) {
            publishDataAlbumsCache(blackboard, str);
        }

        private AnonymousClass2(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.activity.preload.DataRequesterAction$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends DataRequesterAction {
        public /* synthetic */ AnonymousClass3() {
            this("MTP", 2, "location://mtp");
        }

        public void run(Blackboard blackboard, String str) {
            publishDataAfterErasing(blackboard, str);
        }

        private AnonymousClass3(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.activity.preload.DataRequesterAction$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends DataRequesterAction {
        public /* synthetic */ AnonymousClass4() {
            this("SHARINGS", 3, "location://sharing/albums");
        }

        public void run(Blackboard blackboard, String str) {
            publishDataWithSharingGroup(blackboard);
        }

        private AnonymousClass4(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.activity.preload.DataRequesterAction$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends DataRequesterAction {
        public /* synthetic */ AnonymousClass5() {
            this("SHARINGS_CHOICE", 4, "location://sharing/choice");
        }

        public void run(Blackboard blackboard, String str) {
            publishDataWithSharingGroup(blackboard);
        }

        private AnonymousClass5(String str, int i2, String str2) {
            super(str, i2, str2, 0);
        }
    }

    public static DataRequesterAction getAction(String str) {
        return (DataRequesterAction) Arrays.stream(values()).filter(new a(str)).findFirst().orElse((Object) null);
    }

    private String getLocationKey() {
        return this.mLocationKey;
    }

    private boolean isAlbumFirstTabLoading() {
        return LocationKey.isAlbumsMatch(LocationKey.getCurrentLocation());
    }

    private boolean isFromBixby(Blackboard blackboard) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Optional.ofNullable(blackboard.read("data://launch_intent")).ifPresent(new b(atomicBoolean));
        return atomicBoolean.get();
    }

    private void publishDataByLocationKey(Blackboard blackboard, String str) {
        BlackboardUtils.publishDataRequest(blackboard, str);
    }

    public void publishDataAfterErasing(Blackboard blackboard, String str) {
        blackboard.erase(DataKey.DATA_CURSOR(getLocationKey()));
        publishDataByLocationKey(blackboard, str);
    }

    public void publishDataAlbumsCache(Blackboard blackboard, String str) {
        if (!blackboard.isEmpty(CommandKey.DATA_REQUEST("location://albums/cache")) || (isAlbumFirstTabLoading() && !isFromBixby(blackboard))) {
            Log.d(TAG, "publishDataAlbumsCache skip while cache-requesting.");
        } else {
            publishDataByLocationKey(blackboard, str);
        }
    }

    public void publishDataTimelineFake(Blackboard blackboard, String str) {
        if (blackboard.isEmpty(CommandKey.DATA_REQUEST("location://timeline/fake"))) {
            publishDataByLocationKey(blackboard, str);
        } else {
            Log.d(TAG, "publishDataTimelineFake skip while cache-requesting.");
        }
    }

    public void publishDataWithSharingGroup(Blackboard blackboard) {
        publishDataByLocationKey(blackboard, getLocationKey());
        publishDataByLocationKey(blackboard, "location://sharing/groups");
    }

    private DataRequesterAction(String str) {
        this.mLocationKey = str;
    }
}
