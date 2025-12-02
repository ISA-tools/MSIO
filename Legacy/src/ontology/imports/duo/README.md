The [MSIO](https://github.com/ISA-tools/MSIO/) imports a module from the Data Use Ontology ([DUO](https://github.com/EBISPOT/DUO)) to provide 
support for annotating datasets with any restrictions about their usage. 
 
To build the module, we include the following classes:

- `http://purl.obolibrary.org/obo/DUO_0000001` #consent code
- `http://purl.obolibrary.org/obo/DUO_0000017` #data use requirement

and their descendants, and build an ontology module relying on the [SyntacticLocalityModuleExtractor or SLME](http://owlcs.github.io/owlapi/apidocs_4/uk/ac/manchester/cs/owlapi/modularity/SyntacticLocalityModuleExtractor.html) 
of the [OWLAPI](http://owlcs.github.io/owlapi/) via the [ROBOT](https://github.com/ontodev/robot) tool. 


We first execute a SPARQL query to obtain the descendants of the two terms:

```robot query -I "http://purl.obolibrary.org/obo/duo.owl" -query descendants.sparql duo-terms-file.txt```

and they

```robot extract --method BOT -I "http://purl.obolibrary.org/obo/duo.owl" --term-file duo-terms-file.txt --output MSIO_DUO_import.owl```