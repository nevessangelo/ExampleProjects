/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;


/**
 *
 * @author angelo
 */
public class SitopExample {

    public static void main(String[] args) {
        //get author from json
        JSONParser parser = new JSONParser();
        Object obj;
        try {
            obj = parser.parse(new FileReader("/home/angelo/Projeto/FPSO_CIDADE_DE_ANCHIETA_508_11_06_2014_1.json"));
            JSONObject jsonObject = (JSONObject) obj;
            String USUA_CD_USERNAME = (String) jsonObject.get("USUA_CD_USERNAME");
            System.out.println(USUA_CD_USERNAME);

            //read ontology from disk
            String path = "/home/angelo/Projeto/Test_ontology2.owl";
            OntDocumentManager mgr = new OntDocumentManager();
            OntModelSpec s = new OntModelSpec(OntModelSpec.RDFS_MEM);
            s.setDocumentManager(mgr);
            OntModel m = ModelFactory.createOntologyModel(s, null);
            InputStream in = FileManager.get().open(path);
            m.read(in, "RDF/XML");
            m.write(System.out);
            
            String SitopNS = "http://www.sitop.com/ontologies/sitop.owl#";
          
            //get ontoClass
           
            OntClass sitop_class = m.getOntClass(SitopNS + "SITOPReport");
            OntClass author_class = m.getOntClass(SitopNS + "Author");
            
            int id = 1;
            String reportURIID = sitop_class.toString() + "/"+id;
            String authorURIID = author_class.toString() + "/"+ id;
            
            System.out.println(sitop_class);
            System.out.println(author_class);
            
            //create individuals
            System.out.println("---------individuals------------");
            OntModel inf = ModelFactory.createOntologyModel(s, null);
            Individual reportIndividual = inf.createIndividual(reportURIID, sitop_class);
            Individual authorIndividual = inf.createIndividual(authorURIID, author_class);
            
            //get propretys
            OntProperty hasAuthor = m.getObjectProperty(SitopNS + "hasAuthor");
            DatatypeProperty hasCode = m.getDatatypeProperty(SitopNS + "hasCode");
            
            reportIndividual.addProperty(hasAuthor, authorIndividual);
            Node codeNode = NodeFactory.createLiteralByValue(USUA_CD_USERNAME, XSDDatatype.XSDstring);
            RDFNode RDFcodeNode = inf.asRDFNode(codeNode);
            authorIndividual.addProperty(hasCode, RDFcodeNode);
            inf.write(System.out, "N-Triples");
            
            //export to .rdf
            FileOutputStream out = new FileOutputStream("/home/angelo/Test.rdf",false);
            inf.write(out, "RDF/XML");
            inf.close();
            
            
            


        } catch (FileNotFoundException ex) {
            Logger.getLogger(SitopExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SitopExample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(SitopExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
