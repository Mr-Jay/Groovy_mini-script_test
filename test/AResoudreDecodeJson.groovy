//def file =new File('C:/Users/jeremy.desmars/test2.json') 
//def decoded = new String(file.JSONdecode())
//println decoded
import com.fasterxml.jackson.databind.ObjectMapper;          
//On d�finit le fichier � lire
def f = new File("C:\\Users\\jeremy.desmars\\temporaire\\clamav-php-clamdscan-1.0.1-c55ade\\clamav\\composer.json")
def fichier = ""
f.eachLine
{
    test->
    fichier = fichier + test
}

//Conversion d�un String json en un objet java :

mapper = new ObjectMapper()
obj = mapper.readTree(fichier)

//Acc�s � une propri�t� avec get()

String Json = obj.get("license").asText()
println obj
println obj.get("autoload")