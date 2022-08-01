package com.cheeseocean.common.util;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

public class CheeseOceanBanner implements Banner {

//    private static final String[] BANNER = {
//            "   ██████  ██                                          ███████                                     ",
//            "  ██░░░░██░██                                         ██░░░░░██                                    ",
//            " ██    ░░ ░██████   █████   █████   ██████  █████    ██     ░░██  █████   █████   ██████   ███████ ",
//            "░██       ░██░░░██ ██░░░██ ██░░░██ ██░░░░  ██░░░██  ░██      ░██ ██░░░██ ██░░░██ ░░░░░░██ ░██░░░██",
//            "░██       ░██  ░██░███████░███████░░█████ ░███████  ░██      ░██░██  ░░ ░███████  ███████ ░██  ░██",
//            "░░██    ██░██  ░██░██░░░░ ░██░░░░  ░░░░░██░██░░░░   ░░██     ██ ░██   ██░██░░░░  ██░░░░██ ░██  ░██",
//            " ░░██████ ░██  ░██░░██████░░██████ ██████ ░░██████   ░░███████  ░░█████ ░░██████░░███████ ░██  ░██",
//            "  ░░░░░░  ░░   ░░  ░░░░░░  ░░░░░░ ░░░░░░   ░░░░░░     ░░░░░░░    ░░░░░   ░░░░░░  ░░░░░░░  ░░   ░░ "};
//
    private static final String[] BANNER = {
            " ██████╗██╗  ██╗███████╗███████╗███████╗███████╗     ██████╗  ██████╗███████╗ █████╗ ███╗   ██╗" +
            "██╔════╝██║  ██║██╔════╝██╔════╝██╔════╝██╔════╝    ██╔═══██╗██╔════╝██╔════╝██╔══██╗████╗  ██║" +
            "██║     ███████║█████╗  █████╗  ███████╗█████╗      ██║   ██║██║     █████╗  ███████║██╔██╗ ██║" +
            "██║     ██╔══██║██╔══╝  ██╔══╝  ╚════██║██╔══╝      ██║   ██║██║     ██╔══╝  ██╔══██║██║╚██╗██║" +
            "╚██████╗██║  ██║███████╗███████╗███████║███████╗    ╚██████╔╝╚██████╗███████╗██║  ██║██║ ╚████║" +
            " ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝╚══════╝     ╚═════╝  ╚═════╝╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝"};
    private static final String CHEESE_OCEAN = " :: Cheese Ocean :: ";
    private static final String SPRING_BOOT = " :: Spring Boot :: ";

    private static final int STRAP_LINE_SIZE = 96;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, line));
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }
        printStream.println(AnsiOutput.toString(AnsiColor.YELLOW, CHEESE_OCEAN, AnsiColor.DEFAULT, padding.toString(),
                AnsiStyle.FAINT, version));
        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding.toString(),
                AnsiStyle.FAINT, version));
        printStream.println();
    }
}
