package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.ClientInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.C;
import com.google.protobuf.C0153z;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal$IntList;
import com.google.protobuf.Internal$ListAdapter$Converter;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RobinAvailabilityRequest extends GeneratedMessageLite implements RobinAvailabilityRequestOrBuilder {
    public static final int CLIENT_INFO_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final RobinAvailabilityRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int REQUESTED_CAPABILITIES_FIELD_NUMBER = 2;
    private static final Internal$ListAdapter$Converter requestedCapabilities_converter_ = new Internal$ListAdapter$Converter() {
        public RobinIntegrationCapability convert(Integer num) {
            RobinIntegrationCapability forNumber = RobinIntegrationCapability.forNumber(num.intValue());
            return forNumber == null ? RobinIntegrationCapability.UNRECOGNIZED : forNumber;
        }
    };
    private int bitField0_;
    private ClientInfo clientInfo_;
    private int requestedCapabilitiesMemoizedSerializedSize;
    private Internal$IntList requestedCapabilities_ = GeneratedMessageLite.emptyIntList();

    /* renamed from: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailabilityRequest$2  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass2 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailabilityRequest.AnonymousClass2.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends GeneratedMessageLite.Builder implements RobinAvailabilityRequestOrBuilder {
        public /* synthetic */ Builder(int i2) {
            this();
        }

        public Builder addAllRequestedCapabilities(Iterable<? extends RobinIntegrationCapability> iterable) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).addAllRequestedCapabilities(iterable);
            return this;
        }

        public Builder addAllRequestedCapabilitiesValue(Iterable<Integer> iterable) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).addAllRequestedCapabilitiesValue(iterable);
            return this;
        }

        public Builder addRequestedCapabilities(RobinIntegrationCapability robinIntegrationCapability) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).addRequestedCapabilities(robinIntegrationCapability);
            return this;
        }

        public Builder addRequestedCapabilitiesValue(int i2) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).addRequestedCapabilitiesValue(i2);
            return this;
        }

        public Builder clearClientInfo() {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).clearClientInfo();
            return this;
        }

        public Builder clearRequestedCapabilities() {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).clearRequestedCapabilities();
            return this;
        }

        public ClientInfo getClientInfo() {
            return ((RobinAvailabilityRequest) this.instance).getClientInfo();
        }

        public RobinIntegrationCapability getRequestedCapabilities(int i2) {
            return ((RobinAvailabilityRequest) this.instance).getRequestedCapabilities(i2);
        }

        public int getRequestedCapabilitiesCount() {
            return ((RobinAvailabilityRequest) this.instance).getRequestedCapabilitiesCount();
        }

        public List<RobinIntegrationCapability> getRequestedCapabilitiesList() {
            return ((RobinAvailabilityRequest) this.instance).getRequestedCapabilitiesList();
        }

        public int getRequestedCapabilitiesValue(int i2) {
            return ((RobinAvailabilityRequest) this.instance).getRequestedCapabilitiesValue(i2);
        }

        public List<Integer> getRequestedCapabilitiesValueList() {
            return Collections.unmodifiableList(((RobinAvailabilityRequest) this.instance).getRequestedCapabilitiesValueList());
        }

        public boolean hasClientInfo() {
            return ((RobinAvailabilityRequest) this.instance).hasClientInfo();
        }

        public Builder mergeClientInfo(ClientInfo clientInfo) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).mergeClientInfo(clientInfo);
            return this;
        }

        public Builder setClientInfo(ClientInfo clientInfo) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).setClientInfo(clientInfo);
            return this;
        }

        public Builder setRequestedCapabilities(int i2, RobinIntegrationCapability robinIntegrationCapability) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).setRequestedCapabilities(i2, robinIntegrationCapability);
            return this;
        }

        public Builder setRequestedCapabilitiesValue(int i2, int i7) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).setRequestedCapabilitiesValue(i2, i7);
            return this;
        }

        private Builder() {
            super(RobinAvailabilityRequest.DEFAULT_INSTANCE);
        }

        public Builder setClientInfo(ClientInfo.Builder builder) {
            copyOnWrite();
            ((RobinAvailabilityRequest) this.instance).setClientInfo((ClientInfo) builder.build());
            return this;
        }
    }

    static {
        RobinAvailabilityRequest robinAvailabilityRequest = new RobinAvailabilityRequest();
        DEFAULT_INSTANCE = robinAvailabilityRequest;
        GeneratedMessageLite.registerDefaultInstance(RobinAvailabilityRequest.class, robinAvailabilityRequest);
    }

    private RobinAvailabilityRequest() {
    }

    /* access modifiers changed from: private */
    public void addAllRequestedCapabilities(Iterable<? extends RobinIntegrationCapability> iterable) {
        ensureRequestedCapabilitiesIsMutable();
        for (RobinIntegrationCapability number : iterable) {
            this.requestedCapabilities_.k(number.getNumber());
        }
    }

    /* access modifiers changed from: private */
    public void addAllRequestedCapabilitiesValue(Iterable<Integer> iterable) {
        ensureRequestedCapabilitiesIsMutable();
        for (Integer intValue : iterable) {
            this.requestedCapabilities_.k(intValue.intValue());
        }
    }

    /* access modifiers changed from: private */
    public void addRequestedCapabilities(RobinIntegrationCapability robinIntegrationCapability) {
        robinIntegrationCapability.getClass();
        ensureRequestedCapabilitiesIsMutable();
        this.requestedCapabilities_.k(robinIntegrationCapability.getNumber());
    }

    /* access modifiers changed from: private */
    public void addRequestedCapabilitiesValue(int i2) {
        ensureRequestedCapabilitiesIsMutable();
        this.requestedCapabilities_.k(i2);
    }

    /* access modifiers changed from: private */
    public void clearClientInfo() {
        this.clientInfo_ = null;
        this.bitField0_ &= -2;
    }

    /* access modifiers changed from: private */
    public void clearRequestedCapabilities() {
        this.requestedCapabilities_ = GeneratedMessageLite.emptyIntList();
    }

    private void ensureRequestedCapabilitiesIsMutable() {
        Internal$IntList internal$IntList = this.requestedCapabilities_;
        if (!internal$IntList.j()) {
            this.requestedCapabilities_ = GeneratedMessageLite.mutableCopy(internal$IntList);
        }
    }

    public static RobinAvailabilityRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    /* access modifiers changed from: private */
    public void mergeClientInfo(ClientInfo clientInfo) {
        clientInfo.getClass();
        ClientInfo clientInfo2 = this.clientInfo_;
        if (clientInfo2 == null || clientInfo2 == ClientInfo.getDefaultInstance()) {
            this.clientInfo_ = clientInfo;
        } else {
            this.clientInfo_ = (ClientInfo) ((ClientInfo.Builder) ClientInfo.newBuilder(this.clientInfo_).mergeFrom(clientInfo)).buildPartial();
        }
        this.bitField0_ |= 1;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static RobinAvailabilityRequest parseDelimitedFrom(InputStream inputStream) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RobinAvailabilityRequest parseFrom(ByteBuffer byteBuffer) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void setClientInfo(ClientInfo clientInfo) {
        clientInfo.getClass();
        this.clientInfo_ = clientInfo;
        this.bitField0_ |= 1;
    }

    /* access modifiers changed from: private */
    public void setRequestedCapabilities(int i2, RobinIntegrationCapability robinIntegrationCapability) {
        robinIntegrationCapability.getClass();
        ensureRequestedCapabilitiesIsMutable();
        this.requestedCapabilities_.e(i2, robinIntegrationCapability.getNumber());
    }

    /* access modifiers changed from: private */
    public void setRequestedCapabilitiesValue(int i2, int i7) {
        ensureRequestedCapabilitiesIsMutable();
        this.requestedCapabilities_.e(i2, i7);
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser parser;
        switch (AnonymousClass2.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new RobinAvailabilityRequest();
            case 2:
                return new Builder(0);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဉ\u0000\u0002,", new Object[]{"bitField0_", "clientInfo_", "requestedCapabilities_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser2 = PARSER;
                if (parser2 != null) {
                    return parser2;
                }
                synchronized (RobinAvailabilityRequest.class) {
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

    public ClientInfo getClientInfo() {
        ClientInfo clientInfo = this.clientInfo_;
        if (clientInfo == null) {
            return ClientInfo.getDefaultInstance();
        }
        return clientInfo;
    }

    public RobinIntegrationCapability getRequestedCapabilities(int i2) {
        RobinIntegrationCapability forNumber = RobinIntegrationCapability.forNumber(this.requestedCapabilities_.getInt(i2));
        if (forNumber == null) {
            return RobinIntegrationCapability.UNRECOGNIZED;
        }
        return forNumber;
    }

    public int getRequestedCapabilitiesCount() {
        return this.requestedCapabilities_.size();
    }

    public List<RobinIntegrationCapability> getRequestedCapabilitiesList() {
        return new C(this.requestedCapabilities_, requestedCapabilities_converter_);
    }

    public int getRequestedCapabilitiesValue(int i2) {
        return this.requestedCapabilities_.getInt(i2);
    }

    public List<Integer> getRequestedCapabilitiesValueList() {
        return this.requestedCapabilities_;
    }

    public boolean hasClientInfo() {
        if ((this.bitField0_ & 1) != 0) {
            return true;
        }
        return false;
    }

    public static Builder newBuilder(RobinAvailabilityRequest robinAvailabilityRequest) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(robinAvailabilityRequest);
    }

    public static RobinAvailabilityRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RobinAvailabilityRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RobinAvailabilityRequest parseFrom(ByteString byteString) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RobinAvailabilityRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RobinAvailabilityRequest parseFrom(byte[] bArr) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RobinAvailabilityRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RobinAvailabilityRequest parseFrom(InputStream inputStream) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RobinAvailabilityRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RobinAvailabilityRequest parseFrom(CodedInputStream codedInputStream) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RobinAvailabilityRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailabilityRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
