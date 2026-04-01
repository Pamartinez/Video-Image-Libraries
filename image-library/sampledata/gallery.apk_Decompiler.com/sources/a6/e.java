package a6;

import androidx.core.util.Function;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Function {
    public final Object apply(Object obj) {
        return DbCompat.query(new QueryParams("mp://People").setOrder("random()").setLimit(((Integer) obj).intValue()));
    }
}
