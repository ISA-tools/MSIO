OBO=http://purl.obolibrary.org/obo
ONT=MSIO
BASE=$(OBO)/$(ONT)
SRC=$(ONT)-edit.owl
RELEASEDIR=./releases
IMPORTSDIR=./imports
ROBOT-PLUS=robot-plus
GRADLE=gradle

all: $(ONT).owl

test:all

prepare_release:
	$(GRADLE) clean
	$(GRADLE) jar
	cp ./build/libs/robot-plus.jar ./bin/
	export PATH=$(PATH):$(PWD)/bin

	$(ROBOT-PLUS) extract --method MIREOT -I "http://purl.obolibrary.org/obo/duo.owl" --upper-term "obo:DUO_0000001" --upper-term "obo:DUO_0000017" --lower-term "obo:DUO_0000001" --lower-term "obo:DUO_0000017" --output $(IMPORTSDIR)/duo_import.owl
