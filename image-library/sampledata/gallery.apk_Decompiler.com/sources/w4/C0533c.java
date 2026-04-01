package w4;

import android.graphics.Bitmap;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.ui.dialog.hidescene.HideSceneSelectionDialogAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultPresenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.creature.CreaturePicturesDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.dragdrop.dragshadow.DragShadowCreator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;

/* renamed from: w4.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0533c implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ C0533c(Object obj, Object obj2, Object obj3, int i2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
        this.g = obj3;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((HideSceneSelectionDialogAdapter) this.e).lambda$bindThumbnail$0((ListViewHolder) this.f, (Bitmap) this.g);
                return;
            case 1:
                ((SearchClusterResultPresenter) this.e).lambda$setSubtitle$4((Toolbar) this.f, (String) this.g);
                return;
            case 2:
                ((DragShadowCreator) this.e).lambda$getBitmapFromRef$5((ArrayList) this.f, (Bitmap[]) this.g);
                return;
            case 3:
                ((StoryHighlightViewPagerAdapter) this.e).lambda$onThumbnailLoadCompleted$0((ViewPagerHolder) this.f, (Bitmap) this.g);
                return;
            case 4:
                ((CreaturePicturesDelegate) this.e).lambda$addCreatureMainFilterOnHeaderItemLoaded$5((MediaItem) this.f, (Bitmap) this.g);
                return;
            case 5:
                ((SearchCreatureHeader2View) this.e).lambda$updateRecommendedContactHeader$5((String) this.f, (CreatureNameData) this.g);
                return;
            case 6:
                ((SearchCreatureHeaderView) this.e).lambda$updateRecommendedContactHeader$1((String) this.f, (CreatureNameData) this.g);
                return;
            default:
                ((SearchCreatureHeaderView) this.e).lambda$bindImage$17((MediaItem) this.f, (Bitmap) this.g);
                return;
        }
    }
}
