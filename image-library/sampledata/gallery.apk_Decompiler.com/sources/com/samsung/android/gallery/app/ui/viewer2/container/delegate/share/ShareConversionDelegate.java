package com.samsung.android.gallery.app.ui.viewer2.container.delegate.share;

import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.externals.ShareViaCmd;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.share.ShareComponent;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.type.PendingShare;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MediaSessionBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShareConversionDelegate extends AbsVuDelegate<IVuContainerView> {
    private ConvertingUsageType mPendingConvertingUsageType = ConvertingUsageType.NONE;
    private MediaItem mPendingShareItem;
    private Boolean mQuickCropShare;

    public ShareConversionDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private String getTargetClassName(String str) {
        return ArgumentsUtil.getArgValue(str, "className", (String) null);
    }

    private String getTargetPackageName(String str) {
        return ArgumentsUtil.getArgValue(str, MediaSessionBundleWrapper.BUNDLE_KEY_APP_PACKAGE_NAME, (String) null);
    }

    private boolean isQuickCropShare(String str) {
        if (this.mQuickCropShare == null) {
            this.mQuickCropShare = Boolean.valueOf(ArgumentsUtil.getArgValue(str, "quickCropShare", false));
        }
        return this.mQuickCropShare.booleanValue();
    }

    private void resetQuickCropShare() {
        this.mQuickCropShare = Boolean.FALSE;
    }

    private void stopMediaCaptureService(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MediaCaptureService");
            intent.setAction("com.samsung.android.gallery.app.service.DELETE_SERVICE");
            context.startService(intent);
        }
    }

    private void stopVideoConversionService(Context context) {
        if (context != null) {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.VideoConversionService");
            intent.setAction("com.samsung.android.gallery.app.service.DELETE_SERVICE");
            context.startService(intent);
        }
    }

    public void clearPendedShare() {
        this.mPendingConvertingUsageType = ConvertingUsageType.NONE;
        this.mPendingShareItem = null;
    }

    public void handlePendedShare(boolean z, ConvertingUsageType convertingUsageType, MediaItem mediaItem) {
        if (z) {
            handlePendedShare(convertingUsageType, mediaItem);
            return;
        }
        this.mPendingConvertingUsageType = convertingUsageType;
        this.mPendingShareItem = mediaItem;
    }

    public ShareComponent handlePendedShareIfExists(String str) {
        ConvertingUsageType convertingUsageType;
        MediaItem mediaItem = this.mPendingShareItem;
        if (mediaItem != null && (convertingUsageType = this.mPendingConvertingUsageType) != ConvertingUsageType.NONE) {
            handlePendedShare(convertingUsageType, mediaItem);
            clearPendedShare();
            return null;
        } else if (!isQuickCropShare(str)) {
            return null;
        } else {
            resetQuickCropShare();
            return ShareComponent.builder().setPackageName(getTargetPackageName(str)).setClassName(getTargetClassName(str)).setFromBixby();
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3018) {
            return false;
        }
        handlePendedShare(((IVuContainerView) this.mView).isViewActive(), ConvertingUsageType.of(eventMessage.arg1), (MediaItem) eventMessage.obj);
        return true;
    }

    private void handlePendedShare(ConvertingUsageType convertingUsageType, MediaItem mediaItem) {
        PendingShare.clear();
        if (convertingUsageType == ConvertingUsageType.COMMON_SHARE) {
            new ShareViaCmd().addConfig(1).execute(((IVuContainerView) this.mView).getEventContext(), new MediaItem[]{mediaItem}, null);
        }
        stopVideoConversionService(((IVuContainerView) this.mView).getContext());
        stopMediaCaptureService(((IVuContainerView) this.mView).getContext());
    }
}
