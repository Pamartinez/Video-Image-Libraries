package db;

import android.content.Context;
import android.view.LayoutInflater;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.pictures.related.RelatedDataHolder;
import com.samsung.android.gallery.module.fileio.database.helper.DatabaseOperation;
import com.samsung.android.gallery.widget.MultiColumnTextView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Consumer {
    public final /* synthetic */ int d = 2;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Serializable g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f3260h;

    /* JADX WARNING: type inference failed for: r3v0, types: [com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[], java.io.Serializable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ a(com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader r2, com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[] r3, int r4, java.util.concurrent.CountDownLatch r5) {
        /*
            r1 = this;
            r0 = 2
            r1.d = r0
            r1.<init>()
            r1.f = r2
            r1.g = r3
            r1.e = r4
            r1.f3260h = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: db.a.<init>(com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.PageDataLoader, com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem[], int, java.util.concurrent.CountDownLatch):void");
    }

    public final void accept(Object obj) {
        switch (this.d) {
            case 0:
                ((MultiColumnTextView) this.f).lambda$setData$0((AtomicBoolean) this.g, (LayoutInflater) this.f3260h, this.e, (String) obj);
                return;
            case 1:
                ((DatabaseOperation) this.f).lambda$applyRequest$1(this.e, (ArrayList) this.g, (String) this.f3260h, (Context) obj);
                return;
            default:
                ((PageDataLoader) this.f).lambda$loadRelatedItem$7((PageItem[]) this.g, this.e, (CountDownLatch) this.f3260h, (RelatedDataHolder) obj);
                return;
        }
    }

    public /* synthetic */ a(DatabaseOperation databaseOperation, int i2, ArrayList arrayList, String str) {
        this.f = databaseOperation;
        this.e = i2;
        this.g = arrayList;
        this.f3260h = str;
    }

    public /* synthetic */ a(MultiColumnTextView multiColumnTextView, AtomicBoolean atomicBoolean, LayoutInflater layoutInflater, int i2) {
        this.f = multiColumnTextView;
        this.g = atomicBoolean;
        this.f3260h = layoutInflater;
        this.e = i2;
    }
}
