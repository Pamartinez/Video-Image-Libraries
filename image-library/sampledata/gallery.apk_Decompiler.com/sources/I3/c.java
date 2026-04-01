package I3;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureChoiceCmd;
import com.samsung.android.gallery.app.controller.creature.people.RelationshipPickerCmd;
import com.samsung.android.gallery.app.controller.internals.NondestructiveSaveRemasterCmd;
import com.samsung.android.gallery.app.ui.list.hover.HoverHandler;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2358a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2359c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    public /* synthetic */ c(RelationshipPickerCmd relationshipPickerCmd, AtomicLong atomicLong, MediaItem mediaItem, CreatureNameData creatureNameData) {
        this.f2358a = 1;
        this.f2359c = relationshipPickerCmd;
        this.d = atomicLong;
        this.b = mediaItem;
        this.e = creatureNameData;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2358a) {
            case 0:
                return ((MergeCreatureChoiceCmd) this.f2359c).lambda$executeMerge$1((String) this.d, (String) this.e, (MediaItem) this.b, jobContext);
            case 1:
                return ((RelationshipPickerCmd) this.f2359c).lambda$completed$1((AtomicLong) this.d, (MediaItem) this.b, (CreatureNameData) this.e, jobContext);
            case 2:
                return ((NondestructiveSaveRemasterCmd) this.f2359c).lambda$onExecute$0((String) this.d, (String) this.e, (Uri) this.b, jobContext);
            case 3:
                return ((HoverHandler) this.f2359c).lambda$loadHoverPreviewPopup$1((Context) this.d, (ListViewHolder) this.e, (ViewGroup) this.b, jobContext);
            default:
                return ((EditCreatureNamePresenter) this.f2359c).lambda$executeMerge$21((String) this.d, (String[]) this.e, (MediaItem[]) this.b, jobContext);
        }
    }

    public /* synthetic */ c(Object obj, Object obj2, Object obj3, Object obj4, int i2) {
        this.f2358a = i2;
        this.f2359c = obj;
        this.d = obj2;
        this.e = obj3;
        this.b = obj4;
    }
}
