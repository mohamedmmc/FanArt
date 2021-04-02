/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.utilis;

import com.esprit.dao.ServicePanier;
import com.esprit.dao.ServicePanier_elem;
import com.esprit.dao.ServiceProduit;
import com.esprit.dao.ServiceUser;
import com.esprit.dao.Session;
import com.esprit.entity.Panier_elem;
import com.esprit.entity.Produit;
import com.esprit.entity.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Juka
 */
public class pdf {
     public  void creepdf() {
          int prixtotal=0;
        ServiceUser serviceuser=new ServiceUser();
        User user = new User();
        user = serviceuser.findBymail(Session.getId());
        ServicePanier panier =new ServicePanier();
        int idpanier=panier.verif(Session.getId());
        List list = new ArrayList<>(getList(idpanier));
         try {
            
            String file_name="D:\\facture"+idpanier+".pdf";
            //serviceuser.sendphp(file_name);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            Paragraph para =new Paragraph("Nom:"+user.getNom());
            document.add(para);
            Paragraph para2 =new Paragraph("Prenom:"+user.getPrenom());
            document.add(para2);
            Paragraph para3 =new Paragraph("Facture N°: Facture"+idpanier);
            document.add(para3);
            //document.add(Image.getInstance("http://localhost:8080/img/logo.png"));
             PdfPTable table =new PdfPTable(4);
        // Step-4 Adding cells to the table
            table.addCell("Produit");
            table.addCell("Prix");
            table.addCell("Quantité");
            table.addCell("Prix total du produit");
             for (Iterator it = list.iterator(); it.hasNext();) {
            Panier_elem panier_elem = (Panier_elem) it.next();
            ServiceProduit serviceproduit =new ServiceProduit();
            Produit produit =new Produit();
            produit = serviceproduit.displayById(panier_elem.getId_produit()); 
            table.addCell(produit.getTitre());
            table.addCell(String.valueOf(produit.getPrix())+" DT");
            table.addCell(String.valueOf(panier_elem.getQuantite()));
            table.addCell(String.valueOf(panier_elem.getQuantite()*produit.getPrix()));
            prixtotal+=panier_elem.getQuantite()*produit.getPrix();
             }
             PdfPTable table1 =new PdfPTable(2);
             table1.addCell("Prix total");
             table1.addCell(prixtotal+" DT");
            // Paragraph para1 =new Paragraph("Prix total"+prixtotal+" DT");
  
        // Step-6 Adding Table to document
            document.add(table);
            document.add(table1);
            //document.add(para1);
            document.close();
              } catch (Exception ex) {
            System.err.println(ex);
        }
            }
      private List<Panier_elem> getList(int idpanier) {
        List<Panier_elem> list;
        ServicePanier_elem servicepanier_elem = ServicePanier_elem.getInstance();
        //list = servicepanier_elem.displayList();
        list = servicepanier_elem.displayListById(idpanier);
        return list;  
    }
            // TODO code application logic here 
}
