package t4;

import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureMultipleDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.relationship.RelationshipChoiceAdapter;
import com.samsung.android.gallery.database.dal.mp.impl.CategoriesImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryApiImpl;
import com.samsung.android.gallery.module.trash.helper.TrashMpRecoverTask;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorDexImpl;
import com.samsung.android.gallery.widget.dragdrop.clipdata.ClipDataCreatorImpl;
import java.util.function.IntFunction;

/* renamed from: t4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0517a implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2707a;

    public /* synthetic */ C0517a(int i2) {
        this.f2707a = i2;
    }

    public final Object apply(int i2) {
        switch (this.f2707a) {
            case 0:
                return MergeCreatureMultipleDialog.lambda$initDialog$0(i2);
            case 1:
                return MergeCreatureMultipleDialog.lambda$initDialog$1(i2);
            case 2:
                return CategoriesImpl.lambda$getScreenShotCursor$12(i2);
            case 3:
                return CategoriesImpl.lambda$getScreenShotCursor$14(i2);
            case 4:
                return MpStoryApiImpl.lambda$addHideSceneRule$3(i2);
            case 5:
                return MpStoryApiImpl.lambda$deleteStory$0(i2);
            case 6:
                return TrashMpRecoverTask.lambda$getTrashFileList$0(i2);
            case 7:
                return RelationshipChoiceAdapter.lambda$onStartEditDialog$8(i2);
            case 8:
                return ClipDataCreatorDexImpl.lambda$createClipDescription$0(i2);
            default:
                return ClipDataCreatorImpl.lambda$get$1(i2);
        }
    }
}
