/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalam_s6.GUI.NewGame;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import static java.lang.Math.round;
import javax.swing.ImageIcon;

/**
 *
 * @author loic
 */
public class NewGameAdapterListener implements ComponentListener {

    private final GUI_NewGame ng;

    public NewGameAdapterListener(GUI_NewGame newgame) {
        this.ng = newgame;
    }

    //JButton player, prec, sup, load, retour, start;
    @Override
    public void componentResized(ComponentEvent e) {
        Insets insets = this.ng.getInsets();
        Image newimg;
        Dimension size;
        
        //1920-1080 => taille de base
        double ratioW = (double) this.ng.getWidth() / (double) 1920;
        double ratioH = (double) this.ng.getHeight() / (double) 1080;
        
        newimg = this.ng.getReturnI().getScaledInstance(((int) round(263 * ratioW)), ((int) round(123 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getRetour().setIcon(new ImageIcon(newimg));
        this.ng.getRetour().setBounds((int) round((25 + insets.left) * ratioW), ((int) round((934 + insets.top) * ratioH)), (int) round(263 * ratioW), (int) round(123 * ratioH));
        this.ng.getRetour().setSize((int) round(263 * ratioW), (int) round(123 * ratioH));

        newimg = this.ng.getStartI().getScaledInstance(((int) round(1012 * ratioW)), ((int) round(141 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getStart().setIcon(new ImageIcon(newimg));
        this.ng.getStart().setBounds((int) round((449 + insets.left) * ratioW), ((int) round((904 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getStart().setSize((int) round(1012 * ratioW), (int) round(141 * ratioH));
        
        
        newimg = this.ng.getPrecI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getPreccolor1().setIcon(new ImageIcon(newimg));
        this.ng.getPreccolor1().setBounds((int) round((15 + insets.left) * ratioW), ((int) round((389 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getPreccolor1().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getColorImgs()[this.ng.getP1colorselect()].getScaledInstance(((int) round(412 * ratioW)), ((int) round(103 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getP1color().setIcon(new ImageIcon(newimg));
        this.ng.getP1color().setBounds((int) round((129 + insets.left) * ratioW), ((int) round((394 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getP1color().setSize((int) round(412 * ratioW), (int) round(103 * ratioH));
        
        newimg = this.ng.getSupI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getSupcolor1().setIcon(new ImageIcon(newimg));
        this.ng.getSupcolor1().setBounds((int) round((536 + insets.left) * ratioW), ((int) round((387 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getSupcolor1().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getPrecI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getPreccolor2().setIcon(new ImageIcon(newimg));
        this.ng.getPreccolor2().setBounds((int) round((1267 + insets.left) * ratioW), ((int) round((389 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getPreccolor2().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        newimg = this.ng.getColorImgs()[this.ng.getP2colorselect()].getScaledInstance(((int) round(412 * ratioW)), ((int) round(103 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getP2color().setIcon(new ImageIcon(newimg));
        this.ng.getP2color().setBounds((int) round((1381 + insets.left) * ratioW), ((int) round((394 + insets.top) * ratioH)), (int) round(1012 * ratioW), (int) round(141 * ratioH));
        this.ng.getP2color().setSize((int) round(412 * ratioW), (int) round(103 * ratioH));
        
        newimg = this.ng.getSupI().getScaledInstance(((int) round(117 * ratioW)), ((int) round(117 * ratioH)), java.awt.Image.SCALE_SMOOTH);
        this.ng.getSupcolor2().setIcon(new ImageIcon(newimg));
        this.ng.getSupcolor2().setBounds((int) round((1788 + insets.left) * ratioW), ((int) round((387 + insets.top) * ratioH)), (int) round(117 * ratioW), (int) round(117 * ratioH));
        this.ng.getSupcolor2().setSize((int) round(117 * ratioW), (int) round(117 * ratioH));
        
        size = this.ng.getPrec1().getPreferredSize();
        this.ng.getPrec1().setBounds((int) round((15 + insets.left) * ratioW), ((int) round((577 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec1().setSize((int) round(this.ng.getPrec1().getWidth() * ratioW), (int) round(this.ng.getPrec1().getHeight() * ratioH));
        size = this.ng.getP1button().getPreferredSize();
        this.ng.getP1button().setBounds((int) round((129 + insets.left) * ratioW), ((int) round((581 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP1button().setSize((int) round(this.ng.getP1button().getWidth() * ratioW), (int) round(this.ng.getP1button().getHeight() * ratioH));
        size = this.ng.getSup1().getPreferredSize();
        this.ng.getSup1().setBounds((int) round((536 + insets.left) * ratioW), ((int) round((575 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup1().setSize((int) round(this.ng.getSup1().getWidth() * ratioW), (int) round(this.ng.getSup1().getHeight() * ratioH));

        size = this.ng.getPrec2().getPreferredSize();
        this.ng.getPrec2().setBounds((int) round((1267 + insets.left) * ratioW), ((int) round((577 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrec2().setSize((int) round(this.ng.getPrec2().getWidth() * ratioW), (int) round(this.ng.getPrec2().getHeight() * ratioH));
        size = this.ng.getP2button().getPreferredSize();
        this.ng.getP2button().setBounds((int) round((1381 + insets.left) * ratioW), ((int) round((581 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getP2button().setSize((int) round(this.ng.getP2button().getWidth() * ratioW), (int) round(this.ng.getP2button().getHeight() * ratioH));
        size = this.ng.getSup2().getPreferredSize();
        this.ng.getSup2().setBounds((int) round((1788 + insets.left) * ratioW), ((int) round((575 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSup2().setSize((int) round(this.ng.getSup2().getWidth() * ratioW), (int) round(this.ng.getSup2().getHeight() * ratioH));

        size = this.ng.getName1().getPreferredSize();
        this.ng.getName1().setBounds((int) round((180 + insets.left) * ratioW), ((int) round((776 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getName1().setSize((int) round(this.ng.getName1().getWidth() * ratioW), (int) round(this.ng.getName1().getHeight() * ratioH));
        size = this.ng.getName2().getPreferredSize();
        this.ng.getName2().setBounds((int) round((1432 + insets.left) * ratioW), ((int) round((776 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getName2().setSize((int) round(this.ng.getName2().getWidth() * ratioW), (int) round(this.ng.getName2().getHeight() * ratioH));

        size = this.ng.getPrecgrille().getPreferredSize();
        this.ng.getPrecgrille().setBounds((int) round((670 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getPrecgrille().setSize((int) round(this.ng.getPrecgrille().getWidth() * ratioW), (int) round(this.ng.getPrecgrille().getHeight() * ratioH));
        
        size = this.ng.getSupgrille().getPreferredSize();
        this.ng.getSupgrille().setBounds((int) round((1156 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getSupgrille().setSize((int) round(this.ng.getSupgrille().getWidth() * ratioW), (int) round(this.ng.getSupgrille().getHeight() * ratioH));
        
        size = this.ng.getGrilleName().getPreferredSize();
        this.ng.getGrilleName().setBounds((int) round((769 + insets.left) * ratioW), ((int) round((229 + insets.top) * ratioH)), size.width, size.height);
        this.ng.getGrilleName().setSize((int) round(384 * ratioW), (int) round(100 * ratioH));
        
        this.ng.callResize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}
