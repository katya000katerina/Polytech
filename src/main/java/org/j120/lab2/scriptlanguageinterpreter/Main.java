package org.j120.lab2.scriptlanguageinterpreter;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String sep = File.separator;
        ScriptLanguageInterpreter sci = new ScriptLanguageInterpreter("C:" + sep + "texts" + sep + "script.txt");
        sci.interpret();
    }
}
