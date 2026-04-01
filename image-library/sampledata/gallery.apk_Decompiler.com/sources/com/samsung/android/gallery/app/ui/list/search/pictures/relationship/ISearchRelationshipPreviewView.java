package com.samsung.android.gallery.app.ui.list.search.pictures.relationship;

import com.samsung.android.gallery.app.ui.list.search.pdc.PdcSearchDelegate;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ISearchRelationshipPreviewView extends ISearchClusterResultView {
    PdcSearchDelegate getPdcSearchDelegate();

    void pendingFilterData(ArrayList<String> arrayList);

    void pendingReloadCreatureList();
}
