package D3;

import com.samsung.android.gallery.app.activity.external.LocationPickerActivity;
import com.samsung.android.gallery.module.map.abstraction.MapPickerContainer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements MapPickerContainer.MapClickListener, MapPickerContainer.MarkerDragEndListener {
    public final /* synthetic */ LocationPickerActivity d;

    public /* synthetic */ c(LocationPickerActivity locationPickerActivity) {
        this.d = locationPickerActivity;
    }

    public void onMapClicked(double d2, double d3) {
        this.d.lambda$initMapContainer$0(d2, d3);
    }
}
