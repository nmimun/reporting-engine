package org.norkamal.reporting.model;

import org.norkamal.reporting.utils.CurrencyUtil;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Currency;

/**
 * The Instruction class represents a foreign exchange instruction.
 * Implements the builder pattern for object instantiation.
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public class Instruction {

    private String entity;
    private Operation operation;
    private BigDecimal agreedFx;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private int units;
    private BigDecimal pricePerUnit;


    public Instruction(InstructionBuilder builder) {

        this.entity = builder.entity;
        this.operation = builder.operation;
        this.agreedFx = builder.agreedFx;
        this.currency = builder.currency;
        this.instructionDate = builder.instructionDate;
        this.settlementDate = builder.settlementDate;
        this.units = builder.units;
        this.pricePerUnit = builder.pricePerUnit;
    }

    /**
     * @return the effective settlement date
     */
    public LocalDate getEffectiveSettlementDate() {
        LocalDate effectiveSettlementDate = getSettlementDate();

        while (isSettlementDateOnHoliday(effectiveSettlementDate)) {
            effectiveSettlementDate = effectiveSettlementDate.plusDays(1);
        }

        return effectiveSettlementDate;
    }

    /**
     * @param localDate date to check if falls on a holiday
     * @return true if it falls on a holiday, false otherwise
     */
    private boolean isSettlementDateOnHoliday(LocalDate localDate) {

        LocalDate settlementDate = localDate;
        if (CurrencyUtil.isTradingFromSundayToThursday(this.getCurrency())) {
            return (settlementDate.getDayOfWeek() == DayOfWeek.FRIDAY
                    || settlementDate.getDayOfWeek() == DayOfWeek.SATURDAY);

        } else {
            return (settlementDate.getDayOfWeek() == DayOfWeek.SATURDAY
                    || settlementDate.getDayOfWeek() == DayOfWeek.SUNDAY);
        }
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(BigDecimal agreedFx) throws IllegalArgumentException {

        if (agreedFx.compareTo(new BigDecimal(0)) >= 0) {
            this.agreedFx = agreedFx;
        } else {
            throw new IllegalArgumentException("AgreeFx rate cannot be negative");
        }
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getInstrucionDate() {
        return instructionDate;
    }

    public void setInstrucionDate(LocalDate instrucionDate) {
        this.instructionDate = instrucionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) throws IllegalArgumentException {

        if (units > 0) {
            this.units = units;
        } else {
            throw new IllegalArgumentException("Units should be greater than zero");
        }
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) throws IllegalArgumentException {
        if (pricePerUnit.compareTo(new BigDecimal(0)) > 0) {
            this.pricePerUnit = pricePerUnit;
        } else {
            throw new IllegalArgumentException("Price units should be greater than zero");
        }
    }

    /**
     * The InstructionBuilder inner class helps implement the builder pattern for the Instruction class
     * {@link Instruction}
     *
     * @author Norkamal Mimun
     * @version 1.0, May 2017
     */
    public static class InstructionBuilder {

        private String entity;
        private Operation operation;
        private BigDecimal agreedFx;
        private Currency currency;
        private LocalDate instructionDate;
        private LocalDate settlementDate;
        private int units;
        private BigDecimal pricePerUnit;

        public InstructionBuilder withEntity(String entity) {
            this.entity = entity;
            return this;
        }

        public InstructionBuilder withOperation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public InstructionBuilder withAgreeFx(BigDecimal agreedFx) throws IllegalArgumentException {
            if (agreedFx.compareTo(new BigDecimal(0)) >= 0) {
                this.agreedFx = agreedFx;
                return this;
            } else {
                throw new IllegalArgumentException("AgreeFx rate cannot be negative");
            }
        }

        public InstructionBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public InstructionBuilder withInstructionDate(LocalDate instructionDate) {
            this.instructionDate = instructionDate;
            return this;
        }

        public InstructionBuilder withSettlementDate(LocalDate settlementDate) {
            this.settlementDate = settlementDate;
            return this;
        }

        public InstructionBuilder withUnits(int units) throws IllegalArgumentException {
            if (units > 0) {
                this.units = units;
                return this;
            } else {
                throw new IllegalArgumentException("Units should be greater than zero");
            }
        }

        public InstructionBuilder withPricePerUnits(BigDecimal pricePerUnit) throws IllegalArgumentException {
            if (pricePerUnit.compareTo(new BigDecimal(0)) > 0) {
                this.pricePerUnit = pricePerUnit;
                return this;
            } else {
                throw new IllegalArgumentException("Price units should be greater than zero");
            }
        }


        public Instruction build() throws IllegalStateException {

            if (isValid()) {
                return new Instruction(this);
            } else
                throw new IllegalStateException("The object to instantiate is invalid ");
        }

        /**
         * Validator method that checks the state of the instruction about to be built
         *
         * @author Norkamal Mimun
         * @version 1.0, May 2017
         */
        private boolean isValid() {

            boolean validValues = false;
            boolean mandatoryFields = this.settlementDate != null & this.agreedFx != null && this.currency != null && this.pricePerUnit != null;

            if (mandatoryFields)
                validValues = (this.agreedFx.compareTo(new BigDecimal(0)) >= 0) && (this.units > 0) && (this.pricePerUnit.compareTo(new BigDecimal(0)) > 0);

            return (mandatoryFields && validValues);
        }

    }
}
