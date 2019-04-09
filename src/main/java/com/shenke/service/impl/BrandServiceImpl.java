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
import com.shenke.entity.Brand;
import com.shenke.repository.BrandRepository;
import com.shenke.repository.DaoRepository;
import com.shenke.service.BrandService;
import com.shenke.util.StringUtil;

/**
 * 商标Service实现类
 * @author Administrator
 *
 */
@Service("brandService")
public class BrandServiceImpl implements BrandService{

	@Resource
	private BrandRepository brandRepository;

	@Override
	public List<Brand> list(Brand dao, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Brand> pageUser = brandRepository.findAll(new Specification<Brand>() {
			@Override
			public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (dao != null) {
					if (StringUtil.isNotEmpty(dao.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + dao.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Brand brand) {
		Long count = brandRepository.count(new Specification<Brand>() {

			@Override
			public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (brand != null) {
					if (StringUtil.isNotEmpty(brand.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + brand.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Brand findByBrandName(String name) {
		return brandRepository.findByBrandName(name);
	}

	@Override
	public void save(Brand dao) {
		brandRepository.save(dao);
	}

	@Override
	public Brand findById(Integer id) {
		return brandRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		brandRepository.delete(id);
	}

	@Override
	public List<Brand> findByName(String string) {
		return brandRepository.findByName(string);
	}

}
