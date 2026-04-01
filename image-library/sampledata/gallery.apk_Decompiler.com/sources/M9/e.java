package M9;

import android.database.Cursor;
import android.os.Bundle;
import androidx.core.util.Consumer;
import com.samsung.android.gallery.app.ui.list.stories.category.ondemand.OnDemandLoader;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.module.publisher.CursorPublisher;
import com.samsung.android.gallery.module.publisher.StoriesDataPublisher;
import com.samsung.android.gallery.module.search.engine.PersonalDataCore;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Runnable {
    public final /* synthetic */ int d = 3;
    public final /* synthetic */ Object e;
    public final /* synthetic */ long f;
    public final /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f2840h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ Object f2841i;

    public /* synthetic */ e(OnDemandLoader onDemandLoader, ArrayList arrayList, String str, long j2, Consumer consumer) {
        this.f2840h = onDemandLoader;
        this.e = arrayList;
        this.g = str;
        this.f = j2;
        this.f2841i = consumer;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                ((CursorPublisher) this.f2840h).lambda$publishRecentFileData$10((Cursor[]) this.e, this.f, (Bundle) this.f2841i, (String) this.g);
                return;
            case 1:
                ((StoriesDataPublisher) this.f2840h).lambda$publishStoryAlbumFileData$3((QueryParams) this.f2841i, (Cursor[]) this.e, this.f, (String) this.g);
                return;
            case 2:
                ((PersonalDataCore) this.f2840h).lambda$loadCandidatePeopleBy$3((List) this.e, (LinkedHashMap) this.f2841i, this.f, (java.util.function.Consumer) this.g);
                return;
            default:
                ((OnDemandLoader) this.f2840h).lambda$loadImageInfo$1((ArrayList) this.e, (String) this.g, this.f, (Consumer) this.f2841i);
                return;
        }
    }

    public /* synthetic */ e(CursorPublisher cursorPublisher, Cursor[] cursorArr, long j2, Bundle bundle, String str) {
        this.f2840h = cursorPublisher;
        this.e = cursorArr;
        this.f = j2;
        this.f2841i = bundle;
        this.g = str;
    }

    public /* synthetic */ e(StoriesDataPublisher storiesDataPublisher, QueryParams queryParams, Cursor[] cursorArr, long j2, String str) {
        this.f2840h = storiesDataPublisher;
        this.f2841i = queryParams;
        this.e = cursorArr;
        this.f = j2;
        this.g = str;
    }

    public /* synthetic */ e(PersonalDataCore personalDataCore, List list, LinkedHashMap linkedHashMap, long j2, java.util.function.Consumer consumer) {
        this.f2840h = personalDataCore;
        this.e = list;
        this.f2841i = linkedHashMap;
        this.f = j2;
        this.g = consumer;
    }
}
