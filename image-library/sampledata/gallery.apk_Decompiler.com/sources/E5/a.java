package E5;

import android.content.Context;
import android.net.Uri;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.creature.HideCreatureCmd;
import com.samsung.android.gallery.app.controller.story.EditNotifiedContentCmd;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history.FloatingHistoryCoverItemLoader;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.creature.base.CreatureNameDataLoader;
import com.samsung.android.gallery.module.creature.base.LoadFinishedListener;
import com.samsung.android.gallery.module.creature.people.PersonNameDataLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.history.SearchHistory;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2317a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2318c;
    public final /* synthetic */ Object d;

    public /* synthetic */ a(Object obj, Object obj2, Object obj3, int i2) {
        this.f2317a = i2;
        this.f2318c = obj;
        this.b = obj2;
        this.d = obj3;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2317a) {
            case 0:
                return ((FloatingHistoryCoverItemLoader) this.f2318c).lambda$load$2((ArrayList) this.b, (Consumer) this.d, jobContext);
            case 1:
                return ((HideCreatureCmd) this.f2318c).lambda$onExecute$0((ArrayList) this.b, (ArrayList) this.d, jobContext);
            case 2:
                return ((AlbumPicturesPresenter) this.f2318c).lambda$updateToolbar$0((Toolbar) this.b, (MediaItem) this.d, jobContext);
            case 3:
                return ((EditNotifiedContentCmd) this.f2318c).lambda$onExecute$0((MediaItem) this.b, (Context) this.d, jobContext);
            case 4:
                return ((CreatureNameDataLoader) this.f2318c).lambda$load$0((CreatureNameData.ContactType[]) this.b, (LoadFinishedListener) this.d, jobContext);
            case 5:
                return ((PersonNameDataLoader) this.f2318c).lambda$loadContactDataFromContact$1((Uri) this.b, (LoadFinishedListener) this.d, jobContext);
            default:
                return ((SearchHistory) this.f2318c).lambda$delete$1((String) this.b, (String[]) this.d, jobContext);
        }
    }
}
