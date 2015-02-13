import java.nio.file.Files
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

zip="clamav-php-clamdscan-1.0.0-f79183.zip"

def zipFile = new java.util.zip.ZipFile(new File("C:\\Users\\jeremy.desmars\\Downloads\\"+zip)  )
def searchstr = "composer.json" 
zipFile.entries().each 
{
    entry->  
                if (entry.name == searchstr)
                {
                     fichier =  zipFile.getInputStream(entry).text    
                    find = true      
                }        
}

if (find == true)
{
    def jsonSlurper = new JsonSlurper()
    def object = jsonSlurper.parseText(fichier)
    
    expression= /(.*)-([0-9]+).([0-9]+).([0-9]+)-(.*)/
    matcher = ( zip =~ expression )
    if (matcher.matches()) 
    {
        version = matcher[0][2]+"."+matcher[0][3]+"."+matcher[0][4]
    }
    //On créer le fichier json
    def fileName = ""
    def versionFile = ""
    def name = object.name
    def version = version
    def source = ""
    def version_Normalized = version+".0"
    def typefile = ""
    def url = ""
    def reference = ""
    def typeFile = ""
    def urlFile = ""
    def referenceFile = ""
    def shasum = ""
    def require = object.require
    def require_dev = object.get("require-dev")
    def time = ""
    def type = object.type
    def installationsource = ""
    def autoload = object.autoload
    def license = object.license
    def description = object.description
    def homepage = object.homepage
    def src = ""
    def keywords =  object.keywords
    def builder = new groovy.json.JsonBuilder()
    def json = new JsonBuilder([Package:[fileName:[version:[name: name, version: version, version_Normalized: version_Normalized, source:[type: typefile, url: url, reference: reference],dist:[type: typeFile, url:urlFile, reference: referenceFile, shasum: shasum],require:require, require_dev:require_dev, time : time, type : type, installation_source: installationsource, autoload: autoload],],license: license],description:description, homepage: homepage, keywords:keywords])
    new File('C:/Users/jeremy.desmars/testPlugin/test.json').delete()
    def generatedJson = new File('C:/Users/jeremy.desmars/testPlugin/test.json') << json.toPrettyString() 
}
