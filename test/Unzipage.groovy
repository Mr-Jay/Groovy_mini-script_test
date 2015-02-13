
def ant = new AntBuilder()   // create an antbuilder
ant.unzip(  src:"C:\\Users\\jeremy.desmars\\Downloads\\clamav-php-clamdscan-1.0.0-f79183.zip",
            dest:"C:\\Users\\jeremy.desmars\\temporaire",
            overwrite:"true" )