package uy.bse.catalogoaplicaciones.admin.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.AplicacionLenguaje;
import uy.bse.catalogoaplicaciones.domain.Interface;

@Named("treeSelectionView")
@ViewScoped
public class InterfacesWizardController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TreeNode root3;

	private TreeNode[] selectedNodes2;

	private List<Interface> interfaces = new ArrayList<Interface>();

	/**
	 * @return the interfaces
	 */
	public List<Interface> getInterfaces() {
		return interfaces;
	}

	/**
	 * @param interfaces the interfaces to set
	 */
	public void setInterfaces(List<Interface> interfaces) {
		this.interfaces = interfaces;
	}

	@Inject
	AplicacionesWizardController aplicacionesWizardController;


	@PostConstruct
	public void init() {

	}

	public void initOnDemand() {
		root3 = new CheckboxTreeNode(new Aplicacion("id", "dsc", AplicacionLenguaje.APEX), null);

		List<Aplicacion> applicacionesSeleccionadas = aplicacionesWizardController.getSelectedAplicaciones();

		if (interfaces.size() > 0) {
			selectedNodes2 = new TreeNode[interfaces.size()];
		}
		
		for (Aplicacion a : applicacionesSeleccionadas) {
			
			if(a.getProveeInterface().size()>0) {
				TreeNode aNode = new CheckboxTreeNode("app", a, root3);
	
				aNode.setSelectable(false);
				aNode.setExpanded(true);
	
				for (Interface i : a.getProveeInterface()) {
	
					TreeNode ti = new CheckboxTreeNode("pinterface", i, aNode);
	
					for (Interface inter : interfaces) {
						if (inter.getId() == i.getId()) {
							ti.setSelected(true);
							
						}
					}
	
				}
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