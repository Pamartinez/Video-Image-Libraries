package com.samsung.scsp.media.api;

import android.animation.ValueAnimator;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.PositionInTrips;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItem;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItemFactory;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.ScrollText;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import com.samsung.android.gallery.module.fileio.database.helper.MyTagCopyOperation;
import com.samsung.android.gallery.module.secured.KeepStorageManager;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.widget.awesome.IAwesomeItem;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.framework.core.api.SCHashMap;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4223a;

    public /* synthetic */ d(int i2) {
        this.f4223a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4223a) {
            case 0:
                return ((SCHashMap) obj).getAsString(MediaApiContract.Parameter.ORIGINAL_URL);
            case 1:
                return ((SCHashMap) obj).getAsLong(FileApiContract.Parameter.RANGE_START);
            case 2:
                return ((SCHashMap) obj).getAsLong(MediaApiContract.Parameter.ORIGINAL_UPLOAD_RANGE_START);
            case 3:
                return ((SCHashMap) obj).getAsLong(FileApiContract.Parameter.RANGE_START);
            case 4:
                return ((SCHashMap) obj).getAsString("url");
            case 5:
                return KeepStorageManager.lambda$getCounter$0((String) obj);
            case 6:
                return KeepStorageManager.lambda$toString$1((Map.Entry) obj);
            case 7:
                return ((String) obj).toLowerCase(Locale.US);
            case 8:
                return ((String) obj).split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            case 9:
                return PrivateDatabase.lambda$getNdeFile$6((String[]) obj);
            case 10:
                return ((String) obj).split("=| is | like ", 2);
            case 11:
                return PicturesFragment.lambda$onBindView$0((GalleryAppBarLayout) obj);
            case 12:
                return ((ImageView) obj).getTransitionName();
            case 13:
                return ((ScrollText) obj).getDate();
            case 14:
                return HideRuleViewHolder.lambda$bindListView$0((MediaItem[]) obj);
            case 15:
                return Boolean.valueOf(((List) obj).isEmpty());
            case 16:
                return Boolean.valueOf(((Blackboard) obj).hasSubscriber("command://bottomtab/select"));
            case 17:
                return Integer.valueOf(PositionInTrips.getStartYear((MediaItem) obj));
            case 18:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getFileId());
            case 19:
                return Integer.valueOf(AiEditItemFactory.getSortingOrder(((AiEditItem) obj).getClass()));
            case 20:
                return Integer.valueOf(AiEditItemFactory.getSortingOrder(((IAwesomeItem) obj).getClass()));
            case 21:
                return String.valueOf(((FileItemInterface) obj).getFileId());
            case 22:
                return Boolean.valueOf(((FileItemInterface) obj).isPrivateItem());
            case 23:
                return FileUtils.getNameFromPath((String) obj);
            case 24:
                return ((ValueAnimator) obj).getAnimatedValue();
            case 25:
                return DatabaseOperation.lambda$addValuesWithApply$3((Integer) obj);
            case 26:
                return DatabaseOperation.lambda$addValuesWithApply$4((String) obj);
            case 27:
                return DatabaseOperation.lambda$addValuesWithApply$5((Integer) obj);
            case 28:
                return DatabaseOperation.lambda$addValuesWithApply$6((String) obj);
            default:
                return MyTagCopyOperation.lambda$addValues$0((String) obj);
        }
    }
}
