package com.samsung.android.app.sdk.deepsky.textextraction.capsule;

import android.view.View;
import android.widget.LinearLayout;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleUpdateListener;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.Capsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.CompositeCapsule;
import com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.CompositeCapsuleLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J'\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J#\u0010\u0015\u001a\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u00132\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0017\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0018R \u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\b0\u00198\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001c¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleRenderer;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleUpdateListener;", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleViewFactory;", "viewFactory", "<init>", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleViewFactory;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;", "capsule", "Landroid/view/View;", "capsuleView", "Landroid/widget/LinearLayout;", "parentLayout", "Lme/x;", "addChildView", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/types/Capsule;Landroid/view/View;Landroid/widget/LinearLayout;)V", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;", "event", "updateViews", "(Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/model/event/CapsuleEvent;)V", "", "capsules", "drawCapsules", "(Ljava/util/List;Landroid/widget/LinearLayout;)V", "onCapsuleUpdate", "Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleViewFactory;", "", "", "renderedViews", "Ljava/util/Map;", "Companion", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CapsuleRenderer implements CapsuleUpdateListener {
    public static final Companion Companion = new Companion((e) null);
    private final Map<String, View> renderedViews = new LinkedHashMap();
    private final CapsuleViewFactory viewFactory;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/capsule/CapsuleRenderer$Companion;", "", "<init>", "()V", "TAG", "", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public CapsuleRenderer(CapsuleViewFactory capsuleViewFactory) {
        j.e(capsuleViewFactory, "viewFactory");
        this.viewFactory = capsuleViewFactory;
    }

    private final void addChildView(Capsule capsule, View view, LinearLayout linearLayout) {
        if (capsule instanceof CompositeCapsule) {
            List<Capsule> children = ((CompositeCapsule) capsule).getChildren();
            j.c(view, "null cannot be cast to non-null type com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.CompositeCapsuleLayout");
            CompositeCapsuleLayout compositeCapsuleLayout = (CompositeCapsuleLayout) view;
            int i2 = 0;
            for (Object next : children) {
                int i7 = i2 + 1;
                if (i2 >= 0) {
                    this.renderedViews.put(((Capsule) next).getId(), compositeCapsuleLayout.getContainerLayout().getChildAt(i2));
                    i2 = i7;
                } else {
                    C1195m.v0();
                    throw null;
                }
            }
        }
        linearLayout.addView(view);
        this.renderedViews.put(capsule.getId(), view);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ToggleCapsuleLayout} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updateViews(com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent.OnToggled
            r1 = 0
            if (r0 == 0) goto L_0x002a
            java.util.Map<java.lang.String, android.view.View> r2 = r2.renderedViews
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent$OnToggled r3 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent.OnToggled) r3
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ToggleCapsule r0 = r3.getCapsule()
            java.lang.String r0 = r0.getId()
            java.lang.Object r2 = r2.get(r0)
            android.view.View r2 = (android.view.View) r2
            if (r2 == 0) goto L_0x0053
            boolean r0 = r2 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ToggleCapsuleLayout
            if (r0 == 0) goto L_0x0020
            r1 = r2
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ToggleCapsuleLayout r1 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ToggleCapsuleLayout) r1
        L_0x0020:
            if (r1 == 0) goto L_0x0053
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ToggleState r2 = r3.getState()
            r1.handleToggleEvent(r2)
            return
        L_0x002a:
            boolean r0 = r3 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent.OnExpanded
            if (r0 == 0) goto L_0x0053
            java.util.Map<java.lang.String, android.view.View> r2 = r2.renderedViews
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent$OnExpanded r3 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent.OnExpanded) r3
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.types.ExpandableCapsule r0 = r3.getCapsule()
            java.lang.String r0 = r0.getId()
            java.lang.Object r2 = r2.get(r0)
            android.view.View r2 = (android.view.View) r2
            if (r2 == 0) goto L_0x0053
            boolean r0 = r2 instanceof com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout
            if (r0 == 0) goto L_0x0049
            r1 = r2
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout r1 = (com.samsung.android.app.sdk.deepsky.textextraction.capsule.ui.ExpandableCapsuleLayout) r1
        L_0x0049:
            if (r1 == 0) goto L_0x0053
            com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.state.ExpandState r2 = r3.getState()
            r3 = 0
            r1.handleExpandedEvent(r2, r3)
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.capsule.CapsuleRenderer.updateViews(com.samsung.android.app.sdk.deepsky.textextraction.capsule.model.event.CapsuleEvent):void");
    }

    public final void drawCapsules(List<? extends Capsule> list, LinearLayout linearLayout) {
        j.e(list, "capsules");
        j.e(linearLayout, "parentLayout");
        linearLayout.removeAllViews();
        this.renderedViews.clear();
        for (Capsule capsule : list) {
            addChildView(capsule, this.viewFactory.createView(capsule), linearLayout);
        }
    }

    public void onCapsuleUpdate(CapsuleEvent capsuleEvent) {
        j.e(capsuleEvent, "event");
        updateViews(capsuleEvent);
    }
}
