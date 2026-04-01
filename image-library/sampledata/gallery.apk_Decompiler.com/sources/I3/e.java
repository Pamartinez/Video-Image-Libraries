package I3;

import android.view.View;
import com.samsung.android.gallery.app.controller.creature.MergeCreatureChoiceCmd;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ MergeCreatureChoiceCmd d;
    public final /* synthetic */ String e;
    public final /* synthetic */ List f;
    public final /* synthetic */ String g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ String f2362h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ String f2363i;

    public /* synthetic */ e(MergeCreatureChoiceCmd mergeCreatureChoiceCmd, String str, List list, String str2, String str3, String str4) {
        this.d = mergeCreatureChoiceCmd;
        this.e = str;
        this.f = list;
        this.g = str2;
        this.f2362h = str3;
        this.f2363i = str4;
    }

    public final void onClick(View view) {
        this.d.lambda$showUndoMergeSnackBar$3(this.e, this.f, this.g, this.f2362h, this.f2363i, view);
    }
}
