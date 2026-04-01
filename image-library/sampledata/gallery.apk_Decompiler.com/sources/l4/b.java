package l4;

import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.search.analysis.SearchAnalysisTipView;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryFragment;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryItemAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResultContainer;
import com.samsung.android.gallery.app.ui.list.search.suggestionview.SuggesterView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.details.DetailsView;
import com.samsung.android.gallery.widget.drawer.DrawerRecyclerView;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ boolean e;

    public /* synthetic */ b(boolean z, int i2) {
        this.d = i2;
        this.e = z;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        boolean z = this.e;
        switch (i2) {
            case 0:
                ((MvpBaseFragment) obj).setDefaultExitTransitioning(z);
                return;
            case 1:
                ((MvpBaseFragment) obj).setDefaultExitTransitioning(z);
                return;
            case 2:
                CreatureCategoryFragment.lambda$onSelectionModeChanged$1(z, (CreatureCategoryItemAdapter) obj);
                return;
            case 3:
                ((SuggesterView) obj).setEnable(!z);
                return;
            case 4:
                ((SearchAnalysisTipView) obj).setEnable(!z);
                return;
            case 5:
                CreatureData.of((MediaItem) obj).isCreatureHide = !z;
                return;
            case 6:
                ((Delegate) obj).onMultiWindowModeChanged(z);
                return;
            case 7:
                ((DrawerRecyclerView) obj).setAnimating(z);
                return;
            case 8:
                ((AlertDialog) obj).getButton(-1).setEnabled(z);
                return;
            case 9:
                DetailsView.lambda$setImportantForAccessibility$1(z, (LinearLayout) obj);
                return;
            case 10:
                DetailsView.lambda$setImportantForAccessibility$2(z, (Button) obj);
                return;
            case 11:
                ((SearchHeaderView) obj).showCountHeaderOnly(z);
                return;
            case 12:
                ((SearchHeaderView) obj).setEnabled(z);
                return;
            case 13:
                ((SearchHeaderView) obj).setEnabled(z);
                return;
            default:
                ((SearchClusterResultContainer) obj).setEnabled(z);
                return;
        }
    }
}
