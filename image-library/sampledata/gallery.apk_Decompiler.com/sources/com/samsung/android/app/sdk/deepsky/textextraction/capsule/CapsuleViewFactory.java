package com.samsung.android.app.sdk.deepsky.textextraction.capsule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CompositeCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.SimpleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ToggleCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.CapsuleColor;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.CompositeCapsuleLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.SimpleCapsuleLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ToggleCapsuleLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 %2\u00020\u0001:\u0001%B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleViewFactory;", "", "Landroid/content/Context;", "context", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "capsuleColor", "<init>", "(Landroid/content/Context;Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;", "capsule", "Landroid/view/View;", "createSimpleCapsuleView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/SimpleCapsule;)Landroid/view/View;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;", "createToggleCapsuleView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ToggleCapsule;)Landroid/view/View;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;", "createExpandableCapsuleView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/ExpandableCapsule;)Landroid/view/View;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/CompositeCapsule;", "createCompositeCapsuleView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/CompositeCapsule;)Landroid/view/View;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "capsuleModel", "createView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;)Landroid/view/View;", "Landroid/content/Context;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "getCapsuleColor", "()Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;", "setCapsuleColor", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/ui/CapsuleColor;)V", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/LayoutInflater;", "getInflater", "()Landroid/view/LayoutInflater;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleViewFactory {
    public static final Companion Companion = new Companion((e) null);
    private CapsuleColor capsuleColor;
    private final Context context;
    private final LayoutInflater inflater;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleViewFactory$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public CapsuleViewFactory(Context context2, CapsuleColor capsuleColor2) {
        j.e(context2, "context");
        j.e(capsuleColor2, "capsuleColor");
        this.context = context2;
        this.capsuleColor = capsuleColor2;
        LayoutInflater from = LayoutInflater.from(context2);
        j.d(from, "from(...)");
        this.inflater = from;
    }

    private final View createCompositeCapsuleView(CompositeCapsule compositeCapsule) {
        CompositeCapsuleLayout compositeCapsuleLayout = new CompositeCapsuleLayout(this.context, (AttributeSet) null, 0, 6, (e) null);
        compositeCapsuleLayout.bind(this.capsuleColor);
        for (Capsule createView : compositeCapsule.getChildren()) {
            compositeCapsuleLayout.addCapsuleView(createView(createView));
        }
        compositeCapsuleLayout.ignoreMarginOfLastAddedView();
        return compositeCapsuleLayout;
    }

    private final View createExpandableCapsuleView(ExpandableCapsule expandableCapsule) {
        ExpandableCapsuleLayout expandableCapsuleLayout = new ExpandableCapsuleLayout(this.context, (AttributeSet) null, 0, 6, (e) null);
        expandableCapsuleLayout.bind(expandableCapsule, this.capsuleColor);
        return expandableCapsuleLayout;
    }

    private final View createSimpleCapsuleView(SimpleCapsule simpleCapsule) {
        SimpleCapsuleLayout simpleCapsuleLayout = new SimpleCapsuleLayout(this.context, (AttributeSet) null, 0, 6, (e) null);
        simpleCapsuleLayout.bind(simpleCapsule, this.capsuleColor);
        return simpleCapsuleLayout;
    }

    private final View createToggleCapsuleView(ToggleCapsule toggleCapsule) {
        ToggleCapsuleLayout toggleCapsuleLayout = new ToggleCapsuleLayout(this.context, (AttributeSet) null, 0, 6, (e) null);
        toggleCapsuleLayout.bind(toggleCapsule, this.capsuleColor);
        return toggleCapsuleLayout;
    }

    public final View createView(Capsule capsule) {
        j.e(capsule, "capsuleModel");
        if (capsule instanceof SimpleCapsule) {
            return createSimpleCapsuleView((SimpleCapsule) capsule);
        }
        if (capsule instanceof ToggleCapsule) {
            return createToggleCapsuleView((ToggleCapsule) capsule);
        }
        if (capsule instanceof ExpandableCapsule) {
            return createExpandableCapsuleView((ExpandableCapsule) capsule);
        }
        if (capsule instanceof CompositeCapsule) {
            return createCompositeCapsuleView((CompositeCapsule) capsule);
        }
        throw new IllegalArgumentException("inflating unsupported capsule type!");
    }

    public final void setCapsuleColor(CapsuleColor capsuleColor2) {
        j.e(capsuleColor2, "<set-?>");
        this.capsuleColor = capsuleColor2;
    }
}
