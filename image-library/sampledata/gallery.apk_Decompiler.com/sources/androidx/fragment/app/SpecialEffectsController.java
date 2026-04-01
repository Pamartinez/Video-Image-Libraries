package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.fragment.R$id;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1200r;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0013\b!\u0018\u0000 I2\u00020\u0001:\u0004IJKLB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\nJ'\u0010\u0013\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0018\u001a\u00020\u00172\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001a\u001a\u00020\u00172\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0002¢\u0006\u0004\b\u001a\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u001d\u0010\u001f\u001a\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u001dH\u0002¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b!\u0010\"J\u001d\u0010#\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b%\u0010&J\u0015\u0010'\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b'\u0010&J\u0015\u0010(\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b(\u0010&J\u0015\u0010*\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u0017¢\u0006\u0004\b*\u0010+J\r\u0010,\u001a\u00020\u0012¢\u0006\u0004\b,\u0010\u001cJ\r\u0010-\u001a\u00020\u0017¢\u0006\u0004\b-\u0010.J\r\u0010/\u001a\u00020\u0012¢\u0006\u0004\b/\u0010\u001cJ\r\u00100\u001a\u00020\u0012¢\u0006\u0004\b0\u0010\u001cJ\u0017\u00104\u001a\u00020\u00122\u0006\u00101\u001a\u00020\bH\u0000¢\u0006\u0004\b2\u00103J\r\u00105\u001a\u00020\u0012¢\u0006\u0004\b5\u0010\u001cJ%\u00106\u001a\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u001d2\u0006\u0010)\u001a\u00020\u0017H&¢\u0006\u0004\b6\u00107J\u001d\u00109\u001a\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u001dH\u0010¢\u0006\u0004\b8\u0010 J\u0015\u0010<\u001a\u00020\u00122\u0006\u0010;\u001a\u00020:¢\u0006\u0004\b<\u0010=J\r\u0010>\u001a\u00020\u0012¢\u0006\u0004\b>\u0010\u001cR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010?\u001a\u0004\b@\u0010AR\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020\b0\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020\b0\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\bD\u0010CR\u0016\u0010E\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010G\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u0010FR\u0016\u0010H\u001a\u00020\u00178\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bH\u0010F¨\u0006M"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController;", "", "Landroid/view/ViewGroup;", "container", "<init>", "(Landroid/view/ViewGroup;)V", "Landroidx/fragment/app/Fragment;", "fragment", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "findPendingOperation", "(Landroidx/fragment/app/Fragment;)Landroidx/fragment/app/SpecialEffectsController$Operation;", "findRunningOperation", "Landroidx/fragment/app/SpecialEffectsController$Operation$State;", "finalState", "Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;", "lifecycleImpact", "Landroidx/fragment/app/FragmentStateManager;", "fragmentStateManager", "Lme/x;", "enqueue", "(Landroidx/fragment/app/SpecialEffectsController$Operation$State;Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;Landroidx/fragment/app/FragmentStateManager;)V", "", "newPendingOperations", "", "isOperationTransitioning", "(Ljava/util/List;)Z", "isOperationSeekable", "updateFinalState", "()V", "", "operations", "processStart", "(Ljava/util/List;)V", "getAwaitingCompletionLifecycleImpact", "(Landroidx/fragment/app/FragmentStateManager;)Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;", "enqueueAdd", "(Landroidx/fragment/app/SpecialEffectsController$Operation$State;Landroidx/fragment/app/FragmentStateManager;)V", "enqueueShow", "(Landroidx/fragment/app/FragmentStateManager;)V", "enqueueHide", "enqueueRemove", "isPop", "updateOperationDirection", "(Z)V", "markPostponedState", "isPendingExecute", "()Z", "forcePostponedExecutePendingOperations", "executePendingOperations", "operation", "applyContainerChangesToOperation$fragment_release", "(Landroidx/fragment/app/SpecialEffectsController$Operation;)V", "applyContainerChangesToOperation", "forceCompleteAllOperations", "collectEffects", "(Ljava/util/List;Z)V", "commitEffects$fragment_release", "commitEffects", "Landroidx/activity/BackEventCompat;", "backEvent", "processProgress", "(Landroidx/activity/BackEventCompat;)V", "completeBack", "Landroid/view/ViewGroup;", "getContainer", "()Landroid/view/ViewGroup;", "pendingOperations", "Ljava/util/List;", "runningOperations", "runningNonSeekableTransition", "Z", "operationDirectionIsPop", "isContainerPostponed", "Companion", "Effect", "FragmentStateManagerOperation", "Operation", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SpecialEffectsController {
    public static final Companion Companion = new Companion((e) null);
    private final ViewGroup container;
    private boolean isContainerPostponed;
    private boolean operationDirectionIsPop;
    private final List<Operation> pendingOperations = new ArrayList();
    private boolean runningNonSeekableTransition;
    private final List<Operation> runningOperations = new ArrayList();

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007¨\u0006\u000b"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController$Companion;", "", "()V", "getOrCreateController", "Landroidx/fragment/app/SpecialEffectsController;", "container", "Landroid/view/ViewGroup;", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "factory", "Landroidx/fragment/app/SpecialEffectsControllerFactory;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final SpecialEffectsController getOrCreateController(ViewGroup viewGroup, FragmentManager fragmentManager) {
            j.e(viewGroup, "container");
            j.e(fragmentManager, "fragmentManager");
            SpecialEffectsControllerFactory specialEffectsControllerFactory = fragmentManager.getSpecialEffectsControllerFactory();
            j.d(specialEffectsControllerFactory, "fragmentManager.specialEffectsControllerFactory");
            return getOrCreateController(viewGroup, specialEffectsControllerFactory);
        }

        private Companion() {
        }

        public final SpecialEffectsController getOrCreateController(ViewGroup viewGroup, SpecialEffectsControllerFactory specialEffectsControllerFactory) {
            j.e(viewGroup, "container");
            j.e(specialEffectsControllerFactory, "factory");
            int i2 = R$id.special_effects_controller_view_tag;
            Object tag = viewGroup.getTag(i2);
            if (tag instanceof SpecialEffectsController) {
                return (SpecialEffectsController) tag;
            }
            SpecialEffectsController createController = specialEffectsControllerFactory.createController(viewGroup);
            j.d(createController, "factory.createController(container)");
            viewGroup.setTag(i2, createController);
            return createController;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\t\u0010\bJ\u001f\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000e\u0010\bJ\u0015\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\bJ\u0017\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0010\u0010\bR\u001a\u0010\u0012\u001a\u00020\u00118\u0016XD¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0012\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0016\u001a\u00020\u00118\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0013¨\u0006\u0017"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController$Effect;", "", "<init>", "()V", "Landroid/view/ViewGroup;", "container", "Lme/x;", "performStart", "(Landroid/view/ViewGroup;)V", "onStart", "Landroidx/activity/BackEventCompat;", "backEvent", "onProgress", "(Landroidx/activity/BackEventCompat;Landroid/view/ViewGroup;)V", "onCommit", "cancel", "onCancel", "", "isSeekingSupported", "Z", "()Z", "isStarted", "isCancelled", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Effect {
        private boolean isCancelled;
        private final boolean isSeekingSupported;
        private boolean isStarted;

        public final void cancel(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            if (!this.isCancelled) {
                onCancel(viewGroup);
            }
            this.isCancelled = true;
        }

        public boolean isSeekingSupported() {
            return this.isSeekingSupported;
        }

        public abstract void onCancel(ViewGroup viewGroup);

        public abstract void onCommit(ViewGroup viewGroup);

        public void onProgress(BackEventCompat backEventCompat, ViewGroup viewGroup) {
            j.e(backEventCompat, "backEvent");
            j.e(viewGroup, "container");
        }

        public void onStart(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
        }

        public final void performStart(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            if (!this.isStarted) {
                onStart(viewGroup);
            }
            this.isStarted = true;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\nH\u0010¢\u0006\u0004\b\r\u0010\fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController$FragmentStateManagerOperation;", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "Landroidx/fragment/app/SpecialEffectsController$Operation$State;", "finalState", "Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;", "lifecycleImpact", "Landroidx/fragment/app/FragmentStateManager;", "fragmentStateManager", "<init>", "(Landroidx/fragment/app/SpecialEffectsController$Operation$State;Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;Landroidx/fragment/app/FragmentStateManager;)V", "Lme/x;", "onStart", "()V", "complete$fragment_release", "complete", "Landroidx/fragment/app/FragmentStateManager;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FragmentStateManagerOperation extends Operation {
        private final FragmentStateManager fragmentStateManager;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public FragmentStateManagerOperation(androidx.fragment.app.SpecialEffectsController.Operation.State r3, androidx.fragment.app.SpecialEffectsController.Operation.LifecycleImpact r4, androidx.fragment.app.FragmentStateManager r5) {
            /*
                r2 = this;
                java.lang.String r0 = "finalState"
                kotlin.jvm.internal.j.e(r3, r0)
                java.lang.String r0 = "lifecycleImpact"
                kotlin.jvm.internal.j.e(r4, r0)
                java.lang.String r0 = "fragmentStateManager"
                kotlin.jvm.internal.j.e(r5, r0)
                androidx.fragment.app.Fragment r0 = r5.getFragment()
                java.lang.String r1 = "fragmentStateManager.fragment"
                kotlin.jvm.internal.j.d(r0, r1)
                r2.<init>(r3, r4, r0)
                r2.fragmentStateManager = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.SpecialEffectsController.FragmentStateManagerOperation.<init>(androidx.fragment.app.SpecialEffectsController$Operation$State, androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact, androidx.fragment.app.FragmentStateManager):void");
        }

        public void complete$fragment_release() {
            super.complete$fragment_release();
            getFragment().mTransitioning = false;
            this.fragmentStateManager.moveToExpectedState();
        }

        public void onStart() {
            if (!isStarted()) {
                super.onStart();
                if (getLifecycleImpact() == Operation.LifecycleImpact.ADDING) {
                    Fragment fragment = this.fragmentStateManager.getFragment();
                    j.d(fragment, "fragmentStateManager.fragment");
                    View findFocus = fragment.mView.findFocus();
                    if (findFocus != null) {
                        fragment.setFocusedView(findFocus);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + fragment);
                        }
                    }
                    View requireView = getFragment().requireView();
                    j.d(requireView, "this.fragment.requireView()");
                    if (requireView.getParent() == null) {
                        this.fragmentStateManager.addViewToContainer();
                        requireView.setAlpha(0.0f);
                    }
                    if (requireView.getAlpha() == 0.0f && requireView.getVisibility() == 0) {
                        requireView.setVisibility(4);
                    }
                    requireView.setAlpha(fragment.getPostOnViewCreatedAlpha());
                } else if (getLifecycleImpact() == Operation.LifecycleImpact.REMOVING) {
                    Fragment fragment2 = this.fragmentStateManager.getFragment();
                    j.d(fragment2, "fragmentStateManager.fragment");
                    View requireView2 = fragment2.requireView();
                    j.d(requireView2, "fragment.requireView()");
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Clearing focus " + requireView2.findFocus() + " on view " + requireView2 + " for Fragment " + fragment2);
                    }
                    requireView2.clearFocus();
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u0001:\u0002BCB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001c\u0010\u001bJ\u000f\u0010\u001d\u001a\u00020\u000fH\u0017¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u000fH\u0011¢\u0006\u0004\b\u001f\u0010\u001eR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010+\u001a\u0004\b,\u0010-R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00140.8\u0002X\u0004¢\u0006\u0006\n\u0004\b/\u00100R$\u00103\u001a\u0002012\u0006\u00102\u001a\u0002018\u0006@BX\u000e¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b3\u00105R$\u00106\u001a\u0002012\u0006\u00102\u001a\u0002018\u0006@BX\u000e¢\u0006\f\n\u0004\b6\u00104\u001a\u0004\b6\u00105R*\u00107\u001a\u0002012\u0006\u00102\u001a\u0002018\u0006@@X\u000e¢\u0006\u0012\n\u0004\b7\u00104\u001a\u0004\b7\u00105\"\u0004\b8\u00109R$\u0010:\u001a\u0002012\u0006\u00102\u001a\u0002018\u0006@BX\u000e¢\u0006\f\n\u0004\b:\u00104\u001a\u0004\b:\u00105R\"\u0010;\u001a\u0002018\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b;\u00104\u001a\u0004\b;\u00105\"\u0004\b<\u00109R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00180.8\u0002X\u0004¢\u0006\u0006\n\u0004\b=\u00100R \u0010?\u001a\b\u0012\u0004\u0012\u00020\u00180>8\u0000X\u0004¢\u0006\f\n\u0004\b?\u00100\u001a\u0004\b@\u0010A¨\u0006D"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController$Operation;", "", "Landroidx/fragment/app/SpecialEffectsController$Operation$State;", "finalState", "Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;", "lifecycleImpact", "Landroidx/fragment/app/Fragment;", "fragment", "<init>", "(Landroidx/fragment/app/SpecialEffectsController$Operation$State;Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;Landroidx/fragment/app/Fragment;)V", "", "toString", "()Ljava/lang/String;", "Landroid/view/ViewGroup;", "container", "Lme/x;", "cancel", "(Landroid/view/ViewGroup;)V", "mergeWith", "(Landroidx/fragment/app/SpecialEffectsController$Operation$State;Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;)V", "Ljava/lang/Runnable;", "listener", "addCompletionListener", "(Ljava/lang/Runnable;)V", "Landroidx/fragment/app/SpecialEffectsController$Effect;", "effect", "addEffect", "(Landroidx/fragment/app/SpecialEffectsController$Effect;)V", "completeEffect", "onStart", "()V", "complete$fragment_release", "complete", "Landroidx/fragment/app/SpecialEffectsController$Operation$State;", "getFinalState", "()Landroidx/fragment/app/SpecialEffectsController$Operation$State;", "setFinalState", "(Landroidx/fragment/app/SpecialEffectsController$Operation$State;)V", "Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;", "getLifecycleImpact", "()Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;", "setLifecycleImpact", "(Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;)V", "Landroidx/fragment/app/Fragment;", "getFragment", "()Landroidx/fragment/app/Fragment;", "", "completionListeners", "Ljava/util/List;", "", "<set-?>", "isCanceled", "Z", "()Z", "isComplete", "isSeeking", "setSeeking$fragment_release", "(Z)V", "isStarted", "isAwaitingContainerChanges", "setAwaitingContainerChanges", "_effects", "", "effects", "getEffects$fragment_release", "()Ljava/util/List;", "LifecycleImpact", "State", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Operation {
        private final List<Effect> _effects;
        private final List<Runnable> completionListeners = new ArrayList();
        private final List<Effect> effects;
        private State finalState;
        private final Fragment fragment;
        private boolean isAwaitingContainerChanges = true;
        private boolean isCanceled;
        private boolean isComplete;
        private boolean isSeeking;
        private boolean isStarted;
        private LifecycleImpact lifecycleImpact;

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController$Operation$LifecycleImpact;", "", "(Ljava/lang/String;I)V", "NONE", "ADDING", "REMOVING", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING
        }

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController$Operation$State;", "", "<init>", "(Ljava/lang/String;I)V", "Landroid/view/View;", "view", "Landroid/view/ViewGroup;", "container", "Lme/x;", "applyState", "(Landroid/view/View;Landroid/view/ViewGroup;)V", "Companion", "REMOVED", "VISIBLE", "GONE", "INVISIBLE", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;
            
            public static final Companion Companion = null;

            @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\n\u0010\u0007\u001a\u00020\u0004*\u00020\b¨\u0006\t"}, d2 = {"Landroidx/fragment/app/SpecialEffectsController$Operation$State$Companion;", "", "()V", "from", "Landroidx/fragment/app/SpecialEffectsController$Operation$State;", "visibility", "", "asOperationState", "Landroid/view/View;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public static final class Companion {
                public /* synthetic */ Companion(e eVar) {
                    this();
                }

                public final State asOperationState(View view) {
                    j.e(view, "<this>");
                    if (view.getAlpha() == 0.0f && view.getVisibility() == 0) {
                        return State.INVISIBLE;
                    }
                    return from(view.getVisibility());
                }

                public final State from(int i2) {
                    if (i2 == 0) {
                        return State.VISIBLE;
                    }
                    if (i2 == 4) {
                        return State.INVISIBLE;
                    }
                    if (i2 == 8) {
                        return State.GONE;
                    }
                    throw new IllegalArgumentException(C0086a.i(i2, "Unknown visibility "));
                }

                private Companion() {
                }
            }

            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

                /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
                /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
                static {
                    /*
                        androidx.fragment.app.SpecialEffectsController$Operation$State[] r0 = androidx.fragment.app.SpecialEffectsController.Operation.State.values()
                        int r0 = r0.length
                        int[] r0 = new int[r0]
                        androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.REMOVED     // Catch:{ NoSuchFieldError -> 0x0010 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                        r2 = 1
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                    L_0x0010:
                        androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0019 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                        r2 = 2
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                    L_0x0019:
                        androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE     // Catch:{ NoSuchFieldError -> 0x0022 }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                        r2 = 3
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                    L_0x0022:
                        androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.INVISIBLE     // Catch:{ NoSuchFieldError -> 0x002b }
                        int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                        r2 = 4
                        r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                    L_0x002b:
                        $EnumSwitchMapping$0 = r0
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.SpecialEffectsController.Operation.State.WhenMappings.<clinit>():void");
                }
            }

            static {
                Companion = new Companion((e) null);
            }

            public static final State from(int i2) {
                return Companion.from(i2);
            }

            /* JADX WARNING: type inference failed for: r4v3, types: [android.view.ViewParent] */
            /* JADX WARNING: type inference failed for: r4v8, types: [android.view.ViewParent] */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Unknown variable types count: 2 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void applyState(android.view.View r5, android.view.ViewGroup r6) {
                /*
                    r4 = this;
                    java.lang.String r0 = "view"
                    kotlin.jvm.internal.j.e(r5, r0)
                    java.lang.String r0 = "container"
                    kotlin.jvm.internal.j.e(r6, r0)
                    int[] r0 = androidx.fragment.app.SpecialEffectsController.Operation.State.WhenMappings.$EnumSwitchMapping$0
                    int r4 = r4.ordinal()
                    r4 = r0[r4]
                    r0 = 1
                    r1 = 0
                    java.lang.String r2 = "FragmentManager"
                    r3 = 2
                    if (r4 == r0) goto L_0x00b2
                    java.lang.String r0 = "SpecialEffectsController: Setting view "
                    if (r4 == r3) goto L_0x0064
                    r6 = 3
                    if (r4 == r6) goto L_0x0044
                    r6 = 4
                    if (r4 == r6) goto L_0x0026
                    goto L_0x00e1
                L_0x0026:
                    boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r3)
                    if (r4 == 0) goto L_0x0040
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>(r0)
                    r4.append(r5)
                    java.lang.String r0 = " to INVISIBLE"
                    r4.append(r0)
                    java.lang.String r4 = r4.toString()
                    android.util.Log.v(r2, r4)
                L_0x0040:
                    r5.setVisibility(r6)
                    return
                L_0x0044:
                    boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r3)
                    if (r4 == 0) goto L_0x005e
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>(r0)
                    r4.append(r5)
                    java.lang.String r6 = " to GONE"
                    r4.append(r6)
                    java.lang.String r4 = r4.toString()
                    android.util.Log.v(r2, r4)
                L_0x005e:
                    r4 = 8
                    r5.setVisibility(r4)
                    return
                L_0x0064:
                    boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r3)
                    if (r4 == 0) goto L_0x007e
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>(r0)
                    r4.append(r5)
                    java.lang.String r0 = " to VISIBLE"
                    r4.append(r0)
                    java.lang.String r4 = r4.toString()
                    android.util.Log.v(r2, r4)
                L_0x007e:
                    android.view.ViewParent r4 = r5.getParent()
                    boolean r0 = r4 instanceof android.view.ViewGroup
                    if (r0 == 0) goto L_0x0089
                    r1 = r4
                    android.view.ViewGroup r1 = (android.view.ViewGroup) r1
                L_0x0089:
                    if (r1 != 0) goto L_0x00ad
                    boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r3)
                    if (r4 == 0) goto L_0x00aa
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    java.lang.String r0 = "SpecialEffectsController: Adding view "
                    r4.<init>(r0)
                    r4.append(r5)
                    java.lang.String r0 = " to Container "
                    r4.append(r0)
                    r4.append(r6)
                    java.lang.String r4 = r4.toString()
                    android.util.Log.v(r2, r4)
                L_0x00aa:
                    r6.addView(r5)
                L_0x00ad:
                    r4 = 0
                    r5.setVisibility(r4)
                    return
                L_0x00b2:
                    android.view.ViewParent r4 = r5.getParent()
                    boolean r6 = r4 instanceof android.view.ViewGroup
                    if (r6 == 0) goto L_0x00bd
                    r1 = r4
                    android.view.ViewGroup r1 = (android.view.ViewGroup) r1
                L_0x00bd:
                    if (r1 == 0) goto L_0x00e1
                    boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r3)
                    if (r4 == 0) goto L_0x00de
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    java.lang.String r6 = "SpecialEffectsController: Removing view "
                    r4.<init>(r6)
                    r4.append(r5)
                    java.lang.String r6 = " from container "
                    r4.append(r6)
                    r4.append(r1)
                    java.lang.String r4 = r4.toString()
                    android.util.Log.v(r2, r4)
                L_0x00de:
                    r1.removeView(r5)
                L_0x00e1:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.SpecialEffectsController.Operation.State.applyState(android.view.View, android.view.ViewGroup):void");
            }
        }

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            static {
                /*
                    androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact[] r0 = androidx.fragment.app.SpecialEffectsController.Operation.LifecycleImpact.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact r1 = androidx.fragment.app.SpecialEffectsController.Operation.LifecycleImpact.ADDING     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact r1 = androidx.fragment.app.SpecialEffectsController.Operation.LifecycleImpact.REMOVING     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact r1 = androidx.fragment.app.SpecialEffectsController.Operation.LifecycleImpact.NONE     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.SpecialEffectsController.Operation.WhenMappings.<clinit>():void");
            }
        }

        public Operation(State state, LifecycleImpact lifecycleImpact2, Fragment fragment2) {
            j.e(state, "finalState");
            j.e(lifecycleImpact2, "lifecycleImpact");
            j.e(fragment2, "fragment");
            this.finalState = state;
            this.lifecycleImpact = lifecycleImpact2;
            this.fragment = fragment2;
            ArrayList arrayList = new ArrayList();
            this._effects = arrayList;
            this.effects = arrayList;
        }

        public final void addCompletionListener(Runnable runnable) {
            j.e(runnable, "listener");
            this.completionListeners.add(runnable);
        }

        public final void addEffect(Effect effect) {
            j.e(effect, "effect");
            this._effects.add(effect);
        }

        public final void cancel(ViewGroup viewGroup) {
            j.e(viewGroup, "container");
            this.isStarted = false;
            if (!this.isCanceled) {
                this.isCanceled = true;
                if (this._effects.isEmpty()) {
                    complete$fragment_release();
                    return;
                }
                for (Effect cancel : C1194l.k1(this.effects)) {
                    cancel.cancel(viewGroup);
                }
            }
        }

        public void complete$fragment_release() {
            this.isStarted = false;
            if (!this.isComplete) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
                }
                this.isComplete = true;
                for (Runnable run : this.completionListeners) {
                    run.run();
                }
            }
        }

        public final void completeEffect(Effect effect) {
            j.e(effect, "effect");
            if (this._effects.remove(effect) && this._effects.isEmpty()) {
                complete$fragment_release();
            }
        }

        public final List<Effect> getEffects$fragment_release() {
            return this.effects;
        }

        public final State getFinalState() {
            return this.finalState;
        }

        public final Fragment getFragment() {
            return this.fragment;
        }

        public final LifecycleImpact getLifecycleImpact() {
            return this.lifecycleImpact;
        }

        public final boolean isAwaitingContainerChanges() {
            return this.isAwaitingContainerChanges;
        }

        public final boolean isCanceled() {
            return this.isCanceled;
        }

        public final boolean isComplete() {
            return this.isComplete;
        }

        public final boolean isSeeking() {
            return this.isSeeking;
        }

        public final boolean isStarted() {
            return this.isStarted;
        }

        public final void mergeWith(State state, LifecycleImpact lifecycleImpact2) {
            j.e(state, "finalState");
            j.e(lifecycleImpact2, "lifecycleImpact");
            int i2 = WhenMappings.$EnumSwitchMapping$0[lifecycleImpact2.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.fragment + " mFinalState = " + this.finalState + " -> REMOVED. mLifecycleImpact  = " + this.lifecycleImpact + " to REMOVING.");
                    }
                    this.finalState = State.REMOVED;
                    this.lifecycleImpact = LifecycleImpact.REMOVING;
                    this.isAwaitingContainerChanges = true;
                } else if (i2 == 3 && this.finalState != State.REMOVED) {
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.fragment + " mFinalState = " + this.finalState + " -> " + state + '.');
                    }
                    this.finalState = state;
                }
            } else if (this.finalState == State.REMOVED) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.fragment + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.lifecycleImpact + " to ADDING.");
                }
                this.finalState = State.VISIBLE;
                this.lifecycleImpact = LifecycleImpact.ADDING;
                this.isAwaitingContainerChanges = true;
            }
        }

        public void onStart() {
            this.isStarted = true;
        }

        public final void setAwaitingContainerChanges(boolean z) {
            this.isAwaitingContainerChanges = z;
        }

        public final void setSeeking$fragment_release(boolean z) {
            this.isSeeking = z;
        }

        public String toString() {
            StringBuilder k = N2.j.k("Operation {", Integer.toHexString(System.identityHashCode(this)), "} {finalState = ");
            k.append(this.finalState);
            k.append(" lifecycleImpact = ");
            k.append(this.lifecycleImpact);
            k.append(" fragment = ");
            k.append(this.fragment);
            k.append('}');
            return k.toString();
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Operation.LifecycleImpact.values().length];
            try {
                iArr[Operation.LifecycleImpact.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SpecialEffectsController(ViewGroup viewGroup) {
        j.e(viewGroup, "container");
        this.container = viewGroup;
    }

    private final void enqueue(Operation.State state, Operation.LifecycleImpact lifecycleImpact, FragmentStateManager fragmentStateManager) {
        synchronized (this.pendingOperations) {
            try {
                Fragment fragment = fragmentStateManager.getFragment();
                j.d(fragment, "fragmentStateManager.fragment");
                Operation findPendingOperation = findPendingOperation(fragment);
                if (findPendingOperation == null) {
                    if (fragmentStateManager.getFragment().mTransitioning) {
                        Fragment fragment2 = fragmentStateManager.getFragment();
                        j.d(fragment2, "fragmentStateManager.fragment");
                        findPendingOperation = findRunningOperation(fragment2);
                    } else {
                        findPendingOperation = null;
                    }
                }
                if (findPendingOperation != null) {
                    findPendingOperation.mergeWith(state, lifecycleImpact);
                    return;
                }
                FragmentStateManagerOperation fragmentStateManagerOperation = new FragmentStateManagerOperation(state, lifecycleImpact, fragmentStateManager);
                this.pendingOperations.add(fragmentStateManagerOperation);
                fragmentStateManagerOperation.addCompletionListener(new k(this, fragmentStateManagerOperation, 0));
                fragmentStateManagerOperation.addCompletionListener(new k(this, fragmentStateManagerOperation, 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void enqueue$lambda$4$lambda$2(SpecialEffectsController specialEffectsController, FragmentStateManagerOperation fragmentStateManagerOperation) {
        j.e(specialEffectsController, "this$0");
        j.e(fragmentStateManagerOperation, "$operation");
        if (specialEffectsController.pendingOperations.contains(fragmentStateManagerOperation)) {
            Operation.State finalState = fragmentStateManagerOperation.getFinalState();
            View view = fragmentStateManagerOperation.getFragment().mView;
            j.d(view, "operation.fragment.mView");
            finalState.applyState(view, specialEffectsController.container);
        }
    }

    /* access modifiers changed from: private */
    public static final void enqueue$lambda$4$lambda$3(SpecialEffectsController specialEffectsController, FragmentStateManagerOperation fragmentStateManagerOperation) {
        j.e(specialEffectsController, "this$0");
        j.e(fragmentStateManagerOperation, "$operation");
        specialEffectsController.pendingOperations.remove(fragmentStateManagerOperation);
        specialEffectsController.runningOperations.remove(fragmentStateManagerOperation);
    }

    private final Operation findPendingOperation(Fragment fragment) {
        Object obj;
        Iterator it = this.pendingOperations.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Operation operation = (Operation) obj;
            if (j.a(operation.getFragment(), fragment) && !operation.isCanceled()) {
                break;
            }
        }
        return (Operation) obj;
    }

    private final Operation findRunningOperation(Fragment fragment) {
        Object obj;
        Iterator it = this.runningOperations.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Operation operation = (Operation) obj;
            if (j.a(operation.getFragment(), fragment) && !operation.isCanceled()) {
                break;
            }
        }
        return (Operation) obj;
    }

    public static final SpecialEffectsController getOrCreateController(ViewGroup viewGroup, FragmentManager fragmentManager) {
        return Companion.getOrCreateController(viewGroup, fragmentManager);
    }

    private final boolean isOperationSeekable(List<Operation> list) {
        boolean z;
        Iterable<Operation> iterable = list;
        loop0:
        while (true) {
            z = true;
            for (Operation operation : iterable) {
                if (!operation.getEffects$fragment_release().isEmpty()) {
                    Iterable<Effect> effects$fragment_release = operation.getEffects$fragment_release();
                    if (!(effects$fragment_release instanceof Collection) || !((Collection) effects$fragment_release).isEmpty()) {
                        for (Effect isSeekingSupported : effects$fragment_release) {
                            if (!isSeekingSupported.isSeekingSupported()) {
                            }
                        }
                    }
                }
                z = false;
            }
            break loop0;
        }
        if (z) {
            ArrayList arrayList = new ArrayList();
            for (Operation effects$fragment_release2 : iterable) {
                C1200r.A0(effects$fragment_release2.getEffects$fragment_release(), arrayList);
            }
            if (!arrayList.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private final boolean isOperationTransitioning(List<Operation> list) {
        boolean z = true;
        for (Operation fragment : list) {
            if (!fragment.getFragment().mTransitioning) {
                z = false;
            }
        }
        return z;
    }

    private final void processStart(List<Operation> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            list.get(i2).onStart();
        }
        ArrayList arrayList = new ArrayList();
        for (Operation effects$fragment_release : list) {
            C1200r.A0(effects$fragment_release.getEffects$fragment_release(), arrayList);
        }
        List k12 = C1194l.k1(C1194l.p1(arrayList));
        int size2 = k12.size();
        for (int i7 = 0; i7 < size2; i7++) {
            ((Effect) k12.get(i7)).performStart(this.container);
        }
    }

    private final void updateFinalState() {
        for (Operation next : this.pendingOperations) {
            if (next.getLifecycleImpact() == Operation.LifecycleImpact.ADDING) {
                View requireView = next.getFragment().requireView();
                j.d(requireView, "fragment.requireView()");
                next.mergeWith(Operation.State.Companion.from(requireView.getVisibility()), Operation.LifecycleImpact.NONE);
            }
        }
    }

    public final void applyContainerChangesToOperation$fragment_release(Operation operation) {
        j.e(operation, "operation");
        if (operation.isAwaitingContainerChanges()) {
            Operation.State finalState = operation.getFinalState();
            View requireView = operation.getFragment().requireView();
            j.d(requireView, "operation.fragment.requireView()");
            finalState.applyState(requireView, this.container);
            operation.setAwaitingContainerChanges(false);
        }
    }

    public abstract void collectEffects(List<Operation> list, boolean z);

    public void commitEffects$fragment_release(List<Operation> list) {
        j.e(list, "operations");
        Iterable<Operation> iterable = list;
        ArrayList arrayList = new ArrayList();
        for (Operation effects$fragment_release : iterable) {
            C1200r.A0(effects$fragment_release.getEffects$fragment_release(), arrayList);
        }
        List k12 = C1194l.k1(C1194l.p1(arrayList));
        int size = k12.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Effect) k12.get(i2)).onCommit(this.container);
        }
        int size2 = list.size();
        for (int i7 = 0; i7 < size2; i7++) {
            applyContainerChangesToOperation$fragment_release(list.get(i7));
        }
        List k13 = C1194l.k1(iterable);
        int size3 = k13.size();
        for (int i8 = 0; i8 < size3; i8++) {
            Operation operation = (Operation) k13.get(i8);
            if (operation.getEffects$fragment_release().isEmpty()) {
                operation.complete$fragment_release();
            }
        }
    }

    public final void completeBack() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "SpecialEffectsController: Completing Back ");
        }
        processStart(this.runningOperations);
        commitEffects$fragment_release(this.runningOperations);
    }

    public final void enqueueAdd(Operation.State state, FragmentStateManager fragmentStateManager) {
        j.e(state, "finalState");
        j.e(fragmentStateManager, "fragmentStateManager");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(state, Operation.LifecycleImpact.ADDING, fragmentStateManager);
    }

    public final void enqueueHide(FragmentStateManager fragmentStateManager) {
        j.e(fragmentStateManager, "fragmentStateManager");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(Operation.State.GONE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    public final void enqueueRemove(FragmentStateManager fragmentStateManager) {
        j.e(fragmentStateManager, "fragmentStateManager");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(Operation.State.REMOVED, Operation.LifecycleImpact.REMOVING, fragmentStateManager);
    }

    public final void enqueueShow(FragmentStateManager fragmentStateManager) {
        j.e(fragmentStateManager, "fragmentStateManager");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + fragmentStateManager.getFragment());
        }
        enqueue(Operation.State.VISIBLE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0152, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void executePendingOperations() {
        /*
            r9 = this;
            boolean r0 = r9.isContainerPostponed
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            android.view.ViewGroup r0 = r9.container
            boolean r0 = r0.isAttachedToWindow()
            r1 = 0
            if (r0 != 0) goto L_0x0014
            r9.forceCompleteAllOperations()
            r9.operationDirectionIsPop = r1
            return
        L_0x0014:
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r0 = r9.pendingOperations
            monitor-enter(r0)
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r2 = r9.runningOperations     // Catch:{ all -> 0x0048 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x0048 }
            java.util.ArrayList r2 = ne.C1194l.m1(r2)     // Catch:{ all -> 0x0048 }
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r3 = r9.runningOperations     // Catch:{ all -> 0x0048 }
            r3.clear()     // Catch:{ all -> 0x0048 }
            java.util.Iterator r3 = r2.iterator()     // Catch:{ all -> 0x0048 }
        L_0x0028:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0048 }
            r5 = 1
            if (r4 == 0) goto L_0x0050
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0048 }
            androidx.fragment.app.SpecialEffectsController$Operation r4 = (androidx.fragment.app.SpecialEffectsController.Operation) r4     // Catch:{ all -> 0x0048 }
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r6 = r9.pendingOperations     // Catch:{ all -> 0x0048 }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ all -> 0x0048 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x0048 }
            if (r6 != 0) goto L_0x004b
            androidx.fragment.app.Fragment r6 = r4.getFragment()     // Catch:{ all -> 0x0048 }
            boolean r6 = r6.mTransitioning     // Catch:{ all -> 0x0048 }
            if (r6 == 0) goto L_0x004b
            goto L_0x004c
        L_0x0048:
            r9 = move-exception
            goto L_0x0153
        L_0x004b:
            r5 = r1
        L_0x004c:
            r4.setSeeking$fragment_release(r5)     // Catch:{ all -> 0x0048 }
            goto L_0x0028
        L_0x0050:
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0048 }
        L_0x0054:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0048 }
            r4 = 2
            if (r3 == 0) goto L_0x00b4
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x0048 }
            androidx.fragment.app.SpecialEffectsController$Operation r3 = (androidx.fragment.app.SpecialEffectsController.Operation) r3     // Catch:{ all -> 0x0048 }
            boolean r6 = r9.runningNonSeekableTransition     // Catch:{ all -> 0x0048 }
            if (r6 == 0) goto L_0x0085
            boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r4)     // Catch:{ all -> 0x0048 }
            if (r4 == 0) goto L_0x0081
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0048 }
            r6.<init>()     // Catch:{ all -> 0x0048 }
            java.lang.String r7 = "SpecialEffectsController: Completing non-seekable operation "
            r6.append(r7)     // Catch:{ all -> 0x0048 }
            r6.append(r3)     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0048 }
            android.util.Log.v(r4, r6)     // Catch:{ all -> 0x0048 }
        L_0x0081:
            r3.complete$fragment_release()     // Catch:{ all -> 0x0048 }
            goto L_0x00a6
        L_0x0085:
            boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r4)     // Catch:{ all -> 0x0048 }
            if (r4 == 0) goto L_0x00a1
            java.lang.String r4 = "FragmentManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0048 }
            r6.<init>()     // Catch:{ all -> 0x0048 }
            java.lang.String r7 = "SpecialEffectsController: Cancelling operation "
            r6.append(r7)     // Catch:{ all -> 0x0048 }
            r6.append(r3)     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0048 }
            android.util.Log.v(r4, r6)     // Catch:{ all -> 0x0048 }
        L_0x00a1:
            android.view.ViewGroup r4 = r9.container     // Catch:{ all -> 0x0048 }
            r3.cancel(r4)     // Catch:{ all -> 0x0048 }
        L_0x00a6:
            r9.runningNonSeekableTransition = r1     // Catch:{ all -> 0x0048 }
            boolean r4 = r3.isComplete()     // Catch:{ all -> 0x0048 }
            if (r4 != 0) goto L_0x0054
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r4 = r9.runningOperations     // Catch:{ all -> 0x0048 }
            r4.add(r3)     // Catch:{ all -> 0x0048 }
            goto L_0x0054
        L_0x00b4:
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r2 = r9.pendingOperations     // Catch:{ all -> 0x0048 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x0048 }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x0048 }
            if (r2 != 0) goto L_0x0151
            r9.updateFinalState()     // Catch:{ all -> 0x0048 }
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r2 = r9.pendingOperations     // Catch:{ all -> 0x0048 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x0048 }
            java.util.ArrayList r2 = ne.C1194l.m1(r2)     // Catch:{ all -> 0x0048 }
            boolean r3 = r2.isEmpty()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x00d1
            monitor-exit(r0)
            return
        L_0x00d1:
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r3 = r9.pendingOperations     // Catch:{ all -> 0x0048 }
            r3.clear()     // Catch:{ all -> 0x0048 }
            java.util.List<androidx.fragment.app.SpecialEffectsController$Operation> r3 = r9.runningOperations     // Catch:{ all -> 0x0048 }
            r3.addAll(r2)     // Catch:{ all -> 0x0048 }
            boolean r3 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r4)     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x00e8
            java.lang.String r3 = "FragmentManager"
            java.lang.String r6 = "SpecialEffectsController: Executing pending operations"
            android.util.Log.v(r3, r6)     // Catch:{ all -> 0x0048 }
        L_0x00e8:
            boolean r3 = r9.operationDirectionIsPop     // Catch:{ all -> 0x0048 }
            r9.collectEffects(r2, r3)     // Catch:{ all -> 0x0048 }
            boolean r3 = r9.isOperationSeekable(r2)     // Catch:{ all -> 0x0048 }
            boolean r6 = r9.isOperationTransitioning(r2)     // Catch:{ all -> 0x0048 }
            if (r6 == 0) goto L_0x00fa
            if (r3 != 0) goto L_0x00fa
            goto L_0x00fb
        L_0x00fa:
            r5 = r1
        L_0x00fb:
            r9.runningNonSeekableTransition = r5     // Catch:{ all -> 0x0048 }
            boolean r5 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r4)     // Catch:{ all -> 0x0048 }
            if (r5 == 0) goto L_0x0121
            java.lang.String r5 = "FragmentManager"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0048 }
            r7.<init>()     // Catch:{ all -> 0x0048 }
            java.lang.String r8 = "SpecialEffectsController: Operation seekable = "
            r7.append(r8)     // Catch:{ all -> 0x0048 }
            r7.append(r3)     // Catch:{ all -> 0x0048 }
            java.lang.String r8 = " \ntransition = "
            r7.append(r8)     // Catch:{ all -> 0x0048 }
            r7.append(r6)     // Catch:{ all -> 0x0048 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0048 }
            android.util.Log.v(r5, r7)     // Catch:{ all -> 0x0048 }
        L_0x0121:
            if (r6 != 0) goto L_0x012a
            r9.processStart(r2)     // Catch:{ all -> 0x0048 }
            r9.commitEffects$fragment_release(r2)     // Catch:{ all -> 0x0048 }
            goto L_0x0142
        L_0x012a:
            if (r3 == 0) goto L_0x0142
            r9.processStart(r2)     // Catch:{ all -> 0x0048 }
            int r3 = r2.size()     // Catch:{ all -> 0x0048 }
            r5 = r1
        L_0x0134:
            if (r5 >= r3) goto L_0x0142
            java.lang.Object r6 = r2.get(r5)     // Catch:{ all -> 0x0048 }
            androidx.fragment.app.SpecialEffectsController$Operation r6 = (androidx.fragment.app.SpecialEffectsController.Operation) r6     // Catch:{ all -> 0x0048 }
            r9.applyContainerChangesToOperation$fragment_release(r6)     // Catch:{ all -> 0x0048 }
            int r5 = r5 + 1
            goto L_0x0134
        L_0x0142:
            r9.operationDirectionIsPop = r1     // Catch:{ all -> 0x0048 }
            boolean r9 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r4)     // Catch:{ all -> 0x0048 }
            if (r9 == 0) goto L_0x0151
            java.lang.String r9 = "FragmentManager"
            java.lang.String r1 = "SpecialEffectsController: Finished executing pending operations"
            android.util.Log.v(r9, r1)     // Catch:{ all -> 0x0048 }
        L_0x0151:
            monitor-exit(r0)
            return
        L_0x0153:
            monitor-exit(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.SpecialEffectsController.executePendingOperations():void");
    }

    public final void forceCompleteAllOperations() {
        String str;
        String str2;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
        }
        boolean isAttachedToWindow = this.container.isAttachedToWindow();
        synchronized (this.pendingOperations) {
            try {
                updateFinalState();
                processStart(this.pendingOperations);
                ArrayList m12 = C1194l.m1(this.runningOperations);
                Iterator it = m12.iterator();
                while (it.hasNext()) {
                    ((Operation) it.next()).setSeeking$fragment_release(false);
                }
                Iterator it2 = m12.iterator();
                while (it2.hasNext()) {
                    Operation operation = (Operation) it2.next();
                    if (FragmentManager.isLoggingEnabled(2)) {
                        if (isAttachedToWindow) {
                            str2 = "";
                        } else {
                            str2 = "Container " + this.container + " is not attached to window. ";
                        }
                        Log.v("FragmentManager", "SpecialEffectsController: " + str2 + "Cancelling running operation " + operation);
                    }
                    operation.cancel(this.container);
                }
                ArrayList m13 = C1194l.m1(this.pendingOperations);
                Iterator it3 = m13.iterator();
                while (it3.hasNext()) {
                    ((Operation) it3.next()).setSeeking$fragment_release(false);
                }
                Iterator it4 = m13.iterator();
                while (it4.hasNext()) {
                    Operation operation2 = (Operation) it4.next();
                    if (FragmentManager.isLoggingEnabled(2)) {
                        if (isAttachedToWindow) {
                            str = "";
                        } else {
                            str = "Container " + this.container + " is not attached to window. ";
                        }
                        Log.v("FragmentManager", "SpecialEffectsController: " + str + "Cancelling pending operation " + operation2);
                    }
                    operation2.cancel(this.container);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void forcePostponedExecutePendingOperations() {
        if (this.isContainerPostponed) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Forcing postponed operations");
            }
            this.isContainerPostponed = false;
            executePendingOperations();
        }
    }

    public final Operation.LifecycleImpact getAwaitingCompletionLifecycleImpact(FragmentStateManager fragmentStateManager) {
        Operation.LifecycleImpact lifecycleImpact;
        int i2;
        j.e(fragmentStateManager, "fragmentStateManager");
        Fragment fragment = fragmentStateManager.getFragment();
        j.d(fragment, "fragmentStateManager.fragment");
        Operation findPendingOperation = findPendingOperation(fragment);
        Operation.LifecycleImpact lifecycleImpact2 = null;
        if (findPendingOperation != null) {
            lifecycleImpact = findPendingOperation.getLifecycleImpact();
        } else {
            lifecycleImpact = null;
        }
        Operation findRunningOperation = findRunningOperation(fragment);
        if (findRunningOperation != null) {
            lifecycleImpact2 = findRunningOperation.getLifecycleImpact();
        }
        if (lifecycleImpact == null) {
            i2 = -1;
        } else {
            i2 = WhenMappings.$EnumSwitchMapping$0[lifecycleImpact.ordinal()];
        }
        if (i2 == -1 || i2 == 1) {
            return lifecycleImpact2;
        }
        return lifecycleImpact;
    }

    public final ViewGroup getContainer() {
        return this.container;
    }

    public final boolean isPendingExecute() {
        return !this.pendingOperations.isEmpty();
    }

    public final void markPostponedState() {
        Fragment fragment;
        Operation operation;
        boolean z;
        synchronized (this.pendingOperations) {
            try {
                updateFinalState();
                List<Operation> list = this.pendingOperations;
                ListIterator<Operation> listIterator = list.listIterator(list.size());
                while (true) {
                    fragment = null;
                    if (!listIterator.hasPrevious()) {
                        operation = null;
                        break;
                    }
                    operation = listIterator.previous();
                    Operation operation2 = operation;
                    Operation.State.Companion companion = Operation.State.Companion;
                    View view = operation2.getFragment().mView;
                    j.d(view, "operation.fragment.mView");
                    Operation.State asOperationState = companion.asOperationState(view);
                    Operation.State finalState = operation2.getFinalState();
                    Operation.State state = Operation.State.VISIBLE;
                    if (finalState == state && asOperationState != state) {
                        break;
                    }
                }
                Operation operation3 = operation;
                if (operation3 != null) {
                    fragment = operation3.getFragment();
                }
                if (fragment != null) {
                    z = fragment.isPostponed();
                } else {
                    z = false;
                }
                this.isContainerPostponed = z;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void processProgress(BackEventCompat backEventCompat) {
        j.e(backEventCompat, "backEvent");
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Processing Progress " + backEventCompat.getProgress());
        }
        ArrayList arrayList = new ArrayList();
        for (Operation effects$fragment_release : this.runningOperations) {
            C1200r.A0(effects$fragment_release.getEffects$fragment_release(), arrayList);
        }
        List k12 = C1194l.k1(C1194l.p1(arrayList));
        int size = k12.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((Effect) k12.get(i2)).onProgress(backEventCompat, this.container);
        }
    }

    public final void updateOperationDirection(boolean z) {
        this.operationDirectionIsPop = z;
    }

    public static final SpecialEffectsController getOrCreateController(ViewGroup viewGroup, SpecialEffectsControllerFactory specialEffectsControllerFactory) {
        return Companion.getOrCreateController(viewGroup, specialEffectsControllerFactory);
    }
}
