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
import com.shenke.entity.Wight;
import com.shenke.repository.WightRepository;
import com.shenke.service.WightService;
import com.shenke.util.StringUtil;

/**
 * 重量设置Service实现类
 * 
 * @author Administrator
 *
 */
@Service("wightService")
public class WightServiceImpl implements WightService {

	@Resource
	private WightRepository wightRepository;

	@Override
	public List<Wight> list(Wight wight, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Wight> pageUser = wightRepository.findAll(new Specification<Wight>() {
			@Override
			public Predicate toPredicate(Root<Wight> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (wight != null) {
					if (StringUtil.isNotEmpty(wight.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + wight.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Wight wight) {
		Long count = wightRepository.count(new Specification<Wight>() {

			@Override
			public Predicate toPredicate(Root<Wight> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (wight != null) {
					if (StringUtil.isNotEmpty(wight.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + wight.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Wight findByWightName(String name) {
		return wightRepository.findByWightName(name);
	}

	@Override
	public void save(Wight wight) {
		wightRepository.save(wight);
	}

	@Override
	public Wight findById(Integer id) {
		return wightRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		wightRepository.delete(id);
	}

	@Override
	public List<Wight> findByName(String string) {
		return wightRepository.findByName(string);
	}
}
