package com.samsung.android.gallery.app.ui.list.search.category.location;

import com.samsung.android.gallery.app.ui.list.search.category.location.SearchLocationItemAdapter;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Comparator {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final int compare(Object obj, Object obj2) {
        SearchLocationItemAdapter.CityPositionInfo cityPositionInfo = (SearchLocationItemAdapter.CityPositionInfo) obj;
        SearchLocationItemAdapter.CityPositionInfo cityPositionInfo2 = (SearchLocationItemAdapter.CityPositionInfo) obj2;
        switch (this.d) {
            case 0:
                return cityPositionInfo.city().compareToIgnoreCase(cityPositionInfo2.city());
            default:
                return SearchLocationItemAdapter.lambda$sortPositionMap$2(cityPositionInfo, cityPositionInfo2);
        }
    }
}
