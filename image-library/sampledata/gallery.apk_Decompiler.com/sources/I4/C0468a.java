package i4;

import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.samsung.android.gallery.app.ui.container.phone.BottomTabPresenter;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureHidePresenter;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BixbyDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.grid.GridHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerDelegate;
import com.samsung.android.gallery.widget.videoview.mediaplayer.PlayState;
import com.samsung.android.gallery.widget.window.WindowInsetManager;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Function;

/* renamed from: i4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0468a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2649a;

    public /* synthetic */ C0468a(int i2) {
        this.f2649a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2649a) {
            case 0:
                return ((ViewGroup) obj).getLayoutParams();
            case 1:
                return MediaPlayerDelegate.lambda$pause$12((PlayState) obj);
            case 2:
                return MediaPlayerDelegate.lambda$seekTo$17((PlayState) obj);
            case 3:
                return MediaPlayerDelegate.lambda$visualSeekTo$21((PlayState) obj);
            case 4:
                return MediaPlayerDelegate.lambda$seekFinish$15((PlayState) obj);
            case 5:
                return MediaPlayerDelegate.lambda$setVolume$10((PlayState) obj);
            case 6:
                return MediaPlayerDelegate.lambda$setPlaybackRange$23((PlayState) obj);
            case 7:
                return MediaPlayerDelegate.lambda$setRenderingPosition$3((PlayState) obj);
            case 8:
                return MediaPlayerDelegate.lambda$play$0((PlayState) obj);
            case 9:
                return MediaPlayerDelegate.lambda$play$2((PlayState) obj);
            case 10:
                return MediaPlayerDelegate.lambda$seekBegin$13((PlayState) obj);
            case 11:
                return ((ViewParent) obj).getParent();
            case 12:
                return DynamicViewData.of((MediaItem) obj).dynamicViewPlayInfo;
            case 13:
                return Integer.valueOf(((DynamicViewPlayInfo) obj).getType());
            case 14:
                return ((BixbyDelegate) obj).getShareComponent();
            case 15:
                return ((BixbyDelegate) obj).getEditMode();
            case 16:
                return ((BixbyDelegate) obj).getEditSubInfo();
            case 17:
                return DynamicViewData.of((MediaItem) obj).dynamicViewPlayInfo;
            case 18:
                return Integer.valueOf(((DynamicViewPlayInfo) obj).getShortClipIndex());
            case 19:
                return new SecureFile(FileUtils.getParentDirectory((String) obj)).getName();
            case 20:
                return Boolean.valueOf(((ViewerMenuDelegate) obj).supportFastOption());
            case 21:
                return Boolean.valueOf(SuperHdrConfig.isSystemEnabled());
            case 22:
                return DebugLogger.b(((Integer) obj).intValue());
            case 23:
                return WindowInsetManager.lambda$getInstance$0((String) obj);
            case 24:
                return BottomTabPresenter.lambda$isMoveMode$3((Blackboard) obj);
            case 25:
                return ((Menu) obj).findItem(R.id.action_save);
            case 26:
                return DbTable.lambda$getProjection$0((ArrayList) obj);
            case 27:
                return GridHelper.createGridHelper((String) obj);
            case 28:
                return CreatureHidePresenter.lambda$getChangedMap$2((String) obj);
            default:
                return ((MediaItem) ((MediaItem) obj)).getTitle();
        }
    }
}
