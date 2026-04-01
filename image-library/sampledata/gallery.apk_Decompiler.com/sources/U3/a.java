package U3;

import K1.c;
import android.graphics.Bitmap;
import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleExtractor;
import androidx.media3.extractor.ts.SeiReader;
import com.google.android.gms.maps.model.LatLng;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.story.DeleteStoryCmd;
import com.samsung.android.gallery.app.controller.story.DownloadAllBgmCmd;
import com.samsung.android.gallery.app.controller.story.HighlightExportOptionsDialogCmd;
import com.samsung.android.gallery.app.controller.story.RemoveFromStoryDialogCmd;
import com.samsung.android.gallery.app.controller.story.RenameStoryCmd;
import com.samsung.android.gallery.app.controller.story.SaveHideRuleCmd;
import com.samsung.android.gallery.app.controller.trash.EmptySingleTrashCmd;
import com.samsung.android.gallery.app.controller.trash.EmptyTrashCmd;
import com.samsung.android.gallery.app.controller.trash.LaunchTrashBinCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteSingleCmd;
import com.samsung.android.gallery.app.controller.viewer.DirectorsViewChooseCmd;
import com.samsung.android.gallery.app.controller.viewer.EditDateSingleCmd;
import com.samsung.android.gallery.app.controller.viewer.EditDetailsSaveOrDiscardCmd;
import com.samsung.android.gallery.app.controller.viewer.EraseObjectCmd;
import com.samsung.android.gallery.app.controller.viewer.SaveGroupShotCmd;
import com.samsung.android.gallery.app.remote.v2.GalleryBasePresentation;
import com.samsung.android.gallery.app.ui.list.picker.pictures.PicturesPickerPresenter;
import com.samsung.android.gallery.app.ui.list.stories.category.abstraction.IStoriesCategoryView;
import com.samsung.android.gallery.app.ui.map.base.GalleryMapFragment;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.map.abstraction.MapContainer;
import com.samsung.android.gallery.module.map.clustering.IClusterZoomListener;
import com.samsung.android.gallery.module.map.transition.abstraction.MapItem;
import com.samsung.android.gallery.module.remote.dlna.DlnaHelper;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.searchbar.SearchToolbar;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements DataCollectionDelegate.OnDataCompletionListener, ListViewHolder.OnItemClickListener, PopupMenu.OnMenuItemClickListener, IClusterZoomListener, MapItem.MediaItemProvider, MapContainer.OnGalleryMapReadyListener, c, Consumer, DlnaHelper.DlnaServiceListener, GalleryBasePresentation.DisplayRemovedListener, ReorderingBufferQueue.OutputConsumer, EventContext.OnSelectionListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public void a(LatLng latLng) {
        ((MapContainer.OnClickListener) this.e).onClick();
    }

    public void accept(Object obj) {
        ((SubtitleExtractor) this.e).lambda$parseAndWriteToOutput$0((CuesWithTiming) obj);
    }

    public void b(Bitmap bitmap) {
        ((MapContainer.OnSnapshotReadyListener) this.e).onSnapshotReady(bitmap);
    }

    public void consume(long j2, ParsableByteArray parsableByteArray) {
        ((SeiReader) this.e).lambda$new$0(j2, parsableByteArray);
    }

    public void onDataCompleted(EventContext eventContext, ArrayList arrayList) {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                ((DeleteStoryCmd) obj).deleteStories(eventContext, arrayList);
                return;
            case 1:
                ((DownloadAllBgmCmd) obj).onOptionSelected(eventContext, arrayList);
                return;
            case 2:
                ((HighlightExportOptionsDialogCmd) obj).updateCustomRatio(eventContext, arrayList);
                return;
            case 3:
                ((RemoveFromStoryDialogCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 4:
                ((RenameStoryCmd) obj).renameStory(eventContext, arrayList);
                return;
            case 5:
                ((SaveHideRuleCmd) obj).executeHide(eventContext, arrayList);
                return;
            case 7:
                ((EmptySingleTrashCmd) obj).onDataCompleted(eventContext, arrayList);
                return;
            case 8:
                ((EmptyTrashCmd) obj).onDataCompleted(eventContext, arrayList);
                return;
            case 9:
                ((LaunchTrashBinCmd) obj).onTurnOnTrash(eventContext, arrayList);
                return;
            case 11:
                ((DeleteSingleCmd) obj).onConfirmed(eventContext, arrayList);
                return;
            case 12:
                ((DirectorsViewChooseCmd) obj).onSelected(eventContext, arrayList);
                return;
            case 13:
                ((EditDateSingleCmd) obj).lambda$onExecute$0(eventContext, arrayList);
                return;
            case 14:
                ((EditDetailsSaveOrDiscardCmd) obj).onDataComplete(eventContext, arrayList);
                return;
            case 15:
                ((EraseObjectCmd) obj).onItemSelected(eventContext, arrayList);
                return;
            default:
                ((SaveGroupShotCmd) obj).onConfirmed(eventContext, arrayList);
                return;
        }
    }

    public void onItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        ((IStoriesCategoryView) this.e).onListItemClick(listViewHolder, i2, mediaItem, i7);
    }

    public void onMapReady(Object obj) {
        ((GalleryMapFragment) this.e).onMapReady(obj);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return ((SearchToolbar) this.e).onMenuItemClickInternal(menuItem);
    }

    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        return ((PicturesPickerPresenter) this.e).onSelectionCompleted(eventContext, mediaItemArr);
    }
}
