package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import Ae.b;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import com.samsung.android.sesl.visualeffect.R$drawable;
import com.samsung.android.sum.core.message.Message;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.y;
import ne.z;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\b\b\u0007\u0018\u0000 -2\u00020\u0001:\u0003-./B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ\u001b\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e0\nH\u0002¢\u0006\u0004\b\u000f\u0010\rJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007¢\u0006\u0004\b\u0014\u0010\u0015J'\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000e2\b\b\u0002\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0010¢\u0006\u0004\b\u001e\u0010\u0012R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R&\u0010$\u001a\u0012\u0012\u0004\u0012\u00020\"0!j\b\u0012\u0004\u0012\u00020\"`#8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010&\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\"\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000e0(8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\"\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0(8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b+\u0010*R\"\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070(8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b,\u0010*¨\u00060"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor;", "", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "initialState", "<init>", "(Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;)V", "", "", "getColorsInRenderOrder", "()Ljava/util/List;", "", "Landroid/graphics/PointF;", "getPositionsInRenderOrder", "()Ljava/util/Map;", "", "getScalesInRenderOrder", "Lme/x;", "resetSpotConfigs", "()V", "spotIndex", "getSpotColor", "(I)I", "Landroid/content/Context;", "appContext", "fps", "", "wiggleInterval", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradControl;", "build", "(Landroid/content/Context;FJ)Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradControl;", "destroy", "control", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradControl;", "Ljava/util/ArrayList;", "Landroid/animation/ValueAnimator;", "Lkotlin/collections/ArrayList;", "colorAnims", "Ljava/util/ArrayList;", "state", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "", "currentSpotScales", "Ljava/util/Map;", "currentSpotPositions", "currentSpotColors", "Companion", "SpotState", "State", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PresetEffectColor {
    public static final Companion Companion = new Companion((e) null);
    private static Bitmap radialMap;
    private ArrayList<ValueAnimator> colorAnims = new ArrayList<>();
    private RadialGradControl control;
    private Map<Integer, Integer> currentSpotColors;
    private Map<Integer, PointF> currentSpotPositions;
    private Map<Integer, Float> currentSpotScales;
    private State state;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$Companion;", "", "<init>", "()V", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u000eR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$SpotState;", "", "", "color", "", "scale", "Landroid/graphics/PointF;", "position", "<init>", "(IFLandroid/graphics/PointF;)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getColor", "F", "getScale", "()F", "Landroid/graphics/PointF;", "getPosition", "()Landroid/graphics/PointF;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SpotState {
        private final int color;
        private final PointF position;
        private final float scale;

        public SpotState(int i2, float f, PointF pointF) {
            j.e(pointF, Message.KEY_POSITION);
            this.color = i2;
            this.scale = f;
            this.position = pointF;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpotState)) {
                return false;
            }
            SpotState spotState = (SpotState) obj;
            if (this.color == spotState.color && Float.compare(this.scale, spotState.scale) == 0 && j.a(this.position, spotState.position)) {
                return true;
            }
            return false;
        }

        public final int getColor() {
            return this.color;
        }

        public final PointF getPosition() {
            return this.position;
        }

        public final float getScale() {
            return this.scale;
        }

        public int hashCode() {
            return this.position.hashCode() + N2.j.a(this.scale, Integer.hashCode(this.color) * 31, 31);
        }

        public String toString() {
            int i2 = this.color;
            float f = this.scale;
            PointF pointF = this.position;
            return "SpotState(color=" + i2 + ", scale=" + f + ", position=" + pointF + ")";
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$State;", "", "", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/PresetEffectColor$SpotState;", "spots", "<init>", "(Ljava/lang/String;ILjava/util/List;)V", "", "toString", "()Ljava/lang/String;", "Ljava/util/List;", "getSpots", "()Ljava/util/List;", "Common", "Input", "Processing", "Mono", "Button", "Action", "Result", "Processing85", "Nudge", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum State {
        Common(C1195m.q0(new SpotState(r3, 2.5f, r4.getPOS_SPOT1()), new SpotState(r2.getCOLOR_LIGHT_BLUE(), 2.25f, r4.getPOS_SPOT2()), new SpotState(r2.getCOLOR_BLUE(), 0.95f, r4.getPOS_SPOT3()), new SpotState(r2.getCOLOR_GREEN(), 2.25f, r4.getPOS_SPOT4()))),
        Input(C1195m.q0(new SpotState(r2.getCOLOR_BLUE(), 2.5f, r4.getPOS_SPOT1()), new SpotState(r2.getCOLOR_LIGHT_BLUE(), 2.25f, r4.getPOS_SPOT2()), new SpotState(r2.getCOLOR_PURPLE(), 0.95f, r4.getPOS_SPOT3()), new SpotState(r2.getCOLOR_GREEN(), 2.25f, r4.getPOS_SPOT4()))),
        Processing(C1195m.q0(new SpotState(r2.getCOLOR_BLUE(), 2.5f, r4.getPOS_SPOT1()), new SpotState(r2.getCOLOR_LIGHT_BLUE(), 2.25f, r4.getPOS_SPOT2()), new SpotState(r2.getCOLOR_LIGHT_BLUE(), 0.95f, r4.getPOS_SPOT3()), new SpotState(r2.getCOLOR_YELLOW(), 2.25f, r4.getPOS_SPOT4()))),
        Mono(C1195m.q0(new SpotState(r2.getCOLOR_WHITE(), 2.5f, r4.getPOS_SPOT1()), new SpotState(r2.getCOLOR_WHITE(), 2.25f, r4.getPOS_SPOT2()), new SpotState(r2.getCOLOR_WHITE(), 0.95f, r4.getPOS_SPOT3()), new SpotState(r2.getCOLOR_WHITE(), 2.25f, r4.getPOS_SPOT4()))),
        Button(C1195m.q0(new SpotState(Color.parseColor("#C2B2FF"), 2.71f, new PointF(0.08f, 0.6f)), new SpotState(Color.parseColor("#8BE6CA"), 2.3f, new PointF(1.29f, -0.02f)), new SpotState(Color.parseColor("#8BBDFF"), 1.75f, new PointF(0.49f, 0.01f)), new SpotState(Color.parseColor("#F0F488"), 1.86f, new PointF(1.26f, 1.2f)))),
        Action(C1195m.q0(new SpotState(Color.parseColor("#C2B2FF"), 3.7f, new PointF(0.5f, 0.8f)), new SpotState(Color.parseColor("#88E6E3"), 3.31f, new PointF(1.34f, -0.22f)), new SpotState(Color.parseColor("#8BBDFF"), 2.29f, new PointF(0.1f, -0.14f)), new SpotState(Color.parseColor("#F0F488"), 2.4f, new PointF(1.44f, 0.62f)))),
        Result(C1195m.q0(new SpotState(Color.parseColor("#C2B2FF"), 3.7f, new PointF(0.5f, 0.45f)), new SpotState(Color.parseColor("#BF88E6E3"), 3.15f, new PointF(1.34f, -0.52f)), new SpotState(Color.parseColor("#BF8BBDFF"), 2.24f, new PointF(0.1f, -0.34f)), new SpotState(Color.parseColor("#F0F488"), 2.7f, new PointF(1.54f, 0.72f)))),
        Processing85(C1195m.q0(new SpotState(r2.getCOLOR_LAVENDER_PURPLE(), 3.0f, r4.getPROCESSING_POS_SPOT1()), new SpotState(r2.getCOLOR_LIGHT_BLUE(), 3.31f, r4.getPROCESSING_POS_SPOT2()), new SpotState(r2.getCOLOR_SKY_BLUE(), 2.2f, r4.getPROCESSING_POS_SPOT3()), new SpotState(r2.getCOLOR_SOFT_YELLOW(), 1.7f, r4.getPROCESSING_POS_SPOT4()))),
        Nudge(C1195m.q0(new SpotState(Color.parseColor("#C2B2FF"), 1.87f, new PointF(0.08f, 0.62f)), new SpotState(Color.parseColor("#8BBDFF"), 0.0f, new PointF(-1.0f, -1.0f)), new SpotState(Color.parseColor("#8BBDFF"), 1.48f, new PointF(0.55f, 0.65f)), new SpotState(Color.parseColor("#88E6E3"), 1.32f, new PointF(1.17f, 0.49f))));
        
        private final List<SpotState> spots;

        static {
            State[] $values;
            $ENTRIES = c.t($values);
        }

        private State(List<SpotState> list) {
            this.spots = list;
        }

        public final List<SpotState> getSpots() {
            return this.spots;
        }

        public String toString() {
            Iterable iterable = this.spots;
            ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
            int i2 = 0;
            for (Object next : iterable) {
                int i7 = i2 + 1;
                if (i2 >= 0) {
                    SpotState spotState = (SpotState) next;
                    String format = String.format("#%08X", Arrays.copyOf(new Object[]{Integer.valueOf(spotState.getColor())}, 1));
                    float scale = spotState.getScale();
                    float f = spotState.getPosition().x;
                    float f5 = spotState.getPosition().y;
                    StringBuilder sb2 = new StringBuilder("Spot");
                    sb2.append(i2);
                    sb2.append("[Color=");
                    sb2.append(format);
                    sb2.append(", Scale=");
                    C0086a.y(sb2, scale, ", Position=(", f, ArcCommonLog.TAG_COMMA);
                    sb2.append(f5);
                    sb2.append(")]");
                    arrayList.add(sb2.toString());
                    i2 = i7;
                } else {
                    C1195m.v0();
                    throw null;
                }
            }
            String R02 = C1194l.R0(arrayList, ArcCommonLog.TAG_COMMA, (String) null, (String) null, (b) null, 62);
            String name = name();
            return name + "(" + R02 + ")";
        }
    }

    public PresetEffectColor(State state2) {
        j.e(state2, "initialState");
        this.state = state2;
        this.currentSpotScales = new LinkedHashMap();
        this.currentSpotPositions = new LinkedHashMap();
        this.currentSpotColors = new LinkedHashMap();
        resetSpotConfigs();
    }

    private final List<Integer> getColorsInRenderOrder() {
        Ge.c cVar = new Ge.c(0, 3, 1);
        ArrayList arrayList = new ArrayList(C1196n.w0(cVar, 10));
        Iterator it = cVar.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(getSpotColor(((y) it).nextInt())));
        }
        return SpotIndexManager.INSTANCE.convertToRenderOrder(arrayList);
    }

    private final Map<Integer, PointF> getPositionsInRenderOrder() {
        Ge.c cVar = new Ge.c(0, 3, 1);
        int Z = z.Z(C1196n.w0(cVar, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        for (Object next : cVar) {
            int intValue = ((Number) next).intValue();
            PointF pointF = this.currentSpotPositions.get(Integer.valueOf(intValue));
            if (pointF == null) {
                pointF = PresetEffectColorKt.copy(this.state.getSpots().get(intValue).getPosition());
            }
            linkedHashMap.put(next, pointF);
        }
        return SpotIndexManager.INSTANCE.convertToRenderOrderMap(linkedHashMap);
    }

    private final Map<Integer, Float> getScalesInRenderOrder() {
        float f;
        Ge.c cVar = new Ge.c(0, 3, 1);
        int Z = z.Z(C1196n.w0(cVar, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        for (Object next : cVar) {
            int intValue = ((Number) next).intValue();
            Float f5 = this.currentSpotScales.get(Integer.valueOf(intValue));
            if (f5 != null) {
                f = f5.floatValue();
            } else {
                f = this.state.getSpots().get(intValue).getScale();
            }
            linkedHashMap.put(next, Float.valueOf(f));
        }
        return SpotIndexManager.INSTANCE.convertToRenderOrderMap(linkedHashMap);
    }

    public final RadialGradControl build(Context context, float f, long j2) {
        j.e(context, StateHandler.KEY_APP_STATE);
        if (radialMap == null) {
            radialMap = BitmapFactory.decodeResource(context.getResources(), R$drawable.lightmap);
        }
        Bitmap bitmap = radialMap;
        Constants constants = Constants.INSTANCE;
        RadialGradControl radialGradControl = new RadialGradControl(new RadialGradConfig(constants.getCOLOR_BACKGROUND(), bitmap, constants.getBUFFER_SIZE(), 0, 0.0f, AnimationHelper.INSTANCE.buildSpotConfigs(getColorsInRenderOrder(), f, j2, getPositionsInRenderOrder(), getScalesInRenderOrder()), 24, (e) null));
        this.control = radialGradControl;
        radialGradControl.getAnimators$sesl_visualeffect_INTERNAL_2_1_6_release().addAll(this.colorAnims);
        return radialGradControl;
    }

    public final void destroy() {
        for (ValueAnimator cancel : this.colorAnims) {
            cancel.cancel();
        }
        this.colorAnims.clear();
        Bitmap bitmap = radialMap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        radialMap = null;
    }

    public final int getSpotColor(int i2) {
        if (i2 < 0 || i2 >= 4) {
            throw new IllegalArgumentException("Spot index must be between 0 and 3");
        }
        Integer num = this.currentSpotColors.get(Integer.valueOf(i2));
        if (num != null) {
            return num.intValue();
        }
        return this.state.getSpots().get(i2).getColor();
    }

    public final void resetSpotConfigs() {
        int i2 = 0;
        for (Object next : this.state.getSpots()) {
            int i7 = i2 + 1;
            if (i2 >= 0) {
                SpotState spotState = (SpotState) next;
                this.currentSpotScales.put(Integer.valueOf(i2), Float.valueOf(spotState.getScale()));
                this.currentSpotPositions.put(Integer.valueOf(i2), PresetEffectColorKt.copy(spotState.getPosition()));
                this.currentSpotColors.put(Integer.valueOf(i2), Integer.valueOf(spotState.getColor()));
                i2 = i7;
            } else {
                C1195m.v0();
                throw null;
            }
        }
    }
}
