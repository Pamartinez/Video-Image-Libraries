package com.samsung.android.sdk.sgpl.pip.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import c0.C0086a;
import java.nio.Buffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OpenGlHelper {
    public static int checkGLError(String str) {
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                return glGetError;
            }
            Log.e(Constants.TAG, str + ": glError " + glGetError);
        }
    }

    private static int compileFragmentShader(String str) {
        return compileShader(35632, str);
    }

    private static int compileShader(int i2, String str) {
        int glCreateShader = GLES20.glCreateShader(i2);
        if (glCreateShader == 0) {
            checkGLError("shader type " + i2 + " creation failded");
            return 0;
        }
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder o2 = C0086a.o(i2, "Could not compile shader ", " ");
        o2.append(GLES20.glGetShaderInfoLog(glCreateShader));
        Log.e(Constants.TAG, o2.toString());
        GLES20.glDeleteShader(glCreateShader);
        return 0;
    }

    private static int compileVertexShader(String str) {
        return compileShader(35633, str);
    }

    public static int createProgram(String str, String str2) {
        int compileVertexShader = compileVertexShader(str);
        if (compileVertexShader == 0) {
            return 0;
        }
        int compileFragmentShader = compileFragmentShader(str2);
        if (compileFragmentShader == 0) {
            GLES20.glDeleteShader(compileVertexShader);
            return 0;
        }
        int linkProgram = linkProgram(compileVertexShader, compileFragmentShader);
        GLES20.glDeleteShader(compileVertexShader);
        GLES20.glDeleteShader(compileVertexShader);
        return linkProgram;
    }

    public static void deleteTexture(int i2) {
        GLES20.glDeleteTextures(1, new int[]{i2}, 0);
    }

    private static int getOptimalSamplingSize(String str, int i2, int i7, int i8) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        float f = ((float) options.outWidth) / ((float) i2);
        float f5 = ((float) options.outHeight) / ((float) i7);
        if (f < f5) {
            f = f5;
        }
        return Math.round(f);
    }

    private static int linkProgram(int i2, int i7) {
        int glCreateProgram = GLES20.glCreateProgram();
        if (glCreateProgram == 0) {
            checkGLError("CreateProgram failed");
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
        Log.e(Constants.TAG, "Couldn't link program :" + GLES20.glGetProgramInfoLog(glCreateProgram));
        GLES20.glDeleteProgram(glCreateProgram);
        return 0;
    }

    public static int loadMainTexture(int i2, int i7) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i8 = iArr[0];
        if (i8 == 0) {
            Log.e(Constants.TAG, "Could not create new opengl oes texture object");
            return 0;
        }
        GLES20.glBindTexture(3553, i8);
        if (checkGLError("glBindTexture error") != 0) {
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        }
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        if (checkGLError("glTexParameter error") != 0) {
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        }
        GLES20.glTexImage2D(3553, 0, 6408, i2, i7, 0, 6408, 5121, (Buffer) null);
        if (checkGLError("texImage2D error") != 0) {
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        }
        GLES20.glGenerateMipmap(3553);
        GLES20.glBindTexture(3553, iArr[0]);
        return iArr[0];
    }

    public static int loadTexture(String str, int i2, int i7, Rect rect) {
        int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = getOptimalSamplingSize(str, i2, i7, attributeInt);
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        if (decodeFile == null) {
            Log.e(Constants.TAG, "Could not decode bitmap. error.");
            return 0;
        }
        rect.left = 0;
        rect.top = 0;
        rect.right = decodeFile.getWidth();
        rect.bottom = decodeFile.getHeight();
        int loadTexture = loadTexture(decodeFile);
        decodeFile.recycle();
        return loadTexture;
    }

    public static int loadTextureOES() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i2 = iArr[0];
        if (i2 == 0) {
            Log.e(Constants.TAG, "Could not create new opengl oes texture object");
            return 0;
        }
        GLES20.glBindTexture(36197, i2);
        if (checkGLError("glBindTexture error") != 0) {
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        }
        GLES20.glTexParameterf(36197, 10241, 9729.0f);
        GLES20.glTexParameterf(36197, 10240, 9729.0f);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        if (checkGLError("External OES parameter set error.") == 0) {
            return iArr[0];
        }
        GLES20.glDeleteTextures(1, iArr, 0);
        return 0;
    }

    public static void deleteTexture(int[] iArr) {
        GLES20.glDeleteTextures(1, iArr, 0);
    }

    public static int loadTexture(Bitmap bitmap) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        int i2 = iArr[0];
        if (i2 == 0) {
            Log.e(Constants.TAG, "Could not create new opengl texture object");
            Log.d("framebuffer", "loadTexture: error -1 ");
            return 0;
        }
        GLES20.glBindTexture(3553, i2);
        if (checkGLError("glBindTexture error") != 0) {
            Log.d("framebuffer", "loadTexture: error0 ");
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        }
        GLES20.glTexParameteri(3553, 10241, 9729);
        GLES20.glTexParameteri(3553, 10240, 9729);
        GLES20.glTexParameterf(3553, 10242, 33071.0f);
        GLES20.glTexParameterf(3553, 10243, 33071.0f);
        if (checkGLError("glTexParameter error") != 0) {
            Log.d("framebuffer", "loadTexture: error1 ");
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        }
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        if (checkGLError("texImage2D error") != 0) {
            Log.d("framebuffer", "loadTexture: error2 ");
            GLES20.glDeleteTextures(1, iArr, 0);
            return 0;
        }
        GLES20.glGenerateMipmap(3553);
        GLES20.glBindTexture(3553, 0);
        Log.d("framebuffer", "loadTexture:  " + iArr[0]);
        return iArr[0];
    }
}
