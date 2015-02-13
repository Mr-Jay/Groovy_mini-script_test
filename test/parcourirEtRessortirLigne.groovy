def f = new File("C:\\Users\\jeremy.desmars\\test.txt")
f.eachLine
{
    recherche->
    def texte = recherche
    def inverse = ""
    
    for (i=1 ; i<=texte.size(); i++)
    {
        
       inverse=inverse + texte.substring(i-1, i) 
       if (inverse == "name")
       {
           println recherche
       }
    }
}
