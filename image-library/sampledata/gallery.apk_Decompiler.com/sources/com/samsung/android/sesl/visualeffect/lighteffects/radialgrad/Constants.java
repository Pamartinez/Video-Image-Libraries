package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import android.graphics.Color;
import android.graphics.PointF;
import android.util.Size;
import android.view.animation.PathInterpolator;
import java.util.Map;
import kotlin.Metadata;
import me.i;
import ne.z;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010$\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001:\u00014B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\bR\u0017\u0010\u0013\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0006\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0016\u0010\bR\u0017\u0010\u0017\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\bR\u0017\u0010\u0019\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0006\u001a\u0004\b\u001a\u0010\bR\u001a\u0010\u001c\u001a\u00020\u001b8\u0006XD¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010!\u001a\u00020 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010&\u001a\u00020%8\u0006¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010*\u001a\u00020%8\u0006¢\u0006\f\n\u0004\b*\u0010'\u001a\u0004\b+\u0010)R\u0017\u0010,\u001a\u00020%8\u0006¢\u0006\f\n\u0004\b,\u0010'\u001a\u0004\b-\u0010)R,\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040.8\u0006X\u0004¢\u0006\u0012\n\u0004\b/\u00100\u0012\u0004\b3\u0010\u0003\u001a\u0004\b1\u00102¨\u00065"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/Constants;", "", "<init>", "()V", "", "COLOR_WHITE", "I", "getCOLOR_WHITE", "()I", "COLOR_PURPLE", "getCOLOR_PURPLE", "COLOR_BLUE", "getCOLOR_BLUE", "COLOR_LIGHT_BLUE", "getCOLOR_LIGHT_BLUE", "COLOR_GREEN", "getCOLOR_GREEN", "COLOR_YELLOW", "getCOLOR_YELLOW", "COLOR_BACKGROUND", "getCOLOR_BACKGROUND", "COLOR_LAVENDER_PURPLE", "getCOLOR_LAVENDER_PURPLE", "COLOR_SKY_BLUE", "getCOLOR_SKY_BLUE", "COLOR_SOFT_YELLOW", "getCOLOR_SOFT_YELLOW", "", "RESULT_ALPHA_RATIO", "F", "getRESULT_ALPHA_RATIO", "()F", "Landroid/util/Size;", "BUFFER_SIZE", "Landroid/util/Size;", "getBUFFER_SIZE", "()Landroid/util/Size;", "Landroid/view/animation/PathInterpolator;", "STATE_CHANGE_INTERPOLATOR", "Landroid/view/animation/PathInterpolator;", "getSTATE_CHANGE_INTERPOLATOR", "()Landroid/view/animation/PathInterpolator;", "STATE_CHANGE_INTERPOLATOR_PROCESSING", "getSTATE_CHANGE_INTERPOLATOR_PROCESSING", "CUBIC_BEZIER_INTERPOLATOR", "getCUBIC_BEZIER_INTERPOLATOR", "", "COLOR_TO_SPOT_IDX", "Ljava/util/Map;", "getCOLOR_TO_SPOT_IDX", "()Ljava/util/Map;", "getCOLOR_TO_SPOT_IDX$annotations", "SpotPositions", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Constants {
    private static final Size BUFFER_SIZE = new Size(200, 200);
    private static final int COLOR_BACKGROUND = Color.parseColor("#FFFFFFFF");
    private static final int COLOR_BLUE = Color.parseColor("#3E85FB");
    private static final int COLOR_GREEN = Color.parseColor("#BDFAA5");
    private static final int COLOR_LAVENDER_PURPLE = Color.parseColor("#C2B2FF");
    private static final int COLOR_LIGHT_BLUE = Color.parseColor("#88E6E3");
    private static final int COLOR_PURPLE = Color.parseColor("#8F74FF");
    private static final int COLOR_SKY_BLUE = Color.parseColor("#8BBDFF");
    private static final int COLOR_SOFT_YELLOW = Color.parseColor("#FFFFAC");
    private static final Map<Integer, Integer> COLOR_TO_SPOT_IDX = z.b0(new i(0, 0), new i(1, 3), new i(2, 1), new i(3, 2));
    private static final int COLOR_WHITE = Color.parseColor("#FFFFFF");
    private static final int COLOR_YELLOW = Color.parseColor("#FFE653");
    private static final PathInterpolator CUBIC_BEZIER_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    public static final Constants INSTANCE = new Constants();
    private static final float RESULT_ALPHA_RATIO = 0.8f;
    private static final PathInterpolator STATE_CHANGE_INTERPOLATOR = new PathInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    private static final PathInterpolator STATE_CHANGE_INTERPOLATOR_PROCESSING = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/Constants$SpotPositions;", "", "<init>", "()V", "POS_SPOT1", "Landroid/graphics/PointF;", "getPOS_SPOT1", "()Landroid/graphics/PointF;", "POS_SPOT2", "getPOS_SPOT2", "POS_SPOT3", "getPOS_SPOT3", "POS_SPOT4", "getPOS_SPOT4", "PROCESSING_POS_SPOT1", "getPROCESSING_POS_SPOT1", "PROCESSING_POS_SPOT2", "getPROCESSING_POS_SPOT2", "PROCESSING_POS_SPOT3", "getPROCESSING_POS_SPOT3", "PROCESSING_POS_SPOT4", "getPROCESSING_POS_SPOT4", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SpotPositions {
        public static final SpotPositions INSTANCE = new SpotPositions();
        private static final PointF POS_SPOT1 = new PointF(0.22f, 0.15f);
        private static final PointF POS_SPOT2 = new PointF(1.2f, 0.38f);
        private static final PointF POS_SPOT3 = new PointF(-0.1f, 0.75f);
        private static final PointF POS_SPOT4 = new PointF(0.72f, 1.32f);
        private static final PointF PROCESSING_POS_SPOT1 = new PointF(0.28f, 0.77f);
        private static final PointF PROCESSING_POS_SPOT2 = new PointF(1.39f, -0.27f);
        private static final PointF PROCESSING_POS_SPOT3 = new PointF(0.1f, -0.09f);
        private static final PointF PROCESSING_POS_SPOT4 = new PointF(0.9f, 0.62f);

        private SpotPositions() {
        }

        public final PointF getPOS_SPOT1() {
            return POS_SPOT1;
        }

        public final PointF getPOS_SPOT2() {
            return POS_SPOT2;
        }

        public final PointF getPOS_SPOT3() {
            return POS_SPOT3;
        }

        public final PointF getPOS_SPOT4() {
            return POS_SPOT4;
        }

        public final PointF getPROCESSING_POS_SPOT1() {
            return PROCESSING_POS_SPOT1;
        }

        public final PointF getPROCESSING_POS_SPOT2() {
            return PROCESSING_POS_SPOT2;
        }

        public final PointF getPROCESSING_POS_SPOT3() {
            return PROCESSING_POS_SPOT3;
        }

        public final PointF getPROCESSING_POS_SPOT4() {
            return PROCESSING_POS_SPOT4;
        }
    }

    private Constants() {
    }

    public final Size getBUFFER_SIZE() {
        return BUFFER_SIZE;
    }

    public final int getCOLOR_BACKGROUND() {
        return COLOR_BACKGROUND;
    }

    public final int getCOLOR_BLUE() {
        return COLOR_BLUE;
    }

    public final int getCOLOR_GREEN() {
        return COLOR_GREEN;
    }

    public final int getCOLOR_LAVENDER_PURPLE() {
        return COLOR_LAVENDER_PURPLE;
    }

    public final int getCOLOR_LIGHT_BLUE() {
        return COLOR_LIGHT_BLUE;
    }

    public final int getCOLOR_PURPLE() {
        return COLOR_PURPLE;
    }

    public final int getCOLOR_SKY_BLUE() {
        return COLOR_SKY_BLUE;
    }

    public final int getCOLOR_SOFT_YELLOW() {
        return COLOR_SOFT_YELLOW;
    }

    public final int getCOLOR_WHITE() {
        return COLOR_WHITE;
    }

    public final int getCOLOR_YELLOW() {
        return COLOR_YELLOW;
    }

    public final PathInterpolator getCUBIC_BEZIER_INTERPOLATOR() {
        return CUBIC_BEZIER_INTERPOLATOR;
    }
}
