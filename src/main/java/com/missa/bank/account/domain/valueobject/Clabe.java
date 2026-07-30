package com.missa.bank.account.domain.valueobject;

import com.missa.bank.account.domain.exception.InvalidClabeException;

import java.util.concurrent.ThreadLocalRandom;

public record Clabe(String clabe) {

    // Pesos oficiales para el algoritmo de validación de la CLABE en México
    private static final int[] PESOS = {3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7};

    // Constructor compacto para validar cualquier CLABE que se intente registrar
    public Clabe {
        if (clabe == null || !clabe.matches("\\d{18}")) {
            throw new InvalidClabeException("La CLABE debe contener exactamente 18 dígitos numéricos.");
        }

        // Validar que el dígito verificador proporcionado sea matemáticamente correcto
        int digitoProvisto = Character.getNumericValue(clabe.charAt(17));
        int digitoCalculado = calcularDigitoVerificador(clabe.substring(0, 17));
        if (digitoProvisto != digitoCalculado) {
            throw new InvalidClabeException("CLABE inválida: El dígito verificador no coincide.");
        }
    }

    // Factory Method corregido e implementado
    public static Clabe create() {
        // 1. Definimos un banco y plaza por defecto (Ej: 002 Banamex, 010 Aguascalientes)
        String banco = "012";
        String plaza = "180";

        // 2. Generamos los 11 dígitos aleatorios de la cuenta
        long maxCuenta = 100000000000L;
        long numeroAleatorio = ThreadLocalRandom.current().nextLong(maxCuenta);
        String cuenta = String.format("%011d", numeroAleatorio);

        // 3. Concatenamos los primeros 17 dígitos
        String primeros17 = banco + plaza + cuenta;

        // 4. Calculamos el dígito verificador matemático oficial
        int digitoVerificador = calcularDigitoVerificador(primeros17);

        System.out.println(cuenta);
        System.out.println(cuenta.length());

        System.out.println(primeros17);
        System.out.println(primeros17.length());

        System.out.println(primeros17 + digitoVerificador);
        System.out.println((primeros17 + digitoVerificador).length());

        // 5. Retornamos la instancia completa de 18 dígitos
        return new Clabe(primeros17 + digitoVerificador);
    }

    // Algoritmo oficial Módulo 10 con pesos [3, 7, 1]
    private static int calcularDigitoVerificador(String primeros17Digitos) {
        int suma = 0;
        for (int i = 0; i < 17; i++) {
            int digito = Character.getNumericValue(primeros17Digitos.charAt(i));
            // Multiplicamos por el peso correspondiente y nos quedamos solo con las unidades (módulo 10)
            suma += (digito * PESOS[i]) % 10;
        }
        // Restamos de 10 el residuo de la suma y aplicamos módulo 10 nuevamente por si da 10
        return (10 - (suma % 10)) % 10;
    }
}

