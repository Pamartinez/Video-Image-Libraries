package K3;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.creature.people.CommitPeopleNContactCmd;
import com.samsung.android.gallery.app.controller.creature.pet.CommitPetCmd;
import com.samsung.android.gallery.app.controller.internals.FixDateTimeCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.lottie.recap.binder.RecapImageAssetDelegateForRecorder;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

/* renamed from: K3.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0404a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0404a(long j2, ArrayList arrayList) {
        this.d = 3;
        this.e = j2;
        this.f = arrayList;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((CommitPeopleNContactCmd) this.f).lambda$mergeCreature$0(this.e, (MediaItem) obj);
                return;
            case 1:
                ((CommitPetCmd) this.f).lambda$mergeCreature$0(this.e, (MediaItem) obj);
                return;
            case 2:
                ((FixDateTimeCmd) this.f).lambda$fixDateTimeOver3000$6(this.e, (MediaItem) obj);
                return;
            default:
                RecapImageAssetDelegateForRecorder.lambda$freeUnusedBitmap$3(this.e, (ArrayList) this.f, (Map.Entry) obj);
                return;
        }
    }

    public /* synthetic */ C0404a(BaseCommand baseCommand, long j2, int i2) {
        this.d = i2;
        this.f = baseCommand;
        this.e = j2;
    }
}
