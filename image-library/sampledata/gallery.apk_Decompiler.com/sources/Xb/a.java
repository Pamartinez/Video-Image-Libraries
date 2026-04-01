package Xb;

import android.view.KeyEvent;
import android.widget.TextView;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersEditableAdapter;
import com.samsung.android.gallery.widget.search.searchbar.selectedfilter.SearchSelectedFiltersEditableViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements TextView.OnEditorActionListener {
    public final /* synthetic */ SearchSelectedFiltersEditableAdapter d;
    public final /* synthetic */ SearchSelectedFiltersEditableViewHolder e;

    public /* synthetic */ a(SearchSelectedFiltersEditableAdapter searchSelectedFiltersEditableAdapter, SearchSelectedFiltersEditableViewHolder searchSelectedFiltersEditableViewHolder) {
        this.d = searchSelectedFiltersEditableAdapter;
        this.e = searchSelectedFiltersEditableViewHolder;
    }

    public final boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
        return this.d.lambda$onBindViewHolder$0(this.e, textView, i2, keyEvent);
    }
}
