import java.nio.file.Files
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
 

    //On recupere l'ancien include
    def include=  new File('C:/Users/jeremy.desmars/testPlugin/include.json')
    includeString= ""
    include.eachLine
    {
        line->  
        includeString= includeString + line 
    }

//On decode
def property="eyJuYW1lIjoiY2xhbWF2XC9waHAtY2xhbWRzY2FuIiwidmVyc2lvbiI6IjEuMC4wIiwidmVyc2lvbl9ub3JtYWxpemVkIjoiMS4wLjAuMCIsInNvdXJjZSI6eyJ0eXBlIjoiZ2l0IiwidXJsIjoiaHR0cHM6XC9cL2dpdC5ub3ZpYS1zeXN0ZW1zLmZyXC9yXC9DUklGXC83RU5BTjAxMDQwVE1BLURzaUZvcmdlXC9waHAtY2xhbWRzY2FuLmdpdCIsInJlZmVyZW5jZSI6ImJjNWY4ZDNkNWFmZWEyMzU2NDQxYjc4Y2NlZDMzYWQ4M2VjZjI4NzgifSwiZGlzdCI6eyJ0eXBlIjoiemlwIiwidXJsIjoiaHR0cHM6XC9cL3N0b3JlLm5vdmlhLXN5c3RlbXMuZnJcL0ludGVybmUtQ29tcG9zZXItbG9jYWxcL2Rpc3RcL2NsYW1hdi1waHAtY2xhbWRzY2FuLTEuMC4wLWY3OTE4My56aXAiLCJyZWZlcmVuY2UiOiJiYzVmOGQzZDVhZmVhMjM1NjQ0MWI3OGNjZWQzM2FkODNlY2YyODc4Iiwic2hhc3VtIjoiM2QyYzRmMTc1OGIyMWU3MmVkOWQ4YWNiNWU5OThlZmVhNGFiMjU0NSJ9LCJyZXF1aXJlIjp7InBocCI6Ij49NS4zIn0sInJlcXVpcmUtZGV2Ijp7InBocHVuaXRcL3BocHVuaXQiOiI0LjAuKiJ9LCJ0aW1lIjoiMjAxNC0xMS0xMyAxMDo0NTo0MiIsInR5cGUiOiJsaWJyYXJ5IiwiaW5zdGFsbGF0aW9uLXNvdXJjZSI6InNvdXJjZSIsImF1dG9sb2FkIjp7InBzci0wIjp7IkNsYW1hdiI6InNyY1wvIn19LCJsaWNlbnNlIjpbIk1JVCJdLCJkZXNjcmlwdGlvbiI6IkNsYW1hdiBQSFAgQ2xpZW50IHVzaW5nIHRoZSAnJ2NsYW1kc2NhbicnIGNvbW1hbmQgbGluZSB0b29sIiwiaG9tZXBhZ2UiOiJodHRwOlwvXC93d3cuY2xhbWF2Lm5ldFwvIiwia2V5d29yZHMiOlsiYW50aXZpcnVzIiwiY2xhbWF2Il19DQo"
def decoded = new String(property.decodeBase64())
//def decoded= new File ('C:/Users/jeremy.desmars/testPlugin/test.json')

//on change ce qui était encodé en objet
def jsonSlurper = new JsonSlurper()
def objectDecoded = jsonSlurper.parseText(decoded)
//objectDecoded.name = "twig"

//on change le contenue de l'ancien include en objet
def jsonSlurperInclude = new JsonSlurper()
def object = jsonSlurperInclude.parseText(includeString)

//mettre valeur en brut pour test
version=objectDecoded.version
name = objectDecoded.name
println  objectDecoded

//on inclus l'objet objectDecoded dans l'objet object à l'emplacement souhaité
if (object.packages.get("${name}") ==null)
{
    object.packages.put("${name}", objectDecoded)
}
else
{
    object.packages.get("${name}").put("${version}", objectDecoded)
}

def includeJson = new JsonBuilder(object) 
                
new File('C:/Users/jeremy.desmars/testPlugin/test2.json').delete()
new File('C:/Users/jeremy.desmars/testPlugin/test2.json') << includeJson.toPrettyString()