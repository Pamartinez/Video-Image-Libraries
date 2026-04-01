package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ClusterResultType;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterResultFactory {
    private static final HashMap<ClusterResultType, SearchResultClusterConstructor> sFactoryMap = new HashMap<ClusterResultType, SearchResultClusterConstructor>() {
        {
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH) {
                put(ClusterResultType.TOP_RESULT, new a(0));
            }
            if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                put(ClusterResultType.CAROUSELS, new a(1));
                return;
            }
            put(ClusterResultType.ALBUMS, new a(2));
            put(ClusterResultType.LOCATIONS, new a(3));
            put(ClusterResultType.PEOPLE, new a(4));
            put(ClusterResultType.OCRS, new a(5));
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SearchResultClusterConstructor {
        ClusterResult create(View view, EventContext eventContext);
    }

    public ClusterResult create(ClusterResultType clusterResultType, View view, EventContext eventContext) {
        SearchResultClusterConstructor searchResultClusterConstructor = sFactoryMap.get(clusterResultType);
        if (searchResultClusterConstructor != null) {
            return searchResultClusterConstructor.create(view, eventContext);
        }
        return null;
    }
}
