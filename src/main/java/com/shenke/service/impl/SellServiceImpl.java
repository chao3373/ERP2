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

import com.shenke.entity.Client;
import com.shenke.entity.Sell;
import com.shenke.repository.SellRepository;
import com.shenke.service.SellService;
import com.shenke.util.StringUtil;

/**
 * 销售方式Service实现类
 * 
 * @author Administrator
 *
 */
@Service("sellService")
public class SellServiceImpl implements SellService {
	
	@Resource
	private SellRepository sellRepository;

	@Override
	public List<Sell> list(Sell sell, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Sell> pageUser = sellRepository.findAll(new Specification<Sell>() {
			@Override
			public Predicate toPredicate(Root<Sell> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (sell != null) {
					if (StringUtil.isNotEmpty(sell.getMethod())) {
						predicate.getExpressions().add(cb.like(root.get("method"), "%" + sell.getMethod() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Sell sell) {
		Long count = sellRepository.count(new Specification<Sell>() {

			@Override
			public Predicate toPredicate(Root<Sell> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (sell != null) {
					if (StringUtil.isNotEmpty(sell.getMethod())) {
						predicate.getExpressions().add(cb.like(root.get("method"), "%" + sell.getMethod() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Sell findBySellMethod(String method) {
		return sellRepository.findBySellMethod(method);
	}

	@Override
	public void save(Sell sell) {
		sellRepository.save(sell);
	}

	@Override
	public Sell findById(Integer id) {
		return sellRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		sellRepository.delete(id);
	}

	@Override
	public List<Sell> findByName(String string) {
		return sellRepository.findByName(string);
	}

}
