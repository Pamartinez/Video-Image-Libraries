package com.samsung.android.gallery.app.controller.externals;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum WallpaperType {
    ;
    
    private final int mResId;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends WallpaperType {
        public /* synthetic */ AnonymousClass1() {
            this("LOCK_SCREEN", 0, R.string.lock_screen);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            SetWallpaperCmd setWallpaperCmd = new SetWallpaperCmd();
            int length = mediaItemArr.length;
            MediaItem[] mediaItemArr2 = mediaItemArr;
            if (length <= 1) {
                mediaItemArr2 = mediaItemArr[0];
            }
            setWallpaperCmd.execute(eventContext, mediaItemArr2, 2);
        }

        private AnonymousClass1(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends WallpaperType {
        public /* synthetic */ AnonymousClass2() {
            this("HOME_SCREEN", 1, R.string.home_screen);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetWallpaperCmd().execute(eventContext, mediaItemArr[0], 1);
        }

        private AnonymousClass2(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends WallpaperType {
        public /* synthetic */ AnonymousClass3() {
            this("LOCK_AND_HOME_SCREEN", 2, R.string.lock_home_screen);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetWallpaperCmd().execute(eventContext, mediaItemArr[0], 3);
        }

        private AnonymousClass3(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends WallpaperType {
        public /* synthetic */ AnonymousClass4() {
            this("ALWAYS_ON_DISPLAY", 3, R.string.always_on_display_image);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetAlwaysOnDisplayCmd().execute(eventContext, mediaItemArr[0]);
        }

        private AnonymousClass4(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$5  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass5 extends WallpaperType {
        public /* synthetic */ AnonymousClass5() {
            this("CALL_BACKGROUND", 4, R.string.call_background);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetCallBackgroundCmd().execute(eventContext, mediaItemArr[0]);
        }

        private AnonymousClass5(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$6  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass6 extends WallpaperType {
        public /* synthetic */ AnonymousClass6() {
            this("WATCH_FACE", 5, R.string.watch_face);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetWatchFaceCmd().execute(eventContext, mediaItemArr[0]);
        }

        private AnonymousClass6(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$7  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass7 extends WallpaperType {
        public /* synthetic */ AnonymousClass7() {
            this("COVER_SCREEN", 6, R.string.cover_screen);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetCoverScreenCmd().execute(eventContext, mediaItemArr);
        }

        private AnonymousClass7(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$8  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass8 extends WallpaperType {
        public /* synthetic */ AnonymousClass8() {
            this("CLEAR_COVER_SCREEN", 7, R.string.cover_screen);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetClearCoverScreenCmd().execute(eventContext, mediaItemArr);
        }

        private AnonymousClass8(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.controller.externals.WallpaperType$9  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass9 extends WallpaperType {
        public /* synthetic */ AnonymousClass9() {
            this("ALARM", 8, R.string.alarm_background);
        }

        public void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr) {
            new SetAlarmBackgroundCmd().execute(eventContext, mediaItemArr[0]);
        }

        private AnonymousClass9(String str, int i2, int i7) {
            super(str, i2, i7, 0);
        }
    }

    public abstract void onExecuteCmd(EventContext eventContext, MediaItem[] mediaItemArr);

    public int value() {
        return this.mResId;
    }

    private WallpaperType(int i2) {
        this.mResId = i2;
    }
}
