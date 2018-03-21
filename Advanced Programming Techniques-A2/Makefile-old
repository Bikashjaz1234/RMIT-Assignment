CC = gcc
RM = rm -rf
CFLAGS = -Wall -pedantic -ansi -g

SOURCES = *.c
EXECUTABLES = ppd

all: $(SOURCES)
	$(CC) $(CFLAGS) -o $(EXECUTABLES) $(SOURCES)

g: $(SOURCES)
	$(CC) $(CFLAGS) -g -o $(EXECUTABLES) $(SOURCES)

test: $(SOURCES)
	valgrind ./$(EXECUTABLES) --track-origins=yes --log-file log.txt

clean:
	$(RM) $(EXECUTABLES)
