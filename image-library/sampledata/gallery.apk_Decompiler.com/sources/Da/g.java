package Da;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;
import androidx.preference.SwitchPreferenceCompat;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.creature.EditCreatureDialogDelegate;
import com.samsung.android.gallery.app.ui.list.sharings.SharingsContextMenuHandler;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.portrait.SetAsWallpaperDialog;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import com.samsung.android.gallery.settings.ui.LabsDeveloperFragment;
import com.samsung.android.gallery.settings.ui.delegate.DialogDelegate;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements DialogInterface.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ g(DialogDelegate dialogDelegate, SwitchPreferenceCompat switchPreferenceCompat, Context context) {
        this.d = 4;
        this.e = dialogDelegate;
        this.g = switchPreferenceCompat;
        this.f = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i2) {
        switch (this.d) {
            case 0:
                ((SetAsWallpaperDialog) this.e).lambda$show$0((Context) this.f, (String) this.g, dialogInterface, i2);
                return;
            case 1:
                ((LabsAlbumBnRFragment) this.e).lambda$onRestoreAlbumDbClicked$3((AtomicInteger) this.f, (ArrayList) this.g, dialogInterface, i2);
                return;
            case 2:
                SimpleThreadPool.getInstance().execute(new D7.g(13, new File((String) this.g, ((String[]) this.e)[((AtomicInteger) this.f).get()])));
                return;
            case 3:
                ((LabsDeveloperFragment) this.e).lambda$initPreferenceDevOptions$16((EditText) this.f, (EditText) this.g, dialogInterface, i2);
                return;
            case 4:
                ((DialogDelegate) this.e).lambda$showTrashTurnOffDialog$3((SwitchPreferenceCompat) this.g, (Context) this.f, dialogInterface, i2);
                return;
            case 5:
                ((EditCreatureDialogDelegate) this.e).lambda$showMergeWithLinkedContactDialog$2((CreatureNameData) this.f, (Consumer) this.g, dialogInterface, i2);
                return;
            default:
                ((SharingsContextMenuHandler) this.e).lambda$showActionDialog$0((EventContext) this.f, (MediaItem) this.g, dialogInterface, i2);
                return;
        }
    }

    public /* synthetic */ g(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public /* synthetic */ g(String str, String[] strArr, AtomicInteger atomicInteger) {
        this.d = 2;
        this.g = str;
        this.e = strArr;
        this.f = atomicInteger;
    }
}
