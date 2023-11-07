package edu.school21.console.game.app;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import edu.school21.console.game.args.Args;

public class Main {
    private static final String ERROR_MESSAGE = "Error: invalid parameters passed to the method";

    public static void main(String[] args) {
        try {
            Args jargs = new Args();
            JCommander jCommander = new JCommander(jargs);
            jCommander.parse(args);

            System.out.println(jargs.getEnemiesCount());
            System.out.println(jargs.getWallsCount());
            System.out.println(jargs.getSize());
            System.out.println(jargs.getProfile());
        } catch (ParameterException e) {
            throw new IllegalParametersException(ERROR_MESSAGE);
        }


    }
}