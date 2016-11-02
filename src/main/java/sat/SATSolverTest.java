package sat;

/*
import static org.junit.Assert.*;

import org.junit.Test;
*/

import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.tools.internal.xjc.outline.ClassOutline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.type.ArrayType;

import immutable.ImList;
import sat.env.*;
import sat.formula.*;


public class SATSolverTest {
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();

    private static Graph G;


	
	// TODO: add the main method that reads the .cnf file and calls SATSolver.solve to determine the satisfiability
    public static void main(String[] args) throws IOException {
        String content=readFile("/Users/chs/Documents/SUTD/intro to info/Project-2D/Project-2D-starting/sampleCNF/largeUnsat.cnf");

        System.out.println(getV(content));
        String[] 




    }

    private static String readFile (String filename) throws IOException {
        File cnfFile=new File(filename);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(cnfFile));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while((line = bufferedReader.readLine())!=null){
            stringBuffer.append(line).append(" ");
        }
        return stringBuffer.toString().substring(getV(stringBuffer.toString()));
    }




    private static int getV (String cnfString) {
        int indTmp = cnfString.indexOf('p');
        return Character.getNumericValue(cnfString.charAt(indTmp + 6));
        // "p\scnf\s(\d+)\s(\d+)" // Group(1): number of vars, Group(2):number of clauses
        // +6:#vars, +8+trunc(lg_10 #vars):#clauses
    }




	
    public void testSATSolver1(){
    	// (a v b)
    	Environment e = SATSolver.solve(makeFm(makeCl(a,b))	);
/*
    	assertTrue( "one of the literals should be set to true",
    			Bool.TRUE == e.get(a.getVariable())  
    			|| Bool.TRUE == e.get(b.getVariable())	);
    	
*/    	
    }
    
    
    public void testSATSolver2(){
    	// (~a)
    	Environment e = SATSolver.solve(makeFm(makeCl(na)));
/*
    	assertEquals( Bool.FALSE, e.get(na.getVariable()));
*/    	
    }
    
    private static Formula makeFm(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
            f = f.addClause(c);
        }
        return f;
    }
    
    private static Clause makeCl(Literal... e) {
        Clause c = new Clause();
        for (Literal l : e) {
            c = c.add(l);
        }
        return c;
    }
    
    
    
}