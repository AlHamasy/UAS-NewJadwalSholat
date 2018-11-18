/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgnew.jadwal.sholat;

import jaco.mp3.player.MP3Player;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author asad
 */
public class UIJadwalSholat extends javax.swing.JFrame{

    
    
    /**
     * Creates new form UIJadwalSholat
     */
    public UIJadwalSholat() {
        initComponents();
        try {
            //getJadwal();
            getJadwalBaru("jakarta");
        } catch (Exception e) {
        }
    }
    
    //public String namaKota;
    
    //Handler handler;
    
    private void getJadwalBaru(String kota) throws Exception{
        
        // set bulan
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String bulan = sdf.format(new Date());
        
        // set tahun
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy");
        String tahun = sdfr.format(new Date());
        
        // set url 
        String url = "http://api.aladhan.com/v1/calendarByCity?city=" +kota+"&country=Indonesia&method=11&month=" +bulan+ "&year="+tahun+"";
        
        // get response
        String response = getResponse(url);
    
        // set response get json
        getJson(response);  
    }
    
    private String getResponse(String url) throws Exception{
        
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        // optional default is GET
        con.setRequestMethod("GET");
        
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        StringBuffer response;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        
        //print in String
        System.out.println(response.toString());
        
        return response.toString();
    }
    
    String currentHours;
    
    private void getJson(String responseJson) throws JSONException{
        
        JSONObject all = new JSONObject(responseJson);
        JSONArray data = all.getJSONArray("data");
       
        // set tanggal
        SimpleDateFormat sdfrt = new SimpleDateFormat("dd");
        String currentDate = sdfrt.format(new Date());
        int tanggalInt = Integer.parseInt(currentDate);
        
        JSONObject object = data.getJSONObject(tanggalInt-1);
        JSONObject timings = object.getJSONObject("timings");
        JSONObject date = object.getJSONObject("date");
       
        String readable = date.getString("readable");
        
        String subuh = timings.getString("Fajr");
        String dzuhur = timings.getString("Dhuhr");
        String ashar = timings.getString("Asr");
        String magrib = timings.getString("Maghrib");
        String isya = timings.getString("Isha");
        
        lblSubuh.setText(subuh);
        lblDzuhur.setText(dzuhur);
        lblAshar.setText(ashar);
        lblMagrib.setText(magrib);
        lblIsya.setText(isya);
        
        String coba = "06:44 (WIB)";
        
        // set jam menit
        ActionListener task = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat format = new SimpleDateFormat("kk:mm");
                currentHours = format.format(new Date()) + " (WIB)";
                lblTime.setText(currentHours);
                
                if(subuh.equals(currentHours) || dzuhur.equals(currentHours) || ashar.equals(currentHours) 
                    || magrib.equals(currentHours) || isya.equals(currentHours) || coba.equals(currentHours)){

                    adzan();
                    JOptionPane.showMessageDialog(rootPane, "masuk waktu sholat");
                    
                } 
            }    
        };
        new Timer(1000, task).start();  
    }
    
    private void adzan(){
        
        final String alamat = "/Users/asad/NetBeansProjects/TestAudio/audio/adzan.mp3";
        MP3Player player = new MP3Player(new File(alamat));
        player.play();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblSubuh = new javax.swing.JLabel();
        lblDzuhur = new javax.swing.JLabel();
        lblAshar = new javax.swing.JLabel();
        lblMagrib = new javax.swing.JLabel();
        lblIsya = new javax.swing.JLabel();
        cmbKota = new javax.swing.JComboBox<>();
        btnGantiDaerah = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("Jadwal Sholat ");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setText("Pilih Kota");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setText("Subuh");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setText("Dzuhur");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setText("Ashar");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setText("Magrib");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setText("Isya");

        lblSubuh.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        lblDzuhur.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        lblAshar.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        lblMagrib.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        lblIsya.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSubuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDzuhur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblAshar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMagrib, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIsya, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSubuh, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lblDzuhur, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblAshar, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblMagrib, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblIsya, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );

        cmbKota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jakarta", "Bandung", "Cirebon", "Semarang", "Yogyakarta", "Surabaya", "Malang", "Denpasar", "Aceh", "Medan", "Padang", "Pekanbaru", "Jambi", "Palembang", "Bengkulu", "Lampung", "Pangkalpinang", "Pontianak", "Samarinda", "Banjarmasin", "Palangka Raya", "Manado", "Palu", "Makassar", "Kendari", "Gorontalo", "Mamuju", "Ambon", "Jayapura", "Manokwari" }));
        cmbKota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKotaActionPerformed(evt);
            }
        });

        btnGantiDaerah.setText("Ganti Daerah");
        btnGantiDaerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGantiDaerahActionPerformed(evt);
            }
        });

        lblTime.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        lblTime.setText("Current Time");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Sekarang jam :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(159, 159, 159))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTime)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cmbKota, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGantiDaerah)
                                .addGap(47, 47, 47))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbKota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnGantiDaerah))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTime)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbKotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKotaActionPerformed
        
        //String pilihKota = (String) cmbKota.getSelectedItem();
//        try {
//            //getJadwal();
//            String namaKota = (String) cmbKota.getSelectedItem();
//            getJadwalBaru(namaKota);
//        } catch (Exception ex) {
//            System.out.println(ex);
//            Logger.getLogger(UIJadwalSholat.class.getName()).log(Level.SEVERE, null, ex);
//        } 

    }//GEN-LAST:event_cmbKotaActionPerformed

    private void btnGantiDaerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGantiDaerahActionPerformed
      
        try {
            //getJadwal();
            String namaKota = (String) cmbKota.getSelectedItem();
            getJadwalBaru(namaKota);
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(UIJadwalSholat.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_btnGantiDaerahActionPerformed

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIJadwalSholat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIJadwalSholat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIJadwalSholat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIJadwalSholat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIJadwalSholat().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGantiDaerah;
    private javax.swing.JComboBox<String> cmbKota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblAshar;
    private javax.swing.JLabel lblDzuhur;
    private javax.swing.JLabel lblIsya;
    private javax.swing.JLabel lblMagrib;
    private javax.swing.JLabel lblSubuh;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables

   
}
