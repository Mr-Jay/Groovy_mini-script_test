//testLectureFichier.groowy
//Fichier lisant un fichier, encodant le contenue pour le d�coder dans 
//un autres fichier g�n�r�

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
    def chaine = recherche
    def premierMot = ""
    //On parcourt la ligne
    for (i = 1; i <=chaine.size(); i++)
    {
        //Si le caractere est un espace on l'ignore
        if(chaine.substring(i-1,i)!=" ")
        { 
            //On construit une chaine jusqu'a ce que celui-ci corresponde au mot voulus
            premierMot = premierMot + chaine.substring(i-1, i)
            //Si la chaine correspond on le signal
            if (premierMot == '\"FileName\"')
            {
                println recherche.size()
                println "Trouv� ${recherche}"
            }
        }
    }
    //On stoque toutes les lignes dans une variable
    encode= encode +"\n"+ recherche
}

//Encodage du contenu du fichier
def encoded = encode.bytes.encodeBase64().toString()
println encoded

//D�codage du contenu du fichier
def decoded = new String(encoded.decodeBase64())
println decoded
//def builder = new groovy.json.JsonBuilder()
//def json = new JsonBuilder(decoded)
//println json
new File('C:/Users/jeremy.desmars/testPlugin/test2.json').delete()
//On cr�er un fichier dans le quel on met le contenue du premier fichier d�cod�
new File('C:/Users/jeremy.desmars/testPlugin/test2.json') << decoded