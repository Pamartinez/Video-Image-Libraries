package p5;

import android.view.MenuItem;
import com.samsung.android.gallery.app.ui.list.search.creaturecoverchoice.CreatureCoverChoiceFragment;
import com.samsung.android.gallery.support.threadpool.Future;
import com.samsung.android.gallery.support.threadpool.FutureListener;
import p2.C0262c;

/* renamed from: p5.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0503a implements FutureListener, C0262c {
    public final /* synthetic */ CreatureCoverChoiceFragment d;

    public /* synthetic */ C0503a(CreatureCoverChoiceFragment creatureCoverChoiceFragment) {
        this.d = creatureCoverChoiceFragment;
    }

    public void onFutureDone(Future future) {
        this.d.lambda$updateEditCreatureNameHeader$5(future);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return this.d.onMenuItemSelected(menuItem);
    }
}
