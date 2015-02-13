import java.net.*;
import java.io.*;



def file = new File('C:/artifactoryPro/data/tmp/work/')
url = new URL("http://localhost:8081/artifactory/Interne-Composer-local/include/all\$0c7043b510e5bc1a92b77c9af7c79a14249ddf6b.json ")
getFile(url)


    public void getFile(URL u) throws IOException
    {
        uc = u.openConnection();
        FileType = uc.getContentType();
        int FileLenght = uc.getContentLength();
        if (FileLenght == -1) {
            throw new IOException("Fichier non valide.");
        }
        inS = uc.getInputStream();
        FileName = u.getFile();
        FileName = FileName.substring(FileName.lastIndexOf('/') + 1);
        WritenFile = new FileOutputStream('C:/artifactoryPro/data/tmp/work/'+FileName);
        byte[]buff = new byte[1024];
        int l = inS.read(buff);
        while(l>0)
        {
        WritenFile.write(buff, 0, l);
        l = inS.read(buff);
        }
        WritenFile.flush();
        WritenFile.close();

    }

