package B8;

import A8.C0545b;
import Fa.C0571z;
import G6.a;
import android.view.View;
import androidx.core.util.Pair;
import com.samsung.android.gallery.app.ui.list.dragdrop.AlbumsDragAndDropDelegate;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.analyticsquery.SasCount;
import com.samsung.android.gallery.module.bgm.updater.BgmUpdateListener;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.creature.pet.PetDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.ClipInfo;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.service.download.DownloadTask;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersView;
import com.samsung.android.sdk.sgpl.pip.core.Encode;
import java.util.ArrayList;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ ArrayList e;

    public /* synthetic */ d(ArrayList arrayList, int i2) {
        this.d = i2;
        this.e = arrayList;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        ArrayList arrayList = this.e;
        switch (i2) {
            case 0:
                arrayList.add(IdentityCreatureUtil.createPeopleUnifiedId(((Long) obj).longValue()));
                return;
            case 1:
                arrayList.add(IdentityCreatureUtil.createPetUnifiedId(((Long) obj).longValue()));
                return;
            case 2:
                SasCount.lambda$keys$0(arrayList, (int[]) obj);
                return;
            case 3:
                ((BgmUpdateListener) obj).onUpdated(arrayList);
                return;
            case 4:
                arrayList.add(Integer.valueOf(((MediaItem) obj).getAlbumID()));
                return;
            case 5:
                arrayList.add(Integer.valueOf(((MediaItem) obj).getAlbumID()));
                return;
            case 6:
                arrayList.add((MediaPlayback) obj);
                return;
            case 7:
                arrayList.add(Encode.TrimInfo.create(((long) ((PlaybackOption) obj).mStartMs) * 1000, ((long) ((PlaybackOption) obj).mEndMs) * 1000));
                return;
            case 8:
                arrayList.add(((PeopleData) obj).clone());
                return;
            case 9:
                arrayList.add((Integer) obj);
                return;
            case 10:
                arrayList.addAll((ArrayList) obj);
                return;
            case 11:
                arrayList.add(((MediaItem) obj).getPath());
                return;
            case 12:
                arrayList.add(Pair.create((View) obj, "fastOption"));
                return;
            case 13:
                arrayList.addAll(PeopleDataManager.getMediaIdListByUnifiedId((Long) obj));
                return;
            case 14:
                arrayList.add(Integer.valueOf(((MediaItem) obj).getAlbumID()));
                return;
            case 15:
                ((QueryParams) obj).addGroupType(GroupType.SINGLE_TAKEN).addAlbumIds(arrayList.stream().mapToInt(new C0545b(15)).toArray());
                return;
            case 16:
                AlbumsDragAndDropDelegate.lambda$getDragAlbums$7(arrayList, (MediaItem) obj);
                return;
            case 17:
                arrayList.addAll(PeopleDataManager.getMediaIdListByUnifiedId((Long) obj));
                return;
            case 18:
                ((SearchSelectedFiltersView) obj).swapFilter(arrayList);
                return;
            case 19:
                arrayList.addAll(PetDataManager.getMediaIdListByUnifiedId((Long) obj));
                return;
            case 20:
                arrayList.remove((String) obj);
                return;
            case 21:
                arrayList.addAll((ArrayList) obj);
                return;
            case 22:
                arrayList.addAll((ArrayList) obj);
                return;
            case 23:
                arrayList.add((MediaItem) obj);
                return;
            case 24:
                arrayList.add(((PeopleData) obj).clone());
                return;
            case 25:
                arrayList.stream().filter(new C0571z(4)).filter(new j((MediaItem) obj, 7)).findAny().ifPresent(new a((MediaItem) obj, 8));
                return;
            case 26:
                arrayList.add((ClusterResultsEntity) obj);
                return;
            case 27:
                arrayList.addAll(((DownloadTask) obj).getRemains());
                return;
            case 28:
                arrayList.addAll(((ClipInfo) obj).playbackOptions);
                return;
            default:
                arrayList.add(new MediaPlayback(((PlaybackOption) obj).mStartMs, ((PlaybackOption) obj).mEndMs, ((PlaybackOption) obj).mSpeed));
                return;
        }
    }
}
