package uy.bse.catalogoaplicaciones.backingbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

import uy.bse.catalogoaplicaciones.domain.Aplicacion;
import uy.bse.catalogoaplicaciones.domain.AplicacionLenguaje;
import uy.bse.catalogoaplicaciones.domain.ComponenteSoftware;
import uy.bse.catalogoaplicaciones.domain.Interface;
import uy.bse.catalogoaplicaciones.domain.Product;
import uy.bse.catalogoaplicaciones.ejbs.SearchViewService;
import uy.bse.catalogoaplicaciones.model.LazyCustomerDataModel;
import uy.bse.catalogoaplicaciones.model.ProductFrontEnd;

@Named("searchViewController")
@ViewScoped
public class SearchViewController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String componente = "";
	private String palabraClave = "";

	private List<ProductFrontEnd> products;
	private ProductFrontEnd selectedProduct;

	private LazyDataModel<ProductFrontEnd> lazyModel;

	@Inject
	private SearchViewService searchViewService;

	public SearchViewController() {

	}

	public ProductFrontEnd getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductFrontEnd selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public List<ProductFrontEnd> getProducts() {
		return products;
	}

	public void setProducts(List<ProductFrontEnd> products) {
		this.products = products;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getPalabraClave() {
		return palabraClave;
	}

	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	public String buscar() {
		List<Product> productsBackend = searchViewService.getComponentes(componente, palabraClave);
		construirProductFrontEnd(productsBackend);
		PrimeFaces.current().ajax().update("form:listaComponentes");
		return null;
	}

	public LazyDataModel<ProductFrontEnd> getLazyModel() {
		return lazyModel;
	}

	private void construirProductFrontEnd(List<Product> productsBackend) {
		List<ProductFrontEnd> products = new ArrayList<ProductFrontEnd>();
		for (Product productBackend : productsBackend) {
			ProductFrontEnd pfe = new ProductFrontEnd();
			pfe.setDescripcion(productBackend.getDescripcion());
			pfe.setId(productBackend.getId());
			pfe.setIdentificador(productBackend.getIdentificador());
			pfe.setTipoComponente(productBackend.getTipoComponente());
			pfe.setRoot3(null);
			pfe.setComponentes(productBackend.getComponentes());
			pfe.setRoles(productBackend.getRoles());
			products.add(pfe);
		}
		construirTreeNodeParaSolucion(products);

		this.products = products;

		this.lazyModel = new LazyCustomerDataModel(products);
	}

	private void construirTreeNodeParaSolucion(List<ProductFrontEnd> products) {
		for (ProductFrontEnd product : products) {
			if (product.getTipoComponente().equals("SOLUCION")) {
				TreeNode root3 = new CheckboxTreeNode(new Aplicacion("id", "dsc", AplicacionLenguaje.APEX), null);

				for (ComponenteSoftware cs : product.getComponentes()) {
					Aplicacion a = (Aplicacion) cs;

					TreeNode aNode = new CheckboxTreeNode("app", a, root3);

					aNode.setSelectable(false);
					aNode.setExpanded(true);

					for (Interface i : a.getProveeInterface()) {
						TreeNode ti = new CheckboxTreeNode("pinterface", i, aNode);
					}
				}

				product.setRoot3(root3);
			}
		}
	}
}
