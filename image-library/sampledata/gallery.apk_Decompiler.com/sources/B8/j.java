package B8;

import android.content.ClipData;
import android.content.pm.ShortcutInfo;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.controller.story.ChangeStoryCoverCmd;
import com.samsung.android.gallery.app.controller.story.SaveManageContentsCmd;
import com.samsung.android.gallery.app.controller.story.ShareStoryToAlbumCmd;
import com.samsung.android.gallery.app.ui.list.search.editcreature.EditCreatureNamePresenter;
import com.samsung.android.gallery.app.ui.list.stories.header.StoriesPinModel;
import com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AbsRemasterAiEdit;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPagerDelegate;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class j implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2774a;
    public final /* synthetic */ MediaItem b;

    public /* synthetic */ j(MediaItem mediaItem, int i2) {
        this.f2774a = i2;
        this.b = mediaItem;
    }

    public final boolean test(Object obj) {
        int i2 = this.f2774a;
        MediaItem mediaItem = this.b;
        switch (i2) {
            case 0:
                return ShortcutHelper.needToUpdateShortcut(mediaItem, (ShortcutInfo) obj);
            case 1:
                return MergeCreatureMultipleCmd.lambda$addTargetItem$2(mediaItem, (MediaItem) obj);
            case 2:
                return MergeCreatureMultipleCmd.lambda$getMeredItem$7(mediaItem, (MediaItem) obj);
            case 3:
                return ChangeStoryCoverCmd.lambda$updateContentsOrder$0(mediaItem, (MediaItem) obj);
            case 4:
                return SaveManageContentsCmd.lambda$isHeaderHidden$1(mediaItem, (MediaItem) obj);
            case 5:
                return ShareStoryToAlbumCmd.lambda$hasCustomCover$0(mediaItem, (MediaItem) obj);
            case 6:
                return StoriesPinModel.lambda$notifyDataRangeChanged$0(mediaItem, (MediaItem) obj);
            case 7:
                return StoriesPinModel.lambda$updateFavoriteToMediaData$6(mediaItem, (MediaItem) obj);
            case 8:
                return ((AbsRemasterAiEdit) obj).support(mediaItem);
            case 9:
                return ((AbsRemasterAiEdit) obj).support(mediaItem);
            case 10:
                return EditCreatureNamePresenter.lambda$getMergedInfo$23(mediaItem, (MediaItem) obj);
            case 11:
                return ViewPagerDelegate.lambda$findHoldingPosition$1(mediaItem, (MediaItem) obj);
            case 12:
                return mediaItem.getSubCategory().contains((String) obj);
            default:
                return ((ClipData.Item) obj).getIntent().getExtras().getString("AbsolutePath").equals(mediaItem.getPath());
        }
    }
}
