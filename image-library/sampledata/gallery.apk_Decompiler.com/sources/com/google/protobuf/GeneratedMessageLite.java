package com.google.protobuf;

import Od.b;
import c0.C0086a;
import com.google.protobuf.AbstractMessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GeneratedMessageLite extends AbstractMessageLite {
    private static final int MEMOIZED_SERIALIZED_SIZE_MASK = Integer.MAX_VALUE;
    private static final int MUTABLE_FLAG_MASK = Integer.MIN_VALUE;
    static final int UNINITIALIZED_HASH_CODE = 0;
    static final int UNINITIALIZED_SERIALIZED_SIZE = Integer.MAX_VALUE;
    private static Map<Object, GeneratedMessageLite> defaultInstanceMap = new ConcurrentHashMap();
    private int memoizedSerializedSize = -1;
    protected UnknownFieldSetLite unknownFields = UnknownFieldSetLite.f;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Builder extends AbstractMessageLite.Builder {
        private final GeneratedMessageLite defaultInstance;
        protected GeneratedMessageLite instance;

        public Builder(GeneratedMessageLite generatedMessageLite) {
            this.defaultInstance = generatedMessageLite;
            if (!generatedMessageLite.isMutable()) {
                this.instance = generatedMessageLite.newMutableInstance();
                return;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        public final void copyOnWrite() {
            if (!this.instance.isMutable()) {
                copyOnWriteInternal();
            }
        }

        public void copyOnWriteInternal() {
            GeneratedMessageLite newMutableInstance = this.defaultInstance.newMutableInstance();
            GeneratedMessageLite generatedMessageLite = this.instance;
            d0 d0Var = d0.f1601c;
            d0Var.getClass();
            d0Var.a(newMutableInstance.getClass()).b(newMutableInstance, generatedMessageLite);
            this.instance = newMutableInstance;
        }

        public final boolean isInitialized() {
            return GeneratedMessageLite.isInitialized(this.instance, false);
        }

        public final GeneratedMessageLite build() {
            GeneratedMessageLite buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw AbstractMessageLite.Builder.newUninitializedMessageException(buildPartial);
        }

        public GeneratedMessageLite buildPartial() {
            if (!this.instance.isMutable()) {
                return this.instance;
            }
            this.instance.makeImmutable();
            return this.instance;
        }

        public final Builder clear() {
            if (!this.defaultInstance.isMutable()) {
                this.instance = this.defaultInstance.newMutableInstance();
                return this;
            }
            throw new IllegalArgumentException("Default instance must be immutable.");
        }

        public GeneratedMessageLite getDefaultInstanceForType() {
            return this.defaultInstance;
        }

        public Builder internalMergeFrom(GeneratedMessageLite generatedMessageLite) {
            return mergeFrom(generatedMessageLite);
        }

        public Builder clone() {
            Builder newBuilderForType = getDefaultInstanceForType().newBuilderForType();
            newBuilderForType.instance = buildPartial();
            return newBuilderForType;
        }

        public Builder mergeFrom(GeneratedMessageLite generatedMessageLite) {
            if (getDefaultInstanceForType().equals(generatedMessageLite)) {
                return this;
            }
            copyOnWrite();
            GeneratedMessageLite generatedMessageLite2 = this.instance;
            d0 d0Var = d0.f1601c;
            d0Var.getClass();
            d0Var.a(generatedMessageLite2.getClass()).b(generatedMessageLite2, generatedMessageLite);
            return this;
        }

        public Builder mergeFrom(byte[] bArr, int i2, int i7, ExtensionRegistryLite extensionRegistryLite) {
            copyOnWrite();
            try {
                d0 d0Var = d0.f1601c;
                GeneratedMessageLite generatedMessageLite = this.instance;
                d0Var.getClass();
                d0Var.a(generatedMessageLite.getClass()).j(this.instance, bArr, i2, i2 + i7, new b(extensionRegistryLite));
                return this;
            } catch (F e) {
                throw e;
            } catch (IndexOutOfBoundsException unused) {
                throw F.g();
            } catch (IOException e7) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e7);
            }
        }

        public Builder mergeFrom(byte[] bArr, int i2, int i7) {
            return mergeFrom(bArr, i2, i7, ExtensionRegistryLite.a());
        }

        public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
            copyOnWrite();
            try {
                d0 d0Var = d0.f1601c;
                GeneratedMessageLite generatedMessageLite = this.instance;
                d0Var.getClass();
                Schema a7 = d0Var.a(generatedMessageLite.getClass());
                GeneratedMessageLite generatedMessageLite2 = this.instance;
                C0141m mVar = codedInputStream.f1575c;
                if (mVar == null) {
                    mVar = new C0141m(codedInputStream);
                }
                a7.g(generatedMessageLite2, mVar, extensionRegistryLite);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GeneratedExtension extends ExtensionLite {

        /* renamed from: a  reason: collision with root package name */
        public final MessageLite f1580a;
        public final Object b;

        /* renamed from: c  reason: collision with root package name */
        public final MessageLite f1581c;

        public GeneratedExtension(MessageLite messageLite, Object obj, MessageLite messageLite2, A a7) {
            if (messageLite == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (a7.f == WireFormat$FieldType.MESSAGE && messageLite2 == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.f1580a = messageLite;
                this.b = obj;
                this.f1581c = messageLite2;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum MethodToInvoke {
    }

    public GeneratedMessageLite() {
        this.memoizedHashCode = 0;
    }

    public static GeneratedExtension access$000(ExtensionLite extensionLite) {
        extensionLite.getClass();
        return (GeneratedExtension) extensionLite;
    }

    public static void b(GeneratedMessageLite generatedMessageLite) {
        if (generatedMessageLite != null && !generatedMessageLite.isInitialized()) {
            UninitializedMessageException newUninitializedMessageException = generatedMessageLite.newUninitializedMessageException();
            newUninitializedMessageException.getClass();
            throw new IOException(newUninitializedMessageException.getMessage());
        }
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.IOException] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.protobuf.GeneratedMessageLite c(com.google.protobuf.GeneratedMessageLite r2, java.io.InputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
        /*
            int r0 = r3.read()     // Catch:{ F -> 0x002a, IOException -> 0x001f }
            r1 = -1
            if (r0 != r1) goto L_0x0009
            r2 = 0
            return r2
        L_0x0009:
            int r0 = com.google.protobuf.CodedInputStream.s(r3, r0)     // Catch:{ F -> 0x002a, IOException -> 0x001f }
            com.google.protobuf.a r1 = new com.google.protobuf.a
            r1.<init>(r3, r0)
            com.google.protobuf.CodedInputStream r3 = com.google.protobuf.CodedInputStream.g(r1)
            com.google.protobuf.GeneratedMessageLite r2 = parsePartialFrom(r2, r3, r4)
            r4 = 0
            r3.a(r4)
            return r2
        L_0x001f:
            r2 = move-exception
            com.google.protobuf.F r3 = new com.google.protobuf.F
            java.lang.String r4 = r2.getMessage()
            r3.<init>(r4, r2)
            throw r3
        L_0x002a:
            r2 = move-exception
            boolean r3 = r2.d
            if (r3 == 0) goto L_0x0039
            com.google.protobuf.F r3 = new com.google.protobuf.F
            java.lang.String r4 = r2.getMessage()
            r3.<init>(r4, r2)
            r2 = r3
        L_0x0039:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.GeneratedMessageLite.c(com.google.protobuf.GeneratedMessageLite, java.io.InputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.GeneratedMessageLite");
    }

    /* JADX WARNING: type inference failed for: r7v2, types: [java.io.IOException] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.protobuf.GeneratedMessageLite d(com.google.protobuf.GeneratedMessageLite r6, byte[] r7, int r8, int r9, com.google.protobuf.ExtensionRegistryLite r10) {
        /*
            com.google.protobuf.GeneratedMessageLite r1 = r6.newMutableInstance()
            com.google.protobuf.d0 r6 = com.google.protobuf.d0.f1601c     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            r6.getClass()     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            java.lang.Class r0 = r1.getClass()     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            com.google.protobuf.Schema r0 = r6.a(r0)     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            int r4 = r8 + r9
            Od.b r5 = new Od.b     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            r5.<init>(r10)     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            r2 = r7
            r3 = r8
            r0.j(r1, r2, r3, r4, r5)     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            r0.c(r1)     // Catch:{ F -> 0x004d, UninitializedMessageException -> 0x0041, IOException -> 0x0026, IndexOutOfBoundsException -> 0x0021 }
            return r1
        L_0x0021:
            com.google.protobuf.F r6 = com.google.protobuf.F.g()
            throw r6
        L_0x0026:
            r0 = move-exception
            r6 = r0
            java.lang.Throwable r7 = r6.getCause()
            boolean r7 = r7 instanceof com.google.protobuf.F
            if (r7 == 0) goto L_0x0037
            java.lang.Throwable r6 = r6.getCause()
            com.google.protobuf.F r6 = (com.google.protobuf.F) r6
            throw r6
        L_0x0037:
            com.google.protobuf.F r7 = new com.google.protobuf.F
            java.lang.String r8 = r6.getMessage()
            r7.<init>(r8, r6)
            throw r7
        L_0x0041:
            r0 = move-exception
            r6 = r0
            com.google.protobuf.F r7 = new com.google.protobuf.F
            java.lang.String r6 = r6.getMessage()
            r7.<init>(r6)
            throw r7
        L_0x004d:
            r0 = move-exception
            r6 = r0
            boolean r7 = r6.d
            if (r7 == 0) goto L_0x005d
            com.google.protobuf.F r7 = new com.google.protobuf.F
            java.lang.String r8 = r6.getMessage()
            r7.<init>(r8, r6)
            r6 = r7
        L_0x005d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.GeneratedMessageLite.d(com.google.protobuf.GeneratedMessageLite, byte[], int, int, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.GeneratedMessageLite");
    }

    public static Internal$BooleanList emptyBooleanList() {
        return C0134f.g;
    }

    public static Internal$DoubleList emptyDoubleList() {
        return C0144p.g;
    }

    public static Internal$FloatList emptyFloatList() {
        return C0151x.g;
    }

    public static Internal$IntList emptyIntList() {
        return B.g;
    }

    public static Internal$LongList emptyLongList() {
        return M.g;
    }

    public static <E> Internal$ProtobufList emptyProtobufList() {
        return e0.g;
    }

    public static <T extends GeneratedMessageLite> T getDefaultInstance(Class<T> cls) {
        T t = (GeneratedMessageLite) defaultInstanceMap.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (GeneratedMessageLite) defaultInstanceMap.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        T defaultInstanceForType = ((GeneratedMessageLite) p0.b(cls)).getDefaultInstanceForType();
        if (defaultInstanceForType != null) {
            defaultInstanceMap.put(cls, defaultInstanceForType);
            return defaultInstanceForType;
        }
        throw new IllegalStateException();
    }

    public static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Generated message class \"" + cls.getName() + "\" missing method \"" + str + "\".", e);
        }
    }

    public static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e7) {
            Throwable cause = e7.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public static Internal$IntList mutableCopy(Internal$IntList internal$IntList) {
        int size = internal$IntList.size();
        return internal$IntList.a(size == 0 ? 10 : size * 2);
    }

    public static Object newMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        return new f0(messageLite, str, objArr);
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension newRepeatedGeneratedExtension(ContainingType containingtype, MessageLite messageLite, Internal$EnumLiteMap internal$EnumLiteMap, int i2, WireFormat$FieldType wireFormat$FieldType, boolean z, Class cls) {
        return new GeneratedExtension(containingtype, Collections.EMPTY_LIST, messageLite, new A(internal$EnumLiteMap, i2, wireFormat$FieldType, true, z));
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension newSingularGeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, Internal$EnumLiteMap internal$EnumLiteMap, int i2, WireFormat$FieldType wireFormat$FieldType, Class cls) {
        return new GeneratedExtension(containingtype, type, messageLite, new A(internal$EnumLiteMap, i2, wireFormat$FieldType, false, false));
    }

    public static <T extends GeneratedMessageLite> T parseDelimitedFrom(T t, InputStream inputStream) {
        T c5 = c(t, inputStream, ExtensionRegistryLite.a());
        b(c5);
        return c5;
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream codedInputStream;
        if (byteBuffer.hasArray()) {
            codedInputStream = CodedInputStream.f(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.remaining(), false);
        } else if (!byteBuffer.isDirect() || !p0.d) {
            int remaining = byteBuffer.remaining();
            byte[] bArr = new byte[remaining];
            byteBuffer.duplicate().get(bArr);
            codedInputStream = CodedInputStream.f(bArr, 0, remaining, true);
        } else {
            codedInputStream = new C0140l(byteBuffer, false);
        }
        T parseFrom = parseFrom(t, codedInputStream, extensionRegistryLite);
        b(parseFrom);
        return parseFrom;
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.IOException] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T extends com.google.protobuf.GeneratedMessageLite> T parsePartialFrom(T r2, com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) {
        /*
            com.google.protobuf.GeneratedMessageLite r2 = r2.newMutableInstance()
            com.google.protobuf.d0 r0 = com.google.protobuf.d0.f1601c     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            r0.getClass()     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            java.lang.Class r1 = r2.getClass()     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            com.google.protobuf.Schema r0 = r0.a(r1)     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            com.google.protobuf.m r1 = r3.f1575c     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            if (r1 == 0) goto L_0x0016
            goto L_0x001b
        L_0x0016:
            com.google.protobuf.m r1 = new com.google.protobuf.m     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            r1.<init>(r3)     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
        L_0x001b:
            r0.g(r2, r1, r4)     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            r0.c(r2)     // Catch:{ F -> 0x0058, UninitializedMessageException -> 0x004d, IOException -> 0x0033, RuntimeException -> 0x0022 }
            return r2
        L_0x0022:
            r2 = move-exception
            java.lang.Throwable r3 = r2.getCause()
            boolean r3 = r3 instanceof com.google.protobuf.F
            if (r3 == 0) goto L_0x0032
            java.lang.Throwable r2 = r2.getCause()
            com.google.protobuf.F r2 = (com.google.protobuf.F) r2
            throw r2
        L_0x0032:
            throw r2
        L_0x0033:
            r2 = move-exception
            java.lang.Throwable r3 = r2.getCause()
            boolean r3 = r3 instanceof com.google.protobuf.F
            if (r3 == 0) goto L_0x0043
            java.lang.Throwable r2 = r2.getCause()
            com.google.protobuf.F r2 = (com.google.protobuf.F) r2
            throw r2
        L_0x0043:
            com.google.protobuf.F r3 = new com.google.protobuf.F
            java.lang.String r4 = r2.getMessage()
            r3.<init>(r4, r2)
            throw r3
        L_0x004d:
            r2 = move-exception
            com.google.protobuf.F r3 = new com.google.protobuf.F
            java.lang.String r2 = r2.getMessage()
            r3.<init>(r2)
            throw r3
        L_0x0058:
            r2 = move-exception
            boolean r3 = r2.d
            if (r3 == 0) goto L_0x0067
            com.google.protobuf.F r3 = new com.google.protobuf.F
            java.lang.String r4 = r2.getMessage()
            r3.<init>(r4, r2)
            r2 = r3
        L_0x0067:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.GeneratedMessageLite.parsePartialFrom(com.google.protobuf.GeneratedMessageLite, com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.protobuf.GeneratedMessageLite");
    }

    public static <T extends GeneratedMessageLite> void registerDefaultInstance(Class<T> cls, T t) {
        t.markImmutable();
        defaultInstanceMap.put(cls, t);
    }

    public Object buildMessageInfo() {
        return dynamicMethod(MethodToInvoke.BUILD_MESSAGE_INFO);
    }

    public void clearMemoizedHashCode() {
        this.memoizedHashCode = 0;
    }

    public void clearMemoizedSerializedSize() {
        setMemoizedSerializedSize(Integer.MAX_VALUE);
    }

    public int computeHashCode() {
        d0 d0Var = d0.f1601c;
        d0Var.getClass();
        return d0Var.a(getClass()).h(this);
    }

    public final <MessageType extends GeneratedMessageLite, BuilderType extends Builder> BuilderType createBuilder() {
        return (Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    public Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj) {
        return dynamicMethod(methodToInvoke, obj, (Object) null);
    }

    public abstract Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d0 d0Var = d0.f1601c;
        d0Var.getClass();
        return d0Var.a(getClass()).i(this, (GeneratedMessageLite) obj);
    }

    public int getMemoizedHashCode() {
        return this.memoizedHashCode;
    }

    public int getMemoizedSerializedSize() {
        return this.memoizedSerializedSize & Integer.MAX_VALUE;
    }

    public final Parser getParserForType() {
        return (Parser) dynamicMethod(MethodToInvoke.GET_PARSER);
    }

    public int getSerializedSize(Schema schema) {
        int i2;
        int i7;
        if (isMutable()) {
            if (schema == null) {
                d0 d0Var = d0.f1601c;
                d0Var.getClass();
                i7 = d0Var.a(getClass()).e(this);
            } else {
                i7 = schema.e(this);
            }
            if (i7 >= 0) {
                return i7;
            }
            throw new IllegalStateException(C0086a.i(i7, "serialized size must be non-negative, was "));
        } else if (getMemoizedSerializedSize() != Integer.MAX_VALUE) {
            return getMemoizedSerializedSize();
        } else {
            if (schema == null) {
                d0 d0Var2 = d0.f1601c;
                d0Var2.getClass();
                i2 = d0Var2.a(getClass()).e(this);
            } else {
                i2 = schema.e(this);
            }
            setMemoizedSerializedSize(i2);
            return i2;
        }
    }

    public int hashCode() {
        if (isMutable()) {
            return computeHashCode();
        }
        if (hashCodeIsNotMemoized()) {
            setMemoizedHashCode(computeHashCode());
        }
        return getMemoizedHashCode();
    }

    public boolean hashCodeIsNotMemoized() {
        if (getMemoizedHashCode() == 0) {
            return true;
        }
        return false;
    }

    public final boolean isInitialized() {
        return isInitialized(this, true);
    }

    public boolean isMutable() {
        if ((this.memoizedSerializedSize & Integer.MIN_VALUE) != 0) {
            return true;
        }
        return false;
    }

    public void makeImmutable() {
        d0 d0Var = d0.f1601c;
        d0Var.getClass();
        d0Var.a(getClass()).c(this);
        markImmutable();
    }

    public void markImmutable() {
        this.memoizedSerializedSize &= Integer.MAX_VALUE;
    }

    public void mergeLengthDelimitedField(int i2, ByteString byteString) {
        if (this.unknownFields == UnknownFieldSetLite.f) {
            this.unknownFields = new UnknownFieldSetLite();
        }
        UnknownFieldSetLite unknownFieldSetLite = this.unknownFields;
        unknownFieldSetLite.a();
        if (i2 != 0) {
            unknownFieldSetLite.f((i2 << 3) | 2, byteString);
            return;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    public final void mergeUnknownFields(UnknownFieldSetLite unknownFieldSetLite) {
        this.unknownFields = UnknownFieldSetLite.e(this.unknownFields, unknownFieldSetLite);
    }

    public void mergeVarintField(int i2, int i7) {
        if (this.unknownFields == UnknownFieldSetLite.f) {
            this.unknownFields = new UnknownFieldSetLite();
        }
        UnknownFieldSetLite unknownFieldSetLite = this.unknownFields;
        unknownFieldSetLite.a();
        if (i2 != 0) {
            unknownFieldSetLite.f(i2 << 3, Long.valueOf((long) i7));
            return;
        }
        throw new IllegalArgumentException("Zero is not a valid field number.");
    }

    public GeneratedMessageLite newMutableInstance() {
        return (GeneratedMessageLite) dynamicMethod(MethodToInvoke.NEW_MUTABLE_INSTANCE);
    }

    public boolean parseUnknownField(int i2, CodedInputStream codedInputStream) {
        if ((i2 & 7) == 4) {
            return false;
        }
        if (this.unknownFields == UnknownFieldSetLite.f) {
            this.unknownFields = new UnknownFieldSetLite();
        }
        return this.unknownFields.d(i2, codedInputStream);
    }

    public void setMemoizedHashCode(int i2) {
        this.memoizedHashCode = i2;
    }

    public void setMemoizedSerializedSize(int i2) {
        if (i2 >= 0) {
            this.memoizedSerializedSize = (i2 & Integer.MAX_VALUE) | (this.memoizedSerializedSize & Integer.MIN_VALUE);
            return;
        }
        throw new IllegalStateException(C0086a.i(i2, "serialized size must be non-negative, was "));
    }

    public String toString() {
        String obj = super.toString();
        char[] cArr = W.f1590a;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("# ");
        sb2.append(obj);
        W.c(this, sb2, 0);
        return sb2.toString();
    }

    public void writeTo(CodedOutputStream codedOutputStream) {
        d0 d0Var = d0.f1601c;
        d0Var.getClass();
        Schema a7 = d0Var.a(getClass());
        P p6 = codedOutputStream.g;
        if (p6 == null) {
            p6 = new P(codedOutputStream);
        }
        a7.a(this, p6);
    }

    public static final <T extends GeneratedMessageLite> boolean isInitialized(T t, boolean z) {
        byte byteValue = ((Byte) t.dynamicMethod(MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        d0 d0Var = d0.f1601c;
        d0Var.getClass();
        boolean d = d0Var.a(t.getClass()).d(t);
        if (z) {
            t.dynamicMethod(MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED, d ? t : null);
        }
        return d;
    }

    public final <MessageType extends GeneratedMessageLite, BuilderType extends Builder> BuilderType createBuilder(MessageType messagetype) {
        return createBuilder().mergeFrom(messagetype);
    }

    public Object dynamicMethod(MethodToInvoke methodToInvoke) {
        return dynamicMethod(methodToInvoke, (Object) null, (Object) null);
    }

    public final GeneratedMessageLite getDefaultInstanceForType() {
        return (GeneratedMessageLite) dynamicMethod(MethodToInvoke.GET_DEFAULT_INSTANCE);
    }

    public final Builder newBuilderForType() {
        return (Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER);
    }

    public final Builder toBuilder() {
        return ((Builder) dynamicMethod(MethodToInvoke.NEW_BUILDER)).mergeFrom(this);
    }

    public static Internal$LongList mutableCopy(Internal$LongList internal$LongList) {
        int size = internal$LongList.size();
        return internal$LongList.a(size == 0 ? 10 : size * 2);
    }

    public static <T extends GeneratedMessageLite> T parseDelimitedFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        T c5 = c(t, inputStream, extensionRegistryLite);
        b(c5);
        return c5;
    }

    public static Internal$FloatList mutableCopy(Internal$FloatList internal$FloatList) {
        int size = internal$FloatList.size();
        return internal$FloatList.a(size == 0 ? 10 : size * 2);
    }

    public static Internal$DoubleList mutableCopy(Internal$DoubleList internal$DoubleList) {
        int size = internal$DoubleList.size();
        return internal$DoubleList.a(size == 0 ? 10 : size * 2);
    }

    public static Internal$BooleanList mutableCopy(Internal$BooleanList internal$BooleanList) {
        int size = internal$BooleanList.size();
        return internal$BooleanList.a(size == 0 ? 10 : size * 2);
    }

    public static <E> Internal$ProtobufList mutableCopy(Internal$ProtobufList internal$ProtobufList) {
        int size = internal$ProtobufList.size();
        return internal$ProtobufList.a(size == 0 ? 10 : size * 2);
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, ByteBuffer byteBuffer) {
        return parseFrom(t, byteBuffer, ExtensionRegistryLite.a());
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, ByteString byteString) {
        T parseFrom = parseFrom(t, byteString, ExtensionRegistryLite.a());
        b(parseFrom);
        return parseFrom;
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream v = byteString.v();
        T parsePartialFrom = parsePartialFrom(t, v, extensionRegistryLite);
        v.a(0);
        b(parsePartialFrom);
        return parsePartialFrom;
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, byte[] bArr) {
        T d = d(t, bArr, 0, bArr.length, ExtensionRegistryLite.a());
        b(d);
        return d;
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
        T d = d(t, bArr, 0, bArr.length, extensionRegistryLite);
        b(d);
        return d;
    }

    public int getSerializedSize() {
        return getSerializedSize((Schema) null);
    }

    public static <T extends GeneratedMessageLite> T parsePartialFrom(T t, CodedInputStream codedInputStream) {
        return parsePartialFrom(t, codedInputStream, ExtensionRegistryLite.a());
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, InputStream inputStream) {
        T parsePartialFrom = parsePartialFrom(t, CodedInputStream.g(inputStream), ExtensionRegistryLite.a());
        b(parsePartialFrom);
        return parsePartialFrom;
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        T parsePartialFrom = parsePartialFrom(t, CodedInputStream.g(inputStream), extensionRegistryLite);
        b(parsePartialFrom);
        return parsePartialFrom;
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, CodedInputStream codedInputStream) {
        return parseFrom(t, codedInputStream, ExtensionRegistryLite.a());
    }

    public static <T extends GeneratedMessageLite> T parseFrom(T t, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        T parsePartialFrom = parsePartialFrom(t, codedInputStream, extensionRegistryLite);
        b(parsePartialFrom);
        return parsePartialFrom;
    }
}
