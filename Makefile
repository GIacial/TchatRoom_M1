

JAVAC = javac
EXEC = exo12cpp exo12.exe jar
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(EXEC)

# JAVA #
myProgram: $(classes)


%.class : %.java
	$(JAVAC) $<

jar: $(classes)
	jar cvfm exo12.jar MANIFEST.MF $(classes)




# GENERAL #
clean:
	rm -rf *.o $(EXEC) *.exe *.class *.jar


.PHONY: clean
