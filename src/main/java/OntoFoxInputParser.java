import org.semanticweb.owlapi.model.IRI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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

    private final String URI_LINE = "[URI of the OWL(RDF/XML) output file]";
    private String uri = null;
    private final String SOURCE_ONTOLOGY_LINE = "[Source ontology]";
    private String sourceOntology = null;
    private final String LOWER_URIs_LINE = "[Low level source term URIs]";
    private Set<IRI> lowerIRIs = null;
    private final String UPPER_URIs_LINE = "[Top level source term URIs and target direct superclass URIs]";
    private Set<IRI> upperIRIs = null;
    private final String SOURCE_TERM_RETRIEVAL_LINE = "[Source term retrieval setting]";
    private String sourceRetrievalSetting;
    private final String BRANCH_LINE = "[Branch extractions from source term URIs and target direct superclass URIs]";
    private final String SOURCE_ANN_URIs_LINE = "[Source annotation URIs]";
    private Set<IRI> sourceAnnotationURIs = null;
    private String sourceAnnotationSetting = null;
    private final String SOURCE_ANN_URIs_EXCLUDE_LINE = "[Source annotation URIs to be excluded]";
    private String filenamePath = null;

    //[Source annotation URIs]

    public OntoFoxInputParser(String fnp){
        lowerIRIs = new HashSet<>();
        upperIRIs = new HashSet<>();
        sourceAnnotationURIs = new HashSet<>();
        filenamePath = fnp;
    }

    public boolean parse() throws IOException{

        try (BufferedReader br = new BufferedReader(new FileReader(filenamePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                if (line.equals(URI_LINE)){
                    while((line = br.readLine()).trim().isEmpty() || line.equals(LOWER_URIs_LINE)){
                        continue;
                    }
                    uri = line;
                    continue;
                }
                if (line.equals(SOURCE_ONTOLOGY_LINE)) {
                    while((line = br.readLine()).trim().isEmpty() || line.equals(LOWER_URIs_LINE)){
                        continue;
                    }
                    sourceOntology = line;
                    continue;
                }
                if (line.equals(LOWER_URIs_LINE)){
                    while(!(line = br.readLine()).equals(UPPER_URIs_LINE)){
                        if (line.trim().isEmpty())
                            continue;
                        lowerIRIs.add(IRI.create(line));
                    }
                }
                if (line.equals(UPPER_URIs_LINE)){
                    //TODO add support for subClassOf
                    while(!(line = br.readLine()).equals(SOURCE_TERM_RETRIEVAL_LINE)){
                        if (line.trim().isEmpty())
                            continue;
                        upperIRIs.add(IRI.create(line));
                    }
                }
                if (line.equals(SOURCE_TERM_RETRIEVAL_LINE)){
                    while((line = br.readLine()).trim().isEmpty() || line.equals(BRANCH_LINE)){
                        continue;
                    }
                    sourceRetrievalSetting = line;
                }
                if (line.equals(BRANCH_LINE)){
                    while((line = br.readLine()).trim().isEmpty() || line.equals(SOURCE_ANN_URIs_LINE)){
                        continue;
                    }

                }
                if (line.equals(SOURCE_ANN_URIs_LINE)){
                    while(!(line = br.readLine()).equals(SOURCE_ANN_URIs_EXCLUDE_LINE)){
                        if (line.trim().isEmpty())
                            continue;
                        try {
                            URL url = new URL(line);
                            sourceAnnotationURIs.add(IRI.create(line));
                        }catch(MalformedURLException ex){
                            sourceAnnotationSetting = line;
                        }

                    }

                }
                if (line.equals(SOURCE_ANN_URIs_EXCLUDE_LINE)){

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

    public Set<IRI> getUpperIRIs(){
        return upperIRIs;
    }

    public String getSourceRetrievalSetting(){
        return sourceRetrievalSetting;
    }

    public Set<IRI> getSourceAnnotationURIs(){
        return sourceAnnotationURIs;
    }

    public String getSourceAnnotationSetting(){
        return sourceAnnotationSetting;
    }

}