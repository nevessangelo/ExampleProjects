/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

/**
 *
 * @author angelo
 */
public class TriplesBlankNode {
    
    public static void main(String[] args) {
        String personURI = "http://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;
        
        Model model = ModelFactory.createDefaultModel();
        
        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N,
                        model.createResource()
                            .addProperty(VCARD.Given, givenName)
                            .addProperty(VCARD.Family, familyName));
                            
        
        Triples.Triples.PrintTriples(model);                    

        



        
    }
    
}
