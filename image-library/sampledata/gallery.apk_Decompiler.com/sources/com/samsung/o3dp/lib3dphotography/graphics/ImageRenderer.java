package com.samsung.o3dp.lib3dphotography.graphics;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.samsung.o3dp.lib3dphotography.DepthClusteringConfig;
import com.samsung.o3dp.lib3dphotography.LayerGeneratorConfig;
import com.samsung.o3dp.lib3dphotography.MeshDecimatorConfig;
import com.samsung.o3dp.lib3dphotography.MeshUtils;
import com.samsung.o3dp.lib3dphotography.nativelib.NativeInterface;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageRenderer extends NativeInterface {
    private static final String TAG = "ImageRenderer";
    private long m_imageRendererObj;
    private final SharedRenderParams m_sharedRenderParams;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MeshAttributeIndex {
        Primitives,
        Vertices,
        Colors,
        TexCoords,
        Indices,
        MAX
    }

    public ImageRenderer() {
        if (EGLHandler.isCurrentContextValid()) {
            long nativeImageRenderer = nativeImageRenderer();
            this.m_imageRendererObj = nativeImageRenderer;
            this.m_sharedRenderParams = new SharedRenderParams(nativeSharedRenderParams(nativeImageRenderer));
            return;
        }
        throw new IllegalStateException("Cannot initialize ImageRenderer without current EGL context");
    }

    private static native void nativeDeleteImageRenderer(long j2);

    private static native void nativeDepthChanged(long j2);

    private static native void nativeGenerateInitialLayers(long j2);

    private static native void nativeGenerateSurfaceModels(long j2);

    private static native long nativeGetColorTexForLayer(long j2, int i2);

    private static native long nativeGetDepthTexForLayer(long j2, int i2);

    private static native long nativeGetForegroundMaskTex(long j2);

    private static native int nativeGetLayerAtPixel(long j2, int i2, int i7);

    private static native float nativeGetMaxDepthForLayer(long j2, int i2);

    private static native List<Object[]> nativeGetMeshInfo(long j2);

    private static native float nativeGetMinDepthForLayer(long j2, int i2);

    private static native int nativeGetNumLayers(long j2);

    private static native long nativeGetResultTex(long j2);

    private static native long nativeImageRenderer();

    private static native boolean nativeIsClipperEnabled(long j2);

    private static native boolean nativeIsDepthChanged(long j2);

    private static native void nativeProcessDepthInpainting(long j2);

    private static native void nativeSetBgColor(long j2, float[] fArr);

    private static native void nativeSetBokehEnabled(long j2, boolean z);

    private static native void nativeSetClipperConfig(long j2, boolean z);

    private static native void nativeSetColorTex(long j2, long j3);

    private static native void nativeSetColorTexForLayer(long j2, int i2, long j3);

    private static native void nativeSetDepthClusteringConfig(long j2, boolean z, int i2, int i7, boolean z3, boolean z7, boolean z9, boolean z10, int i8, float f, float f5, float f8, float f10, float f11, boolean z11, boolean z12, float f12, boolean z13, boolean z14);

    private static native void nativeSetDepthTex(long j2, long j3, long j8);

    private static native void nativeSetDynamicOffsetEnabled(long j2, boolean z);

    private static native void nativeSetLayerGeneratorConfig(long j2, boolean z, int i2, boolean z3, int i7, boolean z7, int i8, boolean z9, int i10, boolean z10);

    private static native void nativeSetMeshDecimatorConfig(long j2, boolean z, int i2, float f, float f5, float f8);

    private static native void nativeSetMeshInfo(long j2, List<Object[]> list);

    private static native void nativeSetOutSize(long j2, int i2, int i7);

    private static native void nativeSetPanorama(long j2, boolean z);

    private static native void nativeSetProjectionParams(long j2, double d, double d2, double d3);

    private static native void nativeSetSegmentedTex(long j2, long j3);

    private static native void nativeSetStereo(long j2, boolean z);

    private static native long nativeSharedRenderParams(long j2);

    private static native void nativeVisualize(long j2);

    public void depthChanged() {
        nativeDepthChanged(this.m_imageRendererObj);
    }

    public void destroyImageRenderer() {
        long j2 = this.m_imageRendererObj;
        if (j2 != 0) {
            nativeDeleteImageRenderer(j2);
            this.m_imageRendererObj = 0;
            return;
        }
        LogUtil.w(TAG, "Oops, ImageRenderer is already destroyed!");
    }

    public void generateInitialLayers() {
        nativeGenerateInitialLayers(this.m_imageRendererObj);
    }

    public void generateSurfaceModels() {
        nativeGenerateSurfaceModels(this.m_imageRendererObj);
    }

    public long getColorTexForLayer(int i2) {
        return nativeGetColorTexForLayer(this.m_imageRendererObj, i2);
    }

    public long getDepthTexForLayer(int i2) {
        return nativeGetDepthTexForLayer(this.m_imageRendererObj, i2);
    }

    public long getForegroundMaskTex() {
        return nativeGetForegroundMaskTex(this.m_imageRendererObj);
    }

    public int getLayerAtPixel(Point point) {
        return nativeGetLayerAtPixel(this.m_imageRendererObj, point.x, point.y);
    }

    public float getMaxDepthForLayer(int i2) {
        return nativeGetMaxDepthForLayer(this.m_imageRendererObj, i2);
    }

    public MeshUtils.MeshInfo getMeshInfo() {
        List<Object[]> nativeGetMeshInfo = nativeGetMeshInfo(this.m_imageRendererObj);
        if (nativeGetMeshInfo == null) {
            return null;
        }
        MeshUtils.MeshInfo meshInfo = new MeshUtils.MeshInfo();
        meshInfo.layers = new ArrayList();
        for (int i2 = 0; i2 < nativeGetMeshInfo.size(); i2++) {
            MeshUtils.MeshLayerInfo meshLayerInfo = new MeshUtils.MeshLayerInfo();
            meshLayerInfo.primitive = ((Integer) nativeGetMeshInfo.get(i2)[MeshAttributeIndex.Primitives.ordinal()]).intValue();
            meshLayerInfo.indices = (int[]) nativeGetMeshInfo.get(i2)[MeshAttributeIndex.Indices.ordinal()];
            meshLayerInfo.vertices = (float[]) nativeGetMeshInfo.get(i2)[MeshAttributeIndex.Vertices.ordinal()];
            meshLayerInfo.colors = (float[]) nativeGetMeshInfo.get(i2)[MeshAttributeIndex.Colors.ordinal()];
            meshLayerInfo.texCoords = (float[]) nativeGetMeshInfo.get(i2)[MeshAttributeIndex.TexCoords.ordinal()];
            meshInfo.layers.add(meshLayerInfo);
        }
        for (int i7 = 0; i7 < meshInfo.layers.size(); i7++) {
            Texture texture = new Texture();
            texture.textureObj = getColorTexForLayer(i7);
            meshInfo.layers.get(i7).bitmap = texture.download();
            texture.release();
        }
        return meshInfo;
    }

    public float getMinDepthForLayer(int i2) {
        return nativeGetMinDepthForLayer(this.m_imageRendererObj, i2);
    }

    public int getNumLayers() {
        return nativeGetNumLayers(this.m_imageRendererObj);
    }

    public long getResultTex() {
        return nativeGetResultTex(this.m_imageRendererObj);
    }

    public SharedRenderParams getSharedRenderParams() {
        return this.m_sharedRenderParams;
    }

    public boolean isClipperEnabled() {
        return nativeIsClipperEnabled(this.m_imageRendererObj);
    }

    public boolean isDepthChanged() {
        return nativeIsDepthChanged(this.m_imageRendererObj);
    }

    public void processDepthInpainting() {
        nativeProcessDepthInpainting(this.m_imageRendererObj);
    }

    public void setBgColor(float[] fArr) {
        nativeSetBgColor(this.m_imageRendererObj, fArr);
    }

    public void setBokehEnabled(boolean z) {
        nativeSetBokehEnabled(this.m_imageRendererObj, z);
    }

    public void setClipperConfig(boolean z) {
        nativeSetClipperConfig(this.m_imageRendererObj, z);
    }

    public void setColorTex(Texture texture) {
        nativeSetColorTex(this.m_imageRendererObj, texture.textureObj);
    }

    public void setDepthClusteringConfig(DepthClusteringConfig depthClusteringConfig) {
        DepthClusteringConfig depthClusteringConfig2 = depthClusteringConfig;
        long j2 = this.m_imageRendererObj;
        boolean z = depthClusteringConfig2.useDepthClustering;
        long j3 = j2;
        int i2 = depthClusteringConfig2.expectedLayers;
        long j8 = j3;
        int i7 = depthClusteringConfig2.maxKForKMeans;
        long j10 = j8;
        boolean z3 = depthClusteringConfig2.useInclusionClustering;
        long j11 = j10;
        boolean z7 = depthClusteringConfig2.useAgglomerativeClustering;
        long j12 = j11;
        boolean z9 = depthClusteringConfig2.useDepthBasedMerging;
        long j13 = j12;
        boolean z10 = depthClusteringConfig2.useDeterministicKMeans;
        long j14 = j13;
        int i8 = depthClusteringConfig2.histogramBins;
        long j15 = j14;
        float f = depthClusteringConfig2.histogramPeakThreshold;
        long j16 = j15;
        float f5 = depthClusteringConfig2.histogramSmoothingSigma;
        long j17 = j16;
        float f8 = depthClusteringConfig2.minMergeThreshold;
        long j18 = j17;
        float f10 = depthClusteringConfig2.gradientMergeThreshold;
        long j19 = j18;
        float f11 = depthClusteringConfig2.gradientOutlierThreshold;
        long j20 = j19;
        boolean z11 = depthClusteringConfig2.usePeakCountForKMeans;
        boolean z12 = depthClusteringConfig2.forcePeakCountAsExpectedLayers;
        float f12 = depthClusteringConfig2.minDepthThresholdForPeakAnalysis;
        long j21 = j20;
        boolean z13 = z12;
        float f13 = f12;
        nativeSetDepthClusteringConfig(j21, z, i2, i7, z3, z7, z9, z10, i8, f, f5, f8, f10, f11, z11, z13, f13, depthClusteringConfig2.useOptimized1DKMeans, depthClusteringConfig2.verboseLogging);
    }

    public void setDepthTex(Texture texture, Texture texture2) {
        nativeSetDepthTex(this.m_imageRendererObj, texture.textureObj, texture2.textureObj);
    }

    public void setDynamicOffsetEnabled(boolean z) {
        nativeSetDynamicOffsetEnabled(this.m_imageRendererObj, z);
    }

    public void setLayerGeneratorConfig(LayerGeneratorConfig layerGeneratorConfig) {
        nativeSetLayerGeneratorConfig(this.m_imageRendererObj, layerGeneratorConfig.useBlurColorAlpha, layerGeneratorConfig.blurColorAlphaSize, layerGeneratorConfig.useBlurForegroundDepthSplitSegmented, layerGeneratorConfig.blurForegroundDepthSplitSegmentedSize, layerGeneratorConfig.useBlurForegroundDepthSplitLabels, layerGeneratorConfig.blurForegroundDepthSplitLabelsSize, layerGeneratorConfig.useDilateBackgroundDepth, layerGeneratorConfig.dilateBackgroundDepthSize, layerGeneratorConfig.adjustMinimumForegroundDepth);
    }

    public void setMeshDecimatorConfig(MeshDecimatorConfig meshDecimatorConfig) {
        nativeSetMeshDecimatorConfig(this.m_imageRendererObj, meshDecimatorConfig.enabled, meshDecimatorConfig.targetTriangles, meshDecimatorConfig.fgSimplifyFraction, meshDecimatorConfig.fgSimplifyAggressiveness, meshDecimatorConfig.bgSimplifyAggressiveness);
    }

    public void setMeshInfo(MeshUtils.MeshInfo meshInfo) {
        ArrayList arrayList = new ArrayList();
        for (MeshUtils.MeshLayerInfo next : meshInfo.layers) {
            Object[] objArr = new Object[MeshAttributeIndex.MAX.ordinal()];
            objArr[MeshAttributeIndex.Primitives.ordinal()] = Integer.valueOf(next.primitive);
            objArr[MeshAttributeIndex.Indices.ordinal()] = next.indices;
            objArr[MeshAttributeIndex.Vertices.ordinal()] = next.vertices;
            objArr[MeshAttributeIndex.Colors.ordinal()] = next.colors;
            objArr[MeshAttributeIndex.TexCoords.ordinal()] = next.texCoords;
            arrayList.add(objArr);
        }
        nativeSetMeshInfo(this.m_imageRendererObj, arrayList);
        for (int i2 = 0; i2 < meshInfo.layers.size(); i2++) {
            Bitmap bitmap = meshInfo.layers.get(i2).bitmap;
            Texture create = Texture.create(bitmap.getWidth(), bitmap.getHeight(), 32856, 6408, 5121, 3553);
            nativeSetColorTexForLayer(this.m_imageRendererObj, i2, create.textureObj);
            create.upload(bitmap);
            create.release();
        }
    }

    public void setOutSize(int i2, int i7) {
        nativeSetOutSize(this.m_imageRendererObj, i2, i7);
    }

    public void setPanorama(boolean z) {
        nativeSetPanorama(this.m_imageRendererObj, z);
    }

    public void setProjectionParams(double d, double d2, double d3) {
        nativeSetProjectionParams(this.m_imageRendererObj, d, d2, d3);
    }

    public void setSegmentedTex(Texture texture) {
        nativeSetSegmentedTex(this.m_imageRendererObj, texture.textureObj);
    }

    public void setStereo(boolean z) {
        nativeSetStereo(this.m_imageRendererObj, z);
    }

    public void visualize() {
        nativeVisualize(this.m_imageRendererObj);
    }
}
