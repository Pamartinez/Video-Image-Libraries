package g6;

import Yb.a;
import android.graphics.Bitmap;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.FloatingAutoCompleteDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultContainer;
import com.samsung.android.gallery.app.ui.list.stories.highlight.SharedTransitionHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.ThemeItemViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.bgmpicker.ThemeListAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.FilmStripViewDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.DualShotOptionsViewHandler;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.clip.textextraction.TextExtractionHandler;
import com.samsung.android.gallery.database.dal.local.recovery.RecoverCollector;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.fileio.compat.RestoreUserData;
import com.samsung.android.gallery.module.utils.ProgressServiceUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import q2.q;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2641a;
    public final /* synthetic */ Object b;

    public /* synthetic */ f(int i2, Object obj) {
        this.f2641a = i2;
        this.b = obj;
    }

    public final void accept(Object obj, Object obj2) {
        int i2 = this.f2641a;
        Object obj3 = this.b;
        switch (i2) {
            case 0:
                ((SharedTransitionHandler) obj3).lambda$applyFilter$7((Bitmap) obj, (Filter) obj2);
                return;
            case 1:
                ((RestoreUserData) obj3).lambda$init$2((String) obj, (String) obj2);
                return;
            case 2:
                ((FloatingAutoCompleteDelegate) obj3).onAutoCompleteItemsLoaded((ArrayList) obj, (String) obj2);
                return;
            case 3:
                ((ThemeListAdapter) obj3).onSelect((ThemeItemViewHolder) obj, ((Boolean) obj2).booleanValue());
                return;
            case 4:
                ((FragmentTransaction) obj3).hide((MvpBaseFragment) obj2);
                return;
            case 5:
                ((FilmStripViewDelegate) obj3).applyFilterBitmap((Bitmap) obj, (BiConsumer) obj2);
                return;
            case 6:
                ((RecoverCollector) obj3).callRecoveryApi((ArrayList<String>) (ArrayList) ((List) obj2), ((Integer) obj).intValue());
                return;
            case 7:
                ((q) obj3).invoke(obj, obj2);
                return;
            case 8:
                ((o1.f) obj3).invoke(obj, obj2);
                return;
            case 9:
                ((TransitionDelegate) obj3).lambda$createEnterTransitionBitmapMap$6((String) obj, (View) obj2);
                return;
            case 10:
                ((ProgressServiceUtil) obj3).lambda$continueIfServiceRunning$0((String) obj, (Blackboard) obj2);
                return;
            case 11:
                ThreadUtil.runOnUiThread(new a((ListViewHolder) obj3, (Bitmap) obj, 1));
                return;
            case 12:
                ((DualShotOptionsViewHandler) obj3).onDualPhotoChanged(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue());
                return;
            case 13:
                ((SearchClusterResultContainer) obj3).lambda$loadClusterDataIncludeCarousel$1((ClusterResultType) obj, (ArrayList) obj2);
                return;
            default:
                ((TextExtractionHandler) obj3).postAnalyticsDetailLog((String) obj, (String) obj2);
                return;
        }
    }
}
