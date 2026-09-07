import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String ESPECIAIS = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    private final int tamanho;
    private final boolean maiusculas;
    private final boolean minusculas;
    private final boolean numeros;
    private final boolean especiais;

    private final SecureRandom random = new SecureRandom();

    public PasswordGenerator(int tamanho, boolean maiusculas, boolean minusculas, boolean numeros, boolean especiais) {
        this.tamanho = tamanho;
        this.maiusculas = maiusculas;
        this.minusculas = minusculas;
        this.numeros = numeros;
        this.especiais = especiais;
    }

    public String gerar() {
        validar();

        String caracteresValidos = obterCaracteresValidos();
        StringBuilder senha = new StringBuilder(this.tamanho);

        for (int i = 0; i < this.tamanho; i++) {
            int indiceSorteado = random.nextInt(caracteresValidos.length());
            senha.append(caracteresValidos.charAt(indiceSorteado));
        }
        return senha.toString();
    }

    private void validar() {
        if (this.tamanho <= 0) {
            throw new IllegalArgumentException("Insira um número válido!");
        }

        if (!this.maiusculas && !this.minusculas && !this.numeros && !this.especiais) {
            throw new IllegalArgumentException("Selecione um grupo de caracters!");
        }
    }

    private String obterCaracteresValidos() {
        StringBuilder caracteres = new StringBuilder();

        if (this.maiusculas) {
            caracteres.append(MAIUSCULAS);
        }
        if (this.minusculas) {
            caracteres.append(MINUSCULAS);
        }
        if (this.numeros) {
            caracteres.append(NUMEROS);
        }
        if (this.especiais) {
            caracteres.append(ESPECIAIS);
        }

        return caracteres.toString();
    }
}