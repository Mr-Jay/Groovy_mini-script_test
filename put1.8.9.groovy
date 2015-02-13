import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
import java.nio.file.Files

createInclude()


/**
 * Generate a new Json include
 * @param item 
 */
private def createInclude()
{
    def property = '{"firstName":"Jeremy","name":"Desmars","version":"21","City":"St Nazaire"}'
    
    def jsonSlurperProperty = new JsonSlurper()
    def objectProperty = jsonSlurperProperty.parseText(property) 
   
    def include = '{"packages":{"Desmars":{"21":{"firstName":"Jeremy","name":"Desmars","version":"22","City":"Nantes"}}}}'
    def jsonSlurperInclude = new JsonSlurper()
    def objectInclude = jsonSlurperInclude.parseText(include)

    version = objectProperty.version
    name = objectProperty.name
  
    //put objectProperty in objectInclude
    if (!objectInclude.packages.get(name))
    {
        println "titi"
        def stringTmp ="{}"
        def jsonSlurperTmp = new JsonSlurper()
        def objectTmp = jsonSlurperTmp.parseText(stringTmp)

        objectTmp.put("${version}", objectProperty)
        objectInclude.packages.put("${name}", objectTmp) 
    }
    else
    {
        println "toto"
        objectInclude.packages.get(name).put(version, objectProperty)
    }


    //create new includeJson and delete old include
    def includeJson = new JsonBuilder(objectInclude)
    println includeJson.toPrettyString()          
}
