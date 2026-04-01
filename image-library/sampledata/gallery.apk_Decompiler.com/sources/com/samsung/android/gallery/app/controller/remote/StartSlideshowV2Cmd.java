package com.samsung.android.gallery.app.controller.remote;

import A.a;
import N2.j;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.app.NotificationManagerCompat;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RequestRuntimePermissionCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remote.SlideshowServiceHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.DisplayManagerCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSlideshowV2Cmd extends BaseSelectedCommand {
    private static final Uri APP_CAST_URI = Uri.parse("content://com.samsung.android.smartmirroring/app_cast_sent_result");

    private String getTargetUri(Object[] objArr) {
        MediaItem[] mediaItemArr;
        String str;
        String str2;
        String str3;
        if (objArr == null || objArr.length <= 0) {
            mediaItemArr = null;
        } else {
            mediaItemArr = objArr[0];
        }
        if (objArr == null || objArr.length <= 1) {
            str = null;
        } else {
            str = objArr[1];
        }
        if (objArr == null || objArr.length <= 2) {
            str2 = null;
        } else {
            str2 = objArr[2];
        }
        if (!getHandler().isSelectionMode() || mediaItemArr == null || mediaItemArr.length == 0) {
            String locationKey = getHandler().getLocationKey();
            if (locationKey == null) {
                Log.e(this.TAG, "startSlideshow failed");
                return null;
            }
            str3 = ArgumentsUtil.appendUriKey(locationKey, "/slideshow", true);
        } else {
            str3 = ArgumentsUtil.appendUriKey("location://selectedItems", "/slideshow", true);
            getBlackboard().publish(DataKey.DATA("location://selectedItems"), mediaItemArr);
            if (PocFeatures.isEnabled(PocFeatures.Recap)) {
                str3 = ArgumentsUtil.appendArgs(str3, "slide_show_recap", (String) getParameter("slide_show_recap"));
            }
        }
        String appendArgs = ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str3, Message.KEY_POSITION, "0"), "media_item", (String) null);
        if (!TextUtils.isEmpty(str)) {
            appendArgs = ArgumentsUtil.appendArgs(appendArgs, "filter_name", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            return ArgumentsUtil.appendArgs(appendArgs, "bgm_name", str2);
        }
        return appendArgs;
    }

    private boolean hasNotificationPermission() {
        if (!SdkConfig.lessThan(SdkConfig.SEM.T)) {
            return RuntimePermissionUtil.hasPermission(getContext(), RuntimePermissionUtil.NOTIFICATION_PERMISSION_GROUP);
        }
        try {
            return NotificationManagerCompat.from(getContext()).areNotificationsEnabled();
        } catch (Exception e) {
            a.s(e, new StringBuilder("hasNotificationPermission failed : "), this.TAG);
            return false;
        }
    }

    private boolean isAppCastRunning() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            boolean parseBoolean = Boolean.parseBoolean(AppResources.getAppContext().getContentResolver().getType(APP_CAST_URI));
            String str = this.TAG;
            Log.rm(str, "isAppCastRunning " + Logger.vt(Boolean.valueOf(parseBoolean), Long.valueOf(currentTimeMillis)));
            return parseBoolean;
        } catch (Exception e) {
            j.s(e, new StringBuilder("isAppCastRunning failed e="), this.TAG);
            return false;
        }
    }

    private boolean isConnectLebo() {
        if (SeApiCompat.getSettingsGlobalInt(getContext(), "lelink_cast_on", 0) == 1) {
            return true;
        }
        return false;
    }

    private boolean isExternalDisplayConnected() {
        DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
        if (displayManagerCompat == null || displayManagerCompat.getDisplay(displayManagerCompat.getPrimaryPresentationId()) == null || displayManagerCompat.isHdmiConnected()) {
            return false;
        }
        return true;
    }

    private boolean isMultiWindowMode() {
        Activity activity = getActivity();
        String str = this.TAG;
        Log.rm(str, "isMultiWindowMode : " + activity);
        if (activity == null || !activity.isInMultiWindowMode()) {
            return false;
        }
        return true;
    }

    private boolean isPresentationAvailable() {
        return PocFeatures.PRESENTATION_ENABLED;
    }

    private boolean isVideoPlayingOnRemote() {
        DisplayManagerCompat displayManagerCompat = SeApiCompat.getDisplayManagerCompat(AppResources.getAppContext());
        if (displayManagerCompat == null) {
            return false;
        }
        Display[] displays = displayManagerCompat.getDisplays("android.hardware.display.category.PRESENTATION");
        if (displays == null || displays.length <= 0) {
            return displayManagerCompat.isActiveDlnaUsedByVideo();
        }
        String presentationOwner = displayManagerCompat.getPresentationOwner(displays[0].getDisplayId());
        if (presentationOwner == null || !presentationOwner.contains("com.samsung.android.video")) {
            return false;
        }
        return true;
    }

    private void requestRuntimePermission() {
        new RequestRuntimePermissionCmd().execute(getHandler(), RuntimePermissionUtil.NOTIFICATION_PERMISSION_GROUP, 123, Integer.valueOf(R.string.slideshow));
    }

    private void startSlideshow(String str) {
        int i2;
        if (!isExternalDisplayConnected() || !isPresentationAvailable() || isAppCastRunning() || isMultiWindowMode() || isConnectLebo()) {
            Log.rm(this.TAG, "start slideshow on the phone.");
            getBlackboard().post("command://MoveURL", str);
        } else if (!Settings.canDrawOverlays(getContext())) {
            Context context = getContext();
            Context context2 = getContext();
            boolean isEnabled = Features.isEnabled(Features.IS_VERIZON_DEVICE);
            Context context3 = getContext();
            if (isEnabled) {
                i2 = R.string.draw_over_other_apps;
            } else {
                i2 = R.string.appear_on_top;
            }
            Utils.showToast(context, SeApiCompat.naturalizeText(context2.getString(R.string.overlay_permission_off_warning_toast, new Object[]{context3.getString(i2)})));
        } else if (!hasNotificationPermission()) {
            requestRuntimePermission();
        } else if (isVideoPlayingOnRemote()) {
            Utils.showToast(getContext(), (int) R.string.unable_to_play_presentation);
        } else {
            SlideshowServiceHelper.startService(getContext(), getBlackboard().getName(), str);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_SLIDESHOW.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
        String targetUri = getTargetUri(objArr);
        if (targetUri == null) {
            Log.d(this.TAG, "startSlideshow Fail.  null target uri");
        } else {
            startSlideshow(targetUri);
        }
    }
}
