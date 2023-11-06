package edu.school21.printer.logic;

import com.beust.jcommander.*;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = {"--white"})
    private String whiteCharColor;
    @Parameter(names = {"--black"})
    private String blackCharColor;

    public String getWhite() {
        return whiteCharColor;
    }

    public String getBlack() {
        return blackCharColor;
    }


}
