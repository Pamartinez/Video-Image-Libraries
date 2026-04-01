package com.samsung.android.gallery.app.controller.externals;

import A.a;
import M3.d;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.app.sdk.deepsky.objectcapture.utils.ServiceManagerUtil;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.module.graphics.CodecCompat;
import com.samsung.android.gallery.module.utils.ShareList;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSimplePhotoEditorCmd extends BaseCommand {
    private ModeInfo mModeInfo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum PhotoEditorMode {
        spe_crop,
        spe_tone,
        spe_effect,
        spe_sticker,
        spe_draw,
        spe_text,
        spe_objecteraser,
        spe_lasso,
        spe_genedit,
        spe_portrait_bg,
        spe_bestface,
        spe_bgblur;

        public static PhotoEditorMode getMode(String str) {
            if (TextUtils.isEmpty(str)) {
                return spe_crop;
            }
            for (PhotoEditorMode photoEditorMode : values()) {
                if (photoEditorMode.name().equals(str)) {
                    return photoEditorMode;
                }
            }
            return spe_crop;
        }
    }

    private Intent createIntent(MediaItem mediaItem, PhotoEditorMode photoEditorMode, byte[] bArr) {
        Uri uri = ContentUri.getUri(mediaItem);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, ServiceManagerUtil.PHOTO_EDIT_ACTIVITY_CLASS_NAME));
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FILE_PATH_KEY, uri);
        intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_SERVICE_KEY, photoEditorMode.toString());
        intent.putExtra("need_result", true);
        intent.putExtra("intent_flag", getActivity().getIntent().getFlags());
        if (photoEditorMode == PhotoEditorMode.spe_objecteraser) {
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY, true);
            if (isShadowRemovalMode()) {
                intent.putExtra("isShadowRemovalFromGalleryDetails", true);
            } else if (isReflectionRemovalMode()) {
                intent.putExtra("isReflectionRemovalFromGalleryDetails", true);
            }
        } else if (isPasteMode()) {
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY, true);
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_ORIGINAL_FILE_PATH_KEY, grantUri(this.mModeInfo.getOriginalPath(), true));
            String str = this.TAG;
            Log.d(str, "intent data", photoEditorMode + "@P", Logger.getEncodedString((Object) uri), Logger.getEncodedString((Object) this.mModeInfo.getOriginalPath()));
        } else if (isEditMode()) {
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY, true);
            ExtrasID extrasID = ExtrasID.ORIGIN_PATH;
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_ORIGINAL_FILE_PATH_KEY, (String) mediaItem.getExtra(extrasID));
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_SAVE_DIR_KEY, StorageInfo.getDefault().clippedImages);
            String str2 = this.TAG;
            Log.d(str2, "intent data", photoEditorMode + "@E", Logger.getEncodedString((Object) uri), Logger.getEncodedString(mediaItem.getExtra(extrasID)), Logger.getEncodedString(StorageInfo.getDefault().clippedImages));
        } else if (photoEditorMode == PhotoEditorMode.spe_genedit) {
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY, true);
            if (isAutoTiltMode()) {
                intent.putExtra("autotiltvalue", true);
            } else if (isRemoveBgPeopleMode()) {
                intent.putExtra("mode", "removebgpeople");
            }
        } else if (photoEditorMode == PhotoEditorMode.spe_bestface) {
            intent.putExtra(ServiceManagerUtil.PHOTO_EDIT_EXTRA_FROM_GALLERY_KEY, true);
        }
        setBixbySubInfo(intent);
        setIntentWithCommonExtra(intent);
        if (bArr != null) {
            intent.putExtra("bm", bArr);
        }
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            intent.addFlags(3);
            if (!handleSharedItemEdit(mediaItem, intent)) {
                grantUri(uri, false);
            }
        }
        if (CodecCompat.PATCH_JPEG_PROGRESSIVE) {
            intent.putExtra("inPreferredAndroidCodec", !CodecCompat.ensureJpegSyntaxCompatible(mediaItem));
        }
        return intent;
    }

    private Uri grantUri(Uri uri, boolean z) {
        int i2;
        if (uri != null) {
            try {
                Context context = getContext();
                if (z) {
                    i2 = 1;
                } else {
                    i2 = 3;
                }
                context.grantUriPermission(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, uri, i2);
                return uri;
            } catch (Exception e) {
                Log.e((CharSequence) this.TAG, "failed to grant uri permission", (Throwable) e);
            }
        }
        return uri;
    }

    private boolean hasBixbySubInfo() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.hasBixbySubInfo()) {
            return false;
        }
        return true;
    }

    private boolean isAutoTiltMode() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.isAutoTiltMode()) {
            return false;
        }
        return true;
    }

    private boolean isBixbyFilterMode() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.isBixbyFilerMode()) {
            return false;
        }
        return true;
    }

    private boolean isEditMode() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.isEditMode()) {
            return false;
        }
        return true;
    }

    private boolean isInvalidItem(MediaItem mediaItem) {
        if (mediaItem.isLocal() && mediaItem.getMediaId() == 0) {
            return true;
        }
        if (mediaItem.getMediaId() >= 0 || isEditMode()) {
            return false;
        }
        return true;
    }

    private boolean isPasteMode() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.isPasteMode()) {
            return false;
        }
        return true;
    }

    private boolean isReflectionRemovalMode() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.isReflectionRemovalMode()) {
            return false;
        }
        return true;
    }

    private boolean isRemoveBgPeopleMode() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.isRemoveBgPeopleMode()) {
            return false;
        }
        return true;
    }

    private boolean isShadowRemovalMode() {
        ModeInfo modeInfo = this.mModeInfo;
        if (modeInfo == null || !modeInfo.isShadowRemovalMode()) {
            return false;
        }
        return true;
    }

    private void setBixbySubInfo(Intent intent) {
        boolean z;
        if (hasBixbySubInfo()) {
            String[] subInfo = this.mModeInfo.getSubInfo();
            boolean z3 = false;
            Optional.ofNullable(subInfo[0]).ifPresent(new d(intent, 1));
            Optional.ofNullable(subInfo[1]).ifPresent(new d(intent, 2));
            String str = this.TAG;
            Boolean valueOf = Boolean.valueOf(isBixbyFilterMode());
            if (subInfo[0] != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf2 = Boolean.valueOf(z);
            if (subInfo[1] != null) {
                z3 = true;
            }
            Log.d(str, "set bixby sub info", valueOf, valueOf2, Boolean.valueOf(z3));
        }
    }

    private void startEditor(MediaItem mediaItem, PhotoEditorMode photoEditorMode, byte[] bArr, boolean z) {
        String str;
        try {
            Intent createIntent = createIntent(mediaItem, photoEditorMode, bArr);
            if (PocFeatures.DUAL_PHOTO_PREVIEW && mediaItem.getStorageType() == StorageType.Stream && (str = (String) mediaItem.getTag("dual-shot-state")) != null) {
                createIntent.putExtra("dual-shot-state", str);
                String str2 = this.TAG;
                Log.d(str2, "startEditor {" + photoEditorMode + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str + "}");
            }
            if (SuperHdrConfig.SUPPORT) {
                createIntent.putExtra("photo_hdr", SuperHdrConfig.isEnabled());
            }
            int sharedMemoryHash = ShareList.getSharedMemoryHash();
            if (sharedMemoryHash > 0) {
                createIntent.putExtra("bitmap_hash", sharedMemoryHash);
                String str3 = this.TAG;
                Log.at(str3, "start PE , bitmapHash : " + sharedMemoryHash);
            }
            if (isEditMode() || ((z && !SdkConfig.atLeast(SdkConfig.GED.R)) || SystemUi.isInMultiWindowMode(getActivity()) || !startActivityWithTransition(createIntent, 782, "gallery_to_pe"))) {
                getActivity().startActivityForResult(createIntent, 782);
            }
        } catch (ActivityNotFoundException unused) {
            guideDownloadPackage(ServiceManagerUtil.PHOTO_EDIT_PACKAGE_NAME, getContext().getString(R.string.photo_editor));
            Log.e(this.TAG, "startEditor not founded com.sec.android.mimage.photoretouching");
        } catch (Exception e) {
            a.s(e, new StringBuilder("startEditor failed e="), this.TAG);
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        byte[] bArr;
        boolean z;
        Activity activity = getActivity();
        MediaItem mediaItem = objArr[0];
        PhotoEditorMode photoEditorMode = PhotoEditorMode.spe_draw;
        if (objArr.length > 1) {
            photoEditorMode = objArr[1];
        }
        if (objArr.length > 2) {
            bArr = objArr[2];
        } else {
            bArr = null;
        }
        if (objArr.length > 3) {
            z = objArr[3].booleanValue();
        } else {
            z = false;
        }
        if (objArr.length > 4) {
            this.mModeInfo = objArr[4];
        }
        if (isInvalidItem(mediaItem)) {
            Toast.makeText(activity, R.string.gallery360viewer_error_file_corrupt, 0).show();
        } else {
            startEditor(mediaItem, photoEditorMode, bArr, z);
        }
    }
}
