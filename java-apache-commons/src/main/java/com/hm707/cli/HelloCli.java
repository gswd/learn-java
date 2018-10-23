package com.hm707.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * user guide : http://commons.apache.org/proper/commons-cli/introduction.html
 */
public class HelloCli {
  public static void main(String[] args) {

    //definition Option
    Option help = new Option("help", "print this message");
    Option quiet = new Option("quiet", "be extra quiet");

    Option logfile = Option.builder("logfile").argName("file").hasArg().desc("use give file for log").build();

    Option logger = Option.builder("logger").argName("className").hasArg().desc(
      "the class which it to perform logging").build();

    Option property = Option.builder("D").hasArgs().valueSeparator().desc("use value for given property").build();

    Options options = new Options();
    options.addOption(help);
    options.addOption(quiet);
    options.addOption(logfile);
    options.addOption(logger);
    options.addOption(property);

    // create the parser
    CommandLineParser parser = new DefaultParser();
    try {
      // parse the command line arguments
      CommandLine line = parser.parse(options, args);
      if (line.hasOption("help")) {
        System.out.println("~~~~~help~~~~~~");
        System.out.println(help.getDescription());

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "ant", options);

        System.out.println("--------------------");

        formatter.printHelp( "ant", options, true);

        System.out.println("-----------------------");
      }

      if (line.hasOption("logfile")) {
        System.out.println("[logfile] - " + line.getOptionValue("logfile"));
      }

      if (line.hasOption("D")) {
        System.out.println("[property] - key : " + line.getOptionValues("D")[0]);
        System.out.println("[property] - value : " + line.getOptionValues("D")[0]);
      }
    } catch (ParseException exp) {
      // oops, something went wrong
      System.err.println("Parsing failed.  Reason: " + exp.getMessage());
    }


  }

}
