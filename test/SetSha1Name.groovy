import java.security.MessageDigest

private def generateHash(file, hashType, leftChar) 
{
    digest = MessageDigest.getInstance(hashType)
    file.withInputStream()
    {is->
        buffer = new byte[8192]
        read = 0
        while((read = is.read(buffer)) > 0) 
        {
                  digest.update(buffer, 0, read);
        }
    }
    hash = digest.digest()
    bigInt = new BigInteger(1, hash)
    return bigInt.toString(16).padLeft(leftChar, '0')
}


file = new File("C:/artifactoryPro/data/tmp/work/test.json")
nameFile = generateHash(file, 'SHA1', 40)
println nameFile
file = new File("C:/artifactoryPro/data/tmp/work/"+nameFile+".json")
println file 