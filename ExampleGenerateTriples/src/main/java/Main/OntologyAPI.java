/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.InputStream;
import java.util.Iterator;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import static org.apache.jena.ontology.OntModelSpec.OWL_MEM_MICRO_RULE_INF;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;

/**
 *
 * @author angelo
 */
public class OntologyAPI {
    
    public static void main(String[] args) {
        //read ontology
        String path = "/home/angelo/Codigo/ExampleProjects/ExampleGenerateTriples/ontology/eswc-2006-09-21.rdf";
        OntDocumentManager mgr = new OntDocumentManager();
        OntModelSpec s = new OntModelSpec(OntModelSpec.RDFS_MEM);
        s.setDocumentManager(mgr);
        OntModel base = ModelFactory.createOntologyModel(s, null);
        InputStream in = FileManager.get().open(path);
        base.read(in, "RDF/XML");
        base.write(System.out);
        
        OntModel inf = ModelFactory.createOntologyModel(OWL_MEM_MICRO_RULE_INF, base);

        String SOURCE = "http://www.eswc2006.org/technologies/ontology";
        String NS = SOURCE + "#";
        
        
        //listsubclasses
        
        OntClass artefact = base.getOntClass(NS + "Artefact");
        for (Iterator<OntClass> i = artefact.listSubClasses(); i.hasNext(); ) {
            try{
                OntClass c = i.next();
                System.out.println( c.getURI() );
            }catch(Exception e ){
                continue;
            }
            
        }
        
//        OntClass paper = base.getOntClass(NS + "Paper");
//        System.out.println(paper);
//        
//        Individual p1 = base.createIndividual(NS + "paper1", paper);
//        
//        p1 = inf.getIndividual( NS + "paper1" );


    



        







    }
    
}
