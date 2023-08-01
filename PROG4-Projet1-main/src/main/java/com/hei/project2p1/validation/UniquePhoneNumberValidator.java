package com.hei.project2p1.validation;

import com.hei.project2p1.modele.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, List<PhoneNumber>> {
    @Override
    public boolean isValid(List<PhoneNumber> phoneNumbers, ConstraintValidatorContext context) {
        if (phoneNumbers == null || phoneNumbers.isEmpty()) {
            return true;
        }
        Set<String> uniquePhoneNumbers = new HashSet<>();

        for (PhoneNumber phoneNumberObj : phoneNumbers) {
            String phoneNumber = phoneNumberObj.getPhoneNumber();

            String countryCode = getCountryCodeFromPhoneNumber(phoneNumber);

            if (uniquePhoneNumbers.contains(countryCode)) {
                return false;
            }

            uniquePhoneNumbers.add(countryCode);
        }
        return true;
    }
    private String getCountryCodeFromPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.startsWith("+")) {
            int endIndex = phoneNumber.indexOf(' ', 1);
            if (endIndex != -1) {
                return phoneNumber.substring(0, endIndex);
            }
        }
        return ""; // Retourner une chaîne vide si le numéro de téléphone n'est pas valide
    }

}

