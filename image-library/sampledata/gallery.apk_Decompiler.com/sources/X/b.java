package x;

import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements LifecycleEventObserver {
    public final /* synthetic */ MenuHostHelper d;
    public final /* synthetic */ MenuProvider e;

    public /* synthetic */ b(MenuHostHelper menuHostHelper, MenuProvider menuProvider) {
        this.d = menuHostHelper;
        this.e = menuProvider;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        this.d.lambda$addMenuProvider$0(this.e, lifecycleOwner, event);
    }
}
