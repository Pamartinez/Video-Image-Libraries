package androidx.media3.transformer;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.transformer.TransformationRequest;
import androidx.media3.transformer.Transformer;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class FallbackListener {
    private final Composition composition;
    private TransformationRequest fallbackTransformationRequest;
    private final TransformationRequest originalTransformationRequest;
    private final AtomicInteger trackCount = new AtomicInteger();
    private final HandlerWrapper transformerListenerHandler;
    private final ListenerSet<Transformer.Listener> transformerListeners;

    public FallbackListener(Composition composition2, ListenerSet<Transformer.Listener> listenerSet, HandlerWrapper handlerWrapper, TransformationRequest transformationRequest) {
        this.composition = composition2;
        this.transformerListeners = listenerSet;
        this.transformerListenerHandler = handlerWrapper;
        this.originalTransformationRequest = transformationRequest;
        this.fallbackTransformationRequest = transformationRequest;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTransformationRequestFinalized$0(TransformationRequest transformationRequest, Transformer.Listener listener) {
        listener.onFallbackApplied(this.composition, this.originalTransformationRequest, transformationRequest);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTransformationRequestFinalized$1(TransformationRequest transformationRequest) {
        this.transformerListeners.sendEvent(-1, new b(this, transformationRequest));
    }

    public synchronized void onTransformationRequestFinalized(TransformationRequest transformationRequest) {
        boolean z;
        try {
            if (this.trackCount.getAndDecrement() > 0) {
                z = true;
            } else {
                z = false;
            }
            Assertions.checkState(z);
            TransformationRequest.Builder buildUpon = this.fallbackTransformationRequest.buildUpon();
            if (!Objects.equals(transformationRequest.audioMimeType, this.originalTransformationRequest.audioMimeType)) {
                buildUpon.setAudioMimeType(transformationRequest.audioMimeType);
            }
            if (!Objects.equals(transformationRequest.videoMimeType, this.originalTransformationRequest.videoMimeType)) {
                buildUpon.setVideoMimeType(transformationRequest.videoMimeType);
            }
            int i2 = transformationRequest.outputHeight;
            if (i2 != this.originalTransformationRequest.outputHeight) {
                buildUpon.setResolution(i2);
            }
            int i7 = transformationRequest.hdrMode;
            if (i7 != this.originalTransformationRequest.hdrMode) {
                buildUpon.setHdrMode(i7);
            }
            TransformationRequest build = buildUpon.build();
            this.fallbackTransformationRequest = build;
            if (this.trackCount.get() == 0 && !this.originalTransformationRequest.equals(this.fallbackTransformationRequest)) {
                this.transformerListenerHandler.post(new d(0, this, build));
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void setTrackCount(int i2) {
        this.trackCount.set(i2);
    }
}
