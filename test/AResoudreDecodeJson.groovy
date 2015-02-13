//def file =new File('C:/Users/jeremy.desmars/test2.json') 
//def decoded = new String(file.JSONdecode())
//println decoded
import com.fasterxml.jackson.databind.ObjectMapper;          
//On définit le fichier à lire
def f = new File("C:\\Users\\jeremy.desmars\\temporaire\\clamav-php-clamdscan-1.0.1-c55ade\\clamav\\composer.json")
def fichier = ""
f.eachLine
{
    test->
    fichier = fichier + test
}

//Conversion d’un String json en un objet java :

mapper = new ObjectMapper()
obj = mapper.readTree(fichier)

//Accès à une propriété avec get()

String Json = obj.get("license").asText()
println obj
println obj.get("autoload")