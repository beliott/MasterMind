import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args){
        // test initTab
       int[] tabInit =  MasterMindBase.initTab(6, 5);
        System.out.println(tabInit);

        // test copieTab
        int[] tab1 = {3, 9, 78, 5, 4, 3};
        int[] tab2 = {4, 6, 7, 55, 4, 973};
        int[] tab3 = new int[0];
        int[] cpTab = MasterMindBase.copieTab(tab1);
        System.out.println(cpTab);
        System.out.println(MasterMindBase.copieTab(tab3));

        // test listElem
        char[] tabA = {'a', 'b', 'c', 'z'};
        char[] tabB = {'a', 'b', 'c', 'z', 'd', 'c'};
        char[] tabVide = new char[0];
        System.out.println(MasterMindBase.listElem(tabA));
        System.out.println(MasterMindBase.listElem(tabVide));

        // test PlusGrandIndice
        System.out.println(MasterMindBase.plusGrandIndice(tabA, 'c'));
        System.out.println(MasterMindBase.plusGrandIndice(tabB, 'c'));
        System.out.println(MasterMindBase.plusGrandIndice(tabVide, 'c'));

        // test estPresent
        System.out.println(MasterMindBase.estPresent(tabB, 'x'));
        System.out.println(MasterMindBase.estPresent(tabB, 'z'));
        System.out.println(MasterMindBase.estPresent(tabVide, 'a'));

        // test elemDiff
        System.out.println(MasterMindBase.elemDiff(tabA));
        System.out.println(MasterMindBase.elemDiff(tabB));
        System.out.println(MasterMindBase.elemDiff(tabVide));

        // test sontEgaux
        System.out.println(MasterMindBase.sontEgaux(tab1, cpTab));
        System.out.println(MasterMindBase.sontEgaux(tab1, tab3));
        System.out.println(MasterMindBase.sontEgaux(tab3, tab3));

        // test codeAleat
        System.out.println(MasterMindBase.codeAleat(4, 6));
        System.out.println(MasterMindBase.codeAleat(4, 6));
        System.out.println(MasterMindBase.codeAleat(4, 6));
    }


}
