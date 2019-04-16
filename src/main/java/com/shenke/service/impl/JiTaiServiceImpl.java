package com.shenke.service.impl;

import java.util.List;
import java.util.Map;
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
import com.shenke.entity.JiTai;
import com.shenke.repository.JiTaiRepository;
import com.shenke.service.JiTaiService;
import com.shenke.util.StringUtil;

/**
 * 机台Service实现类
 * 
 * @author Administrator
 *
 */
@Service("jiTaiService")
public class JiTaiServiceImpl implements JiTaiService {

	@Resource
	private JiTaiRepository jiTaiRepository;

	@Override
	public List<JiTai> list(JiTai group, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<JiTai> pageUser = jiTaiRepository.findAll(new Specification<JiTai>() {
			@Override
			public Predicate toPredicate(Root<JiTai> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (group != null) {
					if (StringUtil.isNotEmpty(group.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + group.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(JiTai jitai) {
		Long count = jiTaiRepository.count(new Specification<JiTai>() {

			@Override
			public Predicate toPredicate(Root<JiTai> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (jitai != null) {
					if (StringUtil.isNotEmpty(jitai.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + jitai.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public JiTai findByJiTaiName(String name) {
		return jiTaiRepository.findByJiTaiName(name);
	}

	@Override
	public void save(JiTai entrepot) {
		jiTaiRepository.save(entrepot);
	}

	@Override
	public JiTai findById(Integer id) {
		return jiTaiRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		jiTaiRepository.delete(id);
	}

	@Override
	public List<JiTai> findByName(String string) {
		return jiTaiRepository.findByName(string);
	}

	@Override
	public List<JiTai> findAll() {
		return jiTaiRepository.findAll();
	}

}
