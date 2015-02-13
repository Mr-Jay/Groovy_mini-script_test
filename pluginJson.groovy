//PluginJson.groowy
//Genere un json en fonction du zip uploadé

import com.fasterxml.jackson.databind.ObjectMapper;          
import groovy.json.JsonBuilder
import groovy.json.JsonLexer
//On décompresse le zip temporairement
def ant = new AntBuilder()   // create an antbuilder
ant.unzip(  src:"C:\\Users\\jeremy.desmars\\Downloads\\clamav-php-clamdscan-1.0.1-c55ade.zip",
            dest:"C:\\Users\\jeremy.desmars\\temporaire",
            overwrite:"false" )

//On définit le fichier à lire
def composer = new File("C:\\Users\\jeremy.desmars\\temporaire\\clamav-php-clamdscan-1.0.1-c55ade\\clamav\\composer.json")
def fichier = ""
//on parcourt chaque ligne pour chercher un élément précis
composer.eachLine
{
   stock->
        fichier= fichier + stock        
}


//Conversion d’un String json en un objet java :
mapper = new ObjectMapper()
obj = mapper.readTree(fichier)

println obj.get("keywords")
//Accès à une propriété avec get()
String nameString = obj.get("keywords").asText()
println nameString
//On créer le fichier json
def fileName = ""
def versionFile = ""
def name = obj.get("name").asText()
def version = ""
def source = ""
def version_Normalized = ""
def typefile = ""
def url = ""
def reference = ""
def typeFile = ""
def urlFile = ""
def referenceFile = ""
def shasum = ""
def require = obj.get("require")
def requiredDevVersionLib = ""
def time = ""
def type = obj.get("type").asText()
def installationsource = ""
def license = obj.get("license").asText()
def description = obj.get("description").asText()
def homepage = obj.get("homepage").asText()
def src = ""
def keywords =  obj.get("keywords")

def builder = new groovy.json.JsonBuilder()
def json = new JsonBuilder([Package:[fileName:[version:[name: name, version: version, version_Normalized: version_Normalized, source:[type: typefile, url: url, reference: reference],dist:[type: typeFile, url:urlFile, reference: referenceFile, shasum: shasum],require:require,require_dev:[requiredDevLib: requiredDevVersionLib],time : time, type : type, installation_source: installationsource, autoload:[psr0:[name: src],],],license: license],description:description, homepage: homepage, keywords:keywords],])
new File('C:/Users/jeremy.desmars/testPlugin/test.json').delete()
def generatedJson = new File('C:/Users/jeremy.desmars/testPlugin/test.json') << json.toPrettyString() 

//On génére un tableau composé de mots clef
def tabSearch = ['name','require','require_dev','type','autoload','license', 'description','homepage','keywords'] 
fichierGeneratedJson = ""
//on parcourt le fichier json créée, pour le remplir
generatedJson.eachLine
{
    change->
    fichierGeneratedJson = fichierGeneratedJson + change
}

//Accès à une propriété avec get()
//String newJson = obj.get("").asText()
//println newJson
//
// for (j=0; j<tabSearch.size(); j++)
//    {
//        condition = objJson.get(tabSearch[j])
//            if (condition==null)
//            {   
////                valeur1 = obj.get(tabSearch[j])
////                valeur2 =  objJson.get(tabSearch[j])
//                  valeur3 =  obj.get(tabSearch[j])
//                  objJson.set(tabSearch[j]) = valeur
//               // println valeur3
//            } 
//    }
//    println objJson