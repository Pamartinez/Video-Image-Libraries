package com.samsung.android.gallery.image360.engine.texture;

import android.opengl.Matrix;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DefaultSpreadTexture extends BaseSpreadTexture {
    public DefaultSpreadTexture() {
        createStrips();
    }

    private void createStrips() {
        clearBuffers();
        for (int i2 = 0; i2 < 160; i2++) {
            float[] fArr = new float[576];
            float[] fArr2 = new float[384];
            double d = ((double) i2) * 0.039269908169872414d;
            double d2 = 1.5707963267948966d;
            int i7 = 0;
            while (i7 < 192) {
                DefaultSpreadTexture defaultSpreadTexture = this;
                defaultSpreadTexture.calculatePoint(fArr, fArr2, i7, d2, d);
                int i8 = i7;
                d2 -= 0.032724923474893676d;
                double d3 = d - 0.019634954084936207d;
                defaultSpreadTexture.calculatePoint(fArr, fArr2, i8 + 1, d2, d3);
                d = d3 + 0.039269908169872414d;
                i7 = i8 + 2;
                this = defaultSpreadTexture;
            }
            this.updatePosBuffers(fArr, fArr2, i2);
        }
    }

    public float getNextTexturePoint(double d) {
        return (float) ((d + 1.5707963267948966d) / 3.141592653589793d);
    }

    public void updateGlAttributes() {
        Matrix.setLookAtM(this.mViewMatrix, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -100.0f, 0.0f, 1.0f, 0.0f);
        super.updateGlAttributes("uniform mat4 u_MVPMatrix; \nattribute vec4 a_Position;     \nattribute vec2 a_TexCoordinate;\nvarying vec2 v_TexCoordinate;  \nuniform float u_ThetaX;  \nuniform float u_ThetaY;  \nvoid main()                    \n{                              \nvec4 tempPos = a_Position;\nvec4 rotationMatY = a_Position;\nvec4 rotationMatYX = a_Position;\nrotationMatY.x = a_Position.x * cos(u_ThetaY) - a_Position.z * sin(u_ThetaY); \nrotationMatY.z = a_Position.x * sin(u_ThetaY) + a_Position.z * cos(u_ThetaY); \nrotationMatY.y = a_Position.y; \nrotationMatYX.x = rotationMatY.x; \nrotationMatYX.y = rotationMatY.y * cos(u_ThetaX) - rotationMatY.z * sin(u_ThetaX); \nrotationMatYX.z = rotationMatY.y * sin(u_ThetaX) + rotationMatY.z * cos(u_ThetaX); \ntempPos.x = -1.9 * rotationMatYX.x / (rotationMatYX.z);\ntempPos.y = -1.9 * rotationMatYX.y / (rotationMatYX.z);\nif(tempPos.x > 15.0 || tempPos.x < -15.0 || tempPos.y > 15.0 || tempPos.y < -15.0) {\ntempPos.z = -100000.0; }\nelse { tempPos.z = 0.0; }\nv_TexCoordinate = a_TexCoordinate;\n   gl_Position = u_MVPMatrix   \n               * tempPos;   \n}                              \n");
    }
}
