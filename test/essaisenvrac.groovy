import groovy.json.JsonBuilder

//On d�compresse le zip temporairement
def ant = new AntBuilder()   // create an antbuilder
ant.unzip(  src:"C:\\Users\\jeremy.desmars\\Downloads\\clamav-php-clamdscan-1.0.1-c55ade.zip",
            dest:"C:\\Users\\jeremy.desmars\\temporaire",
            overwrite:"false" )
            
//On d�finit le fichier � lire
def f = new File("C:\\Users\\jeremy.desmars\\temporaire\\clamav-php-clamdscan-1.0.1-c55ade\\clamav\\composer.json")
def encode = ""
def tab = []
//on parcourt chaque ligne pour chercher un �l�ment pr�cis
f.eachLine
{
    recherche->
        tab = tab + recherche        
}
println tab
def f2 = new File("C:\\Users\\jeremy.desmars\\testPlugin\\test.json")
def tab2 = ['\"name\"','\"license\"', '\"description\"','\"type\"','\"Require','\"keywords\"'] 
f2.eachLine
{
    changement->
    for (j=0; j<=5; j++)
    {
        boolean b3 = changement.contains (tab2[j]);        // b3 vaut true
        if (b3)
        { 
            int p6 = changement.lastIndexOf (":")            
            changement.replace (changement, 'test' )
            println changement
        }
    }
}