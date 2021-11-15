package uy.bse.catalogoaplicaciones.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

public class LazyCustomerDataModel extends LazyDataModel<ProductFrontEnd> {

	private static final long serialVersionUID = 1L;

	private List<ProductFrontEnd> datasource;

	public LazyCustomerDataModel(List<ProductFrontEnd> datasource) {
		super();
		this.datasource = datasource;
	}

	public List<ProductFrontEnd> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<ProductFrontEnd> datasource) {
		this.datasource = datasource;
	}

	@Override
	public List<ProductFrontEnd> load(int offset, int pageSize, Map<String, SortMeta> sortBy,
			Map<String, FilterMeta> filterBy) {
		List<ProductFrontEnd> data = new ArrayList<>();
		// filter
		for (ProductFrontEnd product : this.datasource) {
			boolean match = true;

			if (filterBy != null) {
				match = filter(FacesContext.getCurrentInstance(), filterBy.values(), product);
			}

			if (match) {
				data.add(product);
			}
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(offset, offset + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(offset, offset + (dataSize % pageSize));
			}
		} else {
			return data;
		}

	}

	private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
		boolean matching = true;

		for (FilterMeta filter : filterBy) {
			FilterConstraint constraint = filter.getConstraint();
			Object filterValue = filter.getFilterValue();

			try {
				Field field = o.getClass().getDeclaredField(filter.getField());
				field.setAccessible(true);
				Object columnValue = String.valueOf(field.get(o));

				matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
			} catch (ReflectiveOperationException e) {
				matching = false;
			}

			if (!matching) {
				break;
			}
		}

		return matching;
	}

}
