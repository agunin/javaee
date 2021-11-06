package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.view.ViewScoped;

import uy.bse.catalogoaplicaciones.admin.model.Document;
import javax.inject.Named;

import org.primefaces.model.CheckboxTreeNode;

import org.primefaces.model.TreeNode;

@Named("documentController")
@ViewScoped
public class DocumentController implements Serializable{

   

    public TreeNode createCheckboxDocuments() {
        TreeNode root = new CheckboxTreeNode(new Document("Files", "-", "Folder"), null);

        
        TreeNode cloud = new CheckboxTreeNode(new Document("Cloud", "20kb", "Folder"), root);
       
        //Cloud
        TreeNode backup1 = new CheckboxTreeNode("document", new Document("backup-1.zip", "10kb", "Zip"), cloud);
        TreeNode backup2 = new CheckboxTreeNode("document", new Document("backup-2.zip", "10kb", "Zip"), cloud);

        return root;
    }
}