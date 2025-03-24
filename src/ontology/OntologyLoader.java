package ontology;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.InputStream;

public class OntologyLoader {
    public static Model loadModel(String filename) {
        Model model = ModelFactory.createDefaultModel();
        InputStream in = OntologyLoader.class.getResourceAsStream("/data/" + filename);
        if (in == null) {
            System.err.println("ERROR: Ontology file not found: " + filename);
            return model;
        }
        model.read(in, null);
        return model;
    }
}
