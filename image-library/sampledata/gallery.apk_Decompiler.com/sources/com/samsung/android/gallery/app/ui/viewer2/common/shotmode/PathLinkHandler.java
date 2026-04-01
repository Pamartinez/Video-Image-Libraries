package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartBrowserByPathCmd;
import com.samsung.android.gallery.app.controller.viewer.StartCapturedFileViewCmd;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PathLinkHandler extends AbsShotModeHandler {
    private boolean mIsFile = true;

    private boolean isGotoCaptureEnabled() {
        return Features.isEnabled(Features.SUPPORT_GO_TO_CAPTURED_PATH);
    }

    private boolean isValidPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String scheme = Uri.parse(str).getScheme();
        if (TextUtils.isEmpty(scheme) || "package".equals(scheme)) {
            this.mIsFile = true;
            return FileUtils.exists(str);
        }
        this.mIsFile = false;
        return true;
    }

    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (this.mIsFile) {
            new StartCapturedFileViewCmd().execute(eventContext, mediaItem);
        } else {
            new StartBrowserByPathCmd().execute(eventContext, mediaItem);
        }
    }

    public int getPlayButtonIconId() {
        if (this.mIsFile) {
            return super.getPlayButtonIconId();
        }
        return R.drawable.internet;
    }

    public int getTitleId() {
        if (this.mIsFile) {
            return R.string.view_original;
        }
        return R.string.go_to_website;
    }

    public boolean isEnableToRunCloudOnly() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        if (!isGotoCaptureEnabled() || !isValidPath(DetailsData.of(mediaItem).capturedPath)) {
            return false;
        }
        return true;
    }
}
