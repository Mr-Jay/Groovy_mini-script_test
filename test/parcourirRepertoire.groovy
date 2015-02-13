
 def include = new File('C:/artifactoryPro/data/tmp/work/include.json')  
 def dir = new File('C:/artifactoryPro/data/tmp/work/')
    dir.eachFile{file->
      if (file == 'C:/artifactoryPro/data/tmp/work/include_Interne-Composer-local.json')
      {
         include = new File('C:/artifactoryPro/data/tmp/work/include_Interne-Composer-local.json')      
      } 
    }
    
println include