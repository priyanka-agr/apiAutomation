package com.tajawal.common.utilities;

import com.github.javafaker.Faker;

import java.text.Normalizer;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Fakers {

    Faker fakeCheckoutData = new Faker();
    public String title = fakeCheckoutData.name().prefix();
    public String firstName = fakeCheckoutData.name().firstName();
    public String lastName = fakeCheckoutData.name().lastName();
    public String email = fakeCheckoutData.internet().emailAddress();
    public String phoneNumber = fakeCheckoutData.phoneNumber().cellPhone().replace("-","");
}
