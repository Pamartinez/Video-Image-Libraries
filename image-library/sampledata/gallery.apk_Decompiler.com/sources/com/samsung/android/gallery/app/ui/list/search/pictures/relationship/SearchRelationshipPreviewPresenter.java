package com.samsung.android.gallery.app.ui.list.search.pictures.relationship;

import A4.C0366a;
import A4.C0375j;
import A4.C0384t;
import A5.a;
import Ad.C0720a;
import B5.e;
import android.content.Context;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.abstraction.ListMenuUpdater;
import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResult2Presenter;
import com.samsung.android.gallery.app.ui.list.search.pictures.relationship.ISearchRelationshipPreviewView;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.filter.SearchFilterUtil;
import com.samsung.android.gallery.module.search.root.FilterResultsEntry;
import com.samsung.android.gallery.module.search.root.PeopleFilterResultsEntity;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchRelationshipPreviewPresenter<V extends ISearchRelationshipPreviewView> extends SearchClusterResult2Presenter<V> {
    private boolean mShowCreatureList;

    public SearchRelationshipPreviewPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private void filterRelationshipCandidates(FilterResultsEntry filterResultsEntry) {
        ArrayList arrayList = (ArrayList) filterResultsEntry.getAllItems().stream().filter(new C0375j(3)).map(new C0384t(18)).collect(Collectors.toCollection(new C0720a(1)));
        PdcSearchDelegate pdcSearchDelegate = ((ISearchRelationshipPreviewView) this.mView).getPdcSearchDelegate();
        if (pdcSearchDelegate != null) {
            PdcSearchDelegate.FilteredResult filterData = pdcSearchDelegate.filterData(arrayList);
            if (filterData == PdcSearchDelegate.FilteredResult.FILTERED) {
                ((ISearchRelationshipPreviewView) this.mView).setProgressBarVisibility(false);
            } else if (filterData == PdcSearchDelegate.FilteredResult.NO_ITEM) {
                pdcSearchDelegate.showFullListWithoutRecommended();
            }
        } else {
            ((ISearchRelationshipPreviewView) this.mView).pendingFilterData(arrayList);
        }
    }

    private String getIdentityIds(ArrayList<MediaItem> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new e(stringJoiner, 0));
        return stringJoiner.toString();
    }

    private String getPeopleSelectedFilter(ArrayList<MediaItem> arrayList) {
        return SearchFilterUtil.addSelectedFilter("", new PeopleFilterResultsEntity(TextUtils.join("\u001f", (List) arrayList.stream().map(new C0384t(19)).collect(Collectors.toList())), "recommended_id"));
    }

    private String getSelectedFilter() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "SelectedFilter");
    }

    private String getUnifiedIds(ArrayList<MediaItem> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        arrayList.forEach(new e(stringJoiner, 1));
        return stringJoiner.toString();
    }

    private void handleFilterResult(EventMessage eventMessage) {
        if (!hasPeopleFilter()) {
            filterRelationshipCandidates((FilterResultsEntry) ((Object[]) eventMessage.obj)[0]);
        }
    }

    private void handlePickerCompleted(EventMessage eventMessage) {
        boolean booleanValue = ((Boolean) eventMessage.obj).booleanValue();
        PdcSearchDelegate pdcSearchDelegate = ((ISearchRelationshipPreviewView) this.mView).getPdcSearchDelegate();
        if (pdcSearchDelegate != null) {
            pdcSearchDelegate.skipRelationship(booleanValue);
            ((ISearchRelationshipPreviewView) this.mView).setProgressBarVisibility(pdcSearchDelegate.needToShowFullList());
            return;
        }
        ((ISearchRelationshipPreviewView) this.mView).setProgressBarVisibility(false);
    }

    private boolean hasPeopleFilter() {
        String selectedFilter = getSelectedFilter();
        if (selectedFilter == null || !selectedFilter.contains("recommended_id")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String lambda$getTagName$0(PdcSearchDelegate pdcSearchDelegate) {
        return getContext().getString(R.string.relationship_suggestion_header, new Object[]{pdcSearchDelegate.getRelationshipName()});
    }

    public MenuDataBinding createMenuDataBinding() {
        return new MenuDataBinding(R.menu.menu_search_category_suggested_creature_select);
    }

    public int getDividerButtonHeight() {
        Context context = getContext();
        if (context != null) {
            return context.getResources().getDimensionPixelOffset(R.dimen.divider_button_layout_height);
        }
        return 0;
    }

    public Object getSuggesterData() {
        return null;
    }

    public String getTagName() {
        return (String) Optional.ofNullable(((ISearchRelationshipPreviewView) this.mView).getPdcSearchDelegate()).map(new a(2, this)).orElse("");
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 8513) {
            handleFilterResult(eventMessage);
            return true;
        }
        if (i2 == 8015) {
            handlePickerCompleted(eventMessage);
        }
        return super.handleEvent(eventMessage);
    }

    public void handleNoData() {
        if (!ArgumentsUtil.getArgValue(getLocationKey(), "show_creature_list", false)) {
            Optional.ofNullable(((ISearchRelationshipPreviewView) this.mView).getPdcSearchDelegate()).ifPresent(new C0366a(23));
        } else if (!hasPeopleFilter()) {
            this.mShowCreatureList = true;
            PdcSearchDelegate pdcSearchDelegate = ((ISearchRelationshipPreviewView) this.mView).getPdcSearchDelegate();
            if (pdcSearchDelegate != null) {
                pdcSearchDelegate.fillFilterData();
                if (pdcSearchDelegate.isListLoaded()) {
                    reloadListWithRelationship(pdcSearchDelegate.getCurrentItems());
                } else {
                    ((ISearchRelationshipPreviewView) this.mView).pendingReloadCreatureList();
                }
            }
        }
    }

    public void reloadListWithRelationship(ArrayList<MediaItem> arrayList) {
        String locationKey = getLocationKey();
        if (locationKey != null) {
            if (this.mShowCreatureList && arrayList.isEmpty()) {
                PdcSearchDelegate pdcSearchDelegate = ((ISearchRelationshipPreviewView) this.mView).getPdcSearchDelegate();
                if (pdcSearchDelegate == null) {
                    ((ISearchRelationshipPreviewView) this.mView).pendingReloadCreatureList();
                    return;
                }
                arrayList = pdcSearchDelegate.getCurrentItems();
            }
            UriBuilder uriBuilder = new UriBuilder(locationKey);
            uriBuilder.removeArg("SelectedFilter").removeArg("relationship_name").removeArg("selected_creature");
            if (!arrayList.isEmpty()) {
                PdcSearchDelegate pdcSearchDelegate2 = ((ISearchRelationshipPreviewView) this.mView).getPdcSearchDelegate();
                uriBuilder.appendArg("SelectedFilter", getPeopleSelectedFilter(arrayList));
                if (pdcSearchDelegate2 != null) {
                    uriBuilder.appendArg("relationship_name", pdcSearchDelegate2.currentRelationshipType());
                }
                if (this.mShowCreatureList) {
                    uriBuilder.appendArg("selected_creature_unified_ids", getUnifiedIds(arrayList));
                } else {
                    uriBuilder.appendArg("selected_creature", getIdentityIds(arrayList));
                }
            }
            uriBuilder.appendArg("need_keyword_search", !this.mShowCreatureList).appendArg("search_skip_save_history", false);
            this.mBlackboard.postEvent(EventMessage.obtain(1066, uriBuilder.build()));
        }
    }

    public ListMenuUpdater createMenuUpdateDelegation(V v) {
        return new ListMenuUpdater(v, this) {
            /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, java.util.function.Function] */
            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$updateOptionsMenuAction$1(MenuItem menuItem) {
                menuItem.setEnabled(((Boolean) Optional.ofNullable(((ISearchRelationshipPreviewView) SearchRelationshipPreviewPresenter.this.mView).getPdcSearchDelegate()).map(new Object()).orElse(Boolean.FALSE)).booleanValue());
            }

            public void updateOptionsMenuAction(Menu menu, MenuDataBinding.SelectionMode selectionMode) {
                super.updateOptionsMenuAction(menu, selectionMode);
                boolean isLandscape = ((ISearchRelationshipPreviewView) SearchRelationshipPreviewPresenter.this.mView).isLandscape();
                setMenuVisibility(menu, (int) R.id.action_later, isLandscape);
                setMenuVisibility(menu, (int) R.id.action_set_as_relation, isLandscape);
                if (isLandscape) {
                    Optional.ofNullable(menu.findItem(R.id.action_set_as_relation)).ifPresent(new a(this));
                }
            }
        };
    }

    public void hideProgressbarOnDataPrepared() {
    }
}
