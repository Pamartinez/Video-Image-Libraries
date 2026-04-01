package com.adobe.internal.xmp.options;

import com.adobe.internal.xmp.XMPException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Options {
    private Map optionNames = null;
    private int options = 0;

    public Options() {
    }

    private void assertOptionsValid(int i2) {
        int i7 = (~getValidOptions()) & i2;
        if (i7 == 0) {
            assertConsistency(i2);
            return;
        }
        throw new XMPException("The option bit(s) 0x" + Integer.toHexString(i7) + " are invalid!", 103);
    }

    private String getOptionName(int i2) {
        Map procureOptionNames = procureOptionNames();
        Integer num = new Integer(i2);
        String str = (String) procureOptionNames.get(num);
        if (str != null) {
            return str;
        }
        String defineOptionName = defineOptionName(i2);
        if (defineOptionName == null) {
            return "<option name not defined>";
        }
        procureOptionNames.put(num, defineOptionName);
        return defineOptionName;
    }

    private Map procureOptionNames() {
        if (this.optionNames == null) {
            this.optionNames = new HashMap();
        }
        return this.optionNames;
    }

    public void clear() {
        this.options = 0;
    }

    public boolean containsAllOptions(int i2) {
        if ((getOptions() & i2) == i2) {
            return true;
        }
        return false;
    }

    public boolean containsOneOf(int i2) {
        if ((getOptions() & i2) != 0) {
            return true;
        }
        return false;
    }

    public abstract String defineOptionName(int i2);

    public boolean equals(Object obj) {
        if (getOptions() == ((Options) obj).getOptions()) {
            return true;
        }
        return false;
    }

    public boolean getOption(int i2) {
        if ((this.options & i2) != 0) {
            return true;
        }
        return false;
    }

    public int getOptions() {
        return this.options;
    }

    public String getOptionsString() {
        if (this.options == 0) {
            return "<none>";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = this.options;
        while (i2 != 0) {
            int i7 = (i2 - 1) & i2;
            stringBuffer.append(getOptionName(i2 ^ i7));
            if (i7 != 0) {
                stringBuffer.append(" | ");
            }
            i2 = i7;
        }
        return stringBuffer.toString();
    }

    public abstract int getValidOptions();

    public int hashCode() {
        return getOptions();
    }

    public boolean isExactly(int i2) {
        if (getOptions() == i2) {
            return true;
        }
        return false;
    }

    public void setOption(int i2, boolean z) {
        int i7;
        if (z) {
            i7 = i2 | this.options;
        } else {
            i7 = (~i2) & this.options;
        }
        this.options = i7;
    }

    public void setOptions(int i2) {
        assertOptionsValid(i2);
        this.options = i2;
    }

    public String toString() {
        return "0x" + Integer.toHexString(this.options);
    }

    public Options(int i2) {
        assertOptionsValid(i2);
        setOptions(i2);
    }

    public void assertConsistency(int i2) {
    }
}
