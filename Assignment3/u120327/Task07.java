package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * 
 * @author u120327
 */
public class Task07 {
	public static String ns = "http://somewhere#";

	public static void main(String args[]) {
		String filename = "example6.rdf";

		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);

		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);

		if (in == null)
			throw new IllegalArgumentException("File: " + filename + " not found");

		// Read the RDF/XML file
		model.read(in, null);

		// ** TASK 7.1: List all individuals of "Person" **
		System.out.println("First of all I will list all individuals of Person:");
		// Para listar a todos estos individuals lo que hare sera crear un
		// interador a partir de la lista generada del modelo en la cual se
		// encuentren las de tipo "Person". Recorrere la lista creada
		// imprimiendo su Local Name
		ExtendedIterator<Individual> eIterInd = model.listIndividuals(model.getOntClass(ns + "Person"));
		while (eIterInd.hasNext()) {
			Individual persona = eIterInd.next();
			System.out.println("\t" + persona.getLocalName().toString() + " is a person.");
		}

		// ** TASK 7.2: List all subclasses of "Person" **
		System.out.println("\nNow I will list every subclass of Person: ");
		// Para listar las subclases de "Person" simplemente recorrere la lista
		// de subclases de la ontologia "Person"
		ExtendedIterator<OntClass> eIterPers = model.getOntClass(ns + "Person").listSubClasses();
		while (eIterPers.hasNext()) {
			OntClass clase = eIterPers.next();
			System.out.println("\t" + clase.getLocalName().toString() + " is a subclass of Person.");
		}

		// ** TASK 7.3: Make the necessary changes to get as well indirect
		// instances and subclasses. TIP: you need some inference... **
		System.out.println("\nNow I'd try to show every indirect instance/subclass");
		

	}
}
