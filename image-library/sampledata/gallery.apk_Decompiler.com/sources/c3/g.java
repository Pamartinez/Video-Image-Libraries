package C3;

import android.content.Context;
import android.content.DialogInterface;
import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.app.activity.external.PickerSelectionHandler;
import com.samsung.android.gallery.app.controller.creature.abstraction.CreatureHideCmd;
import com.samsung.android.gallery.app.controller.externals.StartRemoteServerCmd;
import com.samsung.android.gallery.app.ui.dialog.UpdateContactPhotoDialog;
import com.samsung.android.gallery.app.ui.list.albums.mx.header.MxAlbumsHeaderPresenter;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsFragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.compare.CompareActivity;
import com.samsung.android.gallery.widget.dialog.MissingPackage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ g(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                ((GalleryActivityHandler) this.e).lambda$onActivityResumeBG$5((String) this.f, dialogInterface, i2);
                return;
            case 1:
                ((PickerSelectionHandler) this.e).lambda$startPrivateModeSetAsAlertDialog$0((MediaItem) this.f, dialogInterface, i2);
                return;
            case 2:
                ((CreatureHideCmd) this.e).lambda$hideThisCreatureAndFinishFragment$0((MediaItem) this.f, dialogInterface, i2);
                return;
            case 3:
                ((MxAlbumsHeaderPresenter) this.e).lambda$showNoticeProfileSharingDialog$0((MediaItem) this.f, dialogInterface, i2);
                return;
            case 4:
                ((SharingsFragment) this.e).lambda$showNoticeProfileSharingDialog$2((MediaItem) this.f, dialogInterface, i2);
                return;
            case 5:
                ((StartRemoteServerCmd) this.e).lambda$onExecute$3((String) this.f, dialogInterface, i2);
                return;
            case 6:
                ((UpdateContactPhotoDialog) this.e).lambda$createDialog$1((String) this.f, dialogInterface, i2);
                return;
            case 7:
                MissingPackage.lambda$showGotoSettingIfDisabled$1((String) this.f, (Context) this.e, dialogInterface, i2);
                return;
            default:
                ((CompareActivity) this.e).lambda$onCreate$1((MediaItem) this.f, dialogInterface, i2);
                return;
        }
    }

    public /* synthetic */ g(Context context, String str) {
        this.d = 7;
        this.f = str;
        this.e = context;
    }
}
