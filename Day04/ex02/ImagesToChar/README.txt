1. Compile and package the project using Maven:
    mvn package

2. After successfully building and packaging the project, you can run it with the following command:
    java -cp "target/*:lib/*" edu.school21.printer.app.Main --white=RED --black=GREEN

Where:
    - GREEN is the color to be used for black pixels.
    - RED is the character to be used for white pixels.