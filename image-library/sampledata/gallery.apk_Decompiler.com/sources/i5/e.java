package I5;

import android.app.Activity;
import android.net.Uri;
import android.view.Menu;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.creature.abstraction.CreatureHideCmd;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalImageCmd;
import com.samsung.android.gallery.app.controller.internals.SaveAsRemasterCmd;
import com.samsung.android.gallery.app.controller.trash.RestoreTrashCmd;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.FaceClusterMergeDelegate;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.RelationshipView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.module.creature.base.LoadFinishedListener;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.people.PersonNameDataLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.myquery.MyQueryUtil;
import com.samsung.android.gallery.plugins.filebrowser.BitmapLoader;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2372a;
    public final /* synthetic */ Object b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f2373c;

    public /* synthetic */ e(int i2, Object obj, Object obj2) {
        this.f2372a = i2;
        this.b = obj;
        this.f2373c = obj2;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        switch (this.f2372a) {
            case 0:
                return ((RelationshipView) this.b).lambda$bind$0((String) this.f2373c, jobContext);
            case 1:
                return ((CreatureHideCmd) this.b).lambda$hide$1((ArrayList) this.f2373c, jobContext);
            case 2:
                return ((RevertOriginalImageCmd) this.b).lambda$executeInternal$1((MediaItem) this.f2373c, jobContext);
            case 3:
                return ((SaveAsRemasterCmd) this.b).lambda$onExecute$0((MediaItem) this.f2373c, jobContext);
            case 4:
                return ((RestoreTrashCmd) this.b).lambda$onExecute$0((Object[]) this.f2373c, jobContext);
            case 5:
                return PersonNameDataLoader.loadContactDataFromSimilarContact((LoadFinishedListener) this.b, (long[]) this.f2373c);
            case 6:
                return ((SearchToolbar) this.b).lambda$updateSearchableInfo$1((Activity) this.f2373c, jobContext);
            case 7:
                return ((CategoryPresenter) this.b).lambda$updateToolbar$1((Toolbar) this.f2373c, jobContext);
            case 8:
                return ((CreaturePicturesDelegate) this.b).lambda$updateCreatureMenu$4((Menu) this.f2373c, jobContext);
            case 9:
                return PeopleDataManager.merge(new String[]{(String) this.f2373c}, (String) this.b, MyQueryUtil.getInterface());
            case 10:
                return ((FaceClusterMergeDelegate) this.b).lambda$reloadLastMergedItem$10((String) this.f2373c, jobContext);
            case 11:
                return ((StoryPicturesBasePresenter) this.b).lambda$onAddItemFromPhotoEditor$5((Uri) this.f2373c, jobContext);
            default:
                return ((BitmapLoader) this.b).lambda$decodeThumbnail$0((String) this.f2373c, jobContext);
        }
    }

    public /* synthetic */ e(String str, String str2) {
        this.f2372a = 9;
        this.f2373c = str;
        this.b = str2;
    }
}
