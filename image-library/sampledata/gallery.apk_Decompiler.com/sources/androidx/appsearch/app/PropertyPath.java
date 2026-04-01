package androidx.appsearch.app;

import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import c0.C0086a;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PropertyPath implements Iterable<PathSegment> {
    private final List<PathSegment> mPathList = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PathSegment {
        private final int mPropertyIndex;
        private final String mPropertyName;

        public PathSegment(String str, int i2) {
            this.mPropertyName = (String) ObjectsCompat.requireNonNull(str);
            this.mPropertyIndex = i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof PathSegment)) {
                return false;
            }
            PathSegment pathSegment = (PathSegment) obj;
            if (this.mPropertyIndex != pathSegment.mPropertyIndex || !this.mPropertyName.equals(pathSegment.mPropertyName)) {
                return false;
            }
            return true;
        }

        public int getPropertyIndex() {
            return this.mPropertyIndex;
        }

        public String getPropertyName() {
            return this.mPropertyName;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mPropertyName, Integer.valueOf(this.mPropertyIndex));
        }

        public String toString() {
            if (this.mPropertyIndex == -1) {
                return this.mPropertyName;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.mPropertyName);
            sb2.append("[");
            return C0086a.l(sb2, this.mPropertyIndex, "]");
        }
    }

    public PropertyPath(String str) {
        Preconditions.checkNotNull(str);
        try {
            recursivePathScan(str);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage() + ": " + str);
        }
    }

    private String consumePropertyWithIndex(String str, int i2) {
        Preconditions.checkNotNull(str);
        String substring = str.substring(0, i2);
        int indexOf = str.indexOf(93, i2);
        if (indexOf != -1) {
            int i7 = indexOf + 1;
            if (i7 >= str.length() || str.charAt(i7) == '.') {
                String substring2 = str.substring(i2 + 1, indexOf);
                try {
                    int parseInt = Integer.parseInt(substring2);
                    if (parseInt >= 0) {
                        this.mPathList.add(new PathSegment(substring, parseInt));
                        if (i7 < str.length()) {
                            return str.substring(indexOf + 2);
                        }
                        return null;
                    }
                    throw new IllegalArgumentException("Malformed path (path index less than 0)");
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException(C0212a.m("Malformed path (\"", substring2, "\" as path index)"));
                }
            } else {
                throw new IllegalArgumentException("Malformed path (']' not followed by '.'): ".concat(str));
            }
        } else {
            throw new IllegalArgumentException("Malformed path (no ending ']')");
        }
    }

    private void recursivePathScan(String str) {
        boolean z;
        String str2;
        int i2 = 0;
        while (true) {
            if (i2 >= str.length()) {
                z = false;
                i2 = -1;
                break;
            }
            char charAt = str.charAt(i2);
            if (charAt == ']') {
                throw new IllegalArgumentException("Malformed path (no starting '[')");
            } else if (charAt != '[' && charAt != '.') {
                i2++;
            } else if (charAt == '[') {
                z = true;
            } else {
                z = false;
            }
        }
        if (i2 == 0 || str.isEmpty()) {
            throw new IllegalArgumentException("Malformed path (blank property name)");
        } else if (i2 == -1) {
            this.mPathList.add(new PathSegment(str, -1));
        } else {
            if (!z) {
                String substring = str.substring(0, i2);
                str2 = str.substring(i2 + 1);
                this.mPathList.add(new PathSegment(substring, -1));
            } else {
                str2 = consumePropertyWithIndex(str, i2);
                if (str2 == null) {
                    return;
                }
            }
            recursivePathScan(str2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof PropertyPath)) {
            return ObjectsCompat.equals(this.mPathList, ((PropertyPath) obj).mPathList);
        }
        return false;
    }

    public PathSegment get(int i2) {
        return this.mPathList.get(i2);
    }

    public int hashCode() {
        return ObjectsCompat.hashCode(this.mPathList);
    }

    public Iterator<PathSegment> iterator() {
        return this.mPathList.iterator();
    }

    public int size() {
        return this.mPathList.size();
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        for (int i2 = 0; i2 < this.mPathList.size(); i2++) {
            sb2.append(get(i2).toString());
            if (i2 < this.mPathList.size() - 1) {
                sb2.append('.');
            }
        }
        return sb2.toString();
    }
}
