package sparql;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.InfModel;

public class QueryExecutor {

    private static final String PREFIX = "PREFIX : <http://www.semanticweb.org/lunixiao/ontologies/2025/2/untitled-ontology-31#>\n";

    public static void runTriggerScenarioQuery(InfModel infModel) {
        String queryString = PREFIX +
            "SELECT ?event ?scenario WHERE { ?event :triggersScenario ?scenario . }";

        Query query = QueryFactory.create(queryString);
        try (QueryExecution qe = QueryExecutionFactory.create(query, infModel)) {
            ResultSet results = qe.execSelect();
            System.out.println("\n📌 Inferred Event → Scenario Relations:");
            if (!results.hasNext()) {
                System.out.println("⚠️ No inferred triggerScenario found.");
            }
            while (results.hasNext()) {
                QuerySolution sol = results.nextSolution();
                String event = sol.get("event").asResource().getLocalName();
                String scenario = sol.get("scenario").asResource().getLocalName();
                System.out.println("Event: " + event + " → Scenario: " + scenario);
            }
        }
    }
}
