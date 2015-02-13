import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import java.nio.file.Files



def zipFile = new java.util.zip.ZipFile(new File("C:\\Users\\jeremy.desmars\\Downloads\\clamav-php-clamdscan-1.0.0-f79183.zip")  )
def searchstr = "composer.json" 
zipFile.entries().each 
{
    entry->  
                if (entry.name == searchstr)
                {
                     fichier =  zipFile.getInputStream(entry).text
                     println fichier                 
                }  
             
}