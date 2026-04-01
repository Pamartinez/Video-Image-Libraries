package com.samsung.android.gallery.app.ui.dialog;

import androidx.fragment.app.DialogFragment;
import com.samsung.android.gallery.app.ui.dialog.DialogFactory;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureChoiceDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureMultipleDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceDialog;
import com.samsung.android.gallery.app.ui.dialog.switchable.SwitchableDialog;
import com.samsung.android.gallery.widget.dialog.ChinaGdprDialog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements DialogFactory.CreateDialogInstance {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2524a;

    public /* synthetic */ b(int i2) {
        this.f2524a = i2;
    }

    public final DialogFragment create() {
        switch (this.f2524a) {
            case 0:
                return new CreateAlbumDialog();
            case 1:
                return new MergeCreatureDialog();
            case 2:
                return new CreateAutoUpdatingAlbumDialog();
            case 3:
                return new MergeCreatureMultipleDialog();
            case 4:
                return new CreaturePickerDialog();
            case 5:
                return new RelationshipChoiceDialog();
            case 6:
                return new MergeCreatureChoiceDialog();
            case 7:
                return new CreateMyQueryDialog();
            case 8:
                return new DownloadAppDialog();
            case 9:
                return new ViewAsDialog();
            case 10:
                return new CopyOrMoveDialog();
            case 11:
                return new RenameSharedAlbumDialog();
            case 12:
                return new SimpleConfirmDialog();
            case 13:
                return new SimpleConfirmProgressDialog();
            case 14:
                return new SortByDialog();
            case 15:
                return new SwitchableDialog();
            case 16:
                return new ChinaGdprDialog();
            case 17:
                return new CreateFolderDialog();
            case 18:
                return new RenameFolderDialog();
            case 19:
                return new SimpleSpinnerDialog();
            case 20:
                return new ChangeAlbumCoverDialog();
            case 21:
                return new SyncItemTransferDialog();
            case 22:
                return new RenameSharedFamilyAlbumDialog();
            case 23:
                return new TurnOnTrashDialog();
            case 24:
                return new StartAppInfoDialog();
            case 25:
                return new GroupShotCheckBoxDialog();
            case 26:
                return new SortByAlbumDialog();
            case 27:
                return new AppRatingDialog();
            case 28:
                return new ViewerListChooserDialog();
            default:
                return new CreateNewDialogV2();
        }
    }
}
