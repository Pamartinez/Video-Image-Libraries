package androidx.fragment.app;

import kotlin.Metadata;
import kotlin.jvm.internal.e;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0001\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB/\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0014\u0010\u0006\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Landroidx/fragment/app/SeslFragmentTransactionAnimationSet;", "", "enter", "", "exit", "popEnter", "popExit", "(Ljava/lang/String;IIIII)V", "getEnter$fragment_release", "()I", "getExit$fragment_release", "getPopEnter$fragment_release", "getPopExit$fragment_release", "Horizontal", "HorizontalForRTL", "Companion", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SeslFragmentTransactionAnimationSet {
    Horizontal(r3, r4, r5, r6),
    HorizontalForRTL(r3, r4, r6, r7);
    
    public static final Companion Companion = null;
    private final int enter;
    private final int exit;
    private final int popEnter;
    private final int popExit;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/fragment/app/SeslFragmentTransactionAnimationSet$Companion;", "", "<init>", "()V", "", "resId", "", "isFragmentAnimationRes", "(I)Z", "isOpenEnter", "isOpenExit", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final boolean isFragmentAnimationRes(int i2) {
            for (SeslFragmentTransactionAnimationSet seslFragmentTransactionAnimationSet : SeslFragmentTransactionAnimationSet.values()) {
                if (seslFragmentTransactionAnimationSet.getEnter$fragment_release() == i2 || seslFragmentTransactionAnimationSet.getExit$fragment_release() == i2 || seslFragmentTransactionAnimationSet.getPopEnter$fragment_release() == i2 || seslFragmentTransactionAnimationSet.getPopExit$fragment_release() == i2) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isOpenEnter(int i2) {
            for (SeslFragmentTransactionAnimationSet enter$fragment_release : SeslFragmentTransactionAnimationSet.values()) {
                if (enter$fragment_release.getEnter$fragment_release() == i2) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isOpenExit(int i2) {
            for (SeslFragmentTransactionAnimationSet exit$fragment_release : SeslFragmentTransactionAnimationSet.values()) {
                if (exit$fragment_release.getExit$fragment_release() == i2) {
                    return true;
                }
            }
            return false;
        }

        private Companion() {
        }
    }

    static {
        Companion = new Companion((e) null);
    }

    private SeslFragmentTransactionAnimationSet(int i2, int i7, int i8, int i10) {
        this.enter = i2;
        this.exit = i7;
        this.popEnter = i8;
        this.popExit = i10;
    }

    public final int getEnter$fragment_release() {
        return this.enter;
    }

    public final int getExit$fragment_release() {
        return this.exit;
    }

    public final int getPopEnter$fragment_release() {
        return this.popEnter;
    }

    public final int getPopExit$fragment_release() {
        return this.popExit;
    }
}
