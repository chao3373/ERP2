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
import com.shenke.entity.Patu;
import com.shenke.repository.PatuRepository;
import com.shenke.service.PatuService;
import com.shenke.util.StringUtil;

/**
 * 纸管设置Service实现类
 * 
 * @author Administrator
 *
 */
@Service("patuService")
public class PatuServiceImpl implements PatuService {

	@Resource
	private PatuRepository patuRepository;

	@Override
	public List<Patu> list(Patu patu, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Patu> pageUser = patuRepository.findAll(new Specification<Patu>() {
			@Override
			public Predicate toPredicate(Root<Patu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (patu != null) {
					if (StringUtil.isNotEmpty(patu.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + patu.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Patu patu) {
		Long count = patuRepository.count(new Specification<Patu>() {

			@Override
			public Predicate toPredicate(Root<Patu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (patu != null) {
					if (StringUtil.isNotEmpty(patu.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + patu.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Patu findByPatuName(String name) {
		return patuRepository.findByPatuName(name);
	}

	@Override
	public void save(Patu dao) {
		patuRepository.save(dao);
	}

	@Override
	public Patu findById(Integer id) {
		return patuRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		patuRepository.delete(id);
	}

	@Override
	public List<Patu> findByName(String string) {
		return patuRepository.findByName(string);
	}

}
