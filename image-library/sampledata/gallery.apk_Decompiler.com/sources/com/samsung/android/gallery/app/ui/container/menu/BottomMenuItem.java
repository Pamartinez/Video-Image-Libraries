package com.samsung.android.gallery.app.ui.container.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.StartPrivateAlbumCmd;
import com.samsung.android.gallery.app.controller.externals.GotoMyFilesCmd;
import com.samsung.android.gallery.app.controller.externals.StartVideoStudioCmd;
import com.samsung.android.gallery.app.controller.internals.StartSettingsCmd;
import com.samsung.android.gallery.app.controller.sharing.GroupSharingApplyCmd;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.settings.helper.PopoverUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsDetailKey;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BottomMenuItem {
    public final AnalyticsEventId eventId;
    public final int iconRes;
    public final String locationKey;
    public final int titleRes;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Favorite extends BottomMenuItem {
        MediaItem album;

        public Favorite() {
            super(R.drawable.gallery_ic_menu_favorite, R.string.smart_album_favorites, AnalyticsEventId.EVENT_SMART_ALBUM_FAVORITE, buildLocationKey());
        }

        public static String buildLocationKey() {
            String str;
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                str = "location://albums/fileList/mxVirtual/favorite";
            } else {
                str = "location://virtual/album/favorite/fileList";
            }
            return new UriBuilder(str).appendArg("id", BucketUtils.VirtualBucketHolder.favorite).appendArg("show_album_info", false).appendArg("album_split_blocked", true).appendArg("with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("type", AlbumType.Virtual.toInt()).build();
        }

        public void onItemClicked(EventContext eventContext) {
            publishData(eventContext);
            BottomMenuItem.super.onItemClicked(eventContext);
        }

        public void publishData(EventContext eventContext) {
            if (this.album == null) {
                int i2 = BucketUtils.VirtualBucketHolder.favorite;
                this.album = MediaItemBuilder.createVirtualAlbum(i2, AlbumTitleHelper.getAlbumTitle(i2));
            }
            eventContext.getBlackboard().publish("data://albums/current", this.album);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Location extends BottomMenuItem {
        public Location() {
            super(R.drawable.gallery_ic_menu_location, R.string.searchview_location, AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_LOCATIONS, buildLocationKey());
        }

        public static String buildLocationKey() {
            return new UriBuilder("location://search/fileList/Category/Location").appendArg("searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("searchFromTabMenu", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).build();
        }

        public boolean support(Context context) {
            if (!Features.isEnabled(Features.SUPPORT_LOCATION) || PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Mtp extends BottomMenuItem {
        public Mtp() {
            super(R.drawable.gallery_ic_menu_mtp, R.string.set_label_mtp_devices, AnalyticsEventId.EVENT_TAB_MTP, "location://mtp");
        }

        public boolean support(Context context) {
            return MtpClient.getInstance(context.getApplicationContext()).isAvailable();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PrivateAlbum extends BottomMenuItem {
        public PrivateAlbum() {
            super(R.drawable.gallery_ic_menu_locked, R.string.private_album, (AnalyticsEventId) null, "location://albums/private/fileList");
        }

        public void onItemClicked(EventContext eventContext) {
            new StartPrivateAlbumCmd().execute(eventContext, new Object[0]);
        }

        public boolean support(Context context) {
            return PocFeatures.SUPPORT_PRIVATE_ALBUM;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Recent extends BottomMenuItem {
        MediaItem album;

        public Recent() {
            super(R.drawable.gallery_ic_menu_recent, R.string.recently_added, AnalyticsEventId.EVENT_SMART_ALBUM_RECENTLY, buildLocationKey());
        }

        public static String buildLocationKey() {
            String str;
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                str = "location://albums/fileList/mxVirtual/recent";
            } else {
                str = "location://virtual/album/recently/fileList";
            }
            return new UriBuilder(str).appendArg("id", BucketUtils.VirtualBucketHolder.recent).appendArg("show_album_info", false).appendArg("album_split_blocked", true).appendArg("type", AlbumType.Virtual.toInt()).build();
        }

        public void onItemClicked(EventContext eventContext) {
            publishData(eventContext);
            BottomMenuItem.super.onItemClicked(eventContext);
        }

        public void publishData(EventContext eventContext) {
            if (this.album == null) {
                int i2 = BucketUtils.VirtualBucketHolder.recent;
                this.album = MediaItemBuilder.createVirtualAlbum(i2, AlbumTitleHelper.getAlbumTitle(i2));
            }
            eventContext.getBlackboard().publish("data://albums/current", this.album);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Settings extends BottomMenuItem {
        public Settings() {
            super(R.drawable.gallery_ic_menu_setting, R.string.more_options_setting, AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_SETTINGS, "location://settings");
        }

        public void onItemClicked(EventContext eventContext) {
            new StartSettingsCmd().execute(eventContext, PopoverUtils.Anchor.BOTTOM_END);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Shared extends BottomMenuItem {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Shared() {
            /*
                r4 = this;
                boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi8x.IS_ONE_UI_80
                if (r0 == 0) goto L_0x0008
                r0 = 2131888499(0x7f120973, float:1.9411635E38)
                goto L_0x000b
            L_0x0008:
                r0 = 2131888500(0x7f120974, float:1.9411637E38)
            L_0x000b:
                com.samsung.android.gallery.support.analytics.AnalyticsEventId r1 = com.samsung.android.gallery.support.analytics.AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_SHARED_ALBUMS
                java.lang.String r2 = "location://sharing/albums"
                r3 = 2131231737(0x7f0803f9, float:1.8079563E38)
                r4.<init>(r3, r0, r1, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem.Shared.<init>():void");
        }

        public void onItemClicked(EventContext eventContext) {
            if (MdeSharingService.getInstance().showSemsPermissionPopup()) {
                new GroupSharingApplyCmd().execute(eventContext, new Object[0]);
            } else {
                BottomMenuItem.super.onItemClicked(eventContext);
            }
        }

        public boolean support(Context context) {
            return MdeSharingService.getInstance().isServiceSupported();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShotModes extends BottomMenuItem {
        public ShotModes() {
            super(R.drawable.gallery_ic_shot_types, R.string.shot_types, (AnalyticsEventId) null, buildLocationKey());
        }

        public static String buildLocationKey() {
            return new UriBuilder("location://search/fileList/Category/ShotMode").appendArg("searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("searchFromTabMenu", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE).build();
        }

        public void onItemClicked(EventContext eventContext) {
            BottomMenuItem.super.onItemClicked(eventContext);
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_SHOT_TYPE.toString());
        }

        public boolean support(Context context) {
            return PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Studio extends BottomMenuItem {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Studio() {
            /*
                r3 = this;
                boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi8x.IS_ONE_UI_85
                if (r0 == 0) goto L_0x0008
                r0 = 2131232042(0x7f08052a, float:1.8080182E38)
                goto L_0x000b
            L_0x0008:
                r0 = 2131231714(0x7f0803e2, float:1.8079517E38)
            L_0x000b:
                r1 = 2131888989(0x7f120b5d, float:1.9412629E38)
                r2 = 0
                r3.<init>(r0, r1, r2, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem.Studio.<init>():void");
        }

        public Drawable getIcon(Context context) {
            return context.getDrawable(this.iconRes);
        }

        public String getTitle(Context context) {
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                return BottomMenuItem.super.getTitle(context);
            }
            return SeApiCompat.naturalizeText(context.getString(R.string.video_studio_title, new Object[]{context.getString(this.titleRes)}));
        }

        public void onItemClicked(EventContext eventContext) {
            if (Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO)) {
                new StartVideoStudioCmd().execute(eventContext, new Object[0]);
            }
        }

        public boolean support(Context context) {
            if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85 || !Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO) || !PocFeatures.isEnabled(PocFeatures.GotoStudioMenuEnabled)) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Suggestion extends BottomMenuItem {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Suggestion() {
            /*
                r4 = this;
                boolean r0 = com.samsung.android.gallery.support.utils.PreferenceFeatures.OneUi7x.IS_ONE_UI_70
                if (r0 == 0) goto L_0x0008
                r1 = 2131231734(0x7f0803f6, float:1.8079557E38)
                goto L_0x000b
            L_0x0008:
                r1 = 2131231743(0x7f0803ff, float:1.8079576E38)
            L_0x000b:
                if (r0 == 0) goto L_0x0011
                r0 = 2131886384(0x7f120130, float:1.9407345E38)
                goto L_0x0014
            L_0x0011:
                r0 = 2131886385(0x7f120131, float:1.9407347E38)
            L_0x0014:
                com.samsung.android.gallery.support.analytics.AnalyticsEventId r2 = com.samsung.android.gallery.support.analytics.AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_SUGGESTIONS
                java.lang.String r3 = "location://suggestions"
                r4.<init>(r1, r0, r2, r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem.Suggestion.<init>():void");
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.lang.Runnable] */
        public void onItemClicked(EventContext eventContext) {
            ThreadUtil.postOnBgThread(new Object());
            BottomMenuItem.super.onItemClicked(eventContext);
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_SUGGESTIONS);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Trash extends BottomMenuItem {
        public Trash() {
            super(R.drawable.gallery_ic_menu_delete, R.string.trash, AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_TRASH, "location://trash");
        }

        public void onItemClicked(EventContext eventContext) {
            new LaunchTrashBinCmd().execute(eventContext, new Object[0]);
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_TRASH);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UsbStorage extends BottomMenuItem {
        final StorageVolumeCompat volumeCompat;
        final int volumeIndex;
        final int volumeTotalCount;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public UsbStorage(com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat r5, int r6, int r7) {
            /*
                r4 = this;
                com.samsung.android.gallery.support.utils.UriBuilder r0 = new com.samsung.android.gallery.support.utils.UriBuilder
                java.lang.String r1 = "location://albums/otg/fileList"
                r0.<init>(r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = r5.directory
                r1.append(r2)
                java.lang.String r2 = "/DCIM/Camera/"
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                java.lang.String r2 = "directory"
                com.samsung.android.gallery.support.utils.UriBuilder r0 = r0.appendArg((java.lang.String) r2, (java.lang.String) r1)
                java.lang.String r0 = r0.build()
                r1 = 2131231740(0x7f0803fc, float:1.807957E38)
                r2 = 2131888958(0x7f120b3e, float:1.9412566E38)
                r3 = 0
                r4.<init>(r1, r2, r3, r0)
                r4.volumeCompat = r5
                r4.volumeIndex = r6
                r4.volumeTotalCount = r7
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem.UsbStorage.<init>(com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat, int, int):void");
        }

        public String getTitle(Context context) {
            return context.getString(this.titleRes) + " " + this.volumeIndex;
        }

        public void onItemClicked(EventContext eventContext) {
            if (this.volumeCompat != null) {
                new GotoMyFilesCmd().execute(eventContext, C0212a.p(new StringBuilder(), this.volumeCompat.directory, "/DCIM/Camera/"));
                AnalyticsLogger.getInstance().postCustomDimension(eventContext.getScreenId(), AnalyticsEventId.EVENT_BOTTOM_TAB_MENU_USB_STORAGE.toString(), new Pair[]{new Pair(AnalyticsDetailKey.USB_NUM.toString(), String.valueOf(this.volumeTotalCount))});
            }
        }

        public boolean support(Context context) {
            return Features.isEnabled(Features.SUPPORT_USB_STORAGE);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Video extends BottomMenuItem {
        public Video() {
            super(R.drawable.gallery_ic_menu_video, R.string.smart_album_videos, AnalyticsEventId.EVENT_SMART_ALBUM_VIDEO, buildLocationKey());
        }

        public static String buildLocationKey() {
            return new UriBuilder("location://virtual/album/video/fileList").appendArg("id", BucketUtils.VirtualBucketHolder.video).build();
        }
    }

    public BottomMenuItem(int i2, int i7, AnalyticsEventId analyticsEventId, String str) {
        this.iconRes = i2;
        this.titleRes = i7;
        this.eventId = analyticsEventId;
        this.locationKey = str;
    }

    public Drawable getIcon(Context context) {
        Drawable drawable = context.getDrawable(this.iconRes);
        if (drawable != null) {
            drawable.setTint(context.getColor(R.color.dialog_content_text_color));
        }
        return drawable;
    }

    public String getTitle(Context context) {
        return context.getString(this.titleRes);
    }

    public void onItemClicked(EventContext eventContext) {
        if (this.locationKey != null) {
            eventContext.getBlackboard().post("command://MoveURL", this.locationKey);
        }
    }

    public boolean support(Context context) {
        return true;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Dummy extends BottomMenuItem {
        public Dummy() {
            super(0, 0, (AnalyticsEventId) null, (String) null);
        }

        public Drawable getIcon(Context context) {
            return null;
        }

        public String getTitle(Context context) {
            return null;
        }

        public void onItemClicked(EventContext eventContext) {
        }
    }

    public void publishData(EventContext eventContext) {
    }
}
