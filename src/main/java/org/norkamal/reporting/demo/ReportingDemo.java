package org.norkamal.reporting.demo;

import org.norkamal.reporting.data.SampleDataProducer;
import org.norkamal.reporting.model.Instruction;
import org.norkamal.reporting.engine.ReportingEngine;

import java.util.List;

/**
 * The ReportingDemo is a class that allows to illustrate the engine functionality in action.
 * The result will be a report printed in the system standard output.
 *
 * @author Norkamal Mimun
 * @version 1.0, May 2017
 */
public class ReportingDemo {

    public static void main(String[] args) {

        try {
            /* Prepare sample data */
            List<Instruction> instructions = SampleDataProducer.getListWithInstructions();

            /* Generate and print reports */
            ReportingEngine.printGlobalForexReport(instructions);
        } catch (Exception e) {
            System.out.println("Unable to generate the report due to exception: " + e.getStackTrace());
        }

    }
}
