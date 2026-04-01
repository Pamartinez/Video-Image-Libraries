package com.samsung.android.gallery.image360.engine.util;

import android.opengl.GLES20;
import android.util.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ShaderHelper {
    public static int createProgram(int i2, int i7, String[] strArr) {
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram != 0) {
            GLES20.glAttachShader(glCreateProgram, i2);
            GLES20.glAttachShader(glCreateProgram, i7);
            if (strArr != null) {
                int length = strArr.length;
                for (int i8 = 0; i8 < length; i8++) {
                    GLES20.glBindAttribLocation(glCreateProgram, i8, strArr[i8]);
                }
            }
            glCreateProgram = linkProgram(glCreateProgram);
        }
        if (glCreateProgram == 0) {
            Log.e("ShaderHelper", "Error Creating Program");
        }
        return glCreateProgram;
    }

    private static int linkProgram(int i2) {
        GLES20.glLinkProgram(i2);
        int[] iArr = new int[1];
        GLES20.glGetProgramiv(i2, 35714, iArr, 0);
        if (iArr[0] != 0) {
            return i2;
        }
        Log.e("ShaderHelper", "Error compiling program: " + GLES20.glGetProgramInfoLog(i2));
        GLES20.glDeleteProgram(i2);
        return 0;
    }

    public static int loadShader(int i2, String str) {
        int glCreateShader = GLES20.glCreateShader(i2);
        if (glCreateShader != 0) {
            GLES20.glShaderSource(glCreateShader, str);
            GLES20.glCompileShader(glCreateShader);
            int[] iArr = new int[1];
            GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
            if (iArr[0] == 0) {
                Log.e("ShaderHelper", "Error compiling shader: " + GLES20.glGetShaderInfoLog(glCreateShader));
                GLES20.glDeleteShader(glCreateShader);
                glCreateShader = 0;
            }
        }
        if (glCreateShader == 0) {
            Log.e("ShaderHelper", "Error Creating Shader");
        }
        return glCreateShader;
    }
}
