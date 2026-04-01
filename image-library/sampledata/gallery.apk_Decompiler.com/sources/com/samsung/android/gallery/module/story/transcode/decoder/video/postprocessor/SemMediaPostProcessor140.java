package com.samsung.android.gallery.module.story.transcode.decoder.video.postprocessor;

import A.a;
import android.media.MediaFormat;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.decoder.video.postprocessor.PostProcessor;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.message.Message;
import i.C0212a;
import java.nio.ByteBuffer;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemMediaPostProcessor140 implements PostProcessor {
    private Class<?> mBufferInfoClass;
    private int mFilterIntensity;
    private String mFilterPath;
    private Object mSemMediaPostProcessor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BufferInfoClient implements PostProcessor.BufferInfoWrapper {
        public Object bufferInfoInstance;
        private Integer flags;
        private Integer index;
        private Long timeUs;

        public BufferInfoClient(Object obj) {
            this.bufferInfoInstance = obj;
        }

        public Object getBufferInfo() {
            return this.bufferInfoInstance;
        }

        public int getFlags() {
            if (this.flags == null) {
                try {
                    Integer num = (Integer) Optional.ofNullable(Reflector.getField(this.bufferInfoInstance.getClass(), this.bufferInfoInstance, "flags")).orElse(0);
                    num.intValue();
                    this.flags = num;
                } catch (Exception unused) {
                    this.flags = 0;
                }
            }
            return this.flags.intValue();
        }

        public int getIndex() {
            if (this.index == null) {
                try {
                    Integer num = (Integer) Optional.ofNullable(Reflector.getField(this.bufferInfoInstance.getClass(), this.bufferInfoInstance, "index")).orElse(0);
                    num.intValue();
                    this.index = num;
                } catch (Exception unused) {
                    this.index = 0;
                }
            }
            return this.index.intValue();
        }

        public long getPresentationTimeUs() {
            if (this.timeUs == null) {
                try {
                    Long l = (Long) Optional.ofNullable(Reflector.getField(this.bufferInfoInstance.getClass(), this.bufferInfoInstance, "timeUs")).orElse(0L);
                    l.longValue();
                    this.timeUs = l;
                } catch (Exception unused) {
                    this.timeUs = 0L;
                }
            }
            return this.timeUs.longValue();
        }

        public boolean isEndOfStream() {
            if (getFlags() == 2) {
                return true;
            }
            return false;
        }
    }

    public SemMediaPostProcessor140() {
        create();
    }

    private Object callReflect(String str, Object... objArr) {
        try {
            return Reflector.invoke(this.mSemMediaPostProcessor.getClass(), this.mSemMediaPostProcessor, str, objArr);
        } catch (Error | Exception e) {
            Log.e((CharSequence) "PostProcessor140", C0212a.l("fail to ", str), e.getMessage());
            return null;
        }
    }

    private void create() {
        try {
            this.mSemMediaPostProcessor = Reflector.invoke(Reflector.getClass("com.samsung.android.media.SemMediaPostProcessor"), "createByType", Reflector.getClass("com.samsung.android.media.SemMediaPostProcessor$Type").getEnumConstants()[2]);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("fail to create = "), "PostProcessor140");
        }
        Log.v("PostProcessor140", "SemMediaPostProcessor140 create", this.mSemMediaPostProcessor);
    }

    private Object getConfiguration(MediaFormat mediaFormat) {
        try {
            Class<?> cls = Reflector.getClass("com.samsung.android.media.SemMediaPostProcessor$ProcessingFormat");
            Object newInstance = cls.newInstance();
            Object[] enumConstants = Reflector.getClass("com.samsung.android.media.SemMediaPostProcessor$ColorFormat").getEnumConstants();
            int integer = mediaFormat.getInteger("width");
            int integer2 = mediaFormat.getInteger("height");
            Reflector.invoke(cls, newInstance, "setWidth", Integer.valueOf(integer));
            Reflector.invoke(cls, newInstance, "setHeight", Integer.valueOf(integer2));
            Reflector.invoke(cls, newInstance, "setStride", Integer.valueOf(mediaFormat.getInteger("stride", integer)));
            Reflector.invoke(cls, newInstance, "setElevation", Integer.valueOf(mediaFormat.getInteger("slice-height", integer2)));
            Reflector.invoke(cls, newInstance, "setRotation", Integer.valueOf(mediaFormat.getInteger(Message.KEY_ROTATION, 0)));
            Reflector.invoke(cls, newInstance, "setInputColorFormat", enumConstants[0]);
            Reflector.invoke(cls, newInstance, "setOutputColorFormat", enumConstants[4]);
            MediaFormat mediaFormat2 = mediaFormat;
            setConfiguration(cls, newInstance, mediaFormat2, "setColorRange", "color-range");
            setConfiguration(cls, newInstance, mediaFormat2, "setColorStandard", "color-standard");
            setConfiguration(cls, newInstance, mediaFormat2, "setColorTransfer", "color-transfer");
            setConfiguration(cls, newInstance, mediaFormat2, "setFps", "frame-rate");
            String str = this.mFilterPath;
            if (str == null) {
                str = "";
            }
            Reflector.invoke(cls, newInstance, "setFilterName", str);
            Reflector.invoke(cls, newInstance, "setFilterLevel", Integer.valueOf(this.mFilterIntensity));
            return newInstance;
        } catch (Error | Exception e) {
            Throwable th = e;
            Log.e("PostProcessor140", "fail to getConfiguration = " + th.getMessage());
            th.printStackTrace();
            return null;
        }
    }

    private void setConfiguration(Class<?> cls, Object obj, MediaFormat mediaFormat, String str, String str2) {
        if (mediaFormat.containsKey(str2)) {
            Reflector.invoke(cls, obj, str, Integer.valueOf(mediaFormat.getInteger(str2)));
        }
    }

    public void configure(MediaFormat mediaFormat, Surface surface) {
        try {
            callReflect("configure", getConfiguration(mediaFormat), surface);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("fail to init = "), "PostProcessor140");
        }
    }

    public PostProcessor.BufferInfoWrapper createBufferInfo() {
        try {
            if (this.mBufferInfoClass == null) {
                this.mBufferInfoClass = Reflector.getClass("com.samsung.android.media.SemMediaPostProcessor$BufferInfo");
            }
            return new BufferInfoClient(this.mBufferInfoClass.newInstance());
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("fail to createBufferInfo = "), "PostProcessor140");
            return null;
        }
    }

    public Surface createInputSurface() {
        try {
            return (Surface) Reflector.invoke(this.mSemMediaPostProcessor.getClass(), this.mSemMediaPostProcessor, "createInputSurface");
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("fail to createInputSurface = "), "PostProcessor140");
            return null;
        }
    }

    public ByteBuffer dequeueOutputBuffer(PostProcessor.BufferInfoWrapper bufferInfoWrapper) {
        return (ByteBuffer) callReflect("dequeueOutputBuffer", bufferInfoWrapper.getBufferInfo(), 1000000L);
    }

    public void release() {
        callReflect("release", new Object[0]);
    }

    public void renderAndReleaseOutputBuffer(int i2, long j2, long j3) {
        try {
            callReflect("renderAndReleaseOutputBuffer", Integer.valueOf(i2), Long.valueOf(j2), Long.valueOf(j3));
        } catch (Exception e) {
            Log.e((CharSequence) "PostProcessor140", "fail to renderAndReleaseOutputBuffer", e.getMessage());
        }
    }

    public void setFilter(String str, int i2) {
        this.mFilterPath = str;
        this.mFilterIntensity = i2;
    }

    public void signalEndOfInputStream() {
        callReflect("signalEndOfInputStream", new Object[0]);
    }

    public boolean support() {
        if (this.mSemMediaPostProcessor != null) {
            return true;
        }
        return false;
    }
}
