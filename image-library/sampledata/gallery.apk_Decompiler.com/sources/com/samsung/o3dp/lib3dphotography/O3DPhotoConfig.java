package com.samsung.o3dp.lib3dphotography;

import android.text.TextUtils;
import com.samsung.o3dp.lib3dphotography.O3DPContext;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.samsung.o3dp.lib3dphotography.graphics.Texture;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPhotoConfig {
    private static final String TAG = "O3DPhotoConfig";
    private Animation mAnimation;
    private float mAnimationSpeed = 1.0f;
    private int mBgColor = 0;
    private Set<String> mBlockAnimations = new HashSet();
    private DepthClusteringConfig mDepthClusteringConfig = new DepthClusteringConfig();
    private String mFilePath;
    private Texture.FitMode mFitMode = Texture.FitMode.FIT_FULL;
    private boolean mIsAutoAnimation = true;
    private boolean mIsDynamicsEnabled = true;
    private LayerGeneratorConfig mLayerGeneratorConfig = new LayerGeneratorConfig();
    private final List<OnConfigChangedListener> mListenerList = new ArrayList();
    private MeshDecimatorConfig mMeshDecimatorConfig = new MeshDecimatorConfig();
    private O3DPContext.OutputType mOutputType = O3DPContext.OutputType.LOOP;
    private boolean mPanorama = false;
    private boolean mStereo = false;
    private boolean mUseClipper = true;
    private boolean mWriteImageFiles = false;
    private boolean mWriteMeshFile = false;

    public O3DPhotoConfig() {
        setAnimationSpeed(1.0f);
    }

    public void addOnConfigChangedListener(OnConfigChangedListener onConfigChangedListener) {
        this.mListenerList.add(onConfigChangedListener);
    }

    public boolean applyConfig() {
        boolean z;
        try {
            if (this.mOutputType != O3DPContext.OutputType.DEFAULT) {
                z = true;
            } else {
                z = false;
            }
            setOutputType(z);
            setAnimationSpeed(this.mAnimationSpeed);
            setAnimation(this.mAnimation);
            setBgColor(this.mBgColor);
            setStereo(this.mStereo);
            setDynamicsEnabled(this.mIsDynamicsEnabled);
            return true;
        } catch (NullPointerException e) {
            LogUtil.e(TAG, "NullPointerException at applyConfig(): " + e);
            return false;
        }
    }

    public String getAnimationName() {
        Animation animation = this.mAnimation;
        if (animation != null) {
            return animation.getName();
        }
        LogUtil.w(TAG, "Animation is not set yet");
        return null;
    }

    public float getAnimationSpeed() {
        return this.mAnimationSpeed;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public Set<String> getBlockAnimations() {
        return this.mBlockAnimations;
    }

    public DepthClusteringConfig getDepthClusteringConfig() {
        return this.mDepthClusteringConfig;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public Texture.FitMode getFitMode() {
        return this.mFitMode;
    }

    public LayerGeneratorConfig getLayerGeneratorConfig() {
        return this.mLayerGeneratorConfig;
    }

    public MeshDecimatorConfig getMeshDecimatorConfig() {
        return this.mMeshDecimatorConfig;
    }

    public O3DPContext.OutputType getOutputType() {
        return this.mOutputType;
    }

    public boolean getStereo() {
        return this.mStereo;
    }

    public boolean getUseClipper() {
        return this.mUseClipper;
    }

    public boolean getWriteImageFiles() {
        return this.mWriteImageFiles;
    }

    public boolean getWriteMeshFile() {
        return this.mWriteMeshFile;
    }

    public boolean isAutoAnimation() {
        if (this.mIsAutoAnimation || this.mAnimation == null) {
            return true;
        }
        return false;
    }

    public boolean isPanorama() {
        return this.mPanorama;
    }

    public void removeOnConfigChangedListener(OnConfigChangedListener onConfigChangedListener) {
        this.mListenerList.remove(onConfigChangedListener);
    }

    public void reset() {
        setAnimationSpeed(1.0f);
    }

    public void setAnimation(Animation animation) {
        for (OnConfigChangedListener onAnimationChanged : this.mListenerList) {
            onAnimationChanged.onAnimationChanged(animation);
        }
    }

    public void setAnimationName(String str) {
        for (OnConfigChangedListener onAnimationNameChanged : this.mListenerList) {
            onAnimationNameChanged.onAnimationNameChanged(str);
        }
    }

    public void setAnimationSpeed(float f) {
        this.mAnimationSpeed = f;
        for (OnConfigChangedListener onAnimationSpeedChanged : this.mListenerList) {
            onAnimationSpeedChanged.onAnimationSpeedChanged(f);
        }
    }

    public void setAutoAnimation(boolean z) {
        this.mIsAutoAnimation = z;
    }

    public void setBgColor(int i2) {
        this.mBgColor = i2;
        for (OnConfigChangedListener onBgColorChanged : this.mListenerList) {
            onBgColorChanged.onBgColorChanged(i2);
        }
    }

    public void setBlockAnimations(Set<String> set) {
        this.mBlockAnimations = set;
    }

    public void setDepthClusteringConfig(DepthClusteringConfig depthClusteringConfig) {
        this.mDepthClusteringConfig = depthClusteringConfig;
    }

    public void setDynamicsEnabled(boolean z) {
        this.mIsDynamicsEnabled = z;
        for (OnConfigChangedListener onDynamicsEnableChanged : this.mListenerList) {
            onDynamicsEnableChanged.onDynamicsEnableChanged(this.mIsDynamicsEnabled);
        }
    }

    public void setFilePath(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("file path is empty");
        } else if (str.toLowerCase().endsWith(O3DPConstant.MP4_EXTENSION)) {
            this.mFilePath = str;
        } else {
            throw new IllegalArgumentException("file extension error.");
        }
    }

    public void setFitMode(Texture.FitMode fitMode) {
        this.mFitMode = fitMode;
    }

    public void setLayerGeneratorConfig(LayerGeneratorConfig layerGeneratorConfig) {
        this.mLayerGeneratorConfig = layerGeneratorConfig;
    }

    public void setMeshDecimatorConfig(MeshDecimatorConfig meshDecimatorConfig) {
        this.mMeshDecimatorConfig = meshDecimatorConfig;
    }

    public void setOutputType(boolean z) {
        O3DPContext.OutputType outputType;
        if (z) {
            outputType = O3DPContext.OutputType.LOOP;
        } else {
            outputType = O3DPContext.OutputType.DEFAULT;
        }
        this.mOutputType = outputType;
        for (OnConfigChangedListener onOutputTypeChanged : this.mListenerList) {
            onOutputTypeChanged.onOutputTypeChanged(this.mOutputType);
        }
    }

    public void setPanorama(boolean z) {
        this.mPanorama = z;
    }

    public void setStereo(boolean z) {
        this.mStereo = z;
        for (OnConfigChangedListener onStereoChanged : this.mListenerList) {
            onStereoChanged.onStereoChanged(z);
        }
    }

    public void setUseClipper(boolean z) {
        this.mUseClipper = z;
    }

    public void setWriteImageFiles(boolean z) {
        this.mWriteImageFiles = false;
    }

    public void setWriteMeshFile(boolean z) {
        this.mWriteMeshFile = false;
    }

    public void updateCurrentAnimation(Animation animation) {
        this.mAnimation = animation;
    }
}
