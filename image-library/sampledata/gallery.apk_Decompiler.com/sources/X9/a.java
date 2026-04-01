package X9;

import android.net.Uri;
import com.samsung.android.gallery.module.lottie.recap.template.element.RecapImage;
import java.util.function.Consumer;
import org.json.JSONArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ JSONArray e;

    public /* synthetic */ a(JSONArray jSONArray, int i2) {
        this.d = i2;
        this.e = jSONArray;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                this.e.remove(((Integer) obj).intValue());
                return;
            case 1:
                this.e.put((String) obj);
                return;
            case 2:
                this.e.put(((RecapImage) obj).mUsedItem.getFileId());
                return;
            case 3:
                this.e.put((Uri) obj);
                return;
            default:
                this.e.put((String) obj);
                return;
        }
    }
}
