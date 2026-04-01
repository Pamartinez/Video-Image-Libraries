package z5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchScreenShotHeaderViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.plugins.filebrowser.FileListFragment;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ f(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((SearchCreatureHeader2View) obj).lambda$setEnableContactDetail$8(view);
                return;
            case 1:
                ((SearchScreenShotHeaderViewHolder) obj).lambda$bindView$0(view);
                return;
            case 2:
                ((StoryPicturesBasePresenter) obj).onNavigationPressed(view);
                return;
            default:
                ((FileListFragment) obj).lambda$onBindView$0(view);
                return;
        }
    }
}
