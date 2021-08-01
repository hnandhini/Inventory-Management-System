/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invshop;

import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HARITHA NANDHINI
 */
public class invoice extends javax.swing.JFrame {

    /**
     * Creates new form invoice
     */
    public invoice() {
        initComponents();
        connect();
        getBillNumber();
    }
    Connection con;
    PreparedStatement ps;
    PreparedStatement ps1;
    ResultSet rs;
    DefaultTableModel df;
    
    
    public void connect()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","123");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void getBillNumber()
    {
        try {
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select max(invid) from invoice");
            rs.next();
            rs.getString("MAX(invid)");
            if(rs.getString("MAX(invid)")==null)
            {
                txtbillno.setText("1");
            }
            else
            {
                int id=Integer.parseInt(rs.getString("MAX(invid)"));
                id++;
                String idd=String.valueOf(id);
                txtbillno.setText(idd);
        }
        } catch (SQLException ex) {
            Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void saless()
    {
        try {
            String id=txtbillno.getText();
            String subtotal=totcostinp.getText();
            String pay=payinp.getText();
            String bal=balinp.getText();
            ps=con.prepareStatement("insert into sales(invid,subtotal,pay,bal)values(?,?,?,?)");
            ps.setString(1,id);
            ps.setString(2,subtotal);
            ps.setString(3,pay);
            ps.setString(4,bal);
            ps.executeUpdate();
            int rows=jTable1.getRowCount();
            String idd=txtbillno.getText();
            ps1=con.prepareStatement("insert into invoice(invid,pname,cost,qty,total)values(?,?,?,?,?)");
            String pname="";
            String cost;
            String qty;
            int total=0;
            for(int i=0;i<jTable1.getRowCount();i++)
            {
                pname=(String)jTable1.getValueAt(i,0);
                cost=(String)jTable1.getValueAt(i, 1);
                qty=(String)jTable1.getValueAt(i, 2);
                total=(int)jTable1.getValueAt(i, 3);
                ps1.setString(1,idd);
                ps1.setString(2,pname);
                ps1.setString(3, cost);
                ps1.setString(4,qty);
                ps1.setInt(5,total);
                ps1.executeUpdate();
            }
            JOptionPane.showMessageDialog(this, "Sales Completed");

            
        } catch (SQLException ex) {
            Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void bill()
    {
        df=(DefaultTableModel)jTable1.getModel();
        txtbill.setText(txtbill.getText()+"****************************************************************\n");
        txtbill.setText(txtbill.getText()+"               *          INVOICE               *               \n");
        txtbill.setText(txtbill.getText()+"****************************************************************\n");
        txtbill.setText(txtbill.getText()+"Bill No. "+txtbillno.getText()+"\n\n");
        txtbill.setText(txtbill.getText()+"Product "+"\t\t"+"Price"+"\t\t"+"Quantity"+"\t"+"Amount"+"\n");
        txtbill.setText(txtbill.getText()+"----------------------------------------------------------------\n");
        for(int i=0;i<df.getRowCount();i++)
        {
            String pname=(String)df.getValueAt(i, 0).toString();
            String cost=(String)df.getValueAt(i, 1).toString();
            String qty= (String)df.getValueAt(i,2).toString();
            String amt=(String)df.getValueAt(i,3).toString();
             txtbill.setText(txtbill.getText()+pname+"\t\t"+cost+"\t\t"+qty+"\t\t"+amt+"\n");
        }
        int total=Integer.parseInt(totcostinp.getText());
        int pay=Integer.parseInt(payinp.getText());
        int bal=Integer.parseInt(balinp.getText());
        txtbill.setText(txtbill.getText()+"\n\n\n\n\n\n");
        txtbill.setText(txtbill.getText()+"\n");
        txtbill.setText(txtbill.getText()+"\t"+"\t"+"\t"+"Total :"+total+"\n");
        txtbill.setText(txtbill.getText()+"\t"+"\t"+"\t"+"Paid :"+pay+"\n");
        txtbill.setText(txtbill.getText()+"\t"+"\t"+"\t"+"Balance :"+bal+"\n\n\n\n\n");
        txtbill.setText(txtbill.getText()+"................................................................\n");
        txtbill.setText(txtbill.getText()+"               *          THANK YOU             *               ");                
    }
    
    public void print()
    {
        try {
            txtbill.print();
        } catch (PrinterException ex) {
            Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pcodeinp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pnameinp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        qtyinp = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        costinp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        totcostinp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        payinp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        balinp = new javax.swing.JTextField();
        addbut = new javax.swing.JButton();
        addbut1 = new javax.swing.JButton();
        addbut3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        printbut = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtbill = new javax.swing.JTextArea();
        addbut2 = new javax.swing.JButton();
        txtbillno = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Invoice");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel3.setText("Product Code:");

        pcodeinp.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        pcodeinp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcodeinpActionPerformed(evt);
            }
        });
        pcodeinp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pcodeinpKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel4.setText("Product Name:");

        pnameinp.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        pnameinp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnameinpActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel5.setText("Quantity");

        qtyinp.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        qtyinp.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qtyinpStateChanged(evt);
            }
        });
        qtyinp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                qtyinpMouseReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel6.setText("Cost");

        costinp.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel7.setText("Total cost:");

        totcostinp.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel8.setText("Pay:");

        payinp.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        payinp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payinpActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel9.setText("Balance:");

        balinp.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N

        addbut.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        addbut.setText("ADD");
        addbut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbutActionPerformed(evt);
            }
        });

        addbut1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        addbut1.setText("BACK");
        addbut1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addbut1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbut1ActionPerformed(evt);
            }
        });

        addbut3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        addbut3.setText("RESET");
        addbut3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addbut3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbut3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnameinp, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(costinp, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pcodeinp, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addbut)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addbut3))))
                    .addComponent(qtyinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(totcostinp, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                        .addComponent(payinp)
                        .addComponent(balinp))
                    .addComponent(addbut1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pcodeinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(totcostinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pnameinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(payinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(costinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(balinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(qtyinp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addbut)
                            .addComponent(addbut1)
                            .addComponent(addbut3))
                        .addGap(31, 31, 31))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 740, 340));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setText("INVENTORY INVOICE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 400, 60));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Cost", "Quantity", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 680, 380));

        printbut.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        printbut.setText("Print Invoice");
        printbut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbutActionPerformed(evt);
            }
        });
        printbut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                printbutKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                printbutKeyReleased(evt);
            }
        });
        getContentPane().add(printbut, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 890, -1, -1));

        txtbill.setColumns(20);
        txtbill.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        txtbill.setRows(5);
        jScrollPane2.setViewportView(txtbill);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 180, 530, 610));

        addbut2.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        addbut2.setText("Log Out");
        addbut2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addbut2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbut2ActionPerformed(evt);
            }
        });
        getContentPane().add(addbut2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 20, 110, 40));

        txtbillno.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        txtbillno.setText("Bill No");
        getContentPane().add(txtbillno, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 80, -1));

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel11.setText("Bill No.");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 80, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\HARITHA NANDHINI\\Pictures\\The-Color-Grey-1.jpg")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 940));

        setSize(new java.awt.Dimension(1419, 992));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pcodeinpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcodeinpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pcodeinpActionPerformed

    private void payinpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payinpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payinpActionPerformed

    private void printbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbutActionPerformed
        // TODO add your handling code here:
        int pay=Integer.parseInt(payinp.getText());
        int totalcost=Integer.parseInt(totcostinp.getText());
        int bal=pay-totalcost;
        balinp.setText(String.valueOf(bal));
        saless();
        bill();
        print();

        
        
        
    }//GEN-LAST:event_printbutActionPerformed

    private void pcodeinpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pcodeinpKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            try {
                String pcode=pcodeinp.getText();
                ps=con.prepareStatement("select * from prodentry where pcode=?");
                ps.setString(1,pcode);
                rs=ps.executeQuery();
                if(rs.next()==false)
                {
                    JOptionPane.showMessageDialog(this, "Product Code Not Found");
                }
                else
                {
                    String pname=rs.getString("pname");
                    pnameinp.setText(pname.trim());
                    String cost=rs.getString("cost");
                    costinp.setText(cost.trim());
                    qtyinp.requestFocus();
                }
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }//GEN-LAST:event_pcodeinpKeyPressed

    private void addbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbutActionPerformed
        // TODO add your handling code here:
        int cost=Integer.parseInt(costinp.getText());
        int qty=Integer.parseInt(qtyinp.getValue().toString());
        int tot=cost*qty;
        df=(DefaultTableModel)jTable1.getModel();
        df.addRow(new Object[]
        {
            pnameinp.getText(),
            costinp.getText(),
            qtyinp.getValue().toString(),
            tot
        });
        int sum=0;
        for(int i=0;i<jTable1.getRowCount();i++)
        {
            sum=sum+Integer.parseInt(jTable1.getValueAt(i,3).toString());
        }
        totcostinp.setText(String.valueOf(sum));
        try {
            // TODO add your handling code here:
            String quanty=qtyinp.getValue().toString();
            ps=con.prepareStatement("select qty from prodentry where pname=?");
             ps.setString(1,pnameinp.getText());
             rs=ps.executeQuery();
              if(rs.next()==true)
                {
                  String qtty=rs.getString("qty");
                  int uqty=Integer.valueOf(qtty)-Integer.valueOf(quanty);
                  String uq=String.valueOf(uqty);
                  ps=con.prepareStatement("update prodentry set qty= ? where pname=?");
                  ps.setString(1,uq);
                  ps.setString(2,pnameinp.getText());
                  ps.executeUpdate();
                  

                }
              else
              {
                  JOptionPane.showMessageDialog(this, "Database Error");
              }
            
        } catch (SQLException ex) {
            Logger.getLogger(invoice.class.getName()).log(Level.SEVERE, null, ex);
        }
        pcodeinp.setText("");
        pnameinp.setText("");
        costinp.setText("");
        qtyinp.setValue(0);
        pcodeinp.requestFocus();
        
        
        
    }//GEN-LAST:event_addbutActionPerformed

    private void qtyinpStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qtyinpStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_qtyinpStateChanged

    private void addbut1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbut1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        psales ps=new psales();
        ps.setVisible(true);
    }//GEN-LAST:event_addbut1ActionPerformed

    private void addbut2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbut2ActionPerformed
        // TODO add your handling code here:
        dispose();
        System.exit(0);
    }//GEN-LAST:event_addbut2ActionPerformed

    private void printbutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printbutKeyPressed
        // TODO add your handling code here:
      
        
    }//GEN-LAST:event_printbutKeyPressed

    private void qtyinpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtyinpMouseReleased
        

        
    }//GEN-LAST:event_qtyinpMouseReleased

    private void printbutKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_printbutKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_printbutKeyReleased

    private void addbut3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbut3ActionPerformed
        // TODO add your handling code here
        totcostinp.setText("");
        payinp.setText("");
        balinp.setText("");
        txtbill.setText("");
        getBillNumber();
        df.setRowCount(0);
       
    }//GEN-LAST:event_addbut3ActionPerformed

    private void pnameinpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnameinpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnameinpActionPerformed

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
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new invoice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbut;
    private javax.swing.JButton addbut1;
    private javax.swing.JButton addbut2;
    private javax.swing.JButton addbut3;
    private javax.swing.JTextField balinp;
    private javax.swing.JTextField costinp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField payinp;
    private javax.swing.JTextField pcodeinp;
    private javax.swing.JTextField pnameinp;
    private javax.swing.JButton printbut;
    private javax.swing.JSpinner qtyinp;
    private javax.swing.JTextField totcostinp;
    private javax.swing.JTextArea txtbill;
    private javax.swing.JLabel txtbillno;
    // End of variables declaration//GEN-END:variables
}
