package com.google.protobuf;

import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbstractMessageLite implements MessageLite {
    protected int memoizedHashCode;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Builder implements MessageLite.Builder {
        @Deprecated
        public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
            addAll(iterable, (List) collection);
        }

        public static UninitializedMessageException newUninitializedMessageException(MessageLite messageLite) {
            return new UninitializedMessageException();
        }

        public final String a(String str) {
            return "Reading " + getClass().getName() + " from a " + str + " threw an IOException (should never happen).";
        }

        public abstract Builder clone();

        public abstract Builder internalMergeFrom(AbstractMessageLite abstractMessageLite);

        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            int read = inputStream.read();
            if (read == -1) {
                return false;
            }
            mergeFrom((InputStream) new C0129a(inputStream, CodedInputStream.s(inputStream, read)), extensionRegistryLite);
            return true;
        }

        public abstract Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

        public Builder mergeFrom(byte[] bArr, int i2, int i7) {
            try {
                C0138j f = CodedInputStream.f(bArr, 0, i7, false);
                mergeFrom((CodedInputStream) f);
                f.a(0);
                return this;
            } catch (F e) {
                throw e;
            } catch (IOException e7) {
                throw new RuntimeException(a("byte array"), e7);
            }
        }

        public static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
            Charset charset = D.f1578a;
            iterable.getClass();
            if (iterable instanceof I) {
                List b = ((I) iterable).b();
                I i2 = (I) list;
                int size = list.size();
                for (Object next : b) {
                    if (next == null) {
                        String str = "Element at index " + (i2.size() - size) + " is null.";
                        for (int size2 = i2.size() - 1; size2 >= size; size2--) {
                            i2.remove(size2);
                        }
                        throw new NullPointerException(str);
                    } else if (next instanceof ByteString) {
                        i2.f((ByteString) next);
                    } else {
                        i2.add((String) next);
                    }
                }
            } else if (iterable instanceof b0) {
                list.addAll((Collection) iterable);
            } else {
                if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                    ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
                }
                int size3 = list.size();
                for (T next2 : iterable) {
                    if (next2 == null) {
                        String str2 = "Element at index " + (list.size() - size3) + " is null.";
                        for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                            list.remove(size4);
                        }
                        throw new NullPointerException(str2);
                    }
                    list.add(next2);
                }
            }
        }

        public boolean mergeDelimitedFrom(InputStream inputStream) {
            return mergeDelimitedFrom(inputStream, ExtensionRegistryLite.a());
        }

        public Builder mergeFrom(byte[] bArr, int i2, int i7, ExtensionRegistryLite extensionRegistryLite) {
            try {
                C0138j f = CodedInputStream.f(bArr, 0, i7, false);
                mergeFrom((CodedInputStream) f, extensionRegistryLite);
                f.a(0);
                return this;
            } catch (F e) {
                throw e;
            } catch (IOException e7) {
                throw new RuntimeException(a("byte array"), e7);
            }
        }

        public Builder mergeFrom(CodedInputStream codedInputStream) {
            return mergeFrom(codedInputStream, ExtensionRegistryLite.a());
        }

        public Builder mergeFrom(ByteString byteString) {
            try {
                CodedInputStream v = byteString.v();
                mergeFrom(v);
                v.a(0);
                return this;
            } catch (F e) {
                throw e;
            } catch (IOException e7) {
                throw new RuntimeException(a("ByteString"), e7);
            }
        }

        public Builder mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) {
            try {
                CodedInputStream v = byteString.v();
                mergeFrom(v, extensionRegistryLite);
                v.a(0);
                return this;
            } catch (F e) {
                throw e;
            } catch (IOException e7) {
                throw new RuntimeException(a("ByteString"), e7);
            }
        }

        public Builder mergeFrom(byte[] bArr) {
            return mergeFrom(bArr, 0, bArr.length);
        }

        public Builder mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) {
            return mergeFrom(bArr, 0, bArr.length, extensionRegistryLite);
        }

        public Builder mergeFrom(InputStream inputStream) {
            CodedInputStream g = CodedInputStream.g(inputStream);
            mergeFrom(g);
            g.a(0);
            return this;
        }

        public Builder mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
            CodedInputStream g = CodedInputStream.g(inputStream);
            mergeFrom(g, extensionRegistryLite);
            g.a(0);
            return this;
        }

        public Builder mergeFrom(MessageLite messageLite) {
            if (getDefaultInstanceForType().getClass().isInstance(messageLite)) {
                return internalMergeFrom((AbstractMessageLite) messageLite);
            }
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
    }

    @Deprecated
    public static <T> void addAll(Iterable<T> iterable, Collection<? super T> collection) {
        Builder.addAll(iterable, (List) collection);
    }

    public static void checkByteStringIsUtf8(ByteString byteString) {
        if (!byteString.u()) {
            throw new IllegalArgumentException("Byte string is not UTF-8.");
        }
    }

    public final String a(String str) {
        return "Serializing " + getClass().getName() + " to a " + str + " threw an IOException (should never happen).";
    }

    public int getMemoizedSerializedSize() {
        throw new UnsupportedOperationException();
    }

    public int getSerializedSize(Schema schema) {
        int memoizedSerializedSize = getMemoizedSerializedSize();
        if (memoizedSerializedSize != -1) {
            return memoizedSerializedSize;
        }
        int e = schema.e(this);
        setMemoizedSerializedSize(e);
        return e;
    }

    public UninitializedMessageException newUninitializedMessageException() {
        return new UninitializedMessageException();
    }

    public void setMemoizedSerializedSize(int i2) {
        throw new UnsupportedOperationException();
    }

    public byte[] toByteArray() {
        try {
            int serializedSize = getSerializedSize();
            byte[] bArr = new byte[serializedSize];
            Logger logger = CodedOutputStream.f1576h;
            C0142n nVar = new C0142n(bArr, 0, serializedSize);
            writeTo(nVar);
            if (nVar.u0() == 0) {
                return bArr;
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException(a("byte array"), e);
        }
    }

    public ByteString toByteString() {
        try {
            int serializedSize = getSerializedSize();
            C0137i iVar = ByteString.e;
            byte[] bArr = new byte[serializedSize];
            Logger logger = CodedOutputStream.f1576h;
            C0142n nVar = new C0142n(bArr, 0, serializedSize);
            writeTo(nVar);
            if (nVar.u0() == 0) {
                return new C0137i(bArr);
            }
            throw new IllegalStateException("Did not write as much data as expected.");
        } catch (IOException e) {
            throw new RuntimeException(a("ByteString"), e);
        }
    }

    public void writeDelimitedTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        int b0 = CodedOutputStream.b0(serializedSize) + serializedSize;
        if (b0 > 4096) {
            b0 = 4096;
        }
        C0143o oVar = new C0143o(outputStream, b0);
        oVar.r0(serializedSize);
        writeTo(oVar);
        if (oVar.l > 0) {
            oVar.z0();
        }
    }

    public void writeTo(OutputStream outputStream) {
        int serializedSize = getSerializedSize();
        Logger logger = CodedOutputStream.f1576h;
        if (serializedSize > 4096) {
            serializedSize = 4096;
        }
        C0143o oVar = new C0143o(outputStream, serializedSize);
        writeTo(oVar);
        if (oVar.l > 0) {
            oVar.z0();
        }
    }

    public static <T> void addAll(Iterable<T> iterable, List<? super T> list) {
        Builder.addAll(iterable, list);
    }
}
