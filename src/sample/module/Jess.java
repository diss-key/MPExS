package sample.module;

import jess.JessException;
import jess.Rete;

import java.io.StringWriter;
import java.io.Writer;

public class Jess {
    public static Rete rete;
    public boolean open()  {
        rete = new Rete();
        try {
            rete.batch("templates.clp");
            rete.batch("facts.clp");
            return true;
        } catch (JessException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static String runner() throws JessException {
        Writer writer = new StringWriter();
        rete.addOutputRouter("t", writer);
        rete.run();
        return writer.toString();
    }

    public static void resetter() throws JessException {
        rete.reset();
    }

    public static void end() throws JessException {
        rete.reset();
        rete.halt();
    }


}
