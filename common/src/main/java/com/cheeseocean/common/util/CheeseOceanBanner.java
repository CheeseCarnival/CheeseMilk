package com.cheeseocean.common.util;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

public class CheeseOceanBanner implements Banner {

    private static final String[] BANNER = {"     _/_/_/  _/    _/  _/_/_/_/  _/_/_/_/    _/_/_/  _/_/_/_/        _/_/      _/_/_/  _/_/_/_/    _/_/    _/      _/   ",
            "  _/        _/    _/  _/        _/        _/        _/            _/    _/  _/        _/        _/    _/  _/_/    _/    ",
            " _/        _/_/_/_/  _/_/_/    _/_/_/      _/_/    _/_/_/        _/    _/  _/        _/_/_/    _/_/_/_/  _/  _/  _/     ",
            "_/        _/    _/  _/        _/              _/  _/            _/    _/  _/        _/        _/    _/  _/    _/_/      ",
            " _/_/_/  _/    _/  _/_/_/_/  _/_/_/_/  _/_/_/    _/_/_/_/        _/_/      _/_/_/  _/_/_/_/  _/    _/  _/      _/       "};
    private static final String CHEESE_OCEAN = " :: Cheese Ocean :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(AnsiOutput.toString(AnsiColor.BRIGHT_RED, line));
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE - (version.length() + CHEESE_OCEAN.length())) {
            padding.append(" ");
        }
        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, CHEESE_OCEAN, AnsiColor.DEFAULT, padding.toString(),
                AnsiStyle.FAINT, version));
        printStream.println();
    }
}
