OBO=http://purl.obolibrary.org/obo
ONT=MSIO
BASE=$(OBO)/$(ONT)
SRC=../../$(ONT)-edit.owl
RELEASEDIR=./releases
IMPORTSDIR=./imports
ROBOT-PLUS=../../bin/robot-plus
GRADLE=gradle

all: $(ONT).owl

test:all

$(ONT).owl: $(SRC)
	$(ROBOT-PLUS) reason -i $< -r ELK relax reduce -r ELK annotate -V $(BASE)/releases/`date +%Y-%m-%d`/$(ONT).owl -o $@

prepare_release:
	$(GRADLE) clean
	$(GRADLE) jar
	cp ./build/libs/robot-plus.jar ./bin/
	export PATH=$(PATH):$(PWD)/bin

	$(ROBOT-PLUS) merge  -I "http://purl.obolibrary.org/obo/duo.owl" extract --method MIREOT \
	          --upper-term "owl:Thing" --lower-term "obo:DUO_0000001" --lower-term "obo:DUO_0000017" \
	          --branch-from-term "obo:DUO_0000001" --branch-from-term "obo:DUO_0000017" \
	          --output $(IMPORTSDIR)/duo_import.owl


duo_import:
	cd ./imports/duo/; pwd; ../../$(ROBOT-PLUS) query -I "http://purl.obolibrary.org/obo/duo.owl" -query descendants.sparql duo-terms-file.txt; ../../$(ROBOT-PLUS) extract --method BOT -I "http://purl.obolibrary.org/obo/duo.owl" --term-file duo-terms-file.txt --output ../MSIO_DUO_import.owl
