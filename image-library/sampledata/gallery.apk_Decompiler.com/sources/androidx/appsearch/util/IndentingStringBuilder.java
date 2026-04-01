package androidx.appsearch.util;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IndentingStringBuilder {
    private int mIndentLevel = 0;
    private boolean mIndentNext = false;
    private final StringBuilder mStringBuilder = new StringBuilder();

    private void applyIndent() {
        for (int i2 = 0; i2 < this.mIndentLevel; i2++) {
            this.mStringBuilder.append("  ");
        }
    }

    private void applyIndentToString(String str) {
        int indexOf = str.indexOf("\n");
        if (indexOf == 0) {
            this.mStringBuilder.append("\n");
            this.mIndentNext = true;
            if (str.length() > 1) {
                applyIndentToString(str.substring(indexOf + 1));
            }
        } else if (indexOf >= 1) {
            applyIndentToString(str.substring(0, indexOf));
            this.mStringBuilder.append("\n");
            this.mIndentNext = true;
            int i2 = indexOf + 1;
            if (str.length() > i2) {
                applyIndentToString(str.substring(i2));
            }
        } else {
            if (this.mIndentNext) {
                applyIndent();
                this.mIndentNext = false;
            }
            this.mStringBuilder.append(str);
        }
    }

    public IndentingStringBuilder append(String str) {
        applyIndentToString(str);
        return this;
    }

    public IndentingStringBuilder decreaseIndentLevel() {
        int i2 = this.mIndentLevel;
        if (i2 != 0) {
            this.mIndentLevel = i2 - 1;
            return this;
        }
        throw new IllegalStateException("Cannot set indent level below 0.");
    }

    public IndentingStringBuilder increaseIndentLevel() {
        this.mIndentLevel++;
        return this;
    }

    public String toString() {
        return this.mStringBuilder.toString();
    }

    public IndentingStringBuilder append(Object obj) {
        applyIndentToString(obj.toString());
        return this;
    }
}
