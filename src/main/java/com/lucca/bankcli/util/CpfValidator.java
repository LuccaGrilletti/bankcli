package com.lucca.bankcli.util;

/**
 * CPF validation based on the official check-digit algorithm (modulo 11).
 * Does not verify whether the CPF actually exists at the Receita Federal —
 * it only validates the format and the mathematical consistency of the digits.
 */

public final class CpfValidator {

    private CpfValidator() {
        // utility class: not meant to be instantiated
    }

    /**
     * Checks whether {@code cpf} is a well-formed, mathematically valid CPF.
     * Accepts any punctuation (dots/dashes are stripped before validation).
     */
    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        String digits = cpf.replaceAll("[^0-9]", "");

        if (digits.length() != 11) {
            return false;
        }

        // Rejects repeated-digit sequences (111.111.111-11, 000.000.000-00, etc.)
        // that would pass the mathematical check but are invalid in practice.
        if (digits.chars().distinct().count() == 1) {
            return false;
        }

        int firstCheckDigit = calculateCheckDigit(digits.substring(0, 9), 10);
        int secondCheckDigit = calculateCheckDigit(digits.substring(0, 9) + firstCheckDigit, 11);

        return digits.equals(digits.substring(0, 9) + firstCheckDigit + secondCheckDigit);
    }

    private static int calculateCheckDigit(String base, int startWeight) {
        int sum = 0;
        int weight = startWeight;

        for (char c : base.toCharArray()) {
            sum += Character.getNumericValue(c) * weight;
            weight--;
        }

        int remainder = sum % 11;
        return (remainder < 2) ? 0 : (11 - remainder);
    }
}