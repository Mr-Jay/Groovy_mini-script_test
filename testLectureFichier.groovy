//testLectureFichier.groowy
//Fichier lisant un fichier, encodant le contenue pour le décoder dans 
//un autres fichier généré

import groovy.json.JsonBuilder

//On décompresse le zip temporairement
def ant = new AntBuilder()   // create an antbuilder
ant.unzip(  src:"C:\\Users\\jeremy.desmars\\Downloads\\clamav-php-clamdscan-1.0.1-c55ade.zip",
            dest:"C:\\Users\\jeremy.desmars\\temporaire",
            overwrite:"false" )
            
//On définit le fichier à lire
def f = new File("C:\\Users\\jeremy.desmars\\temporaire\\clamav-php-clamdscan-1.0.1-c55ade\\clamav\\composer.json")
def encode = ""
//on parcourt chaque ligne pour chercher un élément précis
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
                println "Trouvé ${recherche}"
            }
        }
    }
    //On stoque toutes les lignes dans une variable
    encode= encode +"\n"+ recherche
}

//Encodage du contenu du fichier
def encoded = encode.bytes.encodeBase64().toString()
println encoded

//Décodage du contenu du fichier
def decoded = new String(encoded.decodeBase64())
println decoded
//def builder = new groovy.json.JsonBuilder()
//def json = new JsonBuilder(decoded)
//println json
new File('C:/Users/jeremy.desmars/testPlugin/test2.json').delete()
//On créer un fichier dans le quel on met le contenue du premier fichier décodé
new File('C:/Users/jeremy.desmars/testPlugin/test2.json') << decoded