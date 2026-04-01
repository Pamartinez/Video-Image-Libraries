package androidx.media3.effect;

import android.graphics.Bitmap;
import android.graphics.Gainmap;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class GainmapUtil {
    private static String addIndex(String str, int i2) {
        if (i2 == -1) {
            return str;
        }
        return str + i2;
    }

    private static boolean areAllChannelsEqual(float[] fArr) {
        float f = fArr[0];
        float f5 = fArr[1];
        if (f == f5 && f5 == fArr[2]) {
            return true;
        }
        return false;
    }

    public static boolean equals(Gainmap gainmap, Gainmap gainmap2) {
        if (gainmap.getGamma() == gainmap2.getGamma() && gainmap.getRatioMax() == gainmap2.getRatioMax() && gainmap.getRatioMin() == gainmap2.getRatioMin() && gainmap.getEpsilonHdr() == gainmap2.getEpsilonHdr() && gainmap.getEpsilonSdr() == gainmap2.getEpsilonSdr() && gainmap.getDisplayRatioForFullHdr() == gainmap2.getDisplayRatioForFullHdr() && gainmap.getMinDisplayRatioForHdrTransition() == gainmap2.getMinDisplayRatioForHdrTransition() && gainmap.getGainmapContents() == gainmap2.getGainmapContents() && gainmap.getGainmapContents().getGenerationId() == gainmap2.getGainmapContents().getGenerationId()) {
            return true;
        }
        return false;
    }

    private static float[] logRgb(float[] fArr) {
        return new float[]{(float) Math.log((double) fArr[0]), (float) Math.log((double) fArr[1]), (float) Math.log((double) fArr[2])};
    }

    public static void setGainmapUniforms(GlProgram glProgram, Gainmap gainmap, int i2) {
        int i7;
        int i8;
        int i10 = 0;
        if (gainmap.getGainmapContents().getConfig() == Bitmap.Config.ALPHA_8) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        float[] w = gainmap.getGamma();
        if (w[0] == 1.0f && w[1] == 1.0f && w[2] == 1.0f) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        if (areAllChannelsEqual(w) && areAllChannelsEqual(gainmap.getRatioMax()) && areAllChannelsEqual(gainmap.getRatioMin())) {
            i10 = 1;
        }
        glProgram.setIntUniform(addIndex("uGainmapIsAlpha", i2), i7);
        glProgram.setIntUniform(addIndex("uNoGamma", i2), i8);
        glProgram.setIntUniform(addIndex("uSingleChannel", i2), i10);
        glProgram.setFloatsUniform(addIndex("uLogRatioMin", i2), logRgb(gainmap.getRatioMin()));
        glProgram.setFloatsUniform(addIndex("uLogRatioMax", i2), logRgb(gainmap.getRatioMax()));
        glProgram.setFloatsUniform(addIndex("uEpsilonSdr", i2), gainmap.getEpsilonSdr());
        glProgram.setFloatsUniform(addIndex("uEpsilonHdr", i2), gainmap.getEpsilonHdr());
        glProgram.setFloatsUniform(addIndex("uGainmapGamma", i2), w);
        glProgram.setFloatUniform(addIndex("uDisplayRatioHdr", i2), gainmap.getDisplayRatioForFullHdr());
        glProgram.setFloatUniform(addIndex("uDisplayRatioSdr", i2), gainmap.getMinDisplayRatioForHdrTransition());
        GlUtil.checkGlError();
    }
}
