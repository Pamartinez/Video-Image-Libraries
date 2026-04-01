package za;

import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import com.samsung.android.gallery.plugins.R$drawable;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Consumer {
    public final /* synthetic */ int d;

    public /* synthetic */ b(int i2) {
        this.d = i2;
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((FragmentActivity) obj).onBackPressed();
                return;
            default:
                ((ImageView) obj).setImageDrawable(((ImageView) obj).getContext().getDrawable(R$drawable.ic_file_type_zip));
                return;
        }
    }
}
