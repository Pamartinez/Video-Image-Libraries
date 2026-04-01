package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

import Ad.C0720a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.aiedit.RemasterDetector;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AiEditItemFactory {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AiEditImageItemList {
        static final ArrayList<AiEditItemConstructor> list = new ArrayList<AiEditItemConstructor>() {
            {
                if (!RemasterDetector.SUPPORT_UNIFIED && PreferenceFeatures.OneUi41.SUPPORT_PORTRAIT_CHANGE && (SdkConfig.lessThan(SdkConfig.SEM.B_MR5) || Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES))) {
                    add(new b(0));
                }
                if (LongExposureAiEdit.SUPPORT) {
                    add(new b(1));
                }
                if (SdkConfig.lessThan(SdkConfig.SEM.B_MR5) || Features.isEnabled(Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES) || Features.isEnabled(Features.IS_JDM)) {
                    add(new b(2));
                }
                if (Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER) && !Features.isEnabled(Features.IS_REPAIR_MODE)) {
                    add(new b(3));
                }
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface AiEditItemConstructor extends BiFunction<EventContext, ActionInvoker, AiEditItem> {
        AiEditItem apply(EventContext eventContext, ActionInvoker actionInvoker);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AiEditVideoItemList {
        static final ArrayList<AiEditItemConstructor> list = new ArrayList<AiEditItemConstructor>() {
            {
                if (SdkConfig.lessThan(SdkConfig.SEM.B_MR5)) {
                    boolean z = PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL;
                    if (z) {
                        add(new b(4));
                    }
                    if (LongExposureAiEdit.SUPPORT) {
                        add(new b(1));
                    }
                    if (z && SdkConfig.lessThan(SdkConfig.SEM.V)) {
                        add(new b(5));
                    }
                    if (Features.isEnabled(Features.SUPPORT_COLOR_CORRECT)) {
                        add(new b(6));
                    }
                }
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AwesomeImageItemList {
        static final ArrayList<AiEditItemConstructor> list = new ArrayList<AiEditItemConstructor>() {
            {
                addAll(AiEditImageItemList.list);
                if (Features.isEnabled(Features.SUPPORT_OBJECT_ERASER)) {
                    add(new b(7));
                }
            }
        };
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SortingMapHolder {
        static final HashMap<Class<?>, Integer> map = new HashMap<Class<?>, Integer>() {
            /* JADX WARNING: Removed duplicated region for block: B:20:0x0078  */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x009c  */
            /* JADX WARNING: Removed duplicated region for block: B:26:0x00c0  */
            {
                /*
                    r8 = this;
                    r8.<init>()
                    boolean r0 = com.samsung.android.gallery.module.aiedit.RemasterDetector.SUPPORT_BEST_FACE
                    r1 = 0
                    if (r0 == 0) goto L_0x0012
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterBestFaceAiEdit> r0 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterBestFaceAiEdit.class
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    r8.put(r0, r1)
                    r1 = 1
                L_0x0012:
                    boolean r0 = com.samsung.android.gallery.module.aiedit.RemasterDetector.SUPPORT_REMASTER_DETECT
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterGifAiEdit> r2 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterGifAiEdit.class
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterAiEdit> r3 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterAiEdit.class
                    if (r0 == 0) goto L_0x002c
                    int r4 = r1 + 1
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
                    r8.put(r3, r5)
                    int r1 = r1 + 2
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                    r8.put(r2, r4)
                L_0x002c:
                    boolean r4 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.LiveEffectAiEdit.SUPPORT
                    if (r4 == 0) goto L_0x003c
                    int r4 = r1 + 1
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.LiveEffectAiEdit> r5 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.LiveEffectAiEdit.class
                    r8.put(r5, r1)
                    r1 = r4
                L_0x003c:
                    boolean r4 = com.samsung.android.gallery.module.aiedit.RemasterDetector.SUPPORT_UNIFIED
                    if (r4 == 0) goto L_0x004d
                    int r4 = r1 + 1
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterPortraitAiEdit> r5 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterPortraitAiEdit.class
                    r8.put(r5, r1)
                L_0x004b:
                    r1 = r4
                    goto L_0x0069
                L_0x004d:
                    com.samsung.android.gallery.support.config.SdkConfig$SEM r4 = com.samsung.android.gallery.support.config.SdkConfig.SEM.B_MR5
                    boolean r4 = com.samsung.android.gallery.support.config.SdkConfig.lessThan((com.samsung.android.gallery.support.config.SdkConfig.SEM) r4)
                    if (r4 != 0) goto L_0x005d
                    com.samsung.android.gallery.support.utils.Features r4 = com.samsung.android.gallery.support.utils.Features.SUPPORT_AWESOME_INTELLIGENT_FEATURES
                    boolean r4 = com.samsung.android.gallery.support.utils.Features.isEnabled(r4)
                    if (r4 == 0) goto L_0x0069
                L_0x005d:
                    int r4 = r1 + 1
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.PortraitEffectAiEdit> r5 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.PortraitEffectAiEdit.class
                    r8.put(r5, r1)
                    goto L_0x004b
                L_0x0069:
                    int r4 = r1 + 1
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterMoireAiEdit> r6 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterMoireAiEdit.class
                    r8.put(r6, r5)
                    boolean r5 = com.samsung.android.gallery.module.aiedit.RemasterDetector.SUPPORT_FLARE_DISTORTION
                    if (r5 == 0) goto L_0x0084
                    int r1 = r1 + 2
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterFlareAiEdit> r6 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterFlareAiEdit.class
                    r8.put(r6, r4)
                    r4 = r1
                L_0x0084:
                    int r1 = r4 + 1
                    java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterShadowAiEdit> r7 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterShadowAiEdit.class
                    r8.put(r7, r6)
                    int r6 = r4 + 2
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterReflectionAiEdit> r7 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterReflectionAiEdit.class
                    r8.put(r7, r1)
                    if (r5 == 0) goto L_0x00a8
                    int r4 = r4 + 3
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterDistortionAiEdit> r5 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.RemasterDistortionAiEdit.class
                    r8.put(r5, r1)
                    r6 = r4
                L_0x00a8:
                    int r1 = r6 + 1
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.LongExposureAiEdit> r5 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.LongExposureAiEdit.class
                    r8.put(r5, r4)
                    int r4 = r6 + 2
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.BackgroundEffectAiEdit> r5 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.BackgroundEffectAiEdit.class
                    r8.put(r5, r1)
                    if (r0 != 0) goto L_0x00d2
                    int r0 = r6 + 3
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
                    r8.put(r3, r1)
                    int r4 = r6 + 4
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    r8.put(r2, r0)
                L_0x00d2:
                    java.lang.Class<com.samsung.android.gallery.app.ui.viewer2.aiedit.items.ObjectEraserAiEdit> r0 = com.samsung.android.gallery.app.ui.viewer2.aiedit.items.ObjectEraserAiEdit.class
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
                    r8.put(r0, r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.aiedit.items.AiEditItemFactory.SortingMapHolder.AnonymousClass1.<init>():void");
            }
        };
    }

    public static ArrayList<AiEditItem> createAwesomeList(EventContext eventContext, ActionInvoker actionInvoker, boolean z) {
        ArrayList<AiEditItemConstructor> arrayList;
        ThreadUtil.assertBgThread("AiEditListFactory");
        if (z) {
            arrayList = AwesomeImageItemList.list;
        } else {
            arrayList = AiEditVideoItemList.list;
        }
        return (ArrayList) arrayList.stream().map(new a(eventContext, actionInvoker, 1)).collect(Collectors.toCollection(new C0720a(1)));
    }

    public static ArrayList<AiEditItem> createList(EventContext eventContext, ActionInvoker actionInvoker, boolean z) {
        ArrayList<AiEditItemConstructor> arrayList;
        ThreadUtil.assertBgThread("AiEditListFactory");
        if (z) {
            arrayList = AiEditImageItemList.list;
        } else {
            arrayList = AiEditVideoItemList.list;
        }
        return (ArrayList) arrayList.stream().map(new a(eventContext, actionInvoker, 0)).collect(Collectors.toCollection(new C0720a(1)));
    }

    public static int getSortingOrder(Class<?> cls) {
        return ((Integer) Optional.ofNullable(SortingMapHolder.map.get(cls)).orElse(-1)).intValue();
    }
}
