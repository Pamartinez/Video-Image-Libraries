package h3;

import android.view.MenuItem;
import android.view.MotionEvent;
import com.samsung.android.app.sdk.deepsky.barcode.parser.adapter.UriBarcodeAdapterFactory;
import com.samsung.android.gallery.app.ui.dialog.CreateNameDialog;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureSelectPresenterV2;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResult;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.MapPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.TitleEditDelegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.theme.StoryThemeView;
import com.samsung.android.gallery.module.bgm.BgmUriService;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.module.knox.KnoxOperationTask;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.plugins.compare.CompareActivity;
import com.samsung.android.gallery.widget.bottom.BottomBar;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1780a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b(int i2, Object obj) {
        this.f1780a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f1780a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return UriBarcodeAdapterFactory.isSamsungAccountUri$lambda$3((a) obj2, obj);
            case 1:
                return ((CreatureSelectPresenterV2) obj2).lambda$getCreatureNamesArray$5((MediaItem) obj);
            case 2:
                return BottomBar.lambda$containsSubMenu$3((MenuItem) obj2, (MenuItem) obj);
            case 3:
                return ((CreateNameDialog) obj2).predicateText((CharSequence) obj);
            case 4:
                return ((KnoxOperationTask) obj2).lambda$addFilePath$4((String) obj);
            case 5:
                return ((MapPage) obj2).isValidLocation((MediaItem) obj);
            case 6:
                return ((TitleEditDelegate) obj2).predicateText((CharSequence) obj);
            case 7:
                return ((BgmUriService) obj2).isDownloaded((String) obj);
            case 8:
                return StoryThemeView.lambda$getMatchedTheme$2((Filter) obj2, (EffectTheme) obj);
            case 9:
                return ((ClusterResult) obj).isVirtualCtrlKeyPressedAllowablePoint((MotionEvent) obj2);
            default:
                return ((CompareActivity) obj2).lambda$onActivityResult$5((MediaItem) obj);
        }
    }
}
