package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import uy.bse.catalogoaplicaciones.admin.model.Document;
import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.AplicacionLenguaje;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.InterfaceTipo;
import uy.bse.catalogoaplicaciones.ejbs.InterfaceService;

@Named("treeSelectionView")
@ViewScoped
public class InterfacesWizardController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private TreeNode root3;

    private TreeNode[] selectedNodes2;

    
    // Referenciar al Aplicacion Controller
 	@Inject
 	AplicacionesWizardController aplicacionesWizardController;
 	
    //@Inject
   // private DocumentController service;

    @PostConstruct
    public void init() {
    	
    }

    public void initOnDemand() {
	    root3 = new CheckboxTreeNode(new Aplicacion("id", "dsc", AplicacionLenguaje.APEX), null);
	
		
		
		List<Aplicacion> applicacionesSeleccionadas = aplicacionesWizardController.getSelectedAplicaciones();
		
		
	    
		for (Aplicacion a : applicacionesSeleccionadas) {
			TreeNode aNode = new CheckboxTreeNode(a, root3);
		     
			
			for (Interface i : a.getProveeInterface()) {
				
	            new CheckboxTreeNode("document", i, aNode);
	
			}
	        
		}
    }
    
    

    public TreeNode getRoot3() {

        return root3;
    }

    

    

    public TreeNode[] getSelectedNodes2() {
        return selectedNodes2;
    }

    public void setSelectedNodes2(TreeNode[] selectedNodes2) {
        this.selectedNodes2 = selectedNodes2;
    }

    
    

    public void displaySelectedMultiple(TreeNode[] nodes) {
        if (nodes != null && nodes.length > 0) {
            StringBuilder builder = new StringBuilder();

            for (TreeNode node : nodes) {
                builder.append(node.getData().toString());
                builder.append("<br />");
            }

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", builder.toString());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}