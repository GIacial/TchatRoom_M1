

JAVAC = javac
EXEC = myProgram  jar
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(EXEC)

# JAVA #
myProgram: $(classes)


%.class : %.java
	$(JAVAC) $<

jar: $(classes)
	jar cvfm tchatroom.jar MANIFEST.MF $(classes)




# GENERAL #
clean:
	rm -rf *.o $(EXEC) *.exe *.class *.jar


.PHONY: clean
