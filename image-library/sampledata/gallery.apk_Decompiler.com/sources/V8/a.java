package V8;

import android.content.pm.Signature;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.service.AddTagService;
import com.samsung.android.gallery.app.service.DownloadService;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.tables.SearchCreatureSorter;
import com.samsung.android.gallery.module.fileio.compat.FileOpJob;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntity;
import com.samsung.android.gallery.module.search.root.ClusterResultsEntry;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import java.text.Collator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2887a;

    public /* synthetic */ a(int i2) {
        this.f2887a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f2887a) {
            case 0:
                return IdentityCreatureUtil.createWithUnifiedId(((Long) obj).longValue(), IdentityCreatureUtil.Category.PEOPLE);
            case 1:
                return Integer.valueOf(((Display) obj).getFlags());
            case 2:
                return ((View) obj).getParent();
            case 3:
                return Integer.valueOf(((ViewGroup) obj).getHeight());
            case 4:
                return Integer.valueOf(((GalleryToolbar) obj).getHeight());
            case 5:
                return String.valueOf(((double[]) obj)[0]);
            case 6:
                return String.valueOf(((double[]) obj)[1]);
            case 7:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getDateTaken());
            case 8:
                return IdentityCreatureUtil.createWithUnifiedId(((Long) obj).longValue(), IdentityCreatureUtil.Category.PET);
            case 9:
                return PersonalDataCore.lambda$getInstance$0((String) obj);
            case 10:
                return StorageInfo.getInstance((String) obj);
            case 11:
                return Integer.toHexString(((Signature) obj).hashCode());
            case 12:
                return Integer.valueOf(TimeUtil.getYearInt(MediaItemStory.getStoryStartTime((MediaItem) obj)));
            case 13:
                return Collator.getInstance((Locale) obj);
            case 14:
                return Objects.toString((Long) obj);
            case 15:
                return Boolean.valueOf(((MediaItem) obj).isFolder());
            case 16:
                return ItemProperty.lambda$getItemProperty$0((String) obj);
            case 17:
                return SearchCreatureSorter.lambda$sort$0((String) obj);
            case 18:
                return String.valueOf(((TextView) obj).getText());
            case 19:
                return ((Blackboard) obj).getName();
            case 20:
                return ((IRecommendationItem) obj).getTitle();
            case 21:
                return Long.valueOf(((MediaItem) ((MediaItem) obj)).getDateTaken());
            case 22:
                return ((ClusterResultsEntity) obj).getName();
            case 23:
                return ClusterResultsEntry.Extractor.lambda$setAllEntityMap$0((String) obj);
            case 24:
                return ((FilterResultsEntity) obj).toString();
            case 25:
                return AppResources.getString(((Integer) obj).intValue());
            case 26:
                return AddTagService.lambda$addTagIdMap$0((String) obj);
            case 27:
                return DownloadService.lambda$onDownloadFinish$0((Map.Entry) obj);
            case 28:
                return ((FileOpJob) obj).toGroupSummary();
            default:
                return Logger.getEncodedString((String) obj);
        }
    }
}
