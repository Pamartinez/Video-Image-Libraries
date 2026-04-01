package T3;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.widget.PopupMenu;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.dragdrop.popup.ListPopupMenuDelegate;
import com.samsung.android.gallery.app.ui.list.stories.category.TopColorEffectHandler;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.EventListener;
import com.samsung.android.gallery.app.ui.list.stories.category.category.StoriesCatBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem.StoriesCatItemAdapter;
import com.samsung.android.gallery.app.ui.map.search.SearchMarkerManagerDelegate;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.search.CreatureIndexingBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.dialog.ProgressDialogCompat;
import com.samsung.android.gallery.widget.filmstrip3.selection.SelectionFilmStripView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import com.sec.android.gallery3d.R;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ e(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Dialog) obj).dismiss();
                return;
            case 1:
                ((IBaseListView) obj).updateSelectionToolBar();
                return;
            case 2:
                Toast.makeText((Context) obj, ((Context) obj).getString(R.string.move_to_knox_max_number_exceeded, new Object[]{1000}), 0).show();
                return;
            case 3:
                ((QueryParams) obj).setGroupTypes(GroupType.BURST);
                return;
            case 4:
                ((Blackboard) obj).post("command://UiEventStartShrinkAnimation", (Object) null);
                return;
            case 5:
                FileUtils.delete((String) obj);
                return;
            case 6:
                ((ActivityManager) obj).clearApplicationUserData();
                return;
            case 7:
                ((FilterResultsEntity) obj).setSelected(true);
                return;
            case 8:
                ((ProgressDialogCompat) obj).dismiss();
                return;
            case 9:
                FileUtils.delete(((FileItemInterface) obj).getPath());
                return;
            case 10:
                ((Blackboard) obj).postEvent(EventMessage.obtain(1083));
                return;
            case 11:
                ((PopupMenu) obj).dismiss();
                return;
            case 12:
                ListPopupMenuDelegate.lambda$destroy$7((PopupMenu) obj);
                return;
            case 13:
                ((StoriesCatBaseViewHolder) obj).resume();
                return;
            case 14:
                ((StoriesCatBaseViewHolder) obj).pause();
                return;
            case 15:
                ((StoriesCatBaseViewHolder) obj).stop();
                return;
            case 16:
                ((StoriesCatBaseViewHolder) obj).destroy();
                return;
            case 17:
                ((EventListener) obj).handleDensityChange();
                return;
            case 18:
                ((EventListener) obj).invalidateLayout();
                return;
            case 19:
                ((TopColorEffectHandler) obj).destroy();
                return;
            case 20:
                ((TopColorEffectHandler) obj).adjustAlphaByPosition();
                return;
            case 21:
                TopColorEffectHandler.lambda$onPageSelected$2((ViewPager2) obj);
                return;
            case 22:
                SharedTransition.setTransitionName(((ListViewHolder) obj).getImage(), (String) null);
                return;
            case 23:
                ((SelectionFilmStripView) obj).notifyDataChanged();
                return;
            case 24:
                PeopleDataManager.sIndexingDelegate.index(new CreatureIndexingBuilder().unifiedId(Long.MIN_VALUE, ((Long) obj).longValue() + 100000).targetGroupId(((Long) obj).longValue()));
                return;
            case 25:
                ((SearchSelectedFiltersView) obj).clearDataExceptMainEntity();
                return;
            case 26:
                ((SearchSelectedFiltersView) obj).onDestroy();
                return;
            case 27:
                ((SearchMarkerManagerDelegate) obj).removeHighlightMarkers();
                return;
            case 28:
                ((View) obj).playSoundEffect(0);
                return;
            default:
                ((StoriesCatItemAdapter) obj).notifyDataSetChanged();
                return;
        }
    }
}
