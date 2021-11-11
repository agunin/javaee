package uy.bse.catalogoaplicaciones.backingbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import uy.bse.catalogoaplicaciones.domain.Product;
import uy.bse.catalogoaplicaciones.ejbs.SearchViewService;

@Named("searchViewController")
@ViewScoped
public class SearchViewController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String componente = "";
	private String palabraClave = "";

	private List<Product> products;
    private Product selectedProduct;
    
	
	@Inject
	private SearchViewService searchViewService;

	public SearchViewController() {

	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
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
		products = searchViewService.getComponentes(componente, palabraClave);
		PrimeFaces.current().ajax().update("listaComponentes");
		return null;
	}
}
