
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder
import java.nio.file.Files

def firstName = "Jeremy"
def Name = "Desmars"
def Age = "21"
def City = "Nantes"

def property = '{"firstName":"Jeremy","Name":"Desmars","Age":"21","City":"Nantes"}'

def jsonSlurperProperty = new JsonSlurper()
def objectProperty = jsonSlurperProperty.parseText(property) 
    
def builder = new groovy.json.JsonBuilder()
def json = new JsonBuilder(objectProperty)



println json.toPrettyString()