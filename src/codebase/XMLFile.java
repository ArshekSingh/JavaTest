package codebase;

import java.util.List;
import java.util.Optional;

public class XMLFile {

    private String prepareEnquiryRequest(List<DcbEnquiryRequest> dcbEnquiryRequest) {
        Optional<DcbEnquiryRequest> client = dcbEnquiryRequest.stream().filter(v -> v.getApplicantType().equals("Applicant")).findFirst();
        Optional<DcbEnquiryRequest> coApplicant = dcbEnquiryRequest.stream().filter(v -> v.getApplicantType().equals("CoApplicant")).findFirst();
        if (client.isPresent() && coApplicant.isPresent()) {
            return "<soapenv:Envelope " +
                    "xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:tem=\"http://tempuri.org/\" " +
                    "xmlns:net=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\" " +
                    "xmlns:arr=\"http://schemas.microsoft.com/2003/10/Serialization/Arrays\" " +
                    "xmlns:net1=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <tem:ProcessRequest>\n" +
                    "         <!--Optional:-->\n" +
                    "         <tem:request>\n" +
                    "            <!--Zero or more repetitions:-->\n" +
                    "            <net:Request>\n" +
                    "\n" +
                    "               <NS2:Additional_Name1 xmlns:NS2=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getAdditional_Name1() + "</NS2:Additional_Name1>\n" +
                    "               <NS3:Additional_Name_Type1 xmlns:NS3=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getAdditional_Name_Type1() + "</NS3:Additional_Name_Type1>\n" +
                    "               <NS4:AddrLine1 xmlns:NS4=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getAddrLine1() + "</NS4:AddrLine1>\n" +
                    "               <NS5:AddrType xmlns:NS5=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getAddrType() + "</NS5:AddrType>\n" +
                    "               <NS6:BureauCategory xmlns:NS6=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                    "                  <NS6:CategoryId>" + client.get().getCategoryId() + "</NS6:CategoryId>\n" +
                    "                  <NS6:CategoryName>" + client.get().getCategoryName() + "</NS6:CategoryName>\n" +
                    "                  <NS6:IsEnabled>" + client.get().isEnabled() + "</NS6:IsEnabled>\n" +
                    "               </NS6:BureauCategory>\n" +
                    "                <NS7:Customfields xmlns:NS7=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                    "                  <NS8:CustomFields xmlns:NS8=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS8:Key>WORKFLOW_ID</NS8:Key>\n" +
                    "                     <NS8:Value>" + client.get().getWorkFlowId() + "</NS8:Value>\n" +
                    "                  </NS8:CustomFields>\n" +
                    "                  <NS9:CustomFields xmlns:NS9=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS9:Key>BC_NAME</NS9:Key>\n" +
                    "                     <NS9:Value>" + client.get().getBcName() + "</NS9:Value>\n" +
                    "                  </NS9:CustomFields>\n" +
                    "                  <NS10:CustomFields xmlns:NS10=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS10:Key>BC_BRANCH_ID</NS10:Key>\n" +
                    "                     <NS10:Value>" + client.get().getBcBranchId() + "</NS10:Value>\n" +
                    "                  </NS10:CustomFields>\n" +
                    "                  <NS11:CustomFields xmlns:NS11=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS11:Key>BC_BRANCH_NAME</NS11:Key>\n" +
                    "                     <NS11:Value>" + client.get().getBcBranchName() + "</NS11:Value>\n" +
                    "                  </NS11:CustomFields>\n" +
                    "                  <NS12:CustomFields xmlns:NS12=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS12:Key>REGION_NAME</NS12:Key>\n" +
                    "                     <NS12:Value>" + client.get().getRegionName() + "</NS12:Value>\n" +
                    "                  </NS12:CustomFields>\n" +
                    "                  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>PRODUCT_ID</NS13:Key>\n" +
                    "                     <NS13:Value>" + client.get().getProductId() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>APPLICANT_TYPE</NS13:Key>\n" +
                    "                     <NS13:Value>" + client.get().getApplicantType() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>FAMILY_HOUSEHOLD_MONTHLY_INCOME</NS13:Key>\n" +
                    "                     <NS13:Value>" + client.get().getFamilyHouseHoldMonthlyIncome() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>TENURE</NS13:Key>\n" +
                    "                     <NS13:Value>" + client.get().getTenure() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>INTEREST_RATE</NS13:Key>\n" +
                    "                     <NS13:Value>" + client.get().getInterestRate() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields> \n" +
                    "\t\t\t\t\n" +
                    "               </NS7:Customfields>\n" +
                    "               <NS14:DOB xmlns:NS14=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getDOB() + "</NS14:DOB>\n" +
                    "               <NS15:First_Name xmlns:NS15=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getFirst_Name() + "</NS15:First_Name>\n" +
                    "               <NS16:Gender xmlns:NS16=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getGender() + "</NS16:Gender>\n" +
                    "               <NS17:Inquiry_Purpose xmlns:NS17=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getInquiry_Purpose() + "</NS17:Inquiry_Purpose>\n" +
                    "               <NS18:Last_Name xmlns:NS18=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getLast_Name() + "</NS18:Last_Name>\n" +
                    "               <NS19:LosIndicator xmlns:NS19=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getLosIndicator() + "</NS19:LosIndicator>\n" +
                    "               <NS20:Los_Appl_No xmlns:NS20=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getLos_Appl_No() + "</NS20:Los_Appl_No>\n" +
                    "               <NS21:Marital_Status xmlns:NS21=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getMarital_Status() + "</NS21:Marital_Status>\n" +
                    "               <NS22:Middle_Name xmlns:NS22=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                    "               <NS23:National_Id_Card xmlns:NS23=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getNational_Id_Card() + "</NS23:National_Id_Card>\n" +
                    "               <NS24:OverrideCoolingPeriod xmlns:NS24=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().isOverrideCoolingPeriod() + "</NS24:OverrideCoolingPeriod>\n" +
                    "               <NS25:Pan_Id xmlns:NS25=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getPan_Id() + "</NS25:Pan_Id>\n" +
                    "               <NS26:Passport_Id xmlns:NS26=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                    "               <NS27:Postal xmlns:NS27=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getPostal() + "</NS27:Postal>\n" +
                    "               <NS28:State xmlns:NS28=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getState() + "</NS28:State>\n" +
                    "               <NS29:Street xmlns:NS29=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getStreet() + "</NS29:Street>\n" +
                    "               <NS30:Transaction_Amount xmlns:NS30=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getTransaction_Amount() + "</NS30:Transaction_Amount>\n" +
                    "               <NS31:Voter_Id xmlns:NS31=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + client.get().getVoter_Id() + "</NS31:Voter_Id>\n" +
                    "            </net:Request>\n" +
                    "         \n" +
                    "\t\t   <!--Co-Applicant-->\n" +
                    "\t\t   <net:Request>\n" +
                    "               <NS2:Additional_Name1 xmlns:NS2=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getAdditional_Name1() + "</NS2:Additional_Name1>\n" +
                    "               <NS3:Additional_Name_Type1 xmlns:NS3=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getAdditional_Name_Type1() + "</NS3:Additional_Name_Type1>\n" +
                    "               <NS4:AddrLine1 xmlns:NS4=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getAddrLine1() + "</NS4:AddrLine1>\n" +
                    "               <NS5:AddrType xmlns:NS5=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getAddrType() + "</NS5:AddrType>\n" +
                    "               <NS6:BureauCategory xmlns:NS6=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                    "                  <NS6:CategoryId>" + coApplicant.get().getCategoryId() + "</NS6:CategoryId>\n" +
                    "                  <NS6:CategoryName>" + coApplicant.get().getCategoryName() + "</NS6:CategoryName>\n" +
                    "                  <NS6:IsEnabled>" + coApplicant.get().isEnabled() + "</NS6:IsEnabled>\n" +
                    "               </NS6:BureauCategory>\n" +
                    "                <NS7:Customfields xmlns:NS7=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">\n" +
                    "                  <NS8:CustomFields xmlns:NS8=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS8:Key>WORKFLOW_ID</NS8:Key>\n" +
                    "                     <NS8:Value>" + coApplicant.get().getWorkFlowId() + "</NS8:Value>\n" +
                    "                  </NS8:CustomFields>\n" +
                    "                  <NS9:CustomFields xmlns:NS9=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS9:Key>BC_NAME</NS9:Key>\n" +
                    "                     <NS9:Value>" + coApplicant.get().getBcName() + "</NS9:Value>\n" +
                    "                  </NS9:CustomFields>\n" +
                    "                  <NS10:CustomFields xmlns:NS10=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS10:Key>BC_BRANCH_ID</NS10:Key>\n" +
                    "                     <NS10:Value>" + coApplicant.get().getBcBranchId() + "</NS10:Value>\n" +
                    "                  </NS10:CustomFields>\n" +
                    "                  <NS11:CustomFields xmlns:NS11=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS11:Key>BC_BRANCH_NAME</NS11:Key>\n" +
                    "                     <NS11:Value>" + coApplicant.get().getBcBranchName() + "</NS11:Value>\n" +
                    "                  </NS11:CustomFields>\n" +
                    "                  <NS12:CustomFields xmlns:NS12=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS12:Key>REGION_NAME</NS12:Key>\n" +
                    "                     <NS12:Value>" + coApplicant.get().getRegionName() + "</NS12:Value>\n" +
                    "                  </NS12:CustomFields>\n" +
                    "                  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>PRODUCT_ID</NS13:Key>\n" +
                    "                     <NS13:Value>" + coApplicant.get().getProductId() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>APPLICANT_TYPE</NS13:Key>\n" +
                    "                     <NS13:Value>" + coApplicant.get().getApplicantType() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t   <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>FAMILY_HOUSEHOLD_MONTHLY_INCOME</NS13:Key>\n" +
                    "                     <NS13:Value>" + coApplicant.get().getFamilyHouseHoldMonthlyIncome() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>TENURE</NS13:Key>\n" +
                    "                     <NS13:Value>" + coApplicant.get().getTenure() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields>\n" +
                    "\t\t\t\t  <NS13:CustomFields xmlns:NS13=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects.BureauOperation\">\n" +
                    "                     <NS13:Key>INTEREST_RATE</NS13:Key>\n" +
                    "                     <NS13:Value>" + coApplicant.get().getInterestRate() + "</NS13:Value>\n" +
                    "                  </NS13:CustomFields> \n" +
                    "\t\t\t\t\n" +
                    "               </NS7:Customfields>\n" +
                    "               <NS14:DOB xmlns:NS14=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getDOB() + "</NS14:DOB>\n" +
                    "               <NS15:First_Name xmlns:NS15=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getFirst_Name() + "</NS15:First_Name>\n" +
                    "               <NS16:Gender xmlns:NS16=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getGender() + "</NS16:Gender>\n" +
                    "               <NS17:Inquiry_Purpose xmlns:NS17=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getInquiry_Purpose() + "</NS17:Inquiry_Purpose>\n" +
                    "               <NS18:Last_Name xmlns:NS18=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getLast_Name() + "</NS18:Last_Name>\n" +
                    "               <NS19:LosIndicator xmlns:NS19=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getLosIndicator() + "</NS19:LosIndicator>\n" +
                    "               <NS20:Los_Appl_No xmlns:NS20=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getLos_Appl_No() + "</NS20:Los_Appl_No>\n" +
                    "               <NS21:Marital_Status xmlns:NS21=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getMarital_Status() + "</NS21:Marital_Status>\n" +
                    "               <NS22:Middle_Name xmlns:NS22=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                    "               <NS23:National_Id_Card xmlns:NS23=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getNational_Id_Card() + "</NS23:National_Id_Card>\n" +
                    "               <NS24:OverrideCoolingPeriod xmlns:NS24=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().isOverrideCoolingPeriod() + "</NS24:OverrideCoolingPeriod>\n" +
                    "               <NS25:Pan_Id xmlns:NS25=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getPan_Id() + "</NS25:Pan_Id>\n" +
                    "               <NS26:Passport_Id xmlns:NS26=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\"/>\n" +
                    "               <NS27:Postal xmlns:NS27=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getPostal() + "</NS27:Postal>\n" +
                    "               <NS28:State xmlns:NS28=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getState() + "</NS28:State>\n" +
                    "               <NS29:Street xmlns:NS29=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getStreet() + "</NS29:Street>\n" +
                    "               <NS30:Transaction_Amount xmlns:NS30=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getTransaction_Amount() + "</NS30:Transaction_Amount>\n" +
                    "               <NS31:Voter_Id xmlns:NS31=\"http://schemas.datacontract.org/2004/07/Nettpositive.BureauOne.BusinessObjects\">" + coApplicant.get().getVoter_Id() + "</NS31:Voter_Id>\n" +
                    "            </net:Request>\n" +
                    "         \n" +
                    "\t\t \n" +
                    "\t\t \n" +
                    "\t\t </tem:request>\n" +
                    "      </tem:ProcessRequest>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
        } else {
            log.info("Applicant/CoApplicant data should not be null");
            return null;
        }
    }
}
