
CC = gcc
CPP = g++
JAVAC = javac
CS = mcs
CXXFLAG = -Werror -Wall -ansi -pedantic -O3
EXEC = exo12cpp exo12.exe jar
OBJ = exo12.o exo12main.o
sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: $(EXEC)

# JAVA #
myProgram: $(classes)


%.class : %.java
	$(JAVAC) $<

jar: $(classes)
	jar cvfm exo12.jar MANIFEST.MF $(classes)




# C++ #
%.o : %.cpp
	$(CPP) -c -o $@ $< $(CXXFLAG)

exo12cpp : $(OBJ)
	$(CPP) -o $@ $^




# C# #
exo12.exe : exo12.cs exo12main.cs
	$(CS) -optimize $^


# GENERAL #
clean:
	rm -rf *.o $(EXEC) *.exe *.class *.jar


.PHONY: clean


archive: clean
	tar -zcvf COO_MOISE_TP_1.tar.gz ../COO_MOISE_TP_1
