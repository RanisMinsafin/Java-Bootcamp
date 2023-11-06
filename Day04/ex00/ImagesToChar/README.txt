1. Compile the project using Maven:
    mvn compile

2. After successfully building the project, you can run it with the following command:
     java -cp target/classes edu.school21.printer.app.Main 0 . /full/path/to/image.bmp

Where:
    - 0 is the character to be used for black pixels.
    - . is the character to be used for white pixels.
    - /full/path/to/image.bmp is the full path to the image you want to display.

For example:
    java -cp target/classes edu.school21.printer.app.Main 0 .  /Users/ranis/MyProjects/Java/Java_Bootcamp.Day04-1/src/ex00/it.bmp