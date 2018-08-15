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
        //base.write(System.out);
        
        OntModel inf = ModelFactory.createOntologyModel(OWL_MEM_MICRO_RULE_INF, base);

        String SOURCE = "http://www.eswc2006.org/technologies/ontology";
        String NS = SOURCE + "#";
        
        OntClass paper = base.getOntClass( NS + "Paper" );
        Individual p1 = base.createIndividual(NS + "paper1", paper);
        
        p1 = inf.getIndividual(NS + "paper1");
        
        //inf.write(System.out);
        
        
        OntClass ontClass = base.getOntClass(NS+"Artefact");
        Individual p2 = base.createIndividual("Http://myselet/"+"artefact1", ontClass);
        //<Http://myselet/artefact1> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.eswc2006.org/technologies/ontology#Artefact> .
        //base.write(System.out, "N-Triples");
        
        //create indivuduals
        OntModel individuals = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        individuals.createResource(p2);
        individuals.createResource(p2);
        individuals.createResource(NS+"artefact2", ontClass);
        individuals.createResource(NS+"artefact2", ontClass);
        System.out.println("---------individual------------");
        individuals.write(System.out, "N-Triples");




       


        









    }
    
}
