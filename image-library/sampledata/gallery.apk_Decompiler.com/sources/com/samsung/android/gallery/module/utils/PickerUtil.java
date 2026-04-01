package com.samsung.android.gallery.module.utils;

import F3.C0395a;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.abstraction.StoryItemCuration;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.IntentAction;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PickerUtil {
    public static String appendPickerArgs(Blackboard blackboard, String str) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent != null) {
            MediaFilterType filterMediaType = getFilterMediaType(launchIntent);
            if (!MediaFilterType.ALL.equals(filterMediaType)) {
                str = ArgumentsUtil.appendArgs(str, "filterMediaType", filterMediaType.toString());
            }
            if (launchIntent.isLocalContentOnly()) {
                str = ArgumentsUtil.appendArgs(str, "filterLocalOnly", String.valueOf(true));
            }
            if (launchIntent.isDisableTimeline()) {
                str = ArgumentsUtil.appendArgs(str, "disableTimeline", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            }
            String minResolution = launchIntent.getMinResolution();
            if (minResolution != null) {
                str = ArgumentsUtil.appendArgs(str, "filterMinResolution", minResolution);
            }
            Log.pk("PickerUtil", "appendPickerArgs " + str);
        }
        return str;
    }

    public static int getAppbarCollapsedHeight(Context context) {
        int i2;
        Resources resources = context.getResources();
        if (supportSearch()) {
            i2 = R$dimen.smart_album_layout_height_in_picker;
        } else {
            i2 = R$dimen.smart_album_layout_height_in_picker_without_search;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    public static int getAppbarTopOffsetInMultiPick(Context context) {
        int i2;
        Resources resources = context.getResources();
        if (supportSearch()) {
            i2 = R$dimen.clipboard_layout_height;
        } else {
            i2 = R$dimen.clipboard_layout_height_without_search;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    public static int getClipboardHeightDimenRes() {
        if (supportSearch()) {
            return R$dimen.clipboard_layout_height;
        }
        return R$dimen.clipboard_layout_height_without_search;
    }

    public static int getContentViewTopPadding(Blackboard blackboard, boolean z) {
        int i2;
        if (!isMultiplePickLaunchMode(blackboard)) {
            return 0;
        }
        Resources resources = BlackboardUtils.readActivity(blackboard).getResources();
        if (z) {
            i2 = getClipboardHeightDimenRes();
        } else {
            i2 = R$dimen.search_card_arrow_size;
        }
        return resources.getDimensionPixelOffset(i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI.equals(r0) == false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.samsung.android.gallery.support.type.MediaFilterType getFilterMediaType(com.samsung.android.gallery.module.abstraction.LaunchIntent r3) {
        /*
            if (r3 == 0) goto L_0x002c
            android.net.Uri r0 = r3.getUriData()
            java.lang.String r3 = r3.getType()
            com.samsung.android.gallery.support.type.MediaFilterType r1 = com.samsung.android.gallery.support.type.MediaFilterType.IMAGE_ONLY
            boolean r2 = com.samsung.android.gallery.support.type.MediaFilterType.match(r1, r3)
            if (r2 != 0) goto L_0x002b
            android.net.Uri r2 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x001b
            goto L_0x002b
        L_0x001b:
            com.samsung.android.gallery.support.type.MediaFilterType r1 = com.samsung.android.gallery.support.type.MediaFilterType.VIDEO_ONLY
            boolean r3 = com.samsung.android.gallery.support.type.MediaFilterType.match(r1, r3)
            if (r3 != 0) goto L_0x002b
            android.net.Uri r3 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x002c
        L_0x002b:
            return r1
        L_0x002c:
            com.samsung.android.gallery.support.type.MediaFilterType r3 = com.samsung.android.gallery.support.type.MediaFilterType.ALL
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.utils.PickerUtil.getFilterMediaType(com.samsung.android.gallery.module.abstraction.LaunchIntent):com.samsung.android.gallery.support.type.MediaFilterType");
    }

    public static int getMaxPickCount(Blackboard blackboard) {
        if (isSinglePickLaunchMode(blackboard)) {
            return 1;
        }
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent == null || launchIntent.getMaxPickItem() <= 0) {
            return 500;
        }
        return launchIntent.getMaxPickItem();
    }

    public static LaunchModeType getPickerLaunchMode(Blackboard blackboard) {
        String str;
        LaunchIntent launchIntent;
        LaunchModeType launchModeType = (LaunchModeType) blackboard.read("data://launch_mode_type", null);
        if (launchModeType != null) {
            return launchModeType;
        }
        LaunchModeType launchModeType2 = LaunchModeType.ACTION_PICK;
        Activity readActivity = BlackboardUtils.readActivity(blackboard);
        if (readActivity == null) {
            Log.w("PickerUtil", "Blackboard's activity is null");
            return launchModeType2;
        }
        Intent intent = readActivity.getIntent();
        if (intent != null) {
            str = intent.getAction();
        } else {
            str = null;
        }
        if (str == null || (launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null)) == null) {
            return launchModeType2;
        }
        if (IntentAction.isGetContent(str) || IntentAction.isPick(str) || IntentAction.isAlbumPick(str) || IntentAction.isItemPick(str)) {
            if (launchIntent.isMultiPick()) {
                launchModeType2 = LaunchModeType.ACTION_MULTIPLE_PICK;
            } else if (IntentAction.isAlbumPick(str)) {
                launchModeType2 = LaunchModeType.ACTION_ALBUM_PICK;
            } else if (launchIntent.isCoverPick()) {
                launchModeType2 = LaunchModeType.ACTION_COVER_ITEM_PICK;
            } else if (IntentAction.isItemPick(str)) {
                intent.putExtra("view_item", true);
            }
        } else if (IntentAction.isMultiplePick(str)) {
            launchModeType2 = LaunchModeType.ACTION_MULTIPLE_PICK;
        }
        blackboard.publish("data://launch_mode_type", launchModeType2);
        return launchModeType2;
    }

    public static int getStoryMaxCount() {
        if (SdkConfig.atLeast(SdkConfig.SEM.T)) {
            return StoryItemCuration.MAX_CURATION;
        }
        return 200;
    }

    public static String getUsageDescription(Blackboard blackboard) {
        String str = null;
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent != null) {
            str = launchIntent.getPickerUsageDescription();
        }
        if (str != null) {
            return str;
        }
        return AppResources.getString(R$string.no_selection_items);
    }

    public static String getUsageTitle(Blackboard blackboard) {
        int i2;
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent != null) {
            String pickerUsageTitle = launchIntent.getPickerUsageTitle();
            if (pickerUsageTitle != null) {
                return pickerUsageTitle;
            }
            LaunchModeType launchModeType = (LaunchModeType) blackboard.read("data://launch_mode_type", LaunchModeType.ACTION_NORMAL);
            if (launchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
                if (!PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER || !launchIntent.isAlbumCoverPick()) {
                    i2 = R$string.change_cover_image;
                } else {
                    i2 = R$string.select_item;
                }
                return AppResources.getString(i2);
            } else if (launchModeType != LaunchModeType.ACTION_MULTIPLE_PICK) {
                return AppResources.getString(R$string.select_item);
            } else {
                if (launchIntent.isAlbumMultiPick()) {
                    return AppResources.getString(R$string.select_albums);
                }
            }
        }
        return AppResources.getString(R$string.select_items);
    }

    public static boolean isAlbumMultiPickLaunchMode(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isAlbumMultiPick()) {
            return false;
        }
        return true;
    }

    public static boolean isAlbumPickLaunchMode(Blackboard blackboard) {
        if (blackboard.read("data://launch_mode_type") == LaunchModeType.ACTION_ALBUM_PICK) {
            return true;
        }
        return false;
    }

    public static boolean isLocalContentOnly(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent == null || !launchIntent.isLocalContentOnly()) {
            return false;
        }
        return true;
    }

    public static boolean isMultiplePickLaunchMode(Blackboard blackboard) {
        if (blackboard.read("data://launch_mode_type") == LaunchModeType.ACTION_MULTIPLE_PICK) {
            return true;
        }
        return false;
    }

    public static boolean isNormalLaunchMode(Blackboard blackboard) {
        Object read = blackboard.read("data://launch_mode_type");
        if (read == null || read == LaunchModeType.ACTION_NORMAL) {
            return true;
        }
        return false;
    }

    public static boolean isPeopleMultiplePick(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent == null || launchIntent.getMultiPickCreatureType() != 0) {
            return false;
        }
        return true;
    }

    public static boolean isPetMultiplePick(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent == null || launchIntent.getMultiPickCreatureType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean isPickerFilterMode(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent == null || MediaFilterType.ALL.equals(getFilterMediaType(launchIntent))) {
            return false;
        }
        return true;
    }

    public static boolean isPickerMode(Blackboard blackboard) {
        if (blackboard == null) {
            return false;
        }
        return ((Boolean) blackboard.computeIfAbsent("data://picker_mode", new C0395a(blackboard, 3))).booleanValue();
    }

    public static boolean isSinglePickLaunchMode(Blackboard blackboard) {
        Object read = blackboard.read("data://launch_mode_type");
        if (read == LaunchModeType.ACTION_PICK || read == LaunchModeType.ACTION_ALBUM_PICK || read == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return true;
        }
        return false;
    }

    public static boolean isSpecificPathIncluded(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        if (launchIntent == null || launchIntent.getIncludedPath() == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$isPickerMode$0(Blackboard blackboard, String str) {
        Intent intent;
        boolean z;
        Activity readActivity = BlackboardUtils.readActivity(blackboard);
        if (readActivity != null) {
            intent = readActivity.getIntent();
        } else {
            intent = null;
        }
        if (intent == null || !IntentAction.isForPick(intent.getAction())) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static boolean supportAlbumCreation(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.supportAlbumCreation()) {
            return false;
        }
        return true;
    }

    public static boolean supportClipboard(Blackboard blackboard) {
        LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent", null);
        if (launchIntent == null || launchIntent.isStoryMultiPick() || launchIntent.isAlbumMultiPick() || !isMultiplePickLaunchMode(blackboard)) {
            return false;
        }
        return true;
    }

    public static boolean supportSearch() {
        if (!Features.isEnabled(Features.SUPPORT_SEARCH_IN_PICKER) || !PreferenceFeatures.isEnabled(PreferenceFeatures.SearchPicker)) {
            return false;
        }
        return true;
    }
}
