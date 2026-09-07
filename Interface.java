import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Interface extends JFrame {

    private JTextField txtTamanho;
    private JCheckBox chkMaiusculas;
    private JCheckBox chkMinusculas;
    private JCheckBox chkNumeros;
    private JCheckBox chkEspeciais;
    private JTextField txtSenha;
    private JButton btnGerar;
    private JLabel lblMensagem;

    public Interface() {
        setTitle("Gerador de Senhas");
        setSize(380, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel painelTamanho = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelTamanho.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelTamanho.add(new JLabel("Tamanho da Senha:  "));
        
        txtTamanho = new JTextField("8", 5);
        txtTamanho.setHorizontalAlignment(JTextField.CENTER);
        painelTamanho.add(txtTamanho);
        
        painelPrincipal.add(painelTamanho);
        painelPrincipal.add(Box.createVerticalStrut(10));

        chkMaiusculas = new JCheckBox("Incluir letras maiúsculas", false);
        chkMinusculas = new JCheckBox("Incluir letras minúsculas", false);
        chkNumeros = new JCheckBox("Incluir números", false);
        chkEspeciais = new JCheckBox("Incluir caracteres especiais", false);

        chkMaiusculas.setAlignmentX(Component.LEFT_ALIGNMENT);
        chkMinusculas.setAlignmentX(Component.LEFT_ALIGNMENT);
        chkNumeros.setAlignmentX(Component.LEFT_ALIGNMENT);
        chkEspeciais.setAlignmentX(Component.LEFT_ALIGNMENT);

        painelPrincipal.add(chkMaiusculas);
        painelPrincipal.add(chkMinusculas);
        painelPrincipal.add(chkNumeros);
        painelPrincipal.add(chkEspeciais);
        
        painelPrincipal.add(Box.createVerticalStrut(15));

        JPanel painelAcao = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelAcao.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnGerar = new JButton("Gerar Senha");

        txtSenha = new JTextField(18);
        txtSenha.setEditable(false);
        txtSenha.setHorizontalAlignment(JTextField.CENTER);

        painelAcao.add(txtSenha);
        painelAcao.add(Box.createHorizontalStrut(10));
        painelAcao.add(btnGerar);

        painelPrincipal.add(painelAcao);
        painelPrincipal.add(Box.createVerticalStrut(8));

        lblMensagem = new JLabel(" "); // Espaço em branco inicial
        lblMensagem.setForeground(Color.RED);
        lblMensagem.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelPrincipal.add(lblMensagem);

        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarSenha();
            }
        });

        add(painelPrincipal);
    }

    private void gerarSenha() {
        try {
            lblMensagem.setText(" ");
            int tamanho = Integer.parseInt(txtTamanho.getText().trim());
            boolean maiusculas = chkMaiusculas.isSelected();
            boolean minusculas = chkMinusculas.isSelected();
            boolean numeros = chkNumeros.isSelected();
            boolean especiais = chkEspeciais.isSelected();

            PasswordGenerator generator = new PasswordGenerator(tamanho, maiusculas, minusculas, numeros, especiais);
            String senhaGerada = generator.gerar();

            txtSenha.setText(senhaGerada);

        } catch (NumberFormatException ex) {
            lblMensagem.setText("Insira o tamanho da senha!");
        } catch (IllegalArgumentException ex) {
            lblMensagem.setText(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Interface().setVisible(true);
        });
    }
}