package androidx.media3.extractor.text;

import W.a;
import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.BundleCollectionUtil;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CueEncoder {
    public byte[] encode(List<Cue> list, long j2) {
        ArrayList<Bundle> bundleArrayList = BundleCollectionUtil.toBundleArrayList(list, new a(2));
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("c", bundleArrayList);
        bundle.putLong("d", j2);
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
