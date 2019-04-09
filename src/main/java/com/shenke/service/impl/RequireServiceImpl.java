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
import com.shenke.entity.Require;
import com.shenke.repository.RequireRepository;
import com.shenke.service.RequireService;
import com.shenke.util.StringUtil;

@Service("requireService")
public class RequireServiceImpl implements RequireService {
	
	@Resource
	private RequireRepository requireRepository;
	
	@Override
	public List<Require> list(Require Require, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Require> pageUser = requireRepository.findAll(new Specification<Require>() {
			@Override
			public Predicate toPredicate(Root<Require> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (Require != null) {
					if (StringUtil.isNotEmpty(Require.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + Require.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Require Require) {
		Long count = requireRepository.count(new Specification<Require>() {

			@Override
			public Predicate toPredicate(Root<Require> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (Require != null) {
					if (StringUtil.isNotEmpty(Require.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + Require.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Require findByRequireName(String name) {
		return requireRepository.findByRequireName(name);
	}

	@Override
	public void save(Require dao) {
		requireRepository.save(dao);
	}

	@Override
	public Require findById(Integer id) {
		return requireRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		requireRepository.delete(id);
	}

	@Override
	public List<Require> findByName(String string) {
		return requireRepository.findByName(string);
	}
}
