package com.aninfo.service;

public class PromoService {
    private static final double promoMinimumDeposit = 2000;
    private static final double promoMaximumValue = 500;
    private static final double promoMultiplier = 0.1;
    public static Double applyPromo(Double sum){
        double promoBonus = 0;
        if (promoMinimumDeposit <= sum)
            promoBonus = sum * promoMultiplier;
        if (promoBonus > promoMaximumValue) {
            promoBonus = promoMaximumValue;
        }

        return promoBonus;
    }
}
