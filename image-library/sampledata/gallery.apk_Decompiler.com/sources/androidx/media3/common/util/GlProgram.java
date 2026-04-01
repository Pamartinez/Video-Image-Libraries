package androidx.media3.common.util;

import android.content.Context;
import android.opengl.GLES20;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GlProgram {
    private final Map<String, Attribute> attributeByName;
    private final Attribute[] attributes;
    private boolean externalTexturesRequireNearestSampling;
    private final int programId;
    private final Map<String, Uniform> uniformByName;
    private final Uniform[] uniforms;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Attribute {
        private Buffer buffer;
        private final int location;
        public final String name;
        private int size;

        private Attribute(String str, int i2) {
            this.name = str;
            this.location = i2;
        }

        public static Attribute create(int i2, int i7) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i2, 35722, iArr, 0);
            int i8 = iArr[0];
            byte[] bArr = new byte[i8];
            GLES20.glGetActiveAttrib(i2, i7, i8, new int[1], 0, new int[1], 0, new int[1], 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Attribute(str, GlProgram.getAttributeLocation(i2, str));
        }

        public void bind() {
            GLES20.glBindBuffer(34962, 0);
            GLES20.glVertexAttribPointer(this.location, this.size, 5126, false, 0, (Buffer) Assertions.checkNotNull(this.buffer, "call setBuffer before bind"));
            GLES20.glEnableVertexAttribArray(this.location);
            GlUtil.checkGlError();
        }

        public void setBuffer(float[] fArr, int i2) {
            this.buffer = GlUtil.createBuffer(fArr);
            this.size = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Uniform {
        private final float[] floatValue = new float[16];
        private final int[] intValue = new int[4];
        private final int location;
        public final String name;
        private int texIdValue;
        private int texMinFilter = 9729;
        private int texUnitIndex;
        private final int type;

        private Uniform(String str, int i2, int i7) {
            this.name = str;
            this.location = i2;
            this.type = i7;
        }

        public static Uniform create(int i2, int i7) {
            int[] iArr = new int[1];
            GLES20.glGetProgramiv(i2, 35719, iArr, 0);
            int[] iArr2 = new int[1];
            int i8 = iArr[0];
            byte[] bArr = new byte[i8];
            GLES20.glGetActiveUniform(i2, i7, i8, new int[1], 0, new int[1], 0, iArr2, 0, bArr, 0);
            String str = new String(bArr, 0, GlProgram.getCStringLength(bArr));
            return new Uniform(str, GlProgram.getUniformLocation(i2, str), iArr2[0]);
        }

        public void bind(boolean z) {
            int i2;
            int i7;
            int i8 = this.type;
            if (i8 == 5124) {
                GLES20.glUniform1iv(this.location, 1, this.intValue, 0);
                GlUtil.checkGlError();
            } else if (i8 == 5126) {
                GLES20.glUniform1fv(this.location, 1, this.floatValue, 0);
                GlUtil.checkGlError();
            } else if (i8 != 35678 && i8 != 35815 && i8 != 36198) {
                switch (i8) {
                    case 35664:
                        GLES20.glUniform2fv(this.location, 1, this.floatValue, 0);
                        GlUtil.checkGlError();
                        return;
                    case 35665:
                        GLES20.glUniform3fv(this.location, 1, this.floatValue, 0);
                        GlUtil.checkGlError();
                        return;
                    case 35666:
                        GLES20.glUniform4fv(this.location, 1, this.floatValue, 0);
                        GlUtil.checkGlError();
                        return;
                    case 35667:
                        GLES20.glUniform2iv(this.location, 1, this.intValue, 0);
                        GlUtil.checkGlError();
                        return;
                    case 35668:
                        GLES20.glUniform3iv(this.location, 1, this.intValue, 0);
                        GlUtil.checkGlError();
                        return;
                    case 35669:
                        GLES20.glUniform4iv(this.location, 1, this.intValue, 0);
                        GlUtil.checkGlError();
                        return;
                    default:
                        switch (i8) {
                            case 35675:
                                GLES20.glUniformMatrix3fv(this.location, 1, false, this.floatValue, 0);
                                GlUtil.checkGlError();
                                return;
                            case 35676:
                                GLES20.glUniformMatrix4fv(this.location, 1, false, this.floatValue, 0);
                                GlUtil.checkGlError();
                                return;
                            default:
                                throw new IllegalStateException("Unexpected uniform type: " + this.type);
                        }
                }
            } else if (this.texIdValue != 0) {
                GLES20.glActiveTexture(this.texUnitIndex + 33984);
                GlUtil.checkGlError();
                int i10 = this.type;
                if (i10 == 35678) {
                    i2 = 3553;
                } else {
                    i2 = 36197;
                }
                int i11 = this.texIdValue;
                if (i10 == 35678 || !z) {
                    i7 = 9729;
                } else {
                    i7 = 9728;
                }
                GlUtil.bindTexture(i2, i11, i7);
                if (this.type == 35678) {
                    if (this.texMinFilter == 9987) {
                        GLES20.glGenerateMipmap(3553);
                        GlUtil.checkGlError();
                    }
                    GLES20.glTexParameteri(3553, 10241, this.texMinFilter);
                    GlUtil.checkGlError();
                }
                GLES20.glUniform1i(this.location, this.texUnitIndex);
                GlUtil.checkGlError();
            } else {
                throw new IllegalStateException("No call to setSamplerTexId() before bind.");
            }
        }

        public void setFloat(float f) {
            this.floatValue[0] = f;
        }

        public void setFloats(float[] fArr) {
            System.arraycopy(fArr, 0, this.floatValue, 0, fArr.length);
        }

        public void setInt(int i2) {
            this.intValue[0] = i2;
        }

        public void setSamplerTexId(int i2, int i7) {
            this.texIdValue = i2;
            this.texUnitIndex = i7;
        }

        public void setTexMinFilter(int i2) {
            this.texMinFilter = i2;
        }
    }

    public GlProgram(Context context, String str, String str2) {
        this(Util.loadAsset(context, str), Util.loadAsset(context, str2));
    }

    private static void addShader(int i2, int i7, String str) {
        int glCreateShader = GLES20.glCreateShader(i7);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        boolean z = false;
        int[] iArr = {0};
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 1) {
            z = true;
        }
        GlUtil.checkGlException(z, GLES20.glGetShaderInfoLog(glCreateShader) + ", source: \n" + str);
        GLES20.glAttachShader(i2, glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        GlUtil.checkGlError();
    }

    /* access modifiers changed from: private */
    public static int getAttributeLocation(int i2, String str) {
        return GLES20.glGetAttribLocation(i2, str);
    }

    /* access modifiers changed from: private */
    public static int getCStringLength(byte[] bArr) {
        for (int i2 = 0; i2 < bArr.length; i2++) {
            if (bArr[i2] == 0) {
                return i2;
            }
        }
        return bArr.length;
    }

    /* access modifiers changed from: private */
    public static int getUniformLocation(int i2, String str) {
        return GLES20.glGetUniformLocation(i2, str);
    }

    public void bindAttributesAndUniforms() {
        for (Attribute bind : this.attributes) {
            bind.bind();
        }
        for (Uniform bind2 : this.uniforms) {
            bind2.bind(this.externalTexturesRequireNearestSampling);
        }
    }

    public void delete() {
        GLES20.glDeleteProgram(this.programId);
        GlUtil.checkGlError();
    }

    public void setBufferAttribute(String str, float[] fArr, int i2) {
        ((Attribute) Assertions.checkNotNull(this.attributeByName.get(str))).setBuffer(fArr, i2);
    }

    public void setExternalTexturesRequireNearestSampling(boolean z) {
        this.externalTexturesRequireNearestSampling = z;
    }

    public void setFloatUniform(String str, float f) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setFloat(f);
    }

    public void setFloatsUniform(String str, float[] fArr) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setFloats(fArr);
    }

    public void setFloatsUniformIfPresent(String str, float[] fArr) {
        Uniform uniform = this.uniformByName.get(str);
        if (uniform != null) {
            uniform.setFloats(fArr);
        }
    }

    public void setIntUniform(String str, int i2) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setInt(i2);
    }

    public void setSamplerTexIdUniform(String str, int i2, int i7) {
        ((Uniform) Assertions.checkNotNull(this.uniformByName.get(str))).setSamplerTexId(i2, i7);
    }

    public void use() {
        GLES20.glUseProgram(this.programId);
        GlUtil.checkGlError();
    }

    public void setSamplerTexIdUniform(String str, int i2, int i7, int i8) {
        Uniform uniform = (Uniform) Assertions.checkNotNull(this.uniformByName.get(str));
        uniform.setSamplerTexId(i2, i7);
        uniform.setTexMinFilter(i8);
    }

    public GlProgram(String str, String str2) {
        int glCreateProgram = GLES20.glCreateProgram();
        this.programId = glCreateProgram;
        GlUtil.checkGlError();
        addShader(glCreateProgram, 35633, str);
        addShader(glCreateProgram, 35632, str2);
        GLES20.glLinkProgram(glCreateProgram);
        int[] iArr = {0};
        GLES20.glGetProgramiv(glCreateProgram, 35714, iArr, 0);
        GlUtil.checkGlException(iArr[0] == 1, "Unable to link shader program: \n" + GLES20.glGetProgramInfoLog(glCreateProgram));
        GLES20.glUseProgram(glCreateProgram);
        this.attributeByName = new HashMap();
        int[] iArr2 = new int[1];
        GLES20.glGetProgramiv(glCreateProgram, 35721, iArr2, 0);
        this.attributes = new Attribute[iArr2[0]];
        for (int i2 = 0; i2 < iArr2[0]; i2++) {
            Attribute create = Attribute.create(this.programId, i2);
            this.attributes[i2] = create;
            this.attributeByName.put(create.name, create);
        }
        this.uniformByName = new HashMap();
        int[] iArr3 = new int[1];
        GLES20.glGetProgramiv(this.programId, 35718, iArr3, 0);
        this.uniforms = new Uniform[iArr3[0]];
        for (int i7 = 0; i7 < iArr3[0]; i7++) {
            Uniform create2 = Uniform.create(this.programId, i7);
            this.uniforms[i7] = create2;
            this.uniformByName.put(create2.name, create2);
        }
        GlUtil.checkGlError();
    }
}
