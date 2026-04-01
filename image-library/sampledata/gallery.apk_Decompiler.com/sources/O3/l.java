package O3;

import Pb.b;
import Pb.c;
import android.content.Context;
import android.view.View;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.app.ui.list.stories.StoriesViewAdapter;
import com.samsung.android.gallery.app.ui.list.suggestions.remaster.RemasterPicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.timeline.TimelineFragment;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.list.YearQueryCache;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.predictiveback.SimpleOnBackPressedCallback;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import java.io.Closeable;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ l(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((Runnable) obj).run();
                return;
            case 1:
                ((GalleryAppBarLayout) obj).setSubtitle((CharSequence) null);
                return;
            case 2:
                ((GalleryToolbar) obj).setTitle("");
                return;
            case 3:
                ((GalleryToolbar) obj).setSubtitle((CharSequence) "");
                return;
            case 4:
                ((GalleryAppBarLayout) obj).setExpanded(true);
                return;
            case 5:
                SharingPicturesFragment.lambda$adjustToolbarLayout$5((GalleryToolbar) obj);
                return;
            case 6:
                ((GalleryAppBarLayout) obj).setTitle((CharSequence) null);
                return;
            case 7:
                ((PreferenceScreen) obj).removeAll();
                return;
            case 8:
                SeApiCompat.announceVoiceAssistant((Context) obj, ((Context) obj).getString(R.string.sharing_album_setting));
                return;
            case 9:
                ((GalleryAppBarLayout) obj).setTitle((CharSequence) "Manage categories");
                return;
            case 10:
                ((Preference) obj).setEnabled(false);
                return;
            case 11:
                SharingPicturesSettingFragment.lambda$onViewCreated$3((GalleryAppBarLayout) obj);
                return;
            case 12:
                ((RemasterPicturesViewAdapter) obj).updateLayout();
                return;
            case 13:
                ((Blackboard) obj).post("command:///OnBackPressInvokableStateChanged", (Object) null);
                return;
            case 14:
                ((Blackboard) obj).post("command:///OnPredictiveBackPressedInFragment", (Object) null);
                return;
            case 15:
                ((Blackboard) obj).post("command:///OnPredictiveBackCanceledInFragment", (Object) null);
                return;
            case 16:
                ((c) ((SimpleOnBackPressedCallback.OnBackPressedListener) obj)).f2865a.onBackPressedInternal();
                return;
            case 17:
                ((b) ((SimpleOnBackPressedCallback.OnBackCancelledListener) obj)).f2864a.lambda$new$2();
                return;
            case 18:
                ((FragmentActivity) obj).finish();
                return;
            case 19:
                Utils.closeSilently((Closeable) obj);
                return;
            case 20:
                ((GalleryListView) obj).checkIfEmpty();
                return;
            case 21:
                YearQueryCache.getInstance().syncIfChanged(((MediaData) obj).getDataHash(), new C0451a(10, (MediaData) obj));
                return;
            case 22:
                ((Blackboard) obj).post("command://UiEventStartShrinkAnimation", (Object) null);
                return;
            case 23:
                ((GalleryAppBarLayout) obj).setTitle((CharSequence) null);
                return;
            case 24:
                TimelineFragment.lambda$handleResolutionChange$10((PicturesViewAdapter) obj);
                return;
            case 25:
                ((GalleryToolbar) obj).setCheckBoxEnabled(false);
                return;
            case 26:
                ViewUtils.setVisibility((View) obj, 8);
                return;
            case 27:
                ((StoriesViewAdapter) obj).checkVisibleViewHoldersForXLarge();
                return;
            case 28:
                ((PopupMenu) obj).show();
                return;
            default:
                ((View) obj).setRotation(0.0f);
                return;
        }
    }
}
