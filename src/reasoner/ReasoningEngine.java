package reasoner;

import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.*;
import org.apache.jena.reasoner.rulesys.*;
import org.apache.jena.util.FileManager;

import java.io.InputStream;

public class ReasoningEngine {

    public InfModel executeReasoning() {
        // Load ontology model
        Model model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open("data/AutonomousApproachLandingScenariosCV.rdf");
        if (in == null) {
            throw new IllegalArgumentException("Ontology file not found.");
        }
        model.read(in, null);

        // ODRA-style rule: if event is LowVisibility, trigger GoAround
        String rules =
            "[r1: (?event rdf:type <http://www.semanticweb.org/lunixiao/ontologies/2025/2/untitled-ontology-31#LowVisibility>) " +
            "-> (?event <http://www.semanticweb.org/lunixiao/ontologies/2025/2/untitled-ontology-31#triggersScenario> " +
            "<http://www.semanticweb.org/lunixiao/ontologies/2025/2/untitled-ontology-31#GoAround>)]";

        Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rules));
        InfModel infModel = ModelFactory.createInfModel(reasoner, model);

        // Optional: show inferred triples
        StmtIterator iter = infModel.listStatements(null, null, (RDFNode) null);
        while (iter.hasNext()) {
            System.out.println(iter.nextStatement());
        }

        return infModel;
    }
}
