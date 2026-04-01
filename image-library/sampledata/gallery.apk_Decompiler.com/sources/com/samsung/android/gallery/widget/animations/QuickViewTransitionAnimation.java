package com.samsung.android.gallery.widget.animations;

import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sum.core.descriptor.b;
import e5.C0451a;
import e6.C0453a;
import eb.C0687a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuickViewTransitionAnimation {
    public static int RETURN_DELAY = 400;
    private final Blackboard mBlackboard;
    private final boolean mIsBlackBg;
    private Runnable mOnCancelListener;
    private Runnable mOnConvertListener;
    private Runnable mOnViewReadyListener;
    private SimpleShrinkView mShrinkView;
    private final QuickViewShrinkType mType;

    /* renamed from: com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$widget$animations$QuickViewTransitionAnimation$QuickViewShrinkType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation$QuickViewShrinkType[] r0 = com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation.QuickViewShrinkType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$widget$animations$QuickViewTransitionAnimation$QuickViewShrinkType = r0
                com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation$QuickViewShrinkType r1 = com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation.QuickViewShrinkType.DRAG     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$animations$QuickViewTransitionAnimation$QuickViewShrinkType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation$QuickViewShrinkType r1 = com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation.QuickViewShrinkType.PINCH     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.animations.QuickViewTransitionAnimation.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum QuickViewShrinkType {
        BACK,
        DRAG,
        PINCH
    }

    public QuickViewTransitionAnimation(Blackboard blackboard, QuickViewShrinkType quickViewShrinkType, boolean z) {
        this.mBlackboard = blackboard;
        this.mType = quickViewShrinkType;
        this.mIsBlackBg = z;
    }

    /* access modifiers changed from: private */
    public void onConverted(boolean z) {
        Runnable runnable = this.mOnConvertListener;
        if (runnable != null) {
            runnable.run();
        }
        this.mShrinkView = new SimpleShrinkView(this.mBlackboard).withReadyAction(new C0451a(7, this)).show(this.mIsBlackBg);
    }

    /* access modifiers changed from: private */
    public void onShrinkDone(boolean z) {
        if (z) {
            Optional.ofNullable(BlackboardUtils.readActivity(this.mBlackboard)).ifPresent(new C0453a(5));
            this.mBlackboard.post("command://request_suicide", (Object) null);
            return;
        }
        Runnable runnable = this.mOnCancelListener;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public void onShrinkViewReady() {
        Runnable runnable = this.mOnViewReadyListener;
        if (runnable != null) {
            runnable.run();
        }
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$animations$QuickViewTransitionAnimation$QuickViewShrinkType[this.mType.ordinal()];
        if (i2 == 1) {
            new QuickViewDragShrinkHandler(this.mShrinkView, (ListViewHolder) null).withFinishAction(new b(20, this)).show();
        } else if (i2 != 2) {
            new QuickViewBackShrinkHandler(this.mShrinkView, (ListViewHolder) null).withFinishAction(new b(20, this)).show();
        } else {
            new QuickViewNewPinchShrinkHandler(this.mShrinkView, (ListViewHolder) null).withFinishAction(new b(20, this)).show();
        }
    }

    public QuickViewTransitionAnimation setConvertListener(Runnable runnable) {
        this.mOnConvertListener = runnable;
        return this;
    }

    public QuickViewTransitionAnimation setOnCancelListener(Runnable runnable) {
        this.mOnCancelListener = runnable;
        return this;
    }

    public QuickViewTransitionAnimation setViewReadyListener(Runnable runnable) {
        this.mOnViewReadyListener = runnable;
        return this;
    }

    public void start() {
        SeApiCompat.convertActivityToTranslucent(BlackboardUtils.readActivity(this.mBlackboard), new C0687a(this));
    }
}
