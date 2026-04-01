package androidx.versionedparcelable;

import android.os.IBinder;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VersionedParcel {
    protected final ArrayMap<String, Class> mParcelizerCache;
    protected final ArrayMap<String, Method> mReadCache;
    protected final ArrayMap<String, Method> mWriteCache;

    public VersionedParcel(ArrayMap<String, Method> arrayMap, ArrayMap<String, Method> arrayMap2, ArrayMap<String, Class> arrayMap3) {
        this.mReadCache = arrayMap;
        this.mWriteCache = arrayMap2;
        this.mParcelizerCache = arrayMap3;
    }

    private Class findParcelClass(Class<? extends VersionedParcelable> cls) {
        Class cls2 = this.mParcelizerCache.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        String name = cls.getPackage().getName();
        String simpleName = cls.getSimpleName();
        Class<?> cls3 = Class.forName(name + "." + simpleName + "Parcelizer", false, cls.getClassLoader());
        this.mParcelizerCache.put(cls.getName(), cls3);
        return cls3;
    }

    private Method getReadMethod(String str) {
        Method method = this.mReadCache.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Class<VersionedParcel> cls = VersionedParcel.class;
        Method declaredMethod = Class.forName(str, true, cls.getClassLoader()).getDeclaredMethod("read", new Class[]{cls});
        this.mReadCache.put(str, declaredMethod);
        return declaredMethod;
    }

    private <T> int getType(T t) {
        if (t instanceof String) {
            return 4;
        }
        if (t instanceof Parcelable) {
            return 2;
        }
        if (t instanceof VersionedParcelable) {
            return 1;
        }
        if (t instanceof Serializable) {
            return 3;
        }
        if (t instanceof IBinder) {
            return 5;
        }
        if (t instanceof Integer) {
            return 7;
        }
        if (t instanceof Float) {
            return 8;
        }
        throw new IllegalArgumentException(t.getClass().getName().concat(" cannot be VersionedParcelled"));
    }

    private Method getWriteMethod(Class cls) {
        Method method = this.mWriteCache.get(cls.getName());
        if (method != null) {
            return method;
        }
        Class findParcelClass = findParcelClass(cls);
        System.currentTimeMillis();
        Method declaredMethod = findParcelClass.getDeclaredMethod("write", new Class[]{cls, VersionedParcel.class});
        this.mWriteCache.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    private void writeSerializable(Serializable serializable) {
        if (serializable == null) {
            writeString((String) null);
            return;
        }
        String name = serializable.getClass().getName();
        writeString(name);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            writeByteArray(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(C0212a.m("VersionedParcelable encountered IOException writing serializable object (name = ", name, ")"), e);
        }
    }

    private void writeVersionedParcelableCreator(VersionedParcelable versionedParcelable) {
        try {
            writeString(findParcelClass(versionedParcelable.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName().concat(" does not have a Parcelizer"), e);
        }
    }

    public abstract void closeField();

    public abstract VersionedParcel createSubParcel();

    public boolean isStream() {
        return false;
    }

    public <T> T[] readArray(T[] tArr, int i2) {
        if (!readField(i2)) {
            return tArr;
        }
        return readArray(tArr);
    }

    public abstract byte[] readByteArray();

    public abstract boolean readField(int i2);

    public <T extends VersionedParcelable> T readFromParcel(String str, VersionedParcel versionedParcel) {
        try {
            return (VersionedParcelable) getReadMethod(str).invoke((Object) null, new Object[]{versionedParcel});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e7) {
            if (e7.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e7.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e7);
        } catch (NoSuchMethodException e8) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e8);
        } catch (ClassNotFoundException e9) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e9);
        }
    }

    public abstract int readInt();

    public int readInt(int i2, int i7) {
        if (!readField(i7)) {
            return i2;
        }
        return readInt();
    }

    public abstract long readLong();

    public long readLong(long j2, int i2) {
        if (!readField(i2)) {
            return j2;
        }
        return readLong();
    }

    public abstract <T extends Parcelable> T readParcelable();

    public <T extends Parcelable> T readParcelable(T t, int i2) {
        if (!readField(i2)) {
            return t;
        }
        return readParcelable();
    }

    public Serializable readSerializable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        try {
            return (Serializable) new ObjectInputStream(new ByteArrayInputStream(readByteArray())) {
                public Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
                    Class<?> cls = Class.forName(objectStreamClass.getName(), false, getClass().getClassLoader());
                    if (cls != null) {
                        return cls;
                    }
                    return super.resolveClass(objectStreamClass);
                }
            }.readObject();
        } catch (IOException e) {
            throw new RuntimeException(C0212a.m("VersionedParcelable encountered IOException reading a Serializable object (name = ", readString, ")"), e);
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException(C0212a.m("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = ", readString, ")"), e7);
        }
    }

    public abstract String readString();

    public String readString(String str, int i2) {
        if (!readField(i2)) {
            return str;
        }
        return readString();
    }

    public abstract IBinder readStrongBinder();

    public <T extends VersionedParcelable> T readVersionedParcelable(T t, int i2) {
        if (!readField(i2)) {
            return t;
        }
        return readVersionedParcelable();
    }

    public abstract void setOutputField(int i2);

    public <T> void writeArray(T[] tArr, int i2) {
        setOutputField(i2);
        writeArray(tArr);
    }

    public abstract void writeByteArray(byte[] bArr);

    public abstract void writeInt(int i2);

    public void writeInt(int i2, int i7) {
        setOutputField(i7);
        writeInt(i2);
    }

    public abstract void writeLong(long j2);

    public void writeLong(long j2, int i2) {
        setOutputField(i2);
        writeLong(j2);
    }

    public abstract void writeParcelable(Parcelable parcelable);

    public void writeParcelable(Parcelable parcelable, int i2) {
        setOutputField(i2);
        writeParcelable(parcelable);
    }

    public abstract void writeString(String str);

    public void writeString(String str, int i2) {
        setOutputField(i2);
        writeString(str);
    }

    public abstract void writeStrongBinder(IBinder iBinder);

    public <T extends VersionedParcelable> void writeToParcel(T t, VersionedParcel versionedParcel) {
        try {
            getWriteMethod(t.getClass()).invoke((Object) null, new Object[]{t, versionedParcel});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e);
        } catch (InvocationTargetException e7) {
            if (e7.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e7.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e7);
        } catch (NoSuchMethodException e8) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e8);
        } catch (ClassNotFoundException e9) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e9);
        }
    }

    public void writeVersionedParcelable(VersionedParcelable versionedParcelable, int i2) {
        setOutputField(i2);
        writeVersionedParcelable(versionedParcelable);
    }

    public <T> T[] readArray(T[] tArr) {
        int readInt = readInt();
        if (readInt < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(readInt);
        if (readInt != 0) {
            int readInt2 = readInt();
            if (readInt < 0) {
                return null;
            }
            if (readInt2 == 1) {
                while (readInt > 0) {
                    arrayList.add(readVersionedParcelable());
                    readInt--;
                }
            } else if (readInt2 == 2) {
                while (readInt > 0) {
                    arrayList.add(readParcelable());
                    readInt--;
                }
            } else if (readInt2 == 3) {
                while (readInt > 0) {
                    arrayList.add(readSerializable());
                    readInt--;
                }
            } else if (readInt2 == 4) {
                while (readInt > 0) {
                    arrayList.add(readString());
                    readInt--;
                }
            } else if (readInt2 == 5) {
                while (readInt > 0) {
                    arrayList.add(readStrongBinder());
                    readInt--;
                }
            }
        }
        return arrayList.toArray(tArr);
    }

    public <T extends VersionedParcelable> T readVersionedParcelable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        return readFromParcel(readString, createSubParcel());
    }

    public <T> void writeArray(T[] tArr) {
        if (tArr == null) {
            writeInt(-1);
            return;
        }
        int length = tArr.length;
        writeInt(length);
        if (length > 0) {
            int i2 = 0;
            int type = getType(tArr[0]);
            writeInt(type);
            if (type == 1) {
                while (i2 < length) {
                    writeVersionedParcelable((VersionedParcelable) tArr[i2]);
                    i2++;
                }
            } else if (type == 2) {
                while (i2 < length) {
                    writeParcelable((Parcelable) tArr[i2]);
                    i2++;
                }
            } else if (type == 3) {
                while (i2 < length) {
                    writeSerializable((Serializable) tArr[i2]);
                    i2++;
                }
            } else if (type == 4) {
                while (i2 < length) {
                    writeString((String) tArr[i2]);
                    i2++;
                }
            } else if (type == 5) {
                while (i2 < length) {
                    writeStrongBinder((IBinder) tArr[i2]);
                    i2++;
                }
            }
        }
    }

    public void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            writeString((String) null);
            return;
        }
        writeVersionedParcelableCreator(versionedParcelable);
        VersionedParcel createSubParcel = createSubParcel();
        writeToParcel(versionedParcelable, createSubParcel);
        createSubParcel.closeField();
    }

    public void setSerializationFlags(boolean z, boolean z3) {
    }
}
