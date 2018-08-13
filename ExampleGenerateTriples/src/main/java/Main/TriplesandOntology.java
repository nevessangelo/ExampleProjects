/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;

/**
 *
 * @author angelo
 */
public class TriplesandOntology {
    
    public static void main(String[] args) {
          final String NS = "http://example.org/";
          System.out.println( "=== content of ontology on disk ===" );
          final OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
          final OntClass Region = model.createClass(NS+"Region");
          
          System.out.println("=== new content to append ===");
          final OntModel update = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
          final Individual newHampshire = update.createIndividual(NS+"NewHampshire", Region);
          newHampshire.addLabel("New Hampshire", "en");
          update.write(System.out, "N-Triples");
    }
    
}
