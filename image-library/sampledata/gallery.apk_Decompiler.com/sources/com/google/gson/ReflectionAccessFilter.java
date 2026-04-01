package com.google.gson;

import com.google.gson.internal.ReflectionAccessFilterHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ReflectionAccessFilter {
    public static final ReflectionAccessFilter BLOCK_ALL_ANDROID = new ReflectionAccessFilter() {
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isAndroidType(cls)) {
                return FilterResult.BLOCK_ALL;
            }
            return FilterResult.INDECISIVE;
        }

        public String toString() {
            return "ReflectionAccessFilter#BLOCK_ALL_ANDROID";
        }
    };
    public static final ReflectionAccessFilter BLOCK_ALL_JAVA = new ReflectionAccessFilter() {
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isJavaType(cls)) {
                return FilterResult.BLOCK_ALL;
            }
            return FilterResult.INDECISIVE;
        }

        public String toString() {
            return "ReflectionAccessFilter#BLOCK_ALL_JAVA";
        }
    };
    public static final ReflectionAccessFilter BLOCK_ALL_PLATFORM = new ReflectionAccessFilter() {
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isAnyPlatformType(cls)) {
                return FilterResult.BLOCK_ALL;
            }
            return FilterResult.INDECISIVE;
        }

        public String toString() {
            return "ReflectionAccessFilter#BLOCK_ALL_PLATFORM";
        }
    };
    public static final ReflectionAccessFilter BLOCK_INACCESSIBLE_JAVA = new ReflectionAccessFilter() {
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isJavaType(cls)) {
                return FilterResult.BLOCK_INACCESSIBLE;
            }
            return FilterResult.INDECISIVE;
        }

        public String toString() {
            return "ReflectionAccessFilter#BLOCK_INACCESSIBLE_JAVA";
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FilterResult {
        ALLOW,
        INDECISIVE,
        BLOCK_INACCESSIBLE,
        BLOCK_ALL
    }

    FilterResult check(Class<?> cls);
}
