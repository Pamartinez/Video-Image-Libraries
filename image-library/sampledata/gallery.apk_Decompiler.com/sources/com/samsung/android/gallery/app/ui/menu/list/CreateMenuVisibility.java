package com.samsung.android.gallery.app.ui.menu.list;

import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateMenuVisibility {
    protected Boolean mSupportVideoCollage;

    private boolean isShotModeCategory(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("location://search/fileList/Category/ShotMode")) {
            return false;
        }
        return true;
    }

    private boolean isVideoOnlyView(String str) {
        if (LocationKey.isVideoPictures(str) || isVideoTypeSearchCategory(str)) {
            return true;
        }
        return false;
    }

    private boolean isVideoType(String str) {
        ShotMode byType = ShotModeList.getInstance().getByType(ArgumentsUtil.getArgValue(str, "sub"));
        if (byType == null || !byType.isVideo()) {
            return false;
        }
        return true;
    }

    private boolean isVideoTypeSearchCategory(String str) {
        if (!isShotModeCategory(str) || !isVideoType(str)) {
            return false;
        }
        return true;
    }

    public boolean[] getVisibilities(String str) {
        boolean[] zArr = new boolean[CreateNewDialogCmd.CreateType.values().length];
        boolean z = true;
        zArr[CreateNewDialogCmd.CreateType.HIGHLIGHT_REEL.ordinal()] = true;
        zArr[CreateNewDialogCmd.CreateType.MOVIE.ordinal()] = true;
        zArr[CreateNewDialogCmd.CreateType.GIF.ordinal()] = !isVideoOnlyView(str);
        int ordinal = CreateNewDialogCmd.CreateType.COLLAGE.ordinal();
        if (!supportVideoCollage() && isVideoOnlyView(str)) {
            z = false;
        }
        zArr[ordinal] = z;
        boolean isEnabled = SettingPreference.Meitu.isEnabled();
        zArr[CreateNewDialogCmd.CreateType.MEITU_POSTER.ordinal()] = isEnabled;
        zArr[CreateNewDialogCmd.CreateType.MEITU_VIDEO_CLIP.ordinal()] = isEnabled;
        return zArr;
    }

    public boolean supportVideoCollage() {
        boolean z;
        if (this.mSupportVideoCollage == null) {
            if (!PreferenceFeatures.OneUi40.SUPPORT_COLLAGE_ON_VIDEO_TRIMMER || !Features.isEnabled(Features.SUPPORT_VIDEO_COLLAGE)) {
                z = false;
            } else {
                z = true;
            }
            this.mSupportVideoCollage = Boolean.valueOf(z);
        }
        return this.mSupportVideoCollage.booleanValue();
    }
}
