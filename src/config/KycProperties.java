package config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kyc")
public class KycProperties {

	private String memberId;
	private String subMemberId;
	private String reqVolTyp;
	private String reqActnTyp;
	private String testFlg;
	private String authFlag;
	private String himarkUrl;
	private String userId;
	private String password;
	private String productType;
	private String productVersion;
	private String reqVolType;
	private String creditReqTyp;
	private String credtInqPurpsTyp;
	private String creditInquiryStage;
	private String indvFlag;
	private String scoreFlag;
	private String groupFlag;
	private String sOneHimarkUrl;
	private Long docVerificationDays;
	private String dcbAuthUrl;
	private String dcbEnquiryUrl;
	private String dcbUser;
	private String dcbPassword;
	private String dcbClientSecret;
	private String dcbClientId;
	private String dcbClientIdHeader;
	private String dcbClientSecretHeader;
	private String dcbAuthTokenKey;
	private String dcbBasicUser;
	private String dcbBasicPassword;
	private String losId;
	private String karzaKey;
	private String karzaApi;
	private String karzaFetchOtpUrl;
	private String karzaVerifyOtpUrl;
	private String karzaApiKey;
	private String himarkResponsePath;
}