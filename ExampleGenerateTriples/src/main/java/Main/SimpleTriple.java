/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.VCARD;

/**
 *
 * @author angelo
 */
public class SimpleTriple {
    
    public static void main(String[] args) {
        
        String personURI = "http://somewhere/JohnSmith";
        String fullName = "John Smith";
        
        Model model = ModelFactory.createDefaultModel();
        Resource johnSmith = model.createResource(personURI);
        
        johnSmith.addProperty(VCARD.FN, fullName);
        
        Triples.Triples.PrintTriples(model);
        




        
    }
    
}
