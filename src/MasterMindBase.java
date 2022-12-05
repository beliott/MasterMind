import java.util.*;
import java.lang.*;

public class MasterMindBase {

    //.........................................................................
    // OUTILS DE BASE
    //.........................................................................
    
    // fonctions classiques sur les tableaux

    /** pré-requis : nb >= 0
	résultat : un tableau de nb entiers égaux à val
    */
    public static int[] initTab(int nb, int val){
        int[] tab = new int[nb];
        for(int i = 0; i < nb; i++){
            tab[i] = val;
        }
        return tab;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : une copie de tab
    */
    public static int[] copieTab(int[] tab){
        int[] tabCop =  new int[tab.length];
        for(int i = 0;  i < tab.length; i++){
            tabCop[i] = tab[i];
        }
	    return tabCop;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : la liste des éléments de t entre parenthèses et séparés par des virgules
    */
    public static String listElem(char[] t){
        String elts = "( ";
        for(int i = 0; i < t.length; i++){
            elts = elts + t[i];
            if(i != t.length - 1){ // si on est pas sur denier element, on ajoute une virgule pr celui d'apres
                elts = elts + ", ";
            }
        }
        elts = elts + ")";
        return elts;
    }

    //______________________________________________
    
    /** pré-requis : aucun
	résultat : le plus grand indice d'une case de t contenant c s'il existe, -1 sinon
    */
    public static int plusGrandIndice(char[] t, char c){
        int indice = -1;
        for( int i = 0; i < t.length; i++){
            if(t[i] == c){
                indice = i;
            }
        }
        return indice;

    }
    //______________________________________________

    /** pré-requis : aucun
	résultat : vrai ssi c est un élément de t
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean estPresent(char[] t, char c){
        return plusGrandIndice(t, c) != -1;
    }
        // regarder si la réponse est différente de -1  = regarder si le parcours du tableau a trouvé une correspondance

    //______________________________________________
    
    /** pré-requis : aucun
	action : affiche un doublon et 2 de ses indices dans t s'il en existe
	résultat : vrai ssi les éléments de t sont différents
	stratégie : utilise la fonction plusGrandIndice
    */
    public static boolean elemDiff(char[] t){
        for(int i = 0; i < t.length; i++){
            if( i != plusGrandIndice(t, t[i])){ // si t[i] n'est pas le plusGrandIndice -> alors il a un doublon
                System.out.println(t[i] + " indices : " + i + " et " + plusGrandIndice(t, t[i]));
                return false;
            }
        }
        return true;
    }
    
    //______________________________________________

    /** pré-requis : t1.length = t2.length
	résultat : vrai ssi t1 et t2 contiennent la même suite d'entiers
    */
    public static boolean sontEgaux(int[] t1, int[] t2){
        for( int i = 0; i < t1.length; i++){ // comparer petit a petit avec le meme compteur (ici i ) = regarder si tout le contenu est le meme.
            if(t1[i] != t2[i]){ // Ca fonctionne dans le cas ou le pre-requis est respecté
                return false;
            }
        }
        return true;
    }

    //______________________________________________

    // Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres lgCode, nbCouleurs et tabCouleurs :
    // lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont différents

    // fonctions sur les codes pour la manche Humain

    /** pré-requis : aucun
	résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et nbCouleurs-1
    */
    public static int[] codeAleat(int lgCode, int nbCouleurs){
        int[] code = new int[lgCode]; // on crée tableau de longueur du code
        for(int i = 0; i < lgCode; i++){
            int aleat = Ut.randomMinMax(0, nbCouleurs - 1); // on prend un nb aléatoire dans les bornes
            code[i] = aleat; // on l'ajoute dans la case parcourue
        }
	    return code;
    }      

    //____________________________________________________________
    
    /** pré-requis : aucun
	action : si codMot n'est pas correct, affiche pourquoi
	résultat : vrai ssi codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de tabCouleurs
    */
    public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs){
        if( codMot.length() != lgCode){
            System.out.println(" Le paramètre codMot n'est pas de longueur lgCode.");
            return false;
        }
        for(int i = 0; i < codMot.length(); i++){
            char l = codMot.charAt(i);  // je recupere la i-ème lettre du code
            for(int j = 0; j < tabCouleurs.length; j++){
                if(tabCouleurs[j] == l){
                    break;  // si lettre du code == element parcouru on peut passer
                }
                if(j == tabCouleurs.length - 1){ // si on a tt parcouru sans trouver de correspondances
                    System.out.println(l + " n'est pas un élément de tabCouleurs.");
                    return false;  // alors lettre du code pas dans les lettres du tab.
                }
            }
        }
        return true;
    }
   
    //____________________________________________________________
    
    /** pré-requis : les caractères de codMot sont des éléments de tabCouleurs
	résultat : le code codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans tabCouleurs
    */
    public static int[] motVersEntiers(String codMot, char[] tabCouleurs){
        int [] tabR = new int[codMot.length()];    // nouveau tableau qui prend la longueur de codMot
        for (int i = 0 ; i<tabR.length ; i++ ){
            for(int j = 0; j<tabCouleurs.length ; j++){
                if (tabCouleurs[j] == codMot.charAt(i)){ // on récupère le i-ème caractère du codMot
                    tabR[i] = j;                         // on remplace par l'indice tabCouleurs de la couleur de codmot
                }
            }
        }
        return tabR;
    }

    //____________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir la (nbCoups + 1)ème proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce 
	qu'elle soit correcte (le paramètre nbCoups ne sert que pour l'affichage)
	résultat : le code saisi sous forme de tableau d'entiers
    */
    public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs){
        String codeEssai;
        do {
            System.out.println("Veuillez saisir votre tentative numéro" + nbCoups + 1 + " :");
            codeEssai = Ut.saisirChaine(); // on demande à l'utilisateur de saisir un code ( chaine de caractères)
        }while( !codeCorrect(codeEssai, lgCode, tabCouleurs)); // il recommence jusqu'à ce que le format respecte les règles de codeCorrect

        return motVersEntiers(codeEssai, tabCouleurs); // transforme le code dans le type de retour attendu
    }

    //____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length
	résultat : le nombre d'éléments communs de cod1 et cod2 se trouvant au même indice
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 1 (le "0" à l'indice 3)
    */
    public static int nbBienPlaces(int[] cod1,int[] cod2){
        int compteur = 0;
        for (int i = 0; i < cod1.length; i++) {
            if (cod1[i] == cod2[i]) { // si les éléments du meme indice sont les memes alors ils sont bien placés
                compteur++;
            }

        }
        return compteur;
    }

    //____________________________________________________________
    
    /** pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod
	Par exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
    */
    public static int[] tabFrequence(int[] cod, int nbCouleurs){
        int compteur = 0;     // Permet de compter le nombre d'elements communs
        int[] tabR = new int [nbCouleurs];
        for (int i = 0 ; i<cod.length ; i ++){
            compteur=0;
            for(int j = 0; j<cod.length ; j++){
                if( i == cod[j]) {
                    compteur++;
                }
            }
            tabR[i] = compteur;
        }
        return tabR;
    }

    //____________________________________________________________
    
    /** pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : le nombre d'éléments communs de cod1 et cod2, indépendamment de leur position
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")nbCommuns + idee celui d'apres
    */
    public static int nbCommuns(int[] cod1,int[] cod2, int nbCouleurs){
        int cptCommuns = 0;
        int[] freqCod1 = tabFrequence(cod1, nbCouleurs);
        int[] freqCod2 = tabFrequence(cod2, nbCouleurs);
        for(int i = 0; i < freqCod1.length ; i++){
            if(freqCod1[i] == freqCod2[i]){
               cptCommuns += freqCod1[i];
            }
            else if (freqCod1[i] < freqCod2[i]){
                cptCommuns += freqCod1[i];
            }
            else if (freqCod1[i] > freqCod2[i]) {
                cptCommuns += freqCod2[i];
            }
        }

        return cptCommuns;
    }

    //____________________________________________________________
    
    /** pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1
	résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre d'éléments communs de cod1 et cod2
	se trouvant  (resp. ne se trouvant pas) au même indice
	Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé (le "0" à l'indice 3) 
	et 2 mal placés (1 "0" et 1 "1")
    */
    public static int[] nbBienMalPlaces(int[] cod1,int[] cod2, int nbCouleurs){
        // faire  k += 1 quand indice j = i et return ( k, nbCommuns-k)
        int[] nbBMP = {nbBienPlaces(cod1,cod2), nbCommuns(cod1, cod2,nbCouleurs) - nbBienPlaces(cod1, cod2)};
        return nbBMP;
    }


    //____________________________________________________________

    //.........................................................................
    // MANCHEHUMAIN
    //.........................................................................

    /** pré-requis : numMache >= 1
	action : effectue la (numManche)ème manche où l'ordinateur est le codeur et l'humain le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain (cf. sujet), 
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais 
            - sinon le nombre de codes proposés par le joueur humain          
    */
    public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax){
        int looser = 0;                                                    // Score final si le joueur ne gagne pas
        int[] machineCodeur = codeAleat(lgCode,tabCouleurs.length);
        while(numManche <= nbEssaisMax) {
            System.out.println("Vous êtes à la manche numéro" + numManche);
            if (numManche == nbEssaisMax -1) {
                System.out.println("Attention ! Il ne vous reste plus qu'une seule manche !");
            }
            int[] humainProposition = propositionCodeHumain(numManche, lgCode, tabCouleurs); //le joueur propose un code
            if (sontEgaux(humainProposition, machineCodeur) == true) {                  //le joueur gagne ?
                System.out.println("Bien joué ! Vous avez gagnés en " + numManche);
                return numManche;
            }
            // placement c'est un tableau avec i[0] pions biens placés / i[1] pions mal placés
            int [] placement = nbBienMalPlaces(machineCodeur,humainProposition,tabCouleurs.length);
            System.out.println("Vous avez " + placement[0] + "pions bien placés");
            System.out.print("Vous avez " + placement[1] + "pions mal placés" );
            if(numManche == nbEssaisMax) {
                looser = placement[1] + 2 * (lgCode - (placement[0] + placement[1])); // Score total malus
            }
            numManche++;
        }
        return looser;
    }

    //____________________________________________________________

    //...................................................................
    // FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
    //...................................................................

    /** pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1
	résultat : le code cod sous forme de mot d'après le tableau tabCouleurs
    */
    public static String entiersVersMot(int[] cod, char[] tabCouleurs){
        String codMot = "";
        for (int i = 0 ; i<cod.length;i++){
            codMot+=tabCouleurs[cod[i]]; // on ajoute chaque lettre dont l'indice est la valeur du tableau à l'indice i
        }
        return codMot;
    }

    //___________________________________________________________________
    
    /** pré-requis : rep.length = 2
	action : si rep n'est pas  correcte, affiche pourquoi, sachant que rep[0] et rep[1] sont 
	         les nombres de bien et mal placés resp.
	résultat : vrai ssi rep est correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
    */
    public static boolean repCorrecte(int[] rep, int lgCode){
        if(rep[0] >= 0 && rep[1] >= 0 && rep[0] + rep[1] <= lgCode){
            return true;
        }
        else if(rep[0]<0 || rep[1]<0){
            System.out.println("Le nombre de pions mal ou bien placés ne peut pas être négatif");
            return false;
        }
        else if(rep[0] + rep[1] > lgCode){
            System.out.println("Le nombre total de pion ne peut pas être supérieur a la taille du code");
            return false;
        }
        return true;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir les nombres de bien et mal placés, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : les réponses du joueur humain dans un tableau à 2 entiers
    */
    public static int[] reponseHumain(int lgCode){
        int repHumain[] = new int[2];
        String phrase [] = {"Veuillez saisir le nombre de pions bien placés","Veuillez saisir le nombre de pions mal placés"};
        for(int i = 0 ; i<3;i++){
            System.out.println(phrase[i]);
            repHumain [i] = Ut.saisirEntier();
            repCorrecte(repHumain,lgCode);
        }
        return repHumain;
    }

    //___________________________________________________________________

     /**CHANGE : action si le code suivant n'existe pas
     *************************************************
        pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1
	action/résultat : met dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble
    des codes à valeurs  de 0 à nbCouleurs-1) et retourne vrai si ce code existe,
     sinon met dans cod1 le code ne contenant que des "0" et retourne faux
    */
    public static boolean passeCodeSuivantLexico(int[] cod1, int  nbCouleurs) {
        int i = cod1.length - 1; // on part du dernier nb pour aller au suivant
        cod1[i] += 1;
        while(cod1[i] == nbCouleurs){ // on regarde si l'incrémentation dépasse le nb de couleurs dispos
           cod1[i] = 0;
           i--; // on baisse i pour voir si on retombe pas sur meme cas avec celui qu'on modifie
           if(i == -1){
               return false; // on a regressé en remplacant ttes les valeurs par 0 donc on était au max
           }
           cod1[i] += 1; // on incrémente la valeur au dessus ( comme quand on passe de 9 à 10 )
        }
        return true;
        }


    //___________________________________________________________________

     /**CHANGE : ajout du paramètre cod1 et modification des spécifications 
     *********************************************************************  
        pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                    nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
	résultat : vrai ssi cod1 est compatible avec les nbCoups premières lignes de cod et de rep,
             c'est-à-dire que si cod1 était le code secret, les réponses aux nbCoups premières
            propositions de cod seraient les nbCoups premières réponses de rep resp.
   */
   public static boolean estCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
       int[] nbBMP;
       for(int i = 0; i < nbCoups; i++){
           nbBMP = nbBienMalPlaces(cod1, cod[i], nbCouleurs);
           if(nbBMP[0] != rep[i][0] || nbBMP[1] == rep[i][1] ){
               return false;
           }
       }
       return true;
   }

    //___________________________________________________________________

     /**CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat, 
                 ajout du paramètre cod1 et modification des spécifications 
     **************************************************************************      
            pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0 <= nbCoups < cod.length, 
                    nbCoups < rep.length et les éléments de cod1 et de cod sont des entiers de 0 à nbCouleurs-1
	    action/résultat : met dans cod1 le plus petit code (selon l'ordre lexicographique (dans l'ensemble
    des codes à valeurs  de 0 à nbCouleurs-1) qui est à la fois plus grand que
      cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et rep si ce code existe,
      sinon met dans cod1 le code ne contenant que des "0" et retourne faux
   */
   public static boolean passeCodeSuivantLexicoCompat(int [] cod1, int [][] cod,int [][] rep, int nbCoups, int  nbCouleurs){
  
    }

    //___________________________________________________________________
    
    // manche Ordinateur

    /** pré-requis : numManche >= 2
	action : effectue la (numManche)ème  manche où l'humain est le codeur et l'ordinateur le décodeur
	(le paramètre numManche ne sert que pour l'affichage)
	résultat : 
            - 0 si le programme détecte une erreur dans les réponses du joueur humain
            - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf. sujet), 
              s'il n'a toujours pas trouvé au bout du nombre maximum d'essais 
            - sinon le nombre de codes proposés par l'ordinateur
    */
    public static int mancheOrdinateur(int lgCode,char[] tabCouleurs, int numManche, int nbEssaisMax) {
  
    }

    //___________________________________________________________________

    //.........................................................................
    // FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
    //.........................................................................


    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier strictement positif, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier strictement positif saisi
    */
    public static int saisirEntierPositif(){
        int rep;
        do{
            System.out.print("Veuillez saisir un entier positif");
            rep = Ut.saisirEntier();

        }while (rep < 0);
        return rep;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir un entier pair strictement positif, 
                 avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : l'entier pair strictement positif saisi
    */
    public static int saisirEntierPairPositif(){
        int rep;
        do{
            System.out.print("Veuillez saisir un entier pair positif");
            rep = Ut.saisirEntier();

        }while (rep < 0 && rep % 2 == 0);
        return rep;
    }

    //___________________________________________________________________
    
    /** pré-requis : aucun
	action : demande au joueur humain de saisir le nombre de couleurs (stricement positif), 
                 puis les noms de couleurs aux initiales différentes, 
	         avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte
	résultat : le tableau des initiales des noms de couleurs saisis
    */
    public static char[] saisirCouleurs(){
        System.out.print("Veuillez saisir le nombre de couleurs que vous voulez utilisez");
        int nbCouleurs = saisirEntierPositif();
        char[] tabCouleurs = new char [nbCouleurs];
        for(int i = 0; i<tabCouleurs.length ; i++){
            System.out.print("Veuillez saisir l'iniatiale de la couleur");
            tabCouleurs[i] = Ut.saisirCaractere();
        }
        return tabCouleurs;
    }

    //___________________________________________________________________

    //.........................................................................
    // PROGRAMME PRINCIPAL
    //.........................................................................
    

        /**CHANGE : ajout de : le nombre d'essais maximum doit être strictement positif
        ******************************************************************************
           action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs, 
           nbManches, nbEssaisMax), 
	   effectue la partie et affiche le résultat (identité du gagnant ou match nul).
	   La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum doivent être strictement positifs. 
	   Le nombre de manches doit être un nombre pair strictement positif.
	   Les initiales des noms de couleurs doivent être différentes. 
	   Toute donnée incorrecte doit être re-saisie jusqu'à ce qu'elle soit correcte.
    */
    public static void main (String[] args){
	
   
    } // fin main

    //___________________________________________________________________
    
} // fin MasterMindBase
