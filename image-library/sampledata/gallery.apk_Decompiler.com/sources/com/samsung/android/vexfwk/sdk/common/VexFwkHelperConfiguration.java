package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import android.view.Surface;
import com.samsung.android.vexfwk.session.VexFwkStreamType;
import com.samsung.android.vexfwk.session.VexFwkStreamUsage;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1194l;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0003\u0014\u0015\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R=\u0010\n\u001a(\u0012$\u0012\"\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0002`\t0\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rRd\u0010\u0013\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0002`\t2&\u0010\u000e\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005j\u0002`\t8F@FX\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration;", "", "<init>", "()V", "", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$Session;", "Lqe/c;", "Lme/x;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperSessionInitializer;", "sessions", "Ljava/util/List;", "getSessions", "()Ljava/util/List;", "value", "getSession", "()LAe/c;", "setSession", "(LAe/c;)V", "session", "BufferStream", "SurfaceStream", "Session", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperConfiguration {
    private final List<c> sessions = new ArrayList();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$BufferStream;", "", "id", "", "type", "Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "usage", "Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "<init>", "(Ljava/lang/Integer;Lcom/samsung/android/vexfwk/session/VexFwkStreamType;Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getType", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "setType", "(Lcom/samsung/android/vexfwk/session/VexFwkStreamType;)V", "getUsage", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "setUsage", "(Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;)V", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Lcom/samsung/android/vexfwk/session/VexFwkStreamType;Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;)Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$BufferStream;", "equals", "", "other", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BufferStream {
        private Integer id;
        private VexFwkStreamType type;
        private VexFwkStreamUsage usage;

        public BufferStream() {
            this((Integer) null, (VexFwkStreamType) null, (VexFwkStreamUsage) null, 7, (e) null);
        }

        public static /* synthetic */ BufferStream copy$default(BufferStream bufferStream, Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                num = bufferStream.id;
            }
            if ((i2 & 2) != 0) {
                vexFwkStreamType = bufferStream.type;
            }
            if ((i2 & 4) != 0) {
                vexFwkStreamUsage = bufferStream.usage;
            }
            return bufferStream.copy(num, vexFwkStreamType, vexFwkStreamUsage);
        }

        public final Integer component1() {
            return this.id;
        }

        public final VexFwkStreamType component2() {
            return this.type;
        }

        public final VexFwkStreamUsage component3() {
            return this.usage;
        }

        public final BufferStream copy(Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage) {
            return new BufferStream(num, vexFwkStreamType, vexFwkStreamUsage);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BufferStream)) {
                return false;
            }
            BufferStream bufferStream = (BufferStream) obj;
            if (j.a(this.id, bufferStream.id) && this.type == bufferStream.type && this.usage == bufferStream.usage) {
                return true;
            }
            return false;
        }

        public final Integer getId() {
            return this.id;
        }

        public final VexFwkStreamType getType() {
            return this.type;
        }

        public final VexFwkStreamUsage getUsage() {
            return this.usage;
        }

        public int hashCode() {
            int i2;
            int i7;
            Integer num = this.id;
            int i8 = 0;
            if (num == null) {
                i2 = 0;
            } else {
                i2 = num.hashCode();
            }
            int i10 = i2 * 31;
            VexFwkStreamType vexFwkStreamType = this.type;
            if (vexFwkStreamType == null) {
                i7 = 0;
            } else {
                i7 = vexFwkStreamType.hashCode();
            }
            int i11 = (i10 + i7) * 31;
            VexFwkStreamUsage vexFwkStreamUsage = this.usage;
            if (vexFwkStreamUsage != null) {
                i8 = vexFwkStreamUsage.hashCode();
            }
            return i11 + i8;
        }

        public final void setId(Integer num) {
            this.id = num;
        }

        public final void setType(VexFwkStreamType vexFwkStreamType) {
            this.type = vexFwkStreamType;
        }

        public final void setUsage(VexFwkStreamUsage vexFwkStreamUsage) {
            this.usage = vexFwkStreamUsage;
        }

        public String toString() {
            Integer num = this.id;
            VexFwkStreamType vexFwkStreamType = this.type;
            VexFwkStreamUsage vexFwkStreamUsage = this.usage;
            return "BufferStream(id=" + num + ", type=" + vexFwkStreamType + ", usage=" + vexFwkStreamUsage + ")";
        }

        public BufferStream(Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage) {
            this.id = num;
            this.type = vexFwkStreamType;
            this.usage = vexFwkStreamUsage;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ BufferStream(Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : num, (i2 & 2) != 0 ? null : vexFwkStreamType, (i2 & 4) != 0 ? null : vexFwkStreamUsage);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u0001BA\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012,\b\u0002\u0010\t\u001a&\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004j\u0004\u0018\u0001`\b¢\u0006\u0004\b\n\u0010\u000bR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010RF\u0010\t\u001a&\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0004j\u0004\u0018\u0001`\b8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R=\u0010\u0019\u001a(\u0012$\u0012\"\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u00180\u00168\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR=\u0010\u001f\u001a(\u0012$\u0012\"\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u001e0\u00168\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001a\u001a\u0004\b \u0010\u001cR=\u0010!\u001a(\u0012$\u0012\"\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u00180\u00168\u0006¢\u0006\f\n\u0004\b!\u0010\u001a\u001a\u0004\b\"\u0010\u001cR=\u0010#\u001a(\u0012$\u0012\"\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u001e0\u00168\u0006¢\u0006\f\n\u0004\b#\u0010\u001a\u001a\u0004\b$\u0010\u001cRd\u0010(\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u00182&\u0010%\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u00188F@FX\u000e¢\u0006\f\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015Rd\u0010+\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u001e2&\u0010%\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u001e8F@FX\u000e¢\u0006\f\u001a\u0004\b)\u0010\u0013\"\u0004\b*\u0010\u0015Rd\u0010.\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u00182&\u0010%\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u00188F@FX\u000e¢\u0006\f\u001a\u0004\b,\u0010\u0013\"\u0004\b-\u0010\u0015Rd\u00101\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u001e2&\u0010%\u001a\"\b\u0001\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004j\u0002`\u001e8F@FX\u000e¢\u0006\f\u001a\u0004\b/\u0010\u0013\"\u0004\b0\u0010\u0015¨\u00062"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$Session;", "", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "usecase", "Lkotlin/Function2;", "Lcom/samsung/android/vexfwk/metadata/VexFwkMetadataNative;", "Lqe/c;", "Lme/x;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperMetadataInitializer;", "configMetadata", "<init>", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;LAe/c;)V", "Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "getUsecase", "()Lcom/samsung/android/vexfwk/session/VexFwkUsecase;", "setUsecase", "(Lcom/samsung/android/vexfwk/session/VexFwkUsecase;)V", "LAe/c;", "getConfigMetadata", "()LAe/c;", "setConfigMetadata", "(LAe/c;)V", "", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$BufferStream;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBufferStreamInitializer;", "inputBufferStreams", "Ljava/util/List;", "getInputBufferStreams", "()Ljava/util/List;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$SurfaceStream;", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperSurfaceStreamInitializer;", "inputSurfaceStreams", "getInputSurfaceStreams", "outputBufferStreams", "getOutputBufferStreams", "outputSurfaceStreams", "getOutputSurfaceStreams", "value", "getInputBufferStream", "setInputBufferStream", "inputBufferStream", "getInputSurfaceStream", "setInputSurfaceStream", "inputSurfaceStream", "getOutputBufferStream", "setOutputBufferStream", "outputBufferStream", "getOutputSurfaceStream", "setOutputSurfaceStream", "outputSurfaceStream", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Session {
        private c configMetadata;
        private final List<c> inputBufferStreams;
        private final List<c> inputSurfaceStreams;
        private final List<c> outputBufferStreams;
        private final List<c> outputSurfaceStreams;
        private VexFwkUsecase usecase;

        public Session() {
            this((VexFwkUsecase) null, (c) null, 3, (e) null);
        }

        public final c getConfigMetadata() {
            return this.configMetadata;
        }

        public final c getInputBufferStream() {
            return (c) C1194l.T0(this.inputBufferStreams);
        }

        public final List<c> getInputBufferStreams() {
            return this.inputBufferStreams;
        }

        public final c getInputSurfaceStream() {
            return (c) C1194l.T0(this.inputSurfaceStreams);
        }

        public final List<c> getInputSurfaceStreams() {
            return this.inputSurfaceStreams;
        }

        public final c getOutputBufferStream() {
            return (c) C1194l.T0(this.outputBufferStreams);
        }

        public final List<c> getOutputBufferStreams() {
            return this.outputBufferStreams;
        }

        public final c getOutputSurfaceStream() {
            return (c) C1194l.T0(this.outputSurfaceStreams);
        }

        public final List<c> getOutputSurfaceStreams() {
            return this.outputSurfaceStreams;
        }

        public final VexFwkUsecase getUsecase() {
            return this.usecase;
        }

        public final void setConfigMetadata(c cVar) {
            this.configMetadata = cVar;
        }

        public final void setInputBufferStream(c cVar) {
            j.e(cVar, "value");
            this.inputBufferStreams.add(cVar);
        }

        public final void setInputSurfaceStream(c cVar) {
            j.e(cVar, "value");
            this.inputSurfaceStreams.add(cVar);
        }

        public final void setOutputBufferStream(c cVar) {
            j.e(cVar, "value");
            this.outputBufferStreams.add(cVar);
        }

        public final void setOutputSurfaceStream(c cVar) {
            j.e(cVar, "value");
            this.outputSurfaceStreams.add(cVar);
        }

        public final void setUsecase(VexFwkUsecase vexFwkUsecase) {
            this.usecase = vexFwkUsecase;
        }

        public Session(VexFwkUsecase vexFwkUsecase, c cVar) {
            this.usecase = vexFwkUsecase;
            this.configMetadata = cVar;
            this.inputBufferStreams = new ArrayList();
            this.inputSurfaceStreams = new ArrayList();
            this.outputBufferStreams = new ArrayList();
            this.outputSurfaceStreams = new ArrayList();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Session(VexFwkUsecase vexFwkUsecase, c cVar, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : vexFwkUsecase, (i2 & 2) != 0 ? null : cVar);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010,\u001a\u0004\u0018\u00010\fHÆ\u0003Jb\u0010-\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010.J\u0013\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u0003HÖ\u0001J\t\u00103\u001a\u000204HÖ\u0001R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001c\u0010\u0010\"\u0004\b\u001d\u0010\u0012R\u001e\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012R\u001e\u0010\n\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00065"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$SurfaceStream;", "", "id", "", "type", "Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "usage", "Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "width", "height", "format", "surface", "Landroid/view/Surface;", "<init>", "(Ljava/lang/Integer;Lcom/samsung/android/vexfwk/session/VexFwkStreamType;Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/view/Surface;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getType", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamType;", "setType", "(Lcom/samsung/android/vexfwk/session/VexFwkStreamType;)V", "getUsage", "()Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;", "setUsage", "(Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;)V", "getWidth", "setWidth", "getHeight", "setHeight", "getFormat", "setFormat", "getSurface", "()Landroid/view/Surface;", "setSurface", "(Landroid/view/Surface;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/Integer;Lcom/samsung/android/vexfwk/session/VexFwkStreamType;Lcom/samsung/android/vexfwk/session/VexFwkStreamUsage;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/view/Surface;)Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperConfiguration$SurfaceStream;", "equals", "", "other", "hashCode", "toString", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SurfaceStream {
        private Integer format;
        private Integer height;
        private Integer id;
        private Surface surface;
        private VexFwkStreamType type;
        private VexFwkStreamUsage usage;
        private Integer width;

        public SurfaceStream() {
            this((Integer) null, (VexFwkStreamType) null, (VexFwkStreamUsage) null, (Integer) null, (Integer) null, (Integer) null, (Surface) null, 127, (e) null);
        }

        public static /* synthetic */ SurfaceStream copy$default(SurfaceStream surfaceStream, Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, Integer num2, Integer num3, Integer num4, Surface surface2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                num = surfaceStream.id;
            }
            if ((i2 & 2) != 0) {
                vexFwkStreamType = surfaceStream.type;
            }
            if ((i2 & 4) != 0) {
                vexFwkStreamUsage = surfaceStream.usage;
            }
            if ((i2 & 8) != 0) {
                num2 = surfaceStream.width;
            }
            if ((i2 & 16) != 0) {
                num3 = surfaceStream.height;
            }
            if ((i2 & 32) != 0) {
                num4 = surfaceStream.format;
            }
            if ((i2 & 64) != 0) {
                surface2 = surfaceStream.surface;
            }
            Integer num5 = num4;
            Surface surface3 = surface2;
            Integer num6 = num2;
            Integer num7 = num3;
            return surfaceStream.copy(num, vexFwkStreamType, vexFwkStreamUsage, num6, num7, num5, surface3);
        }

        public final Integer component1() {
            return this.id;
        }

        public final VexFwkStreamType component2() {
            return this.type;
        }

        public final VexFwkStreamUsage component3() {
            return this.usage;
        }

        public final Integer component4() {
            return this.width;
        }

        public final Integer component5() {
            return this.height;
        }

        public final Integer component6() {
            return this.format;
        }

        public final Surface component7() {
            return this.surface;
        }

        public final SurfaceStream copy(Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, Integer num2, Integer num3, Integer num4, Surface surface2) {
            return new SurfaceStream(num, vexFwkStreamType, vexFwkStreamUsage, num2, num3, num4, surface2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SurfaceStream)) {
                return false;
            }
            SurfaceStream surfaceStream = (SurfaceStream) obj;
            if (j.a(this.id, surfaceStream.id) && this.type == surfaceStream.type && this.usage == surfaceStream.usage && j.a(this.width, surfaceStream.width) && j.a(this.height, surfaceStream.height) && j.a(this.format, surfaceStream.format) && j.a(this.surface, surfaceStream.surface)) {
                return true;
            }
            return false;
        }

        public final Integer getFormat() {
            return this.format;
        }

        public final Integer getHeight() {
            return this.height;
        }

        public final Integer getId() {
            return this.id;
        }

        public final Surface getSurface() {
            return this.surface;
        }

        public final VexFwkStreamType getType() {
            return this.type;
        }

        public final VexFwkStreamUsage getUsage() {
            return this.usage;
        }

        public final Integer getWidth() {
            return this.width;
        }

        public int hashCode() {
            int i2;
            int i7;
            int i8;
            int i10;
            int i11;
            int i12;
            Integer num = this.id;
            int i13 = 0;
            if (num == null) {
                i2 = 0;
            } else {
                i2 = num.hashCode();
            }
            int i14 = i2 * 31;
            VexFwkStreamType vexFwkStreamType = this.type;
            if (vexFwkStreamType == null) {
                i7 = 0;
            } else {
                i7 = vexFwkStreamType.hashCode();
            }
            int i15 = (i14 + i7) * 31;
            VexFwkStreamUsage vexFwkStreamUsage = this.usage;
            if (vexFwkStreamUsage == null) {
                i8 = 0;
            } else {
                i8 = vexFwkStreamUsage.hashCode();
            }
            int i16 = (i15 + i8) * 31;
            Integer num2 = this.width;
            if (num2 == null) {
                i10 = 0;
            } else {
                i10 = num2.hashCode();
            }
            int i17 = (i16 + i10) * 31;
            Integer num3 = this.height;
            if (num3 == null) {
                i11 = 0;
            } else {
                i11 = num3.hashCode();
            }
            int i18 = (i17 + i11) * 31;
            Integer num4 = this.format;
            if (num4 == null) {
                i12 = 0;
            } else {
                i12 = num4.hashCode();
            }
            int i19 = (i18 + i12) * 31;
            Surface surface2 = this.surface;
            if (surface2 != null) {
                i13 = surface2.hashCode();
            }
            return i19 + i13;
        }

        public final void setFormat(Integer num) {
            this.format = num;
        }

        public final void setHeight(Integer num) {
            this.height = num;
        }

        public final void setId(Integer num) {
            this.id = num;
        }

        public final void setSurface(Surface surface2) {
            this.surface = surface2;
        }

        public final void setType(VexFwkStreamType vexFwkStreamType) {
            this.type = vexFwkStreamType;
        }

        public final void setUsage(VexFwkStreamUsage vexFwkStreamUsage) {
            this.usage = vexFwkStreamUsage;
        }

        public final void setWidth(Integer num) {
            this.width = num;
        }

        public String toString() {
            Integer num = this.id;
            VexFwkStreamType vexFwkStreamType = this.type;
            VexFwkStreamUsage vexFwkStreamUsage = this.usage;
            Integer num2 = this.width;
            Integer num3 = this.height;
            Integer num4 = this.format;
            Surface surface2 = this.surface;
            return "SurfaceStream(id=" + num + ", type=" + vexFwkStreamType + ", usage=" + vexFwkStreamUsage + ", width=" + num2 + ", height=" + num3 + ", format=" + num4 + ", surface=" + surface2 + ")";
        }

        public SurfaceStream(Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, Integer num2, Integer num3, Integer num4, Surface surface2) {
            this.id = num;
            this.type = vexFwkStreamType;
            this.usage = vexFwkStreamUsage;
            this.width = num2;
            this.height = num3;
            this.format = num4;
            this.surface = surface2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ SurfaceStream(Integer num, VexFwkStreamType vexFwkStreamType, VexFwkStreamUsage vexFwkStreamUsage, Integer num2, Integer num3, Integer num4, Surface surface2, int i2, e eVar) {
            this((i2 & 1) != 0 ? null : num, (i2 & 2) != 0 ? null : vexFwkStreamType, (i2 & 4) != 0 ? null : vexFwkStreamUsage, (i2 & 8) != 0 ? null : num2, (i2 & 16) != 0 ? null : num3, (i2 & 32) != 0 ? null : num4, (i2 & 64) != 0 ? null : surface2);
        }
    }

    public final c getSession() {
        return (c) C1194l.T0(this.sessions);
    }

    public final List<c> getSessions() {
        return this.sessions;
    }

    public final void setSession(c cVar) {
        j.e(cVar, "value");
        this.sessions.add(cVar);
    }
}
