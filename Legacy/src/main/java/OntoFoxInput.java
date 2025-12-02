import org.semanticweb.owlapi.model.IRI;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by agbeltran
 *
 * An object with the information of the OntoFox input.
 *
 */
public class OntoFoxInput {

    public OntoFoxInput(String fnp){
        lowerIRIs = new HashSet<>();
        upperIRIs = new HashSet<>();
        sourceAnnotationURIs = new HashSet<>();
        filenamePath = fnp;
    }

    private String uri = null;
    private String sourceOntology = null;
    private Set<IRI> lowerIRIs = null;
    private Set<IRI> upperIRIs = null;
    private String sourceRetrievalSetting;
    private Set<IRI> sourceAnnotationURIs = null;
    private String sourceAnnotationSetting = null;
    private String filenamePath = null;


    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setSourceOntology(String sourceOntology) {
        this.sourceOntology = sourceOntology;
    }

    public void setLowerIRIs(Set<IRI> lowerIRIs) {
        this.lowerIRIs = lowerIRIs;
    }

    public void addLowerIRI(IRI lowerIRI) { this.lowerIRIs.add(lowerIRI); }

    public void setUpperIRIs(Set<IRI> upperIRIs) {
        this.upperIRIs = upperIRIs;
    }

    public void addUpperIRI(IRI lowerIRI) { this.upperIRIs.add(lowerIRI); }

    public void setSourceRetrievalSetting(String sourceRetrievalSetting) {
        this.sourceRetrievalSetting = sourceRetrievalSetting;
    }

    public void setSourceAnnotationURIs(Set<IRI> sourceAnnotationURIs) {
        this.sourceAnnotationURIs = sourceAnnotationURIs;
    }

    public void addSourceAnnotationURI(IRI sourceAnnotationURI){
        this.sourceAnnotationURIs.add(sourceAnnotationURI);
    }

    public void setSourceAnnotationSetting(String sourceAnnotationSetting) {
        this.sourceAnnotationSetting = sourceAnnotationSetting;
    }

    public void setFilenamePath(String filenamePath) {
        this.filenamePath = filenamePath;
    }

    public String getURI() { return this.uri; }

    public String getSourceOntology() {
        return sourceOntology;
    }

    public Set<IRI> getLowerIRIs() {
        return lowerIRIs;
    }

    public Set<IRI> getUpperIRIs() {
        return upperIRIs;
    }

    public String getSourceRetrievalSetting() {
        return sourceRetrievalSetting;
    }

    public Set<IRI> getSourceAnnotationURIs() {
        return sourceAnnotationURIs;
    }

    public String getSourceAnnotationSetting() {
        return sourceAnnotationSetting;
    }

    public String getFilenamePath() {
        return filenamePath;
    }

}
