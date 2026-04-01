package androidx.room;

import Tf.n;
import Tf.v;
import android.content.Context;
import androidx.room.RoomDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\n\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\n\u0010\u000bJ5\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\"\b\b\u0000\u0010\u0004*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0007¢\u0006\u0004\b\u0010\u0010\u0011J?\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\"\b\b\u0000\u0010\u0004*\u00020\f2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0007¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/room/Room;", "", "<init>", "()V", "T", "C", "Ljava/lang/Class;", "klass", "", "suffix", "getGeneratedImplementation", "(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;", "Landroidx/room/RoomDatabase;", "Landroid/content/Context;", "context", "Landroidx/room/RoomDatabase$Builder;", "inMemoryDatabaseBuilder", "(Landroid/content/Context;Ljava/lang/Class;)Landroidx/room/RoomDatabase$Builder;", "name", "databaseBuilder", "(Landroid/content/Context;Ljava/lang/Class;Ljava/lang/String;)Landroidx/room/RoomDatabase$Builder;", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Room {
    public static final Room INSTANCE = new Room();

    private Room() {
    }

    public static final <T extends RoomDatabase> RoomDatabase.Builder<T> databaseBuilder(Context context, Class<T> cls, String str) {
        boolean z;
        j.e(context, "context");
        j.e(cls, "klass");
        if (str == null || n.C0(str)) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return new RoomDatabase.Builder<>(context, cls, str);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
    }

    public static final <T, C> T getGeneratedImplementation(Class<C> cls, String str) {
        String str2;
        j.e(cls, "klass");
        j.e(str, "suffix");
        Package packageR = cls.getPackage();
        j.b(packageR);
        String name = packageR.getName();
        String canonicalName = cls.getCanonicalName();
        j.b(canonicalName);
        j.d(name, "fullPackage");
        if (name.length() != 0) {
            canonicalName = canonicalName.substring(name.length() + 1);
            j.d(canonicalName, "this as java.lang.String).substring(startIndex)");
        }
        String concat = v.r0(canonicalName, '.', '_').concat(str);
        try {
            if (name.length() == 0) {
                str2 = concat;
            } else {
                str2 = name + '.' + concat;
            }
            Class<?> cls2 = Class.forName(str2, true, cls.getClassLoader());
            j.c(cls2, "null cannot be cast to non-null type java.lang.Class<T of androidx.room.Room.getGeneratedImplementation>");
            return cls2.getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (ClassNotFoundException unused) {
            throw new RuntimeException("Cannot find implementation for " + cls.getCanonicalName() + ". " + concat + " does not exist");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException("Cannot access the constructor " + cls.getCanonicalName());
        } catch (InstantiationException unused3) {
            throw new RuntimeException("Failed to create an instance of " + cls.getCanonicalName());
        }
    }

    public static final <T extends RoomDatabase> RoomDatabase.Builder<T> inMemoryDatabaseBuilder(Context context, Class<T> cls) {
        j.e(context, "context");
        j.e(cls, "klass");
        return new RoomDatabase.Builder<>(context, cls, (String) null);
    }
}
