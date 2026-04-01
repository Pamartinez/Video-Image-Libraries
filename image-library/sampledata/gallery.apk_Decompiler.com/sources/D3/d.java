package D3;

import android.view.View;
import com.samsung.android.gallery.app.activity.external.LocationPickerActivity;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LocationPickerActivity e;

    public /* synthetic */ d(LocationPickerActivity locationPickerActivity, int i2) {
        this.d = i2;
        this.e = locationPickerActivity;
    }

    public final void onClick(View view) {
        int i2 = this.d;
        LocationPickerActivity locationPickerActivity = this.e;
        switch (i2) {
            case 0:
                locationPickerActivity.onCurrentLocationButtonClicked(view);
                return;
            default:
                locationPickerActivity.onNavigationUp(view);
                return;
        }
    }
}
