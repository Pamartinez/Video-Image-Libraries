package com.samsung.android.gallery.app.ui.dialog;

import androidx.fragment.app.DialogFragment;
import com.samsung.android.gallery.app.ui.dialog.DialogFactory;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveSubscribedCreatureDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.EditRelationShipDialog;
import com.samsung.android.gallery.app.ui.dialog.hidescene.HideSceneSelectionDialog;
import com.samsung.android.gallery.app.ui.dialog.permission.PermissionRationaleDialog;
import com.samsung.android.gallery.app.ui.dialog.tag.AddTagDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements DialogFactory.CreateDialogInstance {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2525a;

    public /* synthetic */ c(int i2) {
        this.f2525a = i2;
    }

    public final DialogFragment create() {
        switch (this.f2525a) {
            case 0:
                return new CreateNewDialog();
            case 1:
                return new AlbumCreatePopupDialog();
            case 2:
                return new RemoveSubscribedCreatureDialog();
            case 3:
                return new AddCloudItemToSharedAlbumDialog();
            case 4:
                return new SharedSortByDialog();
            case 5:
                return new SharedInvitationDialog();
            case 6:
                return new RangeDatePickerDialog();
            case 7:
                return new DateTimePickerDialog();
            case 8:
                return new SortBySharingDialog();
            case 9:
                return new CustomChooserDialog();
            case 10:
                return new RequestGroupSharingEnableDialog();
            case 11:
                return new EditRelationShipDialog();
            case 12:
                return new HighlightExportOptionsDialog();
            case 13:
                return new PasteClipboardPrepareDialog();
            case 14:
                return new UntagPeopleDialog();
            case 15:
                return new RemoveCreatureDialog();
            case 16:
                return new HideSceneSelectionDialog();
            case 17:
                return new EraseObjectDialog();
            case 18:
                return new LocationSortByDialog();
            case 19:
                return new SortByAlbumSharedDialog();
            case 20:
                return new CreateStoryDialog();
            case 21:
                return new RenameAlbumDialog();
            case 22:
                return new RenameStoryDialog();
            case 23:
                return new CreateSharedAlbumDialog();
            case 24:
                return new ShowLowStorageDialog();
            case 25:
                return new ShareWithWebLinkDialog();
            case 26:
                return new UpdateContactPhotoDialog();
            case 27:
                return new AddTagDialog();
            default:
                return new PermissionRationaleDialog();
        }
    }
}
