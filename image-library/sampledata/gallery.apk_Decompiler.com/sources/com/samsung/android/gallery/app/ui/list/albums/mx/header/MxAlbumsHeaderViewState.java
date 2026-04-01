package com.samsung.android.gallery.app.ui.list.albums.mx.header;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSecMPSettingsCmd;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum MxAlbumsHeaderViewState {
    ;

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass1 extends MxAlbumsHeaderViewState {
        public /* synthetic */ AnonymousClass1() {
            this("CUSTOMIZE_ESSENTIAL_ALBUM", 0);
        }

        public void onAcceptClicked(EventContext eventContext) {
            eventContext.getBlackboard().post("command://MoveURL", "location://albums/manage");
        }

        public void onTipCardClicked(EventContext eventContext) {
            PreferenceCache.MxAlbumTipCard.setBoolean(false);
        }

        private AnonymousClass1(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass2 extends MxAlbumsHeaderViewState {
        public /* synthetic */ AnonymousClass2() {
            this("TURN_OFF_ESSENTIAL_ALBUM", 1);
        }

        public void onAcceptClicked(EventContext eventContext) {
            new StartSettingsCmd().execute(eventContext, SettingPreference.EssentialAlbum.key, PopoverUtils.Anchor.TOP_END);
        }

        public void onTipCardClicked(EventContext eventContext) {
            PreferenceCache.MxAlbumTipCardCount.setInt(-1);
        }

        private AnonymousClass2(String str, int i2) {
            super(str, i2, 0);
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass3 extends MxAlbumsHeaderViewState {
        public /* synthetic */ AnonymousClass3() {
            this("INVITATION", 2);
        }

        private AnonymousClass3(String str, int i2) {
            super(str, i2, 0);
        }

        public void onAcceptClicked(EventContext eventContext) {
        }

        public void onTipCardClicked(EventContext eventContext) {
        }
    }

    /* 'enum' modifier removed */
    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderViewState$4  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class AnonymousClass4 extends MxAlbumsHeaderViewState {
        public /* synthetic */ AnonymousClass4() {
            this("SEC_MP_PERMISSION", 3);
        }

        public void onAcceptClicked(EventContext eventContext) {
            new StartSecMPSettingsCmd().execute(eventContext, new Object[0]);
        }

        private AnonymousClass4(String str, int i2) {
            super(str, i2, 0);
        }

        public void onTipCardClicked(EventContext eventContext) {
        }
    }

    public abstract void onAcceptClicked(EventContext eventContext);

    public abstract void onTipCardClicked(EventContext eventContext);
}
