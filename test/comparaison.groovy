test= "Interne-Composer-local:include/all\$96a61fd7945485a1a3668a48e9fe6f3c3acb0e41.json"
//p1 = test.equals("tes/?")
//println p1
zip="clamav-php-clamdscan-1.0.0-f79183.zip"
locationData = "Liverpool, England: 53d 25m 0s N 3d 0m 0s"
myRegularExpression = /([a-zA-Z]+), ([a-zA-Z]+): ([0-9]+). ([0-9]+). ([0-9]+). ([A-Z]) ([0-9]+). ([0-9]+). ([0-9]+)./
expression=  /(.*):([a-zA-Z]+)(.)([a-zA-Z]+)(\$)([a-zA-Z-0-9]+)(.)([a-zA-Z]+)/
matcher = ( test =~ expression )
if (matcher.matches()) {
    println matcher[0][6]
    println "toto"
   // println(matcher.getCount()+ " occurrence of the regular expression was found in the string.");
    //println(matcher[0][1] + " is in the " + matcher[0][6] + " hemisphere. (According to: " + matcher[0][0] + ")")
}