public class Testumgebung
{
    public static void main( String[] args )
    {
        testMethods();
    }
    
    private static void testMethods()
    {
        char[] chars = {'n','d','a','f','b','e','k','i','x','o','s','z','r'};
        CharacterSearchTree cst = new CharacterSearchTree( chars );
        System.out.println("cst.show() - SOLL-Ergebnis: a b d e f i k n o r s x z");
        cst.show();
        
        // weitere Tests ergaenzen
    }

}

