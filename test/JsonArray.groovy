import groovy.json.JsonBuilder


def autoload = [psr0=[name = "clamav"]]
def require_dev = [required_dev = "3.2"]
def require = [required= "5.3"]
def dist = [type = "zip", url = "http:localhost:8081", reference = "56874257654", shasum = "dfhfg54f5xw4gw"]
def source = [type = "zip", url =  "", reference = "156985432156513"]
def version = [name = "clamav", version = "1.0.0", versionnormalized = "1.0.0.0", source, dist,require,require_dev, time = "23/01/2015", type = "lib", installation_source = "", autoload[]]
def fileName = [version, licence = "MIT"]
def packages = [fileName,  description = "Software antivirus", homepage = "http:clamav.net", keyword =  "antivirus, clamav"]
def JsonMap = [packages]


def builder = new groovy.json.JsonBuilder()
def json = new JsonBuilder(JsonMap)
println json.toPrettyString()