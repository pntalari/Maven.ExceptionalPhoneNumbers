package com.zipcodewilmington.phone;

import com.zipcodewilmington.exceptions.InvalidPhoneNumberFormatException;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by leon on 5/1/17.
 */
public final class PhoneNumberFactory {
    private static final Logger logger = Logger.getGlobal();

    private PhoneNumberFactory() {
        /** This constructor is private
         *  This class is uninstantiable */

    }

    /**
     * @param phoneNumberCount - number of PhoneNumber objects to instantiate
     * @return array of randomly generated PhoneNumber objects
     */ //TODO - Implement logic
    public static PhoneNumber[] createRandomPhoneNumberArray(int phoneNumberCount) throws InvalidPhoneNumberFormatException {
        PhoneNumber[] phoneNumbers = new PhoneNumber[phoneNumberCount];
        for (int i = 0; i <phoneNumberCount ; i++) {
           phoneNumbers[i] = createRandomPhoneNumber();
        }
        return phoneNumbers;
    }

    /**
     * @return an instance of PhoneNumber with randomly generated phone number value
     */ //TODO - Implement logic
    public static PhoneNumber createRandomPhoneNumber() throws InvalidPhoneNumberFormatException {
        //minimum + rn.nextInt(maxValue - minvalue + 1)
        int min = 100;
        int max = 999;
        int range = max - min;
        Random random = new Random();
        int areaCode, centralOfficeCode, phoneLineCode = 0;

        areaCode = min + random.nextInt(range + 1);
        centralOfficeCode = min + random.nextInt(range + 1);
        phoneLineCode = min + random.nextInt((9999-1000) + 1);

        return createPhoneNumberSafely(areaCode, centralOfficeCode, phoneLineCode);
    }


    /**
     * @param areaCode          - 3 digit code
     * @param centralOfficeCode - 3 digit code
     * @param phoneLineCode     - 4 digit code
     * @return a new phone number object
     */ //TODO - if input is valid, return respective PhoneNumber object, else return null
    public static PhoneNumber createPhoneNumberSafely(Integer areaCode, Integer centralOfficeCode, Integer phoneLineCode) throws InvalidPhoneNumberFormatException {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        builder.append(areaCode);
        builder.append(')');
        builder.append('-');
        builder.append(centralOfficeCode);
        builder.append('-');
        builder.append(phoneLineCode);

        String phoneNumber = builder.toString();
        try {
            if (PhoneNumber.formatCheck(phoneNumber))
                return createPhoneNumber(phoneNumber);
            else
                throw new InvalidPhoneNumberFormatException();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param phoneNumberString - some String corresponding to a phone number whose format is `(###)-###-####`
     * @return a new phone number object
     * @throws InvalidPhoneNumberFormatException - thrown if phoneNumberString does not match acceptable format
     */ // TODO - Add throws statement to method signature
    public static PhoneNumber createPhoneNumber(String phoneNumberString) throws InvalidPhoneNumberFormatException {
        if (PhoneNumber.formatCheck(phoneNumberString))
            return new PhoneNumber(phoneNumberString);
        else
            throw new InvalidPhoneNumberFormatException();

    }
}
