package util;

import com.sts.finncub.core.constants.Constant;
import com.sts.finncub.core.dto.CenterGroupCodeConfig;
import com.sts.finncub.core.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Shahzad Hussain
 */
@Slf4j
@Component
public class CommonUtil implements Constant {

    @Autowired
    CenterGroupCodeConfig centerGroupCodeConfig;
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public String getBatchId(UserSession userSession) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmsssss");
        String batchId = formatter.format(new Date().getTime());
        return userSession.getUserId() + "_" + batchId;
    }

    public String getBatchId() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmsssss");
        String batchId = formatter.format(new Date().getTime());
        return "TS00005" + "_" + batchId;
    }

    public File convert(MultipartFile file) throws IOException {
        FileOutputStream fos = null;
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
        } catch (Exception e) {
            log.error("Exception occured while writing file {}", file.getOriginalFilename());
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
        return convFile;
    }

    public List<String> getCenterGroupCode(String centerGroupCode) {
        for (List<String> list : centerGroupCodeConfig.getCenterGroupCode().values()) {
            if (list.contains(centerGroupCode)) {
                return list;
            }
        }
        return List.of(centerGroupCode);
    }

    public BigDecimal round(BigDecimal value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        value = value.setScale(places, RoundingMode.HALF_UP);
        return value;
    }

    public String convertToIndianCurrency(BigDecimal bd) {
        long number = bd.longValue();
        long no = bd.longValue();
        int decimal = (int) (bd.remainder(BigDecimal.ONE).doubleValue() * 100);
        int digits_length = String.valueOf(no).length();
        int i = 0;
        ArrayList<String> str = new ArrayList<>();
        HashMap<Integer, String> words = new HashMap<>();
        words.put(0, "");
        words.put(1, "One");
        words.put(2, "Two");
        words.put(3, "Three");
        words.put(4, "Four");
        words.put(5, "Five");
        words.put(6, "Six");
        words.put(7, "Seven");
        words.put(8, "Eight");
        words.put(9, "Nine");
        words.put(10, "Ten");
        words.put(11, "Eleven");
        words.put(12, "Twelve");
        words.put(13, "Thirteen");
        words.put(14, "Fourteen");
        words.put(15, "Fifteen");
        words.put(16, "Sixteen");
        words.put(17, "Seventeen");
        words.put(18, "Eighteen");
        words.put(19, "Nineteen");
        words.put(20, "Twenty");
        words.put(30, "Thirty");
        words.put(40, "Forty");
        words.put(50, "Fifty");
        words.put(60, "Sixty");
        words.put(70, "Seventy");
        words.put(80, "Eighty");
        words.put(90, "Ninety");
        String[] digits = {"", "Hundred", "Thousand", "Lakh", "Crore"};
        while (i < digits_length) {
            int divider = (i == 2) ? 10 : 100;
            number = no % divider;
            no = no / divider;
            i += divider == 10 ? 1 : 2;
            if (number > 0) {
                int counter = str.size();
                String plural = (counter > 0 && number > 9) ? "s" : "";
                String tmp = (number < 21) ? words.get((int) number) + " " + digits[counter] + plural : words.get((int) Math.floor(number / 10) * 10) + " " + words.get((int) (number % 10)) + " " + digits[counter] + plural;
                str.add(tmp);
            } else {
                str.add("");
            }
        }
        Collections.reverse(str);
        String Rupees = String.join(" ", str).trim();
        String paise = (decimal) > 0 ? " And Paise " + words.get((int) (decimal - decimal % 10)) + " " + words.get((int) (decimal % 10)) : "";
        return "Rupees " + Rupees + paise + " Only";
    }

    public static boolean isPresent(String s) {
        if (s != null && !"".equalsIgnoreCase(s) && s.length() > 0) {
            return true;
        }
        return false;
    }

    public static BigDecimal percentage(BigDecimal base, BigDecimal pct) {
        return base.multiply(pct).divide(ONE_HUNDRED);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String removeExtraSpace(String value) {
        return value.trim().replaceAll("  ", " ");
    }

    public String getAadharNo(String aadhaarNumber) {
        return !StringUtils.isEmpty(aadhaarNumber) ? StringUtils.overlay(aadhaarNumber, StringUtils.repeat("X", aadhaarNumber.length() - 4), 0, aadhaarNumber.length() - 4) : "";
    }

    public String getMemberName(ClientMasterDraft clientMasterDraft) {
        String memberName = clientMasterDraft.getFirstName();
        if (org.springframework.util.StringUtils.hasText(clientMasterDraft.getMiddleName()))
            memberName += " " + clientMasterDraft.getMiddleName();
        if (org.springframework.util.StringUtils.hasText(clientMasterDraft.getLastName()))
            memberName += " " + clientMasterDraft.getLastName();
        return memberName;
    }

    public String getMemberFullName(ClientMaster clientMaster) {
        String memberName = clientMaster.getFirstName();
        if (org.springframework.util.StringUtils.hasText(clientMaster.getMiddleName()))
            memberName += " " + clientMaster.getMiddleName();
        if (org.springframework.util.StringUtils.hasText(clientMaster.getLastName()))
            memberName += " " + clientMaster.getLastName();
        return memberName;
    }

    public String getEmployeeName(Employee employeeDetail) {
        String employeeName = "";
        if (employeeDetail != null) {
            employeeName = employeeDetail.getFirstName();
            if (org.springframework.util.StringUtils.hasText(employeeDetail.getLastName()))
                employeeName += " " + employeeDetail.getLastName();
        }
        return employeeName;
    }

    public String getCoApplicantName(ClientFamilyMemberDetail clientFamilyMemberDetail) {
        String coApplicantName = "";
        if (clientFamilyMemberDetail != null) {
            coApplicantName = clientFamilyMemberDetail.getFirstName();
            if (org.springframework.util.StringUtils.hasText(clientFamilyMemberDetail.getLastName()))
                coApplicantName += " " + clientFamilyMemberDetail.getLastName();
        }
        return coApplicantName;
    }

    public String nameMatchCheck(String custNameInBank, ClientMasterDraft clientMasterDraft) {
        try {
            String kycName = getMemberName(clientMasterDraft);
            String accountName = custNameInBank;
            List<String> fileNameTokens = Arrays.asList(accountName.toLowerCase().split(" "));
            int fileNameTokenSize = fileNameTokens.size();
            List<String> customerNameTokens = new ArrayList<>(
                    Arrays.asList(kycName.toLowerCase().split(" ")));
            int customerNameTokenSize = customerNameTokens.size();
            if (fileNameTokenSize > customerNameTokenSize || fileNameTokenSize < customerNameTokenSize)
                return "N";
            else {
                customerNameTokens.removeAll(fileNameTokens);
                int sizeAfterRemovingTokens = customerNameTokens.size();

                if (sizeAfterRemovingTokens == 0) {
                    return "Y";
                } else {
                    return "N";
                }
            }
        } catch (Exception e) {
            log.error("Exception occured due to {}", e.getMessage());
            return "N";
        }
    }

    public boolean checkFileType(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        try {
            if (!StringUtils.isEmpty(fileName)) {
                if (".xlsx".equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".")))) {
                    log.info("File type matched");
                    return true;
                }
            }
            log.info("Invalid file type for file {}", fileName);
            return false;
        } catch (Exception e) {
            log.error("Exception occurred, File extension should be .xlsx only for file {}", fileName);
            return false;
        }
    }

    public StringBuilder getClientFullAddress(ApplicationDetails applicationDetails) {
        StringBuilder addressBuilder = new StringBuilder();
        if (!applicationDetails.getApplicationAddressMapping().isEmpty()) {
            List<ApplicationAddressMapping> applicationAddressMapping = applicationDetails.getApplicationAddressMapping();
            Optional<ApplicationAddressMapping> address = applicationAddressMapping.stream().filter(applicationAddress -> applicationAddress.getIsCorrespondence().equals("Y")).findFirst();
            if (address.isPresent()) {
                ApplicationAddressMapping addressMapping = address.get();
                ClientAddressDetail clientAddressDetail = addressMapping.getClientAddressDetail();
                addressBuilder.append(org.springframework.util.StringUtils.hasText(clientAddressDetail.getAddress1()) ? clientAddressDetail.getAddress1() : "").append(",").append(org.springframework.util.StringUtils.hasText(clientAddressDetail.getAddress2()) ? clientAddressDetail.getAddress2() : "").append(",").append(org.springframework.util.StringUtils.hasText(clientAddressDetail.getAddress3()) ? clientAddressDetail.getAddress3() : "");
                VillageMaster villageMaster = clientAddressDetail.getVillageMaster();
                if (villageMaster != null) {
                    addressBuilder.append(",").append(org.springframework.util.StringUtils.hasText(villageMaster.getVillageName()) ? villageMaster.getVillageName() : "").append(",").append(Objects.toString(clientAddressDetail.getTehsil(), ""));
                }
                DistrictMaster districtMaster = clientAddressDetail.getDistrictMaster();
                if (districtMaster != null) {
                    addressBuilder.append(",").append(org.springframework.util.StringUtils.hasText(districtMaster.getDistrictName()) ? districtMaster.getDistrictName() : "").append("-");
                }
                StateMaster stateMaster = clientAddressDetail.getStateMaster();
                if (stateMaster != null) {
                    addressBuilder.append(",").append(org.springframework.util.StringUtils.hasText(stateMaster.getStateName()) ? stateMaster.getStateName() : "");
                }
                addressBuilder.append(clientAddressDetail.getPincode());
            }
        }
        return addressBuilder;
    }

    public String randomString(String emi) {
        return emi + "-" + RandomStringUtils.randomAlphanumeric(6);
    }

    public String getExtention(String content) {
        return org.springframework.util.StringUtils.hasText(content) ? content.substring(content.lastIndexOf(".") + 1) : "";
    }

    public boolean isWithinRadius(double lat1, double lon1, double lat2, double lon2, double radius) {

        final double EARTH_RADIUS = 6371000; //Earth radius in meters.

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS * c;

        return distance <= radius;
    }
}