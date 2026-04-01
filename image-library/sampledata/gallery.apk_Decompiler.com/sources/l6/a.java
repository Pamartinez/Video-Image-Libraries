package l6;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.work.WorkerKt;
import com.samsung.android.gallery.app.ui.dialog.hidescene.HideSceneSelectionDialogAdapter;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryLocationHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryPeopleItemViewHolder;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcResultAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior.StoryHighlightBehavior;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RecapCoverPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.RelatedViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.irregular.IrregularView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.details.DetailsListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import h2.s;
import h2.t;
import java.util.concurrent.Executor;
import n0.C0235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements s, CallbackToFutureAdapter.Resolver, ThumbnailLoadedListener, ListViewHolder.OnItemClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ a(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
        return WorkerKt.future$lambda$2((Executor) this.e, (Ae.a) this.f, completer);
    }

    public WindowInsetsCompat b(View view, WindowInsetsCompat windowInsetsCompat, t tVar) {
        return ((StoryHighlightBehavior) this.e).lambda$setWindowInsetsListener$4((View) this.f, view, windowInsetsCompat, tVar);
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((PdcResultAdapter) this.e).lambda$onBindViewHolder$0((CategoryPeopleItemViewHolder) this.f, listViewHolder, i2, mediaItem, i7);
    }

    public void onLoaded(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        switch (this.d) {
            case 2:
                ((CategoryLocationHeaderViewHolder) this.e).lambda$loadMarkerBitmap$1((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 3:
                ((RecapCoverPage) this.e).lambda$loadBindThumbnail$0((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 4:
                ((RelatedViewAdapter) this.e).lambda$bindThumbnail$2((ListViewHolder) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 5:
                ((IrregularView) this.e).lambda$bindMainItem$0((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 6:
                ((TransitionDelegate) this.e).lambda$loadPreview$4((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 7:
                ((DetailsListAdapter) this.e).lambda$bindThumbnail$2((ListViewHolder) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 8:
                ThreadUtil.postOnUiThread(new C0235b((ImageView) this.e, bitmap, (MediaItem) this.f, 23));
                return;
            case 10:
                ((PdcResultAdapter) this.e).lambda$bindThumbnail$2((ListViewHolder) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 11:
                ((HideSceneSelectionDialogAdapter) this.e).lambda$bindThumbnail$1((ListViewHolder) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 12:
                ((CreaturePicturesDelegate) this.e).lambda$addCreatureMainFilterOnHeaderItemLoaded$6((MediaItem) this.f, bitmap, uniqueKey, thumbKind);
                return;
            case 13:
                ((SearchCreatureHeader2View) this.e).lambda$initContactRecommendThumbnail$7((CreatureNameData) this.f, bitmap, uniqueKey, thumbKind);
                return;
            default:
                ((SearchCreatureHeaderView) this.e).lambda$initContactRecommendThumbnail$3((CreatureNameData) this.f, bitmap, uniqueKey, thumbKind);
                return;
        }
    }
}
