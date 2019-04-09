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
import com.shenke.entity.OutIn;
import com.shenke.repository.OutInRepository;
import com.shenke.service.OutInService;
import com.shenke.util.StringUtil;

/**
 * 出入库Service实现类
 * 
 * @author Administrator
 *
 */
@Service("outInService")
public class OutInServiceImpl implements OutInService {

	@Resource
	private OutInRepository outInRepository;

	@Override
	public List<OutIn> list(OutIn outIn, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<OutIn> pageUser = outInRepository.findAll(new Specification<OutIn>() {
			@Override
			public Predicate toPredicate(Root<OutIn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (outIn != null) {
					if (StringUtil.isNotEmpty(outIn.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + outIn.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(OutIn outIn) {
		Long count = outInRepository.count(new Specification<OutIn>() {

			@Override
			public Predicate toPredicate(Root<OutIn> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (outIn != null) {
					if (StringUtil.isNotEmpty(outIn.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + outIn.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public OutIn findByOutInName(String name) {
		return outInRepository.findByOutInName(name);
	}

	@Override
	public void save(OutIn outIn) {
		outInRepository.save(outIn);
	}

	@Override
	public OutIn findById(Integer id) {
		return outInRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		outInRepository.delete(id);
	}

}
