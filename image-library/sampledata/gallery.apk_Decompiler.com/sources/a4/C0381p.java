package A4;

import com.samsung.android.gallery.app.controller.album.RemoveAutoUpdatedItemsCmd;
import com.samsung.android.gallery.app.controller.album.UpdateAlbumSyncStatusCmd;
import com.samsung.android.gallery.app.controller.trash.EmptyTrashCmd;
import com.samsung.android.gallery.app.controller.trash.RestoreTrashCmd;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureAdapter;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerAdapter;
import com.samsung.android.gallery.app.ui.dialog.tag.AddTagAdapter;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.HiddenCreaturePresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.PeopleSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.SuggestedCreatureSelectPresenter;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoiceFragment;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.SearchPicturesPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.module.search.rubin.SearchWordCollector;
import com.samsung.android.gallery.plugins.filebrowser.LogViewFragment;
import com.samsung.android.gallery.support.threadpool.Sequencer;
import com.samsung.android.gallery.support.threadpool.ThreadPool;

/* renamed from: A4.p  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0381p implements ThreadPool.Job {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2256a;
    public final /* synthetic */ Object b;

    public /* synthetic */ C0381p(int i2, Object obj) {
        this.f2256a = i2;
        this.b = obj;
    }

    public final Object run(ThreadPool.JobContext jobContext) {
        int i2 = this.f2256a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                return ((BaseListFragment) obj).lambda$onViewCreated$7(jobContext);
            case 1:
                return ((RemoveAutoUpdatedItemsCmd) obj).lambda$removeAutoUpdatedItems$0(jobContext);
            case 2:
                return ((UpdateAlbumSyncStatusCmd) obj).lambda$onExecute$0(jobContext);
            case 3:
                return ((AlbumPicturesPresenter) obj).lambda$onTipCardCloseClicked$10(jobContext);
            case 4:
                return ((EmptyTrashCmd) obj).showEmptyDialog(jobContext);
            case 5:
                return ((RestoreTrashCmd) obj).lambda$onExecute$1(jobContext);
            case 6:
                return ((Sequencer) obj).lambda$processParallel$0(jobContext);
            case 7:
                return ((SearchWordCollector) obj).lambda$clear$1(jobContext);
            case 8:
                return ((CategoryPresenter) obj).lambda$onDataPrepared$3(jobContext);
            case 9:
                return ((HiddenCreaturePresenter) obj).lambda$selectCreatures$0(jobContext);
            case 10:
                return ((PeopleSelectPresenter) obj).lambda$saveSelectedPeople$1(jobContext);
            case 11:
                return ((SuggestedCreatureSelectPresenter) obj).lambda$selectCreatures$1(jobContext);
            case 12:
                return ((CreatureCoverChoiceFragment) obj).lambda$updateEditCreatureNameHeader$4(jobContext);
            case 13:
                return ((EditCreatureNamePresenter) obj).lambda$onPositiveClicked$9(jobContext);
            case 14:
                return ((MergeCreatureAdapter) obj).lambda$load$2(jobContext);
            case 15:
                return ((CreaturePickerAdapter) obj).lambda$load$4(jobContext);
            case 16:
                return ((SearchPicturesPresenter) obj).lambda$publishLocationHeaderItem$12(jobContext);
            case 17:
                return ((CreaturePicturesDelegate) obj).lambda$publishCreatureHeaderItem$1(jobContext);
            case 18:
                return ((AddTagAdapter) obj).lambda$new$0(jobContext);
            case 19:
                return ((StoryPicturesBasePresenter) obj).lambda$updateStoryNotifyStatus$2(jobContext);
            default:
                return ((LogViewFragment) obj).lambda$onBindView$1(jobContext);
        }
    }
}
