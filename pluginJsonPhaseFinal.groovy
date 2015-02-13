import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
import java.nio.file.Files

//removeInclude()
createInclude()

/**
 * Generate a temporary composer.Json
 * @param item 
 */
private def createJson()
{
    zip = "clamav-php-clamdscan-1.0.1-c55ade.zip"
    zip2 = "clamav-php-clamdscan-1.0.0-f79183.zip"
    zip3 = "TwigTest-1.2.3-f45687.zip"
    //Create a string with the contain of composer.json                
                                                    //ctx.artifactoryHome.data.tmp.artifactory-uploads
    def zipFile = new java.util.zip.ZipFile(new File("C:\\Users\\jeremy.desmars\\Documents\\satisBuilder\\satisBuilder\\build\\package\\satis\\dist\\"+zip))
    def searchstr = "composer.json" 
    def find = false
    zipFile.entries().each 
    {
        entry->  
        if (entry.name == searchstr)
        {
            stringFile = zipFile.getInputStream(entry).text         
            find = true 
        }         
    }
    def condition = find
    //Check if upload is a composer library
    if (condition)
    {
        //Change string in object
        def jsonSlurper = new JsonSlurper()
        def objectFile = jsonSlurper.parseText(stringFile)
    
        //Define variable for JsonMap
        def name = objectFile.name
        def version = ""
        def version_Normalized = ""
        def typefile = ""
        def url = ""
        def reference = ""
        def typeFile = ""
        def urlFile = "http://localhost:8081/artifactory/"+zip
        def shasum = ""
        def require = objectFile.require
        def require_dev = objectFile.get("require-dev")
        def time = ""
        def type = objectFile.type
        def installationsource = ""
        def autoload = objectFile.autoload
        def license = objectFile.license
        def description = objectFile.description
        def homepage = objectFile.homepage
        def keywords =  objectFile.keywords

        /**Recup version in FileName
        *Filename Type:  string-x.x.x-string
        *string = (.*)
        *x = ([0-9]) =  number
        **/
        expression = /(.*)-([0-9]).([0-9]).([0-9])-(.*)/
        matcher = (zip =~ expression)
        if (matcher.matches()) 
        {
            version = matcher[0][2]+"."+matcher[0][3]+"."+matcher[0][4]
            version_Normalized = version+".0"
        }

        //create JsonMap
        def builder = new groovy.json.JsonBuilder()
        def json = new JsonBuilder([name: name, version: version, version_Normalized: version_Normalized, source:[type: typefile, url: url, reference: reference],dist:[type: typeFile, url:urlFile, reference: reference, shasum: shasum],require:require, require_dev: require_dev, time : time, type : type, installation_source: installationsource, autoload:[autoload],license:[ license],description:description, homepage: homepage, keywords:keywords])
                
        //Create a new JsonFile
        new File('C:/Users/jeremy.desmars/testPlugin/test.json').delete()
        def composer = new File('C:/Users/jeremy.desmars/testPlugin/test.json') << json.toPrettyString()

        return composer
                
    }
}

/**
 * Generate a new Json include
 * @param item 
 */
private def createInclude()
{
    //find old include
    def include = new File('C:/Users/jeremy.desmars/testPlugin/test2.json')
    includeString = ""
    include.eachLine
    {
        line->  
        includeString = includeString + line 
    }
    
    //create string property
    def property = ""
    //property="eyJuYW1lIjoiY2xhbWF2XC9waHAtY2xhbWRzY2FuIiwidmVyc2lvbiI6IjEuMC4wIiwidmVyc2lvbl9ub3JtYWxpemVkIjoiMS4wLjAuMCIsInNvdXJjZSI6eyJ0eXBlIjoiZ2l0IiwidXJsIjoiaHR0cHM6XC9cL2dpdC5ub3ZpYS1zeXN0ZW1zLmZyXC9yXC9DUklGXC83RU5BTjAxMDQwVE1BLURzaUZvcmdlXC9waHAtY2xhbWRzY2FuLmdpdCIsInJlZmVyZW5jZSI6ImJjNWY4ZDNkNWFmZWEyMzU2NDQxYjc4Y2NlZDMzYWQ4M2VjZjI4NzgifSwiZGlzdCI6eyJ0eXBlIjoiemlwIiwidXJsIjoiaHR0cHM6XC9cL3N0b3JlLm5vdmlhLXN5c3RlbXMuZnJcL0ludGVybmUtQ29tcG9zZXItbG9jYWxcL2Rpc3RcL2NsYW1hdi1waHAtY2xhbWRzY2FuLTEuMC4wLWY3OTE4My56aXAiLCJyZWZlcmVuY2UiOiJiYzVmOGQzZDVhZmVhMjM1NjQ0MWI3OGNjZWQzM2FkODNlY2YyODc4Iiwic2hhc3VtIjoiM2QyYzRmMTc1OGIyMWU3MmVkOWQ4YWNiNWU5OThlZmVhNGFiMjU0NSJ9LCJyZXF1aXJlIjp7InBocCI6Ij49NS4zIn0sInJlcXVpcmUtZGV2Ijp7InBocHVuaXRcL3BocHVuaXQiOiI0LjAuKiJ9LCJ0aW1lIjoiMjAxNC0xMS0xMyAxMDo0NTo0MiIsInR5cGUiOiJsaWJyYXJ5IiwiaW5zdGFsbGF0aW9uLXNvdXJjZSI6InNvdXJjZSIsImF1dG9sb2FkIjp7InBzci0wIjp7IkNsYW1hdiI6InNyY1wvIn19LCJsaWNlbnNlIjpbIk1JVCJdLCJkZXNjcmlwdGlvbiI6IkNsYW1hdiBQSFAgQ2xpZW50IHVzaW5nIHRoZSAnJ2NsYW1kc2NhbicnIGNvbW1hbmQgbGluZSB0b29sIiwiaG9tZXBhZ2UiOiJodHRwOlwvXC93d3cuY2xhbWF2Lm5ldFwvIiwia2V5d29yZHMiOlsiYW50aXZpcnVzIiwiY2xhbWF2Il19DQo"
      property = loadProperties()

    if (!property)
    {
        // Call createJson() function
        def composer=createJson()
        composer.eachLine
        {
            line->  
            property = property + line 
        } 
                    println "titi"
     }
       else
       {
           println "tutu"
        property = new String(property.decodeBase64())
    }
    //change string Property to object
    //The actual version of groovy in artifactory is 1.8.8, in 2.2.* or more we can parse file directly (change parseTexte(string) to parse(file))
    def jsonSlurperProperty = new JsonSlurper()
    def objectProperty = jsonSlurperProperty.parseText(property) 
    //change old include to object
    def jsonSlurperInclude = new JsonSlurper()
    def objectInclude = jsonSlurperInclude.parseText(includeString)

    version = objectProperty.version
    name = objectProperty.name

    //put objectProperty in objectInclude
    if (!objectInclude.packages.get(name))
    {
        def stringTmp ="{}"
        def jsonSlurperTmp = new JsonSlurper()
        def objectTmp = jsonSlurperTmp.parseText(stringTmp)

        objectTmp.put("${version}", objectProperty)
        objectInclude.packages.put(name, objectTmp) 
    }
    else
    {
        objectInclude.packages.get(name).put(version, objectProperty)
    }

    //create new includeJson and delete old include
    def includeJson = new JsonBuilder(objectInclude) 
                    
    new File('C:/Users/jeremy.desmars/testPlugin/test2.json').delete()
    new File('C:/Users/jeremy.desmars/testPlugin/test2.json') << includeJson.toPrettyString()

    //Create and set property to artifact
   // encode = createJson().bytes.encodeBase64().toString()
//    repositories.setProperty item.repoPath, 'json', encode
}


/**
*load properties file
*@param item
**/

private def loadProperties()
{    
   properties101="ewogICAgIm5hbWUiOiAiY2xhbWF2L3BocC1jbGFtZHNjYW4iLAogICAgInZlcnNpb24iOiAiMS4wLjEiLAogICAgInZlcnNpb25fTm9ybWFsaXplZCI6ICIxLjAuMS4wIiwKICAgICJzb3VyY2UiOiB7CiAgICAgICAgInR5cGUiOiAiXCJcIiIsCiAgICAgICAgInVybCI6ICJcIlwiIiwKICAgICAgICAicmVmZXJlbmNlIjogIlwiXCIiCiAgICB9LAogICAgImRpc3QiOiB7CiAgICAgICAgInR5cGUiOiAiXCJcIiIsCiAgICAgICAgInVybCI6ICJodHRwOi8vbG9jYWxob3N0OjgwODEvYXJ0aWZhY3RvcnkvSW50ZXJuZS1Db21wb3Nlci1sb2NhbDpjbGFtYXYtcGhwLWNsYW1kc2Nhbi0xLjAuMS1jNTVhZGUoMSkuemlwIiwKICAgICAgICAicmVmZXJlbmNlIjogIlwiXCIiLAogICAgICAgICJzaGFzdW0iOiAiYWUyMzIzMTg5MDAxMzlhMTc0YzIwMzczNTM3NzcyYWI3MGIzNjViNyIKICAgIH0sCiAgICAicmVxdWlyZSI6IHsKICAgICAgICAicGhwIjogIj49NS4zIgogICAgfSwKICAgICJyZXF1aXJlX2RldiI6IHsKICAgICAgICAicGhwdW5pdC9waHB1bml0IjogIjQuMC4qIgogICAgfSwKICAgICJ0aW1lIjogIkludGVybmUtQ29tcG9zZXItbG9jYWwiLAogICAgInR5cGUiOiAibGlicmFyeSIsCiAgICAiaW5zdGFsbGF0aW9uX3NvdXJjZSI6ICJcIlwiIiwKICAgICJhdXRvbG9hZCI6IFsKICAgICAgICB7CiAgICAgICAgICAgICJwc3ItMCI6IHsKICAgICAgICAgICAgICAgICJDbGFtYXYiOiAic3JjLyIKICAgICAgICAgICAgfQogICAgICAgIH0KICAgIF0sCiAgICAibGljZW5zZSI6IFsKICAgICAgICAiTUlUIgogICAgXSwKICAgICJkZXNjcmlwdGlvbiI6ICJDbGFtYXYgUEhQIENsaWVudCB1c2luZyB0aGUgJydjbGFtZHNjYW4nJyBjb21tYW5kIGxpbmUgdG9vbCIsCiAgICAiaG9tZXBhZ2UiOiAiaHR0cDovL3d3dy5jbGFtYXYubmV0LyIsCiAgICAia2V5d29yZHMiOiBbCiAgICAgICAgImNsYW1hdiIsCiAgICAgICAgImFudGl2aXJ1cyIKICAgIF0KfQ"
    propertiestwig = "ewogICAgIm5hbWUiOiAidHdpZy90d2lnIiwKICAgICJ2ZXJzaW9uIjogIjEuMi4zIiwKICAgICJ2ZXJzaW9uX05vcm1hbGl6ZWQiOiAiMS4yLjMuMCIsCiAgICAic291cmNlIjogewogICAgICAgICJ0eXBlIjogIlwiXCIiLAogICAgICAgICJ1cmwiOiAiXCJcIiIsCiAgICAgICAgInJlZmVyZW5jZSI6ICJcIlwiIgogICAgfSwKICAgICJkaXN0IjogewogICAgICAgICJ0eXBlIjogIlwiXCIiLAogICAgICAgICJ1cmwiOiAiaHR0cDovL2xvY2FsaG9zdDo4MDgxL2FydGlmYWN0b3J5L0ludGVybmUtQ29tcG9zZXItbG9jYWw6VHdpZ1Rlc3QtMS4yLjMtZjQ1Njg3LnppcCIsCiAgICAgICAgInJlZmVyZW5jZSI6ICJcIlwiIiwKICAgICAgICAic2hhc3VtIjogIjg1Yzk0YWI0NTRjM2QxOGM0MGVkNTcxZTY5Mjc2ZDU2MjE1YzBmOGIiCiAgICB9LAogICAgInJlcXVpcmUiOiB7CiAgICAgICAgInBocCI6ICI"
    properties100="eyJuYW1lIjoiY2xhbWF2XC9waHAtY2xhbWRzY2FuIiwidmVyc2lvbiI6IjEuMC4wIiwidmVyc2lvbl9ub3JtYWxpemVkIjoiMS4wLjAuMCIsInNvdXJjZSI6eyJ0eXBlIjoiZ2l0IiwidXJsIjoiaHR0cHM6XC9cL2dpdC5ub3ZpYS1zeXN0ZW1zLmZyXC9yXC9DUklGXC83RU5BTjAxMDQwVE1BLURzaUZvcmdlXC9waHAtY2xhbWRzY2FuLmdpdCIsInJlZmVyZW5jZSI6ImJjNWY4ZDNkNWFmZWEyMzU2NDQxYjc4Y2NlZDMzYWQ4M2VjZjI4NzgifSwiZGlzdCI6eyJ0eXBlIjoiemlwIiwidXJsIjoiaHR0cHM6XC9cL3N0b3JlLm5vdmlhLXN5c3RlbXMuZnJcL0ludGVybmUtQ29tcG9zZXItbG9jYWxcL2Rpc3RcL2NsYW1hdi1waHAtY2xhbWRzY2FuLTEuMC4wLWY3OTE4My56aXAiLCJyZWZlcmVuY2UiOiJiYzVmOGQzZDVhZmVhMjM1NjQ0MWI3OGNjZWQzM2FkODNlY2YyODc4Iiwic2hhc3VtIjoiM2QyYzRmMTc1OGIyMWU3MmVkOWQ4YWNiNWU5OThlZmVhNGFiMjU0NSJ9LCJyZXF1aXJlIjp7InBocCI6Ij49NS4zIn0sInJlcXVpcmUtZGV2Ijp7InBocHVuaXRcL3BocHVuaXQiOiI0LjAuKiJ9LCJ0aW1lIjoiMjAxNC0xMS0xMyAxMDo0NTo0MiIsInR5cGUiOiJsaWJyYXJ5IiwiaW5zdGFsbGF0aW9uLXNvdXJjZSI6InNvdXJjZSIsImF1dG9sb2FkIjp7InBzci0wIjp7IkNsYW1hdiI6InNyY1wvIn19LCJsaWNlbnNlIjpbIk1JVCJdLCJkZXNjcmlwdGlvbiI6IkNsYW1hdiBQSFAgQ2xpZW50IHVzaW5nIHRoZSAnJ2NsYW1kc2NhbicnIGNvbW1hbmQgbGluZSB0b29sIiwiaG9tZXBhZ2UiOiJodHRwOlwvXC93d3cuY2xhbWF2Lm5ldFwvIiwia2V5d29yZHMiOlsiYW50aXZpcnVzIiwiY2xhbWF2Il19DQo"
    //def properties = repositories.getProperty(item.repoPath, "json")
    return properties100
}


/**
*remove artifact if is on include
*@param item
**/
private def removeInclude()
{

    //load properties artifact
    def property = loadProperties()
    propertyDecoded = new String(property.decodeBase64())

    //change propertyDecoded to objectProperty
    def jsonSlurper = new JsonSlurper()
    def objectProperty = jsonSlurper.parseText(propertyDecoded)

    //load includeJson
    def include =  new File('C:/Users/jeremy.desmars/testPlugin/test2.json')
    includeString= ""
    include.eachLine
    {
        line->  
        includeString= includeString + line 
    }

    //change includeString to object
    def jsonSlurperInclude = new JsonSlurper()
    def objectInclude = jsonSlurperInclude.parseText(includeString)

    version=objectProperty.version
    name = objectProperty.name

    //remove artifact
    objectInclude.packages.remove("${name}")

    def includeJson = new JsonBuilder(objectInclude) 
    
    //Create new include.json
    new File('C:/Users/jeremy.desmars/testPlugin/test2.json').delete()
    new File('C:/Users/jeremy.desmars/testPlugin/test2.json') << includeJson.toPrettyString()
}