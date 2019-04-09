package com.shenke.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shenke.entity.ProductType;
import com.shenke.repository.ProductTypeRepository;
import com.shenke.service.ProductTypeService;

/**
 * 产品及原料Type Service
 * 
 * @author Administrator
 *
 */
@Service("productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService {

	@Resource
	private ProductTypeRepository productTypeRepository;

	@Override
	public List<ProductType> findByParentId(Integer parentId) {
		return productTypeRepository.findByParentId(parentId);
	}

	@Override
	public ProductType findById(Integer id) {
		return productTypeRepository.findOne(id);
	}

	@Override
	public void save(ProductType ProductType) {
		productTypeRepository.save(ProductType);
	}

	@Override
	public void delete(Integer id) {
		productTypeRepository.delete(id);
	}

}
