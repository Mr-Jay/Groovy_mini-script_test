//PluginJson.groowy
//Genere un json en fonction du zip uploadé


import groovy.json.JsonBuilder

//On décompresse le zip temporairement
def ant = new AntBuilder()   // create an antbuilder
ant.unzip(  src:"C:\\Users\\jeremy.desmars\\Downloads\\clamav-php-clamdscan-1.0.1-c55ade.zip",
            dest:"C:\\Users\\jeremy.desmars\\temporaire",
            overwrite:"false" )

//On définit le fichier à lire
def composer = new File("C:\\Users\\jeremy.desmars\\temporaire\\clamav-php-clamdscan-1.0.1-c55ade\\clamav\\composer.json")
def tabComposer = []
//on parcourt chaque ligne pour chercher un élément précis
composer.eachLine
{
   stock->
        tabComposer= tabComposer + stock        
}

//On créer le fichier json
def fileName = ""
def versionFile = ""
def name = ""
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
def requiredVersion = ""
def requiredDevVersionLib = ""
def time = ""
def type = ""
def installationsource = ""
def description = ""
def homepage = ""
def src = ""
def builder = new groovy.json.JsonBuilder()
def json = new JsonBuilder([Package:[fileName:[version:[name: fileName, version: version, version_Normalized: version_Normalized, source:[type: typefile, url: url, reference: reference],dist:[type: typeFile, url:urlFile, reference: referenceFile, shasum: shasum],require:[requiredLib: requiredVersion],require_dev:[requiredDevLib: requiredDevVersionLib],time : time, type : type, installation_source: installationsource, autoload:[psr0:[name: src],],],licence:""],description:description, homepage: homepage, keywords:""],])
new File('C:/Users/jeremy.desmars/testPlugin/test.json').delete()
def generatedJson = new File('C:/Users/jeremy.desmars/testPlugin/test.json') << json.toPrettyString() 

//On génére un tableau composé de mots clef
def tabSearch = ['\"name\"','\"require\"','require_dev','\"type\"','autoload','license', 'description','homepage','keywords'] 

//on parcourt le fichier json créée, pour le remplir
generatedJson.eachLine
{
    change->
    for (j=0; j<tabSearch.size(); j++)
    {
        for(i=0;i<tabComposer.size();i++)
        {
            boolean inLine = change.contains(tabSearch[j])
            boolean inTab = tabComposer[i].contains(tabSearch[j])
            if ((inLine)&&(inTab))
            {
                change=change.replace (change, tabComposer[i]  )
                println change
            }
        }    
    }
}