import org.semanticweb.owlapi.model.IRI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/*
 * Parsing OntoFox input file
 *
 * OntoFox tutorial: http://ontofox.hegroup.org/tutorial/index.php#toc5
 *
 *
 *
 */
public class OntoFoxInputParser {

    private String EMPTY_LINE = "";
    private String URI_LINE = "[URI of the OWL(RDF/XML) output file]";
    private String uri = null;
    private String SOURCE_ONTOLOGY_LINE = "[Source ontology]";
    private String sourceOntology = null;
    private String LOWER_URIs_LINE = "[Low level source term URIs]";
    private Set<IRI> lowerIRIs = null;
    private String UPPER_URIs_LINE = "[Top level source term URIs and target direct superclass URIs]";
    private Set<IRI> upperIRIs = null;
    private String SOURCE_TERM_RETRIEVAL_LINE = "[Source term retrieval setting]";
    private String sourceRetrievalSetting;
    private String BRANCH_LINE = "[Branch extractions from source term URIs and target direct superclass URIs]";

    //[Source annotation URIs]

    public OntoFoxInputParser(){
        lowerIRIs = new HashSet<>();
    }

    public boolean parse(String filenamePath) throws IOException{

        System.out.println(filenamePath);
        try (BufferedReader br = new BufferedReader(new FileReader(filenamePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(EMPTY_LINE))
                    continue;
                if (line.equals(URI_LINE)){
                    uri = br.readLine();
                    continue;
                }
                if (line.equals(SOURCE_ONTOLOGY_LINE)) {
                    sourceOntology = br.readLine();
                    continue;
                }
                if (line.equals(LOWER_URIs_LINE)){
                    while(!(line = br.readLine()).equals(EMPTY_LINE)){
                        lowerIRIs.add(IRI.create(line));
                    }
                }
            }
        }

        return true;
    }

    public String getURI() {
        return uri;
    }

    public String getSourceOntology(){
        return sourceOntology;
    }

    public Set<IRI> getLowerIRIs(){
        return lowerIRIs;
    }

}