package org.norkamal.reporting.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.time.Month;
import java.util.Currency;

/**
 * The InstructionTest class implements the unit tests for Instruction API.
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public class InstructionTest {

    private Instruction firstInstruction, secondInstruction, thirdInstruction;
    private LocalDate expectedFirstInstEffectiveSettlementDate, expectedSecondInstEffectiveSettlementDate, expectedThirdInstEffectiveSettlementDate;


    @Before
    public void setUp() {
    }

    @Test
    public void Should_Return_EffectiveSettlementDate_When_DealingWithSundayToThursdayTradingCurrency() throws Exception {

        expectedFirstInstEffectiveSettlementDate = LocalDate.of(2016, Month.JUNE, 19);
        expectedSecondInstEffectiveSettlementDate = LocalDate.of(2016, Month.AUGUST, 21);
        expectedThirdInstEffectiveSettlementDate = LocalDate.of(2016, Month.SEPTEMBER, 15);

        firstInstruction = getInstruction(Currency.getInstance("AED"), LocalDate.of(2016, Month.JUNE, 17), 45);
        secondInstruction = getInstruction(Currency.getInstance("SAR"), LocalDate.of(2016, Month.AUGUST, 20), 330);
        thirdInstruction = getInstruction(Currency.getInstance("AED"), LocalDate.of(2016, Month.SEPTEMBER, 15), 220);

        Assert.assertEquals(expectedFirstInstEffectiveSettlementDate, firstInstruction.getEffectiveSettlementDate());
        Assert.assertEquals(expectedSecondInstEffectiveSettlementDate, secondInstruction.getEffectiveSettlementDate());
        Assert.assertEquals(expectedThirdInstEffectiveSettlementDate, thirdInstruction.getEffectiveSettlementDate());
    }

    @Test
    public void Should_Return_EffectiveSettlementDate_When_DealingWithMondayToFridayTradingCurrency() throws Exception {

        expectedFirstInstEffectiveSettlementDate = LocalDate.of(2016, Month.JUNE, 17);
        expectedSecondInstEffectiveSettlementDate = LocalDate.of(2016, Month.OCTOBER, 14);
        expectedThirdInstEffectiveSettlementDate = LocalDate.of(2016, Month.NOVEMBER, 28);

        firstInstruction = getInstruction(Currency.getInstance("USD"), LocalDate.of(2016, Month.JUNE, 17), 200);
        secondInstruction = getInstruction(Currency.getInstance("GBP"), LocalDate.of(2016, Month.OCTOBER, 14), 300);
        thirdInstruction = getInstruction(Currency.getInstance("EUR"), LocalDate.of(2016, Month.NOVEMBER, 26), 250);

        Assert.assertEquals(expectedFirstInstEffectiveSettlementDate, firstInstruction.getEffectiveSettlementDate());
        Assert.assertEquals(expectedSecondInstEffectiveSettlementDate, secondInstruction.getEffectiveSettlementDate());
        Assert.assertEquals(expectedThirdInstEffectiveSettlementDate, thirdInstruction.getEffectiveSettlementDate());
    }

    @Test(expected = IllegalStateException.class)
    public void Should_ThrowIllegalStateException_When_AttemptsToBuildInstructionWithInvalidDate() throws Exception {

        firstInstruction = getInstruction(Currency.getInstance("USD"), null, 110);

        Assert.assertEquals(expectedFirstInstEffectiveSettlementDate, firstInstruction.getEffectiveSettlementDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowIllegalArgumentException_When_AttemptsToBuildInstructionWithInvalidUnits() throws Exception {

        firstInstruction = getInstruction(Currency.getInstance("USD"), LocalDate.of(2016, Month.JULY, 21), -30);

        Assert.assertEquals(expectedFirstInstEffectiveSettlementDate, firstInstruction.getEffectiveSettlementDate());
    }

    @After
    public void tearDown() {
    }

    private Instruction getInstruction(Currency currency, LocalDate settlementDate, int units) throws Exception {

        MathContext MATH_CTX = new MathContext(10);

        Instruction instruction =
                new Instruction.InstructionBuilder()
                        .withEntity("foo")
                        .withOperation(Operation.BUY)
                        .withAgreeFx(new BigDecimal(0.50, MATH_CTX).setScale(2, BigDecimal.ROUND_HALF_EVEN))
                        .withCurrency(currency)
                        .withInstructionDate(LocalDate.of(2016, Month.JANUARY, 1))
                        .withSettlementDate(settlementDate)
                        .withUnits(units)
                        .withPricePerUnits(
                                new BigDecimal(100.25, MATH_CTX).setScale(2, BigDecimal.ROUND_HALF_EVEN))
                        .build();

        return instruction;
    }
}