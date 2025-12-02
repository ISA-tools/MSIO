import com.google.common.collect.ImmutableList;
import org.semanticweb.owlapi.model.IRI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
    private final String SOURCE_ONTOLOGY_LINE = "[Source ontology]";
    private final String LOWER_URIs_LINE = "[Low level source term URIs]";
    private final String UPPER_URIs_LINE = "[Top level source term URIs and target direct superclass URIs]";
    private final String SOURCE_TERM_RETRIEVAL_LINE = "[Source term retrieval setting]";
    private final String BRANCH_LINE = "[Branch extractions from source term URIs and target direct superclass URIs]";
    private final String SOURCE_ANN_URIs_LINE = "[Source annotation URIs]";
    private final String SOURCE_ANN_URIs_EXCLUDE_LINE = "[Source annotation URIs to be excluded]";

    private final ImmutableList<String> HEADERS = ImmutableList.of(
            URI_LINE,
            SOURCE_ONTOLOGY_LINE,
            LOWER_URIs_LINE,
            UPPER_URIs_LINE,
            SOURCE_TERM_RETRIEVAL_LINE,
            BRANCH_LINE,
            SOURCE_ANN_URIs_LINE,
            SOURCE_ANN_URIs_EXCLUDE_LINE
            );


    private OntoFoxInput ontoFoxInput = null;

    public OntoFoxInputParser(String fnp){
        ontoFoxInput = new OntoFoxInput(fnp);
    }

    public OntoFoxInput getOntoFoxInput(){
        return ontoFoxInput;
    }

    public boolean parse() throws IOException{

        try (BufferedReader br = new BufferedReader(new FileReader(ontoFoxInput.getFilenamePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#"))
                    continue;
                if (line.equals(URI_LINE)) {
                    while ((line = br.readLine()).trim().isEmpty() || line.startsWith("#") || HEADERS.contains(line)) {
                        continue;
                    }
                    ontoFoxInput.setUri(line);
                    continue;
                }
                if (line.equals(SOURCE_ONTOLOGY_LINE)) {
                    while ((line = br.readLine()).trim().isEmpty() || line.startsWith("#") || HEADERS.contains(line)) {
                        continue;
                    }
                    ontoFoxInput.setSourceOntology(line);
                    continue;
                }
                if (line.equals(LOWER_URIs_LINE)) {
                    while (!(line = br.readLine()).equals(UPPER_URIs_LINE)) {
                        if (line.trim().isEmpty() || line.startsWith("#"))
                            continue;
                        ontoFoxInput.addLowerIRI(IRI.create(line));
                    }
                }
                if (line.equals(UPPER_URIs_LINE)) {
                    //TODO add support for subClassOf
                    while (!(line = br.readLine()).equals(SOURCE_TERM_RETRIEVAL_LINE)) {
                        if (line.trim().isEmpty() || line.startsWith("#"))
                            continue;
                        ontoFoxInput.addUpperIRI(IRI.create(line));
                    }
                }
                if (line.equals(SOURCE_TERM_RETRIEVAL_LINE)) {
                    while ((line = br.readLine()).trim().isEmpty() || line.startsWith("#") || HEADERS.contains(line)) {
                        continue;
                    }
                    ontoFoxInput.setSourceRetrievalSetting(line);
                    continue;
                }
                if (line.equals(BRANCH_LINE)) {
                    while ((line = br.readLine()).trim().isEmpty() || line.startsWith("#") || HEADERS.contains(line)) {
                        continue;
                    }

                }
                if (line.equals(SOURCE_ANN_URIs_LINE)) {
                    while ((line = br.readLine())!= null
                            && (line!=null && !line.equals(SOURCE_ANN_URIs_EXCLUDE_LINE))) {
                        if (line.trim().isEmpty() || line.startsWith("#"))
                            continue;
                        try {
                            URL url = new URL(line);
                            ontoFoxInput.addSourceAnnotationURI(IRI.create(line));
                        } catch (MalformedURLException ex) {
                            ontoFoxInput.setSourceAnnotationSetting(line);
                        }
                    }
                } // line source_ann_uris_line

            } // while each line
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }


}