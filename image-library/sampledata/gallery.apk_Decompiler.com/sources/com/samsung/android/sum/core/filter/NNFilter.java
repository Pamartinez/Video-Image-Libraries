package com.samsung.android.sum.core.filter;

import J5.c;
import Qa.a;
import android.media.MediaFormat;
import com.samsung.android.motionphoto.utils.ex.b;
import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.DeriveBufferGroup;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.MediaBufferGroup;
import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.descriptor.MFDescriptor;
import com.samsung.android.sum.core.descriptor.NNDescriptor;
import com.samsung.android.sum.core.functional.BufferProcessor;
import com.samsung.android.sum.core.message.Message;
import com.samsung.android.sum.core.plugin.NNPlugin;
import com.samsung.android.sum.core.types.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NNFilter extends PluginDecorateFilter<NNPlugin> {
    private static final String TAG = Def.tagOf((Class<?>) NNFilter.class);
    private NNDescriptor descriptor;

    public NNFilter(NNPlugin nNPlugin, MediaFilter mediaFilter) {
        super(nNPlugin, mediaFilter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ NNDescriptor lambda$getDescriptor$0() {
        return (NNDescriptor) super.getDescriptor();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMessageReceived$5(Consumer consumer) {
        consumer.accept(this.descriptor);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$run$1(MediaBuffer mediaBuffer, BufferProcessor bufferProcessor) {
        return bufferProcessor.process(mediaBuffer, this.descriptor.getFilterOption());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MediaBuffer lambda$run$2(MediaBuffer mediaBuffer) {
        return super.run(mediaBuffer, MediaBuffer.mutableOf(new Object[0])).reset();
    }

    public MFDescriptor getDescriptor() {
        return (MFDescriptor) Optional.ofNullable(this.descriptor).orElseGet(new c(21, this));
    }

    public int[] getMessagesToReceive() {
        return new int[]{201};
    }

    public boolean onMessageReceived(Message message) {
        if (message.getCode() != 201) {
            return super.onMessageReceived(message);
        }
        MediaType mediaType = (MediaType) message.get(Message.KEY_MEDIA_TYPE);
        MediaFormat mediaFormat = (MediaFormat) message.get("media-format");
        if (this.descriptor.getMediaType().isVideo() && mediaType.isVideo()) {
            Optional.ofNullable(this.descriptor.getModelSelector()).map(new b(10, com.samsung.android.sum.core.format.MediaFormat.newImageBuilder().setShape(mediaFormat.getInteger("width"), mediaFormat.getInteger("height")).build())).flatMap(new n(0)).ifPresent(new com.samsung.android.sum.core.descriptor.b(6, this));
            return true;
        } else if (!this.descriptor.getMediaType().isAudio() || !mediaType.isAudio()) {
            return true;
        } else {
            throw new UnsupportedOperationException("not implemented yet for MutableMediaFormat");
        }
    }

    public void prepare() {
        super.prepare();
    }

    public void release() {
        super.release();
    }

    public MutableMediaBuffer run(MediaBuffer mediaBuffer, MutableMediaBuffer mutableMediaBuffer) {
        ArrayList arrayList;
        MediaBuffer mediaBuffer2;
        MediaBuffer mediaBuffer3 = (MediaBuffer) ((NNPlugin) this.plugin).getPreExecutor().map(new a(8, (Object) this, (Object) mediaBuffer)).orElse(mediaBuffer);
        mediaBuffer3.addExtra(mediaBuffer.getExtra());
        String str = TAG;
        SLog.d(str, "input=" + mediaBuffer3);
        if (this.descriptor.getFilterOption().isBatchIO()) {
            ArrayList arrayList2 = new ArrayList();
            super.run(mediaBuffer3, mutableMediaBuffer);
            arrayList2.add(mutableMediaBuffer.reset());
            arrayList = arrayList2;
        } else {
            arrayList = (List) mediaBuffer3.stream().map(new b(9, this)).collect(Collectors.toList());
        }
        if (mediaBuffer3 instanceof DeriveBufferGroup) {
            arrayList.add(0, ((DeriveBufferGroup) mediaBuffer3).getPrimaryBuffer());
            mediaBuffer2 = MediaBuffer.newGroupAlloc().setBuffers(0, (List<MediaBuffer>) arrayList).allocate();
        } else if (mediaBuffer3 instanceof MediaBufferGroup) {
            mediaBuffer2 = MediaBuffer.newGroupAlloc().setBuffers(arrayList).allocate();
        } else {
            mediaBuffer2 = (MediaBuffer) arrayList.get(0);
        }
        mediaBuffer2.addExtra(mediaBuffer3.getExtra());
        mediaBuffer3.release();
        if (((NNPlugin) this.plugin).getPostExecutor() != null) {
            mediaBuffer2 = ((NNPlugin) this.plugin).getPostExecutor().process(mediaBuffer2, this.descriptor.getFilterOption());
        }
        if (mediaBuffer2 instanceof MutableMediaBuffer) {
            mutableMediaBuffer.put(((MutableMediaBuffer) mediaBuffer2).reset());
            return mutableMediaBuffer;
        }
        mutableMediaBuffer.put(mediaBuffer2);
        return mutableMediaBuffer;
    }

    public NNFilter(NNDescriptor nNDescriptor, NNPlugin nNPlugin, MediaFilter mediaFilter) {
        super(nNPlugin, mediaFilter);
        this.descriptor = nNDescriptor;
    }
}
