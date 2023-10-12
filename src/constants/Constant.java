package constants;

import java.util.HashMap;

public interface Constant {

    public static final String Y = "Y";

    public static final String N = "N";

    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DD_MM_YYYY_SLASH = "dd/MM/yyyy";
    public static final String DD_MONTH_YY = "dd-MMMM-yy";

    public static final String DD_MMM_YYYY = "dd-MMM-yyyy";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";

    public static final String DD_MMM_YYYY_HH_MM_SS = "dd-MMM-yyyy HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_S = "yyyy-MM-dd HH:mm:ss.S";
    public static final String SUCCESS = "Transaction completed successfully";
    public static final String FAILED = "Transaction failed";
    public static final String APP_NOT_FOUND = "Application doesn't exist!";
    public static final String NON_NULL = "Excel value column cannot be null";
    public static final String BANK_EXIST = "Bank details already exist for this bank";
    public static final String VOUCHER_CODE_DOES_NOT_EXIST = "Voucher code does not exist.";
    public static final String INVALID_EXCEL = "Invalid excel headers";
    public static final String HUSBAND = "H";

    public static final String FATHER = "F";

    public static final String SON = "S";

    public static final String DAUGHTER = "D";

    public static final String MOTHER = "M";

    public static final String SELF = "Self";

    public static final String NOT_FOUND = "No record found";

    public static final String APPROVED_STATUS = "A";
    public static final String REJECT_STATUS = "X";

    public static final String USER_MASTER_H = "USER_MASTER_H";
    public static final String BRANCH_MASTER_H = "BRANCH_MASTER_H";
    public static final String EMPLOYEE_MASTER_H = "EMPLOYEE_MASTER_H";
    public static final String CENTER_MASTER_H = "CENTER_MASTER_H";
    public static final String CLIENT_MASTER_H = "CLIENT_MASTER_H";
    public static final String APPLICATION_DETAIL_H = "APPLICATION_DETAIL_H";
    public static final String LOAN_DETAIL_H = "LOAN_DETAIL_H";
    public static final String REFERENCE_DETAIL_H = "REFERENCE_DETAIL_H";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String CLIENT_SPOUSE_FLAG = "C";
    public static final String CLIENT = "C";
    public static final String SPOUSE = "S";
    public static final String IS_TOPUP = "y";
    public static final String MALE = "M";
    public static final String FEMALE = "F";
    public static final String BROTHER = "F";
    public static final String WIFE = "W";
    public static final String SISTER = "S";

    public static final String MAS_BC_ID = "MAS";
    public static final String COAPPLICANT = "Y";


    public static final String VOUCHER_UPLOADER_HEADER_PARAM = "Account,Sub Account,Department,Cost Center,Tax,Base Amount,Debit,Credit,Comments,Supplier,Reference Code,SAC,Process Status,Process Message";
    public static final String CROSS_SHELL_PRODUCT = "CROSS_SELL";
    public static final String POST_DISB = "POST_DISB";
    public static final String DIVORCED = "D";
    public static final String WIDOWED = "W";
    public static final String MARRIED = "M";
    public static final String YBL = "YBL";
    public static final String IBL = "IBL";
    public static final String IBL_CLM = "IBL_CLM";
    public static final String YBL_SOURCING_HEADER = "BARCODE NO,BC NAME,BC BRANCH NAME,BC BRANCH CODE,EXTCENTERID,CENTER NAME,EXTGROUPID,GROUP NAME,EXTERNAL-CUSTOMER-ID,TITLE,CUSTOMERNAME,CUSTMIDDLENAME,CUSTLASTNAME,CUSTDOB,CUSTAGE,CASTE,GENDER,RELIGION,NATIONALITY,EDU-QUALIFICATION,MARITALSTATUS,MOBILE NO,MOTHERNAME,FATHERNAME,DDE_SPOUSENAME,SPOUSEDOB,SPOUSE AGE,RELATIVETITLE,RELATIVENAME,RELATIONSHIPTYPE,CURADDLINE1,CURADDVILLAGE,CURADDLANDMARK,CURADDDISTRICTCODE,CITY,CURADDUTCODE,CURADDPINCODE,PERADDLINE1,PERADDVILLAGE,PERADDLANDMARK,PERADDDISTRICTCODE,PERADDUTCODE,PERADDPINCODE,LOANAMOUNT,NOOFINSTALLMENTS,PRODUCT CODE,NO OF LIVES INSURED,LANOMINEENAME,LANOMINEERELATION,LANOMINEEADD,LANOMINEEDOB,INSURANCEOFFERING,BANKNAME,BANK-A/C-NO,BANK-BRANCH-NAME,ORIGINATOR,PSM NAME,PSM ID,APPDATE,CUSTOMER CREATION DATE,LOAN PURPOSE,SEC ID TYPE,SEC ID NO,MEMBER-PLACE-OF-BIRTH,ANNUALHOUSEHOLDINC,ANNUALEXPENSES,LOANCATEG,FARMERCATEG,OCCUPATION,CURRENTRESIDENCEOWNERSHIP,GEOCLASSIFICATION,ROLE IN GROUP,NO. OF DEPENDENTS,LAT,LONG,CENTER DAY,NO OF MFI RELATIONSHIP,LOAN O/S,LAN,BC UNIQUE REFERENCE NO,CGT COMPLETION DATE,LI_NO OF LIVES INSURED,LI_CO-INSURED NAME,LI_CO-INSURED RELATIONSHIP WITH CUSTOMER,LI_CO-INSURED ADDRESS,LI_CO-INSURED DOB,LI_BOR_NOMINEE NAME,LI_BOR_NOMINEE RELATIONSHIP WITH CUSTOMER,LI_BOR_NOMINEE ADDRESS,LI_BOR_NOMINEE DOB,LI_CO-INSURED NOMINEE NAME,LI_CO-INSURED NOMINEE RELATIONSHIP WITH CUSTOMER,LI_CO-INSURED NOMINEE ADDRESS,LI_CO-INSURED NOMINEE DOB,GI_NO OF LIVES INSURED,GI_CO-INSURED NAME,GI_CO-INSURED ADDRESS,GI_CO-INSURED DOB,GI_BOR_NOMINEE NAME,GI_BOR_NOMINEE RELATIONSHIP WITH CUSTOMER,GI_BOR_NOMINEE ADDRESS,GI_BOR_NOMINEE DOB,GI_CO-INSURED NOMINEE NAME,GI_CO-INSURED NOMINEE RELATIONSHIP WITH CUSTOMER,GI_CO-INSURED NOMINEE ADDRESS,GI_CO-INSURED NOMINEE DOB,Remarks";
    public static final String YBL_SANCTION_HEADER = "SR NO,STATENAME,BC Name,BC BRANCH,YBL BRANCH,CUSTOMER ID,LAF Barcode No.,External Cust No,Loan Account No.,Preprocessing ID,BC UNIQUE ID,CUSTOMER NAME,RELATIVE NAME,Loan Status,Applied Amount,Loan Frequency,Loan Tenure,Center Name,External Center ID,Group Name,External Group No,Sanctioned Date,Sanctioned Amount,CB Remark,CB DATE,CB Expired,FIELD DISBURSEMENT DATE,1st EMI Date,Disbursed Amount,DISBURSEMENT MODE,Loan Cycle,LI_Amount,LI_Count,Co-insured Name,LI_Customer Nominee Name,LI_Co-insured Nominee Name,GI-insured Name,GI_Customer Nominee Name,GI_Co-insured Nominee Name,GI_Count,GI_Amount,OutstandingAmt,NoOfDefaultCases,NoOfOtherFinIns,AutoAllocatedAmount,IN FILE UPLOADED,Remarks";
    public static final String IBL_SANCTION_HEADER = "SL.NO.,IBL Base Branch Code,IBL Base Branch Name,Rural/Urban,SP Name,SP Customer ID,SP Loan Number,BankCustomerID,Bank Loan No,SP Branch,SP Centre Name,SP Village,CustomerName,Spouse Name,Bank Product Code,SP Product Code,Purpose,BSR CODE,Applied Amount,Sanctioned Loan Amount,Credit Bureau Verification Done By (Himark / Equifax),Credit Bureau Status,Sanctioned Date,Expected Disbursal Date,Loan Status,Lapsed Date,No Of Days Left for Lapse,Status,Remarks";
    public static final String NARRATION_VOUCHER_HEADER = "S. NO.,Sequence,Journal Code,Period,Voucher Date,Description,Parent Sequence,Account,Sub Account,Supplier,Department,Cost Center,TAX PERCENT,Exemption Tax,Debit,Credit,SGST,CGST,IGST,TDS,Invoice Date,Invoice Number,Invoice Type,Type,SAC,Reference Code,COMMENTS,STATUS,MESSAGE";
    public static final String YOUTUBE_EXT = "YT";
    public static final String YOUTUBE_URL_MATCHED = "watch?v=";
    public static final String DELIMITER = "|";
    public static final String CO_APPLICANT = "Y";
    public static final String CROSSPONDANSE = "Y";
    public static final String PERMANENT = "Y";
    public static final String CASH_ADJUSTMENT_HEADER = "LoanCode,Amount,CollectionDate,ProcessStatus,ProcessMessage";
    public static final String ERROR = "-1";
    public static final String SALARY_ERROR_DATA = "S.No.,Month,Staff Code,Staff Name,Branch Code,Branch,Department,Department Code,Designation,Basic,DA,Special Allowance,HRA,Conveyance,Medical Reimbursement,Mobile Allowance,LTA,Arrears,Leave Encashment,Rimburshment,Field Conveyance,Incentive,Total Earning,EPF,ESIC,TDS,Advance,Interest on Loan to Employees,LWF,Professional Tax,Total Deductions,Net Pay,EPF Employer,ESIC Employer";
    public static final String APPROVE = "A";
    public static final String REJECT = "R";
    public static final String APPLICATION_REJECT = "X";
    public static final String CROSS_ADANCE_RECIPT_TEMPLATE = "cross_adance_recipt";
    public static final String CROSS_SELL_RECEIPT_REPORT = "Cross_Sell_Receipt_Report";
    public static final String DEATH_INTIMATION_REPORT = "Death_Intimation_Report";
    public static final String SVCL_INCOME_ASSESSMENT = "svcl_income_assessment";
    public static final String SVCL_FACT_SHEET = "svcl_factSheet";
    public static final String INDIA_BULLS_FACT_SHEET = "india_bulls_factSheet";
    public static final String INDIA_BULLS_FACT_SHEET_HINDI = "indiaBulls_factSheet_hindi";

    public static final String INDIA_BULLS_LOAN_AGREEMENT = "IndiaBullsLoanAgreement";

    public static final String APPROVAL_NOTE_INDIA_BULLS = "Approval_Note_indiabulls";

    public static final String IBL_FACT_SHEET = "ibl_factsheet";
    public static final String HTML_SPACE = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    public static final String YBL_HOUSE_HOLD_INCOME = "ybl_house_hold_income";
    public static final String A = "A";
    public static final String DEDUPE_HEADER_PARAM = "SOURCE,ENTITY_TYPE,PRODUCT_TYPE,AOF_SR_NO,FULL_NAME,DOB,GENDER,PAN_GIR_NUMBER,AADHAR REF KEY,DRIVING_LICENSE,PASSPORT,VOTER_ID,MOBILE_NUMBER,COMMUNICATION_ADDRESS,COMMUNICATION_CITY,COMMUNICATION_PINCODE,EMAIL_ID";
    public static final String BUREAU_HEADER_PARAM = "Reference Number,Member ID/ Unique Account Number,Inquiry Purpose (Required),Transaction Amount,ConsumerName (Required),Additional Type1 (Relationship),Additional Name1,Additional Type2,Additional Name2,Address & City (Required),State/Union Territory (Required),Postal Pin (Required),Ration Card,Voter ID,Additional Id 1,Additional Id 2,National ID Card (UIN),Tax ID / PAN,Phone (Home),Phone (Mobile),DOB(Required),Gender,Branch ID,Kendra ID,Family code,Household Income (Yearly)";
    public static final String YBL_DISBURSEMENT_CONFIRMATION_HEADER = "SR NO,STATENAME,BC Name,BC BRANCH,YBL BRANCH,CUSTOMER ID,LAF Barcode No.,External Cust No,Loan Account No.,Preprocessing ID,BC UNIQUE ID,CUSTOMER NAME,RELATIVE NAME,Loan Status,Applied Amount,Loan Frequency,Loan Tenure,Center Name,External Center ID,Group Name,External Group No,Sanctioned Date,Sanctioned Amount,CB Remark,CB DATE,CB Expired,FIELD DISBURSEMENT DATE,1st EMI Date,Disbursed Amount,DISBURSEMENT MODE,Loan Cycle,LI_Amount,LI_Count,Co-insured Name,LI_Customer Nominee Name,LI_Co-insured Nominee Name,GI-insured Name,GI_Customer Nominee Name,GI_Co-insured Nominee Name,GI_Count,GI_Amount,OutstandingAmt,NoOfDefaultCases,NoOfOtherFinIns,AutoAllocatedAmount,IN FILE UPLOADED";
    public static final String INSURANCE_HEADER_PARAM = "CLIENT_CODE,MASTER_POL_NO,LOAN_CODE,BRANCH_CODE,BRANCH_NAME,CENTER_CODE,CLIENT_NAME,CLIENT_AGE,GURRANTER_NAME,RELATION_SHIP,RELATIONSHP_AGE,DECEASED_NAME,DECEASED_AGE,NOMINEE_NAME,DEATH_PERSON_DOB,DEATH_PERSON,DEATH_DATE,CAUSE_OF_DEATH,DISBURSEMENT_DATE,LOAN_AMOUNT,REPAYMENT_AMOUNT,PRINCIPAL_OUTSTANDING,ADDRESS,DISTRICT_NAME,STATE_NAME,INSURANCE_NAME,TENURE_IN_MONTHS,PRODUCT_CODE,PRODUCT_ID,BC_PARTNER_ID,CLIENT_MOBILE_NO,FO_NAME,FO_MOBILE_NUMBER,DEATH_STATUS,";
    public static final Integer FIRST_ELEMENT = 0;
    public static final String JANA = "JANA";
    public static final String JANA_DISBURSEMENT_HEADER = "CRES_EMP_ID,CRES_EMP_NAME,VILLAGE__C,PINCODE,Distance From Branch,AREA_NAME,MEETING_CENTER_CODE,MEETING_CENTER_NAME,MEETING_CENETR_DAY,BRANCH_CODE,BRANCH_NAME,BRANCH_CATEGORY,ACCOUNT_NUMBER,CRN,AADHAR_NUMBER,ID_PROOF_TYPE_1,ID_PROOF_NO_1,ID_PROOF_TYPE_2,ID_PROOF_NO_2,CUSTOMER_NAME,CUSTOMER_LAST_NAME,GENDER,DATE_OF_BIRTH,MARITAL_STATUS,FATHER_OR_HUSBAND_FLG,FATHERS_OR_HUSBANDS_FRST_NM,FATHER_SPOUSE_DOB,FATHER_SPOUSE_ADHAR,FATHER_SPOUSE_SEC_ID,ADDRESS_LINE_1,ADDRESS_LINE_2,PRESENT_CITY,REGION,ZONE,STATE,MOBILE_NUMBER,CASTE__C,RELIGION__C,PREFERRED_LANGUAGE,EDUCATION,IFSC_CODE,BANK_ACCOUNT_NUMBER,BANK_BRANCH_NAME,MONTHLY_EXPENSE,MONTHLY_INCOME,HOUSE_TYPE,BELOW_POVERTY_LINE,NATURE_OF_EMPLOYEMENT,NO_OF_ANIMALS,LAND_IN_ACRE,AGRICULTURAL_INCOME,OTHER_INCOME,RISK_CATEGORIZATION,PRODUCT_CATEGORY,Repayment Frequency,SUB_PURPOSE,CREATED_DATE,LOAN_DISSBURSEMENT_MODE,LOAN_PRODUCT_CODE,NAM_PRODUCT,DB_DATE,DB_AMOUNT,SANCTION_DATE,SANCTION_AMOUNT,TERM_OF_THE_LOAN,RATE_OF_INT,Current_EMI,Processing_Fee,Insurance_Fee,NOMINEE_FULL_NM,NOMINEE_INDIVIDUAL_AGE,CO_BORROWER_NAME,DOB_OF_CO_BORROWER,REL_WITH_CUSTOMER,CO_BORROWER_KYC_ID,CO_BORROWER_KYC_TYPE,SECONDARY_CUSTOMER_ID,PRODUCT_CODE,Branchcode,Center Name,Group_Application_No,EMP_Id_as_per_SVCL";
    public static final String JANA_REPAYMENT_HEADER = "ACCOUNT_NUMBER,COD_PROD,NAM_PRODUCT,DB_DATE,DB_AMT,MATURITY_DATE,DAT_STAGE_START,CTR_STAGE_NO,CTR_INSTAL,DATE_INSTAL,RAT_INT,AMT_INSTAL_OUTST,AMT_PRINCIPAL,AMT_INTEREST,AMT_PRINC_BAL,CTR_DAYS";
    public static final String CENTER_TRANSFER_ERROR_HEADER = "CENTER_ID,BATCH_ID,FROM_BRANCH_ID,TO_BRANCH_ID,FROM_USER,TO_USER,STATUS,COMMENTS,MESSAGE";


    public static final String THIRTY_PLUS_DPD = "030";
    public static final String SIXTY_PLUS_DPD = "060";
    public static final String NINETY_PLUS_DPD = "090";
    public static final String SF = "SF";
    public static final String ACTIVE = "ACTIVE";
    public static final String CLOSED = "CLOSED";

    public static final String LOAN_DETAILS_HEADER = "ORG_ID,LOAN_CODE,INTEREST_RATE,TENURE,LOAN_AMOUNT,EMI_AMOUNT,REPAYMENT_FREQUENCY,FIRST_EMI_DATE,DISBURSEMENT_DATE,TOTAL_INSTALLMENT,MESSAGE";

    public static final String SOMETHING_WRONG = "Something went wrong!, Please try later...";

    public String CLM_COLLECTION_DETAIL_HEADER = "ORG_ID,LOAN_CODE,REFERENCE_NUMBER,COLL_SEQUENCE,COLL_PAY_TYPE,COLLECTION_DATE,TOTAL_COLLECTED,PRINCIPAL_COLLECTED,INTEREST_COLLECTED,EXTENDED_INT_COLLECTED,STATUS";

    public String AADHAR_MASKED_DIGITS = "XXXXXXXX";

    public String EXISTING_EMPLOYEE_MSG = "Existing Employees found with employeeCode";
    public String EXISTING_ACTIVE_EMPLOYEE_MSG = "Existing Active Employees found with employeeCode";

    public static final String AUTO_CB = "NA";
    public static final String MANUAL_CB = "NM";

    public String MAS_DEMAND_PROMISE_LETTER = "mas_svcl_demand_promise_letter";
    public String MAS_FACT_SHEET = "mas_factSheet";
    public String MAS_LOAN_CARD_REPORT = "mas_loan_card_report";
    public String BM_ROLE = "BM_ROLE";
    public String ROLE_ATTENDANCE_EDIT = "ROLE_ATTENDANCE_EDIT";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String MM_DD_YYYY = "dd/MM/yyyy";

    public static final String REPAYMENT_DRAFT_HEADER = "ORG_ID,LOAN_CODE,EMI_SEQUENCE,DUE_DATE,PRINCIPAL_DUE,INTEREST_DUE,TOTAL_DUE,PRINCIPAL_OUTSTANDING,INTEREST_OUTSTANDING,TOTAL_AMT_OUTSTANDING,CUMULATIVE_PRINCIPAL_DUE,CUMULATIVE_INTEREST_DUE,EXTENDED_INTEREST";
    public static final String CLM_LOAN_DETAILS_HEADER = "ORG_ID,LOAN_CODE,INTEREST_RATE,TENURE,LOAN_AMOUNT,EMI_AMOUNT,REPAYMENT_FREQUENCY,FIRST_EMI_DATE,DISBURSEMENT_DATE,TOTAL_INSTALLMENT";
    public static final String CROSS_SELL_IGL_LOAN_DETAIL_HEADERS = "ORG_ID, LOAN_ID, LOAN_CODE, CLIENT_ID, CLIENT_CODE, CLIENT_NAME, LOAN_STATUS, PRODUCT_CODE, PRINCIPAL_AMOUNT, DISBURSEMENT_DATE, CENTER_CODE, CENTER_NAME, BRANCH_CODE, BRANCH_NAME, DIVISION_CODE, DIVISION_NAME, ZONE_CODE, ZONE_NAME, BC_PARTNER_ID, NET_PAYABLE_AMOUNT, XCELL_LOANCODE, XCELL_PRO_CODE, XCELL_AMT, XCELL_DISBDT";

    public static final String PAYMENT_PENDING_LOANS = "BRANCH_CODE, BRANCH_NAME, DIVISION_CODE, DIVISION_NAME, ZONE_CODE, ZONE_NAME, STATE_NAME, BC_ID, LOAN_ID, LOAN_CODE, STATUS, CLIENT_ID, CLIENT_NAME, PRODUCT_GROUP_ID, CENTER_ID, DISBURSEMENT_DATE, INTEREST_RATE, TENURE_IN_MONTHS, PRINCIPAL_AMOUNT, PROCESSING_FEE, TOTAL_INSTALLMENTS, DISBURSED_AMOUNT, PRINCIPAL_OUTSTANDING, INTEREST_OUTSTANDING, LOAN_CYCLE, PRINCIPAL_PAY_FREQUENCY, NET_PAYABLE_AMOUNT, PAYMENT_DATE";

    public static final String LISTED_NON_LISTED_POOL = "LOAN_CODE, STATE_NAME, ZONE_CODE, ZONE_NAME, DIVISION_CODE, DIVISION_NAME, BRANCH_CODE, BRANCH_NAME, CENTER_CODE, CENTER_NAME, CLIENT_CODE, COLLECTION_DATE, LISTED_POOL_COLLECTION, REGULAR_COLLECTION";

    public static final String CASH_DIFFERENCE_BRANCH_DAY_CLOSE = "ZONE_CODE, ZONE_NAME, DIVISION_CODE, DIVISION_NAME, BRANCH_ID, BRANCH_CODE, BRANCH_NAME, BRANCH_CLOSE_DATE, BRANCH_BALANCE, CASHBOOK_BALANCE, CASH_DIFFERENCE";
    public static final String LAST_DATE_DAY_CLOSE_REPORT_HEADER = "ZONE_CODE, ZONE_NAME, DIVISION_CODE, DIVISION_NAME, BRANCH_ID, BRANCH_CODE, BRANCH_NAME";

    public String Hospido = "Hospido Private Limited";
    public static final String RPS_FORMATTER_HEADER_PARAM = "LoanCode,EmiSequence,DueDate,PrincipalDue,InterestDue,TotalDue,PrincipalOutstanding,LanNumber";
    public static final String MAPPED = "Branches mapped successfully";
    public static final String WRITE_OFF_ERROR_EXCEL_HEADER = "LOAN_CODE,POS,IARR,WRITE_OFF_DATE,ERROR_MESSAGE";
    public static final String BANK_VALIDATE = "This bank is already validated";

    public static final String UNITY = "UNITY";
    public static final String UNITY_SOURCING_HEADER = "EXT LAN NO, BC NAME, BC BRANCH NAME, BC BRANCH CODE, CENTERID, CENTER NAME, EXTGROUPID, GROUP NAME, EXTERNAL-CUSTOMER-ID, TITLE, CUSTOMERNAME, CUSTMIDDLENAME, CUSTLASTNAME, CUSTDOB, CUSTAGE, PREFERRED LANGUAGE, CASTE, GENDER, RELIGION, NATIONALITY, EDU-QUALIFICATION, MARITALSTATUS, MOBILE NO, ALT CONTACT NO, MOTHERNAME, FATHERNAME, SPOUSENAME, SPOUSEDOB, SPOUSE AGE, CURADDLINE1, CURADDVILLAGE, CURADDLANDMARK, CURADDDISTRICTCODE, CITY, CURADDUTCODE, CURADDPINCODE, PERADDLINE1, PERADDVILLAGE, PERADDLANDMARK, PERADDDISTRICTCODE, PERADDUTCODE, PERADDPINCODE, LOANAMOUNT,NOOFINSTALLMENTS,INTEREST RATE,REPAYMENT FREQUENCY, TENURE IN MONTHS,PROCESSING FEE,INSURANCE CHARGE,NOMINEENAME, NOMINEERELATION,NOMINEEDOB,BANKNAME,BANK-A/C-NO, BANK_IFSC_CODE, BANK-BRANCH-NAME,FO_ID,APPDATE, CUSTOMER CREATION DATE, LOAN PURPOSE,AADHAR_NUMBER,SEC ID TYPE, SEC ID NO,MEMBER-PLACE-OF-BIRTH,ANNUALHOUSEHOLDINC,ANNUALEXPENSES,LOANCATEG,FARMERCATEG, OCCUPATION,CURRENTRESIDENCEOWNERSHIP,GEOCLASSIFICATION,NO. OF DEPENDENTS,LAT, LONG,CENTER DAY,NO OF LIVES INSURED, CO-INSURED NAME, CO-INSURED RELATIONSHIP WITH CUSTOMER, CO-INSURED ADDRESS, CO-INSURED DOB, CO-INSURED KYC ID TYPE, CO-INSURED KYC ID NUMBER, Status, Remarks";
    public static final String IBL_QR = "IBL_QR";

    static HashMap<Integer, String> DCB_ADDITIONAL_NAME_TYPE_MAP() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "F");
        map.put(2, "H");
        map.put(3, "B");
        map.put(4, "S");
        map.put(5, "U");
        map.put(6, "Y");
        map.put(7, "T");
        map.put(8, "O");
        map.put(9, "M");
        map.put(10, "W");
        map.put(11, "C");
        map.put(12, "X");
        map.put(13, "Z");
        map.put(14, "D");
        map.put(15, "V");
        return map;
    }

    public static HashMap<Integer, String> DCB_STATE_MAP() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Andaman & Nicobar Islands");
        map.put(2, "Andhra Pradesh");
        map.put(3, "Arunachal Pradesh");
        map.put(4, "Assam");
        map.put(5, "Bihar");
        map.put(6, "Chandigarh");
        map.put(7, "Chattisgarh");
        map.put(8, "Dadra and Nagar Haveli");
        map.put(9, "Daman and Diu");
        map.put(10, "Delhi");
        map.put(11, "Goa");
        map.put(12, "Gujarat");
        map.put(13, "Haryana");
        map.put(14, "Himachal Pradesh");
        map.put(15, "Jammu & Kashmir");
        map.put(16, "Jharkhand");
        map.put(17, "Karnataka");
        map.put(18, "Kerala");
        map.put(19, "Lakshadweep");
        map.put(20, "Madhya Pradesh");
        map.put(21, "Maharashtra");
        map.put(22, "Manipur");
        map.put(23, "Meghalaya");
        map.put(24, "Mizoram");
        map.put(25, "Nagaland");
        map.put(26, "Orissa");
        map.put(27, "Puducherry");
        map.put(28, "Punjab");
        map.put(29, "Rajasthan");
        map.put(30, "Sikkim");
        map.put(31, "Tamil Nadu");
        map.put(32, "Tripura");
        map.put(33, "Uttar Pradesh");
        map.put(34, "Uttarakhand");
        map.put(35, "West Bengal");
        map.put(36, "Telangana");
        return map;
    }

    public static HashMap<Integer, String> DCM_MARITAL_STATUS_MAP() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "Cohabitating");
        map.put(2, "Divorced");
        map.put(3, "Married");
        map.put(4, "NotAsked");
        map.put(5, "NotGiven");
        map.put(6, "Other");
        map.put(7, "Separated");
        map.put(8, "Single");
        map.put(9, "ToBeMarried");
        map.put(10, "Widowed");
        return map;
    }

    public static final String IBL_FORMATTER_HEADER_PARAM = "Remitter A/c No, Beneficiary A/c No,Beneficiary A/c Name,IFSC Code,Benficiary Bank Name,AMOUNT,PURPOSE,BUDGET HEAD";

    public String COLENDING_COLLECTION_HEADER = "LOAN_ID,COLL_SEQUENCE,COLLECTION_DATE,STATUS,TOTAL_COLLECTED,PRINCIPAL_COLLECTED,INTEREST_COLLECTED,EXTENDED_INT_COLLECTED,BROKEN_INT_COLLECTED,COLLECTED_BY,COLL_PAY_TYPE,COLLECTION_MODE,CLM_STATUS,PARTNER_PRINCIPAL,PARTNER_INTEREST,OWN_PRINCIPAL,OWN_INTEREST,SPREAD";

    public String DISBURSEMENT_HEADER = "EXTERNAL LAN NO,EXTERNAL LOAN ID,EXTERNAL CUSTOMER ID,LOAN AMOUNT, NO OF INSTALLMENT,TENURE IN MONTH,INTEREST RATE,PROCESSING FEE,INSURANCE CHARGE,DISB DATE,NEFT DATE,FIRST EMI DATE,EMI AMOUNT,FIRST EMI AMOUNT,BANLK UTR NO,REMARKS, IS HEALTH CARE AVAIL, HEALTHCARE AMOUNT";

    public String DISB_IBL_CLM_HEADER_PARAM = "Transaction Type,Beneficiary Code,Beneficiary  A/c No,Transaction Amount,Benefirciary Name,Drawee Location,Print Location,Beneficiary add line1,Beneficiary add line2,Beneficiary add line3,Beneficiary add line4,Zipcode,Instrument Ref No.,Customer Ref No.,Advising Detail1,Advising Detail2,Advising Detail3,Advising Detail4,Advising Detail5,Advising Detail6,Advising Detail7,Cheque No.,Instrument Date,MICR No,IFSC Code,Bene Bank Name,Bene Bank Branch,Bene Email ID,Debit A/C Number,Source Narration,Target Narration,Value Date,Cust Name In Bank";
    public String PASSWORD_SEPARATOR = ",,";
    public String UNAUTHORIZED = "You are not authorized to access this resource";
    String UPDATE_YOUR_APP_VERSION = "Please update your app version";

    public String EXISTING_CLIENT_MSG = "Existing Client found with clientId";

    public String PASSWORD_POLICY_MSG = "Password guidelines violated !";

    public String PASSWORD_CANT_BE_USER_ID = "New password can't be userId";

    public String BOTH_PASSWORD_SHOULD_BE_SAME = "Confirm password is not same as new password";

    public String NEW_PASSWORD_MATCHES_WITH_RECENT_PASSWORDS = "New password matches with recent passwords";

    public String SVCL = "SVCL";

    public String INVALID_USER = "Invalid userId - ";

    public String KEY = "USER_SESSION";

    public String FORGET = "FORGET";

    public String IBL_SANCTION_REJECTION_DOWNLOADER = "Application_id,Remarks";
}