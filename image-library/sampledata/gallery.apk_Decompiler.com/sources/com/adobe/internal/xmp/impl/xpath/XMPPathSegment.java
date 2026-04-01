package com.adobe.internal.xmp.impl.xpath;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XMPPathSegment {
    private boolean alias;
    private int aliasForm;
    private int kind;
    private String name;

    public XMPPathSegment(String str) {
        this.name = str;
    }

    public int getAliasForm() {
        return this.aliasForm;
    }

    public int getKind() {
        return this.kind;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAlias() {
        return this.alias;
    }

    public void setAlias(boolean z) {
        this.alias = z;
    }

    public void setAliasForm(int i2) {
        this.aliasForm = i2;
    }

    public void setKind(int i2) {
        this.kind = i2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        switch (this.kind) {
            case 1:
            case 2:
            case 3:
            case 4:
                return this.name;
            case 5:
            case 6:
                return this.name;
            default:
                return this.name;
        }
    }

    public XMPPathSegment(String str, int i2) {
        this.name = str;
        this.kind = i2;
    }
}
