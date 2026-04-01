package androidx.media3.effect;

import android.content.Context;
import android.opengl.GLES20;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.GlProgram;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Size;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class FrameCacheGlShaderProgram extends BaseGlShaderProgram {
    private final GlProgram copyProgram;

    public FrameCacheGlShaderProgram(Context context, int i2, boolean z) {
        super(z, i2);
        try {
            GlProgram glProgram = new GlProgram(context, "shaders/vertex_shader_transformation_es2.glsl", "shaders/fragment_shader_transformation_es2.glsl");
            this.copyProgram = glProgram;
            float[] create4x4IdentityMatrix = GlUtil.create4x4IdentityMatrix();
            glProgram.setFloatsUniform("uTexTransformationMatrix", create4x4IdentityMatrix);
            glProgram.setFloatsUniform("uTransformationMatrix", create4x4IdentityMatrix);
            glProgram.setFloatsUniform("uRgbMatrix", create4x4IdentityMatrix);
            glProgram.setBufferAttribute("aFramePosition", GlUtil.getNormalizedCoordinateBounds(), 4);
        } catch (GlUtil.GlException | IOException e) {
            throw VideoFrameProcessingException.from(e);
        }
    }

    public Size configure(int i2, int i7) {
        return new Size(i2, i7);
    }

    public void drawFrame(int i2, long j2) {
        try {
            this.copyProgram.use();
            this.copyProgram.setSamplerTexIdUniform("uTexSampler", i2, 0);
            this.copyProgram.bindAttributesAndUniforms();
            GLES20.glDrawArrays(5, 0, 4);
        } catch (GlUtil.GlException e) {
            throw VideoFrameProcessingException.from(e);
        }
    }

    public void release() {
        super.release();
        try {
            this.copyProgram.delete();
        } catch (GlUtil.GlException e) {
            throw new VideoFrameProcessingException((Throwable) e);
        }
    }
}
