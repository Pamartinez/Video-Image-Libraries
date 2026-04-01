package Fa;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import androidx.preference.Preference;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.app.remote.dlna.DlnaDisconnectDialog;
import com.samsung.android.gallery.app.ui.dialog.ChangeAlbumCoverDialog;
import com.samsung.android.gallery.app.ui.dialog.DownloadAppDialog;
import com.samsung.android.gallery.app.ui.dialog.HighlightExportOptionsDialog;
import com.samsung.android.gallery.app.ui.dialog.PasteClipboardPrepareDialog;
import com.samsung.android.gallery.app.ui.dialog.SimpleProgressDialog;
import com.samsung.android.gallery.app.ui.dialog.StartAppInfoDialog;
import com.samsung.android.gallery.app.ui.dialog.UpdateContactPhotoDialog;
import com.samsung.android.gallery.app.ui.dialog.ViewAsDialog;
import com.samsung.android.gallery.app.ui.dialog.ViewerListChooserDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.RemoveCreatureDialog;
import com.samsung.android.gallery.app.ui.list.search.category.myquery.SearchMyQueryPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.RecapPreviewDelegate;
import com.samsung.android.gallery.app.ui.map.LocationPermissionUtil;
import com.samsung.android.gallery.app.ui.viewer2.crop.CropMenuDelegate;
import com.samsung.android.gallery.module.remotegallery.RemoteServer;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.widget.dialog.BiometricPromptCompat;
import java.util.function.Consumer;

/* renamed from: Fa.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0558l implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0558l(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        int i7 = this.d;
        Object obj = this.e;
        switch (i7) {
            case 0:
                LabsAlbumBnRFragment.lambda$onBackupAlbumDbClicked$1((Preference) obj, dialogInterface, i2);
                return;
            case 1:
                LabsDeveloperFragment.lambda$initPreferenceDevOptions$12((RemoteServer) obj, dialogInterface, i2);
                return;
            case 2:
                ((LabsFragment) obj).lambda$onHeapDumpClicked$27(dialogInterface, i2);
                return;
            case 3:
                ((DialogDelegate) obj).lambda$showTrashTurnOffDialog$2(dialogInterface, i2);
                return;
            case 4:
                ((Consumer) obj).accept(Boolean.TRUE);
                return;
            case 5:
                ((CropMenuDelegate) obj).onDialogClicked(dialogInterface, i2);
                return;
            case 6:
                ((FixDateTimeCmd) obj).onItemSelected(dialogInterface, i2);
                return;
            case 7:
                LocationPermissionUtil.startLocationSettingActivity((Context) obj);
                return;
            case 8:
                ((DlnaDisconnectDialog) obj).onPositiveClicked(dialogInterface, i2);
                return;
            case 9:
                ((SearchMyQueryPresenter) obj).onDialogClicked(dialogInterface, i2);
                return;
            case 10:
                ((RecapPreviewDelegate) obj).lambda$initiateRecording$4(dialogInterface, i2);
                return;
            case 11:
                ((ChangeAlbumCoverDialog) obj).lambda$createDialog$0(dialogInterface, i2);
                return;
            case 12:
                ((DownloadAppDialog) obj).onPositiveButtonClicked(dialogInterface, i2);
                return;
            case 13:
                ((HighlightExportOptionsDialog) obj).lambda$createDialog$0(dialogInterface, i2);
                return;
            case 14:
                ((PasteClipboardPrepareDialog) obj).onCancelClicked(dialogInterface, i2);
                return;
            case 15:
                ((SimpleProgressDialog) obj).onCancelClicked(dialogInterface, i2);
                return;
            case 16:
                ((StartAppInfoDialog) obj).lambda$createDialog$0(dialogInterface, i2);
                return;
            case 17:
                ((UpdateContactPhotoDialog) obj).lambda$createDialog$0(dialogInterface, i2);
                return;
            case 18:
                ((ViewAsDialog) obj).onClickNegative(dialogInterface, i2);
                return;
            case 19:
                ((ViewerListChooserDialog) obj).lambda$new$0(dialogInterface, i2);
                return;
            case 20:
                ((RemoveCreatureDialog) obj).lambda$showConfirmDialog$2(dialogInterface, i2);
                return;
            default:
                BiometricPromptCompat.lambda$setupScreenLock$2((Activity) obj, dialogInterface, i2);
                return;
        }
    }
}
