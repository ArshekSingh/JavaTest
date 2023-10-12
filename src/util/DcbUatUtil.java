package util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

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
public class DcbUatUtil {
    private static final String PERIOD_SEPARATOR = ".";
    private static final String PERIOD_SEPARATOR_REGEX = "\\.";

    private static final String EMPTY_STRING = "";
    private static final String PUBLIC_KEY_STRING = "30820122300D06092A864886F70D01010105000382010F003082010A0282010100A266665E8EBD10B6E3014B2E6AEF0E6CA05B100F4FB3D129564DBF4C8415C257EC4D327E319ED3B42244B6CE3A3132B152FC0BC7FAC9127D004323945CD62506CD7DA4942E446F781D652369B79A7A694BEDB38909CE684A85F727ED0DEBFAF524D485145A3266D28EE4C04038481C4DEC528B345A6F143F4D82DE5CF5CCD6230665B2BC213909D369043AC638453D33AFF4B4570D7819EF242F8A326B8DF927F9029ED465E2007E38CE7506DC0A48F3A3C0D23F2B67B8180E325281268B142B69650A531AF5DB6C69649FE0E65F32399148471FFA0CA80E555F1B73E964D4C6E38CB428778370B5F5B84975C42556DA647EE41266D64162EE188759DC5724DF0203010001";
    private static final String PRIVATE_KEY_STRING = "308204BF020100300D06092A864886F70D0101010500048204A9308204A50201000282010100A266665E8EBD10B6E3014B2E6AEF0E6CA05B100F4FB3D129564DBF4C8415C257EC4D327E319ED3B42244B6CE3A3132B152FC0BC7FAC9127D004323945CD62506CD7DA4942E446F781D652369B79A7A694BEDB38909CE684A85F727ED0DEBFAF524D485145A3266D28EE4C04038481C4DEC528B345A6F143F4D82DE5CF5CCD6230665B2BC213909D369043AC638453D33AFF4B4570D7819EF242F8A326B8DF927F9029ED465E2007E38CE7506DC0A48F3A3C0D23F2B67B8180E325281268B142B69650A531AF5DB6C69649FE0E65F32399148471FFA0CA80E555F1B73E964D4C6E38CB428778370B5F5B84975C42556DA647EE41266D64162EE188759DC5724DF02030100010282010100978957A28A2CAF89083FAFC88B6DB016FA769E80BB0D7E71C51F5D0EE7FD69D0232651A46166E402A52B5B2D0A2DD83090CFB1AA943FC5A9B071F5A43E1070926D72FCFC6C43DD83C662166E522DAA65D0BCE8174A7838E6B924CC33AE16F1A088126A6F784EA9957652CB75E14EA062B1CEC02844EE7B852A2DA0923BA63C7502910E8D94EBAC3AFCF0316CD8275FE7EE3F271263C34C75D5F5C651860804C1025F4BC6A53A39119F22653AECE049940194B62CA17A56CB26A59252DB7A2604FBF7D44D922073818FBB67BE50BAB20CA976273A0B5A769B4A79FE9B9544586EB7770AF5FA43C0D134159D59BF04C9DEB1D5793EB6E8CE3A26FBB2239A02E6D102818100FDE33BEF29AC39C306F4D2F5D1AFAF5F502383E482A6B95C5FD21FE87F88753EC0C669CA0FBC696FA90FC03C921F7502D48A45154020098354754009B3B398601A85A86C108A0871882F4F00C3F5C26730F90732C6908214B805696B20C946C9CD56DF58E9FC80EAAA724189CB0A6F7EA0BB259EE1C62A62A8C68EB631BC20D702818100A3C04D6BDEAA847DD6E7BD536B2CF1BC9668C896D553577C05844653EF16786A606ADDBECF0647B30767614EA5F352F30B50DBD135D634755D5DD7042A42D0F321B54E3AD26AF69BDF5762B5C26F2F772B3AA35824E2AE7F448E23DC04462E9E2C4904E6E825B952BC46F55DEEDAE03E79969860747FFECF1E95F254563F33390281810086F50485F43BF594631D3F0AA684E20F23554DA5E98160A4E6D696598B7969018B8DACEC59030974EE844DE6A4B20E0C0C18D7F21CDD2AD161D2D5D09272943BF9BD34B304F3AADB56CDF70BEC1EB6510E2A42164E90C064ED110B6183D31A4AE959156E792ADD0860C44D6C46BEEC577E6F1CE761126AB3790DDB0CA451618F0281810091C94464741225F10F22B4790F232D78F4040809D994E07F673DF38B08825DE47F40C578FA41A215EED2885C87FC90EE1735FEEE4CDB0F4B2A3BC09151BAB8E2AD31089BEFF810E4873BFA82CDBF4567F851B5596545778B911267349B8D8C358041E61E59B2344347A279130EF348CFF65A300137D7389957A6B1DF8A05AC6902818009E5428668ECB8030C1D3DD5B96BD6229965FE15415A65E712CBFF4F20E072D5A6C3B4BECBC7839EBD119D6F500F644BAD431FF03B77ED521F49CE40B4B0F4BB765DA72DC455B073B56A70F261F358D96EBA8C9E9E2F7FF60C71D6CD82454001E848528F75952D5789D08E53B02485576CCF8D380E102853CD24086B2E41EC54";

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
        String encryptRequest = encrypt("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:net=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.Security\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "    <soapenv:Header/>\n" +
                "    <soapenv:Body>\n" +
                "        <tem:Authenticate>\n" +
                "            <tem:request>\n" +
                "                <net:Credential>\n" +
                "                    <net:LosId>1</net:LosId>\n" +
                "                    <net:Password>welcome@123</net:Password>\n" +
                "                    <net:User>DCBUser</net:User>\n" +
                "                </net:Credential>\n" +
                "                <net:DeviceInfo>\n" +
                "                    <net:DeviceId>?</net:DeviceId>\n" +
                "                    <net:MacAddress>?</net:MacAddress>\n" +
                "                    <net:SerialNumber>?</net:SerialNumber>\n" +
                "                </net:DeviceInfo>\n" +
                "            </tem:request>\n" +
                "        </tem:Authenticate>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>\n");
        System.out.println("JWE: " + encryptRequest);
        String decryptedRequest = decrypt("{\n" +
                "    \"ciphertext\": \"1qptUtnYAT-u9rcK_JZTJaWa5eBFO4kBOLuIb71dTYIwzi54u_06frfXWOx75qPyKrmrJwvufRu7-FzqrZIFbEboihEUeuZGoIavUFry4-Eh7aGnBglpAO4o6LWGFJrva0f3qV3PBLsnE9ThGOjasm7KGeiashmljC73n3pY7AIjOthNfeQPKj3hesT2N4o7Zx4Lld4f8kytgQZ1WrvWth0Lf5W7K2pIXwlrY8tR2Gige4mi5MfTu-yxBvNV-amwss43V84vb7x2EXVT2niiS41VK3fjpdIhqkZQAUjGY3CQieO7EmLYPZpuudWbD-JBWf6CZk2hfbNw9elT2pdkR7mQWyC40vem-O2gA6Q8dvxRnCRnhQfBJUPA3BtqtN3aa1DvxRvF4pocKihA4aKCPu__2Z-RwAbdDX-cBuWV6RbcLxCP6IDmfBEotLtpyX0oQRx53pJDUkDLBFAOriwawC6K1xO1xSEXDXYW3mJMOPRCUHjcFp1QqI4uGtN6mapS9IJYO-PUy58ptVHpNG5YVPazj1DvpxPSgqi6UvAzhcEECVCLEJdx3tP4pAps0zg2P3c7xa8vjc3oMZ3JoXmmtdz8emHzNtdCqfevbGEr-MvpniquGoKRbDIZFEUZp0t8ZhAdXd_kixa2c-6KgkvqMj-S_LDQo_5lxixtLjqgpkpYVkhPUg3muma9Nc80n0b2qEXeQy8lvjH4fMxAxqGswl6h2wrUf-ylPE0D7pEfPzpoUYIERhPGyd9zZ-brgDNsXvfx3Aka0kIV-AXi71VwJuhdc97hppWEWwjZTWGWJrqztIxJtZi_N2dg3-z3CYIsS4-Vm8yUM7eQQo9ZNcwe0iv-YIll7sPQaGg0zj_mTd2pZlYflav1HzOBVGfumRwqYAG_WBDlbaIadzK1Q3z33dxojasexbF1lT7HCSOZTC-_qBjfZzxWlEch7msHO2apYq_ssTdtvhQ-laI3QjzcUL077oVt9s-MhNHarxLPXfo4BTlirFOh4tOQ9m5X1fgQ2XFVWPEpYmKu7UoVAIRR6-7G1qz70kkvrb6Yc4RDu5zuLLkYv6de7CWWainRo5u4Pm5-d6uqeP6YRLCBA4sMriZ0hTZRlYifrnTv8ewi7qhI_uHJau3mPKvmmQTdBy3VQFSeu7sjBKBFUakmlo3IT6FAKDUuBF2QiYJL67w0nEW95i625rOjr3kmIPc5jNMIulh_k3EPgJ6Bq1MfiRGoJpvhKxu8LlLPs29DZdXzBZo81f94vNOqG5QduM8Qj50gUOQmhog61wp4p11T71NPt3z5HLA3CkvXdriv0eSPBMp7lbTmM8iVuyl5_-NPNzM51nJYffWoci71h8k5Fh3Xh5EohJFUl9iF8kd3B4JiSSY5cHPtyvpi5wPhSwk5spWLrFTuwf2vau2rP8eFoCzP11pbkOXcUYEY5wBDNkl__7m5LFvSSvrjJFmi1VgH5RDuKTvQ_bLelsVsAgAhpMftNv1gdnlZU_Y1khNLIWoN9ZW411te6l-tlrmvXfb9hBOPScH4AVw3FjLxKToUh8RNdDsd_OFSuwXI8CYIipuSsC2sB2Wvw2sTI_MqKomjnfLsfNiuVT6JMGS2kXoCbiuYIIRGWu1gYMMurmIllJcRQQ5Y0Au95UXnL5nlw_DI6G6mS9yX0SGvFbtHJVqorGTPiv955OrErUYbmt92o9RB-_hDc7b9f_-Syp2XfPMcpjyZYM65S5JbuR8x2vySMVFdYDY1UFXWyLPxKlTbTWKCJZnMpvuajU7V_FktvW05AmGP-EKaPUAnQ3ihbsuI6rhY9h5pB25XhOPO3hXgEd1PKK9Zpsswi18d19rQpV8ZPWVgl_6oL_MmJKck4uIKxBbEvD-rjIWq-9KTKRFiCppViXDZUDbZXUB21doP3jEnKNdKB4BAB840DhW92JkH64dm0EUTm3zkaazS8yMvgMBELMKf2cWFXI7uOTOZaa0mtiJolHCbBuO4uODZNmtACTPgP4effh0ejXopw3uEJlvcXxwgsrsEweh-1IiAkOyzG7Fs_Al_yEgJhCD_f5zMhvxn5JAwMuoJfgSd7Ttl5Ctm7L1mPhLQUJAmrizROLtUaQgiohTR4D4IVfvY_810urGhk44e9hTNSorP0rmiyzZZBaSJhErkxZS6vn39dS46xtZxsWBKY4n-eTbs1VGXgLaRh5HjbUGXm7yvgj8hUd8Rqplz6WTEuZG_OOt9ArwqOqCBjxaKW8yYlEQr_-HTPUhJDg56yC_MVrkVTc-R1DsXJEBZ2tvyyJLW34UelW9TTPUTV1f_p_L1vw1Znds2ACNICDjWtKVyVf3VadTbzNb9jj8l_IIA0G8-86yx-6Q3K5m5eT0iB43glUp9c2nLHHl2qbRjzvCRWMjQXZYKKo1ZjQ12U8xsyOZtk0in9rC3JetK_396tsVxlch9ihz9zuFVHpoWNzcttlmWDvj3h2uOpWNxNaURQkHRPRVOrDX7JyQxOzPrSdHmhSW0tbNkR7ipwXsMrsrYOWXFD_9GwvKo3zoz2CbYVOQ7buGscudVlAXX19R55fXX4zDlPs7SXJ4ho4UcJMRriOQNubWXY5SgrCe_aWiQtkT5Kz36y2hQj6DuG_eZ-olvXlngb-fdxDJ3InjJBWXey7bG4_ZhPi7CjuYU2o2MAZGi0z8ifbVyIX6NDJ696sT9m2nqveHMJljfc8tMPOes51vRanYYLRCDvsa8IPsvwlY2bSR7cn_Tytd6LHLyLvBjYkfAQOQuKf_vTxTPJTtEhzov41W8Kt2W8zyx2Oma3vGCBBqpEaK_1RkOydabUIZgxQs5QsQLE5nOpD7pHEtjAEKaMjiD-ChBfTFfNAybFVFlKtmScU0BFr6phhX-hR53kiYFnmo3fpOluvXY_16ic5Tdqqx1ZRPvX2IX2CYIruqMBegTLbHKP-AQS05CHYp65BfxFvlRF8ISOkqeyY7AUaxU3x5rJ638pTLuppr3ItOoc88j1PANAbN53U-SiO5y4imwmp3HAb7ZneJDk57C4MxACemSqEVl-CRrSJ-2r_cz0HZzBm3tiA2rMCuXUn_B3gjPm3-_DFonZqf1ZCQSrRMXSV2ZQu8gAOraH9qNqf7CvFeoLmBRfWIrRP8G_7pjPNkfGHOyII8bHw7atgyAbTIY912TFY3yVz7FS5c-B5dcUdVCJw0VXdzptZa50-Ydgiduh-OVDE973QOdei68HMTOVZA8-BsjF_mHFZg1TTGFsBL2F9K6Lqt3gBtRLkUpScsiSq4CYwv-CBrbWrxHzP2moE4ltYdPofs9CqI6SIh0nnKioDFwEup3UOvjNCcq3noqmUki6gpyXrusa1BVNGWlGV772z1wQDKjacI1PSHekCBldWqLYvglqS864W24shttUtQ2lClFkU9U9q5UZqsCLkue7jNs8F_SpHQ-bU5SBedI50w3YJC4OYpXAHouY3vQxrwcAODAn1Jt0s9_mlui0cvBFNnDSQyWVzQPIPqm_lqqI3l34PPkCB94NUrs1g94cp-XWOh_tQ1g-9ZFdcm4cJqAz2iim5H3ekORWev9r5oQGBcuwggjlN8DtbCytOcxmwp7KXmSdDZxCTWuw49TRLrO5LITLwfKl5gcLIlDsmDcL2eq9_fG3Xiq4u6NuepLX6AQvjI0Bu7_mwgg6gVHrrpGSiOPppJRlF5v4I8GF5HX-TXUapFUsY9dtBkVCZnTU6uLYLD74Boc9Batdr3WEBzA_ZhbN85Zxfxakq1teYA-ubjQEshGNtBiWtYY9zz7DGIpNPYgnYWxcFtv6rPdYM4o7MVaRqXgAqq9rtC0US_9MeG5b_aDsqWU-QUkZf5rUqGwChADgopikyhUpOcuhQJsdcQ99u4yiiK2rHKRfYRkqWPIwfdSl04u8-O5twR8QoKWce6JgeKKWB8v72lDg_PpImadak_7x5Cag_DWcIOt6vyN_zTgo35D2Ex0jTWeQGd0LjhAd6XNU1UTfF63DtJz_RoSNOoFg-XbZDVxgyviki_KBZ7bjEjXAoZCvcWLx3jeEY9L3ARs6foTy1EdalNfCf7LEMVtXTpGlVjtoKVi8RFcBQz8k70wDmCUTj2ai_cBbqr9vLXu8P3k82vHglgAGCPyqPn3Zv7-ouaIwjmnjpvIMnuKNLNeUUXricxt98cnk2il9drkIo1JKgJESqIgRWSAybXSAiTMvayVFkV3CfdB3ZvMZ_IbjPY305bf8LM7dDXEEuemfIp5q0hfZeexUnAPFl8Yp0DCJjhlEcBEoSREeFg0gmK0SNlow1QaP3CrJOVENYWe52KWzJ8lry4JAXxau4tnPmx2xFbx56xjkJslubuBPI28nGbFdEuK6MdxTIbYfmfLglYE7H75xQWCIo6bnm-OwSnjiNO51aR2nKfRMNoSPi_em-1UKM9-llvR-DXmk6ZIzJSHv_g_OKLfjv4ZQ8izyxAZjrUsQ0802edVNNY3FD__EoZzHgw2W1umiECOgs47GxIZ6t_H6f8GT8KExuatOo7f8-2D16gvtlAB10qGhQTghrqnkMA7LynM97URaGzBYaJnmvzsw8tr7FOaOuJH6Jn2GnZOBJpnEP2U2_z2MfH5X5pRYkZP0kkpieWixzaSq0Jml_ABa5qX9NsC0-MLnJ6PsSACvSDohQQzKFCJkAtd3_VNq8ZUz3_ygLACYX3ZF88zm8MIhdzVcbHApr8KjoPRrvq1RpfHqZ0FH5Vq7SJsHScuch4S_1OhrtzUBJTh1dbQnDwNiWT-KVoNoUSzN1wHcMeZP19Xy5K80AR5lNtf1ZfchtDhPgq0vGX40pfob-tZTVq0wQJmMQgdyISz3PI3tS-4N2MwnSfhTrtwlwOUfhGyXP_xCRLRSeEbhZBAm4l-fgx86UW51CSzX6IayYyV6eSKYHM0iuEvmG4-dBpKgvU-WFLaURN8yoW90-fGaBLKfuesW6M-MwzzBmjiIjkoCONCzlcfISRVdMzGpdhkNDk1rd-kF4s6qpQzI8Ujg3gwX_m4NHHrq7kLMCahU3kVLrgkhY5P3wBKHhnqDU9YvrMYUwWmBy4IljLuw_8WNnxBhDPst3KKhf3LC5UjVSvg4RzV3xOC3BcUD9SzonHDYAiLr4cEro6vV78s0xz89vd4ysToeyPj9haQ26npgk79Xg0sAYJumCqMfEAybqWGmif_geWUtrfm4ywnTUcfhGcXrPpCN9nTfAGjoctAUYFi24XOg2E1PKiM9xdaWu1qfqtfvD0I_1SRjN_-q6CjTk3OfuLn0EKiliwZzROv-x6WndCe2tP81HiurX0Cu784an2zyYM9S5gotMnX3nhCI5W4uXQjpvvScNdixzkd9kmE1qVIjegu6p2U3-SST7JBgFjyomM6DhS1uIp2ebI0d-_ljAq_TwulOoZlc242J-pTlk2iJBw_eBsiG8IR3Tdsit3YSBNZ4SjVNRK_5rsVeOaVBuniRWa5UuKxl67IcfNhutJyBDj199P2DchvETjm_2lqmI54pWh4KYkk4Px_R4S4yBQRNqS8mfZSOqzTFtg5gWUsW2C9gFkqo1T0rzi5Unvtd4jbmdf5YVE19NZZI0Zf11zj8XNGWsIwZ08vAr-lm-YjrcISV7J8W7XNG55izUgoUMGq79MjdvjmM8QOHrIr5dKWwHbwHc_75feiYCABVk5-9dDeoiegELwes2HFdTqJYVLiwdoriwcV7vU0xFBmLFHbDxd4arlxiT6RQTfWby1KMzYGgCzpXVVDbJviDU2ujx0tI45IvoiigVSXpsAWhBYlauaT53_MgzYCsf5wRTU6rMO3ktQQ6AD8ZUx5LdyYrIFgmFCniotQA3ynRZE7o4HDKkJk_YZ9nMysX2fZc4haSeNwdZ86e1b4D04R1fGpegygUw9b0G2m8-X9fkZ_882Cwcz7ghlahXJPIfsyGBqytU8JmS8KCe4AVIetA-NY0YM-x_L_-RGp865emdDwspN5peUVEzOXwpj_IBm1QJ0kQOYnlCzcLKonLY-3nbjpWhKWbkL9b4nEFJmLOUrxfszN1kKIBjQ8Nj0V470Ezggj14Oxd3IJy3MrTrgM7iDoA448gQOgxsU61JpbeBBFBnUWBeR-kYcpBMh4uqT4xYcWLdUmS7GBz9W87ThMDe3BNrk1zVlRHmNjs_hKBZg97h0QC6pXSaUMIEBMfgZ94YAfE2QtxFfgiUc5WGVQCqQLZ6KLQdH9h2uPkOyFxo7-1xNVtW_6oDkNKmfHls-tWGe8i7WyplvtPScb3iALLcuY-zjseOQaU8EsDoFuNuDh7y4J6oHx7uXjrRWHf9-_PxlcOox86dbAXKfD3MMtsQTEzpvQ_x21eHN9Ihsg3EQurisLhdVjg1bn-Z3vCwHsDUokU3763VRwR4EunIFnsb5yhN3fouRw0Qt7RQbEnV3plV117sspunRH6OgDBKywx7qwqb6xCaQDBmSJPqsREpTdYz7NnNGLAdWVY6Lhx2mJzSUzlMk-WQxRT3HPFY-F4sJc8hW2LKKCxd9oIb0BqwwSSkwE3BZfm2vWmMo7YxvAKiwFLaylQbuFFEv-4Dgn4XnUj3zoFtKFqpJCsZ4pXo6IoBiCFwCn49MALbvpjFOo6P4SF1Csuk5yXf4\",\n" +
                "    \"tag\": \"qWcrB4q6Ao4VQvYpX5h2nw\",\n" +
                "    \"protected\": \"eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiUlNBLU9BRVAtMjU2In0\",\n" +
                "    \"iv\": \"M6z3wkrwRB_dFBNrc957bA\",\n" +
                "    \"recipients\": [\n" +
                "        {\n" +
                "            \"encrypted_key\": \"ZPNEbnxym-qbcTS9-IibKJ3xinxrSSO-wI9QA6ftfp9Ahf00Yi0sUi5NR9kIErTP85jeIzjdLxfGuCOjAfQqO5lsGbLyYkEFokHzQdeax-YDqpsSyN0TwvStOJCsMWIu_V2VAF98OAi_u5yABYp8J2PJWxGqfqiY0xj1v3dESFlAUCgEKfdY5yg_frSyiKi3hyQq-KztJLMzM9AKZmOI9hUqP8-IcvwzFpt4Dw2IhwikIlAdzWXV2CL9ij4QcYZv-EDW4cdTD6q1yotUJ7RC1OBOQRaLKBV-QVHSIw3vrMgromtIl1bqjmxZuWWP2rjndfuHThULEJPRv6EGe32-kw\"\n" +
                "        }\n" +
                "    ]\n" +
                "}");
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

