package A4;

import com.samsung.android.gallery.app.controller.album.CreateAlbumAndMoveCmd;
import com.samsung.android.gallery.app.controller.album.CreateAutoUpdatingAlbumCmd;
import com.samsung.android.gallery.app.controller.album.CreateFolderCmd;
import com.samsung.android.gallery.app.controller.creature.AddContentsToMergedCreatureCmd;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureMultipleCmd;
import com.samsung.android.gallery.app.controller.creature.UnmergeCreatureCmd;
import com.samsung.android.gallery.app.controller.externals.CreateNewDialogCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd2;
import com.samsung.android.gallery.app.controller.internals.DeleteAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.ChooseSharedAlbumCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.albums.AlbumsFragment;
import com.samsung.android.gallery.app.ui.list.albums.pictures.AlbumPicturesViewAdapter;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.SharingPicturesFragment;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Fragment;
import com.samsung.android.gallery.app.ui.list.stories.slideshow.SlideshowV2Presenter;
import com.samsung.android.gallery.module.clip.textextraction.TextExtractionHelper;
import com.samsung.android.gallery.module.clipboard.ClipDataCompat;
import com.samsung.android.gallery.module.receiver.GlobalActionReceiver;
import com.samsung.android.gallery.settings.ui.LabsAlbumBnRFragment;
import com.samsung.android.gallery.settings.ui.LabsDevManageFragment;
import com.samsung.android.gallery.settings.ui.LabsFragment;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItemBuilder;
import java.util.function.IntFunction;

/* renamed from: A4.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0387w implements IntFunction {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f2261a;

    public /* synthetic */ C0387w(int i2) {
        this.f2261a = i2;
    }

    public final Object apply(int i2) {
        switch (this.f2261a) {
            case 0:
                return BaseListFragment.lambda$getAllItems$9(i2);
            case 1:
                return BaseListViewAdapter.lambda$getSelectedItems$3(i2);
            case 2:
                return BaseListViewAdapter.lambda$prepareExtendedShare$4(i2);
            case 3:
                return AlbumsFragment.lambda$getAllItems$3(i2);
            case 4:
                return LabsAlbumBnRFragment.lambda$onRestoreAlbumDbClicked$2(i2);
            case 5:
                return LabsDevManageFragment.lambda$addCategoryStatus$45(i2);
            case 6:
                return LabsFragment.lambda$onHeapDumpClicked$25(i2);
            case 7:
                return CreateAlbumAndMoveCmd.lambda$getFilteredMediaItem$0(i2);
            case 8:
                return CreateAutoUpdatingAlbumCmd.lambda$getAutoAlbumTitleArray$1(i2);
            case 9:
                return CreateFolderCmd.lambda$getAllItems$7(i2);
            case 10:
                return AddContentsToMergedCreatureCmd.lambda$onExecute$0(i2);
            case 11:
                return MergeCreatureMultipleCmd.lambda$getMeredItem$8(i2);
            case 12:
                return MergeCreatureMultipleCmd.lambda$getMultiplePrimaryKey$13(i2);
            case 13:
                return MergeCreatureMultipleCmd.lambda$getMergedInfo$10(i2);
            case 14:
                return MergeCreatureMultipleCmd.lambda$addTargetItem$3(i2);
            case 15:
                return UnmergeCreatureCmd.lambda$undoMerge$2(i2);
            case 16:
                return UnmergeCreatureCmd.lambda$undoMerge$5(i2);
            case 17:
                return SlideshowV2Fragment.lambda$getExportItems$0(i2);
            case 18:
                return SlideshowV2Presenter.lambda$getAllItems$0(i2);
            case 19:
                return PinchItemBuilder.lambda$createDividerItems$2(i2);
            case 20:
                return PinchItemBuilder.lambda$createDataItems$0(i2);
            case 21:
                return AlbumPicturesViewAdapter.lambda$restoreClipboard$0(i2);
            case 22:
                return CreateNewDialogCmd.lambda$getItems$0(i2);
            case 23:
                return ChangeBestItemCmd2.lambda$onExecute$0(i2);
            case 24:
                return DeleteAlbumCmd.lambda$getValidItems$0(i2);
            case 25:
                return SharingPicturesFragment.lambda$getAllItems$8(i2);
            case 26:
                return TextExtractionHelper.lambda$getHighlightPoints$4(i2);
            case 27:
                return ClipDataCompat.lambda$of$2(i2);
            case 28:
                return GlobalActionReceiver.lambda$processAdjustPopOverOptions$18(i2);
            default:
                return ChooseSharedAlbumCmd.lambda$checkMediaItem$3(i2);
        }
    }
}
