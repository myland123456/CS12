# A simple Makefile for the HelloWorld program
HandDisplayTest.class: *.java
	javac -Xlint *.java

HandDisplayTest.jar: HandDisplayTest.class
	echo Main-class: HandDisplayTest > Manifest
	jar cvfm HandDisplayTest.jar Manifest *.class *.java cards/*.gif
	rm Manifest
	chmod u+x HandDisplayTest.jar

clean:
	rm -f *.jar *.class