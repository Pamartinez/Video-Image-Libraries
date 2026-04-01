package D3;

import com.samsung.android.gallery.app.activity.external.LocationPickerActivity;
import com.samsung.android.gallery.module.map.manager.AddressCompat;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ LocationPickerActivity e;

    public /* synthetic */ e(LocationPickerActivity locationPickerActivity, int i2) {
        this.d = i2;
        this.e = locationPickerActivity;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        LocationPickerActivity locationPickerActivity = this.e;
        AddressCompat addressCompat = (AddressCompat) obj;
        switch (i2) {
            case 0:
                locationPickerActivity.lambda$startAddressTask$3(addressCompat);
                return;
            default:
                locationPickerActivity.lambda$startAddressTask$2(addressCompat);
                return;
        }
    }
}
