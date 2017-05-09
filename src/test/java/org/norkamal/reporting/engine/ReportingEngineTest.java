package org.norkamal.reporting.engine;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.norkamal.reporting.data.SampleDataProducer;
import org.norkamal.reporting.model.Instruction;
import org.norkamal.reporting.model.Operation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


/**
 * The ReportingEngineTest class implements the unit tests for ReportingEngine API
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public class ReportingEngineTest {

    private ByteArrayOutputStream outputStream;


    @Before
    public void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void Should_ReturnTheGlobalForexReport_Given_ListOfInstructions() throws Exception {

        ReportingEngine.printGlobalForexReport(SampleDataProducer.getListWithInstructions());
        Assert.assertEquals(getExpectedTransactionPerDayReport(), outputStream.toString());
    }

    @Test
    public void Should_ReturnListOfIncomingTransactionsPerDay_Given_ListOfInstructions() throws Exception {

        ReportingEngine.printAmountSettledPerDayReport(SampleDataProducer.getListWithInstructions(), Operation.SELL);
        Assert.assertEquals(getExpectedIncomingTransactionsPerDayReport(), outputStream.toString());
    }

    @Test
    public void Should_ReturnListOfOutgoingTransactionsPerDay_Given_ListOfInstructions() throws Exception {

        ReportingEngine.printAmountSettledPerDayReport(SampleDataProducer.getListWithInstructions(), Operation.BUY);
        Assert.assertEquals(getExpectedOutgoingTransactionsPerDayReport(), outputStream.toString());
    }

    @Test
    public void Should_ReturnIncomingRankingOfEntities_Given_ListOfTransactions() throws Exception {

        ReportingEngine.printEntitiesRankingReport(SampleDataProducer.getListWithInstructions(), Operation.SELL);
        Assert.assertEquals(getExpectedIncomingRankingOfEntitiesReport(), outputStream.toString());
    }

    @Test
    public void Should_ReturnOutgoingRankingOfEntities_Given_ListOfTransactions() throws Exception {

        ReportingEngine.printEntitiesRankingReport(SampleDataProducer.getListWithInstructions(), Operation.BUY);
        Assert.assertEquals(getExpectedOutgoingRankingOfEntitiesReport(), outputStream.toString());
    }

    @Test
    public void Should_ReturnEmptyReport_Given_NullListOfTransactions() {

        ReportingEngine.printGlobalForexReport(null);
        Assert.assertEquals(getExpectedInvalidInputsReport(), outputStream.toString());
    }

    @Test
    public void Should_ReturnEmptyReport_Given_EmptyListOfTransactions() {

        ReportingEngine.printGlobalForexReport(new ArrayList<Instruction>());
        Assert.assertEquals(getExpectedInvalidInputsReport(), outputStream.toString());
    }


    @After
    public void tearDown() throws IOException {
        outputStream.close();
    }


    private String getExpectedTransactionPerDayReport() {

        return "\nAmount in USD settled per day for incoming instructions:\n" +
                "Day: 2016-02-07, Amount: 14899.50 USD\n" +
                "Day: 2016-05-17, Amount: 12581.80 USD\n" +
                "Day: 2016-06-19, Amount: 30503.38 USD\n" +
                "Day: 2016-08-24, Amount: 12078.00 USD\n" +
                "Day: 2016-10-24, Amount: 15477.00 USD\n" +
                "Day: 2016-11-24, Amount: 8959.50 USD\n" +
                "\n" +
                "Amount in USD settled per day for outgoing instructions:\n" +
                "Day: 2016-01-04, Amount: 10025.00 USD\n" +
                "Day: 2016-06-19, Amount: 288299.73 USD\n" +
                "Day: 2016-07-11, Amount: 164630.00 USD\n" +
                "Day: 2016-08-11, Amount: 55935.88 USD\n" +
                "\n" +
                "Ranking of entities in descending order by amount instructed to incoming instructions:\n" +
                "Entity: qux    , Amount: 30503.38 USD\n" +
                "Entity: waldo  , Amount: 24436.50 USD\n" +
                "Entity: bar    , Amount: 14899.50 USD\n" +
                "Entity: baz    , Amount: 12581.80 USD\n" +
                "Entity: grault , Amount: 12078.00 USD\n" +
                "\n" +
                "Ranking of entities in descending order by amount instructed to outgoing instructions:\n" +
                "Entity: quux   , Amount: 288299.73 USD\n" +
                "Entity: corge  , Amount: 164630.00 USD\n" +
                "Entity: garply , Amount: 55935.88 USD\n" +
                "Entity: foo    , Amount: 10025.00 USD\n";
    }

    private String getExpectedIncomingTransactionsPerDayReport() {

        return "\nAmount in USD settled per day for incoming instructions:\n" +
                "Day: 2016-02-07, Amount: 14899.50 USD\n" +
                "Day: 2016-05-17, Amount: 12581.80 USD\n" +
                "Day: 2016-06-19, Amount: 30503.38 USD\n" +
                "Day: 2016-08-24, Amount: 12078.00 USD\n" +
                "Day: 2016-10-24, Amount: 15477.00 USD\n" +
                "Day: 2016-11-24, Amount: 8959.50 USD\n";
    }


    private String getExpectedOutgoingTransactionsPerDayReport() {

        return "\nAmount in USD settled per day for outgoing instructions:\n" +
                "Day: 2016-01-04, Amount: 10025.00 USD\n" +
                "Day: 2016-06-19, Amount: 288299.73 USD\n" +
                "Day: 2016-07-11, Amount: 164630.00 USD\n" +
                "Day: 2016-08-11, Amount: 55935.88 USD\n";
    }

    private String getExpectedIncomingRankingOfEntitiesReport() {

        return "\n" +
                "Ranking of entities in descending order by amount instructed to incoming instructions:\n" +
                "Entity: qux    , Amount: 30503.38 USD\n" +
                "Entity: waldo  , Amount: 24436.50 USD\n" +
                "Entity: bar    , Amount: 14899.50 USD\n" +
                "Entity: baz    , Amount: 12581.80 USD\n" +
                "Entity: grault , Amount: 12078.00 USD\n";
    }

    private String getExpectedOutgoingRankingOfEntitiesReport() {
        return "\n" +
                "Ranking of entities in descending order by amount instructed to outgoing instructions:\n" +
                "Entity: quux   , Amount: 288299.73 USD\n" +
                "Entity: corge  , Amount: 164630.00 USD\n" +
                "Entity: garply , Amount: 55935.88 USD\n" +
                "Entity: foo    , Amount: 10025.00 USD\n";
    }

    private String getExpectedInvalidInputsReport() {

        return "\nAmount in USD settled per day for incoming instructions:\n" +
                "\n" +
                "Amount in USD settled per day for outgoing instructions:\n" +
                "\n" +
                "Ranking of entities in descending order by amount instructed to incoming instructions:\n" +
                "\n" +
                "Ranking of entities in descending order by amount instructed to outgoing instructions:\n";
    }

}
