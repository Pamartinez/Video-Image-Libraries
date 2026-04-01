package com.samsung.android.sdk.scs.ai.text.entity;

import com.samsung.android.sdk.scs.ai.text.entity.BasicEntityExtractor;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BasicEntity {
    private String bankAccountNumber;
    private String bankAmount;
    private String bankName;
    private Date endDate;
    private int endIndex;
    private LocalDateTime endLocalDateTime;
    private BasicEntityExtractor.EntityType entityType;
    private boolean hasDay;
    private boolean hasMonth;
    private boolean hasRecurrenceWithinRange;
    private boolean hasYear;
    private boolean isMappable;
    private boolean isRelative;
    private boolean isSpecialDay;
    private Map<BasicEntityExtractor.RecurrenceUnit, List<Integer>> recurrenceInfo;
    private String repeatInfo;
    private Date startDate;
    private int startIndex;
    private LocalDateTime startLocalDateTime;
    private String text;
    private String unitSymbol;
    private String unitValue;
    private EnumSet<BasicEntityExtractor.DateTimeUnit> unresolvedEndDateTimeUnit;
    private EnumSet<BasicEntityExtractor.DateTimeUnit> unresolvedStartDateTimeUnit;

    public static BasicEntity create() {
        return new BasicEntity();
    }

    public String getBankAccountNumber() {
        return this.bankAccountNumber;
    }

    public String getBankAmount() {
        return this.bankAmount;
    }

    public String getBankName() {
        return this.bankName;
    }

    public Date getEndDateTime() {
        return this.endDate;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public LocalDateTime getEndLocalDateTime() {
        return this.endLocalDateTime;
    }

    public Map<BasicEntityExtractor.RecurrenceUnit, List<Integer>> getRecurrenceInfo() {
        return this.recurrenceInfo;
    }

    public String getRepeatInfo() {
        return this.repeatInfo;
    }

    public Date getStartDateTime() {
        return this.startDate;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public LocalDateTime getStartLocalDateTime() {
        return this.startLocalDateTime;
    }

    public String getString() {
        return this.text;
    }

    public BasicEntityExtractor.EntityType getType() {
        return this.entityType;
    }

    public String getUnitSymbol() {
        return this.unitSymbol;
    }

    public String getUnitValue() {
        return this.unitValue;
    }

    public EnumSet<BasicEntityExtractor.DateTimeUnit> getUnresolvedEndDateTimeUnit() {
        return this.unresolvedEndDateTimeUnit;
    }

    public EnumSet<BasicEntityExtractor.DateTimeUnit> getUnresolvedStartDateTimeUnit() {
        return this.unresolvedStartDateTimeUnit;
    }

    public boolean hasDay() {
        return this.hasDay;
    }

    public boolean hasMonth() {
        return this.hasMonth;
    }

    public boolean hasRecurrenceWithinRange() {
        return this.hasRecurrenceWithinRange;
    }

    public boolean hasYear() {
        return this.hasYear;
    }

    public boolean isMappable() {
        return this.isMappable;
    }

    public boolean isRelative() {
        return this.isRelative;
    }

    public boolean isSpecialDay() {
        return this.isSpecialDay;
    }

    public void setBankAccountNumber(String str) {
        this.bankAccountNumber = str;
    }

    public void setBankAmount(String str) {
        this.bankAmount = str;
    }

    public void setBankName(String str) {
        this.bankName = str;
    }

    public void setEndDateTime(Date date) {
        this.endDate = date;
    }

    public void setEndIndex(int i2) {
        this.endIndex = i2;
    }

    public void setEndLocalDateTime(LocalDateTime localDateTime) {
        this.endLocalDateTime = localDateTime;
    }

    public void setHasDay(boolean z) {
        this.hasDay = z;
    }

    public void setHasMonth(boolean z) {
        this.hasMonth = z;
    }

    public void setHasRecurrenceWithinRange(boolean z) {
        this.hasRecurrenceWithinRange = z;
    }

    public void setHasYear(boolean z) {
        this.hasYear = z;
    }

    public void setMappable(boolean z) {
        this.isMappable = z;
    }

    public void setRecurrenceInfo(Map<BasicEntityExtractor.RecurrenceUnit, List<Integer>> map) {
        this.recurrenceInfo = map;
    }

    public void setRelative(boolean z) {
        this.isRelative = z;
    }

    public void setRepeatInfo(String str) {
        this.repeatInfo = str;
    }

    public void setSpecialDay(boolean z) {
        this.isSpecialDay = z;
    }

    public void setStartDateTime(Date date) {
        this.startDate = date;
    }

    public void setStartIndex(int i2) {
        this.startIndex = i2;
    }

    public void setStartLocalDateTime(LocalDateTime localDateTime) {
        this.startLocalDateTime = localDateTime;
    }

    public void setString(String str) {
        this.text = str;
    }

    public void setType(BasicEntityExtractor.EntityType entityType2) {
        this.entityType = entityType2;
    }

    public void setUnitSymbol(String str) {
        this.unitSymbol = str;
    }

    public void setUnitValue(String str) {
        this.unitValue = str;
    }

    public void setUnresolvedEndDateTimeUnit(EnumSet<BasicEntityExtractor.DateTimeUnit> enumSet) {
        this.unresolvedEndDateTimeUnit = enumSet;
    }

    public void setUnresolvedStartDateTimeUnit(EnumSet<BasicEntityExtractor.DateTimeUnit> enumSet) {
        this.unresolvedStartDateTimeUnit = enumSet;
    }
}
