import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KahveDiyari extends JFrame {
    private JComboBox<String> kahveSecimi;
    private JComboBox<String> sekerSecimi;
    private JButton hazirlaButton;
    private JTextArea ciktiAlani;
    private JLabel baslikLabel;

    public KahveDiyari() {
        // Genel pencere ayarları
        setTitle("s Kahve Diyarı Otomasyonu");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Arka plan paneli
        JPanel arkaPlan = new JPanel(new BorderLayout());
        arkaPlan.setBackground(new Color(245, 222, 179)); // kahve tonlu arka plan

        // Başlık
        baslikLabel = new JLabel("KAHVE DİYARI ", SwingConstants.CENTER);
        baslikLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        baslikLabel.setForeground(new Color(92, 64, 51));
        baslikLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        // Seçim paneli (kahve ve şeker)
        JPanel secimPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        secimPanel.setBackground(new Color(245, 222, 179));
        secimPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JLabel kahveLabel = new JLabel("Kahve Seçimi:");
        kahveLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        kahveSecimi = new JComboBox<>(new String[]{
                "Türk Kahvesi",
                "Espresso",
                "Latte",
                "Americano",
                "Cappuccino"
        });
        kahveSecimi.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JLabel sekerLabel = new JLabel(" Şeker Miktarı:");
        sekerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        sekerSecimi = new JComboBox<>(new String[]{
                "Şekersiz",
                "Az Şekerli",
                "Orta Şekerli",
                "Şekerli"
        });
        sekerSecimi.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        secimPanel.add(kahveLabel);
        secimPanel.add(kahveSecimi);
        secimPanel.add(sekerLabel);
        secimPanel.add(sekerSecimi);

        // Buton
        hazirlaButton = new JButton(" Kahveyi Hazırla");
        hazirlaButton.setBackground(new Color(139, 69, 19));
        hazirlaButton.setForeground(Color.WHITE);
        hazirlaButton.setFocusPainted(false);
        hazirlaButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        hazirlaButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Çıktı alanı
        ciktiAlani = new JTextArea();
        ciktiAlani.setEditable(false);
        ciktiAlani.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ciktiAlani.setBackground(new Color(255, 250, 240));
        ciktiAlani.setLineWrap(true);
        ciktiAlani.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(ciktiAlani);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Hazırlanma Durumu"));

        // Buton işlevi
        hazirlaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kahve = (String) kahveSecimi.getSelectedItem();
                String seker = (String) sekerSecimi.getSelectedItem();
                ciktiAlani.setText("Kahveniz hazırlanıyor...\n");
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                        ciktiAlani.append("→ " + kahve + " seçildi.\n");
                        Thread.sleep(1000);
                        ciktiAlani.append("→ " + seker + " tercih edildi.\n");
                        Thread.sleep(1000);
                        ciktiAlani.append("→ Sıcak su ekleniyor...\n");
                        Thread.sleep(1000);
                        ciktiAlani.append("→ Kahve karıştırılıyor...\n");
                        Thread.sleep(1000);
                        ciktiAlani.append("\n Kahveniz hazır! Afiyet olsun ");
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });

        // Alt panel (buton + çıktı)
        JPanel altPanel = new JPanel(new BorderLayout(10, 10));
        altPanel.setBackground(new Color(245, 222, 179));
        altPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        altPanel.add(hazirlaButton, BorderLayout.NORTH);
        altPanel.add(scrollPane, BorderLayout.CENTER);

        // Ana düzen
        arkaPlan.add(baslikLabel, BorderLayout.NORTH);
        arkaPlan.add(secimPanel, BorderLayout.CENTER);
        arkaPlan.add(altPanel, BorderLayout.SOUTH);

        add(arkaPlan);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KahveDiyari::new);
    }
}
