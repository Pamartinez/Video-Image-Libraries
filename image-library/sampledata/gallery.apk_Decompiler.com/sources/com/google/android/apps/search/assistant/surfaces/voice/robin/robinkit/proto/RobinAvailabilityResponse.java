package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailable;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinNotAvailable;
import com.google.protobuf.ByteString;
import com.google.protobuf.C0153z;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RobinAvailabilityResponse extends GeneratedMessageLite implements RobinAvailabilityResponseOrBuilder {
    public static final int AVAILABLE_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final RobinAvailabilityResponse DEFAULT_INSTANCE;
    public static final int NOT_AVAILABLE_FIELD_NUMBER = 2;
    private static volatile Parser PARSER;
    private int statusCase_ = 0;
    private Object status_;

    /* renamed from: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailabilityResponse$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailabilityResponse.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends GeneratedMessageLite.Builder implements RobinAvailabilityResponseOrBuilder {
        public /* synthetic */ Builder(int i2) {
            this();
        }

        public Builder clearAvailable() {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).clearAvailable();
            return this;
        }

        public Builder clearNotAvailable() {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).clearNotAvailable();
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).clearStatus();
            return this;
        }

        public RobinAvailable getAvailable() {
            return ((RobinAvailabilityResponse) this.instance).getAvailable();
        }

        public RobinNotAvailable getNotAvailable() {
            return ((RobinAvailabilityResponse) this.instance).getNotAvailable();
        }

        public StatusCase getStatusCase() {
            return ((RobinAvailabilityResponse) this.instance).getStatusCase();
        }

        public boolean hasAvailable() {
            return ((RobinAvailabilityResponse) this.instance).hasAvailable();
        }

        public boolean hasNotAvailable() {
            return ((RobinAvailabilityResponse) this.instance).hasNotAvailable();
        }

        public Builder mergeAvailable(RobinAvailable robinAvailable) {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).mergeAvailable(robinAvailable);
            return this;
        }

        public Builder mergeNotAvailable(RobinNotAvailable robinNotAvailable) {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).mergeNotAvailable(robinNotAvailable);
            return this;
        }

        public Builder setAvailable(RobinAvailable robinAvailable) {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).setAvailable(robinAvailable);
            return this;
        }

        public Builder setNotAvailable(RobinNotAvailable robinNotAvailable) {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).setNotAvailable(robinNotAvailable);
            return this;
        }

        private Builder() {
            super(RobinAvailabilityResponse.DEFAULT_INSTANCE);
        }

        public Builder setAvailable(RobinAvailable.Builder builder) {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).setAvailable((RobinAvailable) builder.build());
            return this;
        }

        public Builder setNotAvailable(RobinNotAvailable.Builder builder) {
            copyOnWrite();
            ((RobinAvailabilityResponse) this.instance).setNotAvailable((RobinNotAvailable) builder.build());
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum StatusCase {
        AVAILABLE(1),
        NOT_AVAILABLE(2),
        STATUS_NOT_SET(0);
        
        private final int value;

        private StatusCase(int i2) {
            this.value = i2;
        }

        public static StatusCase forNumber(int i2) {
            if (i2 == 0) {
                return STATUS_NOT_SET;
            }
            if (i2 == 1) {
                return AVAILABLE;
            }
            if (i2 != 2) {
                return null;
            }
            return NOT_AVAILABLE;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static StatusCase valueOf(int i2) {
            return forNumber(i2);
        }
    }

    static {
        RobinAvailabilityResponse robinAvailabilityResponse = new RobinAvailabilityResponse();
        DEFAULT_INSTANCE = robinAvailabilityResponse;
        GeneratedMessageLite.registerDefaultInstance(RobinAvailabilityResponse.class, robinAvailabilityResponse);
    }

    private RobinAvailabilityResponse() {
    }

    /* access modifiers changed from: private */
    public void clearAvailable() {
        if (this.statusCase_ == 1) {
            this.statusCase_ = 0;
            this.status_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void clearNotAvailable() {
        if (this.statusCase_ == 2) {
            this.statusCase_ = 0;
            this.status_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void clearStatus() {
        this.statusCase_ = 0;
        this.status_ = null;
    }

    public static RobinAvailabilityResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* access modifiers changed from: private */
    public void mergeAvailable(RobinAvailable robinAvailable) {
        robinAvailable.getClass();
        if (this.statusCase_ != 1 || this.status_ == RobinAvailable.getDefaultInstance()) {
            this.status_ = robinAvailable;
        } else {
            this.status_ = ((RobinAvailable.Builder) RobinAvailable.newBuilder((RobinAvailable) this.status_).mergeFrom(robinAvailable)).buildPartial();
        }
        this.statusCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeNotAvailable(RobinNotAvailable robinNotAvailable) {
        robinNotAvailable.getClass();
        if (this.statusCase_ != 2 || this.status_ == RobinNotAvailable.getDefaultInstance()) {
            this.status_ = robinNotAvailable;
        } else {
            this.status_ = ((RobinNotAvailable.Builder) RobinNotAvailable.newBuilder((RobinNotAvailable) this.status_).mergeFrom(robinNotAvailable)).buildPartial();
        }
        this.statusCase_ = 2;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static RobinAvailabilityResponse parseDelimitedFrom(InputStream inputStream) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RobinAvailabilityResponse parseFrom(ByteBuffer byteBuffer) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void setAvailable(RobinAvailable robinAvailable) {
        robinAvailable.getClass();
        this.status_ = robinAvailable;
        this.statusCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void setNotAvailable(RobinNotAvailable robinNotAvailable) {
        robinNotAvailable.getClass();
        this.status_ = robinNotAvailable;
        this.statusCase_ = 2;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser parser;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new RobinAvailabilityResponse();
            case 2:
                return new Builder(0);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"status_", "statusCase_", RobinAvailable.class, RobinNotAvailable.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser2 = PARSER;
                if (parser2 != null) {
                    return parser2;
                }
                synchronized (RobinAvailabilityResponse.class) {
                    try {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new C0153z(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public RobinAvailable getAvailable() {
        if (this.statusCase_ == 1) {
            return (RobinAvailable) this.status_;
        }
        return RobinAvailable.getDefaultInstance();
    }

    public RobinNotAvailable getNotAvailable() {
        if (this.statusCase_ == 2) {
            return (RobinNotAvailable) this.status_;
        }
        return RobinNotAvailable.getDefaultInstance();
    }

    public StatusCase getStatusCase() {
        return StatusCase.forNumber(this.statusCase_);
    }

    public boolean hasAvailable() {
        if (this.statusCase_ == 1) {
            return true;
        }
        return false;
    }

    public boolean hasNotAvailable() {
        if (this.statusCase_ == 2) {
            return true;
        }
        return false;
    }

    public static Builder newBuilder(RobinAvailabilityResponse robinAvailabilityResponse) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(robinAvailabilityResponse);
    }

    public static RobinAvailabilityResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RobinAvailabilityResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RobinAvailabilityResponse parseFrom(ByteString byteString) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RobinAvailabilityResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RobinAvailabilityResponse parseFrom(byte[] bArr) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RobinAvailabilityResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RobinAvailabilityResponse parseFrom(InputStream inputStream) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RobinAvailabilityResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RobinAvailabilityResponse parseFrom(CodedInputStream codedInputStream) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RobinAvailabilityResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
