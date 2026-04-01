package u4;

import android.view.View;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.app.sdk.deepsky.textextraction.languagepack.OnDeviceLanguage;
import com.samsung.android.app.sdk.deepsky.textextraction.translate.data.ui.LanguageSelectFragment;
import com.samsung.android.gallery.app.ui.dialog.creature.picker.CreaturePickerAdapter;
import com.samsung.android.gallery.app.ui.dialog.tag.AddTagAdapter;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeader2View;
import com.samsung.android.gallery.app.ui.list.search.pictures.headerview.SearchCreatureHeaderView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.plugins.compare.CompareActivity;
import com.samsung.android.gallery.widget.search.pictures.OnHeaderClickListener;
import z2.r;

/* renamed from: u4.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0518a implements View.OnClickListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ C0518a(int i2, Object obj, Object obj2) {
        this.d = i2;
        this.e = obj;
        this.f = obj2;
    }

    public final void onClick(View view) {
        switch (this.d) {
            case 0:
                ((CreaturePickerAdapter) this.e).lambda$onBindViewHolder$3((MediaItem) this.f, view);
                return;
            case 1:
                LanguageSelectFragment.createLanguageItem$lambda$15$lambda$14((LanguageSelectFragment) this.e, (OnDeviceLanguage) this.f, view);
                return;
            case 2:
                ((CompareActivity) this.e).lambda$bindToolbar$14((Toolbar) this.f, view);
                return;
            case 3:
                r rVar = (r) this.e;
                rVar.getClass();
                ((View.OnClickListener) this.f).onClick(view);
                rVar.a(1);
                return;
            case 4:
                ((AddTagAdapter) this.e).lambda$onBindViewHolder$1((String) this.f, view);
                return;
            case 5:
                ((SearchCreatureHeader2View) this.e).lambda$setHeaderItemClickListener$9((OnHeaderClickListener) this.f, view);
                return;
            default:
                ((SearchCreatureHeaderView) this.e).lambda$setHeaderItemClickListener$12((OnHeaderClickListener) this.f, view);
                return;
        }
    }
}
