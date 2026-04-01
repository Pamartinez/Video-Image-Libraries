package com.samsung.android.gallery.image360.engine.texture;

import android.opengl.Matrix;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelfieSpreadTexture extends BaseSpreadTexture {
    private final double mAltitudeStepAngle;
    private final int mImageHeight;
    private final int mImageWidth;
    private final double mStartAltitude;

    public SelfieSpreadTexture(int i2, int i7) {
        String str = this.TAG;
        Log.d(str, "width: " + i2 + ", height: " + i7);
        this.mImageWidth = i2;
        this.mImageHeight = i7;
        this.mStartAltitude = ((double) (((float) i7) / ((float) i2))) * 3.141592653589793d;
        this.mAltitudeStepAngle = (((double) (((float) i7) / ((float) i2))) * 12.566370614359172d) / 192.0d;
        createStrips();
    }

    private void createStrips() {
        double d;
        int i2;
        SelfieSpreadTexture selfieSpreadTexture = this;
        selfieSpreadTexture.clearBuffers();
        for (int i7 = 0; i7 < 160; i7++) {
            float[] fArr = new float[576];
            float[] fArr2 = new float[384];
            double d2 = 0.039269908169872414d;
            double d3 = ((double) i7) * 0.039269908169872414d;
            double d5 = selfieSpreadTexture.mStartAltitude;
            int i8 = 0;
            while (i8 < 192) {
                if (Double.compare(d5, selfieSpreadTexture.mStartAltitude) == 0) {
                    d5 -= selfieSpreadTexture.mAltitudeStepAngle;
                    selfieSpreadTexture.calculatePoint(fArr, fArr2, i8, d5, d3);
                    d = d2;
                } else {
                    d = d2;
                    if (d5 < (-(selfieSpreadTexture.mStartAltitude - (selfieSpreadTexture.mAltitudeStepAngle * 2.0d)))) {
                        selfieSpreadTexture.calculatePoint(fArr, fArr2, i8, d5, d3);
                        selfieSpreadTexture = this;
                    } else {
                        selfieSpreadTexture.calculatePoint(fArr, fArr2, i8, d5, d3);
                        i2 = i8;
                        d5 -= selfieSpreadTexture.mAltitudeStepAngle;
                        double d6 = d3 - 0.019634954084936207d;
                        selfieSpreadTexture.calculatePoint(fArr, fArr2, i2 + 1, d5, d6);
                        d3 = d6 + d;
                        i8 = i2 + 2;
                        d2 = d;
                    }
                }
                i2 = i8;
                double d62 = d3 - 0.019634954084936207d;
                selfieSpreadTexture.calculatePoint(fArr, fArr2, i2 + 1, d5, d62);
                d3 = d62 + d;
                i8 = i2 + 2;
                d2 = d;
            }
            selfieSpreadTexture.updatePosBuffers(fArr, fArr2, i7);
        }
    }

    public float getMaxXScroll() {
        return -2.552544f;
    }

    public float getMinXScroll() {
        return -3.7306414f;
    }

    public float getNextTexturePoint(double d) {
        double d2 = this.mStartAltitude;
        return (float) ((((((d2 / (d2 - this.mAltitudeStepAngle)) * d) * ((double) ((float) this.mImageWidth))) / (((double) ((float) this.mImageHeight)) * 3.141592653589793d)) + 1.0d) / 2.0d);
    }

    public void updateGlAttributes() {
        Matrix.setLookAtM(this.mViewMatrix, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -100.0f, 0.0f, 1.0f, 0.0f);
        super.updateGlAttributes("uniform mat4 u_MVPMatrix; \nattribute vec4 a_Position;     \nattribute vec2 a_TexCoordinate;\nvarying vec2 v_TexCoordinate;  \nuniform float u_ThetaX;  \nuniform float u_ThetaY;  \nvoid main()                    \n{                              \nvec4 tempPos = a_Position;\nvec4 rotationMatY = a_Position;\nvec4 rotationMatYX = a_Position;\nrotationMatY.x = a_Position.x * cos(u_ThetaY) - a_Position.z * sin(u_ThetaY); \nrotationMatY.z = a_Position.x * sin(u_ThetaY) + a_Position.z * cos(u_ThetaY); \nrotationMatY.y = a_Position.y; \nrotationMatYX.x = rotationMatY.x; \nrotationMatYX.y = rotationMatY.y * cos(u_ThetaX) - rotationMatY.z * sin(u_ThetaX); \nrotationMatYX.z = rotationMatY.y * sin(u_ThetaX) + rotationMatY.z * cos(u_ThetaX); \ntempPos.x = -2.5 * rotationMatYX.x / (rotationMatYX.z);\ntempPos.y = -2.5 * rotationMatYX.y / (rotationMatYX.z);\nif(tempPos.x > 15.0 || tempPos.x < -15.0 || tempPos.y > 15.0 || tempPos.y < -15.0) {\ntempPos.z = -100000.0; }\nelse { tempPos.z = 0.0; }\nv_TexCoordinate = a_TexCoordinate;\n   gl_Position = u_MVPMatrix   \n               * tempPos;   \n}                              \n");
    }
}
