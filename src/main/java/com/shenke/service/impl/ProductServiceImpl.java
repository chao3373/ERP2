package com.shenke.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.shenke.entity.Product;
import com.shenke.repository.ProductRepository;
import com.shenke.service.ProductService;
import com.shenke.util.StringUtil;

/**
 * 产品及原料Service 实现类
 * 
 * @author Administrator
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductRepository productRepository;

	@Override
	public List<Product> findByProductTypeId(Integer id) {
		return productRepository.findByProductTypeId(id);
	}

	@Override
	public List<Product> list(Product goods, Integer page, Integer pageSize, Direction direction, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Product> pageGoods = productRepository.findAll(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (goods != null) {
					if (StringUtil.isNotEmpty(goods.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + goods.getName() + "%"));
					}
					if (goods.getType() != null && goods.getType().getId() != null && goods.getType().getId() != 1) {
						predicate.getExpressions().add(cb.equal(root.get("type").get("id"), goods.getType().getId()));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageGoods.getContent();
	}

	@Override
	public Long getCount(Product goods) {
		Long count = productRepository.count(new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (goods != null) {
					if (StringUtil.isNotEmpty(goods.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + goods.getName() + "%"));
					}
					if (goods.getType() != null && goods.getType().getId() != null && goods.getType().getId() != 1) {
						predicate.getExpressions().add(cb.equal(root.get("type").get("id"), goods.getType().getId()));
					}
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public void save(Product Product) {
		productRepository.save(Product);
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.delete(id);
	}

	@Override
	public List<Product> findByName(String string) {
		return productRepository.findByName(string);
	}

}
