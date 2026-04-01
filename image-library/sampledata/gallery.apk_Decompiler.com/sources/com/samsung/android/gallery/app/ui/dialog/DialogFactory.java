package com.samsung.android.gallery.app.ui.dialog;

import androidx.fragment.app.DialogFragment;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DialogFactory {
    static final HashMap<String, CreateDialogInstance> sMap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface CreateDialogInstance {
        DialogFragment create();
    }

    static {
        Object obj;
        HashMap<String, CreateDialogInstance> hashMap = new HashMap<>();
        sMap = hashMap;
        hashMap.put("data://user/dialog/AlbumName", new b(0));
        hashMap.put("data://user/dialog/AutoUpdatingAlbumName", new b(2));
        hashMap.put("data://user/dialog/SortBy", new b(14));
        hashMap.put("data://user/dialog/SortByAlbum", new b(26));
        hashMap.put("data://user/dialog/SortBySharing", new c(8));
        hashMap.put("data://user/dialog/SortByAlbumShared", new c(19));
        hashMap.put("data://user/dialog/StoryName", new c(20));
        hashMap.put("data://user/dialog/AlbumRename", new c(21));
        hashMap.put("data://user/dialog/StoryRename", new c(22));
        hashMap.put("data://user/dialog/SharedAlbumCreate", new c(23));
        hashMap.put("data://user/dialog/SharedAlbumRename", new b(11));
        hashMap.put("data://user/dialog/SharedFamilyAlbumRename", new b(22));
        hashMap.put("data://user/dialog/AddCloudItemToSharedAlbum", new c(3));
        hashMap.put("data://user/dialog/RemovePeopleTag", new c(14));
        hashMap.put("data://user/dialog/LowStorage", new c(24));
        hashMap.put("data://user/dialog/ShareWithWebLink", new c(25));
        hashMap.put("data://user/dialog/UpdateContactPhoto", new c(26));
        hashMap.put("data://user/dialog/AddTag", new c(27));
        hashMap.put("data://user/dialog/PermissionRequest", new c(28));
        hashMap.put("data://user/dialog/MergeCreature", new b(1));
        hashMap.put("data://user/dialog/MergeCreatureMultiple", new b(3));
        hashMap.put("data://user/dialog/CreaturePicker", new b(4));
        hashMap.put("data://user/dialog/RelationshipChoice", new b(5));
        hashMap.put("data://user/dialog/MergePeopleChoice", new b(6));
        hashMap.put("data://user/dialog/MyQueryName", new b(7));
        hashMap.put("data://user/dialog/downloadApp", new b(8));
        hashMap.put("data://user/dialog/ViewAs", new b(9));
        hashMap.put("data://user/dialog/CopyOrMove", new b(10));
        hashMap.put("data://user/dialog/SimpleConfirm", new b(12));
        hashMap.put("data://user/dialog/SimpleConfirmProgress", new b(13));
        hashMap.put("data://user/dialog/Switchable", new b(15));
        if ((Features.isEnabled(Features.IS_CHINESE_DEVICE) && !Features.isEnabled(Features.IS_RDU_MODE)) || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/GDPRLocation", new b(16));
        }
        hashMap.put("data://user/dialog/FolderName", new b(17));
        hashMap.put("data://user/dialog/FolderRename", new b(18));
        hashMap.put("data://user/dialog/SimpleSpinner", new b(19));
        hashMap.put("data://user/dialog/ChangeAlbumCover", new b(20));
        hashMap.put("data://user/dialog/SyncDataTransfer", new b(21));
        hashMap.put("data://user/dialog/TurnOnTrash", new b(23));
        hashMap.put("data://user/dialog/StartAppInfo", new b(24));
        hashMap.put("data://user/dialog/GroupShotCheckbox", new b(25));
        hashMap.put("data://user/dialog/AppRating", new b(27));
        hashMap.put("data://user/dialog/ViewerListChooser", new b(28));
        SdkConfig.SEM sem = SdkConfig.SEM.U;
        if (SdkConfig.atLeast(sem)) {
            obj = new b(29);
        } else {
            obj = new c(0);
        }
        hashMap.put("data://user/dialog/CreateNew", obj);
        hashMap.put("data://user/dialog/AlbumCreatePopup", new c(1));
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/UnsubscribeCreature", new c(2));
        }
        if (Features.isEnabled(Features.SUPPORT_SHARED_SORT) || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/SharedSortBy", new c(4));
        }
        hashMap.put("data://user/dialog/SharedInvitation", new c(5));
        hashMap.put("data://user/dialog/RangeDatePicker", new c(6));
        hashMap.put("data://user/dialog/DateTimePicker", new c(7));
        hashMap.put("data://user/dialog/CustomChooser", new c(9));
        if (Features.isEnabled(Features.SUPPORT_SHARED_PERMISSION_POPUP) || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/RequestGroupSharing", new c(10));
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/EditRelationshipName", new c(11));
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave) || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/HighlightExportOptions", new c(12));
        }
        if (SdkConfig.atLeast(sem) || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/PastePrepare", new c(13));
        }
        hashMap.put("data://user/dialog/RemoveCreature", new c(15));
        hashMap.put("data://user/dialog/HideSceneSelection", new c(16));
        hashMap.put("data://user/dialog/EraseObject", new c(17));
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || DeviceConfig.UNIT_TEST) {
            hashMap.put("data://user/dialog/LocationSortBy", new c(18));
        }
    }

    public static DialogFragment create(String str) {
        CreateDialogInstance createDialogInstance = sMap.get(str);
        if (createDialogInstance != null) {
            return createDialogInstance.create();
        }
        return null;
    }
}
