package util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DcbUtil {

    private static final String PERIOD_SEPARATOR = ".";
    private static final String PERIOD_SEPARATOR_REGEX = "\\.";

    private static final String EMPTY_STRING = "";
    private static final String PUBLIC_KEY_STRING = "30820122300d06092a864886f70d01010105000382010f003082010a028201010092cc908df1ebe71f1cd615795d4e2ad3da9471eb553d94c8a5b2a03e7f8992624ec79eb4db883447fc0ce5a288131a40551904b50b046b8b67909f3d08c50839be3c45e186fd2ea28c1da2ca765a274d65eb49ba925ad8f27fec5006b01fbd898f456098f525359494fe7516b40faad5f12bf982e8ef15a5d915ab33f708c9ad7704a9a110f7e6ff7091b74745ecc031e45d42aa6811c35465dd25742a2d95896715f032daf1ab9729c1770cc03b1b29c4345073bcc28076f4bd8cf3409cf98b7dab659c451d9839f5349860302633b84fe4e82c3677fbbb46bf8f8df025529a9c60591e6c8311e959bbc55d456e851343f992db08ecfe9392ba1922e2d894610203010001";
    private static final String PRIVATE_KEY_STRING = "308204be020100300d06092a864886f70d0101010500048204a8308204a4020100028201010092cc908df1ebe71f1cd615795d4e2ad3da9471eb553d94c8a5b2a03e7f8992624ec79eb4db883447fc0ce5a288131a40551904b50b046b8b67909f3d08c50839be3c45e186fd2ea28c1da2ca765a274d65eb49ba925ad8f27fec5006b01fbd898f456098f525359494fe7516b40faad5f12bf982e8ef15a5d915ab33f708c9ad7704a9a110f7e6ff7091b74745ecc031e45d42aa6811c35465dd25742a2d95896715f032daf1ab9729c1770cc03b1b29c4345073bcc28076f4bd8cf3409cf98b7dab659c451d9839f5349860302633b84fe4e82c3677fbbb46bf8f8df025529a9c60591e6c8311e959bbc55d456e851343f992db08ecfe9392ba1922e2d89461020301000102820100701c80ad973be588125ac18dad9f9bffade83ed44e863851c09219e5813b8cab3da52064d9603d2558ae74d5dafc53c91bf47e6ed19a2f9408a51fa7da41b7d5799ec873c3c72a4d839ac28fc33178e0985cc8330fc107fcb38bc356483e055fc02668ee881241c856bdbaa87b1d9807ca271e68470308d25f673817f8ab22720adb236e2339231cbf1b3d349397beefa201c20a0ac905d0f92bb337f1974c09af1cf47df6fafe572ff96a49e5b0ee96faef147bbb0126ab9d656a82223a99750acec7ab18350a793d343476a12c936a7177d2ed57d3e949a11f583a4d38969c22474b1a65a8766552ebf767a1f1fd03b40e9f9b1dca32ffbd60aabcb6c6024102818100c40219f8e5f4b3afc8349254337059169cc519d4e628c6bdb9db6f5d2857bbfe3bdd90c7929551fe5b78d95c9a3fd9edc2da3df94a4450dbddc39951cbcd38e4a6426a82ee8e6e6da0cdea280c20dfc52755fb575dbaf35b2f2d8b9af157a07b4b769bc81295f121d8b4c125641135f85696a2166b15cbe7bd4cb0163478385902818100bfbac373f157880098ec21b13c5a9a0077e03c9e77daa6e7de8061cb8242de3fd3ca3a57a8f182c12f76218e49e20c3859c403362ecf41c6546cdb13b0f288e07934f8a79dde6c6feecb2d70ad1f1bc8c875dc548dc50d56cbe1c75740c4f888aa734390ee9e6d35ea4b13c4ef1c1f0daa0f15d9fe8ca01f6e54ece041b43b4902818100a12681280c1845eb8e14fb01d350d94e28123d0cb7366b3feda0a64cd7305ad5c96e7eec366e21fd984321a26b07782b6cfa75dd91dbdd0707ab1b510ed869a043b9734cb6c9cfacbe7acdbba34d1bed424c04db6daa990e2d19ab1ff6b0eeb6aff2925dca3a11089cdcefb7c830064969176ba836deb2c892f08c0d959209f102818100b56947aa9abed1a68f0b4e4ddee77333e2393fe249518fff70de54aa349484aa2c00faf71a9979b8c4e9a7927481f842115d9014627224b03e0dc9b47606e6504f1f39ef8f46779fe97841848daa13a60556cfa509875bb9b6abe8ebbc13237e5c9937631904ba1cfb95121d92892f30c4aa0569009b9a9e62cce45cc48e7e510281807cc74bb203741def795ce1d2551e32404fe033836c5d2980bf7404da65cdaeaa1077eea2c9ea73024c45bb828a76334e284fd1b07e1c7d962225fda7ed16e6e48439010e1b4cdc8d250fdaa67b64d7eb059457cc6c0283e08d0bcab657bce8cce72bbc36d40ec6977adc02858448d6cf5911c4fd7d40d9d37bc2004b7e74deb6";

    public static String encrypt(String jsonString) {
        JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A128CBC_HS256);
        Payload payload = new Payload(jsonString);

        JWEObject jweObject = new JWEObject(header, payload);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(DatatypeConverter.parseHexBinary(PUBLIC_KEY_STRING));
        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PublicKey publicKey = null;
        try {
            publicKey = kf.generatePublic(keySpec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JWEEncrypter encrypter = new RSAEncrypter((RSAPublicKey) publicKey);

        try {
            jweObject.encrypt(encrypter);
        } catch (JOSEException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String jweString = jweObject.serialize();

        String[] components = deserialize(jweString);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("protected", components[0]);
        map.put("ciphertext", components[3]);
        map.put("iv", components[2]);
        map.put("tag", components[4]);

        Map<String, String> encryptedKey = new HashMap<String, String>();
        encryptedKey.put("encrypted_key", components[1]);

        map.put("recipients", Arrays.asList(encryptedKey));
        JSONObject resultObject = new JSONObject(map);
        return resultObject.toString();
    }

    public static String decrypt(String encMessage) {
        @SuppressWarnings("deprecation")
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(encMessage);
        } catch (net.minidev.json.parser.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List recipients = (List) jsonObject.get("recipients");
        Map keyMap = (Map) recipients.get(0);
        String serializedJwe = serialize((String) jsonObject.get("protected"), (String) keyMap.get("encrypted_key"),
                (String) jsonObject.get("iv"), (String) jsonObject.get("ciphertext"), (String) jsonObject.get("tag"));

        JWEObject jweObject = null;
        try {
            jweObject = JWEObject.parse(serializedJwe);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(DatatypeConverter.parseHexBinary(PRIVATE_KEY_STRING));
        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PrivateKey privateKey = null;
        try {
            privateKey = kf.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JWEDecrypter decrypter = new RSADecrypter(privateKey);

        try {
            jweObject.decrypt(decrypter);
        } catch (JOSEException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String str = jweObject.getPayload().toString();

        return jweObject.getPayload().toString();
    }

    private static String serialize(String... parts) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = (parts[i] == null) ? EMPTY_STRING : parts[i];
            sb.append(part);
            if (i != parts.length - 1) {
                sb.append(PERIOD_SEPARATOR);
            }
        }
        return sb.toString();
    }

    private static String[] deserialize(String compactSerialization) {
        String[] parts = compactSerialization.split(PERIOD_SEPARATOR_REGEX);

        if (compactSerialization.endsWith(PERIOD_SEPARATOR)) {
            String[] tempParts = new String[parts.length + 1];
            System.arraycopy(parts, 0, tempParts, 0, parts.length);
            tempParts[parts.length] = EMPTY_STRING;
            parts = tempParts;
        }

        return parts;
    }

    public static void main(String[] args) {
        String encryptRequest = encrypt("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\" xmlns:net=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\" xmlns:arr=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" xmlns:net1=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:ProcessRequest>\n" +
                "         <!--Optional:-->\n" +
                "         <tem:request>\n" +
                "            <!--Zero or more repetitions:-->\n" +
                "            <net:Request>\n" +
                "\n" +
                "               <NS2:Additional_Name1 xmlns:NS2=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">MURUGAN</NS2:Additional_Name1>\n" +
                "               <NS3:Additional_Name_Type1 xmlns:NS3=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">2</NS3:Additional_Name_Type1>\n" +
                "               <NS4:AddrLine1 xmlns:NS4=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                "ratnawat colony  shamgarh  Shamgarh</NS4:AddrLine1>\n" +
                "               <NS5:AddrType xmlns:NS5=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">1</NS5:AddrType>\n" +
                "               <NS6:BureauCategory xmlns:NS6=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                "                  <NS6:CategoryId>4</NS6:CategoryId>\n" +
                "                  <NS6:CategoryName>CCR</NS6:CategoryName>\n" +
                "                  <NS6:IsEnabled>true</NS6:IsEnabled>\n" +
                "               </NS6:BureauCategory>\n" +
                "                <NS7:Customfields xmlns:NS7=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                "                  <NS8:CustomFields xmlns:NS8=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS8:Key>WORKFLOW_ID</NS8:Key>\n" +
                "                     <NS8:Value>DCB_R2</NS8:Value>\n" +
                "                  </NS8:CustomFields>\n" +
                "                  <NS9:CustomFields xmlns:NS9=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS9:Key>BC_NAME</NS9:Key>\n" +
                "                     <NS9:Value>Nocpl</NS9:Value>\n" +
                "                  </NS9:CustomFields>\n" +
                "                  <NS10:CustomFields xmlns:NS10=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS10:Key>BC_BRANCH_ID</NS10:Key>\n" +
                "                     <NS10:Value>5006</NS10:Value>\n" +
                "                  </NS10:CustomFields>\n" +
                "                  <NS11:CustomFields xmlns:NS11=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS11:Key>BC_BRANCH_NAME</NS11:Key>\n" +
                "                     <NS11:Value>INDERGARH</NS11:Value>\n" +
                "                  </NS11:CustomFields>\n" +
                "                  <NS12:CustomFields xmlns:NS12=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS12:Key>REGION_NAME</NS12:Key>\n" +
                "                     <NS12:Value>HR</NS12:Value>\n" +
                "                  </NS12:CustomFields>\n" +
                "                  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>PRODUCT_ID</NS13:Key>\n" +
                "                     <NS13:Value>JLGM</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>APPLICANT_TYPE</NS13:Key>\n" +
                "                     <NS13:Value>Applicant</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>FAMILY_HOUSEHOLD_MONTHLY_INCOME</NS13:Key>\n" +
                "                     <NS13:Value>16666</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>TENURE</NS13:Key>\n" +
                "                     <NS13:Value>18</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>INTEREST_RATE</NS13:Key>\n" +
                "                     <NS13:Value>8</NS13:Value>\n" +
                "                  </NS13:CustomFields> \n" +
                "\t\t\t\t\n" +
                "               </NS7:Customfields>\n" +
                "               <NS14:DOB xmlns:NS14=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">1970-08-20</NS14:DOB>\n" +
                "               <NS15:First_Name xmlns:NS15=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">Arthur Rightus</NS15:First_Name>\n" +
                "               <NS16:Gender xmlns:NS16=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">2</NS16:Gender>\n" +
                "               <NS17:Inquiry_Purpose xmlns:NS17=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">37</NS17:Inquiry_Purpose>\n" +
                "               <NS18:Last_Name xmlns:NS18=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">.</NS18:Last_Name>\n" +
                "               <NS19:LosIndicator xmlns:NS19=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">3</NS19:LosIndicator>\n" +
                "               <NS20:Los_Appl_No xmlns:NS20=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">1659802403</NS20:Los_Appl_No>\n" +
                "               <NS21:Marital_Status xmlns:NS21=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">3</NS21:Marital_Status>\n" +
                "               <NS22:Middle_Name xmlns:NS22=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                "               <NS23:National_Id_Card xmlns:NS23=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"></NS23:National_Id_Card>\n" +
                "               <NS24:OverrideCoolingPeriod xmlns:NS24=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">true</NS24:OverrideCoolingPeriod>\n" +
                "               <NS25:Pan_Id xmlns:NS25=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">ABKPB8812P</NS25:Pan_Id>\n" +
                "               <NS26:Passport_Id xmlns:NS26=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                "               <NS27:Postal xmlns:NS27=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">625515</NS27:Postal>\n" +
                "               <NS28:State xmlns:NS28=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">31</NS28:State>\n" +
                "               <NS29:Street xmlns:NS29=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"></NS29:Street>\n" +
                "               <NS30:Transaction_Amount xmlns:NS30=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">100</NS30:Transaction_Amount>\n" +
                "               <NS31:Voter_Id xmlns:NS31=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">TN291881173678</NS31:Voter_Id>\n" +
                "            </net:Request>\n" +
                "         \n" +
                "\t\t   <!--Co-Applicant-->\n" +
                "\t\t   <net:Request>\n" +
                "               <NS2:Additional_Name1 xmlns:NS2=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">MURUGAN</NS2:Additional_Name1>\n" +
                "               <NS3:Additional_Name_Type1 xmlns:NS3=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">2</NS3:Additional_Name_Type1>\n" +
                "               <NS4:AddrLine1 xmlns:NS4=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                "ratnawat colony  shamgarh  Shamgarh</NS4:AddrLine1>\n" +
                "               <NS5:AddrType xmlns:NS5=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">1</NS5:AddrType>\n" +
                "               <NS6:BureauCategory xmlns:NS6=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                "                  <NS6:CategoryId>4</NS6:CategoryId>\n" +
                "                  <NS6:CategoryName>CCR</NS6:CategoryName>\n" +
                "                  <NS6:IsEnabled>true</NS6:IsEnabled>\n" +
                "               </NS6:BureauCategory>\n" +
                "                <NS7:Customfields xmlns:NS7=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                "                  <NS8:CustomFields xmlns:NS8=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS8:Key>WORKFLOW_ID</NS8:Key>\n" +
                "                     <NS8:Value>DCB_R2</NS8:Value>\n" +
                "                  </NS8:CustomFields>\n" +
                "                  <NS9:CustomFields xmlns:NS9=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS9:Key>BC_NAME</NS9:Key>\n" +
                "                     <NS9:Value>BureauOne_SVCL</NS9:Value>\n" +
                "                  </NS9:CustomFields>\n" +
                "                  <NS10:CustomFields xmlns:NS10=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS10:Key>BC_BRANCH_ID</NS10:Key>\n" +
                "                     <NS10:Value>5006</NS10:Value>\n" +
                "                  </NS10:CustomFields>\n" +
                "                  <NS11:CustomFields xmlns:NS11=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS11:Key>BC_BRANCH_NAME</NS11:Key>\n" +
                "                     <NS11:Value>INDERGARH</NS11:Value>\n" +
                "                  </NS11:CustomFields>\n" +
                "                  <NS12:CustomFields xmlns:NS12=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS12:Key>REGION_NAME</NS12:Key>\n" +
                "                     <NS12:Value>HR</NS12:Value>\n" +
                "                  </NS12:CustomFields>\n" +
                "                  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>PRODUCT_ID</NS13:Key>\n" +
                "                     <NS13:Value>JLGM</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>APPLICANT_TYPE</NS13:Key>\n" +
                "                     <NS13:Value>CoApplicant</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>FAMILY_HOUSEHOLD_MONTHLY_INCOME</NS13:Key>\n" +
                "                     <NS13:Value>15000</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>TENURE</NS13:Key>\n" +
                "                     <NS13:Value>18</NS13:Value>\n" +
                "                  </NS13:CustomFields>\n" +
                "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                "                     <NS13:Key>INTEREST_RATE</NS13:Key>\n" +
                "                     <NS13:Value>24</NS13:Value>\n" +
                "                  </NS13:CustomFields>   \n" +
                "\t\t\t\t   \n" +
                "\t\t\t\t   \n" +
                "               </NS7:Customfields>\n" +
                "               <NS14:DOB xmlns:NS14=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">1970-08-20</NS14:DOB>\n" +
                "               <NS15:First_Name xmlns:NS15=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">Arthur Rightus</NS15:First_Name>\n" +
                "               <NS16:Gender xmlns:NS16=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">2</NS16:Gender>\n" +
                "               <NS17:Inquiry_Purpose xmlns:NS17=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">37</NS17:Inquiry_Purpose>\n" +
                "               <NS18:Last_Name xmlns:NS18=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">.</NS18:Last_Name>\n" +
                "               <NS19:LosIndicator xmlns:NS19=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">3</NS19:LosIndicator>\n" +
                "               <NS20:Los_Appl_No xmlns:NS20=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">1659802403</NS20:Los_Appl_No>\n" +
                "               <NS21:Marital_Status xmlns:NS21=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">3</NS21:Marital_Status>\n" +
                "               <NS22:Middle_Name xmlns:NS22=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                "               <NS23:National_Id_Card xmlns:NS23=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"></NS23:National_Id_Card>\n" +
                "               <NS24:OverrideCoolingPeriod xmlns:NS24=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">true</NS24:OverrideCoolingPeriod>\n" +
                "               <NS25:Pan_Id xmlns:NS25=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">ABKPB8812P</NS25:Pan_Id>\n" +
                "               <NS26:Passport_Id xmlns:NS26=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                "               <NS27:Postal xmlns:NS27=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">625515</NS27:Postal>\n" +
                "               <NS28:State xmlns:NS28=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">31</NS28:State>\n" +
                "               <NS29:Street xmlns:NS29=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"></NS29:Street>\n" +
                "               <NS30:Transaction_Amount xmlns:NS30=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">100</NS30:Transaction_Amount>\n" +
                "               <NS31:Voter_Id xmlns:NS31=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">TN291881173678</NS31:Voter_Id>\n" +
                "            </net:Request>\n" +
                "         \n" +
                "\t\t \n" +
                "\t\t \n" +
                "\t\t </tem:request>\n" +
                "      </tem:ProcessRequest>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        System.out.println("JWE: " + encryptRequest);
        String decryptedRequest = decrypt("");
        log.info(decryptedRequest);

        String clientRejectionMessage = "Client";
        String coApplicantRejectionMessage = "Co-Applicant";
        String applicationRejectionMessage = "";
        if (!clientRejectionMessage.isEmpty()) {
            applicationRejectionMessage = applicationRejectionMessage.concat("[Client] DCB credit bureau failed. Reason: " + clientRejectionMessage + ". \n");
        }
        if (!coApplicantRejectionMessage.isEmpty()) {
            applicationRejectionMessage = applicationRejectionMessage.concat("[Borrower] DCB credit bureau failed. Reason: " + coApplicantRejectionMessage);
        }
        System.out.println(applicationRejectionMessage);
    }
}

