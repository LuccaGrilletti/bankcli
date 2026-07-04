package com.lucca.bankcli.util;

/**
 * Validação de CPF baseada no algoritmo oficial de dígitos verificadores
 * (módulo 11). Não verifica se o CPF existe de fato na Receita Federal —
 * só valida o formato e a consistência matemática dos dígitos.
 */

public final class CpfValidator {

    private CpfValidator() {
        // classe utilitária: não deve ser instanciada
    }

    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        String digits = cpf.replaceAll("[^0-9]", "");

        if (digits.length() != 11) {
            return false;
        }

        // Rejeita sequências repetidas (111.111.111-11, 000.000.000-00, etc.)
        // que passariam no cálculo matemático mas são inválidas na prática.
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