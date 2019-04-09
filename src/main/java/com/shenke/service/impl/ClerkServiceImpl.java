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
import com.shenke.entity.Clerk;
import com.shenke.repository.ClerkRepository;
import com.shenke.service.ClerkService;
import com.shenke.util.StringUtil;

/**
 * 员工Service实现类
 * 
 * @author Administrator
 *
 */
@Service("clerkService")
public class ClerkServiceImpl implements ClerkService {

	@Resource
	private ClerkRepository clerkRepository;

	@Override
	public List<Clerk> findByDepId(Integer id) {
		return clerkRepository.findByDepId(id);
	}

	@Override
	public List<Clerk> list(Clerk goods, Integer page, Integer pageSize, Direction direction, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Clerk> pageGoods = clerkRepository.findAll(new Specification<Clerk>() {

			@Override
			public Predicate toPredicate(Root<Clerk> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (goods != null) {
					if (StringUtil.isNotEmpty(goods.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + goods.getName() + "%"));
					}
					if (goods.getDep() != null && goods.getDep().getId() != null && goods.getDep().getId() != 1) {
						predicate.getExpressions().add(cb.equal(root.get("dep").get("id"), goods.getDep().getId()));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageGoods.getContent();
	}

	@Override
	public Long getCount(Clerk goods) {
		Long count = clerkRepository.count(new Specification<Clerk>() {

			@Override
			public Predicate toPredicate(Root<Clerk> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (goods != null) {
					if (StringUtil.isNotEmpty(goods.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + goods.getName() + "%"));
					}
					if (goods.getDep() != null && goods.getDep().getId() != null && goods.getDep().getId() != 1) {
						predicate.getExpressions().add(cb.equal(root.get("dep").get("id"), goods.getDep().getId()));
					}
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public void save(Clerk clerk) {
		clerkRepository.save(clerk);
	}

	@Override
	public Clerk findById(Integer id) {
		return clerkRepository.findOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		clerkRepository.delete(id);
	}

	@Override
	public List<Clerk> findByName(String string) {
		return clerkRepository.findByName(string);
	}
	
}
