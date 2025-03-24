package main;

import org.apache.jena.rdf.model.InfModel;
import reasoner.ReasoningEngine;
import sparql.QueryExecutor;

public class Start {
    public static void main(String[] args) {
        System.out.println("=== Starting ODRA-Based Scenario Reasoner ===");

        ReasoningEngine engine = new ReasoningEngine();
        InfModel infModel = engine.executeReasoning();  // run reasoning

        System.out.println("=== Reasoning Completed ===");

        QueryExecutor.runTriggerScenarioQuery(infModel);  // run SPARQL
    }
}
