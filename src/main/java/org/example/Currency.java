package org.example;

public class Currency {
    String code;
    String nominal;
    String name;
    Double value;

    public Currency(String code, String nominal, String name, Double value) {
        this.code = code;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }


    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", nominal='" + nominal + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
