package com.google.gson.internal;

import W.a;
import a6.g;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import i.C0212a;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ConstructorConstructor {
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    private final List<ReflectionAccessFilter> reflectionFilters;
    private final boolean useJdkUnsafe;

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> map, boolean z, List<ReflectionAccessFilter> list) {
        this.instanceCreators = map;
        this.useJdkUnsafe = z;
        this.reflectionFilters = list;
    }

    public static String checkInstantiable(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(cls.getName());
        }
        if (!Modifier.isAbstract(modifiers)) {
            return null;
        }
        return "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + cls.getName() + "\nSee " + TroubleshootingGuide.createUrl("r8-abstract-class");
    }

    private static boolean hasStringKeyType(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return true;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 0 && GsonTypes.getRawType(actualTypeArguments[0]) == String.class) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$get$2(String str) {
        throw new JsonIOException(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$get$3(String str) {
        throw new JsonIOException(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$get$4(String str) {
        throw new JsonIOException(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Collection lambda$newCollectionConstructor$10() {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Collection lambda$newCollectionConstructor$11() {
        return new LinkedHashSet();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Collection lambda$newCollectionConstructor$12() {
        return new TreeSet();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Collection lambda$newCollectionConstructor$13() {
        return new ArrayDeque();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$newDefaultConstructor$7(String str) {
        throw new JsonIOException(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$newDefaultConstructor$8(String str) {
        throw new JsonIOException(str);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$newDefaultConstructor$9(Constructor constructor) {
        try {
            return constructor.newInstance((Object[]) null);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(constructor) + "' with no args", e);
        } catch (InvocationTargetException e7) {
            throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(constructor) + "' with no args", e7.getCause());
        } catch (IllegalAccessException e8) {
            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e8);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$newMapConstructor$14() {
        return new LinkedTreeMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$newMapConstructor$15() {
        return new LinkedHashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$newMapConstructor$16() {
        return new TreeMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$newMapConstructor$17() {
        return new ConcurrentHashMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Map lambda$newMapConstructor$18() {
        return new ConcurrentSkipListMap();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$newSpecialCollectionConstructor$5(Type type) {
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + type.toString());
        }
        throw new JsonIOException("Invalid EnumSet type: " + type.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$newSpecialCollectionConstructor$6(Type type) {
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return new EnumMap((Class) type2);
            }
            throw new JsonIOException("Invalid EnumMap type: " + type.toString());
        }
        throw new JsonIOException("Invalid EnumMap type: " + type.toString());
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$newUnsafeAllocator$19(Class cls) {
        try {
            return UnsafeAllocator.INSTANCE.newInstance(cls);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create instance of " + cls + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$newUnsafeAllocator$20(String str) {
        throw new JsonIOException(str);
    }

    private static ObjectConstructor<? extends Collection<? extends Object>> newCollectionConstructor(Class<?> cls) {
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new a(14);
        }
        if (cls.isAssignableFrom(LinkedHashSet.class)) {
            return new a(15);
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new a(16);
        }
        if (cls.isAssignableFrom(ArrayDeque.class)) {
            return new a(17);
        }
        return null;
    }

    private static <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> cls, ReflectionAccessFilter.FilterResult filterResult) {
        String tryMakeAccessible;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
            ReflectionAccessFilter.FilterResult filterResult2 = ReflectionAccessFilter.FilterResult.ALLOW;
            if (filterResult != filterResult2 && (!ReflectionAccessFilterHelper.canAccess(declaredConstructor, (Object) null) || (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL && !Modifier.isPublic(declaredConstructor.getModifiers())))) {
                return new M8.a("Unable to invoke no-args constructor of " + cls + "; constructor is not accessible and ReflectionAccessFilter does not permit making it accessible. Register an InstanceCreator or a TypeAdapter for this type, change the visibility of the constructor or adjust the access filter.", 3);
            } else if (filterResult != filterResult2 || (tryMakeAccessible = ReflectionHelper.tryMakeAccessible(declaredConstructor)) == null) {
                return new c(declaredConstructor);
            } else {
                return new M8.a(tryMakeAccessible, 4);
            }
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private static <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return newCollectionConstructor(cls);
        }
        if (Map.class.isAssignableFrom(cls)) {
            return newMapConstructor(type, cls);
        }
        return null;
    }

    private static ObjectConstructor<? extends Map<? extends Object, Object>> newMapConstructor(Type type, Class<?> cls) {
        if (cls.isAssignableFrom(LinkedTreeMap.class) && hasStringKeyType(type)) {
            return new a(9);
        }
        if (cls.isAssignableFrom(LinkedHashMap.class)) {
            return new a(10);
        }
        if (cls.isAssignableFrom(TreeMap.class)) {
            return new a(11);
        }
        if (cls.isAssignableFrom(ConcurrentHashMap.class)) {
            return new a(12);
        }
        if (cls.isAssignableFrom(ConcurrentSkipListMap.class)) {
            return new a(13);
        }
        return null;
    }

    private static <T> ObjectConstructor<T> newSpecialCollectionConstructor(Type type, Class<? super T> cls) {
        if (EnumSet.class.isAssignableFrom(cls)) {
            return new b(type, 0);
        }
        if (cls == EnumMap.class) {
            return new b(type, 1);
        }
        return null;
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Class<? super T> cls) {
        if (this.useJdkUnsafe) {
            return new g(17, cls);
        }
        String str = "Unable to create instance of " + cls + "; usage of JDK Unsafe is disabled. Registering an InstanceCreator or a TypeAdapter for this type, adding a no-args constructor, or enabling usage of JDK Unsafe may fix this problem.";
        if (cls.getDeclaredConstructors().length == 0) {
            str = C0212a.A(str, " Or adjust your R8 configuration to keep the no-args constructor of the class.");
        }
        return new M8.a(str, 2);
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        return get(typeToken, true);
    }

    public String toString() {
        return this.instanceCreators.toString();
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken, boolean z) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        InstanceCreator instanceCreator = this.instanceCreators.get(type);
        if (instanceCreator != null) {
            return new a(instanceCreator, type, 0);
        }
        InstanceCreator instanceCreator2 = this.instanceCreators.get(rawType);
        if (instanceCreator2 != null) {
            return new a(instanceCreator2, type, 1);
        }
        ObjectConstructor<T> newSpecialCollectionConstructor = newSpecialCollectionConstructor(type, rawType);
        if (newSpecialCollectionConstructor != null) {
            return newSpecialCollectionConstructor;
        }
        ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, rawType);
        ObjectConstructor<T> newDefaultConstructor = newDefaultConstructor(rawType, filterResult);
        if (newDefaultConstructor != null) {
            return newDefaultConstructor;
        }
        ObjectConstructor<T> newDefaultImplementationConstructor = newDefaultImplementationConstructor(type, rawType);
        if (newDefaultImplementationConstructor != null) {
            return newDefaultImplementationConstructor;
        }
        String checkInstantiable = checkInstantiable(rawType);
        if (checkInstantiable != null) {
            return new M8.a(checkInstantiable, 5);
        }
        if (!z) {
            return new M8.a("Unable to create instance of " + rawType + "; Register an InstanceCreator or a TypeAdapter for this type.", 6);
        } else if (filterResult == ReflectionAccessFilter.FilterResult.ALLOW) {
            return newUnsafeAllocator(rawType);
        } else {
            return new M8.a("Unable to create instance of " + rawType + "; ReflectionAccessFilter does not permit using reflection or Unsafe. Register an InstanceCreator or a TypeAdapter for this type or adjust the access filter to allow using reflection.", 7);
        }
    }
}
