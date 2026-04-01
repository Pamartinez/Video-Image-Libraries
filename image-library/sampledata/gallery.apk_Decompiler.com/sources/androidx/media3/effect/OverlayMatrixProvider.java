package androidx.media3.effect;

import android.opengl.Matrix;
import android.util.Pair;
import androidx.media3.common.OverlaySettings;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class OverlayMatrixProvider {
    private final float[] aspectRatioMatrix = GlUtil.create4x4IdentityMatrix();
    private final float[] backgroundFrameAnchorMatrix = GlUtil.create4x4IdentityMatrix();
    private Size backgroundSize;
    private final float[] overlayAspectRatioMatrix = GlUtil.create4x4IdentityMatrix();
    private final float[] overlayAspectRatioMatrixInv = GlUtil.create4x4IdentityMatrix();
    private final float[] overlayFrameAnchorMatrix = GlUtil.create4x4IdentityMatrix();
    private final float[] rotateMatrix = GlUtil.create4x4IdentityMatrix();
    private final float[] scaleMatrix = GlUtil.create4x4IdentityMatrix();
    private final float[] scaleMatrixInv = GlUtil.create4x4IdentityMatrix();
    private final float[] transformationMatrix = GlUtil.create4x4IdentityMatrix();

    private void reset() {
        GlUtil.setToIdentity(this.aspectRatioMatrix);
        GlUtil.setToIdentity(this.backgroundFrameAnchorMatrix);
        GlUtil.setToIdentity(this.overlayFrameAnchorMatrix);
        GlUtil.setToIdentity(this.scaleMatrix);
        GlUtil.setToIdentity(this.scaleMatrixInv);
        GlUtil.setToIdentity(this.rotateMatrix);
        GlUtil.setToIdentity(this.overlayAspectRatioMatrix);
        GlUtil.setToIdentity(this.overlayAspectRatioMatrixInv);
        GlUtil.setToIdentity(this.transformationMatrix);
    }

    public void configure(Size size) {
        this.backgroundSize = size;
    }

    public float[] getTransformationMatrix(Size size, OverlaySettings overlaySettings) {
        reset();
        Pair<Float, Float> backgroundFrameAnchor = overlaySettings.getBackgroundFrameAnchor();
        Matrix.translateM(this.backgroundFrameAnchorMatrix, 0, ((Float) backgroundFrameAnchor.first).floatValue(), ((Float) backgroundFrameAnchor.second).floatValue(), 0.0f);
        Assertions.checkStateNotNull(this.backgroundSize);
        Matrix.scaleM(this.aspectRatioMatrix, 0, ((float) size.getWidth()) / ((float) this.backgroundSize.getWidth()), ((float) size.getHeight()) / ((float) this.backgroundSize.getHeight()), 1.0f);
        Pair<Float, Float> scale = overlaySettings.getScale();
        Matrix.scaleM(this.scaleMatrix, 0, ((Float) scale.first).floatValue(), ((Float) scale.second).floatValue(), 1.0f);
        Matrix.invertM(this.scaleMatrixInv, 0, this.scaleMatrix, 0);
        Pair<Float, Float> overlayFrameAnchor = overlaySettings.getOverlayFrameAnchor();
        Matrix.translateM(this.overlayFrameAnchorMatrix, 0, ((Float) overlayFrameAnchor.first).floatValue() * -1.0f, ((Float) overlayFrameAnchor.second).floatValue() * -1.0f, 0.0f);
        Matrix.rotateM(this.rotateMatrix, 0, overlaySettings.getRotationDegrees(), 0.0f, 0.0f, 1.0f);
        Matrix.scaleM(this.overlayAspectRatioMatrix, 0, ((float) size.getHeight()) / ((float) size.getWidth()), 1.0f, 1.0f);
        Matrix.invertM(this.overlayAspectRatioMatrixInv, 0, this.overlayAspectRatioMatrix, 0);
        float[] fArr = this.transformationMatrix;
        Matrix.multiplyMM(fArr, 0, fArr, 0, this.backgroundFrameAnchorMatrix, 0);
        float[] fArr2 = this.transformationMatrix;
        Matrix.multiplyMM(fArr2, 0, fArr2, 0, this.aspectRatioMatrix, 0);
        float[] fArr3 = this.transformationMatrix;
        Matrix.multiplyMM(fArr3, 0, fArr3, 0, this.scaleMatrix, 0);
        float[] fArr4 = this.transformationMatrix;
        Matrix.multiplyMM(fArr4, 0, fArr4, 0, this.overlayFrameAnchorMatrix, 0);
        float[] fArr5 = this.transformationMatrix;
        Matrix.multiplyMM(fArr5, 0, fArr5, 0, this.scaleMatrixInv, 0);
        float[] fArr6 = this.transformationMatrix;
        Matrix.multiplyMM(fArr6, 0, fArr6, 0, this.overlayAspectRatioMatrix, 0);
        float[] fArr7 = this.transformationMatrix;
        Matrix.multiplyMM(fArr7, 0, fArr7, 0, this.rotateMatrix, 0);
        float[] fArr8 = this.transformationMatrix;
        Matrix.multiplyMM(fArr8, 0, fArr8, 0, this.overlayAspectRatioMatrixInv, 0);
        float[] fArr9 = this.transformationMatrix;
        Matrix.multiplyMM(fArr9, 0, fArr9, 0, this.scaleMatrix, 0);
        return this.transformationMatrix;
    }
}
