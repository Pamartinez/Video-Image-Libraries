package com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto;

import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.ClientInfo;
import com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.TextQuery;
import com.google.protobuf.ByteString;
import com.google.protobuf.C0153z;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Parser;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LaunchRobinRequest extends GeneratedMessageLite implements LaunchRobinRequestOrBuilder {
    public static final int CLIENT_INFO_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final LaunchRobinRequest DEFAULT_INSTANCE;
    private static volatile Parser PARSER = null;
    public static final int TEXT_QUERY_FIELD_NUMBER = 5;
    private int bitField0_;
    private ClientInfo clientInfo_;
    private int requestedActionCase_ = 0;
    private Object requestedAction_;

    /* renamed from: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.LaunchRobinRequest$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.search.assistant.surfaces.voice.robin.robinkit.proto.LaunchRobinRequest.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder extends GeneratedMessageLite.Builder implements LaunchRobinRequestOrBuilder {
        public /* synthetic */ Builder(int i2) {
            this();
        }

        public Builder clearClientInfo() {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).clearClientInfo();
            return this;
        }

        public Builder clearRequestedAction() {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).clearRequestedAction();
            return this;
        }

        public Builder clearTextQuery() {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).clearTextQuery();
            return this;
        }

        public ClientInfo getClientInfo() {
            return ((LaunchRobinRequest) this.instance).getClientInfo();
        }

        public RequestedActionCase getRequestedActionCase() {
            return ((LaunchRobinRequest) this.instance).getRequestedActionCase();
        }

        public TextQuery getTextQuery() {
            return ((LaunchRobinRequest) this.instance).getTextQuery();
        }

        public boolean hasClientInfo() {
            return ((LaunchRobinRequest) this.instance).hasClientInfo();
        }

        public boolean hasTextQuery() {
            return ((LaunchRobinRequest) this.instance).hasTextQuery();
        }

        public Builder mergeClientInfo(ClientInfo clientInfo) {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).mergeClientInfo(clientInfo);
            return this;
        }

        public Builder mergeTextQuery(TextQuery textQuery) {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).mergeTextQuery(textQuery);
            return this;
        }

        public Builder setClientInfo(ClientInfo clientInfo) {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).setClientInfo(clientInfo);
            return this;
        }

        public Builder setTextQuery(TextQuery textQuery) {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).setTextQuery(textQuery);
            return this;
        }

        private Builder() {
            super(LaunchRobinRequest.DEFAULT_INSTANCE);
        }

        public Builder setClientInfo(ClientInfo.Builder builder) {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).setClientInfo((ClientInfo) builder.build());
            return this;
        }

        public Builder setTextQuery(TextQuery.Builder builder) {
            copyOnWrite();
            ((LaunchRobinRequest) this.instance).setTextQuery((TextQuery) builder.build());
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RequestedActionCase {
        TEXT_QUERY(5),
        REQUESTEDACTION_NOT_SET(0);
        
        private final int value;

        private RequestedActionCase(int i2) {
            this.value = i2;
        }

        public static RequestedActionCase forNumber(int i2) {
            if (i2 == 0) {
                return REQUESTEDACTION_NOT_SET;
            }
            if (i2 != 5) {
                return null;
            }
            return TEXT_QUERY;
        }

        public int getNumber() {
            return this.value;
        }

        @Deprecated
        public static RequestedActionCase valueOf(int i2) {
            return forNumber(i2);
        }
    }

    static {
        LaunchRobinRequest launchRobinRequest = new LaunchRobinRequest();
        DEFAULT_INSTANCE = launchRobinRequest;
        GeneratedMessageLite.registerDefaultInstance(LaunchRobinRequest.class, launchRobinRequest);
    }

    private LaunchRobinRequest() {
    }

    /* access modifiers changed from: private */
    public void clearClientInfo() {
        this.clientInfo_ = null;
        this.bitField0_ &= -2;
    }

    /* access modifiers changed from: private */
    public void clearRequestedAction() {
        this.requestedActionCase_ = 0;
        this.requestedAction_ = null;
    }

    /* access modifiers changed from: private */
    public void clearTextQuery() {
        if (this.requestedActionCase_ == 5) {
            this.requestedActionCase_ = 0;
            this.requestedAction_ = null;
        }
    }

    public static LaunchRobinRequest getDefaultInstance() {
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

    /* access modifiers changed from: private */
    public void mergeTextQuery(TextQuery textQuery) {
        textQuery.getClass();
        if (this.requestedActionCase_ != 5 || this.requestedAction_ == TextQuery.getDefaultInstance()) {
            this.requestedAction_ = textQuery;
        } else {
            this.requestedAction_ = ((TextQuery.Builder) TextQuery.newBuilder((TextQuery) this.requestedAction_).mergeFrom(textQuery)).buildPartial();
        }
        this.requestedActionCase_ = 5;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static LaunchRobinRequest parseDelimitedFrom(InputStream inputStream) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LaunchRobinRequest parseFrom(ByteBuffer byteBuffer) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
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
    public void setTextQuery(TextQuery textQuery) {
        textQuery.getClass();
        this.requestedAction_ = textQuery;
        this.requestedActionCase_ = 5;
    }

    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser parser;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new LaunchRobinRequest();
            case 2:
                return new Builder(0);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0001\u0001\u0005\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0005<\u0000", new Object[]{"requestedAction_", "requestedActionCase_", "bitField0_", "clientInfo_", TextQuery.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser parser2 = PARSER;
                if (parser2 != null) {
                    return parser2;
                }
                synchronized (LaunchRobinRequest.class) {
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

    public RequestedActionCase getRequestedActionCase() {
        return RequestedActionCase.forNumber(this.requestedActionCase_);
    }

    public TextQuery getTextQuery() {
        if (this.requestedActionCase_ == 5) {
            return (TextQuery) this.requestedAction_;
        }
        return TextQuery.getDefaultInstance();
    }

    public boolean hasClientInfo() {
        if ((this.bitField0_ & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean hasTextQuery() {
        if (this.requestedActionCase_ == 5) {
            return true;
        }
        return false;
    }

    public static Builder newBuilder(LaunchRobinRequest launchRobinRequest) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(launchRobinRequest);
    }

    public static LaunchRobinRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LaunchRobinRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static LaunchRobinRequest parseFrom(ByteString byteString) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static LaunchRobinRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static LaunchRobinRequest parseFrom(byte[] bArr) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static LaunchRobinRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static LaunchRobinRequest parseFrom(InputStream inputStream) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LaunchRobinRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LaunchRobinRequest parseFrom(CodedInputStream codedInputStream) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static LaunchRobinRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        return (LaunchRobinRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }
}
