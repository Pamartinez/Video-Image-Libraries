package com.samsung.android.gallery.module.story.transcode.renderer.render;

import android.opengl.GLES20;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.module.story.transcode.renderer.constants.BlendOption;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Shader implements GLBase {
    private final BlendOption mBlendOption;
    int mRef = 0;
    private final boolean mSupportRoundness;
    int ma_PositionHandle;
    int ma_ReferencePositionHandle;
    int ma_TextureCoordinatesHandle;
    private int mu_AlphaLocation;
    private int mu_MVPMatrixHandle;
    int mu_MaxX;
    int mu_MaxY;
    int mu_Roundness;
    private int mu_STMatrixHandle;
    int mu_TextureUnitHandle;

    public Shader(int i2, BlendOption blendOption, boolean z) {
        this.mBlendOption = blendOption;
        this.mSupportRoundness = z;
        createProgram(i2);
        setHandlesLocation();
    }

    private int compileShader(int i2, String str) {
        int glCreateShader = GLES20.glCreateShader(i2);
        if (glCreateShader == 0) {
            return 0;
        }
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    private void createProgram(int i2) {
        int compileShader = compileShader(35633, "uniform mat4 u_viewMatrix;\nuniform mat4 u_stMatrix;\nattribute vec4 a_position;\nattribute vec4 a_textureCoordinates;\nattribute vec2 a_referencePosition;\nvarying vec2 v_texCoord;\nvarying vec2 v_referencePosition;\nvoid main() {\n    gl_Position = u_viewMatrix * a_position;\n    v_texCoord = (u_stMatrix * a_textureCoordinates).xy;\n    v_referencePosition = a_referencePosition;\n}\n");
        if (compileShader == 0) {
            this.mRef = 0;
        }
        int compileShader2 = compileShader(35632, getFragmentShaderCode(i2));
        if (compileShader2 == 0) {
            GLES20.glDeleteShader(compileShader);
            this.mRef = 0;
        }
        int linkProgram = linkProgram(compileShader, compileShader2);
        this.mRef = linkProgram;
        if (linkProgram == 0) {
            Log.e("Shader", "init shader fail.");
        }
        GLES20.glDeleteShader(compileShader);
        GLES20.glDeleteShader(compileShader2);
    }

    private String getFragmentShaderCode(int i2) {
        if (this.mSupportRoundness) {
            if (i2 == 36197) {
                return GLSLCode.sRoundFragmentShaderCodeExtension;
            }
            return "precision mediump float;\nvarying vec2 v_texCoord;\nuniform sampler2D u_textureUnit;\nuniform vec4 u_roundness; // x,y,z,w -> l-t r-t r-b l-b order.\nuniform float u_textureAlpha;\nvarying vec2 v_referencePosition;\nuniform float u_maxX;\nuniform float u_maxY;\nconst float thickness = 0.005;\nconst float alphaPrecision = 0.05;\n\nvoid main() {\n    float distanceLT = distance(vec2(u_roundness.x, u_maxY - u_roundness.x), v_referencePosition) * step(v_referencePosition.x, u_roundness.x) * step(u_maxY - u_roundness.x, v_referencePosition.y); // outside of masked area distance will be 0.0\n    float alphaMaskLT = 1.0 - smoothstep(u_roundness.x - thickness, u_roundness.x, distanceLT); // outside of masked area alpha will be 1.0\n    float distanceRT = distance(vec2(u_maxX - u_roundness.y, u_maxY - u_roundness.y), v_referencePosition) * step(u_maxX - u_roundness.y, v_referencePosition.x) * step(u_maxY - u_roundness.y, v_referencePosition.y);\n    float alphaMaskRT = 1.0 - smoothstep(u_roundness.y - thickness, u_roundness.y, distanceRT);\n    float distanceRB = distance(vec2(u_maxX - u_roundness.z, u_roundness.z), v_referencePosition) * step(u_maxX - u_roundness.z, v_referencePosition.x) * step(v_referencePosition.y, u_roundness.z);\n    float alphaMaskRB = 1.0 - smoothstep(u_roundness.z - thickness, u_roundness.z, distanceRB);\n    float distanceLB = distance(vec2(u_roundness.w, u_roundness.w), v_referencePosition) * step(v_referencePosition.x, u_roundness.w) * step(v_referencePosition.y, u_roundness.w);\n    float alphaMaskLB = 1.0 - smoothstep(u_roundness.w-thickness, u_roundness.w, distanceLB);\n    gl_FragColor = texture2D(u_textureUnit, v_texCoord);\n    float alphaMask = step(alphaPrecision, gl_FragColor.a);\n    gl_FragColor.a *= mix(gl_FragColor.a, gl_FragColor.a * u_textureAlpha, alphaMask) * alphaMaskLB * alphaMaskRB * alphaMaskLT * alphaMaskRT;\n}\n";
        } else if (i2 == 36197) {
            return GLSLCode.sFragmentShaderCodeExtension;
        } else {
            return "precision mediump float;\nvarying vec2 v_texCoord;\nuniform sampler2D u_textureUnit;\nuniform float u_textureAlpha;\nconst float alphaPrecision = 0.05;\n\nvoid main() {\n    gl_FragColor = texture2D(u_textureUnit, v_texCoord);\n    float alphaMask = step(alphaPrecision, gl_FragColor.a);\n    gl_FragColor.a *= mix(gl_FragColor.a, gl_FragColor.a * u_textureAlpha, alphaMask);\n}\n";
        }
    }

    private int linkProgram(int i2, int i7) {
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram == 0) {
            return 0;
        }
        GLES20.glAttachShader(glCreateProgram, i2);
        GLES20.glAttachShader(glCreateProgram, i7);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateProgram;
        }
        GLES20.glDeleteProgram(glCreateProgram);
        return 0;
    }

    private void setHandlesLocation() {
        this.mu_MVPMatrixHandle = GLES20.glGetUniformLocation(this.mRef, "u_viewMatrix");
        this.mu_STMatrixHandle = GLES20.glGetUniformLocation(this.mRef, "u_stMatrix");
        this.ma_PositionHandle = GLES20.glGetAttribLocation(this.mRef, "a_position");
        this.ma_TextureCoordinatesHandle = GLES20.glGetAttribLocation(this.mRef, "a_textureCoordinates");
        this.mu_TextureUnitHandle = GLES20.glGetUniformLocation(this.mRef, "u_textureUnit");
        this.mu_AlphaLocation = GLES20.glGetUniformLocation(this.mRef, "u_textureAlpha");
        this.ma_ReferencePositionHandle = GLES20.glGetAttribLocation(this.mRef, "a_referencePosition");
        this.mu_Roundness = GLES20.glGetUniformLocation(this.mRef, "u_roundness");
        this.mu_MaxX = GLES20.glGetUniformLocation(this.mRef, "u_maxX");
        this.mu_MaxY = GLES20.glGetUniformLocation(this.mRef, "u_maxY");
    }

    public void applyAlpha(float f) {
        if (this.mBlendOption.supportBlend()) {
            GLES20.glUniform1f(this.mu_AlphaLocation, f);
            GLES20.glEnable(3042);
            GLES20.glBlendFunc(this.mBlendOption.get(), ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8);
        }
    }

    public void applyMatrix(float[] fArr, float[] fArr2) {
        GLES20.glUniformMatrix4fv(this.mu_MVPMatrixHandle, 1, false, fArr, 0);
        GLES20.glUniformMatrix4fv(this.mu_STMatrixHandle, 1, false, fArr2, 0);
    }

    public void release() {
        GLES20.glDeleteProgram(this.mRef);
        this.mRef = 0;
        this.mu_MVPMatrixHandle = 0;
        this.mu_STMatrixHandle = 0;
        this.ma_PositionHandle = 0;
        this.ma_TextureCoordinatesHandle = 0;
        this.mu_TextureUnitHandle = 0;
        this.mu_AlphaLocation = 0;
        this.ma_ReferencePositionHandle = 0;
        this.mu_Roundness = 0;
        this.mu_MaxX = 0;
        this.mu_MaxY = 0;
    }

    public void use() {
        GLES20.glUseProgram(this.mRef);
    }
}
