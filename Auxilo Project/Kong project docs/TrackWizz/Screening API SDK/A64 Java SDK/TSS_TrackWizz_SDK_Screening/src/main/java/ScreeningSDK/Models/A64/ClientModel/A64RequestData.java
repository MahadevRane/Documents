package ScreeningSDK.Models.A64.ClientModel;


import ScreeningSDK.Enums.Nationality;
import ScreeningSDK.Models.A64.Common.Nationalities;
import ScreeningSDK.Models.A64.Common.ProductSegments;
import ScreeningSDK.Models.A64.Common.Tags;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@XmlRootElement(name = "ScreeningRequestData")
@XmlAccessorType(XmlAccessType.FIELD)
public class A64RequestData {
    public String RecordIdentifier;
    public String ApplicationRefNumber;
    public String RequestId;
    public String IntermediaryCode;
    public String SourceSystemName;
    public String ParentCompany;
    public String FirstName;
    public Object MiddleName;
    public Object LastName;
    public String CustomerCategory;
    public String Gender;
    public String Pan;
    public String Din;
    public String Cin;
    public String PassportNumber;
    public String DrivingLicenseNumber;
    public Date DateOfBirth;
    public String BirthCity;
    public String CountryOfBirth;
    public Nationalities Nationalities;
    public String CorrespondenceAddressLine1;
    public String CorrespondenceAddressLine2;
    public String CorrespondenceAddressLine3;
    public String CorrespondenceAddressCity;
    public String CorrespondenceAddressState;
    public String CorrespondenceAddressCountry;
    public String CorrespondenceAddressPinCode;
    public String PermanentAddressLine1;
    public String PermanentAddressLine2;
    public String PermanentAddressLine3;
    public String PermanentAddressCity;
    public String PermanentAddressState;
    public String PermanentAddressCountry;
    public String PermanentAddressPinCode;
    public String WorkMobileISD;
    public String WorkMobileNumber;
    public String PersonalMobileISD;
    public String PersonalMobileNumber;
    public String WorkEmail;
    public String PersonalEmail;
    public Tags Tags;
    public ProductSegments ProductSegments;
    public String ScreeningCategory;

    public A64RequestData(){
        ScreeningCategory = "Initial Screening Master";
        Nationalities = new Nationalities();
        Tags = new Tags();
        ProductSegments = new ProductSegments();
    }

    public A64RequestData(String requestId, String parentCompany, String firstName, String customerCategory) {
        RequestId = requestId;
        ParentCompany = parentCompany;
        FirstName = firstName;
        CustomerCategory = customerCategory;
        ScreeningCategory = "Initial Screening Master";
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getRecordIdentifier() {
        return RecordIdentifier;
    }

    public void setRecordIdentifier(String recordIdentifier) {
        RecordIdentifier = recordIdentifier;
    }

    public String getApplicationRefNumber() {
        return ApplicationRefNumber;
    }

    public void setApplicationRefNumber(String applicationRefNumber) {
        ApplicationRefNumber = applicationRefNumber;
    }

    public String getIntermediaryCode() {
        return IntermediaryCode;
    }

    public void setIntermediaryCode(String intermediaryCode) {
        IntermediaryCode = intermediaryCode;
    }

    public String getSourceSystemName() {
        return SourceSystemName;
    }

    public void setSourceSystemName(String sourceSystemName) {
        SourceSystemName = sourceSystemName;
    }

    public String getParentCompany() {
        return ParentCompany;
    }

    public void setParentCompany(String parentCompany) {
        ParentCompany = parentCompany;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public Object getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(Object middleName) {
        MiddleName = middleName;
    }

    public Object getLastName() {
        return LastName;
    }

    public void setLastName(Object lastName) {
        LastName = lastName;
    }

    public String getCustomerCategory() {
        return CustomerCategory;
    }

    public void setCustomerCategory(String customerCategory) {
        CustomerCategory = customerCategory;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPan() {
        return Pan;
    }

    public void setPan(String pan) {
        Pan = pan;
    }

    public String getDin() {
        return Din;
    }

    public void setDin(String din) {
        Din = din;
    }

    public String getCin() {
        return Cin;
    }

    public void setCin(String cin) {
        Cin = cin;
    }

    public String getPassportNumber() {
        return PassportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        PassportNumber = passportNumber;
    }

    public String getDrivingLicenseNumber() {
        return DrivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        DrivingLicenseNumber = drivingLicenseNumber;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getBirthCity() {
        return BirthCity;
    }

    public void setBirthCity(String birthCity) {
        BirthCity = birthCity;
    }

    public String getCountryOfBirth() {
        return CountryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        CountryOfBirth = countryOfBirth;
    }

    public String getCorrespondenceAddressLine1() {
        return CorrespondenceAddressLine1;
    }

    public void setCorrespondenceAddressLine1(String correspondenceAddressLine1) {
        CorrespondenceAddressLine1 = correspondenceAddressLine1;
    }

    public String getCorrespondenceAddressLine2() {
        return CorrespondenceAddressLine2;
    }

    public void setCorrespondenceAddressLine2(String correspondenceAddressLine2) {
        CorrespondenceAddressLine2 = correspondenceAddressLine2;
    }

    public String getCorrespondenceAddressLine3() {
        return CorrespondenceAddressLine3;
    }

    public void setCorrespondenceAddressLine3(String correspondenceAddressLine3) {
        CorrespondenceAddressLine3 = correspondenceAddressLine3;
    }

    public String getCorrespondenceAddressCity() {
        return CorrespondenceAddressCity;
    }

    public void setCorrespondenceAddressCity(String correspondenceAddressCity) {
        CorrespondenceAddressCity = correspondenceAddressCity;
    }

    public String getCorrespondenceAddressState() {
        return CorrespondenceAddressState;
    }

    public void setCorrespondenceAddressState(String correspondenceAddressState) {
        CorrespondenceAddressState = correspondenceAddressState;
    }

    public String getCorrespondenceAddressCountry() {
        return CorrespondenceAddressCountry;
    }

    public void setCorrespondenceAddressCountry(String correspondenceAddressCountry) {
        CorrespondenceAddressCountry = correspondenceAddressCountry;
    }

    public String getCorrespondenceAddressPinCode() {
        return CorrespondenceAddressPinCode;
    }

    public void setCorrespondenceAddressPinCode(String correspondenceAddressPinCode) {
        CorrespondenceAddressPinCode = correspondenceAddressPinCode;
    }

    public String getPermanentAddressLine1() {
        return PermanentAddressLine1;
    }

    public void setPermanentAddressLine1(String permanentAddressLine1) {
        PermanentAddressLine1 = permanentAddressLine1;
    }

    public String getPermanentAddressLine2() {
        return PermanentAddressLine2;
    }

    public void setPermanentAddressLine2(String permanentAddressLine2) {
        PermanentAddressLine2 = permanentAddressLine2;
    }

    public String getPermanentAddressLine3() {
        return PermanentAddressLine3;
    }

    public void setPermanentAddressLine3(String permanentAddressLine3) {
        PermanentAddressLine3 = permanentAddressLine3;
    }

    public String getPermanentAddressCity() {
        return PermanentAddressCity;
    }

    public void setPermanentAddressCity(String permanentAddressCity) {
        PermanentAddressCity = permanentAddressCity;
    }

    public String getPermanentAddressState() {
        return PermanentAddressState;
    }

    public void setPermanentAddressState(String permanentAddressState) {
        PermanentAddressState = permanentAddressState;
    }

    public String getPermanentAddressCountry() {
        return PermanentAddressCountry;
    }

    public void setPermanentAddressCountry(String permanentAddressCountry) {
        PermanentAddressCountry = permanentAddressCountry;
    }

    public String getPermanentAddressPinCode() {
        return PermanentAddressPinCode;
    }

    public void setPermanentAddressPinCode(String permanentAddressPinCode) {
        PermanentAddressPinCode = permanentAddressPinCode;
    }

    public String getWorkMobileISD() {
        return WorkMobileISD;
    }

    public void setWorkMobileISD(String workMobileISD) {
        WorkMobileISD = workMobileISD;
    }

    public String getWorkMobileNumber() {
        return WorkMobileNumber;
    }

    public void setWorkMobileNumber(String workMobileNumber) {
        WorkMobileNumber = workMobileNumber;
    }

    public String getPersonalMobileISD() {
        return PersonalMobileISD;
    }

    public void setPersonalMobileISD(String personalMobileISD) {
        PersonalMobileISD = personalMobileISD;
    }

    public String getPersonalMobileNumber() {
        return PersonalMobileNumber;
    }

    public void setPersonalMobileNumber(String personalMobileNumber) {
        PersonalMobileNumber = personalMobileNumber;
    }

    public String getWorkEmail() {
        return WorkEmail;
    }

    public void setWorkEmail(String workEmail) {
        WorkEmail = workEmail;
    }

    public String getPersonalEmail() {
        return PersonalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        PersonalEmail = personalEmail;
    }

    public String getScreeningCategory() {
        return ScreeningCategory;
    }

    public void setScreeningCategory(String screeningCategory) {
        ScreeningCategory = screeningCategory;
    }




}

