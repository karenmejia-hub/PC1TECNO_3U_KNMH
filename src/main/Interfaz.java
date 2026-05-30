// KAREN NOEMY MEJIA HERNÁNDEZ
package main;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class Interfaz extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel Login, Usuario, Password;
    private JTextField nombre;
    private JPasswordField pass;
    private JButton ingresar;

    Conexion conexion = new Conexion();
    Connection con = conexion.getConnection();

    public Interfaz() {
        IniciarComponentes();
        AñadirComponentes();
    }

    public void IniciarComponentes() {
        this.setLayout(null);
        this.setTitle("Login");
        this.setBounds(100, 100, 700, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarPanels();
        iniciarLabels();
        iniciarFields();
        iniciarBotón();
    }

    public void iniciarPanels() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 700, 500);

        // Color de fondo cyan
        panel.setBackground(Color.CYAN);
    }

    public void iniciarLabels() {
        Login = new JLabel("LOGIN");
        Usuario = new JLabel("Ingrese su nombre de usuario: ");
        Password = new JLabel("Ingrese su contraseña: ");

        Login.setBounds(290, 25, 200, 40);
        Usuario.setBounds(120, 120, 220, 30);
        Password.setBounds(120, 170, 220, 30);

        // Fuente del título
        Login.setFont(new Font("Arial", Font.BOLD, 28));

        // Fuente de las etiquetas
        Usuario.setFont(new Font("Arial", Font.BOLD, 14));
        Password.setFont(new Font("Arial", Font.BOLD, 14));

        // Color azul oscuro para las letras
        Login.setForeground(new Color(0, 51, 102));
        Usuario.setForeground(new Color(0, 51, 102));
        Password.setForeground(new Color(0, 51, 102));
    }

    public void iniciarFields() {
        nombre = new JTextField();
        pass = new JPasswordField();

        nombre.setBounds(350, 120, 180, 30);
        pass.setBounds(350, 170, 180, 30);

        // Color de fondo de los campos
        nombre.setBackground(Color.WHITE);
        pass.setBackground(Color.WHITE);

        // Color de texto de los campos
        nombre.setForeground(Color.BLACK);
        pass.setForeground(Color.BLACK);

        // Color del borde
        nombre.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
        pass.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 2));
    }

    public void iniciarBotón() {
        ingresar = new JButton("Ingresar");
        ingresar.setBounds(290, 240, 120, 35);
        ingresar.addActionListener(this);

        // Color azul oscuro para el botón
        ingresar.setBackground(new Color(0, 51, 102));

        // Color blanco para el texto del botón
        ingresar.setForeground(Color.WHITE);

        // Fuente del botón
        ingresar.setFont(new Font("Arial", Font.BOLD, 14));

        // Quita el borde feo del botón
        ingresar.setFocusPainted(false);
    }

    public void AñadirComponentes() {
        panel.add(Login);
        panel.add(Usuario);
        panel.add(Password);
        panel.add(nombre);
        panel.add(pass);
        panel.add(ingresar);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ingresar) {
            String nuser = nombre.getText();
            String npassword = String.valueOf(pass.getPassword());

            String sql = "SELECT * FROM usuarios WHERE usuario=? AND pass=?";

            try {

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, nuser);
                ps.setString(2, npassword);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    JOptionPane.showMessageDialog(this,
                            "Bienvenido " + nuser);

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Usuario/contraseña incorrectos");
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error en la base de datos");
            }
        }
    }
}