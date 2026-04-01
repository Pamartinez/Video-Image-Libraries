package n5;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.people.CreatureCategoryItemAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* renamed from: n5.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0493b implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ CreatureCategoryItemAdapter e;
    public final /* synthetic */ ListViewHolder f;

    public /* synthetic */ C0493b(CreatureCategoryItemAdapter creatureCategoryItemAdapter, ListViewHolder listViewHolder, int i2) {
        this.d = i2;
        this.e = creatureCategoryItemAdapter;
        this.f = listViewHolder;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                this.e.lambda$onBindViewHolder$0(this.f, view);
                return;
            default:
                this.e.lambda$onBindViewHolder$1(this.f, view);
                return;
        }
    }
}
