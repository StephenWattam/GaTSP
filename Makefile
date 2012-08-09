CC=javac
CFLAGS=

all:
	$(CC) $(CFLAGS) gatsp/*.java *.java

docs:
	javadoc -d docs gatsp/*.java *.java

clean:
	rm -rfv docs
	rm -rfv *.class
	rm -rfv gatsp/*.class
