import groovy.json.JsonBuilder

//On d�compresse le zip temporairement
def ant = new AntBuilder()   // create an antbuilder
ant.unzip(  src:"C:\\Users\\jeremy.desmars\\Downloads\\clamav-php-clamdscan-1.0.1-c55ade.zip",
            dest:"C:\\Users\\jeremy.desmars\\temporaire",
            overwrite:"false" )
            
//On d�finit le fichier � lire
def f = new File("C:\\Users\\jeremy.desmars\\temporaire\\clamav-php-clamdscan-1.0.1-c55ade\\clamav\\composer.json")
def encode = ""
//on parcourt chaque ligne pour chercher un �l�ment pr�cis
f.eachLine
{
    recherche->
    def ligne = recherche
    def premierMot = ""
    def tab = ['\"name\"','\"license\"', '\"description\"','\"type\"','\"require','\"keywords\"']
   
    
    //On parcourt la ligne
    for (i = 1; i <=ligne.size(); i++)
    {
        //Si le caractere est un espace on l'ignore
        if((ligne.substring(i-1,i)!="\t")&&(ligne.substring(i-1,i)!=" "))
        { 
            //On construit une chaine jusqu'a ce que celui-ci corresponde au mot voulus
            premierMot = premierMot + ligne.substring(i-1, i)
            for (j=0; j<=5; j++)
            {
                //Si la chaine correspond on le signal
                if (premierMot == tab[j])
                {
                    liste= recherche
                    println liste             
                }
            }
        }
    }
}