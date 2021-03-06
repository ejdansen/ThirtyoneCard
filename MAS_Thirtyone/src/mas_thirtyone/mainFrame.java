
package mas_thirtyone;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;


public class mainFrame extends javax.swing.JFrame {
private ArrayList <card> cardPool; //array list for all cards
private ArrayList <player> playerPool;
private int numberofPlayer;
private int playerTurn=0;
   
    public mainFrame() {
        initComponents();
        pageSetup(); //page setup function
        
    }
    
    public void testWithRandomPlayer(){
        player p = new player( "P1", cardPool.get(2), cardPool.get(6), cardPool.get(15));
        player d = new player("P2", cardPool.get(3), cardPool.get(11), cardPool.get(6));
        
        System.out.println("Testing player p1 with these cards: "+p.hand[0].name+" , "+ p.hand[1].name+" , "+p.hand[2].name);
        System.out.println("Testing player p2 with these cards: "+d.hand[0].name+" , "+ d.hand[1].name+" , "+d.hand[2].name);
        
        System.out.println("Testing for player :"+p.name);
        System.out.println("First card: "+p.hand[0].name+" /type:"+p.hand[0].type+"/value: "+p.hand[0].value+" /suit: "+p.hand[0].suit+" \n"
                + "Second card: "+p.hand[1].name+" /type: "+p.hand[1].type+" /value: "+p.hand[1].value+" /suit: "+p.hand[1].suit+" \n"
                        + "Third card: "+p.hand[2].name+" /type:"+p.hand[2].type+"/value: "+p.hand[2].value+" /suit: "+p.hand[2].suit+" \n"
                                + "Does he have three of same suit? "+p.threeOfsameSuit+ " \n"
                                        + "Does he have only two of the same suit? "+p.twoOfSameSuit+"\n"
                                                + "What is his highest suit? "+p.highestSuit+"\n"
                                                         + "What is his highest card? "+p.highestCard.name+"\n"
                                                        + "Does he have three of a kind? "+p.threeOfAKind+"\n"
                                                        + "Does he have two of a kind? "+p.twoOfAKind+"\n"
                                                                + "Does p1 Knows he has "+p.hand[0].name+"? "+ p.doesKnowCard(p, p.hand[0])+"\n"
                                                                        + "Does p1 Knows he has "+cardPool.get(19).name+"? "+ p.doesKnowCard(p, cardPool.get(19))+""
                                                                                + "");
       card temp=p.hand[0];
        p.swapCard(p.hand[0],cardPool.get(19));
        System.out.println("Does p1 Knows he has "+temp.name+"? "+ p.doesKnowCard(p, temp)+"\n"
                                                                        + "Does p1 Knows he has "+cardPool.get(19).name+"? "+ p.doesKnowCard(p, cardPool.get(19))+""
                                                                                + "");
    }
   
    
    private void pageSetup()
    {
        this.cardPool=new ArrayList<card>(); //initi array list for all cards
        setCardPool(); //setting all the cards
        printPlayableCards();
        numberofPlayer=0;
        this.playerPool= new ArrayList<player>();
        
        System.out.println("Page setup was successful");
     
        viewPlayerComboBox.setVisible(true);
    }
    
    private void setupMatch(){
        
        //get number of players
        numberofPlayer=playerCombobox.getSelectedIndex()+1;
         //update combobox of viewing player
        viewPlayerComboBox.removeAllItems(); //removing all the boxes 
        viewPlayerComboBox.setVisible(true);
        
        //create players
        for(int i=0; i<=numberofPlayer;i++)
        {   //creating random player
//            card temp1= getArandomCard();
//            
//            card temp2= getArandomCard();
//            
//            card temp3= getArandomCard();
            
            playerPool.add(new player("p"+(i+1), getArandomCard(), getArandomCard(), getArandomCard()));
            playerPool.get(i).setPlayerNumber(i);
            viewPlayerComboBox.addItem(playerPool.get(i).name);
        }
        viewPlayerComboBox.addItem("Widow");
//         card temp1= getArandomCard();
//            card temp2=getArandomCard();
//            card temp3=getArandomCard();
         playerPool.add(new player("widow", getArandomCard(), getArandomCard(),getArandomCard()));
         playerPool.get(playerPool.size()-1).setPlayerNumber(100);
         
        for(int z=0;z<=playerPool.size()-1;z++)
        {
            playerPool.get(z).updateWidowKnowledge(playerPool.get(playerPool.size()-1));
        }
        
        

    }
    
   
    
    private card getArandomCard()
    {int randNumber= randInt(0, cardPool.size()-1);
        card temp = cardPool.get(randNumber);
        cardPool.remove(randNumber);
       
        
        return temp;
    }
    
  
    
    private void setCardPool()
    {
        //adding cards from 7 to 10 
        for (int i=7; i<=10;i++)
    {
        cardPool.add(new card("s"+i, i,"spade")); //spade suit
        cardPool.add(new card("d"+i, i, "dimond")); //dimond suit
        cardPool.add(new card("h"+i, i, "heart")); //heart suit
        cardPool.add(new card("c"+i, i, "club")); //club suit 
        
    }
     cardPool.add(new card("s"+"k", 10,"spade"));cardPool.add(new card("s"+"q", 10,"spade"));cardPool.add(new card("s"+"j", 10,"spade"));cardPool.add(new card("s"+"a", 11,"spade"));
     cardPool.add(new card("d"+"k", 10, "dimond"));cardPool.add(new card("d"+"q", 10, "dimond"));cardPool.add(new card("d"+"j", 10, "dimond"));cardPool.add(new card("d"+"a", 11, "dimond"));
     cardPool.add(new card("h"+"k", 10, "heart"));cardPool.add(new card("h"+"q", 10, "heart"));cardPool.add(new card("h"+"j", 10, "heart"));cardPool.add(new card("h"+"a", 11, "heart"));
     cardPool.add(new card("c"+"k", 10, "club"));cardPool.add(new card("c"+"q", 10, "club"));cardPool.add(new card("c"+"j", 10, "club"));cardPool.add(new card("c"+"a", 11, "club"));
    
     System.out.println("All cards created Successfully");
    }
    
    private void printPlayableCards()
    {
          jta_playablecards.setText(jta_playablecards.getText()+"Spade cards: ");
        for (int i=0;i<=cardPool.size()-1;i++)
        {
         if(cardPool.get(i).suit.equals("spade"))
         {
               jta_playablecards.setText(jta_playablecards.getText()+" , "+cardPool.get(i).name);
         }
        }
        jta_playablecards.setText(jta_playablecards.getText()+"\n");
         jta_playablecards.setText(jta_playablecards.getText()+"Dimond cards: ");
        for (int i=0;i<=cardPool.size()-1;i++)
        {
         if(cardPool.get(i).suit.equals("dimond"))
         {
               jta_playablecards.setText(jta_playablecards.getText()+" , "+cardPool.get(i).name);
         }
        }
        jta_playablecards.setText(jta_playablecards.getText()+"\n");
         jta_playablecards.setText(jta_playablecards.getText()+"Heart cards: ");
        for (int i=0;i<=cardPool.size()-1;i++)
        {
         if(cardPool.get(i).suit.equals("heart"))
         {
               jta_playablecards.setText(jta_playablecards.getText()+" , "+cardPool.get(i).name);
         }
        }
        jta_playablecards.setText(jta_playablecards.getText()+"\n");
         jta_playablecards.setText(jta_playablecards.getText()+"Club cards: ");
        for (int i=0;i<=cardPool.size()-1;i++)
        {
         if(cardPool.get(i).suit.equals("club"))
         {
               jta_playablecards.setText(jta_playablecards.getText()+" , "+cardPool.get(i).name);
         }
        }
        
        System.out.println("All cards were printed in text area successfully");
    }
    
    private int randInt(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        setupPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jta_playablecards = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        playerCombobox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        viewPlayerComboBox = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        answer = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));
        jPanel1.setLayout(new java.awt.CardLayout());

        jta_playablecards.setEditable(false);
        jta_playablecards.setColumns(20);
        jta_playablecards.setRows(5);
        jScrollPane1.setViewportView(jta_playablecards);

        jButton1.setText("Test with a Random Player ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("All the cards in cardpool");

        playerCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2 players", "3 players", "4 players", "5 players", "6 players", "7 players" }));

        jLabel2.setText("Select the number of players:");

        jButton2.setText("Start");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        viewPlayerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPlayerComboBoxActionPerformed(evt);
            }
        });

        answer.setColumns(20);
        answer.setRows(5);
        jScrollPane12.setViewportView(answer);

        jButton3.setText("show");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Make Sample ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout setupPaneLayout = new javax.swing.GroupLayout(setupPane);
        setupPane.setLayout(setupPaneLayout);
        setupPaneLayout.setHorizontalGroup(
            setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setupPaneLayout.createSequentialGroup()
                .addGroup(setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(setupPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(setupPaneLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(setupPaneLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addGroup(setupPaneLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(setupPaneLayout.createSequentialGroup()
                                .addComponent(viewPlayerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jButton3))
                            .addGroup(setupPaneLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, setupPaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(194, 194, 194))
        );
        setupPaneLayout.setVerticalGroup(
            setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(setupPaneLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(playerCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(10, 10, 10)
                .addComponent(jButton4)
                .addGap(29, 29, 29)
                .addGroup(setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(viewPlayerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(setupPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, setupPaneLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, setupPaneLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );

        jPanel1.add(setupPane, "card6");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, "card5");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, "card4");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, "card3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, "card2");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       testWithRandomPlayer();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     setupMatch();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void viewPlayerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPlayerComboBoxActionPerformed
    //showPan();
    
    }//GEN-LAST:event_viewPlayerComboBoxActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
player p = playerPool.get(viewPlayerComboBox.getSelectedIndex());
      String b =("Testing for player :"+p.name);
        String a =("First card: "+p.hand[0].name+" /type:"+p.hand[0].type+"/value: "+p.hand[0].value+" /suit: "+p.hand[0].suit+" \n"
                + "Second card: "+p.hand[1].name+" /type: "+p.hand[1].type+" /value: "+p.hand[1].value+" /suit: "+p.hand[1].suit+" \n"
                        + "Third card: "+p.hand[2].name+" /type:"+p.hand[2].type+"/value: "+p.hand[2].value+" /suit: "+p.hand[2].suit+" \n"
                                + "Does he have three of same suit? "+p.threeOfsameSuit+ " \n"
                                        + "Does he have only two of the same suit? "+p.twoOfSameSuit+"\n"
                                                + "What is his highest suit? "+p.highestSuit+"\n"
                                                         + "What is his highest card? "+p.highestCard.name+"\n"
                                                        + "Does he have three of a kind? "+p.threeOfAKind+"\n"
                                                        + "Does he have two of a kind? "+p.twoOfAKind+"\n"
                                                                + "Does p1 Knows he has "+p.hand[0].name+"? "+ p.doesKnowCard(p, p.hand[0])+"\n"
                                                                        + "hand's value "+p.handvalue+""
                                                                                + "");
        
        answer.setText(b+"\n"+a);        // TODO add your handling code here:
        for (int i=0; i<=p.knowsCard.size()-1;i++)
        {
           answer.setText(answer.getText()+"\n Player "+p.name+" knows that player "
                   +p.knowsCard.get(i).targetPlayer.name+ " has the card : "+p.knowsCard.get(i).targetCard.name);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
player selectedPlayer = playerPool.get(viewPlayerComboBox.getSelectedIndex());
        actionExchangeCard(selectedPlayer, selectedPlayer.hand[0], playerPool.get(playerPool.size()-1).hand[0]);

    }//GEN-LAST:event_jButton4ActionPerformed
   
    
    private void actionExchangeCard(player argPlayer, card argPlayercard, card argWidowcard){
        int playerNumber=0;
        for(int i=0;i<=playerPool.size()-1;i++)
        {
            if(playerPool.get(i).name.equals(argPlayer.name))
            {
                playerNumber=i;
                break;
            }
        }
        //updating widow cards
        playerPool.get(playerPool.size()-1).swapCard(argWidowcard, argPlayercard);
        System.out.println("widow has to lose : "+argWidowcard.name +"and pick up "+argPlayercard.name);
        playerPool.get(playerPool.size()-1).updateWidowKnowledge(playerPool.get(playerPool.size()-1));
        playerPool.get(playerPool.size()-1).updatePlayerEntireKnowledge();
        //update player cards
        playerPool.get(playerNumber).swapCard(argPlayercard, argWidowcard);
        playerPool.get(playerNumber).updateWidowKnowledge(playerPool.get(playerPool.size()-1));
        playerPool.get(playerNumber).updatePlayerEntireKnowledge();
      //  System.out.println(playerPool.get(playerNumber).name+" Exchanged with widow such that it dropped "+playerPool.get(playerPool.size()-1).hand[0].name
       // +" that meant to be "+argPlayercard.name+ " While it wanted to pick  "+argWidowcard.name+" and actually picked "+playerPool.get(playerNumber).hand[0].name);
    }
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
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea answer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JTextArea jta_playablecards;
    private javax.swing.JComboBox<String> playerCombobox;
    private javax.swing.JPanel setupPane;
    private javax.swing.JComboBox<String> viewPlayerComboBox;
    // End of variables declaration//GEN-END:variables
}
