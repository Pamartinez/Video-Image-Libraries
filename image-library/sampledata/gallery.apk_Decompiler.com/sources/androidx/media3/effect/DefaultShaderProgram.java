package androidx.media3.effect;

import F2.C0040v;
import F2.G;
import F2.U;
import F2.y0;
import android.content.Context;
import android.graphics.Gainmap;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.os.Build;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class DefaultShaderProgram extends BaseGlShaderProgram implements ExternalShaderProgram, RepeatingGainmapShaderProgram {
    private static final float[] BT2020_FULL_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX = {1.0f, 1.0f, 1.0f, 0.0f, -0.1646f, 1.8814f, 1.4746f, -0.5714f, 0.0f};
    private static final float[] BT2020_LIMITED_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX = {1.1689f, 1.1689f, 1.1689f, 0.0f, -0.1881f, 2.1502f, 1.6853f, -0.653f, 0.0f};
    private static final U NDC_SQUARE;
    private final float[] compositeRgbMatrixArray = GlUtil.create4x4IdentityMatrix();
    private final float[] compositeTransformationMatrixArray = GlUtil.create4x4IdentityMatrix();
    private int gainmapTexId = -1;
    private final GlProgram glProgram;
    private boolean isRepeatingFrameDrawn;
    private Gainmap lastGainmap;
    private final U matrixTransformations;
    private int outputColorTransfer;
    private final U rgbMatrices;
    private final float[][] rgbMatrixCache;
    private boolean shouldRepeatLastFrame;
    private final float[] tempResultMatrix = new float[16];
    private final int textureMinFilter;
    private final float[][] transformationMatrixCache;
    private final boolean useHdr;
    private U visiblePolygon = NDC_SQUARE;

    static {
        G g = U.e;
        Object[] objArr = {new float[]{-1.0f, -1.0f, 0.0f, 1.0f}, new float[]{-1.0f, 1.0f, 0.0f, 1.0f}, new float[]{1.0f, 1.0f, 0.0f, 1.0f}, new float[]{1.0f, -1.0f, 0.0f, 1.0f}};
        C0040v.a(4, objArr);
        NDC_SQUARE = U.w(4, objArr);
    }

    private DefaultShaderProgram(GlProgram glProgram2, U u, U u3, int i2, boolean z) {
        super(z, 1);
        this.glProgram = glProgram2;
        this.outputColorTransfer = i2;
        this.matrixTransformations = u;
        this.rgbMatrices = u3;
        this.useHdr = z;
        int size = u.size();
        int[] iArr = new int[2];
        iArr[1] = 16;
        iArr[0] = size;
        Class cls = Float.TYPE;
        this.transformationMatrixCache = (float[][]) Array.newInstance(cls, iArr);
        int size2 = u3.size();
        int[] iArr2 = new int[2];
        iArr2[1] = 16;
        iArr2[0] = size2;
        this.rgbMatrixCache = (float[][]) Array.newInstance(cls, iArr2);
        int i7 = 9729;
        for (int i8 = 0; i8 < u.size(); i8++) {
            i7 = Math.max(i7, ((GlMatrixTransformation) u.get(i8)).getGlTextureMinFilter());
        }
        this.textureMinFilter = i7;
    }

    public static DefaultShaderProgram create(Context context, List<GlMatrixTransformation> list, List<Object> list2, boolean z) {
        String str;
        if (list2.isEmpty()) {
            str = "shaders/fragment_shader_copy_es2.glsl";
        } else {
            str = "shaders/fragment_shader_transformation_es2.glsl";
        }
        return new DefaultShaderProgram(createGlProgram(context, "shaders/vertex_shader_transformation_es2.glsl", str), U.y(list), U.y(list2), 1, z);
    }

    public static DefaultShaderProgram createApplyingOetf(Context context, List<GlMatrixTransformation> list, List<Object> list2, ColorInfo colorInfo, int i2) {
        boolean z;
        String str;
        String str2;
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        boolean z3 = false;
        if (i2 == 2) {
            z = true;
        } else {
            z = false;
        }
        if (isTransferHdr) {
            str = "shaders/vertex_shader_transformation_es3.glsl";
        } else {
            str = "shaders/vertex_shader_transformation_es2.glsl";
        }
        if (isTransferHdr) {
            str2 = "shaders/fragment_shader_oetf_es3.glsl";
        } else if (z) {
            str2 = "shaders/fragment_shader_transformation_sdr_oetf_es2.glsl";
        } else if (list2.isEmpty()) {
            str2 = "shaders/fragment_shader_copy_es2.glsl";
        } else {
            str2 = "shaders/fragment_shader_transformation_es2.glsl";
        }
        GlProgram createGlProgram = createGlProgram(context, str, str2);
        int i7 = colorInfo.colorTransfer;
        if (isTransferHdr) {
            if (i7 == 7 || i7 == 6) {
                z3 = true;
            }
            Assertions.checkArgument(z3);
            createGlProgram.setIntUniform("uOutputColorTransfer", i7);
        } else if (z) {
            if (i7 == 3 || i7 == 10) {
                z3 = true;
            }
            Assertions.checkArgument(z3);
            createGlProgram.setIntUniform("uOutputColorTransfer", i7);
        }
        return new DefaultShaderProgram(createGlProgram, U.y(list), U.y(list2), colorInfo.colorTransfer, isTransferHdr);
    }

    private static GlProgram createGlProgram(Context context, String str, String str2) {
        try {
            GlProgram glProgram2 = new GlProgram(context, str, str2);
            glProgram2.setFloatsUniform("uTexTransformationMatrix", GlUtil.create4x4IdentityMatrix());
            return glProgram2;
        } catch (GlUtil.GlException | IOException e) {
            throw new VideoFrameProcessingException(e);
        }
    }

    public static DefaultShaderProgram createWithExternalSampler(Context context, ColorInfo colorInfo, ColorInfo colorInfo2, int i2, boolean z) {
        String str;
        String str2;
        float[] fArr;
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        if (isTransferHdr) {
            str = "shaders/vertex_shader_transformation_es3.glsl";
        } else {
            str = "shaders/vertex_shader_transformation_es2.glsl";
        }
        if (isTransferHdr) {
            str2 = "shaders/fragment_shader_transformation_external_yuv_es3.glsl";
        } else {
            str2 = "shaders/fragment_shader_transformation_sdr_external_es2.glsl";
        }
        GlProgram createGlProgram = createGlProgram(context, str, str2);
        if (isTransferHdr) {
            if (GlUtil.isYuvTargetExtensionSupported()) {
                int i7 = 1;
                if (colorInfo.colorRange == 1) {
                    fArr = BT2020_FULL_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX;
                } else {
                    fArr = BT2020_LIMITED_RANGE_YUV_TO_RGB_COLOR_TRANSFORM_MATRIX;
                }
                createGlProgram.setFloatsUniform("uYuvToRgbColorTransform", fArr);
                createGlProgram.setIntUniform("uInputColorTransfer", colorInfo.colorTransfer);
                if (colorInfo2.colorSpace == 6) {
                    i7 = 0;
                }
                createGlProgram.setIntUniform("uApplyHdrToSdrToneMapping", i7);
            } else {
                throw new VideoFrameProcessingException("The EXT_YUV_target extension is required for HDR editing input.");
            }
        }
        createGlProgram.setExternalTexturesRequireNearestSampling(z);
        return createWithSampler(createGlProgram, colorInfo, colorInfo2, i2, y0.f278h);
    }

    public static DefaultShaderProgram createWithInternalSampler(Context context, ColorInfo colorInfo, ColorInfo colorInfo2, int i2, int i7) {
        boolean z;
        boolean z3;
        String str;
        String str2;
        boolean z7;
        int i8;
        int i10 = 1;
        if (colorInfo.colorTransfer != 2 || i7 == 2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        if (i7 == 2 && colorInfo2.colorSpace == 6) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (isTransferHdr || z3) {
            str = "shaders/vertex_shader_transformation_es3.glsl";
        } else {
            str = "shaders/vertex_shader_transformation_es2.glsl";
        }
        if (z3) {
            str2 = "shaders/fragment_shader_transformation_ultra_hdr_es3.glsl";
        } else if (isTransferHdr) {
            str2 = "shaders/fragment_shader_transformation_hdr_internal_es3.glsl";
        } else {
            str2 = "shaders/fragment_shader_transformation_sdr_internal_es2.glsl";
        }
        GlProgram createGlProgram = createGlProgram(context, str, str2);
        if (!z3) {
            if (isTransferHdr || (i8 = colorInfo.colorTransfer) == 2 || i8 == 3) {
                z7 = true;
            } else {
                z7 = false;
            }
            Assertions.checkArgument(z7);
            createGlProgram.setIntUniform("uInputColorTransfer", colorInfo.colorTransfer);
        }
        if (isTransferHdr) {
            if (colorInfo2.colorSpace == 6) {
                i10 = 0;
            }
            createGlProgram.setIntUniform("uApplyHdrToSdrToneMapping", i10);
        }
        y0 y0Var = y0.f278h;
        if (i7 == 2) {
            y0Var = U.B(new Object());
        }
        return createWithSampler(createGlProgram, colorInfo, colorInfo2, i2, y0Var);
    }

    private static DefaultShaderProgram createWithSampler(GlProgram glProgram2, ColorInfo colorInfo, ColorInfo colorInfo2, int i2, U u) {
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9;
        boolean isTransferHdr = ColorInfo.isTransferHdr(colorInfo);
        int i7 = colorInfo.colorSpace;
        boolean z10 = false;
        if ((i7 == 1 || i7 == 2) && colorInfo2.colorSpace == 6) {
            z = true;
        } else {
            z = false;
        }
        int i8 = colorInfo2.colorTransfer;
        if (isTransferHdr) {
            if (i8 == 3) {
                i8 = 10;
            }
            if (i8 == 1 || i8 == 10 || i8 == 6 || i8 == 7) {
                z9 = true;
            } else {
                z9 = false;
            }
            Assertions.checkArgument(z9);
            glProgram2.setIntUniform("uOutputColorTransfer", i8);
        } else if (z) {
            if (i8 == 1 || i8 == 6 || i8 == 7) {
                z7 = true;
            } else {
                z7 = false;
            }
            Assertions.checkArgument(z7);
            glProgram2.setIntUniform("uOutputColorTransfer", i8);
        } else {
            glProgram2.setIntUniform("uSdrWorkingColorSpace", i2);
            if (i8 == 3 || i8 == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            Assertions.checkArgument(z3);
            glProgram2.setIntUniform("uOutputColorTransfer", i8);
        }
        boolean z11 = z;
        y0 y0Var = y0.f278h;
        int i10 = colorInfo2.colorTransfer;
        if (isTransferHdr || z11) {
            z10 = true;
        }
        int i11 = i10;
        DefaultShaderProgram defaultShaderProgram = new DefaultShaderProgram(glProgram2, u, y0Var, i11, z10);
        return defaultShaderProgram;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Matrix lambda$createWithInternalSampler$0(long j2) {
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        return matrix;
    }

    private void setGainmapSamplerAndUniforms() {
        if (this.lastGainmap != null) {
            if (Build.VERSION.SDK_INT >= 34) {
                this.glProgram.setSamplerTexIdUniform("uGainmapTexSampler", this.gainmapTexId, 1);
                GainmapUtil.setGainmapUniforms(this.glProgram, this.lastGainmap, -1);
                return;
            }
            throw new IllegalStateException("Gainmaps not supported under API 34.");
        }
    }

    private boolean updateCompositeRgbMatrixArray(long j2) {
        int size = this.rgbMatrices.size();
        int[] iArr = new int[2];
        iArr[1] = 16;
        iArr[0] = size;
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, iArr);
        if (this.rgbMatrices.size() > 0) {
            this.rgbMatrices.get(0).getClass();
            throw new ClassCastException();
        } else if (!updateMatrixCache(this.rgbMatrixCache, fArr)) {
            return false;
        } else {
            GlUtil.setToIdentity(this.compositeRgbMatrixArray);
            if (this.rgbMatrices.size() <= 0) {
                return true;
            }
            this.rgbMatrices.get(0).getClass();
            throw new ClassCastException();
        }
    }

    private boolean updateCompositeTransformationMatrixAndVisiblePolygon(long j2) {
        int size = this.matrixTransformations.size();
        int[] iArr = new int[2];
        iArr[1] = 16;
        iArr[0] = size;
        float[][] fArr = (float[][]) Array.newInstance(Float.TYPE, iArr);
        for (int i2 = 0; i2 < this.matrixTransformations.size(); i2++) {
            fArr[i2] = ((GlMatrixTransformation) this.matrixTransformations.get(i2)).getGlMatrixArray(j2);
        }
        if (!updateMatrixCache(this.transformationMatrixCache, fArr)) {
            return false;
        }
        GlUtil.setToIdentity(this.compositeTransformationMatrixArray);
        this.visiblePolygon = NDC_SQUARE;
        for (float[] fArr2 : this.transformationMatrixCache) {
            android.opengl.Matrix.multiplyMM(this.tempResultMatrix, 0, fArr2, 0, this.compositeTransformationMatrixArray, 0);
            float[] fArr3 = this.tempResultMatrix;
            System.arraycopy(fArr3, 0, this.compositeTransformationMatrixArray, 0, fArr3.length);
            U clipConvexPolygonToNdcRange = MatrixUtils.clipConvexPolygonToNdcRange(MatrixUtils.transformPoints(fArr2, this.visiblePolygon));
            this.visiblePolygon = clipConvexPolygonToNdcRange;
            if (clipConvexPolygonToNdcRange.size() < 3) {
                return true;
            }
        }
        android.opengl.Matrix.invertM(this.tempResultMatrix, 0, this.compositeTransformationMatrixArray, 0);
        this.visiblePolygon = MatrixUtils.transformPoints(this.tempResultMatrix, this.visiblePolygon);
        return true;
    }

    private static boolean updateMatrixCache(float[][] fArr, float[][] fArr2) {
        boolean z;
        boolean z3 = false;
        for (int i2 = 0; i2 < fArr.length; i2++) {
            float[] fArr3 = fArr[i2];
            float[] fArr4 = fArr2[i2];
            if (!Arrays.equals(fArr3, fArr4)) {
                if (fArr4.length == 16) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.checkState(z, "A 4x4 transformation matrix must have 16 elements");
                System.arraycopy(fArr4, 0, fArr3, 0, fArr4.length);
                z3 = true;
            }
        }
        return z3;
    }

    public Size configure(int i2, int i7) {
        return MatrixUtils.configureAndGetOutputSize(i2, i7, this.matrixTransformations);
    }

    public void drawFrame(int i2, long j2) {
        boolean z;
        boolean updateCompositeRgbMatrixArray = updateCompositeRgbMatrixArray(j2);
        boolean updateCompositeTransformationMatrixAndVisiblePolygon = updateCompositeTransformationMatrixAndVisiblePolygon(j2);
        if (updateCompositeRgbMatrixArray || updateCompositeTransformationMatrixAndVisiblePolygon) {
            z = true;
        } else {
            z = false;
        }
        if (this.visiblePolygon.size() >= 3) {
            if (!this.shouldRepeatLastFrame || z || !this.isRepeatingFrameDrawn) {
                try {
                    this.glProgram.use();
                    setGainmapSamplerAndUniforms();
                    this.glProgram.setSamplerTexIdUniform("uTexSampler", i2, 0, this.textureMinFilter);
                    this.glProgram.setFloatsUniform("uTransformationMatrix", this.compositeTransformationMatrixArray);
                    this.glProgram.setFloatsUniformIfPresent("uRgbMatrix", this.compositeRgbMatrixArray);
                    this.glProgram.setBufferAttribute("aFramePosition", GlUtil.createVertexBuffer(this.visiblePolygon), 4);
                    this.glProgram.bindAttributesAndUniforms();
                    GLES20.glDrawArrays(6, 0, this.visiblePolygon.size());
                    GlUtil.checkGlError();
                    this.isRepeatingFrameDrawn = true;
                } catch (GlUtil.GlException e) {
                    throw new VideoFrameProcessingException((Throwable) e, j2);
                }
            }
        }
    }

    public void release() {
        super.release();
        try {
            this.glProgram.delete();
            int i2 = this.gainmapTexId;
            if (i2 != -1) {
                GlUtil.deleteTexture(i2);
            }
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }

    public void setGainmap(Gainmap gainmap) {
        if (this.useHdr) {
            Gainmap gainmap2 = this.lastGainmap;
            if (gainmap2 == null || !GainmapUtil.equals(gainmap2, gainmap)) {
                this.isRepeatingFrameDrawn = false;
                this.lastGainmap = gainmap;
                int i2 = this.gainmapTexId;
                if (i2 == -1) {
                    this.gainmapTexId = GlUtil.createTexture(gainmap.getGainmapContents());
                } else {
                    GlUtil.setTexture(i2, gainmap.getGainmapContents());
                }
            }
        }
    }

    public void setTextureTransformMatrix(float[] fArr) {
        this.glProgram.setFloatsUniform("uTexTransformationMatrix", fArr);
    }

    public boolean shouldClearTextureBuffer() {
        if (!this.isRepeatingFrameDrawn || !this.shouldRepeatLastFrame) {
            return true;
        }
        return false;
    }

    public void signalNewRepeatingFrameSequence() {
        boolean z;
        if (this.outputTexturePool.capacity() == 1) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.shouldRepeatLastFrame = true;
        this.isRepeatingFrameDrawn = false;
    }
}
