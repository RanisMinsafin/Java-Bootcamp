1. Compile and package the project using Maven:
    mvn package

2. After successfully building and packaging the project, you can run it with the following command:
     java -cp target/classes edu.school21.printer.app.Main 0 . /full/path/to/image.bmp

Where:
    - 0 is the character to be used for black pixels.
    - . is the character to be used for white pixels.
    - /full/path/to/image.bmp is the full path to the image you want to display.

For example:
    java -cp target/classes edu.school21.printer.app.Main . 0 target/classes/it.bmp
