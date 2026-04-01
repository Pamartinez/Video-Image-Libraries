package Ba;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.SeslIndicator;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.ButtonModel;
import com.google.android.material.appbar.model.view.SuggestAppBarView;
import com.google.android.material.oneui.dividerbuttonlayout.DividerButtonLayout;
import com.samsung.android.app.sdk.deepsky.barcode.action.abstraction.Action;
import com.samsung.android.app.sdk.deepsky.objectcapture.ui.ObjectCapturePopup;
import com.samsung.android.app.sdk.deepsky.textextraction.selectionui.barcode.BarcodeDialogFragment;
import com.samsung.android.gallery.app.activity.GalleryPrivateActivity;
import com.samsung.android.gallery.app.controller.externals.DocumentScanCmd;
import com.samsung.android.gallery.app.controller.externals.GotoMyFilesCmd;
import com.samsung.android.gallery.app.controller.internals.ShowSnackBarForViewerCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteUndoSingleCmd;
import com.samsung.android.gallery.app.service.HighlightEncodeService;
import com.samsung.android.gallery.app.ui.container.clipboard.ClipboardViewAdapter;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureChoiceDialog;
import com.samsung.android.gallery.app.ui.dialog.creature.merge.MergeCreatureMultipleDialog;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.AutoCompleteListHolder;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompleteAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.LocationItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.editcreature.RegisteredCreatureAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.SearchMyTagAdapter;
import com.samsung.android.gallery.app.ui.list.search.recommendation.floating.suggestion.FloatingSuggestionListAdapter;
import com.samsung.android.gallery.app.ui.list.suggestions.SuggestionsViewHolder;
import com.samsung.android.gallery.app.ui.list.trash.TrashFragment;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.FocusItemViewHolder;
import com.samsung.android.gallery.app.ui.viewer2.remaster.focusview.FocusViewAdapter;
import com.samsung.android.gallery.module.commandline.ops.DbDump;
import com.samsung.android.gallery.module.creature.base.CreatureNameData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.search.autocomplete.AutoCompleteItem;
import com.samsung.android.gallery.plugins.motionphoto.MotionPhotoActivity;
import com.samsung.android.gallery.settings.ui.AboutFragment;
import com.samsung.android.gallery.widget.awesome.AwesomeIntelligenceAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.search.filter.FilterSubListViewAdapter;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersAdapter;
import com.samsung.android.gallery.widget.search.filter.SearchFiltersViewHolder;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import com.samsung.android.gallery.widget.search.pictures.SearchHeaderView;
import com.samsung.android.gallery.widget.search.searchbar.autocomplete.FilterAutoCompleteAdapter;
import java.util.concurrent.atomic.AtomicInteger;
import p2.C0262c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ g(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        Object obj = this.f;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((MotionPhotoActivity) obj2).lambda$bindToolbar$1((Toolbar) obj, view);
                return;
            case 1:
                ((GalleryPrivateActivity) obj2).lambda$onCreate$2((AtomicInteger) obj, view);
                return;
            case 2:
                ((SearchMyTagAdapter) obj2).lambda$onBindViewHolder$0((MediaItem) obj, view);
                return;
            case 3:
                ((AboutFragment) obj2).lambda$setLabsEnabling$4((View) obj, view);
                return;
            case 4:
                ((FloatingSuggestionListAdapter) obj2).lambda$onCreateViewHolder$0((FloatingSuggestionListAdapter.ViewHolder) obj, view);
                return;
            case 5:
                new GotoMyFilesCmd().execute(new DocumentScanCmd.DocumentScanSaveTask.EventContextImpl((Activity) obj2), (String) obj);
                return;
            case 6:
                ((SuggestionsViewHolder) obj2).lambda$bindListView$1((MediaItem) obj, view);
                return;
            case 7:
                ((ShowSnackBarForViewerCmd) obj2).lambda$makeSnackBar$1((MediaItem) obj, view);
                return;
            case 8:
                SuggestAppBarView.generateButton$lambda$11$lambda$10((ButtonModel) obj2, (SuggestAppBarView) obj, view);
                return;
            case 9:
                SuggestAppBarView.setCloseClickListener$lambda$6$lambda$5((AppBarModel.OnClickListener) obj2, (SuggestAppBarView) obj, view);
                return;
            case 10:
                ((TrashFragment) obj2).lambda$initTrashOnButtonIfNeeded$1((View) obj, view);
                return;
            case 11:
                ((FocusViewAdapter) obj2).lambda$onCreateViewHolder$0((FocusItemViewHolder) obj, view);
                return;
            case 12:
                ((FilterSubListViewAdapter) obj2).lambda$addClickListener$0((SearchFiltersViewHolder) obj, view);
                return;
            case 13:
                ((SearchFiltersAdapter) obj2).lambda$addClickListener$0((SearchFiltersViewHolder) obj, view);
                return;
            case 14:
                ((SearchHeaderView) obj2).lambda$setHeaderItemClickListener$0((OnHeaderClickListener) obj, view);
                return;
            case 15:
                ((DeleteUndoSingleCmd) obj2).lambda$showSnackBar$2((MediaData) obj, view);
                return;
            case 16:
                ((FilterAutoCompleteAdapter) obj2).lambda$onBindViewHolder$0((AutoCompleteItem) obj, view);
                return;
            case 17:
                ((HighlightEncodeService) obj2).lambda$showSnackBar$14((MediaItem) obj, view);
                return;
            case 18:
                ObjectCapturePopup.createOverflowButton$lambda$3$lambda$2((ObjectCapturePopup) obj2, (ImageButton) obj, view);
                return;
            case 19:
                DbDump.lambda$showSnackbar$2((Context) obj2, (String) obj, view);
                return;
            case 20:
                SeslIndicator.setOnItemClickListener$lambda$9$lambda$8((SeslIndicator.OnItemClickListener) obj2, (SeslIndicator) obj, view);
                return;
            case 21:
                ((ClipboardViewAdapter) obj2).lambda$setItemLayoutMargin$0((MediaItem) obj, view);
                return;
            case 22:
                ((SearchAutoCompleteAdapterV2) obj2).lambda$createViewHolderInternal$0((AutoCompleteListHolder) obj, view);
                return;
            case 23:
                ((LocationItemAdapterV2) obj2).lambda$setListeners$0((ListViewHolder) obj, view);
                return;
            case 24:
                ((AwesomeIntelligenceAdapter) obj2).lambda$onCreateViewHolder$0((AwesomeIntelligenceAdapter.AwesomeItemViewHolder) obj, view);
                return;
            case 25:
                MenuItemImpl menuItemImpl = (MenuItemImpl) obj;
                int i7 = DividerButtonLayout.l;
                C0262c cVar = ((DividerButtonLayout) obj2).k;
                if (cVar != null) {
                    cVar.onMenuItemClick(menuItemImpl);
                    return;
                }
                return;
            case 26:
                ((RegisteredCreatureAdapter) obj2).lambda$onBindViewHolder$0((CreatureNameData) obj, view);
                return;
            case 27:
                BarcodeDialogFragment.setContent$lambda$13$lambda$12((BarcodeDialogFragment) obj2, (Action) obj, view);
                return;
            case 28:
                ((MergeCreatureChoiceDialog) obj2).lambda$initRadioButton$0((MediaItem) obj, view);
                return;
            default:
                ((MergeCreatureMultipleDialog) obj2).lambda$initRadioButton$2((MediaItem) obj, view);
                return;
        }
    }
}
