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
import com.shenke.entity.Entrepot;
import com.shenke.repository.EntrepotRepository;
import com.shenke.service.EntrepotService;
import com.shenke.util.StringUtil;

/**
 * 仓库设置Service实现类
 * 
 * @author Administrator
 *
 */
@Service("entrepotService")
public class EntrepotServiceImpl implements EntrepotService {

	@Resource
	private EntrepotRepository entrepotRepository;

	@Override
	public List<Entrepot> findByEntrepotTypeId(Integer id) {
		return entrepotRepository.findByEntrepotTypeId(id);
	}

	@Override
	public List<Entrepot> list(Entrepot goods, Integer page, Integer pageSize, Direction direction, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Entrepot> pageGoods = entrepotRepository.findAll(new Specification<Entrepot>() {
			@Override
			public Predicate toPredicate(Root<Entrepot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Long getCount(Entrepot goods) {
		Long count = entrepotRepository.count(new Specification<Entrepot>() {

			@Override
			public Predicate toPredicate(Root<Entrepot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public void save(Entrepot Entrepot) {
		entrepotRepository.save(Entrepot);
	}

	@Override
	public Entrepot findById(Integer id) {
		return entrepotRepository.findOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		entrepotRepository.delete(id);
	}
}
