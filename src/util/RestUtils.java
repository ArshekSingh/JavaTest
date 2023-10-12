package util;

import com.google.api.client.util.Base64;
import com.google.gson.Gson;
import com.sts.finncub.core.mockresponse.MockAccountResponse;
import com.sts.finncub.core.request.KarzaAccountValidateRequest;
import com.sts.finncub.core.response.KarzaPennyDropResponse;
import com.sts.finncub.kyc.config.KycProperties;
import com.sts.finncub.kyc.request.AadhaarFetchOtpRequest;
import com.sts.finncub.kyc.request.VerifyKarzaAadhaarRequest;
import com.sts.finncub.kyc.response.KarzaFetchOtpResponse;
import com.sts.finncub.kyc.response.KarzaVerifyOtpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class RestUtils {

    @Autowired
    private KycProperties kycProperties;

    @Autowired
    @Qualifier("cbRestTemplate")
    private RestTemplate restTemplate;

    @Value("${spring.config.activate.on-profile}")
    private String environment;

    public String fetchCIRReport(String requestXML) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("userId", kycProperties.getUserId());
        headers.set("password", kycProperties.getPassword());
        headers.set("productType", kycProperties.getProductType());
        headers.set("productVersion", kycProperties.getProductVersion());
        headers.set("reqVolType", kycProperties.getReqVolType());
        headers.set("mbrid", kycProperties.getMemberId());
        headers.set("requestXML", requestXML);
        HttpEntity<Object> request = new HttpEntity<>(headers);
        // make an HTTP GET request with headers
        try {
            ResponseEntity<String> response = restTemplate.exchange(kycProperties.getHimarkUrl(), HttpMethod.POST, request,
                    String.class);
            if (200 == response.getStatusCodeValue()) {
                if (response.getBody() != null)
                    return response.getBody();
            } else {
                log.error("Unable to fetch CIR report! Response: {}", response.getBody());
                return null;
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching CIR! message: {}", e.getMessage());
            return null;
        }
        return null;
    }

    public String processingS1HiMarkRequest(String s1HiMarkRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        log.info("calling S1HiMark Api ");
        // build the request
        HttpEntity<String> request = new HttpEntity<>(s1HiMarkRequest, headers);
        // make an HTTP GET request with headers
        ResponseEntity<String> response = restTemplate.exchange(kycProperties.getSOneHimarkUrl(), HttpMethod.POST,
                request, String.class);
        if (response.getBody() != null)
            return response.getBody();
        return null;
    }

    public KarzaPennyDropResponse validateAccountByKarza(KarzaAccountValidateRequest request) {
        log.info("request initiated to call Karza Penny drop API for client id {}", request.getClientId());
        if ("dev".equalsIgnoreCase(environment) || "uat".equalsIgnoreCase(environment)) {
            log.info("Current environment is {} Returning mock response", environment);
            return MockAccountResponse.getAccountValidationResponse(request);
        }
        try {
            restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
                @Override
                protected boolean hasError(HttpStatus statusCode) {
                    return false;
                }
            });
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-karza-key", kycProperties.getKarzaKey());
            HttpEntity<KarzaAccountValidateRequest> requestObj = new HttpEntity<>(request, headers);
            log.info("Request for account verification for clientId {} : {}", request.getClientId(), new Gson().toJson(request));
            ResponseEntity<String> response = restTemplate.exchange(kycProperties.getKarzaApi(),
                    HttpMethod.POST, requestObj, String.class);
            if (response.getBody() != null) {
                return new Gson().fromJson(response.getBody(), KarzaPennyDropResponse.class);
            }
        } catch (Exception e) {
            log.info("Exception occurred due to {}", e.getMessage());
        }
        return null;
    }

    public KarzaFetchOtpResponse fetchKarzaOtpForAadhaar(AadhaarFetchOtpRequest otpRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-karza-key", kycProperties.getKarzaApiKey());
        log.info("calling karza get otp api for aadhaar verification");
        // build the request
        HttpEntity<AadhaarFetchOtpRequest> request = new HttpEntity<>(otpRequest, headers);
        // make an HTTP GET request with headers
        ResponseEntity<KarzaFetchOtpResponse> response = restTemplate.exchange(kycProperties.getKarzaFetchOtpUrl(), HttpMethod.POST,
                request, KarzaFetchOtpResponse.class);
        if (response.getBody() != null)
            return response.getBody();
        return null;
    }

    public KarzaVerifyOtpResponse verifyKarzaOtpForAadhaar(VerifyKarzaAadhaarRequest verifyKarzaAadhaarRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-karza-key", kycProperties.getKarzaApiKey());
        log.info("calling karza verify otp api for aadhaar verification for aadhaar: {}", verifyKarzaAadhaarRequest.getAadhaarNo());
        // build the request
        try {
            HttpEntity<VerifyKarzaAadhaarRequest> request = new HttpEntity<>(verifyKarzaAadhaarRequest, headers);
            // make an HTTP GET request with headers
            ResponseEntity<KarzaVerifyOtpResponse> response = restTemplate.exchange(kycProperties.getKarzaVerifyOtpUrl(), HttpMethod.POST,
                    request, KarzaVerifyOtpResponse.class);
            return response.getBody() != null ? response.getBody() : null;
        } catch (Exception exception) {
            log.info("Error while calling Karza api {}", exception.getMessage());
            return null;
        }
    }

    public String authenticateDcbService(String dcbAuthRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add(kycProperties.getDcbClientIdHeader(), kycProperties.getDcbClientId());
        headers.add(kycProperties.getDcbClientSecretHeader(), kycProperties.getDcbClientSecret());
        headers.add("Authorization", createBasicAuthString(kycProperties.getDcbBasicUser(), kycProperties.getDcbBasicPassword()));
        log.info("Calling DCB authentication service API");
        try {
            HttpEntity<String> request = new HttpEntity<>(dcbAuthRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(kycProperties.getDcbAuthUrl(), HttpMethod.POST, request, String.class);
            return response.getBody() != null ? response.getBody() : null;
        } catch (Exception exception) {
            log.info("Error while calling DBC api {}", exception.getMessage());
            return null;
        }
    }

    public String fetchDcbClientDetails(String dcbEnquiryRequest, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add(kycProperties.getDcbClientIdHeader(), kycProperties.getDcbClientId());
        headers.add(kycProperties.getDcbClientSecretHeader(), kycProperties.getDcbClientSecret());
        headers.add("Authorization", createBasicAuthString(kycProperties.getDcbBasicUser(), kycProperties.getDcbBasicPassword()));
        headers.add(kycProperties.getDcbAuthTokenKey(), token);
        log.info("Calling DCB enquiry service API");
        try {
            HttpEntity<String> request = new HttpEntity<>(dcbEnquiryRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(kycProperties.getDcbEnquiryUrl(), HttpMethod.POST, request, String.class);
            return response.getBody() != null ? response.getBody() : null;
        } catch (Exception exception) {
            log.info("Error while calling DBC api {}", exception.getMessage());
            return null;
        }
    }

    private String createBasicAuthString(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(encodedAuth);
    }
}