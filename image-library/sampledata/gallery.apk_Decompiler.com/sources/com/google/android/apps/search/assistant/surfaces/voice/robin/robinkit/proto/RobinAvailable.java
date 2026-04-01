package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

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
public final class RobinAvailable extends GeneratedMessageLite implements RobinAvailableOrBuilder {
    /* access modifiers changed from: private */
    public static final RobinAvailable DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int SUPPORTED_CAPABILITIES_FIELD_NUMBER = 1;
    private static final Internal$ListAdapter$Converter supportedCapabilities_converter_ = new Internal$ListAdapter$Converter() {
        public RobinIntegrationCapability convert(Integer num) {
            RobinIntegrationCapability forNumber = RobinIntegrationCapability.forNumber(num.intValue());
            return forNumber == null ? RobinIntegrationCapability.UNRECOGNIZED : forNumber;
        }
    };
    private int supportedCapabilitiesMemoizedSerializedSize;
    private Internal$IntList supportedCapabilities_ = GeneratedMessageLite.emptyIntList();

    /* renamed from: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailable$2  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.RobinAvailable.AnonymousClass2.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends GeneratedMessageLite.Builder implements RobinAvailableOrBuilder {
        public /* synthetic */ Builder(int i2) {
            this();
        }

        public Builder addAllSupportedCapabilities(Iterable<? extends RobinIntegrationCapability> iterable) {
            copyOnWrite();
            ((RobinAvailable) this.instance).addAllSupportedCapabilities(iterable);
            return this;
        }

        public Builder addAllSupportedCapabilitiesValue(Iterable<Integer> iterable) {
            copyOnWrite();
            ((RobinAvailable) this.instance).addAllSupportedCapabilitiesValue(iterable);
            return this;
        }

        public Builder addSupportedCapabilities(RobinIntegrationCapability robinIntegrationCapability) {
            copyOnWrite();
            ((RobinAvailable) this.instance).addSupportedCapabilities(robinIntegrationCapability);
            return this;
        }

        public Builder addSupportedCapabilitiesValue(int i2) {
            copyOnWrite();
            ((RobinAvailable) this.instance).addSupportedCapabilitiesValue(i2);
            return this;
        }

        public Builder clearSupportedCapabilities() {
            copyOnWrite();
            ((RobinAvailable) this.instance).clearSupportedCapabilities();
            return this;
        }

        public RobinIntegrationCapability getSupportedCapabilities(int i2) {
            return ((RobinAvailable) this.instance).getSupportedCapabilities(i2);
        }

        public int getSupportedCapabilitiesCount() {
            return ((RobinAvailable) this.instance).getSupportedCapabilitiesCount();
        }

        public List<RobinIntegrationCapability> getSupportedCapabilitiesList() {
            return ((RobinAvailable) this.instance).getSupportedCapabilitiesList();
        }

        public int getSupportedCapabilitiesValue(int i2) {
            return ((RobinAvailable) this.instance).getSupportedCapabilitiesValue(i2);
        }

        public List<Integer> getSupportedCapabilitiesValueList() {
            return Collections.unmodifiableList(((RobinAvailable) this.instance).getSupportedCapabilitiesValueList());
        }

        public Builder setSupportedCapabilities(int i2, RobinIntegrationCapability robinIntegrationCapability) {
            copyOnWrite();
            ((RobinAvailable) this.instance).setSupportedCapabilities(i2, robinIntegrationCapability);
            return this;
        }

        public Builder setSupportedCapabilitiesValue(int i2, int i7) {
            copyOnWrite();
            ((RobinAvailable) this.instance).setSupportedCapabilitiesValue(i2, i7);
            return this;
        }

        private Builder() {
            super(RobinAvailable.DEFAULT_INSTANCE);
        }
    }

    static {
        RobinAvailable robinAvailable = new RobinAvailable();
        DEFAULT_INSTANCE = robinAvailable;
        GeneratedMessageLite.registerDefaultInstance(RobinAvailable.class, robinAvailable);
    }

    private RobinAvailable() {
    }

    /* access modifiers changed from: private */
    public void addAllSupportedCapabilities(Iterable<? extends RobinIntegrationCapability> iterable) {
        ensureSupportedCapabilitiesIsMutable();
        for (RobinIntegrationCapability number : iterable) {
            this.supportedCapabilities_.k(number.getNumber());
        }
    }

    /* access modifiers changed from: private */
    public void addAllSupportedCapabilitiesValue(Iterable<Integer> iterable) {
        ensureSupportedCapabilitiesIsMutable();
        for (Integer intValue : iterable) {
            this.supportedCapabilities_.k(intValue.intValue());
        }
    }

    /* access modifiers changed from: private */
    public void addSupportedCapabilities(RobinIntegrationCapability robinIntegrationCapability) {
        robinIntegrationCapability.getClass();
        ensureSupportedCapabilitiesIsMutable();
        this.supportedCapabilities_.k(robinIntegrationCapability.getNumber());
    }

    /* access modifiers changed from: private */
    public void addSupportedCapabilitiesValue(int i2) {
        ensureSupportedCapabilitiesIsMutable();
        this.supportedCapabilities_.k(i2);
    }

    /* access modifiers changed from: private */
    public void clearSupportedCapabilities() {
        this.supportedCapabilities_ = GeneratedMessageLite.emptyIntList();
    }

    private void ensureSupportedCapabilitiesIsMutable() {
        Internal$IntList internal$IntList = this.supportedCapabilities_;
        if (!internal$IntList.j()) {
            this.supportedCapabilities_ = GeneratedMessageLite.mutableCopy(internal$IntList);
        }
    }

    public static RobinAvailable getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static RobinAvailable parseDelimitedFrom(InputStream inputStream) {
        return (RobinAvailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RobinAvailable parseFrom(ByteBuffer byteBuffer) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Parser parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* access modifiers changed from: private */
    public void setSupportedCapabilities(int i2, RobinIntegrationCapability robinIntegrationCapability) {
        robinIntegrationCapability.getClass();
        ensureSupportedCapabilitiesIsMutable();
        this.supportedCapabilities_.e(i2, robinIntegrationCapability.getNumber());
    }

    /* access modifiers changed from: private */
    public void setSupportedCapabilitiesValue(int i2, int i7) {
        ensureSupportedCapabilitiesIsMutable();
        this.supportedCapabilities_.e(i2, i7);
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser parser;
        switch (AnonymousClass2.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new RobinAvailable();
            case 2:
                return new Builder(0);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001,", new Object[]{"supportedCapabilities_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser2 = PARSER;
                if (parser2 != null) {
                    return parser2;
                }
                synchronized (RobinAvailable.class) {
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

    public RobinIntegrationCapability getSupportedCapabilities(int i2) {
        RobinIntegrationCapability forNumber = RobinIntegrationCapability.forNumber(this.supportedCapabilities_.getInt(i2));
        if (forNumber == null) {
            return RobinIntegrationCapability.UNRECOGNIZED;
        }
        return forNumber;
    }

    public int getSupportedCapabilitiesCount() {
        return this.supportedCapabilities_.size();
    }

    public List<RobinIntegrationCapability> getSupportedCapabilitiesList() {
        return new C(this.supportedCapabilities_, supportedCapabilities_converter_);
    }

    public int getSupportedCapabilitiesValue(int i2) {
        return this.supportedCapabilities_.getInt(i2);
    }

    public List<Integer> getSupportedCapabilitiesValueList() {
        return this.supportedCapabilities_;
    }

    public static Builder newBuilder(RobinAvailable robinAvailable) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(robinAvailable);
    }

    public static RobinAvailable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailable) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RobinAvailable parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static RobinAvailable parseFrom(ByteString byteString) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RobinAvailable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RobinAvailable parseFrom(byte[] bArr) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RobinAvailable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RobinAvailable parseFrom(InputStream inputStream) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RobinAvailable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RobinAvailable parseFrom(CodedInputStream codedInputStream) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RobinAvailable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (RobinAvailable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
