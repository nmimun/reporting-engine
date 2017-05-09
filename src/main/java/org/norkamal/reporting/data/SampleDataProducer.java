package org.norkamal.reporting.data;

import org.norkamal.reporting.model.Instruction;
import org.norkamal.reporting.model.Operation;
import org.norkamal.reporting.engine.ReportingEngine;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * The SampleDataProducer is a class in charge of producing sample data that can be used for demo and/or testing proposes.
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public class SampleDataProducer {

    /**
     * Creates a lists of foreign exchange instructions
     *
     * @return a list if instructions with the sample data
     */
    public static List<Instruction> getListWithInstructions() throws Exception {

        Instruction firstInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("foo")
                        .withOperation(Operation.BUY)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(0.50)))
                        .withCurrency(Currency.getInstance("SGD"))
                        .withInstructionDate(LocalDate.of(2016, Month.JANUARY, 1))
                        .withSettlementDate(LocalDate.of(2016, Month.JANUARY, 2))
                        .withUnits(200)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(100.25)))
                        .build();

        Instruction secondInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("bar")
                        .withOperation(Operation.SELL)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(0.22)))
                        .withCurrency(Currency.getInstance("AED"))
                        .withInstructionDate(LocalDate.of(2015, Month.FEBRUARY, 5))
                        .withSettlementDate(LocalDate.of(2016, Month.FEBRUARY, 5))
                        .withUnits(450)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(150.5)))
                        .build();

        Instruction thirdInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("baz")
                        .withOperation(Operation.SELL)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(0.22)))
                        .withCurrency(Currency.getInstance("AED"))
                        .withInstructionDate(LocalDate.of(2016, Month.MAY, 5))
                        .withSettlementDate(LocalDate.of(2016, Month.MAY, 17))
                        .withUnits(380)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(150.5)))
                        .build();

        Instruction forthInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("qux")
                        .withOperation(Operation.SELL)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(0.27)))
                        .withCurrency(Currency.getInstance("SAR"))
                        .withInstructionDate(LocalDate.of(2016, Month.JUNE, 5))
                        .withSettlementDate(LocalDate.of(2016, Month.JUNE, 17))
                        .withUnits(451)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(250.5)))
                        .build();

        Instruction fifthInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("quux")
                        .withOperation(Operation.BUY)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(3.26)))
                        .withCurrency(Currency.getInstance("AED"))
                        .withInstructionDate(LocalDate.of(2016, Month.JUNE, 18))
                        .withSettlementDate(LocalDate.of(2016, Month.JUNE, 19))
                        .withUnits(551)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(160.5)))
                        .build();

        Instruction sixthInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("corge")
                        .withOperation(Operation.BUY)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(3.26)))
                        .withCurrency(Currency.getInstance("SAR"))
                        .withInstructionDate(LocalDate.of(2016, Month.JULY, 11))
                        .withSettlementDate(LocalDate.of(2016, Month.JULY, 11))
                        .withUnits(1000)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(50.5)))
                        .build();

        Instruction seventhInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("grault")
                        .withOperation(Operation.SELL)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(1.22)))
                        .withCurrency(Currency.getInstance("GBP"))
                        .withInstructionDate(LocalDate.of(2016, Month.AUGUST, 22))
                        .withSettlementDate(LocalDate.of(2016, Month.AUGUST, 24))
                        .withUnits(450)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(22)))
                        .build();

        Instruction eighthInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("garply")
                        .withOperation(Operation.BUY)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(0.77)))
                        .withCurrency(Currency.getInstance("GBP"))
                        .withInstructionDate(LocalDate.of(2016, Month.AUGUST, 11))
                        .withSettlementDate(LocalDate.of(2016, Month.AUGUST, 11))
                        .withUnits(220)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(330.2)))
                        .build();

        Instruction nightInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("waldo")
                        .withOperation(Operation.SELL)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(0.22)))
                        .withCurrency(Currency.getInstance("EUR"))
                        .withInstructionDate(LocalDate.of(2016, Month.SEPTEMBER, 22))
                        .withSettlementDate(LocalDate.of(2016, Month.OCTOBER, 24))
                        .withUnits(700)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(100.5)))
                        .build();

        Instruction tenthInstruction =
                new Instruction.InstructionBuilder()
                        .withEntity("waldo")
                        .withOperation(Operation.SELL)
                        .withAgreeFx(setScaleAndRound(new BigDecimal(0.22)))
                        .withCurrency(Currency.getInstance("EUR"))
                        .withInstructionDate(LocalDate.of(2016, Month.NOVEMBER, 22))
                        .withSettlementDate(LocalDate.of(2016, Month.NOVEMBER, 24))
                        .withUnits(450)
                        .withPricePerUnits(setScaleAndRound(new BigDecimal(90.5)))
                        .build();

        List<Instruction> instructions = new ArrayList<>();

        instructions.add(firstInstruction);
        instructions.add(secondInstruction);
        instructions.add(thirdInstruction);
        instructions.add(forthInstruction);
        instructions.add(fifthInstruction);
        instructions.add(sixthInstruction);
        instructions.add(seventhInstruction);
        instructions.add(eighthInstruction);
        instructions.add(nightInstruction);
        instructions.add(tenthInstruction);

        return instructions;
    }

    private static BigDecimal setScaleAndRound(BigDecimal value) {

        if (value != null)
            return value.setScale(ReportingEngine.OPERATION_SCALE, BigDecimal.ROUND_HALF_EVEN);

        return value;
    }

}
